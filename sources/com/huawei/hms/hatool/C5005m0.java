package com.huawei.hms.hatool;

import android.os.Build;
import android.util.Log;

/* renamed from: com.huawei.hms.hatool.m0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5005m0 {

    /* renamed from: a */
    private boolean f11461a = false;

    /* renamed from: b */
    private int f11462b = 4;

    /* renamed from: a */
    private static String m14607a() {
        return "FormalHASDK_2.2.0.313" + C5015p.m14561a();
    }

    /* renamed from: a */
    public void m14606a(int i) {
        String str;
        StringBuilder sb = new StringBuilder();
        if (Build.VERSION.SDK_INT >= 19) {
            sb.append(System.lineSeparator());
            sb.append("======================================= ");
            sb.append(System.lineSeparator());
            sb.append(m14607a());
            sb.append("");
            sb.append(System.lineSeparator());
            str = "=======================================";
        } else {
            sb.append("====================");
            sb.append(m14607a());
            str = "===================";
        }
        sb.append(str);
        Log.i("FormalHASDK", sb.toString());
        this.f11462b = i;
        this.f11461a = true;
    }

    /* renamed from: a */
    public void m14605a(int i, String str, String str2) {
        if (i == 3) {
            Log.d(str, str2);
        } else if (i == 5) {
            Log.w(str, str2);
        } else if (i != 6) {
            Log.i(str, str2);
        } else {
            Log.e(str, str2);
        }
    }

    /* renamed from: b */
    public void m14603b(int i, String str, String str2) {
        m14605a(i, "FormalHASDK", str + "=> " + str2);
    }

    /* renamed from: b */
    public boolean m14604b(int i) {
        return this.f11461a && i >= this.f11462b;
    }
}
