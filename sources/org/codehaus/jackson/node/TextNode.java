package org.codehaus.jackson.node;

import java.io.IOException;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.Base64Variants;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonLocation;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.p467io.NumberInput;
import org.codehaus.jackson.util.CharTypes;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class TextNode extends ValueNode {
    static final TextNode EMPTY_STRING_NODE = new TextNode("");
    static final int INT_SPACE = 32;
    final String _value;

    @Override // org.codehaus.jackson.JsonNode
    public boolean isTextual() {
        return true;
    }

    public TextNode(String str) {
        this._value = str;
    }

    public static TextNode valueOf(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return EMPTY_STRING_NODE;
        }
        return new TextNode(str);
    }

    @Override // org.codehaus.jackson.node.ValueNode, org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return JsonToken.VALUE_STRING;
    }

    @Override // org.codehaus.jackson.JsonNode
    public String getTextValue() {
        return this._value;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0023, code lost:
        if (r6 >= 0) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0025, code lost:
        _reportInvalidBase64(r12, r4, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0028, code lost:
        if (r5 < r2) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002a, code lost:
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002d, code lost:
        r4 = r5 + 1;
        r5 = r1.charAt(r5);
        r7 = r12.decodeBase64Char(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0037, code lost:
        if (r7 >= 0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0039, code lost:
        _reportInvalidBase64(r12, r5, 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003d, code lost:
        r5 = (r6 << 6) | r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0040, code lost:
        if (r4 < r2) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0046, code lost:
        if (r12.usesPadding() != false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0048, code lost:
        r0.append(r5 >> 4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004f, code lost:
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0052, code lost:
        r6 = r4 + 1;
        r4 = r1.charAt(r4);
        r7 = r12.decodeBase64Char(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x005f, code lost:
        if (r7 >= 0) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0061, code lost:
        if (r7 == (-2)) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0063, code lost:
        _reportInvalidBase64(r12, r4, 2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0066, code lost:
        if (r6 < r2) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0068, code lost:
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006b, code lost:
        r4 = r6 + 1;
        r6 = r1.charAt(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0075, code lost:
        if (r12.usesPaddingChar(r6) != false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0077, code lost:
        _reportInvalidBase64(r12, r6, 3, "expected padding character '" + r12.getPaddingChar() + "'");
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0094, code lost:
        r0.append(r5 >> 4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x009b, code lost:
        r4 = (r5 << 6) | r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x009e, code lost:
        if (r6 < r2) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00a4, code lost:
        if (r12.usesPadding() != false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00a6, code lost:
        r0.appendTwoBytes(r4 >> 2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00ac, code lost:
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00af, code lost:
        r5 = r6 + 1;
        r6 = r1.charAt(r6);
        r7 = r12.decodeBase64Char(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00b9, code lost:
        if (r7 >= 0) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00bb, code lost:
        if (r7 == (-2)) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00bd, code lost:
        _reportInvalidBase64(r12, r6, 3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00c0, code lost:
        r0.appendTwoBytes(r4 >> 2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00c6, code lost:
        r0.appendThreeBytes((r4 << 6) | r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00cc, code lost:
        r4 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
        r6 = r12.decodeBase64Char(r4);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] getBinaryValue(org.codehaus.jackson.Base64Variant r12) throws java.io.IOException {
        /*
            r11 = this;
            org.codehaus.jackson.util.ByteArrayBuilder r0 = new org.codehaus.jackson.util.ByteArrayBuilder
            r1 = 100
            r0.<init>(r1)
            java.lang.String r1 = r11._value
            int r2 = r1.length()
            r3 = 0
            r4 = r3
        Lf:
            if (r4 >= r2) goto Ld2
        L11:
            int r5 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r5 < r2) goto L1b
            goto Ld2
        L1b:
            r6 = 32
            if (r4 <= r6) goto Lcf
            int r6 = r12.decodeBase64Char(r4)
            if (r6 >= 0) goto L28
            r11._reportInvalidBase64(r12, r4, r3)
        L28:
            if (r5 < r2) goto L2d
            r11._reportBase64EOF()
        L2d:
            int r4 = r5 + 1
            char r5 = r1.charAt(r5)
            int r7 = r12.decodeBase64Char(r5)
            if (r7 >= 0) goto L3d
            r8 = 1
            r11._reportInvalidBase64(r12, r5, r8)
        L3d:
            int r5 = r6 << 6
            r5 = r5 | r7
            if (r4 < r2) goto L52
            boolean r6 = r12.usesPadding()
            if (r6 != 0) goto L4f
            int r12 = r5 >> 4
            r0.append(r12)
            goto Ld2
        L4f:
            r11._reportBase64EOF()
        L52:
            int r6 = r4 + 1
            char r4 = r1.charAt(r4)
            int r7 = r12.decodeBase64Char(r4)
            r8 = 3
            r9 = -2
            r10 = 2
            if (r7 >= 0) goto L9b
            if (r7 == r9) goto L66
            r11._reportInvalidBase64(r12, r4, r10)
        L66:
            if (r6 < r2) goto L6b
            r11._reportBase64EOF()
        L6b:
            int r4 = r6 + 1
            char r6 = r1.charAt(r6)
            boolean r7 = r12.usesPaddingChar(r6)
            if (r7 != 0) goto L94
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r9 = "expected padding character '"
            r7.append(r9)
            char r9 = r12.getPaddingChar()
            r7.append(r9)
            java.lang.String r9 = "'"
            r7.append(r9)
            java.lang.String r7 = r7.toString()
            r11._reportInvalidBase64(r12, r6, r8, r7)
        L94:
            int r5 = r5 >> 4
            r0.append(r5)
            goto Lf
        L9b:
            int r4 = r5 << 6
            r4 = r4 | r7
            if (r6 < r2) goto Laf
            boolean r5 = r12.usesPadding()
            if (r5 != 0) goto Lac
            int r12 = r4 >> 2
            r0.appendTwoBytes(r12)
            goto Ld2
        Lac:
            r11._reportBase64EOF()
        Laf:
            int r5 = r6 + 1
            char r6 = r1.charAt(r6)
            int r7 = r12.decodeBase64Char(r6)
            if (r7 >= 0) goto Lc6
            if (r7 == r9) goto Lc0
            r11._reportInvalidBase64(r12, r6, r8)
        Lc0:
            int r4 = r4 >> 2
            r0.appendTwoBytes(r4)
            goto Lcc
        Lc6:
            int r4 = r4 << 6
            r4 = r4 | r7
            r0.appendThreeBytes(r4)
        Lcc:
            r4 = r5
            goto Lf
        Lcf:
            r4 = r5
            goto L11
        Ld2:
            byte[] r12 = r0.toByteArray()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.node.TextNode.getBinaryValue(org.codehaus.jackson.Base64Variant):byte[]");
    }

    @Override // org.codehaus.jackson.JsonNode
    public byte[] getBinaryValue() throws IOException {
        return getBinaryValue(Base64Variants.getDefaultVariant());
    }

    @Override // org.codehaus.jackson.JsonNode
    public String asText() {
        return this._value;
    }

    @Override // org.codehaus.jackson.JsonNode
    public boolean asBoolean(boolean z) {
        String str = this._value;
        if (str == null || !"true".equals(str.trim())) {
            return z;
        }
        return true;
    }

    @Override // org.codehaus.jackson.JsonNode
    public int asInt(int i) {
        return NumberInput.parseAsInt(this._value, i);
    }

    @Override // org.codehaus.jackson.JsonNode
    public long asLong(long j) {
        return NumberInput.parseAsLong(this._value, j);
    }

    @Override // org.codehaus.jackson.JsonNode
    public double asDouble(double d) {
        return NumberInput.parseAsDouble(this._value, d);
    }

    @Override // org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        String str = this._value;
        if (str == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeString(str);
        }
    }

    @Override // org.codehaus.jackson.JsonNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            return ((TextNode) obj)._value.equals(this._value);
        }
        return false;
    }

    public int hashCode() {
        return this._value.hashCode();
    }

    @Override // org.codehaus.jackson.node.ValueNode, org.codehaus.jackson.JsonNode
    public String toString() {
        int length = this._value.length();
        StringBuilder sb = new StringBuilder(length + 2 + (length >> 4));
        appendQuoted(sb, this._value);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void appendQuoted(StringBuilder sb, String str) {
        sb.append('\"');
        CharTypes.appendQuoted(sb, str);
        sb.append('\"');
    }

    protected void _reportInvalidBase64(Base64Variant base64Variant, char c, int i) throws JsonParseException {
        _reportInvalidBase64(base64Variant, c, i, null);
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
        throw new JsonParseException(str2, JsonLocation.f27381NA);
    }

    protected void _reportBase64EOF() throws JsonParseException {
        throw new JsonParseException("Unexpected end-of-String when base64 content", JsonLocation.f27381NA);
    }
}
