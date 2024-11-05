package dev.tolana.testrss.openai.dto;

import java.util.List;

public record JsonOpenAiResponse(
        List<String> bullet_points,
        List<String> political_bias
) {
}
