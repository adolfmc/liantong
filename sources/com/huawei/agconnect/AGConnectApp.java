package com.huawei.agconnect;

import android.content.Context;
import com.huawei.agconnect.config.impl.C4770a;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class AGConnectApp {
    public static AGConnectApp getInstance() {
        return C4770a.m15428a();
    }

    private static AGConnectApp getInstance(String str) {
        return C4770a.m15425a(str);
    }

    public static AGConnectApp initialize(Context context) {
        return C4770a.m15427a(context);
    }

    private static AGConnectApp initialize(Context context, String str) {
        return C4770a.m15426a(context, str);
    }

    public abstract void setApiKey(String str);

    public abstract void setAppId(String str);

    public abstract void setClientId(String str);

    public abstract void setClientSecret(String str);

    public abstract void setCpId(String str);

    public abstract void setCustomAuthProvider(CustomAuthProvider customAuthProvider);

    public abstract void setCustomCredentialsProvider(CustomCredentialsProvider customCredentialsProvider);

    public abstract void setParam(String str, String str2);

    public abstract void setProductId(String str);
}
