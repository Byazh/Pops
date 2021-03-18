package com.byaz.pops.components;

import com.byaz.pops.listeners.profile.RemoveListener;
import com.byaz.pops.listeners.profile.SelectListener;
import com.byaz.pops.profile.Profile;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lombok.Getter;

/**
 * This class represents a pane that contains the profile's name,
 * avatar and the delete image, called minus
 */

public class ProfileComponent extends Pane {

    /**
     * This variable represents the profile connected to the component
     */

    @Getter private Profile profile;

    /**
     * This constant represents the avatar of the profile
     */

    private final ImageView avatar = new ImageView();

    /**
     * This constant represents the name of the profile
     */

    private final Label name = new Label();

    /**
     * This constant represents the minus of the profile
     */

    @Getter private ImageView minus = new ImageView(new Image("/images/minus.png"));

    /**
     * This method is the constructor of the class
     * @param profile The profile connected to the component that has to be created
     */

    public ProfileComponent(Profile profile){
        this.profile = profile;
        getStylesheets().add("/styles/styles.css");
        registerImage();
        registerName();
        registerMinus();
        getChildren().addAll(avatar, name, minus);
    }

    /**
     * This method registers the avatar of the profile component and sets its listeners
     */

    private void registerImage(){
        avatar.setImage(profile.getType().getAvatar().getImage());
        avatar.setFitWidth(150);
        avatar.setFitHeight(150);
        avatar.setOnMouseEntered(event -> avatar.setOpacity(avatar.getOpacity() - 0.25));
        avatar.setOnMouseExited(event -> avatar.setOpacity(avatar.getOpacity() + 0.25));
        avatar.setOnMouseClicked(new SelectListener(profile));
    }


    /**
     * This method registers the name of the profile component
     */

    private void registerName(){
        name.setText(profile.getName());
        name.getStyleClass().add("profiles-name");
        name.setAlignment(Pos.CENTER);
        name.setPrefWidth(150);
        name.setLayoutY(getLayoutY() + 150);
    }

    /**
     * This method registers the minus of the profile component and sets its listeners
     */

    private void registerMinus(){
        minus.setLayoutX(getLayoutX() + 114);
        minus.setFitWidth(25);
        minus.setFitHeight(20);
        minus.setOnMouseEntered(event -> minus.setImage(new Image("/images/minus_red.png")));
        minus.setOnMouseExited(event -> minus.setImage(new Image("/images/minus.png")));
        minus.setOnMouseClicked(new RemoveListener(this));
    }
}
