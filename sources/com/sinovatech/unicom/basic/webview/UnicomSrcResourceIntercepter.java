package com.sinovatech.unicom.basic.webview;

import android.content.Context;
import android.content.pm.PackageManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.jakewharton.disklrucache.DiskLruCache;
import com.sinovatech.unicom.hub.utils.MsLogUtil;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.dic.ManagerWebCacheDictionary;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.Md5FileNameGenerator;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.HashMap;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UnicomSrcResourceIntercepter {
    public static final String SCHEME = "unicomsrc://";
    public static final String SCHEMESSL = "unicomsrcs://";
    private static UnicomSrcResourceIntercepter instance;
    private Context context;
    private DiskLruCache mDiskLruCache;
    public final String TAG = "UnicomSrcResourceIntercepter";
    private int maxCacheSize = 209715200;
    private OkHttpClient okHttpClient = App.getAsyncHttpClient(10, 10, 10, 10).getOkHttpClient();

    public static synchronized UnicomSrcResourceIntercepter getInstance(Context context) {
        UnicomSrcResourceIntercepter unicomSrcResourceIntercepter;
        synchronized (UnicomSrcResourceIntercepter.class) {
            if (instance == null) {
                synchronized (UnicomSrcResourceIntercepter.class) {
                    if (instance == null) {
                        instance = new UnicomSrcResourceIntercepter(context);
                    }
                }
            }
            unicomSrcResourceIntercepter = instance;
        }
        return unicomSrcResourceIntercepter;
    }

    private UnicomSrcResourceIntercepter(Context context) {
        this.context = context;
        try {
            this.mDiskLruCache = DiskLruCache.open(createResourceCacheDir(), getVersionCode(context), 1, this.maxCacheSize);
        } catch (Exception e) {
            MsLogUtil.m7997e("UnicomSrcResourceIntercepter", "初始化DiskLruCache错误" + e.toString());
        }
    }

    public WebResourceResponse interceptSrcResource(WebView webView, WebResourceRequest webResourceRequest) {
        final String uri = webResourceRequest.getUrl().toString();
        final String fileNameFromUrl = getFileNameFromUrl(uri);
        try {
            if (uri.startsWith(SCHEME) || uri.startsWith(SCHEMESSL)) {
                String replace = uri.replace(SCHEMESSL, "https://").replace(SCHEME, "http://");
                DiskLruCache.Snapshot snapshot = this.mDiskLruCache.get(fileNameFromUrl);
                if (snapshot != null && snapshot.getInputStream(0) != null) {
                    MsLogUtil.m7999d("UnicomSrcResourceIntercepter", "有缓存文件直接返回 " + uri);
                    WebResourceResponse webResourceResponse = new WebResourceResponse(getMimeTypeFromFileName(replace), "UTF-8", snapshot.getInputStream(0));
                    HashMap hashMap = new HashMap();
                    hashMap.put("access-control-allow-origin", "*");
                    webResourceResponse.setResponseHeaders(hashMap);
                    return webResourceResponse;
                }
                PipedInputStream pipedInputStream = new PipedInputStream();
                final PipedOutputStream pipedOutputStream = new PipedOutputStream(pipedInputStream);
                this.okHttpClient.newCall(new Request.Builder().url(replace).build()).enqueue(new Callback() { // from class: com.sinovatech.unicom.basic.webview.UnicomSrcResourceIntercepter.1
                    @Override // okhttp3.Callback
                    public void onFailure(@NotNull Call call, @NotNull IOException iOException) {
                        try {
                            MsLogUtil.m7999d("UnicomSrcResourceIntercepter", "请求远程文件报错 onFailure " + iOException.getMessage() + " " + uri);
                            pipedOutputStream.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                            MsLogUtil.m7999d("UnicomSrcResourceIntercepter", "请求远程文件报错 onFailure catch " + e.getMessage());
                        }
                    }

                    @Override // okhttp3.Callback
                    public void onResponse(@NotNull Call call, @NotNull Response response) {
                        PipedOutputStream pipedOutputStream2;
                        try {
                            DiskLruCache.Editor edit = UnicomSrcResourceIntercepter.this.mDiskLruCache.edit(fileNameFromUrl);
                            try {
                                if (response.isSuccessful()) {
                                    InputStream byteStream = response.body().byteStream();
                                    OutputStream newOutputStream = edit != null ? edit.newOutputStream(0) : null;
                                    byte[] bArr = new byte[4096];
                                    while (true) {
                                        int read = byteStream.read(bArr);
                                        if (read == -1) {
                                            break;
                                        }
                                        pipedOutputStream.write(bArr, 0, read);
                                        if (edit != null && newOutputStream != null) {
                                            newOutputStream.write(bArr, 0, read);
                                        }
                                    }
                                    if (edit != null && newOutputStream != null) {
                                        newOutputStream.close();
                                        edit.commit();
                                    }
                                    MsLogUtil.m7999d("UnicomSrcResourceIntercepter", "远程文件流写入结束 onResponse  线程：" + Thread.currentThread().getName() + " " + uri);
                                } else {
                                    edit.abort();
                                    MsLogUtil.m7999d("UnicomSrcResourceIntercepter", "请求远程文件报错 onResponse " + response.isSuccessful() + " 线程：" + Thread.currentThread().getName() + " " + uri);
                                }
                                UnicomSrcResourceIntercepter.this.mDiskLruCache.flush();
                                pipedOutputStream2 = pipedOutputStream;
                            } catch (Exception e) {
                                e.printStackTrace();
                                MsLogUtil.m7999d("UnicomSrcResourceIntercepter", "请求远程文件报错 onResponse catch " + e.getMessage() + " " + uri);
                                edit.abort();
                                UnicomSrcResourceIntercepter.this.mDiskLruCache.flush();
                                pipedOutputStream2 = pipedOutputStream;
                            }
                            pipedOutputStream2.close();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            MsLogUtil.m7999d("UnicomSrcResourceIntercepter", "请求远程文件报错 onResponse catch2 " + e2.getMessage() + " " + uri);
                        }
                    }
                });
                WebResourceResponse webResourceResponse2 = new WebResourceResponse(getMimeTypeFromFileName(replace), "UTF-8", pipedInputStream);
                HashMap hashMap2 = new HashMap();
                hashMap2.put("access-control-allow-origin", "*");
                webResourceResponse2.setResponseHeaders(hashMap2);
                return webResourceResponse2;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7999d("UnicomSrcResourceIntercepter", "错误：" + e.getMessage() + " " + uri);
            return null;
        }
    }

    private String getMimeTypeFromFileName(String str) {
        return str.endsWith(".js") ? "application/javascript" : str.endsWith(".css") ? "text/css" : str.endsWith(".gif") ? "image/gif" : str.endsWith(".jpeg") ? "image/jpeg" : str.endsWith(".png") ? "image/png" : "*/*";
    }

    public File createResourceCacheDir() {
        return ManagerWebCacheDictionary.getWebviewResourceIntercept_Normal_Dic(true);
    }

    private String getFileNameFromUrl(String str) {
        return Md5FileNameGenerator.generate(str);
    }

    private int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void clearAllCache() {
        try {
            this.mDiskLruCache.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
