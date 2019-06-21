package com.lee.vademovies.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lee.vademovies.R;
import com.lee.vademovies.model.bean.HotWillBean;
import com.lee.vademovies.model.bean.Result;
import com.lee.vademovies.util.ImageLoaderManager;

import java.util.List;

/**
 * Created :  LiZhIX
 * Date :  2019/6/18 11:44
 * Description  :
 */
public class HotWillRecyclerViewAdapter extends RecyclerView.Adapter<HotWillRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private Result<List<HotWillBean.ResultBean>> mHotBean;

    public HotWillRecyclerViewAdapter(Context context, Result<List<HotWillBean.ResultBean>> hotBean) {
        this.context = context;
        mHotBean = hotBean;
    }

    @NonNull
    @Override
    public HotWillRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.include_recycler_item, null);
        HotWillRecyclerViewAdapter.ViewHolder holder = new HotWillRecyclerViewAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HotWillRecyclerViewAdapter.ViewHolder holder, int i) {
        holder.txtName.setText(mHotBean.getResult().get(i).getName());
        ImageLoaderManager.loadRoundImage(context, mHotBean.getResult().get(i).getImageUrl(), holder.sdvImage, 12);
    }

    @Override
    public int getItemCount() {
        return mHotBean.getResult().size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView sdvImage;
        private final TextView txtName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            sdvImage = itemView.findViewById(R.id.iv_rv_item);
            txtName = itemView.findViewById(R.id.tv_rv_item);
        }
    }
}
