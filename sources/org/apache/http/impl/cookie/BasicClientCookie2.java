package org.apache.http.impl.cookie;

import java.util.Date;
import org.apache.http.cookie.SetCookie2;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class BasicClientCookie2 extends BasicClientCookie implements SetCookie2 {
    private String commentURL;
    private boolean discard;
    private int[] ports;

    public BasicClientCookie2(String str, String str2) {
        super(str, str2);
    }

    @Override // org.apache.http.impl.cookie.BasicClientCookie, org.apache.http.cookie.Cookie
    public int[] getPorts() {
        return this.ports;
    }

    @Override // org.apache.http.cookie.SetCookie2
    public void setPorts(int[] iArr) {
        this.ports = iArr;
    }

    @Override // org.apache.http.impl.cookie.BasicClientCookie, org.apache.http.cookie.Cookie
    public String getCommentURL() {
        return this.commentURL;
    }

    @Override // org.apache.http.cookie.SetCookie2
    public void setCommentURL(String str) {
        this.commentURL = str;
    }

    @Override // org.apache.http.cookie.SetCookie2
    public void setDiscard(boolean z) {
        this.discard = z;
    }

    @Override // org.apache.http.impl.cookie.BasicClientCookie, org.apache.http.cookie.Cookie
    public boolean isPersistent() {
        return !this.discard && super.isPersistent();
    }

    @Override // org.apache.http.impl.cookie.BasicClientCookie, org.apache.http.cookie.Cookie
    public boolean isExpired(Date date) {
        return this.discard || super.isExpired(date);
    }

    @Override // org.apache.http.impl.cookie.BasicClientCookie
    public Object clone() throws CloneNotSupportedException {
        BasicClientCookie2 basicClientCookie2 = (BasicClientCookie2) super.clone();
        basicClientCookie2.ports = (int[]) this.ports.clone();
        return basicClientCookie2;
    }
}
