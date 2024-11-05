package dev.tolana.testrss.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Value("${app.openai.API_KEY}")
    private String openAiKey;

    @Bean
    @Qualifier("openaiRestTemplate")
    public RestTemplate template(){
        RestTemplate template = new RestTemplate();
        template.getInterceptors().add(((request, body, execution) -> {
            request.getHeaders().add("Authorization","Bearer "+openAiKey);
            return execution.execute(request,body);
        }));
        return template;
    }

}
