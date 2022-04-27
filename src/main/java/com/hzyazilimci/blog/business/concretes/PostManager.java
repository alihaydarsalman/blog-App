package com.hzyazilimci.blog.business.concretes;

import com.hzyazilimci.blog.business.abstracts.PostService;
import com.hzyazilimci.blog.business.constants.messages.BusinessMessages;
import com.hzyazilimci.blog.core.utilities.exceptions.BusinessException;
import com.hzyazilimci.blog.core.utilities.mapping.ModelMapperService;
import com.hzyazilimci.blog.core.utilities.results.DataResult;
import com.hzyazilimci.blog.core.utilities.results.Result;
import com.hzyazilimci.blog.core.utilities.results.SuccessDataResult;
import com.hzyazilimci.blog.core.utilities.results.SuccessResult;
import com.hzyazilimci.blog.dataAccess.PostDao;
import com.hzyazilimci.blog.entities.dtos.blogDtos.GetPostDto;
import com.hzyazilimci.blog.entities.requests.create.CreatePostRequest;
import com.hzyazilimci.blog.entities.requests.update.UpdatePostRequest;
import com.hzyazilimci.blog.entities.sourceEntities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostManager implements PostService {

    private final ModelMapperService modelMapperService;
    private final PostDao postDao;

    @Autowired
    public PostManager(ModelMapperService modelMapperService, PostDao postDao) {
        this.modelMapperService = modelMapperService;
        this.postDao = postDao;
    }

    @Override
    public DataResult<List<GetPostDto>> getAll() {

        List<Post> posts = this.postDao.findAll();
        List<GetPostDto> result = posts.stream()
                .map(post -> this.modelMapperService.forDto().map(post,GetPostDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(result,BusinessMessages.GlobalSuccessMessages.SUCCESS_LIST);
    }

    @Override
    public DataResult<GetPostDto> getById(int postId) {

        isPostExistById(postId);

        Post post = this.postDao.findById(postId);
        GetPostDto result = this.modelMapperService.forDto().map(post,GetPostDto.class);

        return new SuccessDataResult<>(result,BusinessMessages.GlobalSuccessMessages.SUCCESS_GET);
    }

    @Override
    public DataResult<List<GetPostDto>> getAllPaged(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo-1,pageSize);

        List<Post> posts = postDao.findAll(pageable).getContent();
        List<GetPostDto> result = posts.stream()
                .map(post -> this.modelMapperService.forDto().map(post,GetPostDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(result,BusinessMessages.GlobalSuccessMessages.SUCCESS_LIST_PAGED);
    }

    @Override
    public DataResult<List<GetPostDto>> getAllSorted(int pageNo, int pageSize, String sortBy,String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo-1,pageSize, sort);

        Page<Post> posts = this.postDao.findAll(pageable);
        List<Post> postList = posts.getContent();

        List<GetPostDto> result = postList.stream()
                .map(post -> this.modelMapperService.forDto().map(post,GetPostDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(result,BusinessMessages.GlobalSuccessMessages.SUCCESS_LIST_SORTED);
    }


    @Override
    public Result createPost(CreatePostRequest createPostRequest) {

        isPostExistsByTitle(createPostRequest.getTitle());

        Post post = this.modelMapperService.forRequest().map(createPostRequest,Post.class);
        post.setId(0);
        this.postDao.save(post);

        return new SuccessResult(BusinessMessages.GlobalSuccessMessages.SUCCESS_ADD);
    }

    @Override
    public Result updatePost(UpdatePostRequest updatePostRequest) {

        isPostExistById(updatePostRequest.getId());
        isPostExistsByTitle(updatePostRequest.getTitle());

        Post post = this.modelMapperService.forRequest().map(updatePostRequest,Post.class);
        this.postDao.save(post);

        return new SuccessResult(BusinessMessages.GlobalSuccessMessages.SUCCESS_UPDATE);
    }

    @Override
    public Result deletePost(int postId) {

        isPostExistById(postId);

        this.postDao.deleteById(postId);

        return new SuccessResult(BusinessMessages.GlobalSuccessMessages.SUCCESS_DELETE);
    }

    @Override
    public void isPostExistById(int id){
        if (!this.postDao.existsById(id)){
            throw new BusinessException(BusinessMessages.PostExceptionMessages.POST_NOT_FOUND);
        }
    }

    public Post getPostById(int postId){

        isPostExistById(postId);

        return this.postDao.findById(postId);
    }

    private void isPostExistsByTitle(String title){
        if(this.postDao.existsByTitle(title)){
            throw new BusinessException(BusinessMessages.PostExceptionMessages.TITLE_ALREADY_EXIST);
        }
    }
}
