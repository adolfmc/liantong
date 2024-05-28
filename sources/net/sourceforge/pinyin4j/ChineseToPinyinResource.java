package net.sourceforge.pinyin4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
class ChineseToPinyinResource {
    private Properties unicodeToHanyuPinyinTable;

    private void setUnicodeToHanyuPinyinTable(Properties properties) {
        this.unicodeToHanyuPinyinTable = properties;
    }

    private Properties getUnicodeToHanyuPinyinTable() {
        return this.unicodeToHanyuPinyinTable;
    }

    private ChineseToPinyinResource() {
        this.unicodeToHanyuPinyinTable = null;
        initializeResource();
    }

    private void initializeResource() {
        try {
            setUnicodeToHanyuPinyinTable(new Properties());
            getUnicodeToHanyuPinyinTable().load(ResourceHelper.getResourceInputStream("/pinyindb/unicode_to_hanyu_pinyin.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String[] getHanyuPinyinStringArray(char c) {
        String hanyuPinyinRecordFromChar = getHanyuPinyinRecordFromChar(c);
        if (hanyuPinyinRecordFromChar != null) {
            return hanyuPinyinRecordFromChar.substring(hanyuPinyinRecordFromChar.indexOf("(") + 1, hanyuPinyinRecordFromChar.lastIndexOf(")")).split(",");
        }
        return null;
    }

    private boolean isValidRecord(String str) {
        return str != null && !str.equals("(none0)") && str.startsWith("(") && str.endsWith(")");
    }

    private String getHanyuPinyinRecordFromChar(char c) {
        String property = getUnicodeToHanyuPinyinTable().getProperty(Integer.toHexString(c).toUpperCase());
        if (isValidRecord(property)) {
            return property;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ChineseToPinyinResource getInstance() {
        return ChineseToPinyinResourceHolder.theInstance;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class ChineseToPinyinResourceHolder {
        static final ChineseToPinyinResource theInstance = new ChineseToPinyinResource();

        private ChineseToPinyinResourceHolder() {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    class Field {
        static final String COMMA = ",";
        static final String LEFT_BRACKET = "(";
        static final String RIGHT_BRACKET = ")";

        Field() {
        }
    }
}
