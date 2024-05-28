package net.sourceforge.pinyin4j;

import java.io.BufferedInputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
class ResourceHelper {
    ResourceHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BufferedInputStream getResourceInputStream(String str) {
        return new BufferedInputStream(ResourceHelper.class.getResourceAsStream(str));
    }
}
