package cn.sharesdk.tencent.qzone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.utils.SSDKLog;
import com.bytedance.applog.tracker.Tracker;
import com.mob.tools.MobUIShell;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import com.sinovatech.unicom.separatemodule.miniprogram.cumphome.PrefetchCumpLauncher;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ReceiveActivity extends Activity {

    /* renamed from: a */
    private static String f3201a;

    /* renamed from: b */
    private static PlatformActionListener f3202b;

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    /* renamed from: a */
    public static void m21384a(String str) {
        f3201a = str;
    }

    /* renamed from: a */
    public static void m21385a(PlatformActionListener platformActionListener) {
        f3202b = platformActionListener;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        SSDKLog.m21740b().m21744a("Qzone CallBack Start", new Object[0]);
        super.onCreate(bundle);
        try {
            Intent intent = getIntent();
            String scheme = intent.getScheme();
            if (scheme == null || !scheme.startsWith(f3201a)) {
                return;
            }
            finish();
            Bundle urlToBundle = ResHelper.urlToBundle(intent.getDataString());
            String valueOf = String.valueOf(urlToBundle.get("result"));
            String valueOf2 = String.valueOf(urlToBundle.get("action"));
            if ("shareToQQ".equals(valueOf2) || "shareToQzone".equals(valueOf2)) {
                if (PrefetchCumpLauncher.PrefetchStatus_Complete.equals(valueOf)) {
                    if (f3202b != null) {
                        f3202b.onComplete(null, 9, new Hashon().fromJson(String.valueOf(urlToBundle.get("response"))));
                    }
                } else if ("error".equals(valueOf)) {
                    if (f3202b != null) {
                        f3202b.onError(null, 9, new Throwable(String.valueOf(urlToBundle.get("response"))));
                    }
                } else if (f3202b != null) {
                    f3202b.onCancel(null, 9);
                }
            }
            Intent intent2 = new Intent("android.intent.action.VIEW");
            intent2.setClass(this, MobUIShell.class);
            intent2.setFlags(335544320);
            startActivity(intent2);
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            PlatformActionListener platformActionListener = f3202b;
            if (platformActionListener != null) {
                platformActionListener.onError(null, 9, th);
            }
        }
    }
}
