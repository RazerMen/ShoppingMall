package com.atguigu.shoppingmall.community.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.community.adapter.HotPostListViewAdapter;
import com.atguigu.shoppingmall.community.bean.HotPostBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by 一名程序员 on 2017/2/22.
 * <p>
 * 作用：用户的Fragment
 */

public class HotPostFragment extends BaseFragment {


    @BindView(R.id.lv_hot_post)
    ListView lvHotPost;

    private HotPostListViewAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_hot_post, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils.get().url(Constants.HOT_POST_URL).id(100).build().execute(new StringCallback() {
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
        HotPostBean hotPostBean = JSON.parseObject(json, HotPostBean.class);
//        Toast.makeText(mContext, "" + hotPostBean.getResult().get(0).getSaying(), Toast.LENGTH_SHORT).show();
        List<HotPostBean.ResultBean> result = hotPostBean.getResult();
        if(result != null && result.size() > 0) {
            //设置适配器
            adapter = new HotPostListViewAdapter(mContext,result);
            lvHotPost.setAdapter(adapter);
        }
    }
}
