package com.p210hp.hpl.sparta;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hp.hpl.sparta.ParseHandler */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ParseHandler {
    void characters(char[] cArr, int i, int i2) throws ParseException;

    void endDocument() throws ParseException;

    void endElement(Element element) throws ParseException;

    ParseSource getParseSource();

    void setParseSource(ParseSource parseSource);

    void startDocument() throws ParseException;

    void startElement(Element element) throws ParseException;
}
