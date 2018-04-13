package astics.com.upstackdemo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static final String TAG = RecyclerAdapter.class.getSimpleName();
    private Context mContext;
    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;
    private List<ImageData> mListOfImage = new ArrayList<>();

    public RecyclerAdapter(Context context, List<ImageData> listOfImage, OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        mContext = context;
        mListOfImage = listOfImage;
        mOnRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ImageData images = mListOfImage.get(holder.getAdapterPosition());
        Log.e(TAG, "onBindViewHolder: webp : " + images.getWebp());
        try {
            Glide.with(mContext).load(images.getUrl().trim()).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    Log.e(TAG, "onLoadFailed: rxception : " + e.getMessage());
                    holder.mProgressBar.setVisibility(View.GONE);
                    holder.image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_launcher));
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    holder.mProgressBar.setVisibility(View.GONE);
                    return false;
                }
            }).into(holder.image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnRecyclerViewItemClickListener.onListItemClick(images);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListOfImage.size();
    }

    public interface OnRecyclerViewItemClickListener {
        void onListItemClick(ImageData images);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public ProgressBar mProgressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_view);
            mProgressBar = itemView.findViewById(R.id.progress_bar);
        }
    }
}
