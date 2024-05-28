package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11455i;
import com.xiaomi.push.C11649x;
import com.xiaomi.push.EnumC11473n;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.xiaomi.push.service.q */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11615q {

    /* renamed from: a */
    private static C11614p f23736a;

    /* renamed from: a */
    private static InterfaceC11616a f23737a;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.service.q$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11616a {
        /* renamed from: a */
        void mo2421a();
    }

    /* renamed from: a */
    public static synchronized C11614p m2430a(Context context) {
        synchronized (C11615q.class) {
            if (f23736a != null) {
                return f23736a;
            }
            SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_account", 0);
            String string = sharedPreferences.getString("uuid", null);
            String string2 = sharedPreferences.getString("token", null);
            String string3 = sharedPreferences.getString("security", null);
            String string4 = sharedPreferences.getString("app_id", null);
            String string5 = sharedPreferences.getString("app_token", null);
            String string6 = sharedPreferences.getString("package_name", null);
            String string7 = sharedPreferences.getString("device_id", null);
            int i = sharedPreferences.getInt("env_type", 1);
            if (!TextUtils.isEmpty(string7) && C11455i.m3044a(string7)) {
                string7 = C11455i.m3031g(context);
                sharedPreferences.edit().putString("device_id", string7).commit();
            }
            if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
                return null;
            }
            String m3031g = C11455i.m3031g(context);
            if (!"com.xiaomi.xmsf".equals(context.getPackageName()) && !TextUtils.isEmpty(m3031g) && !TextUtils.isEmpty(string7) && !string7.equals(m3031g)) {
                AbstractC11049b.m5282a("read_phone_state permission changes.");
            }
            f23736a = new C11614p(string, string2, string3, string4, string5, string6, i);
            return f23736a;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(29:3|4|(2:8|(26:10|11|(1:13)(1:122)|14|(1:16)(1:121)|17|(1:19)(1:120)|20|21|22|23|(1:25)(1:116)|26|(6:28|(1:30)|31|(1:35)|36|(1:38))|39|(1:41)|42|(6:45|46|47|49|50|43)|54|55|(1:57)(1:115)|58|(3:63|64|(2:66|67)(8:(1:70)|71|72|(1:109)(2:76|(4:86|87|88|(7:90|(1:92)|93|94|95|96|97)(2:99|100))(1:78))|(1:82)|83|84|85))|114|64|(0)(0)))|123|11|(0)(0)|14|(0)(0)|17|(0)(0)|20|21|22|23|(0)(0)|26|(0)|39|(0)|42|(1:43)|54|55|(0)(0)|58|(4:60|63|64|(0)(0))|114|64|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0087, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0088, code lost:
        com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5276a(r0);
        r0 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:110:0x032d A[Catch: all -> 0x0342, TryCatch #1 {, blocks: (B:4:0x0005, B:6:0x001a, B:8:0x0024, B:10:0x003b, B:12:0x0047, B:16:0x005a, B:20:0x0066, B:24:0x0072, B:25:0x007c, B:31:0x0090, B:33:0x0099, B:35:0x00c3, B:37:0x00cf, B:38:0x00e2, B:40:0x00ec, B:42:0x00f2, B:43:0x0106, B:45:0x010c, B:46:0x0111, B:48:0x0134, B:49:0x013e, B:50:0x0175, B:52:0x017b, B:53:0x0182, B:56:0x0191, B:57:0x01c2, B:59:0x01c6, B:61:0x01d1, B:63:0x01ed, B:66:0x01f4, B:68:0x020b, B:74:0x021a, B:78:0x0221, B:80:0x0238, B:82:0x023e, B:84:0x0248, B:86:0x0255, B:88:0x0276, B:89:0x028c, B:91:0x02aa, B:103:0x02f8, B:110:0x032d, B:112:0x0333, B:113:0x033b, B:106:0x0311, B:95:0x02cd, B:60:0x01cb, B:28:0x0088), top: B:119:0x0005, inners: #3, #5, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0090 A[Catch: all -> 0x0342, TryCatch #1 {, blocks: (B:4:0x0005, B:6:0x001a, B:8:0x0024, B:10:0x003b, B:12:0x0047, B:16:0x005a, B:20:0x0066, B:24:0x0072, B:25:0x007c, B:31:0x0090, B:33:0x0099, B:35:0x00c3, B:37:0x00cf, B:38:0x00e2, B:40:0x00ec, B:42:0x00f2, B:43:0x0106, B:45:0x010c, B:46:0x0111, B:48:0x0134, B:49:0x013e, B:50:0x0175, B:52:0x017b, B:53:0x0182, B:56:0x0191, B:57:0x01c2, B:59:0x01c6, B:61:0x01d1, B:63:0x01ed, B:66:0x01f4, B:68:0x020b, B:74:0x021a, B:78:0x0221, B:80:0x0238, B:82:0x023e, B:84:0x0248, B:86:0x0255, B:88:0x0276, B:89:0x028c, B:91:0x02aa, B:103:0x02f8, B:110:0x032d, B:112:0x0333, B:113:0x033b, B:106:0x0311, B:95:0x02cd, B:60:0x01cb, B:28:0x0088), top: B:119:0x0005, inners: #3, #5, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00c3 A[Catch: all -> 0x0342, TryCatch #1 {, blocks: (B:4:0x0005, B:6:0x001a, B:8:0x0024, B:10:0x003b, B:12:0x0047, B:16:0x005a, B:20:0x0066, B:24:0x0072, B:25:0x007c, B:31:0x0090, B:33:0x0099, B:35:0x00c3, B:37:0x00cf, B:38:0x00e2, B:40:0x00ec, B:42:0x00f2, B:43:0x0106, B:45:0x010c, B:46:0x0111, B:48:0x0134, B:49:0x013e, B:50:0x0175, B:52:0x017b, B:53:0x0182, B:56:0x0191, B:57:0x01c2, B:59:0x01c6, B:61:0x01d1, B:63:0x01ed, B:66:0x01f4, B:68:0x020b, B:74:0x021a, B:78:0x0221, B:80:0x0238, B:82:0x023e, B:84:0x0248, B:86:0x0255, B:88:0x0276, B:89:0x028c, B:91:0x02aa, B:103:0x02f8, B:110:0x032d, B:112:0x0333, B:113:0x033b, B:106:0x0311, B:95:0x02cd, B:60:0x01cb, B:28:0x0088), top: B:119:0x0005, inners: #3, #5, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0134 A[Catch: all -> 0x0342, TryCatch #1 {, blocks: (B:4:0x0005, B:6:0x001a, B:8:0x0024, B:10:0x003b, B:12:0x0047, B:16:0x005a, B:20:0x0066, B:24:0x0072, B:25:0x007c, B:31:0x0090, B:33:0x0099, B:35:0x00c3, B:37:0x00cf, B:38:0x00e2, B:40:0x00ec, B:42:0x00f2, B:43:0x0106, B:45:0x010c, B:46:0x0111, B:48:0x0134, B:49:0x013e, B:50:0x0175, B:52:0x017b, B:53:0x0182, B:56:0x0191, B:57:0x01c2, B:59:0x01c6, B:61:0x01d1, B:63:0x01ed, B:66:0x01f4, B:68:0x020b, B:74:0x021a, B:78:0x0221, B:80:0x0238, B:82:0x023e, B:84:0x0248, B:86:0x0255, B:88:0x0276, B:89:0x028c, B:91:0x02aa, B:103:0x02f8, B:110:0x032d, B:112:0x0333, B:113:0x033b, B:106:0x0311, B:95:0x02cd, B:60:0x01cb, B:28:0x0088), top: B:119:0x0005, inners: #3, #5, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x017b A[Catch: all -> 0x0342, TRY_LEAVE, TryCatch #1 {, blocks: (B:4:0x0005, B:6:0x001a, B:8:0x0024, B:10:0x003b, B:12:0x0047, B:16:0x005a, B:20:0x0066, B:24:0x0072, B:25:0x007c, B:31:0x0090, B:33:0x0099, B:35:0x00c3, B:37:0x00cf, B:38:0x00e2, B:40:0x00ec, B:42:0x00f2, B:43:0x0106, B:45:0x010c, B:46:0x0111, B:48:0x0134, B:49:0x013e, B:50:0x0175, B:52:0x017b, B:53:0x0182, B:56:0x0191, B:57:0x01c2, B:59:0x01c6, B:61:0x01d1, B:63:0x01ed, B:66:0x01f4, B:68:0x020b, B:74:0x021a, B:78:0x0221, B:80:0x0238, B:82:0x023e, B:84:0x0248, B:86:0x0255, B:88:0x0276, B:89:0x028c, B:91:0x02aa, B:103:0x02f8, B:110:0x032d, B:112:0x0333, B:113:0x033b, B:106:0x0311, B:95:0x02cd, B:60:0x01cb, B:28:0x0088), top: B:119:0x0005, inners: #3, #5, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01c6 A[Catch: all -> 0x0342, TryCatch #1 {, blocks: (B:4:0x0005, B:6:0x001a, B:8:0x0024, B:10:0x003b, B:12:0x0047, B:16:0x005a, B:20:0x0066, B:24:0x0072, B:25:0x007c, B:31:0x0090, B:33:0x0099, B:35:0x00c3, B:37:0x00cf, B:38:0x00e2, B:40:0x00ec, B:42:0x00f2, B:43:0x0106, B:45:0x010c, B:46:0x0111, B:48:0x0134, B:49:0x013e, B:50:0x0175, B:52:0x017b, B:53:0x0182, B:56:0x0191, B:57:0x01c2, B:59:0x01c6, B:61:0x01d1, B:63:0x01ed, B:66:0x01f4, B:68:0x020b, B:74:0x021a, B:78:0x0221, B:80:0x0238, B:82:0x023e, B:84:0x0248, B:86:0x0255, B:88:0x0276, B:89:0x028c, B:91:0x02aa, B:103:0x02f8, B:110:0x032d, B:112:0x0333, B:113:0x033b, B:106:0x0311, B:95:0x02cd, B:60:0x01cb, B:28:0x0088), top: B:119:0x0005, inners: #3, #5, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01cb A[Catch: all -> 0x0342, TryCatch #1 {, blocks: (B:4:0x0005, B:6:0x001a, B:8:0x0024, B:10:0x003b, B:12:0x0047, B:16:0x005a, B:20:0x0066, B:24:0x0072, B:25:0x007c, B:31:0x0090, B:33:0x0099, B:35:0x00c3, B:37:0x00cf, B:38:0x00e2, B:40:0x00ec, B:42:0x00f2, B:43:0x0106, B:45:0x010c, B:46:0x0111, B:48:0x0134, B:49:0x013e, B:50:0x0175, B:52:0x017b, B:53:0x0182, B:56:0x0191, B:57:0x01c2, B:59:0x01c6, B:61:0x01d1, B:63:0x01ed, B:66:0x01f4, B:68:0x020b, B:74:0x021a, B:78:0x0221, B:80:0x0238, B:82:0x023e, B:84:0x0248, B:86:0x0255, B:88:0x0276, B:89:0x028c, B:91:0x02aa, B:103:0x02f8, B:110:0x032d, B:112:0x0333, B:113:0x033b, B:106:0x0311, B:95:0x02cd, B:60:0x01cb, B:28:0x0088), top: B:119:0x0005, inners: #3, #5, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0215 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0217  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized com.xiaomi.push.service.C11614p m2424a(android.content.Context r17, java.lang.String r18, java.lang.String r19, java.lang.String r20) {
        /*
            Method dump skipped, instructions count: 837
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C11615q.m2424a(android.content.Context, java.lang.String, java.lang.String, java.lang.String):com.xiaomi.push.service.p");
    }

    /* renamed from: a */
    private static String m2423a(Context context, boolean z) {
        String m2592a = C11578b.m2591a(context).m2592a();
        String str = z ? "/pass/v2/register/encrypt" : "/pass/v2/register";
        if (C11649x.m2262b()) {
            return "http://10.38.162.35:9085" + str;
        } else if (EnumC11473n.China.name().equals(m2592a)) {
            return "https://cn.register.xmpush.xiaomi.com" + str;
        } else {
            return null;
        }
    }

    /* renamed from: a */
    private static boolean m2427a(Context context) {
        return context.getPackageName().equals("com.xiaomi.xmsf");
    }

    /* renamed from: a */
    private static void m2426a(Context context, int i) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_account", 0).edit();
        edit.putInt("enc_req_fail_count", i);
        edit.commit();
    }

    /* renamed from: a */
    private static int m2431a(Context context) {
        return context.getSharedPreferences("mipush_account", 0).getInt("enc_req_fail_count", 0);
    }

    /* renamed from: a */
    public static void m2425a(Context context, C11614p c11614p) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_account", 0).edit();
        edit.putString("uuid", c11614p.f23730a);
        edit.putString("security", c11614p.f23732c);
        edit.putString("token", c11614p.f23731b);
        edit.putString("app_id", c11614p.f23733d);
        edit.putString("package_name", c11614p.f23735f);
        edit.putString("app_token", c11614p.f23734e);
        edit.putString("device_id", C11455i.m3031g(context));
        edit.putInt("env_type", c11614p.f23729a);
        edit.commit();
        m2432a();
    }

    /* renamed from: a */
    public static void m2428a(Context context) {
        context.getSharedPreferences("mipush_account", 0).edit().clear().commit();
        f23736a = null;
        m2432a();
    }

    /* renamed from: a */
    public static void m2422a(InterfaceC11616a interfaceC11616a) {
        f23737a = interfaceC11616a;
    }

    /* renamed from: a */
    public static void m2432a() {
        InterfaceC11616a interfaceC11616a = f23737a;
        if (interfaceC11616a != null) {
            interfaceC11616a.mo2421a();
        }
    }

    /* renamed from: a */
    public static String m2429a(Context context) {
        C11614p m2430a = m2430a(context);
        if (m2430a != null && !TextUtils.isEmpty(m2430a.f23730a)) {
            String[] split = m2430a.f23730a.split("@");
            if (split.length > 0) {
                return split[0];
            }
        }
        return null;
    }
}
