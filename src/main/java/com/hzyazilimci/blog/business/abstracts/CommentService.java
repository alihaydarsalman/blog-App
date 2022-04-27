package com.hzyazilimci.blog.business.abstracts;

import com.hzyazilimci.blog.core.utilities.results.DataResult;
import com.hzyazilimci.blog.core.utilities.results.Result;
import com.hzyazilimci.blog.entities.dtos.GetCommentDto;
import com.hzyazilimci.blog.entities.requests.create.CreateCommentRequest;
import com.hzyazilimci.blog.entities.requests.update.UpdateCommentRequest;
import java.util.List;

public interface CommentService {

    DataResult<List<GetCommentDto>> getAll();
    DataResult<GetCommentDto> getById(int postId,int commentId);
    DataResult<List<GetCommentDto>> getByPostId(int postId);
    Result submitComment(CreateCommentRequest createCommentRequest);
    Result updateComment(UpdateCommentRequest updateCommentRequest);
    Result deleteComment(int commentId);
}
