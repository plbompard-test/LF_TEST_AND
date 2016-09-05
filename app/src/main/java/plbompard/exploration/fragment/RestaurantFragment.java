package plbompard.exploration.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import plbompard.exploration.adapter.PhotosAdapter;
import plbompard.exploration.R;
import plbompard.exploration.models.Restaurant;
import plbompard.exploration.models.Image;

public class RestaurantFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private CoordinatorLayout mCoordinatorLayout;

    //UI
    private ImageView mMastheadBackground;

    private TextView mTitle1;
    private TextView mTitle2;
    private TextView mTitle3;

    private TextView mTab1;
    private TextView mTab2;
    private TextView mTab3;

    private MapView mMapView;

    private RecyclerView mPhotosBottomSheet;
    private BottomSheetBehavior mBottomSheetBehavior;
    private LinearLayoutManager mLinearLayoutManager;
    private PhotosAdapter mPhotosAdapter;

    private ProgressDialog mLoadingDataDialog;

    public RestaurantFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_restaurant, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        AppCompatActivity activity = ((AppCompatActivity)getActivity());
        activity.setSupportActionBar(toolbar);
        activity.setTitle("");

        mCoordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinator_layout);

        mMastheadBackground = (ImageView) view.findViewById(R.id.masthead_background);

        mTitle1 = (TextView) view.findViewById(R.id.title1);
        mTitle2 = (TextView) view.findViewById(R.id.title2);
        mTitle3 = (TextView) view.findViewById(R.id.title3);

        mTab1 = (TextView) view.findViewById(R.id.tab1);
        mTab2 = (TextView) view.findViewById(R.id.tab2);
        mTab3 = (TextView) view.findViewById(R.id.tab3);

        mPhotosAdapter = new PhotosAdapter(new ArrayList<Image>(), new Image(), null);

        mPhotosBottomSheet = (RecyclerView) view.findViewById(R.id.photos_bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(mPhotosBottomSheet);
        mLinearLayoutManager = new LinearLayoutManager(view.getContext());
        mBottomSheetBehavior.isHideable();
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        mBottomSheetBehavior.setSkipCollapsed(true);

        mPhotosBottomSheet.setAdapter(mPhotosAdapter);
        mPhotosBottomSheet.setLayoutManager(mLinearLayoutManager);

        mMapView = (MapView) view.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Data filling
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private boolean checkAvailableOrDisplayLoader(Restaurant data) {
        if (data == null) {
            mLoadingDataDialog = new ProgressDialog(getContext());
            mLoadingDataDialog.setTitle(R.string.loading_msg);
            mLoadingDataDialog.show();
            return false;
        } else if (mLoadingDataDialog != null) {
            mLoadingDataDialog.hide();
        }

        return true;
    }

    private void fillHeaderData(@NonNull Restaurant data) {
        Glide.with(this)
                .load(data.getMainImage().getUrl480_270())
                .into(mMastheadBackground);

        getActivity().setTitle(data.getName());

        mTitle1.setText(data.getSpecialityWithPrice());
        mTitle2.setText(data.getName());
        mTitle3.setText(data.getFullAddress());
    }

    private void fillTabs(@NonNull Restaurant data) {
        List<Image> imgs = data.getImageDiaporama();
        int size = 0;
        if (imgs != null && !imgs.isEmpty()) {
            size = imgs.size();
        }

        mTab1.setText(size + " photos");

        mTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            }
        });

        float averageGrade = data.getAverageRate();
        String grade = String.valueOf(averageGrade);
        String tab2text = grade + "/10\n" + data.getRateCount() + " avis";
        SpannableStringBuilder builder = new SpannableStringBuilder(tab2text);
        builder.setSpan(new StyleSpan(Typeface.BOLD), 0, grade.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTab2.setText(builder);

        mTab3.setText(R.string.map);
    }

    private void loadMap(@NonNull final Restaurant data) {
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                LatLng latLng = new LatLng((double)data.getLat(), (double)data.getLong());
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                googleMap.addMarker(new MarkerOptions().position(latLng).title(data.getAddress()));
                mMapView.onResume();
            }
        });
    }

    private void fillPhotos(Restaurant data) {
        mPhotosAdapter.setData(data.getImageDiaporama(), data.getMainImage());
        mPhotosAdapter.notifyDataSetChanged();
    }

    public void updateData(final Restaurant data) {
        if (!checkAvailableOrDisplayLoader(data)) {
            return;
        }
        if (getView() == null) return;

        fillHeaderData(data);
        fillTabs(data);
        loadMap(data);
        fillPhotos(data);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Activity interactions
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListener.onRestaurantFragmentAttached();
    }

    public void onBackPressed() {
        if (mBottomSheetBehavior != null) {
            switch (mBottomSheetBehavior.getState()) {
                case BottomSheetBehavior.STATE_EXPANDED:
                case BottomSheetBehavior.STATE_COLLAPSED:
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                    return;
            }
        }

        mListener.onFragmentBackPressed();
    }

    public interface OnFragmentInteractionListener {
        void onRestaurantFragmentAttached();

        void onFragmentBackPressed();
    }
}
