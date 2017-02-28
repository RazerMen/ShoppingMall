package com.atguigu.shoppingmall.shoppingcart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.home.bean.GoodsBean;
import com.atguigu.shoppingmall.shoppingcart.view.AddSubView;
import com.atguigu.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 一名程序员 on 2017/2/28.
 * <p>
 * 作用：购物车的RecyclerView适配器
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {

    private final Context mContext;
    private final List<GoodsBean> list;
    private final TextView tvShopcartTotal;
    private final CheckBox checkboxAll;
    private final CheckBox checkboxDeleteAll;

    public ShoppingCartAdapter(Context mContext, List<GoodsBean> list, TextView tvShopcartTotal, CheckBox checkboxAll, CheckBox checkboxDeleteAll) {
        this.mContext = mContext;
        this.list = list;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        this.checkboxDeleteAll = checkboxDeleteAll;

        showTotalPrice();

    }

    private void showTotalPrice() {
        //显示总价格
        tvShopcartTotal.setText("合计：" + getTotalPrice());
    }

    /**
     * 返回总价格
     *
     * @return
     */
    private double getTotalPrice() {
        double totalPrice = 0;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                GoodsBean goodsBean = list.get(i);
                if (goodsBean.isChecked()) {
                    totalPrice += Double.parseDouble(goodsBean.getCover_price()) * goodsBean.getNumber();
                }
            }
        }
        return totalPrice;
    }

    /**
     * 创建ViewHolder------创建视图
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mContext, R.layout.item_shopping_cart, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //先得到数据
        GoodsBean goodsBean = list.get(position);
        //绑定数据
        holder.cbGov.setChecked(goodsBean.isChecked());
        //图片
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + goodsBean.getFigure()).into(holder.ivGov);
        //设置名称
        holder.tvDescGov.setText(goodsBean.getName());
        //设置价格
        holder.tvPriceGov.setText("￥" + goodsBean.getCover_price());
        //设置数量
        holder.addSubView.setValue(goodsBean.getNumber());
        holder.addSubView.setMinValue(1);
        //设置库存-----来自服务器
        holder.addSubView.setMaxValue(100);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cb_gov)
        CheckBox cbGov;
        @BindView(R.id.iv_gov)
        ImageView ivGov;
        @BindView(R.id.tv_desc_gov)
        TextView tvDescGov;
        @BindView(R.id.tv_price_gov)
        TextView tvPriceGov;
        @BindView(R.id.addSubView)
        AddSubView addSubView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
