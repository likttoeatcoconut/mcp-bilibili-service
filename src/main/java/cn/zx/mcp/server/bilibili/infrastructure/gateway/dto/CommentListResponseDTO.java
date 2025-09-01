package cn.zx.mcp.server.bilibili.infrastructure.gateway.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Bilibili评论列表响应DTO
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentListResponseDTO {
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

    /**
     * 评论数据
     */
    private CommentData data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CommentData {
        private List<CommentItem> replies;
        /**
         * 顶级评论总数
         */
        private Integer allCount;
        
        /**
         * 置顶评论
         */
        private UpperTop upper;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CommentItem {
        /**
         * 评论ID
         */
        private Long rpid;
        
        /**
         * 视频ID
         */
        private Long oid;
        
        /**
         * 评论类型
         */
        private Integer type;
        
        /**
         * 用户ID
         */
        private Long mid;
        
        /**
         * 根评论ID
         */
        private Long root;
        
        /**
         * 父评论ID
         */
        private Long parent;
        
        /**
         * 对话ID
         */
        private Long dialog;
        
        /**
         * 评论数
         */
        private Integer count;
        
        /**
         * 回复数
         */
        private Integer rcount;
        
        /**
         * 评论状态
         */
        private Integer state;
        
        /**
         * 粉丝等级
         */
        private Integer fansgrade;
        
        /**
         * 属性
         */
        private Integer attr;
        
        /**
         * 创建时间
         */
        private Long ctime;
        
        /**
         * 点赞数
         */
        private Integer like;
        
        /**
         * 动作
         */
        private Integer action;
        
        /**
         * 评论内容
         */
        private Content content;
        
        /**
         * 用户信息
         */
        private Member member;
        
        /**
         * 回复列表
         */
        private List<CommentItem> replies;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Content {
        /**
         * 评论消息内容
         */
        private String message;
        
        /**
         * 提到的用户列表
         */
        private List<Member> members;
        
        /**
         * 表情
         */
        private Object emote;
        
        /**
         * 跳转链接
         */
        private Object jump_url;
        
        /**
         * 最大行数
         */
        private Integer max_line;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Member {
        /**
         * 用户ID
         */
        private Long mid;
        
        /**
         * 用户名
         */
        private String uname;
        
        /**
         * 性别
         */
        private String sex;
        
        /**
         * 签名
         */
        private String sign;
        
        /**
         * 头像
         */
        private String avatar;
        
        /**
         * 等级信息
         */
        private LevelInfo level_info;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class LevelInfo {
        /**
         * 当前等级
         */
        private Integer current_level;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UpperTop {
        private CommentItem reply;
    }
}