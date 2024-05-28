package com.gmrz.appsdk.commlib;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.gmrz.appsdk.FidoOut;
import com.gmrz.appsdk.commlib.api.FidoStatus;
import com.gmrz.appsdk.commlib.api.IAppSDK;
import com.gmrz.appsdk.commlib.api.ICommunicationClientResponse;
import com.gmrz.appsdk.entity.OperationHeader;
import com.gmrz.appsdk.entity.UafMessage;
import com.gmrz.appsdk.task.FidoTask;
import com.gmrz.appsdk.util.Logger;
import com.gmrz.fido.offline.CryptoFileSuit;
import com.gmrz.fido.offline.ProcessCallback;
import com.gmrz.fido.offline.StrongBox;
import com.gmrz.uaf.OfflineVerifier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.util.concurrent.Semaphore;
import org.fidoalliance.aidl.IUAFResponseListener;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.gmrz.appsdk.commlib.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class AppSdkBase implements IAppSDK {

    /* renamed from: b */
    private Context f10280b;

    /* renamed from: e */
    private OperationHeader.OperationType f10283e;

    /* renamed from: f */
    private String f10284f;

    /* renamed from: g */
    private SharedPreferences f10285g;

    /* renamed from: h */
    private boolean f10286h;

    /* renamed from: i */
    private boolean f10287i;

    /* renamed from: j */
    private StrongBox f10288j;

    /* renamed from: k */
    private String f10289k;

    /* renamed from: a */
    private final String f10279a = AppSdkBase.class.getSimpleName() + "_fido";

    /* renamed from: c */
    protected FidoOut f10281c = new FidoOut();

    /* renamed from: l */
    private boolean f10290l = true;

    /* renamed from: d */
    protected Semaphore f10282d = new Semaphore(0, true);

    /* compiled from: AppSdkBase.java */
    /* renamed from: com.gmrz.appsdk.commlib.a$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class BinderC4414b extends IUAFResponseListener.AbstractBinderC13421a implements ICommunicationClientResponse {
        public BinderC4414b() {
        }

        @Override // com.gmrz.appsdk.commlib.api.ICommunicationClientResponse
        public void onResponse(Object obj) {
        }

        @Override // org.fidoalliance.aidl.IUAFResponseListener
        public void onResult(Intent intent) {
            Logger.m15757e(AppSdkBase.this.f10279a, "AppSDKBase Response Received => Service ++");
            if (AppSdkBase.this.f10290l) {
                Logger.m15756i(AppSdkBase.this.f10279a, "current session is effective!!");
                AppSdkBase appSdkBase = AppSdkBase.this;
                appSdkBase.f10281c = appSdkBase.m15838a(intent);
            } else {
                Logger.m15756i(AppSdkBase.this.f10279a, "current session is abandoned!!");
            }
            AppSdkBase.this.f10282d.release();
            Logger.m15757e(AppSdkBase.this.f10279a, "AppSDKBase Response Notified  => Service ==");
        }
    }

    @Override // com.gmrz.appsdk.commlib.api.IAppSDK
    public FidoOut process(Context context, Object obj) {
        if (context == null) {
            Logger.m15757e(this.f10279a, "AppSdkBase process method pass in params context is null");
            FidoOut fidoOut = this.f10281c;
            fidoOut.fidoStatus = FidoStatus.PROTOCOL_ERROR;
            return fidoOut;
        }
        this.f10280b = context;
        if (obj instanceof Intent) {
            Bundle extras = ((Intent) obj).getExtras();
            if (extras == null) {
                Logger.m15757e(this.f10279a, "bundle in request intent is null");
                FidoOut fidoOut2 = this.f10281c;
                fidoOut2.fidoStatus = FidoStatus.PROTOCOL_ERROR;
                return fidoOut2;
            }
            boolean z = extras.getBoolean("offline");
            this.f10286h = z;
            Logger.m15756i(this.f10279a, z ? "offline mode enable" : "offline mode disable");
            String string = extras.getString("message");
            if (this.f10286h && !TextUtils.isEmpty(string)) {
                Logger.m15756i(this.f10279a, "...offline mode prepare process...");
                boolean z2 = extras.getBoolean("exact");
                this.f10287i = z2;
                Logger.m15756i(this.f10279a, z2 ? "exact match enable" : "exact match disable");
                String string2 = extras.getString("cacheFileName");
                this.f10289k = string2;
                this.f10285g = context.getSharedPreferences(string2, 0);
                if (string2.split("_")[1].equals("03")) {
                    this.f10287i = true;
                }
                try {
                    this.f10288j = new StrongBox(string2);
                    if (TextUtils.isEmpty(CryptoFileSuit.m15719b(context, string2))) {
                        this.f10288j.m15715a();
                    }
                    String optString = new JSONObject(string).optString("uafProtocolMessage");
                    Gson create = new GsonBuilder().setPrettyPrinting().create();
                    UafMessage[] uafMessageArr = (UafMessage[]) (!(create instanceof Gson) ? create.fromJson(optString, (Class<Object>) UafMessage[].class) : NBSGsonInstrumentation.fromJson(create, optString, (Class<Object>) UafMessage[].class));
                    if (uafMessageArr != null && uafMessageArr.length != 0) {
                        this.f10283e = uafMessageArr[0].header.getOp();
                        String str = this.f10279a;
                        Logger.m15756i(str, "get operation type from uafMessage: " + this.f10283e);
                        if (this.f10283e.equals(OperationHeader.OperationType.Reg)) {
                            Logger.m15756i(this.f10279a, "offline prepare process find will do register process and save challenge");
                            String str2 = uafMessageArr[0].challenge;
                            this.f10284f = str2;
                            if (TextUtils.isEmpty(str2)) {
                                throw new IllegalArgumentException("uafMessages[0].challenge is null");
                            }
                        }
                    }
                    Logger.m15757e(this.f10279a, "parse uaf message is null can not do prepare for offline authenticate");
                    FidoOut fidoOut3 = this.f10281c;
                    fidoOut3.fidoStatus = FidoStatus.PROTOCOL_ERROR;
                    return fidoOut3;
                } catch (Exception e) {
                    e.printStackTrace();
                    FidoOut fidoOut4 = this.f10281c;
                    fidoOut4.fidoStatus = FidoStatus.FAILED;
                    return fidoOut4;
                }
            }
            FidoOut fidoOut5 = this.f10281c;
            fidoOut5.fidoStatus = FidoStatus.SUCCESS;
            return fidoOut5;
        }
        Logger.m15757e(this.f10279a, "requestData is not Intent type params");
        FidoOut fidoOut6 = this.f10281c;
        fidoOut6.fidoStatus = FidoStatus.PROTOCOL_ERROR;
        return fidoOut6;
    }

    /* renamed from: a */
    public void m15835a(boolean z) {
        this.f10290l = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public FidoOut m15838a(Intent intent) {
        FidoOut fidoOut = new FidoOut();
        fidoOut.fidoStatus = FidoStatus.PROTOCOL_ERROR;
        if (intent == null) {
            Logger.m15757e(this.f10279a, "Malformed response: data is missing");
            return fidoOut;
        }
        Bundle extras = intent.getExtras();
        if (extras == null) {
            Logger.m15757e(this.f10279a, "Malformed response: data.extras is missing");
            return fidoOut;
        } else if (!extras.containsKey("componentName")) {
            Logger.m15757e(this.f10279a, "Malformed response: mandatory field IEN_COMPONENT_NAME is missing");
            return fidoOut;
        } else if (!extras.containsKey("errorCode")) {
            Logger.m15757e(this.f10279a, "Malformed response: mandatory field IEN_ERROR_CODE is missing");
            return fidoOut;
        } else if (!extras.containsKey("UAFIntentType")) {
            Logger.m15757e(this.f10279a, "Malformed response: mandatory field IEN_UAF_INTENT_TYPE is missing");
            return fidoOut;
        } else {
            String string = extras.getString("UAFIntentType");
            try {
                UAFIntentType valueOf = UAFIntentType.valueOf(string);
                switch (valueOf.ordinal()) {
                    case 0:
                    case 2:
                    case 4:
                        break;
                    case 1:
                        if (extras.containsKey("discoveryData")) {
                            fidoOut.discoveryData = extras.getString("discoveryData");
                            break;
                        } else {
                            Logger.m15757e(this.f10279a, "IEN_DISCOVERY_DATA is not set");
                            break;
                        }
                    case 3:
                    case 5:
                        if (extras.containsKey("message")) {
                            UAFMessage uAFMessage = new UAFMessage(intent.getExtras().getString("message"));
                            fidoOut.fidoResponse = uAFMessage.f10272a;
                            fidoOut.responseParams = uAFMessage.f10273b;
                            break;
                        } else {
                            Logger.m15757e(this.f10279a, "IEN_MESSAGE is not set");
                            break;
                        }
                    default:
                        String str = this.f10279a;
                        Logger.m15757e(str, "Unsupported IEN_UAF_INTENT_TYPE " + valueOf);
                        return fidoOut;
                }
                fidoOut.fidoStatus = FidoTask.m15763a(extras.getShort("errorCode"));
                String str2 = this.f10279a;
                Logger.m15757e(str2, "fidoStatus:" + fidoOut.fidoStatus);
                return fidoOut;
            } catch (IllegalArgumentException unused) {
                String str3 = this.f10279a;
                Logger.m15757e(str3, "Malformed response: unknown IEN_UAF_INTENT_TYPE " + string);
                return fidoOut;
            }
        }
    }

    /* compiled from: AppSdkBase.java */
    /* renamed from: com.gmrz.appsdk.commlib.a$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C4411a implements ICommunicationClientResponse {
        public C4411a() {
        }

        @Override // com.gmrz.appsdk.commlib.api.ICommunicationClientResponse
        public void onResponse(Object obj) {
            Logger.m15757e(AppSdkBase.this.f10279a, "AppSDKBase Response Received => NORMAL ++");
            if (AppSdkBase.this.f10290l) {
                Logger.m15756i(AppSdkBase.this.f10279a, "current session is effective!!");
                if (obj != null) {
                    AppSdkBase.this.f10281c = (FidoOut) obj;
                }
                AppSdkBase appSdkBase = AppSdkBase.this;
                String str = appSdkBase.f10281c.fidoResponse;
                if (appSdkBase.f10286h && !TextUtils.isEmpty(str)) {
                    Logger.m15756i(AppSdkBase.this.f10279a, "!! in offline mode !! received fido response message");
                    try {
                        String optString = new JSONArray(str).optJSONObject(0).optJSONArray("assertions").optString(0);
                        if (TextUtils.isEmpty(optString)) {
                            Logger.m15757e(AppSdkBase.this.f10279a, "fido response assertion json parse failed");
                            AppSdkBase.this.f10281c.fidoStatus = FidoStatus.PROTOCOL_ERROR;
                            return;
                        } else if (AppSdkBase.this.f10283e.equals(OperationHeader.OperationType.Reg)) {
                            Logger.m15756i(AppSdkBase.this.f10279a, "--## REG: fido response received and post process for offline authenticate ##--");
                            if (!TextUtils.isEmpty(new JSONArray(str).optJSONObject(0).optJSONObject("header").optString("appID"))) {
                                Logger.m15757e(AppSdkBase.this.f10279a, "sorry, buddy!! offline mode ONLY support appID null situation, has value not supportable");
                            }
                            String[] parsedRegAssertion = OfflineVerifier.getInstance().parsedRegAssertion(optString);
                            if (!TextUtils.isEmpty(parsedRegAssertion[0]) && !TextUtils.isEmpty(parsedRegAssertion[1]) && !TextUtils.isEmpty(parsedRegAssertion[2]) && !TextUtils.isEmpty(parsedRegAssertion[3])) {
                                StringBuilder sb = new StringBuilder();
                                int i = 0;
                                for (String str2 : parsedRegAssertion) {
                                    if (i == 3 && !AppSdkBase.this.f10287i) {
                                        break;
                                    }
                                    sb.append(str2);
                                    sb.append("@");
                                    i++;
                                }
                                String sb2 = sb.toString();
                                String substring = sb2.substring(0, sb2.length() - 1);
                                if (AppSdkBase.this.f10288j != null) {
                                    AppSdkBase.this.f10288j.m15713a(new C4412a());
                                    if (Build.VERSION.SDK_INT >= 23) {
                                        AppSdkBase.this.f10288j.m15714a(1, substring, null);
                                    }
                                } else {
                                    throw new Exception("strong box instance is null");
                                }
                            }
                            Logger.m15757e(AppSdkBase.this.f10279a, "received register response assertion parsed failed");
                            AppSdkBase.this.f10281c.fidoStatus = FidoStatus.PROTOCOL_ERROR;
                            return;
                        } else if (AppSdkBase.this.f10283e.equals(OperationHeader.OperationType.Auth)) {
                            Logger.m15756i(AppSdkBase.this.f10279a, "--## AUTH: fido response received and post process for offline authenticate ##--");
                            if (AppSdkBase.this.f10288j != null) {
                                AppSdkBase.this.f10288j.m15713a(new C4413b(optString));
                                String string = AppSdkBase.this.f10285g.getString("cache", "");
                                if (!TextUtils.isEmpty(string)) {
                                    String m15719b = CryptoFileSuit.m15719b(AppSdkBase.this.f10280b, AppSdkBase.this.f10289k);
                                    if (!TextUtils.isEmpty(m15719b)) {
                                        if (Build.VERSION.SDK_INT >= 23) {
                                            AppSdkBase.this.f10288j.m15714a(2, string, m15719b);
                                        }
                                    } else {
                                        throw new Exception("get aes iv from file failed");
                                    }
                                } else {
                                    throw new Exception("get encrypt cached user info from shared preferences failed");
                                }
                            } else {
                                throw new Exception("strong box instance is null");
                            }
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        AppSdkBase.this.f10281c.fidoStatus = FidoStatus.NOT_INSTALLED;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        AppSdkBase.this.f10281c.fidoStatus = FidoStatus.FAILED;
                    }
                }
            } else {
                Logger.m15756i(AppSdkBase.this.f10279a, "current session is abandoned!!");
            }
            AppSdkBase.this.f10282d.release();
            Logger.m15757e(AppSdkBase.this.f10279a, "AppSDKBase Response Notified => NORMAL ==");
        }

        /* compiled from: AppSdkBase.java */
        /* renamed from: com.gmrz.appsdk.commlib.a$a$a */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        class C4412a extends ProcessCallback {
            C4412a() {
            }

            @Override // com.gmrz.fido.offline.ProcessCallback, com.gmrz.fido.offline.StrongBox.InterfaceC4438a
            /* renamed from: a */
            public void mo15710a(String str, String str2) {
                if (!AppSdkBase.this.f10285g.edit().putString("cache", str).commit()) {
                    Logger.wtf(AppSdkBase.this.f10279a, "offline auth prepare: save user info to shared preferences failed!!");
                    AppSdkBase.this.f10281c.fidoStatus = FidoStatus.FAILED;
                } else if (!CryptoFileSuit.m15722a(AppSdkBase.this.f10280b, AppSdkBase.this.f10289k, str2)) {
                    Logger.wtf(AppSdkBase.this.f10279a, "offline auth prepare: save aes iv to file failed!!");
                    AppSdkBase.this.f10281c.fidoStatus = FidoStatus.FAILED;
                } else if (!AppSdkBase.this.f10285g.edit().putString("challenge", AppSdkBase.this.f10284f).commit()) {
                    Logger.wtf(AppSdkBase.this.f10279a, "offline auth prepare: save challenge to shared preferences failed!!");
                    AppSdkBase.this.f10281c.fidoStatus = FidoStatus.FAILED;
                } else {
                    String str3 = AppSdkBase.this.f10279a;
                    Logger.m15756i(str3, "challenge: " + AppSdkBase.this.f10284f);
                    Logger.m15756i(AppSdkBase.this.f10279a, "offline auth prepare: save data for offline authenticate successful ^_^!!");
                }
            }

            @Override // com.gmrz.fido.offline.StrongBox.InterfaceC4438a
            /* renamed from: a */
            public void mo15712a(Exception exc) {
                exc.printStackTrace();
                AppSdkBase.this.f10281c.fidoStatus = FidoStatus.FAILED;
            }
        }

        /* compiled from: AppSdkBase.java */
        /* renamed from: com.gmrz.appsdk.commlib.a$a$b */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        class C4413b extends ProcessCallback {

            /* renamed from: a */
            final /* synthetic */ String f10293a;

            C4413b(String str) {
                this.f10293a = str;
            }

            @Override // com.gmrz.fido.offline.ProcessCallback, com.gmrz.fido.offline.StrongBox.InterfaceC4438a
            /* renamed from: a */
            public void mo15711a(String str) {
                if (TextUtils.isEmpty(str) || !str.contains("@")) {
                    Logger.m15757e(AppSdkBase.this.f10279a, "decrypt cached user info failed");
                    AppSdkBase.this.f10281c.fidoStatus = FidoStatus.FAILED;
                    return;
                }
                String[] split = str.split("@");
                String str2 = split[2];
                String str3 = split.length == 4 ? split[3] : null;
                String[] strArr = {Boolean.toString(false), null};
                try {
                    strArr = OfflineVerifier.getInstance().verifySignature(this.f10293a, str2, str3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                AppSdkBase.this.f10281c.fidoStatus = Boolean.parseBoolean(strArr[0]) ? FidoStatus.SUCCESS : FidoStatus.FAILED;
                AppSdkBase.this.f10281c.fidoStatusMsg = strArr[1];
            }

            @Override // com.gmrz.fido.offline.StrongBox.InterfaceC4438a
            /* renamed from: a */
            public void mo15712a(Exception exc) {
                exc.printStackTrace();
                AppSdkBase.this.f10281c.fidoStatus = FidoStatus.FAILED;
            }
        }
    }
}
