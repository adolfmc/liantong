package com.baidu.p120ar.auth;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.baidu.p120ar.auth.IAuthGuard;
import com.baidu.p120ar.bean.ARConfig;
import com.baidu.p120ar.bean.DuMixARConfig;
import com.baidu.p120ar.ihttp.HttpException;
import com.baidu.p120ar.ihttp.HttpFactory;
import com.baidu.p120ar.ihttp.IHttpCallback;
import com.baidu.p120ar.ihttp.IHttpRequest;
import com.baidu.p120ar.utils.ARSDKInfo;
import com.baidu.p120ar.utils.RequestParamsBuilder;
import com.baidu.p120ar.utils.UrlUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.auth.AipAuthGuard */
/* loaded from: E:\10201592_dexfile_execute.dex */
class AipAuthGuard implements IAuthGuard {
    private boolean mIgnoreNetError;
    private IAuthGuard.FeatureCodeUpdateListener mUpdateListener;

    public AipAuthGuard(AuthSetting authSetting) {
        this.mIgnoreNetError = authSetting.ignoreNetError;
    }

    @Override // com.baidu.p120ar.auth.IAuthGuard
    public void setValidFeatureCodeUpdateListener(IAuthGuard.FeatureCodeUpdateListener featureCodeUpdateListener) {
        this.mUpdateListener = featureCodeUpdateListener;
    }

    @Override // com.baidu.p120ar.auth.IAuthGuard
    public void doAuth(Context context, final IAuthCallback iAuthCallback) {
        IHttpRequest newRequest = HttpFactory.newRequest();
        if (newRequest == null) {
            return;
        }
        newRequest.setMethod("POST").setUrl(UrlUtils.getAipAuthUrl()).addQueryField("access_token", getToken(context)).setBody(getAuthParams(context));
        newRequest.enqueue(new IHttpCallback() { // from class: com.baidu.ar.auth.AipAuthGuard.1
            /* JADX WARN: Removed duplicated region for block: B:11:0x0037 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:12:0x0038  */
            @Override // com.baidu.p120ar.ihttp.IHttpCallback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onResponse(com.baidu.p120ar.ihttp.IHttpResponse r5) {
                /*
                    r4 = this;
                    boolean r0 = r5.isSuccess()
                    if (r0 == 0) goto L23
                    org.json.JSONObject r0 = new org.json.JSONObject     // Catch: java.lang.Exception -> L10
                    java.lang.String r5 = r5.getContent()     // Catch: java.lang.Exception -> L10
                    r0.<init>(r5)     // Catch: java.lang.Exception -> L10
                    goto L35
                L10:
                    r5 = move-exception
                    com.baidu.ar.auth.AipAuthGuard r0 = com.baidu.p120ar.auth.AipAuthGuard.this
                    com.baidu.ar.auth.IAuthCallback r1 = r2
                    com.baidu.ar.ihttp.HttpException r2 = new com.baidu.ar.ihttp.HttpException
                    r3 = 4
                    java.lang.String r5 = r5.getMessage()
                    r2.<init>(r3, r5)
                    com.baidu.p120ar.auth.AipAuthGuard.access$000(r0, r1, r2)
                    goto L34
                L23:
                    com.baidu.ar.auth.AipAuthGuard r0 = com.baidu.p120ar.auth.AipAuthGuard.this
                    com.baidu.ar.auth.IAuthCallback r1 = r2
                    com.baidu.ar.ihttp.HttpException r2 = new com.baidu.ar.ihttp.HttpException
                    r3 = 1
                    java.lang.String r5 = r5.getMessage()
                    r2.<init>(r3, r5)
                    com.baidu.p120ar.auth.AipAuthGuard.access$000(r0, r1, r2)
                L34:
                    r0 = 0
                L35:
                    if (r0 != 0) goto L38
                    return
                L38:
                    if (r0 == 0) goto L57
                    java.lang.String r5 = "error_msg"
                    boolean r5 = r0.has(r5)
                    if (r5 == 0) goto L57
                    java.lang.String r5 = "error_msg"
                    java.lang.String r5 = r0.getString(r5)     // Catch: org.json.JSONException -> L49
                    goto L4e
                L49:
                    r5 = move-exception
                    java.lang.String r5 = r5.getMessage()
                L4e:
                    com.baidu.ar.auth.IAuthCallback r0 = r2
                    if (r0 == 0) goto L7b
                    r1 = 0
                    r0.onError(r5, r1)
                    goto L7b
                L57:
                    com.baidu.ar.auth.AipAuthGuard r5 = com.baidu.p120ar.auth.AipAuthGuard.this
                    com.baidu.ar.auth.IAuthGuard$FeatureCodeUpdateListener r5 = com.baidu.p120ar.auth.AipAuthGuard.access$100(r5)
                    if (r5 == 0) goto L74
                    java.util.HashSet r5 = new java.util.HashSet
                    r5.<init>()
                    java.util.List r0 = com.baidu.p120ar.auth.FeatureCodes.getAll()
                    r5.addAll(r0)
                    com.baidu.ar.auth.AipAuthGuard r0 = com.baidu.p120ar.auth.AipAuthGuard.this
                    com.baidu.ar.auth.IAuthGuard$FeatureCodeUpdateListener r0 = com.baidu.p120ar.auth.AipAuthGuard.access$100(r0)
                    r0.onUpdate(r5)
                L74:
                    com.baidu.ar.auth.IAuthCallback r5 = r2
                    if (r5 == 0) goto L7b
                    r5.onSuccess()
                L7b:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.baidu.p120ar.auth.AipAuthGuard.C22481.onResponse(com.baidu.ar.ihttp.IHttpResponse):void");
            }

            @Override // com.baidu.p120ar.ihttp.IHttpCallback
            public void onFail(HttpException httpException) {
                AipAuthGuard.this.executeErrorCallback(iAuthCallback, httpException);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeErrorCallback(IAuthCallback iAuthCallback, HttpException httpException) {
        if (iAuthCallback != null) {
            if (this.mIgnoreNetError && httpException.getCode() == 1) {
                iAuthCallback.onSuccess();
            } else {
                iAuthCallback.onError(httpException.getMessage(), 0);
            }
        }
    }

    private JSONObject getAuthParams(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            RequestParamsBuilder.appendSignInfo(jSONObject);
            RequestParamsBuilder.appendUserInfo(context, jSONObject);
            jSONObject.put("sdk_type", ARSDKInfo.getSDKType());
            jSONObject.put("function_type", ARSDKInfo.getFunctionType());
            jSONObject.put("sdk_version_code", ARSDKInfo.getVersionCode());
            jSONObject.put("sdk_version_name", ARSDKInfo.getVersionName());
            jSONObject.put("os_type", "android");
            jSONObject.put("os_version", Build.VERSION.RELEASE);
            jSONObject.put("device_id", Build.MODEL.toLowerCase());
            jSONObject.put("ar_key", ARConfig.getARKey());
            jSONObject.put("ar_type", ARConfig.getARType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private String getToken(Context context) {
        try {
            String token = AipTokenWrapper.getToken(context);
            if (TextUtils.isEmpty(token) || !token.contains("-")) {
                return token;
            }
            String substring = token.substring(0, token.lastIndexOf("-"));
            return substring + "-" + DuMixARConfig.getAipAppId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
