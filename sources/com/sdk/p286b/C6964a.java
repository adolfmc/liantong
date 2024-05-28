package com.sdk.p286b;

import android.util.Base64;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sdk.p290f.C6998d;
import com.sdk.p296l.C7011a;
import com.sdk.p302r.C7037a;
import com.sdk.p306v.C7042b;
import com.sdk.p306v.C7046f;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.sdk.b.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6964a {

    /* renamed from: a */
    public static final String f18051a = "a";

    /* renamed from: b */
    public static Boolean f18052b = Boolean.valueOf(C6998d.f18135a);

    /* renamed from: c */
    public static Boolean f18053c = Boolean.TRUE;

    /* JADX WARN: Removed duplicated region for block: B:20:0x008a A[Catch: Exception -> 0x00ed, TRY_LEAVE, TryCatch #1 {Exception -> 0x00ed, blocks: (B:2:0x0000, B:4:0x000e, B:6:0x001c, B:8:0x0028, B:9:0x004f, B:20:0x008a, B:22:0x008e, B:24:0x009a, B:25:0x009f, B:27:0x00a8, B:29:0x00b3, B:28:0x00ad, B:31:0x00e4, B:33:0x00e8, B:17:0x006f, B:11:0x0058), top: B:39:0x0000, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00e4 A[Catch: Exception -> 0x00ed, TRY_LEAVE, TryCatch #1 {Exception -> 0x00ed, blocks: (B:2:0x0000, B:4:0x000e, B:6:0x001c, B:8:0x0028, B:9:0x004f, B:20:0x008a, B:22:0x008e, B:24:0x009a, B:25:0x009f, B:27:0x00a8, B:29:0x00b3, B:28:0x00ad, B:31:0x00e4, B:33:0x00e8, B:17:0x006f, B:11:0x0058), top: B:39:0x0000, inners: #0 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m8196a(android.content.Context r5, int r6, java.lang.String r7) {
        /*
            java.lang.String r7 = m8197a(r6, r7)     // Catch: java.lang.Exception -> Led
            java.lang.Boolean r0 = com.sdk.p302r.C7037a.m8129b(r7)     // Catch: java.lang.Exception -> Led
            boolean r0 = r0.booleanValue()     // Catch: java.lang.Exception -> Led
            if (r0 == 0) goto Led
            java.lang.String r7 = com.sdk.p294j.C7008a.m8155b(r5, r7)     // Catch: java.lang.Exception -> Led
            java.lang.Boolean r0 = com.sdk.p302r.C7037a.m8129b(r7)     // Catch: java.lang.Exception -> Led
            boolean r0 = r0.booleanValue()     // Catch: java.lang.Exception -> Led
            if (r0 == 0) goto Led
            java.lang.String r0 = com.sdk.p304t.C7039a.f18205g     // Catch: java.lang.Exception -> Led
            java.lang.Boolean r0 = com.sdk.p302r.C7037a.m8130a(r0)     // Catch: java.lang.Exception -> Led
            boolean r0 = r0.booleanValue()     // Catch: java.lang.Exception -> Led
            if (r0 == 0) goto L4f
            java.lang.String r0 = com.sdk.base.module.config.BaseConfig.apk     // Catch: java.lang.Exception -> Led
            java.lang.String r5 = com.sdk.p293i.C7007a.m8161a(r5, r0)     // Catch: java.lang.Exception -> Led
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Led
            r0.<init>()     // Catch: java.lang.Exception -> Led
            r0.append(r5)     // Catch: java.lang.Exception -> Led
            r0.append(r5)     // Catch: java.lang.Exception -> Led
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> Led
            int r1 = r5.length()     // Catch: java.lang.Exception -> Led
            int r1 = r1 + (-16)
            int r5 = r5.length()     // Catch: java.lang.Exception -> Led
            int r5 = r5 + 16
            java.lang.String r5 = r0.substring(r1, r5)     // Catch: java.lang.Exception -> Led
            com.sdk.p304t.C7039a.f18205g = r5     // Catch: java.lang.Exception -> Led
        L4f:
            java.lang.String r5 = m8195a(r7)     // Catch: java.lang.Exception -> Led
            java.lang.String r5 = com.sdk.p303s.C7038a.m8128a(r5)     // Catch: java.lang.Exception -> Led
            r0 = 1
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: java.lang.Exception -> L6e
            r1.<init>(r5)     // Catch: java.lang.Exception -> L6e
            java.lang.String r2 = "exp"
            long r1 = r1.optLong(r2)     // Catch: java.lang.Exception -> L6e
            long r3 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L6e
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 >= 0) goto L6c
            goto L87
        L6c:
            r1 = 0
            goto L88
        L6e:
            r1 = move-exception
            java.lang.String r2 = com.sdk.p303s.C7038a.f18197a     // Catch: java.lang.Exception -> Led
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Led
            r3.<init>()     // Catch: java.lang.Exception -> Led
            java.lang.String r4 = "out data error"
            r3.append(r4)     // Catch: java.lang.Exception -> Led
            r3.append(r1)     // Catch: java.lang.Exception -> Led
            java.lang.String r1 = r3.toString()     // Catch: java.lang.Exception -> Led
            java.lang.Boolean r3 = com.sdk.p303s.C7038a.f18198b     // Catch: java.lang.Exception -> Led
            com.sdk.base.framework.utils.log.LogUtils.m8184w(r2, r1, r3)     // Catch: java.lang.Exception -> Led
        L87:
            r1 = r0
        L88:
            if (r1 != 0) goto Le4
            java.lang.String r1 = com.sdk.p286b.C6964a.f18051a     // Catch: java.lang.Exception -> Led
            java.lang.String r2 = "can use cache"
            java.lang.Boolean r3 = com.sdk.p286b.C6964a.f18052b     // Catch: java.lang.Exception -> Led
            com.sdk.base.framework.utils.log.LogUtils.m8185i(r1, r2, r3)     // Catch: java.lang.Exception -> Led
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: java.lang.Exception -> Led
            r1.<init>(r5)     // Catch: java.lang.Exception -> Led
            if (r6 != r0) goto L9f
            java.lang.String r5 = "fakeMobile"
            r1.remove(r5)     // Catch: java.lang.Exception -> Led
        L9f:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Led
            r5.<init>()     // Catch: java.lang.Exception -> Led
            boolean r6 = r1 instanceof org.json.JSONObject     // Catch: java.lang.Exception -> Led
            if (r6 != 0) goto Lad
            java.lang.String r6 = r1.toString()     // Catch: java.lang.Exception -> Led
            goto Lb3
        Lad:
            org.json.JSONObject r1 = (org.json.JSONObject) r1     // Catch: java.lang.Exception -> Led
            java.lang.String r6 = com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation.toString(r1)     // Catch: java.lang.Exception -> Led
        Lb3:
            r5.append(r6)     // Catch: java.lang.Exception -> Led
            java.lang.String r6 = "-"
            r5.append(r6)     // Catch: java.lang.Exception -> Led
            java.lang.String r6 = m8193b(r7)     // Catch: java.lang.Exception -> Led
            r5.append(r6)     // Catch: java.lang.Exception -> Led
            java.lang.String r6 = "-"
            r5.append(r6)     // Catch: java.lang.Exception -> Led
            java.lang.String r6 = m8192c(r7)     // Catch: java.lang.Exception -> Led
            r5.append(r6)     // Catch: java.lang.Exception -> Led
            java.lang.String r6 = "-"
            r5.append(r6)     // Catch: java.lang.Exception -> Led
            java.lang.String r6 = "-"
            java.lang.String[] r6 = r7.split(r6)     // Catch: java.lang.Exception -> Led
            r7 = 3
            r6 = r6[r7]     // Catch: java.lang.Exception -> Led
            r5.append(r6)     // Catch: java.lang.Exception -> Led
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Exception -> Led
            return r5
        Le4:
            java.lang.String r5 = com.sdk.p286b.C6964a.f18051a     // Catch: java.lang.Exception -> Led
            java.lang.String r6 = "OutDate cache invalid"
            java.lang.Boolean r7 = com.sdk.p286b.C6964a.f18052b     // Catch: java.lang.Exception -> Led
            com.sdk.base.framework.utils.log.LogUtils.m8185i(r5, r6, r7)     // Catch: java.lang.Exception -> Led
        Led:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sdk.p286b.C6964a.m8196a(android.content.Context, int, java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    public static String m8195a(String str) {
        return str.split("-")[0];
    }

    /* renamed from: a */
    public static <T> String m8194a(String str, T t, String str2, String str3) {
        byte[] m8119b;
        try {
            if (f18053c.booleanValue()) {
                m8119b = str3.getBytes();
            } else {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(str2.getBytes());
                m8119b = C7042b.m8119b(str3.getBytes(), messageDigest.digest(), null, 1);
            }
            String encodeToString = Base64.encodeToString(m8119b, 0);
            return t + "-" + str2 + "-" + encodeToString + "-" + str;
        } catch (C7046f e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException(e2);
        }
    }

    /* renamed from: b */
    public static String m8193b(String str) {
        return str.split("-")[1];
    }

    /* renamed from: c */
    public static String m8192c(String str) {
        byte[] m8120a;
        String str2 = str.split("-")[2];
        String str3 = str.split("-")[1];
        try {
            if (f18053c.booleanValue()) {
                m8120a = Base64.decode(str2, 0);
            } else {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(str3.getBytes());
                m8120a = C7042b.m8120a(Base64.decode(str2, 0), messageDigest.digest(), null, 1);
            }
            return new String(m8120a);
        } catch (C7046f e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException(e2);
        }
    }

    /* renamed from: a */
    public static String m8197a(int i, String str) {
        String str2;
        String str3 = C7011a.f18161a;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            loop0: while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet4Address)) {
                        str2 = nextElement.getHostAddress();
                        break loop0;
                    }
                }
            }
        } catch (SocketException unused) {
        }
        str2 = null;
        if (C7037a.m8129b(str2).booleanValue()) {
            return "accessCode" + i + str + str2;
        }
        return null;
    }
}
