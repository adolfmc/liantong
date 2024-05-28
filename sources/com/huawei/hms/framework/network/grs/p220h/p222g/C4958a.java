package com.huawei.hms.framework.network.grs.p220h.p222g;

import android.content.Context;
import android.content.res.AssetManager;
import com.huawei.hms.framework.network.grs.GrsApp;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactoryNew;
import com.huawei.secure.android.common.ssl.SecureX509TrustManager;
import com.huawei.secure.android.common.ssl.hostname.StrictHostnameVerifier;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.framework.network.grs.h.g.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4958a {

    /* renamed from: a */
    private static final HostnameVerifier f11330a = new StrictHostnameVerifier();

    /* renamed from: a */
    public static HostnameVerifier m14849a() {
        return f11330a;
    }

    /* renamed from: a */
    public static SSLSocketFactory m14848a(Context context) {
        try {
            AssetManager assets = context.getAssets();
            return new SecureSSLSocketFactoryNew(new SecureX509TrustManager(assets.open(GrsApp.getInstance().getBrand("/") + "grs_sp.bks"), ""));
        } catch (IOException | KeyManagementException | NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }
}
