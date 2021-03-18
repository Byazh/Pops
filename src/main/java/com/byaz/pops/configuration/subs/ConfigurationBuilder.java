package com.byaz.pops.configuration.subs;

import com.byaz.pops.configuration.AbstractBuilder;
import com.byaz.pops.notification.NotificationSender;
import lombok.Getter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class represents a configuration file builder, which creates
 * a file inside of the project's main folder
 * @author Byaz
 */

public class ConfigurationBuilder extends AbstractBuilder {

    /**
     * This variable represents the json object contained in the configuration file
     */

    @Getter private JSONObject object;

    /**
     * This method is the constructor of the class
     * @param name The name of the configuration file that has to be created
     */

    public ConfigurationBuilder(String name) {
        super(name);
    }

    /**
     * This method is the overridden method from the extended class
     * @return The current builder object
     */

    @Override
    public ConfigurationBuilder build() {
        File file = new File(FolderBuilder.DIRECTORY + "\\" + name);
        if(!file.exists()){
            try {
                boolean success = file.createNewFile();
                try (FileWriter writer = new FileWriter(file)){
                    writer.write("{}");
                }
                if(!success){
                    NotificationSender.send(NotificationSender.NotificationType.CONFIGURATION);
                }
            } catch (IOException e) {
                NotificationSender.send(NotificationSender.NotificationType.CONFIGURATION);
            }
        }
        try {
            this.object = (JSONObject) new JSONParser().parse(new FileReader(file.getAbsolutePath()));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * This method saves the file by writing the new json object to the file,
     * so it deletes the previous content and adds the new one
     */

    public void save(){
        if(object == null){
            NotificationSender.send(NotificationSender.NotificationType.CONFIGURATION);
            return;
        }
        try (FileWriter writer = new FileWriter(FolderBuilder.DIRECTORY + "\\" + name)) {
            writer.write(object.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
