package org.bouncycastle.pqc.crypto.lms;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Encodable;
import org.bouncycastle.util.p466io.Streams;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class LMOtsPublicKey implements Encodable {

    /* renamed from: I */
    private final byte[] f27199I;

    /* renamed from: K */
    private final byte[] f27200K;
    private final LMOtsParameters parameter;

    /* renamed from: q */
    private final int f27201q;

    public LMOtsPublicKey(LMOtsParameters lMOtsParameters, byte[] bArr, int i, byte[] bArr2) {
        this.parameter = lMOtsParameters;
        this.f27199I = bArr;
        this.f27201q = i;
        this.f27200K = bArr2;
    }

    public static LMOtsPublicKey getInstance(Object obj) throws Exception {
        DataInputStream dataInputStream;
        if (obj instanceof LMOtsPublicKey) {
            return (LMOtsPublicKey) obj;
        }
        if (obj instanceof DataInputStream) {
            DataInputStream dataInputStream2 = (DataInputStream) obj;
            LMOtsParameters parametersForType = LMOtsParameters.getParametersForType(dataInputStream2.readInt());
            byte[] bArr = new byte[16];
            dataInputStream2.readFully(bArr);
            int readInt = dataInputStream2.readInt();
            byte[] bArr2 = new byte[parametersForType.getN()];
            dataInputStream2.readFully(bArr2);
            return new LMOtsPublicKey(parametersForType, bArr, readInt, bArr2);
        } else if (!(obj instanceof byte[])) {
            if (obj instanceof InputStream) {
                return getInstance(Streams.readAll((InputStream) obj));
            }
            throw new IllegalArgumentException("cannot parse " + obj);
        } else {
            DataInputStream dataInputStream3 = null;
            try {
                dataInputStream = new DataInputStream(new ByteArrayInputStream((byte[]) obj));
            } catch (Throwable th) {
                th = th;
            }
            try {
                LMOtsPublicKey lMOtsPublicKey = getInstance(dataInputStream);
                dataInputStream.close();
                return lMOtsPublicKey;
            } catch (Throwable th2) {
                th = th2;
                dataInputStream3 = dataInputStream;
                if (dataInputStream3 != null) {
                    dataInputStream3.close();
                }
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LMSContext createOtsContext(LMOtsSignature lMOtsSignature) {
        Digest digest = DigestUtil.getDigest(this.parameter.getDigestOID());
        LmsUtils.byteArray(this.f27199I, digest);
        LmsUtils.u32str(this.f27201q, digest);
        LmsUtils.u16str((short) -32383, digest);
        LmsUtils.byteArray(lMOtsSignature.getC(), digest);
        return new LMSContext(this, lMOtsSignature, digest);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LMSContext createOtsContext(LMSSignature lMSSignature) {
        Digest digest = DigestUtil.getDigest(this.parameter.getDigestOID());
        LmsUtils.byteArray(this.f27199I, digest);
        LmsUtils.u32str(this.f27201q, digest);
        LmsUtils.u16str((short) -32383, digest);
        LmsUtils.byteArray(lMSSignature.getOtsSignature().getC(), digest);
        return new LMSContext(this, lMSSignature, digest);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LMOtsPublicKey lMOtsPublicKey = (LMOtsPublicKey) obj;
        if (this.f27201q != lMOtsPublicKey.f27201q) {
            return false;
        }
        LMOtsParameters lMOtsParameters = this.parameter;
        if (lMOtsParameters == null ? lMOtsPublicKey.parameter == null : lMOtsParameters.equals(lMOtsPublicKey.parameter)) {
            if (Arrays.equals(this.f27199I, lMOtsPublicKey.f27199I)) {
                return Arrays.equals(this.f27200K, lMOtsPublicKey.f27200K);
            }
            return false;
        }
        return false;
    }

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() throws IOException {
        return Composer.compose().u32str(this.parameter.getType()).bytes(this.f27199I).u32str(this.f27201q).bytes(this.f27200K).build();
    }

    public byte[] getI() {
        return this.f27199I;
    }

    public byte[] getK() {
        return this.f27200K;
    }

    public LMOtsParameters getParameter() {
        return this.parameter;
    }

    public int getQ() {
        return this.f27201q;
    }

    public int hashCode() {
        LMOtsParameters lMOtsParameters = this.parameter;
        return ((((((lMOtsParameters != null ? lMOtsParameters.hashCode() : 0) * 31) + Arrays.hashCode(this.f27199I)) * 31) + this.f27201q) * 31) + Arrays.hashCode(this.f27200K);
    }
}
