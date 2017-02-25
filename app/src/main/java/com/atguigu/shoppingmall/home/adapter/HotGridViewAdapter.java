package com.atguigu.shoppingmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.home.bean.HomeBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 一名程序员 on 2017/2/25.
 * <p>
 * 作用：热卖的适配器
 */

public class HotGridViewAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<HomeBean.ResultBean.HotInfoBean> datas;

    public HotGridViewAdapter(Context mContext, List<HomeBean.ResultBean.HotInfoBean> hot_info) {
        this.mContext = mContext;
        this.datas = hot_info;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_hot, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //根据位置获取数据
        HomeBean.ResultBean.HotInfoBean hotInfoBean = datas.get(position);
        viewHolder.tvName.setText(hotInfoBean.getName());
        viewHolder.tvPrice.setText("￥" + hotInfoBean.getCover_price());
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + hotInfoBean.getFigure()).into(viewHolder.ivHot);

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_hot)
        ImageView ivHot;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
