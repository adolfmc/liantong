package net.sourceforge.pinyin4j.format;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class HanyuPinyinToneType {
    protected String name;
    public static final HanyuPinyinToneType WITH_TONE_NUMBER = new HanyuPinyinToneType("WITH_TONE_NUMBER");
    public static final HanyuPinyinToneType WITHOUT_TONE = new HanyuPinyinToneType("WITHOUT_TONE");
    public static final HanyuPinyinToneType WITH_TONE_MARK = new HanyuPinyinToneType("WITH_TONE_MARK");

    public String getName() {
        return this.name;
    }

    protected void setName(String str) {
        this.name = str;
    }

    protected HanyuPinyinToneType(String str) {
        setName(str);
    }
}
