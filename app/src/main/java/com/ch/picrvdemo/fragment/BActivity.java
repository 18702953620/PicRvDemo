package com.ch.picrvdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.ch.picrvdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BActivity extends AppCompatActivity {

    @BindView(R.id.btn_a)
    Button btnA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_a)
    public void onViewClicked() {
        Intent intent = new Intent();
        intent.putExtra("msg", "1111");
        setResult(100, intent);

        finish();
    }
}
