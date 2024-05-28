package org.limlee.hipraiseanimationlib;

import java.util.Random;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Utils {
    private static final Random RANDOM = new Random();

    private Utils() {
    }

    public static int rondomRange(int i, int i2) {
        return (RANDOM.nextInt(i) % ((i - i2) + 1)) + i2;
    }
}
