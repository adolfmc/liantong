package com.baidu.cloud.videocache;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ass extends C2563a {

    /* renamed from: e */
    private static final Logger f4855e = LoggerFactory.getLogger("HlsProxyCache");

    /* renamed from: f */
    private uwb f4856f;

    /* renamed from: g */
    private nxb f4857g;

    public ass(uwb uwbVar, com.baidu.cloud.videocache.file.oia oiaVar) {
        super(uwbVar, oiaVar);
        this.f4856f = uwbVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0086, code lost:
        r7 = r7 + r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0088, code lost:
        m19800a(r7, r2);
     */
    /* JADX WARN: Finally extract failed */
    /* renamed from: g */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m19840g() {
        /*
            Method dump skipped, instructions count: 267
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.cloud.videocache.ass.m19840g():void");
    }

    @Override // com.baidu.cloud.videocache.C2572h
    /* renamed from: a */
    public /* bridge */ /* synthetic */ int mo19797a(byte[] bArr, long j, int i) {
        return super.mo19797a(bArr, j, i);
    }

    @Override // com.baidu.cloud.videocache.C2563a
    /* renamed from: a */
    public /* bridge */ /* synthetic */ void mo19844a(CacheListener cacheListener) {
        super.mo19844a(cacheListener);
    }

    @Override // com.baidu.cloud.videocache.C2563a
    /* renamed from: a */
    public void mo19843a(nxb nxbVar, Socket socket) {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        this.f4857g = nxbVar;
        Logger logger = f4855e;
        logger.info("Hls request: {\n" + nxbVar + "}");
        long j = nxbVar.f4903b;
        if (m19847a(nxbVar)) {
            mo19842a(bufferedOutputStream, j);
        } else {
            mo19841b(bufferedOutputStream, j);
        }
    }

    @Override // com.baidu.cloud.videocache.C2563a
    /* renamed from: a */
    protected void mo19842a(OutputStream outputStream, long j) {
        if (!this.f4852a.isCompleted() && !m19792e()) {
            m19840g();
        }
        if (this.f4852a.isCompleted() && this.f4887d != 100) {
            this.f4887d = 100;
            mo19801a(100);
        }
        byte[] bArr = new byte[8192];
        outputStream.write(m19845b(this.f4857g).getBytes("UTF-8"));
        while (true) {
            int read = this.f4852a.read(bArr, j, bArr.length);
            if (read == -1) {
                outputStream.flush();
                return;
            } else {
                outputStream.write(bArr, 0, read);
                j += read;
            }
        }
    }

    @Override // com.baidu.cloud.videocache.C2572h
    /* renamed from: a */
    public /* bridge */ /* synthetic */ boolean mo19802a() {
        return super.mo19802a();
    }

    @Override // com.baidu.cloud.videocache.C2572h
    /* renamed from: b */
    public /* bridge */ /* synthetic */ void mo19796b() {
        super.mo19796b();
    }

    @Override // com.baidu.cloud.videocache.C2563a
    /* renamed from: b */
    protected void mo19841b(OutputStream outputStream, long j) {
        uwb uwbVar = new uwb(this.f4856f);
        try {
            uwbVar.open((int) j);
            byte[] bArr = new byte[8192];
            byte[] bArr2 = new byte[(int) this.f4856f.length()];
            int i = 0;
            while (true) {
                int read = uwbVar.read(bArr);
                if (read == -1) {
                    byte[] m19730a = rwd.m19730a(bArr2, this.f4856f.m19833b());
                    this.f4856f.m19720a(m19730a.length);
                    outputStream.write(m19845b(this.f4857g).getBytes("UTF-8"));
                    outputStream.write(m19730a, 0, m19730a.length);
                    outputStream.flush();
                    f4855e.info("Read from source bytes " + i);
                    return;
                }
                System.arraycopy(bArr, 0, bArr2, i, read);
                i += read;
            }
        } finally {
            uwbVar.close();
        }
    }
}
