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
import com.atguigu.shoppingmall.shoppingcart.utils.CartStorage;
import com.atguigu.shoppingmall.shoppingcart.view.AddSubView;
import com.atguigu.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.Iterator;
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

    public void showTotalPrice() {
        //显示总价格
        tvShopcartTotal.setText("合计：" + getTotalPrice());
    }

    /**
     * 返回总价格
     *
     * @return
     */
    public double getTotalPrice() {
        double totalPrice = 0;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                //把数据遍历出来
                GoodsBean goodsBean = list.get(i);
                //必须是选择状态
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
        final GoodsBean goodsBean = list.get(position);

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

        holder.addSubView.setOnNumberChangerListener(new AddSubView.OnNumberChangerListener() {
            //回传过来的值
            @Override
            public void onNumberChanger(int value) {
                //回调的数量
                goodsBean.setNumber(value);

                //持久化
                CartStorage.getInstance(mContext).updateData(goodsBean);

                //显示总价格
                showTotalPrice();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 校验是否全选
     */
    public void checkAll() {
        if (list != null && list.size() > 0) {
            int number = 0;
            for (int i = 0; i < list.size(); i++) {
                GoodsBean goodsBean = list.get(i);
                if (!goodsBean.isChecked()) {
                    //只要有一个不勾选
                    checkboxAll.setChecked(false);
                    checkboxDeleteAll.setChecked(false);
                } else {
                    //勾选
                    number++;
                }
            }
            //勾选的数量和购物车的条目相同就全选
            if (list.size() == number) {
                checkboxAll.setChecked(true);
                checkboxDeleteAll.setChecked(true);
            }
        } else {
            //没有数据
            checkboxAll.setChecked(false);
            checkboxDeleteAll.setChecked(false);
        }
    }

    /**
     * 全选和反选
     *
     * @param isChecked
     */
    public void checkAll_none(boolean isChecked) {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                //把购物车里的Bean对象都设置勾选或者非勾选
                GoodsBean goodsBean = list.get(i);
                //设置是否勾选状态
                goodsBean.setChecked(isChecked);
                //设置checkbox的标签
                checkboxAll.setChecked(isChecked);
                checkboxDeleteAll.setChecked(isChecked);

                //更新视图
                notifyItemChanged(i);
            }
        }
    }

    /**
     * 删除数据
     */
//    public void deleteData() {
//        if (list != null && list.size() > 0) {
//            for (int i = 0; i < list.size(); i++) {
//                GoodsBean goodsBean = list.get(i);
//                if (goodsBean.isChecked()) {
//                    //1、从内存中删除
//                    list.remove(goodsBean);
//
//                    CartStorage.getInstance(mContext).deletData(goodsBean);
//                    //刷新数据
//                    notifyItemRemoved(i);
//
//                    i--;
//                }
//            }
//        }
//    }

    /**
     * 删除数据
     */
    public void deleteData() {
        if (list != null && list.size() > 0) {
            for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {

                GoodsBean goodsBean = (GoodsBean) iterator.next();

                //是否选中，选中了才能删除
                if (goodsBean.isChecked()) {
                    int position = list.indexOf(goodsBean);

                    //从内存中移除
                    iterator.remove();

                    //本地同步
                    CartStorage.getInstance(mContext).deletData(goodsBean);

                    //刷新页面
                    notifyItemRemoved(position);
                }
            }
        }
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

            //设置item的点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //回调的接口
                    if (itemClickListener != null) {
                        itemClickListener.OnItemClickListener(v, getLayoutPosition());
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        public void OnItemClickListener(View view, int position);
    }

    private OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }
}
