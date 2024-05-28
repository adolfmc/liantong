package com.p210hp.hpl.sparta;

import java.io.IOException;
import java.io.Reader;
import java.util.Hashtable;

/* renamed from: com.hp.hpl.sparta.ParseCharStream */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
class ParseCharStream implements ParseSource {
    private static final char[] BEGIN_CDATA;
    private static final char[] BEGIN_ETAG;
    private static final char[] CHARREF_BEGIN;
    private static final char[] COMMENT_BEGIN;
    private static final char[] COMMENT_END;
    private static final boolean DEBUG = true;
    private static final char[] DOCTYPE_BEGIN;
    private static final char[] ENCODING;
    private static final char[] END_CDATA;
    private static final char[] END_EMPTYTAG;
    private static final char[] ENTITY_BEGIN;
    public static final int HISTORY_LENGTH = 100;
    private static final boolean H_DEBUG = false;
    private static final char[] MARKUPDECL_BEGIN;
    private static final int MAX_COMMON_CHAR = 128;
    private static final char[] NDATA;
    private static final char[] PI_BEGIN;
    private static final char[] PUBLIC;
    private static final char[] QU_END;
    private static final char[] SYSTEM;
    private static final int TMP_BUF_SIZE = 255;
    private static final char[] VERSION;
    private static final char[] VERSIONNUM_PUNC_CHARS;
    private static final char[] XML_BEGIN;
    private final int CBUF_SIZE;
    private final char[] cbuf_;
    private int ch_;
    private int curPos_;
    private String docTypeName_;
    private final String encoding_;
    private int endPos_;
    private final Hashtable entities_;
    private boolean eos_;
    private final ParseHandler handler_;
    private final CharCircBuffer history_;
    private boolean isExternalDtd_;
    private int lineNumber_;
    private final ParseLog log_;
    private final Hashtable pes_;
    private final Reader reader_;
    private String systemId_;
    private final char[] tmpBuf_;
    private static final char[] NAME_PUNCT_CHARS = {'.', '-', '_', ':'};
    private static final boolean[] IS_NAME_CHAR = new boolean[128];

