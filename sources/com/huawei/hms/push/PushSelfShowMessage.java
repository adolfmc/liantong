package com.huawei.hms.push;

import android.text.TextUtils;
import com.huawei.hms.support.log.HMSLog;
import java.nio.charset.Charset;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.huawei.hms.push.m */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class PushSelfShowMessage {

    /* renamed from: B */
    private int f11634B;

    /* renamed from: D */
    private String f11636D;

    /* renamed from: b */
    private int f11640b;

    /* renamed from: c */
    private String f11641c;

    /* renamed from: d */
    private String f11642d;

    /* renamed from: l */
    private String f11650l;

    /* renamed from: m */
    private String f11651m;

    /* renamed from: n */
    private String f11652n;

    /* renamed from: o */
    private String f11653o;

    /* renamed from: p */
    private String f11654p;

    /* renamed from: r */
    private String f11656r;

    /* renamed from: s */
    private String f11657s;

    /* renamed from: z */
    private String f11664z;

    /* renamed from: a */
    private String f11639a = "";

    /* renamed from: e */
    private String f11643e = "";

    /* renamed from: f */
    private String f11644f = "";

    /* renamed from: g */
    private String f11645g = "";

    /* renamed from: h */
    private String f11646h = "";

    /* renamed from: i */
    private String f11647i = "";

    /* renamed from: j */
    private String f11648j = "";

    /* renamed from: k */
    private String f11649k = "";

    /* renamed from: q */
    private String f11655q = "";

    /* renamed from: t */
    private int f11658t = NotifyStyle.STYLE_DEFAULT.ordinal();

    /* renamed from: u */
    private String f11659u = "";

    /* renamed from: v */
    private String f11660v = "";

    /* renamed from: w */
    private String f11661w = "";

    /* renamed from: x */
    private int f11662x = 0;

    /* renamed from: y */
    private int f11663y = 0;

    /* renamed from: A */
    private String f11633A = "";

    /* renamed from: C */
    private String f11635C = "";

    /* renamed from: E */
    private String f11637E = "";

    /* renamed from: F */
    private String f11638F = "";

    public PushSelfShowMessage(byte[] bArr, byte[] bArr2) {
        Charset charset = PushConst.f11631a;
        this.f11656r = new String(bArr, charset);
        this.f11657s = new String(bArr2, charset);
    }

    /* renamed from: a */
    private JSONObject m14242a(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("msgContent", jSONObject);
        jSONObject2.put("group", this.f11639a);
        jSONObject2.put("tag", this.f11633A);
        jSONObject2.put("autoCancel", this.f11662x);
        jSONObject2.put("visibility", this.f11663y);
        jSONObject2.put("when", this.f11664z);
        return jSONObject2;
    }

    /* renamed from: b */
    private JSONObject m14239b(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("cmd", this.f11645g);
        jSONObject2.put("content", this.f11646h);
        jSONObject2.put("notifyIcon", this.f11647i);
        jSONObject2.put("notifyTitle", this.f11648j);
        jSONObject2.put("notifySummary", this.f11649k);
        jSONObject2.put("param", jSONObject);
        return jSONObject2;
    }

    /* renamed from: c */
    private void m14237c(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("ap")) {
            String string = jSONObject.getString("ap");
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(string) && string.length() < 48) {
                int length = 48 - string.length();
                for (int i = 0; i < length; i++) {
                    sb.append("0");
                }
                sb.append(string);
                this.f11642d = sb.toString();
                return;
            }
            this.f11642d = string.substring(0, 48);
        }
    }

    /* renamed from: d */
    private boolean m14235d(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return false;
        }
        if (jSONObject.has("acn")) {
            this.f11651m = jSONObject.getString("acn");
        }
        if (jSONObject.has("intentUri")) {
            this.f11641c = jSONObject.getString("intentUri");
        }
        if (jSONObject.has("appPackageName")) {
            this.f11650l = jSONObject.getString("appPackageName");
            return true;
        }
        HMSLog.m14115d("PushSelfShowLog", "appPackageName is null");
        return false;
    }

    /* renamed from: e */
    private boolean m14233e(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("msgId")) {
            Object obj = jSONObject.get("msgId");
            if (obj instanceof String) {
                this.f11643e = (String) obj;
                return true;
            } else if (obj instanceof Integer) {
                this.f11643e = String.valueOf(((Integer) obj).intValue());
                return true;
            } else {
                return true;
            }
        }
        HMSLog.m14110i("PushSelfShowLog", "msgId == null");
        return false;
    }

    /* renamed from: f */
    private boolean m14231f(JSONObject jSONObject) {
        HMSLog.m14115d("PushSelfShowLog", "enter parseNotifyParam");
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("notifyDetail");
            if (jSONObject2.has("style")) {
                this.f11658t = jSONObject2.getInt("style");
            }
            this.f11659u = jSONObject2.optString("bigTitle");
            this.f11660v = jSONObject2.optString("bigContent");
            this.f11637E = jSONObject2.optString("icon");
            return true;
        } catch (JSONException e) {
            HMSLog.m14110i("PushSelfShowLog", e.toString());
            return false;
        }
    }

    /* renamed from: g */
    private void m14229g(JSONObject jSONObject) {
        this.f11639a = jSONObject.optString("group");
        HMSLog.m14115d("PushSelfShowLog", "NOTIFY_GROUP:" + this.f11639a);
        this.f11662x = jSONObject.optInt("autoCancel", 1);
        HMSLog.m14115d("PushSelfShowLog", "autoCancel: " + this.f11662x);
        this.f11663y = jSONObject.optInt("visibility", 0);
        this.f11664z = jSONObject.optString("when");
        this.f11633A = jSONObject.optString("tag");
    }

    /* renamed from: h */
    private boolean m14227h(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("param");
            if (jSONObject2.has("autoClear")) {
                this.f11640b = jSONObject2.getInt("autoClear");
            } else {
                this.f11640b = 0;
            }
            if (!"app".equals(this.f11645g) && !"cosa".equals(this.f11645g)) {
                if ("url".equals(this.f11645g)) {
                    m14221k(jSONObject2);
                    return true;
                } else if ("rp".equals(this.f11645g)) {
                    m14223j(jSONObject2);
                    return true;
                } else {
                    return true;
                }
            }
            m14235d(jSONObject2);
            return true;
        } catch (Exception e) {
            HMSLog.m14111e("PushSelfShowLog", "ParseParam error ", e);
            return false;
        }
    }

    /* renamed from: i */
    private boolean m14225i(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("psContent")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("psContent");
            this.f11645g = jSONObject2.getString("cmd");
            this.f11646h = jSONObject2.optString("content");
            this.f11647i = jSONObject2.optString("notifyIcon");
            this.f11648j = jSONObject2.optString("notifyTitle");
            this.f11649k = jSONObject2.optString("notifySummary");
            this.f11636D = jSONObject2.optString("ticker");
            if ((!jSONObject2.has("notifyDetail") || m14231f(jSONObject2)) && jSONObject2.has("param")) {
                return m14227h(jSONObject2);
            }
        }
        return false;
    }

    /* renamed from: j */
    private boolean m14223j(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return false;
        }
        if (jSONObject.has("appPackageName")) {
            this.f11650l = jSONObject.getString("appPackageName");
        }
        if (jSONObject.has("rpt") && jSONObject.has("rpl")) {
            this.f11653o = jSONObject.getString("rpl");
            this.f11654p = jSONObject.getString("rpt");
            if (jSONObject.has("rpct")) {
                this.f11655q = jSONObject.getString("rpct");
                return true;
            }
            return true;
        }
        HMSLog.m14115d("PushSelfShowLog", "rpl or rpt is null");
        return false;
    }

    /* renamed from: k */
    private boolean m14221k(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return false;
        }
        if (jSONObject.has("url")) {
            this.f11652n = jSONObject.getString("url");
            if (jSONObject.has("appPackageName")) {
                this.f11650l = jSONObject.getString("appPackageName");
            }
            if (jSONObject.has("rpt") && jSONObject.has("rpl")) {
                this.f11653o = jSONObject.getString("rpl");
                this.f11654p = jSONObject.getString("rpt");
                if (jSONObject.has("rpct")) {
                    this.f11655q = jSONObject.getString("rpct");
                    return true;
                }
                return true;
            }
            return true;
        }
        HMSLog.m14115d("PushSelfShowLog", "url is null");
        return false;
    }

    /* renamed from: r */
    private JSONObject m14214r() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("style", this.f11658t);
        jSONObject.put("bigTitle", this.f11659u);
        jSONObject.put("bigContent", this.f11660v);
        jSONObject.put("bigPic", this.f11661w);
        return jSONObject;
    }

    /* renamed from: v */
    private JSONObject m14210v() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("autoClear", this.f11640b);
        jSONObject.put("url", this.f11652n);
        jSONObject.put("rpl", this.f11653o);
        jSONObject.put("rpt", this.f11654p);
        jSONObject.put("rpct", this.f11655q);
        jSONObject.put("appPackageName", this.f11650l);
        jSONObject.put("acn", this.f11651m);
        jSONObject.put("intentUri", this.f11641c);
        return jSONObject;
    }

    /* renamed from: l */
    public String m14220l() {
        return this.f11639a;
    }

    /* renamed from: m */
    public String m14219m() {
        return this.f11637E;
    }

    /* renamed from: n */
    public String m14218n() {
        return this.f11641c;
    }

    /* renamed from: o */
    public byte[] m14217o() {
        try {
            return m14242a(m14241a(m14239b(m14210v()), m14214r())).toString().getBytes(PushConst.f11631a);
        } catch (JSONException e) {
            HMSLog.m14111e("PushSelfShowLog", "getMsgData failed JSONException:", e);
            return new byte[0];
        }
    }

    /* renamed from: p */
    public String m14216p() {
        HMSLog.m14115d("PushSelfShowLog", "msgId =" + this.f11643e);
        return this.f11643e;
    }

    /* renamed from: q */
    public String m14215q() {
        return this.f11633A;
    }

    /* renamed from: s */
    public int m14213s() {
        return this.f11634B;
    }

    /* renamed from: t */
    public String m14212t() {
        return this.f11649k;
    }

    /* renamed from: u */
    public String m14211u() {
        return this.f11648j;
    }

    /* renamed from: w */
    public int m14209w() {
        return this.f11658t;
    }

    /* renamed from: x */
    public String m14208x() {
        return this.f11636D;
    }

    /* renamed from: y */
    public byte[] m14207y() {
        return this.f11657s.getBytes(PushConst.f11631a);
    }

    /* renamed from: z */
    public boolean m14206z() {
        try {
            if (TextUtils.isEmpty(this.f11656r)) {
                HMSLog.m14115d("PushSelfShowLog", "msg is null");
                return false;
            }
            JSONObject jSONObject = new JSONObject(this.f11656r);
            m14229g(jSONObject);
            JSONObject jSONObject2 = jSONObject.getJSONObject("msgContent");
            if (m14233e(jSONObject2)) {
                this.f11644f = jSONObject2.optString("dispPkgName");
                m14237c(jSONObject2);
                this.f11634B = jSONObject2.optInt("notifyId", -1);
                this.f11635C = jSONObject2.optString("data");
                this.f11638F = jSONObject2.optString("analyticInfo");
                return m14225i(jSONObject2);
            }
            return false;
        } catch (JSONException unused) {
            HMSLog.m14115d("PushSelfShowLog", "parse message exception.");
            return false;
        } catch (Exception e) {
            HMSLog.m14115d("PushSelfShowLog", e.toString());
            return false;
        }
    }

    /* renamed from: a */
    private JSONObject m14241a(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("dispPkgName", this.f11644f);
        jSONObject3.put("msgId", this.f11643e);
        jSONObject3.put("ap", this.f11642d);
        jSONObject3.put("notifyId", this.f11634B);
        jSONObject3.put("psContent", jSONObject);
        jSONObject3.put("notifyDetail", jSONObject2);
        jSONObject3.put("ticker", this.f11636D);
        jSONObject3.put("data", this.f11635C);
        return jSONObject3;
    }

    /* renamed from: b */
    public String m14240b() {
        return this.f11638F;
    }

    /* renamed from: d */
    public String m14236d() {
        return this.f11650l;
    }

    /* renamed from: e */
    public int m14234e() {
        return this.f11662x;
    }

    /* renamed from: c */
    public String m14238c() {
        return this.f11642d;
    }

    /* renamed from: j */
    public String m14224j() {
        return this.f11646h;
    }

    /* renamed from: g */
    public String m14230g() {
        return this.f11660v;
    }

    /* renamed from: a */
    public String m14244a() {
        return this.f11651m;
    }

    /* renamed from: f */
    public int m14232f() {
        return this.f11640b;
    }

    /* renamed from: a */
    public void m14243a(int i) {
        this.f11634B = i;
    }

    /* renamed from: k */
    public String m14222k() {
        return this.f11644f;
    }

    /* renamed from: h */
    public String m14228h() {
        return this.f11659u;
    }

    /* renamed from: i */
    public String m14226i() {
        return this.f11645g;
    }
}
