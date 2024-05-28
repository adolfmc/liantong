package net.lingala.zip4j.tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderUtil;
import net.lingala.zip4j.headers.HeaderWriter;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import net.lingala.zip4j.p410io.outputstream.SplitOutputStream;
import net.lingala.zip4j.p410io.outputstream.ZipOutputStream;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;
import net.lingala.zip4j.tasks.RemoveFilesFromZipTask;
import net.lingala.zip4j.util.BitUtils;
import net.lingala.zip4j.util.CrcUtil;
import net.lingala.zip4j.util.FileUtils;
import net.lingala.zip4j.util.Zip4jUtil;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class AbstractAddFileToZipTask<T> extends AsyncZipTask<T> {
    private HeaderWriter headerWriter;
    private char[] password;
    private byte[] readBuff;
    private int readLen;
    private ZipModel zipModel;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractAddFileToZipTask(ZipModel zipModel, char[] cArr, HeaderWriter headerWriter, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(asyncTaskParameters);
        this.readBuff = new byte[4096];
        this.readLen = -1;
        this.zipModel = zipModel;
        this.password = cArr;
        this.headerWriter = headerWriter;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addFilesToZip(List<File> list, ProgressMonitor progressMonitor, ZipParameters zipParameters, Charset charset) throws IOException {
        FileUtils.assertFilesExist(list, zipParameters.getSymbolicLinkAction());
        List<File> removeFilesIfExists = removeFilesIfExists(list, zipParameters, progressMonitor, charset);
        SplitOutputStream splitOutputStream = new SplitOutputStream(this.zipModel.getZipFile(), this.zipModel.getSplitLength());
        try {
            ZipOutputStream initializeOutputStream = initializeOutputStream(splitOutputStream, charset);
            for (File file : removeFilesIfExists) {
                verifyIfTaskIsCancelled();
                ZipParameters cloneAndAdjustZipParameters = cloneAndAdjustZipParameters(zipParameters, file, progressMonitor);
                progressMonitor.setFileName(file.getAbsolutePath());
                if (FileUtils.isSymbolicLink(file) && addSymlink(cloneAndAdjustZipParameters)) {
                    addSymlinkToZip(file, initializeOutputStream, cloneAndAdjustZipParameters, splitOutputStream);
                    if (ZipParameters.SymbolicLinkAction.INCLUDE_LINK_ONLY.equals(cloneAndAdjustZipParameters.getSymbolicLinkAction())) {
                    }
                }
                addFileToZip(file, initializeOutputStream, cloneAndAdjustZipParameters, splitOutputStream, progressMonitor);
            }
            if (initializeOutputStream != null) {
                initializeOutputStream.close();
            }
            splitOutputStream.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (th != null) {
                    try {
                        splitOutputStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                } else {
                    splitOutputStream.close();
                }
                throw th2;
            }
        }
    }

    private void addSymlinkToZip(File file, ZipOutputStream zipOutputStream, ZipParameters zipParameters, SplitOutputStream splitOutputStream) throws IOException {
        ZipParameters zipParameters2 = new ZipParameters(zipParameters);
        zipParameters2.setFileNameInZip(replaceFileNameInZip(zipParameters.getFileNameInZip(), file.getName()));
        zipParameters2.setEncryptFiles(false);
        zipParameters2.setCompressionMethod(CompressionMethod.STORE);
        zipOutputStream.putNextEntry(zipParameters2);
        zipOutputStream.write(FileUtils.readSymbolicLink(file).getBytes());
        closeEntry(zipOutputStream, splitOutputStream, file, true);
    }

    private void addFileToZip(File file, ZipOutputStream zipOutputStream, ZipParameters zipParameters, SplitOutputStream splitOutputStream, ProgressMonitor progressMonitor) throws IOException {
        zipOutputStream.putNextEntry(zipParameters);
        if (!file.isDirectory()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                try {
                    int read = fileInputStream.read(this.readBuff);
                    this.readLen = read;
                    if (read == -1) {
                        break;
                    }
                    zipOutputStream.write(this.readBuff, 0, this.readLen);
                    progressMonitor.updateWorkCompleted(this.readLen);
                    verifyIfTaskIsCancelled();
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        if (th != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                            }
                        } else {
                            fileInputStream.close();
                        }
                        throw th2;
                    }
                }
            }
            fileInputStream.close();
        }
        closeEntry(zipOutputStream, splitOutputStream, file, false);
    }

    private void closeEntry(ZipOutputStream zipOutputStream, SplitOutputStream splitOutputStream, File file, boolean z) throws IOException {
        FileHeader closeEntry = zipOutputStream.closeEntry();
        byte[] fileAttributes = FileUtils.getFileAttributes(file);
        if (!z) {
            fileAttributes[3] = BitUtils.unsetBit(fileAttributes[3], 5);
        }
        closeEntry.setExternalFileAttributes(fileAttributes);
        updateLocalFileHeader(closeEntry, splitOutputStream);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long calculateWorkForFiles(List<File> list, ZipParameters zipParameters) throws ZipException {
        long j = 0;
        for (File file : list) {
            if (file.exists()) {
                if (zipParameters.isEncryptFiles() && zipParameters.getEncryptionMethod() == EncryptionMethod.ZIP_STANDARD) {
                    j += file.length() * 2;
                } else {
                    j += file.length();
                }
                FileHeader fileHeader = HeaderUtil.getFileHeader(getZipModel(), FileUtils.getRelativeFileName(file, zipParameters));
                if (fileHeader != null) {
                    j += getZipModel().getZipFile().length() - fileHeader.getCompressedSize();
                }
            }
        }
        return j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZipOutputStream initializeOutputStream(SplitOutputStream splitOutputStream, Charset charset) throws IOException {
        if (this.zipModel.getZipFile().exists()) {
            splitOutputStream.seek(HeaderUtil.getOffsetStartOfCentralDirectory(this.zipModel));
        }
        return new ZipOutputStream(splitOutputStream, this.password, charset, this.zipModel);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void verifyZipParameters(ZipParameters zipParameters) throws ZipException {
        if (zipParameters == null) {
            throw new ZipException("cannot validate zip parameters");
        }
        if (zipParameters.getCompressionMethod() != CompressionMethod.STORE && zipParameters.getCompressionMethod() != CompressionMethod.DEFLATE) {
            throw new ZipException("unsupported compression type");
        }
        if (zipParameters.isEncryptFiles()) {
            if (zipParameters.getEncryptionMethod() == EncryptionMethod.NONE) {
                throw new ZipException("Encryption method has to be set, when encrypt files flag is set");
            }
            char[] cArr = this.password;
            if (cArr == null || cArr.length <= 0) {
                throw new ZipException("input password is empty or null");
            }
            return;
        }
        zipParameters.setEncryptionMethod(EncryptionMethod.NONE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateLocalFileHeader(FileHeader fileHeader, SplitOutputStream splitOutputStream) throws IOException {
        this.headerWriter.updateLocalFileHeader(fileHeader, getZipModel(), splitOutputStream);
    }

    private ZipParameters cloneAndAdjustZipParameters(ZipParameters zipParameters, File file, ProgressMonitor progressMonitor) throws IOException {
        ZipParameters zipParameters2 = new ZipParameters(zipParameters);
        zipParameters2.setLastModifiedFileTime(Zip4jUtil.javaToDosTime(file.lastModified()));
        if (file.isDirectory()) {
            zipParameters2.setEntrySize(0L);
        } else {
            zipParameters2.setEntrySize(file.length());
        }
        zipParameters2.setWriteExtendedLocalFileHeader(false);
        zipParameters2.setLastModifiedFileTime(file.lastModified());
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(zipParameters.getFileNameInZip())) {
            zipParameters2.setFileNameInZip(FileUtils.getRelativeFileName(file, zipParameters));
        }
        if (file.isDirectory()) {
            zipParameters2.setCompressionMethod(CompressionMethod.STORE);
            zipParameters2.setEncryptionMethod(EncryptionMethod.NONE);
            zipParameters2.setEncryptFiles(false);
        } else {
            if (zipParameters2.isEncryptFiles() && zipParameters2.getEncryptionMethod() == EncryptionMethod.ZIP_STANDARD) {
                progressMonitor.setCurrentTask(ProgressMonitor.Task.CALCULATE_CRC);
                zipParameters2.setEntryCRC(CrcUtil.computeFileCrc(file, progressMonitor));
                progressMonitor.setCurrentTask(ProgressMonitor.Task.ADD_ENTRY);
            }
            if (file.length() == 0) {
                zipParameters2.setCompressionMethod(CompressionMethod.STORE);
            }
        }
        return zipParameters2;
    }

    private List<File> removeFilesIfExists(List<File> list, ZipParameters zipParameters, ProgressMonitor progressMonitor, Charset charset) throws ZipException {
        ArrayList arrayList = new ArrayList(list);
        if (this.zipModel.getZipFile().exists()) {
            for (File file : list) {
                FileHeader fileHeader = HeaderUtil.getFileHeader(this.zipModel, FileUtils.getRelativeFileName(file, zipParameters));
                if (fileHeader != null) {
                    if (zipParameters.isOverrideExistingFilesInZip()) {
                        progressMonitor.setCurrentTask(ProgressMonitor.Task.REMOVE_ENTRY);
                        removeFile(fileHeader, progressMonitor, charset);
                        verifyIfTaskIsCancelled();
                        progressMonitor.setCurrentTask(ProgressMonitor.Task.ADD_ENTRY);
                    } else {
                        arrayList.remove(file);
                    }
                }
            }
            return arrayList;
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeFile(FileHeader fileHeader, ProgressMonitor progressMonitor, Charset charset) throws ZipException {
        new RemoveFilesFromZipTask(this.zipModel, this.headerWriter, new AsyncZipTask.AsyncTaskParameters(null, false, progressMonitor)).execute(new RemoveFilesFromZipTask.RemoveFilesFromZipTaskParameters(Collections.singletonList(fileHeader.getFileName()), charset));
    }

    private String replaceFileNameInZip(String str, String str2) {
        if (str.contains("/")) {
            return str.substring(0, str.lastIndexOf("/") + 1) + str2;
        }
        return str2;
    }

    private boolean addSymlink(ZipParameters zipParameters) {
        return ZipParameters.SymbolicLinkAction.INCLUDE_LINK_ONLY.equals(zipParameters.getSymbolicLinkAction()) || ZipParameters.SymbolicLinkAction.INCLUDE_LINK_AND_LINKED_FILE.equals(zipParameters.getSymbolicLinkAction());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public ProgressMonitor.Task getTask() {
        return ProgressMonitor.Task.ADD_ENTRY;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ZipModel getZipModel() {
        return this.zipModel;
    }
}
