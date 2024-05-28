package p479t0;

import java.util.List;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: t0.a */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class C14163a {
    /* renamed from: a */
    public static int m93a(char[] cArr, int i, Set<Character> set) {
        while (i < cArr.length) {
            if (!set.contains(Character.valueOf(cArr[i]))) {
                return i;
            }
            i++;
        }
        return cArr.length;
    }

    /* renamed from: a */
    public static void m94a(List<Integer> list, int i, int i2) {
        for (int i3 = 0; i3 < i; i3++) {
            list.add(Integer.valueOf(i2));
        }
    }
}
