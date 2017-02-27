package com.atguigu.shoppingmall.shoppingcart.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.atguigu.shoppingmall.home.bean.GoodsBean;
import com.atguigu.shoppingmall.utils.CacheUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 一名程序员 on 2017/2/27.
 * <p>
 * 作用：把List数据转为SparseArray
 */

public class CartStorage {

    private static final String JSON_CART = "json_cart";
    private static CartStorage instace;
    private final Context mContext;
    /**
     * SparseArray替代HashMap
     */
    private final SparseArray<GoodsBean> sparseArray;

    public CartStorage(Context context) {
        this.mContext = context;
        sparseArray = new SparseArray();

        //从本地获取数据
        listToSparseArray();
    }

    /**
     * 把List数据转为SparseArray
     */
    private void listToSparseArray() {

        List<GoodsBean> beanList = getAllData();

        for (int i = 0; i < beanList.size(); i++) {
            GoodsBean goodsBean = beanList.get(i);
            sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);
        }

    }

    /**
     * 得到所有的数据
     *
     * @return
     */
    private List<GoodsBean> getAllData() {
        return getLocalData();
    }

    /**
     * 得到本地缓存的数据
     *
     * @return
     */
    private List<GoodsBean> getLocalData() {
        List<GoodsBean> goodsBeen = new ArrayList<>();

        //从本地获得数据
        String json = CacheUtils.getString(mContext, JSON_CART);//保持的json数据
        if (!TextUtils.isEmpty(json)) {
            //把它转化成列表
            goodsBeen = new Gson().fromJson(json, new TypeToken<List<GoodsBean>>() {
            }.getType());
        }
        //把json数据解析成List数据
        return goodsBeen;
    }

    /**
     * 懒汉模式，单例
     *
     * @param context
     * @return
     */
    public static CartStorage getInstance(Context context) {

        if (instace == null) {
            synchronized (CartStorage.class) {
                if (instace == null) {
                    instace = new CartStorage(context);
                }
            }
        }

        return instace;
    }
}
