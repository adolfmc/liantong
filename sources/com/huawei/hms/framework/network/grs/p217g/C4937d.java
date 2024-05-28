package com.huawei.hms.framework.network.grs.p217g;

import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;

/* renamed from: com.huawei.hms.framework.network.grs.g.d */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4937d {

    /* renamed from: o */
    private static final String f11279o = "d";

    /* renamed from: a */
    private Map<String, List<String>> f11280a;

    /* renamed from: b */
    private byte[] f11281b;

    /* renamed from: c */
    private int f11282c;

    /* renamed from: d */
    private long f11283d;

    /* renamed from: e */
    private long f11284e;

    /* renamed from: f */
    private long f11285f;

    /* renamed from: g */
    private String f11286g;

    /* renamed from: h */
    private int f11287h;

    /* renamed from: i */
    private int f11288i;

    /* renamed from: j */
    private String f11289j;

    /* renamed from: k */
    private long f11290k;

    /* renamed from: l */
    private String f11291l;

    /* renamed from: m */
    private Exception f11292m;

    /* renamed from: n */
    private String f11293n;

    public C4937d(int i, Map<String, List<String>> map, byte[] bArr, long j) {
        this.f11282c = 0;
        this.f11287h = 2;
        this.f11288i = 9001;
        this.f11289j = "";
        this.f11290k = 0L;
        this.f11291l = "";
        this.f11282c = i;
        this.f11280a = map;
        this.f11281b = ByteBuffer.wrap(bArr).array();
        this.f11283d = j;
        m14897s();
    }

    public C4937d(Exception exc, long j) {
        this.f11282c = 0;
        this.f11287h = 2;
        this.f11288i = 9001;
        this.f11289j = "";
        this.f11290k = 0L;
        this.f11291l = "";
        this.f11292m = exc;
        this.f11283d = j;
    }

    /* renamed from: a */
    private void m14926a(Map<String, String> map) {
        String str;
        String str2;
        if (map.containsKey("ETag")) {
            String str3 = map.get("ETag");
            if (!TextUtils.isEmpty(str3)) {
                Logger.m15049i(f11279o, "success get Etag from server");
                m14927a(str3);
                return;
            }
            str = f11279o;
            str2 = "The Response Heads Etag is Empty";
        } else {
            str = f11279o;
            str2 = "Response Heads has not Etag";
        }
        Logger.m15049i(str, str2);
    }

    /* renamed from: b */
    private void m14924b(int i) {
        this.f11288i = i;
    }

    /* renamed from: b */
    private void m14921b(Map<String, String> map) {
        long time;
        if (map.containsKey("Cache-Control")) {
            String str = map.get("Cache-Control");
            if (!TextUtils.isEmpty(str) && str.contains("max-age=")) {
                try {
                    time = Long.parseLong(str.substring(str.indexOf("max-age=") + 8));
                } catch (NumberFormatException e) {
                    e = e;
                    time = 0;
                }
                try {
                    Logger.m15046v(f11279o, "Cache-Control value{%s}", Long.valueOf(time));
                } catch (NumberFormatException e2) {
                    e = e2;
                    Logger.m15044w(f11279o, "getExpireTime addHeadersToResult NumberFormatException", e);
                    long j = ((time > 0 || time > 2592000) ? 86400L : 86400L) * 1000;
                    Logger.m15048i(f11279o, "convert expireTime{%s}", Long.valueOf(j));
                    m14917c(String.valueOf(j + System.currentTimeMillis()));
                }
            }
            time = 0;
        } else {
            if (map.containsKey("Expires")) {
                String str2 = map.get("Expires");
                Logger.m15046v(f11279o, "expires is{%s}", str2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.ROOT);
                String str3 = map.containsKey("Date") ? map.get("Date") : null;
                try {
                    time = (simpleDateFormat.parse(str2).getTime() - (TextUtils.isEmpty(str3) ? new Date() : simpleDateFormat.parse(str3)).getTime()) / 1000;
                } catch (ParseException e3) {
                    Logger.m15044w(f11279o, "getExpireTime ParseException.", e3);
                }
            } else {
                Logger.m15049i(f11279o, "response headers neither contains Cache-Control nor Expires.");
            }
            time = 0;
        }
        long j2 = ((time > 0 || time > 2592000) ? 86400L : 86400L) * 1000;
        Logger.m15048i(f11279o, "convert expireTime{%s}", Long.valueOf(j2));
        m14917c(String.valueOf(j2 + System.currentTimeMillis()));
    }

    /* renamed from: c */
    private void m14919c(int i) {
        this.f11287h = i;
    }

    /* renamed from: c */
    private void m14918c(long j) {
        this.f11290k = j;
    }

    /* renamed from: c */
    private void m14917c(String str) {
        this.f11289j = str;
    }

    /* renamed from: c */
    private void m14916c(Map<String, String> map) {
        long j;
        if (map.containsKey("Retry-After")) {
            String str = map.get("Retry-After");
            if (!TextUtils.isEmpty(str)) {
                try {
                    j = Long.parseLong(str);
                } catch (NumberFormatException e) {
                    Logger.m15044w(f11279o, "getRetryAfter addHeadersToResult NumberFormatException", e);
                }
                long j2 = j * 1000;
                Logger.m15046v(f11279o, "convert retry-afterTime{%s}", Long.valueOf(j2));
                m14918c(j2);
            }
        }
        j = 0;
        long j22 = j * 1000;
        Logger.m15046v(f11279o, "convert retry-afterTime{%s}", Long.valueOf(j22));
        m14918c(j22);
    }

    /* renamed from: d */
    private void m14914d(String str) {
    }

    /* renamed from: e */
    private void m14912e(String str) {
    }

    /* renamed from: f */
    private void m14910f(String str) {
        this.f11286g = str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0050, code lost:
        if (r4.getInt("resultCode") == 0) goto L17;
     */
    /* renamed from: p */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m14900p() {
        /*
            r7 = this;
            boolean r0 = r7.m14903m()
            r1 = 1
            if (r0 == 0) goto L12
            java.lang.String r0 = com.huawei.hms.framework.network.grs.p217g.C4937d.f11279o
            java.lang.String r2 = "GRSSDK get httpcode{304} not any changed."
            com.huawei.hms.framework.common.Logger.m15049i(r0, r2)
            r7.m14919c(r1)
            return
        L12:
            boolean r0 = r7.m14901o()
            r2 = 2
            if (r0 != 0) goto L24
            java.lang.String r0 = com.huawei.hms.framework.network.grs.p217g.C4937d.f11279o
            java.lang.String r1 = "GRSSDK parse server body all failed."
            com.huawei.hms.framework.common.Logger.m15049i(r0, r1)
            r7.m14919c(r2)
            return
        L24:
            r0 = 0
            byte[] r3 = r7.f11281b     // Catch: org.json.JSONException -> Lcb
            java.lang.String r3 = com.huawei.hms.framework.common.StringUtils.byte2Str(r3)     // Catch: org.json.JSONException -> Lcb
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: org.json.JSONException -> Lcb
            r4.<init>(r3)     // Catch: org.json.JSONException -> Lcb
            r3 = -1
            java.lang.String r5 = "isSuccess"
            boolean r5 = r4.has(r5)     // Catch: org.json.JSONException -> Lcb
            if (r5 == 0) goto L42
            java.lang.String r3 = "isSuccess"
            int r3 = r4.getInt(r3)     // Catch: org.json.JSONException -> Lcb
            if (r3 != r1) goto L54
            goto L52
        L42:
            java.lang.String r5 = "resultCode"
            boolean r5 = r4.has(r5)     // Catch: org.json.JSONException -> Lcb
            if (r5 == 0) goto L56
            java.lang.String r3 = "resultCode"
            int r3 = r4.getInt(r3)     // Catch: org.json.JSONException -> Lcb
            if (r3 != 0) goto L54
        L52:
            r3 = r1
            goto L5e
        L54:
            r3 = r2
            goto L5e
        L56:
            java.lang.String r5 = com.huawei.hms.framework.network.grs.p217g.C4937d.f11279o     // Catch: org.json.JSONException -> Lcb
            java.lang.String r6 = "sth. wrong because server errorcode's key."
            com.huawei.hms.framework.common.Logger.m15052e(r5, r6)     // Catch: org.json.JSONException -> Lcb
        L5e:
            if (r3 == r1) goto L69
            java.lang.String r5 = "services"
            boolean r5 = r4.has(r5)     // Catch: org.json.JSONException -> Lcb
            if (r5 == 0) goto L69
            r3 = r0
        L69:
            r7.m14919c(r3)     // Catch: org.json.JSONException -> Lcb
            if (r3 == r1) goto L9a
            if (r3 != 0) goto L71
            goto L9a
        L71:
            java.lang.String r3 = "errorCode"
            boolean r3 = r4.has(r3)     // Catch: org.json.JSONException -> Lcb
            if (r3 == 0) goto L80
            java.lang.String r3 = "errorCode"
            int r3 = r4.getInt(r3)     // Catch: org.json.JSONException -> Lcb
            goto L82
        L80:
            r3 = 9001(0x2329, float:1.2613E-41)
        L82:
            r7.m14924b(r3)     // Catch: org.json.JSONException -> Lcb
            java.lang.String r3 = "errorDesc"
            boolean r3 = r4.has(r3)     // Catch: org.json.JSONException -> Lcb
            if (r3 == 0) goto L94
            java.lang.String r3 = "errorDesc"
            java.lang.String r3 = r4.getString(r3)     // Catch: org.json.JSONException -> Lcb
            goto L96
        L94:
            java.lang.String r3 = ""
        L96:
            r7.m14914d(r3)     // Catch: org.json.JSONException -> Lcb
            goto Le2
        L9a:
            java.lang.String r3 = "services"
            boolean r3 = r4.has(r3)     // Catch: org.json.JSONException -> Lcb
            if (r3 == 0) goto Lad
            java.lang.String r3 = "services"
            org.json.JSONObject r3 = r4.getJSONObject(r3)     // Catch: org.json.JSONException -> Lcb
            java.lang.String r3 = r3.toString()     // Catch: org.json.JSONException -> Lcb
            goto Laf
        Lad:
            java.lang.String r3 = ""
        Laf:
            r7.m14910f(r3)     // Catch: org.json.JSONException -> Lcb
            java.lang.String r3 = "errorList"
            boolean r3 = r4.has(r3)     // Catch: org.json.JSONException -> Lcb
            if (r3 == 0) goto Lc5
            java.lang.String r3 = "errorList"
            org.json.JSONObject r3 = r4.getJSONObject(r3)     // Catch: org.json.JSONException -> Lcb
            java.lang.String r3 = r3.toString()     // Catch: org.json.JSONException -> Lcb
            goto Lc7
        Lc5:
            java.lang.String r3 = ""
        Lc7:
            r7.m14912e(r3)     // Catch: org.json.JSONException -> Lcb
            goto Le2
        Lcb:
            r3 = move-exception
            java.lang.String r4 = com.huawei.hms.framework.network.grs.p217g.C4937d.f11279o
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r3 = r3.getMessage()
            java.lang.String r3 = com.huawei.hms.framework.common.StringUtils.anonymizeMessage(r3)
            r1[r0] = r3
            java.lang.String r0 = "GrsResponse GrsResponse(String result) JSONException: %s"
            com.huawei.hms.framework.common.Logger.m15043w(r4, r0, r1)
            r7.m14919c(r2)
        Le2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.p217g.C4937d.m14900p():void");
    }

    /* renamed from: q */
    private void m14899q() {
        if (m14901o() || m14902n() || m14903m()) {
            Map<String, String> m14898r = m14898r();
            if (m14898r.size() <= 0) {
                Logger.m15045w(f11279o, "parseHeader {headers.size() <= 0}");
                return;
            }
            try {
                if (m14901o() || m14903m()) {
                    m14921b(m14898r);
                    m14926a(m14898r);
                }
                if (m14902n()) {
                    m14916c(m14898r);
                }
            } catch (JSONException e) {
                Logger.m15043w(f11279o, "parseHeader catch JSONException: %s", StringUtils.anonymizeMessage(e.getMessage()));
            }
        }
    }

    /* renamed from: r */
    private Map<String, String> m14898r() {
        HashMap hashMap = new HashMap(16);
        Map<String, List<String>> map = this.f11280a;
        if (map == null || map.size() <= 0) {
            Logger.m15047v(f11279o, "parseRespHeaders {respHeaders == null} or {respHeaders.size() <= 0}");
            return hashMap;
        }
        for (Map.Entry<String, List<String>> entry : this.f11280a.entrySet()) {
            String key = entry.getKey();
            for (String str : entry.getValue()) {
                hashMap.put(key, str);
            }
        }
        return hashMap;
    }

    /* renamed from: s */
    private void m14897s() {
        m14899q();
        m14900p();
    }

    /* renamed from: a */
    public String m14930a() {
        return this.f11289j;
    }

    /* renamed from: a */
    public void m14929a(int i) {
    }

    /* renamed from: a */
    public void m14928a(long j) {
        this.f11285f = j;
    }

    /* renamed from: a */
    public void m14927a(String str) {
        this.f11291l = str;
    }

    /* renamed from: b */
    public int m14925b() {
        return this.f11282c;
    }

    /* renamed from: b */
    public void m14923b(long j) {
        this.f11284e = j;
    }

    /* renamed from: b */
    public void m14922b(String str) {
        this.f11293n = str;
    }

    /* renamed from: c */
    public int m14920c() {
        return this.f11288i;
    }

    /* renamed from: d */
    public Exception m14915d() {
        return this.f11292m;
    }

    /* renamed from: e */
    public String m14913e() {
        return this.f11291l;
    }

    /* renamed from: f */
    public int m14911f() {
        return this.f11287h;
    }

    /* renamed from: g */
    public long m14909g() {
        return this.f11285f;
    }

    /* renamed from: h */
    public long m14908h() {
        return this.f11284e;
    }

    /* renamed from: i */
    public long m14907i() {
        return this.f11283d;
    }

    /* renamed from: j */
    public String m14906j() {
        return this.f11286g;
    }

    /* renamed from: k */
    public long m14905k() {
        return this.f11290k;
    }

    /* renamed from: l */
    public String m14904l() {
        return this.f11293n;
    }

    /* renamed from: m */
    public boolean m14903m() {
        return this.f11282c == 304;
    }

    /* renamed from: n */
    public boolean m14902n() {
        return this.f11282c == 503;
    }

    /* renamed from: o */
    public boolean m14901o() {
        return this.f11282c == 200;
    }
}
