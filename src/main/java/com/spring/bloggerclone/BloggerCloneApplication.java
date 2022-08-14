package com.spring.bloggerclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-${spring.profiles.active:default}.properties")
public class BloggerCloneApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(BloggerCloneApplication.class, args);
	}
}
