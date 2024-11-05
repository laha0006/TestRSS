package dev.tolana.testrss.article;

import dev.tolana.testrss.article.model.ProcessedArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcessedArticleRepository extends JpaRepository<ProcessedArticle, Long> {
    List<ProcessedArticle> findAllByOrderByTimestampDesc();
}
