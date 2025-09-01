package cn.zx.mcp.server.bilibili.config;

import jakarta.annotation.Resource;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import cn.zx.mcp.server.bilibili.types.properties.OpenAiProperties;

@Component
@Slf4j
public class BilibiliCommentConfig {
    
    @Resource
    private OpenAiProperties openAiProperties;
    
    @Bean
    public ChatClient chatClient(){
        log.info("初始化OpenAiChatClient");
        log.info("OpenAiProperties: {}", openAiProperties);
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
        OpenAiChatModel openAiChatModel = OpenAiChatModel.builder()
                .openAiApi(openAiApi)
                .defaultOptions(openAiChatOptions)
                .build();
        return ChatClient.builder(openAiChatModel).defaultSystem("请根据用户提供的视频标题进行评论。").build();
    }
}