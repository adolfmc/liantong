package com.bytedance.applog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.l2 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3622l2 implements IEventObserver {

    /* renamed from: b */
    public static volatile C3622l2 f8549b;

    /* renamed from: a */
    public final CopyOnWriteArraySet<IEventObserver> f8550a = new CopyOnWriteArraySet<>();

    /* renamed from: a */
    public static C3622l2 m17245a() {
        if (f8549b == null) {
            synchronized (C3622l2.class) {
                if (f8549b == null) {
                    f8549b = new C3622l2();
                }
            }
        }
        return f8549b;
    }

    /* renamed from: a */
    public void m17244a(IEventObserver iEventObserver) {
        if (iEventObserver != null) {
            this.f8550a.add(iEventObserver);
        }
    }

    /* renamed from: b */
    public void m17243b(IEventObserver iEventObserver) {
        if (iEventObserver != null) {
            this.f8550a.remove(iEventObserver);
        }
    }

    @Override // com.bytedance.applog.IEventObserver
    public void onEvent(@NonNull String str, @NonNull String str2, String str3, long j, long j2, String str4) {
        Iterator<IEventObserver> it = this.f8550a.iterator();
        while (it.hasNext()) {
            it.next().onEvent(str, str2, str3, j, j2, str4);
        }
    }

    @Override // com.bytedance.applog.IEventObserver
    public void onEventV3(@NonNull String str, @Nullable JSONObject jSONObject) {
        Iterator<IEventObserver> it = this.f8550a.iterator();
        while (it.hasNext()) {
            it.next().onEventV3(str, jSONObject);
        }
    }
}
