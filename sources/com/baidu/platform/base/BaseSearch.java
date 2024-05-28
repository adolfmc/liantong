package com.baidu.platform.base;

import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import com.baidu.mapapi.http.AsyncHttpClient;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.district.DistrictResult;
import com.baidu.mapsdkplatform.comapi.util.AlgorithmUtil;
import com.baidu.mapsdkplatform.comapi.util.PermissionCheck;
import com.baidu.mapsdkplatform.comjni.util.AppMD5;
import com.baidu.platform.core.p158b.DistrictParser;
import com.baidu.platform.core.p158b.DistrictRequest;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.base.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class BaseSearch {

    /* renamed from: f */
    private SearchType f7499f;

    /* renamed from: b */
    private AsyncHttpClient f7495b = new AsyncHttpClient();

    /* renamed from: c */
    private Handler f7496c = new Handler(Looper.getMainLooper());

    /* renamed from: a */
    protected final Lock f7494a = new ReentrantLock();

    /* renamed from: d */
    private boolean f7497d = true;

    /* renamed from: e */
    private DistrictResult f7498e = null;

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public boolean m18098a(SearchRequest searchRequest, Object obj, SearchParser searchParser) {
        if (searchParser == null) {
            Log.e(BaseSearch.class.getSimpleName(), "The SearchParser is null, must be applied.");
            return false;
        }
        this.f7499f = searchParser.m18092a();
        String m18088a = searchRequest.m18088a(this.f7499f);
        if (m18088a == null) {
            Log.e("BaseSearch", "The sendurl is: " + m18088a);
            m18104a(searchParser.mo17483a("{SDK_InnerError:{PermissionCheckError:Error}}"), obj, searchParser);
            return false;
        }
        this.f7495b.get(m18088a, new C2973b(this, searchParser, obj));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public String m18097a(String str) {
        byte[] bArr = {102, 97, 105, 108, 100};
        try {
            bArr = AlgorithmUtil.getUrlNeedInfo(AppMD5.getUrlNeedInfo(), AppMD5.getUrlNeedInfo(), Base64.decode(str.getBytes(), 0));
        } catch (Exception unused) {
            Log.e("BaseSearch", "transform result failed");
        }
        return new String(bArr).trim();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18096a(String str, SearchParser searchParser, Object obj, AsyncHttpClient asyncHttpClient, HttpClient.ProtoResultCallback protoResultCallback) {
        SearchResult mo17483a = searchParser.mo17483a(str);
        mo17483a.status = m18094b(str);
        if (m18099a(searchParser, mo17483a)) {
            m18106a(asyncHttpClient, protoResultCallback, mo17483a);
        } else if (searchParser instanceof DistrictParser) {
            DistrictResult districtResult = this.f7498e;
            if (districtResult != null) {
                DistrictResult districtResult2 = (DistrictResult) mo17483a;
                districtResult2.setCityCode(districtResult.getCityCode());
                districtResult2.setCenterPt(this.f7498e.getCenterPt());
            }
            m18104a(mo17483a, obj, searchParser);
            this.f7497d = true;
            this.f7498e = null;
            ((DistrictParser) searchParser).m17621a(false);
        } else {
            m18104a(mo17483a, obj, searchParser);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18105a(HttpClient.HttpStateError httpStateError, SearchParser searchParser, Object obj) {
        m18104a(searchParser.mo17483a("{SDK_InnerError:{httpStateError:" + httpStateError + "}}"), obj, searchParser);
    }

    /* renamed from: a */
    private void m18104a(SearchResult searchResult, Object obj, SearchParser searchParser) {
        this.f7496c.post(new RunnableC2974c(this, searchParser, searchResult, obj));
    }

    /* renamed from: a */
    private boolean m18099a(SearchParser searchParser, SearchResult searchResult) {
        if (searchParser instanceof DistrictParser) {
            DistrictResult districtResult = (DistrictResult) searchResult;
            if (SearchResult.ERRORNO.RESULT_NOT_FOUND == districtResult.error && districtResult.getCityName() != null && this.f7497d) {
                this.f7497d = false;
                this.f7498e = districtResult;
                ((DistrictParser) searchParser).m17621a(true);
                return true;
            }
            return false;
        }
        return false;
    }

    /* renamed from: a */
    private void m18106a(AsyncHttpClient asyncHttpClient, HttpClient.ProtoResultCallback protoResultCallback, SearchResult searchResult) {
        asyncHttpClient.get(new DistrictRequest(((DistrictResult) searchResult).getCityName()).m18088a(this.f7499f), protoResultCallback);
    }

    /* renamed from: b */
    private int m18094b(String str) {
        JSONObject optJSONObject;
        if (str == null || str.equals("")) {
            return 10204;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("status")) {
                return jSONObject.getInt("status");
            }
            if (jSONObject.has("status_sp")) {
                return jSONObject.getInt("status_sp");
            }
            if (!jSONObject.has("result") || (optJSONObject = jSONObject.optJSONObject("result")) == null) {
                return 10204;
            }
            return optJSONObject.optInt("error");
        } catch (JSONException unused) {
            Log.e("BaseSearch", "Create JSONObject failed when get response result status");
            return 10204;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public boolean m18093c(String str) {
        int i;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("status") || jSONObject.has("status_sp")) {
                if (jSONObject.has("status")) {
                    i = jSONObject.getInt("status");
                } else {
                    i = jSONObject.getInt("status_sp");
                }
                switch (i) {
                    case 105:
                    case 106:
                        int permissionCheck = PermissionCheck.permissionCheck();
                        if (permissionCheck != 0) {
                            Log.e("BaseSearch", "permissionCheck result is: " + permissionCheck);
                            return true;
                        }
                        return true;
                    default:
                        return true;
                }
            }
            return true;
        } catch (JSONException unused) {
            return false;
        }
    }
}
