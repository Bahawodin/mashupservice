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
public class MusicBrainzSerivce {

    private final String MUSIC_BRAINZ_API_URL = "http://musicbrainz.org/ws/2";
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper mapper = new ObjectMapper();

    public JsonNode getArtistResourceByMBiD(String mbid) {

        JsonNode root = null;

        try {
            ResponseEntity<String> response
                    = restTemplate.getForEntity(MUSIC_BRAINZ_API_URL + "/artist/" + mbid + "?&fmt=json&inc=url-rels+release-groups", String.class);
            root = mapper.readTree(response.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }

        return root;
    }
}
