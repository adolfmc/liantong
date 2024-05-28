package cn.sharesdk.sina.weibo.utils;

import android.content.pm.Signature;
import android.net.Uri;
import android.text.TextUtils;
import cn.sharesdk.framework.utils.AppUtils;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.tools.utils.Data;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.sina.weibo.utils.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WeiboAppManager {

    /* renamed from: a */
    private static final Uri f3033a = Uri.parse("content://com.sina.weibo.sdkProvider/query/package");

    /* renamed from: b */
    private static C1819a f3034b = null;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: WeiboAppManager.java */
    /* renamed from: cn.sharesdk.sina.weibo.utils.c$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C1819a {

        /* renamed from: a */
        private String f3035a;

        /* renamed from: b */
        private int f3036b;

        public String toString() {
            return "WeiboInfo: PackageName = " + this.f3035a + ", supportApi = " + this.f3036b;
        }
    }

    /* renamed from: a */
    public static boolean m21593a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return m21592a(AppUtils.m21715b(str, 64).signatures, "18da2bf10352443a00a5e046d9fca6bd");
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: a */
    private static boolean m21592a(Signature[] signatureArr, String str) {
        if (signatureArr == null || str == null) {
            return false;
        }
        for (Signature signature : signatureArr) {
            if (str.equals(Data.MD5(signature.toByteArray()))) {
                SSDKLog.m21740b().m21744a("check pass", new Object[0]);
                return true;
            }
        }
        return false;
    }
}
