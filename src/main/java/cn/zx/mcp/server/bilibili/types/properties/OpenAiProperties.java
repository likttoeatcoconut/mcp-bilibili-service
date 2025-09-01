package cn.zx.mcp.server.bilibili.types.properties;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.ai.openai")
@Component
@Slf4j
@Data
public class OpenAiProperties {

    private String baseUrl;
    private String apiKey;
    private Chat chat = new Chat();

    @Data
    public static class Chat {
        private Options options = new Options();
    }

    @Data
    public static class Options {
        private String model;
        private Double topP;
        private Double temperature;
        private Integer maxTokens;
    }

    @PostConstruct
    public void init() {
        log.info("OpenAiProperties: baseUrl={}, apiKey={}, model={}, topP={}, temperature={}, maxTokens={}", 
                baseUrl, apiKey, chat.options.model, chat.options.topP, chat.options.temperature, chat.options.maxTokens);
    }
}