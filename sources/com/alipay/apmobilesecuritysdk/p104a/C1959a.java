package com.alipay.apmobilesecuritysdk.p104a;

import android.content.Context;
import android.os.Environment;
import com.alipay.apmobilesecuritysdk.otherid.UmidSdkWrapper;
import com.alipay.apmobilesecuritysdk.p105b.C1960a;
import com.alipay.apmobilesecuritysdk.p106c.C1961a;
import com.alipay.apmobilesecuritysdk.p107d.C1968e;
import com.alipay.apmobilesecuritysdk.p108e.C1969a;
import com.alipay.apmobilesecuritysdk.p108e.C1970b;
import com.alipay.apmobilesecuritysdk.p108e.C1971c;
import com.alipay.apmobilesecuritysdk.p108e.C1972d;
import com.alipay.apmobilesecuritysdk.p108e.C1975g;
import com.alipay.apmobilesecuritysdk.p108e.C1976h;
import com.alipay.apmobilesecuritysdk.p108e.C1977i;
import com.alipay.security.mobile.module.http.C2104d;
import com.alipay.security.mobile.module.http.model.C2107c;
import com.alipay.security.mobile.module.http.model.C2108d;
import com.alipay.security.mobile.module.p110a.C2081a;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.apmobilesecuritysdk.a.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1959a {

    /* renamed from: a */
    private Context f3475a;

    /* renamed from: b */
    private C1960a f3476b = C1960a.m21052a();

    /* renamed from: c */
    private int f3477c = 4;

    public C1959a(Context context) {
        this.f3475a = context;
    }

    /* renamed from: a */
    public static String m21058a(Context context) {
        String m21054b = m21054b(context);
        return C2081a.m20577a(m21054b) ? C1976h.m20990f(context) : m21054b;
    }

    /* renamed from: a */
    public static String m21057a(Context context, String str) {
        try {
            m21055b();
            String m20982a = C1977i.m20982a(str);
            if (C2081a.m20577a(m20982a)) {
                String m21005a = C1975g.m21005a(context, str);
                C1977i.m20981a(str, m21005a);
                return !C2081a.m20577a(m21005a) ? m21005a : "";
            }
            return m20982a;
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: a */
    private static boolean m21059a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] strArr = {"2017-01-27 2017-01-28", "2017-11-10 2017-11-11", "2017-12-11 2017-12-12"};
        int random = ((int) (Math.random() * 24.0d * 60.0d * 60.0d)) * 1;
        for (int i = 0; i < 3; i++) {
            try {
                String[] split = strArr[i].split(" ");
                if (split != null && split.length == 2) {
                    Date date = new Date();
                    Date parse = simpleDateFormat.parse(split[0] + " 00:00:00");
                    Date parse2 = simpleDateFormat.parse(split[1] + " 23:59:59");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(parse2);
                    calendar.add(13, random);
                    Date time = calendar.getTime();
                    if (date.after(parse) && date.before(time)) {
                        return true;
                    }
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    /* renamed from: b */
    private C2107c m21053b(Map<String, String> map) {
        C1970b m21028b;
        C1970b m21026c;
        try {
            Context context = this.f3475a;
            C2108d c2108d = new C2108d();
            String m20574a = C2081a.m20574a(map, "appName", "");
            String m20574a2 = C2081a.m20574a(map, "sessionId", "");
            String m20574a3 = C2081a.m20574a(map, "rpcVersion", "");
            String m21057a = m21057a(context, m20574a);
            String securityToken = UmidSdkWrapper.getSecurityToken(context);
            String m20994d = C1976h.m20994d(context);
            if (C2081a.m20573b(m20574a2)) {
                c2108d.f4032c = m20574a2;
            } else {
                c2108d.f4032c = m21057a;
            }
            c2108d.f4033d = securityToken;
            c2108d.f4034e = m20994d;
            c2108d.f4030a = "android";
            String str = "";
            String str2 = "";
            String str3 = "";
            String str4 = "";
            C1971c m21019c = C1972d.m21019c(context);
            if (m21019c != null) {
                str2 = m21019c.f3485a;
                str3 = m21019c.f3487c;
            }
            if (C2081a.m20577a(str2) && (m21026c = C1969a.m21026c(context)) != null) {
                str2 = m21026c.f3482a;
                str3 = m21026c.f3484c;
            }
            C1971c m21021b = C1972d.m21021b();
            if (m21021b != null) {
                str = m21021b.f3485a;
                str4 = m21021b.f3487c;
            }
            if (C2081a.m20577a(str) && (m21028b = C1969a.m21028b()) != null) {
                str = m21028b.f3482a;
                str4 = m21028b.f3484c;
            }
            c2108d.f4037h = str2;
            c2108d.f4036g = str;
            c2108d.f4039j = m20574a3;
            if (C2081a.m20577a(str2)) {
                c2108d.f4031b = str;
                c2108d.f4038i = str4;
            } else {
                c2108d.f4031b = str2;
                c2108d.f4038i = str3;
            }
            c2108d.f4035f = C1968e.m21036a(context, map);
            return C2104d.m20468b(this.f3475a, this.f3476b.m21049c()).mo20461a(c2108d);
        } catch (Throwable th) {
            th.printStackTrace();
            C1961a.m21046a(th);
            return null;
        }
    }

    /* renamed from: b */
    private static String m21054b(Context context) {
        try {
            String m20980b = C1977i.m20980b();
            if (C2081a.m20577a(m20980b)) {
                C1971c m21020b = C1972d.m21020b(context);
                if (m21020b != null) {
                    C1977i.m20983a(m21020b);
                    String str = m21020b.f3485a;
                    if (C2081a.m20573b(str)) {
                        return str;
                    }
                }
                C1970b m21027b = C1969a.m21027b(context);
                if (m21027b != null) {
                    C1977i.m20984a(m21027b);
                    String str2 = m21027b.f3482a;
                    return C2081a.m20573b(str2) ? str2 : "";
                }
                return "";
            }
            return m20980b;
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: b */
    private static void m21055b() {
        try {
            String[] strArr = {"device_feature_file_name", "wallet_times", "wxcasxx_v3", "wxcasxx_v4", "wxxzyy_v1"};
            for (int i = 0; i < 5; i++) {
                String str = strArr[i];
                File externalStorageDirectory = Environment.getExternalStorageDirectory();
                File file = new File(externalStorageDirectory, ".SystemConfig/" + str);
                if (file.exists() && file.canWrite()) {
                    file.delete();
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00e4 A[Catch: Exception -> 0x0257, TryCatch #0 {Exception -> 0x0257, blocks: (B:2:0x0000, B:4:0x003d, B:7:0x0047, B:36:0x00cf, B:67:0x01ff, B:69:0x021a, B:71:0x0220, B:73:0x0226, B:77:0x022f, B:79:0x0235, B:39:0x00e4, B:41:0x00fc, B:47:0x0109, B:48:0x0119, B:50:0x0120, B:54:0x0132, B:56:0x0187, B:58:0x0191, B:60:0x0199, B:62:0x01ab, B:64:0x01b5, B:66:0x01bd, B:65:0x01b9, B:59:0x0195, B:10:0x005c, B:12:0x0074, B:15:0x007f, B:17:0x0085, B:20:0x0090, B:23:0x0099, B:26:0x00a6, B:29:0x00b3, B:32:0x00c1), top: B:85:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x021a A[Catch: Exception -> 0x0257, TryCatch #0 {Exception -> 0x0257, blocks: (B:2:0x0000, B:4:0x003d, B:7:0x0047, B:36:0x00cf, B:67:0x01ff, B:69:0x021a, B:71:0x0220, B:73:0x0226, B:77:0x022f, B:79:0x0235, B:39:0x00e4, B:41:0x00fc, B:47:0x0109, B:48:0x0119, B:50:0x0120, B:54:0x0132, B:56:0x0187, B:58:0x0191, B:60:0x0199, B:62:0x01ab, B:64:0x01b5, B:66:0x01bd, B:65:0x01b9, B:59:0x0195, B:10:0x005c, B:12:0x0074, B:15:0x007f, B:17:0x0085, B:20:0x0090, B:23:0x0099, B:26:0x00a6, B:29:0x00b3, B:32:0x00c1), top: B:85:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0220 A[Catch: Exception -> 0x0257, TryCatch #0 {Exception -> 0x0257, blocks: (B:2:0x0000, B:4:0x003d, B:7:0x0047, B:36:0x00cf, B:67:0x01ff, B:69:0x021a, B:71:0x0220, B:73:0x0226, B:77:0x022f, B:79:0x0235, B:39:0x00e4, B:41:0x00fc, B:47:0x0109, B:48:0x0119, B:50:0x0120, B:54:0x0132, B:56:0x0187, B:58:0x0191, B:60:0x0199, B:62:0x01ab, B:64:0x01b5, B:66:0x01bd, B:65:0x01b9, B:59:0x0195, B:10:0x005c, B:12:0x0074, B:15:0x007f, B:17:0x0085, B:20:0x0090, B:23:0x0099, B:26:0x00a6, B:29:0x00b3, B:32:0x00c1), top: B:85:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x022f A[Catch: Exception -> 0x0257, TryCatch #0 {Exception -> 0x0257, blocks: (B:2:0x0000, B:4:0x003d, B:7:0x0047, B:36:0x00cf, B:67:0x01ff, B:69:0x021a, B:71:0x0220, B:73:0x0226, B:77:0x022f, B:79:0x0235, B:39:0x00e4, B:41:0x00fc, B:47:0x0109, B:48:0x0119, B:50:0x0120, B:54:0x0132, B:56:0x0187, B:58:0x0191, B:60:0x0199, B:62:0x01ab, B:64:0x01b5, B:66:0x01bd, B:65:0x01b9, B:59:0x0195, B:10:0x005c, B:12:0x0074, B:15:0x007f, B:17:0x0085, B:20:0x0090, B:23:0x0099, B:26:0x00a6, B:29:0x00b3, B:32:0x00c1), top: B:85:0x0000 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int m21056a(java.util.Map<java.lang.String, java.lang.String> r8) {
        /*
            Method dump skipped, instructions count: 606
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.apmobilesecuritysdk.p104a.C1959a.m21056a(java.util.Map):int");
    }
}
