package com.baidu.p120ar.steploading;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.p120ar.bean.ARCaseBundleInfo;
import com.baidu.p120ar.callback.ICallbackWith;
import com.baidu.p120ar.callback.IError;
import com.baidu.p120ar.ihttp.HttpException;
import com.baidu.p120ar.ihttp.HttpFactory;
import com.baidu.p120ar.ihttp.IHttpCallback;
import com.baidu.p120ar.ihttp.IHttpRequest;
import com.baidu.p120ar.ihttp.IHttpResponse;
import com.baidu.p120ar.pipeline.AbstractChannelHandler;
import com.baidu.p120ar.statistic.StatisticApi;
import com.baidu.p120ar.utils.RequestParamsBuilder;
import com.baidu.p120ar.utils.UrlUtils;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.steploading.StepLoadingQueryHandler */
/* loaded from: E:\10201592_dexfile_execute.dex */
class StepLoadingQueryHandler extends AbstractChannelHandler<String, String> {
    private ARCaseBundleInfo mARBundle;
    private WeakReference<Context> mContextRef;
    private IHttpRequest mRequest;
    private BundleStepResReader mStepResReader;

    public StepLoadingQueryHandler(Context context, ARCaseBundleInfo aRCaseBundleInfo, BundleStepResReader bundleStepResReader) {
        this.mContextRef = new WeakReference<>(context);
        this.mARBundle = aRCaseBundleInfo;
        this.mStepResReader = bundleStepResReader;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.baidu.p120ar.pipeline.AbstractChannelHandler
    public void run(String str, final ICallbackWith<String> iCallbackWith, final IError iError) {
        ARCaseBundleInfo aRCaseBundleInfo;
        Context context = this.mContextRef.get();
        if (context == null || (aRCaseBundleInfo = this.mARBundle) == null) {
            return;
        }
        String str2 = aRCaseBundleInfo.arKey;
        StepResInfo resInfo = this.mStepResReader.getResInfo(str);
        if (resInfo == null) {
            iError.onError(1, "资源不存在", null);
        } else if (TextUtils.isEmpty(resInfo.resId)) {
            iError.onError(1, "资源id不存在", null);
        } else if ("local".equals(resInfo.resId)) {
            iCallbackWith.run("local");
        } else if (TextUtils.isEmpty(resInfo.encoding)) {
            iError.onError(1, "编码不正确", null);
        } else {
            StatisticApi.onEvent("load_start_query");
            String stepLoadingBatchUrl = UrlUtils.getStepLoadingBatchUrl();
            this.mRequest = HttpFactory.newRequest();
            this.mRequest.setUrl(stepLoadingBatchUrl).setMethod("POST").addQueryField("id", resInfo.resId).setBody(getQueryParams(context, str2)).enqueue(new IHttpCallback() { // from class: com.baidu.ar.steploading.StepLoadingQueryHandler.1
                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Type inference failed for: r4v1, types: [java.lang.Exception] */
                @Override // com.baidu.p120ar.ihttp.IHttpCallback
                public void onResponse(IHttpResponse iHttpResponse) {
                    int i;
                    String message;
                    JSONException jSONException = null;
                    if (iHttpResponse.isSuccess()) {
                        try {
                            String downloadUrl = StepLoadingQueryHandler.this.getDownloadUrl(iHttpResponse.getContent());
                            if (iCallbackWith != null) {
                                iCallbackWith.run(downloadUrl);
                            }
                        } catch (JSONException e) {
                            jSONException = e;
                            jSONException.printStackTrace();
                            i = 3;
                            message = jSONException.getMessage();
                        } catch (Exception e2) {
                            jSONException = e2;
                            jSONException.printStackTrace();
                            i = 1;
                            message = jSONException.getMessage();
                        }
                    }
                    i = 0;
                    message = null;
                    if (TextUtils.isEmpty(message)) {
                        return;
                    }
                    StatisticApi.onEvent("load_query_failure");
                    IError iError2 = iError;
                    if (iError2 != null) {
                        iError2.onError(i, message, jSONException);
                    }
                }

                @Override // com.baidu.p120ar.ihttp.IHttpCallback
                public void onFail(HttpException httpException) {
                    StatisticApi.onEvent("load_query_failure");
                    IError iError2 = iError;
                    if (iError2 != null) {
                        iError2.onError(1, httpException.getMessage(), httpException);
                    }
                }
            });
        }
    }

    @Override // com.baidu.p120ar.pipeline.AbstractChannelHandler
    public void doCancel() {
        IHttpRequest iHttpRequest = this.mRequest;
        if (iHttpRequest != null) {
            iHttpRequest.cancel();
            this.mRequest = null;
        }
    }

    private JSONObject getQueryParams(Context context, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            RequestParamsBuilder.appendSignInfo(jSONObject);
            RequestParamsBuilder.appendUserInfo(context, jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getDownloadUrl(String str) throws Exception {
        JSONObject jSONObject = new JSONObject(str);
        if (jSONObject.getInt("errorNum") != 0) {
            throw new Exception(jSONObject.optString("errorMsg", "query res failed"));
        }
        String string = jSONObject.getString("data");
        if (TextUtils.isEmpty(string)) {
            throw new Exception("query res failed");
        }
        return string;
    }
}
