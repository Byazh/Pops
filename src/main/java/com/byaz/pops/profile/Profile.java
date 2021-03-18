package com.byaz.pops.profile;

import com.byaz.pops.helpers.ProfileHelper;
import com.byaz.pops.notification.NotificationSender;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Getter;
import org.json.simple.JSONObject;

/**
 * This class represents the profile concept
 * @author Byaz
 */

@Getter
public class Profile {

    /**
     * This variable represents the name of the profile
     */

    private String name;

    /**
     * This variable represents the type of the profile
     */

    private ProfileType type;

    /**
     * This method is the constructor of the class
     * @param name The name of the profile that has to be created
     */

    public Profile(String name){
        this.name = name;
        this.type = ProfileType.getRandom();
    }

    /**
     * This method is the constructor of the class
     * @param object The json object of the profile that has to be created
     */

    public Profile(JSONObject object){
        this.name = object.get("name").toString();
        this.type = ProfileType.forName(object.get("type").toString());
    }

    /**
     * This enum contains all the possible profile
     * @author Byaz
     */

    public enum ProfileType {

        DOPE("first"),
        NINJA("second"),
        ANGRY("third"),
        PANDA("fourth"),
        SUPER("fifth");

        /**
         * This variable represents the index of the profile
         */

        @Getter private String index;

        /**
         * This variable represents the avatar of the profile
         */

        @Getter private ImageView avatar;

        /**
         * This method is the constructor of the profile
         * @param index The index of the profile that has to be created, for example first, second ecc.
         */

        ProfileType(String index){
            this.index = index;
            this.avatar = new ImageView(new Image("/profiles/" + index + ".png"));
        }

        /**
         * This method searches for the profile type with the given name
         * @param name The name of the profile type
         * @return The profile type with the given name
         */

        public static ProfileType forName(String name){
            for(ProfileType type : values()){
                if(type.name().equalsIgnoreCase(name)){
                    return type;
                }
            }
            NotificationSender.send(NotificationSender.NotificationType.PROFILES_GENERAL);
            return null;
        }

        /**
         * This method searches for a profile type which is not being used
         * @return A profile type which is not being used
         */

        public static ProfileType getRandom(){
            for(ProfileType type : values()){
                if(type.isAvailable()){
                    return type;
                }
            }
            return null;
        }

        /**
         * This method checks if the current type is already being used by other profile
         * @return The boolean that will be true if it's not used, false if not
         */

        private boolean isAvailable(){
            for(Profile profile : ProfileHelper.getProfiles()){
                if(profile.getType() == this){
                    return false;
                }
            }
            return true;
        }
    }
}
