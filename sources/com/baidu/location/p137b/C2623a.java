package com.baidu.location.p137b;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.baidu.lbsapi.auth.LBSAuthManager;
import com.baidu.location.Jni;
import com.baidu.location.LocationClientOption;
import com.baidu.location.p140e.AbstractC2729f;
import com.baidu.location.p140e.C2725d;
import com.baidu.location.p140e.C2735k;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.b.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2623a {

    /* renamed from: q */
    private static String f5123q = "BDLocConfigManager";

    /* renamed from: a */
    public boolean f5124a;

    /* renamed from: b */
    public int f5125b;

    /* renamed from: c */
    public double f5126c;

    /* renamed from: d */
    public int f5127d;

    /* renamed from: e */
    public int f5128e;

    /* renamed from: f */
    public double f5129f;

    /* renamed from: g */
    public int f5130g;

    /* renamed from: h */
    public int f5131h;

    /* renamed from: i */
    public int f5132i;

    /* renamed from: j */
    public int f5133j;

    /* renamed from: k */
    public int f5134k;

    /* renamed from: l */
    public int f5135l;

    /* renamed from: m */
    public double[] f5136m;

    /* renamed from: n */
    public int f5137n;

    /* renamed from: o */
    public int f5138o;

    /* renamed from: p */
    public int f5139p;

    /* renamed from: r */
    private SharedPreferences f5140r;

    /* renamed from: s */
    private long f5141s;

    /* renamed from: t */
    private String f5142t;

    /* renamed from: u */
    private C2625a f5143u;

    /* renamed from: v */
    private boolean f5144v;

    /* renamed from: w */
    private String f5145w;

    /* renamed from: x */
    private String f5146x;

    /* renamed from: y */
    private String f5147y;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.a$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2625a extends AbstractC2729f {

        /* renamed from: a */
        String f5148a = null;

        /* renamed from: b */
        boolean f5149b = false;

        public C2625a() {
            this.f5727j = new HashMap();
        }

        @Override // com.baidu.location.p140e.AbstractC2729f
        /* renamed from: a */
        public void mo19077a() {
            this.f5725h = 2;
            String encode = Jni.encode(this.f5148a);
            this.f5148a = null;
            this.f5727j.put("qt", "conf");
            this.f5727j.put("req", encode);
        }

        /* renamed from: a */
        public void m19561a(String str) {
            if (this.f5149b) {
                return;
            }
            this.f5149b = true;
            this.f5148a = str;
            m19073b(C2725d.f5706d);
        }

        @Override // com.baidu.location.p140e.AbstractC2729f
        /* renamed from: a */
        public void mo19074a(boolean z) {
            if (z && this.f5726i != null) {
                try {
                    new JSONObject(this.f5726i);
                    if (C2623a.this.f5140r != null) {
                        SharedPreferences.Editor edit = C2623a.this.f5140r.edit();
                        String encodeToString = Base64.encodeToString(C2735k.m19059a(this.f5726i.getBytes()), 0);
                        edit.putString(C2623a.f5123q + "_newConfig", encodeToString);
                        edit.apply();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (this.f5727j != null) {
                this.f5727j.clear();
            }
            this.f5149b = false;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.a$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class C2626b {

        /* renamed from: a */
        public static final C2623a f5151a = new C2623a();
    }

    private C2623a() {
        this.f5140r = null;
        this.f5124a = false;
        this.f5125b = 16;
        this.f5141s = 300L;
        this.f5126c = 0.75d;
        this.f5127d = 0;
        this.f5128e = 1;
        this.f5129f = -0.10000000149011612d;
        this.f5130g = 0;
        this.f5131h = 1;
        this.f5132i = 1;
        this.f5133j = 10;
        this.f5134k = 3;
        this.f5135l = 40;
        this.f5137n = 1;
        this.f5138o = 0;
        this.f5139p = 1;
        this.f5142t = null;
        this.f5143u = null;
        this.f5144v = false;
        this.f5145w = null;
        this.f5146x = null;
        this.f5147y = null;
    }

    /* renamed from: a */
    public static C2623a m19570a() {
        return C2626b.f5151a;
    }

    /* renamed from: a */
    private void m19567a(LocationClientOption locationClientOption) {
        String string;
        String str = "&ver=" + C2735k.f5832x + "&usr=" + m19562c() + "&app=" + this.f5145w + "&prod=" + locationClientOption.prodName + "&newwf=1";
        String str2 = Build.VERSION.RELEASE;
        if (str2 != null && str2.length() > 6) {
            str2 = str2.substring(0, 6);
        }
        String str3 = str + "&sv=" + str2;
        String m19049c = C2735k.m19049c("ro.miui.ui.version.name");
        if (!TextUtils.isEmpty(m19049c)) {
            str3 = str3 + "&miui=" + m19049c;
        }
        String m19038j = C2735k.m19038j();
        if (!TextUtils.isEmpty(m19038j)) {
            str3 = str3 + "&mtk=" + m19038j;
        }
        if (!TextUtils.isEmpty(this.f5140r.getString(f5123q + "_loc", null))) {
            try {
                str3 = str3 + "&loc=" + new String(Base64.decode(string, 0), "UTF-8");
            } catch (Exception unused) {
            }
        }
        if (this.f5143u == null) {
            this.f5143u = new C2625a();
        }
        this.f5143u.m19561a(str3);
    }

    /* renamed from: a */
    private void m19565a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("is_check_Per") && jSONObject.getInt("is_check_Per") > 0) {
                this.f5124a = true;
            }
            if (jSONObject.has("wfnum")) {
                this.f5125b = jSONObject.getInt("wfnum");
            }
            if (jSONObject.has("freq")) {
                this.f5141s = jSONObject.getLong("freq");
            }
            if (jSONObject.has("wfsm")) {
                this.f5126c = jSONObject.getDouble("wfsm");
            }
            if (jSONObject.has("idmoc")) {
                this.f5127d = jSONObject.getInt("idmoc");
            }
            if (jSONObject.has("gnmcrm")) {
                this.f5129f = jSONObject.getDouble("gnmcrm");
            }
            if (jSONObject.has("gnmcon")) {
                this.f5130g = jSONObject.getInt("gnmcon");
            }
            if (jSONObject.has("lpcs")) {
                this.f5128e = jSONObject.getInt("lpcs");
            }
            if (jSONObject.has("iupl")) {
                this.f5131h = jSONObject.getInt("iupl");
            }
            if (jSONObject.has("opetco")) {
                this.f5132i = jSONObject.getInt("opetco");
            }
            if (jSONObject.has("ct")) {
                this.f5133j = jSONObject.getInt("ct");
            }
            if (jSONObject.has("suci")) {
                this.f5134k = jSONObject.getInt("suci");
            }
            if (jSONObject.has("smn")) {
                this.f5135l = jSONObject.getInt("smn");
            }
            if (jSONObject.has("bcar")) {
                m19564a(jSONObject);
            }
            if (jSONObject.has("ums")) {
                this.f5137n = jSONObject.getInt("ums");
            }
            if (jSONObject.has("hpdts")) {
                this.f5138o = jSONObject.getInt("hpdts");
            }
            if (jSONObject.has("oldts")) {
                this.f5139p = jSONObject.getInt("oldts");
            }
            this.f5142t = str;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: c */
    private String m19562c() {
        return "v9.333|" + this.f5146x + "|" + Build.MODEL + "&cu=" + this.f5146x + "&mb=" + Build.MODEL;
    }

    /* renamed from: a */
    public synchronized void m19569a(double d, double d2, String str) {
        if (this.f5147y == null && str != null) {
            try {
                if (str.equals("bd09") || str.equals("wgs84mc")) {
                    double[] coorEncrypt = Jni.coorEncrypt(d2, d, "bd092gcj");
                    double d3 = coorEncrypt[1];
                    double d4 = coorEncrypt[0];
                    d = d3;
                    d2 = d4;
                }
                this.f5147y = String.format(Locale.US, "%.5f|%.5f", Double.valueOf(d2), Double.valueOf(d));
                String encodeToString = Base64.encodeToString(this.f5147y.getBytes("UTF-8"), 0);
                if (encodeToString != null && this.f5140r != null) {
                    SharedPreferences.Editor edit = this.f5140r.edit();
                    edit.putString(f5123q + "_loc", encodeToString);
                    edit.apply();
                }
            } catch (Exception unused) {
                this.f5147y = null;
            }
        }
    }

    /* renamed from: a */
    public synchronized void m19568a(Context context, LocationClientOption locationClientOption, String str) {
        if (!this.f5144v && context != null) {
            this.f5144v = true;
            if (locationClientOption == null) {
                locationClientOption = new LocationClientOption();
            }
            this.f5145w = context.getPackageName();
            this.f5146x = LBSAuthManager.getInstance(context).getCUID();
            if (this.f5140r == null) {
                this.f5140r = context.getSharedPreferences(f5123q + "BDLocConfig", 0);
            }
            if (this.f5140r != null) {
                SharedPreferences sharedPreferences = this.f5140r;
                long j = sharedPreferences.getLong(f5123q + "_lastCheckTime", 0L);
                SharedPreferences sharedPreferences2 = this.f5140r;
                String string = sharedPreferences2.getString(f5123q + "_config", "");
                SharedPreferences sharedPreferences3 = this.f5140r;
                String string2 = sharedPreferences3.getString(f5123q + "_newConfig", "");
                if (!TextUtils.isEmpty(string2)) {
                    m19565a(new String(C2735k.m19052b(Base64.decode(string2, 0))));
                } else if (!TextUtils.isEmpty(string)) {
                    m19565a(string);
                    SharedPreferences.Editor edit = this.f5140r.edit();
                    edit.remove(f5123q + "_config");
                    edit.apply();
                }
                if (Math.abs((System.currentTimeMillis() / 1000) - j) > this.f5141s) {
                    SharedPreferences.Editor edit2 = this.f5140r.edit();
                    edit2.putLong(f5123q + "_lastCheckTime", System.currentTimeMillis() / 1000);
                    edit2.apply();
                    m19567a(locationClientOption);
                }
            }
        }
    }

    /* renamed from: a */
    public void m19564a(JSONObject jSONObject) {
        JSONArray jSONArray;
        if (jSONObject != null) {
            double[] dArr = this.f5136m;
            if (dArr != null && dArr.length > 0) {
                this.f5136m = null;
            }
            try {
                if (!jSONObject.has("bcar") || (jSONArray = jSONObject.getJSONArray("bcar")) == null || jSONArray.length() <= 0) {
                    return;
                }
                if (this.f5136m == null) {
                    this.f5136m = new double[jSONArray.length() * 4];
                }
                int i = 0;
                int i2 = 0;
                while (i < jSONArray.length()) {
                    int i3 = i2 + 1;
                    this.f5136m[i2] = jSONArray.getJSONObject(i).getDouble("x1");
                    int i4 = i3 + 1;
                    this.f5136m[i3] = jSONArray.getJSONObject(i).getDouble("y1");
                    int i5 = i4 + 1;
                    this.f5136m[i4] = jSONArray.getJSONObject(i).getDouble("x2");
                    int i6 = i5 + 1;
                    this.f5136m[i5] = jSONArray.getJSONObject(i).getDouble("y2");
                    i++;
                    i2 = i6;
                }
            } catch (Exception unused) {
            }
        }
    }
}
