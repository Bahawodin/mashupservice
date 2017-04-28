package se.javatar.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @author Ajmal Bahawodin {@literal <mailto:ajmal@javatar.se/>}
 */
@Service
public class WikipediaService {

    private final String WIKIPEDIA_API_URL = "https://en.wikipedia.org/w/api.php";
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper mapper = new ObjectMapper();

    public String getExtract(String title){

        String extract = "NOT FOUND";

        try {

            ResponseEntity<String> response
                 = restTemplate.getForEntity(WIKIPEDIA_API_URL+"?action=query&format=json&prop=extracts&exintro=true&redirects=true&titles="+title,
                 String.class);

            JsonNode root = mapper.readTree(response.getBody());

            JsonNode pathNode = root.path("query").path("pages");

            String extractSection = pathNode.fieldNames().next();

            extract = pathNode.path(extractSection).get("extract").textValue();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
        return extract;
    }

}
