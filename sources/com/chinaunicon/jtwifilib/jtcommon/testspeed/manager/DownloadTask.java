package com.chinaunicon.jtwifilib.jtcommon.testspeed.manager;

import com.chinaunicon.jtwifilib.jtcommon.OnSpeedListener;
import com.chinaunicon.jtwifilib.jtcommon.testspeed.request.HttpUtils;
import okhttp3.OkHttpClient;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DownloadTask implements Runnable {
    private int code;
    private String downUrl;
    private OkHttpClient okClient;
    private OnSpeedListener onSpeedListener;

    public DownloadTask(int i, String str, OnSpeedListener onSpeedListener, OkHttpClient okHttpClient) {
        this.code = i;
        this.downUrl = str;
        this.onSpeedListener = onSpeedListener;
        this.okClient = okHttpClient;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            new HttpUtils(this.okClient).downloadAPK(this.code, this.downUrl, this.onSpeedListener);
        } catch (Exception unused) {
            OnSpeedListener onSpeedListener = this.onSpeedListener;
            if (onSpeedListener != null) {
                onSpeedListener.onFiled(this.code, this.downUrl);
            }
        }
    }
}
