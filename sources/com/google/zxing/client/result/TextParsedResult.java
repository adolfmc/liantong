package com.google.zxing.client.result;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class TextParsedResult extends ParsedResult {
    private final String language;
    private final String text;

    public TextParsedResult(String str, String str2) {
        super(ParsedResultType.TEXT);
        this.text = str;
        this.language = str2;
    }

    public String getText() {
        return this.text;
    }

    public String getLanguage() {
        return this.language;
    }

    @Override // com.google.zxing.client.result.ParsedResult
    public String getDisplayResult() {
        return this.text;
    }
}