    private static boolean isExtender(char c) {
        if (c == 183 || c == 903 || c == 1600 || c == 3654 || c == 3782 || c == 12293) {
            return true;
        }
        switch (c) {
            case 720:
            case 721:
                return true;
            default:
                switch (c) {
                    case 12337:
                    case 12338:
                    case 12339:
                    case 12340:
                    case 12341:
                        return true;
                    default:
                        switch (c) {
                            case 12445:
                            case 12446:
                                return true;
                            default:
                                switch (c) {
                                    case 12540:
                                    case 12541:
                                    case 12542:
                                        return true;
                                    default:
                                        return false;
                                }
                        }
                }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String getHistory() {
        return "";
    }

    public ParseCharStream(String str, char[] cArr, ParseLog parseLog, String str2, ParseHandler parseHandler) throws ParseException, EncodingMismatchException, IOException {
        this(str, null, cArr, parseLog, str2, parseHandler);
    }

    public ParseCharStream(String str, Reader reader, ParseLog parseLog, String str2, ParseHandler parseHandler) throws ParseException, EncodingMismatchException, IOException {
        this(str, reader, null, parseLog, str2, parseHandler);
    }

    public ParseCharStream(String str, Reader reader, char[] cArr, ParseLog parseLog, String str2, ParseHandler parseHandler) throws ParseException, EncodingMismatchException, IOException {
        this.docTypeName_ = null;
        this.entities_ = new Hashtable();
        this.pes_ = new Hashtable();
        this.ch_ = -2;
        this.isExternalDtd_ = false;
        this.CBUF_SIZE = 1024;
        this.curPos_ = 0;
        this.endPos_ = 0;
        this.eos_ = false;
        this.tmpBuf_ = new char[255];
        this.lineNumber_ = -1;
        this.lineNumber_ = 1;
        this.history_ = null;
        this.log_ = parseLog == null ? DEFAULT_LOG : parseLog;
        this.encoding_ = str2 == null ? null : str2.toLowerCase();
        this.entities_.put("lt", "<");
        this.entities_.put("gt", ">");
        this.entities_.put("amp", "&");
        this.entities_.put("apos", "'");
        this.entities_.put("quot", "\"");
        if (cArr != null) {
            this.cbuf_ = cArr;
            this.curPos_ = 0;
            this.endPos_ = this.cbuf_.length;
            this.eos_ = true;
            this.reader_ = null;
        } else {
            this.reader_ = reader;
            this.cbuf_ = new char[1024];
            fillBuf();
        }
        this.systemId_ = str;
        this.handler_ = parseHandler;
        this.handler_.setParseSource(this);
        readProlog();
        this.handler_.startDocument();
        Element readElement = readElement();
        String str3 = this.docTypeName_;
        if (str3 != null && !str3.equals(readElement.getTagName())) {
            ParseLog parseLog2 = this.log_;
            parseLog2.warning("DOCTYPE name \"" + this.docTypeName_ + "\" not same as tag name, \"" + readElement.getTagName() + "\" of root element", this.systemId_, getLineNumber());
        }
        while (isMisc()) {
            readMisc();
        }
        Reader reader2 = this.reader_;
        if (reader2 != null) {
            reader2.close();
        }
        this.handler_.endDocument();
    }

    @Override // com.p210hp.hpl.sparta.ParseSource
    public String toString() {
        return this.systemId_;
    }

    @Override // com.p210hp.hpl.sparta.ParseSource
    public String getSystemId() {
        return this.systemId_;
    }

    @Override // com.p210hp.hpl.sparta.ParseSource
    public int getLineNumber() {
        return this.lineNumber_;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getLastCharRead() {
        return this.ch_;
    }

    private int fillBuf() throws IOException {
        if (this.eos_) {
            return -1;
        }
        if (this.endPos_ == this.cbuf_.length) {
            this.endPos_ = 0;
            this.curPos_ = 0;
        }
        Reader reader = this.reader_;
        char[] cArr = this.cbuf_;
        int i = this.endPos_;
        int read = reader.read(cArr, i, cArr.length - i);
        if (read <= 0) {
            this.eos_ = true;
            return -1;
        }
        this.endPos_ += read;
        return read;
    }

    private int fillBuf(int i) throws IOException {
        int i2;
        int i3;
        int i4;
        if (this.eos_) {
            return -1;
        }
        if (this.cbuf_.length - this.curPos_ < i) {
            int i5 = 0;
            while (true) {
                i3 = this.curPos_;
                int i6 = i3 + i5;
                i4 = this.endPos_;
                if (i6 >= i4) {
                    break;
                }
                char[] cArr = this.cbuf_;
                cArr[i5] = cArr[i3 + i5];
                i5++;
            }
            i2 = i4 - i3;
            this.endPos_ = i2;
            this.curPos_ = 0;
        } else {
            i2 = 0;
        }
        int fillBuf = fillBuf();
        if (fillBuf == -1) {
            if (i2 == 0) {
                return -1;
            }
            return i2;
        }
        return i2 + fillBuf;
    }

    private final char readChar() throws ParseException, IOException {
        if (this.curPos_ < this.endPos_ || fillBuf() != -1) {
            if (this.cbuf_[this.curPos_] == '\n') {
                this.lineNumber_++;
            }
            char[] cArr = this.cbuf_;
            int i = this.curPos_;
            this.curPos_ = i + 1;
            return cArr[i];
        }
        throw new ParseException(this, "unexpected end of expression.");
    }

    private final char peekChar() throws ParseException, IOException {
        if (this.curPos_ < this.endPos_ || fillBuf() != -1) {
            return this.cbuf_[this.curPos_];
        }
        throw new ParseException(this, "unexpected end of expression.");
    }

    private final void readChar(char c) throws ParseException, IOException {
        char readChar = readChar();
        if (readChar != c) {
            throw new ParseException(this, readChar, c);
        }
    }

    private final boolean isChar(char c) throws ParseException, IOException {
        if (this.curPos_ < this.endPos_ || fillBuf() != -1) {
            return this.cbuf_[this.curPos_] == c;
        }
        throw new ParseException(this, "unexpected end of expression.");
    }

    private final char readChar(char c, char c2) throws ParseException, IOException {
        char readChar = readChar();
        if (readChar == c || readChar == c2) {
            return readChar;
        }
        throw new ParseException(this, readChar, new char[]{c, c2});
    }

    private final char readChar(char c, char c2, char c3, char c4) throws ParseException, IOException {
        char readChar = readChar();
        if (readChar == c || readChar == c2 || readChar == c3 || readChar == c4) {
            return readChar;
        }
        throw new ParseException(this, readChar, new char[]{c, c2, c3, c4});
    }

    private final boolean isChar(char c, char c2) throws ParseException, IOException {
        if (this.curPos_ < this.endPos_ || fillBuf() != -1) {
            char c3 = this.cbuf_[this.curPos_];
            return c3 == c || c3 == c2;
        }
        return false;
    }

    private final boolean isChar(char c, char c2, char c3, char c4) throws ParseException, IOException {
        if (this.curPos_ < this.endPos_ || fillBuf() != -1) {
            char c5 = this.cbuf_[this.curPos_];
            return c5 == c || c5 == c2 || c5 == c3 || c5 == c4;
        }
        return false;
    }

    private static final boolean isIn(char c, char[] cArr) {
        for (char c2 : cArr) {
            if (c == c2) {
                return true;
            }
        }
        return false;
    }

    private final void readS() throws ParseException, IOException {
        readChar(' ', '\t', '\r', '\n');
        while (isChar(' ', '\t', '\r', '\n')) {
            readChar();
        }
    }

    private final boolean isS() throws ParseException, IOException {
        return isChar(' ', '\t', '\r', '\n');
    }

    static {
        for (char c = 0; c < 128; c = (char) (c + 1)) {
            IS_NAME_CHAR[c] = isNameChar(c);
        }
        COMMENT_BEGIN = "<!--".toCharArray();
        COMMENT_END = "-->".toCharArray();
        PI_BEGIN = "<?".toCharArray();
        QU_END = "?>".toCharArray();
        DOCTYPE_BEGIN = "<!DOCTYPE".toCharArray();
        XML_BEGIN = "<?xml".toCharArray();
        ENCODING = "encoding".toCharArray();
        VERSION = "version".toCharArray();
        VERSIONNUM_PUNC_CHARS = new char[]{'_', '.', ':', '-'};
        MARKUPDECL_BEGIN = "<!".toCharArray();
        CHARREF_BEGIN = "&#".toCharArray();
        ENTITY_BEGIN = "<!ENTITY".toCharArray();
        NDATA = "NDATA".toCharArray();
        SYSTEM = "SYSTEM".toCharArray();
        PUBLIC = "PUBLIC".toCharArray();
        BEGIN_CDATA = "<![CDATA[".toCharArray();
        END_CDATA = "]]>".toCharArray();
        END_EMPTYTAG = "/>".toCharArray();
        BEGIN_ETAG = "</".toCharArray();
    }

    private boolean isNameChar() throws ParseException, IOException {
        char peekChar = peekChar();
        return peekChar < 128 ? IS_NAME_CHAR[peekChar] : isNameChar(peekChar);
    }

    private static boolean isLetter(char c) {
        return "abcdefghijklmnopqrstuvwxyz".indexOf(Character.toLowerCase(c)) != -1;
    }

    private static boolean isNameChar(char c) {
        return Character.isDigit(c) || isLetter(c) || isIn(c, NAME_PUNCT_CHARS) || isExtender(c);
    }

    private final String readName() throws ParseException, IOException {
        this.tmpBuf_[0] = readNameStartChar();
        int i = 1;
        StringBuffer stringBuffer = null;
        while (isNameChar()) {
            if (i >= 255) {
                if (stringBuffer == null) {
                    stringBuffer = new StringBuffer(i);
                    stringBuffer.append(this.tmpBuf_, 0, i);
                } else {
                    stringBuffer.append(this.tmpBuf_, 0, i);
                }
                i = 0;
            }
            this.tmpBuf_[i] = readChar();
            i++;
        }
        if (stringBuffer == null) {
            return Sparta.intern(new String(this.tmpBuf_, 0, i));
        }
        stringBuffer.append(this.tmpBuf_, 0, i);
        return stringBuffer.toString();
    }

    private char readNameStartChar() throws ParseException, IOException {
        char readChar = readChar();
        if (isLetter(readChar) || readChar == '_' || readChar == ':') {
            return readChar;
        }
        throw new ParseException(this, readChar, "letter, underscore, colon");
    }

    private final String readEntityValue() throws ParseException, IOException {
        char readChar = readChar('\'', '\"');
        StringBuffer stringBuffer = new StringBuffer();
        while (!isChar(readChar)) {
            if (isPeReference()) {
                stringBuffer.append(readPeReference());
            } else if (isReference()) {
                stringBuffer.append(readReference());
            } else {
                stringBuffer.append(readChar());
            }
        }
        readChar(readChar);
        return stringBuffer.toString();
    }

    private final boolean isEntityValue() throws ParseException, IOException {
        return isChar('\'', '\"');
    }

    private final void readSystemLiteral() throws ParseException, IOException {
        char readChar = readChar();
        while (peekChar() != readChar) {
            readChar();
        }
        readChar(readChar);
    }

    private final void readPubidLiteral() throws ParseException, IOException {
        readSystemLiteral();
    }

    private boolean isMisc() throws ParseException, IOException {
        return isComment() || isPi() || isS();
    }

    private void readMisc() throws ParseException, IOException {
        if (isComment()) {
            readComment();
        } else if (isPi()) {
            readPi();
        } else if (isS()) {
            readS();
        } else {
            throw new ParseException(this, "expecting comment or processing instruction or space");
        }
    }

    private final void readComment() throws ParseException, IOException {
        readSymbol(COMMENT_BEGIN);
        while (!isSymbol(COMMENT_END)) {
            readChar();
        }
        readSymbol(COMMENT_END);
    }

    private final boolean isComment() throws ParseException, IOException {
        return isSymbol(COMMENT_BEGIN);
    }

    private final void readPi() throws ParseException, IOException {
        readSymbol(PI_BEGIN);
        while (!isSymbol(QU_END)) {
            readChar();
        }
        readSymbol(QU_END);
    }

    private final boolean isPi() throws ParseException, IOException {
        return isSymbol(PI_BEGIN);
    }

    private void readProlog() throws ParseException, EncodingMismatchException, IOException {
        if (isXmlDecl()) {
            readXmlDecl();
        }
        while (isMisc()) {
            readMisc();
        }
        if (isDocTypeDecl()) {
            readDocTypeDecl();
            while (isMisc()) {
                readMisc();
            }
        }
    }

    private boolean isDocTypeDecl() throws ParseException, IOException {
        return isSymbol(DOCTYPE_BEGIN);
    }

    private void readXmlDecl() throws ParseException, EncodingMismatchException, IOException {
        readSymbol(XML_BEGIN);
        readVersionInfo();
        if (isS()) {
            readS();
        }
        if (isEncodingDecl()) {
            String readEncodingDecl = readEncodingDecl();
            if (this.encoding_ != null && !readEncodingDecl.toLowerCase().equals(this.encoding_)) {
                throw new EncodingMismatchException(this.systemId_, readEncodingDecl, this.encoding_);
            }
        }
        while (!isSymbol(QU_END)) {
            readChar();
        }
        readSymbol(QU_END);
    }

    private boolean isXmlDecl() throws ParseException, IOException {
        return isSymbol(XML_BEGIN);
    }

    private boolean isEncodingDecl() throws ParseException, IOException {
        return isSymbol(ENCODING);
    }

    private String readEncodingDecl() throws ParseException, IOException {
        readSymbol(ENCODING);
        readEq();
        char readChar = readChar('\'', '\"');
        StringBuffer stringBuffer = new StringBuffer();
        while (!isChar(readChar)) {
            stringBuffer.append(readChar());
        }
        readChar(readChar);
        return stringBuffer.toString();
    }

    private void readVersionInfo() throws ParseException, IOException {
        readS();
        readSymbol(VERSION);
        readEq();
        char readChar = readChar('\'', '\"');
        readVersionNum();
        readChar(readChar);
    }

    private final void readEq() throws ParseException, IOException {
        if (isS()) {
            readS();
        }
        readChar('=');
        if (isS()) {
            readS();
        }
    }

    private boolean isVersionNumChar() throws ParseException, IOException {
        char peekChar = peekChar();
        return Character.isDigit(peekChar) || ('a' <= peekChar && peekChar <= 'z') || (('Z' <= peekChar && peekChar <= 'Z') || isIn(peekChar, VERSIONNUM_PUNC_CHARS));
    }

    private void readVersionNum() throws ParseException, IOException {
        readChar();
        while (isVersionNumChar()) {
            readChar();
        }
    }

    private void readDocTypeDecl() throws ParseException, IOException {
        readSymbol(DOCTYPE_BEGIN);
        readS();
        this.docTypeName_ = readName();
        if (isS()) {
            readS();
            if (!isChar('>') && !isChar('[')) {
                this.isExternalDtd_ = true;
                readExternalId();
                if (isS()) {
                    readS();
                }
            }
        }
        if (isChar('[')) {
            readChar();
            while (!isChar(']')) {
                if (isDeclSep()) {
                    readDeclSep();
                } else {
                    readMarkupDecl();
                }
            }
            readChar(']');
            if (isS()) {
                readS();
            }
        }
        readChar('>');
    }

    private void readDeclSep() throws ParseException, IOException {
        if (isPeReference()) {
            readPeReference();
        } else {
            readS();
        }
    }

    private boolean isDeclSep() throws ParseException, IOException {
        return isPeReference() || isS();
    }

    private void readMarkupDecl() throws ParseException, IOException {
        if (isPi()) {
            readPi();
        } else if (isComment()) {
            readComment();
        } else if (isEntityDecl()) {
            readEntityDecl();
        } else if (isSymbol(MARKUPDECL_BEGIN)) {
            while (!isChar('>')) {
                if (isChar('\'', '\"')) {
                    char readChar = readChar();
                    while (!isChar(readChar)) {
                        readChar();
                    }
                    readChar(readChar);
                } else {
                    readChar();
                }
            }
            readChar('>');
        } else {
            throw new ParseException(this, "expecting processing instruction, comment, or \"<!\"");
        }
    }

    private char readCharRef() throws ParseException, IOException {
        int i;
        readSymbol(CHARREF_BEGIN);
        if (isChar('x')) {
            readChar();
            i = 16;
        } else {
            i = 10;
        }
        int i2 = 0;
        while (!isChar(';')) {
            int i3 = i2 + 1;
            this.tmpBuf_[i2] = readChar();
            if (i3 >= 255) {
                this.log_.warning("Tmp buffer overflow on readCharRef", this.systemId_, getLineNumber());
                return ' ';
            }
            i2 = i3;
        }
        readChar(';');
        String str = new String(this.tmpBuf_, 0, i2);
        try {
            return (char) Integer.parseInt(str, i);
        } catch (NumberFormatException unused) {
            ParseLog parseLog = this.log_;
            StringBuilder sb = new StringBuilder();
            sb.append("\"");
            sb.append(str);
            sb.append("\" is not a valid ");
            sb.append(i == 16 ? "hexadecimal" : "decimal");
            sb.append(" number");
            parseLog.warning(sb.toString(), this.systemId_, getLineNumber());
            return ' ';
        }
    }

    private final char[] readReference() throws ParseException, IOException {
        return isSymbol(CHARREF_BEGIN) ? new char[]{readCharRef()} : readEntityRef().toCharArray();
    }

    private final boolean isReference() throws ParseException, IOException {
        return isChar('&');
    }

    private String readEntityRef() throws ParseException, IOException {
        readChar('&');
        String readName = readName();
        String str = (String) this.entities_.get(readName);
        if (str == null) {
            str = "";
            if (this.isExternalDtd_) {
                ParseLog parseLog = this.log_;
                parseLog.warning("&" + readName + "; not found -- possibly defined in external DTD)", this.systemId_, getLineNumber());
            } else {
                ParseLog parseLog2 = this.log_;
                parseLog2.warning("No declaration of &" + readName + ";", this.systemId_, getLineNumber());
            }
        }
        readChar(';');
        return str;
    }

    private String readPeReference() throws ParseException, IOException {
        readChar('%');
        String readName = readName();
        String str = (String) this.pes_.get(readName);
        if (str == null) {
            str = "";
            ParseLog parseLog = this.log_;
            parseLog.warning("No declaration of %" + readName + ";", this.systemId_, getLineNumber());
        }
        readChar(';');
        return str;
    }

    private boolean isPeReference() throws ParseException, IOException {
        return isChar('%');
    }

    private void readEntityDecl() throws ParseException, IOException {
        String readExternalId;
        String readExternalId2;
        readSymbol(ENTITY_BEGIN);
        readS();
        if (isChar('%')) {
            readChar('%');
            readS();
            String readName = readName();
            readS();
            if (isEntityValue()) {
                readExternalId2 = readEntityValue();
            } else {
                readExternalId2 = readExternalId();
            }
            this.pes_.put(readName, readExternalId2);
        } else {
            String readName2 = readName();
            readS();
            if (isEntityValue()) {
                readExternalId = readEntityValue();
            } else if (isExternalId()) {
                readExternalId = readExternalId();
                if (isS()) {
                    readS();
                }
                if (isSymbol(NDATA)) {
                    readSymbol(NDATA);
                    readS();
                    readName();
                }
            } else {
                throw new ParseException(this, "expecting double-quote, \"PUBLIC\" or \"SYSTEM\" while reading entity declaration");
            }
            this.entities_.put(readName2, readExternalId);
        }
        if (isS()) {
            readS();
        }
        readChar('>');
    }

    private boolean isEntityDecl() throws ParseException, IOException {
        return isSymbol(ENTITY_BEGIN);
    }

    private String readExternalId() throws ParseException, IOException {
        if (isSymbol(SYSTEM)) {
            readSymbol(SYSTEM);
        } else if (isSymbol(PUBLIC)) {
            readSymbol(PUBLIC);
            readS();
            readPubidLiteral();
        } else {
            throw new ParseException(this, "expecting \"SYSTEM\" or \"PUBLIC\" while reading external ID");
        }
        readS();
        readSystemLiteral();
        return "(WARNING: external ID not read)";
    }

    private boolean isExternalId() throws ParseException, IOException {
        return isSymbol(SYSTEM) || isSymbol(PUBLIC);
    }

    private final void readSymbol(char[] cArr) throws ParseException, IOException {
        int length = cArr.length;
        if (this.endPos_ - this.curPos_ < length && fillBuf(length) <= 0) {
            this.ch_ = -1;
            throw new ParseException(this, "end of XML file", cArr);
        }
        char[] cArr2 = this.cbuf_;
        int i = this.endPos_;
        this.ch_ = cArr2[i - 1];
        if (i - this.curPos_ < length) {
            throw new ParseException(this, "end of XML file", cArr);
        }
        for (int i2 = 0; i2 < length; i2++) {
            char[] cArr3 = this.cbuf_;
            int i3 = this.curPos_;
            if (cArr3[i3 + i2] != cArr[i2]) {
                throw new ParseException(this, new String(cArr3, i3, length), cArr);
            }
        }
        this.curPos_ += length;
    }

    private final boolean isSymbol(char[] cArr) throws ParseException, IOException {
        int length = cArr.length;
        if (this.endPos_ - this.curPos_ < length && fillBuf(length) <= 0) {
            this.ch_ = -1;
            return false;
        }
        char[] cArr2 = this.cbuf_;
        int i = this.endPos_;
        this.ch_ = cArr2[i - 1];
        if (i - this.curPos_ < length) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (this.cbuf_[this.curPos_ + i2] != cArr[i2]) {
                return false;
            }
        }
        return true;
    }

    private String readAttValue() throws ParseException, IOException {
        char readChar = readChar('\'', '\"');
        StringBuffer stringBuffer = new StringBuffer();
        while (!isChar(readChar)) {
            if (isReference()) {
                stringBuffer.append(readReference());
            } else {
                stringBuffer.append(readChar());
            }
        }
        readChar(readChar);
        return stringBuffer.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x004b, code lost:
        r4.handler_.characters(r4.tmpBuf_, 0, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0052, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void readPossibleCharData() throws com.p210hp.hpl.sparta.ParseException, java.io.IOException {
        /*
            r4 = this;
            r0 = 0
            r1 = r0
        L2:
            r2 = 60
            boolean r2 = r4.isChar(r2)
            if (r2 != 0) goto L49
            r2 = 38
            boolean r2 = r4.isChar(r2)
            if (r2 != 0) goto L49
            char[] r2 = com.p210hp.hpl.sparta.ParseCharStream.END_CDATA
            boolean r2 = r4.isSymbol(r2)
            if (r2 != 0) goto L49
            char[] r2 = r4.tmpBuf_
            char r3 = r4.readChar()
            r2[r1] = r3
            char[] r2 = r4.tmpBuf_
            char r2 = r2[r1]
            r3 = 13
            if (r2 != r3) goto L3a
            char r2 = r4.peekChar()
            r3 = 10
            if (r2 != r3) goto L3a
            char[] r2 = r4.tmpBuf_
            char r3 = r4.readChar()
            r2[r1] = r3
        L3a:
            int r1 = r1 + 1
            r2 = 255(0xff, float:3.57E-43)
            if (r1 != r2) goto L2
            com.hp.hpl.sparta.ParseHandler r1 = r4.handler_
            char[] r3 = r4.tmpBuf_
            r1.characters(r3, r0, r2)
            r1 = r0
            goto L2
        L49:
            if (r1 <= 0) goto L52
            com.hp.hpl.sparta.ParseHandler r2 = r4.handler_
            char[] r3 = r4.tmpBuf_
            r2.characters(r3, r0, r1)
        L52:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p210hp.hpl.sparta.ParseCharStream.readPossibleCharData():void");
    }

    private void readCdSect() throws ParseException, IOException {
        readSymbol(BEGIN_CDATA);
        StringBuffer stringBuffer = null;
        int i = 0;
        while (!isSymbol(END_CDATA)) {
            if (i >= 255) {
                if (stringBuffer == null) {
                    stringBuffer = new StringBuffer(i);
                    stringBuffer.append(this.tmpBuf_, 0, i);
                } else {
                    stringBuffer.append(this.tmpBuf_, 0, i);
                }
                i = 0;
            }
            this.tmpBuf_[i] = readChar();
            i++;
        }
        readSymbol(END_CDATA);
        if (stringBuffer != null) {
            stringBuffer.append(this.tmpBuf_, 0, i);
            char[] charArray = stringBuffer.toString().toCharArray();
            this.handler_.characters(charArray, 0, charArray.length);
            return;
        }
        this.handler_.characters(this.tmpBuf_, 0, i);
    }

    private boolean isCdSect() throws ParseException, IOException {
        return isSymbol(BEGIN_CDATA);
    }

    private final Element readElement() throws ParseException, IOException {
        Element element = new Element();
        boolean readEmptyElementTagOrSTag = readEmptyElementTagOrSTag(element);
        this.handler_.startElement(element);
        if (readEmptyElementTagOrSTag) {
            readContent();
            readETag(element);
        }
        this.handler_.endElement(element);
        return element;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ParseLog getLog() {
        return this.log_;
    }

    private boolean readEmptyElementTagOrSTag(Element element) throws ParseException, IOException {
        readChar('<');
        element.setTagName(readName());
        while (isS()) {
            readS();
            if (!isChar('/', '>')) {
                readAttribute(element);
            }
        }
        if (isS()) {
            readS();
        }
        boolean isChar = isChar('>');
        if (isChar) {
            readChar('>');
        } else {
            readSymbol(END_EMPTYTAG);
        }
        return isChar;
    }

    private void readAttribute(Element element) throws ParseException, IOException {
        String readName = readName();
        readEq();
        String readAttValue = readAttValue();
        if (element.getAttribute(readName) != null) {
            ParseLog parseLog = this.log_;
            parseLog.warning("Element " + this + " contains attribute " + readName + "more than once", this.systemId_, getLineNumber());
        }
        element.setAttribute(readName, readAttValue);
    }

    private void readETag(Element element) throws ParseException, IOException {
        readSymbol(BEGIN_ETAG);
        String readName = readName();
        if (!readName.equals(element.getTagName())) {
            ParseLog parseLog = this.log_;
            parseLog.warning("end tag (" + readName + ") does not match begin tag (" + element.getTagName() + ")", this.systemId_, getLineNumber());
        }
        if (isS()) {
            readS();
        }
        readChar('>');
    }

    private boolean isETag() throws ParseException, IOException {
        return isSymbol(BEGIN_ETAG);
    }

    private void readContent() throws ParseException, IOException {
        readPossibleCharData();
        boolean z = true;
        while (z) {
            if (isETag()) {
                z = false;
            } else if (isReference()) {
                char[] readReference = readReference();
                this.handler_.characters(readReference, 0, readReference.length);
            } else if (isCdSect()) {
                readCdSect();
            } else if (isPi()) {
                readPi();
            } else if (isComment()) {
                readComment();
            } else if (isChar('<')) {
                readElement();
            } else {
                z = false;
            }
            readPossibleCharData();
        }
    }
}
