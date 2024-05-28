package com.bytedance.applog;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.e2 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3577e2 implements IDataObserver {

    /* renamed from: b */
    public static volatile C3577e2 f8435b;

    /* renamed from: a */
    public final CopyOnWriteArraySet<IDataObserver> f8436a = new CopyOnWriteArraySet<>();

    /* renamed from: a */
    public static C3577e2 m17309a() {
        if (f8435b == null) {
            synchronized (C3577e2.class) {
                if (f8435b == null) {
                    f8435b = new C3577e2();
                }
            }
        }
        return f8435b;
    }

    /* renamed from: a */
    public void m17308a(IDataObserver iDataObserver) {
        if (iDataObserver != null) {
            this.f8436a.add(iDataObserver);
        }
    }

    /* renamed from: b */
    public void m17307b(IDataObserver iDataObserver) {
        if (iDataObserver != null) {
            this.f8436a.remove(iDataObserver);
        }
    }

    @Override // com.bytedance.applog.IDataObserver
    public void onAbVidsChange(String str, String str2) {
        Iterator<IDataObserver> it = this.f8436a.iterator();
        while (it.hasNext()) {
            it.next().onAbVidsChange(str, str2);
        }
    }

    @Override // com.bytedance.applog.IDataObserver
    public void onIdLoaded(String str, String str2, String str3) {
        Iterator<IDataObserver> it = this.f8436a.iterator();
        while (it.hasNext()) {
            it.next().onIdLoaded(str, str2, str3);
        }
    }

    @Override // com.bytedance.applog.IDataObserver
    public void onRemoteAbConfigGet(boolean z, JSONObject jSONObject) {
        Iterator<IDataObserver> it = this.f8436a.iterator();
        while (it.hasNext()) {
            it.next().onRemoteAbConfigGet(z, jSONObject);
        }
    }

    @Override // com.bytedance.applog.IDataObserver
    public void onRemoteConfigGet(boolean z, JSONObject jSONObject) {
        Iterator<IDataObserver> it = this.f8436a.iterator();
        while (it.hasNext()) {
            it.next().onRemoteConfigGet(z, jSONObject);
        }
    }

    @Override // com.bytedance.applog.IDataObserver
    public void onRemoteIdGet(boolean z, String str, String str2, String str3, String str4, String str5, String str6) {
        Iterator<IDataObserver> it = this.f8436a.iterator();
        while (it.hasNext()) {
            it.next().onRemoteIdGet(z, str, str2, str3, str4, str5, str6);
        }
    }
}
