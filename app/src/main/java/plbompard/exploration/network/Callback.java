package plbompard.exploration.network;

/**
 * Created by plbompard on 02/09/2016.
 */
public interface Callback<T> {

    void onSuccess(T data);

    void onError(Exception e);

}
