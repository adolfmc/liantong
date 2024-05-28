package net.lingala.zip4j.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.p410io.inputstream.NumberedSplitInputStream;
import net.lingala.zip4j.p410io.inputstream.SplitInputStream;
import net.lingala.zip4j.p410io.inputstream.ZipInputStream;
import net.lingala.zip4j.p410io.inputstream.ZipStandardSplitInputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class UnzipUtil {
    public static ZipInputStream createZipInputStream(ZipModel zipModel, FileHeader fileHeader, char[] cArr) throws IOException {
        SplitInputStream splitInputStream;
        try {
            splitInputStream = createSplitInputStream(zipModel);
            try {
                splitInputStream.prepareExtractionForFileHeader(fileHeader);
                ZipInputStream zipInputStream = new ZipInputStream(splitInputStream, cArr);
                if (zipInputStream.getNextEntry(fileHeader) != null) {
                    return zipInputStream;
                }
                throw new ZipException("Could not locate local file header for corresponding file header");
            } catch (IOException e) {
                e = e;
                if (splitInputStream != null) {
                    splitInputStream.close();
                }
                throw e;
            }
        } catch (IOException e2) {
            e = e2;
            splitInputStream = null;
        }
    }

    public static void applyFileAttributes(FileHeader fileHeader, File file) {
        try {
            Path path = file.toPath();
            FileUtils.setFileAttributes(path, fileHeader.getExternalFileAttributes());
            FileUtils.setFileLastModifiedTime(path, fileHeader.getLastModifiedTime());
        } catch (NoSuchMethodError unused) {
            FileUtils.setFileLastModifiedTimeWithoutNio(file, fileHeader.getLastModifiedTime());
        }
    }

    public static SplitInputStream createSplitInputStream(ZipModel zipModel) throws IOException {
        if (zipModel.getZipFile().getName().endsWith(".zip.001")) {
            return new NumberedSplitInputStream(zipModel.getZipFile(), true, zipModel.getEndOfCentralDirectoryRecord().getNumberOfThisDisk());
        }
        return new ZipStandardSplitInputStream(zipModel.getZipFile(), zipModel.isSplitArchive(), zipModel.getEndOfCentralDirectoryRecord().getNumberOfThisDisk());
    }
}
