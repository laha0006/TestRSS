package dev.tolana.testrss.scrape;

import dev.tolana.testrss.rss.Source;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class RawArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 500)
    private String title;
    @Column(length = 60000)
    private String content;
    private Source source;
    private long timestamp;


}
