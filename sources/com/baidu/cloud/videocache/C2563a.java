package com.baidu.cloud.videocache;

import android.text.TextUtils;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Locale;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.cloud.videocache.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2563a extends C2572h {

    /* renamed from: a */
    protected final com.baidu.cloud.videocache.file.oia f4852a;

    /* renamed from: e */
    private final C2564b f4853e;

    /* renamed from: f */
    private CacheListener f4854f;

    public C2563a(C2564b c2564b, com.baidu.cloud.videocache.file.oia oiaVar) {
        super(c2564b, oiaVar);
        this.f4852a = oiaVar;
        this.f4853e = c2564b;
    }

    /* renamed from: a */
    protected String m19846a(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.baidu.cloud.videocache.C2572h
    /* renamed from: a */
    public void mo19801a(int i) {
        CacheListener cacheListener = this.f4854f;
        if (cacheListener != null) {
            cacheListener.onCacheAvailable(this.f4852a.f4880a, this.f4853e.m19833b(), i);
        }
    }

    /* renamed from: a */
    public void mo19844a(CacheListener cacheListener) {
        this.f4854f = cacheListener;
    }

    /* renamed from: a */
    public void mo19843a(nxb nxbVar, Socket socket) {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        bufferedOutputStream.write(m19845b(nxbVar).getBytes("UTF-8"));
        long j = nxbVar.f4903b;
        if (m19847a(nxbVar)) {
            mo19842a(bufferedOutputStream, j);
        } else {
            mo19841b(bufferedOutputStream, j);
        }
    }

    /* renamed from: a */
    protected void mo19842a(OutputStream outputStream, long j) {
        byte[] bArr = new byte[8192];
        while (true) {
            int a = mo19797a(bArr, j, bArr.length);
            if (a == -1) {
                outputStream.flush();
                return;
            } else {
                outputStream.write(bArr, 0, a);
                j += a;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public boolean m19847a(nxb nxbVar) {
        long length = this.f4853e.length();
        return (((length > 0L ? 1 : (length == 0L ? 0 : -1)) > 0) && nxbVar.f4904c && ((float) nxbVar.f4903b) > ((float) this.f4852a.available()) + (((float) length) * 0.2f)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public String m19845b(nxb nxbVar) {
        String m19839a = this.f4853e.m19839a();
        boolean z = !TextUtils.isEmpty(m19839a);
        long available = this.f4852a.isCompleted() ? this.f4852a.available() : this.f4853e.length();
        boolean z2 = available >= 0;
        long j = nxbVar.f4904c ? available - nxbVar.f4903b : available;
        boolean z3 = z2 && nxbVar.f4904c;
        StringBuilder sb = new StringBuilder();
        sb.append(nxbVar.f4904c ? "HTTP/1.1 206 PARTIAL CONTENT\n" : "HTTP/1.1 200 OK\n");
        sb.append("Accept-Ranges: bytes\n");
        sb.append(z2 ? m19846a("Content-Length: %d\n", Long.valueOf(j)) : "");
        sb.append(z3 ? m19846a("Content-Range: bytes %d-%d/%d\n", Long.valueOf(nxbVar.f4903b), Long.valueOf(available - 1), Long.valueOf(available)) : "");
        sb.append(z ? m19846a("Content-Type: %s\n", m19839a) : "");
        sb.append("\n");
        return sb.toString();
    }

    /* renamed from: b */
    protected void mo19841b(OutputStream outputStream, long j) {
        C2564b c2564b = new C2564b(this.f4853e);
        try {
            c2564b.open((int) j);
            byte[] bArr = new byte[8192];
            while (true) {
                int read = c2564b.read(bArr);
                if (read == -1) {
                    outputStream.flush();
                    return;
                }
                outputStream.write(bArr, 0, read);
            }
        } finally {
            c2564b.close();
        }
    }
}
