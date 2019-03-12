package com.ch.picrvdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.ch.picrvdemo.R;
import com.ch.picrvdemo.fragment.helper.ResultHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AActivity extends AppCompatActivity {

    @BindView(R.id.btn_a)
    Button btnA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_a)
    public void onViewClicked() {
        ResultHelper.init(this).startActivityForResult(BActivity.class, new ResultHelper.CallBack() {
            @Override
            public void onActivityResult(int resultCode, Intent data) {
                Toast.makeText(AActivity.this, data.getStringExtra("msg"), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
