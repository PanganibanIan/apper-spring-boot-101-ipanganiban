package com.apper.theblogservice.service;

import com.apper.theblogservice.exceptions.AccountNotFoundException;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.repository.BloggerRepository;
import org.springframework.stereotype.Service;
import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.repository.BlogRepository;
@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final BloggerRepository bloggerRepository;
    public BlogService(BlogRepository blogRepository, BloggerRepository bloggerRepository) {this.blogRepository = blogRepository ;
        this.bloggerRepository = bloggerRepository;
    }
    public Blog createBlog(String title, String body, String bloggerId) {

        for (Blogger blogger: bloggerRepository.findAll()){
            if (blogger.getId().equals(bloggerId)){
                Blog blog = new Blog();
                blog.setTitle(title);
                blog.setBody(body);
                blog.setBloggerId(bloggerId) ;

                return blogRepository.save(blog);
            }
        }
        throw new AccountNotFoundException("blogger_id: " + bloggerId + " does not exist.") ;
    }

}
