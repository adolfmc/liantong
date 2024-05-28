package com.huawei.agconnect.core.p211a;

import com.huawei.agconnect.AGCInitFinishManager;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.agconnect.core.a.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4784a extends AGCInitFinishManager {

    /* renamed from: a */
    private static final List<AGCInitFinishManager.AGCInitFinishCallback> f10800a = new CopyOnWriteArrayList();

    /* renamed from: a */
    public static void m15396a() {
        for (AGCInitFinishManager.AGCInitFinishCallback aGCInitFinishCallback : f10800a) {
            aGCInitFinishCallback.onFinish();
        }
    }

    @Override // com.huawei.agconnect.AGCInitFinishManager
    public void addAGCInitFinishCallback(AGCInitFinishManager.AGCInitFinishCallback aGCInitFinishCallback) {
        if (aGCInitFinishCallback != null) {
            f10800a.add(aGCInitFinishCallback);
        }
    }
}
