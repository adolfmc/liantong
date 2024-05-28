package com.bytedance.pangle.p180g;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

/* renamed from: com.bytedance.pangle.g.p */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
final class C3861p extends C3863r {

    /* renamed from: a */
    private final byte[] f9211a;

    /* renamed from: b */
    private int f9212b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C3861p(X509Certificate x509Certificate, byte[] bArr) {
        super(x509Certificate);
        this.f9212b = -1;
        this.f9211a = bArr;
    }

    @Override // com.bytedance.pangle.p180g.C3863r, java.security.cert.Certificate
    public final byte[] getEncoded() {
        return this.f9211a;
    }

    @Override // java.security.cert.Certificate
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C3861p) {
            try {
                return Arrays.equals(getEncoded(), ((C3861p) obj).getEncoded());
            } catch (CertificateEncodingException unused) {
                return false;
            }
        }
        return false;
    }

    @Override // java.security.cert.Certificate
    public final int hashCode() {
        if (this.f9212b == -1) {
            try {
                this.f9212b = Arrays.hashCode(getEncoded());
            } catch (CertificateEncodingException unused) {
                this.f9212b = 0;
            }
        }
        return this.f9212b;
    }
}
