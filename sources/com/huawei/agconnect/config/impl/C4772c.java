package com.huawei.agconnect.config.impl;

import android.content.Context;
import android.util.Log;
import com.huawei.agconnect.AGCRoutePolicy;
import com.huawei.agconnect.JsonProcessingFactory;
import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.agconnect.config.LazyInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.huawei.agconnect.config.impl.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4772c extends AGConnectServicesConfig {

    /* renamed from: a */
    private final Context f10768a;

    /* renamed from: b */
    private final String f10769b;

    /* renamed from: c */
    private LazyInputStream f10770c;

    /* renamed from: d */
    private volatile InterfaceC4774d f10771d;

    /* renamed from: e */
    private final Object f10772e = new Object();

    /* renamed from: f */
    private AGCRoutePolicy f10773f = AGCRoutePolicy.UNKNOWN;

    /* renamed from: g */
    private final Map<String, String> f10774g = new HashMap();

    /* renamed from: h */
    private volatile C4775e f10775h;

    public C4772c(Context context, String str) {
        this.f10768a = context;
        this.f10769b = str;
    }

    /* renamed from: a */
    private static LazyInputStream m15420a(Context context, final InputStream inputStream) {
        return new LazyInputStream(context) { // from class: com.huawei.agconnect.config.impl.c.1
            @Override // com.huawei.agconnect.config.LazyInputStream
            public InputStream get(Context context2) {
                return inputStream;
            }
        };
    }

    /* renamed from: a */
    private static String m15419a(String str) {
        int i = 0;
        if (str.length() > 0) {
            while (str.charAt(i) == '/') {
                i++;
            }
        }
        return '/' + str.substring(i);
    }

    /* renamed from: a */
    private void m15421a() {
        Log.d("AGC_ConfigImpl", "initConfigReader");
        if (this.f10771d == null) {
            synchronized (this.f10772e) {
                if (this.f10771d == null) {
                    if (this.f10770c != null) {
                        this.f10771d = new C4778h(this.f10770c.loadInputStream(), "UTF-8");
                        this.f10770c.close();
                        this.f10770c = null;
                    } else {
                        this.f10771d = new C4781k(this.f10768a, this.f10769b);
                    }
                    this.f10775h = new C4775e(this.f10771d);
                }
                m15418b();
            }
        }
    }

    /* renamed from: b */
    private String m15417b(String str) {
        JsonProcessingFactory.JsonProcessor jsonProcessor;
        Map<String, JsonProcessingFactory.JsonProcessor> processors = JsonProcessingFactory.getProcessors();
        if (processors.containsKey(str) && (jsonProcessor = processors.get(str)) != null) {
            return jsonProcessor.processOption(this);
        }
        return null;
    }

    /* renamed from: b */
    private void m15418b() {
        if (this.f10773f == AGCRoutePolicy.UNKNOWN) {
            if (this.f10771d != null) {
                this.f10773f = Utils.getRoutePolicyFromJson(this.f10771d.mo15400a("/region", null), this.f10771d.mo15400a("/agcgw/url", null));
            } else {
                Log.w("AGConnectServiceConfig", "get route fail , config not ready");
            }
        }
    }

    @Override // com.huawei.agconnect.AGConnectOptions
    public boolean getBoolean(String str) {
        return getBoolean(str, false);
    }

    @Override // com.huawei.agconnect.AGConnectOptions
    public boolean getBoolean(String str, boolean z) {
        return Boolean.parseBoolean(getString(str, String.valueOf(z)));
    }

    @Override // com.huawei.agconnect.AGConnectOptions
    public Context getContext() {
        return this.f10768a;
    }

    @Override // com.huawei.agconnect.AGConnectOptions
    public String getIdentifier() {
        return "DEFAULT_INSTANCE";
    }

    @Override // com.huawei.agconnect.AGConnectOptions
    public int getInt(String str) {
        return getInt(str, 0);
    }

    @Override // com.huawei.agconnect.AGConnectOptions
    public int getInt(String str, int i) {
        try {
            return Integer.parseInt(getString(str, String.valueOf(i)));
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    @Override // com.huawei.agconnect.AGConnectOptions
    public String getPackageName() {
        return this.f10769b;
    }

    @Override // com.huawei.agconnect.AGConnectOptions
    public AGCRoutePolicy getRoutePolicy() {
        if (this.f10773f == null) {
            this.f10773f = AGCRoutePolicy.UNKNOWN;
        }
        if (this.f10773f == AGCRoutePolicy.UNKNOWN && this.f10771d == null) {
            m15421a();
        }
        AGCRoutePolicy aGCRoutePolicy = this.f10773f;
        return aGCRoutePolicy == null ? AGCRoutePolicy.UNKNOWN : aGCRoutePolicy;
    }

    @Override // com.huawei.agconnect.AGConnectOptions
    public String getString(String str) {
        return getString(str, null);
    }

    @Override // com.huawei.agconnect.AGConnectOptions
    public String getString(String str, String str2) {
        if (str != null) {
            if (this.f10771d == null) {
                m15421a();
            }
            String m15419a = m15419a(str);
            String str3 = this.f10774g.get(m15419a);
            if (str3 != null) {
                return str3;
            }
            String m15417b = m15417b(m15419a);
            if (m15417b != null) {
                return m15417b;
            }
            String mo15400a = this.f10771d.mo15400a(m15419a, str2);
            return C4775e.m15415a(mo15400a) ? this.f10775h.mo15413a(mo15400a, str2) : mo15400a;
        }
        throw new NullPointerException("path must not be null.");
    }

    @Override // com.huawei.agconnect.config.AGConnectServicesConfig
    public void overlayWith(LazyInputStream lazyInputStream) {
        this.f10770c = lazyInputStream;
    }

    @Override // com.huawei.agconnect.config.AGConnectServicesConfig
    public void overlayWith(InputStream inputStream) {
        overlayWith(m15420a(this.f10768a, inputStream));
    }

    @Override // com.huawei.agconnect.config.AGConnectServicesConfig
    public void setParam(String str, String str2) {
        this.f10774g.put(Utils.fixPath(str), str2);
    }

    @Override // com.huawei.agconnect.config.AGConnectServicesConfig
    public void setRoutePolicy(AGCRoutePolicy aGCRoutePolicy) {
        this.f10773f = aGCRoutePolicy;
    }
}
