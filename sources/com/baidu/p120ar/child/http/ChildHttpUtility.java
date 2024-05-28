package com.baidu.p120ar.child.http;

import com.baidu.p120ar.ihttp.HttpFactory;
import com.baidu.p120ar.ihttp.IHttpCallback;
import com.baidu.p120ar.ihttp.IHttpRequest;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.child.http.ChildHttpUtility */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ChildHttpUtility {
    public static void postJsonWithCallback(String str, HashMap hashMap, IHttpCallback iHttpCallback) {
        IHttpRequest newRequest = HttpFactory.newRequest();
        if (newRequest != null) {
            newRequest.setMethod("POST").setUrl(str).addFormData(hashMap);
            newRequest.enqueue(iHttpCallback);
        }
    }
}
