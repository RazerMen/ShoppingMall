package com.atguigu.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.home.bean.HomeBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.BackgroundToForegroundTransformer;
import com.zhy.magicviewpager.transformer.RotateYTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;

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
        return 6;
    }

    /**
     * @param parent
     * @param viewType 当前的类型
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {

            return new BannerViewHolder(mContext, inflater.inflate(R.layout.banner_viewpager, null));

        } else if (viewType == CHANNEL) {

            return new ChannelViewHolder(mContext, inflater.inflate(R.layout.channel_item, null));

        } else if (viewType == ACT) {

            return new ActViewHolder(mContext, inflater.inflate(R.layout.act_item, null));

        } else if (viewType == SECKILL) {

            return new SeckillViewHolder(mContext, inflater.inflate(R.layout.seckill_item, null));

        } else if (viewType == RECOMMEND) {


            return new RecommendViewHolder(mContext, inflater.inflate(R.layout.recommend_item, null));

        } else if (viewType == HOT) {

            return new HotViewHolder(mContext,inflater.inflate(R.layout.hot_item,null));

        }
        return null;
    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            //绑定数据
            bannerViewHolder.setData(result.getBanner_info());
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHoder = (ChannelViewHolder) holder;
            //绑定数据
            channelViewHoder.setData(result.getChannel_info());
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(result.getAct_info());
        } else if (getItemViewType(position) == SECKILL) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            seckillViewHolder.setData(result.getSeckill_info());
        } else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(result.getRecommend_info());
        }else if(getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(result.getHot_info());
        }
    }

    class HotViewHolder extends RecyclerView.ViewHolder{

        public HotViewHolder(Context mContext, View itemView) {
            super(itemView);
        }

        public void setData(List<HomeBean.ResultBean.HotInfoBean> hot_info) {

        }
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        @BindView(R.id.tv_more_recommend)
        TextView tvMoreRecommend;
        @BindView(R.id.gv_recommend)
        GridView gvRecommend;

        private RecommendGridViewAdapter recommendGridViewAdapter;

        public RecommendViewHolder(Context mContext, View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            this.mContext = mContext;
        }

        public void setData(List<HomeBean.ResultBean.RecommendInfoBean> recommend_info) {

            //设置GridView的适配器
            recommendGridViewAdapter = new RecommendGridViewAdapter(mContext,recommend_info);
            gvRecommend.setAdapter(recommendGridViewAdapter);

            //设置点击事件
            gvRecommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class SeckillViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        @BindView(R.id.countDownView)
        CountdownView countDownView;
        @BindView(R.id.tv_more_seckill)
        TextView tvMoreSeckill;
        @BindView(R.id.rv_seckill)
        RecyclerView rvSeckill;

        private SeckillRecyclerViewAdapter seckillRecyclerViewAdapter;

        public SeckillViewHolder(Context mContext, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mContext = mContext;
        }

        public void setData(HomeBean.ResultBean.SeckillInfoBean seckill_info) {

            //设置RecyclerView的适配器
            seckillRecyclerViewAdapter = new SeckillRecyclerViewAdapter(mContext, seckill_info);
            rvSeckill.setAdapter(seckillRecyclerViewAdapter);

            //设置布局管理器
            rvSeckill.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));

            //设置点击事件
            seckillRecyclerViewAdapter.setOnItemClickListener(new SeckillRecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                }
            });

            //设置秒杀时间
            countDownView.setTag("text1");
            long duration = Long.parseLong(seckill_info.getEnd_time()) - Long.parseLong(seckill_info.getStart_time());
            countDownView.start(duration);

        }
    }

    class ActViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        @BindView(R.id.viewPager)
        ViewPager viewPager;

        private ViewPagerAdapter viewPagerAdapter;

        public ActViewHolder(Context mContext, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mContext = mContext;
        }

        public void setData(List<HomeBean.ResultBean.ActInfoBean> act_info) {

            //设置适配器
            viewPagerAdapter = new ViewPagerAdapter(mContext, act_info);

            //viewpager美化
            viewPager.setPageMargin(20);//设置page间间距，自行根据需求设置
            viewPager.setOffscreenPageLimit(3);

            viewPager.setAdapter(viewPagerAdapter);

            viewPager.setPageTransformer(true, new RotateYTransformer());

            //设置点击事件
            viewPagerAdapter.setOnItemClickListener(new ViewPagerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;

        ChannelAdapter adapter;

        @BindView(R.id.gv_channel)
        GridView gvChannel;

        public ChannelViewHolder(Context mContext, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mContext = mContext;
        }

        public void setData(List<HomeBean.ResultBean.ChannelInfoBean> channel_info) {

            //设置GridView的适配器
            adapter = new ChannelAdapter(mContext, channel_info);
            gvChannel.setAdapter(adapter);

            //设置点击事件
            gvChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private Banner banner;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            banner = (Banner) itemView.findViewById(R.id.banner);
        }

        public void setData(List<HomeBean.ResultBean.BannerInfoBean> banner_info) {

            //1、得到数据
            //2、设置Banner数据
            List<String> images = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                images.add(Constants.BASE_URL_IMAGE + banner_info.get(i).getImage());
            }

            //banner的简单使用
            banner.setImages(images)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
                            Glide.with(context)
                                    .load(path)
                                    .crossFade()
                                    .into(imageView);
                        }
                    }).start();

            //设置样式
            banner.setBannerAnimation(BackgroundToForegroundTransformer.class);
            //banner的时间
            banner.setDelayTime(3000);

            //3、设置Banner点击事件
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    int realPosition = position;
                    Toast.makeText(mContext, "realPosition==" + realPosition, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
