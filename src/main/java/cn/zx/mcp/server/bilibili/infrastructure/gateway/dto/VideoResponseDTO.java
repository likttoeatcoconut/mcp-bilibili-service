package cn.zx.mcp.server.bilibili.infrastructure.gateway.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Bilibili视频推荐响应DTO
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoResponseDTO {
    private Integer code;
    private String message;
    private Integer ttl;
    private VideoData data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VideoData {
        private List<VideoItem> item;
        private Object business_card;
        private Object floor_info;
        private Object user_feature;
        private Double preload_expose_pct;
        private Double preload_floor_expose_pct;
        private Long mid;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VideoItem {
        private Long id;
        private String bvid;
        private Long cid;
        @JsonProperty("goto")
        private String gotoStr;
        private String uri;
        private String pic;
        private String pic_4_3;
        private String title;
        private Integer duration;
        private Long pubdate;
        private Owner owner;
        private Stat stat;
        private Object av_feature;
        private Integer is_followed;
        private RcmdReason rcmd_reason;
        private Integer show_info;
        private String track_id;
        private Integer pos;
        private Object room_info;
        private Object ogv_info;
        private BusinessInfo business_info;
        private Integer is_stock;
        private Integer enable_vt;
        private String vt_display;
        private Integer dislike_switch;
        private Integer dislike_switch_pc;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Owner {
        private Long mid;
        private String name;
        private String face;
    }

    @Data
    public static class Stat {
        private Integer view;
        private Integer like;
        private Integer danmaku;
        private Integer vt;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RcmdReason {
        private String content;
        private Integer reason_type;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BusinessInfo {
        private Long id;
        private String contract_id;
        private Long res_id;
        private Long asg_id;
        private Integer pos_num;
        private String name;
        private String pic;
        private String litpic;
        private String url;
        private Integer style;
        private Boolean is_ad;
        private String agency;
        private String label;
        private String intro;
        private Integer creative_type;
        private String request_id;
        private Long creative_id;
        private Long src_id;
        private Integer area;
        private Boolean is_ad_loc;
        private String ad_cb;
        private String title;
        private Integer server_type;
        private Integer cm_mark;
        private Long stime;
        private String mid;
        private Integer activity_type;
        private Long epid;
        private String sub_title;
        private String ad_desc;
        private String adver_name;
        private Boolean null_frame;
        private String pic_main_color;
        private Integer card_type;
        private BusinessMark business_mark;
        private Inline inline;
        private String operater;
        private Integer jump_target;
        private Object show_urls;
        private Object click_urls;
        private FeedbackPanel feedback_panel;
        private Integer sales_type;
        private Object rcmd_reason_style;
        private PcButton pc_button;
        private String desc;
        private Object wx_program_info;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BusinessMark {
        private String bg_border_color;
        private String bg_color;
        private String bg_color_night;
        private String border_color;
        private String border_color_night;
        private Integer img_height;
        private String img_url;
        private Integer img_width;
        private String text;
        private String text_color;
        private String text_color_night;
        private Integer type;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Inline {
        private Integer inline_use_same;
        private Integer inline_type;
        private String inline_url;
        private Integer inline_barrage_switch;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FeedbackPanel {
        private String close_rec_tips;
        private List<FeedbackPanelDetail> feedback_panel_detail;
        private String open_rec_tips;
        private String panel_type_text;
        private String toast;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FeedbackPanelDetail {
        private String icon_url;
        private Integer jump_type;
        private String jump_url;
        private Integer module_id;
        private List<SecondaryPanel> secondary_panel;
        private String sub_text;
        private String text;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SecondaryPanel {
        private Integer reason_id;
        private String text;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PcButton {
        private String button_text;
    }
}