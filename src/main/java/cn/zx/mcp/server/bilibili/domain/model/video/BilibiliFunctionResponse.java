package cn.zx.mcp.server.bilibili.domain.model.video;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BilibiliFunctionResponse {
    
    @JsonProperty(required = true, value = "code")
    @JsonPropertyDescription("code")
    private Integer code;

    @JsonProperty(required = true, value = "msg")
    @JsonPropertyDescription("msg")
    private String msg;

    @JsonProperty(required = true, value = "videoData")
    @JsonPropertyDescription("videoData")
    private VideoData videoData;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class VideoData {
        @JsonProperty(required = true, value = "id")
        @JsonPropertyDescription("内部视频唯一标识")
        private Long id;

        @JsonProperty(required = true, value = "bvid")
        @JsonPropertyDescription("视频唯一标识")
        private String bvid;

        @JsonProperty(required = true, value = "title")
        @JsonPropertyDescription("视频标题")
        private String title;

        @JsonProperty(required = true, value = "duration")
        @JsonPropertyDescription("视频时长（秒）")
        private Integer duration;

        @JsonProperty(required = true, value = "view")
        @JsonPropertyDescription("播放量")
        private Integer view;

        @JsonProperty(required = true, value = "like")
        @JsonPropertyDescription("点赞数")
        private Integer like;

        @JsonProperty(required = true, value = "danmaku")
        @JsonPropertyDescription("弹幕数")
        private Integer danmaku;

        @JsonProperty(required = true, value = "ownerName")
        @JsonPropertyDescription("作者名称")
        private String ownerName;
    }
}
