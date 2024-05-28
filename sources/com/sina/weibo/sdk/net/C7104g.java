package com.sina.weibo.sdk.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sina.weibo.sdk.net.g */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C7104g implements InterfaceC7103f {
    private int code;

    /* renamed from: p */
    private InputStream f18325p;

    public C7104g(int i, InputStream inputStream) {
        this.code = i;
        this.f18325p = inputStream;
    }

    @Override // com.sina.weibo.sdk.net.InterfaceC7103f
    /* renamed from: f */
    public final String mo8050f() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = this.f18325p.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    String byteArrayOutputStream2 = byteArrayOutputStream.toString();
                    byteArrayOutputStream.close();
                    return byteArrayOutputStream2;
                }
            }
        } catch (IOException e) {
            throw e;
        }
    }
}
