package com.atguigu.shoppingmall.community.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.community.bean.NewPostBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by 一名程序员 on 2017/2/22.
 * <p>
 * 作用：用户的Fragment
 */

public class NewPostFragment extends BaseFragment {


    @BindView(R.id.lv_new_post)
    ListView lvNewPost;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_news_post, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils.get().url(Constants.NEW_POST_URL).id(100).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "联网失败==" + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "联网成功==" + response);
                processData(response);
            }
        });
    }

    private void processData(String json) {
        NewPostBean newPostBean = JSON.parseObject(json, NewPostBean.class);
        Toast.makeText(mContext, "" + newPostBean.getResult().get(0).getSaying(), Toast.LENGTH_SHORT).show();
    }
}
