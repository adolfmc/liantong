package net.sourceforge.pinyin4j;

import com.p210hp.hpl.sparta.Document;
import com.p210hp.hpl.sparta.ParseException;
import com.p210hp.hpl.sparta.Parser;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class GwoyeuRomatzyhResource {
    private Document pinyinToGwoyeuMappingDoc;

    private void setPinyinToGwoyeuMappingDoc(Document document) {
        this.pinyinToGwoyeuMappingDoc = document;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Document getPinyinToGwoyeuMappingDoc() {
        return this.pinyinToGwoyeuMappingDoc;
    }

    private GwoyeuRomatzyhResource() {
        initializeResource();
    }

    private void initializeResource() {
        try {
            setPinyinToGwoyeuMappingDoc(Parser.parse("", ResourceHelper.getResourceInputStream("/pinyindb/pinyin_gwoyeu_mapping.xml")));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GwoyeuRomatzyhResource getInstance() {
        return GwoyeuRomatzyhSystemResourceHolder.theInstance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class GwoyeuRomatzyhSystemResourceHolder {
        static final GwoyeuRomatzyhResource theInstance = new GwoyeuRomatzyhResource();

        private GwoyeuRomatzyhSystemResourceHolder() {
        }
    }
}
