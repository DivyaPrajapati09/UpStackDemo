package astics.com.upstackdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context mContext;
    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;
    private List<ResponseModel.ImageData> mListOfImage = new ArrayList<>();

    public RecyclerAdapter(Context context, List<ResponseModel.ImageData> listOfImage, OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        mContext = context;
        mListOfImage = listOfImage;
        mOnRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ResponseModel.ImageData images = mListOfImage.get(holder.getAdapterPosition());
        Glide.with(mContext).load(images.getWebp()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return mListOfImage.size();
    }

    public interface OnRecyclerViewItemClickListener {
        void onListItemClick();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image_view);
        }
    }
}
