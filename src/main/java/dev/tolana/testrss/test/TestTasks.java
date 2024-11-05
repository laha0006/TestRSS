package dev.tolana.testrss.test;

import com.rometools.rome.feed.synd.SyndFeed;
import dev.tolana.testrss.openai.ArticleSummarizer;
import dev.tolana.testrss.rss.FeedSaver;
import dev.tolana.testrss.rss.RSSReader;
import dev.tolana.testrss.scrape.RawArticleSaver;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class TestTasks {

    private final FeedSaver feedSaver;
    private final RawArticleSaver rawArticleSaver;
    private final ArticleSummarizer summarizer;

//    @Scheduled(fixedDelay = 15, timeUnit = TimeUnit.MINUTES)
    public void testTask() {
        System.out.println("TASK START ############");
        feedSaver.saveAll();
        rawArticleSaver.saveAll();
        summarizer.process();
        System.out.println("TASK END ############");
    }

}
