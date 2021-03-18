package com.byaz.pops.listeners.profile;

import com.byaz.pops.Pops;
import com.byaz.pops.components.GroupComponent;
import com.byaz.pops.controllers.subs.HomeController;
import com.byaz.pops.profile.Profile;
import com.byaz.pops.stage.CustomStage;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import lombok.AllArgsConstructor;

/**
 * This class represents the listener called when
 * the user selects a profile by clicking on its avatar
 * @author Byaz
 */

@AllArgsConstructor
public class SelectListener implements EventHandler<MouseEvent> {

    /**
     * This variable represents the profile connected to the clicked component
     */

    private Profile profile;

    /**
     * This method is the overridden method from the implemented interface
     */

    @Override
    public void handle(MouseEvent event) {
        CustomStage.PROFILES.close();
        Pops application = Pops.getInstance();
        application.setProfile(profile);
        HomeController.getInstance().getAvatar().setImage(profile.getType().getAvatar().getImage());
        if(haveStarted()){
            CustomStage.HOME.open();
            return;
        }
        Platform.runLater(CustomStage.LOADING::open);
        new Thread(() -> {
            while (true) {
                if(haveStarted()){
                    Platform.runLater(() -> {
                        CustomStage.LOADING.close();
                        CustomStage.HOME.open();
                    });
                    break;
                }
            }
        }).start();
    }

    /**
     * This method checks if the group components already have at least one series component each
     * @return The boolean that will be true if the group components have at least one series component each, false if not
     */

    private boolean haveStarted(){
        for(GroupComponent component : HomeController.getInstance().getComponents()){
            if(!component.hasStarted()){
                return false;
            }
        }
        return true;
    }
}
