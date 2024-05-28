package org.bouncycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SM2ParameterSpec implements AlgorithmParameterSpec {

    /* renamed from: id */
    private byte[] f26899id;

    public SM2ParameterSpec(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("id string cannot be null");
        }
        this.f26899id = Arrays.clone(bArr);
    }

    public byte[] getID() {
        return Arrays.clone(this.f26899id);
    }
}
