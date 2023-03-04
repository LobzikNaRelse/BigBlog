package com.ObuchenieSpring.blog.Controllers;

import com.ObuchenieSpring.blog.models.About;
import com.ObuchenieSpring.blog.models.Post;
import com.ObuchenieSpring.blog.repo.AboutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.ObuchenieSpring.blog.repo.PostRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller

public class BlogController
{
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AboutRepository aboutRepository;


    @GetMapping("/blog")
    public String blogMain (Model model)
        {
            Iterable<Post> posts = postRepository.findAll();
            model.addAttribute("posts", posts);
            return "blog-main";
        }

    @GetMapping("/blog/add")
    public String blogAdd (Model model)
        {
            return "blog-add";
        }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title,@RequestParam String anons,@RequestParam String full_text, Model model)
        {
            Post post = new Post(title, anons, full_text);
            postRepository.save(post);
            return "redirect:/blog";
        }

    @GetMapping("/blog/{id}")   // с динамическим параметром
    public String blogDetails (@PathVariable(value = "id") long id, Model model)
    {
        if(!postRepository.existsById(id))   // проверка есть ли такая страница
            {
                return "redirect:/blog";
            }
        Optional<Post> post = postRepository.findById(id);//ищем нужную статью
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-details";
    }

    @GetMapping("/about/{id}")   // с динамическим параметром
    public String aboutDetails (@PathVariable(value = "id") long id, Model model)
    {
        if(!aboutRepository.existsById(id))   // проверка есть ли такая страница
        {
            return "redirect:/about";
        }
        Optional<About> post = aboutRepository.findById(id);//ищем нужную статью
        ArrayList<About> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("about", res);
        return "about-details";
    }

    @GetMapping("/blog/{id}/edit")   // с динамическим параметром
    public String blogEdit (@PathVariable(value = "id") long id, Model model)
    {
        if(!postRepository.existsById(id))   // проверка есть ли такая страница
        {
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);//ищем нужную статью
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostEdit(@PathVariable(value = "id") long id, @RequestParam String title,@RequestParam String anons,@RequestParam String full_text, Model model)
    {
        Post post = postRepository.findById(id).orElseThrow(); //  orElseThrow() обязательно, если запись не найдена
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepository.save(post); // обновляем сущ объект, по этому выделять память не нужно
        return "redirect:/blog/{id}";
    }


    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") long id, Model model)
    {
        Post post = postRepository.findById(id).orElseThrow(); //  orElseThrow() обязательно, если запись не найдена
        postRepository.delete(post);
        return "redirect:/blog/{id}";
    }


}
