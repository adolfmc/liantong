package com.networkbench.agent.impl.instrumentation.p263io;

import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.instrumentation.io.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
class C6481a {

    /* renamed from: a */
    private boolean f16384a = false;

    /* renamed from: b */
    private ArrayList<NBSStreamCompleteListener> f16385b = new ArrayList<>();

    /* renamed from: a */
    public boolean m9858a() {
        boolean z;
        synchronized (this) {
            z = this.f16384a;
        }
        return z;
    }

    /* renamed from: a */
    public void m9856a(NBSStreamCompleteListener nBSStreamCompleteListener) {
        ArrayList<NBSStreamCompleteListener> arrayList = this.f16385b;
        if (arrayList == null) {
            return;
        }
        synchronized (arrayList) {
            this.f16385b.add(nBSStreamCompleteListener);
        }
    }

    /* renamed from: b */
    public void m9853b(NBSStreamCompleteListener nBSStreamCompleteListener) {
        ArrayList<NBSStreamCompleteListener> arrayList = this.f16385b;
        if (arrayList == null) {
            return;
        }
        synchronized (arrayList) {
            this.f16385b.remove(nBSStreamCompleteListener);
        }
    }

    /* renamed from: a */
    public void m9857a(NBSStreamCompleteEvent nBSStreamCompleteEvent) {
        if (m9855b()) {
            return;
        }
        List<NBSStreamCompleteListener> m9852c = m9852c();
        if (m9852c != null || m9852c.size() > 0) {
            for (NBSStreamCompleteListener nBSStreamCompleteListener : m9852c) {
                nBSStreamCompleteListener.streamComplete(nBSStreamCompleteEvent);
            }
        }
    }

    /* renamed from: b */
    public void m9854b(NBSStreamCompleteEvent nBSStreamCompleteEvent) {
        if (m9855b()) {
            return;
        }
        List<NBSStreamCompleteListener> m9852c = m9852c();
        if (m9852c != null || m9852c.size() > 0) {
            for (NBSStreamCompleteListener nBSStreamCompleteListener : m9852c) {
                nBSStreamCompleteListener.streamError(nBSStreamCompleteEvent);
            }
        }
    }

    /* renamed from: b */
    private boolean m9855b() {
        boolean m9858a;
        synchronized (this) {
            m9858a = m9858a();
            if (!m9858a) {
                this.f16384a = true;
            }
        }
        return m9858a;
    }

    /* renamed from: c */
    private List<NBSStreamCompleteListener> m9852c() {
        ArrayList arrayList;
        synchronized (this.f16385b) {
            arrayList = new ArrayList(this.f16385b);
            this.f16385b.clear();
        }
        return arrayList;
    }
}
