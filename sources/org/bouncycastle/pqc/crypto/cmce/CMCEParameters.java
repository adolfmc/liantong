package org.bouncycastle.pqc.crypto.cmce;

import org.bouncycastle.crypto.CipherParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CMCEParameters implements CipherParameters {
    private final int defaultKeySize;
    private final CMCEEngine engine;

    /* renamed from: m */
    private final int f27118m;

    /* renamed from: n */
    private final int f27119n;
    private final String name;

    /* renamed from: t */
    private final int f27120t;
    private final boolean usePivots;
    private static int[] poly3488 = {3, 1, 0};
    private static int[] poly4608 = {10, 9, 6, 0};
    private static int[] poly6688 = {7, 2, 1, 0};
    private static int[] poly6960 = {8, 0};
    private static int[] poly8192 = {7, 2, 1, 0};
    public static final CMCEParameters mceliece348864r3 = new CMCEParameters("mceliece348864", 12, 3488, 64, poly3488, false, 128);
    public static final CMCEParameters mceliece348864fr3 = new CMCEParameters("mceliece348864f", 12, 3488, 64, poly3488, true, 128);
    public static final CMCEParameters mceliece460896r3 = new CMCEParameters("mceliece460896", 13, 4608, 96, poly4608, false, 192);
    public static final CMCEParameters mceliece460896fr3 = new CMCEParameters("mceliece460896f", 13, 4608, 96, poly4608, true, 192);
    public static final CMCEParameters mceliece6688128r3 = new CMCEParameters("mceliece6688128", 13, 6688, 128, poly6688, false, 256);
    public static final CMCEParameters mceliece6688128fr3 = new CMCEParameters("mceliece6688128f", 13, 6688, 128, poly6688, true, 256);
    public static final CMCEParameters mceliece6960119r3 = new CMCEParameters("mceliece6960119", 13, 6960, 119, poly6960, false, 256);
    public static final CMCEParameters mceliece6960119fr3 = new CMCEParameters("mceliece6960119f", 13, 6960, 119, poly6960, true, 256);
    public static final CMCEParameters mceliece8192128r3 = new CMCEParameters("mceliece8192128", 13, 8192, 128, poly8192, false, 256);
    public static final CMCEParameters mceliece8192128fr3 = new CMCEParameters("mceliece8192128f", 13, 8192, 128, poly8192, true, 256);

    private CMCEParameters(String str, int i, int i2, int i3, int[] iArr, boolean z, int i4) {
        this.name = str;
        this.f27118m = i;
        this.f27119n = i2;
        this.f27120t = i3;
        this.usePivots = z;
        this.defaultKeySize = i4;
        this.engine = new CMCEEngine(i, i2, i3, iArr, z, i4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CMCEEngine getEngine() {
        return this.engine;
    }

    public int getM() {
        return this.f27118m;
    }

    public int getMu() {
        return this.usePivots ? 32 : 0;
    }

    public int getN() {
        return this.f27119n;
    }

    public String getName() {
        return this.name;
    }

    public int getNu() {
        return this.usePivots ? 64 : 0;
    }

    public int getSessionKeySize() {
        return this.defaultKeySize;
    }

    public int getT() {
        return this.f27120t;
    }
}
