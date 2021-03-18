package com.byaz.pops.controllers.subs;

import com.byaz.pops.controllers.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * This class represents the loading page's controller
 * @author Byaz
 */

public class LoadingController extends AbstractController {

    /**
     * This variable represents the root of the stage
     */

    @FXML private AnchorPane root;

    /**
     * This variable represents the loading gif
     */

    @FXML private ImageView gif;

    /**
     * This method is one of the overridden methods from the initializable interface
     * that is called after the @FXML files get loaded
     */

    @Override
    protected void initialize() {}

    /**
     * This method is one of the overridden methods from the extended class
     */

    @Override
    protected void handleWidth() {
        root.widthProperty().addListener((observable, oldValue, newValue) -> gif.setLayoutX((double) newValue / 2 - 100));
    }

    /**
     * This method is one of the overridden methods from the extended class
     */

    @Override
    protected void handleHeight() {
        root.heightProperty().addListener((observable, oldValue, newValue) -> gif.setLayoutY((double) newValue / 2 - 100));
    }
}
