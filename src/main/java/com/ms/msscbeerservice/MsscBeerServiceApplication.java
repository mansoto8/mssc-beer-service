package com.ms.msscbeerservice;

import com.ms.msscbeerservice.repositories.BeerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class MsscBeerServiceApplication
{
  public static void main(String[] args) {
    SpringApplication.run(MsscBeerServiceApplication.class, args);
  }
}
