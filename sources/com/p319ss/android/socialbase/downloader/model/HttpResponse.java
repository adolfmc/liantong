package com.p319ss.android.socialbase.downloader.model;

import android.text.TextUtils;
import com.p319ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection;
import com.p319ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.IOException;

/* renamed from: com.ss.android.socialbase.downloader.model.HttpResponse */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class HttpResponse {
    public final IDownloadHeadHttpConnection connection;
    private long contentLength;
    public final int responseCode;
    private long totalLength;
    public final String url;

    public HttpResponse(String str, IDownloadHeadHttpConnection iDownloadHeadHttpConnection) throws IOException {
        this.url = str;
        this.responseCode = iDownloadHeadHttpConnection.getResponseCode();
        this.connection = iDownloadHeadHttpConnection;
    }

    public boolean isResponseDataFromBegin() {
        return DownloadUtils.isResponseDataFromBegin(this.responseCode);
    }

    public boolean acceptPartial() {
        return DownloadUtils.canAcceptPartial(this.responseCode, this.connection.getResponseHeaderField("Accept-Ranges"));
    }

    public String getEtag() {
        return this.connection.getResponseHeaderField("Etag");
    }

    public String getContentType() {
        return this.connection.getResponseHeaderField("Content-Type");
    }

    public String getContentRange() {
        return DownloadUtils.getRespHeadFieldIgnoreCase(this.connection, "Content-Range");
    }

    public String getLastModified() {
        String respHeadFieldIgnoreCase = DownloadUtils.getRespHeadFieldIgnoreCase(this.connection, "last-modified");
        return TextUtils.isEmpty(respHeadFieldIgnoreCase) ? DownloadUtils.getRespHeadFieldIgnoreCase(this.connection, "Last-Modified") : respHeadFieldIgnoreCase;
    }

    public String getCacheControl() {
        return DownloadUtils.getRespHeadFieldIgnoreCase(this.connection, "Cache-Control");
    }

    public long getContentLength() {
        if (this.contentLength <= 0) {
            this.contentLength = DownloadUtils.getContentLength(this.connection);
        }
        return this.contentLength;
    }

    public boolean isChunked() {
        if (DownloadExpSwitchCode.isSwitchEnable(8)) {
            return DownloadUtils.isChunkedTask(this.connection);
        }
        return DownloadUtils.isChunkedTask(getContentLength());
    }

    public long getTotalLength() {
        if (this.totalLength <= 0) {
            if (!isChunked()) {
                String contentRange = getContentRange();
                if (!TextUtils.isEmpty(contentRange)) {
                    this.totalLength = DownloadUtils.parseContentRangeOfInstanceLength(contentRange);
                }
            } else {
                this.totalLength = -1L;
            }
        }
        return this.totalLength;
    }

    public long getMaxAge() {
        return DownloadUtils.parserMaxAge(getCacheControl());
    }
}
