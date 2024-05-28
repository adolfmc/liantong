package com.huawei.hms.framework.network.grs.p217g;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.network.grs.C4916a;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.p215e.C4923a;
import com.huawei.hms.framework.network.grs.p215e.C4925c;
import com.huawei.hms.framework.network.grs.p217g.p219k.C4949c;
import com.huawei.hms.framework.network.grs.p220h.C4956e;
import org.json.JSONException;

/* renamed from: com.huawei.hms.framework.network.grs.g.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4934b {

    /* renamed from: a */
    private final Context f11258a;

    /* renamed from: b */
    private final GrsBaseInfo f11259b;

    /* renamed from: c */
    private final C4923a f11260c;

    public C4934b(Context context, C4923a c4923a, GrsBaseInfo grsBaseInfo) {
        this.f11258a = context;
        this.f11259b = grsBaseInfo;
        this.f11260c = c4923a;
    }

    /* renamed from: a */
    public String m14945a(boolean z) {
        String str;
        String str2 = C4916a.m15035a(this.f11260c.m15004a().m14990a("geoipCountryCode", ""), "geoip.countrycode").get("ROOT");
        Logger.m15049i("GeoipCountry", "geoIpCountry is: " + str2);
        String m14990a = this.f11260c.m15004a().m14990a("geoipCountryCodetime", "0");
        long j = 0;
        if (!TextUtils.isEmpty(m14990a) && m14990a.matches("\\d+")) {
            try {
                j = Long.parseLong(m14990a);
            } catch (NumberFormatException e) {
                Logger.m15044w("GeoipCountry", "convert urlParamKey from String to Long catch NumberFormatException.", e);
            }
        }
        if (TextUtils.isEmpty(str2) || C4956e.m14853a(Long.valueOf(j))) {
            C4949c c4949c = new C4949c(this.f11259b, this.f11258a);
            c4949c.m14879a("geoip.countrycode");
            C4925c m14996c = this.f11260c.m14996c();
            if (m14996c != null) {
                try {
                    str = C4945i.m14885a(m14996c.m14990a("services", ""), c4949c.m14877c());
                } catch (JSONException e2) {
                    Logger.m15043w("GeoipCountry", "getGeoipCountry merge services occure jsonException. %s", StringUtils.anonymizeMessage(e2.getMessage()));
                    str = null;
                }
                if (!TextUtils.isEmpty(str)) {
                    m14996c.m14988b("services", str);
                }
            }
            if (z) {
                C4937d m14888a = this.f11260c.m14998b().m14888a(c4949c, "geoip.countrycode", m14996c);
                if (m14888a != null) {
                    str2 = C4916a.m15035a(m14888a.m14906j(), "geoip.countrycode").get("ROOT");
                }
                Logger.m15049i("GeoipCountry", "sync request to query geoip.countrycode is:" + str2);
            } else {
                Logger.m15049i("GeoipCountry", "async request to query geoip.countrycode");
                this.f11260c.m14998b().m14889a(c4949c, null, "geoip.countrycode", m14996c);
            }
        }
        return str2;
    }
}
