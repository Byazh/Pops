package com.byaz.pops.controllers.subs;

import com.byaz.pops.controllers.AbstractController;
import com.byaz.pops.helpers.BrowserHelper;
import com.byaz.pops.stage.CustomStage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.awt.*;

/**
 * This class represents the front page's controller
 * @author Byaz
 */

public class FrontController extends AbstractController {

    /**
     * This variable represents the root of the stage
     */

    @FXML private AnchorPane root;

    /**
     * This variable represents the vbox that contains the background image
     */

    @FXML private VBox box;

    /**
     * This variable represents the background image
     */

    @FXML private ImageView background;

    /**
     * This variable represents the pane that makes the background image darker
     */

    @FXML private Pane darkPane;

    /**
     * This variable represents the pane that contains the two labels and the button
     */

    @FXML private Pane containerPane;

    /**
     * This variable represents the label that stands at the top of the container pane
     */

    @FXML private Label topLabel;

    /**
     * This variable represents the label that stands right under the top label
     */

    @FXML private Label downLabel;

    /**
     * This variable represents the only button of the scene
     */

    @FXML private Button button;

    /**
     * This variable represents the pane containing the two social labels
     */

    @FXML private Pane socialPane;

    /**
     * This variable represents the instagram label
     */

    @FXML private Label instagram;

    /**
     * This variable represents the github label
     */

    @FXML private Label github;

    /**
     * This variable represents the instagram's logo image
     */

    @FXML private ImageView instagramLogo;

    /**
     * This variable represents the github's logo image
     */

    @FXML private ImageView githubLogo;

    /**
     * This method is one of the overridden methods from the initializable interface
     * that is called after the @FXML files get loaded
     */

    @Override
    public void initialize() {
        addBasics(button, instagram, github);
        registerContainers();
        registerGraphics();
    }

    /**
     * This method is one of the overridden methods from the extended class
     */

    @Override
    public void handleWidth() {
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double width = (double) newValue;
            if(width < 770){
                downLabel.setPrefWidth(width / 1.3);
            } else {
                downLabel.setPrefWidth(770);
            }
            if(width < 775){
                topLabel.setPrefWidth(width / 1.3);
            } else {
                topLabel.setPrefWidth(740);
            }
            if(width < 385){
                button.setPrefWidth(width / 1.7);
            } else {
                button.setPrefWidth(313);
            }
            socialPane.setLayoutX(root.getWidth() - 182.5);
            instagram.setLayoutX(25);
            github.setLayoutX(95);
        });
    }

    /**
     * This method is one of the overridden methods from the extended class
     */

    @Override
    public void handleHeight() {
        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            containerPane.setLayoutY((double) newValue / 3.2);
            downLabel.setLayoutY(topLabel.getLayoutY() + 70);
            button.setLayoutY(downLabel.getLayoutY() + 70);
            socialPane.setLayoutY(root.getHeight() - 100);
            instagram.setLayoutY(12);
            github.setLayoutY(12);
        });
    }

    /**
     * This method registers the containers and their size's bindings
     */

    private void registerContainers(){
        box.prefWidthProperty().bind(root.widthProperty());
        box.prefHeightProperty().bind(root.heightProperty());
        darkPane.prefWidthProperty().bind(root.widthProperty());
        darkPane.prefHeightProperty().bind(root.heightProperty());
    }

    /**
     * This method registers the graphics
     */

    private void registerGraphics(){
        background.setFitWidth(Toolkit.getDefaultToolkit().getScreenSize().width + 5);
        background.setFitHeight(Toolkit.getDefaultToolkit().getScreenSize().height + 5);
        instagram.setGraphic(instagramLogo);
        github.setGraphic(githubLogo);
    }

    /**
     * This method sets the button click listener, it closes the
     * current stage and opens the new one
     */

    @FXML
    private void onButtonClick(){
        CustomStage.FRONT.hide();
        CustomStage.PROFILES.open();
    }

    /**
     * This method sets the instagram label click listener,
     * opening the developer's instagram page
     */

    @FXML
    private void onInstagramClick(){
        BrowserHelper.browse("https://www.instagram.com/francesco_sottero/");
    }

    /**
     * This method sets the github label click listener,
     * opening the developer's github page
     */

    @FXML
    private void onGithubClick(){
        BrowserHelper.browse("https://github.com/AThorneGaming?tab=repositories");
    }
}
