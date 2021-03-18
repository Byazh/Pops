package com.byaz.pops.components;

import com.byaz.pops.helpers.ImageHelper;
import com.byaz.pops.series.SeriesGroup;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a scroll pane that contains a
 * group of series components
 * @author Byaz
 */

public class GroupComponent extends ScrollPane {

    /**
     * This variable represents the group connected to the component
     */

    private SeriesGroup group;

    /**
     * This variable represents the container of the series components
     */

    private HBox box = new HBox();

    /**
     * This variable represents the name of the group component
     */

    @Getter private Label name = new Label();

    /**
     * This constant represents the list of the existing group components
     */

    @Getter private static List<GroupComponent> components = new ArrayList<>();

    static {
        Arrays.stream(SeriesGroup.values())
                .filter(SeriesGroup::isAvailable)
                .forEach(GroupComponent::new);
    }

    /**
     * This method is the constructor of the class
     * @param group The group connected to the group component that has to be created
     */

    private GroupComponent(SeriesGroup group){
        this.group = group;
        components.add(this);
        setContent(box);
        getStylesheets().add("/styles/styles.css");
        getStyleClass().add("home-scroll-pane");
        registerBox();
        registerName();
    }

    /**
     * This method registers the box of the group component
     */

    private void registerBox(){
        setHbarPolicy(ScrollBarPolicy.ALWAYS);
        setVbarPolicy(ScrollBarPolicy.NEVER);
        setVmin(1);
    }

    /**
     * This method registers the name of the group component
     */

    private void registerName(){
        name.setText(group.getText());
        name.getStyleClass().add("home-scroll-name");
    }

    /**
     * This method adds a series component to the group component
     * @param component The series component that has to be added
     */

    public void add(SeriesComponent component){
        new Thread(() -> {
            component.setImage(ImageHelper.getImage(component.getSeries().getPoster()));
            FadeTransition transition = new FadeTransition(Duration.seconds(3.5), component);
            transition.setFromValue(0);
            transition.setToValue(1);
            transition.play();
            Platform.runLater(() -> box.getChildren().add(component));
        }).start();
    }

    /**
     * This method looks for the group component with the given series group
     * @param group The series group of the group component
     * @return The group component connected to the given series group
     */

    public static GroupComponent forGroup(SeriesGroup group){
        return components.stream()
                .filter(component -> component.group == group)
                .findFirst()
                .get();
    }

    /**
     * This method checks if the group component already has at least one series component
     * @return The boolean that will be true if the group component has at least one series component, false if not
     */

    public boolean hasStarted(){
        return box.getChildren().size() > 0;
    }
}
