package dev.tolana.testrss.scrape;

import dev.tolana.testrss.rss.FeedItem;
import dev.tolana.testrss.rss.FeedItemRepository;
import dev.tolana.testrss.rss.Source;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class RawArticleSaver {

    private final FeedItemRepository feedItemRepo;
    private final RawArticleRepository rawArticleRepo;
    private final NewsScraper scraper;
    private final NewsHtmlParser parser;


    public boolean exists(String title) {
        Optional<RawArticle> optionalRawArticle = rawArticleRepo.findByTitle(title);
        return optionalRawArticle.isPresent();
    }

    public void save(FeedItem feedItem) {
        String url = feedItem.getLink();
        String title = feedItem.getTitle();
        Source source = feedItem.getSource();
        String link = feedItem.getLink();
        long pubDate = feedItem.getPubDate();

        if (exists(title)) return;

        String html = scraper.scrape(url);

        String content = switch (source) {
            case DR -> parser.parseDr(html);
            case POLITIKEN -> title +  " " + parser.parsePolitiken(html);
        };

        RawArticle rawArticle = RawArticle.builder()
                .title(title)
                .content(content)
                .source(source)
                .timestamp(pubDate)
                .link(link)
                .build();
        rawArticleRepo.save(rawArticle);
        System.out.println("saved raw article");
    }

    public void saveAll() {
        long unixTimeNow = System.currentTimeMillis();
        long DAY_IN_SECONDS = 86_400_000L;
        long unixTimeOneDayAgo = unixTimeNow - DAY_IN_SECONDS;
        List<FeedItem> feedItems = feedItemRepo.findByPubDateGreaterThanEqualOrderByPubDateDesc(unixTimeOneDayAgo);
        if (feedItems.isEmpty()) return;
        feedItems.forEach(this::save);
    }



}
