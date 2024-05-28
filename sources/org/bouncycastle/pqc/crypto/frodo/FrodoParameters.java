package org.bouncycastle.pqc.crypto.frodo;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.pqc.crypto.frodo.FrodoMatrixGenerator;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class FrodoParameters implements CipherParameters {

    /* renamed from: B */
    private final int f27164B;

    /* renamed from: D */
    private final int f27165D;
    private final int defaultKeySize;
    private final FrodoEngine engine;

    /* renamed from: n */
    private final int f27166n;
    private final String name;
    private static final short[] cdf_table640 = {4643, 13363, 20579, 25843, 29227, 31145, 32103, 32525, 32689, 32745, 32762, 32766, Short.MAX_VALUE};
    private static final short[] cdf_table976 = {5638, 15915, 23689, 28571, 31116, 32217, 32613, 32731, 32760, 32766, Short.MAX_VALUE};
    private static final short[] cdf_table1344 = {9142, 23462, 30338, 32361, 32725, 32765, Short.MAX_VALUE};
    public static final FrodoParameters frodokem640aes = new FrodoParameters("frodokem640aes", 640, 15, 2, cdf_table640, new SHAKEDigest(128), new FrodoMatrixGenerator.Aes128MatrixGenerator(640, 32768));
    public static final FrodoParameters frodokem640shake = new FrodoParameters("frodokem640shake", 640, 15, 2, cdf_table640, new SHAKEDigest(128), new FrodoMatrixGenerator.Shake128MatrixGenerator(640, 32768));
    public static final FrodoParameters frodokem976aes = new FrodoParameters("frodokem976aes", 976, 16, 3, cdf_table976, new SHAKEDigest(256), new FrodoMatrixGenerator.Aes128MatrixGenerator(976, 65536));
    public static final FrodoParameters frodokem976shake = new FrodoParameters("frodokem976shake", 976, 16, 3, cdf_table976, new SHAKEDigest(256), new FrodoMatrixGenerator.Shake128MatrixGenerator(976, 65536));
    public static final FrodoParameters frodokem1344aes = new FrodoParameters("frodokem1344aes", 1344, 16, 4, cdf_table1344, new SHAKEDigest(256), new FrodoMatrixGenerator.Aes128MatrixGenerator(1344, 65536));
    public static final FrodoParameters frodokem1344shake = new FrodoParameters("frodokem1344shake", 1344, 16, 4, cdf_table1344, new SHAKEDigest(256), new FrodoMatrixGenerator.Shake128MatrixGenerator(1344, 65536));

    private FrodoParameters(String str, int i, int i2, int i3, short[] sArr, Xof xof, FrodoMatrixGenerator frodoMatrixGenerator) {
        this.name = str;
        this.f27166n = i;
        this.f27165D = i2;
        this.f27164B = i3;
        this.defaultKeySize = i3 * 8 * 8;
        this.engine = new FrodoEngine(i, i2, i3, sArr, xof, frodoMatrixGenerator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getB() {
        return this.f27164B;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getD() {
        return this.f27165D;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FrodoEngine getEngine() {
        return this.engine;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getN() {
        return this.f27166n;
    }

    public String getName() {
        return this.name;
    }

    public int getSessionKeySize() {
        return this.defaultKeySize;
    }
}
