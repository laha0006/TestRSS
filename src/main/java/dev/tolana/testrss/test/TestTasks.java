package dev.tolana.testrss.test;

import com.rometools.rome.feed.synd.SyndFeed;
import dev.tolana.testrss.rss.RSSReader;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class TestTasks {

    private final RSSReader reader;

//    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void testTask() {
        System.out.println("TASK START ############");
        SyndFeed syndFeed = reader.readRSS("https://www.dr.dk/nyheder/service/feeds/senestenyt");
        System.out.println(syndFeed.getEntries().getFirst().getPublishedDate().getTime());
        System.out.println("TASK END ############");
    }

}
