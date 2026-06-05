package com.example.compliancehubapi.controller;

import com.example.compliancehubapi.service.ChatService;
import com.example.compliancehubapi.tools.DateTimeTools;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final DateTimeTools dateTimeTools;

    @RequestMapping("/ask")
    public String ask(@RequestParam String question){
        return chatService.ask(question);
    }

    @GetMapping("/hello")
    public String getHello(){
        return "Hello Compliance World!";
    }

    @GetMapping("chatbot/{conversationId}")
    public String chatbot(@PathVariable String conversationId, @RequestParam String question){
        return chatService.chatbot(conversationId, question);
    }

}
