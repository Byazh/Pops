package com.byaz.pops.connection;

import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * This class represents an http connection and a html
 * parser which uses the jsoup library
 * @author Byaz
 */

public class HttpConnection {

    /**
     * This variable represents the html document of the page with the given link
     */

    @Getter private Document document;

    /**
     * This method is the constructor of the class
     * @param link The link of the page of the connection
     */

    public HttpConnection(String link){
        try {
            this.document = Jsoup.connect(link).get();
        } catch (IOException ignored) {}
    }
}
