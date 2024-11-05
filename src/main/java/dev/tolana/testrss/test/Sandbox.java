package dev.tolana.testrss.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

@Component
public class Sandbox {

    private final String API_URL = "https://clinicaltables.nlm.nih.gov/api/conditions/v3/search?terms=headache";


    public void test() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(API_URL, String.class);

        ObjectMapper mapper = new ObjectMapper();
        List<String> conditions;
        try {
            List<Object> stuff = mapper.readValue(response.getBody(), new TypeReference<List<Object>>() {});
            List<List<String>> rawConditions = mapper.convertValue(stuff.get(3), new TypeReference<List<List<String>>>() {});
            conditions = rawConditions.stream()
                    .flatMap(Collection::stream)
                    .toList();
            System.out.println("Flattend Conditions: " + conditions);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
