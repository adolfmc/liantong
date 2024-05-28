package com.chinaunicon.jtwifilib.jtcommon;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.chinaunicon.jtwifilib.core.utils.JtGsonUtil;
import com.chinaunicon.jtwifilib.jtcommon.model.Register1Bean;
import com.chinaunicon.jtwifilib.jtcommon.util.JtUploadLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class MessageManager {
    private static final String MESSAGE_APP_TO_SDK = "app_to_sdk";
    private static final String MESSAGE_RECEIVER_ACTION = "app_to_sdk_receiver_action";
    private static final String MESSAGE_SDK_TO_APP = "adk_to_app";
    private static final String MESSAGE_SDK_TO_APP_RECEIVER_ACTION = "sdk_to_app_receiver_action";
    private static long broadCastSequence = 100;
    private static long broadCastToSDKSequence = 101;

    public static String getMessageAppToSdk() {
        return "app_to_sdk";
    }

    public static String getMessageSdkToApp() {
        return "adk_to_app";
    }

    public static String getMessageReceiverAction() {
        return "app_to_sdk_receiver_action" + broadCastToSDKSequence;
    }

    public static String getMessageSdkToAppReceiverAction() {
        return "sdk_to_app_receiver_action_" + broadCastSequence;
    }

    public static void registerAppReceiver(Activity activity, BroadcastReceiver broadcastReceiver) {
        broadCastSequence = System.currentTimeMillis();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("sdk_to_app_receiver_action_" + broadCastSequence);
        activity.registerReceiver(broadcastReceiver, intentFilter);
    }

    public static void unRegisterAppReceiver(Activity activity, BroadcastReceiver broadcastReceiver) {
        activity.unregisterReceiver(broadcastReceiver);
    }

    public static void sendApp(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("adk_to_app", str);
        Intent intent = new Intent();
        intent.setAction("sdk_to_app_receiver_action_" + broadCastSequence);
        intent.putExtras(bundle);
        context.sendBroadcast(intent);
        Register1Bean register1Bean = new Register1Bean();
        register1Bean.setCmdType("adk_to_app");
        register1Bean.setStatus("0");
        register1Bean.setMsg(str);
        JtUploadLog.getInstance(context).updateData("1", JtGsonUtil.getInstance().toJson(register1Bean), "adk_to_app");
    }

    public static void registerSDKReceiver(Activity activity, BroadcastReceiver broadcastReceiver) {
        broadCastToSDKSequence = System.currentTimeMillis();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("app_to_sdk_receiver_action" + broadCastToSDKSequence);
        activity.registerReceiver(broadcastReceiver, intentFilter);
    }

    public static void unRegisterSDKReceiver(Activity activity, BroadcastReceiver broadcastReceiver) {
        activity.unregisterReceiver(broadcastReceiver);
    }

    public static void sendSdk(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("app_to_sdk", str);
        Intent intent = new Intent();
        intent.setAction("app_to_sdk_receiver_action" + broadCastToSDKSequence);
        intent.putExtras(bundle);
        context.sendBroadcast(intent);
    }

    public static String getActivityResultMessage(int i, int i2, int i3, Intent intent) {
        return (i == i2 && i3 == 112121) ? intent.getStringExtra("params") : "";
    }
}
