package com.example.querydsljpademo.repository;

import com.example.querydsljpademo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRepository extends JpaRepository<User,Long> {

    @Query(value="select * from users u where u.name =:uName", nativeQuery = true)
    List<User> findByNameNative(@Param("uName") String s);

    @Query(value="select * from users u where u.name LIKE :keyword%", nativeQuery = true)
    List<User> findByKeyNative(@Param("keyword") String s);
}
