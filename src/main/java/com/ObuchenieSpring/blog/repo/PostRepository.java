package com.ObuchenieSpring.blog.repo;

import com.ObuchenieSpring.blog.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long>
{

}
