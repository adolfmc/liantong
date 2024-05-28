package com.google.zxing.client.result;

import com.google.zxing.Result;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class BookmarkDoCoMoResultParser extends AbstractDoCoMoResultParser {
    @Override // com.google.zxing.client.result.ResultParser
    public URIParsedResult parse(Result result) {
        String text = result.getText();
        if (text.startsWith("MEBKM:")) {
            String matchSingleDoCoMoPrefixedField = matchSingleDoCoMoPrefixedField("TITLE:", text, true);
            String[] matchDoCoMoPrefixedField = matchDoCoMoPrefixedField("URL:", text, true);
            if (matchDoCoMoPrefixedField == null) {
                return null;
            }
            String str = matchDoCoMoPrefixedField[0];
            if (URIResultParser.isBasicallyValidURI(str)) {
                return new URIParsedResult(str, matchSingleDoCoMoPrefixedField);
            }
            return null;
        }
        return null;
    }
}
