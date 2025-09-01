package cn.zx.mcp.server.bilibili.domain.model.commentList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
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
public class BilibiliCommentListResponse {
    @JsonProperty(required = true, value = "commentList")
    @JsonPropertyDescription("热门评论列表")
    private List<String> commentList;

}