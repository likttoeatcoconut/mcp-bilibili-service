package cn.zx.mcp.server.bilibili.domain.service;

import cn.zx.mcp.server.bilibili.domain.adapter.IBilibiliPort;
import cn.zx.mcp.server.bilibili.domain.model.Comment.BilibiliCommentRequest;
import cn.zx.mcp.server.bilibili.domain.model.Comment.BilibiliCommentResponse;
import cn.zx.mcp.server.bilibili.domain.model.commentList.BilibiliCommentListRequest;
import cn.zx.mcp.server.bilibili.domain.model.commentList.BilibiliCommentListResponse;
import cn.zx.mcp.server.bilibili.domain.model.generate.BilibiliGenerateCommentRequest;
import cn.zx.mcp.server.bilibili.domain.model.generate.BilibiliGenerateCommentResponse;
import cn.zx.mcp.server.bilibili.domain.model.video.BilibiliFunctionResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class BilibiliMcpService {

    @Resource
    private IBilibiliPort port;

    @Tool(description = "获取一个B站(bilibili)推荐的视频")
    public BilibiliFunctionResponse getRecommendedVideo() throws IOException {
        log.info("获取一个B站推荐的视频，start");
        return port.getRecommendedVideo();
    }

    @Tool(description = "在B站(bilibili)视频下发送评论")
    public BilibiliCommentResponse sendComment(BilibiliCommentRequest bilibiliCommentRequest) throws IOException {
        log.info("发送评论，start");
        return port.addComment(bilibiliCommentRequest);
    }

    @Tool(description = "获取B站(bilibili)视频的评论列表")
    public BilibiliCommentListResponse getCommentList(BilibiliCommentListRequest bilibiliCommentListRequest) throws IOException {
        log.info("获取B站(bilibili)视频的评论列表，start");
        return port.getCommentList(bilibiliCommentListRequest);
    }

    @Tool(description = "生成B站(bilibili)视频的评论")
    public BilibiliGenerateCommentResponse generateComment(BilibiliGenerateCommentRequest bilibiliGenerateCommentRequest) throws IOException {
        log.info("生成B站(bilibili)视频的评论，start");
        return port.generateComment(bilibiliGenerateCommentRequest);
    }

}
