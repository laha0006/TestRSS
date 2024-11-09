package dev.tolana.testrss.rss;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedItemRepository extends JpaRepository<FeedItem, Long> {
    List<FeedItem> findAllBySourceOrderByPubDateDesc(Source source);
    List<FeedItem> findByPubDateGreaterThanEqualOrderByPubDateDesc(long time);
}
