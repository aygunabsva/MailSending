package com.example.emailsending.service;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import java.io.IOException;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private Connection connection;
    @Mock
    private Document document;
    @Mock
    MailService mailService;
    @InjectMocks
    BookService bookService;

    @Before
    public void setUp() {
        mailService = mock(MailService.class);
        bookService = new BookService(mailService);
    }
    @Test
    public void testIsBookInStockWhenInStock() throws IOException {
        Mockito.lenient().when(connection.get()).thenReturn(document);
        Elements emptyElements = new Elements();
        Mockito.lenient().when(document.getElementsByClass("out-of-stock")).thenReturn(emptyElements);
        boolean result = bookService.isBookInStock("https://example.com");
        assertTrue(result);
    }

    @Test
    public void testScheduledMailChecks() throws IOException {
        String url1 = "https://example1.com";
        String url2 = "https://example2.com";
        BookService bookServiceMock = mock(BookService.class);
        when(bookServiceMock.isBookInStock(url1)).thenReturn(true);
        when(bookServiceMock.isBookInStock(url2)).thenReturn(false);
        bookServiceMock.scheduledMailChecks();
        assertTrue(bookServiceMock.isBookInStock(url1));
        assertFalse(bookServiceMock.isBookInStock(url2));
    }
}