package dev.tolana.testrss.scrape;

import dev.tolana.testrss.rss.FeedItem;
import dev.tolana.testrss.rss.Source;
import dev.tolana.testrss.scrape.util.FileReader;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class NewsHtmlParser {


    public String parseDr(String html) {
        String content = "";
        Document doc = Jsoup.parse(html);
        Elements elements = doc.getElementsByTag("article");
        if (!elements.isEmpty()) {
            Element element = elements.first();
            Elements speech = null;
            if (element != null) {
                speech = element.select(".dre-speech");
                content = speech.text();
                System.out.println(content);
            }
        }
        return content;
    }

    public String parsePolitiken(String html) {
        List<String> stopWords = new ArrayList<>(List.of("ritzau", "fortsæt med at læse"));
        Document doc = Jsoup.parse(html);
        Elements elements = doc.getElementsByTag("article");
        StringBuilder output = new StringBuilder();
        if (!elements.isEmpty()) {
            Element article = elements.first();
            assert article != null;
            Elements content = article.select("p");
            content.stream().takeWhile(element -> !stopWords.contains(element.text().toLowerCase())).forEach(element -> {output.append(element.text());});
        }
        System.out.println("output: " + output.toString());
        return output.toString();
    }
}
