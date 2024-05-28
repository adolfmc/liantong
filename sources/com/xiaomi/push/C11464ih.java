package com.xiaomi.push;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: com.xiaomi.push.ih */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11464ih extends AbstractC11467ik {

    /* renamed from: a */
    protected InputStream f23335a;

    /* renamed from: a */
    protected OutputStream f23336a;

    protected C11464ih() {
        this.f23335a = null;
        this.f23336a = null;
    }

    public C11464ih(OutputStream outputStream) {
        this.f23335a = null;
        this.f23336a = null;
        this.f23336a = outputStream;
    }

    @Override // com.xiaomi.push.AbstractC11467ik
    /* renamed from: a */
    public int mo2982a(byte[] bArr, int i, int i2) {
        InputStream inputStream = this.f23335a;
        if (inputStream == null) {
            throw new C11468il(1, "Cannot read from null inputStream");
        }
        try {
            int read = inputStream.read(bArr, i, i2);
            if (read >= 0) {
                return read;
            }
            throw new C11468il(4);
        } catch (IOException e) {
            throw new C11468il(0, e);
        }
    }

    @Override // com.xiaomi.push.AbstractC11467ik
    /* renamed from: a */
    public void mo2981a(byte[] bArr, int i, int i2) {
        OutputStream outputStream = this.f23336a;
        if (outputStream == null) {
            throw new C11468il(1, "Cannot write to null outputStream");
        }
        try {
            outputStream.write(bArr, i, i2);
        } catch (IOException e) {
            throw new C11468il(0, e);
        }
    }
}
