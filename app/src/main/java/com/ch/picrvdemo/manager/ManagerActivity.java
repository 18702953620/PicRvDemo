package com.ch.picrvdemo.manager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ch.picrvdemo.R;
import com.ch.picrvdemo.deco.DemoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ManagerActivity extends AppCompatActivity {

    @BindView(R.id.rv_manager)
    RecyclerView rvManager;
    private DemoAdapter demoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            list.add("第" + i + "个item");
        }
        demoAdapter = new DemoAdapter(list);
        rvManager.setLayoutManager(new MyLinLayoutManager(this));
        rvManager.setAdapter(demoAdapter);

        rvManager.getRecycledViewPool();

    }

    class MyLinLayoutManager extends LinearLayoutManager {
        public MyLinLayoutManager(Context context) {
            super(context);
        }

        @Override
        public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
            return super.scrollVerticallyBy(dy, recycler, state);
        }

        @Override
        public void removeAndRecycleView(@NonNull View child, @NonNull RecyclerView.Recycler recycler) {
            int postion = rvManager.getChildAdapterPosition(child);
            Log.e("cheng", "removeAndRecycleView postion=" + postion);
            super.removeAndRecycleView(child, recycler);
        }

        @Override
        public void removeView(View child) {
            int postion = rvManager.getChildAdapterPosition(child);
            Log.e("cheng", "removeView postion=" + postion);
            super.removeView(child);
        }


        @Override
        public void removeAndRecycleViewAt(int index, @NonNull RecyclerView.Recycler recycler) {

            Log.e("cheng", "removeAndRecycleViewAt postion=" + index);
            View view = this.getChildAt(index);
            super.removeAndRecycleViewAt(index, recycler);
        }

    }


    class MyLayoutManager extends RecyclerView.LayoutManager {


        private int sumDy;
        private int totalHeight;

        /**
         * 修改item 宽/高/margin/padding等等
         *
         * @return
         */
        @Override
        public RecyclerView.LayoutParams generateDefaultLayoutParams() {
            return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                    RecyclerView.LayoutParams.WRAP_CONTENT);

        }

        @Override
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            super.onLayoutChildren(recycler, state);
            //定义竖直方向的偏移量
            int offsetY = 0;
            for (int i = 0; i < getItemCount(); i++) {
                View view = recycler.getViewForPosition(i);
                //添加
                addView(view);
                //测量 这里是 item+decoration
                measureChildWithMargins(view, 0, 0);
                int width = getDecoratedMeasuredWidth(view);
                int height = getDecoratedMeasuredHeight(view);
                //计算view 的位置 并摆放
                layoutDecorated(view, 0, offsetY, width, offsetY + height);
                //累计高度
                offsetY += height;
            }

            //如果所有子View的高度和没有填满RecyclerView的高度，
            // 则将高度设置为RecyclerView的高度
            totalHeight = Math.max(offsetY, getVerticalSpace());
        }

        /**
         * 是否能垂直方向滚动
         *
         * @return
         */

        @Override
        public boolean canScrollVertically() {
            return true;
        }

        /**
         * 垂直方向 每次滚动的距离
         *
         * @param dy
         * @param recycler
         * @param state
         * @return
         */
        @Override
        public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {

            //dy>0 向下 dy<0 向上
            int tempScroll = dy;
            Log.e("cheng", "dy=" + dy);
            if (sumDy + tempScroll <= 0) {
                //顶部
                tempScroll = -sumDy;
            } else if (sumDy + dy > totalHeight - getVerticalSpace()) {
                //底部
                tempScroll = totalHeight - getVerticalSpace() - sumDy;
            }
            sumDy += tempScroll;


            // 平移容器内的item
            offsetChildrenVertical(-tempScroll);

            return dy;


        }

        private int getVerticalSpace() {
            return getHeight() - getPaddingBottom() - getPaddingTop();
        }
    }
}
