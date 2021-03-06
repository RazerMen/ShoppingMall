package com.atguigu.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atguigu.shoppingmall.home.bean.HomeBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 一名程序员 on 2017/2/25.
 * <p>
 * 作用：viewpager的适配器
 */

public class ViewPagerAdapter extends PagerAdapter {

    private final Context mContext;
    private final List<HomeBean.ResultBean.ActInfoBean> datas;

    public ViewPagerAdapter(Context mContext, List<HomeBean.ResultBean.ActInfoBean> act_info) {
        this.mContext = mContext;
        this.datas = act_info;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + datas.get(position).getIcon_url()).into(imageView);
        //添加到viewpager容器中
        container.addView(imageView);

        //设置点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    listener.onItemClick(v,position);
                }
            }
        });
        return imageView;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //定义接口，设置点击事件
    public interface OnItemClickListener{
        public void onItemClick(View v,int position);
    }
    private OnItemClickListener listener;

    /**
     * 设置item点击事件
     */
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
