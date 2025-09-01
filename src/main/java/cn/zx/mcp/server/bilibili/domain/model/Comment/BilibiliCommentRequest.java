package cn.zx.mcp.server.bilibili.domain.model.Comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Bilibili评论请求DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BilibiliCommentRequest {
    @JsonProperty(required = true, value = "oid")
    @JsonPropertyDescription("视频id")
    private Long oid;
    @JsonProperty(required = true, value = "message")
    @JsonPropertyDescription("评论内容")
    private String message;

}