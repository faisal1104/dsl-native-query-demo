package com.example.querydsljpademo.repository;

import com.example.querydsljpademo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CustomerRepository extends
                 JpaRepository<Customer, Long>, QuerydslPredicateExecutor<Customer> {
}
