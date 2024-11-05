package dev.tolana.testrss.openai.dto;

import java.util.List;

public record OpenAiResponse(List<OpenAiChoice> choices) {
}
