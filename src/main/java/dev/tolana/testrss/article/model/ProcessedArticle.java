package dev.tolana.testrss.article.model;

import dev.tolana.testrss.rss.Source;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class ProcessedArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private Source source;
    private String url;
    private long timestamp;
    @Column(length = 1024)
    private String bias;

    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ArticleBulletPoint> bulletPoints = new HashSet<>();
}
