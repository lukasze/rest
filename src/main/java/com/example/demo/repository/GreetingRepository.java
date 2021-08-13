package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Greeting;

// add some superpowers (spring data will provide a DAO implementation based on the interfaces)
public interface GreetingRepository extends JpaRepository<Greeting, Long>{

}
