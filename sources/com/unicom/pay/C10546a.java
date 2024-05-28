package com.unicom.pay;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import cn.ltzf.passguard.C1730a;
import com.gmrz.appsdk.FidoAppSDK;
import com.gmrz.appsdk.FidoIn;
import com.gmrz.appsdk.FidoReInfo;
import com.gmrz.appsdk.commlib.api.FidoCallback;
import com.gmrz.appsdk.commlib.api.FidoParam;
import com.gmrz.appsdk.commlib.api.FidoStatus;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ijiami.JMEncryptBox;
import com.ijiami.StatusInfo;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.common.bean.ConfigInfo;
import com.unicom.pay.common.bean.EnvConfig;
import com.unicom.pay.common.bean.WPFidoResultBean;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.common.bean.WPTicketBean;
import com.unicom.pay.common.callback.DataCallback;
import com.unicom.pay.common.callback.NativeFunctionCallBack;
import com.unicom.pay.modules.result.bean.WPCheckSignResult;
import com.unicom.pay.modules.verify.p357ui.WPValidatePayPassActivity;
import com.unicom.pay.normal.order.bean.WPDefaultOrderInfoBean;
import com.unicom.pay.normal.order.bean.WPUnionOrderInfoBean;
import com.unicom.pay.normal.order.p359ui.WPOrderActivity;
import com.unicom.pay.qpay.open.bean.WPFindStateRequestBean;
import com.unicom.pay.qpay.open.bean.WPQPayUserInfoBean;
import com.unicom.pay.utils.buried.WPTrendsEventsUtils;
import com.unicompayment.sdk.UnicomFPSDK;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.UUID;
import p393h.C12014f;
import p400k0.C12270a;
import p470p0.C13637b;
import p470p0.C13642f;
import p470p0.C13648k;
import p470p0.C13651n;
import p470p0.C13652o;
import p470p0.C13660s;
import p470p0.C13663u;
import p472q0.C13676a;
import p472q0.C13678c;
import p472q0.C13680e;
import p472q0.C13690m;
import p472q0.C13692n;
import p472q0.C13693o;
import p474r0.InterfaceC13717c;
import p482w.AbstractC14257d;
import p482w.C14255c;
import p482w.C14262f;

