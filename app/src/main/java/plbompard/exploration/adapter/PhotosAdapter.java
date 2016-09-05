package plbompard.exploration.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import plbompard.exploration.R;
import plbompard.exploration.models.Image;

/**
 * Created by PL on 03/09/2016.
 */
public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    private List<Image> mItems;
    private Image mMainImage;
    private PhotosListener mListener;

    public PhotosAdapter(List<Image> items, Image mainImage, PhotosListener listener) {
        mItems = items;
        mMainImage = mainImage;
        mListener = listener;
    }

    public void setData(List<Image> items, Image mainImage) {
        mItems= items;
        mMainImage = mainImage;
    }

    public void setListener(PhotosListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_label_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(mItems.get(position), mMainImage);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imageView;
        public TextView textView;
        public Image item;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.restaurant_img);
            textView = (TextView) itemView.findViewById(R.id.label);
        }

        public void setData(Image item, Image mainImage) {
            this.item = item;
            Glide.with(imageView.getContext())
                    .load(mainImage.getUrl480_270())
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    .into(imageView);

            String label = item.getLabel();
            if (label != null) {
                textView.setText(label);
            }
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(item);
            }
        }
    }

    public interface PhotosListener {
        void onItemClick(Image item);
    }
}
