package com.unicom.pay;

import android.app.Activity;
import android.text.TextUtils;
import com.gmrz.appsdk.FidoAppSDK;
import com.gmrz.appsdk.FidoIn;
import com.gmrz.appsdk.FidoReInfo;
import com.gmrz.appsdk.commlib.api.FidoCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10546a;
import com.unicom.pay.common.bean.WPFidoResultBean;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.common.callback.DataCallback;
import java.lang.reflect.Type;
import p470p0.C13648k;
import p470p0.C13652o;
import p482w.AbstractC14257d;

@NBSInstrumented
/* renamed from: com.unicom.pay.e */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C10584e extends AbstractC14257d<WPFidoResultBean> {

    /* renamed from: c */
    public final /* synthetic */ Activity f20130c;

    /* renamed from: d */
    public final /* synthetic */ String f20131d;

    /* renamed from: e */
    public final /* synthetic */ WPResult f20132e;

    /* renamed from: f */
    public final /* synthetic */ DataCallback f20133f;

    /* renamed from: g */
    public final /* synthetic */ boolean f20134g;

    /* renamed from: h */
    public final /* synthetic */ C10546a f20135h;

    /* renamed from: com.unicom.pay.e$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10585a implements FidoCallback<FidoReInfo> {
        public C10585a() {
        }

        @Override // com.gmrz.appsdk.commlib.api.FidoCallback
        public final void onFidoProcess(FidoReInfo fidoReInfo) {
            C10584e.this.f20130c.runOnUiThread(new RunnableC10583d(this, fidoReInfo));
        }
    }

    /* renamed from: com.unicom.pay.e$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10586b implements C10546a.InterfaceC10577j {
        public C10586b() {
        }

        @Override // com.unicom.pay.C10546a.InterfaceC10577j
        /* renamed from: a */
        public final void mo1917a() {
            C10584e c10584e = C10584e.this;
            C10546a.m6171a(c10584e.f20135h, c10584e.f20130c, c10584e.f20131d, true, c10584e.f20133f);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.e$c */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    class C10587c extends TypeToken<WPResult<WPFidoResultBean>> {
    }

    @NBSInstrumented
    /* renamed from: com.unicom.pay.e$d */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class RunnableC10588d implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ String f20138a;

        public RunnableC10588d(String str) {
            this.f20138a = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            String str = this.f20138a;
            C10584e.this.getClass();
            Type type = new C10587c().getType();
            Gson gson = C13648k.f27492a;
            WPResult<WPFidoResultBean> wPResult = (WPResult) (!(gson instanceof Gson) ? gson.fromJson(str, type) : NBSGsonInstrumentation.fromJson(gson, str, type));
            if (!"0000".equals(wPResult.getCode())) {
                C10584e.this.mo10a(wPResult);
                return;
            }
            if (wPResult.getCommonResp() != null && !TextUtils.isEmpty(wPResult.getCommonResp().getUserTokenId())) {
                C10546a.C10576i.f20125a.m6169a(wPResult.getCommonResp().getUserTokenId());
            }
            C10584e.this.mo9b(wPResult);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.e$e */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class RunnableC10589e implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ String f20140a;

        public RunnableC10589e(String str) {
            this.f20140a = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            C10584e.this.mo11d(this.f20140a);
        }
    }

    public C10584e(C10546a c10546a, Activity activity, String str, WPResult wPResult, DataCallback dataCallback, boolean z) {
        this.f20135h = c10546a;
        this.f20130c = activity;
        this.f20131d = str;
        this.f20132e = wPResult;
        this.f20133f = dataCallback;
        this.f20134g = z;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPFidoResultBean> wPResult) {
        super.mo10a(wPResult);
        Gson gson = C13648k.f27492a;
        C13652o.m174a("WopayConfig code:", !(gson instanceof Gson) ? gson.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson, wPResult));
        C10546a.m6170a(this.f20135h, wPResult, this.f20134g, this.f20133f, new C10586b());
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPFidoResultBean> wPResult) {
        WPFidoResultBean data = wPResult.getData();
        if (data == null) {
            return;
        }
        C13652o.m174a("WopayConfig code:", data.getUafRequest());
        Activity activity = this.f20130c;
        String str = this.f20131d;
        String uafRequest = data.getUafRequest();
        C10585a c10585a = new C10585a();
        try {
            FidoIn Builder = FidoIn.Builder();
            Builder.setAuthType(str.split("\\|"));
            Builder.setCheckDeviceAbilityServerResp(uafRequest);
            FidoAppSDK.getInstance().checkNetSupportAsync(activity, Builder, c10585a);
        } catch (Exception unused) {
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo24b(String str) {
        Activity activity = this.f20130c;
        if (activity != null) {
            activity.runOnUiThread(new RunnableC10589e(str));
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C10587c().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final void mo23c(String str) {
        Activity activity = this.f20130c;
        if (activity != null) {
            activity.runOnUiThread(new RunnableC10588d(str));
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        super.mo11d(str);
        this.f20132e.setCode("0002");
        this.f20132e.setMsg("网络请求错误");
        DataCallback dataCallback = this.f20133f;
        WPResult wPResult = this.f20132e;
        Gson gson = C13648k.f27492a;
        dataCallback.onResult(!(gson instanceof Gson) ? gson.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson, wPResult));
        WPResult wPResult2 = this.f20132e;
        Gson gson2 = C13648k.f27492a;
        C13652o.m174a("WopayConfig code:", !(gson2 instanceof Gson) ? gson2.toJson(wPResult2) : NBSGsonInstrumentation.toJson(gson2, wPResult2));
    }
}
