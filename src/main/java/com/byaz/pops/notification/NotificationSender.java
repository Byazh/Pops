package com.byaz.pops.notification;

import java.awt.*;

/**
 * This class represents a notification sender, which
 * sends a windows notification to the user
 * @author Byaz
 */

public class NotificationSender {

    /**
     * This method sends a windows notification to the user
     * @param type The type of notification that has to be sent
     */

    public static void send(NotificationType type) {
        TrayIcon icon = new TrayIcon(Toolkit.getDefaultToolkit().createImage("icon.png"), null);
        icon.setToolTip(type.tip);
        try {
            SystemTray.getSystemTray().add(icon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        icon.displayMessage("Pops", type.message, type.messageType);
    }

    /**
     * This enum contains all the default notification types
     */

    public enum NotificationType {

        PROFILES_MINIMUM(TrayIcon.MessageType.ERROR,
                "You must have at least one profile", "Please, try creating a new one and deleting this one"),
        PROFILES_MAXIMUM(TrayIcon.MessageType.ERROR
        ,"You cannot create more than 5 profile in your account", "Please, delete one to continue creating profile"),
        CONFIGURATION(TrayIcon.MessageType.ERROR,
                "An error has occurred while creating the configuration files", "Please, try deleting the project's folder and restarting the application"),
        PROFILES_SAME_NAME(TrayIcon.MessageType.ERROR,
                "You cannot create two profile with the same name", "Please, choose another name"),
        PROFILES_GENERAL(TrayIcon.MessageType.ERROR,
                "An error has occurred while trying to delete the profile", "Please, try again later or delete the profile.json file"),
        BROWSER(TrayIcon.MessageType.ERROR,
                "An error has occurred while opening the default browser", "Please, try again later or set a default browser manually");

        /**
         * This variable represents the type of the message of the notification
         */

        private TrayIcon.MessageType messageType;

        /**
         * This variable represents the message of the notification
         */

        private String message;

        /**
         * This variable represents the tip attached to the notification
         */

        private String tip;

        /**
         * This method represents the constructor of the enum
         */

        NotificationType(TrayIcon.MessageType type, String message, String tip){
            this.messageType = type;
            this.message = message;
            this.tip = tip;
        }
    }
}
