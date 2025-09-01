package cn.zx.mcp.server.bilibili.infrastructure.gateway.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Bilibili评论请求DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentRequestDTO {
    /**
     * 平台类型，固定为1
     */
    @Builder.Default
    private Integer plat = 1;
    
    /**
     * 视频ID
     */
    private Long oid;
    
    /**
     * 评论类型，1为视频评论
     */
    @Builder.Default
    private Integer type = 1;
    
    /**
     * 评论内容
     */
    private String message;
    
    /**
     * @用户的mid映射，JSON格式
     */
    @JsonProperty("at_name_to_mid")
    @Builder.Default
    private String atNameToMid = "{}";
    
    /**
     * 来源，固定为main_web
     */
    @JsonProperty("gaia_source")
    @Builder.Default
    private String gaiaSource = "main_web";
    
    /**
     * CSRF令牌
     */
    private String csrf;
    
    /**
     * 统计信息，JSON格式
     */
    @Builder.Default
    private String statistics = "{\"appId\":100,\"platform\":5}";

    public Map<String, Object> getFieldsMap(){
        return Map.of("plat", plat, "oid", oid, "type", type, "message", message, "at_name_to_mid", atNameToMid, "gaia_source", gaiaSource, "csrf", csrf, "statistics", statistics);
    }
}