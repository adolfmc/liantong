package net.sourceforge.pinyin4j;

import com.p210hp.hpl.sparta.Element;
import com.p210hp.hpl.sparta.ParseException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
class PinyinRomanizationTranslator {
    PinyinRomanizationTranslator() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String convertRomanizationSystem(String str, PinyinRomanizationType pinyinRomanizationType, PinyinRomanizationType pinyinRomanizationType2) {
        String extractPinyinString = TextHelper.extractPinyinString(str);
        String extractToneNumber = TextHelper.extractToneNumber(str);
        try {
            Element xpathSelectElement = PinyinRomanizationResource.getInstance().getPinyinMappingDoc().xpathSelectElement("//" + pinyinRomanizationType.getTagName() + "[text()='" + extractPinyinString + "']");
            if (xpathSelectElement != null) {
                String xpathSelectString = xpathSelectElement.xpathSelectString("../" + pinyinRomanizationType2.getTagName() + "/text()");
                return xpathSelectString + extractToneNumber;
            }
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
