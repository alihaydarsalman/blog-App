package com.hzyazilimci.blog.business.concretes;

import com.hzyazilimci.blog.business.abstracts.CommentService;
import com.hzyazilimci.blog.business.abstracts.PostService;
import com.hzyazilimci.blog.business.constants.messages.BusinessMessages;
import com.hzyazilimci.blog.core.utilities.exceptions.BusinessException;
import com.hzyazilimci.blog.core.utilities.mapping.ModelMapperService;
import com.hzyazilimci.blog.core.utilities.results.DataResult;
import com.hzyazilimci.blog.core.utilities.results.Result;
import com.hzyazilimci.blog.core.utilities.results.SuccessDataResult;
import com.hzyazilimci.blog.core.utilities.results.SuccessResult;
import com.hzyazilimci.blog.dataAccess.CommentDao;
import com.hzyazilimci.blog.entities.dtos.blogDtos.GetCommentDto;
import com.hzyazilimci.blog.entities.requests.create.CreateCommentRequest;
import com.hzyazilimci.blog.entities.requests.update.UpdateCommentRequest;
import com.hzyazilimci.blog.entities.sourceEntities.Comment;
import com.hzyazilimci.blog.entities.sourceEntities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentManager implements CommentService {

    private final ModelMapperService modelMapperService;
    private final CommentDao commentDao;
    private final PostService postService;

    @Autowired
    public CommentManager(ModelMapperService modelMapperService, CommentDao commentDao, PostService postService) {
        this.modelMapperService = modelMapperService;
        this.commentDao = commentDao;
        this.postService = postService;
    }

    @Override
    public DataResult<List<GetCommentDto>> getAll() {

        List<Comment> comments = this.commentDao.findAll();
        List<GetCommentDto> result = comments.stream()
                .map(comment -> this.modelMapperService.forDto().map(comment, GetCommentDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(result, BusinessMessages.GlobalSuccessMessages.SUCCESS_LIST);
    }

    @Override
    public DataResult<GetCommentDto> getById(int postId,int commentId) {

        isCommentExistsById(commentId);

        Post post = this.postService.getPostById(postId);
        Comment comment = this.commentDao.findById(commentId);

        isExistsCommentByPost(post, comment);

        GetCommentDto result = this.modelMapperService.forDto().map(comment, GetCommentDto.class);

        return new SuccessDataResult<>(result, BusinessMessages.GlobalSuccessMessages.SUCCESS_GET);
    }


    @Override
    public DataResult<List<GetCommentDto>> getByPostId(int postId) {

        this.postService.isPostExistById(postId);

        List<Comment> comments = this.commentDao.findAllByPost_Id(postId);
        List<GetCommentDto> result = comments.stream()
                .map(comment -> this.modelMapperService.forDto().map(comment,GetCommentDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(result, BusinessMessages.GlobalSuccessMessages.SUCCESS_LIST);
    }

    @Override
    public Result submitComment(CreateCommentRequest createCommentRequest) {

        Post post = this.postService.getPostById(createCommentRequest.getPostId());

        Comment comment = this.modelMapperService.forRequest().map(createCommentRequest, Comment.class);
        comment.setId(0);
        comment.setPost(post);
        this.commentDao.save(comment);

        return new SuccessResult(BusinessMessages.GlobalSuccessMessages.SUCCESS_ADD);
    }

    @Override
    public Result updateComment(UpdateCommentRequest updateCommentRequest) {

        isCommentExistsById(updateCommentRequest.getCommentId());

        Post post = this.postService.getPostById(updateCommentRequest.getPostId());

        Comment comment = this.commentDao.findById(updateCommentRequest.getCommentId());
        comment.setPost(post);
        comment.setCommentBody(updateCommentRequest.getCommentBody());
        comment.setEmail(updateCommentRequest.getEmail());
        comment.setName(updateCommentRequest.getName());
        comment.setCommentDate(LocalDate.now());
        this.commentDao.save(comment);

        return new SuccessResult(BusinessMessages.GlobalSuccessMessages.SUCCESS_UPDATE);
    }

    @Override
    public Result deleteComment(int commentId) {

        isCommentExistsById(commentId);

        this.commentDao.deleteById(commentId);

        return new SuccessResult(BusinessMessages.GlobalSuccessMessages.SUCCESS_DELETE);
    }

    private void isCommentExistsById(int commentId){
        if(!this.commentDao.existsById(commentId)){
            throw new BusinessException(BusinessMessages.CommentExceptionMessages.COMMENT_NOT_FOUND);
        }
    }

    private void isExistsCommentByPost(Post post, Comment comment){
        if (comment.getPost().getId() != post.getId()){
            throw new BusinessException(BusinessMessages.CommentExceptionMessages.COMMENT_NOT_FOUND);
        }
    }
}
