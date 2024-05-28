package com.chinaunicon.jtwifilib.jtcommon.testspeed.request;

import android.util.Log;
import com.chinaunicon.jtwifilib.core.utils.JtL;
import com.chinaunicon.jtwifilib.jtcommon.OnSpeedListener;
import com.networkbench.agent.impl.instrumentation.NBSInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class HttpUtils {
    private OkHttpClient okClient;
    long startTime = 0;
    long endTime = 0;
    double downloadElapsedTime = 0.0d;
    int downloadedByte = 0;
    boolean isFrist = true;

    public HttpUtils(OkHttpClient okHttpClient) {
        this.okClient = okHttpClient;
    }

    public void downloadFile(int i, String str, OnSpeedListener onSpeedListener) throws Exception {
        try {
            Response execute = this.okClient.newCall(new Request.Builder().url(str).build()).execute();
            if (execute.isSuccessful()) {
                Log.i("测速", "执行下载成功，开始读取文件流...");
                byte[] bArr = new byte[2048];
                InputStream byteStream = execute.body().byteStream();
                if (this.isFrist) {
                    this.startTime = System.currentTimeMillis();
                    this.isFrist = false;
                }
                while (true) {
                    int read = byteStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    this.downloadedByte += read;
                    this.endTime = System.currentTimeMillis();
                    this.downloadElapsedTime = (this.endTime - this.startTime) / 1000.0d;
                    float instantDownloadRate = setInstantDownloadRate(this.downloadedByte, this.downloadElapsedTime);
                    if (onSpeedListener != null) {
                        onSpeedListener.onSpeed(i, instantDownloadRate);
                    }
                }
                if (onSpeedListener != null) {
                    onSpeedListener.onSpeed(i, 0.0f);
                }
                JtL.m16342e("下载完成！");
                byteStream.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public float setInstantDownloadRate(double d, double d2) {
        if (d >= 0.0d) {
            return round(Double.valueOf(((d * 8.0d) / 1000000.0d) / d2).doubleValue(), 2);
        }
        return 0.0f;
    }

    private float round(double d, int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        try {
            return new BigDecimal(d).setScale(i, RoundingMode.HALF_UP).floatValue();
        } catch (Exception unused) {
            return 0.0f;
        }
    }

    public void downloadAPK(int i, String str, OnSpeedListener onSpeedListener) throws Exception {
        JtL.m16342e("下载地址：" + str);
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                Thread.currentThread().setPriority(1);
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) NBSInstrumentation.openConnection(new URL(str).openConnection());
                try {
                    httpURLConnection2.setConnectTimeout(15000);
                    httpURLConnection2.setReadTimeout(60000);
                    httpURLConnection2.setDoInput(true);
                    InputStream inputStream = httpURLConnection2.getInputStream();
                    byte[] bArr = new byte[8192];
                    try {
                        if (this.isFrist) {
                            this.startTime = System.currentTimeMillis();
                            this.isFrist = false;
                        }
                        while (true) {
                            int read = inputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            this.downloadedByte += read;
                            this.endTime = System.currentTimeMillis();
                            this.downloadElapsedTime = (this.endTime - this.startTime) / 1000.0d;
                            onSpeedListener.onSpeed(i, setInstantDownloadRate(this.downloadedByte, this.downloadElapsedTime));
                        }
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                    } finally {
                        closeSilently(inputStream);
                    }
                } catch (Exception e) {
                    e = e;
                    Log.w("downloadAPK", e);
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    httpURLConnection = httpURLConnection2;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static final void closeSilently(Object obj) {
        if (obj != null) {
            try {
                if (obj instanceof Closeable) {
                    ((Closeable) obj).close();
                } else if (obj instanceof Socket) {
                    ((Socket) obj).close();
                } else if (obj instanceof ServerSocket) {
                    ((ServerSocket) obj).close();
                }
            } catch (IOException unused) {
            }
        }
    }
}
