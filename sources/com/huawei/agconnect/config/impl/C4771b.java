package com.huawei.agconnect.config.impl;

import android.content.Context;
import com.huawei.agconnect.AGCRoutePolicy;
import com.huawei.agconnect.AGConnectOptions;
import com.huawei.agconnect.JsonProcessingFactory;
import com.huawei.agconnect.core.Service;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.huawei.agconnect.config.impl.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4771b implements AGConnectOptions {

    /* renamed from: a */
    private final String f10759a;

    /* renamed from: b */
    private final Context f10760b;

    /* renamed from: c */
    private final String f10761c;

    /* renamed from: d */
    private final AGCRoutePolicy f10762d;

    /* renamed from: e */
    private final InterfaceC4774d f10763e;

    /* renamed from: f */
    private final C4775e f10764f;

    /* renamed from: g */
    private final Map<String, String> f10765g;

    /* renamed from: h */
    private final List<Service> f10766h;

    /* renamed from: i */
    private final Map<String, String> f10767i = new HashMap();

    public C4771b(Context context, String str, AGCRoutePolicy aGCRoutePolicy, InputStream inputStream, Map<String, String> map, List<Service> list, String str2) {
        this.f10760b = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        str = str == null ? this.f10760b.getPackageName() : str;
        this.f10761c = str;
        if (inputStream != null) {
            this.f10763e = new C4778h(inputStream, str);
            Utils.closeQuietly(inputStream);
        } else {
            this.f10763e = new C4781k(this.f10760b, str);
        }
        this.f10764f = new C4775e(this.f10763e);
        if (aGCRoutePolicy != AGCRoutePolicy.UNKNOWN && "1.0".equals(this.f10763e.mo15400a("/configuration_version", null))) {
            throw new RuntimeException("The file version does not match,please download the latest agconnect-services.json from the AGC website.");
        }
        this.f10762d = (aGCRoutePolicy == null || aGCRoutePolicy == AGCRoutePolicy.UNKNOWN) ? Utils.getRoutePolicyFromJson(this.f10763e.mo15400a("/region", null), this.f10763e.mo15400a("/agcgw/url", null)) : aGCRoutePolicy;
        this.f10765g = Utils.fixKeyPathMap(map);
        this.f10766h = list;
        this.f10759a = str2 == null ? m15422b() : str2;
    }

    /* renamed from: a */
    private String m15423a(String str) {
        Map<String, JsonProcessingFactory.JsonProcessor> processors = JsonProcessingFactory.getProcessors();
        if (processors.containsKey(str)) {
            if (this.f10767i.containsKey(str)) {
                return this.f10767i.get(str);
            }
            JsonProcessingFactory.JsonProcessor jsonProcessor = processors.get(str);
            if (jsonProcessor == null) {
                return null;
            }
            String processOption = jsonProcessor.processOption(this);
            this.f10767i.put(str, processOption);
            return processOption;
        }
        return null;
    }

    /* renamed from: b */
    private String m15422b() {
        StringBuilder sb = new StringBuilder();
        sb.append("{packageName='");
        sb.append(this.f10761c);
        sb.append('\'');
        sb.append(", routePolicy=");
        sb.append(this.f10762d);
        sb.append(", reader=");
        sb.append(this.f10763e.toString().hashCode());
        sb.append(", customConfigMap=");
        JSONObject jSONObject = new JSONObject(this.f10765g);
        sb.append((!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)).hashCode());
        sb.append('}');
        return String.valueOf(sb.toString().hashCode());
    }

    /* renamed from: a */
    public List<Service> m15424a() {
        return this.f10766h;
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
        return this.f10760b;
    }

    @Override // com.huawei.agconnect.AGConnectOptions
    public String getIdentifier() {
        return this.f10759a;
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
        return this.f10761c;
    }

    @Override // com.huawei.agconnect.AGConnectOptions
    public AGCRoutePolicy getRoutePolicy() {
        AGCRoutePolicy aGCRoutePolicy = this.f10762d;
        return aGCRoutePolicy == null ? AGCRoutePolicy.UNKNOWN : aGCRoutePolicy;
    }

    @Override // com.huawei.agconnect.AGConnectOptions
    public String getString(String str) {
        return getString(str, null);
    }

    @Override // com.huawei.agconnect.AGConnectOptions
    public String getString(String str, String str2) {
        if (str == null) {
            return str2;
        }
        String fixPath = Utils.fixPath(str);
        String str3 = this.f10765g.get(fixPath);
        if (str3 != null) {
            return str3;
        }
        String m15423a = m15423a(fixPath);
        if (m15423a != null) {
            return m15423a;
        }
        String mo15400a = this.f10763e.mo15400a(fixPath, str2);
        return C4775e.m15415a(mo15400a) ? this.f10764f.mo15413a(mo15400a, str2) : mo15400a;
    }
}
