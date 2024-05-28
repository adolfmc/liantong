package com.mob.tools.p237a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.location.Location;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Base64;
import com.mob.commons.C5741aa;
import com.mob.commons.C5829d;
import com.mob.commons.C5868q;
import com.mob.commons.C5879w;
import com.mob.commons.CSCenter;
import com.mob.tools.C6122c;
import com.mob.tools.MobLog;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.C6216g;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.MobPersistence;
import java.io.File;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* renamed from: com.mob.tools.a.j */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C6050j implements InterfaceC6028a {

    /* renamed from: d */
    private Context f14894d;

    /* renamed from: e */
    private C6029b f14895e;

    /* renamed from: f */
    private volatile Set<String> f14896f = new HashSet();

    /* renamed from: a */
    private ConcurrentHashMap<String, Object> f14891a = new ConcurrentHashMap<>();

    /* renamed from: b */
    private ConcurrentHashMap<String, Integer> f14892b = new ConcurrentHashMap<>();

    /* renamed from: c */
    private ConcurrentHashMap<String, Long> f14893c = new ConcurrentHashMap<>();

    /* renamed from: g */
    private void m11524g(String str) {
    }

    public C6050j(Context context) {
        this.f14894d = context;
        this.f14895e = C6029b.m11780a(context);
        C6216g.m11041a();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: a */
    public boolean mo11590a() {
        return ((Boolean) m11542b("ird", new AbstractC6116a<Boolean>(false) { // from class: com.mob.tools.a.j.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(Boolean bool) {
                return (bool == null || !bool.booleanValue()) ? 180000L : 86400000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public Boolean mo11378b() {
                return Boolean.valueOf(C6050j.this.f14895e.m11783a());
            }
        })).booleanValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: b */
    public boolean mo11547b() {
        return ((Boolean) m11542b("cx0", new AbstractC6116a<Boolean>(false) { // from class: com.mob.tools.a.j.12
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(Boolean bool) {
                return (bool == null || !bool.booleanValue()) ? 180000L : 86400000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public Boolean mo11378b() {
                return Boolean.valueOf(C6050j.this.f14895e.m11802H());
            }
        })).booleanValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: c */
    public boolean mo11538c() {
        return ((Boolean) m11542b("pd0", new AbstractC6116a<Boolean>(false) { // from class: com.mob.tools.a.j.23
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(Boolean bool) {
                return 604800000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public Boolean mo11378b() {
                return Boolean.valueOf(C6050j.this.f14895e.m11801I());
            }
        })).booleanValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: d */
    public boolean mo11534d() {
        return ((Boolean) m11580a("dee", new AbstractC6116a<Boolean>(false) { // from class: com.mob.tools.a.j.34
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public Boolean mo11378b() {
                return Boolean.valueOf(C6050j.this.f14895e.m11796N());
            }
        })).booleanValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: e */
    public boolean mo11531e() {
        return this.f14895e.m11797M();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: f */
    public boolean mo11528f() {
        return ((Boolean) m11580a("ua0", new AbstractC6116a<Boolean>(false) { // from class: com.mob.tools.a.j.45
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(Boolean bool) {
                return 180000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public Boolean mo11378b() {
                return Boolean.valueOf(C6050j.this.f14895e.m11798L());
            }
        })).booleanValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: g */
    public boolean mo11525g() {
        return ((Boolean) m11580a("dee1", new AbstractC6116a<Boolean>(false) { // from class: com.mob.tools.a.j.56
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(Boolean bool) {
                return 180000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public Boolean mo11378b() {
                return Boolean.valueOf(C6050j.this.f14895e.m11799K());
            }
        })).booleanValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: h */
    public boolean mo11522h() {
        return ((Boolean) m11580a("uee", new AbstractC6116a<Boolean>(false) { // from class: com.mob.tools.a.j.63
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(Boolean bool) {
                return 180000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public Boolean mo11378b() {
                return Boolean.valueOf(C6050j.this.f14895e.m11800J());
            }
        })).booleanValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: i */
    public boolean mo11520i() {
        return ((Boolean) m11580a("wpy", new AbstractC6116a<Boolean>(false) { // from class: com.mob.tools.a.j.64
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(Boolean bool) {
                return 180000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public Boolean mo11378b() {
                return Boolean.valueOf(C6050j.this.f14895e.m11795O());
            }
        })).booleanValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: j */
    public String mo11519j() {
        return (String) m11542b("agi", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.65
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 86400000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11715u();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: a */
    public String mo11575a(boolean z) {
        HashMap<String, Object> mo11529e = mo11529e(z);
        if (mo11529e != null) {
            return (String) mo11529e.get("ssmt");
        }
        return null;
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: b */
    public String mo11540b(boolean z) {
        HashMap<String, Object> mo11529e = mo11529e(z);
        if (mo11529e != null) {
            return (String) mo11529e.get("bsmt");
        }
        return null;
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: c */
    public String mo11535c(boolean z) {
        return (String) m11541b("car", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 600000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11724l();
            }
        }, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.mob.tools.a.j$3 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C60733 extends AbstractC6116a<String> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public long mo11379a(String str) {
            return 600000L;
        }

        C60733(String str) {
            super(str);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
        /* renamed from: a */
        public String mo11378b() throws Throwable {
            return C6050j.this.f14895e.m11723m();
        }
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: d */
    public String mo11532d(boolean z) {
        return (String) m11541b("cne", new C60733(null), z);
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: k */
    public String mo11518k() {
        return (String) m11542b("mvn", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 604800000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11803G();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: l */
    public String mo11517l() {
        return (String) m11542b("mol", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 604800000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11743b();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: m */
    public String mo11516m() {
        return (String) m11542b("mar", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.6
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 604800000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11738c();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: n */
    public String mo11515n() {
        return (String) m11542b("brd", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.7
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 604800000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11787W();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: o */
    public String mo11514o() {
        return (String) m11542b("dte", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.8
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 604800000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11713w();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: p */
    public Object mo11513p() {
        return m11580a("gtecloc", new AbstractC6116a<Object>(null) { // from class: com.mob.tools.a.j.9
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            protected long mo11379a(Object obj) {
                return 180000L;
            }

            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: b */
            protected Object mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11756an();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: q */
    public ArrayList<HashMap<String, Object>> mo11512q() {
        return (ArrayList) m11542b("bsnbcl", new AbstractC6116a<ArrayList<HashMap<String, Object>>>(null) { // from class: com.mob.tools.a.j.10
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(ArrayList<HashMap<String, Object>> arrayList) {
                return 180000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public ArrayList<HashMap<String, Object>> mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11714v();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: r */
    public HashMap<String, Object> mo11511r() {
        return mo11529e(false);
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: e */
    public HashMap<String, Object> mo11529e(boolean z) {
        return (HashMap) m11541b("crtwfo", new AbstractC6116a<HashMap<String, Object>>(null) { // from class: com.mob.tools.a.j.11
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(HashMap<String, Object> hashMap) {
                return 180000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public HashMap<String, Object> mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11711y();
            }
        }, z);
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: s */
    public int mo11510s() {
        return ((Integer) m11542b("ovit", new AbstractC6116a<Integer>(-1) { // from class: com.mob.tools.a.j.13
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(Integer num) {
                return 3600000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public Integer mo11378b() throws Throwable {
                return Integer.valueOf(C6050j.this.f14895e.m11732f());
            }
        })).intValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: t */
    public String mo11509t() {
        return (String) m11542b("ovne", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.14
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 3600000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11730g();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: u */
    public String mo11508u() {
        return (String) m11542b("ole", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.15
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 3600000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11728h();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: v */
    public String mo11507v() {
        return (String) m11542b("ocy", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.16
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 3600000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11726j();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: w */
    public HashMap<String, Object> mo11506w() {
        return (HashMap) m11542b("cio0", new AbstractC6116a<HashMap<String, Object>>(null) { // from class: com.mob.tools.a.j.17
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(HashMap<String, Object> hashMap) {
                return 604800000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public HashMap<String, Object> mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11808B();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: x */
    public ArrayList<ArrayList<String>> mo11505x() {
        return (ArrayList) m11542b("tdio", new AbstractC6116a<ArrayList<ArrayList<String>>>(null) { // from class: com.mob.tools.a.j.18
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(ArrayList<ArrayList<String>> arrayList) {
                return 86400000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public ArrayList<ArrayList<String>> mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11807C();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: y */
    public String mo11504y() {
        return (String) m11542b("qkl", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.19
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return "0".equals(str) ? 86400000L : 604800000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11806D();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: z */
    public HashMap<String, HashMap<String, Long>> mo11503z() {
        return (HashMap) m11542b("siio", new AbstractC6116a<HashMap<String, HashMap<String, Long>>>(null) { // from class: com.mob.tools.a.j.20
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(HashMap<String, HashMap<String, Long>> hashMap) {
                return 86400000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public HashMap<String, HashMap<String, Long>> mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11805E();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: A */
    public HashMap<String, Long> mo11616A() {
        return (HashMap) m11542b("meio", new AbstractC6116a<HashMap<String, Long>>(null) { // from class: com.mob.tools.a.j.21
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(HashMap<String, Long> hashMap) {
                return 180000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public HashMap<String, Long> mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11804F();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: B */
    public String mo11615B() {
        return (String) m11542b("ale", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.22
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 600000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11727i();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: C */
    public String mo11614C() {
        return (String) m11542b("sse", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.24
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 604800000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11725k();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: D */
    public String mo11613D() {
        return C5829d.m12323i() ? mo11526f(false) : "forbid";
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: f */
    public String mo11526f(boolean z) {
        return (String) m11541b("nte", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.25
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 180000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11722n();
            }
        }, z);
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: E */
    public String mo11612E() {
        String lowerCase = mo11613D().toLowerCase();
        if (TextUtils.isEmpty(lowerCase) || C5868q.m12203b("004d-dcJde").equals(lowerCase)) {
            return C5868q.m12203b("004d-dc7de");
        }
        if (lowerCase.startsWith(C5868q.m12203b("002)ghdd")) || lowerCase.startsWith(C5868q.m12203b("0022gkdd")) || lowerCase.startsWith(C5868q.m12203b("002_iedd")) || lowerCase.startsWith(C5868q.m12203b("002:gddd"))) {
            return C5868q.m12203b("004beff");
        }
        if (lowerCase.startsWith(C5868q.m12203b("004 eechdech")) || "forbid".equals(lowerCase)) {
            return C5868q.m12203b("004Oeechdech");
        }
        return C5868q.m12203b("005]dcWhgeHci");
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: F */
    public String mo11611F() {
        String lowerCase = mo11613D().toLowerCase();
        if (TextUtils.isEmpty(lowerCase) || C5868q.m12203b("004d$dc!de").equals(lowerCase)) {
            return C5868q.m12203b("004dEdc5de");
        }
        if (lowerCase.startsWith(C5868q.m12203b("004@eechdech"))) {
            return C5868q.m12203b("0042eechdech");
        }
        if (lowerCase.startsWith(C5868q.m12203b("002 ghdd"))) {
            return C5868q.m12203b("002=ghdd");
        }
        if (lowerCase.startsWith(C5868q.m12203b("002>gkdd"))) {
            return C5868q.m12203b("0025gkdd");
        }
        if (lowerCase.startsWith(C5868q.m12203b("002$iedd"))) {
            return C5868q.m12203b("002[iedd");
        }
        if (lowerCase.startsWith(C5868q.m12203b("002;gddd"))) {
            return C5868q.m12203b("002Lgddd");
        }
        return lowerCase.startsWith(C5868q.m12203b("009Ked^f$cf_ehCdcdcThg")) ? C5868q.m12203b("0095ed>fIcfReh(dcdcFhg") : lowerCase;
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: G */
    public boolean mo11610G() {
        String mo11612E = mo11612E();
        return C5868q.m12203b("004!eechdech").equals(mo11612E) || C5868q.m12203b("004beff").equals(mo11612E);
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: H */
    public int mo11609H() {
        return ((Integer) m11542b("dtnttp", new AbstractC6116a<Integer>(-1) { // from class: com.mob.tools.a.j.26
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(Integer num) {
                return 180000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public Integer mo11378b() throws Throwable {
                return Integer.valueOf(C6050j.this.f14895e.m11788V());
            }
        })).intValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: I */
    public String mo11608I() {
        return (String) m11542b("tize", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.27
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 3600000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11793Q();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: J */
    public String mo11607J() {
        return (String) m11542b("flvr", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.28
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 3600000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11792R();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: K */
    public String mo11606K() {
        return (String) m11542b("babd", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.29
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 604800000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11791S();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: L */
    public String mo11605L() {
        return (String) m11542b("bfsp", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.30
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 604800000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11790T();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: M */
    public String mo11604M() {
        return (String) m11542b("bopm", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.31
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 604800000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11789U();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: N */
    public String mo11603N() {
        if (CSCenter.getInstance().isIpAddressEnable()) {
            if (C5829d.m12323i()) {
                try {
                    Enumeration<NetworkInterface> m11668a = C6034e.m11667a(this.f14894d).m11668a();
                    while (m11668a.hasMoreElements()) {
                        Enumeration<InetAddress> m11658a = C6034e.m11667a(this.f14894d).m11658a(m11668a.nextElement());
                        while (m11658a.hasMoreElements()) {
                            InetAddress nextElement = m11658a.nextElement();
                            if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet4Address)) {
                                return nextElement.getHostAddress();
                            }
                        }
                    }
                    return null;
                } catch (Throwable th) {
                    MobLog.getInstance().m11325w(th);
                    return null;
                }
            }
            return "0.0.0.0";
        }
        return CSCenter.getInstance().getIpAddress();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: a */
    public ArrayList<HashMap<String, String>> mo11571a(boolean z, boolean z2) {
        synchronized ("giafce") {
            ArrayList<HashMap<String, String>> m11521h = m11521h(z2);
            if (z) {
                return this.f14895e.m11775a(m11521h, 0);
            }
            return this.f14895e.m11775a(m11521h, 1);
        }
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: O */
    public ArrayList<HashMap<String, String>> mo11602O() {
        return m11521h(false);
    }

    /* renamed from: h */
    private ArrayList<HashMap<String, String>> m11521h(boolean z) {
        ArrayList<HashMap<String, String>> arrayList;
        synchronized ("gal") {
            arrayList = (ArrayList) m11541b("gal", new AbstractC6116a<ArrayList<HashMap<String, String>>>(null) { // from class: com.mob.tools.a.j.32
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public long mo11379a(ArrayList<HashMap<String, String>> arrayList2) {
                    Calendar calendar = Calendar.getInstance();
                    long m11576a = C6050j.this.m11576a(calendar) - calendar.getTimeInMillis();
                    if (m11576a > 0) {
                        return m11576a;
                    }
                    return 86400000L;
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
                /* renamed from: a */
                public ArrayList<HashMap<String, String>> mo11378b() throws Throwable {
                    return C6050j.this.f14895e.m11717s();
                }
            }, z);
        }
        return arrayList;
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: P */
    public ArrayList<HashMap<String, String>> mo11601P() {
        ArrayList<HashMap<String, String>> m11775a;
        synchronized ("gsl") {
            m11775a = this.f14895e.m11775a(m11521h(false), 2);
        }
        return m11775a;
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: a */
    public Location mo11589a(final int i, final int i2, final boolean z) {
        return (Location) m11542b("gctn-" + i + "-" + i2 + "-" + z, new AbstractC6116a<Location>(null) { // from class: com.mob.tools.a.j.33
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(Location location) {
                return 180000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public Location mo11378b() throws Throwable {
                Object m11781a = C6050j.this.f14895e.m11781a(i, i2, z);
                if (m11781a instanceof Location) {
                    return (Location) m11781a;
                }
                return null;
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: a */
    public String mo11582a(String str) {
        return this.f14895e.m11777a(str);
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: Q */
    public String mo11600Q() {
        return (String) m11542b("deky", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.35
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 604800000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11771a(false);
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: g */
    public String mo11523g(boolean z) {
        return this.f14895e.m11771a(z);
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: b */
    public boolean mo11543b(String str) {
        return this.f14895e.m11733e(str);
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: R */
    public String mo11599R() {
        return (String) m11542b("scph", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.36
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 604800000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11716t();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: S */
    public String mo11598S() {
        return this.f14895e.m11740b(mo11597T());
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: c */
    public String mo11537c(String str) {
        return this.f14895e.m11740b(str);
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: T */
    public String mo11597T() {
        return (String) m11542b("pne", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.37
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11721o();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: U */
    public String mo11596U() {
        return this.f14895e.m11720p();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: d */
    public String mo11533d(String str) {
        return this.f14895e.m11737c(str);
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: V */
    public int mo11595V() {
        return this.f14895e.m11719q();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: W */
    public String mo11594W() {
        return this.f14895e.m11718r();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: X */
    public boolean mo11593X() {
        return ((Boolean) m11580a("imp", new AbstractC6116a<Boolean>(false) { // from class: com.mob.tools.a.j.38
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public Boolean mo11378b() throws Throwable {
                return Boolean.valueOf(C6050j.this.f14895e.m11786X());
            }
        })).booleanValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: Y */
    public String mo11592Y() {
        return (String) m11580a("cpne", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.39
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11785Y();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: Z */
    public boolean mo11591Z() {
        return C5879w.m12167a();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: e */
    public boolean mo11530e(String str) {
        try {
            return this.f14895e.m11735d(str);
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return false;
        }
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: aa */
    public Context mo11570aa() {
        return (Context) m11580a("galct", new AbstractC6116a<Context>(null) { // from class: com.mob.tools.a.j.40
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public Context mo11378b() throws Throwable {
                if (C6050j.this.f14894d != null) {
                    return C6050j.this.f14894d;
                }
                Context m11712x = C6029b.m11712x();
                if (m11712x != null) {
                    C6050j.this.f14894d = m11712x;
                }
                return m11712x;
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: a */
    public List<ResolveInfo> mo11588a(Intent intent, int i) {
        return C6034e.m11667a(this.f14894d).m11666a(intent, i);
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: b */
    public ResolveInfo mo11545b(Intent intent, int i) {
        return C6034e.m11667a(this.f14894d).m11656b(intent, i);
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: a */
    public PackageInfo mo11574a(boolean z, final int i, final String str, final int i2) {
        boolean z2;
        final boolean m11359a = C6122c.m11359a("1009", str);
        String str2 = "gpi-" + i + "-" + str + "-" + i2;
        AbstractC6116a<PackageInfo> abstractC6116a = new AbstractC6116a<PackageInfo>(null) { // from class: com.mob.tools.a.j.41
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(PackageInfo packageInfo) {
                if (m11359a) {
                    return i;
                }
                return 86400000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public PackageInfo mo11378b() throws Throwable {
                return (PackageInfo) C6034e.m11667a(C6050j.this.f14894d).m11663a(str, i2);
            }
        };
        if (!z) {
            if (!m11572a(m11359a, "gpi-" + i + "-" + str + "-" + i2, str)) {
                z2 = false;
                return (PackageInfo) m11541b(str2, abstractC6116a, z2);
            }
        }
        z2 = true;
        return (PackageInfo) m11541b(str2, abstractC6116a, z2);
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: ab */
    public String mo11569ab() {
        return this.f14895e.m11736d();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: ac */
    public String mo11568ac() {
        return this.f14895e.m11734e();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: ad */
    public long mo11567ad() {
        return this.f14895e.m11784Z();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: ae */
    public String mo11566ae() {
        return (String) m11542b("dvcnm", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.42
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 3600000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11769aa();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: af */
    public String mo11565af() {
        return (String) m11542b("cgrp", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.43
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 604800000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11768ab();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: ag */
    public String mo11564ag() {
        return (String) m11542b("cinfo", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.44
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 604800000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11767ac();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: ah */
    public String mo11563ah() {
        return (String) m11542b("odmt", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.46
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return TextUtils.isEmpty(str) ? -1L : 604800000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11766ad();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: ai */
    public String mo11562ai() {
        String mo11563ah = C6031c.m11708a(this.f14894d).m11704d().mo11563ah();
        if (!TextUtils.isEmpty(mo11563ah)) {
            try {
                return Base64.encodeToString(Data.AES128Encode(Data.MD5(C6152DH.SyncMtd.getManufacturer()), mo11563ah), 2);
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
        return mo11563ah;
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: aj */
    public HashMap<String, Object> mo11561aj() {
        return (HashMap) m11542b("alldmt", new AbstractC6116a<HashMap<String, Object>>(null) { // from class: com.mob.tools.a.j.47
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(HashMap<String, Object> hashMap) {
                return 86400000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public HashMap<String, Object> mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11765ae();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: ak */
    public ApplicationInfo mo11560ak() {
        final boolean m11359a = C6122c.m11359a("1009", this.f14894d.getPackageName());
        return (ApplicationInfo) m11541b("gtaif", new AbstractC6116a<ApplicationInfo>(null) { // from class: com.mob.tools.a.j.48
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(ApplicationInfo applicationInfo) {
                return m11359a ? 0L : 86400000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public ApplicationInfo mo11378b() throws Throwable {
                return C6034e.m11667a(C6050j.this.f14894d).m11651d();
            }
        }, m11572a(m11359a, "gtaif", mo11597T()));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: al */
    public ArrayList<HashMap<String, Object>> mo11559al() {
        return (ArrayList) m11542b("gtwflok", new AbstractC6116a<ArrayList<HashMap<String, Object>>>(null) { // from class: com.mob.tools.a.j.49
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(ArrayList<HashMap<String, Object>> arrayList) {
                return 180000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public ArrayList<HashMap<String, Object>> mo11378b() throws Throwable {
                Boolean bool;
                if (C5829d.m12328d() && C6050j.this.mo11530e(C5868q.m12203b("036cdBcbcidcchcbec%ieDcicechegegchdcMd]ecfieidkdghcffcgfddhekdhcgdidjdkdjff")) && C6050j.this.mo11530e(C5868q.m12203b("036cd5cbcidcchcbec=ie%cicechegegchdc[d'ecdkfififfdidicgfddhekdhcgdidjdkdjff"))) {
                    LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
                    C6050j.this.f14895e.m11772a(linkedBlockingQueue);
                    C6050j.this.f14895e.m11809A();
                    try {
                        bool = (Boolean) linkedBlockingQueue.poll(20000L, TimeUnit.MILLISECONDS);
                    } catch (Throwable th) {
                        MobLog.getInstance().m11341d(th);
                        bool = null;
                    }
                    if (bool == null || !bool.booleanValue()) {
                        return null;
                    }
                    return C6050j.this.f14895e.m11710z();
                }
                return null;
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: a */
    public ApplicationInfo mo11581a(String str, int i) {
        return mo11573a(false, str, i);
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: a */
    public ApplicationInfo mo11573a(boolean z, final String str, final int i) {
        boolean z2;
        final boolean m11359a = C6122c.m11359a("1009", str);
        String str2 = "gtaiffce-" + str + "-" + i;
        AbstractC6116a<ApplicationInfo> abstractC6116a = new AbstractC6116a<ApplicationInfo>(null) { // from class: com.mob.tools.a.j.50
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(ApplicationInfo applicationInfo) {
                return m11359a ? 0L : 86400000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public ApplicationInfo mo11378b() throws Throwable {
                return C6034e.m11667a(C6050j.this.f14894d).m11654b(str, i);
            }
        };
        if (!z) {
            if (!m11572a(m11359a, "gtaiffce-" + str + "-" + i, str)) {
                z2 = false;
                return (ApplicationInfo) m11541b(str2, abstractC6116a, z2);
            }
        }
        z2 = true;
        return (ApplicationInfo) m11541b(str2, abstractC6116a, z2);
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: am */
    public long mo11558am() {
        return ((Long) m11542b("gtbdt", new AbstractC6116a<Long>(0L) { // from class: com.mob.tools.a.j.51
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(Long l) {
                return 3600000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public Long mo11378b() throws Throwable {
                return Long.valueOf(C6050j.this.f14895e.m11764af());
            }
        })).longValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: an */
    public double mo11557an() {
        return ((Double) m11542b("gtscnin", new AbstractC6116a<Double>(Double.valueOf(0.0d)) { // from class: com.mob.tools.a.j.52
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(Double d) {
                return 604800000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public Double mo11378b() throws Throwable {
                return Double.valueOf(C6050j.this.f14895e.m11763ag());
            }
        })).doubleValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: ao */
    public int mo11556ao() {
        return ((Integer) m11542b("gtscnppi", new AbstractC6116a<Integer>(0) { // from class: com.mob.tools.a.j.53
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(Integer num) {
                return 604800000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public Integer mo11378b() throws Throwable {
                return Integer.valueOf(C6050j.this.f14895e.m11762ah());
            }
        })).intValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: ap */
    public boolean mo11555ap() {
        return ((Boolean) m11542b("ishmos", new AbstractC6116a<Boolean>(false) { // from class: com.mob.tools.a.j.54
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(Boolean bool) {
                return 3600000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public Boolean mo11378b() throws Throwable {
                return Boolean.valueOf(C6050j.this.f14895e.m11761ai());
            }
        })).booleanValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: aq */
    public String mo11554aq() {
        return (String) m11542b("gthmosv", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.55
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 3600000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11760aj();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: ar */
    public String mo11553ar() {
        return (String) m11542b("gthmosdtlv", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.57
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 3600000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11759ak();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: as */
    public int mo11552as() {
        return ((Integer) m11542b("hmpmst", new AbstractC6116a<Integer>(-1) { // from class: com.mob.tools.a.j.58
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(Integer num) {
                return 180000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public Integer mo11378b() throws Throwable {
                return Integer.valueOf(C6050j.this.f14895e.m11758al());
            }
        })).intValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: at */
    public int mo11551at() {
        return ((Integer) m11542b("hmepmst", new AbstractC6116a<Integer>(-1) { // from class: com.mob.tools.a.j.59
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(Integer num) {
                return 180000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public Integer mo11378b() throws Throwable {
                return Integer.valueOf(C6050j.this.f14895e.m11757am());
            }
        })).intValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: au */
    public String mo11550au() {
        return (String) m11542b("gtinnerlangmt", new AbstractC6116a<String>(null) { // from class: com.mob.tools.a.j.60
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(String str) {
                return 86400000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public String mo11378b() throws Throwable {
                return C6050j.this.f14895e.m11755ao();
            }
        });
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: av */
    public int mo11549av() {
        return ((Integer) m11542b("gtgramgendt", new AbstractC6116a<Integer>(0) { // from class: com.mob.tools.a.j.61
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(Integer num) {
                return 86400000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public Integer mo11378b() throws Throwable {
                return Integer.valueOf(C6050j.this.f14895e.m11754ap());
            }
        })).intValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: b */
    public Object mo11546b(int i, int i2, boolean z) {
        return mo11589a(i, i2, z);
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: b */
    public Object mo11539b(boolean z, int i, String str, int i2) {
        return mo11574a(z, i, str, i2);
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: aw */
    public boolean mo11548aw() {
        return ((Boolean) m11580a("debbing", new AbstractC6116a<Boolean>(false) { // from class: com.mob.tools.a.j.62
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public long mo11379a(Boolean bool) {
                return 60000L;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.mob.tools.p237a.C6050j.AbstractC6116a
            /* renamed from: a */
            public Boolean mo11378b() throws Throwable {
                return Boolean.valueOf(C6050j.this.f14895e.m11752ar());
            }
        })).booleanValue();
    }

    /* renamed from: a */
    private boolean m11583a(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue() == -1;
        } else if (obj instanceof Long) {
            return ((Long) obj).longValue() == -1;
        } else if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        } else {
            if (obj instanceof Collection) {
                return ((Collection) obj).isEmpty();
            }
            return false;
        }
    }

    /* renamed from: a */
    private <T> T m11580a(String str, AbstractC6116a<T> abstractC6116a) {
        return (T) m11579a(str, (AbstractC6116a<Object>) abstractC6116a, false);
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x00fa: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:52:0x00fa */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:64:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private <T> T m11579a(java.lang.String r11, com.mob.tools.p237a.C6050j.AbstractC6116a<T> r12, boolean r13) {
        /*
            Method dump skipped, instructions count: 315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.p237a.C6050j.m11579a(java.lang.String, com.mob.tools.a.j$a, boolean):java.lang.Object");
    }

    /* renamed from: b */
    private <T> T m11542b(String str, AbstractC6116a<T> abstractC6116a) {
        return (T) m11541b(str, (AbstractC6116a<Object>) abstractC6116a, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0035, code lost:
        if (r11.f14896f.contains(r12) == false) goto L15;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0050 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:74:? A[RETURN, SYNTHETIC] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private <T> T m11541b(java.lang.String r12, com.mob.tools.p237a.C6050j.AbstractC6116a<T> r13, boolean r14) {
        /*
            Method dump skipped, instructions count: 326
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.p237a.C6050j.m11541b(java.lang.String, com.mob.tools.a.j$a, boolean):java.lang.Object");
    }

    /* renamed from: c */
    private <T> T m11536c(String str, AbstractC6116a<T> abstractC6116a) throws MobPersistence.NoValidDataException {
        Type m11587a = m11587a((AbstractC6116a) abstractC6116a);
        int m11577a = m11577a(m11587a);
        try {
            if (m11577a == 1) {
                return (T) C6216g.m11041a().m11028a(str, (Class) ((GenericArrayType) m11587a).getGenericComponentType(), (Parcelable[]) abstractC6116a.f14973f);
            }
            if (m11577a != 2 && m11577a != 4) {
                if (m11577a != 3) {
                    if (m11577a == 9) {
                        Class<T> cls = (Class) m11587a;
                        if (cls != null) {
                            if (cls == Integer.class) {
                                return (T) Integer.valueOf(C6216g.m11041a().m11009b(str, ((Integer) abstractC6116a.f14973f).intValue()));
                            }
                            if (cls == Long.class) {
                                return (T) Long.valueOf(C6216g.m11041a().m11037a(str, ((Long) abstractC6116a.f14973f).longValue()));
                            }
                            if (cls == Double.class) {
                                return (T) Double.valueOf(C6216g.m11041a().m11039a(str, ((Double) abstractC6116a.f14973f).doubleValue()));
                            }
                            if (cls == Boolean.class) {
                                return (T) Boolean.valueOf(C6216g.m11041a().m11013a(str, ((Boolean) abstractC6116a.f14973f).booleanValue()));
                            }
                            if (cls == String.class) {
                                return (T) C6216g.m11041a().m11006b(str, (String) abstractC6116a.f14973f);
                            }
                            if (Parcelable.class.isAssignableFrom(cls)) {
                                return (T) C6216g.m11041a().m11031a(str, (Class<Class<T>>) cls, (Class<T>) abstractC6116a.f14973f);
                            }
                            return (T) C6216g.m11041a().m11007b(str, abstractC6116a.f14973f);
                        }
                        return null;
                    }
                    return (T) C6216g.m11041a().m11007b(str, abstractC6116a.f14973f);
                }
                ParameterizedType parameterizedType = (ParameterizedType) m11587a;
                Class cls2 = (Class) parameterizedType.getRawType();
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                Type type = actualTypeArguments[0];
                if (actualTypeArguments.length == 2) {
                    type = actualTypeArguments[1];
                }
                if (type instanceof Class) {
                    Class cls3 = (Class) type;
                    if (Parcelable.class.isAssignableFrom(cls3)) {
                        if (cls2 != List.class && cls2 != LinkedList.class && cls2 != ArrayList.class) {
                            if (cls2 == Map.class || cls2 == HashMap.class || cls2 == TreeMap.class || cls2 == Hashtable.class) {
                                return (T) C6216g.m11041a().m11008b(str, cls3);
                            }
                            return null;
                        }
                        return (T) C6216g.m11041a().m11004c(str, cls3);
                    }
                    return null;
                }
                return null;
            }
            return (T) C6216g.m11041a().m11007b(str, abstractC6116a.f14973f);
        } catch (MobPersistence.NoValidDataException e) {
            throw e;
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return null;
        }
    }

    /* renamed from: a */
    private <T> void m11578a(String str, T t, long j, AbstractC6116a<T> abstractC6116a) {
        try {
            Type m11587a = m11587a((AbstractC6116a) abstractC6116a);
            int m11577a = m11577a(m11587a);
            if (m11577a == 1) {
                C6216g.m11041a().m11011a(str, (Parcelable[]) t, j);
            } else {
                if (m11577a != 2 && m11577a != 4) {
                    if (m11577a == 3) {
                        Class cls = (Class) ((ParameterizedType) m11587a).getRawType();
                        if (cls != List.class && cls != LinkedList.class && cls != ArrayList.class) {
                            if (cls == Map.class || cls == HashMap.class || cls == TreeMap.class || cls == Hashtable.class) {
                                C6216g.m11041a().m11014a(str, (Map) t, j);
                            }
                        }
                        C6216g.m11041a().m11016a(str, (List) t, j);
                    } else if (m11577a == 9) {
                        Class cls2 = (Class) m11587a;
                        if (cls2 != null) {
                            if (cls2 == Integer.class) {
                                C6216g.m11041a().m11024a(str, (Integer) t, j);
                            } else if (cls2 == Long.class) {
                                C6216g.m11041a().m11022a(str, (Long) t, j);
                            } else if (cls2 == Double.class) {
                                C6216g.m11041a().m11026a(str, (Double) t, j);
                            } else if (cls2 == Boolean.class) {
                                C6216g.m11041a().m11033a(str, (Boolean) t, j);
                            } else if (cls2 == String.class) {
                                C6216g.m11041a().m11018a(str, (String) t, j);
                            } else if (Parcelable.class.isAssignableFrom(cls2)) {
                                C6216g.m11041a().m11035a(str, (Parcelable) t, j);
                            } else {
                                C6216g.m11041a().m11020a(str, t, j);
                            }
                        }
                    } else {
                        C6216g.m11041a().m11020a(str, t, j);
                    }
                }
                C6216g.m11041a().m11020a(str, t, j);
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
    }

    /* renamed from: a */
    private <T> Type m11587a(AbstractC6116a<T> abstractC6116a) {
        try {
            return ((ParameterizedType) abstractC6116a.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return null;
        }
    }

    /* renamed from: a */
    private int m11577a(Type type) {
        if (type instanceof GenericArrayType) {
            return Parcelable.class.isAssignableFrom((Class) ((GenericArrayType) type).getGenericComponentType()) ? 1 : 2;
        } else if (type instanceof ParameterizedType) {
            try {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Class cls = (Class) parameterizedType.getRawType();
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                Type type2 = actualTypeArguments[0];
                if (actualTypeArguments.length == 2) {
                    type2 = actualTypeArguments[1];
                }
                if (type2 instanceof ParameterizedType) {
                    ParameterizedType parameterizedType2 = (ParameterizedType) type2;
                    Class cls2 = (Class) parameterizedType2.getRawType();
                    Type[] actualTypeArguments2 = parameterizedType2.getActualTypeArguments();
                    Type type3 = actualTypeArguments2[0];
                    if (actualTypeArguments2.length == 2) {
                        type3 = actualTypeArguments2[1];
                    }
                    if (type3 instanceof Class) {
                        Class cls3 = (Class) type3;
                    }
                    return 4;
                } else if (type2 instanceof Class) {
                    return Parcelable.class.isAssignableFrom((Class) type2) ? 3 : 4;
                } else {
                    return -1;
                }
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return -1;
            }
        } else {
            return 9;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public long m11576a(Calendar calendar) {
        calendar.add(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    /* renamed from: a */
    private boolean m11572a(boolean z, String str, String str2) {
        String str3 = "sdir_able_" + (TextUtils.equals(str2, C6152DH.SyncMtd.getPackageName()) ? 1 : 0);
        if (C5741aa.m12650a().m12635b(str3, -1) != z) {
            C5741aa.m12650a().m12644a(str3, z ? 1 : 0);
            if (!z) {
                return true;
            }
        }
        if (!z || TextUtils.isEmpty(str2)) {
            return false;
        }
        String str4 = "key_almdf-" + str + "-" + str2;
        long m11005c = C6216g.m11041a().m11005c(str4);
        long m11527f = m11527f(str2);
        if (m11527f != m11005c) {
            C6216g.m11041a().m11023a(str4, Long.valueOf(m11527f));
            return true;
        }
        return false;
    }

    /* renamed from: f */
    private long m11527f(String str) {
        if (!TextUtils.isEmpty(str)) {
            ApplicationInfo mo11573a = mo11573a(true, str, 0);
            String str2 = mo11573a != null ? mo11573a.sourceDir : null;
            if (!TextUtils.isEmpty(str2)) {
                return new File(str2).lastModified();
            }
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.mob.tools.a.j$a */
    /* loaded from: E:\567196_dexfile_execute.dex */
    public static abstract class AbstractC6116a<T> {

        /* renamed from: f */
        public T f14973f;

        /* renamed from: a */
        protected long mo11379a(T t) {
            return 0L;
        }

        /* renamed from: b */
        protected abstract T mo11378b() throws Throwable;

        public AbstractC6116a(T t) {
            this.f14973f = t;
        }
    }
}
