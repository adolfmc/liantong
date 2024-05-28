package com.alipay.apmobilesecuritysdk.face;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.otherid.UmidSdkWrapper;
import com.alipay.apmobilesecuritysdk.otherid.UtdidWrapper;
import com.alipay.apmobilesecuritysdk.p104a.C1959a;
import com.alipay.apmobilesecuritysdk.p105b.C1960a;
import com.alipay.apmobilesecuritysdk.p108e.C1969a;
import com.alipay.apmobilesecuritysdk.p108e.C1972d;
import com.alipay.apmobilesecuritysdk.p108e.C1975g;
import com.alipay.apmobilesecuritysdk.p108e.C1976h;
import com.alipay.apmobilesecuritysdk.p108e.C1977i;
import com.alipay.apmobilesecuritysdk.p109f.C1979b;
import com.alipay.security.mobile.module.p110a.C2081a;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class APSecuritySdk {

    /* renamed from: a */
    private static APSecuritySdk f3506a;

    /* renamed from: c */
    private static Object f3507c = new Object();

    /* renamed from: b */
    private Context f3508b;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InitResultListener {
        void onResult(TokenResult tokenResult);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class TokenResult {
        public String apdid;
        public String apdidToken;
        public String clientKey;
        public String umidToken;

        public TokenResult() {
        }
    }

    private APSecuritySdk(Context context) {
        this.f3508b = context;
    }

    public static APSecuritySdk getInstance(Context context) {
        if (f3506a == null) {
            synchronized (f3507c) {
                if (f3506a == null) {
                    f3506a = new APSecuritySdk(context);
                }
            }
        }
        return f3506a;
    }

    public static String getUtdid(Context context) {
        return UtdidWrapper.getUtdid(context);
    }

    public String getApdidToken() {
        String m21057a = C1959a.m21057a(this.f3508b, "");
        if (C2081a.m20577a(m21057a)) {
            initToken(0, new HashMap(), null);
        }
        return m21057a;
    }

    public String getSdkName() {
        return "APPSecuritySDK-ALIPAYSDK";
    }

    public String getSdkVersion() {
        return "3.4.0.201910161639";
    }

    public synchronized TokenResult getTokenResult() {
        TokenResult tokenResult;
        tokenResult = new TokenResult();
        try {
            tokenResult.apdidToken = C1959a.m21057a(this.f3508b, "");
            tokenResult.clientKey = C1976h.m20990f(this.f3508b);
            tokenResult.apdid = C1959a.m21058a(this.f3508b);
            tokenResult.umidToken = UmidSdkWrapper.getSecurityToken(this.f3508b);
            if (C2081a.m20577a(tokenResult.apdid) || C2081a.m20577a(tokenResult.apdidToken) || C2081a.m20577a(tokenResult.clientKey)) {
                initToken(0, new HashMap(), null);
            }
        } catch (Throwable unused) {
        }
        return tokenResult;
    }

    public void initToken(int i, Map<String, String> map, final InitResultListener initResultListener) {
        C1960a.m21052a().m21051a(i);
        String m20998b = C1976h.m20998b(this.f3508b);
        String m21049c = C1960a.m21052a().m21049c();
        if (C2081a.m20573b(m20998b) && !C2081a.m20576a(m20998b, m21049c)) {
            C1969a.m21031a(this.f3508b);
            C1972d.m21024a(this.f3508b);
            C1975g.m21006a(this.f3508b);
            C1977i.m20969h();
        }
        if (!C2081a.m20576a(m20998b, m21049c)) {
            C1976h.m20995c(this.f3508b, m21049c);
        }
        String m20574a = C2081a.m20574a(map, "utdid", "");
        String m20574a2 = C2081a.m20574a(map, "tid", "");
        String m20574a3 = C2081a.m20574a(map, "userId", "");
        if (C2081a.m20577a(m20574a)) {
            m20574a = UtdidWrapper.getUtdid(this.f3508b);
        }
        final HashMap hashMap = new HashMap();
        hashMap.put("utdid", m20574a);
        hashMap.put("tid", m20574a2);
        hashMap.put("userId", m20574a3);
        hashMap.put("appName", "");
        hashMap.put("appKeyClient", "");
        hashMap.put("appchannel", "");
        hashMap.put("rpcVersion", "8");
        C1979b.m20964a().m20962a(new Runnable() { // from class: com.alipay.apmobilesecuritysdk.face.APSecuritySdk.1
            @Override // java.lang.Runnable
            public void run() {
                new C1959a(APSecuritySdk.this.f3508b).m21056a(hashMap);
                InitResultListener initResultListener2 = initResultListener;
                if (initResultListener2 != null) {
                    initResultListener2.onResult(APSecuritySdk.this.getTokenResult());
                }
            }
        });
    }
}
