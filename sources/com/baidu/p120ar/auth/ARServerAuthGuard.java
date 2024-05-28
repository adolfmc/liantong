package com.baidu.p120ar.auth;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.baidu.p120ar.auth.IAuthGuard;
import com.baidu.p120ar.bean.DuMixARConfig;
import com.baidu.p120ar.ihttp.HttpException;
import com.baidu.p120ar.ihttp.HttpFactory;
import com.baidu.p120ar.ihttp.IHttpCallback;
import com.baidu.p120ar.ihttp.IHttpRequest;
import com.baidu.p120ar.ihttp.IHttpResponse;
import com.baidu.p120ar.utils.ARLog;
import com.baidu.p120ar.utils.ARSDKInfo;
import com.baidu.p120ar.utils.DeviceUuidFactory;
import com.baidu.p120ar.utils.MD5Utils;
import com.baidu.p120ar.utils.UrlUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.auth.ARServerAuthGuard */
/* loaded from: E:\10201592_dexfile_execute.dex */
class ARServerAuthGuard implements IAuthGuard {
    private String mApiKey;
    private String mAppId;
    private boolean mIgnoreNetError;
    private volatile IHttpRequest mRequest;
    private int mRetryCount = 0;
    private IAuthGuard.FeatureCodeUpdateListener mUpdateListener;

    public ARServerAuthGuard(AuthSetting authSetting) {
        this.mIgnoreNetError = authSetting.ignoreNetError;
        this.mAppId = authSetting.respectAppId;
        this.mApiKey = authSetting.respectApiKey;
    }

    @Override // com.baidu.p120ar.auth.IAuthGuard
    public void setValidFeatureCodeUpdateListener(IAuthGuard.FeatureCodeUpdateListener featureCodeUpdateListener) {
        this.mUpdateListener = featureCodeUpdateListener;
    }

    @Override // com.baidu.p120ar.auth.IAuthGuard
    public void doAuth(Context context, IAuthCallback iAuthCallback) {
        this.mRequest = HttpFactory.newRequest();
        if (this.mRequest == null) {
            return;
        }
        long[] waitSystemTime = SystemTimeChecker.waitSystemTime(10, 50L);
        if (waitSystemTime[0] != 1) {
            ARLog.m20419e("ARAuth", "time err. " + waitSystemTime[1]);
            if (iAuthCallback != null) {
                iAuthCallback.onSuccess();
                return;
            }
            return;
        }
        String aRAuthUrl = UrlUtils.getARAuthUrl();
        this.mRequest.setMethod("POST").setUrl(aRAuthUrl).addHeader("Content-Type: application/x-www-form-urlencoded").setBody(buildAuthParams(context));
        doRequest(iAuthCallback);
    }

