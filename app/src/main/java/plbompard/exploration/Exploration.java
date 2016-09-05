package plbompard.exploration;

import android.app.Application;

import plbompard.exploration.managers.RestaurantManager;

/**
 * Created by plbompard on 01/09/2016.
 */
public class Exploration extends Application {

    private static RestaurantManager mRestaurantManager;

    @Override
    public void onCreate() {
        super.onCreate();

        initManagers();
    }


    private void initManagers() {
        mRestaurantManager = new RestaurantManager(getApplicationContext());
    }

    public static RestaurantManager getRestaurantManager() {
        return mRestaurantManager;
    }
}
