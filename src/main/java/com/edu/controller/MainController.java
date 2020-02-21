package com.edu.controller;

import com.edu.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepository messageRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

//    @GetMapping("/main")
//    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
//        Iterable<Message> allMessages = messageRepository.findAll();
//        if (filter != null && !filter.isEmpty()) {
//            allMessages = messageRepository.findByTag(filter);
//        }
//        model.addAttribute("messages", allMessages);
//        model.addAttribute("filter", filter);
//        return "main";
//    }

//    @PostMapping("/main")
//    public String add(
//            @AuthenticationPrincipal User user,
//            @RequestParam String text,
//            @RequestParam("file") MultipartFile file,
//            @RequestParam String tag,
//            Map<String, Object> model
//    ) throws IOException {
//        Message message = new Message(text, tag, user);
//        if (file != null && !file.getOriginalFilename().isEmpty()) {
//            File uploadDir = new File(uploadPath);
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//            String uuidFile = UUID.randomUUID().toString();
//            String resultFileName = uuidFile + "." + file.getOriginalFilename();
//            file.transferTo(new File(uploadPath + "/" + resultFileName));
//
//            message.setFilename(resultFileName);
//
//        }
//        messageRepository.save(message);
//
//        Iterable<Message> allMessages = messageRepository.findAll();
//        model.put("messages", allMessages);
//        return "main";
//    }
}
