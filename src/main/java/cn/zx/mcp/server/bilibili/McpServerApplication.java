package cn.zx.mcp.server.bilibili;


import cn.zx.mcp.server.bilibili.domain.service.BilibiliMcpService;
import cn.zx.mcp.server.bilibili.infrastructure.gateway.IBilibiliService;
import cn.zx.mcp.server.bilibili.types.properties.BilibiliApiProperties;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@SpringBootApplication
public class McpServerApplication implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(McpServerApplication.class);
    @Resource
    private BilibiliApiProperties bilibiliApiProperties;

    public static void main(String[] args) {
        SpringApplication.run(McpServerApplication.class, args);
    }

    @Bean
    public IBilibiliService bilibiliService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.bilibili.com")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit.create(IBilibiliService.class);
    }

    @Bean
    public ToolCallbackProvider bilibiliTools(BilibiliMcpService bilibiliMcpService) {
        return MethodToolCallbackProvider.builder().toolObjects(bilibiliMcpService).build();
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("check bilibili cookie ...");
        if (bilibiliApiProperties.getCookie() == null) {
            log.warn("bilibili cookie key is null, please set it in application.yml");
        } else {
            log.info("bilibili cookie  key is {}", bilibiliApiProperties.getCookie());
        }
    }

}
