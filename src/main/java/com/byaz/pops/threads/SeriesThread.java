package com.byaz.pops.threads;

import com.byaz.pops.components.GroupComponent;
import com.byaz.pops.components.SeriesComponent;
import com.byaz.pops.series.SeriesGroup;

import java.util.Arrays;

/**
 * This class represents a thread that loads the groups of series
 * @author Byaz
 */

public class SeriesThread extends Thread {

    /**
     * This method is the overridden method from the extended class
     */

    @Override
    public void run(){
        Arrays.stream(SeriesGroup.values())
                .filter(SeriesGroup::isAvailable)
                .forEach(group -> new Thread(() ->
                        group.getList().forEach(series -> {
                            GroupComponent.forGroup(group).add(new SeriesComponent(series));
                            try {
                                sleep(1500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        })).start());

    }
}
