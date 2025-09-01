package cn.zx.mcp.server.bilibili.domain.adapter;

import cn.zx.mcp.server.bilibili.domain.model.commentList.BilibiliCommentListRequest;
import cn.zx.mcp.server.bilibili.domain.model.Comment.BilibiliCommentRequest;
import cn.zx.mcp.server.bilibili.domain.model.Comment.BilibiliCommentResponse;
import cn.zx.mcp.server.bilibili.domain.model.commentList.BilibiliCommentListResponse;
import cn.zx.mcp.server.bilibili.domain.model.generate.BilibiliGenerateCommentRequest;
import cn.zx.mcp.server.bilibili.domain.model.generate.BilibiliGenerateCommentResponse;
import cn.zx.mcp.server.bilibili.domain.model.video.BilibiliFunctionResponse;
import cn.zx.mcp.server.bilibili.infrastructure.gateway.dto.CommentListResponseDTO;

import java.io.IOException;

public interface IBilibiliPort {

    BilibiliFunctionResponse getRecommendedVideo() throws IOException;

    BilibiliCommentResponse addComment(BilibiliCommentRequest bilibiliCommentRequest) throws IOException;

    BilibiliCommentListResponse getCommentList(BilibiliCommentListRequest bilibiliCommentListRequest) throws IOException;

    BilibiliGenerateCommentResponse generateComment(BilibiliGenerateCommentRequest bilibiliGenerateCommentRequest);
}
