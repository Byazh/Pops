package com.byaz.pops.configuration.subs;

import com.byaz.pops.configuration.AbstractBuilder;
import com.byaz.pops.notification.NotificationSender;

import java.io.File;

/**
 * This class represents a folder builder, which creates
 * a folder inside of the project's main folder
 * @author Byaz
 */

public class FolderBuilder extends AbstractBuilder {

    /**
     * This static constant represents the directory of the project's main folder
     */

    final static String DIRECTORY = System.getenv("APPDATA") + "\\Pops";

    static {
        createFolder(DIRECTORY);
    }

    /**
     * This method is the constructor of the class
     * @param name The name of the folder that has to be created
     */

    public FolderBuilder(String name){
        super(name);
    }

    /**
     * This method creates a folder if it doesn't already exist
     * @param name The name of the folder
     */

    private static void createFolder(String name){
        File file = new File(name);
        if(!file.exists()){
            boolean success = file.mkdirs();
            if(!success){
                NotificationSender.send(NotificationSender.NotificationType.CONFIGURATION);
            }
        }
    }

    /**
     * This method is the overridden method from the implemented interface
     * @return The current builder object
     */

    @Override
    public FolderBuilder build() {
        createFolder(DIRECTORY + "\\" + name);
        return this;
    }
}
