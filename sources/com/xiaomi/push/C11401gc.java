package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.service.C11577az;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.gc */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11401gc {
    /* renamed from: a */
    public static void m3692a(Context context, InterfaceC11403ge interfaceC11403ge, List<C11408gj> list) {
        HashMap<String, ArrayList<C11408gj>> m3690a = m3690a(context, list);
        if (m3690a == null || m3690a.size() == 0) {
            AbstractC11049b.m5282a("TinyData TinyDataCacheUploader.uploadTinyData itemsUploading == null || itemsUploading.size() == 0  ts:" + System.currentTimeMillis());
            return;
        }
        m3693a(context, interfaceC11403ge, m3690a);
    }

    /* renamed from: a */
    private static HashMap<String, ArrayList<C11408gj>> m3690a(Context context, List<C11408gj> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        HashMap<String, ArrayList<C11408gj>> hashMap = new HashMap<>();
        for (C11408gj c11408gj : list) {
            m3691a(context, c11408gj);
            ArrayList<C11408gj> arrayList = hashMap.get(c11408gj.m3655c());
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                hashMap.put(c11408gj.m3655c(), arrayList);
            }
            arrayList.add(c11408gj);
        }
        return hashMap;
    }

    /* renamed from: a */
    private static void m3691a(Context context, C11408gj c11408gj) {
        if (c11408gj.f22517a) {
            c11408gj.m3665a("push_sdk_channel");
        }
        if (TextUtils.isEmpty(c11408gj.m3651d())) {
            c11408gj.m3644f(C11577az.m2598a());
        }
        c11408gj.m3658b(System.currentTimeMillis());
        if (TextUtils.isEmpty(c11408gj.m3648e())) {
            c11408gj.m3646e(context.getPackageName());
        }
        if (TextUtils.isEmpty(c11408gj.m3655c())) {
            c11408gj.m3646e(c11408gj.m3648e());
        }
    }

    /* renamed from: a */
    private static void m3693a(Context context, InterfaceC11403ge interfaceC11403ge, HashMap<String, ArrayList<C11408gj>> hashMap) {
        for (Map.Entry<String, ArrayList<C11408gj>> entry : hashMap.entrySet()) {
            try {
                ArrayList<C11408gj> value = entry.getValue();
                if (value != null && value.size() != 0) {
                    interfaceC11403ge.mo2439a(value, value.get(0).m3648e(), entry.getKey());
                }
            } catch (Exception unused) {
            }
        }
    }
}
