package com.baidu.mapsdkplatform.comapi.favrite;

import android.os.Bundle;
import android.text.TextUtils;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.platform.comapi.basestruct.Point;
import com.baidu.platform.comjni.map.favorite.NAFavorite;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.mapsdkplatform.comapi.favrite.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2906a {

    /* renamed from: b */
    private static C2906a f7192b;

    /* renamed from: a */
    private NAFavorite f7193a = null;

    /* renamed from: c */
    private boolean f7194c = false;

    /* renamed from: d */
    private boolean f7195d = false;

    /* renamed from: e */
    private Vector<String> f7196e = null;

    /* renamed from: f */
    private Vector<String> f7197f = null;

    /* renamed from: g */
    private boolean f7198g = false;

    /* renamed from: h */
    private C2909c f7199h = new C2909c();

    /* renamed from: i */
    private C2908b f7200i = new C2908b();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapsdkplatform.comapi.favrite.a$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2907a implements Comparator<String> {
        C2907a() {
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(String str, String str2) {
            return str2.compareTo(str);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapsdkplatform.comapi.favrite.a$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C2908b {

        /* renamed from: b */
        private long f7203b;

        /* renamed from: c */
        private long f7204c;

        private C2908b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m18434a() {
            this.f7203b = System.currentTimeMillis();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public void m18432b() {
            this.f7204c = System.currentTimeMillis();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public boolean m18430c() {
            return this.f7204c - this.f7203b > 1000;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapsdkplatform.comapi.favrite.a$c */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C2909c {

        /* renamed from: b */
        private String f7206b;

        /* renamed from: c */
        private long f7207c;

        /* renamed from: d */
        private long f7208d;

        private C2909c() {
            this.f7207c = 5000L;
            this.f7208d = 0L;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public String m18428a() {
            return this.f7206b;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m18425a(String str) {
            this.f7206b = str;
            this.f7208d = System.currentTimeMillis();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public boolean m18424b() {
            return TextUtils.isEmpty(this.f7206b);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public boolean m18422c() {
            return true;
        }
    }

    private C2906a() {
    }

    /* renamed from: a */
    public static C2906a m18450a() {
        if (f7192b == null) {
            synchronized (C2906a.class) {
                if (f7192b == null) {
                    f7192b = new C2906a();
                    f7192b.m18438h();
                }
            }
        }
        return f7192b;
    }

    /* renamed from: g */
    public static boolean m18439g() {
        NAFavorite nAFavorite;
        C2906a c2906a = f7192b;
        return (c2906a == null || (nAFavorite = c2906a.f7193a) == null || !nAFavorite.m17632d()) ? false : true;
    }

    /* renamed from: h */
    private boolean m18438h() {
        if (this.f7193a == null) {
            this.f7193a = new NAFavorite();
            if (this.f7193a.m17643a() == 0) {
                this.f7193a = null;
                return false;
            }
            m18436j();
            m18437i();
        }
        return true;
    }

    /* renamed from: i */
    private boolean m18437i() {
        if (this.f7193a == null) {
            return false;
        }
        String str = SysOSUtil.getModuleFileName() + "/";
        this.f7193a.m17642a(1);
        return this.f7193a.m17638a(str, "fav_poi", "fifo", 10, 501, -1);
    }

    /* renamed from: j */
    private void m18436j() {
        this.f7194c = false;
        this.f7195d = false;
    }

    /* renamed from: a */
    public synchronized int m18448a(String str, FavSyncPoi favSyncPoi) {
        if (this.f7193a == null) {
            return 0;
        }
        if (str != null && !str.equals("") && favSyncPoi != null) {
            m18436j();
            ArrayList<String> m18441e = m18441e();
            if ((m18441e != null ? m18441e.size() : 0) + 1 > 500) {
                return -2;
            }
            if (m18441e != null && m18441e.size() > 0) {
                Iterator<String> it = m18441e.iterator();
                while (it.hasNext()) {
                    FavSyncPoi m18446b = m18446b(it.next());
                    if (m18446b != null && str.equals(m18446b.f7183b)) {
                        return -1;
                    }
                }
            }
            try {
                JSONObject jSONObject = new JSONObject();
                favSyncPoi.f7183b = str;
                String valueOf = String.valueOf(System.currentTimeMillis());
                String str2 = valueOf + "_" + favSyncPoi.hashCode();
                favSyncPoi.f7189h = valueOf;
                favSyncPoi.f7182a = str2;
                jSONObject.put("bdetail", favSyncPoi.f7190i);
                jSONObject.put("uspoiname", favSyncPoi.f7183b);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("x", favSyncPoi.f7184c.getDoubleX());
                jSONObject2.put("y", favSyncPoi.f7184c.getDoubleY());
                jSONObject.put("pt", jSONObject2);
                jSONObject.put("ncityid", favSyncPoi.f7186e);
                jSONObject.put("npoitype", favSyncPoi.f7188g);
                jSONObject.put("uspoiuid", favSyncPoi.f7187f);
                jSONObject.put("addr", favSyncPoi.f7185d);
                jSONObject.put("addtimesec", favSyncPoi.f7189h);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("Fav_Sync", jSONObject);
                jSONObject3.put("Fav_Content", favSyncPoi.f7191j);
                if (this.f7193a.m17639a(str2, !(jSONObject3 instanceof JSONObject) ? jSONObject3.toString() : NBSJSONObjectInstrumentation.toString(jSONObject3))) {
                    m18436j();
                    return 1;
                }
                return 0;
            } catch (JSONException unused) {
                return 0;
            } finally {
                m18439g();
            }
        }
        return -1;
    }

    /* renamed from: a */
    public synchronized boolean m18449a(String str) {
        if (this.f7193a == null) {
            return false;
        }
        if (str != null && !str.equals("")) {
            if (m18443c(str)) {
                m18436j();
                return this.f7193a.m17640a(str);
            }
            return false;
        }
        return false;
    }

    /* renamed from: b */
    public FavSyncPoi m18446b(String str) {
        if (this.f7193a != null && str != null && !str.equals("")) {
            try {
                if (m18443c(str)) {
                    FavSyncPoi favSyncPoi = new FavSyncPoi();
                    String m17636b = this.f7193a.m17636b(str);
                    if (m17636b != null && !m17636b.equals("")) {
                        JSONObject jSONObject = new JSONObject(m17636b);
                        JSONObject optJSONObject = jSONObject.optJSONObject("Fav_Sync");
                        String optString = jSONObject.optString("Fav_Content");
                        favSyncPoi.f7183b = optJSONObject.optString("uspoiname");
                        JSONObject optJSONObject2 = optJSONObject.optJSONObject("pt");
                        favSyncPoi.f7184c = new Point(optJSONObject2.optInt("x"), optJSONObject2.optInt("y"));
                        favSyncPoi.f7186e = optJSONObject.optString("ncityid");
                        favSyncPoi.f7187f = optJSONObject.optString("uspoiuid");
                        favSyncPoi.f7188g = optJSONObject.optInt("npoitype");
                        favSyncPoi.f7185d = optJSONObject.optString("addr");
                        favSyncPoi.f7189h = optJSONObject.optString("addtimesec");
                        favSyncPoi.f7190i = optJSONObject.optBoolean("bdetail");
                        favSyncPoi.f7191j = optString;
                        favSyncPoi.f7182a = str;
                        return favSyncPoi;
                    }
                    return null;
                }
                return null;
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (JSONException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /* renamed from: b */
    public void m18447b() {
        C2906a c2906a = f7192b;
        if (c2906a != null) {
            NAFavorite nAFavorite = c2906a.f7193a;
            if (nAFavorite != null) {
                nAFavorite.m17637b();
                f7192b.f7193a = null;
            }
            f7192b = null;
        }
    }

    /* renamed from: b */
    public synchronized boolean m18445b(String str, FavSyncPoi favSyncPoi) {
        boolean z = false;
        if (this.f7193a != null && str != null && !str.equals("") && favSyncPoi != null) {
            if (m18443c(str)) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("uspoiname", favSyncPoi.f7183b);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("x", favSyncPoi.f7184c.getDoubleX());
                    jSONObject2.put("y", favSyncPoi.f7184c.getDoubleY());
                    jSONObject.put("pt", jSONObject2);
                    jSONObject.put("ncityid", favSyncPoi.f7186e);
                    jSONObject.put("npoitype", favSyncPoi.f7188g);
                    jSONObject.put("uspoiuid", favSyncPoi.f7187f);
                    jSONObject.put("addr", favSyncPoi.f7185d);
                    favSyncPoi.f7189h = String.valueOf(System.currentTimeMillis());
                    jSONObject.put("addtimesec", favSyncPoi.f7189h);
                    jSONObject.put("bdetail", false);
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("Fav_Sync", jSONObject);
                    jSONObject3.put("Fav_Content", favSyncPoi.f7191j);
                    m18436j();
                    if (this.f7193a != null) {
                        if (this.f7193a.m17635b(str, !(jSONObject3 instanceof JSONObject) ? jSONObject3.toString() : NBSJSONObjectInstrumentation.toString(jSONObject3))) {
                            z = true;
                        }
                    }
                    return z;
                } catch (JSONException unused) {
                    return false;
                }
            }
            return false;
        }
        return false;
    }

    /* renamed from: c */
    public synchronized boolean m18444c() {
        if (this.f7193a == null) {
            return false;
        }
        m18436j();
        boolean m17634c = this.f7193a.m17634c();
        m18439g();
        return m17634c;
    }

    /* renamed from: c */
    public boolean m18443c(String str) {
        return (this.f7193a == null || str == null || str.equals("") || !this.f7193a.m17633c(str)) ? false : true;
    }

    /* renamed from: d */
    public ArrayList<String> m18442d() {
        String m17636b;
        Vector<String> vector;
        if (this.f7193a == null) {
            return null;
        }
        if (!this.f7195d || (vector = this.f7197f) == null) {
            try {
                Bundle bundle = new Bundle();
                this.f7193a.m17641a(bundle);
                String[] stringArray = bundle.getStringArray("rstString");
                if (stringArray != null) {
                    if (this.f7197f == null) {
                        this.f7197f = new Vector<>();
                    } else {
                        this.f7197f.clear();
                    }
                    for (int i = 0; i < stringArray.length; i++) {
                        if (!stringArray[i].equals("data_version") && (m17636b = this.f7193a.m17636b(stringArray[i])) != null && !m17636b.equals("")) {
                            this.f7197f.add(stringArray[i]);
                        }
                    }
                    if (this.f7197f.size() > 0) {
                        try {
                            Collections.sort(this.f7197f, new C2907a());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        this.f7195d = true;
                    }
                } else if (this.f7197f != null) {
                    this.f7197f.clear();
                    this.f7197f = null;
                }
                if (this.f7197f != null && !this.f7197f.isEmpty()) {
                    return new ArrayList<>(this.f7197f);
                }
                return null;
            } catch (Exception unused) {
                return null;
            }
        }
        return new ArrayList<>(vector);
    }

    /* renamed from: e */
    public ArrayList<String> m18441e() {
        Vector<String> vector;
        if (this.f7193a == null) {
            return null;
        }
        if (!this.f7194c || (vector = this.f7196e) == null) {
            try {
                Bundle bundle = new Bundle();
                this.f7193a.m17641a(bundle);
                String[] stringArray = bundle.getStringArray("rstString");
                if (stringArray != null) {
                    if (this.f7196e == null) {
                        this.f7196e = new Vector<>();
                    } else {
                        this.f7196e.clear();
                    }
                    for (String str : stringArray) {
                        if (!str.equals("data_version")) {
                            this.f7196e.add(str);
                        }
                    }
                    if (this.f7196e.size() > 0) {
                        try {
                            Collections.sort(this.f7196e, new C2907a());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        this.f7194c = true;
                    }
                } else if (this.f7196e != null) {
                    this.f7196e.clear();
                    this.f7196e = null;
                }
                Vector<String> vector2 = this.f7196e;
                if (vector2 == null || vector2.size() == 0) {
                    return null;
                }
                return new ArrayList<>(this.f7196e);
            } catch (Exception unused) {
                return null;
            }
        }
        return new ArrayList<>(vector);
    }

    /* renamed from: f */
    public String m18440f() {
        String m17636b;
        if (!this.f7200i.m18430c() || this.f7199h.m18422c() || this.f7199h.m18424b()) {
            this.f7200i.m18434a();
            if (this.f7193a == null) {
                return null;
            }
            ArrayList<String> m18442d = m18442d();
            JSONObject jSONObject = new JSONObject();
            if (m18442d != null) {
                try {
                    JSONArray jSONArray = new JSONArray();
                    int i = 0;
                    Iterator<String> it = m18442d.iterator();
                    while (it.hasNext()) {
                        String next = it.next();
                        if (next != null && !next.equals("data_version") && (m17636b = this.f7193a.m17636b(next)) != null && !m17636b.equals("")) {
                            JSONObject optJSONObject = new JSONObject(m17636b).optJSONObject("Fav_Sync");
                            optJSONObject.put("key", next);
                            jSONArray.put(i, optJSONObject);
                            i++;
                        }
                    }
                    if (i > 0) {
                        jSONObject.put("favcontents", jSONArray);
                        jSONObject.put("favpoinum", i);
                    }
                } catch (JSONException unused) {
                    return null;
                }
            }
            this.f7200i.m18432b();
            this.f7199h.m18425a(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            return this.f7199h.m18428a();
        }
        return this.f7199h.m18428a();
    }
}
