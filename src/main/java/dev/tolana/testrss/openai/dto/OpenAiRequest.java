package dev.tolana.testrss.openai.dto;

import java.util.List;
import java.util.Map;

public record OpenAiRequest(
        String model,
        List<OpenAiMessage> messages,
        JsonResponse response_format
) {
}
