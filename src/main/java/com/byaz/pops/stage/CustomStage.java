package com.byaz.pops.stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class represents a custom stage, created to handle
 * stages and views through an easier way
 * @author Byaz
 */

public class CustomStage extends Stage {

    /**
     * This constant represents the front view stage
     */

    public final static CustomStage FRONT = new CustomStage(220, 643).withView("Front");

    /**
     * This constant represents the profile view stage
     */

    public final static CustomStage PROFILES = new CustomStage(790, 524).withView("Profiles");

    /**
     * This constant represents the loading view stage
     */

    public final static CustomStage LOADING = new CustomStage(204, 340).withView("Loading");

    /**
     * This constant represents the home view stage
     */

    public final static CustomStage HOME = new CustomStage(700, 856).withView("Home");

    /**
     * This method is the constructor of the class
     * @param width The minimum width that the stage can reach
     * @param height The minimum height that the stage can reach
     */

    private CustomStage(int width, int height){
        setMinWidth(width);
        setMinHeight(height);
    }

    /**
     * This method is the setter of the view of the stage
     * @param name The name of the view of the stage
     * @return The current stage object
     */

    private CustomStage withView(String name){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/views/" + name + "View.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setScene(new Scene(root));
        return this;
    }

    /**
     * This method opens the stage
     */

    public void open(){
        setTitle("Pops | Alpha");
        getIcons().add(new Image("/images/logo.png"));
        setFullScreen(true);
        setFullScreenExitHint("");
        show();
    }
}
