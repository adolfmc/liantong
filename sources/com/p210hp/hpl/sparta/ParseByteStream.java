package com.p210hp.hpl.sparta;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hp.hpl.sparta.ParseByteStream */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ParseByteStream implements ParseSource {
    private ParseCharStream parseSource_;

    public ParseByteStream(String str, InputStream inputStream, ParseLog parseLog, String str2, ParseHandler parseHandler) throws ParseException, IOException {
        parseLog = parseLog == null ? DEFAULT_LOG : parseLog;
        if (!inputStream.markSupported()) {
            throw new Error("Precondition violation: the InputStream passed to ParseByteStream must support mark");
        }
        inputStream.mark(MAXLOOKAHEAD);
        byte[] bArr = new byte[4];
        str2 = str2 == null ? guessEncoding(str, bArr, inputStream.read(bArr), parseLog) : str2;
        try {
            inputStream.reset();
            try {
                this.parseSource_ = new ParseCharStream(str, new InputStreamReader(inputStream, fixEncoding(str2)), parseLog, str2, parseHandler);
            } catch (IOException unused) {
                parseLog.note("Problem reading with assumed encoding of " + str2 + " so restarting with euc-jp", str, 1);
                inputStream.reset();
                try {
                    this.parseSource_ = new ParseCharStream(str, new InputStreamReader(inputStream, fixEncoding("euc-jp")), parseLog, (String) null, parseHandler);
                } catch (UnsupportedEncodingException unused2) {
                    throw new ParseException(parseLog, str, 1, 0, "euc-jp", "\"euc-jp\" is not a supported encoding");
                }
            }
        } catch (EncodingMismatchException e) {
            String declaredEncoding = e.getDeclaredEncoding();
            parseLog.note("Encoding declaration of " + declaredEncoding + " is different that assumed " + str2 + " so restarting the parsing with the new encoding", str, 1);
            inputStream.reset();
            try {
                this.parseSource_ = new ParseCharStream(str, new InputStreamReader(inputStream, fixEncoding(declaredEncoding)), parseLog, (String) null, parseHandler);
            } catch (UnsupportedEncodingException unused3) {
                throw new ParseException(parseLog, str, 1, 0, declaredEncoding, "\"" + declaredEncoding + "\" is not a supported encoding");
            }
        }
    }

    @Override // com.p210hp.hpl.sparta.ParseSource
    public String toString() {
        return this.parseSource_.toString();
    }

    @Override // com.p210hp.hpl.sparta.ParseSource
    public String getSystemId() {
        return this.parseSource_.getSystemId();
    }

    @Override // com.p210hp.hpl.sparta.ParseSource
    public int getLineNumber() {
        return this.parseSource_.getLineNumber();
    }

    private static String guessEncoding(String str, byte[] bArr, int i, ParseLog parseLog) throws IOException {
        String str2;
        String str3;
        if (i != 4) {
            if (i <= 0) {
                str3 = "no characters in input";
            } else {
                str3 = "less than 4 characters in input: \"" + new String(bArr, 0, i) + "\"";
            }
            parseLog.error(str3, str, 1);
            str2 = "UTF-8";
        } else if (equals(bArr, 65279) || equals(bArr, -131072) || equals(bArr, 65534) || equals(bArr, -16842752) || equals(bArr, 60) || equals(bArr, 1006632960) || equals(bArr, 15360) || equals(bArr, 3932160)) {
            str2 = "UCS-4";
        } else if (equals(bArr, 3932223)) {
            str2 = "UTF-16BE";
        } else if (equals(bArr, 1006649088)) {
            str2 = "UTF-16LE";
        } else if (equals(bArr, 1010792557)) {
            str2 = "UTF-8";
        } else if (equals(bArr, 1282385812)) {
            str2 = "EBCDIC";
        } else {
            str2 = (equals(bArr, (short) -2) || equals(bArr, (short) -257)) ? "UTF-16" : "UTF-8";
        }
        if (!str2.equals("UTF-8")) {
            parseLog.note("From start " + hex(bArr[0]) + " " + hex(bArr[1]) + " " + hex(bArr[2]) + " " + hex(bArr[3]) + " deduced encoding = " + str2, str, 1);
        }
        return str2;
    }

    private static String hex(byte b) {
        String hexString = Integer.toHexString(b);
        switch (hexString.length()) {
            case 1:
                return "0" + hexString;
            case 2:
                return hexString;
            default:
                return hexString.substring(hexString.length() - 2);
        }
    }

    private static boolean equals(byte[] bArr, int i) {
        return bArr[0] == ((byte) (i >>> 24)) && bArr[1] == ((byte) ((i >>> 16) & 255)) && bArr[2] == ((byte) ((i >>> 8) & 255)) && bArr[3] == ((byte) (i & 255));
    }

    private static boolean equals(byte[] bArr, short s) {
        return bArr[0] == ((byte) (s >>> 8)) && bArr[1] == ((byte) (s & 255));
    }

    private static String fixEncoding(String str) {
        return str.toLowerCase().equals("utf8") ? "UTF-8" : str;
    }
}
