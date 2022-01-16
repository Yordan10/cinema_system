package com.example.cinemasystem.controller;

import com.example.cinemasystem.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/api")
@CrossOrigin("*")
public class MessageController {

    private final SimpMessagingTemplate msgTemplate;

    @Autowired
    public MessageController(SimpMessagingTemplate msgTemplate){
        this.msgTemplate=msgTemplate;
    }

    @GetMapping("/broadcast")
    public String hi(@RequestBody String message) {

        Greeting greeting = new Greeting(message);
        msgTemplate.convertAndSend("/topic/broadcast",greeting);
        return "message sent ...";
    }
}
