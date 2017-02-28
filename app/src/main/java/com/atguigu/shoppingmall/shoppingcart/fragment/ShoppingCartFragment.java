package com.atguigu.shoppingmall.shoppingcart.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.shoppingcart.utils.CartStorage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 一名程序员 on 2017/2/22.
 * <p>
 * 作用：购物车的Fragment
 */

public class ShoppingCartFragment extends BaseFragment {

    @BindView(R.id.tv_shopcart_edit)
    TextView tvShopcartEdit;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.checkbox_all)
    CheckBox checkboxAll;
    @BindView(R.id.tv_shopcart_total)
    TextView tvShopcartTotal;
    @BindView(R.id.btn_check_out)
    Button btnCheckOut;
    @BindView(R.id.ll_check_all)
    LinearLayout llCheckAll;
    @BindView(R.id.checkbox_delete_all)
    CheckBox checkboxDeleteAll;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_collection)
    Button btnCollection;
    @BindView(R.id.ll_delete)
    LinearLayout llDelete;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    @BindView(R.id.tv_empty_cart_tobuy)
    TextView tvEmptyCartTobuy;
    @BindView(R.id.ll_empty_shopcart)
    LinearLayout llEmptyShopcart;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shopping_cart, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "购物车的数据被初始化了...");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            for (int i = 0; i < CartStorage.getInstance(mContext).getAllData().size(); i++) {
                Log.e("TAG", "" + CartStorage.getInstance(mContext).getAllData().get(i).toString());
            }
        }
    }

    @OnClick({R.id.tv_shopcart_edit, R.id.checkbox_all, R.id.btn_check_out, R.id.checkbox_delete_all, R.id.btn_delete, R.id.btn_collection, R.id.tv_empty_cart_tobuy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_shopcart_edit:
                Toast.makeText(mContext, "编辑", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox_all:
                Toast.makeText(mContext, "全选", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_check_out:
                Toast.makeText(mContext, "结算", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox_delete_all:
                Toast.makeText(mContext, "删除全选", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_delete:
                Toast.makeText(mContext, "删除按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_collection:
                Toast.makeText(mContext, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_empty_cart_tobuy:
                Toast.makeText(mContext, "去逛逛", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
