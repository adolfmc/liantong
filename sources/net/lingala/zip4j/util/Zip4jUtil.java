package net.lingala.zip4j.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.model.enums.CompressionMethod;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class Zip4jUtil {
    private static final int MAX_RAW_READ_FULLY_RETRY_ATTEMPTS = 15;

    public static boolean isStringNotNullAndNotEmpty(String str) {
        return str != null && str.trim().length() > 0;
    }

    public static boolean createDirectoryIfNotExists(File file) throws ZipException {
        if (file == null) {
            throw new ZipException("output path is null");
        }
        if (file.exists()) {
            if (file.isDirectory()) {
                return true;
            }
            throw new ZipException("output directory is not valid");
        } else if (file.mkdirs()) {
            return true;
        } else {
            throw new ZipException("Cannot create output directories");
        }
    }

    public static long javaToDosTime(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        int i = calendar.get(1);
        if (i < 1980) {
            return 2162688L;
        }
        return (calendar.get(13) >> 1) | ((i - 1980) << 25) | ((calendar.get(2) + 1) << 21) | (calendar.get(5) << 16) | (calendar.get(11) << 11) | (calendar.get(12) << 5);
    }

    public static long dosToJavaTme(long j) {
        int i = (int) ((j & 31) * 2);
        int i2 = (int) ((j >> 5) & 63);
        int i3 = (int) ((j >> 11) & 31);
        int i4 = (int) (31 & (j >> 16));
        int i5 = (int) (((j >> 25) & 127) + 1980);
        Calendar calendar = Calendar.getInstance();
        calendar.set(i5, (int) (((j >> 21) & 15) - 1), i4, i3, i2, i);
        calendar.set(14, 0);
        return calendar.getTime().getTime();
    }

    public static byte[] convertCharArrayToByteArray(char[] cArr) {
        byte[] bArr = new byte[cArr.length];
        for (int i = 0; i < cArr.length; i++) {
            bArr[i] = (byte) cArr[i];
        }
        return bArr;
    }

    public static CompressionMethod getCompressionMethod(LocalFileHeader localFileHeader) {
        if (localFileHeader.getCompressionMethod() != CompressionMethod.AES_INTERNAL_ONLY) {
            return localFileHeader.getCompressionMethod();
        }
        if (localFileHeader.getAesExtraDataRecord() == null) {
            throw new RuntimeException("AesExtraDataRecord not present in local header for aes encrypted data");
        }
        return localFileHeader.getAesExtraDataRecord().getCompressionMethod();
    }

    public static int readFully(InputStream inputStream, byte[] bArr) throws IOException {
        int read = inputStream.read(bArr);
        if (read == bArr.length || (read = readUntilBufferIsFull(inputStream, bArr, read)) == bArr.length) {
            return read;
        }
        throw new IOException("Cannot read fully into byte buffer");
    }

    public static int readFully(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        if (i >= 0) {
            if (i2 >= 0) {
                int i3 = 0;
                if (i2 == 0) {
                    return 0;
                }
                if (i + i2 <= bArr.length) {
                    while (i3 != i2) {
                        int read = inputStream.read(bArr, i + i3, i2 - i3);
                        if (read == -1) {
                            if (i3 == 0) {
                                return -1;
                            }
                            return i3;
                        }
                        i3 += read;
                    }
                    return i3;
                }
                throw new IllegalArgumentException("Length greater than buffer size");
            }
            throw new IllegalArgumentException("Negative length");
        }
        throw new IllegalArgumentException("Negative offset");
    }

    private static int readUntilBufferIsFull(InputStream inputStream, byte[] bArr, int i) throws IOException {
        int length = bArr.length - i;
        int i2 = 0;
        for (int i3 = 1; i < bArr.length && i2 != -1 && i3 < 15; i3++) {
            i2 = inputStream.read(bArr, i, length);
            if (i2 > 0) {
                i += i2;
                length -= i2;
            }
        }
        return i;
    }
}
