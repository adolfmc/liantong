package org.apache.commons.lang3.text;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\11617560_dexfile_execute.dex */
public class CompositeFormat extends Format {
    private static final long serialVersionUID = -4329119827877627683L;
    private final Format formatter;
    private final Format parser;

    public CompositeFormat(Format format, Format format2) {
        this.parser = format;
        this.formatter = format2;
    }

    @Override // java.text.Format
    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        return this.formatter.format(obj, stringBuffer, fieldPosition);
    }

    @Override // java.text.Format
    public Object parseObject(String str, ParsePosition parsePosition) {
        return this.parser.parseObject(str, parsePosition);
    }

    public Format getParser() {
        return this.parser;
    }

    public Format getFormatter() {
        return this.formatter;
    }

    public String reformat(String str) throws ParseException {
        return format(parseObject(str));
    }
}
