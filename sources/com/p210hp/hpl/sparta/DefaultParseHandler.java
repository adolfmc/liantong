package com.p210hp.hpl.sparta;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hp.hpl.sparta.DefaultParseHandler */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class DefaultParseHandler implements ParseHandler {
    private ParseSource parseSource_ = null;

    @Override // com.p210hp.hpl.sparta.ParseHandler
    public void characters(char[] cArr, int i, int i2) throws ParseException {
    }

    @Override // com.p210hp.hpl.sparta.ParseHandler
    public void endDocument() throws ParseException {
    }

    @Override // com.p210hp.hpl.sparta.ParseHandler
    public void endElement(Element element) throws ParseException {
    }

    @Override // com.p210hp.hpl.sparta.ParseHandler
    public void startDocument() throws ParseException {
    }

    @Override // com.p210hp.hpl.sparta.ParseHandler
    public void startElement(Element element) throws ParseException {
    }

    @Override // com.p210hp.hpl.sparta.ParseHandler
    public void setParseSource(ParseSource parseSource) {
        this.parseSource_ = parseSource;
    }

    @Override // com.p210hp.hpl.sparta.ParseHandler
    public ParseSource getParseSource() {
        return this.parseSource_;
    }
}
