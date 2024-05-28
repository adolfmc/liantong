package com.example.asus.detectionandalign.utils;

import android.content.Context;
import android.os.Build;

/* renamed from: com.example.asus.detectionandalign.utils.f */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4294f {

    /* renamed from: a */
    private Context f10092a;

    public C4294f(Context context) {
        this.f10092a = context;
    }

    /* renamed from: a */
    public String m15949a() {
        int i = Build.VERSION.SDK_INT;
        return i == 15 ? "Android 4.0.3" : i == 16 ? "Android 4.1.2" : i == 17 ? "Android 4.2.2" : i == 18 ? "Android 4.3.1" : i == 19 ? "Android 4.4.2" : i == 20 ? "Android 4.4W.2" : i == 21 ? "Android 5.0.1" : i == 22 ? "Android 5.1.1" : i == 23 ? "Android 6.0" : i == 24 ? "Android 7.0" : i == 25 ? "Android 7.1" : i == 26 ? "Android 8.0" : i == 27 ? "Android 8.1" : i == 28 ? "Android 9.0" : i > 28 ? "Android 9.0+" : "";
    }
}
