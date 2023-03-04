package com.ObuchenieSpring.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApplication // нужно прописать что бы зайти на сервер http://localhost:8080/
{

	public static void main(String[] args)
	{
		SpringApplication.run(BlogApplication.class, args);
	}

}
