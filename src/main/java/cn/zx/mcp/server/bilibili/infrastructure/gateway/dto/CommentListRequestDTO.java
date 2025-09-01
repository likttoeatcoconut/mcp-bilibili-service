package cn.zx.mcp.server.bilibili.infrastructure.gateway.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.HashMap;

/**
 * Bilibili评论列表请求DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentListRequestDTO {
    
    /**
     * 视频ID (oid)
     */
    private Long oid;
    
    /**
     * JSONP标识，默认为"jsonp"
     */
    @Builder.Default
    private String jsonp = "jsonp";
    
    /**
     * 下一页标识，默认为1
     */
    @Builder.Default
    private Integer next = 1;
    
    /**
     * 评论类型，默认为1 (视频评论)
     */
    @Builder.Default
    private Integer type = 1;
    
    /**
     * 排序模式，默认为3
     */
    @Builder.Default
    private Integer mode = 3;
    
    /**
     * 平台类型，默认为1
     */
    @Builder.Default
    private Integer plat = 1;
    
    /**
     * 时间戳，默认为1146258991018
     */
    @Builder.Default
    private Long timestamp = 1146258991018L;
    
    /**
     * 将对象属性转换为Map，用于查询参数
     * @return 包含所有属性的Map
     */
    public Map<String, Object> toMap() {
        Map<String, Object> params = new HashMap<>();
        params.put("oid", this.oid);
        params.put("jsonp", this.jsonp);
        params.put("next", this.next);
        params.put("type", this.type);
        params.put("mode", this.mode);
        params.put("plat", this.plat);
        params.put("_", this.timestamp);
        return params;
    }
}