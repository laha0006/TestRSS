package dev.tolana.testrss.openai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.tolana.testrss.openai.dto.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class OpenAiService {


    private final RestTemplate restTemplate;

    @Value("${app.openai.url}")
    private String OPENAI_URL;

    public OpenAiService(@Qualifier("openaiRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private JsonResponse getJsonResponseScehma() {
        Map<String, Property> props = new HashMap<>();
        props.put("bullet_points", new Property("array", "A list of summarized bullet points from the article.", new Items("string")));
        props.put("political_bias", new Property("array", "The political bias of the article.", new Items("string")));
        JsonResponse schema = new JsonResponse("json_schema",
                new JsonSchema(
                        "article_summary",
                        true,
                        new Schema(
                                "object",
                                props,
                                List.of("bullet_points", "political_bias"),
                                false

                        )
                ));
        return schema;
    }

    private JsonOpenAiResponse mapResponseToJsonOpenAiResponse(OpenAiResponse response) {
        ObjectMapper mapper = new ObjectMapper();

        if (response != null && !response.choices().isEmpty()) {
            String content = response.choices().getFirst().message().content();
            try {
                return mapper.readValue(content, JsonOpenAiResponse.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public JsonOpenAiResponse summarize(String article) {
        List<OpenAiMessage> msgs = new ArrayList<>(List.of(
                new OpenAiMessage("system",
                        "You summarize articles in danish in a bullet point format, with one line of analyzed political bias"),
                new OpenAiMessage("user", article)
        ));

        JsonResponse schema = getJsonResponseScehma();

        OpenAiRequest request = new OpenAiRequest("gpt-4o", msgs, schema);

        OpenAiResponse response = restTemplate.postForObject(OPENAI_URL, request, OpenAiResponse.class);

        return mapResponseToJsonOpenAiResponse(response);

    }


}
