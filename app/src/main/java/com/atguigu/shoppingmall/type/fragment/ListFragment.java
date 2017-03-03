package com.atguigu.shoppingmall.type.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.type.adapter.TypeLeftAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 一名程序员 on 2017/2/22.
 * <p>
 * 作用：
 */

public class ListFragment extends BaseFragment {


    @BindView(R.id.lv_left)
    ListView lvLeft;
    @BindView(R.id.rv_right)
    RecyclerView rvRight;

    //网络请求得到数据
    private String[] titles = new String[]{"小裙子", "上衣", "下装", "外套", "配件", "包包", "装扮", "居家宅品",
            "办公文具", "数码周边", "游戏专区"};

    private TypeLeftAdapter leftAdapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_list, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        //设置适配器
        leftAdapter = new TypeLeftAdapter(mContext,titles);
        lvLeft.setAdapter(leftAdapter);
        //设置item点击事件
        lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(mContext, "position" + position, Toast.LENGTH_SHORT).show();

                //传入被点击的位置
                leftAdapter.changeSelected(position);
                //刷新适配器
                leftAdapter.notifyDataSetChanged();
            }
        });
    }
}
