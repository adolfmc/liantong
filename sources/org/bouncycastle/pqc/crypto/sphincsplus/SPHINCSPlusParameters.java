package org.bouncycastle.pqc.crypto.sphincsplus;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SPHINCSPlusParameters {
    private final SPHINCSPlusEngineProvider engineProvider;
    private final String name;
    public static final SPHINCSPlusParameters sha2_128f = new SPHINCSPlusParameters("sha2-128f-robust", new Sha2EngineProvider(true, 16, 16, 22, 6, 33, 66));
    public static final SPHINCSPlusParameters sha2_128s = new SPHINCSPlusParameters("sha2-128s-robust", new Sha2EngineProvider(true, 16, 16, 7, 12, 14, 63));
    public static final SPHINCSPlusParameters sha2_192f = new SPHINCSPlusParameters("sha2-192f-robust", new Sha2EngineProvider(true, 24, 16, 22, 8, 33, 66));
    public static final SPHINCSPlusParameters sha2_192s = new SPHINCSPlusParameters("sha2-192s-robust", new Sha2EngineProvider(true, 24, 16, 7, 14, 17, 63));
    public static final SPHINCSPlusParameters sha2_256f = new SPHINCSPlusParameters("sha2-256f-robust", new Sha2EngineProvider(true, 32, 16, 17, 9, 35, 68));
    public static final SPHINCSPlusParameters sha2_256s = new SPHINCSPlusParameters("sha2-256s-robust", new Sha2EngineProvider(true, 32, 16, 8, 14, 22, 64));
    public static final SPHINCSPlusParameters sha2_128f_simple = new SPHINCSPlusParameters("sha2-128f-simple", new Sha2EngineProvider(false, 16, 16, 22, 6, 33, 66));
    public static final SPHINCSPlusParameters sha2_128s_simple = new SPHINCSPlusParameters("sha2-128s-simple", new Sha2EngineProvider(false, 16, 16, 7, 12, 14, 63));
    public static final SPHINCSPlusParameters sha2_192f_simple = new SPHINCSPlusParameters("sha2-192f-simple", new Sha2EngineProvider(false, 24, 16, 22, 8, 33, 66));
    public static final SPHINCSPlusParameters sha2_192s_simple = new SPHINCSPlusParameters("sha2-192s-simple", new Sha2EngineProvider(false, 24, 16, 7, 14, 17, 63));
    public static final SPHINCSPlusParameters sha2_256f_simple = new SPHINCSPlusParameters("sha2-256f-simple", new Sha2EngineProvider(false, 32, 16, 17, 9, 35, 68));
    public static final SPHINCSPlusParameters sha2_256s_simple = new SPHINCSPlusParameters("sha2-256s-simple", new Sha2EngineProvider(false, 32, 16, 8, 14, 22, 64));
    public static final SPHINCSPlusParameters shake_128f = new SPHINCSPlusParameters("shake-128f-robust", new Shake256EngineProvider(true, 16, 16, 22, 6, 33, 66));
    public static final SPHINCSPlusParameters shake_128s = new SPHINCSPlusParameters("shake-128s-robust", new Shake256EngineProvider(true, 16, 16, 7, 12, 14, 63));
    public static final SPHINCSPlusParameters shake_192f = new SPHINCSPlusParameters("shake-192f-robust", new Shake256EngineProvider(true, 24, 16, 22, 8, 33, 66));
    public static final SPHINCSPlusParameters shake_192s = new SPHINCSPlusParameters("shake-192s-robust", new Shake256EngineProvider(true, 24, 16, 7, 14, 17, 63));
    public static final SPHINCSPlusParameters shake_256f = new SPHINCSPlusParameters("shake-256f-robust", new Shake256EngineProvider(true, 32, 16, 17, 9, 35, 68));
    public static final SPHINCSPlusParameters shake_256s = new SPHINCSPlusParameters("shake-256s-robust", new Shake256EngineProvider(true, 32, 16, 8, 14, 22, 64));
    public static final SPHINCSPlusParameters shake_128f_simple = new SPHINCSPlusParameters("shake-128f-simple", new Shake256EngineProvider(false, 16, 16, 22, 6, 33, 66));
    public static final SPHINCSPlusParameters shake_128s_simple = new SPHINCSPlusParameters("shake-128s-simple", new Shake256EngineProvider(false, 16, 16, 7, 12, 14, 63));
    public static final SPHINCSPlusParameters shake_192f_simple = new SPHINCSPlusParameters("shake-192f-simple", new Shake256EngineProvider(false, 24, 16, 22, 8, 33, 66));
    public static final SPHINCSPlusParameters shake_192s_simple = new SPHINCSPlusParameters("shake-192s-simple", new Shake256EngineProvider(false, 24, 16, 7, 14, 17, 63));
    public static final SPHINCSPlusParameters shake_256f_simple = new SPHINCSPlusParameters("shake-256f-simple", new Shake256EngineProvider(false, 32, 16, 17, 9, 35, 68));
    public static final SPHINCSPlusParameters shake_256s_simple = new SPHINCSPlusParameters("shake-256s-simple", new Shake256EngineProvider(false, 32, 16, 8, 14, 22, 64));
    public static final SPHINCSPlusParameters haraka_128f = new SPHINCSPlusParameters("haraka-128f-robust", new HarakaSEngineProvider(true, 16, 16, 22, 6, 33, 66));
    public static final SPHINCSPlusParameters haraka_128s = new SPHINCSPlusParameters("haraka-128s-robust", new HarakaSEngineProvider(true, 16, 16, 7, 12, 14, 63));
    public static final SPHINCSPlusParameters haraka_256f = new SPHINCSPlusParameters("haraka-256f-robust", new HarakaSEngineProvider(true, 32, 16, 17, 9, 35, 68));
    public static final SPHINCSPlusParameters haraka_256s = new SPHINCSPlusParameters("haraka-256s-robust", new HarakaSEngineProvider(true, 32, 16, 8, 14, 22, 64));
    public static final SPHINCSPlusParameters haraka_192f = new SPHINCSPlusParameters("haraka-192f-robust", new HarakaSEngineProvider(true, 24, 16, 22, 8, 33, 66));
    public static final SPHINCSPlusParameters haraka_192s = new SPHINCSPlusParameters("haraka-192s-robust", new HarakaSEngineProvider(true, 24, 16, 7, 14, 17, 63));
    public static final SPHINCSPlusParameters haraka_128f_simple = new SPHINCSPlusParameters("haraka-128f-simple", new HarakaSEngineProvider(false, 16, 16, 22, 6, 33, 66));
    public static final SPHINCSPlusParameters haraka_128s_simple = new SPHINCSPlusParameters("haraka-128s-simple", new HarakaSEngineProvider(false, 16, 16, 7, 12, 14, 63));
    public static final SPHINCSPlusParameters haraka_192f_simple = new SPHINCSPlusParameters("haraka-192f-simple", new HarakaSEngineProvider(false, 24, 16, 22, 8, 33, 66));
    public static final SPHINCSPlusParameters haraka_192s_simple = new SPHINCSPlusParameters("haraka-192s-simple", new HarakaSEngineProvider(false, 24, 16, 7, 14, 17, 63));
    public static final SPHINCSPlusParameters haraka_256f_simple = new SPHINCSPlusParameters("haraka-256f-simple", new HarakaSEngineProvider(false, 32, 16, 17, 9, 35, 68));
    public static final SPHINCSPlusParameters haraka_256s_simple = new SPHINCSPlusParameters("haraka-256s-simple", new HarakaSEngineProvider(false, 32, 16, 8, 14, 22, 64));
    private static final Integer sphincsPlus_sha2_128f_robust = Integers.valueOf(65793);
    private static final Integer sphincsPlus_sha2_128s_robust = Integers.valueOf(65794);
    private static final Integer sphincsPlus_sha2_192f_robust = Integers.valueOf(65795);
    private static final Integer sphincsPlus_sha2_192s_robust = Integers.valueOf(65796);
    private static final Integer sphincsPlus_sha2_256f_robust = Integers.valueOf(65797);
    private static final Integer sphincsPlus_sha2_256s_robust = Integers.valueOf(65798);
    private static final Integer sphincsPlus_sha2_128f_simple = Integers.valueOf(66049);
    private static final Integer sphincsPlus_sha2_128s_simple = Integers.valueOf(66050);
    private static final Integer sphincsPlus_sha2_192f_simple = Integers.valueOf(66051);
    private static final Integer sphincsPlus_sha2_192s_simple = Integers.valueOf(66052);
    private static final Integer sphincsPlus_sha2_256f_simple = Integers.valueOf(66053);
    private static final Integer sphincsPlus_sha2_256s_simple = Integers.valueOf(66054);
    private static final Integer sphincsPlus_shake_128f_robust = Integers.valueOf(131329);
    private static final Integer sphincsPlus_shake_128s_robust = Integers.valueOf(131330);
    private static final Integer sphincsPlus_shake_192f_robust = Integers.valueOf(131331);
    private static final Integer sphincsPlus_shake_192s_robust = Integers.valueOf(131332);
    private static final Integer sphincsPlus_shake_256f_robust = Integers.valueOf(131333);
    private static final Integer sphincsPlus_shake_256s_robust = Integers.valueOf(131334);
    private static final Integer sphincsPlus_shake_128f_simple = Integers.valueOf(131585);
    private static final Integer sphincsPlus_shake_128s_simple = Integers.valueOf(131586);
    private static final Integer sphincsPlus_shake_192f_simple = Integers.valueOf(131587);
    private static final Integer sphincsPlus_shake_192s_simple = Integers.valueOf(131588);
    private static final Integer sphincsPlus_shake_256f_simple = Integers.valueOf(131589);
    private static final Integer sphincsPlus_shake_256s_simple = Integers.valueOf(131590);
    private static final Integer sphincsPlus_haraka_128f_robust = Integers.valueOf(196865);
    private static final Integer sphincsPlus_haraka_128s_robust = Integers.valueOf(196866);
    private static final Integer sphincsPlus_haraka_192f_robust = Integers.valueOf(196867);
    private static final Integer sphincsPlus_haraka_192s_robust = Integers.valueOf(196868);
    private static final Integer sphincsPlus_haraka_256f_robust = Integers.valueOf(196869);
    private static final Integer sphincsPlus_haraka_256s_robust = Integers.valueOf(196870);
    private static final Integer sphincsPlus_haraka_128f_simple = Integers.valueOf(197121);
    private static final Integer sphincsPlus_haraka_128s_simple = Integers.valueOf(197122);
    private static final Integer sphincsPlus_haraka_192f_simple = Integers.valueOf(197123);
    private static final Integer sphincsPlus_haraka_192s_simple = Integers.valueOf(197124);
    private static final Integer sphincsPlus_haraka_256f_simple = Integers.valueOf(197125);
    private static final Integer sphincsPlus_haraka_256s_simple = Integers.valueOf(197126);
    private static final Map<Integer, SPHINCSPlusParameters> oidToParams = new HashMap();
    private static final Map<SPHINCSPlusParameters, Integer> paramsToOid = new HashMap();

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class HarakaSEngineProvider implements SPHINCSPlusEngineProvider {

        /* renamed from: a */
        private final int f27253a;

        /* renamed from: d */
        private final int f27254d;

        /* renamed from: h */
        private final int f27255h;

        /* renamed from: k */
        private final int f27256k;

        /* renamed from: n */
        private final int f27257n;
        private final boolean robust;

        /* renamed from: w */
        private final int f27258w;

        public HarakaSEngineProvider(boolean z, int i, int i2, int i3, int i4, int i5, int i6) {
            this.robust = z;
            this.f27257n = i;
            this.f27258w = i2;
            this.f27254d = i3;
            this.f27253a = i4;
            this.f27256k = i5;
            this.f27255h = i6;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngineProvider
        public SPHINCSPlusEngine get() {
            return new SPHINCSPlusEngine.HarakaSEngine(this.robust, this.f27257n, this.f27258w, this.f27254d, this.f27253a, this.f27256k, this.f27255h);
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngineProvider
        public int getN() {
            return this.f27257n;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class Sha2EngineProvider implements SPHINCSPlusEngineProvider {

        /* renamed from: a */
        private final int f27259a;

        /* renamed from: d */
        private final int f27260d;

        /* renamed from: h */
        private final int f27261h;

        /* renamed from: k */
        private final int f27262k;

        /* renamed from: n */
        private final int f27263n;
        private final boolean robust;

        /* renamed from: w */
        private final int f27264w;

        public Sha2EngineProvider(boolean z, int i, int i2, int i3, int i4, int i5, int i6) {
            this.robust = z;
            this.f27263n = i;
            this.f27264w = i2;
            this.f27260d = i3;
            this.f27259a = i4;
            this.f27262k = i5;
            this.f27261h = i6;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngineProvider
        public SPHINCSPlusEngine get() {
            return new SPHINCSPlusEngine.Sha2Engine(this.robust, this.f27263n, this.f27264w, this.f27260d, this.f27259a, this.f27262k, this.f27261h);
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngineProvider
        public int getN() {
            return this.f27263n;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class Shake256EngineProvider implements SPHINCSPlusEngineProvider {

        /* renamed from: a */
        private final int f27265a;

        /* renamed from: d */
        private final int f27266d;

        /* renamed from: h */
        private final int f27267h;

        /* renamed from: k */
        private final int f27268k;

        /* renamed from: n */
        private final int f27269n;
        private final boolean robust;

        /* renamed from: w */
        private final int f27270w;

        public Shake256EngineProvider(boolean z, int i, int i2, int i3, int i4, int i5, int i6) {
            this.robust = z;
            this.f27269n = i;
            this.f27270w = i2;
            this.f27266d = i3;
            this.f27265a = i4;
            this.f27268k = i5;
            this.f27267h = i6;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngineProvider
        public SPHINCSPlusEngine get() {
            return new SPHINCSPlusEngine.Shake256Engine(this.robust, this.f27269n, this.f27270w, this.f27266d, this.f27265a, this.f27268k, this.f27267h);
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngineProvider
        public int getN() {
            return this.f27269n;
        }
    }

    static {
        oidToParams.put(sphincsPlus_sha2_128f_robust, sha2_128f);
        oidToParams.put(sphincsPlus_sha2_128s_robust, sha2_128s);
        oidToParams.put(sphincsPlus_sha2_192f_robust, sha2_192f);
        oidToParams.put(sphincsPlus_sha2_192s_robust, sha2_192s);
        oidToParams.put(sphincsPlus_sha2_256f_robust, sha2_256f);
        oidToParams.put(sphincsPlus_sha2_256s_robust, sha2_256s);
        oidToParams.put(sphincsPlus_sha2_128f_simple, sha2_128f_simple);
        oidToParams.put(sphincsPlus_sha2_128s_simple, sha2_128s_simple);
        oidToParams.put(sphincsPlus_sha2_192f_simple, sha2_192f_simple);
        oidToParams.put(sphincsPlus_sha2_192s_simple, sha2_192s_simple);
        oidToParams.put(sphincsPlus_sha2_256f_simple, sha2_256f_simple);
        oidToParams.put(sphincsPlus_sha2_256s_simple, sha2_256s_simple);
        oidToParams.put(sphincsPlus_shake_128f_robust, shake_128f);
        oidToParams.put(sphincsPlus_shake_128s_robust, shake_128s);
        oidToParams.put(sphincsPlus_shake_192f_robust, shake_192f);
        oidToParams.put(sphincsPlus_shake_192s_robust, shake_192s);
        oidToParams.put(sphincsPlus_shake_256f_robust, shake_256f);
        oidToParams.put(sphincsPlus_shake_256s_robust, shake_256s);
        oidToParams.put(sphincsPlus_shake_128f_simple, shake_128f_simple);
        oidToParams.put(sphincsPlus_shake_128s_simple, shake_128s_simple);
        oidToParams.put(sphincsPlus_shake_192f_simple, shake_192f_simple);
        oidToParams.put(sphincsPlus_shake_192s_simple, shake_192s_simple);
        oidToParams.put(sphincsPlus_shake_256f_simple, shake_256f_simple);
        oidToParams.put(sphincsPlus_shake_256s_simple, shake_256s_simple);
        oidToParams.put(sphincsPlus_haraka_128f_simple, haraka_128f_simple);
        oidToParams.put(sphincsPlus_haraka_128f_robust, haraka_128f);
        oidToParams.put(sphincsPlus_haraka_192f_simple, haraka_192f_simple);
        oidToParams.put(sphincsPlus_haraka_192f_robust, haraka_192f);
        oidToParams.put(sphincsPlus_haraka_256f_simple, haraka_256f_simple);
        oidToParams.put(sphincsPlus_haraka_256f_robust, haraka_256f);
        oidToParams.put(sphincsPlus_haraka_128s_simple, haraka_128s_simple);
        oidToParams.put(sphincsPlus_haraka_128s_robust, haraka_128s);
        oidToParams.put(sphincsPlus_haraka_192s_simple, haraka_192s_simple);
        oidToParams.put(sphincsPlus_haraka_192s_robust, haraka_192s);
        oidToParams.put(sphincsPlus_haraka_256s_simple, haraka_256s_simple);
        oidToParams.put(sphincsPlus_haraka_256s_robust, haraka_256s);
        paramsToOid.put(sha2_128f, sphincsPlus_sha2_128f_robust);
        paramsToOid.put(sha2_128s, sphincsPlus_sha2_128s_robust);
        paramsToOid.put(sha2_192f, sphincsPlus_sha2_192f_robust);
        paramsToOid.put(sha2_192s, sphincsPlus_sha2_192s_robust);
        paramsToOid.put(sha2_256f, sphincsPlus_sha2_256f_robust);
        paramsToOid.put(sha2_256s, sphincsPlus_sha2_256s_robust);
        paramsToOid.put(sha2_128f_simple, sphincsPlus_sha2_128f_simple);
        paramsToOid.put(sha2_128s_simple, sphincsPlus_sha2_128s_simple);
        paramsToOid.put(sha2_192f_simple, sphincsPlus_sha2_192f_simple);
        paramsToOid.put(sha2_192s_simple, sphincsPlus_sha2_192s_simple);
        paramsToOid.put(sha2_256f_simple, sphincsPlus_sha2_256f_simple);
        paramsToOid.put(sha2_256s_simple, sphincsPlus_sha2_256s_simple);
        paramsToOid.put(shake_128f, sphincsPlus_shake_128f_robust);
        paramsToOid.put(shake_128s, sphincsPlus_shake_128s_robust);
        paramsToOid.put(shake_192f, sphincsPlus_shake_192f_robust);
        paramsToOid.put(shake_192s, sphincsPlus_shake_192s_robust);
        paramsToOid.put(shake_256f, sphincsPlus_shake_256f_robust);
        paramsToOid.put(shake_256s, sphincsPlus_shake_256s_robust);
        paramsToOid.put(shake_128f_simple, sphincsPlus_shake_128f_simple);
        paramsToOid.put(shake_128s_simple, sphincsPlus_shake_128s_simple);
        paramsToOid.put(shake_192f_simple, sphincsPlus_shake_192f_simple);
        paramsToOid.put(shake_192s_simple, sphincsPlus_shake_192s_simple);
        paramsToOid.put(shake_256f_simple, sphincsPlus_shake_256f_simple);
        paramsToOid.put(shake_256s_simple, sphincsPlus_shake_256s_simple);
        paramsToOid.put(haraka_128f, sphincsPlus_haraka_128f_robust);
        paramsToOid.put(haraka_192f, sphincsPlus_haraka_192f_robust);
        paramsToOid.put(haraka_256f, sphincsPlus_haraka_256f_robust);
        paramsToOid.put(haraka_128s, sphincsPlus_haraka_128s_robust);
        paramsToOid.put(haraka_192s, sphincsPlus_haraka_192s_robust);
        paramsToOid.put(haraka_256s, sphincsPlus_haraka_256s_robust);
        paramsToOid.put(haraka_128f_simple, sphincsPlus_haraka_128f_simple);
        paramsToOid.put(haraka_192f_simple, sphincsPlus_haraka_192f_simple);
        paramsToOid.put(haraka_256f_simple, sphincsPlus_haraka_256f_simple);
        paramsToOid.put(haraka_128s_simple, sphincsPlus_haraka_128s_simple);
        paramsToOid.put(haraka_192s_simple, sphincsPlus_haraka_192s_simple);
        paramsToOid.put(haraka_256s_simple, sphincsPlus_haraka_256s_simple);
    }

    private SPHINCSPlusParameters(String str, SPHINCSPlusEngineProvider sPHINCSPlusEngineProvider) {
        this.name = str;
        this.engineProvider = sPHINCSPlusEngineProvider;
    }

    public static Integer getID(SPHINCSPlusParameters sPHINCSPlusParameters) {
        return paramsToOid.get(sPHINCSPlusParameters);
    }

    public static SPHINCSPlusParameters getParams(Integer num) {
        return oidToParams.get(num);
    }

    public byte[] getEncoded() {
        return Pack.intToBigEndian(getID(this).intValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SPHINCSPlusEngine getEngine() {
        return this.engineProvider.get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getN() {
        return this.engineProvider.getN();
    }

    public String getName() {
        return this.name;
    }
}
