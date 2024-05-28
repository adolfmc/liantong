package com.vivo.push.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.vivo.push.PushClientManager;
import com.vivo.push.model.UnvarnishedMessage;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.LogUtil;
import com.vivo.push.util.PushPackageUtils;
import java.util.List;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class BasePushMessageReceiver extends BroadcastReceiver implements PushMessageCallback {
    public static final String TAG = "PushMessageReceiver";

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onBind(Context context, int i, String str) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onDelAlias(Context context, int i, List<String> list, List<String> list2, String str) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onDelTags(Context context, int i, List<String> list, List<String> list2, String str) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onListTags(Context context, int i, List<String> list, String str) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onLog(Context context, String str, int i, boolean z) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onPublish(Context context, int i, String str) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onSetAlias(Context context, int i, List<String> list, List<String> list2, String str) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onSetTags(Context context, int i, List<String> list, List<String> list2, String str) {
    }

    public void onTransmissionMessage(Context context, UnvarnishedMessage unvarnishedMessage) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onUnBind(Context context, int i, String str) {
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Context context2 = ContextDelegate.getContext(context);
        PushClientManager.m5648a().m5646a(context2);
        String stringExtra = intent.getStringExtra("req_id");
        LogUtil.m5341d(TAG, "PushMessageReceiver " + context2.getPackageName() + " ; requestId = " + stringExtra);
        try {
            PushClientManager.m5648a().m5645a(intent, this);
        } catch (Exception e) {
            LogUtil.m5341d(TAG, "onReceive doReceiveCommand erroe" + e.getMessage());
        }
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public boolean isAllowNet(Context context) {
        if (context == null) {
            LogUtil.m5354a(TAG, "isAllowNet sContext is null");
            return false;
        }
        String packageName = context.getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            LogUtil.m5354a(TAG, "isAllowNet pkgName is null");
            return false;
        }
        Intent intent = new Intent("com.vivo.pushservice.action.PUSH_SERVICE");
        intent.setPackage(packageName);
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 576);
        if (queryIntentServices == null || queryIntentServices.size() <= 0) {
            LogUtil.m5354a(TAG, "this is client sdk");
            return true;
        }
        return PushPackageUtils.m5469a(context, packageName);
    }
}
