package com.ch.picrvdemo.deco;

import android.support.annotation.Nullable;

import com.ch.picrvdemo.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 作者： ch
 * 时间： 2019/2/26 0026-下午 5:27
 * 描述：
 * 来源：
 */

public class DemoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public DemoAdapter(@Nullable List<String> data) {
        super(R.layout.item_demo, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_demo, item);
    }
}
