package com.example.querydsljpademo.controller;

import com.example.querydsljpademo.model.Customer;
import com.example.querydsljpademo.model.QCustomer;
import com.example.querydsljpademo.repository.CustomerRepository;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class Controller {
    @Autowired
   private CustomerRepository customerRepository;
   @PersistenceContext
   private EntityManager manager;

   private QCustomer qCustomer = QCustomer.customer;

   @GetMapping("/{name}")
   public List<Customer> get(@PathVariable String name){
       JPAQuery query = new JPAQuery(manager);
       query.from(qCustomer).where(qCustomer.name.eq(name).or(qCustomer.name.eq("Fahim")));
       List<Customer> c1 = query.fetch();
       return c1;
   }

   @GetMapping("/{name}/{age}")
   public List<Customer> get2(@PathVariable String name, @PathVariable long age){
       JPAQuery query = new JPAQuery(manager);
       query.from(qCustomer).where(qCustomer.name.eq(name).and(qCustomer.age.eq(age)));
       List<Customer> customers = query.fetch();
       return customers;
   }

   @GetMapping("age/{age}")
   public List<Customer> get3(@PathVariable long age){
       BooleanExpression booleanExpression = qCustomer.age.between(10,age);
      OrderSpecifier<String> orderSpecifier = qCustomer.name.asc();
       return (List<Customer>) customerRepository.findAll(booleanExpression,orderSpecifier);
   }
}