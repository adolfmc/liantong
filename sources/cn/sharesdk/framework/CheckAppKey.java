package cn.sharesdk.framework;

import android.text.TextUtils;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobSDK;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CheckAppKey {

    /* renamed from: a */
    public static volatile boolean f2765a;

    /* renamed from: b */
    public static String f2766b;

    /* JADX WARN: Type inference failed for: r0v3, types: [cn.sharesdk.framework.a$1] */
    /* renamed from: a */
    public static boolean m21992a() {
        String appkey = MobSDK.getAppkey();
        if (f2765a || TextUtils.isEmpty(appkey)) {
            return false;
        }
        if (TextUtils.isEmpty(f2766b)) {
            SSDKLog.m21740b().m21744a("CheckAppKeyAsynchronously verify the appkey", new Object[0]);
            new Thread() { // from class: cn.sharesdk.framework.a.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        CheckAppKeyRequestUrl.m21862a().m21861b();
                    } catch (Throwable th) {
                        SSDKLog m21740b = SSDKLog.m21740b();
                        m21740b.m21744a("CheckAppKeyAsyn verify the appkey catch " + th, new Object[0]);
                    }
                }
            }.start();
            return true;
        }
        SSDKLog.m21740b().m21744a("CheckAppKeyDetermine whether successAppKey is equal to mobsdk.getappkey", new Object[0]);
        return appkey.equals(f2766b);
    }
}
