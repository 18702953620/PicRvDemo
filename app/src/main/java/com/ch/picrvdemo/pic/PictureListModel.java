package com.ch.picrvdemo.pic;

import java.util.List;

/**
 * 作者： ch
 * 时间： 2018/11/27 0027-上午 9:18
 * 描述：
 * 来源：
 */

public class PictureListModel {
    /**
     * date : 2018-11
     * children : [{"id":"9","title":"测试图集","date_time":"1543161600","create_time":"1543222989","picture_count":"0","status":"1","date":"2018-11-26","cover_image":"http://image.38.hn/public/attachment/201811/26/17/5bfbb669cd528.jpg"}]
     */

    private String date;
    private List<PictureModel> children;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<PictureModel> getChildren() {
        return children;
    }

    public void setChildren(List<PictureModel> children) {
        this.children = children;
    }


}
