package cn.zx.mcp.server.bilibili.domain.model.generate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class BilibiliGenerateCommentResponse {
    @JsonProperty(required = true, value = "commentList")
    @JsonPropertyDescription("生成的评论")
    private String comment;

}