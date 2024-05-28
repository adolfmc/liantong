package com.xiaomi.push;

import java.io.IOException;

/* renamed from: com.xiaomi.push.e */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC11313e {
    /* renamed from: a */
    public abstract int mo4063a();

    /* renamed from: a */
    public abstract AbstractC11313e mo4061a(C11182b c11182b);

    /* renamed from: a */
    public abstract void mo4059a(C11224c c11224c);

    /* renamed from: b */
    public abstract int mo4055b();

    /* renamed from: a */
    public byte[] m4062a() {
        byte[] bArr = new byte[mo4055b()];
        m4056a(bArr, 0, bArr.length);
        return bArr;
    }

    /* renamed from: a */
    public void m4056a(byte[] bArr, int i, int i2) {
        try {
            C11224c m4628a = C11224c.m4628a(bArr, i, i2);
            mo4059a(m4628a);
            m4628a.m4626b();
        } catch (IOException unused) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).");
        }
    }

    /* renamed from: a */
    public AbstractC11313e m4058a(byte[] bArr) {
        return m4057a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public AbstractC11313e m4057a(byte[] bArr, int i, int i2) {
        try {
            C11182b m4774a = C11182b.m4774a(bArr, i, i2);
            mo4061a(m4774a);
            m4774a.m4780a(0);
            return this;
        } catch (C11264d e) {
            throw e;
        } catch (IOException unused) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public boolean m4060a(C11182b c11182b, int i) {
        return c11182b.m4779a(i);
    }
}
