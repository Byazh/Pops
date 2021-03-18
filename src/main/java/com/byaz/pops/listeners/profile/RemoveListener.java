package com.byaz.pops.listeners.profile;

import com.byaz.pops.components.ProfileComponent;
import com.byaz.pops.controllers.subs.ProfilesController;
import com.byaz.pops.helpers.ProfileHelper;
import com.byaz.pops.notification.NotificationSender;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import lombok.AllArgsConstructor;

/**
 * This class represents the listener called when
 * the user deletes a profile by clicking on the minus image
 * @author Byaz
 */

@AllArgsConstructor
public class RemoveListener implements EventHandler<MouseEvent> {

    /**
     * This variable represents the component that contains the clicked image
     */

    private ProfileComponent profile;

    /**
     * This method is the overridden method from the implemented interface
     */

    @Override
    public void handle(MouseEvent event) {
        HBox box = ProfilesController.getInstance().getBox();
        if(box.getChildren().size() != 1){
            box.getChildren().remove(profile);
            ProfileHelper.removeProfile(profile.getProfile());
            return;
        }
        NotificationSender.send(NotificationSender.NotificationType.PROFILES_MINIMUM);
    }
}
