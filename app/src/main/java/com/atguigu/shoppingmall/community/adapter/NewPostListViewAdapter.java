package com.atguigu.shoppingmall.community.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.community.bean.NewPostBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;
import com.opendanmaku.DanmakuItem;
import com.opendanmaku.DanmakuView;
import com.opendanmaku.IDanmakuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 一名程序员 on 2017/3/4.
 * <p>
 * 作用：listview的适配器
 */

public class NewPostListViewAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<NewPostBean.ResultBean> datas;

    public NewPostListViewAdapter(Context mContext, List<NewPostBean.ResultBean> result) {
        this.mContext = mContext;
        this.datas = result;
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
            convertView = View.inflate(mContext, R.layout.item_listview_newpost, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //根据位置得到相应的数据
        NewPostBean.ResultBean resultBean = datas.get(position);

        //头像
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + resultBean.getAvatar()).into(viewHolder.ibNewPostAvatar);
        viewHolder.tvCommunityUsername.setText(resultBean.getUsername());

        //图片
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + resultBean.getFigure()).into(viewHolder.ivCommunityFigure);

        viewHolder.tvCommunitySaying.setText(resultBean.getSaying());
        viewHolder.tvCommunityLikes.setText(resultBean.getLikes());
        viewHolder.tvCommunityComments.setText(resultBean.getComments());
//        viewHolder.tvCommunityAddtime.setText(resultBean.getAdd_time());

        //显示弹幕
        List<String> strings = resultBean.getComment_list();
        if (strings != null && strings.size() > 0) {

            //有弹幕的数据
            List<IDanmakuItem> list = initItems(viewHolder.danmakuView, strings);

            Collections.shuffle(list);

            viewHolder.danmakuView.addItem(list, true);
            viewHolder.danmakuView.show();
            viewHolder.danmakuView.setVisibility(View.VISIBLE);

        } else {
            viewHolder.danmakuView.hide();
            viewHolder.danmakuView.setVisibility(View.GONE);
        }


        return convertView;
    }

    private List<IDanmakuItem> initItems(DanmakuView danmakuView, List<String> strings) {
        List<IDanmakuItem> list = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            IDanmakuItem item = new DanmakuItem(mContext, strings.get(i), danmakuView.getWidth());
            list.add(item);
        }
        return list;
    }

    static class ViewHolder {
        @BindView(R.id.tv_community_username)
        TextView tvCommunityUsername;
        @BindView(R.id.tv_community_addtime)
        TextView tvCommunityAddtime;
        @BindView(R.id.rl)
        RelativeLayout rl;
        @BindView(R.id.ib_new_post_avatar)
        ImageButton ibNewPostAvatar;
        @BindView(R.id.iv_community_figure)
        ImageView ivCommunityFigure;
        @BindView(R.id.tv_community_saying)
        TextView tvCommunitySaying;
        @BindView(R.id.tv_community_likes)
        TextView tvCommunityLikes;
        @BindView(R.id.tv_community_comments)
        TextView tvCommunityComments;
        @BindView(R.id.danmakuView)
        DanmakuView danmakuView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
