package com.byaz.pops.helpers;

import com.byaz.pops.Pops;
import com.byaz.pops.components.ProfileComponent;
import com.byaz.pops.configuration.subs.ConfigurationBuilder;
import com.byaz.pops.controllers.subs.ProfilesController;
import com.byaz.pops.notification.NotificationSender;
import com.byaz.pops.profile.Profile;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * This class represents a helper class for profiles related stuff
 * @author Byaz
 */

public class ProfileHelper {

    /**
     * This constant represents the profiles configuration builder
     */

    private final static ConfigurationBuilder builder = Pops.getInstance().getProfiles();

    /**
     * This constant represents the json object contained in the configuration
     */

    private final static JSONObject configuration = builder.getObject();

    /**
     * This method adds a profile to the json configuration file
     * @param profile The name of the profile that has to be added
     */

    public static void addProfile(Profile profile){
        if(configuration.get(profile.getType().getIndex()) == null){
            JSONObject object = new JSONObject();
            object.put("name", profile.getName());
            object.put("type", profile.getType().name());
            configuration.put(profile.getType().getIndex(), object);
            builder.save();
            return;
        }
        NotificationSender.send(NotificationSender.NotificationType.PROFILES_SAME_NAME);
    }

    /**
     * This method removes the profile with the given name from the configuration file
     * @param profile The name of the profile that has to be removed
     */

    public static void removeProfile(Profile profile){
        if(configuration.get(profile.getType().getIndex()) != null){
            configuration.remove(profile.getType().getIndex());
            builder.save();
            return;
        }
        NotificationSender.send(NotificationSender.NotificationType.PROFILES_GENERAL);
    }

    /**
     * This method is the getter of the profiles array list
     * @return The array list containing all the profiles
     */

    public static ArrayList<Profile> getProfiles(){
        ArrayList<Profile> profiles = new ArrayList<>();
        configuration.keySet().forEach(element -> profiles.add(new Profile((JSONObject) configuration.get(element))));
        return profiles;
    }

    /**
     * This method adds the default profile to the respective
     * box and to the json configuration file
     */

    public static void addDefault(){
        Profile profile = new Profile("Default");
        ProfilesController.getInstance().getBox().getChildren().add(new ProfileComponent(profile));
        addProfile(profile);
    }
}
