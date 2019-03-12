package com.ch.picrvdemo.fragment.helper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;

/**
 * 作者： ch
 * 时间： 2019/3/7 0007-上午 10:55
 * 描述：
 * 来源：
 */

public class RouterFragment extends Fragment {

    private SparseArray<ResultHelper.CallBack> mCallbacks = new SparseArray<>();

    public RouterFragment() {
    }

    public static RouterFragment newInstance() {
        return new RouterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // configuration change 的时候，fragment 实例不会背重新创建
        setRetainInstance(true);
    }


    public void startActivityForResult(Intent intent, ResultHelper.CallBack callback) {
        int requestCode = getRequestCode();
        mCallbacks.put(requestCode, callback);
        startActivityForResult(intent, requestCode);
    }


    private int getRequestCode() {
        //code 不能大于65535
        return (int) (System.currentTimeMillis() % 65535);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ResultHelper.CallBack callback = mCallbacks.get(requestCode);
        mCallbacks.remove(requestCode);
        if (callback != null) {
            callback.onActivityResult(resultCode, data);
        }
    }
}
