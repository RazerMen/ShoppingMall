package com.atguigu.shoppingmall.home.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 一名程序员 on 2017/2/22.
 * <p>
 * 作用：主页的Fragment
 */

public class HomeFragment extends BaseFragment {


    @BindView(R.id.tv_search_hom)
    TextView tvSearchHom;
    @BindView(R.id.tv_message_home)
    TextView tvMessageHome;
    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    @BindView(R.id.ib_top)
    ImageButton ibTop;
    @BindView(R.id.ll_main_scan)
    LinearLayout llMainScan;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }

    @OnClick({R.id.tv_search_hom, R.id.tv_message_home, R.id.ib_top,R.id.ll_main_scan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search_hom:
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_message_home:
                Toast.makeText(mContext, "消息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_top:
                Toast.makeText(mContext, "回到顶部", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_main_scan:
                Toast.makeText(mContext, "扫一扫", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
