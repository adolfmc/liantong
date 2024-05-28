package org.jsoup.helper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DataUtil {
    private static final int bufferSize = 131072;
    private static final Pattern charsetPattern = Pattern.compile("(?i)\\bcharset=\\s*\"?([^\\s;\"]*)");
    static final String defaultCharset = "UTF-8";

    private DataUtil() {
    }

    public static Document load(File file, String str, String str2) throws IOException {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Throwable th) {
            th = th;
            fileInputStream = null;
        }
        try {
            Document parseByteData = parseByteData(readToByteBuffer(fileInputStream), str, str2, Parser.htmlParser());
            fileInputStream.close();
            return parseByteData;
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
    }

    public static Document load(InputStream inputStream, String str, String str2) throws IOException {
        return parseByteData(readToByteBuffer(inputStream), str, str2, Parser.htmlParser());
    }

    public static Document load(InputStream inputStream, String str, String str2, Parser parser) throws IOException {
        return parseByteData(readToByteBuffer(inputStream), str, str2, parser);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Document parseByteData(ByteBuffer byteBuffer, String str, String str2, Parser parser) {
        String charBuffer;
        Document document = null;
        if (str == null) {
            charBuffer = Charset.forName("UTF-8").decode(byteBuffer).toString();
            Document parseInput = parser.parseInput(charBuffer, str2);
            Element first = parseInput.select("meta[http-equiv=content-type], meta[charset]").first();
            if (first != null) {
                String charsetFromContentType = first.hasAttr("http-equiv") ? getCharsetFromContentType(first.attr("content")) : first.attr("charset");
                if (charsetFromContentType != null && charsetFromContentType.length() != 0 && !charsetFromContentType.equals("UTF-8")) {
                    byteBuffer.rewind();
                    charBuffer = Charset.forName(charsetFromContentType).decode(byteBuffer).toString();
                    str = charsetFromContentType;
                }
            }
            document = parseInput;
        } else {
            Validate.notEmpty(str, "Must set charset arg to character set of file to parse. Set to null to attempt to detect from HTML");
            charBuffer = Charset.forName(str).decode(byteBuffer).toString();
        }
        if (document == null) {
            if (charBuffer.length() > 0 && charBuffer.charAt(0) == 65279) {
                charBuffer = charBuffer.substring(1);
            }
            Document parseInput2 = parser.parseInput(charBuffer, str2);
            parseInput2.outputSettings().charset(str);
            return parseInput2;
        }
        return document;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ByteBuffer readToByteBuffer(InputStream inputStream, int i) throws IOException {
        Validate.isTrue(i >= 0, "maxSize must be 0 (unlimited) or larger");
        boolean z = i > 0;
        byte[] bArr = new byte[131072];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(131072);
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                break;
            }
            if (z) {
                if (read > i) {
                    byteArrayOutputStream.write(bArr, 0, i);
                    break;
                }
                i -= read;
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
        return ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
    }

    static ByteBuffer readToByteBuffer(InputStream inputStream) throws IOException {
        return readToByteBuffer(inputStream, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getCharsetFromContentType(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = charsetPattern.matcher(str);
        if (matcher.find()) {
            String trim = matcher.group(1).trim();
            if (Charset.isSupported(trim)) {
                return trim;
            }
            String upperCase = trim.toUpperCase(Locale.ENGLISH);
            if (Charset.isSupported(upperCase)) {
                return upperCase;
            }
        }
        return null;
    }
}
