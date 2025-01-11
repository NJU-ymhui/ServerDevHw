package com.example.hw4;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ContactController {
    private List<Contact> contacts = new ArrayList<>();

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("contact", new Contact());
        model.addAttribute("contacts", contacts);
        return "contacts";
    }

    @PostMapping("/")
    public String submitForm(@Valid Contact contact, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("contact", contact);
            model.addAttribute("contacts", contacts);
            return "contacts";
        }
        contacts.add(contact);
        return "redirect:/";
    }
}
