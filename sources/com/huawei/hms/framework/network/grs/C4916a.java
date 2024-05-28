package com.huawei.hms.framework.network.grs;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.network.grs.local.model.CountryCodeBean;
import com.huawei.hms.framework.network.grs.p215e.C4923a;
import com.huawei.hms.framework.network.grs.p215e.C4924b;
import com.huawei.hms.framework.network.grs.p215e.C4925c;
import com.huawei.hms.framework.network.grs.p216f.C4927b;
import com.huawei.hms.framework.network.grs.p217g.C4937d;
import com.huawei.hms.framework.network.grs.p217g.C4942h;
import com.huawei.hms.framework.network.grs.p217g.p219k.C4949c;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.huawei.hms.framework.network.grs.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4916a {

    /* renamed from: e */
    private static final String f11190e = "a";

    /* renamed from: a */
    private final GrsBaseInfo f11191a;

    /* renamed from: b */
    private C4923a f11192b;

    /* renamed from: c */
    private C4942h f11193c;

    /* renamed from: d */
    private C4925c f11194d;

    /* renamed from: com.huawei.hms.framework.network.grs.a$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class C4917a implements InterfaceC4919b {

        /* renamed from: a */
        String f11195a;

        /* renamed from: b */
        Map<String, String> f11196b;

        /* renamed from: c */
        IQueryUrlsCallBack f11197c;

        /* renamed from: d */
        Context f11198d;

        /* renamed from: e */
        GrsBaseInfo f11199e;

        /* renamed from: f */
        C4923a f11200f;

        C4917a(String str, Map<String, String> map, IQueryUrlsCallBack iQueryUrlsCallBack, Context context, GrsBaseInfo grsBaseInfo, C4923a c4923a) {
            this.f11195a = str;
            this.f11196b = map;
            this.f11197c = iQueryUrlsCallBack;
            this.f11198d = context;
            this.f11199e = grsBaseInfo;
            this.f11200f = c4923a;
        }

        @Override // com.huawei.hms.framework.network.grs.InterfaceC4919b
        /* renamed from: a */
        public void mo15031a() {
            Map<String, String> map = this.f11196b;
            if (map != null && !map.isEmpty()) {
                Logger.m15048i(C4916a.f11190e, "GrsClientManager.ayncGetGrsUrls: Return [%s] Urls: %s", this.f11195a, StringUtils.anonymizeMessage(new JSONObject(this.f11196b).toString()));
                this.f11197c.onCallBackSuccess(this.f11196b);
            } else if (this.f11196b != null) {
                Logger.m15048i(C4916a.f11190e, "GrsClientManager.ayncGetGrsUrls：Return [%s] Urls is Empty", this.f11195a);
                this.f11197c.onCallBackFail(-3);
            } else {
                Logger.m15049i(C4916a.f11190e, "GrsClientManager.ayncGetGrsUrls: Get URL from Local JSON File");
                Map<String, String> m14966a = C4927b.m14963a(this.f11198d.getPackageName(), this.f11199e).m14966a(this.f11198d, this.f11200f, this.f11199e, this.f11195a, true);
                if (m14966a == null || m14966a.isEmpty()) {
                    Logger.m15050e(C4916a.f11190e, "The serviceName[%s] is not configured in the JSON configuration files to reveal all the details", this.f11195a);
                }
                if (m14966a == null) {
                    m14966a = new ConcurrentHashMap<>();
                }
                Logger.m15048i(C4916a.f11190e, "GrsClientManager.ayncGetGrsUrls: Return [%s] Urls: %s", this.f11195a, StringUtils.anonymizeMessage(new JSONObject(m14966a).toString()));
                this.f11197c.onCallBackSuccess(m14966a);
            }
        }

        @Override // com.huawei.hms.framework.network.grs.InterfaceC4919b
        /* renamed from: a */
        public void mo15030a(C4937d c4937d) {
            IQueryUrlsCallBack iQueryUrlsCallBack;
            String m14906j = c4937d.m14906j();
            Map<String, String> m15035a = C4916a.m15035a(m14906j, this.f11195a);
            if (m15035a.isEmpty()) {
                Map<String, String> map = this.f11196b;
                if (map == null || map.isEmpty()) {
                    if (this.f11196b != null) {
                        Logger.m15048i(C4916a.f11190e, "GrsClientManager.ayncGetGrsUrls：Return [%s] Urls is Empty", this.f11195a);
                        this.f11197c.onCallBackFail(-5);
                        return;
                    }
                    if (!TextUtils.isEmpty(m14906j)) {
                        Logger.m15050e(C4916a.f11190e, "The serviceName[%s] is not configured on the GRS server.", this.f11195a);
                    }
                    Logger.m15049i(C4916a.f11190e, "GrsClientManager.ayncGetGrsUrls: Get URL from Local JSON File");
                    Map<String, String> m14966a = C4927b.m14963a(this.f11198d.getPackageName(), this.f11199e).m14966a(this.f11198d, this.f11200f, this.f11199e, this.f11195a, true);
                    if (m14966a == null || m14966a.isEmpty()) {
                        Logger.m15050e(C4916a.f11190e, "The serviceName[%s] is not configured in the JSON configuration files to reveal all the details", this.f11195a);
                    }
                    if (m14966a == null) {
                        m14966a = new ConcurrentHashMap<>();
                    }
                    Logger.m15048i(C4916a.f11190e, "GrsClientManager.ayncGetGrsUrls: Return [%s] Urls: %s", this.f11195a, StringUtils.anonymizeMessage(new JSONObject(m14966a).toString()));
                    this.f11197c.onCallBackSuccess(m14966a);
                    return;
                }
                Logger.m15048i(C4916a.f11190e, "GrsClientManager.ayncGetGrsUrls: Return [%s][%s] Url: %s", this.f11195a, StringUtils.anonymizeMessage(new JSONObject(this.f11196b).toString()));
                iQueryUrlsCallBack = this.f11197c;
                m15035a = this.f11196b;
            } else {
                Logger.m15048i(C4916a.f11190e, "GrsClientManager.ayncGetGrsUrls: Get URL from Current Called GRS Server Return [%s] Urls: %s", this.f11195a, StringUtils.anonymizeMessage(new JSONObject(m15035a).toString()));
                iQueryUrlsCallBack = this.f11197c;
            }
            iQueryUrlsCallBack.onCallBackSuccess(m15035a);
        }
    }

    /* renamed from: com.huawei.hms.framework.network.grs.a$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class C4918b implements InterfaceC4919b {

        /* renamed from: a */
        String f11201a;

        /* renamed from: b */
        String f11202b;

        /* renamed from: c */
        IQueryUrlCallBack f11203c;

        /* renamed from: d */
        String f11204d;

        /* renamed from: e */
        Context f11205e;

        /* renamed from: f */
        GrsBaseInfo f11206f;

        /* renamed from: g */
        C4923a f11207g;

        C4918b(String str, String str2, IQueryUrlCallBack iQueryUrlCallBack, String str3, Context context, GrsBaseInfo grsBaseInfo, C4923a c4923a) {
            this.f11201a = str;
            this.f11202b = str2;
            this.f11203c = iQueryUrlCallBack;
            this.f11204d = str3;
            this.f11205e = context;
            this.f11206f = grsBaseInfo;
            this.f11207g = c4923a;
        }

        @Override // com.huawei.hms.framework.network.grs.InterfaceC4919b
        /* renamed from: a */
        public void mo15031a() {
            if (!TextUtils.isEmpty(this.f11204d)) {
                Logger.m15048i(C4916a.f11190e, "GrsClientManager.ayncGetGrsUrl: Return [%s][%s] Url: %s", this.f11201a, this.f11202b, StringUtils.anonymizeMessage(this.f11204d));
                this.f11203c.onCallBackSuccess(this.f11204d);
            } else if (!TextUtils.isEmpty(this.f11204d)) {
                Logger.m15048i(C4916a.f11190e, "GrsClientManager.ayncGetGrsUrl：Return [%s][%s] Url is Empty", this.f11201a, this.f11202b);
                this.f11203c.onCallBackFail(-3);
            } else {
                Logger.m15049i(C4916a.f11190e, "GrsClientManager.ayncGetGrsUrl: Get URL from Local JSON File");
                String m14967a = C4927b.m14963a(this.f11205e.getPackageName(), this.f11206f).m14967a(this.f11205e, this.f11207g, this.f11206f, this.f11201a, this.f11202b, true);
                if (m14967a == null || m14967a.isEmpty()) {
                    Logger.m15050e(C4916a.f11190e, "The serviceName[%s][%s] is not configured in the JSON configuration files to reveal all the details", this.f11201a, this.f11202b);
                }
                Logger.m15048i(C4916a.f11190e, "GrsClientManager.ayncGetGrsUrl: Return [%s][%s] Url: %s", this.f11201a, this.f11202b, StringUtils.anonymizeMessage(m14967a));
                this.f11203c.onCallBackSuccess(m14967a);
            }
        }

        @Override // com.huawei.hms.framework.network.grs.InterfaceC4919b
        /* renamed from: a */
        public void mo15030a(C4937d c4937d) {
            IQueryUrlCallBack iQueryUrlCallBack;
            String str;
            String m14906j = c4937d.m14906j();
            Map<String, String> m15035a = C4916a.m15035a(m14906j, this.f11201a);
            if (m15035a.containsKey(this.f11202b)) {
                String str2 = C4916a.f11190e;
                String str3 = this.f11202b;
                Logger.m15048i(str2, "GrsClientManager.ayncGetGrsUrl: Get URL from Current Called GRS Server, Return [%s][%s] Url: %s", this.f11201a, str3, StringUtils.anonymizeMessage(m15035a.get(str3)));
                iQueryUrlCallBack = this.f11203c;
                str = m15035a.get(this.f11202b);
            } else if (TextUtils.isEmpty(this.f11204d)) {
                if (!TextUtils.isEmpty(this.f11204d)) {
                    Logger.m15048i(C4916a.f11190e, "GrsClientManager.ayncGetGrsUrl：Return [%s][%s] Url is Empty", this.f11201a, this.f11202b);
                    this.f11203c.onCallBackFail(-5);
                    return;
                }
                if (!TextUtils.isEmpty(m14906j)) {
                    Logger.m15050e(C4916a.f11190e, "The serviceName[%s][%s] is not configured on the GRS server.", this.f11201a, this.f11202b);
                }
                Logger.m15049i(C4916a.f11190e, "GrsClientManager.ayncGetGrsUrl: Get URL from Local JSON File");
                String m14967a = C4927b.m14963a(this.f11205e.getPackageName(), this.f11206f).m14967a(this.f11205e, this.f11207g, this.f11206f, this.f11201a, this.f11202b, true);
                if (m14967a == null || m14967a.isEmpty()) {
                    Logger.m15050e(C4916a.f11190e, "The serviceName[%s][%s] is not configured in the JSON configuration files to reveal all the details", this.f11201a, this.f11202b);
                }
                Logger.m15048i(C4916a.f11190e, "GrsClientManager.ayncGetGrsUrl: Return [%s][%s] Url: %s", this.f11201a, this.f11202b, StringUtils.anonymizeMessage(m14967a));
                this.f11203c.onCallBackSuccess(m14967a);
                return;
            } else {
                String str4 = C4916a.f11190e;
                String str5 = this.f11202b;
                Logger.m15048i(str4, "GrsClientManager.ayncGetGrsUrl: Return [%s][%s] Url: %s", this.f11201a, str5, StringUtils.anonymizeMessage(m15035a.get(str5)));
                iQueryUrlCallBack = this.f11203c;
                str = this.f11204d;
            }
            iQueryUrlCallBack.onCallBackSuccess(str);
        }
    }

    public C4916a(GrsBaseInfo grsBaseInfo, C4923a c4923a, C4942h c4942h, C4925c c4925c) {
        this.f11191a = grsBaseInfo;
        this.f11192b = c4923a;
        this.f11193c = c4942h;
        this.f11194d = c4925c;
    }

    /* renamed from: a */
    public static CountryCodeBean m15040a(Context context, boolean z) {
        return new CountryCodeBean(context, z);
    }

    /* renamed from: a */
    public static Map<String, Map<String, String>> m15039a(String str) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(16);
        if (TextUtils.isEmpty(str)) {
            Logger.m15047v(f11190e, "isSpExpire jsonValue is null.");
            return concurrentHashMap;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                JSONObject jSONObject2 = jSONObject.getJSONObject(next);
                if (!TextUtils.isEmpty(next)) {
                    concurrentHashMap.put(next, m15032a(jSONObject2));
                }
            }
            return concurrentHashMap;
        } catch (JSONException e) {
            Logger.m15043w(f11190e, "getServicesUrlsMap occur a JSONException: %s", StringUtils.anonymizeMessage(e.getMessage()));
            return concurrentHashMap;
        }
    }

    /* renamed from: a */
    private Map<String, String> m15036a(String str, C4924b c4924b, Context context) {
        Map<String, String> m14999a = this.f11192b.m14999a(this.f11191a, str, c4924b, context);
        if (m14999a != null && !m14999a.isEmpty()) {
            Logger.m15049i(f11190e, "GrsClientManager.getUrlsLocal: Get URL from GRS Server Cache");
            return m14999a;
        }
        Map<String, String> m14966a = C4927b.m14963a(context.getPackageName(), this.f11191a).m14966a(context, this.f11192b, this.f11191a, str, false);
        Logger.m15049i(f11190e, "GrsClientManager.getUrlsLocal: Get URL from Local JSON File");
        return m14966a != null ? m14966a : new HashMap();
    }

    /* renamed from: a */
    public static Map<String, String> m15035a(String str, String str2) {
        HashMap hashMap = new HashMap();
        if (TextUtils.isEmpty(str)) {
            Logger.m15045w(f11190e, "isSpExpire jsonValue from server is null.");
            return hashMap;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = jSONObject.has(str2) ? jSONObject.getJSONObject(str2) : null;
            if (jSONObject2 == null) {
                Logger.m15043w(f11190e, "getServiceNameUrls: paser null from server json data by {%s}.", str2);
                return hashMap;
            }
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject2.get(next).toString());
            }
            return hashMap;
        } catch (JSONException e) {
            Logger.m15043w(f11190e, "Method{getServiceNameUrls} query url from SP occur an JSONException: %s", StringUtils.anonymizeMessage(e.getMessage()));
            return hashMap;
        }
    }

    /* renamed from: a */
    public static Map<String, String> m15032a(JSONObject jSONObject) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(16);
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                String obj = jSONObject.get(next).toString();
                if (!TextUtils.isEmpty(next) && !TextUtils.isEmpty(obj)) {
                    concurrentHashMap.put(next, obj);
                }
            }
            return concurrentHashMap;
        } catch (JSONException e) {
            Logger.m15043w(f11190e, "getServiceUrls occur a JSONException: %s", StringUtils.anonymizeMessage(e.getMessage()));
            return concurrentHashMap;
        }
    }

    /* renamed from: a */
    public String m15041a(Context context, String str) {
        C4937d m14888a = this.f11193c.m14888a(new C4949c(this.f11191a, context), str, this.f11194d);
        return m14888a == null ? "" : m14888a.m14903m() ? this.f11192b.m15004a().m14990a(this.f11191a.getGrsParasKey(true, true, context), "") : m14888a.m14906j();
    }

    /* renamed from: a */
    public String m15034a(String str, String str2, Context context) {
        String str3;
        Object[] objArr;
        C4924b c4924b = new C4924b();
        String str4 = m15036a(str, c4924b, context).get(str2);
        if (!c4924b.m14995a() || TextUtils.isEmpty(str4)) {
            String m15041a = m15041a(context, str);
            String str5 = m15035a(m15041a, str).get(str2);
            if (!TextUtils.isEmpty(str5)) {
                Logger.m15048i(f11190e, "GrsClientManager.synGetGrsUrl: Get URL from Current Called GRS Server, Return [%s][%s] Url: %s", str, str2, StringUtils.anonymizeMessage(str5));
                return str5;
            }
            if (TextUtils.isEmpty(str4)) {
                if (!TextUtils.isEmpty(m15041a)) {
                    Logger.m15050e(f11190e, "The serviceName[%s][%s] is not configured on the GRS server.", str, str2);
                }
                Logger.m15049i(f11190e, "GrsClientManager.synGetGrsUrl: Get URL from Local JSON File.");
                str4 = C4927b.m14963a(context.getPackageName(), this.f11191a).m14967a(context, this.f11192b, this.f11191a, str, str2, true);
                if (str4 == null || str4.isEmpty()) {
                    Logger.m15050e(f11190e, "The serviceName[%s][%s] is not configured in the JSON configuration files to reveal all the details", str, str2);
                }
            }
            str3 = f11190e;
            objArr = new Object[]{str, str2, StringUtils.anonymizeMessage(str4)};
        } else {
            str3 = f11190e;
            objArr = new Object[]{str, str2, StringUtils.anonymizeMessage(str4)};
        }
        Logger.m15048i(str3, "GrsClientManager.synGetGrsUrl: Return [%s][%s] Url: %s", objArr);
        return str4;
    }

    /* renamed from: a */
    public Map<String, String> m15038a(String str, Context context) {
        C4924b c4924b = new C4924b();
        Map<String, String> m15036a = m15036a(str, c4924b, context);
        if (c4924b.m14995a() && !m15036a.isEmpty()) {
            Logger.m15048i(f11190e, "Return [%s] Urls: %s", str, StringUtils.anonymizeMessage(new JSONObject(m15036a).toString()));
            return m15036a;
        }
        String m15041a = m15041a(context, str);
        Map<String, String> m15035a = m15035a(m15041a, str);
        if (!m15035a.isEmpty()) {
            Logger.m15048i(f11190e, "GrsClientManager.synGetGrsUrls: Get URL from Current Called GRS Server Return [%s] Urls: %s", str, StringUtils.anonymizeMessage(new JSONObject(m15035a).toString()));
            return m15035a;
        }
        if (m15036a.isEmpty()) {
            if (!TextUtils.isEmpty(m15041a)) {
                Logger.m15050e(f11190e, "The serviceName[%s] is not configured on the GRS server.", str);
            }
            Logger.m15049i(f11190e, "GrsClientManager.synGetGrsUrls: Get URL from Local JSON File.");
            m15036a = C4927b.m14963a(context.getPackageName(), this.f11191a).m14966a(context, this.f11192b, this.f11191a, str, true);
            if (m15036a == null || m15036a.isEmpty()) {
                Logger.m15050e(f11190e, "The serviceName[%s] is not configured in the JSON configuration files to reveal all the details", str);
            }
        }
        String str2 = f11190e;
        Object[] objArr = new Object[2];
        objArr[0] = str;
        objArr[1] = StringUtils.anonymizeMessage(m15036a != null ? new JSONObject(m15036a).toString() : "");
        Logger.m15048i(str2, "GrsClientManager.synGetGrsUrls: Return [%s] Urls: %s", objArr);
        return m15036a;
    }

    /* renamed from: a */
    public void m15037a(String str, IQueryUrlsCallBack iQueryUrlsCallBack, Context context) {
        C4924b c4924b = new C4924b();
        Map<String, String> m15036a = m15036a(str, c4924b, context);
        if (!c4924b.m14995a()) {
            this.f11193c.m14889a(new C4949c(this.f11191a, context), new C4917a(str, m15036a, iQueryUrlsCallBack, context, this.f11191a, this.f11192b), str, this.f11194d);
        } else if (m15036a.isEmpty()) {
            Logger.m15048i(f11190e, "GrsClientManager.ayncGetGrsUrls：Return [%s] Urls is Empty", str);
            iQueryUrlsCallBack.onCallBackFail(-5);
        } else {
            Logger.m15048i(f11190e, "GrsClientManager.ayncGetGrsUrls：Return [%s] Urls: %s", str, StringUtils.anonymizeMessage(new JSONObject(m15036a).toString()));
            Logger.m15048i(f11190e, "ayncGetGrsUrls: %s", StringUtils.anonymizeMessage(new JSONObject(m15036a).toString()));
            iQueryUrlsCallBack.onCallBackSuccess(m15036a);
        }
    }

    /* renamed from: a */
    public void m15033a(String str, String str2, IQueryUrlCallBack iQueryUrlCallBack, Context context) {
        C4924b c4924b = new C4924b();
        String str3 = m15036a(str, c4924b, context).get(str2);
        if (!c4924b.m14995a()) {
            this.f11193c.m14889a(new C4949c(this.f11191a, context), new C4918b(str, str2, iQueryUrlCallBack, str3, context, this.f11191a, this.f11192b), str, this.f11194d);
        } else if (TextUtils.isEmpty(str3)) {
            Logger.m15048i(f11190e, "GrsClientManager.ayncGetGrsUrl：Return [%s][%s] Url is Empty", str, str2);
            iQueryUrlCallBack.onCallBackFail(-5);
        } else {
            Logger.m15048i(f11190e, "GrsClientManager.ayncGetGrsUrl：Return [%s][%s] Url: %s", str, str2, StringUtils.anonymizeMessage(str3));
            iQueryUrlCallBack.onCallBackSuccess(str3);
        }
    }
}
