package com.byaz.pops.controllers.subs;

import com.byaz.pops.components.ProfileComponent;
import com.byaz.pops.controllers.AbstractController;
import com.byaz.pops.helpers.ProfileHelper;
import com.byaz.pops.listeners.profile.AddListener;
import com.byaz.pops.profile.Profile;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import lombok.Getter;

import java.util.ArrayList;

/**
 * This class represents the profile page's controller
 * @author Byaz
 */

public class ProfilesController extends AbstractController {

    /**
     * This variable represents the only instance of the class
     */

    @Getter private static ProfilesController instance;

    /**
     * This variable represents the root of the stage
     */

    @Getter @FXML private AnchorPane root;

    /**
     * This variable represents the box that contains the profile components
     */

    @Getter @FXML private HBox box;

    /**
     * This variable represents the add image, which adds a new profile if available
     */

    @FXML private ImageView add;

    /**
     * This method is one of the overridden methods from the initializable interface
     * that is called after the @FXML files get loaded
     */

    @Override
    public void initialize() {
        addBasics(add);
        add.setOnMouseClicked(new AddListener());
        registerBox();
    }

    /**
     * This method is the constructor of the class
     */

    public ProfilesController(){
        instance = this;
    }

    /**
     * This method is one of the overridden methods from the extended class
     */

    @Override
    public void handleWidth() {
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            add.setLayoutX((double) newValue / 2 - 18);
        });
    }

    /**
     * This method is one of the overridden methods from the extended class
     */

    @Override
    public void handleHeight() {
        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            box.setLayoutY((double) newValue / 2 - 125);
            add.setLayoutY(box.getLayoutY() + 226);
        });
    }

    /**
     * This method adds the saved profiles to the box if there are any,
     * but if there aren't, it creates a default profile
     */

    private void registerBox(){
        box.prefWidthProperty().bind(root.widthProperty());
        ArrayList<Profile> profiles = ProfileHelper.getProfiles();
        if(profiles.size() == 0){
            ProfileHelper.addDefault();
            return;
        }
        profiles.forEach(profile -> box.getChildren().add(new ProfileComponent(profile)));
    }
}
