package dev.tolana.testrss.openai.dto;

import java.util.List;
import java.util.Map;

public record Schema(
        String type,
        Map<String, Property> properties,
        List<String> required,
        boolean additionalProperties

) {
}