    private void doRequest(final IAuthCallback iAuthCallback) {
        if (this.mRequest == null) {
            return;
        }
        this.mRequest.enqueue(new IHttpCallback() { // from class: com.baidu.ar.auth.ARServerAuthGuard.1
            @Override // com.baidu.p120ar.ihttp.IHttpCallback
            public void onResponse(IHttpResponse iHttpResponse) {
                try {
                    ResponseData parseResponse = ARServerAuthGuard.this.parseResponse(iHttpResponse);
                    if (parseResponse.isSuccess) {
                        ARServerAuthGuard.this.processFeatures(parseResponse.data);
                        if (iAuthCallback != null) {
                            iAuthCallback.onSuccess();
                        }
                    } else if (iAuthCallback != null) {
                        iAuthCallback.onError(parseResponse.errorMessage, 0);
                    }
                } catch (Exception unused) {
                    ARServerAuthGuard.this.retry(iAuthCallback);
                }
            }

            @Override // com.baidu.p120ar.ihttp.IHttpCallback
            public void onFail(HttpException httpException) {
                ARServerAuthGuard.this.retry(iAuthCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ResponseData parseResponse(IHttpResponse iHttpResponse) throws HttpException, IOException, JSONException {
        if (iHttpResponse == null || !iHttpResponse.isSuccess()) {
            throw new HttpException(4, "response fail");
        }
        JSONObject jSONObject = new JSONObject(iHttpResponse.getContent());
        if (jSONObject.has("errorNum")) {
            ResponseData responseData = new ResponseData();
            responseData.isSuccess = jSONObject.getInt("errorNum") == 0;
            if (responseData.isSuccess) {
                responseData.data = jSONObject.optJSONObject("data");
            } else {
                responseData.errorMessage = jSONObject.has("errorMsg") ? jSONObject.getString("errorMsg") : "";
            }
            return responseData;
        }
        throw new HttpException(4, "response format is error");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void retry(IAuthCallback iAuthCallback) {
        int i = this.mRetryCount + 1;
        this.mRetryCount = i;
        if (i > 5) {
            if (iAuthCallback != null) {
                iAuthCallback.onSuccess();
                return;
            }
            return;
        }
        ARLog.m20419e("ARAuth", "retry " + this.mRetryCount + " at " + System.currentTimeMillis());
        try {
            Thread.currentThread();
            Thread.sleep(this.mRetryCount * 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        doRequest(iAuthCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processFeatures(JSONObject jSONObject) {
        if (this.mUpdateListener == null) {
            return;
        }
        HashSet hashSet = new HashSet();
        if (jSONObject == null || !jSONObject.has("features")) {
            hashSet.addAll(FeatureCodes.getAll());
        } else {
            JSONArray optJSONArray = jSONObject.optJSONArray("features");
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    hashSet.add(Integer.valueOf(optJSONArray.optInt(i)));
                }
            }
        }
        this.mUpdateListener.onUpdate(hashSet);
    }

    private String buildAuthParams(Context context) {
        UUID deviceUuid = new DeviceUuidFactory(context).getDeviceUuid();
        String uuid = deviceUuid == null ? "" : deviceUuid.toString();
        StringBuilder sb = new StringBuilder();
        String sDKType = ARSDKInfo.getSDKType();
        appendParam(sb, "app_id", TextUtils.isEmpty(this.mAppId) ? DuMixARConfig.getAipAppId() : this.mAppId);
        appendParam(sb, "brand", Build.BRAND);
        appendParam(sb, "device", Build.DEVICE);
        appendParam(sb, "dumix_type", sDKType);
        appendParam(sb, "fr", "-1");
        appendParam(sb, "manufacturer", Build.MANUFACTURER);
        appendParam(sb, "model", Build.MODEL);
        appendParam(sb, "os_type", "android");
        appendParam(sb, "serial_num", getSerial(context));
        appendParam(sb, "timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        appendParam(sb, "user_id", uuid);
        appendParam(sb, "sign", generateSign(sb.toString()));
        return sb.toString();
    }

    private String getSerial(Context context) {
        String str;
        if (Build.VERSION.SDK_INT > 28) {
            str = getPseudoDeviceId();
        } else if (Build.VERSION.SDK_INT > 27 && context.checkSelfPermission("android.permission.READ_PHONE_STATE") == 0) {
            str = Build.getSerial();
        } else {
            str = Build.SERIAL;
        }
        return "unknown".equals(str) ? "" : str;
    }

    private String getPseudoDeviceId() {
        String str = Build.CPU_ABI;
        if (Build.VERSION.SDK_INT >= 21) {
            str = Arrays.asList(Build.SUPPORTED_ABIS).toString();
        }
        String uuid = new UUID(("182020" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (str.length() % 10) + (Build.DEVICE.length() % 10) + (Build.DISPLAY.length() % 10) + (Build.HOST.length() % 10) + (Build.ID.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10) + (Build.TAGS.length() % 10) + (Build.TYPE.length() % 10) + (Build.USER.length() % 10)).hashCode(), "dumix corp".hashCode()).toString();
        String valueOf = TextUtils.isEmpty(Build.MANUFACTURER) ? "A" : String.valueOf(Build.MANUFACTURER.charAt(0));
        return valueOf + uuid.replace("-", "").toUpperCase();
    }

    private String generateSign(String str) {
        String aPIKey = TextUtils.isEmpty(this.mAppId) ? DuMixARConfig.getAPIKey() : this.mApiKey;
        return MD5Utils.md5(str + aPIKey);
    }

    private void appendParam(StringBuilder sb, String str, String str2) {
        if (sb.length() > 0) {
            sb.append("&");
        }
        sb.append(str + "=" + encodeUrlComponent(str2));
    }

    private String encodeUrlComponent(String str) {
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.auth.ARServerAuthGuard$ResponseData */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class ResponseData {
        JSONObject data;
        String errorMessage;
        boolean isSuccess;

        private ResponseData() {
        }
    }
}
