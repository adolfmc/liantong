package org.bouncycastle.pqc.crypto.bike;

import org.bouncycastle.crypto.CipherParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class BIKEParameters implements CipherParameters {
    public static final BIKEParameters bike128 = new BIKEParameters("bike128", 12323, 142, 134, 256, 5, 3, 128);
    public static final BIKEParameters bike192 = new BIKEParameters("bike192", 24659, 206, 199, 256, 5, 3, 192);
    public static final BIKEParameters bike256 = new BIKEParameters("bike256", 40973, 274, 264, 256, 5, 3, 256);
    private BIKEEngine bikeEngine;
    private final int defaultKeySize;

    /* renamed from: l */
    private int f27107l;
    private String name;
    private int nbIter;

    /* renamed from: r */
    private int f27108r;

    /* renamed from: t */
    private int f27109t;
    private int tau;

    /* renamed from: w */
    private int f27110w;

    private BIKEParameters(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.name = str;
        this.f27108r = i;
        this.f27110w = i2;
        this.f27109t = i3;
        this.f27107l = i4;
        this.nbIter = i5;
        this.tau = i6;
        this.defaultKeySize = i7;
        this.bikeEngine = new BIKEEngine(i, i2, i3, i4, i5, i6);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BIKEEngine getEngine() {
        return this.bikeEngine;
    }

    public int getL() {
        return this.f27107l;
    }

    public int getLByte() {
        return this.f27107l / 8;
    }

    public String getName() {
        return this.name;
    }

    public int getNbIter() {
        return this.nbIter;
    }

    public int getR() {
        return this.f27108r;
    }

    public int getRByte() {
        return (this.f27108r + 7) / 8;
    }

    public int getSessionKeySize() {
        return this.defaultKeySize;
    }

    public int getT() {
        return this.f27109t;
    }

    public int getTau() {
        return this.tau;
    }

    public int getW() {
        return this.f27110w;
    }
}
