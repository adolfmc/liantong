package com.mob.tools.network;

import com.mob.tools.utils.Data;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@Deprecated
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ByteArrayPart extends HTTPPart {

    /* renamed from: a */
    private ByteArrayOutputStream f14993a;

    public ByteArrayPart append(byte[] bArr) throws Throwable {
        if (this.f14993a == null) {
            this.f14993a = new ByteArrayOutputStream(bArr.length);
        }
        this.f14993a.write(bArr);
        this.f14993a.flush();
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.mob.tools.network.HTTPPart
    /* renamed from: a */
    public InputStream mo11311a() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream = this.f14993a;
        if (byteArrayOutputStream == null) {
            return new ByteArrayInputStream(new byte[0]);
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (byteArray == null || this.f14993a.size() <= 0) {
            return new ByteArrayInputStream(new byte[0]);
        }
        return new ByteArrayInputStream(byteArray, 0, this.f14993a.size());
    }

    public String toString() {
        byte[] byteArray;
        ByteArrayOutputStream byteArrayOutputStream = this.f14993a;
        if (byteArrayOutputStream == null || (byteArray = byteArrayOutputStream.toByteArray()) == null) {
            return null;
        }
        return Data.byteToHex(byteArray, 0, this.f14993a.size());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.mob.tools.network.HTTPPart
    /* renamed from: b */
    public long mo11310b() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream = this.f14993a;
        if (byteArrayOutputStream == null) {
            return 0L;
        }
        return byteArrayOutputStream.size();
    }
}
