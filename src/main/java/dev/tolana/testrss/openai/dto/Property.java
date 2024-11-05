package dev.tolana.testrss.openai.dto;

public record Property(
        String type,
        String description,
        Items items
) {
}
