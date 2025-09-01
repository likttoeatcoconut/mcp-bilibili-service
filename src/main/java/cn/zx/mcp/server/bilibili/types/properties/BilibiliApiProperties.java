package cn.zx.mcp.server.bilibili.types.properties;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "bilibili.api")
@Component
@Slf4j
public class BilibiliApiProperties {

    private String cookie;
    private String csrf;

    // bean注入后打印信息
    @PostConstruct
    public void init() {
        log.info("BilibiliApiProperties: cookie={}, csrf={}", cookie, csrf);
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getCsrf() {
        return csrf;
    }

    public void setCsrf(String csrf) {
        this.csrf = csrf;
    }
}
