package com.huawei.agconnect.config.impl;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.agconnect.AGConnectApp;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.CustomAuthProvider;
import com.huawei.agconnect.CustomCredentialsProvider;
import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.agconnect.core.p211a.C4785b;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.huawei.agconnect.config.impl.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4770a extends AGConnectApp {

    /* renamed from: a */
    private static final Map<String, AGConnectApp> f10755a = new HashMap();

    /* renamed from: b */
    private static final Object f10756b = new Object();

    /* renamed from: c */
    private static String f10757c;

    /* renamed from: d */
    private AGConnectServicesConfig f10758d;

    private C4770a(Context context, String str) {
        this.f10758d = AGConnectServicesConfig.fromContext(context, str);
    }

    /* renamed from: a */
    public static AGConnectApp m15428a() {
        return m15425a(f10757c);
    }

    /* renamed from: a */
    public static AGConnectApp m15427a(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        f10757c = context.getPackageName();
        return m15426a(context, f10757c);
    }

    /* renamed from: a */
    public static AGConnectApp m15426a(Context context, String str) {
        AGConnectApp aGConnectApp;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("packageName can not be empty");
        }
        synchronized (f10756b) {
            aGConnectApp = f10755a.get(str);
            if (aGConnectApp == null) {
                f10755a.put(str, new C4770a(context, str));
            }
        }
        return aGConnectApp;
    }

    /* renamed from: a */
    public static AGConnectApp m15425a(String str) {
        AGConnectApp aGConnectApp;
        synchronized (f10756b) {
            aGConnectApp = f10755a.get(str);
            if (aGConnectApp == null) {
                throw new IllegalStateException("you should call AGConnectApp.initialize first");
            }
        }
        return aGConnectApp;
    }

    @Override // com.huawei.agconnect.AGConnectApp
    public void setApiKey(String str) {
        this.f10758d.setParam("/client/api_key", str);
    }

    @Override // com.huawei.agconnect.AGConnectApp
    public void setAppId(String str) {
        this.f10758d.setParam("/client/app_id", str);
    }

    @Override // com.huawei.agconnect.AGConnectApp
    public void setClientId(String str) {
        this.f10758d.setParam("/client/client_id", str);
    }

    @Override // com.huawei.agconnect.AGConnectApp
    public void setClientSecret(String str) {
        this.f10758d.setParam("/client/client_secret", str);
    }

    @Override // com.huawei.agconnect.AGConnectApp
    public void setCpId(String str) {
        this.f10758d.setParam("/client/cp_id", str);
    }

    @Override // com.huawei.agconnect.AGConnectApp
    public void setCustomAuthProvider(CustomAuthProvider customAuthProvider) {
        ((C4785b) AGConnectInstance.getInstance()).m15389a(customAuthProvider);
    }

    @Override // com.huawei.agconnect.AGConnectApp
    public void setCustomCredentialsProvider(CustomCredentialsProvider customCredentialsProvider) {
        ((C4785b) AGConnectInstance.getInstance()).m15388a(customCredentialsProvider);
    }

    @Override // com.huawei.agconnect.AGConnectApp
    public void setParam(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("path can not be empty");
        }
        this.f10758d.setParam(str, str2);
    }

    @Override // com.huawei.agconnect.AGConnectApp
    public void setProductId(String str) {
        this.f10758d.setParam("/client/product_id", str);
    }
}
