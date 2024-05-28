package org.codehaus.jackson.p467io;

import org.codehaus.jackson.SerializableString;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.codehaus.jackson.io.SerializedString */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SerializedString implements SerializableString {
    protected char[] _quotedChars;
    protected byte[] _quotedUTF8Ref;
    protected byte[] _unquotedUTF8Ref;
    protected final String _value;

    public SerializedString(String str) {
        this._value = str;
    }

    @Override // org.codehaus.jackson.SerializableString
    public final String getValue() {
        return this._value;
    }

    @Override // org.codehaus.jackson.SerializableString
    public final int charLength() {
        return this._value.length();
    }

    @Override // org.codehaus.jackson.SerializableString
    public final char[] asQuotedChars() {
        char[] cArr = this._quotedChars;
        if (cArr == null) {
            char[] quoteAsString = JsonStringEncoder.getInstance().quoteAsString(this._value);
            this._quotedChars = quoteAsString;
            return quoteAsString;
        }
        return cArr;
    }

    @Override // org.codehaus.jackson.SerializableString
    public final byte[] asUnquotedUTF8() {
        byte[] bArr = this._unquotedUTF8Ref;
        if (bArr == null) {
            byte[] encodeAsUTF8 = JsonStringEncoder.getInstance().encodeAsUTF8(this._value);
            this._unquotedUTF8Ref = encodeAsUTF8;
            return encodeAsUTF8;
        }
        return bArr;
    }

    @Override // org.codehaus.jackson.SerializableString
    public final byte[] asQuotedUTF8() {
        byte[] bArr = this._quotedUTF8Ref;
        if (bArr == null) {
            byte[] quoteAsUTF8 = JsonStringEncoder.getInstance().quoteAsUTF8(this._value);
            this._quotedUTF8Ref = quoteAsUTF8;
            return quoteAsUTF8;
        }
        return bArr;
    }

    public final String toString() {
        return this._value;
    }

    public final int hashCode() {
        return this._value.hashCode();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this._value.equals(((SerializedString) obj)._value);
    }
}
