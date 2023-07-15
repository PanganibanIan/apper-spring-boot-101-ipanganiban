package com.apper.theblogservice.service;

import com.apper.theblogservice.exceptions.AccountNotFoundException;
import com.apper.theblogservice.exceptions.EmailAlreadyRegisteredException;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.repository.BloggerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloggerService {

    private final BloggerRepository bloggerRepository;

    public BloggerService(BloggerRepository bloggerRepository) {
        this.bloggerRepository = bloggerRepository;
    }

    public Blogger createBlogger(String email, String name, String password) {

        if (bloggerRepository.existsByEmail(email)){
            throw new EmailAlreadyRegisteredException(email + "is Already Registered");
        }

        Blogger blogger = new Blogger();
        blogger.setEmail(email);
        blogger.setName(name);
        blogger.setPassword(password);

        return bloggerRepository.save(blogger);
    }

    public Blogger getBlogger(String id) {

        if (!bloggerRepository.existsById(id)) {
            throw new AccountNotFoundException("Account " + id + " not Found!");
        }
        Optional<Blogger> bloggerResult = bloggerRepository.findById(id);
        return bloggerResult.get();

    }

    public List<Blogger> getAllBlogger() {
        return (List<Blogger>) bloggerRepository.findAll();
    }

}