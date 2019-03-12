package com.ch.picrvdemo.deco;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ch.picrvdemo.R;
import com.ch.picrvdemo.pic.DensityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DecoActivity extends AppCompatActivity {

    @BindView(R.id.rv_demo)
    RecyclerView rvDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deco);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        List<String> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            list.add("第" + i + "个item");
        }

        DemoAdapter demoAdapter = new DemoAdapter(list);
        rvDemo.setLayoutManager(new LinearLayoutManager(this));
        rvDemo.addItemDecoration(new DemoDecoration());
        rvDemo.setAdapter(demoAdapter);
    }


    class DemoDecoration extends RecyclerView.ItemDecoration {

        private final Paint mPaint;

        public DemoDecoration() {
            mPaint = new Paint();
            mPaint.setColor(Color.GREEN);
            mPaint.setTextSize(DensityUtils.dp2px(DecoActivity.this, 16));
        }

        /**
         * 每个Item 都会执行
         *
         * @param outRect
         * @param view
         * @param parent
         * @param state
         */
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            //outRect就是表示在item的上下左右所撑开的距离,默认值为0
//            outRect.set(10, 20, 30, 40);

            int index = parent.getChildAdapterPosition(view);
            if (index % 3 == 0) {
                outRect.set(0, 100, 0, 5);
            } else {
                outRect.set(0, 0, 0, 5);
            }

//            outRect.set(0, 0, 0, 5);
        }

        /**
         * 只执行一次
         *
         * @param c
         * @param parent
         * @param state
         */
        @Override
        public void onDraw(@NonNull Canvas c, @NonNull RecyclerView
                parent, @NonNull RecyclerView.State state) {
            super.onDraw(c, parent, state);
            //getItemOffsets撑开的空白区域所对应的画布,可以在getItemOffsets所撑出来的区域任意绘图。

//            c.drawCircle(50, 50, 30, mPaint);

            int itemCount = parent.getChildCount();


            for (int i = 0; i < itemCount; i++) {
                View child = parent.getChildAt(i);
                int cx = child.getWidth() / 2 - 50;
                int cy = child.getTop() - 50 + DensityUtils.dp2px(DecoActivity.this, 16) / 2;
                int index = parent.getChildAdapterPosition(child);
                if (index % 3 == 0) {
                    String text = "第" + index / 3 + "组";
                    c.drawText(text, 0, text.length(), cx, cy, mPaint);
                }
            }

        }

        /**
         * 绘制在最上层  只执行一次
         *
         * @param c
         * @param parent
         * @param state
         */
        @Override
        public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.onDrawOver(c, parent, state);

            int itemCount = parent.getChildCount();
            for (int i = 0; i < itemCount; i++) {
                View child = parent.getChildAt(i);
                int cx = child.getWidth() / 2;
                int cy = child.getTop() + child.getHeight()/2 ;
                c.drawCircle(cx, cy, 30, mPaint);
            }

        }
    }
}
