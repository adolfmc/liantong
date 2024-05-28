package org.bouncycastle.pqc.crypto.lms;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.bouncycastle.util.Encodable;
import org.bouncycastle.util.p466io.Streams;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class LMSSignature implements Encodable {
    private final LMOtsSignature otsSignature;
    private final LMSigParameters parameter;

    /* renamed from: q */
    private final int f27210q;

    /* renamed from: y */
    private final byte[][] f27211y;

    public LMSSignature(int i, LMOtsSignature lMOtsSignature, LMSigParameters lMSigParameters, byte[][] bArr) {
        this.f27210q = i;
        this.otsSignature = lMOtsSignature;
        this.parameter = lMSigParameters;
        this.f27211y = bArr;
    }

    public static LMSSignature getInstance(Object obj) throws IOException {
        DataInputStream dataInputStream;
        if (obj instanceof LMSSignature) {
            return (LMSSignature) obj;
        }
        if (obj instanceof DataInputStream) {
            DataInputStream dataInputStream2 = (DataInputStream) obj;
            int readInt = dataInputStream2.readInt();
            LMOtsSignature lMOtsSignature = LMOtsSignature.getInstance(obj);
            LMSigParameters parametersForType = LMSigParameters.getParametersForType(dataInputStream2.readInt());
            byte[][] bArr = new byte[parametersForType.getH()];
            for (int i = 0; i < bArr.length; i++) {
                bArr[i] = new byte[parametersForType.getM()];
                dataInputStream2.readFully(bArr[i]);
            }
            return new LMSSignature(readInt, lMOtsSignature, parametersForType, bArr);
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
                LMSSignature lMSSignature = getInstance(dataInputStream);
                dataInputStream.close();
                return lMSSignature;
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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LMSSignature lMSSignature = (LMSSignature) obj;
        if (this.f27210q != lMSSignature.f27210q) {
            return false;
        }
        LMOtsSignature lMOtsSignature = this.otsSignature;
        if (lMOtsSignature == null ? lMSSignature.otsSignature == null : lMOtsSignature.equals(lMSSignature.otsSignature)) {
            LMSigParameters lMSigParameters = this.parameter;
            if (lMSigParameters == null ? lMSSignature.parameter == null : lMSigParameters.equals(lMSSignature.parameter)) {
                return Arrays.deepEquals(this.f27211y, lMSSignature.f27211y);
            }
            return false;
        }
        return false;
    }

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() throws IOException {
        return Composer.compose().u32str(this.f27210q).bytes(this.otsSignature.getEncoded()).u32str(this.parameter.getType()).bytes(this.f27211y).build();
    }

    public LMOtsSignature getOtsSignature() {
        return this.otsSignature;
    }

    public LMSigParameters getParameter() {
        return this.parameter;
    }

    public int getQ() {
        return this.f27210q;
    }

    public byte[][] getY() {
        return this.f27211y;
    }

    public int hashCode() {
        int i = this.f27210q * 31;
        LMOtsSignature lMOtsSignature = this.otsSignature;
        int hashCode = (i + (lMOtsSignature != null ? lMOtsSignature.hashCode() : 0)) * 31;
        LMSigParameters lMSigParameters = this.parameter;
        return ((hashCode + (lMSigParameters != null ? lMSigParameters.hashCode() : 0)) * 31) + Arrays.deepHashCode(this.f27211y);
    }
}
