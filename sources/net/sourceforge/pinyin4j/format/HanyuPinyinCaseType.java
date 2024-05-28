package net.sourceforge.pinyin4j.format;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class HanyuPinyinCaseType {
    protected String name;
    public static final HanyuPinyinCaseType UPPERCASE = new HanyuPinyinCaseType("UPPERCASE");
    public static final HanyuPinyinCaseType LOWERCASE = new HanyuPinyinCaseType("LOWERCASE");

    public String getName() {
        return this.name;
    }

    protected void setName(String str) {
        this.name = str;
    }

    protected HanyuPinyinCaseType(String str) {
        setName(str);
    }
}
