package plbompard.exploration.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import plbompard.exploration.Exploration;
import plbompard.exploration.R;
import plbompard.exploration.fragment.RestaurantFragment;
import plbompard.exploration.managers.RestaurantManager;
import plbompard.exploration.models.Restaurant;

public class RestaurantActivity extends AppCompatActivity
        implements RestaurantManager.GetRestaurantCallback,
                   RestaurantFragment.OnFragmentInteractionListener {

    private final static String TAG = RestaurantActivity.class.getSimpleName();

    private Restaurant mRestaurantData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState == null) {
                RestaurantFragment firstFragment = new RestaurantFragment();
                firstFragment.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, firstFragment).commit();
            }
        }

        Exploration.getRestaurantManager().requestRestaurant(6861, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_restaurant, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorite) {
            Toast.makeText(RestaurantActivity.this, "Added to favorite", Toast.LENGTH_SHORT).show();
            return true;
        } else if(id == R.id.action_share) {
            Toast.makeText(RestaurantActivity.this, "Sharing...", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    private void notifyDataLoaded() {
        RestaurantFragment fragment = (RestaurantFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (fragment != null) {
            fragment.updateData(mRestaurantData);
            Log.d(TAG, "onSuccess data : notify fragment for masthead background update img");
        } else {
            Log.d(TAG, "onSuccess data but fragment is not available.");
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // RestaurantRequest Callback
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onSuccess(Restaurant data) {
        mRestaurantData = data;
        notifyDataLoaded();
    }

    @Override
    public void onError(Exception e) {
    }


    @Override
    public void onRestaurantFragmentAttached() {
        notifyDataLoaded();
    }

    @Override
    public void onFragmentBackPressed() {
        finish();
    }

    @Override
    public void onBackPressed() {
        RestaurantFragment fragment = (RestaurantFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment != null) {
            fragment.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }
}
