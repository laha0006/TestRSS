package dev.tolana.testrss.article;

import dev.tolana.testrss.article.model.ProcessedArticle;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/v1")
public class ProcessedArticleController {

    private final ProcessedArticleRepository repo;

    @GetMapping("/news")
    public List<ProcessedArticle> getAll() {
        long unixTimeNow = System.currentTimeMillis();
        long DAY_IN_SECONDS = 86_400_000L;
        long unixTimeOneDayAgo = unixTimeNow - DAY_IN_SECONDS;
        return repo.findByTimestampGreaterThanEqualOrderByTimestampDesc(unixTimeOneDayAgo);
    }

}
