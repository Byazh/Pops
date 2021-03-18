package com.byaz.pops.helpers;

import com.byaz.pops.json.JSONParser;
import org.json.simple.JSONObject;

/**
 * This class represents a helper class for openload related stuff
 * @author Byaz
 */

public class OpenloadHelper {

    private final static String API = "SrnlFFqv";
    private final static String LOGIN = "67d340758f53401d";

    public static JSONObject getResult(String fileId){
        JSONObject object = new JSONParser("https://api.openload.co/1/file/dlticket?file={file}&login={login}&key={key}"
                .replace("{file}", fileId)
                .replace("{login}", LOGIN)
                .replace("{key}", API))
                .getJSON();
        return (JSONObject) object.get("result");
    }

    public static String getDownload(String fileid, JSONObject result){
        JSONObject object = new JSONParser("https://api.openload.co/1/file/dl?file={file}&ticket={ticket}&captcha_response={captcha_response}"
                .replace("{file}", fileid)
                .replace("{ticket}", result.get("ticket").toString())
                .replace("{login}", LOGIN)
                .replace("{key}", API)
                .replace("{captcha_response}", result.get("captcha_url").toString())
        ).getJSON();
        return object.toString();
    }
}
