package com.lee.vademovies.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lee.vademovies.R;

import java.util.List;

/**
 * Created :  LiZhIX
 * Date :  2019/6/17 15:15
 * Description  :
 */
public class BannerHomeAdapter extends RecyclerView.Adapter<BannerHomeAdapter.ViewHolder> {
    private Context context;
    private List<Integer> imgList;
    private List<String> textList;


    public BannerHomeAdapter(Context context, List<Integer> imgList, List<String> textList) {
        this.context = context;
        this.imgList = imgList;
        this.textList = textList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.banner_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

//        ImageLoaderManager.loadRoundImage(context, String.valueOf(imgList.get(i)), viewHolder.sdvImage, 8);
        Glide.with(context).load(imgList.get(i)).into(viewHolder.sdvImage);
        viewHolder.txtName.setText(textList.get(i));
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView sdvImage;
        private final TextView txtName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            sdvImage = itemView.findViewById(R.id.home_sdv_image);
            txtName = itemView.findViewById(R.id.home_txt_name);
        }
    }
}
