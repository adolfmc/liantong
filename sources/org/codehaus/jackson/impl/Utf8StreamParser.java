package org.codehaus.jackson.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.SerializableString;
import org.codehaus.jackson.p467io.IOContext;
import org.codehaus.jackson.sym.BytesToNameCanonicalizer;
import org.codehaus.jackson.sym.Name;
import org.codehaus.jackson.util.ByteArrayBuilder;
import org.codehaus.jackson.util.CharTypes;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class Utf8StreamParser extends JsonParserBase {
    static final byte BYTE_LF = 10;
    protected boolean _bufferRecyclable;
    protected byte[] _inputBuffer;
    protected InputStream _inputStream;
    protected ObjectCodec _objectCodec;
    private int _quad1;
    protected int[] _quadBuffer;
    protected final BytesToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete;
    private static final int[] sInputCodesUtf8 = CharTypes.getInputCodeUtf8();
    private static final int[] sInputCodesLatin1 = CharTypes.getInputCodeLatin1();

    public Utf8StreamParser(IOContext iOContext, int i, InputStream inputStream, ObjectCodec objectCodec, BytesToNameCanonicalizer bytesToNameCanonicalizer, byte[] bArr, int i2, int i3, boolean z) {
        super(iOContext, i);
        this._quadBuffer = new int[16];
        this._tokenIncomplete = false;
        this._inputStream = inputStream;
        this._objectCodec = objectCodec;
        this._symbols = bytesToNameCanonicalizer;
        this._inputBuffer = bArr;
        this._inputPtr = i2;
        this._inputEnd = i3;
        this._bufferRecyclable = z;
        if (JsonParser.Feature.CANONICALIZE_FIELD_NAMES.enabledIn(i)) {
            return;
        }
        _throwInternal();
    }

    @Override // org.codehaus.jackson.JsonParser
    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    @Override // org.codehaus.jackson.JsonParser
    public void setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }

    @Override // org.codehaus.jackson.JsonParser
    public int releaseBuffered(OutputStream outputStream) throws IOException {
        int i = this._inputEnd - this._inputPtr;
        if (i < 1) {
            return 0;
        }
        outputStream.write(this._inputBuffer, this._inputPtr, i);
        return i;
    }

    @Override // org.codehaus.jackson.JsonParser
    public Object getInputSource() {
        return this._inputStream;
    }

    @Override // org.codehaus.jackson.impl.JsonParserBase
    protected final boolean loadMore() throws IOException {
        this._currInputProcessed += this._inputEnd;
        this._currInputRowStart -= this._inputEnd;
        InputStream inputStream = this._inputStream;
        if (inputStream != null) {
            byte[] bArr = this._inputBuffer;
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read > 0) {
                this._inputPtr = 0;
                this._inputEnd = read;
                return true;
            }
            _closeInput();
            if (read == 0) {
                throw new IOException("InputStream.read() returned 0 characters when trying to read " + this._inputBuffer.length + " bytes");
            }
        }
        return false;
    }

    protected final boolean _loadToHaveAtLeast(int i) throws IOException {
        if (this._inputStream == null) {
            return false;
        }
        int i2 = this._inputEnd - this._inputPtr;
        if (i2 > 0 && this._inputPtr > 0) {
            this._currInputProcessed += this._inputPtr;
            this._currInputRowStart -= this._inputPtr;
            System.arraycopy(this._inputBuffer, this._inputPtr, this._inputBuffer, 0, i2);
            this._inputEnd = i2;
        } else {
            this._inputEnd = 0;
        }
        this._inputPtr = 0;
        while (this._inputEnd < i) {
            int read = this._inputStream.read(this._inputBuffer, this._inputEnd, this._inputBuffer.length - this._inputEnd);
            if (read < 1) {
                _closeInput();
                if (read != 0) {
                    return false;
                }
                throw new IOException("InputStream.read() returned 0 characters when trying to read " + i2 + " bytes");
            }
            this._inputEnd += read;
        }
        return true;
    }

    @Override // org.codehaus.jackson.impl.JsonParserBase
    protected void _closeInput() throws IOException {
        if (this._inputStream != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE)) {
                this._inputStream.close();
            }
            this._inputStream = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.codehaus.jackson.impl.JsonParserBase
    public void _releaseBuffers() throws IOException {
        byte[] bArr;
        super._releaseBuffers();
        if (!this._bufferRecyclable || (bArr = this._inputBuffer) == null) {
            return;
        }
        this._inputBuffer = null;
        this._ioContext.releaseReadIOBuffer(bArr);
    }

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    public String getText() throws IOException, JsonParseException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                _finishString();
            }
            return this._textBuffer.contentsAsString();
        }
        return _getText2(jsonToken);
    }

    protected final String _getText2(JsonToken jsonToken) {
        if (jsonToken == null) {
            return null;
        }
        switch (jsonToken) {
            case FIELD_NAME:
                return this._parsingContext.getCurrentName();
            case VALUE_STRING:
            case VALUE_NUMBER_INT:
            case VALUE_NUMBER_FLOAT:
                return this._textBuffer.contentsAsString();
            default:
                return jsonToken.asString();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    public char[] getTextCharacters() throws IOException, JsonParseException {
        if (this._currToken != null) {
            switch (this._currToken) {
                case FIELD_NAME:
                    if (!this._nameCopied) {
                        String currentName = this._parsingContext.getCurrentName();
                        int length = currentName.length();
                        if (this._nameCopyBuffer == null) {
                            this._nameCopyBuffer = this._ioContext.allocNameCopyBuffer(length);
                        } else if (this._nameCopyBuffer.length < length) {
                            this._nameCopyBuffer = new char[length];
                        }
                        currentName.getChars(0, length, this._nameCopyBuffer, 0);
                        this._nameCopied = true;
                    }
                    return this._nameCopyBuffer;
                case VALUE_STRING:
                    if (this._tokenIncomplete) {
                        this._tokenIncomplete = false;
                        _finishString();
                        break;
                    }
                    break;
                case VALUE_NUMBER_INT:
                case VALUE_NUMBER_FLOAT:
                    break;
                default:
                    return this._currToken.asCharArray();
            }
            return this._textBuffer.getTextBuffer();
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    public int getTextLength() throws IOException, JsonParseException {
        if (this._currToken != null) {
            switch (this._currToken) {
                case FIELD_NAME:
                    return this._parsingContext.getCurrentName().length();
                case VALUE_STRING:
                    if (this._tokenIncomplete) {
                        this._tokenIncomplete = false;
                        _finishString();
                        break;
                    }
                    break;
                case VALUE_NUMBER_INT:
                case VALUE_NUMBER_FLOAT:
                    break;
                default:
                    return this._currToken.asCharArray().length;
            }
            return this._textBuffer.size();
        }
        return 0;
    }

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    public int getTextOffset() throws IOException, JsonParseException {
        if (this._currToken != null) {
            switch (this._currToken) {
                case FIELD_NAME:
                    return 0;
                case VALUE_STRING:
                    if (this._tokenIncomplete) {
                        this._tokenIncomplete = false;
                        _finishString();
                    }
                    return this._textBuffer.getTextOffset();
                case VALUE_NUMBER_INT:
                case VALUE_NUMBER_FLOAT:
                    return this._textBuffer.getTextOffset();
            }
        }
        return 0;
    }

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException {
        if (this._currToken != JsonToken.VALUE_STRING && (this._currToken != JsonToken.VALUE_EMBEDDED_OBJECT || this._binaryValue == null)) {
            _reportError("Current token (" + this._currToken + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
        }
        if (this._tokenIncomplete) {
            try {
                this._binaryValue = _decodeBase64(base64Variant);
                this._tokenIncomplete = false;
            } catch (IllegalArgumentException e) {
                throw _constructError("Failed to decode VALUE_STRING as base64 (" + base64Variant + "): " + e.getMessage());
            }
        } else if (this._binaryValue == null) {
            ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
            _decodeBase64(getText(), _getByteArrayBuilder, base64Variant);
            this._binaryValue = _getByteArrayBuilder.toByteArray();
        }
        return this._binaryValue;
    }

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    public JsonToken nextToken() throws IOException, JsonParseException {
        JsonToken parseNumberText;
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            return _nextAfterName();
        }
        if (this._tokenIncomplete) {
            _skipString();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this._currToken = null;
            return null;
        }
        this._tokenInputTotal = (this._currInputProcessed + this._inputPtr) - 1;
        this._tokenInputRow = this._currInputRow;
        this._tokenInputCol = (this._inputPtr - this._currInputRowStart) - 1;
        this._binaryValue = null;
        if (_skipWSOrEnd == 93) {
            if (!this._parsingContext.inArray()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, '}');
            }
            this._parsingContext = this._parsingContext.getParent();
            JsonToken jsonToken = JsonToken.END_ARRAY;
            this._currToken = jsonToken;
            return jsonToken;
        } else if (_skipWSOrEnd == 125) {
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, ']');
            }
            this._parsingContext = this._parsingContext.getParent();
            JsonToken jsonToken2 = JsonToken.END_OBJECT;
            this._currToken = jsonToken2;
            return jsonToken2;
        } else {
            if (this._parsingContext.expectComma()) {
                if (_skipWSOrEnd != 44) {
                    _reportUnexpectedChar(_skipWSOrEnd, "was expecting comma to separate " + this._parsingContext.getTypeDesc() + " entries");
                }
                _skipWSOrEnd = _skipWS();
            }
            if (!this._parsingContext.inObject()) {
                return _nextTokenNotInObject(_skipWSOrEnd);
            }
            this._parsingContext.setCurrentName(_parseFieldName(_skipWSOrEnd).getName());
            this._currToken = JsonToken.FIELD_NAME;
            int _skipWS = _skipWS();
            if (_skipWS != 58) {
                _reportUnexpectedChar(_skipWS, "was expecting a colon to separate field name and value");
            }
            int _skipWS2 = _skipWS();
            if (_skipWS2 == 34) {
                this._tokenIncomplete = true;
                this._nextToken = JsonToken.VALUE_STRING;
                return this._currToken;
            }
            if (_skipWS2 != 45) {
                if (_skipWS2 == 91) {
                    parseNumberText = JsonToken.START_ARRAY;
                } else {
                    if (_skipWS2 != 93) {
                        if (_skipWS2 == 102) {
                            _matchToken("false", 1);
                            parseNumberText = JsonToken.VALUE_FALSE;
                        } else if (_skipWS2 != 110) {
                            if (_skipWS2 != 116) {
                                if (_skipWS2 == 123) {
                                    parseNumberText = JsonToken.START_OBJECT;
                                } else if (_skipWS2 != 125) {
                                    switch (_skipWS2) {
                                        case 48:
                                        case 49:
                                        case 50:
                                        case 51:
                                        case 52:
                                        case 53:
                                        case 54:
                                        case 55:
                                        case 56:
                                        case 57:
                                            break;
                                        default:
                                            parseNumberText = _handleUnexpectedValue(_skipWS2);
                                            break;
                                    }
                                }
                            }
                            _matchToken("true", 1);
                            parseNumberText = JsonToken.VALUE_TRUE;
                        } else {
                            _matchToken("null", 1);
                            parseNumberText = JsonToken.VALUE_NULL;
                        }
                    }
                    _reportUnexpectedChar(_skipWS2, "expected a value");
                    _matchToken("true", 1);
                    parseNumberText = JsonToken.VALUE_TRUE;
                }
                this._nextToken = parseNumberText;
                return this._currToken;
            }
            parseNumberText = parseNumberText(_skipWS2);
            this._nextToken = parseNumberText;
            return this._currToken;
        }
    }

    private final JsonToken _nextTokenNotInObject(int i) throws IOException, JsonParseException {
        if (i == 34) {
            this._tokenIncomplete = true;
            JsonToken jsonToken = JsonToken.VALUE_STRING;
            this._currToken = jsonToken;
            return jsonToken;
        }
        if (i != 45) {
            if (i == 91) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
                JsonToken jsonToken2 = JsonToken.START_ARRAY;
                this._currToken = jsonToken2;
                return jsonToken2;
            }
            if (i != 93) {
                if (i == 102) {
                    _matchToken("false", 1);
                    JsonToken jsonToken3 = JsonToken.VALUE_FALSE;
                    this._currToken = jsonToken3;
                    return jsonToken3;
                } else if (i != 110) {
                    if (i != 116) {
                        if (i == 123) {
                            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                            JsonToken jsonToken4 = JsonToken.START_OBJECT;
                            this._currToken = jsonToken4;
                            return jsonToken4;
                        } else if (i != 125) {
                            switch (i) {
                                case 48:
                                case 49:
                                case 50:
                                case 51:
                                case 52:
                                case 53:
                                case 54:
                                case 55:
                                case 56:
                                case 57:
                                    break;
                                default:
                                    JsonToken _handleUnexpectedValue = _handleUnexpectedValue(i);
                                    this._currToken = _handleUnexpectedValue;
                                    return _handleUnexpectedValue;
                            }
                        }
                    }
                    _matchToken("true", 1);
                    JsonToken jsonToken5 = JsonToken.VALUE_TRUE;
                    this._currToken = jsonToken5;
                    return jsonToken5;
                } else {
                    _matchToken("null", 1);
                    JsonToken jsonToken6 = JsonToken.VALUE_NULL;
                    this._currToken = jsonToken6;
                    return jsonToken6;
                }
            }
            _reportUnexpectedChar(i, "expected a value");
            _matchToken("true", 1);
            JsonToken jsonToken52 = JsonToken.VALUE_TRUE;
            this._currToken = jsonToken52;
            return jsonToken52;
        }
        JsonToken parseNumberText = parseNumberText(i);
        this._currToken = parseNumberText;
        return parseNumberText;
    }

    private final JsonToken _nextAfterName() {
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        this._currToken = jsonToken;
        return jsonToken;
    }

    @Override // org.codehaus.jackson.impl.JsonParserBase, org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        this._symbols.release();
    }

    @Override // org.codehaus.jackson.JsonParser
    public boolean nextFieldName(SerializableString serializableString) throws IOException, JsonParseException {
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            _nextAfterName();
            return false;
        }
        if (this._tokenIncomplete) {
            _skipString();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this._currToken = null;
            return false;
        }
        this._tokenInputTotal = (this._currInputProcessed + this._inputPtr) - 1;
        this._tokenInputRow = this._currInputRow;
        this._tokenInputCol = (this._inputPtr - this._currInputRowStart) - 1;
        this._binaryValue = null;
        if (_skipWSOrEnd == 93) {
            if (!this._parsingContext.inArray()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, '}');
            }
            this._parsingContext = this._parsingContext.getParent();
            this._currToken = JsonToken.END_ARRAY;
            return false;
        } else if (_skipWSOrEnd == 125) {
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, ']');
            }
            this._parsingContext = this._parsingContext.getParent();
            this._currToken = JsonToken.END_OBJECT;
            return false;
        } else {
            if (this._parsingContext.expectComma()) {
                if (_skipWSOrEnd != 44) {
                    _reportUnexpectedChar(_skipWSOrEnd, "was expecting comma to separate " + this._parsingContext.getTypeDesc() + " entries");
                }
                _skipWSOrEnd = _skipWS();
            }
            if (!this._parsingContext.inObject()) {
                _nextTokenNotInObject(_skipWSOrEnd);
                return false;
            }
            if (_skipWSOrEnd == 34) {
                byte[] asQuotedUTF8 = serializableString.asQuotedUTF8();
                int length = asQuotedUTF8.length;
                if (this._inputPtr + length < this._inputEnd) {
                    int i = this._inputPtr + length;
                    if (this._inputBuffer[i] == 34) {
                        int i2 = this._inputPtr;
                        for (int i3 = 0; i3 != length; i3++) {
                            if (asQuotedUTF8[i3] == this._inputBuffer[i2 + i3]) {
                            }
                        }
                        this._inputPtr = i + 1;
                        this._parsingContext.setCurrentName(serializableString.getValue());
                        this._currToken = JsonToken.FIELD_NAME;
                        _isNextTokenNameYes();
                        return true;
                    }
                }
            }
            _isNextTokenNameNo(_skipWSOrEnd);
            return false;
        }
    }

    private final void _isNextTokenNameYes() throws IOException, JsonParseException {
        int _skipColon;
        if (this._inputPtr < this._inputEnd && this._inputBuffer[this._inputPtr] == 58) {
            this._inputPtr++;
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            byte b = bArr[i];
            if (b == 34) {
                this._tokenIncomplete = true;
                this._nextToken = JsonToken.VALUE_STRING;
                return;
            } else if (b == 123) {
                this._nextToken = JsonToken.START_OBJECT;
                return;
            } else if (b == 91) {
                this._nextToken = JsonToken.START_ARRAY;
                return;
            } else {
                _skipColon = b & 255;
                if (_skipColon <= 32 || _skipColon == 47) {
                    this._inputPtr--;
                    _skipColon = _skipWS();
                }
            }
        } else {
            _skipColon = _skipColon();
        }
        if (_skipColon == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            return;
        }
        if (_skipColon != 45) {
            if (_skipColon == 91) {
                this._nextToken = JsonToken.START_ARRAY;
                return;
            }
            if (_skipColon != 93) {
                if (_skipColon == 102) {
                    _matchToken("false", 1);
                    this._nextToken = JsonToken.VALUE_FALSE;
                    return;
                } else if (_skipColon != 110) {
                    if (_skipColon != 116) {
                        if (_skipColon == 123) {
                            this._nextToken = JsonToken.START_OBJECT;
                            return;
                        } else if (_skipColon != 125) {
                            switch (_skipColon) {
                                case 48:
                                case 49:
                                case 50:
                                case 51:
                                case 52:
                                case 53:
                                case 54:
                                case 55:
                                case 56:
                                case 57:
                                    break;
                                default:
                                    this._nextToken = _handleUnexpectedValue(_skipColon);
                                    return;
                            }
                        }
                    }
                    _matchToken("true", 1);
                    this._nextToken = JsonToken.VALUE_TRUE;
                    return;
                } else {
                    _matchToken("null", 1);
                    this._nextToken = JsonToken.VALUE_NULL;
                    return;
                }
            }
            _reportUnexpectedChar(_skipColon, "expected a value");
            _matchToken("true", 1);
            this._nextToken = JsonToken.VALUE_TRUE;
            return;
        }
        this._nextToken = parseNumberText(_skipColon);
    }

    private final void _isNextTokenNameNo(int i) throws IOException, JsonParseException {
        JsonToken parseNumberText;
        this._parsingContext.setCurrentName(_parseFieldName(i).getName());
        this._currToken = JsonToken.FIELD_NAME;
        int _skipWS = _skipWS();
        if (_skipWS != 58) {
            _reportUnexpectedChar(_skipWS, "was expecting a colon to separate field name and value");
        }
        int _skipWS2 = _skipWS();
        if (_skipWS2 == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            return;
        }
        if (_skipWS2 != 45) {
            if (_skipWS2 == 91) {
                parseNumberText = JsonToken.START_ARRAY;
            } else {
                if (_skipWS2 != 93) {
                    if (_skipWS2 == 102) {
                        _matchToken("false", 1);
                        parseNumberText = JsonToken.VALUE_FALSE;
                    } else if (_skipWS2 != 110) {
                        if (_skipWS2 != 116) {
                            if (_skipWS2 == 123) {
                                parseNumberText = JsonToken.START_OBJECT;
                            } else if (_skipWS2 != 125) {
                                switch (_skipWS2) {
                                    case 48:
                                    case 49:
                                    case 50:
                                    case 51:
                                    case 52:
                                    case 53:
                                    case 54:
                                    case 55:
                                    case 56:
                                    case 57:
                                        break;
                                    default:
                                        parseNumberText = _handleUnexpectedValue(_skipWS2);
                                        break;
                                }
                            }
                        }
                        _matchToken("true", 1);
                        parseNumberText = JsonToken.VALUE_TRUE;
                    } else {
                        _matchToken("null", 1);
                        parseNumberText = JsonToken.VALUE_NULL;
                    }
                }
                _reportUnexpectedChar(_skipWS2, "expected a value");
                _matchToken("true", 1);
                parseNumberText = JsonToken.VALUE_TRUE;
            }
            this._nextToken = parseNumberText;
        }
        parseNumberText = parseNumberText(_skipWS2);
        this._nextToken = parseNumberText;
    }

    @Override // org.codehaus.jackson.JsonParser
    public String nextTextValue() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken jsonToken = this._nextToken;
            this._nextToken = null;
            this._currToken = jsonToken;
            if (jsonToken == JsonToken.VALUE_STRING) {
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                }
                return this._textBuffer.contentsAsString();
            }
            if (jsonToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (jsonToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return null;
        } else if (nextToken() == JsonToken.VALUE_STRING) {
            return getText();
        } else {
            return null;
        }
    }

    @Override // org.codehaus.jackson.JsonParser
    public int nextIntValue(int i) throws IOException, JsonParseException {
        if (this._currToken != JsonToken.FIELD_NAME) {
            return nextToken() == JsonToken.VALUE_NUMBER_INT ? getIntValue() : i;
        }
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        this._currToken = jsonToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
            return getIntValue();
        }
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        return i;
    }

    @Override // org.codehaus.jackson.JsonParser
    public long nextLongValue(long j) throws IOException, JsonParseException {
        if (this._currToken != JsonToken.FIELD_NAME) {
            return nextToken() == JsonToken.VALUE_NUMBER_INT ? getLongValue() : j;
        }
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        this._currToken = jsonToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
            return getLongValue();
        }
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        return j;
    }

    @Override // org.codehaus.jackson.JsonParser
    public Boolean nextBooleanValue() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken jsonToken = this._nextToken;
            this._nextToken = null;
            this._currToken = jsonToken;
            if (jsonToken == JsonToken.VALUE_TRUE) {
                return Boolean.TRUE;
            }
            if (jsonToken == JsonToken.VALUE_FALSE) {
                return Boolean.FALSE;
            }
            if (jsonToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (jsonToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return null;
        }
        switch (nextToken()) {
            case VALUE_TRUE:
                return Boolean.TRUE;
            case VALUE_FALSE:
                return Boolean.FALSE;
            default:
                return null;
        }
    }

    protected final JsonToken parseNumberText(int i) throws IOException, JsonParseException {
        int i2;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i3 = 0;
        boolean z = i == 45;
        if (z) {
            emptyAndGetCurrentSegment[0] = '-';
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i4 = this._inputPtr;
            this._inputPtr = i4 + 1;
            i = bArr[i4] & 255;
            if (i < 48 || i > 57) {
                return _handleInvalidNumberStart(i, true);
            }
            i3 = 1;
        }
        if (i == 48) {
            i = _verifyNoLeadingZeroes();
        }
        int i5 = i3 + 1;
        emptyAndGetCurrentSegment[i3] = (char) i;
        int length = this._inputPtr + emptyAndGetCurrentSegment.length;
        if (length > this._inputEnd) {
            length = this._inputEnd;
            i2 = 1;
        } else {
            i2 = 1;
        }
        while (this._inputPtr < length) {
            byte[] bArr2 = this._inputBuffer;
            int i6 = this._inputPtr;
            this._inputPtr = i6 + 1;
            int i7 = bArr2[i6] & 255;
            if (i7 < 48 || i7 > 57) {
                if (i7 == 46 || i7 == 101 || i7 == 69) {
                    return _parseFloatText(emptyAndGetCurrentSegment, i5, i7, z, i2);
                }
                this._inputPtr--;
                this._textBuffer.setCurrentLength(i5);
                return resetInt(z, i2);
            }
            i2++;
            emptyAndGetCurrentSegment[i5] = (char) i7;
            i5++;
        }
        return _parserNumber2(emptyAndGetCurrentSegment, i5, z, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0044, code lost:
        if (r3 == 46) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0048, code lost:
        if (r3 == 101) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004c, code lost:
        if (r3 != 69) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x004f, code lost:
        r6._inputPtr--;
        r6._textBuffer.setCurrentLength(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005e, code lost:
        return resetInt(r9, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0065, code lost:
        return _parseFloatText(r1, r2, r3, r9, r5);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final org.codehaus.jackson.JsonToken _parserNumber2(char[] r7, int r8, boolean r9, int r10) throws java.io.IOException, org.codehaus.jackson.JsonParseException {
        /*
            r6 = this;
            r1 = r7
            r2 = r8
            r5 = r10
        L3:
            int r7 = r6._inputPtr
            int r8 = r6._inputEnd
            if (r7 < r8) goto L19
            boolean r7 = r6.loadMore()
            if (r7 != 0) goto L19
            org.codehaus.jackson.util.TextBuffer r7 = r6._textBuffer
            r7.setCurrentLength(r2)
            org.codehaus.jackson.JsonToken r7 = r6.resetInt(r9, r5)
            return r7
        L19:
            byte[] r7 = r6._inputBuffer
            int r8 = r6._inputPtr
            int r10 = r8 + 1
            r6._inputPtr = r10
            r7 = r7[r8]
            r3 = r7 & 255(0xff, float:3.57E-43)
            r7 = 57
            if (r3 > r7) goto L42
            r7 = 48
            if (r3 >= r7) goto L2e
            goto L42
        L2e:
            int r7 = r1.length
            if (r2 < r7) goto L39
            org.codehaus.jackson.util.TextBuffer r7 = r6._textBuffer
            char[] r7 = r7.finishCurrentSegment()
            r2 = 0
            r1 = r7
        L39:
            int r7 = r2 + 1
            char r8 = (char) r3
            r1[r2] = r8
            int r5 = r5 + 1
            r2 = r7
            goto L3
        L42:
            r7 = 46
            if (r3 == r7) goto L5f
            r7 = 101(0x65, float:1.42E-43)
            if (r3 == r7) goto L5f
            r7 = 69
            if (r3 != r7) goto L4f
            goto L5f
        L4f:
            int r7 = r6._inputPtr
            int r7 = r7 + (-1)
            r6._inputPtr = r7
            org.codehaus.jackson.util.TextBuffer r7 = r6._textBuffer
            r7.setCurrentLength(r2)
            org.codehaus.jackson.JsonToken r7 = r6.resetInt(r9, r5)
            return r7
        L5f:
            r0 = r6
            r4 = r9
            org.codehaus.jackson.JsonToken r7 = r0._parseFloatText(r1, r2, r3, r4, r5)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.impl.Utf8StreamParser._parserNumber2(char[], int, boolean, int):org.codehaus.jackson.JsonToken");
    }

    private final int _verifyNoLeadingZeroes() throws IOException, JsonParseException {
        int i;
        if ((this._inputPtr < this._inputEnd || loadMore()) && (i = this._inputBuffer[this._inputPtr] & 255) >= 48 && i <= 57) {
            if (!isEnabled(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
                reportInvalidNumber("Leading zeroes not allowed");
            }
            this._inputPtr++;
            if (i == 48) {
                do {
                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                        break;
                    }
                    i = this._inputBuffer[this._inputPtr] & 255;
                    if (i < 48 || i > 57) {
                        return 48;
                    }
                    this._inputPtr++;
                } while (i == 48);
            }
            return i;
        }
        return 48;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0045, code lost:
        r5 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final org.codehaus.jackson.JsonToken _parseFloatText(char[] r11, int r12, int r13, boolean r14, int r15) throws java.io.IOException, org.codehaus.jackson.JsonParseException {
        /*
            Method dump skipped, instructions count: 257
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.impl.Utf8StreamParser._parseFloatText(char[], int, int, boolean, int):org.codehaus.jackson.JsonToken");
    }

    protected final Name _parseFieldName(int i) throws IOException, JsonParseException {
        if (i != 34) {
            return _handleUnusualFieldName(i);
        }
        if (this._inputPtr + 9 > this._inputEnd) {
            return slowParseFieldName();
        }
        byte[] bArr = this._inputBuffer;
        int[] iArr = sInputCodesLatin1;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        int i3 = bArr[i2] & 255;
        if (iArr[i3] != 0) {
            if (i3 == 34) {
                return BytesToNameCanonicalizer.getEmptyName();
            }
            return parseFieldName(0, i3, 0);
        }
        int i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        int i5 = bArr[i4] & 255;
        if (iArr[i5] != 0) {
            if (i5 == 34) {
                return findName(i3, 1);
            }
            return parseFieldName(i3, i5, 1);
        }
        int i6 = (i3 << 8) | i5;
        int i7 = this._inputPtr;
        this._inputPtr = i7 + 1;
        int i8 = bArr[i7] & 255;
        if (iArr[i8] != 0) {
            if (i8 == 34) {
                return findName(i6, 2);
            }
            return parseFieldName(i6, i8, 2);
        }
        int i9 = (i6 << 8) | i8;
        int i10 = this._inputPtr;
        this._inputPtr = i10 + 1;
        int i11 = bArr[i10] & 255;
        if (iArr[i11] != 0) {
            if (i11 == 34) {
                return findName(i9, 3);
            }
            return parseFieldName(i9, i11, 3);
        }
        int i12 = (i9 << 8) | i11;
        int i13 = this._inputPtr;
        this._inputPtr = i13 + 1;
        int i14 = bArr[i13] & 255;
        if (iArr[i14] == 0) {
            this._quad1 = i12;
            return parseMediumFieldName(i14, iArr);
        } else if (i14 == 34) {
            return findName(i12, 4);
        } else {
            return parseFieldName(i12, i14, 4);
        }
    }

    protected final Name parseMediumFieldName(int i, int[] iArr) throws IOException, JsonParseException {
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        int i3 = bArr[i2] & 255;
        if (iArr[i3] != 0) {
            if (i3 == 34) {
                return findName(this._quad1, i, 1);
            }
            return parseFieldName(this._quad1, i, i3, 1);
        }
        int i4 = (i << 8) | i3;
        byte[] bArr2 = this._inputBuffer;
        int i5 = this._inputPtr;
        this._inputPtr = i5 + 1;
        int i6 = bArr2[i5] & 255;
        if (iArr[i6] != 0) {
            if (i6 == 34) {
                return findName(this._quad1, i4, 2);
            }
            return parseFieldName(this._quad1, i4, i6, 2);
        }
        int i7 = (i4 << 8) | i6;
        byte[] bArr3 = this._inputBuffer;
        int i8 = this._inputPtr;
        this._inputPtr = i8 + 1;
        int i9 = bArr3[i8] & 255;
        if (iArr[i9] != 0) {
            if (i9 == 34) {
                return findName(this._quad1, i7, 3);
            }
            return parseFieldName(this._quad1, i7, i9, 3);
        }
        int i10 = (i7 << 8) | i9;
        byte[] bArr4 = this._inputBuffer;
        int i11 = this._inputPtr;
        this._inputPtr = i11 + 1;
        int i12 = bArr4[i11] & 255;
        if (iArr[i12] != 0) {
            if (i12 == 34) {
                return findName(this._quad1, i10, 4);
            }
            return parseFieldName(this._quad1, i10, i12, 4);
        }
        int[] iArr2 = this._quadBuffer;
        iArr2[0] = this._quad1;
        iArr2[1] = i10;
        return parseLongFieldName(i12);
    }

    protected Name parseLongFieldName(int i) throws IOException, JsonParseException {
        int[] iArr = sInputCodesLatin1;
        int i2 = 2;
        while (this._inputEnd - this._inputPtr >= 4) {
            byte[] bArr = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            int i4 = bArr[i3] & 255;
            if (iArr[i4] != 0) {
                if (i4 == 34) {
                    return findName(this._quadBuffer, i2, i, 1);
                }
                return parseEscapedFieldName(this._quadBuffer, i2, i, i4, 1);
            }
            int i5 = (i << 8) | i4;
            byte[] bArr2 = this._inputBuffer;
            int i6 = this._inputPtr;
            this._inputPtr = i6 + 1;
            int i7 = bArr2[i6] & 255;
            if (iArr[i7] != 0) {
                if (i7 == 34) {
                    return findName(this._quadBuffer, i2, i5, 2);
                }
                return parseEscapedFieldName(this._quadBuffer, i2, i5, i7, 2);
            }
            int i8 = (i5 << 8) | i7;
            byte[] bArr3 = this._inputBuffer;
            int i9 = this._inputPtr;
            this._inputPtr = i9 + 1;
            int i10 = bArr3[i9] & 255;
            if (iArr[i10] != 0) {
                if (i10 == 34) {
                    return findName(this._quadBuffer, i2, i8, 3);
                }
                return parseEscapedFieldName(this._quadBuffer, i2, i8, i10, 3);
            }
            int i11 = (i8 << 8) | i10;
            byte[] bArr4 = this._inputBuffer;
            int i12 = this._inputPtr;
            this._inputPtr = i12 + 1;
            int i13 = bArr4[i12] & 255;
            if (iArr[i13] != 0) {
                if (i13 == 34) {
                    return findName(this._quadBuffer, i2, i11, 4);
                }
                return parseEscapedFieldName(this._quadBuffer, i2, i11, i13, 4);
            }
            int[] iArr2 = this._quadBuffer;
            if (i2 >= iArr2.length) {
                this._quadBuffer = growArrayBy(iArr2, i2);
            }
            this._quadBuffer[i2] = i11;
            i2++;
            i = i13;
        }
        return parseEscapedFieldName(this._quadBuffer, i2, 0, i, 0);
    }

    protected Name slowParseFieldName() throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(": was expecting closing '\"' for name");
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        int i2 = bArr[i] & 255;
        if (i2 == 34) {
            return BytesToNameCanonicalizer.getEmptyName();
        }
        return parseEscapedFieldName(this._quadBuffer, 0, 0, i2, 0);
    }

    private final Name parseFieldName(int i, int i2, int i3) throws IOException, JsonParseException {
        return parseEscapedFieldName(this._quadBuffer, 0, i, i2, i3);
    }

    private final Name parseFieldName(int i, int i2, int i3, int i4) throws IOException, JsonParseException {
        int[] iArr = this._quadBuffer;
        iArr[0] = i;
        return parseEscapedFieldName(iArr, 1, i2, i3, i4);
    }

    protected Name parseEscapedFieldName(int[] iArr, int i, int i2, int i3, int i4) throws IOException, JsonParseException {
        int[] iArr2 = sInputCodesLatin1;
        while (true) {
            if (iArr2[i3] != 0) {
                if (i3 == 34) {
                    break;
                }
                if (i3 != 92) {
                    _throwUnquotedSpace(i3, "name");
                } else {
                    i3 = _decodeEscaped();
                }
                if (i3 > 127) {
                    if (i4 >= 4) {
                        if (i >= iArr.length) {
                            iArr = growArrayBy(iArr, iArr.length);
                            this._quadBuffer = iArr;
                        }
                        iArr[i] = i2;
                        i++;
                        i2 = 0;
                        i4 = 0;
                    }
                    if (i3 < 2048) {
                        i2 = (i2 << 8) | (i3 >> 6) | 192;
                        i4++;
                    } else {
                        int i5 = (i2 << 8) | (i3 >> 12) | 224;
                        int i6 = i4 + 1;
                        if (i6 >= 4) {
                            if (i >= iArr.length) {
                                iArr = growArrayBy(iArr, iArr.length);
                                this._quadBuffer = iArr;
                            }
                            iArr[i] = i5;
                            i++;
                            i5 = 0;
                            i6 = 0;
                        }
                        i2 = (i5 << 8) | ((i3 >> 6) & 63) | 128;
                        i4 = i6 + 1;
                    }
                    i3 = (i3 & 63) | 128;
                }
            }
            if (i4 < 4) {
                i4++;
                i2 = (i2 << 8) | i3;
            } else {
                if (i >= iArr.length) {
                    iArr = growArrayBy(iArr, iArr.length);
                    this._quadBuffer = iArr;
                }
                iArr[i] = i2;
                i2 = i3;
                i++;
                i4 = 1;
            }
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in field name");
            }
            byte[] bArr = this._inputBuffer;
            int i7 = this._inputPtr;
            this._inputPtr = i7 + 1;
            i3 = bArr[i7] & 255;
        }
        if (i4 > 0) {
            if (i >= iArr.length) {
                iArr = growArrayBy(iArr, iArr.length);
                this._quadBuffer = iArr;
            }
            iArr[i] = i2;
            i++;
        }
        Name findName = this._symbols.findName(iArr, i);
        return findName == null ? addName(iArr, i, i4) : findName;
    }

    protected final Name _handleUnusualFieldName(int i) throws IOException, JsonParseException {
        if (i == 39 && isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
            return _parseApostropheFieldName();
        }
        if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            _reportUnexpectedChar(i, "was expecting double-quote to start field name");
        }
        int[] inputCodeUtf8JsNames = CharTypes.getInputCodeUtf8JsNames();
        if (inputCodeUtf8JsNames[i] != 0) {
            _reportUnexpectedChar(i, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int[] iArr = this._quadBuffer;
        int i2 = 0;
        int i3 = i;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (i2 < 4) {
                i2++;
                i5 = (i5 << 8) | i3;
            } else {
                if (i4 >= iArr.length) {
                    iArr = growArrayBy(iArr, iArr.length);
                    this._quadBuffer = iArr;
                }
                iArr[i4] = i5;
                i4++;
                i5 = i3;
                i2 = 1;
            }
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in field name");
            }
            i3 = this._inputBuffer[this._inputPtr] & 255;
            if (inputCodeUtf8JsNames[i3] != 0) {
                break;
            }
            this._inputPtr++;
        }
        if (i2 > 0) {
            if (i4 >= iArr.length) {
                int[] growArrayBy = growArrayBy(iArr, iArr.length);
                this._quadBuffer = growArrayBy;
                iArr = growArrayBy;
            }
            iArr[i4] = i5;
            i4++;
        }
        Name findName = this._symbols.findName(iArr, i4);
        return findName == null ? addName(iArr, i4, i2) : findName;
    }

    protected final Name _parseApostropheFieldName() throws IOException, JsonParseException {
        int i;
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(": was expecting closing ''' for name");
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        int i3 = bArr[i2] & 255;
        if (i3 == 39) {
            return BytesToNameCanonicalizer.getEmptyName();
        }
        int[] iArr = this._quadBuffer;
        int[] iArr2 = sInputCodesLatin1;
        int[] iArr3 = iArr;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i3 != 39) {
            if (i3 != 34 && iArr2[i3] != 0) {
                if (i3 != 92) {
                    _throwUnquotedSpace(i3, "name");
                } else {
                    i3 = _decodeEscaped();
                }
                if (i3 > 127) {
                    if (i4 >= 4) {
                        if (i5 >= iArr3.length) {
                            iArr3 = growArrayBy(iArr3, iArr3.length);
                            this._quadBuffer = iArr3;
                        }
                        iArr3[i5] = i6;
                        i5++;
                        i4 = 0;
                        i6 = 0;
                    }
                    if (i3 < 2048) {
                        i6 = (i6 << 8) | (i3 >> 6) | 192;
                        i4++;
                    } else {
                        int i7 = (i6 << 8) | (i3 >> 12) | 224;
                        int i8 = i4 + 1;
                        if (i8 >= 4) {
                            if (i5 >= iArr3.length) {
                                int[] growArrayBy = growArrayBy(iArr3, iArr3.length);
                                this._quadBuffer = growArrayBy;
                                iArr3 = growArrayBy;
                            }
                            iArr3[i5] = i7;
                            i5++;
                            i8 = 0;
                            i7 = 0;
                        }
                        i6 = (i7 << 8) | ((i3 >> 6) & 63) | 128;
                        i4 = i8 + 1;
                    }
                    i3 = (i3 & 63) | 128;
                }
            }
            if (i4 < 4) {
                i4++;
                i6 = i3 | (i6 << 8);
            } else {
                if (i5 >= iArr3.length) {
                    iArr3 = growArrayBy(iArr3, iArr3.length);
                    this._quadBuffer = iArr3;
                }
                iArr3[i5] = i6;
                i6 = i3;
                i5++;
                i4 = 1;
            }
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in field name");
            }
            byte[] bArr2 = this._inputBuffer;
            int i9 = this._inputPtr;
            this._inputPtr = i9 + 1;
            i3 = bArr2[i9] & 255;
        }
        if (i4 > 0) {
            if (i5 >= iArr3.length) {
                int[] growArrayBy2 = growArrayBy(iArr3, iArr3.length);
                this._quadBuffer = growArrayBy2;
                iArr3 = growArrayBy2;
            }
            i = i5 + 1;
            iArr3[i5] = i6;
        } else {
            i = i5;
        }
        Name findName = this._symbols.findName(iArr3, i);
        return findName == null ? addName(iArr3, i, i4) : findName;
    }

    private final Name findName(int i, int i2) throws JsonParseException {
        Name findName = this._symbols.findName(i);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this._quadBuffer;
        iArr[0] = i;
        return addName(iArr, 1, i2);
    }

    private final Name findName(int i, int i2, int i3) throws JsonParseException {
        Name findName = this._symbols.findName(i, i2);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this._quadBuffer;
        iArr[0] = i;
        iArr[1] = i2;
        return addName(iArr, 2, i3);
    }

    private final Name findName(int[] iArr, int i, int i2, int i3) throws JsonParseException {
        if (i >= iArr.length) {
            iArr = growArrayBy(iArr, iArr.length);
            this._quadBuffer = iArr;
        }
        int i4 = i + 1;
        iArr[i] = i2;
        Name findName = this._symbols.findName(iArr, i4);
        return findName == null ? addName(iArr, i4, i3) : findName;
    }

    private final Name addName(int[] iArr, int i, int i2) throws JsonParseException {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8 = ((i << 2) - 4) + i2;
        if (i2 < 4) {
            int i9 = i - 1;
            i3 = iArr[i9];
            iArr[i9] = i3 << ((4 - i2) << 3);
        } else {
            i3 = 0;
        }
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i10 = 0;
        int i11 = 0;
        while (i10 < i8) {
            int i12 = (iArr[i10 >> 2] >> ((3 - (i10 & 3)) << 3)) & 255;
            i10++;
            if (i12 > 127) {
                if ((i12 & 224) == 192) {
                    i5 = i12 & 31;
                    i4 = 1;
                } else if ((i12 & 240) == 224) {
                    i5 = i12 & 15;
                    i4 = 2;
                } else if ((i12 & 248) == 240) {
                    i5 = i12 & 7;
                    i4 = 3;
                } else {
                    _reportInvalidInitial(i12);
                    i4 = 1;
                    i5 = 1;
                }
                if (i10 + i4 > i8) {
                    _reportInvalidEOF(" in field name");
                }
                int i13 = iArr[i10 >> 2] >> ((3 - (i10 & 3)) << 3);
                i10++;
                if ((i13 & 192) != 128) {
                    _reportInvalidOther(i13);
                }
                int i14 = (i5 << 6) | (i13 & 63);
                if (i4 > 1) {
                    int i15 = iArr[i10 >> 2] >> ((3 - (i10 & 3)) << 3);
                    i10++;
                    if ((i15 & 192) != 128) {
                        _reportInvalidOther(i15);
                    }
                    i6 = (i15 & 63) | (i14 << 6);
                    i7 = 2;
                    if (i4 > 2) {
                        int i16 = iArr[i10 >> 2] >> ((3 - (i10 & 3)) << 3);
                        i10++;
                        if ((i16 & 192) != 128) {
                            _reportInvalidOther(i16 & 255);
                        }
                        i6 = (i6 << 6) | (i16 & 63);
                        i7 = 2;
                    }
                } else {
                    i6 = i14;
                    i7 = 2;
                }
                if (i4 > i7) {
                    int i17 = i6 - 65536;
                    if (i11 >= emptyAndGetCurrentSegment.length) {
                        emptyAndGetCurrentSegment = this._textBuffer.expandCurrentSegment();
                    }
                    emptyAndGetCurrentSegment[i11] = (char) ((i17 >> 10) + 55296);
                    i12 = (i17 & 1023) | 56320;
                    i11++;
                } else {
                    i12 = i6;
                }
            }
            if (i11 >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.expandCurrentSegment();
            }
            emptyAndGetCurrentSegment[i11] = (char) i12;
            i11++;
        }
        String str = new String(emptyAndGetCurrentSegment, 0, i11);
        if (i2 < 4) {
            iArr[i - 1] = i3;
        }
        return this._symbols.addName(str, iArr, i);
    }

    @Override // org.codehaus.jackson.impl.JsonParserBase
    protected void _finishString() throws IOException, JsonParseException {
        int i = this._inputPtr;
        if (i >= this._inputEnd) {
            loadMoreGuaranteed();
            i = this._inputPtr;
        }
        int i2 = 0;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = sInputCodesUtf8;
        int min = Math.min(this._inputEnd, emptyAndGetCurrentSegment.length + i);
        byte[] bArr = this._inputBuffer;
        while (true) {
            if (i >= min) {
                break;
            }
            int i3 = bArr[i] & 255;
            if (iArr[i3] == 0) {
                i++;
                emptyAndGetCurrentSegment[i2] = (char) i3;
                i2++;
            } else if (i3 == 34) {
                this._inputPtr = i + 1;
                this._textBuffer.setCurrentLength(i2);
                return;
            }
        }
        this._inputPtr = i;
        _finishString2(emptyAndGetCurrentSegment, i2);
    }

    private final void _finishString2(char[] cArr, int i) throws IOException, JsonParseException {
        int[] iArr = sInputCodesUtf8;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int i2 = this._inputPtr;
            if (i2 >= this._inputEnd) {
                loadMoreGuaranteed();
                i2 = this._inputPtr;
            }
            if (i >= cArr.length) {
                cArr = this._textBuffer.finishCurrentSegment();
                i = 0;
            }
            int min = Math.min(this._inputEnd, (cArr.length - i) + i2);
            while (true) {
                if (i2 < min) {
                    int i3 = i2 + 1;
                    int i4 = bArr[i2] & 255;
                    if (iArr[i4] != 0) {
                        this._inputPtr = i3;
                        if (i4 != 34) {
                            switch (iArr[i4]) {
                                case 1:
                                    i4 = _decodeEscaped();
                                    break;
                                case 2:
                                    i4 = _decodeUtf8_2(i4);
                                    break;
                                case 3:
                                    if (this._inputEnd - this._inputPtr >= 2) {
                                        i4 = _decodeUtf8_3fast(i4);
                                        break;
                                    } else {
                                        i4 = _decodeUtf8_3(i4);
                                        break;
                                    }
                                case 4:
                                    int _decodeUtf8_4 = _decodeUtf8_4(i4);
                                    int i5 = i + 1;
                                    cArr[i] = (char) (55296 | (_decodeUtf8_4 >> 10));
                                    if (i5 >= cArr.length) {
                                        cArr = this._textBuffer.finishCurrentSegment();
                                        i5 = 0;
                                    }
                                    i4 = (_decodeUtf8_4 & 1023) | 56320;
                                    i = i5;
                                    break;
                                default:
                                    if (i4 < 32) {
                                        _throwUnquotedSpace(i4, "string value");
                                        break;
                                    } else {
                                        _reportInvalidChar(i4);
                                        break;
                                    }
                            }
                            if (i >= cArr.length) {
                                cArr = this._textBuffer.finishCurrentSegment();
                                i = 0;
                            }
                            cArr[i] = (char) i4;
                            i++;
                        } else {
                            this._textBuffer.setCurrentLength(i);
                            return;
                        }
                    } else {
                        cArr[i] = (char) i4;
                        i2 = i3;
                        i++;
                    }
                } else {
                    this._inputPtr = i2;
                }
            }
        }
    }

    protected void _skipString() throws IOException, JsonParseException {
        this._tokenIncomplete = false;
        int[] iArr = sInputCodesUtf8;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int i = this._inputPtr;
            int i2 = this._inputEnd;
            if (i >= i2) {
                loadMoreGuaranteed();
                i = this._inputPtr;
                i2 = this._inputEnd;
            }
            while (true) {
                if (i < i2) {
                    int i3 = i + 1;
                    int i4 = bArr[i] & 255;
                    if (iArr[i4] != 0) {
                        this._inputPtr = i3;
                        if (i4 == 34) {
                            return;
                        }
                        switch (iArr[i4]) {
                            case 1:
                                _decodeEscaped();
                                continue;
                            case 2:
                                _skipUtf8_2(i4);
                                continue;
                            case 3:
                                _skipUtf8_3(i4);
                                continue;
                            case 4:
                                _skipUtf8_4(i4);
                                continue;
                            default:
                                if (i4 < 32) {
                                    _throwUnquotedSpace(i4, "string value");
                                    break;
                                } else {
                                    _reportInvalidChar(i4);
                                    continue;
                                }
                        }
                    } else {
                        i = i3;
                    }
                } else {
                    this._inputPtr = i;
                }
            }
        }
    }

    protected JsonToken _handleUnexpectedValue(int i) throws IOException, JsonParseException {
        if (i != 39) {
            if (i == 43) {
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    _reportInvalidEOFInValue();
                }
                byte[] bArr = this._inputBuffer;
                int i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                return _handleInvalidNumberStart(bArr[i2] & 255, false);
            } else if (i == 78) {
                _matchToken("NaN", 1);
                if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return resetAsNaN("NaN", Double.NaN);
                }
                _reportError("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            }
        } else if (isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
            return _handleApostropheValue();
        }
        _reportUnexpectedChar(i, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
        return null;
    }

    protected JsonToken _handleApostropheValue() throws IOException, JsonParseException {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = sInputCodesUtf8;
        byte[] bArr = this._inputBuffer;
        int i = 0;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            if (i >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                i = 0;
            }
            int i2 = this._inputEnd;
            int length = this._inputPtr + (emptyAndGetCurrentSegment.length - i);
            if (length < i2) {
                i2 = length;
            }
            while (this._inputPtr < i2) {
                int i3 = this._inputPtr;
                this._inputPtr = i3 + 1;
                int i4 = bArr[i3] & 255;
                if (i4 != 39 && iArr[i4] == 0) {
                    emptyAndGetCurrentSegment[i] = (char) i4;
                    i++;
                } else if (i4 != 39) {
                    switch (iArr[i4]) {
                        case 1:
                            if (i4 != 34) {
                                i4 = _decodeEscaped();
                                break;
                            }
                            break;
                        case 2:
                            i4 = _decodeUtf8_2(i4);
                            break;
                        case 3:
                            if (this._inputEnd - this._inputPtr >= 2) {
                                i4 = _decodeUtf8_3fast(i4);
                                break;
                            } else {
                                i4 = _decodeUtf8_3(i4);
                                break;
                            }
                        case 4:
                            int _decodeUtf8_4 = _decodeUtf8_4(i4);
                            int i5 = i + 1;
                            emptyAndGetCurrentSegment[i] = (char) (55296 | (_decodeUtf8_4 >> 10));
                            if (i5 >= emptyAndGetCurrentSegment.length) {
                                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                                i5 = 0;
                            }
                            int i6 = i5;
                            i4 = 56320 | (_decodeUtf8_4 & 1023);
                            i = i6;
                            break;
                        default:
                            if (i4 < 32) {
                                _throwUnquotedSpace(i4, "string value");
                            }
                            _reportInvalidChar(i4);
                            break;
                    }
                    if (i >= emptyAndGetCurrentSegment.length) {
                        emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                        i = 0;
                    }
                    emptyAndGetCurrentSegment[i] = (char) i4;
                    i++;
                } else {
                    this._textBuffer.setCurrentLength(i);
                    return JsonToken.VALUE_STRING;
                }
            }
        }
    }

    protected JsonToken _handleInvalidNumberStart(int i, boolean z) throws IOException, JsonParseException {
        if (i == 73) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOFInValue();
            }
            byte[] bArr = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            i = bArr[i2];
            if (i == 78) {
                String str = z ? "-INF" : "+INF";
                _matchToken(str, 3);
                if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return resetAsNaN(str, z ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
                }
                _reportError("Non-standard token '" + str + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            } else if (i == 110) {
                String str2 = z ? "-Infinity" : "+Infinity";
                _matchToken(str2, 3);
                if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return resetAsNaN(str2, z ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
                }
                _reportError("Non-standard token '" + str2 + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            }
        }
        reportUnexpectedNumberChar(i, "expected digit (0-9) to follow minus sign, for valid numeric value");
        return null;
    }

    protected final void _matchToken(String str, int i) throws IOException, JsonParseException {
        int i2;
        int length = str.length();
        do {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in a value");
            }
            if (this._inputBuffer[this._inputPtr] != str.charAt(i)) {
                _reportInvalidToken(str.substring(0, i), "'null', 'true', 'false' or NaN");
            }
            this._inputPtr++;
            i++;
        } while (i < length);
        if ((this._inputPtr < this._inputEnd || loadMore()) && (i2 = this._inputBuffer[this._inputPtr] & 255) >= 48 && i2 != 93 && i2 != 125 && Character.isJavaIdentifierPart((char) _decodeCharForError(i2))) {
            this._inputPtr++;
            _reportInvalidToken(str.substring(0, i), "'null', 'true', 'false' or NaN");
        }
    }

    protected void _reportInvalidToken(String str, String str2) throws IOException, JsonParseException {
        StringBuilder sb = new StringBuilder(str);
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                break;
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char _decodeCharForError = (char) _decodeCharForError(bArr[i]);
            if (!Character.isJavaIdentifierPart(_decodeCharForError)) {
                break;
            }
            sb.append(_decodeCharForError);
        }
        _reportError("Unrecognized token '" + sb.toString() + "': was expecting " + str2);
    }

    private final int _skipWS() throws IOException, JsonParseException {
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                int i2 = bArr[i] & 255;
                if (i2 > 32) {
                    if (i2 != 47) {
                        return i2;
                    }
                    _skipComment();
                } else if (i2 != 32) {
                    if (i2 == 10) {
                        _skipLF();
                    } else if (i2 == 13) {
                        _skipCR();
                    } else if (i2 != 9) {
                        _throwInvalidSpace(i2);
                    }
                }
            } else {
                throw _constructError("Unexpected end-of-input within/between " + this._parsingContext.getTypeDesc() + " entries");
            }
        }
    }

    private final int _skipWSOrEnd() throws IOException, JsonParseException {
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                int i2 = bArr[i] & 255;
                if (i2 > 32) {
                    if (i2 != 47) {
                        return i2;
                    }
                    _skipComment();
                } else if (i2 != 32) {
                    if (i2 == 10) {
                        _skipLF();
                    } else if (i2 == 13) {
                        _skipCR();
                    } else if (i2 != 9) {
                        _throwInvalidSpace(i2);
                    }
                }
            } else {
                _handleEOF();
                return -1;
            }
        }
    }

    private final int _skipColon() throws IOException, JsonParseException {
        int i;
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if (b != 58) {
            int i3 = b & 255;
            while (true) {
                if (i3 != 13 && i3 != 32) {
                    if (i3 != 47) {
                        switch (i3) {
                            case 9:
                                break;
                            case 10:
                                _skipLF();
                                break;
                            default:
                                if (i3 < 32) {
                                    _throwInvalidSpace(i3);
                                }
                                if (this._inputPtr >= this._inputEnd) {
                                    loadMoreGuaranteed();
                                }
                                byte[] bArr2 = this._inputBuffer;
                                int i4 = this._inputPtr;
                                this._inputPtr = i4 + 1;
                                int i5 = bArr2[i4] & 255;
                                if (i5 != 58) {
                                    _reportUnexpectedChar(i5, "was expecting a colon to separate field name and value");
                                    break;
                                }
                                break;
                        }
                    } else {
                        _skipComment();
                    }
                }
                _skipCR();
            }
        } else if (this._inputPtr < this._inputEnd && (i = this._inputBuffer[this._inputPtr] & 255) > 32 && i != 47) {
            this._inputPtr++;
            return i;
        }
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                byte[] bArr3 = this._inputBuffer;
                int i6 = this._inputPtr;
                this._inputPtr = i6 + 1;
                int i7 = bArr3[i6] & 255;
                if (i7 > 32) {
                    if (i7 != 47) {
                        return i7;
                    }
                    _skipComment();
                } else if (i7 != 32) {
                    if (i7 == 10) {
                        _skipLF();
                    } else if (i7 == 13) {
                        _skipCR();
                    } else if (i7 != 9) {
                        _throwInvalidSpace(i7);
                    }
                }
            } else {
                throw _constructError("Unexpected end-of-input within/between " + this._parsingContext.getTypeDesc() + " entries");
            }
        }
    }

    private final void _skipComment() throws IOException, JsonParseException {
        if (!isEnabled(JsonParser.Feature.ALLOW_COMMENTS)) {
            _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in a comment");
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        int i2 = bArr[i] & 255;
        if (i2 == 47) {
            _skipCppComment();
        } else if (i2 == 42) {
            _skipCComment();
        } else {
            _reportUnexpectedChar(i2, "was expecting either '*' or '/' for a comment");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x004b, code lost:
        _reportInvalidEOF(" in a comment");
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0050, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void _skipCComment() throws java.io.IOException, org.codehaus.jackson.JsonParseException {
        /*
            r4 = this;
            int[] r0 = org.codehaus.jackson.util.CharTypes.getInputCodeComment()
        L4:
            int r1 = r4._inputPtr
            int r2 = r4._inputEnd
            if (r1 < r2) goto L10
            boolean r1 = r4.loadMore()
            if (r1 == 0) goto L4b
        L10:
            byte[] r1 = r4._inputBuffer
            int r2 = r4._inputPtr
            int r3 = r2 + 1
            r4._inputPtr = r3
            r1 = r1[r2]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r2 = r0[r1]
            if (r2 == 0) goto L4
            r3 = 10
            if (r2 == r3) goto L66
            r3 = 13
            if (r2 == r3) goto L62
            r3 = 42
            if (r2 == r3) goto L3f
            switch(r2) {
                case 2: goto L3b;
                case 3: goto L37;
                case 4: goto L33;
                default: goto L2f;
            }
        L2f:
            r4._reportInvalidChar(r1)
            goto L4
        L33:
            r4._skipUtf8_4(r1)
            goto L4
        L37:
            r4._skipUtf8_3(r1)
            goto L4
        L3b:
            r4._skipUtf8_2(r1)
            goto L4
        L3f:
            int r1 = r4._inputPtr
            int r2 = r4._inputEnd
            if (r1 < r2) goto L51
            boolean r1 = r4.loadMore()
            if (r1 != 0) goto L51
        L4b:
            java.lang.String r0 = " in a comment"
            r4._reportInvalidEOF(r0)
            return
        L51:
            byte[] r1 = r4._inputBuffer
            int r2 = r4._inputPtr
            r1 = r1[r2]
            r2 = 47
            if (r1 != r2) goto L4
            int r0 = r4._inputPtr
            int r0 = r0 + 1
            r4._inputPtr = r0
            return
        L62:
            r4._skipCR()
            goto L4
        L66:
            r4._skipLF()
            goto L4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.impl.Utf8StreamParser._skipCComment():void");
    }

    private final void _skipCppComment() throws IOException, JsonParseException {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                return;
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int i2 = bArr[i] & 255;
            int i3 = inputCodeComment[i2];
            if (i3 != 0) {
                if (i3 == 10) {
                    _skipLF();
                    return;
                } else if (i3 == 13) {
                    _skipCR();
                    return;
                } else if (i3 != 42) {
                    switch (i3) {
                        case 2:
                            _skipUtf8_2(i2);
                            continue;
                        case 3:
                            _skipUtf8_3(i2);
                            continue;
                        case 4:
                            _skipUtf8_4(i2);
                            continue;
                        default:
                            _reportInvalidChar(i2);
                            continue;
                    }
                }
            }
        }
    }

    @Override // org.codehaus.jackson.impl.JsonParserBase
    protected final char _decodeEscaped() throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in character escape sequence");
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte b = bArr[i];
        if (b == 34 || b == 47 || b == 92) {
            return (char) b;
        }
        if (b != 98) {
            if (b != 102) {
                if (b != 110) {
                    if (b != 114) {
                        switch (b) {
                            case 116:
                                return '\t';
                            case 117:
                                int i2 = 0;
                                for (int i3 = 0; i3 < 4; i3++) {
                                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                                        _reportInvalidEOF(" in character escape sequence");
                                    }
                                    byte[] bArr2 = this._inputBuffer;
                                    int i4 = this._inputPtr;
                                    this._inputPtr = i4 + 1;
                                    byte b2 = bArr2[i4];
                                    int charToHex = CharTypes.charToHex(b2);
                                    if (charToHex < 0) {
                                        _reportUnexpectedChar(b2, "expected a hex-digit for character escape sequence");
                                    }
                                    i2 = (i2 << 4) | charToHex;
                                }
                                return (char) i2;
                            default:
                                return _handleUnrecognizedCharacterEscape((char) _decodeCharForError(b));
                        }
                    }
                    return '\r';
                }
                return '\n';
            }
            return '\f';
        }
        return '\b';
    }

    protected int _decodeCharForError(int i) throws IOException, JsonParseException {
        char c;
        if (i < 0) {
            if ((i & 224) == 192) {
                i &= 31;
                c = 1;
            } else if ((i & 240) == 224) {
                i &= 15;
                c = 2;
            } else if ((i & 248) == 240) {
                i &= 7;
                c = 3;
            } else {
                _reportInvalidInitial(i & 255);
                c = 1;
            }
            int nextByte = nextByte();
            if ((nextByte & 192) != 128) {
                _reportInvalidOther(nextByte & 255);
            }
            int i2 = (i << 6) | (nextByte & 63);
            if (c > 1) {
                int nextByte2 = nextByte();
                if ((nextByte2 & 192) != 128) {
                    _reportInvalidOther(nextByte2 & 255);
                }
                int i3 = (i2 << 6) | (nextByte2 & 63);
                if (c > 2) {
                    int nextByte3 = nextByte();
                    if ((nextByte3 & 192) != 128) {
                        _reportInvalidOther(nextByte3 & 255);
                    }
                    return (i3 << 6) | (nextByte3 & 63);
                }
                return i3;
            }
            return i2;
        }
        return i;
    }

    private final int _decodeUtf8_2(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        return ((i & 31) << 6) | (b & 63);
    }

    private final int _decodeUtf8_3(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        int i2 = i & 15;
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b = bArr[i3];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        int i4 = (i2 << 6) | (b & 63);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i5 = this._inputPtr;
        this._inputPtr = i5 + 1;
        byte b2 = bArr2[i5];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & 255, this._inputPtr);
        }
        return (i4 << 6) | (b2 & 63);
    }

    private final int _decodeUtf8_3fast(int i) throws IOException, JsonParseException {
        int i2 = i & 15;
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b = bArr[i3];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        int i4 = (i2 << 6) | (b & 63);
        byte[] bArr2 = this._inputBuffer;
        int i5 = this._inputPtr;
        this._inputPtr = i5 + 1;
        byte b2 = bArr2[i5];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & 255, this._inputPtr);
        }
        return (i4 << 6) | (b2 & 63);
    }

    private final int _decodeUtf8_4(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        int i3 = ((i & 7) << 6) | (b & 63);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        byte b2 = bArr2[i4];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & 255, this._inputPtr);
        }
        int i5 = (i3 << 6) | (b2 & 63);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i6 = this._inputPtr;
        this._inputPtr = i6 + 1;
        byte b3 = bArr3[i6];
        if ((b3 & 192) != 128) {
            _reportInvalidOther(b3 & 255, this._inputPtr);
        }
        return ((i5 << 6) | (b3 & 63)) - 65536;
    }

    private final void _skipUtf8_2(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
    }

    private final void _skipUtf8_3(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b2 = bArr2[i3];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & 255, this._inputPtr);
        }
    }

    private final void _skipUtf8_4(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b2 = bArr2[i3];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & 255, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        byte b3 = bArr3[i4];
        if ((b3 & 192) != 128) {
            _reportInvalidOther(b3 & 255, this._inputPtr);
        }
    }

    protected final void _skipCR() throws IOException {
        if ((this._inputPtr < this._inputEnd || loadMore()) && this._inputBuffer[this._inputPtr] == 10) {
            this._inputPtr++;
        }
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
    }

    protected final void _skipLF() throws IOException {
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
    }

    private int nextByte() throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        return bArr[i] & 255;
    }

    protected void _reportInvalidChar(int i) throws JsonParseException {
        if (i < 32) {
            _throwInvalidSpace(i);
        }
        _reportInvalidInitial(i);
    }

    protected void _reportInvalidInitial(int i) throws JsonParseException {
        _reportError("Invalid UTF-8 start byte 0x" + Integer.toHexString(i));
    }

    protected void _reportInvalidOther(int i) throws JsonParseException {
        _reportError("Invalid UTF-8 middle byte 0x" + Integer.toHexString(i));
    }

    protected void _reportInvalidOther(int i, int i2) throws JsonParseException {
        this._inputPtr = i2;
        _reportInvalidOther(i);
    }

    public static int[] growArrayBy(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        int length = iArr.length;
        int[] iArr2 = new int[i + length];
        System.arraycopy(iArr, 0, iArr2, 0, length);
        return iArr2;
    }

    protected byte[] _decodeBase64(Base64Variant base64Variant) throws IOException, JsonParseException {
        ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int i2 = bArr[i] & 255;
            if (i2 > 32) {
                int decodeBase64Char = base64Variant.decodeBase64Char(i2);
                if (decodeBase64Char < 0) {
                    if (i2 == 34) {
                        return _getByteArrayBuilder.toByteArray();
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant, i2, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr2 = this._inputBuffer;
                int i3 = this._inputPtr;
                this._inputPtr = i3 + 1;
                int i4 = bArr2[i3] & 255;
                int decodeBase64Char2 = base64Variant.decodeBase64Char(i4);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant, i4, 1);
                }
                int i5 = (decodeBase64Char << 6) | decodeBase64Char2;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int i6 = this._inputPtr;
                this._inputPtr = i6 + 1;
                int i7 = bArr3[i6] & 255;
                int decodeBase64Char3 = base64Variant.decodeBase64Char(i7);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (i7 == 34 && !base64Variant.usesPadding()) {
                            _getByteArrayBuilder.append(i5 >> 4);
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char3 = _decodeBase64Escape(base64Variant, i7, 2);
                    }
                    if (decodeBase64Char3 == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        byte[] bArr4 = this._inputBuffer;
                        int i8 = this._inputPtr;
                        this._inputPtr = i8 + 1;
                        int i9 = bArr4[i8] & 255;
                        if (!base64Variant.usesPaddingChar(i9)) {
                            throw reportInvalidBase64Char(base64Variant, i9, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
                        }
                        _getByteArrayBuilder.append(i5 >> 4);
                    }
                }
                int i10 = (i5 << 6) | decodeBase64Char3;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr5 = this._inputBuffer;
                int i11 = this._inputPtr;
                this._inputPtr = i11 + 1;
                int i12 = bArr5[i11] & 255;
                int decodeBase64Char4 = base64Variant.decodeBase64Char(i12);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (i12 == 34 && !base64Variant.usesPadding()) {
                            _getByteArrayBuilder.appendTwoBytes(i10 >> 2);
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char4 = _decodeBase64Escape(base64Variant, i12, 3);
                    }
                    if (decodeBase64Char4 == -2) {
                        _getByteArrayBuilder.appendTwoBytes(i10 >> 2);
                    }
                }
                _getByteArrayBuilder.appendThreeBytes((i10 << 6) | decodeBase64Char4);
            }
        }
    }
}
