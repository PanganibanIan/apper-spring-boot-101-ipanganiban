package com.apper.theblogservice;

import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.payload.*;
import com.apper.theblogservice.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


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

    @PutMapping("{id}")
    public UpdateBlogResponse updateBlog (@PathVariable String id, @RequestBody UpdateBlogRequest request) {
        blogService.updateBlog(id, request.getTitle(), request.getBody());
        Blog blog = blogService.getBlog(id);
        System.out.println(blog);

        UpdateBlogResponse response = new UpdateBlogResponse();
        response.setBlogId(blog.getBlogId());
        response.setBloggerId(blog.getBloggerId());
        response.setDateCreated(blog.getCreatedAt());
        response.setDateUpdated(blog.getLastUpdate());

        return response;
    }

    @GetMapping("{id}")
    public BlogDetails getBlog(@PathVariable String id) {
        Blog blog = blogService.getBlog(id);

        BlogDetails blogDetails = new BlogDetails();
        blogDetails.setBloggerId(blog.getBloggerId());
        blogDetails.setTitle(blog.getTitle());
        blogDetails.setBody(blog.getBody());
        blogDetails.setDateCreated(blog.getCreatedAt());
        blogDetails.setDateUpdated(blog.getLastUpdate());

        return blogDetails;
    }

    @GetMapping
    public List<BlogDetails> getAllBlog() {
        List<BlogDetails> responseList = new ArrayList<>();

        for (Blog blog : blogService.getAllBlog()){
            BlogDetails response = getBlog(blog.getBlogId()) ;
            responseList.add(response) ;
        }

        return responseList;
    }

    @GetMapping("/blogger/{id}")
    public List<GetAllBlogsByBloggerResponse> getAllBlogsByBlogger(@PathVariable String id) {
        List<Blog> blogs =blogService.getAllBlogsBy(id);
        List<GetAllBlogsByBloggerResponse> responseList = new ArrayList<>() ;

        for (Blog blog : blogs){
            GetAllBlogsByBloggerResponse response = new GetAllBlogsByBloggerResponse();
            response.setTitle(blog.getTitle());
            response.setBody(blog.getBody());
            response.setDateCreated(blog.getCreatedAt());
            response.setDateUpdated(blog.getLastUpdate());

            responseList.add(response) ;
        }

        return responseList;
    }


}
