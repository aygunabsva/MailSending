package com.example.emailsending.controller;

import com.example.emailsending.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("checkAvailability")
    public boolean checkBookAvailability(@RequestParam String url) throws IOException {
        return bookService.isBookInStock(url);
    }
//    @PostMapping("sendEmail")
//    public String sendEmail(@RequestParam String url) throws IOException {
//        bookService.scheduledMailChecks();
//        return "mail sended";
//    }
}
