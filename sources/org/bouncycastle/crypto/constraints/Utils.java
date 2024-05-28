package org.bouncycastle.crypto.constraints;

import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class Utils {
    Utils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void addAliases(Set<String> set) {
        String str;
        if (set.contains("RC4")) {
            str = "ARC4";
        } else if (!set.contains("ARC4")) {
            return;
        } else {
            str = "RC4";
        }
        set.add(str);
    }
}
