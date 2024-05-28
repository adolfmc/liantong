package com.sdk.p286b;

import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: com.sdk.b.c */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C6966c<K, V> {

    /* renamed from: a */
    public final LinkedHashMap<K, V> f18054a;

    /* renamed from: b */
    public int f18055b;

    /* renamed from: c */
    public int f18056c;

    /* renamed from: d */
    public C6965b<K, Long> f18057d;

    public C6966c(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.f18056c = i;
        this.f18054a = new LinkedHashMap<>(0, 0.75f, true);
        this.f18057d = new C6965b<>(0, 0.75f);
    }

    /* renamed from: a */
    public final int m8188a(K k, V v) {
        String str = (String) k;
        String str2 = (String) v;
        int length = str2 == null ? 0 : str2.length();
        if (length <= 0) {
            this.f18055b = 0;
            for (Map.Entry<K, V> entry : this.f18054a.entrySet()) {
                int i = this.f18055b;
                String str3 = (String) entry.getKey();
                String str4 = (String) entry.getValue();
                this.f18055b = i + (str4 == null ? 0 : str4.length());
            }
        }
        return length;
    }

    /* renamed from: a */
    public final void m8189a(int i) {
        while (true) {
            synchronized (this) {
                if (this.f18055b <= i || this.f18054a.isEmpty()) {
                    break;
                }
                Map.Entry<K, V> next = this.f18054a.entrySet().iterator().next();
                K key = next.getKey();
                V value = next.getValue();
                this.f18054a.remove(key);
                this.f18057d.m8191a(key);
                this.f18055b -= m8188a(key, value);
            }
        }
    }
}
