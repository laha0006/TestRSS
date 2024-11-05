package dev.tolana.testrss.openai.dto;

public record JsonSchema(
        String name,
        boolean strict,
        Schema schema
) {
}
