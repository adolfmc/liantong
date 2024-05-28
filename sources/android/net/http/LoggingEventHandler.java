package android.net.http;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\452516_dexfile_execute.dex */
public class LoggingEventHandler implements EventHandler {
    public void requestSent() {
        HttpLog.m22213v("LoggingEventHandler:requestSent()");
    }

    @Override // android.net.http.EventHandler
    public void status(int i, int i2, int i3, String str) {
    }

    @Override // android.net.http.EventHandler
    public void headers(Headers headers) {
    }

    public void locationChanged(String str, boolean z) {
    }

    @Override // android.net.http.EventHandler
    public void data(byte[] bArr, int i) {
    }

    @Override // android.net.http.EventHandler
    public void endData() {
    }

    @Override // android.net.http.EventHandler
    public void certificate(SslCertificate sslCertificate) {
    }

    @Override // android.net.http.EventHandler
    public void error(int i, String str) {
    }

    @Override // android.net.http.EventHandler
    public boolean handleSslErrorRequest(SslError sslError) {
        return false;
    }
}
