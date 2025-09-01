package cn.zx.mcp.server.bilibili.domain.model.Comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Bilibili评论响应DTO
 * 实际返回的JSON格式: {"code":0,"message":"0","ttl":1}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class BilibiliCommentResponse {
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