@NBSInstrumented
/* renamed from: com.unicom.pay.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C10546a {

    /* renamed from: a */
    public C13660s f20051a;

    /* renamed from: c */
    public C13663u f20053c;

    /* renamed from: h */
    public String f20058h;

    /* renamed from: k */
    public String f20061k;

    /* renamed from: n */
    public DataCallback f20064n;

    /* renamed from: o */
    public String f20065o;

    /* renamed from: b */
    public EnvConfig f20052b = EnvConfig.PRO;

    /* renamed from: l */
    public String f20062l = "";

    /* renamed from: m */
    public String f20063m = "";

    /* renamed from: d */
    public String f20054d = "";

    /* renamed from: e */
    public String f20055e = "";

    /* renamed from: f */
    public String f20056f = "";

    /* renamed from: g */
    public String f20057g = "";

    /* renamed from: j */
    public String f20060j = "";

    /* renamed from: i */
    public String f20059i = "";

    @NBSInstrumented
    /* renamed from: com.unicom.pay.a$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10547a extends AbstractC14257d<WPFidoResultBean> {

        /* renamed from: c */
        public final /* synthetic */ DataCallback f20066c;

        /* renamed from: d */
        public final /* synthetic */ boolean f20067d;

        /* renamed from: e */
        public final /* synthetic */ Activity f20068e;

        /* renamed from: f */
        public final /* synthetic */ WPResult f20069f;

        /* renamed from: com.unicom.pay.a$a$a */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class C10548a implements InterfaceC10577j {
            public C10548a() {
            }

            @Override // com.unicom.pay.C10546a.InterfaceC10577j
            /* renamed from: a */
            public final void mo1917a() {
                C10547a c10547a = C10547a.this;
                C10546a.this.m6179a(c10547a.f20068e, true, c10547a.f20066c);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.unicom.pay.a$a$b */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        class C10549b extends TypeToken<WPResult<WPFidoResultBean>> {
        }

        @NBSInstrumented
        /* renamed from: com.unicom.pay.a$a$c */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class RunnableC10550c implements Runnable {

            /* renamed from: a */
            public final /* synthetic */ String f20072a;

            public RunnableC10550c(String str) {
                this.f20072a = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                String str = this.f20072a;
                C10547a.this.getClass();
                Type type = new C10549b().getType();
                Gson gson = C13648k.f27492a;
                WPResult<WPFidoResultBean> wPResult = (WPResult) (!(gson instanceof Gson) ? gson.fromJson(str, type) : NBSGsonInstrumentation.fromJson(gson, str, type));
                if (!"0000".equals(wPResult.getCode())) {
                    C10547a.this.mo10a(wPResult);
                    return;
                }
                if (wPResult.getCommonResp() != null && !TextUtils.isEmpty(wPResult.getCommonResp().getUserTokenId())) {
                    C10576i.f20125a.m6169a(wPResult.getCommonResp().getUserTokenId());
                }
                C10547a.this.mo9b(wPResult);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.unicom.pay.a$a$d */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        public class RunnableC10551d implements Runnable {

            /* renamed from: a */
            public final /* synthetic */ String f20074a;

            public RunnableC10551d(String str) {
                this.f20074a = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                C10547a.this.mo11d(this.f20074a);
            }
        }

        public C10547a(DataCallback dataCallback, boolean z, Activity activity, WPResult wPResult) {
            this.f20066c = dataCallback;
            this.f20067d = z;
            this.f20068e = activity;
            this.f20069f = wPResult;
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult<WPFidoResultBean> wPResult) {
            super.mo10a(wPResult);
            C10546a.m6170a(C10546a.this, wPResult, this.f20067d, this.f20066c, new C10548a());
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPFidoResultBean> wPResult) {
            String str;
            WPFidoResultBean data = wPResult.getData();
            WPQPayUserInfoBean m164a = C10546a.this.f20053c.m164a(data.getUserId());
            if (m164a == null) {
                m164a = new WPQPayUserInfoBean();
                m164a.setUserNo(data.getUserId());
            }
            if (!"1".equals(data.getFingerRegStatus()) || !"1".equals(data.getFaceRegStatus())) {
                if ("1".equals(data.getFingerRegStatus())) {
                    str = "00";
                } else if ("1".equals(data.getFaceRegStatus())) {
                    str = "02";
                } else {
                    m164a.clearFido();
                }
                m164a.setCurrentFido(str);
                m164a.setIsSupportTwo("");
            } else if (!"1".equals(m164a.getIsSupportTwo())) {
                m164a.setIsSupportTwo("1");
                m164a.setCurrentFido("00");
            }
            C10546a.this.f20053c.m165a(m164a);
            DataCallback dataCallback = this.f20066c;
            Gson gson = C13648k.f27492a;
            dataCallback.onResult(!(gson instanceof Gson) ? gson.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson, wPResult));
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo24b(String str) {
            Activity activity = this.f20068e;
            if (activity != null) {
                activity.runOnUiThread(new RunnableC10551d(str));
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C10549b().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final void mo23c(String str) {
            Activity activity = this.f20068e;
            if (activity != null) {
                activity.runOnUiThread(new RunnableC10550c(str));
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: d */
        public final void mo11d(String str) {
            super.mo11d(str);
            this.f20069f.setCode("0002");
            this.f20069f.setMsg("网络请求错误");
            DataCallback dataCallback = this.f20066c;
            WPResult wPResult = this.f20069f;
            Gson gson = C13648k.f27492a;
            dataCallback.onResult(!(gson instanceof Gson) ? gson.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson, wPResult));
        }
    }

    @NBSInstrumented
    /* renamed from: com.unicom.pay.a$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10552b extends AbstractC14257d<WPFidoResultBean> {

        /* renamed from: c */
        public final /* synthetic */ WPResult f20076c;

        /* renamed from: d */
        public final /* synthetic */ DataCallback f20077d;

        /* renamed from: e */
        public final /* synthetic */ String f20078e;

        /* renamed from: f */
        public final /* synthetic */ Activity f20079f;

        /* renamed from: g */
        public final /* synthetic */ boolean f20080g;

        /* renamed from: com.unicom.pay.a$b$a */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class C10553a implements FidoCallback<FidoReInfo> {
            public C10553a() {
            }

            @Override // com.gmrz.appsdk.commlib.api.FidoCallback
            public final void onFidoProcess(FidoReInfo fidoReInfo) {
                C10552b.this.f20079f.runOnUiThread(new RunnableC10578b(this));
            }
        }

        /* renamed from: com.unicom.pay.a$b$b */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class C10554b implements InterfaceC10577j {
            public C10554b() {
            }

            @Override // com.unicom.pay.C10546a.InterfaceC10577j
            /* renamed from: a */
            public final void mo1917a() {
                C10552b c10552b = C10552b.this;
                C10546a.this.m6180a(c10552b.f20079f, c10552b.f20078e, true, c10552b.f20077d);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.unicom.pay.a$b$c */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        class C10555c extends TypeToken<WPResult<WPFidoResultBean>> {
        }

        @NBSInstrumented
        /* renamed from: com.unicom.pay.a$b$d */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class RunnableC10556d implements Runnable {

            /* renamed from: a */
            public final /* synthetic */ String f20084a;

            public RunnableC10556d(String str) {
                this.f20084a = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                String str = this.f20084a;
                C10552b.this.getClass();
                Type type = new C10555c().getType();
                Gson gson = C13648k.f27492a;
                WPResult<WPFidoResultBean> wPResult = (WPResult) (!(gson instanceof Gson) ? gson.fromJson(str, type) : NBSGsonInstrumentation.fromJson(gson, str, type));
                if (!"0000".equals(wPResult.getCode())) {
                    C10552b.this.mo10a(wPResult);
                    return;
                }
                if (wPResult.getCommonResp() != null && !TextUtils.isEmpty(wPResult.getCommonResp().getUserTokenId())) {
                    C10576i.f20125a.m6169a(wPResult.getCommonResp().getUserTokenId());
                }
                C10552b.this.mo9b(wPResult);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.unicom.pay.a$b$e */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        public class RunnableC10557e implements Runnable {

            /* renamed from: a */
            public final /* synthetic */ String f20086a;

            public RunnableC10557e(String str) {
                this.f20086a = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                C10552b.this.mo11d(this.f20086a);
            }
        }

        public C10552b(WPResult wPResult, DataCallback dataCallback, String str, Activity activity, boolean z) {
            this.f20076c = wPResult;
            this.f20077d = dataCallback;
            this.f20078e = str;
            this.f20079f = activity;
            this.f20080g = z;
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult<WPFidoResultBean> wPResult) {
            super.mo10a(wPResult);
            C10546a.m6170a(C10546a.this, wPResult, this.f20080g, this.f20077d, new C10554b());
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPFidoResultBean> wPResult) {
            WPFidoResultBean data = wPResult.getData();
            WPQPayUserInfoBean m164a = C10546a.this.f20053c.m164a(wPResult.getData().getUserId());
            if (m164a == null) {
                this.f20076c.setCode("0000");
                DataCallback dataCallback = this.f20077d;
                WPResult wPResult2 = this.f20076c;
                Gson gson = C13648k.f27492a;
                dataCallback.onResult(!(gson instanceof Gson) ? gson.toJson(wPResult2) : NBSGsonInstrumentation.toJson(gson, wPResult2));
                return;
            }
            m164a.resetFido(this.f20078e);
            C10546a.this.f20053c.m165a(m164a);
            Activity activity = this.f20079f;
            String uafRequest = data.getUafRequest();
            FidoAppSDK.getInstance().processAsync(activity, FidoIn.Builder().setFidoIn(uafRequest), new C10553a());
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo24b(String str) {
            Activity activity = this.f20079f;
            if (activity != null) {
                activity.runOnUiThread(new RunnableC10557e(str));
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C10555c().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final void mo23c(String str) {
            Activity activity = this.f20079f;
            if (activity != null) {
                activity.runOnUiThread(new RunnableC10556d(str));
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: d */
        public final void mo11d(String str) {
            super.mo11d(str);
            this.f20076c.setCode("0002");
            this.f20076c.setMsg("网络请求错误");
            DataCallback dataCallback = this.f20077d;
            WPResult wPResult = this.f20076c;
            Gson gson = C13648k.f27492a;
            dataCallback.onResult(!(gson instanceof Gson) ? gson.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson, wPResult));
        }
    }

    /* renamed from: com.unicom.pay.a$c */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10558c implements FidoCallback<FidoReInfo> {
        public C10558c() {
        }

        @Override // com.gmrz.appsdk.commlib.api.FidoCallback
        public final void onFidoProcess(FidoReInfo fidoReInfo) {
            C10546a c10546a = C10546a.this;
            c10546a.f20061k = c10546a.f20059i;
        }
    }

    @NBSInstrumented
    /* renamed from: com.unicom.pay.a$d */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10559d implements DataCallback {

        /* renamed from: b */
        public final /* synthetic */ InterfaceC10577j f20090b;

        public C10559d(InterfaceC10577j interfaceC10577j) {
            this.f20090b = interfaceC10577j;
        }

        @Override // com.unicom.pay.common.callback.DataCallback
        public final void onResult(String str) {
            try {
                Gson gson = C13648k.f27492a;
                C10546a.this.f20057g = ((WPTicketBean) (!(gson instanceof Gson) ? gson.fromJson(str, (Class<Object>) WPTicketBean.class) : NBSGsonInstrumentation.fromJson(gson, str, (Class<Object>) WPTicketBean.class))).getTicket();
            } catch (Exception unused) {
                C10546a.this.f20057g = "";
            }
            this.f20090b.mo1917a();
        }
    }

    @NBSInstrumented
    /* renamed from: com.unicom.pay.a$e */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10560e extends AbstractC14257d<WPCheckSignResult> {

        /* renamed from: c */
        public final /* synthetic */ Context f20091c;

        /* renamed from: d */
        public final /* synthetic */ DataCallback f20092d;

        /* renamed from: e */
        public final /* synthetic */ boolean f20093e;

        /* renamed from: f */
        public final /* synthetic */ String f20094f;

        @NBSInstrumented
        /* renamed from: com.unicom.pay.a$e$a */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class RunnableC10561a implements Runnable {

            /* renamed from: a */
            public final /* synthetic */ String f20096a;

            public RunnableC10561a(String str) {
                this.f20096a = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                String str = this.f20096a;
                C10560e.this.getClass();
                Type type = new C10562b().getType();
                Gson gson = C13648k.f27492a;
                WPResult<WPCheckSignResult> wPResult = (WPResult) (!(gson instanceof Gson) ? gson.fromJson(str, type) : NBSGsonInstrumentation.fromJson(gson, str, type));
                if (!"0000".equals(wPResult.getCode())) {
                    C10560e.this.mo10a(wPResult);
                    return;
                }
                if (wPResult.getCommonResp() != null && !TextUtils.isEmpty(wPResult.getCommonResp().getUserTokenId())) {
                    C10576i.f20125a.m6169a(wPResult.getCommonResp().getUserTokenId());
                }
                C10560e.this.mo9b(wPResult);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.unicom.pay.a$e$b */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        class C10562b extends TypeToken<WPResult<WPCheckSignResult>> {
        }

        @NBSInstrumented
        /* renamed from: com.unicom.pay.a$e$c */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class RunnableC10563c implements Runnable {

            /* renamed from: a */
            public final /* synthetic */ String f20098a;

            public RunnableC10563c(String str) {
                this.f20098a = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                C10560e c10560e = C10560e.this;
                C10546a c10546a = C10546a.this;
                String str = this.f20098a;
                DataCallback dataCallback = c10560e.f20092d;
                c10546a.getClass();
                WPResult wPResult = new WPResult();
                wPResult.setCode("3011219999");
                wPResult.setMsg(str);
                Gson gson = C13648k.f27492a;
                dataCallback.onResult(!(gson instanceof Gson) ? gson.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson, wPResult));
            }
        }

        /* renamed from: com.unicom.pay.a$e$d */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class C10564d implements InterfaceC10577j {
            public C10564d() {
            }

            @Override // com.unicom.pay.C10546a.InterfaceC10577j
            /* renamed from: a */
            public final void mo1917a() {
                C10560e c10560e = C10560e.this;
                C10546a.this.m6173a(c10560e.f20091c, c10560e.f20094f, true, c10560e.f20092d);
            }
        }

        public C10560e(Context context, DataCallback dataCallback, boolean z, String str) {
            this.f20091c = context;
            this.f20092d = dataCallback;
            this.f20093e = z;
            this.f20094f = str;
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult<WPCheckSignResult> wPResult) {
            super.mo10a(wPResult);
            C10546a.m6170a(C10546a.this, wPResult, this.f20093e, this.f20092d, new C10564d());
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPCheckSignResult> wPResult) {
            WPCheckSignResult data = wPResult.getData();
            if (data != null) {
                data.setUserNo("");
                data.setPhoneNo("");
                wPResult.setData(data);
            }
            DataCallback dataCallback = this.f20092d;
            Gson gson = C13648k.f27492a;
            dataCallback.onResult(!(gson instanceof Gson) ? gson.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson, wPResult));
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo24b(String str) {
            super.mo24b(str);
            Context context = this.f20091c;
            if (context instanceof Activity) {
                ((Activity) context).runOnUiThread(new RunnableC10563c(str));
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C10562b().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final void mo23c(String str) {
            Context context = this.f20091c;
            if (context instanceof Activity) {
                ((Activity) context).runOnUiThread(new RunnableC10561a(str));
            }
        }
    }

    @NBSInstrumented
    /* renamed from: com.unicom.pay.a$f */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10565f extends AbstractC14257d<WPCheckSignResult> {

        /* renamed from: c */
        public final /* synthetic */ Context f20101c;

        /* renamed from: d */
        public final /* synthetic */ DataCallback f20102d;

        /* renamed from: e */
        public final /* synthetic */ boolean f20103e;

        /* renamed from: f */
        public final /* synthetic */ String f20104f;

        /* renamed from: g */
        public final /* synthetic */ String f20105g;

        @NBSInstrumented
        /* renamed from: com.unicom.pay.a$f$a */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class RunnableC10566a implements Runnable {

            /* renamed from: a */
            public final /* synthetic */ String f20107a;

            public RunnableC10566a(String str) {
                this.f20107a = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                String str = this.f20107a;
                C10565f.this.getClass();
                Type type = new C10567b().getType();
                Gson gson = C13648k.f27492a;
                WPResult<WPCheckSignResult> wPResult = (WPResult) (!(gson instanceof Gson) ? gson.fromJson(str, type) : NBSGsonInstrumentation.fromJson(gson, str, type));
                if (!"0000".equals(wPResult.getCode())) {
                    C10565f.this.mo10a(wPResult);
                    return;
                }
                if (wPResult.getCommonResp() != null && !TextUtils.isEmpty(wPResult.getCommonResp().getUserTokenId())) {
                    C10576i.f20125a.m6169a(wPResult.getCommonResp().getUserTokenId());
                }
                C10565f.this.mo9b(wPResult);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.unicom.pay.a$f$b */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        class C10567b extends TypeToken<WPResult<WPCheckSignResult>> {
        }

        @NBSInstrumented
        /* renamed from: com.unicom.pay.a$f$c */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class RunnableC10568c implements Runnable {

            /* renamed from: a */
            public final /* synthetic */ String f20109a;

            public RunnableC10568c(String str) {
                this.f20109a = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                C10565f c10565f = C10565f.this;
                C10546a c10546a = C10546a.this;
                String str = this.f20109a;
                DataCallback dataCallback = c10565f.f20102d;
                c10546a.getClass();
                WPResult wPResult = new WPResult();
                wPResult.setCode("3011219999");
                wPResult.setMsg(str);
                Gson gson = C13648k.f27492a;
                dataCallback.onResult(!(gson instanceof Gson) ? gson.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson, wPResult));
            }
        }

        /* renamed from: com.unicom.pay.a$f$d */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class C10569d implements InterfaceC10577j {
            public C10569d() {
            }

            @Override // com.unicom.pay.C10546a.InterfaceC10577j
            /* renamed from: a */
            public final void mo1917a() {
                C10565f c10565f = C10565f.this;
                C10546a.this.m6174a(c10565f.f20101c, c10565f.f20104f, c10565f.f20105g, true, c10565f.f20102d);
            }
        }

        public C10565f(Context context, DataCallback dataCallback, boolean z, String str, String str2) {
            this.f20101c = context;
            this.f20102d = dataCallback;
            this.f20103e = z;
            this.f20104f = str;
            this.f20105g = str2;
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult<WPCheckSignResult> wPResult) {
            super.mo10a(wPResult);
            C10546a.m6170a(C10546a.this, wPResult, this.f20103e, this.f20102d, new C10569d());
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPCheckSignResult> wPResult) {
            WPCheckSignResult data = wPResult.getData();
            if (data != null) {
                data.setUserNo("");
                data.setPhoneNo("");
                wPResult.setData(data);
            }
            DataCallback dataCallback = this.f20102d;
            Gson gson = C13648k.f27492a;
            dataCallback.onResult(!(gson instanceof Gson) ? gson.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson, wPResult));
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo24b(String str) {
            super.mo24b(str);
            Context context = this.f20101c;
            if (context instanceof Activity) {
                ((Activity) context).runOnUiThread(new RunnableC10568c(str));
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C10567b().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final void mo23c(String str) {
            Context context = this.f20101c;
            if (context instanceof Activity) {
                ((Activity) context).runOnUiThread(new RunnableC10566a(str));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.a$g */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10570g extends TypeToken<WPDefaultOrderInfoBean> {
    }

    @NBSInstrumented
    /* renamed from: com.unicom.pay.a$h */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10571h extends AbstractC14257d<WPUnionOrderInfoBean> {

        /* renamed from: c */
        public final /* synthetic */ Context f20112c;

        /* renamed from: d */
        public final /* synthetic */ boolean f20113d;

        /* renamed from: e */
        public final /* synthetic */ DataCallback f20114e;

        /* renamed from: f */
        public final /* synthetic */ String f20115f;

        /* renamed from: g */
        public final /* synthetic */ String f20116g;

        /* renamed from: h */
        public final /* synthetic */ Intent f20117h;

        /* renamed from: i */
        public final /* synthetic */ WPDefaultOrderInfoBean f20118i;

        @NBSInstrumented
        /* renamed from: com.unicom.pay.a$h$a */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class RunnableC10572a implements Runnable {

            /* renamed from: a */
            public final /* synthetic */ String f20120a;

            public RunnableC10572a(String str) {
                this.f20120a = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                String str = this.f20120a;
                C10571h.this.getClass();
                Type type = new C10575d().getType();
                Gson gson = C13648k.f27492a;
                WPResult<WPUnionOrderInfoBean> wPResult = (WPResult) (!(gson instanceof Gson) ? gson.fromJson(str, type) : NBSGsonInstrumentation.fromJson(gson, str, type));
                if (!"0000".equals(wPResult.getCode())) {
                    C10571h.this.mo10a(wPResult);
                    return;
                }
                if (wPResult.getCommonResp() != null && !TextUtils.isEmpty(wPResult.getCommonResp().getUserTokenId())) {
                    C10576i.f20125a.m6169a(wPResult.getCommonResp().getUserTokenId());
                }
                C10571h.this.mo9b(wPResult);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.unicom.pay.a$h$b */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        public class RunnableC10573b implements Runnable {

            /* renamed from: a */
            public final /* synthetic */ String f20122a;

            public RunnableC10573b(String str) {
                this.f20122a = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                C10571h.this.mo11d(this.f20122a);
            }
        }

        /* renamed from: com.unicom.pay.a$h$c */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class C10574c implements InterfaceC10577j {
            public C10574c() {
            }

            @Override // com.unicom.pay.C10546a.InterfaceC10577j
            /* renamed from: a */
            public final void mo1917a() {
                String str;
                C10571h c10571h = C10571h.this;
                C10546a c10546a = C10546a.this;
                Context context = c10571h.f20112c;
                String str2 = c10571h.f20115f;
                String str3 = c10571h.f20116g;
                byte[] bArr = C13637b.f27479a;
                try {
                    str = new String(C13637b.m188a(str3.getBytes("UTF-8")));
                } catch (UnsupportedEncodingException e) {
                    PrintStream printStream = System.err;
                    StringBuilder m22016a = C1730a.m22016a("Base64 encoding error: ");
                    m22016a.append(e.getMessage());
                    printStream.println(m22016a.toString());
                    e.printStackTrace();
                    str = null;
                }
                c10546a.m6175a(context, str2, str, C10571h.this.f20114e, true);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.unicom.pay.a$h$d */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        class C10575d extends TypeToken<WPResult<WPUnionOrderInfoBean>> {
        }

        public C10571h(Context context, boolean z, DataCallback dataCallback, String str, String str2, Intent intent, WPDefaultOrderInfoBean wPDefaultOrderInfoBean) {
            this.f20112c = context;
            this.f20113d = z;
            this.f20114e = dataCallback;
            this.f20115f = str;
            this.f20116g = str2;
            this.f20117h = intent;
            this.f20118i = wPDefaultOrderInfoBean;
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult<WPUnionOrderInfoBean> wPResult) {
            if (wPResult.getData() == null) {
                wPResult.setData(new WPUnionOrderInfoBean());
            }
            if ("GATEWAY9000".equals(wPResult.getCode())) {
                C10546a.m6170a(C10546a.this, wPResult, this.f20113d, this.f20114e, new C10574c());
                return;
            }
            this.f20117h.putExtras(WPOrderActivity.m6103a(this.f20115f, this.f20116g, this.f20118i, wPResult, false));
            this.f20112c.startActivity(this.f20117h);
            WPResult wPResult2 = new WPResult();
            wPResult2.setCode("0000");
            DataCallback dataCallback = this.f20114e;
            Gson gson = C13648k.f27492a;
            dataCallback.onResult(!(gson instanceof Gson) ? gson.toJson(wPResult2) : NBSGsonInstrumentation.toJson(gson, wPResult2));
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPUnionOrderInfoBean> wPResult) {
            this.f20117h.putExtras(WPOrderActivity.m6103a(this.f20115f, this.f20116g, this.f20118i, wPResult, false));
            this.f20112c.startActivity(this.f20117h);
            WPResult wPResult2 = new WPResult();
            wPResult2.setCode("0000");
            DataCallback dataCallback = this.f20114e;
            Gson gson = C13648k.f27492a;
            dataCallback.onResult(!(gson instanceof Gson) ? gson.toJson(wPResult2) : NBSGsonInstrumentation.toJson(gson, wPResult2));
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo24b(String str) {
            Context context = this.f20112c;
            if (context != null) {
                ((Activity) context).runOnUiThread(new RunnableC10573b(str));
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C10575d().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final void mo23c(String str) {
            Context context = this.f20112c;
            if (context != null) {
                ((Activity) context).runOnUiThread(new RunnableC10572a(str));
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: d */
        public final void mo11d(String str) {
            this.f20117h.putExtras(WPOrderActivity.m6103a(this.f20115f, this.f20116g, this.f20118i, (WPResult<WPUnionOrderInfoBean>) null, true));
            this.f20112c.startActivity(this.f20117h);
            WPResult wPResult = new WPResult();
            wPResult.setCode("0000");
            DataCallback dataCallback = this.f20114e;
            Gson gson = C13648k.f27492a;
            dataCallback.onResult(!(gson instanceof Gson) ? gson.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson, wPResult));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.a$i */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C10576i {

        /* renamed from: a */
        public static C10546a f20125a = new C10546a();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.a$j */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC10577j {
        /* renamed from: a */
        void mo1917a();
    }

    /* renamed from: a */
    public static void m6170a(C10546a c10546a, WPResult wPResult, boolean z, DataCallback dataCallback, InterfaceC10577j interfaceC10577j) {
        c10546a.getClass();
        if ("GATEWAY9000".equals(wPResult.getCode())) {
            C10546a c10546a2 = C10576i.f20125a;
            c10546a2.f20057g = "";
            c10546a2.m6169a("");
            if (!z) {
                c10546a.m6172a(new C10579c(interfaceC10577j));
                return;
            }
        }
        Gson gson = C13648k.f27492a;
        dataCallback.onResult(!(gson instanceof Gson) ? gson.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson, wPResult));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m6165b(Application application) {
        String str = "";
        try {
            str = UnicomFPSDK.getDeviceIdOnline(application, "com.unicom.wopay", this.f20052b == EnvConfig.PRO ? "https://fp.unicompayment.com/unifp/mapi" : "https://fptest.unicompayment.com:8000/unifp/mapi");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.f20051a == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.f20051a.m167a("trace_id", str);
    }

    /* renamed from: a */
    public final void m6178a(final Application application) {
        new Thread(new Runnable() { // from class: com.unicom.pay.-$$Lambda$a$FVJf3y5GwWqoAmxixd2ZT2v2K_k
            @Override // java.lang.Runnable
            public final void run() {
                C10546a.this.m6165b(application);
            }
        }).start();
    }

    /* renamed from: a */
    public final void m6169a(String str) {
        if (TextUtils.isEmpty(this.f20055e) || !this.f20055e.equals(str)) {
            this.f20055e = str;
        }
        this.f20057g = "";
    }

    /* renamed from: a */
    public static void m6171a(C10546a c10546a, Activity activity, String str, boolean z, DataCallback dataCallback) {
        c10546a.getClass();
        try {
            WPResult wPResult = new WPResult();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("authorType", str);
            C14255c.C14256a.f27777a.m30a(C14262f.f27797J, hashMap, new C10584e(c10546a, activity, str, wPResult, dataCallback, z));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public final void m6180a(Activity activity, String str, boolean z, DataCallback dataCallback) {
        try {
            WPResult wPResult = new WPResult();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("authorType", str);
            hashMap.put("deviceId", this.f20061k);
            C14255c.C14256a.f27777a.m30a(C14262f.f27802O, hashMap, new C10552b(wPResult, dataCallback, str, activity, z));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public final void m6172a(InterfaceC10577j interfaceC10577j) {
        NativeFunctionCallBack nativeFunctionCallback = UnicomPaySDK.getInstance().getNativeFunctionCallback();
        C10546a c10546a = C10576i.f20125a;
        String str = System.currentTimeMillis() + "";
        String str2 = c10546a.f20062l;
        String str3 = c10546a.f20063m;
        nativeFunctionCallback.getCode(str2, str3, C13651n.m175a(str + str2 + str3), str, new C10559d(interfaceC10577j));
    }

    /* renamed from: a */
    public final void m6177a(Application application, ConfigInfo configInfo) {
        String str;
        this.f20054d = configInfo.getAppVersion();
        this.f20060j = configInfo.getChannelCode();
        C13652o.f27494a = configInfo.isOpenLog();
        C13660s c13660s = C13660s.C13661a.f27498a;
        this.f20051a = c13660s;
        c13660s.f27497a = application.getSharedPreferences("woapy_unicom_pay", 0);
        EnvConfig envConfig = configInfo.getEnvConfig();
        this.f20052b = envConfig;
        if (envConfig == EnvConfig.SIT) {
            this.f20062l = "edop_unicom_663a9c79";
            str = "MDk2ZGRjZGVlNDhkZmJjOQ==";
        } else if (envConfig == EnvConfig.UAT) {
            this.f20062l = "edop_unicom_6db56c2a";
            str = "NGQyYzhlYTY3NTNjNGZmNQ==";
        } else {
            this.f20062l = "edop_unicom_bc40cc05";
            str = "MzRhYzQ0OWEzNWQ1ZDk5OQ==";
        }
        this.f20063m = str;
        C13642f.f27487a = application;
        m6178a(application);
        String string = this.f20051a.f27497a.getString("clientUUID", "");
        this.f20059i = string;
        if (TextUtils.isEmpty(string)) {
            UUID randomUUID = UUID.randomUUID();
            String str2 = randomUUID.toString().replace("-", "") + System.currentTimeMillis();
            this.f20059i = str2;
            this.f20051a.m167a("clientUUID", str2);
        }
        InterfaceC13717c<?> interfaceC13717c = C13692n.f27556c;
        C13692n.f27554a = application;
        C13690m c13690m = new C13690m();
        C13692n.f27555b = c13690m;
        Application application2 = C13692n.f27554a;
        c13690m.f27547a = application2;
        c13690m.f27548b = C13676a.m141a(application2);
        if (interfaceC13717c == null) {
            interfaceC13717c = new C13678c();
        }
        C13692n.f27556c = interfaceC13717c;
        C13690m c13690m2 = (C13690m) C13692n.f27555b;
        c13690m2.f27550d = interfaceC13717c;
        int i = C10531R.C10535layout.wp_custom_toast;
        if (i > 0) {
            C13693o c13693o = new C13693o(i, interfaceC13717c);
            C13692n.f27556c = c13693o;
            c13690m2.f27550d = c13693o;
        }
        c13690m2.f27550d = new C13680e(C13692n.f27556c);
        C13663u c13663u = C13663u.C13664a.f27509a;
        this.f20053c = c13663u;
        c13663u.f27508a = new C12014f(application);
        StatusInfo licenseKey = JMEncryptBox.setLicenseKey("212".equals(this.f20060j) ? "5E7E47B1125F6E64028E5B035F9CF86F86DC5FCA63205C95ACA5511B652FE26E509F0DA0E3DBA9EB49F36184C733B3CB7D11A43E835071FE9A79D187958813799252FD5A0B501C63A8CCD2E45AE95EF637332F61AB5DFFB06BD4BEBC2C94D41FE6B4" : "225".equals(this.f20060j) ? "0EF08074D7FB7E96AC681EF57D3F38F7BC8EB820BFFDE75547D6653B0228A0C5D8B8C3A12153387EB3FCC6489139C29DCD1D0756A74953A125CE3A0D66AEB60C297AC3E148AA741274A50BCE54820371CA688EC962C847A8FD2C658E2F1A7686E635" : "BDC31435807072FF6E9E85DE77AE0E909F268C7DF03A60C48C9C103F451F5597FCCAEB14780F33D0544A490B2445577565EAA664B0979DEFD06442F42CE80A29C90CA787CB6D058DC2CADF225F75A9B3A434EDFD3AE928B96EB400951D163FBFE62D");
        C13652o.m174a("UnicomPaySDK", licenseKey.getStatusCode() + "");
        C13652o.m174a("UnicomPaySDK", licenseKey.getStatusDescribe());
        C10558c c10558c = new C10558c();
        FidoParam fidoParam = new FidoParam();
        fidoParam.enableUseService(true);
        FidoAppSDK.getInstance().initFidoAsync(application, fidoParam, new HashMap(), c10558c);
        this.f20065o = this.f20051a.f27497a.getString("upMobileNo", "") + this.f20051a.f27497a.getString("loginType", "") + this.f20051a.f27497a.getString("netType", "");
    }

    /* renamed from: a */
    public final void m6182a(Activity activity, DataCallback dataCallback) {
        try {
            HashMap hashMap = new HashMap();
            WPResult wPResult = new WPResult();
            hashMap.put("isHaveFinger", Boolean.valueOf(FidoAppSDK.getInstance().isHaveFinger(activity).getStatus() == FidoStatus.SUCCESS));
            wPResult.setData(hashMap);
            wPResult.setCode("0000");
            Gson gson = C13648k.f27492a;
            dataCallback.onResult(!(gson instanceof Gson) ? gson.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson, wPResult));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public final void m6176a(Context context, String str, String str2, DataCallback dataCallback) {
        try {
            byte[] bArr = C13637b.f27479a;
            try {
                str2 = new String(C13637b.m189a(str2), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            C12270a c12270a = C12270a.C12272b.f24894a;
            c12270a.f24888b = "";
            c12270a.f24889c = "";
            c12270a.f24890d = "";
            c12270a.f24893g = dataCallback;
            c12270a.m1904a(context, str2, str);
            WPTrendsEventsUtils.trendsPageData("极速支付loading页", "98U01170qp018", "qp018");
        } catch (Exception e2) {
            e2.printStackTrace();
            WPResult wPResult = new WPResult();
            wPResult.setCode("0000");
            wPResult.setMsg("");
            Gson gson = C13648k.f27492a;
            dataCallback.onResult(!(gson instanceof Gson) ? gson.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson, wPResult));
        }
    }

    /* renamed from: a */
    public final void m6181a(Activity activity, String str, DataCallback dataCallback) {
        try {
            this.f20064n = dataCallback;
            Intent intent = new Intent(activity, WPValidatePayPassActivity.class);
            int i = WPValidatePayPassActivity.f20173u;
            Bundle bundle = new Bundle();
            bundle.putString("bizCode", "FIDO_OPEN");
            bundle.putString("authType", str);
            intent.putExtras(bundle);
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public final void m6168a(String str, DataCallback dataCallback) {
        try {
            HashMap hashMap = new HashMap();
            WPResult wPResult = new WPResult();
            C13663u c13663u = this.f20053c;
            if (c13663u == null) {
                wPResult.setCode("0005");
                wPResult.setMsg("初始化失败");
                Gson gson = C13648k.f27492a;
                dataCallback.onResult(!(gson instanceof Gson) ? gson.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson, wPResult));
                return;
            }
            WPQPayUserInfoBean m164a = c13663u.m164a(str);
            if (m164a == null) {
                wPResult.setCode("0006");
                wPResult.setMsg("用户未在该设备开通");
                Gson gson2 = C13648k.f27492a;
                dataCallback.onResult(!(gson2 instanceof Gson) ? gson2.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson2, wPResult));
                return;
            }
            hashMap.put("authType", m164a.getCurrentFido());
            wPResult.setData(hashMap);
            wPResult.setCode("0000");
            Gson gson3 = C13648k.f27492a;
            dataCallback.onResult(!(gson3 instanceof Gson) ? gson3.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson3, wPResult));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public final void m6179a(Activity activity, boolean z, DataCallback dataCallback) {
        try {
            WPResult wPResult = new WPResult();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("authorType", "00|02");
            hashMap.put("deviceId", this.f20061k);
            C14255c.C14256a.f27777a.m30a(C14262f.f27798K, hashMap, new C10547a(dataCallback, z, activity, wPResult));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public final void m6173a(Context context, String str, boolean z, DataCallback dataCallback) {
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("qsignUrl", str);
            C14255c.C14256a.f27777a.m30a(C14262f.f27819i, hashMap, new C10560e(context, dataCallback, z, str));
        } catch (Exception e) {
            WPResult wPResult = new WPResult();
            wPResult.setCode("3011219999");
            Gson gson = C13648k.f27492a;
            dataCallback.onResult(!(gson instanceof Gson) ? gson.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson, wPResult));
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public final void m6174a(Context context, String str, String str2, boolean z, DataCallback dataCallback) {
        try {
            Gson gson = C13648k.f27492a;
            WPFindStateRequestBean wPFindStateRequestBean = (WPFindStateRequestBean) (!(gson instanceof Gson) ? gson.fromJson(str2, (Class<Object>) WPFindStateRequestBean.class) : NBSGsonInstrumentation.fromJson(gson, str2, (Class<Object>) WPFindStateRequestBean.class));
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("merNo", wPFindStateRequestBean.getMerNo());
            hashMap.put("recommendedScene", wPFindStateRequestBean.getRecommendedScene());
            hashMap.put("orderBalance", wPFindStateRequestBean.getOrderBalance());
            hashMap.put("qsignUrl", str);
            C14255c.C14256a.f27777a.m30a(C14262f.f27820j, hashMap, new C10565f(context, dataCallback, z, str, str2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public final void m6166a(String str, String str2, String str3) {
        this.f20065o = str3 + str + str2;
        C13660s c13660s = this.f20051a;
        if (c13660s != null) {
            c13660s.m167a("loginType", str);
            this.f20051a.m167a("netType", str2);
            this.f20051a.m167a("upMobileNo", str3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0131 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m6175a(android.content.Context r18, java.lang.String r19, java.lang.String r20, com.unicom.pay.common.callback.DataCallback r21, boolean r22) {
        /*
            Method dump skipped, instructions count: 378
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unicom.pay.C10546a.m6175a(android.content.Context, java.lang.String, java.lang.String, com.unicom.pay.common.callback.DataCallback, boolean):void");
    }

    /* renamed from: a */
    public final void m6167a(String str, String str2, DataCallback dataCallback) {
        try {
            new HashMap();
            WPResult wPResult = new WPResult();
            C13663u c13663u = this.f20053c;
            if (c13663u == null) {
                wPResult.setCode("0005");
                wPResult.setMsg("初始化失败");
                Gson gson = C13648k.f27492a;
                dataCallback.onResult(!(gson instanceof Gson) ? gson.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson, wPResult));
                return;
            }
            WPQPayUserInfoBean m164a = c13663u.m164a(str);
            if (m164a == null) {
                wPResult.setCode("0006");
                wPResult.setMsg("用户未在该设备开通");
                Gson gson2 = C13648k.f27492a;
                dataCallback.onResult(!(gson2 instanceof Gson) ? gson2.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson2, wPResult));
                return;
            }
            m164a.setCurrentFido(str2);
            this.f20053c.m165a(m164a);
            wPResult.setCode("0000");
            Gson gson3 = C13648k.f27492a;
            dataCallback.onResult(!(gson3 instanceof Gson) ? gson3.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson3, wPResult));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
