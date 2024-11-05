package dev.tolana.testrss.rss;

import lombok.Getter;

@Getter
public enum Source {
    DR("dr_dk","https://www.dr.dk/nyheder/service/feeds/indland"),
    POLITIKEN("politiken_dk","https://politiken.dk/rss/indland.rss");

    Source(String code,String url) {
        this.url = url;
        this.code = code;
    }

    private final String url;
    private final String code;

}
