package com.huawei.hms.framework.network.grs.p216f;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.p215e.C4923a;
import com.huawei.hms.framework.network.grs.p217g.C4934b;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.framework.network.grs.f.e */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4930e {

    /* renamed from: a */
    private static final String f11244a = "e";

    /* renamed from: b */
    public static final Set<String> f11245b = Collections.unmodifiableSet(new C4931a(16));

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.huawei.hms.framework.network.grs.f.e$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    class C4931a extends HashSet<String> {
        C4931a(int i) {
            super(i);
            add("ser_country");
            add("reg_country");
            add("issue_country");
            add("geo_ip");
        }
    }

    /* renamed from: a */
    private static String m14956a(Context context, C4923a c4923a, String str, GrsBaseInfo grsBaseInfo, boolean z) {
        String[] split;
        String serCountry = grsBaseInfo.getSerCountry();
        String regCountry = grsBaseInfo.getRegCountry();
        String issueCountry = grsBaseInfo.getIssueCountry();
        for (String str2 : str.split(">")) {
            if (f11245b.contains(str2.trim())) {
                if ("ser_country".equals(str2.trim()) && !TextUtils.isEmpty(serCountry) && !"UNKNOWN".equals(serCountry)) {
                    Logger.m15049i(f11244a, "current route_by is serCountry and routerCountry is: " + serCountry);
                    return serCountry;
                } else if ("reg_country".equals(str2.trim()) && !TextUtils.isEmpty(regCountry) && !"UNKNOWN".equals(regCountry)) {
                    Logger.m15049i(f11244a, "current route_by is regCountry and routerCountry is: " + regCountry);
                    return regCountry;
                } else if ("issue_country".equals(str2.trim()) && !TextUtils.isEmpty(issueCountry) && !"UNKNOWN".equals(issueCountry)) {
                    Logger.m15049i(f11244a, "current route_by is issueCountry and routerCountry is: " + issueCountry);
                    return issueCountry;
                } else if ("geo_ip".equals(str2.trim())) {
                    String m14945a = new C4934b(context, c4923a, grsBaseInfo).m14945a(z);
                    Logger.m15049i(f11244a, "current route_by is geo_ip and routerCountry is: " + m14945a);
                    return m14945a;
                }
            }
        }
        return "";
    }

    /* renamed from: b */
    public static String m14955b(Context context, C4923a c4923a, String str, GrsBaseInfo grsBaseInfo, boolean z) {
        if (TextUtils.isEmpty(str)) {
            Logger.m15045w(f11244a, "routeBy must be not empty string or null.");
            return null;
        } else if ("no_route".equals(str) || "unconditional".equals(str)) {
            Logger.m15047v(f11244a, "routeBy equals NO_ROUTE_POLICY");
            return "no_route_country";
        } else {
            return m14956a(context, c4923a, str, grsBaseInfo, z);
        }
    }
}
