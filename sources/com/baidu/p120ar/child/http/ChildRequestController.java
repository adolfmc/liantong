package com.baidu.p120ar.child.http;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.baidu.p120ar.ihttp.HttpException;
import com.baidu.p120ar.ihttp.IHttpCallback;
import com.baidu.p120ar.ihttp.IHttpResponse;
import com.baidu.p120ar.utils.UrlUtils;
import java.io.IOException;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.child.http.ChildRequestController */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ChildRequestController {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.child.http.ChildRequestController$ChildRequestCallback */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface ChildRequestCallback {
        void onRequestCallbak(byte[] bArr);
    }

    private String getDegree(int i) {
        return i != -90 ? i != 0 ? i != 90 ? i != 180 ? "V" : "FV" : "HL" : "V" : "HR";
    }

    public void requestCropFaceResult(byte[] bArr, int i, final ChildRequestCallback childRequestCallback) {
        String childFacePredictUrl = UrlUtils.getChildFacePredictUrl();
        HashMap hashMap = new HashMap();
        String encodeToString = Base64.encodeToString(bArr, 0);
        String degree = getDegree(i);
        hashMap.put("image", encodeToString);
        hashMap.put("svc_name", "child-face");
        hashMap.put("vid", degree);
        ChildHttpUtility.postJsonWithCallback(childFacePredictUrl, hashMap, new IHttpCallback() { // from class: com.baidu.ar.child.http.ChildRequestController.1
            @Override // com.baidu.p120ar.ihttp.IHttpCallback
            public void onResponse(IHttpResponse iHttpResponse) {
                try {
                    String content = iHttpResponse.getContent();
                    if (TextUtils.isEmpty(content)) {
                        return;
                    }
                    try {
                        ChildRequestController.this.parseChildJson(new JSONObject(content), childRequestCallback);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }

            @Override // com.baidu.p120ar.ihttp.IHttpCallback
            public void onFail(HttpException httpException) {
                Log.e("ChildRequestController", httpException.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseChildJson(JSONObject jSONObject, ChildRequestCallback childRequestCallback) throws JSONException {
        if (jSONObject.has("err_no")) {
            if (jSONObject.getInt("err_no") == 0) {
                if (jSONObject.has("feature_res")) {
                    byte[] decode = Base64.decode(jSONObject.getString("feature_res"), 0);
                    if (childRequestCallback != null) {
                        childRequestCallback.onRequestCallbak(decode);
                    }
                    setRequestCallback(childRequestCallback, decode);
                    return;
                }
                return;
            }
            setRequestCallback(childRequestCallback, null);
        }
    }

    private void setRequestCallback(ChildRequestCallback childRequestCallback, byte[] bArr) {
        if (childRequestCallback != null) {
            childRequestCallback.onRequestCallbak(null);
        }
    }
}
