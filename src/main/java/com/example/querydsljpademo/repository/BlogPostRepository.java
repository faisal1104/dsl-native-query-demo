package com.example.querydsljpademo.repository;

import com.example.querydsljpademo.model.Blogpost;
import com.example.querydsljpademo.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BlogPostRepository extends JpaRepository<Blogpost, Long> {

    @Query(value="select * from blogpost b where b.title =:title", nativeQuery = true)
    List<Blogpost> findByTitleNative(@Param("title") String s);

    @Query(value="select * from blogpost b where b.title LIKE :keyword%", nativeQuery = true)
    List<Blogpost> findByKeyNative(@Param("keyword") String s);


    @Query(value = "select new com.example.querydsljpademo.model.Response(u.name,b.title) from Blogpost b INNER JOIN b.user u")
    List<Response> findAllBlogpostByJoinHql();

    @Query(value = "select u.name,b.title from blogpost b INNER JOIN user u on u.id=b.user_id",nativeQuery = true)
    List<NameOnly> findAllBlogpostByJoin();

    public interface NameOnly {
        String getName();
        String gettitle();
    }
}