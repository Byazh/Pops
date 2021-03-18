package com.byaz.pops.helpers;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * This class represents a helper class for downloading images from a website
 * @author Byaz
 */

public class ImageHelper {

    /**
     * This method returns the fx image contained in the page with the given link
     * @param link The link of the page that contains the image
     * @return The image contained in the page with the given link
     */

    public static Image getImage(String link){
        Image image = null;
        try {
            BufferedImage buffered = ImageIO.read(new URL(link));
            image = SwingFXUtils.toFXImage(buffered, null);
        } catch (IOException ignore) {}
        return image;
    }
}
