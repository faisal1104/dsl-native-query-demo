package com.example.querydsljpademo.controller;

import com.example.querydsljpademo.model.Blogpost;
import com.example.querydsljpademo.model.Response;
import com.example.querydsljpademo.model.User;
import com.example.querydsljpademo.repository.BlogPostRepository;
import com.example.querydsljpademo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/native")
public class NativeQueryController {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private BlogPostRepository blogPostRepository;

    @GetMapping("/get-user/{name}")
    public List<User> getAllByName(@PathVariable(value = "name") String name){
        return usersRepository.findByNameNative(name);
    }

    @GetMapping("/get-user/like/{key}")
    public List<User> getAllByKeyword(@PathVariable String key){
        return usersRepository.findByKeyNative(key);
    }

    @GetMapping("/get-blog/{name}")
    public List<Blogpost> getAllByTitle(@PathVariable String name){
        return blogPostRepository.findByTitleNative(name);
    }
    @GetMapping("/get-blog/like/{key}")
    public List<Blogpost> getAllBlogpostByKeyword(@PathVariable String key){
        return blogPostRepository.findByKeyNative(key);
    }

    @GetMapping("/get-user-blog/inner-join/hql")

    public List<Response> getAllBlogpostByJoinHql(){
        return blogPostRepository.findAllBlogpostByJoinHql();
    }

    @GetMapping("/get-user-blog/inner-join")
    public List<BlogPostRepository.NameOnly> getAllBlogpostByJoinNative(){
        return blogPostRepository.findAllBlogpostByJoin();
    }
}
