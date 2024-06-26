package szcom.googlecode.mp4parser.h264;

import java.nio.ShortBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Debug {
    public static final boolean debug = false;

    public static void print(int i) {
    }

    public static void print(String str) {
    }

    public static void print(short[] sArr) {
        int i = 0;
        int i2 = 0;
        while (i < 8) {
            int i3 = i2;
            for (int i4 = 0; i4 < 8; i4++) {
                System.out.printf("%3d, ", Short.valueOf(sArr[i3]));
                i3++;
            }
            System.out.println();
            i++;
            i2 = i3;
        }
    }

    public static final void print8x8(ShortBuffer shortBuffer) {
        for (int i = 0; i < 8; i++) {
            for (int i2 = 0; i2 < 8; i2++) {
                System.out.printf("%3d, ", Short.valueOf(shortBuffer.get()));
            }
            System.out.println();
        }
    }

    public static final void print8x8(int[] iArr) {
        int i = 0;
        int i2 = 0;
        while (i < 8) {
            int i3 = i2;
            for (int i4 = 0; i4 < 8; i4++) {
                System.out.printf("%3d, ", Integer.valueOf(iArr[i3]));
                i3++;
            }
            System.out.println();
            i++;
            i2 = i3;
        }
    }

    public static final void print8x8(short[] sArr) {
        int i = 0;
        int i2 = 0;
        while (i < 8) {
            int i3 = i2;
            for (int i4 = 0; i4 < 8; i4++) {
                System.out.printf("%3d, ", Short.valueOf(sArr[i3]));
                i3++;
            }
            System.out.println();
            i++;
            i2 = i3;
        }
    }

    public static void println(String str) {
    }

    public static void trace(String str, Object... objArr) {
    }
}
