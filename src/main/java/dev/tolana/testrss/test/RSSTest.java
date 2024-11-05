package dev.tolana.testrss.test;

import com.rometools.rome.feed.synd.SyndFeed;
import dev.tolana.testrss.article.ProcessedArticleRepository;
import dev.tolana.testrss.article.model.ProcessedArticle;
import dev.tolana.testrss.openai.ArticleSummarizer;
import dev.tolana.testrss.openai.OpenAiService;
import dev.tolana.testrss.rss.FeedSaver;
import dev.tolana.testrss.rss.RSSReader;
import dev.tolana.testrss.scrape.NewsHtmlParser;
import dev.tolana.testrss.scrape.NewsScraper;
import dev.tolana.testrss.scrape.RawArticleSaver;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class RSSTest implements CommandLineRunner {

    private final RSSReader rssReader;
    private final FeedSaver feedSaver;
    private final NewsScraper newsScraper;
    private final NewsHtmlParser newsHtmlParser;
    private final RawArticleSaver rawArticleSaver;
    private final OpenAiService service;
    private final ProcessedArticleRepository processedArticleRepository;
    private final ArticleSummarizer summarizer;
    private final Sandbox sandbox;

    @Override
    public void run(String... args) {
        System.out.println("cmd runner ran.");
        sandbox.test();
//        summarizer.process();
//        Optional<ProcessedArticle> processedArticleOptional = processedArticleRepository.findById(6L);
//        if (processedArticleOptional.isPresent()) {
//            ProcessedArticle processedArticle = processedArticleOptional.get();
//            processedArticle.getBulletPoints().forEach(b -> System.out.println(b.getBulletPoint()));
//        }
//        System.out.println(service.summarize("Mand har mistet livet i villabrand i Stagstrup Politiet formoder, at der er tale om husets 59-årige beboer. Hans pårørende er underrettet.En mand er søndag omkommet i en brand i Stagstrup syd for Thisted, oplyser Midt- og Vestjyllands Politi til TV Midtvest.Vagtchefen hos Midt- og Vestjyllands Politi, Anders Bøje Hansen, oplyser til regionalmediet, at der i huset bor en 59-årig mand, som formentlig er identisk med den afdøde.Hans pårørende er underrettet, men den endelige identifikation er endnu ikke sket.Det fremgår af oplysninger fra Beredskabsstyrelsens hjemmeside, at brandstationen i Thisted fik melding om branden klokken 11.34 søndag.Årsagen til branden er endnu ikke klarlagt. Det er i første omgang en opgave for politiets brandteknikere at fastslå, hvorvidt der er tale om en forbrydelse eller ej."));
//        feedSaver.saveAll();
//        newsHtmlParser.parse();
//        String html = newsScraper.scrape("https://politiken.dk/kultur/art10145447/Dronning-Margrethe-%C2%BBMin-mor-syntes-at-G%C3%B6ring-var-ganske-r%C3%A6dselsfuld%C2%AB");
//        newsHtmlParser.parsePolitiken("lol");
        //        feedSaver.saveAll();
//        SyndFeed syndFeed = rssReader.readRSS("https://www.dr.dk/nyheder/service/feeds/senestenyt");
//        System.out.println(syndFeed.getEntries().getFirst().getPublishedDate().getTime());

//        syndFeed.getEntries().forEach(entry -> System.out.println(entry.getTitle()) );
//        System.out.println("HELLOW WORLD! #####################");
    }
}
