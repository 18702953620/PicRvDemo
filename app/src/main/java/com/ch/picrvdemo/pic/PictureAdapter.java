package com.ch.picrvdemo.pic;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ch.picrvdemo.R;
import com.ch.picrvdemo.widget.FullSpanUtil;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 作者： ch
 * 时间： 2018/11/19 0019-上午 11:35
 * 描述：
 * 来源：
 */

public class PictureAdapter extends BaseMultiItemQuickAdapter<PictureModel, BaseViewHolder> {
    public PictureAdapter(@Nullable List<PictureModel> data) {
        super(data);
        addItemType(PictureModel.PICTURE_CONTENT, R.layout.item_pictures);
        addItemType(PictureModel.PICTURE_TITLE, R.layout.item_picture_month);
    }


    @Override
    protected void convert(BaseViewHolder helper, PictureModel item) {

        if (helper.getItemViewType() == PictureModel.PICTURE_CONTENT) {
            //标题/数量
            helper.setText(R.id.tv_pictrue_title, item.getTitle() + "(" + item.getPicture_count() + ")");
            //时间
            helper.setText(R.id.tv_pictrue_time, item.getDate());
            //封面图
            Glide.with(mContext).load(item.getCover_image()).apply(new RequestOptions().centerCrop()).into((ImageView) helper.getView(R.id.iv_pictrue));

        } else if (helper.getItemViewType() == PictureModel.PICTURE_TITLE) {
            helper.setText(R.id.tv_picture_month, item.getDate());

        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        FullSpanUtil.onAttachedToRecyclerView(recyclerView, this, PictureModel.PICTURE_TITLE);


    }

    @Override
    public void onViewDetachedFromWindow(@NonNull BaseViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        FullSpanUtil.onViewAttachedToWindow(holder, this, PictureModel.PICTURE_TITLE);
    }

}
