package com.networkbench.agent.impl.p243c;

import com.networkbench.agent.impl.harvest.HarvestConfiguration;
import com.networkbench.agent.impl.harvest.HttpLibType;
import com.networkbench.agent.impl.harvest.RequestMethodType;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p255g.p257b.C6411b;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6653u;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.net.URL;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.e */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6281e extends HarvestableArray {

    /* renamed from: s */
    private static final InterfaceC6393e f15658s = C6394f.m10150a();

    /* renamed from: a */
    public HashMap<String, String> f15659a;

    /* renamed from: b */
    public HashMap<String, String> f15660b;

    /* renamed from: c */
    HashMap<String, JsonObject> f15661c;

    /* renamed from: d */
    private final String f15662d;

    /* renamed from: e */
    private final int f15663e;

    /* renamed from: f */
    private Map f15664f;

    /* renamed from: g */
    private int f15665g;

    /* renamed from: h */
    private final String f15666h;

    /* renamed from: i */
    private final String f15667i;

    /* renamed from: j */
    private final String f15668j;

    /* renamed from: l */
    private Map<String, Object> f15669l;

    /* renamed from: m */
    private final String f15670m;

    /* renamed from: n */
    private final String f15671n;

    /* renamed from: o */
    private String f15672o;

    /* renamed from: p */
    private String f15673p;

    /* renamed from: q */
    private Long f15674q;

    /* renamed from: r */
    private String f15675r;

    /* renamed from: t */
    private RequestMethodType f15676t;

    /* renamed from: u */
    private String f15677u;

    /* renamed from: v */
    private HttpLibType f15678v;

    /* renamed from: w */
    private int f15679w;

    /* renamed from: x */
    private String f15680x;

    /* renamed from: a */
    public Map m10664a() {
        return this.f15664f;
    }

    /* renamed from: a */
    public void m10658a(Map map) {
        this.f15664f = map;
    }

    /* renamed from: b */
    public int m10657b() {
        return this.f15679w;
    }

    /* renamed from: a */
    public void m10663a(int i) {
        this.f15679w = i;
    }

    /* renamed from: c */
    public String m10655c() {
        try {
            return new URL(this.f15662d).getHost();
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = f15658s;
            interfaceC6393e.mo10115e("DownloadPlugin get hostName error: " + e.getMessage());
            return "";
        }
    }

    /* renamed from: d */
    public int m10654d() {
        int i;
        try {
            i = new URL(this.f15662d).getPort();
        } catch (Throwable th) {
            InterfaceC6393e interfaceC6393e = f15658s;
            interfaceC6393e.mo10115e("error getPortFromUrl: " + th.getMessage());
            i = -1;
        }
        return i == -1 ? this.f15662d.startsWith("https://") ? 443 : 80 : i;
    }

    /* renamed from: e */
    public HashMap<String, JsonObject> m10653e() {
        return this.f15661c;
    }

    public C6281e(String str, String str2, String str3, String str4, int i, String str5, String str6, Map<String, Object> map, String str7, RequestMethodType requestMethodType, String str8, HttpLibType httpLibType, int i2, String str9, HashMap hashMap, HashMap hashMap2, Map map2) {
        this.f15659a = new HashMap<>();
        this.f15660b = new HashMap<>();
        this.f15678v = HttpLibType.URLConnection;
        this.f15661c = new HashMap<>();
        this.f15663e = i;
        this.f15665g = 1;
        this.f15662d = str;
        this.f15666h = str5;
        this.f15667i = str6;
        this.f15669l = map;
        this.f15670m = str3;
        this.f15671n = str2;
        this.f15668j = str7;
        this.f15676t = requestMethodType;
        this.f15675r = str4;
        this.f15677u = str8;
        this.f15678v = httpLibType;
        this.f15679w = i2;
        this.f15680x = str9;
        this.f15673p = m10641q();
        this.f15659a = hashMap;
        this.f15660b = hashMap2;
        this.f15664f = map2;
    }

    public C6281e(C6411b c6411b) {
        this(c6411b.m10045j(), c6411b.m10049f(), c6411b.m10047h(), c6411b.m10039p(), c6411b.m10044k(), c6411b.m10043l(), c6411b.m10042m(), c6411b.m10041n(), "", c6411b.m10048g(), c6411b.m10038q(), c6411b.m10046i(), c6411b.m10050e(), c6411b.m10051d(), c6411b.f16199b, c6411b.f16200c, c6411b.m10053c());
        m10660a(Long.valueOf(c6411b.mo10065b()));
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive(this.f15662d));
        C6653u.m8749a(jsonArray, this.f15671n);
        RequestMethodType requestMethodType = this.f15676t;
        if (requestMethodType != null) {
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(requestMethodType.ordinal())));
        } else {
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(RequestMethodType.GET.ordinal())));
        }
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15678v.ordinal())));
        jsonArray.add(new JsonPrimitive(this.f15675r));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15663e)));
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(this.f15665g)));
        jsonArray.add(new JsonPrimitive(m10640r()));
        String str = this.f15677u;
        if (str == null) {
            str = "";
        }
        jsonArray.add(new JsonPrimitive(str));
        if (C6638h.m8963w().m9038ah()) {
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15679w)));
        } else {
            jsonArray.add(new JsonPrimitive((Number) 0));
        }
        String str2 = this.f15680x;
        if (str2 == null) {
            str2 = "";
        }
        jsonArray.add(new JsonPrimitive(str2));
        HashMap<String, JsonObject> hashMap = this.f15661c;
        if (hashMap != null) {
            jsonArray.add(C6653u.m8716c((Map<String, JsonObject>) hashMap));
        } else {
            jsonArray.add(new JsonObject());
        }
        jsonArray.add(new JsonPrimitive(C6653u.m8701f(this.f15664f)));
        return jsonArray;
    }

    /* renamed from: r */
    private String m10640r() {
        String h = m9931h(this.f15666h);
        int errRspSize = HarvestConfiguration.getDefaultHarvestConfiguration().getErrRspSize();
        if (h.length() > errRspSize) {
            InterfaceC6393e interfaceC6393e = f15658s;
            interfaceC6393e.mo10115e("HTTP Error response body is too large. Truncating to " + errRspSize + " bytes.");
            h = h.substring(0, errRspSize);
        }
        JsonObject jsonObject = new JsonObject();
        JsonObject jsonObject2 = new JsonObject();
        C6653u.m8747a(jsonObject2, this.f15669l, this.f15659a);
        jsonObject.add("params", jsonObject2);
        jsonObject.add("requestParams", new JsonPrimitive(m9931h(this.f15670m)));
        C6653u.m8748a(jsonObject, h, "response");
        jsonObject.add("stacktrace", new JsonPrimitive(m9931h(this.f15667i)));
        jsonObject.add("message", new JsonPrimitive(this.f15668j));
        return jsonObject.toString();
    }

    /* renamed from: f */
    public String m10652f() {
        return this.f15675r;
    }

    /* renamed from: a */
    public void m10659a(String str) {
        this.f15675r = str;
    }

    /* renamed from: g */
    public String m10651g() {
        return this.f15662d;
    }

    /* renamed from: h */
    public int m10650h() {
        return this.f15663e;
    }

    /* renamed from: i */
    public int m10649i() {
        return this.f15665g;
    }

    /* renamed from: j */
    public String m10648j() {
        return this.f15666h;
    }

    /* renamed from: k */
    public String m10647k() {
        return this.f15673p;
    }

    /* renamed from: l */
    public void m10646l() {
        this.f15673p = m10641q();
    }

    /* renamed from: m */
    public String m10645m() {
        return this.f15672o;
    }

    /* renamed from: b */
    public void m10656b(String str) {
        this.f15672o = str;
    }

    /* renamed from: n */
    public Long m10644n() {
        return this.f15674q;
    }

    /* renamed from: a */
    public void m10660a(Long l) {
        this.f15674q = l;
    }

    /* renamed from: o */
    public void m10643o() {
        this.f15665g++;
    }

    /* renamed from: a */
    public void m10661a(RequestMethodType requestMethodType) {
        this.f15676t = requestMethodType;
    }

    /* renamed from: p */
    public RequestMethodType m10642p() {
        return this.f15676t;
    }

    /* renamed from: a */
    public void m10662a(HttpLibType httpLibType) {
        this.f15678v = httpLibType;
    }

    /* renamed from: q */
    public String m10641q() {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            String str = this.f15662d;
            if (str != null) {
                messageDigest.update(str.getBytes());
            }
            String str2 = this.f15680x;
            if (str2 != null) {
                messageDigest.update(str2.getBytes());
            }
            messageDigest.update(this.f15676t.name().getBytes());
            messageDigest.update(ByteBuffer.allocate(8).putInt(this.f15679w).array());
            messageDigest.update(ByteBuffer.allocate(8).putInt(this.f15663e).array());
            String str3 = this.f15667i;
            if (str3 != null && str3.length() > 0) {
                messageDigest.update(this.f15667i.getBytes());
            }
            return new String(messageDigest.digest());
        } catch (NoSuchAlgorithmException unused) {
            f15658s.mo10116d("Unable to initialize SHA-1 hash algorithm");
            return null;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("url:" + this.f15662d);
        sb.append(" url:" + this.f15662d);
        sb.append(" remoteIP:" + this.f15675r);
        sb.append(" httpStatusCode:" + this.f15663e);
        sb.append(" errorCount:" + this.f15665g);
        sb.append(" responseBody:" + this.f15666h);
        sb.append(" requestmethod:" + this.f15676t.ordinal());
        sb.append(" stackTrace:" + this.f15667i);
        sb.append(" cdnVendorName:" + this.f15677u);
        sb.append(" userActionId:" + this.f15680x);
        return sb.toString();
    }
}
