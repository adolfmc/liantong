package com.sinovatech.unicom.separatemodule.miniprogram.lowcode;

import android.content.Context;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation;
import com.sinovatech.unicom.hub.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.dic.ManagerWebCacheDictionary;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.Md5FileNameGenerator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.HashMap;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CumpLowcodeResourceIntercepter {
    public static final String SCHEME = "edoplowcode://";
    public static final String TAG = "CumpLowcodeResourceIntercepter";
    private static CumpLowcodeResourceIntercepter instance;
    private Context context;
    private OkHttpClient okHttpClient = NBSOkHttp3Instrumentation.init();

    public static synchronized CumpLowcodeResourceIntercepter getInstance(Context context) {
        CumpLowcodeResourceIntercepter cumpLowcodeResourceIntercepter;
        synchronized (CumpLowcodeResourceIntercepter.class) {
            if (instance == null) {
                synchronized (CumpLowcodeResourceIntercepter.class) {
                    if (instance == null) {
                        instance = new CumpLowcodeResourceIntercepter(context);
                    }
                }
            }
            cumpLowcodeResourceIntercepter = instance;
        }
        return cumpLowcodeResourceIntercepter;
    }

    private CumpLowcodeResourceIntercepter(Context context) {
        this.context = context;
    }

    public WebResourceResponse interceptLowcodeResource(WebView webView, WebResourceRequest webResourceRequest) {
        final String uri = webResourceRequest.getUrl().toString();
        try {
            if (uri.startsWith(SCHEME)) {
                MsLogUtil.m7999d(TAG, "interceptLowcodeResource 原始URL=" + uri + " 线程：" + Thread.currentThread().getName());
                String replace = uri.replace(SCHEME, "https://");
                File file = new File(ManagerWebCacheDictionary.getWebviewResourceIntercept_LowcodeComponent_Dic(true), getFileNameFromUrl(replace));
                MsLogUtil.m7999d(TAG, "interceptLowcodeResource 目标存储=" + file.getAbsolutePath());
                if (file.exists()) {
                    MsLogUtil.m7999d(TAG, "interceptLowcodeResource 有缓存文件直接返回 " + uri);
                    return new WebResourceResponse(getMimeTypeFromFileName(replace), "UTF-8", new FileInputStream(file));
                }
                PipedInputStream pipedInputStream = new PipedInputStream();
                final PipedOutputStream pipedOutputStream = new PipedOutputStream(pipedInputStream);
                final FileOutputStream fileOutputStream = new FileOutputStream(file);
                this.okHttpClient.newCall(new Request.Builder().url(replace).build()).enqueue(new Callback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.lowcode.CumpLowcodeResourceIntercepter.1
                    @Override // okhttp3.Callback
                    public void onFailure(@NotNull Call call, @NotNull IOException iOException) {
                        try {
                            MsLogUtil.m7999d(CumpLowcodeResourceIntercepter.TAG, "interceptLowcodeResource 请求远程文件报错：" + iOException.getMessage() + " " + uri + " " + call.request().url().toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override // okhttp3.Callback
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String str = "";
                        try {
                            try {
                                str = call.request().url().toString();
                                MsLogUtil.m7999d(CumpLowcodeResourceIntercepter.TAG, "interceptLowcodeResource 请求远程文件返回：" + response.isSuccessful() + " 线程：" + Thread.currentThread().getName() + " " + uri + " " + str);
                                if (response.isSuccessful()) {
                                    InputStream byteStream = response.body().byteStream();
                                    byte[] bArr = new byte[4096];
                                    while (true) {
                                        int read = byteStream.read(bArr);
                                        if (read == -1) {
                                            break;
                                        }
                                        pipedOutputStream.write(bArr, 0, read);
                                        fileOutputStream.write(bArr, 0, read);
                                    }
                                    MsLogUtil.m7999d(CumpLowcodeResourceIntercepter.TAG, "interceptLowcodeResource 远程文件流结束 " + uri + " " + str);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                MsLogUtil.m7999d(CumpLowcodeResourceIntercepter.TAG, "interceptLowcodeResource 请求远程文件报错：" + e.getMessage() + " " + uri + " " + str);
                            }
                        } finally {
                            fileOutputStream.close();
                            pipedOutputStream.close();
                        }
                    }
                });
                MsLogUtil.m7999d(TAG, "interceptLowcodeResource 返回远程文件流 " + uri);
                WebResourceResponse webResourceResponse = new WebResourceResponse(getMimeTypeFromFileName(replace), "UTF-8", pipedInputStream);
                HashMap hashMap = new HashMap();
                hashMap.put("access-control-allow-origin", "*");
                webResourceResponse.setResponseHeaders(hashMap);
                return webResourceResponse;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7999d(TAG, "interceptLowcodeResource 错误：" + e.getMessage() + " " + uri);
            return null;
        }
    }

    private String getMimeTypeFromFileName(String str) {
        return str.endsWith(".js") ? "application/javascript" : str.endsWith(".css") ? "text/css" : "*/*";
    }

    private String getFileNameFromUrl(String str) {
        return Md5FileNameGenerator.generate(str);
    }

    public void clearAllCache() {
        try {
            ManagerWebCacheDictionary.clearWebviewResourceIntercept_Lowcode_Dic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
