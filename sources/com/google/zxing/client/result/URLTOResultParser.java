package com.google.zxing.client.result;

import com.google.zxing.Result;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class URLTOResultParser extends ResultParser {
    @Override // com.google.zxing.client.result.ResultParser
    public URIParsedResult parse(Result result) {
        int indexOf;
        String massagedText = getMassagedText(result);
        if ((massagedText.startsWith("urlto:") || massagedText.startsWith("URLTO:")) && (indexOf = massagedText.indexOf(58, 6)) >= 0) {
            return new URIParsedResult(massagedText.substring(indexOf + 1), indexOf > 6 ? massagedText.substring(6, indexOf) : null);
        }
        return null;
    }
}
