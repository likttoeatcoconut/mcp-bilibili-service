package cn.zx.mcp.server.bilibili.test;

import cn.zx.mcp.server.bilibili.domain.model.Comment.BilibiliCommentRequest;
import cn.zx.mcp.server.bilibili.domain.model.Comment.BilibiliCommentResponse;
import cn.zx.mcp.server.bilibili.domain.model.commentList.BilibiliCommentListRequest;
import cn.zx.mcp.server.bilibili.domain.model.commentList.BilibiliCommentListResponse;
import cn.zx.mcp.server.bilibili.domain.model.generate.BilibiliGenerateCommentRequest;
import cn.zx.mcp.server.bilibili.domain.model.generate.BilibiliGenerateCommentResponse;
import cn.zx.mcp.server.bilibili.domain.model.video.BilibiliFunctionResponse;
import cn.zx.mcp.server.bilibili.domain.service.BilibiliMcpService;
import cn.zx.mcp.server.bilibili.infrastructure.gateway.IBilibiliService;
import cn.zx.mcp.server.bilibili.infrastructure.gateway.dto.CommentListRequestDTO;
import cn.zx.mcp.server.bilibili.infrastructure.gateway.dto.CommentListResponseDTO;
import cn.zx.mcp.server.bilibili.infrastructure.gateway.dto.VideoResponseDTO;
import cn.zx.mcp.server.bilibili.types.properties.BilibiliApiProperties;
import cn.zx.mcp.server.bilibili.types.properties.OpenAiProperties;
import jakarta.annotation.Resource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BilibiliApiTest {

    private final Logger log = LoggerFactory.getLogger(BilibiliApiTest.class);

    @Autowired
    private IBilibiliService bilibiliService;
    @Autowired
    private BilibiliMcpService bilibiliMcpService;
    @Autowired
    private BilibiliApiProperties bilibiliApiProperties;
    @Resource
    private OpenAiProperties openAiProperties;
    @Autowired
    private ChatClient client;
    private OpenAiChatModel openAiChatModel;
    @Before
    public void init() {
        OpenAiApi openAiApi = OpenAiApi.builder()
                .baseUrl(openAiProperties.getBaseUrl())
                .apiKey(openAiProperties.getApiKey())
                .build();
        OpenAiChatOptions openAiChatOptions = OpenAiChatOptions.builder()
                .model(openAiProperties.getChat().getOptions().getModel())
                .maxTokens(openAiProperties.getChat().getOptions().getMaxTokens())
                .topP(openAiProperties.getChat().getOptions().getTopP())
                .temperature(openAiProperties.getChat().getOptions().getTemperature())
                .build();
        this.openAiChatModel = OpenAiChatModel.builder()
                .openAiApi(openAiApi)
                .defaultOptions(openAiChatOptions)
                .build();
    }

    @Test
    public void test_chat(){
        ChatClient chatClient = ChatClient.builder(openAiChatModel).defaultSystem("请根据用户提供的视频标题进行评论。").build();
        String content = chatClient.prompt("斯诺克罕见翻车现场，大师们的尴尬瞬间，每一击都令人捧腹大笑。").call().content();
        System.out.println(content);
    }

    @Test
    public void test_chat1(){
        String content = client.prompt("斯诺克罕见翻车现场，大师们的尴尬瞬间，每一击都令人捧腹大笑。").call().content();
        System.out.println(content);
    }

    @Test
    public void test_chat2(){
        OpenAiApi openAiApi = OpenAiApi.builder().baseUrl("https://api.siliconflow.cn").apiKey("【平台的key】").build();
        OpenAiChatOptions openAiChatOptions = OpenAiChatOptions.builder()
                .model("【模型名称】")
                .maxTokens(2048)
                .topP(0.8)
                .temperature(0.7)
                .build();
        OpenAiChatModel openAiChatModel1 = OpenAiChatModel.builder()
                .openAiApi(openAiApi)
                .defaultOptions(openAiChatOptions)
                .build();

        ChatClient chatClient = ChatClient.builder(openAiChatModel1).defaultSystem("请根据用户提供的视频标题进行评论。").build();
        String content = chatClient.prompt("木桩也能飞升！完美的利用游戏机制。").call().content();
        System.out.println(content);
    }

    @Test
    public void test_commentG() throws IOException {
        List<String> commentList = new ArrayList<>();
        commentList.add("水不会碰到岩石而自责，只是记住了岩石的位置。我看到这句话的时候突然茅塞顿开[喜极而泣][喜极而泣][喜极而泣]");
        commentList.add("说白了就是，行动才能打破焦虑，行动先于犹豫，你犹豫多了总有千百个借口，你先行动哪怕行动者再微小也是有用的，空想不做是大忌");
        BilibiliGenerateCommentRequest bilibiliGenerateCommentRequest = BilibiliGenerateCommentRequest.builder().title("如何培养如水般的自律——李小龙的哲学洞察").hotCommentList(commentList).build();
        BilibiliGenerateCommentResponse bilibiliGenerateCommentResponse = bilibiliMcpService.generateComment(bilibiliGenerateCommentRequest);
        System.out.println(bilibiliGenerateCommentResponse);
    }
    @Test
    public void test_getRecommendVideos() throws IOException {
        // 1. 准备Cookie
        String cookie = "【B站cookie】";

        // 2. 调用接口
        Call<VideoResponseDTO> call = bilibiliService.getRecommendVideos(cookie);
        Response<VideoResponseDTO> response = call.execute();

        // 3. 验证结果
        if (response.isSuccessful()) {
            VideoResponseDTO videoResponseDTO = response.body();
            log.info("获取推荐视频成功，响应码: {}", videoResponseDTO.getCode());
            log.info("推荐视频数量: {}", videoResponseDTO.getData().getItem().size());
            
            // 打印第一个视频的信息
            if (!videoResponseDTO.getData().getItem().isEmpty()) {
                VideoResponseDTO.VideoItem firstVideo = videoResponseDTO.getData().getItem().get(0);
                log.info("第一个视频信息: 标题={}, BV号={}, 作者={}, 播放量={}", 
                        firstVideo.getTitle(), 
                        firstVideo.getBvid(), 
                        firstVideo.getOwner().getName(), 
                        firstVideo.getStat().getView());
            }
            
            // 打印完整响应（可选，数据量可能较大）
            // log.info("完整响应: {}", JSON.toJSONString(videoResponseDTO));
        } else {
            log.error("获取推荐视频失败，错误码: {}, 错误信息: {}", response.code(), response.errorBody().string());
        }
    }

    @Test
    public void test_bilibili_1() throws IOException {
        BilibiliFunctionResponse recommendedVideo = bilibiliMcpService.getRecommendedVideo();
        System.out.println(recommendedVideo);
    }
    @Test
    public void test_bilibili_2() throws IOException {
        BilibiliCommentRequest dto = BilibiliCommentRequest.builder().oid(114625899135018L).message("测试1").build();

        // 测试发送评论
        BilibiliCommentResponse recommendedVideo = bilibiliMcpService.sendComment(dto);
        System.out.println(recommendedVideo);
    }

    @Test
    public void test_bilibili_3() throws IOException {
        // 测试获取热评
        BilibiliCommentListRequest dto = BilibiliCommentListRequest.builder().oid(979849123L).build();
        BilibiliCommentListResponse recommendedVideo = bilibiliMcpService.getCommentList(dto);
        System.out.println(recommendedVideo);
    }


    @Test
    public void test_getCommentList() throws IOException {

        // 2. 准备参数 - 使用CommentListRequestDTO
        CommentListRequestDTO requestDTO = CommentListRequestDTO.builder()
                .oid(979849123L)
                .build();
        Map<String, Object> params = requestDTO.toMap();

        // 3. 调用接口
        Call<CommentListResponseDTO> call = bilibiliService.getCommentList(params, bilibiliApiProperties.getCookie());
        Response<CommentListResponseDTO> response = call.execute();

        // 4. 验证结果
        if (response.isSuccessful()) {
            CommentListResponseDTO commentListResponseDTO = response.body();
            log.info("获取评论列表成功，响应码: {}", commentListResponseDTO.getCode());
            if (commentListResponseDTO.getData() != null && commentListResponseDTO.getData().getReplies() != null) {
                log.info("评论数量: {}", commentListResponseDTO.getData().getReplies().size());
                
                // 打印第一条顶级评论信息
                if (!commentListResponseDTO.getData().getReplies().isEmpty()) {
                    CommentListResponseDTO.CommentItem firstComment = commentListResponseDTO.getData().getReplies().get(0);
                    log.info("第一条顶级评论信息: 用户={}, 内容={}, 点赞数={}", 
                            firstComment.getMember().getUname(), 
                            firstComment.getContent().getMessage(), 
                            firstComment.getLike());
                }
            } else {
                log.info("评论数据为空");
            }
        } else {
            log.error("获取评论列表失败，错误码: {}, 错误信息: {}", response.code(), response.errorBody().string());
        }
    }
}