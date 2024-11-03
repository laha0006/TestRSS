package dev.tolana.testrss.rss;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedItemRepository extends JpaRepository<FeedItem, Long> {
    List<FeedItem> findAllBySourceOrderByPubDateDesc(Source source);
}
