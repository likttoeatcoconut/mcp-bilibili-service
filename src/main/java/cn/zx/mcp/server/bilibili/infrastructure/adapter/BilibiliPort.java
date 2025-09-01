package cn.zx.mcp.server.bilibili.infrastructure.adapter;

import cn.zx.mcp.server.bilibili.domain.adapter.IBilibiliPort;
import cn.zx.mcp.server.bilibili.domain.model.commentList.BilibiliCommentListRequest;
import cn.zx.mcp.server.bilibili.domain.model.Comment.BilibiliCommentRequest;
import cn.zx.mcp.server.bilibili.domain.model.Comment.BilibiliCommentResponse;
import cn.zx.mcp.server.bilibili.domain.model.commentList.BilibiliCommentListResponse;
import cn.zx.mcp.server.bilibili.domain.model.generate.BilibiliGenerateCommentRequest;
import cn.zx.mcp.server.bilibili.domain.model.generate.BilibiliGenerateCommentResponse;
import cn.zx.mcp.server.bilibili.domain.model.video.BilibiliFunctionResponse;
import cn.zx.mcp.server.bilibili.infrastructure.gateway.IBilibiliService;
import cn.zx.mcp.server.bilibili.infrastructure.gateway.dto.*;
import cn.zx.mcp.server.bilibili.types.properties.BilibiliApiProperties;
import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BilibiliPort implements IBilibiliPort {

    @Resource
    private IBilibiliService bilibiliService;

    @Resource
    private BilibiliApiProperties bilibiliApiProperties;

    @Autowired
    private ChatClient client;

    private static final String PROMPT_TEMPLATE = 
        "请根据以下视频标题和热门评论，生成一条新的评论。要求评论风格与热门评论相似，但内容原创。\n\n" +
        "视频标题：%s\n\n" +
        "热门评论列表：\n%s\n\n" +
        "请只输出一条评论，不要包含其他内容。";

    @Override
    public BilibiliFunctionResponse getRecommendedVideo() throws IOException {

        Call<VideoResponseDTO> call = bilibiliService.getRecommendVideos(bilibiliApiProperties.getCookie());
        Response<VideoResponseDTO> response = call.execute();

        log.info("请求一个随机推荐视频 \nreq:{}", JSON.toJSONString(response));

        if (response.isSuccessful()) {
            VideoResponseDTO videoResponseDTO = response.body();
            if (null == videoResponseDTO) return null;
            // 选取第一视频的信息返回
            List<VideoResponseDTO.VideoItem> collect = videoResponseDTO.getData().getItem().stream().filter(videoItem -> videoItem.getId() != 0).toList();
            VideoResponseDTO.VideoItem videoItem = collect.get(0);
            BilibiliFunctionResponse bilibiliFunctionResponse = BilibiliFunctionResponse.builder()
                    .code(videoResponseDTO.getCode())
                    .msg(videoResponseDTO.getMessage())
                    .videoData(
                            BilibiliFunctionResponse.VideoData.builder()
                                    .id(videoItem.getId())
                                    .bvid(videoItem.getBvid())
                                    .view(videoItem.getStat().getView())
                                    .like(videoItem.getStat().getLike())
                                    .danmaku(videoItem.getStat().getDanmaku())
                                    .title(videoItem.getTitle())
                                    .duration(videoItem.getDuration())
                                    .ownerName(videoItem.getOwner().getName())
                                    .build()
                    ).build();

            return bilibiliFunctionResponse;
        }

        return null;
    }

    @Override
    public BilibiliCommentResponse addComment(BilibiliCommentRequest bilibiliCommentRequest) throws IOException {
        CommentRequestDTO commentRequestDTO = CommentRequestDTO.builder()
                .oid(bilibiliCommentRequest.getOid())
                .message(bilibiliCommentRequest.getMessage())
                .csrf(bilibiliApiProperties.getCsrf())
                .build();
        Call<CommentResponseDTO> commentResponseDTOCall = bilibiliService.addComment(commentRequestDTO.getFieldsMap(), bilibiliApiProperties.getCookie());
        Response<CommentResponseDTO> response = commentResponseDTOCall.execute();

        log.info("添加评论 \nreq:{}", JSON.toJSONString(response));
        if (response.isSuccessful()) {
            CommentResponseDTO commentResponseDTO = response.body();
            if (null == commentResponseDTO) return null;
            BilibiliCommentResponse bilibiliCommentResponse = BilibiliCommentResponse.builder()
                    .code(commentResponseDTO.getCode())
                    .message(commentResponseDTO.getMessage())
                    .ttl(commentResponseDTO.getTtl())
                    .build();
            return bilibiliCommentResponse;
        }
        return null;
    }

    @Override
    public BilibiliCommentListResponse getCommentList(BilibiliCommentListRequest bilibiliCommentListRequest) throws IOException {
        CommentListRequestDTO build = CommentListRequestDTO.builder().oid(bilibiliCommentListRequest.getOid()).build();
        Call<CommentListResponseDTO> commentListResponseDTORequest = bilibiliService.getCommentList(build.toMap(), bilibiliApiProperties.getCookie());
        Response<CommentListResponseDTO> response = commentListResponseDTORequest.execute();
        log.info("获取评论列表 \nreq:{}", JSON.toJSONString(response));
        if (response.isSuccessful()) {
            CommentListResponseDTO commentListResponseDTO = response.body();
            if (null == commentListResponseDTO) return null;
            List<String> collect = commentListResponseDTO.getData().getReplies().stream().map(reply -> reply.getContent().getMessage()).toList();
            return BilibiliCommentListResponse.builder().commentList(collect).build();
        }
        return null;
    }

    @Override
    public BilibiliGenerateCommentResponse generateComment(BilibiliGenerateCommentRequest bilibiliGenerateCommentRequest) {
        if (bilibiliGenerateCommentRequest == null) {
            log.error("generateComment: received null request");
            return BilibiliGenerateCommentResponse.builder().comment("生成评论失败：空请求").build();
        }

        if (bilibiliGenerateCommentRequest.getHotCommentList() == null || bilibiliGenerateCommentRequest.getHotCommentList().isEmpty()) {
            log.warn("generateComment: empty hot comment list");
        }

        // 构建提示词
        String prompt = String.format(PROMPT_TEMPLATE,
                bilibiliGenerateCommentRequest.getTitle() != null ? bilibiliGenerateCommentRequest.getTitle() : "无标题",
                bilibiliGenerateCommentRequest.getHotCommentList() != null && !bilibiliGenerateCommentRequest.getHotCommentList().isEmpty()
                    ? String.join("\n", bilibiliGenerateCommentRequest.getHotCommentList())
                    : "无热门评论");

        if (log.isDebugEnabled()) {
            log.debug("生成评论的提示词: {}", prompt);
        }

        // 调用AI生成评论
        String generatedComment;
        try {
            generatedComment = client.prompt()
                    .user(prompt)
                    .call()
                    .content();
        } catch (Exception e) {
            log.error("生成评论时发生错误", e);
            return BilibiliGenerateCommentResponse.builder().comment("生成评论失败：" + e.getMessage()).build();
        }

        // 返回生成的评论
        return BilibiliGenerateCommentResponse.builder()
                .comment(generatedComment)
                .build();
    }
}
