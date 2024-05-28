package com.baidu.p120ar.arplay.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.baidu.p120ar.arplay.util.NetUtils;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.util.NetStateReceiver */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class NetStateReceiver extends BroadcastReceiver {
    private static final String ANDROID_NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    public static final String CUSTOM_ANDROID_NET_CHANGE_ACTION = "com.baidu.ar.baiduarsdk.CONNECTIVITY_CHANGE";
    private static final String TAG = "NetStateReceiver";
    private static boolean isNetAvailable;
    private static volatile BroadcastReceiver mBroadcastReceiver;
    private static ArrayList<INetChangeObserver> mNetChangeObservers = new ArrayList<>();
    private static NetUtils.NetType mNetType;

    private static BroadcastReceiver getReceiver() {
        if (mBroadcastReceiver == null) {
            synchronized (NetStateReceiver.class) {
                if (mBroadcastReceiver == null) {
                    mBroadcastReceiver = new NetStateReceiver();
                }
            }
        }
        return mBroadcastReceiver;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        mBroadcastReceiver = this;
        if ("android.net.conn.CONNECTIVITY_CHANGE".equalsIgnoreCase(intent.getAction()) || "com.baidu.ar.baiduarsdk.CONNECTIVITY_CHANGE".equalsIgnoreCase(intent.getAction())) {
            if (!NetUtils.isNetworkAvailable(context)) {
                Log.e(getClass().getName(), "<--- network disconnected --->");
                isNetAvailable = false;
            } else {
                Log.e(getClass().getName(), "<--- network connected --->");
                isNetAvailable = true;
                mNetType = NetUtils.getAPNType(context);
            }
            notifyObserver();
        }
    }

    public static void registerNetworkStateReceiver(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.baidu.ar.baiduarsdk.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.getApplicationContext().registerReceiver(getReceiver(), intentFilter);
    }

    public static void checkNetworkState(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.baidu.ar.baiduarsdk.CONNECTIVITY_CHANGE");
        context.sendBroadcast(intent);
    }

    public static void unRegisterNetworkStateReceiver(Context context) {
        if (mBroadcastReceiver != null) {
            try {
                context.getApplicationContext().unregisterReceiver(mBroadcastReceiver);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isNetworkAvailable() {
        return isNetAvailable;
    }

    public static NetUtils.NetType getAPNType() {
        return mNetType;
    }

    private void notifyObserver() {
        if (mNetChangeObservers.isEmpty()) {
            return;
        }
        int size = mNetChangeObservers.size();
        for (int i = 0; i < size; i++) {
            INetChangeObserver iNetChangeObserver = mNetChangeObservers.get(i);
            if (iNetChangeObserver != null) {
                if (isNetworkAvailable()) {
                    iNetChangeObserver.onNetConnected(mNetType);
                } else {
                    iNetChangeObserver.onNetDisConnect();
                }
            }
        }
    }

    public static void registerObserver(INetChangeObserver iNetChangeObserver) {
        if (mNetChangeObservers == null) {
            mNetChangeObservers = new ArrayList<>();
        }
        mNetChangeObservers.add(iNetChangeObserver);
    }

    public static void removeRegisterObserver(INetChangeObserver iNetChangeObserver) {
        ArrayList<INetChangeObserver> arrayList = mNetChangeObservers;
        if (arrayList == null || !arrayList.contains(iNetChangeObserver)) {
            return;
        }
        mNetChangeObservers.remove(iNetChangeObserver);
    }
}
