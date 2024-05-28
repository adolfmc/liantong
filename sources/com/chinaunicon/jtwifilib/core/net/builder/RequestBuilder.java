package com.chinaunicon.jtwifilib.core.net.builder;

import java.io.IOException;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class RequestBuilder<T> {
    protected Map<String, String> params;
    protected Object tag;
    protected String url;

    abstract Call enqueue(Callback callback);

    abstract Response execute() throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public void appendHeader(Request.Builder builder, String str, String str2) {
        builder.header(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void appendHeaders(Request.Builder builder, Map<String, String> map) {
        Headers.Builder builder2 = new Headers.Builder();
        if (map == null || map.isEmpty()) {
            return;
        }
        for (String str : map.keySet()) {
            builder2.add(str, map.get(str));
        }
        builder.headers(builder2.build());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void appendParams(FormBody.Builder builder, Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        for (String str : map.keySet()) {
            builder.add(str, map.get(str));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String appendParams(String str, Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append(str + "?");
        if (map != null && !map.isEmpty()) {
            for (String str2 : map.keySet()) {
                sb.append(str2);
                sb.append("=");
                sb.append(map.get(str2));
                sb.append("&");
            }
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}
