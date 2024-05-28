package net.sourceforge.pinyin4j;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
class PinyinRomanizationType {
    protected String tagName;
    static final PinyinRomanizationType HANYU_PINYIN = new PinyinRomanizationType("Hanyu");
    static final PinyinRomanizationType WADEGILES_PINYIN = new PinyinRomanizationType("Wade");
    static final PinyinRomanizationType MPS2_PINYIN = new PinyinRomanizationType("MPSII");
    static final PinyinRomanizationType YALE_PINYIN = new PinyinRomanizationType("Yale");
    static final PinyinRomanizationType TONGYONG_PINYIN = new PinyinRomanizationType("Tongyong");
    static final PinyinRomanizationType GWOYEU_ROMATZYH = new PinyinRomanizationType("Gwoyeu");

    protected PinyinRomanizationType(String str) {
        setTagName(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getTagName() {
        return this.tagName;
    }

    protected void setTagName(String str) {
        this.tagName = str;
    }
}
