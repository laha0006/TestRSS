package dev.tolana.testrss.openai;

import dev.tolana.testrss.article.ProcessedArticleRepository;
import dev.tolana.testrss.article.model.ArticleBulletPoint;
import dev.tolana.testrss.article.model.ProcessedArticle;
import dev.tolana.testrss.openai.dto.JsonOpenAiResponse;
import dev.tolana.testrss.rss.Source;
import dev.tolana.testrss.scrape.RawArticle;
import dev.tolana.testrss.scrape.RawArticleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ArticleSummarizer {

    private final OpenAiService openAiService;
    private final ProcessedArticleRepository processedArticleRepository;
    private final RawArticleRepository rawArticleRepository;


    public void process() {
        System.out.println("AI PROCESS...");
        List<ProcessedArticle> processedArticles = processedArticleRepository.findAllByOrderByTimestampDesc();

        long newestSummarized;
        if (!processedArticles.isEmpty()) {
            newestSummarized = processedArticles.getFirst().getTimestamp();
        } else {
            newestSummarized = 0;
        }

        List<RawArticle> rawArticles = rawArticleRepository.findAllByOrderByTimestampDesc();

        if (rawArticles.isEmpty()) return;

        rawArticles.stream()
                .takeWhile(ra -> ra.getTimestamp() > newestSummarized)
                .map(ra -> {
                    String title = ra.getTitle();
                    Source source = ra.getSource();
                    String url = source.getUrl();
                    long timestamp = ra.getTimestamp();
                    JsonOpenAiResponse data = openAiService.summarize(ra.getContent());
//                    JsonOpenAiResponse data = new JsonOpenAiResponse(
//                            List.of("the point of", "another point", "yeppers", "nodders"),
//                            List.of("No bias!"));

                    ProcessedArticle processedArticle = ProcessedArticle.builder()
                            .title(title)
                            .source(source)
                            .url(url)
                            .timestamp(timestamp)
                            .bias(data.political_bias().getFirst())
                            .build();

                    Set<ArticleBulletPoint> bulletPoints = data.bullet_points()
                            .stream()
                            .map(bp -> ArticleBulletPoint.builder()
                                    .bulletPoint(bp)
                                    .article(processedArticle)  // Set the ProcessedArticle reference here
                                    .build())
                            .collect(Collectors.toSet());

                    processedArticle.setBulletPoints(bulletPoints);

                    return processedArticle;
                })
                .forEach(processedArticleRepository::save);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
