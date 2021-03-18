package com.byaz.pops;

import com.byaz.pops.configuration.subs.ConfigurationBuilder;
import com.byaz.pops.helpers.OpenloadHelper;
import com.byaz.pops.profile.Profile;
import com.byaz.pops.stage.CustomStage;
import com.byaz.pops.threads.SeriesThread;
import javafx.application.Application;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

/**
 * This class is the main class of the project
 * @author Byaz
 */

public class Pops extends Application {

    /**
     * This variable represents the only instance of the class
     */

    @Getter private static Pops instance;

    /**
     * This variable represents the profile configuration instance
     */

    @Getter private ConfigurationBuilder profiles = new ConfigurationBuilder("profile.json").build();

    /**
     * This variable represents the profile which is currently being used
     */

    @Getter @Setter private Profile profile;

    /**
     * This method is the constructor of the class
     */

    public Pops(){
        instance = this;
    }

    /**
     * This method is the overridden method from the extended class
     */

    @Override
    public void start(Stage stage) {
        //new SeriesThread().start();
        System.out.println(OpenloadHelper.getDownload("mrt7-YZsyJk&login=67d340758f53401d", OpenloadHelper.getResult("mrt7-YZsyJk&login=67d340758f53401d")));
        //CustomStage.FRONT.open();
    }

    /**
     * This method is the main method of the project
     */

    public static void main(String... args){
        launch(args);
    }

    /**
     * This method is the getter of the json object of the profile
     * which is currently being used
     * @return The json object of the current profile
     */

    public JSONObject getProfileObject(){
        return (JSONObject) profiles.getObject().get(profile.getType().getIndex());
    }
}
