package cn.sharesdk.wechat.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import cn.sharesdk.framework.utils.SSDKLog;
import com.bytedance.applog.tracker.Tracker;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WechatHandlerActivity extends Activity {
    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    public void onGetMessageFromWXReq(WXMediaMessage wXMediaMessage) {
    }

    public void onShowMessageFromWXReq(WXMediaMessage wXMediaMessage) {
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        SSDKLog.m21740b().m21744a("Wechat CallBack Start", new Object[0]);
        setTheme(16973839);
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().addFlags(Integer.MIN_VALUE);
                getWindow().setStatusBarColor(0);
            }
            super.onCreate(bundle);
            WechatHelper.m21279a().m21264a(this);
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
        }
        finish();
    }

    @Override // android.app.Activity
    public void setRequestedOrientation(int i) {
        if (Build.VERSION.SDK_INT == 26) {
            return;
        }
        super.setRequestedOrientation(i);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        SSDKLog.m21740b().m21744a("Wechat CallBack Start", new Object[0]);
        super.onNewIntent(intent);
        setIntent(intent);
        try {
            WechatHelper.m21279a().m21264a(this);
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
        }
        finish();
    }
}
