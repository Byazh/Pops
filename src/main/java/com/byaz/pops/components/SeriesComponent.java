package com.byaz.pops.components;

import com.byaz.pops.series.Series;
import javafx.scene.image.ImageView;
import lombok.Getter;

/**
 * This class represents an image view that's connected
 * to a series
 * @author Byaz
 */

public class SeriesComponent extends ImageView {

    /**
     * This variable represents the series connected to the component
     */

    @Getter private Series series;

    /**
     * This method is the constructor of the class
     * @param series The series connected to the component
     */

    public SeriesComponent(Series series){
        this.series = series;
        setFitWidth(300);
        setPreserveRatio(true);
        setOnMouseEntered(event -> setOpacity(getOpacity() - 0.20));
        setOnMouseExited(event -> setOpacity(getOpacity()  + 0.20));
    }
}
