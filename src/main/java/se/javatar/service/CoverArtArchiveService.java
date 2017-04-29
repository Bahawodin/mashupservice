package se.javatar.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author Ajmal Bahawodin {@literal <mailto:ajmal@javatar.se/>}
 */
@Service
public class CoverArtArchiveService {

    private final String COVER_ART_ARCHIVE_API_URL = "http://coverartarchive.org/";
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Method for getting the Album cover image from Cover Art Archive
     * @param id album id
     * @return a String representing link to the image
     */
    public String getAlbumImageByMBiD(String id) {

        String imgPath = "NOT FOUND";

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(COVER_ART_ARCHIVE_API_URL + "release-group/" + "2a0981fb-9593-3019-864b-ce934d97a16e", String.class);
            JsonNode root = mapper.readTree(response.getBody());
            Iterator<JsonNode> imageNode = root.path("images").elements();

            if (imageNode.hasNext()) {
                imgPath = imageNode.next().get("image").textValue();
            }
            return imgPath;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }

        return imgPath;
    }
}
