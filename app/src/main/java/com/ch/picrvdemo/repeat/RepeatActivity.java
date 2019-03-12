package com.ch.picrvdemo.repeat;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ch.picrvdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepeatActivity extends AppCompatActivity {

    @BindView(R.id.rv_repeat)
    RecyclerView rvRepeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeat);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("第" + i + "个item");
        }
        RepeatAdapter repeatAdapter = new RepeatAdapter(list, this);
        rvRepeat.setLayoutManager(new MyLayoutManager(this));
        rvRepeat.setAdapter(repeatAdapter);

    }


    class MyLayoutManager extends LinearLayoutManager {
        public MyLayoutManager(Context context) {
            super(context);
        }

        @Override
        public void removeAndRecycleViewAt(int index, @NonNull RecyclerView.Recycler recycler) {
            super.removeAndRecycleViewAt(index, recycler);
            Log.e("cheng", "removeAndRecycleViewAt index=" + index);
        }


    }
}
