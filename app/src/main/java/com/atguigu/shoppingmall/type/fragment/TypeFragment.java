package com.atguigu.shoppingmall.type.fragment;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 一名程序员 on 2017/2/22.
 * <p>
 * 作用：分类的Fragment
 */

public class TypeFragment extends BaseFragment {


    @BindView(R.id.tl_1)
    SegmentTabLayout tl1;
    @BindView(R.id.iv_type_search)
    ImageView ivTypeSearch;
    @BindView(R.id.fl_type)
    FrameLayout flType;

    private String[] titles = {"分类","标签"};

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_type, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        tl1.setTabData(titles);

        tl1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                Toast.makeText(mContext, "position" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @OnClick(R.id.iv_type_search)
    public void onClick() {
    }
}
