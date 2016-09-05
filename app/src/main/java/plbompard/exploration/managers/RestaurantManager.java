package plbompard.exploration.managers;

import android.content.Context;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import plbompard.exploration.models.Restaurant;
import plbompard.exploration.network.Callback;
import plbompard.exploration.network.RequestLauncher;
import plbompard.exploration.network.request.CachableJsonObjectRequest;
import plbompard.exploration.network.request.RestaurantRequest;
import plbompard.exploration.network.parser.ExplorationObjectMapper;

/**
 * Created by plbompard on 01/09/2016.
 */
public class RestaurantManager {

    Context mContext;

    public RestaurantManager(Context context) {
        mContext = context;
    }

    public void requestRestaurant(int id, final GetRestaurantCallback callback) {

        RestaurantRequest request = new RestaurantRequest(id);
        JSONObject cachedData = getVolleyCacheEntryByUrl(request.getUrl().toString());

        if (cachedData != null) {
            try {
                Restaurant restaurant = ExplorationObjectMapper.restaurantReader().readValue(cachedData.optJSONObject("data").toString());
                callback.onSuccess(restaurant);

                return;
            } catch (IOException e) {
                Log.d(RestaurantManager.class.getSimpleName(), "Unable to parse cached data for restaurant id " + id);
            }

        }

        //No cache, check network
        CachableJsonObjectRequest getRestaurantInfoRequest = new CachableJsonObjectRequest
                (Request.Method.GET, request.getUrl().toString(), null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Restaurant restaurant = ExplorationObjectMapper.restaurantReader().readValue(response.optJSONObject("data").toString());
                            callback.onSuccess(restaurant);
                        } catch (IOException e) {
                            callback.onError(e);
                            Log.d(RestaurantManager.class.getSimpleName(), "Error while parsing the json repsonse.");
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error);
                        Log.d(RestaurantManager.class.getSimpleName(), "Something went wrong when requesting the restaurant : " + error.getMessage());
                    }
                });
        getRestaurantInfoRequest.setShouldCache(true);
        getRestaurantInfoRequest.setRetryPolicy(new DefaultRetryPolicy(5000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestLauncher.getInstance(mContext).addToRequestQueue(getRestaurantInfoRequest);
    }

    public JSONObject getVolleyCacheEntryByUrl(String relative_url) {
        // RequestQueue queue = Volley.newRequestQueue(c);
        Cache cache = RequestLauncher
                .getInstance(mContext)
                .getRequestQueue()
                .getCache();
        Cache.Entry cacheEntry = cache.get(relative_url);
        if (cacheEntry == null) return null;

        String cachedResponse = new String(cacheEntry.data);

        try {
            JSONObject cacheObj = new JSONObject(cachedResponse);
            Log.e("CacheResult", cacheObj.toString());
            return cacheObj;

        } catch (JSONException e) {

            return null;
        }
    }

    public interface GetRestaurantCallback extends Callback<Restaurant> {

        @Override
        public void onSuccess(Restaurant data);

        @Override
        public void onError(Exception e);
    }
}
