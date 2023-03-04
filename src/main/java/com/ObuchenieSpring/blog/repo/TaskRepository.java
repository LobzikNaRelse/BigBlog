package com.ObuchenieSpring.blog.repo;

import com.ObuchenieSpring.blog.models.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long>
{

}