package com.baidu.cloud.license.api;

import java.util.ArrayList;
import java.util.HashMap;
import retrofit2.Call;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: RetrofitProxyController.java */
/* renamed from: com.baidu.cloud.license.api.nx */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2489nx {

    /* renamed from: a */
    private static final HashMap<String, ArrayList<Call>> f4353a = new HashMap<>();

    /* renamed from: a */
    public static void m20086a(String str, Call call) {
        ArrayList<Call> arrayList = f4353a.get(str);
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        call.cancel();
        arrayList.remove(call);
        if (arrayList.size() == 0) {
            f4353a.remove(str);
        }
    }
}
