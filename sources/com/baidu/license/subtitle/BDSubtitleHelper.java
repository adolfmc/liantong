package com.baidu.license.subtitle;

import android.text.TextUtils;
import com.baidu.cloud.download.utils.LogUtils;
import com.baidu.license.INotProguard;
import com.baidu.license.SDKHttpConfig;
import com.baidu.license.api.ApiCallBack;
import java.io.IOException;
import java.util.HashMap;
import okhttp3.ResponseBody;
import p001a.p002a.p003a.p004a.ApiManager;
import p001a.p002a.p003a.p004a.RequestParameterUtil;
import p001a.p002a.p003a.p057f.AuthenticationManager;
import retrofit2.Call;
import retrofit2.Response;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BDSubtitleHelper implements INotProguard {
    public static final String ERROR_MSG_JOB_ID = "jobId获取失败";
    public static final String ERROR_MSG_SUBTITLE_RESULT = "字幕解析结果失败";
    public static final String ERROR_MSG_UPLOAD_PARAMS = "上传参数获取失败";
    public static final int RESPONSE_ERROR_JOB_ID = 600;
    public static final int RESPONSE_ERROR_SUBTITLE_RESULT = 700;
    public static final int RESPONSE_ERROR_UPLOAD_PARAMS = 500;
    public static final String RESPONSE_STATUS_FAIL = "FAILED";
    public static final String RESPONSE_STATUS_PENDING = "PENDING";
    public static final String RESPONSE_STATUS_RUNNING = "RUNNING";
    public static final String RESPONSE_STATUS_SUCCESS = "SUCCESS";
    public IBDSubtitleCallback ibdSubtitleCallback;

    public BDSubtitleHelper(IBDSubtitleCallback iBDSubtitleCallback) {
        this.ibdSubtitleCallback = iBDSubtitleCallback;
    }

    public void requestStartResolveSubtitle(String str, String str2) {
        IBDSubtitleCallback iBDSubtitleCallback;
        if ((TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) && (iBDSubtitleCallback = this.ibdSubtitleCallback) != null) {
            iBDSubtitleCallback.onFail(600, "参数错误");
            return;
        }
        HashMap<String, Object> m22368a = RequestParameterUtil.m22368a();
        m22368a.put("sign", RequestParameterUtil.m22364a(SDKHttpConfig.getSignStr(), AuthenticationManager.m22352a(), "utf-8"));
        m22368a.put("sourceBucket", str);
        m22368a.put("sourceKey", str2);
        ApiManager.m22371a().reqStartResolve(m22368a).enqueue(new ApiCallBack<ResponseBody>() { // from class: com.baidu.license.subtitle.BDSubtitleHelper.2
            @Override // com.baidu.license.api.ApiCallBack
            public void onFail(Call<ResponseBody> call, Throwable th) {
                th.printStackTrace();
                if (BDSubtitleHelper.this.ibdSubtitleCallback != null) {
                    BDSubtitleHelper.this.ibdSubtitleCallback.onFail(600, "jobId获取失败");
                }
            }

            @Override // com.baidu.license.api.ApiCallBack
            public void onSuccess(Call<ResponseBody> call, Response<ResponseBody> response) {
                String str3;
                try {
                    str3 = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                    str3 = null;
                }
                LogUtils.m20097e("cccc", "job result: " + str3);
                if (BDSubtitleHelper.this.ibdSubtitleCallback != null) {
                    if (!TextUtils.isEmpty(str3)) {
                        BDSubtitleHelper.this.ibdSubtitleCallback.getResolveJobResult(str3);
                    } else {
                        BDSubtitleHelper.this.ibdSubtitleCallback.onFail(600, "jobId获取失败");
                    }
                }
            }
        });
    }

    public void requestSubtitleResult(String str) {
        IBDSubtitleCallback iBDSubtitleCallback;
        if (TextUtils.isEmpty(str) && (iBDSubtitleCallback = this.ibdSubtitleCallback) != null) {
            iBDSubtitleCallback.onFail(600, "参数错误");
            return;
        }
        HashMap<String, Object> m22368a = RequestParameterUtil.m22368a();
        m22368a.put("sign", RequestParameterUtil.m22364a(SDKHttpConfig.getSignStr(), AuthenticationManager.m22352a(), "utf-8"));
        m22368a.put("jobId", str);
        ApiManager.m22371a().reqGetResolveResult(m22368a).enqueue(new ApiCallBack<ResponseBody>() { // from class: com.baidu.license.subtitle.BDSubtitleHelper.3
            @Override // com.baidu.license.api.ApiCallBack
            public void onFail(Call<ResponseBody> call, Throwable th) {
                th.printStackTrace();
                if (BDSubtitleHelper.this.ibdSubtitleCallback != null) {
                    BDSubtitleHelper.this.ibdSubtitleCallback.onFail(700, "字幕解析结果失败");
                }
            }

            @Override // com.baidu.license.api.ApiCallBack
            public void onSuccess(Call<ResponseBody> call, Response<ResponseBody> response) {
                String str2;
                try {
                    str2 = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                    str2 = null;
                }
                LogUtils.m20097e("cccc", "subtitle result: " + str2);
                if (BDSubtitleHelper.this.ibdSubtitleCallback != null) {
                    if (!TextUtils.isEmpty(str2)) {
                        BDSubtitleHelper.this.ibdSubtitleCallback.getSubtitleResult(str2);
                    } else {
                        BDSubtitleHelper.this.ibdSubtitleCallback.onFail(700, "字幕解析结果失败");
                    }
                }
            }
        });
    }

    public void requestUploadParams() {
        HashMap<String, Object> m22368a = RequestParameterUtil.m22368a();
        m22368a.put("sign", RequestParameterUtil.m22364a(SDKHttpConfig.getSignStr(), AuthenticationManager.m22352a(), "utf-8"));
        ApiManager.m22371a().reqUploadParams(m22368a).enqueue(new ApiCallBack<ResponseBody>() { // from class: com.baidu.license.subtitle.BDSubtitleHelper.1
            @Override // com.baidu.license.api.ApiCallBack
            public void onFail(Call<ResponseBody> call, Throwable th) {
                th.printStackTrace();
                if (BDSubtitleHelper.this.ibdSubtitleCallback != null) {
                    BDSubtitleHelper.this.ibdSubtitleCallback.onFail(500, "上传参数获取失败");
                }
            }

            @Override // com.baidu.license.api.ApiCallBack
            public void onSuccess(Call<ResponseBody> call, Response<ResponseBody> response) {
                String str;
                try {
                    str = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                    str = null;
                }
                LogUtils.m20097e("cccc", "uploadParams result: " + str);
                if (BDSubtitleHelper.this.ibdSubtitleCallback != null) {
                    if (!TextUtils.isEmpty(str)) {
                        BDSubtitleHelper.this.ibdSubtitleCallback.getUploadParams(str);
                    } else {
                        BDSubtitleHelper.this.ibdSubtitleCallback.onFail(500, "上传参数获取失败");
                    }
                }
            }
        });
    }
}
