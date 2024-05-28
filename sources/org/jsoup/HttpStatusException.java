package org.jsoup;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class HttpStatusException extends IOException {
    private int statusCode;
    private String url;

    public HttpStatusException(String str, int i, String str2) {
        super(str);
        this.statusCode = i;
        this.url = str2;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getUrl() {
        return this.url;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return super.toString() + ". Status=" + this.statusCode + ", URL=" + this.url;
    }
}
