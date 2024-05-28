package net.sourceforge.pinyin4j;

import com.p210hp.hpl.sparta.Element;
import com.p210hp.hpl.sparta.ParseException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
class GwoyeuRomatzyhTranslator {
    private static String[] tones = {"_I", "_II", "_III", "_IV", "_V"};

    GwoyeuRomatzyhTranslator() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String convertHanyuPinyinToGwoyeuRomatzyh(String str) {
        String extractPinyinString = TextHelper.extractPinyinString(str);
        String extractToneNumber = TextHelper.extractToneNumber(str);
        try {
            Element xpathSelectElement = GwoyeuRomatzyhResource.getInstance().getPinyinToGwoyeuMappingDoc().xpathSelectElement("//" + PinyinRomanizationType.HANYU_PINYIN.getTagName() + "[text()='" + extractPinyinString + "']");
            if (xpathSelectElement != null) {
                return xpathSelectElement.xpathSelectString("../" + PinyinRomanizationType.GWOYEU_ROMATZYH.getTagName() + tones[Integer.parseInt(extractToneNumber) - 1] + "/text()");
            }
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
