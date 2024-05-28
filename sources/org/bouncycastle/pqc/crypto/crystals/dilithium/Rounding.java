package org.bouncycastle.pqc.crypto.crystals.dilithium;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Rounding {
    Rounding() {
    }

    public static int[] decompose(int i, int i2) {
        int i3;
        int i4 = (i + 127) >> 7;
        if (i2 == 261888) {
            i3 = (((i4 * 1025) + 2097152) >> 22) & 15;
        } else if (i2 != 95232) {
            throw new RuntimeException("Wrong Gamma2!");
        } else {
            int i5 = ((i4 * 11275) + 8388608) >> 24;
            i3 = i5 ^ (((43 - i5) >> 31) & i5);
        }
        int i6 = i - ((i3 * 2) * i2);
        return new int[]{i6 - (((4190208 - i6) >> 31) & 8380417), i3};
    }

    public static int makeHint(int i, int i2, DilithiumEngine dilithiumEngine) {
        int i3;
        int dilithiumGamma2 = dilithiumEngine.getDilithiumGamma2();
        if (i <= dilithiumGamma2 || i > (i3 = 8380417 - dilithiumGamma2)) {
            return 0;
        }
        return (i == i3 && i2 == 0) ? 0 : 1;
    }

    public static int[] power2Round(int i) {
        int[] iArr = {((i + 4096) - 1) >> 13, i - (iArr[0] << 13)};
        return iArr;
    }

    public static int useHint(int i, int i2, int i3) {
        int[] decompose = decompose(i, i3);
        int i4 = decompose[0];
        int i5 = decompose[1];
        if (i2 == 0) {
            return i5;
        }
        if (i3 == 261888) {
            return (i4 > 0 ? i5 + 1 : i5 - 1) & 15;
        } else if (i3 == 95232) {
            if (i4 > 0) {
                if (i5 == 43) {
                    return 0;
                }
                return i5 + 1;
            } else if (i5 == 0) {
                return 43;
            } else {
                return i5 - 1;
            }
        } else {
            throw new RuntimeException("Wrong Gamma2!");
        }
    }
}
