package com.baidu.p120ar.utils;

import com.baidu.p120ar.ihttp.HttpFactory;
import com.baidu.p120ar.ihttp.IHttpRequest;
import com.baidu.p120ar.ihttp.IHttpResponse;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.utils.HttpUtils */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class HttpUtils {
    public static String readRequestContent(IHttpRequest iHttpRequest) {
        if (iHttpRequest != null) {
            try {
                IHttpResponse execute = iHttpRequest.execute();
                if (execute.isSuccess()) {
                    return execute.getContent();
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static String getUrlAsString(String str) {
        IHttpRequest newRequest = HttpFactory.newRequest();
        if (newRequest != null) {
            newRequest.setMethod("GET").setUrl(str);
            return readRequestContent(newRequest);
        }
        return null;
    }

    public static String postJson(String str, JSONObject jSONObject) {
        IHttpRequest newRequest = HttpFactory.newRequest();
        if (newRequest != null) {
            newRequest.setMethod("POST").setUrl(str).setBody(jSONObject);
            return readRequestContent(newRequest);
        }
        return null;
    }

    public static String postForm(String str, String str2) {
        IHttpRequest newRequest = HttpFactory.newRequest();
        if (newRequest != null) {
            newRequest.setMethod("POST").setUrl(str).addHeader("Content-Type: application/x-www-form-urlencoded").setBody(str2);
            return readRequestContent(newRequest);
        }
        return null;
    }

    public static String uploadFile(String str, HashMap hashMap, byte[] bArr) {
        IHttpRequest newRequest = HttpFactory.newRequest();
        if (newRequest != null) {
            newRequest.setMethod("POST").setUrl(str).setAsMultipart();
            if (hashMap != null) {
                newRequest.addPartMap(hashMap);
            }
            newRequest.addFile("image", bArr);
            newRequest.setAsMultipart();
            return readRequestContent(newRequest);
        }
        return null;
    }
}
