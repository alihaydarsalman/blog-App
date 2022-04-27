package com.hzyazilimci.blog.business.abstracts;

import com.hzyazilimci.blog.core.utilities.results.DataResult;
import com.hzyazilimci.blog.core.utilities.results.Result;
import com.hzyazilimci.blog.entities.dtos.blogDtos.GetPostDto;
import com.hzyazilimci.blog.entities.requests.create.CreatePostRequest;
import com.hzyazilimci.blog.entities.requests.update.UpdatePostRequest;
import com.hzyazilimci.blog.entities.sourceEntities.Post;

import java.util.List;

public interface PostService {

    DataResult<List<GetPostDto>> getAll();
    DataResult<GetPostDto> getById(int postId);
    DataResult<List<GetPostDto>> getAllPaged(int pageNo, int pageSize);
    DataResult<List<GetPostDto>> getAllSorted(int pageNo, int pageSize, String sortBy, String sortDirection);
    Result createPost(CreatePostRequest createPostRequest);
    Result updatePost(UpdatePostRequest updatePostRequest);
    Result deletePost(int postId);

    // for other services
    void isPostExistById(int id);
    Post getPostById(int postId);

}
