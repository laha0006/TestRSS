package dev.tolana.testrss.rss;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class FeedSaver {

    private final RSSReader reader;
    private final FeedItemRepository repo;

    public void saveAll() {
        Arrays.stream(Source.values()).forEach(s -> save(reader.getFeed(s), s));
    }

    public void save(SyndFeed feed, Source source) {
        //get last feedItem with Source... if not present continue
        //if there, check date, and compare with each item. return/stop if no new entries

        long timeOfNewestEntry;
        List<FeedItem> items = repo.findAllBySourceOrderByPubDateDesc(source);
        if (!items.isEmpty()) {
            FeedItem feedItem = items.getFirst();
            timeOfNewestEntry = feedItem.getPubDate();
        } else {
            timeOfNewestEntry = 0;
        }

//        feed.getEntries().forEach(e -> System.out.println(e.getEnclosures()))

        feed.getEntries().stream()
                .takeWhile(entry -> entry.getPublishedDate().getTime() > timeOfNewestEntry)
                .map(entry -> FeedItem.builder()
                        .source(source)
                        .title(entry.getTitle())
                        .link(entry.getLink())
                        .pubDate(entry.getPublishedDate().getTime())
                        .build()
                )
                .forEach(repo::save);
    }
}
