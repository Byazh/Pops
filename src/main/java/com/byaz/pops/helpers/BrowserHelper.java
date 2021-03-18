package com.byaz.pops.helpers;

import com.byaz.pops.notification.NotificationSender;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * This class represents a helper class for browsing related stuff
 * @author Byaz
 */

public class BrowserHelper {

    /**
     * This method tries to open the browser and the page with the given link
     * @param link The link of the page that has to be opened
     */

    public static void browse(String link){
        if(!Desktop.isDesktopSupported() || !Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)){
            NotificationSender.send(NotificationSender.NotificationType.BROWSER);
            return;
        }
        try {
            Desktop.getDesktop().browse(new URL(link).toURI());
        } catch (IOException | URISyntaxException e) {
            NotificationSender.send(NotificationSender.NotificationType.BROWSER);
        }
    }
}
