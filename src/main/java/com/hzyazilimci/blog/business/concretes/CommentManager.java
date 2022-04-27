package com.hzyazilimci.blog.business.concretes;

import com.hzyazilimci.blog.business.abstracts.CommentService;
import com.hzyazilimci.blog.business.abstracts.PostService;
import com.hzyazilimci.blog.business.abstracts.UserService;
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
import com.hzyazilimci.blog.entities.sourceEntities.User;
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
    private final UserService userService;

    @Autowired
    public CommentManager(ModelMapperService modelMapperService, CommentDao commentDao,
                          PostService postService, UserService userService) {
        this.modelMapperService = modelMapperService;
        this.commentDao = commentDao;
        this.postService = postService;
        this.userService = userService;
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
        User user = this.userService.getUserById(createCommentRequest.getUserId());

        Comment comment = this.modelMapperService.forRequest().map(createCommentRequest, Comment.class);
        comment.setId(0);
        comment.setPost(post);
        comment.setUser(user);
        this.commentDao.save(comment);

        return new SuccessResult(BusinessMessages.GlobalSuccessMessages.SUCCESS_ADD);
    }

    @Override
    public Result updateComment(UpdateCommentRequest updateCommentRequest) {

        isCommentExistsById(updateCommentRequest.getCommentId());

        Post post = this.postService.getPostById(updateCommentRequest.getPostId());
        User user = this.userService.getUserById(updateCommentRequest.getUserId());

        Comment comment = this.commentDao.findById(updateCommentRequest.getCommentId());
        comment.setPost(post);
        comment.setCommentBody(updateCommentRequest.getCommentBody());
        comment.setCommentDate(LocalDate.now());
        comment.setUser(user);

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
