package se.javatar.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.javatar.domain.Album;
import se.javatar.domain.MashupRequest;
import se.javatar.utils.Utils;

import java.util.List;

/**
 * @author Ajmal Bahawodin {@literal <mailto:ajmal@javatar.se/>}
 */
@Service
public class MashupService {

    @Autowired
    private WikipediaService wikipediaService;
    @Autowired
    private MusicBrainzSerivce musicBrainzSerivce;
    @Autowired
    private CoverArtArchiveService coverArtArchiveService;

    public MashupRequest getRequest(String mbid){

        MashupRequest mashupRequest = new MashupRequest();

        JsonNode artistRoot = musicBrainzSerivce.getArtistResourceByMBiD(mbid);
        JsonNode relations = artistRoot.path("relations");

        String wikipediaURLPath = Utils.extractWikipediaPath(relations);

        mashupRequest.setMbid(mbid);
        mashupRequest.setDescription(wikipediaService.getExtract(wikipediaURLPath));
        mashupRequest.setAlbums(getAlbums(artistRoot));

        return mashupRequest;
    }

    private List<Album> getAlbums(JsonNode jsonNode) {
        List<Album> albumList = Utils.collectArtistAlbums(jsonNode);

        albumList.forEach(album -> {
            String image = coverArtArchiveService.getAlbumImageByMBiD(album.getId());
            album.setImage(image);
        });

        return albumList;
    }
}
