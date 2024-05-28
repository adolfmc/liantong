package com.baidu.platform.core.p163g;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.search.share.RouteShareURLOption;
import com.baidu.mapsdkplatform.comjni.util.AppMD5;
import com.baidu.platform.base.SearchRequest;
import com.baidu.platform.comapi.basestruct.Point;
import com.baidu.platform.domain.UrlProvider;
import com.baidu.platform.util.BaseParamBuilder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.g.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RouteShareRequest extends SearchRequest {
    public RouteShareRequest(RouteShareURLOption routeShareURLOption) {
        m17501a(routeShareURLOption);
    }

    /* renamed from: a */
    private void m17501a(RouteShareURLOption routeShareURLOption) {
        String str;
        String str2;
        BaseParamBuilder baseParamBuilder = new BaseParamBuilder();
        Point ll2point = CoordUtil.ll2point(routeShareURLOption.mFrom.getLocation());
        Point ll2point2 = CoordUtil.ll2point(routeShareURLOption.mTo.getLocation());
        if (ll2point != null) {
            str = "1$$$$" + ll2point.f7536x + "," + ll2point.f7537y + "$$";
        } else {
            str = "2$$$$$$";
        }
        String name = routeShareURLOption.mFrom.getName();
        name = (name == null || name.equals("")) ? "起点" : "起点";
        String str3 = str + name + "$$0$$$$";
        if (ll2point2 != null) {
            str2 = "1$$$$" + ll2point2.f7536x + "," + ll2point2.f7537y + "$$";
        } else {
            str2 = "2$$$$$$";
        }
        String name2 = routeShareURLOption.mTo.getName();
        name2 = (name2 == null || name2.equals("")) ? "终点" : "终点";
        String str4 = str2 + name2 + "$$0$$$$";
        String str5 = "";
        String str6 = "";
        switch (routeShareURLOption.mMode.ordinal()) {
            case 0:
                str6 = "&sharecallbackflag=carRoute";
                str5 = "nav";
                baseParamBuilder.m17443a("sc", m17500a(routeShareURLOption.mFrom.getCity()) + "");
                baseParamBuilder.m17443a("ec", m17500a(routeShareURLOption.mTo.getCity()) + "");
                break;
            case 1:
                str6 = "&sharecallbackflag=footRoute";
                str5 = "walk";
                baseParamBuilder.m17443a("sc", m17500a(routeShareURLOption.mFrom.getCity()) + "");
                baseParamBuilder.m17443a("ec", m17500a(routeShareURLOption.mTo.getCity()) + "");
                break;
            case 2:
                str6 = "&sharecallbackflag=cycleRoute";
                str5 = "cycle";
                baseParamBuilder.m17443a("sc", m17500a(routeShareURLOption.mFrom.getCity()) + "");
                baseParamBuilder.m17443a("ec", m17500a(routeShareURLOption.mTo.getCity()) + "");
                break;
            case 3:
                str6 = "&i=" + routeShareURLOption.mPn + ",1,1&sharecallbackflag=busRoute";
                baseParamBuilder.m17443a("c", routeShareURLOption.mCityCode + "");
                str5 = "bt";
                break;
        }
        baseParamBuilder.m17443a("sn", str3);
        baseParamBuilder.m17443a("en", str4);
        String str7 = "&" + baseParamBuilder.m17444a() + ("&start=" + name + "&end=" + name2);
        this.f7508a.m17443a("url", "http://map.baidu.com/?newmap=1&s=" + str5 + (AppMD5.encodeUrlParamsValue(str7) + str6));
        this.f7508a.m17443a("from", "android_map_sdk");
    }

    @Override // com.baidu.platform.base.SearchRequest
    /* renamed from: a */
    public String mo17471a(UrlProvider urlProvider) {
        return urlProvider.mo17450r();
    }

    /* renamed from: a */
    private int m17500a(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }
}
