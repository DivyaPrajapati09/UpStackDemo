package astics.com.upstackdemo.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import astics.com.upstackdemo.R;
import astics.com.upstackdemo.model.ImageData;
import butterknife.BindView;
import butterknife.ButterKnife;

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
        try {
            Glide.with(mContext)
                    .load(images.getUrl().trim())
                    .listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                    holder.image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_launcher));
                    holder.likeUnlikeView.setVisibility(View.GONE);
                    holder.mProgressBar.setVisibility(View.GONE);
                    return true;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    holder.mProgressBar.setVisibility(View.GONE);
                    holder.likeUnlikeView.setVisibility(View.VISIBLE);
                    return false;
                }
            }).into(holder.image);
        } catch (Exception e) {
            e.printStackTrace();
            holder.mProgressBar.setVisibility(View.GONE);
            holder.image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_launcher));
            holder.likeUnlikeView.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnRecyclerViewItemClickListener.onListItemClick(images);
            }
        });

        holder.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String likeCount = holder.txtLike.getText().toString();
                String unLikeCount = holder.txtUnlike.getText().toString();
                if (TextUtils.isEmpty(likeCount) || likeCount.equals("null")) {
                    holder.txtLike.setText("1");
                    likeCount = "1";
                } else {
                    int count = Integer.parseInt(likeCount) + 1;
                    holder.txtLike.setText(String.valueOf(count));
                    likeCount = String.valueOf(count);
                }
                mOnRecyclerViewItemClickListener.setLikeUnlikeCount(holder.getAdapterPosition(), likeCount, unLikeCount);
            }
        });

        holder.imgUnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String likeCount = holder.txtLike.getText().toString();
                String unLikeCount = holder.txtUnlike.getText().toString();
                if (TextUtils.isEmpty(unLikeCount) || unLikeCount.equals("null")) {
                    holder.txtUnlike.setText("1");
                    unLikeCount = "1";
                } else {
                    int count = Integer.parseInt(unLikeCount) + 1;
                    holder.txtUnlike.setText(String.valueOf(count));
                    unLikeCount = String.valueOf(count);
                }
                mOnRecyclerViewItemClickListener.setLikeUnlikeCount(holder.getAdapterPosition(), likeCount, unLikeCount);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListOfImage.size();
    }

    public interface OnRecyclerViewItemClickListener {
        void onListItemClick(ImageData images);

        void setLikeUnlikeCount(int position, String likeCount, String unlikeCount);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view)
        ImageView image;
        @BindView(R.id.progress_bar)
        ProgressBar mProgressBar;
        @BindView(R.id.img_like)
        ImageView imgLike;
        @BindView(R.id.img_unlike)
        ImageView imgUnLike;
        @BindView(R.id.txt_like_count)
        TextView txtLike;
        @BindView(R.id.txt_unlike_count)
        TextView txtUnlike;
        @BindView(R.id.like_unlike_view)
        LinearLayout likeUnlikeView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            likeUnlikeView.setVisibility(View.GONE);
        }
    }
}
