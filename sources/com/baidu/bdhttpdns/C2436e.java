package com.baidu.bdhttpdns;

import android.util.LruCache;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.bdhttpdns.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2436e {

    /* renamed from: a */
    private final String f4321a;

    /* renamed from: b */
    private final LruCache<String, C2437a> f4322b = new LruCache<>(((int) Runtime.getRuntime().maxMemory()) / 16);

    /* renamed from: c */
    private boolean f4323c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.bdhttpdns.e$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2437a {

        /* renamed from: a */
        private ArrayList<String> f4324a;

        /* renamed from: b */
        private long f4325b;

        /* renamed from: c */
        private long f4326c;

        /* renamed from: a */
        public void m20127a(long j) {
            this.f4325b = j;
        }

        /* renamed from: a */
        public void m20126a(ArrayList<String> arrayList) {
            this.f4324a = arrayList;
        }

        /* renamed from: a */
        public boolean m20128a() {
            return m20122d() + this.f4325b < System.currentTimeMillis() / 1000;
        }

        /* renamed from: b */
        public ArrayList<String> m20125b() {
            return this.f4324a;
        }

        /* renamed from: b */
        public void m20124b(long j) {
            this.f4326c = j;
        }

        /* renamed from: c */
        public long m20123c() {
            return this.f4325b;
        }

        /* renamed from: d */
        public long m20122d() {
            return this.f4326c;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2436e(String str, boolean z) {
        this.f4323c = false;
        this.f4321a = str;
        this.f4323c = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public C2437a m20133a(String str) {
        C2437a c2437a = this.f4322b.get(str);
        if (c2437a != null && c2437a.m20128a() && this.f4323c) {
            this.f4322b.remove(str);
            C2444h.m20103a("Remove expired entry from %s cache while reading, host(%s)", this.f4321a, str);
            return null;
        }
        return c2437a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m20134a() {
        this.f4322b.evictAll();
        C2444h.m20103a("Clear %s cache", this.f4321a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m20132a(String str, C2437a c2437a) {
        ArrayList<String> m20125b = c2437a.m20125b();
        if (m20125b == null || m20125b.isEmpty()) {
            return;
        }
        this.f4322b.put(str, c2437a);
        C2444h.m20103a("Set entry to %s cache, host(%s), ipList(%s), ttl(%d)", this.f4321a, str, m20125b.toString(), Long.valueOf(c2437a.m20123c()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m20131a(boolean z) {
        this.f4323c = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public ArrayList<String> m20130b() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str : this.f4322b.snapshot().keySet()) {
            arrayList.add(str);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m20129b(String str) {
        C2437a m20133a = m20133a(str);
        if (m20133a == null || !m20133a.m20128a()) {
            return;
        }
        this.f4322b.remove(str);
        C2444h.m20103a("Remove expired entry from %s cache, host(%s)", this.f4321a, str);
    }
}
