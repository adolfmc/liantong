package com.tencent.p348mm.opensdk.channel.p350a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.tencent.p348mm.opensdk.utils.C10384Log;
import com.tencent.p348mm.opensdk.utils.C10386b;
import java.security.MessageDigest;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.tencent.mm.opensdk.channel.a.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C10372a {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.channel.a.a$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C10373a {

        /* renamed from: a */
        public String f19947a;

        /* renamed from: b */
        public String f19948b;

        /* renamed from: c */
        public String f19949c;

        /* renamed from: d */
        public long f19950d;

        /* renamed from: e */
        public Bundle f19951e;
    }

    /* renamed from: a */
    public static int m6225a(Bundle bundle, String str, int i) {
        if (bundle == null) {
            return i;
        }
        try {
            return bundle.getInt(str, i);
        } catch (Exception e) {
            C10384Log.m6210e("MicroMsg.IntentUtil", "getIntExtra exception:" + e.getMessage());
            return i;
        }
    }

    /* renamed from: a */
    public static Object m6228a(int i, String str) {
        try {
            switch (i) {
                case 1:
                    return Integer.valueOf(str);
                case 2:
                    return Long.valueOf(str);
                case 3:
                    return str;
                case 4:
                    return Boolean.valueOf(str);
                case 5:
                    return Float.valueOf(str);
                case 6:
                    return Double.valueOf(str);
                default:
                    C10384Log.m6210e("MicroMsg.SDK.PluginProvider.Resolver", "unknown type");
                    return null;
            }
        } catch (Exception e) {
            C10384Log.m6210e("MicroMsg.SDK.PluginProvider.Resolver", "resolveObj exception:" + e.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    public static String m6226a(Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        try {
            return bundle.getString(str);
        } catch (Exception e) {
            C10384Log.m6210e("MicroMsg.IntentUtil", "getStringExtra exception:" + e.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m6227a(Context context, C10373a c10373a) {
        String str;
        String str2;
        if (context == null || c10373a == null) {
            str = "MicroMsg.SDK.MMessage";
            str2 = "send fail, invalid argument";
        } else if (!C10386b.m6202b(c10373a.f19948b)) {
            String str3 = null;
            if (!C10386b.m6202b(c10373a.f19947a)) {
                str3 = c10373a.f19947a + ".permission.MM_MESSAGE";
            }
            Intent intent = new Intent(c10373a.f19948b);
            Bundle bundle = c10373a.f19951e;
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            String packageName = context.getPackageName();
            intent.putExtra("_mmessage_sdkVersion", 638058496);
            intent.putExtra("_mmessage_appPackage", packageName);
            intent.putExtra("_mmessage_content", c10373a.f19949c);
            intent.putExtra("_mmessage_support_content_type", c10373a.f19950d);
            intent.putExtra("_mmessage_checksum", m6223a(c10373a.f19949c, 638058496, packageName));
            context.sendBroadcast(intent, str3);
            C10384Log.m6211d("MicroMsg.SDK.MMessage", "send mm message, intent=" + intent + ", perm=" + str3);
            return true;
        } else {
            str = "MicroMsg.SDK.MMessage";
            str2 = "send fail, action is null";
        }
        C10384Log.m6210e(str, str2);
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x015c: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:104:0x015c */
    /* JADX WARN: Removed duplicated region for block: B:131:0x011e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0123 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0128 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:145:0x00ef A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:147:0x00f4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:149:0x00f9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:160:0x014d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0152 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0157 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v35 */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r7v29 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] m6224a(java.lang.String r7, int r8) {
        /*
            Method dump skipped, instructions count: 373
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.p348mm.opensdk.channel.p350a.C10372a.m6224a(java.lang.String, int):byte[]");
    }

    /* renamed from: a */
    public static byte[] m6223a(String str, int i, String str2) {
        String str3;
        StringBuffer stringBuffer = new StringBuffer();
        if (str != null) {
            stringBuffer.append(str);
        }
        stringBuffer.append(i);
        stringBuffer.append(str2);
        stringBuffer.append("mMcShCsTr");
        byte[] bytes = stringBuffer.toString().substring(1, 9).getBytes();
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            byte[] digest = messageDigest.digest();
            char[] cArr2 = new char[digest.length * 2];
            int i2 = 0;
            for (byte b : digest) {
                int i3 = i2 + 1;
                cArr2[i2] = cArr[(b >>> 4) & 15];
                i2 = i3 + 1;
                cArr2[i3] = cArr[b & 15];
            }
            str3 = new String(cArr2);
        } catch (Exception unused) {
            str3 = null;
        }
        return str3.getBytes();
    }
}
