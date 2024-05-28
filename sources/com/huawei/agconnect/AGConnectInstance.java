package com.huawei.agconnect;

import android.content.Context;
import android.util.Log;
import com.huawei.agconnect.core.p211a.C4785b;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class AGConnectInstance {
    public static AGConnectInstance buildInstance(AGConnectOptions aGConnectOptions) {
        return C4785b.m15391a(aGConnectOptions);
    }

    public static AGConnectInstance getInstance() {
        return C4785b.m15395a();
    }

    public static AGConnectInstance getInstance(String str) {
        return C4785b.m15387a(str);
    }

    public static void initialize(Context context) {
        Log.i("AGConnectInstance", "AGConnectInstance#initialize");
        C4785b.m15394a(context);
    }

    public static void initialize(Context context, AGConnectOptionsBuilder aGConnectOptionsBuilder) {
        Log.i("AGConnectInstance", "AGConnectInstance#initialize with options");
        C4785b.m15392a(context, aGConnectOptionsBuilder);
    }

    public abstract Context getContext();

    public abstract String getIdentifier();

    public abstract AGConnectOptions getOptions();

    public abstract <T> T getService(Class<? super T> cls);
}
