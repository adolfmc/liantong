package com.alipay.apmobilesecuritysdk.face;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.otherid.UtdidWrapper;
import com.alipay.apmobilesecuritysdk.p104a.C1959a;
import com.alipay.apmobilesecuritysdk.p109f.C1979b;
import com.alipay.security.mobile.module.p110a.C2081a;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class TMNTokenClient {

    /* renamed from: a */
    private static TMNTokenClient f3513a;

    /* renamed from: b */
    private Context f3514b;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InitResultListener {
        void onResult(String str, int i);
    }

    private TMNTokenClient(Context context) {
        this.f3514b = null;
        if (context == null) {
            throw new IllegalArgumentException("TMNTokenClient initialization error: context is null.");
        }
        this.f3514b = context;
    }

    public static TMNTokenClient getInstance(Context context) {
        if (f3513a == null) {
            synchronized (TMNTokenClient.class) {
                if (f3513a == null) {
                    f3513a = new TMNTokenClient(context);
                }
            }
        }
        return f3513a;
    }

    public void intiToken(final String str, String str2, String str3, final InitResultListener initResultListener) {
        if (C2081a.m20577a(str) && initResultListener != null) {
            initResultListener.onResult("", 2);
        }
        if (C2081a.m20577a(str2) && initResultListener != null) {
            initResultListener.onResult("", 3);
        }
        final HashMap hashMap = new HashMap();
        hashMap.put("utdid", UtdidWrapper.getUtdid(this.f3514b));
        hashMap.put("tid", "");
        hashMap.put("userId", "");
        hashMap.put("appName", str);
        hashMap.put("appKeyClient", str2);
        hashMap.put("appchannel", "openapi");
        hashMap.put("sessionId", str3);
        hashMap.put("rpcVersion", "8");
        C1979b.m20964a().m20962a(new Runnable() { // from class: com.alipay.apmobilesecuritysdk.face.TMNTokenClient.1
            @Override // java.lang.Runnable
            public void run() {
                int m21056a = new C1959a(TMNTokenClient.this.f3514b).m21056a(hashMap);
                InitResultListener initResultListener2 = initResultListener;
                if (initResultListener2 == null) {
                    return;
                }
                if (m21056a != 0) {
                    initResultListener2.onResult("", m21056a);
                    return;
                }
                initResultListener.onResult(C1959a.m21057a(TMNTokenClient.this.f3514b, str), 0);
            }
        });
    }
}
