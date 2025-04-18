package org.example.dailydriver.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {


    @GetMapping("/user/chat")
    public String userChatPage() {
        return "user";
    }

    @GetMapping("/admin/chat")
    public String adminChatPage() {
        return "admin";
    }

}
