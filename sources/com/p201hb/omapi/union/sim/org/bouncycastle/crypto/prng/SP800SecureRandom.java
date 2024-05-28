package com.p201hb.omapi.union.sim.org.bouncycastle.crypto.prng;

import java.security.SecureRandom;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0.InterfaceC0608b;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0.InterfaceC0610d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0.p044j.InterfaceC0620c;

/* renamed from: com.hb.omapi.union.sim.org.bouncycastle.crypto.prng.SP800SecureRandom */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SP800SecureRandom extends SecureRandom {
    public InterfaceC0620c drbg;
    public final InterfaceC0608b drbgProvider;
    public final InterfaceC0610d entropySource;
    public final boolean predictionResistant;
    public final SecureRandom randomSource;

    public SP800SecureRandom(SecureRandom secureRandom, InterfaceC0610d interfaceC0610d, InterfaceC0608b interfaceC0608b, boolean z) {
        this.randomSource = secureRandom;
        this.entropySource = interfaceC0610d;
        this.drbgProvider = interfaceC0608b;
        this.predictionResistant = z;
    }

    @Override // java.security.SecureRandom
    public byte[] generateSeed(int i) {
        byte[] bArr = new byte[i];
        nextBytes(bArr);
        return bArr;
    }

    @Override // java.security.SecureRandom, java.util.Random
    public void nextBytes(byte[] bArr) {
        synchronized (this) {
            if (this.drbg == null) {
                this.drbg = this.drbgProvider.m22740a(this.entropySource);
            }
            if (this.drbg.mo22717a(bArr, null, this.predictionResistant) < 0) {
                this.drbg.mo22718a(this.entropySource.mo22732c());
                this.drbg.mo22717a(bArr, null, this.predictionResistant);
            }
        }
    }

    @Override // java.security.SecureRandom
    public void setSeed(byte[] bArr) {
        synchronized (this) {
            if (this.randomSource != null) {
                this.randomSource.setSeed(bArr);
            }
        }
    }

    @Override // java.security.SecureRandom, java.util.Random
    public void setSeed(long j) {
        synchronized (this) {
            if (this.randomSource != null) {
                this.randomSource.setSeed(j);
            }
        }
    }
}
