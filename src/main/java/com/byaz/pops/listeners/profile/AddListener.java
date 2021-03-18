package com.byaz.pops.listeners.profile;

import com.byaz.pops.components.ProfileComponent;
import com.byaz.pops.controllers.subs.ProfilesController;
import com.byaz.pops.helpers.ProfileHelper;
import com.byaz.pops.notification.NotificationSender;
import com.byaz.pops.profile.Profile;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * This class represents the listener called when
 * the user creates a profile by clicking on the add image
 * @author Byaz
 */

public class AddListener implements EventHandler<MouseEvent> {

    /**
     * This method is the overridden method from the implemented interface
     */

    @Override
    public void handle(MouseEvent event) {
        if(Profile.ProfileType.getRandom() != null){
            Profile profile = new Profile("Ciao");
            ProfilesController.getInstance().getBox().getChildren().add(new ProfileComponent(profile));
            ProfileHelper.addProfile(profile);
            return;
        }
        NotificationSender.send(NotificationSender.NotificationType.PROFILES_MAXIMUM);
    }
}
