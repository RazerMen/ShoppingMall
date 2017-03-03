package com.atguigu.shoppingmall.type.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.main.MainActivity;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

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

    private String[] titles = {"分类", "标签"};

    private ArrayList<BaseFragment> fragments;

    private Fragment tempFragment;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_type, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        initListener();

        initFragment();

        switchFragment(fragments.get(0));
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new ListFragment());
        fragments.add(new TagFragment());
    }

    private void initListener() {
        tl1.setTabData(titles);

        tl1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
//                Toast.makeText(mContext, "position" + position, Toast.LENGTH_SHORT).show();
                switchFragment(fragments.get(position));

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void switchFragment(Fragment currentFragment) {

        if (tempFragment != currentFragment) {
            MainActivity mainActivity = (MainActivity) mContext;
            FragmentTransaction ft = mainActivity.getSupportFragmentManager().beginTransaction();
            if (!currentFragment.isAdded()) {
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.add(R.id.fl_type, currentFragment);
            } else {
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.show(currentFragment);
            }
            ft.commit();
            tempFragment = currentFragment;
        }
    }

    @OnClick(R.id.iv_type_search)
    public void onClick() {
        Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
    }
}
