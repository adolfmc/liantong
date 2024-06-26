package org.bouncycastle.pqc.crypto.saber;

import org.bouncycastle.crypto.CipherParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SABERParameters implements CipherParameters {
    private final int defaultKeySize;
    private final SABEREngine engine;

    /* renamed from: l */
    private final int f27237l;
    private final String name;
    public static final SABERParameters lightsaberkem128r3 = new SABERParameters("lightsaberkem128r3", 2, 128);
    public static final SABERParameters saberkem128r3 = new SABERParameters("saberkem128r3", 3, 128);
    public static final SABERParameters firesaberkem128r3 = new SABERParameters("firesaberkem128r3", 4, 128);
    public static final SABERParameters lightsaberkem192r3 = new SABERParameters("lightsaberkem192r3", 2, 192);
    public static final SABERParameters saberkem192r3 = new SABERParameters("saberkem192r3", 3, 192);
    public static final SABERParameters firesaberkem192r3 = new SABERParameters("firesaberkem192r3", 4, 192);
    public static final SABERParameters lightsaberkem256r3 = new SABERParameters("lightsaberkem256r3", 2, 256);
    public static final SABERParameters saberkem256r3 = new SABERParameters("saberkem256r3", 3, 256);
    public static final SABERParameters firesaberkem256r3 = new SABERParameters("firesaberkem256r3", 4, 256);

    public SABERParameters(String str, int i, int i2) {
        this.name = str;
        this.f27237l = i;
        this.defaultKeySize = i2;
        this.engine = new SABEREngine(i, i2);
    }

    public SABEREngine getEngine() {
        return this.engine;
    }

    public int getL() {
        return this.f27237l;
    }

    public String getName() {
        return this.name;
    }

    public int getSessionKeySize() {
        return this.defaultKeySize;
    }
}
