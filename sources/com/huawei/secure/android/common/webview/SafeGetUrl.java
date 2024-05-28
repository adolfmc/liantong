package com.huawei.secure.android.common.webview;

import android.util.Log;
import android.webkit.WebView;
import com.huawei.secure.android.common.util.C5125b;
import com.huawei.secure.android.common.util.C5126c;
import java.util.concurrent.CountDownLatch;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SafeGetUrl {

    /* renamed from: c */
    private static final String f12171c = "SafeGetUrl";

    /* renamed from: d */
    private static final long f12172d = 200;

    /* renamed from: a */
    private String f12173a;

    /* renamed from: b */
    private WebView f12174b;

    /* renamed from: com.huawei.secure.android.common.webview.SafeGetUrl$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class RunnableC5128a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ CountDownLatch f12175a;

        RunnableC5128a(CountDownLatch countDownLatch) {
            this.f12175a = countDownLatch;
        }

        @Override // java.lang.Runnable
        public void run() {
            SafeGetUrl safeGetUrl = SafeGetUrl.this;
            safeGetUrl.setUrl(safeGetUrl.f12174b.getUrl());
            this.f12175a.countDown();
        }
    }

    public SafeGetUrl() {
    }

    public String getUrlMethod() {
        if (this.f12174b == null) {
            return "";
        }
        if (C5125b.m13768a()) {
            return this.f12174b.getUrl();
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        C5126c.m13767a(new RunnableC5128a(countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            Log.e(f12171c, "getUrlMethod: InterruptedException " + e.getMessage(), e);
        }
        return this.f12173a;
    }

    public WebView getWebView() {
        return this.f12174b;
    }

    public void setUrl(String str) {
        this.f12173a = str;
    }

    public void setWebView(WebView webView) {
        this.f12174b = webView;
    }

    public SafeGetUrl(WebView webView) {
        this.f12174b = webView;
    }
}
