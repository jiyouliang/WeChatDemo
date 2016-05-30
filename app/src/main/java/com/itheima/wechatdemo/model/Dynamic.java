package com.itheima.wechatdemo.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author YouLiang.Ji
 */
public class Dynamic implements Serializable {
    private String name;
    private String content;
    private String logoUrl;

    private List<String> imageList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    @Override
    public String toString() {
        return "Dynamic{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", imageList=" + imageList +
                '}';
    }
}
