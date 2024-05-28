package net.sourceforge.pinyin4j;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
class TextHelper {
    TextHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String extractToneNumber(String str) {
        return str.substring(str.length() - 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String extractPinyinString(String str) {
        return str.substring(0, str.length() - 1);
    }
}
