package com.byaz.pops.controllers;

import javafx.fxml.Initializable;
import javafx.scene.Node;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * This class represents an abstract concept of a controller
 * @author Byaz
 */

public abstract class AbstractController implements Initializable {

    /**
     * This abstract method handles the root's width edit, so when the
     * stage gets horizontally resized, this method will be "called"
     */

    protected abstract void handleWidth();

    /**
     * This abstract method handles the root's height edit, so when the
     * stage gets vertically resized, this method will be "called"
     */

    protected abstract void handleHeight();

    /**
     * This method registers adds the basic listeners to the given nodes
     * @param nodes The varargs of nodes
     */

     protected void addBasics(Node... nodes){
         Arrays.stream(nodes).forEach(node -> {
             node.setOnMouseEntered(event -> node.setOpacity(node.getOpacity() - 0.25));
             node.setOnMouseExited(event -> node.setOpacity(node.getOpacity() + 0.25));
         });
    }

    /**
     * This method is the overridden method from the initializable interface
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         initialize();
         handleWidth();
         handleHeight();
    }

    /**
     * This abstract method is called inside of its overloaded method
     */

    protected abstract void initialize();
}
