package com.example.hw4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ContactController {
    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("contact", new Contact()); // 初始化一个空的Contact对象确保contacts.html可以识别到类
        model.addAttribute("contacts", contactRepository.findAll());
        return "contacts";
    }

    @PostMapping("/")
    public String submitForm(@Valid Contact contact, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("contact", contact);
            model.addAttribute("contacts", contactRepository.findAll());
            return "contacts";
        }
        contactRepository.save(contact);
        return "redirect:/";
    }
}