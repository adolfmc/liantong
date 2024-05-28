package com.networkbench.agent.impl.socket;

import android.text.TextUtils;
import com.networkbench.agent.impl.p254f.C6395g;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.socket.o */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6618o {

    /* renamed from: b */
    private static final InterfaceC6393e f17035b = new C6395g();

    /* renamed from: a */
    public final ConcurrentHashMap<String, C6617n> f17036a = new ConcurrentHashMap<>();

    /* renamed from: a */
    public void m9227a(String str, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        C6617n c6617n = new C6617n();
        c6617n.m9231a(i);
        c6617n.m9229b(i2);
        this.f17036a.put(str, c6617n);
    }

    /* renamed from: a */
    public boolean m9228a(String str) {
        return (str == null || this.f17036a.get(str) == null || this.f17036a.get(str) == null) ? false : true;
    }

    /* renamed from: b */
    public C6617n m9226b(String str) {
        if (str == null || this.f17036a.get(str) == null) {
            return null;
        }
        return this.f17036a.get(str);
    }

    /* renamed from: c */
    public int m9225c(String str) {
        if (str == null || this.f17036a.get(str) == null) {
            return -1;
        }
        return this.f17036a.get(str).m9232a();
    }

    /* renamed from: d */
    public int m9224d(String str) {
        if (str == null || this.f17036a.get(str) == null) {
            return -1;
        }
        return this.f17036a.get(str).m9230b();
    }

    /* renamed from: e */
    public C6617n m9223e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (Map.Entry<String, C6617n> entry : this.f17036a.entrySet()) {
            if (str.contains(entry.getKey().toString())) {
                return entry.getValue();
            }
        }
        return null;
    }
}
