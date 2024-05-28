package com.bytedance.pangle.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Keep;
import com.bytedance.pangle.Zeus;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class IntentUtils {

    /* renamed from: a */
    static HashMap<Long, WeakReference<Bundle>> f9021a = new HashMap<>();

    @Keep
    public static void setUseMemory(Intent intent) {
        intent.putExtra("pangle_use_memory", System.currentTimeMillis());
    }

    /* renamed from: a */
    public static void m16980a(Intent intent, String str) {
        long longExtra = intent.getLongExtra("pangle_use_memory", 0L);
        if (Zeus.getPlugin(str).mUseMemoryForActivityIntent && longExtra == 0) {
            longExtra = System.currentTimeMillis();
        }
        if (longExtra != 0) {
            Bundle extras = intent.getExtras();
            intent.replaceExtras((Bundle) null);
            f9021a.put(Long.valueOf(longExtra), new WeakReference<>(extras));
            intent.putExtra("pangle_use_memory", longExtra);
        }
    }

    /* renamed from: a */
    public static void m16981a(Intent intent) {
        long longExtra = intent.getLongExtra("pangle_use_memory", 0L);
        if (longExtra != 0) {
            WeakReference<Bundle> remove = f9021a.remove(Long.valueOf(longExtra));
            Bundle bundle = remove != null ? remove.get() : null;
            if (bundle != null) {
                intent.putExtras(bundle);
            }
        }
    }
}
