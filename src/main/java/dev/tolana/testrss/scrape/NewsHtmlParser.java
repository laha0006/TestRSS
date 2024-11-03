package dev.tolana.testrss.scrape;

import dev.tolana.testrss.scrape.util.FileReader;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;

@RequiredArgsConstructor
@Component
public class NewsHtmlParser {


    public void parse() {
        String test;
        try {

            test = FileReader.readFile("./raw.txt", Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
            test = "";
        }
        Document doc = Jsoup.parse(test);
        Elements elements = doc.getElementsByTag("article");
        if (!elements.isEmpty()) {
            Element element = elements.first();
            Elements speech = null;
            if (element != null) {
                speech = element.select(".dre-speech");
                String title = speech.first().text();
                String content = speech.text();
                System.out.println("Title: " + title);
            }
        }

    }
}
