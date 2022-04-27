package com.hzyazilimci.blog.api.controllers;

import com.hzyazilimci.blog.business.abstracts.PostService;
import com.hzyazilimci.blog.business.constants.AppCons.AppConstants;
import com.hzyazilimci.blog.core.utilities.results.DataResult;
import com.hzyazilimci.blog.core.utilities.results.Result;
import com.hzyazilimci.blog.entities.dtos.GetPostDto;
import com.hzyazilimci.blog.entities.requests.create.CreatePostRequest;
import com.hzyazilimci.blog.entities.requests.update.UpdatePostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostsController {

    private final PostService postService;

    @Autowired
    public PostsController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/getAll")
    public DataResult<List<GetPostDto>> getAll(){
        return this.postService.getAll();
    }
    @GetMapping("/getById")
    public DataResult<GetPostDto> getById(@RequestParam int postId){
        return this.postService.getById(postId);
    }

    @GetMapping("/getAllPaged")
    public DataResult<List<GetPostDto>> getAllPaged(@RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NO, required = false) int pageNo,
                                                    @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        return this.postService.getAllPaged(pageNo,pageSize);
    }

    @GetMapping("/getAllSorted")
    public DataResult<List<GetPostDto>> getAllSorted(@RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NO, required = false)int pageNo,
                                                     @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false)int pageSize,
                                                     @RequestParam(defaultValue = AppConstants.DEFAULT_SORT_BY, required = false)String sortBy,
                                                     @RequestParam(defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false )String sortDirection){
        return this.postService.getAllSorted(pageNo, pageSize,sortBy,sortDirection);
    }

    @PostMapping
    public Result createPost(@RequestBody @Valid CreatePostRequest createPostRequest){
        return this.postService.createPost(createPostRequest);
    }

    @PutMapping
    public Result updatePost(@RequestBody @Valid UpdatePostRequest updatePostRequest){
        return this.postService.updatePost(updatePostRequest);
    }

    @DeleteMapping
    public Result deletePost(@RequestParam int postId){
        return this.postService.deletePost(postId);
    }
}
