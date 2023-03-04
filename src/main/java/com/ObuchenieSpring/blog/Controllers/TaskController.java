package com.ObuchenieSpring.blog.Controllers;


import com.ObuchenieSpring.blog.models.Post;
import com.ObuchenieSpring.blog.models.Task;
import com.ObuchenieSpring.blog.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskController
{
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/task")
    public String task(Model model)
    {
        Iterable<Task> tasks = taskRepository.findAll();
        model.addAttribute("full_task", tasks);
        return "/task";
    }

    @GetMapping("/task-error")
    public String taskerror(Model model)
    {
        Iterable<Task> tasks = taskRepository.findAll();
        model.addAttribute("full_task", tasks);
        return "/task-error";
    }

    @PostMapping("/task")
    public String TaskAdd(@RequestParam String full_task, Model model)
    {
        if(full_task.length()<5)
            {
                return "redirect:/task-error";
            }
        else
        {
            Task task = new Task(full_task);
            taskRepository.save(task);
            return "redirect:/task";
        }
    }

    /*
    <div th:if="${error}" class="alert alert-danger w-25">
            <p th:text="${error}"/>
        </div>

        Можно было написать что-то типо такого в файле штмл, а в методе выше в иф передавать передавать

        Iterable<Tasks> data = this.taskRepository.findAll();
            model.addAttribute("tasks", data);
            model.addAttribute("title", "Главная страница");
            model.addAttribute("error", "Текст задания не менее 5 символов");

     */

    @PostMapping("/task/{id}/remove")
    public String taskDelete(@PathVariable(value = "id") long id, Model model)
    {
        Task task = taskRepository.findById(id).orElseThrow(); //  orElseThrow() обязательно, если запись не найдена
        taskRepository.delete(task);
        return "redirect:/task";
    }

}
