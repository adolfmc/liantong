package com.xiaomi.push;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: com.xiaomi.push.fo */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC11375fo {

    /* renamed from: b */
    private static long f22322b;

    /* renamed from: c */
    private static String f22324c;

    /* renamed from: a */
    public long f22325a;

    /* renamed from: a */
    private C11381fs f22326a;

    /* renamed from: a */
    private List<C11371fl> f22327a;

    /* renamed from: a */
    private final Map<String, Object> f22328a;

    /* renamed from: d */
    private String f22329d;

    /* renamed from: e */
    private String f22330e;

    /* renamed from: f */
    private String f22331f;

    /* renamed from: g */
    private String f22332g;

    /* renamed from: h */
    private String f22333h;

    /* renamed from: i */
    private String f22334i;

    /* renamed from: a */
    protected static final String f22320a = Locale.getDefault().getLanguage().toLowerCase();

    /* renamed from: b */
    private static String f22323b = null;

    /* renamed from: a */
    public static final DateFormat f22321a = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    /* renamed from: a */
    public abstract String mo3775a();

    static {
        f22321a.setTimeZone(TimeZone.getTimeZone("UTC"));
        f22324c = C11389fx.m3749a(5) + "-";
        f22322b = 0L;
    }

    /* renamed from: i */
    public static synchronized String m3791i() {
        String sb;
        synchronized (AbstractC11375fo.class) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(f22324c);
            long j = f22322b;
            f22322b = 1 + j;
            sb2.append(Long.toString(j));
            sb = sb2.toString();
        }
        return sb;
    }

    public AbstractC11375fo() {
        this.f22329d = f22323b;
        this.f22330e = null;
        this.f22331f = null;
        this.f22332g = null;
        this.f22333h = null;
        this.f22334i = null;
        this.f22327a = new CopyOnWriteArrayList();
        this.f22328a = new HashMap();
        this.f22326a = null;
    }

    public AbstractC11375fo(Bundle bundle) {
        this.f22329d = f22323b;
        this.f22330e = null;
        this.f22331f = null;
        this.f22332g = null;
        this.f22333h = null;
        this.f22334i = null;
        this.f22327a = new CopyOnWriteArrayList();
        this.f22328a = new HashMap();
        this.f22326a = null;
        this.f22331f = bundle.getString("ext_to");
        this.f22332g = bundle.getString("ext_from");
        this.f22333h = bundle.getString("ext_chid");
        this.f22330e = bundle.getString("ext_pkt_id");
        Parcelable[] parcelableArray = bundle.getParcelableArray("ext_exts");
        if (parcelableArray != null) {
            this.f22327a = new ArrayList(parcelableArray.length);
            for (Parcelable parcelable : parcelableArray) {
                C11371fl m3831a = C11371fl.m3831a((Bundle) parcelable);
                if (m3831a != null) {
                    this.f22327a.add(m3831a);
                }
            }
        }
        Bundle bundle2 = bundle.getBundle("ext_ERROR");
        if (bundle2 != null) {
            this.f22326a = new C11381fs(bundle2);
        }
    }

    /* renamed from: j */
    public String m3790j() {
        if ("ID_NOT_AVAILABLE".equals(this.f22330e)) {
            return null;
        }
        if (this.f22330e == null) {
            this.f22330e = m3791i();
        }
        return this.f22330e;
    }

    /* renamed from: k */
    public void m3788k(String str) {
        this.f22330e = str;
    }

    /* renamed from: k */
    public String m3789k() {
        return this.f22333h;
    }

    /* renamed from: l */
    public void m3786l(String str) {
        this.f22333h = str;
    }

    /* renamed from: l */
    public String m3787l() {
        return this.f22331f;
    }

    /* renamed from: m */
    public void m3784m(String str) {
        this.f22331f = str;
    }

    /* renamed from: m */
    public String m3785m() {
        return this.f22332g;
    }

    /* renamed from: n */
    public void m3782n(String str) {
        this.f22332g = str;
    }

    /* renamed from: n */
    public String m3783n() {
        return this.f22334i;
    }

    /* renamed from: o */
    public void m3780o(String str) {
        this.f22334i = str;
    }

    /* renamed from: a */
    public C11381fs m3799a() {
        return this.f22326a;
    }

    /* renamed from: a */
    public void m3796a(C11381fs c11381fs) {
        this.f22326a = c11381fs;
    }

    /* renamed from: a */
    public synchronized Collection<C11371fl> m3798a() {
        if (this.f22327a == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(new ArrayList(this.f22327a));
    }

    /* renamed from: a */
    public C11371fl m3795a(String str) {
        return m3793a(str, null);
    }

    /* renamed from: a */
    public C11371fl m3793a(String str, String str2) {
        for (C11371fl c11371fl : this.f22327a) {
            if (str2 == null || str2.equals(c11371fl.m3825b())) {
                if (str.equals(c11371fl.m3832a())) {
                    return c11371fl;
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public void m3797a(C11371fl c11371fl) {
        this.f22327a.add(c11371fl);
    }

    /* renamed from: a */
    public synchronized Object m3794a(String str) {
        if (this.f22328a == null) {
            return null;
        }
        return this.f22328a.get(str);
    }

    /* renamed from: b */
    public synchronized Collection<String> m3792b() {
        if (this.f22328a == null) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(new HashSet(this.f22328a.keySet()));
    }

    /* renamed from: a */
    public Bundle mo3776a() {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(this.f22329d)) {
            bundle.putString("ext_ns", this.f22329d);
        }
        if (!TextUtils.isEmpty(this.f22332g)) {
            bundle.putString("ext_from", this.f22332g);
        }
        if (!TextUtils.isEmpty(this.f22331f)) {
            bundle.putString("ext_to", this.f22331f);
        }
        if (!TextUtils.isEmpty(this.f22330e)) {
            bundle.putString("ext_pkt_id", this.f22330e);
        }
        if (!TextUtils.isEmpty(this.f22333h)) {
            bundle.putString("ext_chid", this.f22333h);
        }
        C11381fs c11381fs = this.f22326a;
        if (c11381fs != null) {
            bundle.putBundle("ext_ERROR", c11381fs.m3770a());
        }
        List<C11371fl> list = this.f22327a;
        if (list != null) {
            Bundle[] bundleArr = new Bundle[list.size()];
            int i = 0;
            for (C11371fl c11371fl : this.f22327a) {
                Bundle m3834a = c11371fl.m3834a();
                if (m3834a != null) {
                    bundleArr[i] = m3834a;
                    i++;
                }
            }
            bundle.putParcelableArray("ext_exts", bundleArr);
        }
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't wrap try/catch for region: R(8:40|41|42|43|44|(4:45|46|48|49)|51|52) */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0126 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0120 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: o */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized java.lang.String m3781o() {
        /*
            Method dump skipped, instructions count: 327
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.AbstractC11375fo.m3781o():java.lang.String");
    }

    /* renamed from: p */
    public String m3779p() {
        return this.f22329d;
    }

    /* renamed from: q */
    public static String m3778q() {
        return f22320a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AbstractC11375fo abstractC11375fo = (AbstractC11375fo) obj;
        C11381fs c11381fs = this.f22326a;
        if (c11381fs == null ? abstractC11375fo.f22326a == null : c11381fs.equals(abstractC11375fo.f22326a)) {
            String str = this.f22332g;
            if (str == null ? abstractC11375fo.f22332g == null : str.equals(abstractC11375fo.f22332g)) {
                if (this.f22327a.equals(abstractC11375fo.f22327a)) {
                    String str2 = this.f22330e;
                    if (str2 == null ? abstractC11375fo.f22330e == null : str2.equals(abstractC11375fo.f22330e)) {
                        String str3 = this.f22333h;
                        if (str3 == null ? abstractC11375fo.f22333h == null : str3.equals(abstractC11375fo.f22333h)) {
                            Map<String, Object> map = this.f22328a;
                            if (map == null ? abstractC11375fo.f22328a == null : map.equals(abstractC11375fo.f22328a)) {
                                String str4 = this.f22331f;
                                if (str4 == null ? abstractC11375fo.f22331f == null : str4.equals(abstractC11375fo.f22331f)) {
                                    String str5 = this.f22329d;
                                    if (str5 != null) {
                                        if (str5.equals(abstractC11375fo.f22329d)) {
                                            return true;
                                        }
                                    } else if (abstractC11375fo.f22329d == null) {
                                        return true;
                                    }
                                    return false;
                                }
                                return false;
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        String str = this.f22329d;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.f22330e;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.f22331f;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.f22332g;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.f22333h;
        int hashCode5 = (((((hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31) + this.f22327a.hashCode()) * 31) + this.f22328a.hashCode()) * 31;
        C11381fs c11381fs = this.f22326a;
        return hashCode5 + (c11381fs != null ? c11381fs.hashCode() : 0);
    }
}
