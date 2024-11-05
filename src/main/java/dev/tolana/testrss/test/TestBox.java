package dev.tolana.testrss.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TestBox(
        int num,
        List<String> ids,
        List<List<String>> conditions


) {
}
