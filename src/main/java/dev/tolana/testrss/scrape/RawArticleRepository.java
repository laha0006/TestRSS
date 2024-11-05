package dev.tolana.testrss.scrape;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RawArticleRepository extends JpaRepository<RawArticle, Long> {
    Optional<RawArticle> findByTitle(String title);

    List<RawArticle> findAllByOrderByTimestampDesc();
}
