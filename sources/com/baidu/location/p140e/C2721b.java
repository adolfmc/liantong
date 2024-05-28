package com.baidu.location.p140e;

import android.content.Context;
import android.os.Build;
import com.baidu.lbsapi.auth.LBSAuthManager;
import com.baidu.location.ServiceC2737f;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.e.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2721b {

    /* renamed from: e */
    public static String f5690e = null;

    /* renamed from: f */
    public static String f5691f = null;

    /* renamed from: g */
    public static String f5692g = null;

    /* renamed from: h */
    public static String f5693h = null;

    /* renamed from: i */
    public static int f5694i = 0;

    /* renamed from: j */
    public static int f5695j = -2;

    /* renamed from: k */
    public static long f5696k = -1;

    /* renamed from: a */
    public String f5697a;

    /* renamed from: b */
    public String f5698b;

    /* renamed from: c */
    public String f5699c;

    /* renamed from: d */
    public String f5700d;

    /* renamed from: l */
    private boolean f5701l;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.e.b$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2723a {

        /* renamed from: a */
        public static final C2721b f5702a = new C2721b();
    }

    private C2721b() {
        this.f5697a = null;
        this.f5698b = null;
        this.f5699c = null;
        this.f5700d = null;
        this.f5701l = false;
        if (ServiceC2737f.getServiceContext() != null) {
            m19095a(ServiceC2737f.getServiceContext());
        }
    }

    /* renamed from: a */
    public static C2721b m19096a() {
        return C2723a.f5702a;
    }

    /* renamed from: a */
    public String m19093a(boolean z) {
        return m19092a(z, (String) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x010a  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String m19092a(boolean r4, java.lang.String r5) {
        /*
            Method dump skipped, instructions count: 295
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.p140e.C2721b.m19092a(boolean, java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    public void m19095a(Context context) {
        if (context == null || this.f5701l) {
            return;
        }
        try {
            this.f5699c = LBSAuthManager.getInstance(context).getCUID();
        } catch (Exception unused) {
            this.f5699c = null;
        }
        try {
            f5690e = context.getPackageName();
        } catch (Exception unused2) {
            f5690e = null;
        }
        C2735k.f5822n = "" + this.f5699c;
        this.f5701l = true;
    }

    /* renamed from: a */
    public void m19094a(String str, String str2) {
        f5691f = str;
        f5690e = str2;
    }

    /* renamed from: b */
    public String m19091b() {
        String str;
        StringBuffer stringBuffer = new StringBuffer(200);
        if (this.f5699c != null) {
            stringBuffer.append("&cu=");
            str = this.f5699c;
        } else {
            stringBuffer.append("&im=");
            str = this.f5697a;
        }
        stringBuffer.append(str);
        try {
            stringBuffer.append("&mb=");
            stringBuffer.append(Build.MODEL);
        } catch (Exception unused) {
        }
        stringBuffer.append("&pack=");
        try {
            stringBuffer.append(f5690e);
        } catch (Exception unused2) {
        }
        stringBuffer.append("&sdk=");
        stringBuffer.append(9.333f);
        return stringBuffer.toString();
    }
}
