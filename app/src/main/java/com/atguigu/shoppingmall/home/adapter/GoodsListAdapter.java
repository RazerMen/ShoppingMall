package com.atguigu.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.home.bean.TypeListBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 一名程序员 on 2017/3/6.
 * <p>
 * 作用：
 */

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.ViewHolder> {
    private final Context mContext;
    private final List<TypeListBean.ResultBean.PageDataBean> datas;
    private LayoutInflater inflater;

    public GoodsListAdapter(Context mContext, List<TypeListBean.ResultBean.PageDataBean> data) {
        this.mContext = mContext;
        this.datas = data;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_goods_list, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //1、根据位置得到相应的数据
        TypeListBean.ResultBean.PageDataBean dataBean = datas.get(position);
        //2、绑定数据
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + dataBean.getFigure()).placeholder(R.drawable.new_img_loading_2).into(holder.ivHot);
        holder.tvName.setText(dataBean.getName());
        holder.tvPrice.setText("￥" + dataBean.getCover_price());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_hot)
        ImageView ivHot;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(datas.get(getLayoutPosition()));
                    }
                }
            });
        }
    }

    public interface onItemClickListener {
        void onItemClick(TypeListBean.ResultBean.PageDataBean dataBean);
    }

    private onItemClickListener listener;

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }
}
