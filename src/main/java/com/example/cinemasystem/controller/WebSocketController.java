package com.example.cinemasystem.controller;

import com.example.cinemasystem.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class WebSocketController {

    @MessageMapping("/hello")
    @SendTo("topic/greetings")
    public Message message(String message) throws Exception{
        Thread.sleep(500);
        System.out.println("ueee");
        return new Message("Hello, " + HtmlUtils.htmlEscape(message));
    }
}
