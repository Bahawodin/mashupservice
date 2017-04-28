package se.javatar.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ajmal Bahawodin {@literal <mailto:ajmal@javatar.se/>}
 */
public class MashupRequest implements Serializable{

    private String mbid;

    private String description;

    private List<Album> albums;

    public MashupRequest() {
    }

    public MashupRequest(String mbid, String description, List<Album> albums) {
        this.mbid = mbid;
        this.description = description;
        this.albums = albums;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return "MashupRequest{" +
                "mbid='" + mbid + '\'' +
                ", description='" + description + '\'' +
                ", albums=" + albums +
                '}';
    }
}
