package dev.tolana.testrss.test;

import com.rometools.rome.feed.synd.SyndFeed;
import dev.tolana.testrss.rss.FeedSaver;
import dev.tolana.testrss.rss.RSSReader;
import dev.tolana.testrss.scrape.NewsHtmlParser;
import dev.tolana.testrss.scrape.NewsScraper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RSSTest implements CommandLineRunner {

    private final RSSReader rssReader;
    private final FeedSaver feedSaver;
    private final NewsScraper newsScraper;
    private final NewsHtmlParser newsHtmlParser;

    @Override
    public void run(String... args) {
        newsHtmlParser.parse();
//        newsScraper.scrape("https://www.dr.dk/nyheder/seneste/norsk-politi-undersoeger-om-en-kronprinsesse-kan-afhoeres");
//        feedSaver.saveAll();
//        SyndFeed syndFeed = rssReader.readRSS("https://www.dr.dk/nyheder/service/feeds/senestenyt");
//        System.out.println(syndFeed.getEntries().getFirst().getPublishedDate().getTime());

//        syndFeed.getEntries().forEach(entry -> System.out.println(entry.getTitle()) );
//        System.out.println("HELLOW WORLD! #####################");
    }
}
