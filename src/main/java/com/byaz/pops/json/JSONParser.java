package com.byaz.pops.json;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.net.URL;

/**
 * This class represents a json json for websites
 * @author Byaz
 */

public class JSONParser {

    /**
     * This variable represents the link of the site containing the json
     */

    private String source;

    /**
     * This method is the constructor of the class
     * @param source The link of the site that contains the json
     */

    public JSONParser(String source){
        this.source = source;
    }

    /**
     * This method is the getter of the json object
     * @return The json object contained in the site
     */

    public JSONObject getJSON() {
        try {
            return (JSONObject) JSONValue.parse(IOUtils.toString(new URL(source).openStream()));
        } catch (IOException e) {
           return null;
        }
    }
}
