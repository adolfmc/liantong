package com.networkbench.agent.impl.socket;

import android.text.TextUtils;
import com.networkbench.agent.impl.p254f.C6395g;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.socket.r */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6621r {

    /* renamed from: c */
    private static final ConcurrentHashMap<String, C6622a> f17048c = new ConcurrentHashMap<>();

    /* renamed from: a */
    public static final ConcurrentHashMap<String, C6623b> f17046a = new ConcurrentHashMap<>();

    /* renamed from: b */
    public static final Map<String, String> f17047b = new ConcurrentHashMap();

    /* renamed from: d */
    private static final InterfaceC6393e f17049d = new C6395g();

    /* renamed from: e */
    private static final C6618o f17050e = new C6618o();

    /* renamed from: a */
    public static void m9201a(String str, int i) {
        if (TextUtils.isEmpty(str) || i < 0) {
            return;
        }
        f17046a.put(str, new C6623b(i));
    }

    /* renamed from: a */
    public static int m9202a(String str) {
        if (str == null || f17046a.get(str) == null || f17046a.get(str).f17054b) {
            return -1;
        }
        f17046a.get(str).f17054b = true;
        return f17046a.get(str).f17053a;
    }

    /* renamed from: b */
    public static void m9198b(String str, int i) {
        if (TextUtils.isEmpty(str) || i < 0 || f17048c.get(str) != null) {
            return;
        }
        f17048c.put(str, new C6622a(i));
    }

    /* renamed from: b */
    public static int m9199b(String str) {
        if (str == null || f17048c.get(str) == null || f17048c.get(str).f17052b) {
            return -1;
        }
        f17048c.get(str).f17052b = true;
        return f17048c.get(str).f17051a;
    }

    /* renamed from: a */
    public static C6618o m9203a() {
        return f17050e;
    }

    /* renamed from: a */
    public static void m9200a(String str, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        InterfaceC6393e interfaceC6393e = f17049d;
        interfaceC6393e.mo10122a("hostName:" + str + ", firstPackageTime:" + i + ", remainPackageTime:" + i2);
        if (f17050e.m9228a(str)) {
            f17050e.m9226b(str).m9231a(i);
            f17050e.m9226b(str).m9229b(i2);
            return;
        }
        f17050e.m9227a(str, i, i2);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.socket.r$b */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C6623b {

        /* renamed from: a */
        public int f17053a;

        /* renamed from: b */
        public boolean f17054b = false;

        public C6623b(int i) {
            this.f17053a = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.socket.r$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C6622a {

        /* renamed from: a */
        int f17051a;

        /* renamed from: b */
        boolean f17052b = false;

        public C6622a(int i) {
            this.f17051a = i;
        }
    }
}
