package com.tydic.tydic_tracker.tyWebview;

import android.net.Uri;
import android.support.annotation.RequiresApi;
import android.webkit.WebResourceRequest;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@RequiresApi(api = 21)
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WriteHandlingWebResourceRequest implements WebResourceRequest {
    private final WebResourceRequest originalWebResourceRequest;
    private final String requestBody;
    private final Uri uri;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WriteHandlingWebResourceRequest(WebResourceRequest webResourceRequest, String str, Uri uri) {
        this.originalWebResourceRequest = webResourceRequest;
        this.requestBody = str;
        if (uri != null) {
            this.uri = uri;
        } else {
            this.uri = webResourceRequest.getUrl();
        }
    }

    @Override // android.webkit.WebResourceRequest
    public Uri getUrl() {
        return this.uri;
    }

    @Override // android.webkit.WebResourceRequest
    public boolean isForMainFrame() {
        return this.originalWebResourceRequest.isForMainFrame();
    }

    @Override // android.webkit.WebResourceRequest
    public boolean isRedirect() {
        throw new UnsupportedOperationException();
    }

    @Override // android.webkit.WebResourceRequest
    public boolean hasGesture() {
        return this.originalWebResourceRequest.hasGesture();
    }

    @Override // android.webkit.WebResourceRequest
    public String getMethod() {
        return this.originalWebResourceRequest.getMethod();
    }

    @Override // android.webkit.WebResourceRequest
    public Map<String, String> getRequestHeaders() {
        return this.originalWebResourceRequest.getRequestHeaders();
    }

    public String getAjaxData() {
        return this.requestBody;
    }

    public boolean hasAjaxData() {
        return this.requestBody != null;
    }
}
