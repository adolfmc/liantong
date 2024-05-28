package group.pals.android.lib.p392ui.lockpattern.util;

import group.pals.android.lib.p392ui.lockpattern.collect.Lists;
import java.util.ArrayList;
import java.util.Random;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: group.pals.android.lib.ui.lockpattern.util.Randoms */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class Randoms {
    private static final Random RANDOM = new Random();

    public static int randInt() {
        return RANDOM.nextInt((int) (System.nanoTime() % 2147483647L));
    }

    public static int randInt(int i) {
        if (i > 0) {
            return randInt() % i;
        }
        return 0;
    }

    public static int[] randIntArray(int i, int i2) {
        if (i2 <= i) {
            return new int[0];
        }
        ArrayList newArrayList = Lists.newArrayList();
        while (i < i2) {
            newArrayList.add(Integer.valueOf(i));
            i++;
        }
        int[] iArr = new int[newArrayList.size()];
        for (int i3 = 0; i3 < iArr.length; i3++) {
            int randInt = randInt(newArrayList.size());
            iArr[i3] = ((Integer) newArrayList.get(randInt)).intValue();
            newArrayList.remove(randInt);
        }
        return iArr;
    }

    public static int[] randIntArray(int i) {
        return randIntArray(0, i);
    }
}
