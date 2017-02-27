package com.atguigu.shoppingmall.home.bean;

import java.io.Serializable;

/**
 * Created by 一名程序员 on 2017/2/27.
 * <p>
 * 作用：
 */

public class WebViewBean implements Serializable{

    private String icon_url;
    private String name;
    private String url;

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
