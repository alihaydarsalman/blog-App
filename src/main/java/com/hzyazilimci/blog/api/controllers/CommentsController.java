package com.hzyazilimci.blog.api.controllers;

import com.hzyazilimci.blog.business.abstracts.CommentService;
import com.hzyazilimci.blog.core.utilities.results.DataResult;
import com.hzyazilimci.blog.core.utilities.results.Result;
import com.hzyazilimci.blog.entities.dtos.blogDtos.GetCommentDto;
import com.hzyazilimci.blog.entities.requests.create.CreateCommentRequest;
import com.hzyazilimci.blog.entities.requests.update.UpdateCommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {

    private final CommentService commentService;

    @Autowired
    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/getAll")
    public DataResult<List<GetCommentDto>> getAll(){
        return this.commentService.getAll();
    }

    @GetMapping("/getCommentById")
    public DataResult<GetCommentDto> getById(@RequestParam int postId,@RequestParam int commentId){
        return this.commentService.getById(postId,commentId);
    }

    @GetMapping("/getCommentsByPost")
    public DataResult<List<GetCommentDto>> getByPostId(@RequestParam int postId){
        return this.commentService.getByPostId(postId);
    }

    @PostMapping
    public Result submitComment(@RequestBody @Valid CreateCommentRequest createCommentRequest){
        return this.commentService.submitComment(createCommentRequest);
    }

    @PutMapping
    public Result updateComment(@RequestBody @Valid UpdateCommentRequest updateCommentRequest){
        return this.commentService.updateComment(updateCommentRequest);
    }

    @DeleteMapping
    public Result deleteComment(@RequestParam int commentId){
        return this.commentService.deleteComment(commentId);
    }
}
