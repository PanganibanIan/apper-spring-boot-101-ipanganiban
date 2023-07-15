package com.apper.theblogservice.service;

import com.apper.theblogservice.exceptions.AccountNotFoundException;
import com.apper.theblogservice.exceptions.BlogNotFoundException;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.repository.BloggerRepository;
import org.springframework.stereotype.Service;
import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.repository.BlogRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final BloggerRepository bloggerRepository;
    public BlogService(BlogRepository blogRepository, BloggerRepository bloggerRepository) {this.blogRepository = blogRepository ;
        this.bloggerRepository = bloggerRepository;
    }
    public Blog createBlog(String title, String body, String bloggerId) {

//        for (Blogger blogger: bloggerRepository.findAll()){
//            if (blogger.getId().equals(bloggerId)){

//            }
//        }

        if (!bloggerRepository.existsById(bloggerId)){
            throw new AccountNotFoundException("blogger_id: " + bloggerId + " does not exist.") ;
        }
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setBody(body);
        blog.setBloggerId(bloggerId) ;

        return blogRepository.save(blog);

    }

    public Blog getBlog(String id) {
        if(blogRepository.existsById(id)){
            Optional<Blog> blogResult = blogRepository.findById(id);
            return blogResult.get();
        }
        throw new BlogNotFoundException("Blog id: " + id + " not found!") ;
    }

    public List<Blog> getAllBlog() {
        return (List<Blog>) blogRepository.findAll();
    }

    public List<Blog> getAllBlogsBy(String id) {
        if(!bloggerRepository.existsById(id)){
            throw new AccountNotFoundException("Account id: " + id + " not found!") ;
        }
        return blogRepository.findAllByBloggerId(id) ;
    }


    public void updateBlog(String id, String title, String body) {
        if(!blogRepository.existsById(id)){
            throw new BlogNotFoundException("Blog id: " + id + " not found!") ;
        }
        Optional<Blog> blogResult = blogRepository.findById(id) ;

        Blog blog = blogResult.get();
        blog.setTitle(title);
        blog.setBody(body);
        blog.setLastUpdate();
        blogRepository.save(blog) ;
    }

}
