package com.example.cinemasystem.controller;

import com.example.cinemasystem.model.Greeting;
import com.example.cinemasystem.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import java.lang.Thread;

@Controller
public class WebSocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting message(Message message) throws Exception{
        Thread.sleep(1000);
        Greeting greeting = new Greeting(HtmlUtils.htmlEscape(message.getName()));

        return greeting;
    }
}
