package plbompard.exploration.network.request;

import android.net.Uri;
import android.support.v4.util.Pair;

/**
 * Created by plbompard on 01/09/2016.
 */
public abstract class ABaseRequest {

    private static final String SCHEME = "http";
    private static final String BASE_API_URL = "api.lafourchette.com";
    private static final String API_PATH = "api";
    private static final String KEY = "key";
    private static final String METHOD = "method";

    public Uri getUrl() {
        Uri.Builder uriBuilder = new Uri.Builder()
                .scheme(SCHEME)
                .authority(BASE_API_URL)
                .appendPath(API_PATH)
                .appendQueryParameter(KEY, getApiKey())
                .appendQueryParameter(METHOD, getMethodName());

        Pair<String, String> extraParam = getExtraParameter();
        if (extraParam != null) {
            uriBuilder.appendQueryParameter(extraParam.first, extraParam.second);
        }

        return uriBuilder.build();
    }

    abstract protected String getMethodName();

    protected Pair<String, String> getExtraParameter() {
        return null;
    }

    private String getApiKey() {
        return "IPHONEPRODEDCRFV";
    }
}
