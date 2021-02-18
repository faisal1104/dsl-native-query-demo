package com.example.querydsljpademo.controller;

import com.example.querydsljpademo.model.*;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;


@RestController
@RequestMapping("/dsl")
public class DslQueryController {
    @PersistenceContext
   private EntityManager manager;

    private QUser qUser = QUser.user;
    private QBlogpost qBlogpost = QBlogpost.blogpost;

    @GetMapping("/user-get/{name}")
    public List<User> getName1(@PathVariable String name){
        JPAQuery query = new JPAQuery(manager);
        query.select(qUser).from(qUser).where(qUser.name.eq(name));
        List<User> users = query.fetch();
                         //JPAQueryFactory query1 = new JPAQueryFactory(manager);
                         //query1.selectFrom(qUsers).where(qUsers.name.eq("H")).fetchOne();
        return users;
    }

                  /**   @param keyword String
                   *    @return similar matching data with this Keyword
                   */
    @GetMapping("/get-user/like/{keyword}")
    public List<User> getName4(@PathVariable String keyword){
        JPAQuery query = new JPAQuery(manager);
        query.select(qUser).from(qUser).where(qUser.name.contains(keyword));
        List<User> users = query.fetch();
        return users;
    }

    @GetMapping("/get-blogpost")
    public List<Blogpost> getName(){
        JPAQuery query = new JPAQuery(manager);
        List<Blogpost> users = query.select(qBlogpost).from(qBlogpost)
                                 .leftJoin(qUser)
                                 .on(qUser.id.eq(qBlogpost.user.id)).fetch();
        return users;
    }
                        /**
                         * return List<Response>
                         */
    @GetMapping("/get-user-blog/inner-join")
    public List<Response> getAll(){
        JPAQuery query = new JPAQuery(manager);
        List<Response> responses = new ArrayList<>();
        List<Tuple> tuples = query.select(qUser.name, qBlogpost.title).from(qUser)
                .innerJoin(qBlogpost)
                .on(qUser.id.eq(qBlogpost.user.id)).fetch();

        for (Tuple tepm: tuples) {
            Response response = new Response(tepm.get(qUser.name),tepm.get(qBlogpost.title));
             responses.add(response);
        }
        return responses;
    }
}
  /**
   * Source Code : https://www.baeldung.com/intro-to-querydsl
   */
