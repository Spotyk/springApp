package com.edu.controller;

import com.edu.domain.Message;
import com.edu.domain.User;
import com.edu.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Message> allMessages = messageRepository.findAll();
        model.put("messages", allMessages);
        return "main";
    }

    @GetMapping("/login")
    public String login(Map<String, Object> model) {

        return "login";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Map<String, Object> model
    ) {
        Message message = new Message(text, tag, user);
        messageRepository.save(message);

        Iterable<Message> allMessages = messageRepository.findAll();
        model.put("messages", allMessages);
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        List<Message> messages = messageRepository.findByTag(filter);
        model.put("messages", messages);
        return "main";
    }

}
