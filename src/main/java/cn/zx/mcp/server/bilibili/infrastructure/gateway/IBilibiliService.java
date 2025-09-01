package cn.zx.mcp.server.bilibili.infrastructure.gateway;

import cn.zx.mcp.server.bilibili.infrastructure.gateway.dto.CommentListResponseDTO;
import cn.zx.mcp.server.bilibili.infrastructure.gateway.dto.CommentResponseDTO;
import cn.zx.mcp.server.bilibili.infrastructure.gateway.dto.VideoResponseDTO;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface IBilibiliService {

    @Headers({
            "accept: */*",
            "accept-language: zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6",
            "origin: https://www.bilibili.com",
            "priority: u=1, i",
            "referer: https://www.bilibili.com/",
            "sec-ch-ua: \"Not)A;Brand\";v=\"8\", \"Chromium\";v=\"138\", \"Microsoft Edge\";v=\"138\"",
            "sec-ch-ua-mobile: ?0",
            "sec-ch-ua-platform: \"Windows\"",
            "sec-fetch-dest: empty",
            "sec-fetch-mode: cors",
            "sec-fetch-site: same-site",
            "user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36 Edg/138.0.0.0"
    })
    @GET("/x/web-interface/wbi/index/top/feed/rcmd?web_location=1430650&y_num=4&fresh_type=3&feed_version=V8&fresh_idx_1h=2&fetch_row=1&fresh_idx=2&brush=0&device=win&homepage_ver=1&ps=10&last_y_num=4&screen=906-674&seo_info=&last_showlist=av_114945890980825,av_114954111816756,av_114941176647118,av_114945857422669,av_n_114913376734531,ad_5614,av_114947551991715,av_n_114941461796206,av_n_114947250001846,av_n_114906665980026%3Bav_n_114924986635761,av_n_114935287779286,av_n_114907253049538,av_n_114949296754219,av_n_114952853525709,ad_n_5637,av_n_114917738812051,av_n_114827041184017,av_n_114845898773470,av_n_114952350205245,av_n_114923728341723,av_n_114930204346466&uniq_id=108641565034&w_rid=8b6ff8452b9f67df1e890238b9ad696b&wts=1754119379")
    Call<VideoResponseDTO> getRecommendVideos(@Header("Cookie") String cookieValue);

    @Headers({
            "referer: https://www.bilibili.com",
            "content-type: application/x-www-form-urlencoded",
            "user-agent: Mozilla/5.0"
    })
    @POST("/x/v2/reply/add")
    @FormUrlEncoded
    Call<CommentResponseDTO> addComment(@FieldMap Map<String, Object> fields,
                                        @Header("Cookie") String cookieValue);

    @Headers({
            "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.51 Safari/537.36 Edg/99.0.1150.39"
    })
    @GET("/x/v2/reply/main")
    Call<CommentListResponseDTO> getCommentList(@QueryMap Map<String, Object> params,
                                               @Header("Cookie") String cookieValue);
}