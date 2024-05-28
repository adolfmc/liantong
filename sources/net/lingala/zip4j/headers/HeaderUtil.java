package net.lingala.zip4j.headers;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.Zip4jUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class HeaderUtil {
    public static FileHeader getFileHeader(ZipModel zipModel, String str) throws ZipException {
        FileHeader fileHeaderWithExactMatch = getFileHeaderWithExactMatch(zipModel, str);
        if (fileHeaderWithExactMatch == null) {
            String replaceAll = str.replaceAll("\\\\", "/");
            FileHeader fileHeaderWithExactMatch2 = getFileHeaderWithExactMatch(zipModel, replaceAll);
            return fileHeaderWithExactMatch2 == null ? getFileHeaderWithExactMatch(zipModel, replaceAll.replaceAll("/", "\\\\")) : fileHeaderWithExactMatch2;
        }
        return fileHeaderWithExactMatch;
    }

    public static int getIndexOfFileHeader(ZipModel zipModel, FileHeader fileHeader) throws ZipException {
        if (zipModel == null || fileHeader == null) {
            throw new ZipException("input parameters is null, cannot determine index of file header");
        }
        if (zipModel.getCentralDirectory() == null || zipModel.getCentralDirectory().getFileHeaders() == null || zipModel.getCentralDirectory().getFileHeaders().size() <= 0) {
            return -1;
        }
        String fileName = fileHeader.getFileName();
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(fileName)) {
            throw new ZipException("file name in file header is empty or null, cannot determine index of file header");
        }
        List<FileHeader> fileHeaders = zipModel.getCentralDirectory().getFileHeaders();
        for (int i = 0; i < fileHeaders.size(); i++) {
            String fileName2 = fileHeaders.get(i).getFileName();
            if (Zip4jUtil.isStringNotNullAndNotEmpty(fileName2) && fileName.equalsIgnoreCase(fileName2)) {
                return i;
            }
        }
        return -1;
    }

    public static String decodeStringWithCharset(byte[] bArr, boolean z, Charset charset) {
        if (InternalZipConstants.CHARSET_UTF_8.equals(charset) && !z) {
            try {
                return new String(bArr, "Cp437");
            } catch (UnsupportedEncodingException unused) {
                return new String(bArr);
            }
        } else if (charset != null) {
            return new String(bArr, charset);
        } else {
            return new String(bArr, InternalZipConstants.CHARSET_UTF_8);
        }
    }

    public static long getOffsetOfNextEntry(ZipModel zipModel, FileHeader fileHeader) throws ZipException {
        int indexOfFileHeader = getIndexOfFileHeader(zipModel, fileHeader);
        List<FileHeader> fileHeaders = zipModel.getCentralDirectory().getFileHeaders();
        if (indexOfFileHeader == fileHeaders.size() - 1) {
            return getOffsetStartOfCentralDirectory(zipModel);
        }
        return fileHeaders.get(indexOfFileHeader + 1).getOffsetLocalHeader();
    }

    public static long getOffsetStartOfCentralDirectory(ZipModel zipModel) {
        if (zipModel.isZip64Format()) {
            return zipModel.getZip64EndOfCentralDirectoryRecord().getOffsetStartCentralDirectoryWRTStartDiskNumber();
        }
        return zipModel.getEndOfCentralDirectoryRecord().getOffsetOfStartOfCentralDirectory();
    }

    public static List<FileHeader> getFileHeadersUnderDirectory(List<FileHeader> list, final FileHeader fileHeader) {
        if (!fileHeader.isDirectory()) {
            return Collections.emptyList();
        }
        return (List) list.stream().filter(new Predicate() { // from class: net.lingala.zip4j.headers.-$$Lambda$HeaderUtil$feCtIlqQYV0tB-cFa7__Z2miugM
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean startsWith;
                startsWith = ((FileHeader) obj).getFileName().startsWith(FileHeader.this.getFileName());
                return startsWith;
            }
        }).collect(Collectors.toList());
    }

    public static long getTotalUncompressedSizeOfAllFileHeaders(List<FileHeader> list) {
        long j = 0;
        for (FileHeader fileHeader : list) {
            if (fileHeader.getZip64ExtendedInfo() != null && fileHeader.getZip64ExtendedInfo().getUncompressedSize() > 0) {
                j += fileHeader.getZip64ExtendedInfo().getUncompressedSize();
            } else {
                j += fileHeader.getUncompressedSize();
            }
        }
        return j;
    }

    private static FileHeader getFileHeaderWithExactMatch(ZipModel zipModel, String str) throws ZipException {
        if (zipModel == null) {
            throw new ZipException("zip model is null, cannot determine file header with exact match for fileName: " + str);
        } else if (!Zip4jUtil.isStringNotNullAndNotEmpty(str)) {
            throw new ZipException("file name is null, cannot determine file header with exact match for fileName: " + str);
        } else if (zipModel.getCentralDirectory() == null) {
            throw new ZipException("central directory is null, cannot determine file header with exact match for fileName: " + str);
        } else if (zipModel.getCentralDirectory().getFileHeaders() == null) {
            throw new ZipException("file Headers are null, cannot determine file header with exact match for fileName: " + str);
        } else if (zipModel.getCentralDirectory().getFileHeaders().size() == 0) {
            return null;
        } else {
            for (FileHeader fileHeader : zipModel.getCentralDirectory().getFileHeaders()) {
                String fileName = fileHeader.getFileName();
                if (Zip4jUtil.isStringNotNullAndNotEmpty(fileName) && str.equalsIgnoreCase(fileName)) {
                    return fileHeader;
                }
            }
            return null;
        }
    }
}
