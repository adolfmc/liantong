package com.tydic.tydic_tracker.app;

import android.util.Log;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class TianYanUpload {
    public static final String TAG = "TianYanUpload";
    private String prodDomain = "https://m.client.10010.com/collectionService/gatherConsumer/update";
    private String testDomain = "https://ecstest2018.10010.com/collectionService/gatherConsumer/update";

    public String okhttpPostFile_forBytes(byte[] bArr) {
        String str = this.prodDomain;
        if ("test".equals(TYApplication.env)) {
            str = this.testDomain;
        }
        try {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            builder.addFormDataPart("reqData", "reqData", createProgressRequestBody(MediaType.parse("application/octet-stream"), bArr));
            Request build = new Request.Builder().url(str).post(builder.build()).build();
            OkHttpClient init = NBSOkHttp3Instrumentation.init();
            Log.i("tyLog", "开始发送请求");
            init.newCall(build).enqueue(new Callback() { // from class: com.tydic.tydic_tracker.app.TianYanUpload.1
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    Log.i("tyLog", iOException.getMessage());
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) throws IOException {
                    Log.i("tyLog", response.body().string());
                    TYApplication.clearTable();
                }
            });
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private <T> RequestBody createProgressRequestBody(final MediaType mediaType, final byte[] bArr) {
        return new RequestBody() { // from class: com.tydic.tydic_tracker.app.TianYanUpload.2
            @Override // okhttp3.RequestBody
            public MediaType contentType() {
                return mediaType;
            }

            @Override // okhttp3.RequestBody
            public long contentLength() {
                return bArr.length;
            }

            @Override // okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                try {
                    Source source = Okio.source(new ByteArrayInputStream(bArr));
                    Buffer buffer = new Buffer();
                    contentLength();
                    while (true) {
                        long read = source.read(buffer, 2048L);
                        if (read == -1) {
                            return;
                        }
                        bufferedSink.write(buffer, read);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
