package net.sourceforge.pinyin4j;

import com.p210hp.hpl.sparta.Document;
import com.p210hp.hpl.sparta.ParseException;
import com.p210hp.hpl.sparta.Parser;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class PinyinRomanizationResource {
    private Document pinyinMappingDoc;

    private void setPinyinMappingDoc(Document document) {
        this.pinyinMappingDoc = document;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Document getPinyinMappingDoc() {
        return this.pinyinMappingDoc;
    }

    private PinyinRomanizationResource() {
        initializeResource();
    }

    private void initializeResource() {
        try {
            setPinyinMappingDoc(Parser.parse("", ResourceHelper.getResourceInputStream("/pinyindb/pinyin_mapping.xml")));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PinyinRomanizationResource getInstance() {
        return PinyinRomanizationSystemResourceHolder.theInstance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class PinyinRomanizationSystemResourceHolder {
        static final PinyinRomanizationResource theInstance = new PinyinRomanizationResource();

        private PinyinRomanizationSystemResourceHolder() {
        }
    }
}
