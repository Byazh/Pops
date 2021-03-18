package com.byaz.pops.controllers.subs;

import com.byaz.pops.components.GroupComponent;
import com.byaz.pops.controllers.AbstractController;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;

import java.util.List;

/**
 * This class represents the home page's controller
 * @author Byaz
 */

public class HomeController extends AbstractController {

    /**
     * This variable represents the only instance of the class
     */

    @Getter private static HomeController instance;

    /**
     * This variable represents the root of the stage
     */

    @Getter @FXML private AnchorPane root;

    /**
     * This variable represents the avatar of the profile
     */

    @Getter @FXML private ImageView avatar;

    /**
     * This variable represents the down arrow
     */

    @FXML private ImageView arrow;

    /**
     * This variable represents the search text field
     */

    @FXML private TextField field;

    /**
     * This variable represents the search image
     */

    @FXML private ImageView search;

    /**
     * This variable represents the array list of group components
     */

    @Getter private List<GroupComponent> components = GroupComponent.getComponents().subList(0, 3);

    /**
     * This method is one of the overridden methods from the initializable interface
     * that is called after the @FXML files get loaded
     */

    @Override
    public void initialize() {
        addBasics(arrow);
        components.forEach(component -> root.getChildren().addAll(component, component.getName()));
    }

    /**
     * This method is the constructor of the class
     */

    public HomeController(){
        instance = this;
    }

    /**
     * This method is one of the overridden methods from the extended class
     */

    @Override
    public void handleWidth() {
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double width = (double) newValue;
            avatar.setLayoutX(width - 100);
            arrow.setLayoutX(avatar.getLayoutX() - 40);
            field.setLayoutX(arrow.getLayoutX() - 270);
            search.setLayoutX(field.getLayoutX() - 30);
            components.forEach(component -> {
                component.setLayoutX(width / 11);
                component.setPrefWidth(width - width / 5.5);
                component.getName().setLayoutX(width / 11);
            });
        });
    }

    /**
     * This method is one of the overridden methods from the extended class
     */

    @Override
    public void handleHeight() {
        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            double height = (double) newValue;
            for(int i = 0; i < 3; i++){
                GroupComponent current = components.get(i);
                GroupComponent previous;
                if(i == 0){
                    current.setLayoutY(height / 4.35);
                } else {
                    previous = components.get(i - 1);
                    current.setLayoutY(previous.getLayoutY() + height / 4);
                }
                current.getName().setLayoutY(current.getLayoutY() - 30);
            }
        });
    }
}
