package cn.zx.mcp.server.bilibili.domain.model.generate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Bilibili评论列表请求DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BilibiliGenerateCommentRequest {
    @JsonProperty(required = true, value = "title")
    @JsonPropertyDescription("视频标题")
    private String title;

    @JsonProperty(required = true, value = "hotCommentList")
    @JsonPropertyDescription("参考评论的列表")
    private List<String> hotCommentList;

}