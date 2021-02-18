package com.example.querydsljpademo;

import com.example.querydsljpademo.model.Blogpost;
import com.example.querydsljpademo.model.Customer;
import com.example.querydsljpademo.model.User;
import com.example.querydsljpademo.repository.BlogPostRepository;
import com.example.querydsljpademo.repository.CustomerRepository;
import com.example.querydsljpademo.repository.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final UsersRepository usersRepository;
    private final BlogPostRepository blogPostRepository;

    public InitialData(CustomerRepository customerRepository, UsersRepository usersRepository, BlogPostRepository blogPostRepository) {
        this.customerRepository = customerRepository;
        this.usersRepository = usersRepository;
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        customerRepository.deleteAll();
        blogPostRepository.deleteAll();
        usersRepository.deleteAll();

        customerRepository.save(new Customer("Faisal", 10,"Ctg"));
        customerRepository.save(new Customer("Fahim",  11,"Dhaka"));
        customerRepository.save(new Customer("Rafi",   12,"Ctg"));
        customerRepository.save(new Customer("Mainul", 14,"Ctg"));
        customerRepository.save(new Customer("Yasin",  15,"Dhaka"));
        customerRepository.save(new Customer("Polash", 16,"Ctg"));
        customerRepository.save(new Customer("Arfan", 17,"Ctg"));
        customerRepository.save(new Customer("Nisho", 18,"Dhaka"));
        customerRepository.save(new Customer("Minhaj",19,"Ctg"));
        customerRepository.save(new Customer("Shetu", 20,"Ctg"));

        User u1 = new User("Faisal");
        User u2 = new User("Fahim");
        usersRepository.save(u1);
        usersRepository.save(u2);

        Blogpost b1 = new Blogpost("Hi");
        Blogpost b2 = new Blogpost("Hlw");
        Blogpost b3 = new Blogpost("Hello");
        Blogpost b4 = new Blogpost("Welcome");
        b1.setUser(u1);
        b2.setUser(u1);
        b3.setUser(u2);

        blogPostRepository.save(b1);
        blogPostRepository.save(b2);
        blogPostRepository.save(b3);
        blogPostRepository.save(b4);
    }
}
