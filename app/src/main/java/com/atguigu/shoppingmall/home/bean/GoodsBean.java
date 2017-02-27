package com.atguigu.shoppingmall.home.bean;

import java.io.Serializable;

/**
 * Created by 一名程序员 on 2017/2/25.
 * <p>
 * 作用：把HomeAdapter的数据传递到GoodsInfoActivity
 */

public class GoodsBean implements Serializable {

    /**
     * cover_price : 29.00
     * figure : /1452161899947.jpg
     * name : 【喵鹿酱】超萌 假透肉 拼接 踩脚过膝打底袜 裤袜-加绒保暖
     * product_id : 3831
     */

    private String cover_price;
    private String figure;
    private String name;
    private String product_id;

    public String getCover_price() {
        return cover_price;
    }

    public void setCover_price(String cover_price) {
        this.cover_price = cover_price;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "cover_price='" + cover_price + '\'' +
                ", figure='" + figure + '\'' +
                ", name='" + name + '\'' +
                ", product_id='" + product_id + '\'' +
                '}';
    }
}