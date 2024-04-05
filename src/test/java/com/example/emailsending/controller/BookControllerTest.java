package com.example.emailsending.controller;

import com.example.emailsending.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Mock
    BookService bookService;
    @InjectMocks
    BookController bookController;

    @Test
    public void testCheckBookAvailabilityWhenInStock() throws IOException {
        String testUrl = "https://example.com";
        when(bookService.isBookInStock(testUrl)).thenReturn(true);
        boolean result = bookController.checkBookAvailability(testUrl);
        assertTrue(result);
    }

    @Test
    public void testCheckBookAvailabilityWhenOutOfStock() throws IOException {
        String testUrl = "https://example.com";
        when(bookService.isBookInStock(testUrl)).thenReturn(false);
        boolean result = bookController.checkBookAvailability(testUrl);
        assertFalse(result);
    }
}
