package com.huawei.agconnect.core.p211a;

import android.content.Context;
import android.util.Log;
import com.huawei.agconnect.AGCRoutePolicy;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.AGConnectOptions;
import com.huawei.agconnect.AGConnectOptionsBuilder;
import com.huawei.agconnect.CustomAuthProvider;
import com.huawei.agconnect.CustomCredentialsProvider;
import com.huawei.agconnect.JsonProcessingFactory;
import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.agconnect.config.impl.C4770a;
import com.huawei.agconnect.config.impl.C4771b;
import com.huawei.agconnect.config.impl.Utils;
import com.huawei.agconnect.core.Service;
import com.huawei.agconnect.core.service.auth.AuthProvider;
import com.huawei.agconnect.core.service.auth.CredentialsProvider;
import com.huawei.agconnect.core.service.auth.OnTokenListener;
import com.huawei.agconnect.core.service.auth.Token;
import com.huawei.hmf.tasks.Task;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.huawei.agconnect.core.a.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4785b extends AGConnectInstance {

    /* renamed from: a */
    private static List<Service> f10801a;

    /* renamed from: b */
    private static final Map<String, AGConnectInstance> f10802b = new HashMap();

    /* renamed from: c */
    private static String f10803c;

    /* renamed from: d */
    private final AGConnectOptions f10804d;

    /* renamed from: e */
    private final C4793d f10805e;

    /* renamed from: f */
    private final C4793d f10806f;

    public C4785b(AGConnectOptions aGConnectOptions) {
        this.f10804d = aGConnectOptions;
        if (f10801a == null) {
            Log.e("AGConnectInstance", "please call `initialize()` first");
        }
        this.f10805e = new C4793d(f10801a, aGConnectOptions.getContext());
        this.f10806f = new C4793d(null, aGConnectOptions.getContext());
        if (aGConnectOptions instanceof C4771b) {
            this.f10806f.m15375a(((C4771b) aGConnectOptions).m15424a(), aGConnectOptions.getContext());
        }
    }

    /* renamed from: a */
    public static AGConnectInstance m15395a() {
        String str = f10803c;
        if (str == null) {
            str = "DEFAULT_INSTANCE";
        }
        return m15387a(str);
    }

    /* renamed from: a */
    public static AGConnectInstance m15391a(AGConnectOptions aGConnectOptions) {
        return m15390a(aGConnectOptions, false);
    }

    /* renamed from: a */
    private static synchronized AGConnectInstance m15390a(AGConnectOptions aGConnectOptions, boolean z) {
        AGConnectInstance aGConnectInstance;
        synchronized (C4785b.class) {
            aGConnectInstance = f10802b.get(aGConnectOptions.getIdentifier());
            if (aGConnectInstance == null || z) {
                aGConnectInstance = new C4785b(aGConnectOptions);
                f10802b.put(aGConnectOptions.getIdentifier(), aGConnectInstance);
            }
        }
        return aGConnectInstance;
    }

    /* renamed from: a */
    public static synchronized AGConnectInstance m15387a(String str) {
        AGConnectInstance aGConnectInstance;
        synchronized (C4785b.class) {
            aGConnectInstance = f10802b.get(str);
            if (aGConnectInstance == null) {
                if ("DEFAULT_INSTANCE".equals(str)) {
                    Log.w("AGC_Instance", "please call `initialize()` first");
                } else {
                    Log.w("AGC_Instance", "not find instance for : " + str);
                }
            }
        }
        return aGConnectInstance;
    }

    /* renamed from: a */
    public static synchronized void m15394a(Context context) {
        synchronized (C4785b.class) {
            if (f10802b.size() > 0) {
                Log.w("AGC_Instance", "Repeated invoking initialize");
            } else {
                m15393a(context, AGConnectServicesConfig.fromContext(context));
            }
        }
    }

    /* renamed from: a */
    private static synchronized void m15393a(Context context, AGConnectOptions aGConnectOptions) {
        synchronized (C4785b.class) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                Log.w("AGC_Instance", "context.getApplicationContext null");
            } else {
                context = applicationContext;
            }
            m15386b();
            C4770a.m15427a(context);
            if (f10801a == null) {
                f10801a = new C4790c(context).m15384a();
            }
            m15390a(aGConnectOptions, true);
            f10803c = aGConnectOptions.getIdentifier();
            C4784a.m15396a();
            Log.i("AGC_Instance", "AGC SDK initialize end");
        }
    }

    /* renamed from: a */
    public static synchronized void m15392a(Context context, AGConnectOptionsBuilder aGConnectOptionsBuilder) {
        synchronized (C4785b.class) {
            m15385b(context, aGConnectOptionsBuilder);
            m15393a(context, aGConnectOptionsBuilder.build(context));
        }
    }

    /* renamed from: b */
    private static void m15386b() {
        JsonProcessingFactory.registerProcessor("/agcgw/url", new JsonProcessingFactory.JsonProcessor() { // from class: com.huawei.agconnect.core.a.b.1
            @Override // com.huawei.agconnect.JsonProcessingFactory.JsonProcessor
            public String processOption(AGConnectOptions aGConnectOptions) {
                String str;
                if (aGConnectOptions.getRoutePolicy().equals(AGCRoutePolicy.CHINA)) {
                    str = "/agcgw_all/CN";
                } else if (aGConnectOptions.getRoutePolicy().equals(AGCRoutePolicy.RUSSIA)) {
                    str = "/agcgw_all/RU";
                } else if (aGConnectOptions.getRoutePolicy().equals(AGCRoutePolicy.GERMANY)) {
                    str = "/agcgw_all/DE";
                } else if (!aGConnectOptions.getRoutePolicy().equals(AGCRoutePolicy.SINGAPORE)) {
                    return null;
                } else {
                    str = "/agcgw_all/SG";
                }
                return aGConnectOptions.getString(str);
            }
        });
        JsonProcessingFactory.registerProcessor("/agcgw/backurl", new JsonProcessingFactory.JsonProcessor() { // from class: com.huawei.agconnect.core.a.b.2
            @Override // com.huawei.agconnect.JsonProcessingFactory.JsonProcessor
            public String processOption(AGConnectOptions aGConnectOptions) {
                String str;
                if (aGConnectOptions.getRoutePolicy().equals(AGCRoutePolicy.CHINA)) {
                    str = "/agcgw_all/CN_back";
                } else if (aGConnectOptions.getRoutePolicy().equals(AGCRoutePolicy.RUSSIA)) {
                    str = "/agcgw_all/RU_back";
                } else if (aGConnectOptions.getRoutePolicy().equals(AGCRoutePolicy.GERMANY)) {
                    str = "/agcgw_all/DE_back";
                } else if (!aGConnectOptions.getRoutePolicy().equals(AGCRoutePolicy.SINGAPORE)) {
                    return null;
                } else {
                    str = "/agcgw_all/SG_back";
                }
                return aGConnectOptions.getString(str);
            }
        });
    }

    /* renamed from: b */
    private static void m15385b(Context context, AGConnectOptionsBuilder aGConnectOptionsBuilder) {
        AGConnectServicesConfig fromContext = AGConnectServicesConfig.fromContext(context);
        if (aGConnectOptionsBuilder.getInputStream() != null) {
            try {
                String utils = Utils.toString(aGConnectOptionsBuilder.getInputStream(), "UTF-8");
                aGConnectOptionsBuilder.getInputStream().reset();
                fromContext.overlayWith(new ByteArrayInputStream(utils.getBytes(Charset.forName("UTF-8"))));
            } catch (IOException unused) {
                Log.e("AGC_Instance", "input stream set to AGConnectServicesConfig fail");
            }
        }
        for (Map.Entry<String, String> entry : aGConnectOptionsBuilder.getCustomConfigMap().entrySet()) {
            fromContext.setParam(entry.getKey(), entry.getValue());
        }
        if (aGConnectOptionsBuilder.getRoutePolicy() != AGCRoutePolicy.UNKNOWN) {
            fromContext.setRoutePolicy(aGConnectOptionsBuilder.getRoutePolicy());
        }
    }

    /* renamed from: a */
    public void m15389a(final CustomAuthProvider customAuthProvider) {
        this.f10806f.m15375a(Collections.singletonList(Service.builder(AuthProvider.class, new AuthProvider() { // from class: com.huawei.agconnect.core.a.b.4
            @Override // com.huawei.agconnect.core.service.auth.AuthProvider
            public void addTokenListener(OnTokenListener onTokenListener) {
            }

            @Override // com.huawei.agconnect.core.service.auth.AuthProvider
            public Task<Token> getTokens() {
                return customAuthProvider.getTokens(false);
            }

            @Override // com.huawei.agconnect.core.service.auth.AuthProvider
            public Task<Token> getTokens(boolean z) {
                return customAuthProvider.getTokens(z);
            }

            @Override // com.huawei.agconnect.core.service.auth.AuthProvider
            public String getUid() {
                return "";
            }

            @Override // com.huawei.agconnect.core.service.auth.AuthProvider
            public void removeTokenListener(OnTokenListener onTokenListener) {
            }
        }).build()), this.f10804d.getContext());
    }

    /* renamed from: a */
    public void m15388a(final CustomCredentialsProvider customCredentialsProvider) {
        this.f10806f.m15375a(Collections.singletonList(Service.builder(CredentialsProvider.class, new CredentialsProvider() { // from class: com.huawei.agconnect.core.a.b.3
            @Override // com.huawei.agconnect.core.service.auth.CredentialsProvider
            public Task<Token> getTokens() {
                return customCredentialsProvider.getTokens(false);
            }

            @Override // com.huawei.agconnect.core.service.auth.CredentialsProvider
            public Task<Token> getTokens(boolean z) {
                return customCredentialsProvider.getTokens(z);
            }
        }).build()), this.f10804d.getContext());
    }

    @Override // com.huawei.agconnect.AGConnectInstance
    public Context getContext() {
        return this.f10804d.getContext();
    }

    @Override // com.huawei.agconnect.AGConnectInstance
    public String getIdentifier() {
        return this.f10804d.getIdentifier();
    }

    @Override // com.huawei.agconnect.AGConnectInstance
    public AGConnectOptions getOptions() {
        return this.f10804d;
    }

    @Override // com.huawei.agconnect.AGConnectInstance
    public <T> T getService(Class<? super T> cls) {
        T t = (T) this.f10806f.m15378a(this, cls);
        return t != null ? t : (T) this.f10805e.m15378a(this, cls);
    }
}
