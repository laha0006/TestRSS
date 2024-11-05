package dev.tolana.testrss.article.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ArticleBulletPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String bulletPoint;

    @ManyToOne()
    @JsonBackReference
    private ProcessedArticle article;
}
