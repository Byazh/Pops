package com.byaz.pops.configuration;

/**
 * This class represents an abstract concept of a builder
 * @author Byaz
 */

public abstract class AbstractBuilder {

    /**
     * This variable represents the name of the creation, that can be
     * either a file or a folder
     */

    protected String name;

    /**
     * This method is the constructor of the class
     * @param name The name of the creation that has to be created
     */

    protected AbstractBuilder(String name){
        this.name = name;
    }

    /**
     * This abstract method builds the file or the folder,
     * it depends on the class that overrides it
     * @return The current builder object
     */

    protected abstract AbstractBuilder build();
}
