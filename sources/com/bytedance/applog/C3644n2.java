package com.bytedance.applog;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.n2 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3644n2 {

    /* renamed from: a */
    public static volatile String f8616a;

    /* renamed from: a */
    public static String m17195a(Context context, C3726x c3726x) {
        if (TextUtils.isEmpty(f8616a)) {
            synchronized (C3644n2.class) {
                if (!TextUtils.isEmpty(f8616a)) {
                    return f8616a;
                }
                AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
                String id = advertisingIdInfo != null ? advertisingIdInfo.getId() : null;
                if (TextUtils.isEmpty(id)) {
                    id = c3726x.f8900e.getString("google_aid", null);
                } else if (!TextUtils.equals(c3726x.f8900e.getString("google_aid", null), id) && !TextUtils.isEmpty(id) && context != null) {
                    c3726x.f8900e.edit().putString("google_aid", id).apply();
                }
                f8616a = id;
            }
        }
        return f8616a;
    }
}
