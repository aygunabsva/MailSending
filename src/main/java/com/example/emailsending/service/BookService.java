package com.example.emailsending.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
    private final MailService mailService;
    private final List<String> urlsToCheck = Arrays.asList("https://bakubookcenter.az/product/51066", "https://bakubookcenter.az/product/76173");

    @Scheduled(fixedRate = 60000)
    public void scheduledMailChecks() {
        for (String url : urlsToCheck) {
            try {
                if (isBookInStock(url)) {
                    mailService.sendEmail();
                    log.info("Book in Stock for URL: " + url);
                } else {
                    log.info("Out of Stock for URL: " + url);
                }
            } catch (IOException e) {
                log.error("Error during scheduled check for URL: " + url, e);
            }
        }
    }

    public boolean isBookInStock(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements elementsByClass = doc.getElementsByClass("out-of-stock");
        if (elementsByClass.isEmpty())
            return true;
        else return false;
    }
}


