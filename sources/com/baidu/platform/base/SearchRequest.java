package com.baidu.platform.base;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapsdkplatform.comapi.util.AlgorithmUtil;
import com.baidu.mapsdkplatform.comapi.util.PermissionCheck;
import com.baidu.mapsdkplatform.comjni.util.AppMD5;
import com.baidu.platform.comapi.basestruct.Point;
import com.baidu.platform.domain.UrlProvider;
import com.baidu.platform.domain.UrlProviderFactory;
import com.baidu.platform.util.BaseParamBuilder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.base.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class SearchRequest {

    /* renamed from: b */
    private boolean f7509b = true;

    /* renamed from: c */
    private boolean f7510c = true;

    /* renamed from: a */
    protected BaseParamBuilder f7508a = new BaseParamBuilder();

    /* renamed from: a */
    public abstract String mo17471a(UrlProvider urlProvider);

    /* renamed from: a */
    public void m18085a(boolean z) {
        this.f7510c = z;
    }

    /* renamed from: b */
    public void m18083b(boolean z) {
        this.f7509b = z;
    }

    /* renamed from: a */
    public String m18088a(SearchType searchType) {
        String mo17471a = mo17471a(UrlProviderFactory.m17445a());
        String authToken = HttpClient.getAuthToken();
        if (authToken == null) {
            Log.e("SearchRequest", "toUrlString get authtoken failed");
            int permissionCheck = PermissionCheck.permissionCheck();
            if (permissionCheck != 0) {
                Log.e("SearchRequest", "try permissionCheck result is: " + permissionCheck);
                return null;
            }
            authToken = HttpClient.getAuthToken();
        }
        if (this.f7509b) {
            this.f7508a.m17443a("token", authToken);
        }
        String m17444a = this.f7508a.m17444a();
        if (m18084b(searchType)) {
            m17444a = m18087a(searchType, m17444a);
        }
        String str = m17444a + HttpClient.getPhoneInfo();
        if (this.f7510c) {
            str = str + "&sign=" + AppMD5.getSignMD5String(str);
        }
        return mo17471a + "?" + str;
    }

    /* renamed from: a */
    private String m18087a(SearchType searchType, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return SearchType.REVERSE_GEO_CODER == searchType ? m18086a(str) : str;
    }

    /* renamed from: a */
    private String m18086a(String str) {
        String substring = str.substring(str.indexOf("location=") + 9, str.indexOf("&", str.indexOf("location=")));
        if (TextUtils.isEmpty(substring)) {
            return str;
        }
        byte[] bArr = {0};
        try {
            bArr = AlgorithmUtil.setUrlNeedInfo(AppMD5.getUrlNeedInfo(), AppMD5.getUrlNeedInfo(), substring.getBytes());
        } catch (Exception e) {
            Log.e("BaseSearch", "get location failed", e);
        }
        return str.replace(substring, Base64.encodeToString(bArr, 0).trim());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final String m18089a(PlanNode planNode) {
        if (planNode == null) {
            return null;
        }
        String str = new String("{");
        LatLng location = planNode.getLocation();
        if (location != null) {
            String str2 = str + "\"type\":1,";
            Point ll2point = CoordUtil.ll2point(location);
            return str2 + "\"xy\":\"" + ll2point.f7536x + "," + ll2point.f7537y + "\"}";
        } else if (planNode.getName() != null) {
            return (str + "\"type\":2,") + "\"keyword\":\"" + planNode.getName() + "\"}";
        } else {
            return str;
        }
    }

    /* renamed from: b */
    private boolean m18084b(SearchType searchType) {
        return SearchType.REVERSE_GEO_CODER == searchType;
    }
}
