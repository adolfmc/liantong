package com.bytedance.applog;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.s2 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3688s2 implements ISessionObserver {

    /* renamed from: b */
    public static volatile C3688s2 f8809b;

    /* renamed from: a */
    public final CopyOnWriteArraySet<ISessionObserver> f8810a = new CopyOnWriteArraySet<>();

    /* renamed from: a */
    public static C3688s2 m17118a() {
        if (f8809b == null) {
            synchronized (C3688s2.class) {
                f8809b = new C3688s2();
            }
        }
        return f8809b;
    }

    /* renamed from: a */
    public void m17117a(ISessionObserver iSessionObserver) {
        if (iSessionObserver != null) {
            this.f8810a.add(iSessionObserver);
        }
    }

    /* renamed from: b */
    public void m17116b(ISessionObserver iSessionObserver) {
        if (iSessionObserver != null) {
            this.f8810a.remove(iSessionObserver);
        }
    }

    @Override // com.bytedance.applog.ISessionObserver
    public void onSessionBatchEvent(long j, String str, JSONObject jSONObject) {
        Iterator<ISessionObserver> it = this.f8810a.iterator();
        while (it.hasNext()) {
            it.next().onSessionBatchEvent(j, str, jSONObject);
        }
    }

    @Override // com.bytedance.applog.ISessionObserver
    public void onSessionStart(long j, String str) {
        Iterator<ISessionObserver> it = this.f8810a.iterator();
        while (it.hasNext()) {
            it.next().onSessionStart(j, str);
        }
    }

    @Override // com.bytedance.applog.ISessionObserver
    public void onSessionTerminate(long j, String str, JSONObject jSONObject) {
        Iterator<ISessionObserver> it = this.f8810a.iterator();
        while (it.hasNext()) {
            it.next().onSessionTerminate(j, str, jSONObject);
        }
    }
}
