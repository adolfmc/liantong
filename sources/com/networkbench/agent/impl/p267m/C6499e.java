package com.networkbench.agent.impl.p267m;

import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.m.e */
/* loaded from: E:\11480076_dexfile_execute.dex */
final class C6499e {

    /* renamed from: a */
    private String f16456a;

    /* renamed from: b */
    private String f16457b;

    /* renamed from: c */
    private Long f16458c;

    /* renamed from: d */
    private String f16459d;

    /* renamed from: e */
    private String f16460e;

    /* renamed from: f */
    private Map<String, Object> f16461f;

    public C6499e(String str, String str2, Long l, String str3, String str4, Map<String, Object> map) {
        this.f16456a = str;
        this.f16457b = str2;
        this.f16458c = l;
        this.f16459d = str3;
        this.f16460e = str4;
        if (map != null) {
            this.f16461f = new HashMap();
            this.f16461f.putAll(map);
        }
    }

    /* renamed from: a */
    public String m9748a() {
        return this.f16456a;
    }

    /* renamed from: b */
    public String m9747b() {
        String str = this.f16457b;
        return str == null ? "" : str;
    }

    /* renamed from: c */
    public Long m9746c() {
        Long l = this.f16458c;
        return Long.valueOf(l == null ? -1L : l.longValue());
    }

    /* renamed from: d */
    public String m9745d() {
        String str = this.f16459d;
        return str == null ? "" : str;
    }

    /* renamed from: e */
    public String m9744e() {
        String str = this.f16460e;
        return str == null ? "" : str;
    }

    /* renamed from: f */
    public Map<String, Object> m9743f() {
        return this.f16461f;
    }
}
