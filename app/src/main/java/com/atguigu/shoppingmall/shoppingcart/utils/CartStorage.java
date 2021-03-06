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
    public List<GoodsBean> getAllData() {
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

    /**
     * 保存到本地
     */
    private void saveLocal() {
        //1.把sparseArray转成List
        List<GoodsBean> goodsBeanList = sparseArrayToList();
        //2.使用Gson把List转json的String类型数据
        String saveJson = new Gson().toJson(goodsBeanList);
        //3.使用CacheUtils缓存数据
        CacheUtils.setString(mContext, JSON_CART, saveJson);
    }

    /**
     * 把sparseArray转成List
     *
     * @return
     */
    private List<GoodsBean> sparseArrayToList() {
        //列表数据
        List<GoodsBean> goodsBeanList = new ArrayList<>();
        for (int i = 0; i < sparseArray.size(); i++) {
            GoodsBean goodsBean = sparseArray.valueAt(i);
            goodsBeanList.add(goodsBean);
        }
        return goodsBeanList;
    }

    /**
     * 添加数据
     *
     * @param goodsBean
     */
    public void addData(GoodsBean goodsBean) {
        //1.数据添加到sparseArray
        GoodsBean tempGoodsBean = sparseArray.get(Integer.parseInt(goodsBean.getProduct_id()));
        //已经保存过
        if (tempGoodsBean != null) {
            tempGoodsBean.setNumber(tempGoodsBean.getNumber() + goodsBean.getNumber());
        } else {
            //没有添加过
            tempGoodsBean = goodsBean;
        }
        //添加到集合中
        sparseArray.put(Integer.parseInt(tempGoodsBean.getProduct_id()), tempGoodsBean);
        //2.保存到本地
        saveLocal();
    }

    /**
     * 删除数据
     *
     * @param goodsBean
     */
    public void deletData(GoodsBean goodsBean) {
        //删除数据
        sparseArray.delete(Integer.parseInt(goodsBean.getProduct_id()));

        //保存到本地
        saveLocal();
    }

    public void updateData(GoodsBean goodsBean) {
        //修改数据
        sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);

        //保存到本地
        saveLocal();
    }
}
