package org.apache.http.message;

import org.apache.http.Header;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharArrayBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class BasicLineParser implements LineParser {
    public static final BasicLineParser DEFAULT = new BasicLineParser();
    protected final ProtocolVersion protocol;

    public BasicLineParser(ProtocolVersion protocolVersion) {
        this.protocol = protocolVersion == null ? HttpVersion.HTTP_1_1 : protocolVersion;
    }

    public BasicLineParser() {
        this(null);
    }

    public static final ProtocolVersion parseProtocolVersion(String str, LineParser lineParser) throws ParseException {
        if (str == null) {
            throw new IllegalArgumentException("Value to parse may not be null.");
        }
        if (lineParser == null) {
            lineParser = DEFAULT;
        }
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.append(str);
        return lineParser.parseProtocolVersion(charArrayBuffer, new ParserCursor(0, str.length()));
    }

    @Override // org.apache.http.message.LineParser
    public ProtocolVersion parseProtocolVersion(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) throws ParseException {
        if (charArrayBuffer == null) {
            throw new IllegalArgumentException("Char array buffer may not be null");
        }
        if (parserCursor == null) {
            throw new IllegalArgumentException("Parser cursor may not be null");
        }
        String protocol = this.protocol.getProtocol();
        int length = protocol.length();
        int pos = parserCursor.getPos();
        int upperBound = parserCursor.getUpperBound();
        skipWhitespace(charArrayBuffer, parserCursor);
        int pos2 = parserCursor.getPos();
        int i = pos2 + length;
        if (i + 4 > upperBound) {
            throw new ParseException("Not a valid protocol version: " + charArrayBuffer.substring(pos, upperBound));
        }
        boolean z = true;
        for (int i2 = 0; z && i2 < length; i2++) {
            z = charArrayBuffer.charAt(pos2 + i2) == protocol.charAt(i2);
        }
        if (z) {
            z = charArrayBuffer.charAt(i) == '/';
        }
        if (!z) {
            throw new ParseException("Not a valid protocol version: " + charArrayBuffer.substring(pos, upperBound));
        }
        int i3 = pos2 + length + 1;
        int indexOf = charArrayBuffer.indexOf(46, i3, upperBound);
        if (indexOf == -1) {
            throw new ParseException("Invalid protocol version number: " + charArrayBuffer.substring(pos, upperBound));
        }
        try {
            int parseInt = Integer.parseInt(charArrayBuffer.substringTrimmed(i3, indexOf));
            int i4 = indexOf + 1;
            int indexOf2 = charArrayBuffer.indexOf(32, i4, upperBound);
            if (indexOf2 == -1) {
                indexOf2 = upperBound;
            }
            try {
                int parseInt2 = Integer.parseInt(charArrayBuffer.substringTrimmed(i4, indexOf2));
                parserCursor.updatePos(indexOf2);
                return createProtocolVersion(parseInt, parseInt2);
            } catch (NumberFormatException e) {
                throw new ParseException("Invalid protocol minor version number: " + charArrayBuffer.substring(pos, upperBound));
            }
        } catch (NumberFormatException e2) {
            throw new ParseException("Invalid protocol major version number: " + charArrayBuffer.substring(pos, upperBound));
        }
    }

    protected ProtocolVersion createProtocolVersion(int i, int i2) {
        return this.protocol.forVersion(i, i2);
    }

    @Override // org.apache.http.message.LineParser
    public boolean hasProtocolVersion(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        if (charArrayBuffer == null) {
            throw new IllegalArgumentException("Char array buffer may not be null");
        }
        if (parserCursor == null) {
            throw new IllegalArgumentException("Parser cursor may not be null");
        }
        int pos = parserCursor.getPos();
        String protocol = this.protocol.getProtocol();
        int length = protocol.length();
        if (charArrayBuffer.length() < length + 4) {
            return false;
        }
        if (pos < 0) {
            pos = (charArrayBuffer.length() - 4) - length;
        } else if (pos == 0) {
            while (pos < charArrayBuffer.length() && HTTP.isWhitespace(charArrayBuffer.charAt(pos))) {
                pos++;
            }
        }
        int i = pos + length;
        if (i + 4 > charArrayBuffer.length()) {
            return false;
        }
        boolean z = true;
        for (int i2 = 0; z && i2 < length; i2++) {
            z = charArrayBuffer.charAt(pos + i2) == protocol.charAt(i2);
        }
        if (z) {
            return charArrayBuffer.charAt(i) == '/';
        }
        return z;
    }

    public static final RequestLine parseRequestLine(String str, LineParser lineParser) throws ParseException {
        if (str == null) {
            throw new IllegalArgumentException("Value to parse may not be null.");
        }
        if (lineParser == null) {
            lineParser = DEFAULT;
        }
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.append(str);
        return lineParser.parseRequestLine(charArrayBuffer, new ParserCursor(0, str.length()));
    }

    @Override // org.apache.http.message.LineParser
    public RequestLine parseRequestLine(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) throws ParseException {
        if (charArrayBuffer == null) {
            throw new IllegalArgumentException("Char array buffer may not be null");
        }
        if (parserCursor == null) {
            throw new IllegalArgumentException("Parser cursor may not be null");
        }
        int pos = parserCursor.getPos();
        int upperBound = parserCursor.getUpperBound();
        try {
            skipWhitespace(charArrayBuffer, parserCursor);
            int pos2 = parserCursor.getPos();
            int indexOf = charArrayBuffer.indexOf(32, pos2, upperBound);
            if (indexOf < 0) {
                throw new ParseException("Invalid request line: " + charArrayBuffer.substring(pos, upperBound));
            }
            String substringTrimmed = charArrayBuffer.substringTrimmed(pos2, indexOf);
            parserCursor.updatePos(indexOf);
            skipWhitespace(charArrayBuffer, parserCursor);
            int pos3 = parserCursor.getPos();
            int indexOf2 = charArrayBuffer.indexOf(32, pos3, upperBound);
            if (indexOf2 < 0) {
                throw new ParseException("Invalid request line: " + charArrayBuffer.substring(pos, upperBound));
            }
            String substringTrimmed2 = charArrayBuffer.substringTrimmed(pos3, indexOf2);
            parserCursor.updatePos(indexOf2);
            ProtocolVersion parseProtocolVersion = parseProtocolVersion(charArrayBuffer, parserCursor);
            skipWhitespace(charArrayBuffer, parserCursor);
            if (!parserCursor.atEnd()) {
                throw new ParseException("Invalid request line: " + charArrayBuffer.substring(pos, upperBound));
            }
            return createRequestLine(substringTrimmed, substringTrimmed2, parseProtocolVersion);
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException("Invalid request line: " + charArrayBuffer.substring(pos, upperBound));
        }
    }

    protected RequestLine createRequestLine(String str, String str2, ProtocolVersion protocolVersion) {
        return new BasicRequestLine(str, str2, protocolVersion);
    }

    public static final StatusLine parseStatusLine(String str, LineParser lineParser) throws ParseException {
        if (str == null) {
            throw new IllegalArgumentException("Value to parse may not be null.");
        }
        if (lineParser == null) {
            lineParser = DEFAULT;
        }
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.append(str);
        return lineParser.parseStatusLine(charArrayBuffer, new ParserCursor(0, str.length()));
    }

    @Override // org.apache.http.message.LineParser
    public StatusLine parseStatusLine(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) throws ParseException {
        String str;
        if (charArrayBuffer == null) {
            throw new IllegalArgumentException("Char array buffer may not be null");
        }
        if (parserCursor == null) {
            throw new IllegalArgumentException("Parser cursor may not be null");
        }
        int pos = parserCursor.getPos();
        int upperBound = parserCursor.getUpperBound();
        try {
            ProtocolVersion parseProtocolVersion = parseProtocolVersion(charArrayBuffer, parserCursor);
            skipWhitespace(charArrayBuffer, parserCursor);
            int pos2 = parserCursor.getPos();
            int indexOf = charArrayBuffer.indexOf(32, pos2, upperBound);
            if (indexOf < 0) {
                indexOf = upperBound;
            }
            try {
                int parseInt = Integer.parseInt(charArrayBuffer.substringTrimmed(pos2, indexOf));
                if (indexOf < upperBound) {
                    str = charArrayBuffer.substringTrimmed(indexOf, upperBound);
                } else {
                    str = "";
                }
                return createStatusLine(parseProtocolVersion, parseInt, str);
            } catch (NumberFormatException e) {
                throw new ParseException("Unable to parse status code from status line: " + charArrayBuffer.substring(pos, upperBound));
            }
        } catch (IndexOutOfBoundsException e2) {
            throw new ParseException("Invalid status line: " + charArrayBuffer.substring(pos, upperBound));
        }
    }

    protected StatusLine createStatusLine(ProtocolVersion protocolVersion, int i, String str) {
        return new BasicStatusLine(protocolVersion, i, str);
    }

    public static final Header parseHeader(String str, LineParser lineParser) throws ParseException {
        if (str == null) {
            throw new IllegalArgumentException("Value to parse may not be null");
        }
        if (lineParser == null) {
            lineParser = DEFAULT;
        }
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.append(str);
        return lineParser.parseHeader(charArrayBuffer);
    }

    @Override // org.apache.http.message.LineParser
    public Header parseHeader(CharArrayBuffer charArrayBuffer) throws ParseException {
        return new BufferedHeader(charArrayBuffer);
    }

    protected void skipWhitespace(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        int pos = parserCursor.getPos();
        int upperBound = parserCursor.getUpperBound();
        while (pos < upperBound && HTTP.isWhitespace(charArrayBuffer.charAt(pos))) {
            pos++;
        }
        parserCursor.updatePos(pos);
    }
}
