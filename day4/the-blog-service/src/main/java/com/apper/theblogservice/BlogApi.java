package com.apper.theblogservice;

import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.payload.CreateBlogRequest;
import com.apper.theblogservice.payload.CreateBlogResponse;
import com.apper.theblogservice.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("blog")
public class BlogApi {

    private final BlogService blogService;

    public BlogApi(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBlogResponse createBlog(@RequestBody CreateBlogRequest request) {
        Blog createdBlog = blogService.createBlog(request.getTitle(), request.getBody(), request.getBloggerId()) ;
        System.out.println(request);

        CreateBlogResponse response = new CreateBlogResponse();
        response.setBlogId(createdBlog.getBlogId());
        response.setBloggerId(createdBlog.getBloggerId());
        response.setDateCreated(createdBlog.getCreatedAt());
        response.setDateUpdated(createdBlog.getLastUpdate());

        return response;
    }



}
