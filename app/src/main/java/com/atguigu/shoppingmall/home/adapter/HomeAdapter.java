package com.atguigu.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.home.bean.HomeBean;

import java.util.List;

/**
 * Created by 一名程序员 on 2017/2/23.
 * <p>
 * 作用：主页的Adapter
 */

public class HomeAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final HomeBean.ResultBean result;

    /**
     * 六种类型
     */
    /**
     * 横幅广告
     */
    public static final int BANNER = 0;
    /**
     * 频道
     */
    public static final int CHANNEL = 1;

    /**
     * 活动
     */
    public static final int ACT = 2;

    /**
     * 秒杀
     */
    public static final int SECKILL = 3;
    /**
     * 推荐
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    public static final int HOT = 5;

    /**
     * 用他来加载布局
     */
    private final LayoutInflater inflater;

    /**
     * 当前类型
     */
    public int currentType = BANNER;


    public HomeAdapter(Context mContext, HomeBean.ResultBean result) {
        this.mContext = mContext;
        this.result = result;
        inflater = LayoutInflater.from(mContext);
    }


    /**
     * 根据位置得到对应数据
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == BANNER) {
            currentType = BANNER;
        } else if (position == CHANNEL) {
            currentType = CHANNEL;
        } else if (position == ACT) {
            currentType = ACT;
        } else if (position == SECKILL) {
            currentType = SECKILL;
        } else if (position == RECOMMEND) {
            currentType = RECOMMEND;
        } else if (position == HOT) {
            currentType = HOT;
        }
        return currentType;
    }

    /**
     * 代表所有的类型，等6个都写完后改为6
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return 1;
    }

    /**
     *
     * @param parent
     * @param viewType  当前的类型
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == BANNER) {

            return new BannerViewHolder(mContext,inflater.inflate(R.layout.banner_viewpager,null));
            
        }else if(viewType == CHANNEL) {
            
        }else if(viewType == ACT) {
            
        }else if(viewType == SECKILL) {
            
        }else if(viewType == RECOMMEND) {
            
        }else if(viewType == HOT) {

        }
        return null;
    }

    /**
     * 绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(getItemViewType(position) == BANNER) {
            BannerViewHolder viewHolder = (BannerViewHolder) holder;
            //绑定数据
            viewHolder.setData(result.getBanner_info());
        }

    }

    class BannerViewHolder extends RecyclerView.ViewHolder{

        private final Context mContext;
        private TextView tv_title;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }

        public void setData(List<HomeBean.ResultBean.BannerInfoBean> banner_info) {
            tv_title.setText("尚硅谷IT教育----1020Android");
        }
    }
}
