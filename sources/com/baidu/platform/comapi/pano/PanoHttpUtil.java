package com.baidu.platform.comapi.pano;

import android.net.Uri;
import android.text.TextUtils;
import com.baidu.mapapi.http.AsyncHttpClient;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.mapsdkplatform.comjni.util.AppMD5;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.pano.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PanoHttpUtil {

    /* renamed from: a */
    AsyncHttpClient f8027a = new AsyncHttpClient();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: PanoHttpUtil.java */
    /* renamed from: com.baidu.platform.comapi.pano.a$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC3085a<T> {
        /* renamed from: a */
        void mo17713a(HttpClient.HttpStateError httpStateError);

        /* renamed from: a */
        void mo17712a(T t);
    }

    /* renamed from: a */
    public void m17714a(String str, InterfaceC3085a<PanoResult> interfaceC3085a) {
        Uri.Builder builder = new Uri.Builder();
        if (HttpClient.isHttpsEnable) {
            builder.scheme("https");
        } else {
            builder.scheme("http");
        }
        builder.encodedAuthority("api.map.baidu.com");
        builder.path("/sdkproxy/lbs_androidsdk/pano/v1/");
        m17717a(builder, "qt", "poi");
        m17717a(builder, "uid", str);
        m17717a(builder, "action", "0");
        String authToken = HttpClient.getAuthToken();
        if (authToken == null) {
            interfaceC3085a.mo17712a((InterfaceC3085a<PanoResult>) new PanoResult(PanoStateError.PANO_NO_TOKEN));
            return;
        }
        m17717a(builder, "token", authToken);
        this.f8027a.get(m17718a(builder), new C3086b(this, interfaceC3085a));
    }

    /* renamed from: a */
    private void m17717a(Uri.Builder builder, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        builder.appendQueryParameter(str, str2);
    }

    /* renamed from: a */
    private String m17718a(Uri.Builder builder) {
        String uri = builder.build().toString();
        Uri.Builder buildUpon = Uri.parse(uri + HttpClient.getPhoneInfo()).buildUpon();
        buildUpon.appendQueryParameter("sign", AppMD5.getSignMD5String(buildUpon.build().getEncodedQuery()));
        return buildUpon.build().toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public PanoResult m17715a(String str) {
        if (str == null || str.equals("")) {
            return new PanoResult(PanoStateError.PANO_NOT_FOUND);
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject optJSONObject = jSONObject.optJSONObject("result");
            if (optJSONObject == null) {
                return new PanoResult(PanoStateError.PANO_NOT_FOUND);
            }
            if (optJSONObject.optInt("error") == 0) {
                JSONArray optJSONArray = jSONObject.optJSONArray("content");
                if (optJSONArray == null) {
                    return new PanoResult(PanoStateError.PANO_NOT_FOUND);
                }
                PanoResult panoResult = null;
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i).optJSONObject("poiinfo");
                    if (optJSONObject2 != null) {
                        panoResult = new PanoResult(PanoStateError.PANO_NO_ERROR);
                        panoResult.m17709a(optJSONObject2.optString("PID"));
                        panoResult.m17710a(optJSONObject2.optInt("hasstreet"));
                    }
                }
                return panoResult;
            }
            return new PanoResult(PanoStateError.PANO_UID_ERROR);
        } catch (JSONException e) {
            e.printStackTrace();
            return new PanoResult(PanoStateError.PANO_NOT_FOUND);
        }
    }
}
