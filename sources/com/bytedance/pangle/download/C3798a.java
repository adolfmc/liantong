package com.bytedance.pangle.download;

import android.app.Activity;
import com.bytedance.pangle.C3768a;
import com.bytedance.pangle.Zeus;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.download.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3798a {

    /* renamed from: b */
    private static volatile C3798a f9092b;

    /* renamed from: a */
    public final List<String> f9093a = new CopyOnWriteArrayList();

    public C3798a() {
        Zeus.getAppApplication().registerActivityLifecycleCallbacks(new C3768a() { // from class: com.bytedance.pangle.download.a.1
            @Override // com.bytedance.pangle.C3768a, android.app.Application.ActivityLifecycleCallbacks
            public final void onActivityResumed(Activity activity) {
                Iterator it = C3798a.this.f9093a.iterator();
                while (it.hasNext()) {
                    it.next();
                    C3800b.m16911a();
                }
            }
        });
    }

    /* renamed from: a */
    public static C3798a m16913a() {
        if (f9092b == null) {
            synchronized (C3798a.class) {
                if (f9092b == null) {
                    f9092b = new C3798a();
                }
            }
        }
        return f9092b;
    }
}
