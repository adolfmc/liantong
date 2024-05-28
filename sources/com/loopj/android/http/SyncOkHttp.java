package com.loopj.android.http;

import android.text.TextUtils;
import com.loopj.android.http.AsyncHttpClient;
import java.util.Map;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class SyncOkHttp {
    private static final String TAG = "SyncOkHttp";

    public static String execute(OkHttpClient okHttpClient, String str, AsyncHttpClient.RequestBodyWay requestBodyWay, String str2, Map<String, String> map, String str3, Map<String, String> map2) throws Exception {
        Request.Builder builder = new Request.Builder();
        if ("GET".equalsIgnoreCase(str.toString())) {
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (!TextUtils.isEmpty(key)) {
                        String trim = key.trim();
                        if (str2.contains("?")) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(str2);
                            sb.append("&");
                            sb.append(trim);
                            sb.append("=");
                            sb.append(TextUtils.isEmpty(value) ? "" : value.trim());
                            str2 = sb.toString();
                        } else {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(str2);
                            sb2.append("?");
                            sb2.append(trim);
                            sb2.append("=");
                            sb2.append(TextUtils.isEmpty(value) ? "" : value.trim());
                            str2 = sb2.toString();
                        }
                    }
                }
            }
        } else if (requestBodyWay == AsyncHttpClient.RequestBodyWay.Form) {
            FormBody.Builder builder2 = new FormBody.Builder();
            if (map != null) {
                for (Map.Entry<String, String> entry2 : map.entrySet()) {
                    String key2 = entry2.getKey();
                    String value2 = entry2.getValue();
                    if (!TextUtils.isEmpty(key2)) {
                        builder2.add(key2.trim(), TextUtils.isEmpty(value2) ? "" : value2.trim());
                    }
                }
            }
            builder.post(builder2.build());
        } else if (requestBodyWay == AsyncHttpClient.RequestBodyWay.JSON) {
            MediaType parse = MediaType.parse("application/json; charset=utf-8");
            if (TextUtils.isEmpty(str3)) {
                str3 = "";
            }
            builder.post(RequestBody.create(parse, str3));
        } else {
            throw new RuntimeException("RequestBodyWay参数设置错误，请检查！");
        }
        builder.url(str2);
        if (map2 != null) {
            for (Map.Entry<String, String> entry3 : map2.entrySet()) {
                String key3 = entry3.getKey();
                String value3 = entry3.getValue();
                if (!TextUtils.isEmpty(key3) && !TextUtils.isEmpty(value3)) {
                    builder.header(key3.trim(), value3.trim());
                }
            }
        }
        Response execute = okHttpClient.newCall(builder.build()).execute();
        if (execute.isSuccessful()) {
            return execute.body().string();
        }
        int code = execute.code();
        execute.body().close();
        return "HTTP状态码:" + code;
    }
}
