package cn.zx.mcp.server.bilibili.infrastructure.gateway.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Bilibili评论响应DTO
 * 实际返回的JSON格式: {"code":0,"message":"0","ttl":1}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentResponseDTO {
    /**
     * 响应码，0表示成功
     */
    private Integer code;
    
    /**
     * 响应消息
     */
    private String message;
    
    /**
     * Time to live
     */
    private Integer ttl;
}