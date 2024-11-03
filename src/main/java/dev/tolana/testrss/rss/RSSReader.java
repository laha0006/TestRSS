package dev.tolana.testrss.rss;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class RSSReader {

    public SyndFeed readRSS(String feedUrl) {
        RestTemplate restTemplate = new RestTemplate();
        SyndFeed syndFeed = restTemplate.execute(feedUrl, HttpMethod.GET, null, response -> {
            SyndFeedInput input = new SyndFeedInput();
            try {
                return input.build(new XmlReader(response.getBody()));
            } catch (FeedException e) {
                throw new IOException("Could not parse response", e);
            }
        });
        return syndFeed;
    }

    public SyndFeed getFeed(Source source) {
        return readRSS(source.getUrl());
    }
}
