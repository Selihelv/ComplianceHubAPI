package com.example.compliancehubapi.controller;

import com.example.compliancehubapi.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orion")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;


    @GetMapping("/hello")
    public String getHello(){
        return "Hello Compliance World!";
    }


    @GetMapping("compliance_copilot/{conversationId}")
    public String chatbot(@PathVariable String conversationId, @RequestParam String question){
        return chatService.chatbot(conversationId, question);
    }

}
