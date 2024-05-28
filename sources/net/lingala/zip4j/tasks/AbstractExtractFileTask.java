package net.lingala.zip4j.tasks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.regex.Matcher;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.p410io.inputstream.ZipInputStream;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;
import net.lingala.zip4j.util.BitUtils;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.UnzipUtil;
import net.lingala.zip4j.util.Zip4jUtil;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class AbstractExtractFileTask<T> extends AsyncZipTask<T> {
    private byte[] buff;
    private ZipModel zipModel;

    public AbstractExtractFileTask(ZipModel zipModel, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(asyncTaskParameters);
        this.buff = new byte[4096];
        this.zipModel = zipModel;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void extractFile(ZipInputStream zipInputStream, FileHeader fileHeader, String str, String str2, ProgressMonitor progressMonitor) throws IOException {
        if (!str.endsWith(InternalZipConstants.FILE_SEPARATOR)) {
            str = str + InternalZipConstants.FILE_SEPARATOR;
        }
        File determineOutputFile = determineOutputFile(fileHeader, str, str2);
        progressMonitor.setFileName(determineOutputFile.getAbsolutePath());
        if (!determineOutputFile.getCanonicalPath().startsWith(new File(str).getCanonicalPath() + File.separator)) {
            throw new ZipException("illegal file name that breaks out of the target directory: " + fileHeader.getFileName());
        }
        verifyNextEntry(zipInputStream, fileHeader);
        if (fileHeader.isDirectory()) {
            if (determineOutputFile.exists() || determineOutputFile.mkdirs()) {
                return;
            }
            throw new ZipException("Could not create directory: " + determineOutputFile);
        } else if (isSymbolicLink(fileHeader)) {
            createSymLink(zipInputStream, fileHeader, determineOutputFile, progressMonitor);
        } else {
            checkOutputDirectoryStructure(determineOutputFile);
            unzipFile(zipInputStream, fileHeader, determineOutputFile, progressMonitor);
        }
    }

    private boolean isSymbolicLink(FileHeader fileHeader) {
        byte[] externalFileAttributes = fileHeader.getExternalFileAttributes();
        if (externalFileAttributes == null || externalFileAttributes.length < 4) {
            return false;
        }
        return BitUtils.isBitSet(externalFileAttributes[3], 5);
    }

    private void unzipFile(ZipInputStream zipInputStream, FileHeader fileHeader, File file, ProgressMonitor progressMonitor) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            while (true) {
                int read = zipInputStream.read(this.buff);
                if (read != -1) {
                    fileOutputStream.write(this.buff, 0, read);
                    progressMonitor.updateWorkCompleted(read);
                    verifyIfTaskIsCancelled();
                } else {
                    fileOutputStream.close();
                    UnzipUtil.applyFileAttributes(fileHeader, file);
                    return;
                }
            }
        } catch (Exception e) {
            if (file.exists()) {
                file.delete();
            }
            throw e;
        }
    }

    private void createSymLink(ZipInputStream zipInputStream, FileHeader fileHeader, File file, ProgressMonitor progressMonitor) throws IOException {
        String str = new String(readCompleteEntry(zipInputStream, fileHeader, progressMonitor));
        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            throw new ZipException("Could not create parent directories");
        }
        try {
            Files.createSymbolicLink(file.toPath(), Paths.get(str, new String[0]), new FileAttribute[0]);
            UnzipUtil.applyFileAttributes(fileHeader, file);
        } catch (NoSuchMethodError unused) {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(str.getBytes());
                fileOutputStream.close();
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (th != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    } else {
                        fileOutputStream.close();
                    }
                    throw th2;
                }
            }
        }
    }

    private byte[] readCompleteEntry(ZipInputStream zipInputStream, FileHeader fileHeader, ProgressMonitor progressMonitor) throws IOException {
        byte[] bArr = new byte[(int) fileHeader.getUncompressedSize()];
        if (zipInputStream.read(bArr) != bArr.length) {
            throw new ZipException("Could not read complete entry");
        }
        progressMonitor.updateWorkCompleted(bArr.length);
        return bArr;
    }

    private void verifyNextEntry(ZipInputStream zipInputStream, FileHeader fileHeader) throws IOException {
        LocalFileHeader nextEntry = zipInputStream.getNextEntry(fileHeader);
        if (nextEntry == null) {
            throw new ZipException("Could not read corresponding local file header for file header: " + fileHeader.getFileName());
        } else if (!fileHeader.getFileName().equals(nextEntry.getFileName())) {
            throw new ZipException("File header and local file header mismatch");
        }
    }

    private void checkOutputDirectoryStructure(File file) throws ZipException {
        if (file.getParentFile().exists() || file.getParentFile().mkdirs()) {
            return;
        }
        throw new ZipException("Unable to create parent directories: " + file.getParentFile());
    }

    private File determineOutputFile(FileHeader fileHeader, String str, String str2) {
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(str2)) {
            str2 = getFileNameWithSystemFileSeparators(fileHeader.getFileName());
        }
        return new File(str + InternalZipConstants.FILE_SEPARATOR + str2);
    }

    private String getFileNameWithSystemFileSeparators(String str) {
        return str.replaceAll("[/\\\\]", Matcher.quoteReplacement(InternalZipConstants.FILE_SEPARATOR));
    }

    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    protected ProgressMonitor.Task getTask() {
        return ProgressMonitor.Task.EXTRACT_ENTRY;
    }

    public ZipModel getZipModel() {
        return this.zipModel;
    }
}
