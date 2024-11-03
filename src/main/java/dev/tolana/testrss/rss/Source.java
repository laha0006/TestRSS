package dev.tolana.testrss.rss;

import lombok.Getter;

@Getter
public enum Source {
    DR("https://www.dr.dk/nyheder/service/feeds/senestenyt");

    Source(String url) {
        this.url = url;
    }

    private final String url;

}
