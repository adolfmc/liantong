package org.codehaus.jackson.impl;

import java.io.IOException;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonStreamContext;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.p467io.NumberInput;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class JsonParserMinimalBase extends JsonParser {
    protected static final int INT_APOSTROPHE = 39;
    protected static final int INT_ASTERISK = 42;
    protected static final int INT_BACKSLASH = 92;
    protected static final int INT_COLON = 58;
    protected static final int INT_COMMA = 44;
    protected static final int INT_CR = 13;
    protected static final int INT_LBRACKET = 91;
    protected static final int INT_LCURLY = 123;
    protected static final int INT_LF = 10;
    protected static final int INT_QUOTE = 34;
    protected static final int INT_RBRACKET = 93;
    protected static final int INT_RCURLY = 125;
    protected static final int INT_SLASH = 47;
    protected static final int INT_SPACE = 32;
    protected static final int INT_TAB = 9;
    protected static final int INT_b = 98;
    protected static final int INT_f = 102;
    protected static final int INT_n = 110;
    protected static final int INT_r = 114;
    protected static final int INT_t = 116;
    protected static final int INT_u = 117;

    protected abstract void _handleEOF() throws JsonParseException;

    @Override // org.codehaus.jackson.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public abstract void close() throws IOException;

    @Override // org.codehaus.jackson.JsonParser
    public abstract byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException;

    @Override // org.codehaus.jackson.JsonParser
    public abstract String getCurrentName() throws IOException, JsonParseException;

    @Override // org.codehaus.jackson.JsonParser
    public abstract JsonStreamContext getParsingContext();

    @Override // org.codehaus.jackson.JsonParser
    public abstract String getText() throws IOException, JsonParseException;

    @Override // org.codehaus.jackson.JsonParser
    public abstract char[] getTextCharacters() throws IOException, JsonParseException;

    @Override // org.codehaus.jackson.JsonParser
    public abstract int getTextLength() throws IOException, JsonParseException;

    @Override // org.codehaus.jackson.JsonParser
    public abstract int getTextOffset() throws IOException, JsonParseException;

    @Override // org.codehaus.jackson.JsonParser
    public abstract boolean hasTextCharacters();

    @Override // org.codehaus.jackson.JsonParser
    public abstract boolean isClosed();

    @Override // org.codehaus.jackson.JsonParser
    public abstract JsonToken nextToken() throws IOException, JsonParseException;

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonParserMinimalBase() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonParserMinimalBase(int i) {
        super(i);
    }

    @Override // org.codehaus.jackson.JsonParser
    public JsonParser skipChildren() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
            int i = 1;
            while (true) {
                JsonToken nextToken = nextToken();
                if (nextToken != null) {
                    switch (nextToken) {
                        case START_OBJECT:
                        case START_ARRAY:
                            i++;
                            break;
                        case END_OBJECT:
                        case END_ARRAY:
                            i--;
                            if (i != 0) {
                                break;
                            } else {
                                return this;
                            }
                    }
                } else {
                    _handleEOF();
                    return this;
                }
            }
        } else {
            return this;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0033 A[RETURN] */
    @Override // org.codehaus.jackson.JsonParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean getValueAsBoolean(boolean r4) throws java.io.IOException, org.codehaus.jackson.JsonParseException {
        /*
            r3 = this;
            org.codehaus.jackson.JsonToken r0 = r3._currToken
            if (r0 == 0) goto L3e
            int[] r0 = org.codehaus.jackson.impl.JsonParserMinimalBase.C133991.$SwitchMap$org$codehaus$jackson$JsonToken
            org.codehaus.jackson.JsonToken r1 = r3._currToken
            int r1 = r1.ordinal()
            r0 = r0[r1]
            r1 = 0
            r2 = 1
            switch(r0) {
                case 5: goto L36;
                case 6: goto L35;
                case 7: goto L34;
                case 8: goto L34;
                case 9: goto L14;
                case 10: goto L23;
                default: goto L13;
            }
        L13:
            goto L3e
        L14:
            java.lang.Object r0 = r3.getEmbeddedObject()
            boolean r1 = r0 instanceof java.lang.Boolean
            if (r1 == 0) goto L23
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r4 = r0.booleanValue()
            return r4
        L23:
            java.lang.String r0 = r3.getText()
            java.lang.String r0 = r0.trim()
            java.lang.String r1 = "true"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L3e
            return r2
        L34:
            return r1
        L35:
            return r2
        L36:
            int r4 = r3.getIntValue()
            if (r4 == 0) goto L3d
            r1 = r2
        L3d:
            return r1
        L3e:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.impl.JsonParserMinimalBase.getValueAsBoolean(boolean):boolean");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // org.codehaus.jackson.JsonParser
    public int getValueAsInt(int i) throws IOException, JsonParseException {
        if (this._currToken != null) {
            switch (this._currToken) {
                case VALUE_NUMBER_INT:
                case VALUE_NUMBER_FLOAT:
                    return getIntValue();
                case VALUE_TRUE:
                    return 1;
                case VALUE_FALSE:
                case VALUE_NULL:
                    return 0;
                case VALUE_EMBEDDED_OBJECT:
                    Object embeddedObject = getEmbeddedObject();
                    if (embeddedObject instanceof Number) {
                        return ((Number) embeddedObject).intValue();
                    }
                    break;
                case VALUE_STRING:
                    return NumberInput.parseAsInt(getText(), i);
            }
        }
        return i;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // org.codehaus.jackson.JsonParser
    public long getValueAsLong(long j) throws IOException, JsonParseException {
        if (this._currToken != null) {
            switch (this._currToken) {
                case VALUE_NUMBER_INT:
                case VALUE_NUMBER_FLOAT:
                    return getLongValue();
                case VALUE_TRUE:
                    return 1L;
                case VALUE_FALSE:
                case VALUE_NULL:
                    return 0L;
                case VALUE_EMBEDDED_OBJECT:
                    Object embeddedObject = getEmbeddedObject();
                    if (embeddedObject instanceof Number) {
                        return ((Number) embeddedObject).longValue();
                    }
                    break;
                case VALUE_STRING:
                    return NumberInput.parseAsLong(getText(), j);
            }
        }
        return j;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // org.codehaus.jackson.JsonParser
    public double getValueAsDouble(double d) throws IOException, JsonParseException {
        if (this._currToken != null) {
            switch (this._currToken) {
                case VALUE_NUMBER_INT:
                case VALUE_NUMBER_FLOAT:
                    return getDoubleValue();
                case VALUE_TRUE:
                    return 1.0d;
                case VALUE_FALSE:
                case VALUE_NULL:
                    return 0.0d;
                case VALUE_EMBEDDED_OBJECT:
                    Object embeddedObject = getEmbeddedObject();
                    if (embeddedObject instanceof Number) {
                        return ((Number) embeddedObject).doubleValue();
                    }
                    break;
                case VALUE_STRING:
                    return NumberInput.parseAsDouble(getText(), d);
            }
        }
        return d;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x001b, code lost:
        if (r4 >= 0) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x001d, code lost:
        _reportInvalidBase64(r13, r2, 0, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0020, code lost:
        if (r3 < r0) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0022, code lost:
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0025, code lost:
        r2 = r3 + 1;
        r3 = r11.charAt(r3);
        r6 = r13.decodeBase64Char(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002f, code lost:
        if (r6 >= 0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0031, code lost:
        _reportInvalidBase64(r13, r3, 1, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0035, code lost:
        r3 = (r4 << 6) | r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0038, code lost:
        if (r2 < r0) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003e, code lost:
        if (r13.usesPadding() != false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0040, code lost:
        r12.append(r3 >> 4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0047, code lost:
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004a, code lost:
        r4 = r2 + 1;
        r2 = r11.charAt(r2);
        r6 = r13.decodeBase64Char(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0057, code lost:
        if (r6 >= 0) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0059, code lost:
        if (r6 == (-2)) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005b, code lost:
        _reportInvalidBase64(r13, r2, 2, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005e, code lost:
        if (r4 < r0) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0060, code lost:
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0063, code lost:
        r2 = r4 + 1;
        r4 = r11.charAt(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x006d, code lost:
        if (r13.usesPaddingChar(r4) != false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x006f, code lost:
        _reportInvalidBase64(r13, r4, 3, "expected padding character '" + r13.getPaddingChar() + "'");
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x008c, code lost:
        r12.append(r3 >> 4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0093, code lost:
        r2 = (r3 << 6) | r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0096, code lost:
        if (r4 < r0) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x009c, code lost:
        if (r13.usesPadding() != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x009e, code lost:
        r12.appendTwoBytes(r2 >> 2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00a4, code lost:
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00a7, code lost:
        r3 = r4 + 1;
        r4 = r11.charAt(r4);
        r6 = r13.decodeBase64Char(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00b1, code lost:
        if (r6 >= 0) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00b3, code lost:
        if (r6 == (-2)) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00b5, code lost:
        _reportInvalidBase64(r13, r4, 3, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b8, code lost:
        r12.appendTwoBytes(r2 >> 2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00be, code lost:
        r12.appendThreeBytes((r2 << 6) | r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00c4, code lost:
        r2 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00ca, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0016, code lost:
        r4 = r13.decodeBase64Char(r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void _decodeBase64(java.lang.String r11, org.codehaus.jackson.util.ByteArrayBuilder r12, org.codehaus.jackson.Base64Variant r13) throws java.io.IOException, org.codehaus.jackson.JsonParseException {
        /*
            r10 = this;
            int r0 = r11.length()
            r1 = 0
            r2 = r1
        L6:
            if (r2 >= r0) goto Lca
        L8:
            int r3 = r2 + 1
            char r2 = r11.charAt(r2)
            if (r3 < r0) goto L12
            goto Lca
        L12:
            r4 = 32
            if (r2 <= r4) goto Lc7
            int r4 = r13.decodeBase64Char(r2)
            r5 = 0
            if (r4 >= 0) goto L20
            r10._reportInvalidBase64(r13, r2, r1, r5)
        L20:
            if (r3 < r0) goto L25
            r10._reportBase64EOF()
        L25:
            int r2 = r3 + 1
            char r3 = r11.charAt(r3)
            int r6 = r13.decodeBase64Char(r3)
            if (r6 >= 0) goto L35
            r7 = 1
            r10._reportInvalidBase64(r13, r3, r7, r5)
        L35:
            int r3 = r4 << 6
            r3 = r3 | r6
            if (r2 < r0) goto L4a
            boolean r4 = r13.usesPadding()
            if (r4 != 0) goto L47
            int r11 = r3 >> 4
            r12.append(r11)
            goto Lca
        L47:
            r10._reportBase64EOF()
        L4a:
            int r4 = r2 + 1
            char r2 = r11.charAt(r2)
            int r6 = r13.decodeBase64Char(r2)
            r7 = 3
            r8 = -2
            r9 = 2
            if (r6 >= 0) goto L93
            if (r6 == r8) goto L5e
            r10._reportInvalidBase64(r13, r2, r9, r5)
        L5e:
            if (r4 < r0) goto L63
            r10._reportBase64EOF()
        L63:
            int r2 = r4 + 1
            char r4 = r11.charAt(r4)
            boolean r5 = r13.usesPaddingChar(r4)
            if (r5 != 0) goto L8c
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "expected padding character '"
            r5.append(r6)
            char r6 = r13.getPaddingChar()
            r5.append(r6)
            java.lang.String r6 = "'"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r10._reportInvalidBase64(r13, r4, r7, r5)
        L8c:
            int r3 = r3 >> 4
            r12.append(r3)
            goto L6
        L93:
            int r2 = r3 << 6
            r2 = r2 | r6
            if (r4 < r0) goto La7
            boolean r3 = r13.usesPadding()
            if (r3 != 0) goto La4
            int r11 = r2 >> 2
            r12.appendTwoBytes(r11)
            goto Lca
        La4:
            r10._reportBase64EOF()
        La7:
            int r3 = r4 + 1
            char r4 = r11.charAt(r4)
            int r6 = r13.decodeBase64Char(r4)
            if (r6 >= 0) goto Lbe
            if (r6 == r8) goto Lb8
            r10._reportInvalidBase64(r13, r4, r7, r5)
        Lb8:
            int r2 = r2 >> 2
            r12.appendTwoBytes(r2)
            goto Lc4
        Lbe:
            int r2 = r2 << 6
            r2 = r2 | r6
            r12.appendThreeBytes(r2)
        Lc4:
            r2 = r3
            goto L6
        Lc7:
            r2 = r3
            goto L8
        Lca:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.impl.JsonParserMinimalBase._decodeBase64(java.lang.String, org.codehaus.jackson.util.ByteArrayBuilder, org.codehaus.jackson.Base64Variant):void");
    }

    protected void _reportInvalidBase64(Base64Variant base64Variant, char c, int i, String str) throws JsonParseException {
        String str2;
        if (c <= ' ') {
            str2 = "Illegal white space character (code 0x" + Integer.toHexString(c) + ") as character #" + (i + 1) + " of 4-char base64 unit: can only used between units";
        } else if (base64Variant.usesPaddingChar(c)) {
            str2 = "Unexpected padding character ('" + base64Variant.getPaddingChar() + "') as character #" + (i + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
        } else if (!Character.isDefined(c) || Character.isISOControl(c)) {
            str2 = "Illegal character (code 0x" + Integer.toHexString(c) + ") in base64 content";
        } else {
            str2 = "Illegal character '" + c + "' (code 0x" + Integer.toHexString(c) + ") in base64 content";
        }
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        throw _constructError(str2);
    }

    protected void _reportBase64EOF() throws JsonParseException {
        throw _constructError("Unexpected end-of-String in base64 content");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _reportUnexpectedChar(int i, String str) throws JsonParseException {
        String str2 = "Unexpected character (" + _getCharDesc(i) + ")";
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        _reportError(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _reportInvalidEOF() throws JsonParseException {
        _reportInvalidEOF(" in " + this._currToken);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _reportInvalidEOF(String str) throws JsonParseException {
        _reportError("Unexpected end-of-input" + str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _reportInvalidEOFInValue() throws JsonParseException {
        _reportInvalidEOF(" in a value");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _throwInvalidSpace(int i) throws JsonParseException {
        _reportError("Illegal character (" + _getCharDesc((char) i) + "): only regular white space (\\r, \\n, \\t) is allowed between tokens");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _throwUnquotedSpace(int i, String str) throws JsonParseException {
        if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS) || i >= 32) {
            _reportError("Illegal unquoted character (" + _getCharDesc((char) i) + "): has to be escaped using backslash to be included in " + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public char _handleUnrecognizedCharacterEscape(char c) throws JsonProcessingException {
        if (isEnabled(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)) {
            return c;
        }
        if (c == '\'' && isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
            return c;
        }
        _reportError("Unrecognized character escape " + _getCharDesc(c));
        return c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final String _getCharDesc(int i) {
        char c = (char) i;
        if (Character.isISOControl(c)) {
            return "(CTRL-CHAR, code " + i + ")";
        } else if (i > 255) {
            return "'" + c + "' (code " + i + " / 0x" + Integer.toHexString(i) + ")";
        } else {
            return "'" + c + "' (code " + i + ")";
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void _reportError(String str) throws JsonParseException {
        throw _constructError(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void _wrapError(String str, Throwable th) throws JsonParseException {
        throw _constructError(str, th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void _throwInternal() {
        throw new RuntimeException("Internal error: this code path should never get executed");
    }

    protected final JsonParseException _constructError(String str, Throwable th) {
        return new JsonParseException(str, getCurrentLocation(), th);
    }
}
