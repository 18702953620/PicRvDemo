package com.ch.picrvdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ch.picrvdemo.deco.DecoActivity;
import com.ch.picrvdemo.pic.PicActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn_deco)
    Button btnDeco;
    @BindView(R.id.btn_piclist)
    Button btnPiclist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.btn_deco, R.id.btn_piclist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_deco:
                startActivity(new Intent(this, DecoActivity.class));
                break;
            case R.id.btn_piclist:
                startActivity(new Intent(this, PicActivity.class));
                break;
        }
    }
}
