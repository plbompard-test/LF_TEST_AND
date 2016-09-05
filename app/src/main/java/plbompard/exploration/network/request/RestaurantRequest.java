package plbompard.exploration.network.request;

import android.support.v4.util.Pair;

import plbompard.exploration.network.request.ABaseRequest;

/**
 * Created by plbompard on 01/09/2016.
 */
public class RestaurantRequest extends ABaseRequest {

    private final String EXTRA_PARAM__ID_RESTAURANT = "id_restaurant";

    private int mRestaurantId;

    public RestaurantRequest(int restaurantId) {
        mRestaurantId = restaurantId;
    }

    @Override
    protected String getMethodName() {
        return "restaurant_get_info";
    }

    @Override
    protected Pair<String, String> getExtraParameter() {
        return new Pair<>(EXTRA_PARAM__ID_RESTAURANT, String.valueOf(mRestaurantId));
    }
}
