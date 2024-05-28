package com.xiaomi.push;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.service.C11578b;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.xiaomi.push.cg */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11232cg {

    /* renamed from: a */
    protected static Context f21695a;

    /* renamed from: a */
    private static InterfaceC11235a f21696a;

    /* renamed from: a */
    private static C11232cg f21697a;

    /* renamed from: c */
    private static String f21700c;

    /* renamed from: d */
    private static String f21701d;

    /* renamed from: a */
    private long f21702a;

    /* renamed from: a */
    private InterfaceC11231cf f21703a;

    /* renamed from: a */
    protected InterfaceC11236b f21704a;

    /* renamed from: a */
    private String f21705a;

    /* renamed from: a */
    protected final Map<String, C11229cd> f21706a;

    /* renamed from: b */
    private final long f21707b;

    /* renamed from: b */
    private String f21708b;

    /* renamed from: c */
    private long f21709c;

    /* renamed from: b */
    protected static final Map<String, C11228cc> f21699b = new HashMap();

    /* renamed from: a */
    protected static boolean f21698a = false;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.cg$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11235a {
        /* renamed from: a */
        C11232cg mo2660a(Context context, InterfaceC11231cf interfaceC11231cf, InterfaceC11236b interfaceC11236b, String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.cg$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11236b {
        /* renamed from: a */
        String mo2658a(String str);
    }

    /* renamed from: f */
    private String m4546f() {
        return "host_fallbacks";
    }

    /* renamed from: b */
    protected String m4559b() {
        return "resolver.msg.xiaomi.net";
    }

    /* renamed from: a */
    public static synchronized C11232cg m4574a() {
        C11232cg c11232cg;
        synchronized (C11232cg.class) {
            if (f21697a == null) {
                throw new IllegalStateException("the host manager is not initialized yet.");
            }
            c11232cg = f21697a;
        }
        return c11232cg;
    }

    /* renamed from: a */
    public static synchronized void m4567a(InterfaceC11235a interfaceC11235a) {
        synchronized (C11232cg.class) {
            f21696a = interfaceC11235a;
            f21697a = null;
        }
    }

    /* renamed from: a */
    public static synchronized void m4568a(Context context, InterfaceC11231cf interfaceC11231cf, InterfaceC11236b interfaceC11236b, String str, String str2, String str3) {
        synchronized (C11232cg.class) {
            f21695a = context.getApplicationContext();
            if (f21695a == null) {
                f21695a = context;
            }
            if (f21697a == null) {
                if (f21696a == null) {
                    f21697a = new C11232cg(context, interfaceC11231cf, interfaceC11236b, str, str2, str3);
                } else {
                    f21697a = f21696a.mo2660a(context, interfaceC11231cf, interfaceC11236b, str);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C11232cg(Context context, InterfaceC11231cf interfaceC11231cf, InterfaceC11236b interfaceC11236b, String str) {
        this(context, interfaceC11231cf, interfaceC11236b, str, null, null);
    }

    protected C11232cg(Context context, InterfaceC11231cf interfaceC11231cf, InterfaceC11236b interfaceC11236b, String str, String str2, String str3) {
        this.f21706a = new HashMap();
        this.f21705a = "0";
        this.f21702a = 0L;
        this.f21707b = 15L;
        this.f21709c = 0L;
        this.f21708b = "isp_prov_city_country_ip";
        this.f21704a = interfaceC11236b;
        if (interfaceC11231cf == null) {
            this.f21703a = new InterfaceC11231cf() { // from class: com.xiaomi.push.cg.1
                @Override // com.xiaomi.push.InterfaceC11231cf
                /* renamed from: a */
                public boolean mo4544a(String str4) {
                    return true;
                }
            };
        } else {
            this.f21703a = interfaceC11231cf;
        }
        this.f21705a = str;
        f21700c = str2 == null ? context.getPackageName() : str2;
        f21701d = str3 == null ? m4545g() : str3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m4573a() {
        if (f21695a == null) {
            return "unknown";
        }
        try {
            C11175av m4855a = C11169au.m4855a();
            if (m4855a == null) {
                return "unknown";
            }
            if (m4855a.m4826a() == 1) {
                return "WIFI-UNKNOWN";
            }
            return m4855a.m4823a() + "-" + m4855a.m4819b();
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    /* renamed from: a */
    public C11228cc m4566a(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the url is empty");
        }
        return m4561a(new URL(str).getHost(), true);
    }

    /* renamed from: b */
    public C11228cc m4557b(String str) {
        return m4561a(str, true);
    }

    /* renamed from: a */
    public C11228cc m4561a(String str, boolean z) {
        C11228cc m4547e;
        AbstractC11049b.m5271b("HostManager", "-->getFallbacksByHost(): host=", str, ", fetchRemoteIfNeed=", Boolean.valueOf(z));
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the host is empty");
        }
        if (this.f21703a.mo4544a(str)) {
            final C11228cc m4553c = m4553c(str);
            return (m4553c == null || !m4553c.mo4541b()) ? (z && C11169au.m4849a(f21695a) && (m4547e = m4547e(str)) != null) ? m4547e : new C11228cc(str) { // from class: com.xiaomi.push.cg.2

                /* renamed from: a */
                C11228cc f21711a;

                @Override // com.xiaomi.push.C11228cc
                /* renamed from: b */
                public boolean mo4541b() {
                    return false;
                }

                {
                    this.f21711a = m4553c;
                    this.f21682b = this.f21682b;
                    C11228cc c11228cc = m4553c;
                    if (c11228cc != null) {
                        this.f21686f = c11228cc.f21686f;
                    }
                }

                @Override // com.xiaomi.push.C11228cc
                /* renamed from: a */
                public synchronized ArrayList<String> mo4542a(boolean z2) {
                    ArrayList<String> arrayList;
                    arrayList = new ArrayList<>();
                    if (this.f21711a != null) {
                        arrayList.addAll(this.f21711a.mo4542a(true));
                    }
                    synchronized (C11232cg.f21699b) {
                        C11228cc c11228cc = C11232cg.f21699b.get(this.f21682b);
                        if (c11228cc != null) {
                            Iterator<String> it = c11228cc.mo4542a(true).iterator();
                            while (it.hasNext()) {
                                String next = it.next();
                                if (arrayList.indexOf(next) == -1) {
                                    arrayList.add(next);
                                }
                            }
                            arrayList.remove(this.f21682b);
                            arrayList.add(this.f21682b);
                        }
                    }
                    return arrayList;
                }

                @Override // com.xiaomi.push.C11228cc
                /* renamed from: a */
                public synchronized void mo4543a(String str2, C11227cb c11227cb) {
                    if (this.f21711a != null) {
                        this.f21711a.mo4543a(str2, c11227cb);
                    }
                }
            } : m4553c;
        }
        return null;
    }

    /* renamed from: c */
    protected C11228cc m4553c(String str) {
        C11229cd c11229cd;
        C11228cc m4585a;
        synchronized (this.f21706a) {
            m4570a();
            c11229cd = this.f21706a.get(str);
        }
        if (c11229cd == null || (m4585a = c11229cd.m4585a()) == null) {
            return null;
        }
        return m4585a;
    }

    /* renamed from: d */
    public C11228cc m4550d(String str) {
        C11228cc c11228cc;
        synchronized (f21699b) {
            c11228cc = f21699b.get(str);
        }
        return c11228cc;
    }

    /* renamed from: e */
    protected C11228cc m4547e(String str) {
        if (System.currentTimeMillis() - this.f21709c > this.f21702a * 60 * 1000) {
            this.f21709c = System.currentTimeMillis();
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(str);
            C11228cc c11228cc = m4560a(arrayList).get(0);
            if (c11228cc != null) {
                this.f21702a = 0L;
                return c11228cc;
            }
            long j = this.f21702a;
            if (j < 15) {
                this.f21702a = j + 1;
                return null;
            }
            return null;
        }
        return null;
    }

    /* renamed from: a */
    private ArrayList<C11228cc> m4560a(ArrayList<String> arrayList) {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        m4548e();
        synchronized (this.f21706a) {
            m4570a();
            for (String str : this.f21706a.keySet()) {
                if (!arrayList.contains(str)) {
                    arrayList.add(str);
                }
            }
        }
        synchronized (f21699b) {
            for (Object obj : f21699b.values().toArray()) {
                C11228cc c11228cc = (C11228cc) obj;
                if (!c11228cc.mo4541b()) {
                    f21699b.remove(c11228cc.f21682b);
                }
            }
        }
        if (!arrayList.contains(m4559b())) {
            arrayList.add(m4559b());
        }
        ArrayList<C11228cc> arrayList2 = new ArrayList<>(arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList2.add(null);
        }
        try {
            String str2 = C11169au.m4833d(f21695a) ? "wifi" : "wap";
            String mo2657a = mo2657a(arrayList, str2, this.f21705a, true);
            if (!TextUtils.isEmpty(mo2657a)) {
                JSONObject jSONObject3 = new JSONObject(mo2657a);
                AbstractC11049b.m5274b(mo2657a);
                if ("OK".equalsIgnoreCase(jSONObject3.getString("S"))) {
                    JSONObject jSONObject4 = jSONObject3.getJSONObject("R");
                    String string = jSONObject4.getString("province");
                    String string2 = jSONObject4.getString("city");
                    String string3 = jSONObject4.getString("isp");
                    String string4 = jSONObject4.getString("ip");
                    String string5 = jSONObject4.getString("country");
                    JSONObject jSONObject5 = jSONObject4.getJSONObject(str2);
                    StringBuilder sb = new StringBuilder();
                    sb.append("get bucket: net=");
                    sb.append(string3);
                    sb.append(", hosts=");
                    sb.append(!(jSONObject5 instanceof JSONObject) ? jSONObject5.toString() : NBSJSONObjectInstrumentation.toString(jSONObject5));
                    AbstractC11049b.m5270c(sb.toString());
                    int i2 = 0;
                    while (i2 < arrayList.size()) {
                        String str3 = arrayList.get(i2);
                        JSONArray optJSONArray = jSONObject5.optJSONArray(str3);
                        if (optJSONArray == null) {
                            AbstractC11049b.m5282a("no bucket found for " + str3);
                            jSONObject = jSONObject5;
                        } else {
                            C11228cc c11228cc2 = new C11228cc(str3);
                            int i3 = 0;
                            while (i3 < optJSONArray.length()) {
                                String string6 = optJSONArray.getString(i3);
                                if (TextUtils.isEmpty(string6)) {
                                    jSONObject2 = jSONObject5;
                                } else {
                                    jSONObject2 = jSONObject5;
                                    c11228cc2.m4598a(new C11240cj(string6, optJSONArray.length() - i3));
                                }
                                i3++;
                                jSONObject5 = jSONObject2;
                            }
                            jSONObject = jSONObject5;
                            arrayList2.set(i2, c11228cc2);
                            c11228cc2.f21687g = string5;
                            c11228cc2.f21683c = string;
                            c11228cc2.f21685e = string3;
                            c11228cc2.f21686f = string4;
                            c11228cc2.f21684d = string2;
                            if (jSONObject4.has("stat-percent")) {
                                c11228cc2.m4601a(jSONObject4.getDouble("stat-percent"));
                            }
                            if (jSONObject4.has("stat-domain")) {
                                c11228cc2.m4590b(jSONObject4.getString("stat-domain"));
                            }
                            if (jSONObject4.has("ttl")) {
                                c11228cc2.m4600a(jSONObject4.getInt("ttl") * 1000);
                            }
                            m4564a(c11228cc2.m4605a());
                        }
                        i2++;
                        jSONObject5 = jSONObject;
                    }
                    JSONObject optJSONObject = jSONObject4.optJSONObject("reserved");
                    if (optJSONObject != null) {
                        long j = jSONObject4.has("reserved-ttl") ? jSONObject4.getInt("reserved-ttl") * 1000 : 604800000L;
                        Iterator<String> keys = optJSONObject.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            JSONArray optJSONArray2 = optJSONObject.optJSONArray(next);
                            if (optJSONArray2 == null) {
                                AbstractC11049b.m5282a("no bucket found for " + next);
                            } else {
                                C11228cc c11228cc3 = new C11228cc(next);
                                c11228cc3.m4600a(j);
                                for (int i4 = 0; i4 < optJSONArray2.length(); i4++) {
                                    String string7 = optJSONArray2.getString(i4);
                                    if (!TextUtils.isEmpty(string7)) {
                                        c11228cc3.m4598a(new C11240cj(string7, optJSONArray2.length() - i4));
                                    }
                                }
                                synchronized (f21699b) {
                                    if (this.f21703a.mo4544a(next)) {
                                        f21699b.put(next, c11228cc3);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            AbstractC11049b.m5282a("failed to get bucket " + e.getMessage());
        }
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            C11228cc c11228cc4 = arrayList2.get(i5);
            if (c11228cc4 != null) {
                m4563a(arrayList.get(i5), c11228cc4);
            }
        }
        m4554c();
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public String mo2657a(ArrayList<String> arrayList, String str, String str2, boolean z) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        ArrayList<InterfaceC11168at> arrayList3 = new ArrayList();
        arrayList3.add(new C11166ar("type", str));
        if (str.equals("wap")) {
            arrayList3.add(new C11166ar("conpt", m4565a(C11169au.m4850a(f21695a))));
        }
        if (z) {
            arrayList3.add(new C11166ar("reserved", "1"));
        }
        arrayList3.add(new C11166ar("uuid", str2));
        arrayList3.add(new C11166ar("list", C11184bb.m4753a(arrayList, ",")));
        arrayList3.add(new C11166ar("countrycode", C11578b.m2591a(f21695a).m2587b()));
        arrayList3.add(new C11166ar("push_sdk_vc", String.valueOf(50909)));
        String m4559b = m4559b();
        C11228cc m4553c = m4553c(m4559b);
        String format = String.format(Locale.US, "https://%1$s/gslb/?ver=5.0", m4559b);
        if (m4553c == null) {
            arrayList2.add(format);
            synchronized (f21699b) {
                C11228cc c11228cc = f21699b.get(m4559b);
                if (c11228cc != null) {
                    Iterator<String> it = c11228cc.mo4542a(true).iterator();
                    while (it.hasNext()) {
                        arrayList2.add(String.format(Locale.US, "https://%1$s/gslb/?ver=5.0", it.next()));
                    }
                }
            }
        } else {
            arrayList2 = m4553c.m4597a(format);
        }
        Iterator<String> it2 = arrayList2.iterator();
        IOException e = null;
        while (it2.hasNext()) {
            Uri.Builder buildUpon = Uri.parse(it2.next()).buildUpon();
            for (InterfaceC11168at interfaceC11168at : arrayList3) {
                buildUpon.appendQueryParameter(interfaceC11168at.mo4858a(), interfaceC11168at.mo4857b());
            }
            try {
                if (this.f21704a == null) {
                    return C11169au.m4845a(f21695a, new URL(buildUpon.toString()));
                }
                return this.f21704a.mo2658a(buildUpon.toString());
            } catch (IOException e2) {
                e = e2;
            }
        }
        if (e == null) {
            return null;
        }
        AbstractC11049b.m5282a("network exception: " + e.getMessage());
        throw e;
    }

    /* renamed from: a */
    public void m4571a() {
        synchronized (this.f21706a) {
            this.f21706a.clear();
        }
    }

    /* renamed from: c */
    public String m4555c() {
        StringBuilder sb = new StringBuilder();
        synchronized (this.f21706a) {
            for (Map.Entry<String, C11229cd> entry : this.f21706a.entrySet()) {
                sb.append(entry.getKey());
                sb.append(":\n");
                sb.append(entry.getValue().toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    public void m4563a(String str, C11228cc c11228cc) {
        if (TextUtils.isEmpty(str) || c11228cc == null) {
            throw new IllegalArgumentException("the argument is invalid " + str + ", " + c11228cc);
        } else if (this.f21703a.mo4544a(str)) {
            synchronized (this.f21706a) {
                m4570a();
                if (this.f21706a.containsKey(str)) {
                    this.f21706a.get(str).m4581a(c11228cc);
                } else {
                    C11229cd c11229cd = new C11229cd(str);
                    c11229cd.m4581a(c11228cc);
                    this.f21706a.put(str, c11229cd);
                }
            }
        }
    }

    /* renamed from: b */
    public void m4558b() {
        ArrayList<String> arrayList;
        synchronized (this.f21706a) {
            m4570a();
            arrayList = new ArrayList<>(this.f21706a.keySet());
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                C11229cd c11229cd = this.f21706a.get(arrayList.get(size));
                if (c11229cd != null && c11229cd.m4585a() != null) {
                    arrayList.remove(size);
                }
            }
        }
        ArrayList<C11228cc> m4560a = m4560a(arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            if (m4560a.get(i) != null) {
                m4563a(arrayList.get(i), m4560a.get(i));
            }
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: d */
    protected java.lang.String m4552d() {
        /*
            r7 = this;
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L64
            android.content.Context r2 = com.xiaomi.push.C11232cg.f21695a     // Catch: java.lang.Throwable -> L64
            java.io.File r2 = r2.getFilesDir()     // Catch: java.lang.Throwable -> L64
            java.lang.String r3 = r7.m4546f()     // Catch: java.lang.Throwable -> L64
            r1.<init>(r2, r3)     // Catch: java.lang.Throwable -> L64
            boolean r2 = r1.isFile()     // Catch: java.lang.Throwable -> L64
            if (r2 == 0) goto L58
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L64
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L64
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L55
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L55
            byte[] r3 = com.xiaomi.push.C11647w.m2269a(r1)     // Catch: java.lang.Throwable -> L4e
            java.lang.String r4 = new java.lang.String     // Catch: java.lang.Throwable -> L4e
            byte[] r5 = r7.m4569a()     // Catch: java.lang.Throwable -> L4e
            byte[] r3 = com.xiaomi.push.C11425h.m3410a(r5, r3)     // Catch: java.lang.Throwable -> L4e
            java.nio.charset.Charset r5 = java.nio.charset.StandardCharsets.UTF_8     // Catch: java.lang.Throwable -> L4e
            r4.<init>(r3, r5)     // Catch: java.lang.Throwable -> L4e
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4e
            r3.<init>()     // Catch: java.lang.Throwable -> L4e
            java.lang.String r5 = "load host fallbacks = "
            r3.append(r5)     // Catch: java.lang.Throwable -> L4e
            r3.append(r4)     // Catch: java.lang.Throwable -> L4e
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L4e
            com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5274b(r3)     // Catch: java.lang.Throwable -> L4e
            com.xiaomi.push.C11647w.m2274a(r1)
            com.xiaomi.push.C11647w.m2274a(r2)
            return r4
        L4e:
            r3 = move-exception
            goto L67
        L50:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L87
        L55:
            r3 = move-exception
            r1 = r0
            goto L67
        L58:
            com.xiaomi.push.C11647w.m2274a(r0)
            com.xiaomi.push.C11647w.m2274a(r0)
            goto L85
        L5f:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
            goto L87
        L64:
            r3 = move-exception
            r1 = r0
            r2 = r1
        L67:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L86
            r4.<init>()     // Catch: java.lang.Throwable -> L86
            java.lang.String r5 = "load host exception "
            r4.append(r5)     // Catch: java.lang.Throwable -> L86
            java.lang.String r3 = r3.getMessage()     // Catch: java.lang.Throwable -> L86
            r4.append(r3)     // Catch: java.lang.Throwable -> L86
            java.lang.String r3 = r4.toString()     // Catch: java.lang.Throwable -> L86
            com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5282a(r3)     // Catch: java.lang.Throwable -> L86
            com.xiaomi.push.C11647w.m2274a(r1)
            com.xiaomi.push.C11647w.m2274a(r2)
        L85:
            return r0
        L86:
            r0 = move-exception
        L87:
            com.xiaomi.push.C11647w.m2274a(r1)
            com.xiaomi.push.C11647w.m2274a(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C11232cg.m4552d():java.lang.String");
    }

    /* renamed from: a */
    protected boolean m4570a() {
        synchronized (this.f21706a) {
            if (f21698a) {
                return true;
            }
            f21698a = true;
            this.f21706a.clear();
            String m4552d = m4552d();
            if (TextUtils.isEmpty(m4552d)) {
                return false;
            }
            m4556b(m4552d);
            AbstractC11049b.m5274b("loading the new hosts succeed");
            return true;
        }
    }

    /* renamed from: a */
    public static void m4562a(String str, String str2) {
        C11228cc c11228cc = f21699b.get(str);
        synchronized (f21699b) {
            if (c11228cc == null) {
                C11228cc c11228cc2 = new C11228cc(str);
                c11228cc2.m4600a(604800000L);
                c11228cc2.m4596a(str2);
                f21699b.put(str, c11228cc2);
            } else {
                c11228cc.m4596a(str2);
            }
        }
    }

    /* renamed from: c */
    public void m4554c() {
        FileOutputStream fileOutputStream;
        synchronized (this.f21706a) {
            BufferedOutputStream bufferedOutputStream = null;
            try {
                JSONObject m4572a = m4572a();
                String jSONObject = !(m4572a instanceof JSONObject) ? m4572a.toString() : NBSJSONObjectInstrumentation.toString(m4572a);
                AbstractC11049b.m5274b("persist host fallbacks = " + jSONObject);
                if (TextUtils.isEmpty(jSONObject)) {
                    fileOutputStream = null;
                } else {
                    fileOutputStream = f21695a.openFileOutput(m4546f(), 0);
                    try {
                        try {
                            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(fileOutputStream);
                            try {
                                bufferedOutputStream2.write(C11425h.m3409b(m4569a(), jSONObject.getBytes(StandardCharsets.UTF_8)));
                                bufferedOutputStream2.flush();
                                bufferedOutputStream = bufferedOutputStream2;
                            } catch (Exception e) {
                                e = e;
                                bufferedOutputStream = bufferedOutputStream2;
                                AbstractC11049b.m5282a("persist bucket failure: " + e.getMessage());
                                C11647w.m2274a(bufferedOutputStream);
                                C11647w.m2274a(fileOutputStream);
                            } catch (Throwable th) {
                                th = th;
                                bufferedOutputStream = bufferedOutputStream2;
                                C11647w.m2274a(bufferedOutputStream);
                                C11647w.m2274a(fileOutputStream);
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (Exception e2) {
                        e = e2;
                    }
                }
                C11647w.m2274a(bufferedOutputStream);
            } catch (Exception e3) {
                e = e3;
                fileOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
            }
            C11647w.m2274a(fileOutputStream);
        }
    }

    /* renamed from: d */
    public void m4551d() {
        String m4549e = m4549e();
        try {
            File file = new File(f21695a.getFilesDir(), m4549e);
            if (file.exists()) {
                boolean delete = file.delete();
                StringBuilder sb = new StringBuilder();
                sb.append("Delete old host fallbacks file ");
                sb.append(m4549e);
                sb.append(delete ? " successful." : " failed.");
                AbstractC11049b.m5282a(sb.toString());
            } else {
                AbstractC11049b.m5274b("Old host fallbacks file " + m4549e + " does not exist.");
            }
        } catch (Exception e) {
            AbstractC11049b.m5282a("Delete old host fallbacks file " + m4549e + " error: " + e.getMessage());
        }
    }

    /* renamed from: e */
    protected String m4549e() {
        if ("com.xiaomi.xmsf".equals(f21700c)) {
            return f21700c;
        }
        return f21700c + ":pushservice";
    }

    /* renamed from: a */
    private byte[] m4569a() {
        return C11183ba.m4760a(f21695a.getPackageName() + "_key_salt");
    }

    /* renamed from: g */
    private String m4545g() {
        try {
            PackageInfo packageInfo = f21695a.getPackageManager().getPackageInfo(f21695a.getPackageName(), 16384);
            return packageInfo != null ? packageInfo.versionName : "0";
        } catch (Exception unused) {
            return "0";
        }
    }

    /* renamed from: e */
    public void m4548e() {
        synchronized (this.f21706a) {
            for (C11229cd c11229cd : this.f21706a.values()) {
                c11229cd.m4579a(true);
            }
            boolean z = false;
            while (!z) {
                Iterator<String> it = this.f21706a.keySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = true;
                        break;
                    }
                    String next = it.next();
                    if (this.f21706a.get(next).m4583a().isEmpty()) {
                        this.f21706a.remove(next);
                        z = false;
                        break;
                    }
                }
            }
        }
    }

    /* renamed from: a */
    static String m4565a(String str) {
        try {
            int length = str.length();
            byte[] bytes = str.getBytes("UTF-8");
            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                int i2 = b & 240;
                if (i2 != 240) {
                    bytes[i] = (byte) (((b & 15) ^ ((byte) (((b >> 4) + length) & 15))) | i2);
                }
            }
            return new String(bytes);
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    /* renamed from: a */
    public void m4564a(String str) {
        this.f21708b = str;
    }

    /* renamed from: a */
    protected JSONObject m4572a() {
        JSONObject jSONObject;
        synchronized (this.f21706a) {
            jSONObject = new JSONObject();
            jSONObject.put("ver", 2);
            JSONArray jSONArray = new JSONArray();
            for (C11229cd c11229cd : this.f21706a.values()) {
                jSONArray.put(c11229cd.m4582a());
            }
            jSONObject.put("data", jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            for (C11228cc c11228cc : f21699b.values()) {
                jSONArray2.put(c11228cc.m4603a());
            }
            jSONObject.put("reserved", jSONArray2);
        }
        return jSONObject;
    }

    /* renamed from: b */
    protected void m4556b(String str) {
        synchronized (this.f21706a) {
            this.f21706a.clear();
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt("ver") != 2) {
                throw new JSONException("Bad version");
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    C11229cd m4580a = new C11229cd().m4580a(optJSONArray.getJSONObject(i));
                    this.f21706a.put(m4580a.m4584a(), m4580a);
                }
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("reserved");
            if (optJSONArray2 != null) {
                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                    JSONObject jSONObject2 = optJSONArray2.getJSONObject(i2);
                    String optString = jSONObject2.optString("host");
                    if (!TextUtils.isEmpty(optString)) {
                        try {
                            C11228cc m4592a = new C11228cc(optString).m4592a(jSONObject2);
                            f21699b.put(m4592a.f21682b, m4592a);
                            AbstractC11049b.m5282a("load local reserved host for " + m4592a.f21682b);
                        } catch (JSONException unused) {
                            AbstractC11049b.m5282a("parse reserved host fail.");
                        }
                    }
                }
            }
        }
    }
}
