package com.byaz.pops.series;

import com.byaz.pops.connection.HttpConnection;
import com.uwetrottmann.tmdb2.entities.BaseTvShow;
import lombok.Getter;
import org.jsoup.nodes.Document;

/**
 * This class represents the series concept
 * @author Byaz
 */

@Getter
public class Series {

    /**
     * This variable represents the name of the series
     */

    private String name;

    /**
     * This variable represents the html document of the page with the given link
     */

    private Document document;

    /**
     * This variable represents the poster of the series
     */

    private String poster;

    /**
     * This method is the constructor of the class
     * @param name The name of the series that has to be created
     */

    Series(String name) {
        this.name = name;
        this.document = new HttpConnection("https://www.guardaserie.media/" + name.replace(" ", "-").replace("'", "")).getDocument();
    }

    /**
     * This method is the constructor of the class
     * @param show The show connected to the series that has to be created
     */

    Series(BaseTvShow show){
        this(show.name);
        this.poster = "https://image.tmdb.org/t/p/original/" + show.backdrop_path;
    }

    /**
     * This method checks if the series exists
     * @return The boolean that will be true if it exists, false if not
     */

    boolean exists(){
        return document != null;
    }

    /**
     * This method is the getter of the poster of the series
     * @return The poster of the series
     */

    public String getPoster(){
        return poster;
    }

    /**
     * This method is the setter of the poster of the series
     * @param id The id of the poster of the series
     */

    public void setPoster(String id){
        poster = id;
    }

    /**
     * This method is the getter of the description of the series
     * @return The description of the series
     */

    public String getDescription(){
        return document.selectFirst("span.desc-single-serie").text();
    }

    /**
     * This method is the getter of the genre of the series
     * @return The genre of the series
     */

    public String getGenre(){
        return getDetail(1);
    }

    /**
     * This method is the getter of the nationality of the series
     * @return The nationality of the series
     */

    public String getNationality(){
        return getDetail(2);
    }

    /**
     * This method is the getter of the creator of the series
     * @return The creator of the series
     */

    public String getCreator(){
        return getDetail(3);
    }

    /**
     * This method returns the text of the more-details class at the given index
     * @param index The index of the class
     * @return The appended text of the class
     */

    private String getDetail(int index){
        String[] splits = document.select("p.more-details").get(index).text().split(" ");
        StringBuilder builder = new StringBuilder();
        for(int i = 1; i < splits.length; i++){
            builder.append(splits[i]).append(" ");
        }
        return builder.toString();
    }
}
