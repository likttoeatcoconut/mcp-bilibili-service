package cn.zx.mcp.server.bilibili.domain.model.commentList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Bilibili评论列表请求DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BilibiliCommentListRequest {
    @JsonProperty(required = true, value = "oid")
    @JsonPropertyDescription("视频id")
    private Long oid;
}