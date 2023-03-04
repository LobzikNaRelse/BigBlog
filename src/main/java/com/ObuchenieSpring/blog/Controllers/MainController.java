package com.ObuchenieSpring.blog.Controllers;

import com.ObuchenieSpring.blog.models.About;
import com.ObuchenieSpring.blog.repo.AboutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController
{

    @Autowired
    private AboutRepository aboutRepository;

    @GetMapping("/")
    public String home(Model model)
    {
        model.addAttribute("title", "Главная страница");
        return "/home";
    }

    @GetMapping("/about")
    public String about(Model model)
    {
        Iterable<About> abouts = aboutRepository.findAll();
        model.addAttribute("full_about", abouts);
        return "/about";
    }

    @PostMapping("/about")
    public String AboutAdd(@RequestParam String full_about, Model model)
    {
        About about = new About(full_about);
        aboutRepository.save(about);
        return "redirect:/about";
    }

}
