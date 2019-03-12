package com.ch.picrvdemo.fragment.helper;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * 作者： ch
 * 时间： 2019/3/7 0007-上午 10:54
 * 描述：
 * 来源：
 */

public class ResultHelper {

    private RouterFragment mRouterFragment;
    private static final String TAG = "ResultHelper";
    private Context mContext;

    public static ResultHelper init(FragmentActivity activity) {
        return new ResultHelper(activity);
    }

    private ResultHelper(FragmentActivity activity) {
        mContext = activity;
        mRouterFragment = getRouterFragment(activity);
    }

    private RouterFragment getRouterFragment(FragmentActivity activity) {
        RouterFragment routerFragment = findRouterFragment(activity);
        if (routerFragment == null) {
            routerFragment = RouterFragment.newInstance();
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(routerFragment, TAG)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        return routerFragment;
    }

    private RouterFragment findRouterFragment(FragmentActivity activity) {
        return (RouterFragment) activity.getSupportFragmentManager().findFragmentByTag(TAG);
    }

    public void startActivityForResult(Class<?> clazz, CallBack callback) {
        Intent intent = new Intent(mContext, clazz);
        startActivityForResult(intent, callback);
    }

    public void startActivityForResult(Intent intent, CallBack callback) {
        mRouterFragment.startActivityForResult(intent, callback);
    }


    public interface CallBack {
        void onActivityResult(int resultCode, Intent data);
    }

}
