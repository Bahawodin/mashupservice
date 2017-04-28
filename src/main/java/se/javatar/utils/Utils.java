package se.javatar.utils;

import com.fasterxml.jackson.databind.JsonNode;
import se.javatar.domain.Album;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Ajmal Bahawodin {@literal <mailto:ajmal@javatar.se/>}
 */
public class Utils {

    public static String extractWikipediaPath(JsonNode node) {

        String wikiPath = null;
        Iterator<JsonNode> elements = node.elements();

        while (elements.hasNext()){
            JsonNode relationNode = elements.next();
            if (relationNode.get("type").textValue().equals("wikipedia")) {
                wikiPath =  relationNode.path("url").get("resource").textValue();
                break;
            }
        }

        if(wikiPath!=null){
            String[] pathSplitArr = wikiPath.split("/");
            wikiPath = pathSplitArr[pathSplitArr.length-1];
        }

        return wikiPath;
    }

    public static List<Album> collectArtistAlbums(JsonNode jsonNode) {

        List<Album> albumList = new ArrayList<>();

        Iterator<JsonNode> elements = jsonNode.path("release-groups").elements();

        if (elements != null) {

            elements.forEachRemaining((JsonNode node) -> {
                if (node.get("primary-type").textValue().equalsIgnoreCase("album")) {
                    albumList.add(new Album(node.get("id").textValue(), node.get("title").textValue(), null));
                }
            });
        }

        return albumList;
    }
}
