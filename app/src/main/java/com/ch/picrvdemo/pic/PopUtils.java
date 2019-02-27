package com.ch.picrvdemo.pic;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * 作者： ch
 * 时间： 2018/8/20 0020-下午 2:52
 * 描述：
 * 来源：
 */


public class PopUtils {
    private View view;
    private int bg_color = 0x00000000;
    private PopupWindow popComment;
    private boolean isFull = true;

    public PopUtils(View view, boolean isFull) {
        this.view = view;
        this.isFull = isFull;
        init(view.getContext());
    }

    public PopupWindow getPopWindow() {
        return popComment;
    }

    public PopUtils(View view) {
        this.view = view;
        init(view.getContext());
    }

    public PopUtils(Context context, View view, boolean isFull) {
        this.view = view;
        this.isFull = isFull;
        init(context);
    }

    public PopUtils(View view, int bg_color) {
        this.view = view;
        this.bg_color = bg_color;
        init(view.getContext());
    }

    private void init(Context context) {
        popComment = new PopupWindow(context);
        popComment.setContentView(view);
        popComment.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        if (isFull) {
            popComment.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        } else {
            popComment.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        // 设置SelectPicPopupWindow弹出窗体可点击
        popComment.setFocusable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(bg_color);
        popComment.setBackgroundDrawable(dw);
        popComment.setOutsideTouchable(false);
    }

    public void showAtLocation(View parent) {
        popComment.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
    }

    public void dismiss() {
        if (popComment != null && popComment.isShowing()) {
            popComment.dismiss();
        }
    }


}
