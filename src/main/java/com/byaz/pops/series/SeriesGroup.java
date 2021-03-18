package com.byaz.pops.series;

import com.byaz.pops.Pops;
import com.uwetrottmann.tmdb2.Tmdb;
import com.uwetrottmann.tmdb2.entities.TvShowResultsPage;
import com.uwetrottmann.tmdb2.services.TvService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This enum contains all the groups of series
 * @author Byaz
 * @credits Uwetrottmann
 */

@AllArgsConstructor
public enum SeriesGroup {

    CONTINUE_WATCHING("Continue watching"),
    LISTED("On your list"),
    MOST_POPULAR("Most popular on Pops"),
    TOP_RATED("Top rated on Pops"),
    AIRING_TODAY("Today's series");

    /**
     * This variable is the text that will be displayed over the group
     * component connected to this series group
     */

    @Getter private String text;

    /**
     * This method checks if the series group is available
     * @return The boolean that will be true if the series group is available, false if not
     */

    public boolean isAvailable(){
        switch (this){
            case CONTINUE_WATCHING:
            case LISTED:
                if(Pops.getInstance().getProfile() == null){
                    return false;
                }
                break;
        }
        return true;
    }

    /**
     * This method returns the list of series of the current series group
     * @return The list of series of the current series group
     */

    public List<Series> getList(){
        List<Series> list = new ArrayList<>();
        TvService service = new Tmdb("e62fc9fcef4e4b5739481754d8ec99a6").tvService();
        switch (this){
            case CONTINUE_WATCHING:
                addArray(list, "continue-watching");
                break;
            case LISTED:
                addArray(list, "listed");
                break;
            case MOST_POPULAR:
                addList(list, service.popular(1, "en-US"));
                break;
            case TOP_RATED:
                addList(list, service.topRated(1, "en-US"));
                break;
            case AIRING_TODAY:
                addList(list, service.airingToday(1, "en-US"));
                break;
        }
        return list;
    }

    /**
     * This method adds a list of series from the array with the given key
     * @param list The list where the list of series will be added
     * @param key The key of the array that has to be added
     */

    private static void addArray(List<Series> list, String key){
        JSONObject object = Pops.getInstance().getProfileObject();
        if(object.get(key) != null || ((JSONArray) object.get(key)).size() > 0){
            for(Object element : (JSONArray) object.get(key)){
                list.add(new Series((String) element));
            }
        }
    }

    /**
     * This method adds a list of series from the shows list obtained from the given call
     * @param list The list where the list of series will be added
     * @param call The call from the tv service
     */

    private static void addList(List<Series> list, Call<TvShowResultsPage> call){
        try {
            Response<TvShowResultsPage> response = call.execute();
            if(response.isSuccessful()){
                response.body().results.forEach(show -> {
                    Series series = new Series(show);
                    if(series.exists()){
                        list.add(series);
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
