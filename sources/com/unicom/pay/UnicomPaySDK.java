package com.unicom.pay;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10546a;
import com.unicom.pay.common.bean.ConfigInfo;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.common.bean.WPTicketBean;
import com.unicom.pay.common.callback.DataCallback;
import com.unicom.pay.common.callback.NativeFunctionCallBack;
import com.unicom.pay.normal.order.bean.WPCompleteUserInfoNotifyBean;
import com.unicom.pay.qpay.open.bean.WPQPayUserInfoBean;
import com.unicom.pay.qpay.open.p360ui.WPOpenQPayActivity;
import com.unicom.pay.qpay.setting.p361ui.WPQPaySettingActivity;
import java.lang.reflect.Type;
import java.util.HashMap;
import org.greenrobot.eventbus.EventBus;
import p088b.C1466b;
import p393h.EnumC12015g;
import p470p0.C13636a;
import p470p0.C13648k;
import p470p0.C13649l;
import p470p0.C13651n;
import p470p0.C13652o;
import p470p0.RunnableC13643g;

@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class UnicomPaySDK {
    private NativeFunctionCallBack mCallback;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.UnicomPaySDK$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10538a implements NativeFunctionCallBack {
        @Override // com.unicom.pay.common.callback.NativeFunctionCallBack
        public final void aliPay(Context context, String str, DataCallback dataCallback) {
        }

        @Override // com.unicom.pay.common.callback.NativeFunctionCallBack
        public final void getCode(String str, String str2, String str3, String str4, DataCallback dataCallback) {
        }

        @Override // com.unicom.pay.common.callback.NativeFunctionCallBack
        public final void loadUrl(String str, boolean z) {
        }

        @Override // com.unicom.pay.common.callback.NativeFunctionCallBack
        public final void openWebview(Context context, String str) {
        }

        @Override // com.unicom.pay.common.callback.NativeFunctionCallBack
        public final void openWebview(Context context, String str, String str2) {
        }

        @Override // com.unicom.pay.common.callback.NativeFunctionCallBack
        public final void reloadUrl() {
        }

        @Override // com.unicom.pay.common.callback.NativeFunctionCallBack
        public final void trendsEvent(String str, String str2, String str3, String str4) {
        }

        @Override // com.unicom.pay.common.callback.NativeFunctionCallBack
        public final void unionPay(Context context, String str, DataCallback dataCallback) {
        }
    }

    @NBSInstrumented
    /* renamed from: com.unicom.pay.UnicomPaySDK$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10539b implements DataCallback {

        /* renamed from: a */
        public final /* synthetic */ C10546a f20043a;

        /* renamed from: b */
        public final /* synthetic */ Activity f20044b;

        /* renamed from: c */
        public final /* synthetic */ String f20045c;

        /* renamed from: d */
        public final /* synthetic */ String f20046d;

        /* renamed from: e */
        public final /* synthetic */ String f20047e;

        /* renamed from: f */
        public final /* synthetic */ DataCallback f20048f;

        public C10539b(C10546a c10546a, Activity activity, String str, String str2, String str3, DataCallback dataCallback) {
            this.f20043a = c10546a;
            this.f20044b = activity;
            this.f20045c = str;
            this.f20046d = str2;
            this.f20047e = str3;
            this.f20048f = dataCallback;
        }

        @Override // com.unicom.pay.common.callback.DataCallback
        public final void onResult(String str) {
            C13652o.m174a("UnicomPaySdk code:", str);
            try {
                Gson gson = C13648k.f27492a;
                this.f20043a.f20057g = ((WPTicketBean) (!(gson instanceof Gson) ? gson.fromJson(str, (Class<Object>) WPTicketBean.class) : NBSGsonInstrumentation.fromJson(gson, str, (Class<Object>) WPTicketBean.class))).getTicket();
            } catch (Exception unused) {
                this.f20043a.f20057g = "";
            }
            UnicomPaySDK.this.dealAction(this.f20044b, this.f20045c, this.f20046d, this.f20047e, this.f20048f);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.UnicomPaySDK$c */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    class C10540c extends TypeToken<HashMap<String, Object>> {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.UnicomPaySDK$d */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10541d extends TypeToken<HashMap<String, String>> {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.UnicomPaySDK$e */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10542e extends TypeToken<HashMap<String, String>> {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.UnicomPaySDK$f */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10543f extends TypeToken<HashMap<String, String>> {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.UnicomPaySDK$g */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10544g extends TypeToken<HashMap<String, String>> {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.UnicomPaySDK$h */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C10545h {

        /* renamed from: a */
        public static final UnicomPaySDK f20050a = new UnicomPaySDK(null);
    }

    private UnicomPaySDK() {
    }

    public /* synthetic */ UnicomPaySDK(C10538a c10538a) {
        this();
    }

    private void callbackResult(String str, String str2, DataCallback dataCallback) {
        WPResult wPResult = new WPResult();
        wPResult.setCode(str);
        wPResult.setMsg(str2);
        Gson gson = C13648k.f27492a;
        dataCallback.onResult(!(gson instanceof Gson) ? gson.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson, wPResult));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealAction(Activity activity, String str, String str2, String str3, DataCallback dataCallback) {
        C10546a c10546a;
        HashMap hashMap;
        HashMap hashMap2;
        HashMap hashMap3;
        HashMap hashMap4;
        try {
            C13652o.m174a("UnicomPaySdk action:", str2);
            C13652o.m174a("UnicomPaySdk json:", str3);
            c10546a = C10546a.C10576i.f20125a;
        } catch (Exception unused) {
        }
        if ("queryQuickPayStatus".equals(str2)) {
            c10546a.m6173a((Context) activity, str, false, dataCallback);
        } else if ("queryQuickPayStatusByScene".equals(str2)) {
            c10546a.m6174a((Context) activity, str, str3, false, dataCallback);
        } else {
            if ("openOneClickPay".equals(str2)) {
                c10546a.getClass();
                try {
                    Intent intent = new Intent(activity, WPOpenQPayActivity.class);
                    int i = WPOpenQPayActivity.f20386I;
                    Bundle bundle = new Bundle();
                    bundle.putString("bizCode", "QPAY_SET_SIGN");
                    intent.putExtras(bundle);
                    activity.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("jumpPaySet".equals(str2)) {
                c10546a.getClass();
                try {
                    Intent intent2 = new Intent(activity, WPQPaySettingActivity.class);
                    int i2 = WPQPaySettingActivity.f20416C;
                    Bundle bundle2 = new Bundle();
                    bundle2.putBoolean("isFromWebSetting", true);
                    intent2.putExtras(bundle2);
                    activity.startActivity(intent2);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else if ("quickPay".equals(str2)) {
                c10546a.m6176a(activity, str, str3, dataCallback);
            } else if ("openNormalClickPay".equals(str2)) {
                c10546a.m6175a((Context) activity, str, str3, dataCallback, false);
            } else if ("queryFidoSupport".equals(str2)) {
                c10546a.getClass();
                try {
                    C13652o.m174a("WopayConfig code:", "checkDeviceSupport");
                    new Thread(new RunnableC13643g(activity, new C1466b(c10546a, activity, dataCallback))).start();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            } else if ("queryFidoStatus".equals(str2)) {
                c10546a.m6179a(activity, false, dataCallback);
            } else if ("unRegisterFido".equals(str2)) {
                if (TextUtils.isEmpty(str3)) {
                    hashMap4 = new HashMap();
                } else {
                    Type type = new C10541d().getType();
                    Gson gson = C13648k.f27492a;
                    hashMap4 = (HashMap) (!(gson instanceof Gson) ? gson.fromJson(str3, type) : NBSGsonInstrumentation.fromJson(gson, str3, type));
                }
                c10546a.m6180a(activity, (String) hashMap4.get("authType"), false, dataCallback);
            } else if ("registerFido".equals(str2)) {
                if (TextUtils.isEmpty(str3)) {
                    hashMap3 = new HashMap();
                } else {
                    Type type2 = new C10542e().getType();
                    Gson gson2 = C13648k.f27492a;
                    hashMap3 = (HashMap) (!(gson2 instanceof Gson) ? gson2.fromJson(str3, type2) : NBSGsonInstrumentation.fromJson(gson2, str3, type2));
                }
                c10546a.m6181a(activity, (String) hashMap3.get("authType"), dataCallback);
            } else if ("isHaveFinger".equals(str2)) {
                c10546a.m6182a(activity, dataCallback);
            } else if ("updateFidoAuthType".equals(str2)) {
                if (TextUtils.isEmpty(str3)) {
                    hashMap2 = new HashMap();
                } else {
                    Type type3 = new C10543f().getType();
                    Gson gson3 = C13648k.f27492a;
                    hashMap2 = (HashMap) (!(gson3 instanceof Gson) ? gson3.fromJson(str3, type3) : NBSGsonInstrumentation.fromJson(gson3, str3, type3));
                }
                c10546a.m6167a((String) hashMap2.get(WPQPayUserInfoBean.QPAY_COLUMN_ID), (String) hashMap2.get("authType"), dataCallback);
            } else if ("queryFidoFirstType".equals(str2)) {
                if (TextUtils.isEmpty(str3)) {
                    hashMap = new HashMap();
                } else {
                    Type type4 = new C10544g().getType();
                    Gson gson4 = C13648k.f27492a;
                    hashMap = (HashMap) (!(gson4 instanceof Gson) ? gson4.fromJson(str3, type4) : NBSGsonInstrumentation.fromJson(gson4, str3, type4));
                }
                c10546a.m6168a((String) hashMap.get(WPQPayUserInfoBean.QPAY_COLUMN_ID), dataCallback);
            } else if ("closeQpay".equals(str2)) {
                c10546a.getClass();
                try {
                    EventBus.getDefault().post(EnumC12015g.H5_CLOSE_QPAY);
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
        }
    }

    private void getCode(DataCallback dataCallback) {
        try {
            C10546a c10546a = C10546a.C10576i.f20125a;
            String str = System.currentTimeMillis() + "";
            String str2 = c10546a.f20062l;
            String str3 = c10546a.f20063m;
            this.mCallback.getCode(str2, str3, C13651n.m175a(str + str2 + str3), str, dataCallback);
        } catch (Exception unused) {
        }
    }

    public static UnicomPaySDK getInstance() {
        return C10545h.f20050a;
    }

    private void release() {
        C10546a.C10576i.f20125a.f20065o = "";
    }

    public NativeFunctionCallBack getNativeFunctionCallback() {
        NativeFunctionCallBack nativeFunctionCallBack = this.mCallback;
        return nativeFunctionCallBack == null ? new C10538a() : nativeFunctionCallBack;
    }

    public String getSDKVersion() {
        return "1.8.0";
    }

    public void init(Application application, ConfigInfo configInfo) {
        try {
            C10546a.C10576i.f20125a.m6177a(application, configInfo);
        } catch (Exception unused) {
        }
    }

    public void login(String str, String str2, String str3) {
        try {
            C13652o.m174a("UnicomPaySdk loginType", str);
            C13652o.m174a("UnicomPaySdk netType", str2);
            C10546a c10546a = C10546a.C10576i.f20125a;
            c10546a.m6169a("");
            c10546a.f20058h = "";
            c10546a.f20056f = "";
            C10546a.C10576i.f20125a.m6166a(str, str2, str3);
        } catch (Exception unused) {
        }
    }

    public void logout() {
        try {
            C13652o.m174a("UnicomPaySdk", "被登出了");
            C10546a c10546a = C10546a.C10576i.f20125a;
            c10546a.m6169a("");
            c10546a.f20058h = "";
            c10546a.f20056f = "";
            C13636a.m190a();
            C10546a.C10576i.f20125a.m6166a("", "", "");
        } catch (Exception unused) {
        }
    }

    public void notify(Activity activity, String str, String str2, DataCallback dataCallback) {
        HashMap hashMap;
        try {
            C13652o.m174a("UnicomPaySdk action:", str);
            C13652o.m174a("UnicomPaySdk json:", str2);
            if (TextUtils.isEmpty(str2)) {
                hashMap = new HashMap();
            } else {
                Type type = new C10540c().getType();
                Gson gson = C13648k.f27492a;
                hashMap = (HashMap) (!(gson instanceof Gson) ? gson.fromJson(str2, type) : NBSGsonInstrumentation.fromJson(gson, str2, type));
            }
            C10546a c10546a = C10546a.C10576i.f20125a;
            if ("notifyBindBankSuccess".equals(str)) {
                String str3 = (String) hashMap.get("serialNo");
                String str4 = hashMap.containsKey("scene") ? (String) hashMap.get("scene") : "";
                c10546a.getClass();
                try {
                    EnumC12015g enumC12015g = EnumC12015g.BANK_CHANGE;
                    enumC12015g.f24357a = new WPCompleteUserInfoNotifyBean(str3, str4);
                    EventBus.getDefault().post(enumC12015g);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("notifyCompleteUserInfoSuccess".equals(str)) {
                String str5 = hashMap.containsKey("scene") ? (String) hashMap.get("scene") : "";
                c10546a.getClass();
                try {
                    EnumC12015g enumC12015g2 = EnumC12015g.PWD_CHANGE;
                    enumC12015g2.f24357a = new WPCompleteUserInfoNotifyBean("", str5);
                    EventBus.getDefault().post(enumC12015g2);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else if ("notifyWebPayRefresh".equals(str)) {
                String str6 = (String) hashMap.get("isDealing");
                String str7 = (String) hashMap.get("tradeOrderNo");
                c10546a.getClass();
                try {
                    if ("0".equals(str6)) {
                        EnumC12015g enumC12015g3 = EnumC12015g.H5_PAY_RESULT;
                        enumC12015g3.f24357a = str7;
                        EventBus.getDefault().post(enumC12015g3);
                    } else {
                        EventBus.getDefault().post(EnumC12015g.H5_PAY_DEALING);
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        } catch (Exception unused) {
        }
    }

    public void onDestroy() {
        try {
            release();
        } catch (Exception unused) {
        }
    }

    public void pay(Activity activity, String str, String str2, String str3, DataCallback dataCallback) {
        String str4;
        try {
            C10546a c10546a = C10546a.C10576i.f20125a;
            c10546a.f20056f = "";
            if (!"queryQuickPayStatus".equals(str2) && !"queryQuickPayStatusByScene".equals(str2)) {
                if (("openNormalClickPay".equals(str2) || "quickPay".equals(str2)) && C13649l.m176a(c10546a.f20055e)) {
                    c10546a.f20057g = "";
                    str4 = "";
                    c10546a.m6169a(str4);
                }
                if (this.mCallback == null && TextUtils.isEmpty(c10546a.f20057g) && TextUtils.isEmpty(c10546a.f20055e)) {
                    getCode(new C10539b(c10546a, activity, str, str2, str3, dataCallback));
                    return;
                } else {
                    dealAction(activity, str, str2, str3, dataCallback);
                }
            }
            c10546a.f20057g = "";
            str4 = "";
            c10546a.m6169a(str4);
            if (this.mCallback == null) {
            }
            dealAction(activity, str, str2, str3, dataCallback);
        } catch (Exception unused) {
        }
    }

    public void setNativeFunctionCallback(NativeFunctionCallBack nativeFunctionCallBack) {
        this.mCallback = nativeFunctionCallBack;
    }
}
