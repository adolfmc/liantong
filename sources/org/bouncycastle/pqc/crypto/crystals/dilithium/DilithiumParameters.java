package org.bouncycastle.pqc.crypto.crystals.dilithium;

import java.security.SecureRandom;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DilithiumParameters {
    public static final DilithiumParameters dilithium2 = new DilithiumParameters("dilithium2", 2, false);
    public static final DilithiumParameters dilithium2_aes = new DilithiumParameters("dilithium2-aes", 2, true);
    public static final DilithiumParameters dilithium3 = new DilithiumParameters("dilithium3", 3, false);
    public static final DilithiumParameters dilithium3_aes = new DilithiumParameters("dilithium3-aes", 3, true);
    public static final DilithiumParameters dilithium5 = new DilithiumParameters("dilithium5", 5, false);
    public static final DilithiumParameters dilithium5_aes = new DilithiumParameters("dilithium5-aes", 5, true);

    /* renamed from: k */
    private final int f27121k;
    private final String name;
    private final boolean usingAES;

    private DilithiumParameters(String str, int i, boolean z) {
        this.name = str;
        this.f27121k = i;
        this.usingAES = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DilithiumEngine getEngine(SecureRandom secureRandom) {
        return new DilithiumEngine(this.f27121k, secureRandom, this.usingAES);
    }

    public String getName() {
        return this.name;
    }
}
