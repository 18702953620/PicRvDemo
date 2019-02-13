package com.ch.picrvdemo;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 作者： ch
 * 时间： 2018/11/19 0019-下午 2:02
 * 描述：
 * 来源：
 */

public class PictureModel implements MultiItemEntity {

    public static final int PICTURE_TITLE = 1;
    public static final int PICTURE_CONTENT = 0;


    /**
     * date : 2018-11
     * children : [{"id":"9","title":"测试图集","date_time":"1543161600","create_time":"1543222989","picture_count":"0","status":"1","date":"2018-11-26","cover_image":"http://image.38.hn/public/attachment/201811/26/17/5bfbb669cd528.jpg"}]
     */


    public PictureModel(int type) {
        this.type = type;
    }

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }


    /**
     * id : 9
     * title : 测试图集
     * date_time : 1543161600
     * create_time : 1543222989
     * picture_count : 0
     * status : 1
     * date : 2018-11-26
     * cover_image : http://image.38.hn/public/attachment/201811/26/17/5bfbb669cd528.jpg
     */

    private String id;
    private String title;
    private String date_time;
    private String create_time;
    private String picture_count;
    private String status;
    private String cover_image;
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getPicture_count() {
        return picture_count;
    }

    public void setPicture_count(String picture_count) {
        this.picture_count = picture_count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }
}
