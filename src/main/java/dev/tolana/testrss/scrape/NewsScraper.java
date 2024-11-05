package dev.tolana.testrss.scrape;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class NewsScraper {

    private final String hearderField = "User-Agent";
    private final String headerFieldValue = "School Projekt news scraping. contact: laha0006@stud.kea.dk";

    public String scrape(String url) {
        System.out.println("scraping url: " + url);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "School Projekt news scraping. contact: laha0006@stud.kea.dk");

        HttpEntity<String> entity = new HttpEntity<String>(headers);


        ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return res.getBody();

    }
}
