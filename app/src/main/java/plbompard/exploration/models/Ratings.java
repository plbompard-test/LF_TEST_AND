package plbompard.exploration.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by plbompard on 01/09/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ratings {

    @JsonProperty("global_rate")
    private int mGlobalRate;

    @JsonProperty("food_rate")
    private int mFoodRate;

    @JsonProperty("service_rate")
    private int mServiceRate;

    @JsonProperty("ambience_rate")
    private int mAmbienceRate;

    @JsonProperty("price_rate")
    private int mPriceRate;

    @JsonProperty("noice_rate")
    private int mNoiceRate;

    @JsonProperty("waiting_rate")
    private int mWaitingRate;

    public int getGlobalRate() {
        return mGlobalRate;
    }

    public int getFoodRate() {
        return mFoodRate;
    }

    public int getServiceRate() {
        return mServiceRate;
    }

    public int getAmbienceRate() {
        return mAmbienceRate;
    }

    public int getPriceRate() {
        return mPriceRate;
    }

    public int getNoiceRate() {
        return mNoiceRate;
    }

    public int getWaitingRate() {
        return mWaitingRate;
    }
}
