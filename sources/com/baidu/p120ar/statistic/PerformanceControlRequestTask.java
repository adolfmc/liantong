package com.baidu.p120ar.statistic;

import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;
import com.baidu.p120ar.ihttp.HttpFactory;
import com.baidu.p120ar.ihttp.IHttpRequest;
import com.baidu.p120ar.ihttp.IHttpResponse;
import com.baidu.p120ar.utils.ARSDKInfo;
import com.baidu.p120ar.utils.UrlUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.statistic.PerformanceControlRequestTask */
/* loaded from: E:\10201592_dexfile_execute.dex */
class PerformanceControlRequestTask extends AsyncTask<String, Void, List<String>> {
    private static final String ENCODING = "UTF-8";
    private static final String EVENT_SWTICH_ON = "1";
    private IFinishedListener mFinishedListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.statistic.PerformanceControlRequestTask$IFinishedListener */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface IFinishedListener {
        void onPerformanceRequestFinished(List<String> list);
    }

    public PerformanceControlRequestTask(IFinishedListener iFinishedListener) {
        this.mFinishedListener = iFinishedListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public List<String> doInBackground(String... strArr) {
        IHttpRequest newRequest = HttpFactory.newRequest();
        if (newRequest == null) {
            return null;
        }
        String performanceControlUrl = UrlUtils.getPerformanceControlUrl();
        newRequest.setUrl(performanceControlUrl).setMethod("POST").setBody(buildHttpParam()).addHeader("Content-Type: application/x-www-form-urlencoded");
        try {
            IHttpResponse execute = newRequest.execute();
            if (execute.isSuccess()) {
                return parseResponse(execute.getContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<String> parseResponse(String str) {
        int length;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("data");
            JSONObject jSONObject = optJSONObject != null ? optJSONObject.getJSONObject("statistic_swtich") : null;
            if (jSONObject != null && (length = jSONObject.length()) > 0) {
                ArrayList arrayList = new ArrayList(length);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if ("1".equals(jSONObject.opt(next))) {
                        arrayList.add(next);
                    }
                }
                return arrayList;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String buildHttpParam() {
        return String.format("%s=%s&%s=%s&%s=%s&%s=%s&%s=%s", "os_type", "android", "engine_version", String.valueOf(ARSDKInfo.getVersionCode()), "manufacture", encodeQuery(Build.MANUFACTURER), "board", encodeQuery(Build.BOARD), "hardware", encodeQuery(Build.HARDWARE));
    }

    private String encodeQuery(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(List<String> list) {
        super.onPostExecute((PerformanceControlRequestTask) list);
        IFinishedListener iFinishedListener = this.mFinishedListener;
        if (iFinishedListener != null) {
            iFinishedListener.onPerformanceRequestFinished(list);
        }
        this.mFinishedListener = null;
    }
}
