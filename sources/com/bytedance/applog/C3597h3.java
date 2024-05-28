package com.bytedance.applog;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.applog.InterfaceC3645n3;

/* renamed from: com.bytedance.applog.h3 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3597h3 implements InterfaceC3645n3 {

    /* renamed from: a */
    public static final AbstractC3749z2<Boolean> f8492a = new C3598a();

    /* renamed from: com.bytedance.applog.h3$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C3598a extends AbstractC3749z2<Boolean> {
        @Override // com.bytedance.applog.AbstractC3749z2
        /* renamed from: a */
        public Boolean mo16989a(Object[] objArr) {
            return Boolean.valueOf(C3710v0.m17102a((Context) objArr[0], "com.huawei.hwid"));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.h3$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C3599b extends InterfaceC3645n3.C3646a {

        /* renamed from: c */
        public long f8493c = 0;
    }

    /* renamed from: c */
    public static boolean m17284c(Context context) {
        if (context == null) {
            return false;
        }
        return f8492a.m16988b(context).booleanValue();
    }

    @Override // com.bytedance.applog.InterfaceC3645n3
    @WorkerThread
    @Nullable
    /* renamed from: a */
    public InterfaceC3645n3.C3646a mo17057a(Context context) {
        String string;
        String string2;
        C3599b c3599b = new C3599b();
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                string = Settings.Global.getString(context.getContentResolver(), "pps_oaid");
                string2 = Settings.Global.getString(context.getContentResolver(), "pps_track_limit");
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (!TextUtils.isEmpty(string)) {
                c3599b.f8617a = string;
                c3599b.f8618b = Boolean.parseBoolean(string2);
                c3599b.f8493c = 202003021704L;
                return c3599b;
            }
        }
        Pair pair = (Pair) new C3713v3(context, new Intent("com.uodis.opendevice.OPENIDS_SERVICE").setPackage("com.huawei.hwid"), new C3604i3()).m17071a();
        if (pair != null) {
            c3599b.f8617a = (String) pair.first;
            c3599b.f8618b = ((Boolean) pair.second).booleanValue();
            int i = 0;
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.huawei.hwid", 0);
                if (packageInfo != null) {
                    i = packageInfo.versionCode;
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            c3599b.f8493c = i;
        }
        return c3599b;
    }

    @Override // com.bytedance.applog.InterfaceC3645n3
    /* renamed from: b */
    public boolean mo17056b(Context context) {
        return m17284c(context);
    }
}
