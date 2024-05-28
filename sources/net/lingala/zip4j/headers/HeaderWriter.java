package net.lingala.zip4j.headers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.AESExtraDataRecord;
import net.lingala.zip4j.model.ExtraDataRecord;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.model.Zip64EndOfCentralDirectoryLocator;
import net.lingala.zip4j.model.Zip64EndOfCentralDirectoryRecord;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.p410io.outputstream.CountingOutputStream;
import net.lingala.zip4j.p410io.outputstream.OutputStreamWithSplitZipSupport;
import net.lingala.zip4j.p410io.outputstream.SplitOutputStream;
import net.lingala.zip4j.util.FileUtils;
import net.lingala.zip4j.util.RawIO;
import net.lingala.zip4j.util.Zip4jUtil;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class HeaderWriter {
    private static final short AES_EXTRA_DATA_RECORD_SIZE = 11;
    private static final short ZIP64_EXTRA_DATA_RECORD_SIZE_FH = 28;
    private static final short ZIP64_EXTRA_DATA_RECORD_SIZE_LFH = 16;
    private RawIO rawIO = new RawIO();
    private byte[] longBuff = new byte[8];
    private byte[] intBuff = new byte[4];

    /* JADX WARN: Removed duplicated region for block: B:11:0x006f A[Catch: Throwable -> 0x0167, TryCatch #2 {Throwable -> 0x0167, blocks: (B:3:0x0006, B:5:0x0061, B:11:0x006f, B:13:0x00aa, B:15:0x00b6, B:16:0x00be, B:20:0x00ca, B:22:0x00d0, B:23:0x00d2, B:25:0x00da, B:27:0x00df, B:28:0x0104, B:30:0x010a, B:31:0x015a, B:12:0x0087), top: B:50:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0087 A[Catch: Throwable -> 0x0167, TryCatch #2 {Throwable -> 0x0167, blocks: (B:3:0x0006, B:5:0x0061, B:11:0x006f, B:13:0x00aa, B:15:0x00b6, B:16:0x00be, B:20:0x00ca, B:22:0x00d0, B:23:0x00d2, B:25:0x00da, B:27:0x00df, B:28:0x0104, B:30:0x010a, B:31:0x015a, B:12:0x0087), top: B:50:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00b6 A[Catch: Throwable -> 0x0167, TryCatch #2 {Throwable -> 0x0167, blocks: (B:3:0x0006, B:5:0x0061, B:11:0x006f, B:13:0x00aa, B:15:0x00b6, B:16:0x00be, B:20:0x00ca, B:22:0x00d0, B:23:0x00d2, B:25:0x00da, B:27:0x00df, B:28:0x0104, B:30:0x010a, B:31:0x015a, B:12:0x0087), top: B:50:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00d0 A[Catch: Throwable -> 0x0167, TryCatch #2 {Throwable -> 0x0167, blocks: (B:3:0x0006, B:5:0x0061, B:11:0x006f, B:13:0x00aa, B:15:0x00b6, B:16:0x00be, B:20:0x00ca, B:22:0x00d0, B:23:0x00d2, B:25:0x00da, B:27:0x00df, B:28:0x0104, B:30:0x010a, B:31:0x015a, B:12:0x0087), top: B:50:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00da A[Catch: Throwable -> 0x0167, TryCatch #2 {Throwable -> 0x0167, blocks: (B:3:0x0006, B:5:0x0061, B:11:0x006f, B:13:0x00aa, B:15:0x00b6, B:16:0x00be, B:20:0x00ca, B:22:0x00d0, B:23:0x00d2, B:25:0x00da, B:27:0x00df, B:28:0x0104, B:30:0x010a, B:31:0x015a, B:12:0x0087), top: B:50:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00df A[Catch: Throwable -> 0x0167, TryCatch #2 {Throwable -> 0x0167, blocks: (B:3:0x0006, B:5:0x0061, B:11:0x006f, B:13:0x00aa, B:15:0x00b6, B:16:0x00be, B:20:0x00ca, B:22:0x00d0, B:23:0x00d2, B:25:0x00da, B:27:0x00df, B:28:0x0104, B:30:0x010a, B:31:0x015a, B:12:0x0087), top: B:50:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x010a A[Catch: Throwable -> 0x0167, TryCatch #2 {Throwable -> 0x0167, blocks: (B:3:0x0006, B:5:0x0061, B:11:0x006f, B:13:0x00aa, B:15:0x00b6, B:16:0x00be, B:20:0x00ca, B:22:0x00d0, B:23:0x00d2, B:25:0x00da, B:27:0x00df, B:28:0x0104, B:30:0x010a, B:31:0x015a, B:12:0x0087), top: B:50:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void writeLocalFileHeader(net.lingala.zip4j.model.ZipModel r12, net.lingala.zip4j.model.LocalFileHeader r13, java.io.OutputStream r14, java.nio.charset.Charset r15) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 377
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.headers.HeaderWriter.writeLocalFileHeader(net.lingala.zip4j.model.ZipModel, net.lingala.zip4j.model.LocalFileHeader, java.io.OutputStream, java.nio.charset.Charset):void");
    }

    public void writeExtendedLocalHeader(LocalFileHeader localFileHeader, OutputStream outputStream) throws IOException {
        if (localFileHeader == null || outputStream == null) {
            throw new ZipException("input parameters is null, cannot write extended local header");
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            this.rawIO.writeIntLittleEndian(byteArrayOutputStream, (int) HeaderSignature.EXTRA_DATA_RECORD.getValue());
            this.rawIO.writeLongLittleEndian(this.longBuff, 0, localFileHeader.getCrc());
            byteArrayOutputStream.write(this.longBuff, 0, 4);
            if (localFileHeader.isWriteCompressedSizeInZip64ExtraRecord()) {
                this.rawIO.writeLongLittleEndian(byteArrayOutputStream, localFileHeader.getCompressedSize());
                this.rawIO.writeLongLittleEndian(byteArrayOutputStream, localFileHeader.getUncompressedSize());
            } else {
                this.rawIO.writeLongLittleEndian(this.longBuff, 0, localFileHeader.getCompressedSize());
                byteArrayOutputStream.write(this.longBuff, 0, 4);
                this.rawIO.writeLongLittleEndian(this.longBuff, 0, localFileHeader.getUncompressedSize());
                byteArrayOutputStream.write(this.longBuff, 0, 4);
            }
            outputStream.write(byteArrayOutputStream.toByteArray());
            byteArrayOutputStream.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (th != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                } else {
                    byteArrayOutputStream.close();
                }
                throw th2;
            }
        }
    }

    public void finalizeZipFile(ZipModel zipModel, OutputStream outputStream, Charset charset) throws IOException {
        if (zipModel == null || outputStream == null) {
            throw new ZipException("input parameters is null, cannot finalize zip file");
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            processHeaderData(zipModel, outputStream);
            long offsetOfCentralDirectory = getOffsetOfCentralDirectory(zipModel);
            writeCentralDirectory(zipModel, byteArrayOutputStream, this.rawIO, charset);
            int size = byteArrayOutputStream.size();
            if (zipModel.isZip64Format() || offsetOfCentralDirectory >= 4294967295L || zipModel.getCentralDirectory().getFileHeaders().size() >= 65535) {
                if (zipModel.getZip64EndOfCentralDirectoryRecord() == null) {
                    zipModel.setZip64EndOfCentralDirectoryRecord(new Zip64EndOfCentralDirectoryRecord());
                }
                if (zipModel.getZip64EndOfCentralDirectoryLocator() == null) {
                    zipModel.setZip64EndOfCentralDirectoryLocator(new Zip64EndOfCentralDirectoryLocator());
                }
                zipModel.getZip64EndOfCentralDirectoryLocator().setOffsetZip64EndOfCentralDirectoryRecord(size + offsetOfCentralDirectory);
                if (isSplitZipFile(outputStream)) {
                    int currentSplitFileCounter = getCurrentSplitFileCounter(outputStream);
                    zipModel.getZip64EndOfCentralDirectoryLocator().setNumberOfDiskStartOfZip64EndOfCentralDirectoryRecord(currentSplitFileCounter);
                    zipModel.getZip64EndOfCentralDirectoryLocator().setTotalNumberOfDiscs(currentSplitFileCounter + 1);
                } else {
                    zipModel.getZip64EndOfCentralDirectoryLocator().setNumberOfDiskStartOfZip64EndOfCentralDirectoryRecord(0);
                    zipModel.getZip64EndOfCentralDirectoryLocator().setTotalNumberOfDiscs(1);
                }
                writeZip64EndOfCentralDirectoryRecord(zipModel, size, offsetOfCentralDirectory, byteArrayOutputStream, this.rawIO);
                writeZip64EndOfCentralDirectoryLocator(zipModel, byteArrayOutputStream, this.rawIO);
            }
            writeEndOfCentralDirectoryRecord(zipModel, size, offsetOfCentralDirectory, byteArrayOutputStream, this.rawIO, charset);
            writeZipHeaderBytes(zipModel, outputStream, byteArrayOutputStream.toByteArray(), charset);
            byteArrayOutputStream.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (th != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                } else {
                    byteArrayOutputStream.close();
                }
                throw th2;
            }
        }
    }

    public void finalizeZipFileWithoutValidations(ZipModel zipModel, OutputStream outputStream, Charset charset) throws IOException {
        if (zipModel == null || outputStream == null) {
            throw new ZipException("input parameters is null, cannot finalize zip file without validations");
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            long offsetOfStartOfCentralDirectory = zipModel.getEndOfCentralDirectoryRecord().getOffsetOfStartOfCentralDirectory();
            writeCentralDirectory(zipModel, byteArrayOutputStream, this.rawIO, charset);
            int size = byteArrayOutputStream.size();
            if (zipModel.isZip64Format() || offsetOfStartOfCentralDirectory >= 4294967295L || zipModel.getCentralDirectory().getFileHeaders().size() >= 65535) {
                if (zipModel.getZip64EndOfCentralDirectoryRecord() == null) {
                    zipModel.setZip64EndOfCentralDirectoryRecord(new Zip64EndOfCentralDirectoryRecord());
                }
                if (zipModel.getZip64EndOfCentralDirectoryLocator() == null) {
                    zipModel.setZip64EndOfCentralDirectoryLocator(new Zip64EndOfCentralDirectoryLocator());
                }
                zipModel.getZip64EndOfCentralDirectoryLocator().setOffsetZip64EndOfCentralDirectoryRecord(size + offsetOfStartOfCentralDirectory);
                writeZip64EndOfCentralDirectoryRecord(zipModel, size, offsetOfStartOfCentralDirectory, byteArrayOutputStream, this.rawIO);
                writeZip64EndOfCentralDirectoryLocator(zipModel, byteArrayOutputStream, this.rawIO);
            }
            writeEndOfCentralDirectoryRecord(zipModel, size, offsetOfStartOfCentralDirectory, byteArrayOutputStream, this.rawIO, charset);
            writeZipHeaderBytes(zipModel, outputStream, byteArrayOutputStream.toByteArray(), charset);
            byteArrayOutputStream.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (th != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                } else {
                    byteArrayOutputStream.close();
                }
                throw th2;
            }
        }
    }

    public void updateLocalFileHeader(FileHeader fileHeader, ZipModel zipModel, SplitOutputStream splitOutputStream) throws IOException {
        SplitOutputStream splitOutputStream2;
        String str;
        if (fileHeader == null || zipModel == null) {
            throw new ZipException("invalid input parameters, cannot update local file header");
        }
        boolean z = true;
        if (fileHeader.getDiskNumberStart() != splitOutputStream.getCurrentSplitFileCounter()) {
            String parent = zipModel.getZipFile().getParent();
            String zipFileNameWithoutExtension = FileUtils.getZipFileNameWithoutExtension(zipModel.getZipFile().getName());
            String str2 = parent + System.getProperty("file.separator");
            if (fileHeader.getDiskNumberStart() < 9) {
                str = str2 + zipFileNameWithoutExtension + ".z0" + (fileHeader.getDiskNumberStart() + 1);
            } else {
                str = str2 + zipFileNameWithoutExtension + ".z" + (fileHeader.getDiskNumberStart() + 1);
            }
            splitOutputStream2 = new SplitOutputStream(new File(str));
        } else {
            splitOutputStream2 = splitOutputStream;
            z = false;
        }
        long filePointer = splitOutputStream2.getFilePointer();
        splitOutputStream2.seek(fileHeader.getOffsetLocalHeader() + 14);
        this.rawIO.writeLongLittleEndian(this.longBuff, 0, fileHeader.getCrc());
        splitOutputStream2.write(this.longBuff, 0, 4);
        updateFileSizesInLocalFileHeader(splitOutputStream2, fileHeader);
        if (z) {
            splitOutputStream2.close();
        } else {
            splitOutputStream.seek(filePointer);
        }
    }

    private void updateFileSizesInLocalFileHeader(SplitOutputStream splitOutputStream, FileHeader fileHeader) throws IOException {
        if (fileHeader.getUncompressedSize() >= 4294967295L) {
            this.rawIO.writeLongLittleEndian(this.longBuff, 0, 4294967295L);
            splitOutputStream.write(this.longBuff, 0, 4);
            splitOutputStream.write(this.longBuff, 0, 4);
            int fileNameLength = fileHeader.getFileNameLength() + 4 + 2 + 2;
            if (splitOutputStream.skipBytes(fileNameLength) != fileNameLength) {
                throw new ZipException("Unable to skip " + fileNameLength + " bytes to update LFH");
            }
            this.rawIO.writeLongLittleEndian(splitOutputStream, fileHeader.getUncompressedSize());
            this.rawIO.writeLongLittleEndian(splitOutputStream, fileHeader.getCompressedSize());
            return;
        }
        this.rawIO.writeLongLittleEndian(this.longBuff, 0, fileHeader.getCompressedSize());
        splitOutputStream.write(this.longBuff, 0, 4);
        this.rawIO.writeLongLittleEndian(this.longBuff, 0, fileHeader.getUncompressedSize());
        splitOutputStream.write(this.longBuff, 0, 4);
    }

    private boolean isSplitZipFile(OutputStream outputStream) {
        if (outputStream instanceof SplitOutputStream) {
            return ((SplitOutputStream) outputStream).isSplitZipFile();
        }
        if (outputStream instanceof CountingOutputStream) {
            return ((CountingOutputStream) outputStream).isSplitZipFile();
        }
        return false;
    }

    private int getCurrentSplitFileCounter(OutputStream outputStream) {
        if (outputStream instanceof SplitOutputStream) {
            return ((SplitOutputStream) outputStream).getCurrentSplitFileCounter();
        }
        return ((CountingOutputStream) outputStream).getCurrentSplitFileCounter();
    }

    private void writeZipHeaderBytes(ZipModel zipModel, OutputStream outputStream, byte[] bArr, Charset charset) throws IOException {
        if (bArr == null) {
            throw new ZipException("invalid buff to write as zip headers");
        }
        if ((outputStream instanceof CountingOutputStream) && ((CountingOutputStream) outputStream).checkBuffSizeAndStartNextSplitFile(bArr.length)) {
            finalizeZipFile(zipModel, outputStream, charset);
        } else {
            outputStream.write(bArr);
        }
    }

    private void processHeaderData(ZipModel zipModel, OutputStream outputStream) throws IOException {
        int i;
        if (outputStream instanceof OutputStreamWithSplitZipSupport) {
            OutputStreamWithSplitZipSupport outputStreamWithSplitZipSupport = (OutputStreamWithSplitZipSupport) outputStream;
            zipModel.getEndOfCentralDirectoryRecord().setOffsetOfStartOfCentralDirectory(outputStreamWithSplitZipSupport.getFilePointer());
            i = outputStreamWithSplitZipSupport.getCurrentSplitFileCounter();
        } else {
            i = 0;
        }
        if (zipModel.isZip64Format()) {
            if (zipModel.getZip64EndOfCentralDirectoryRecord() == null) {
                zipModel.setZip64EndOfCentralDirectoryRecord(new Zip64EndOfCentralDirectoryRecord());
            }
            if (zipModel.getZip64EndOfCentralDirectoryLocator() == null) {
                zipModel.setZip64EndOfCentralDirectoryLocator(new Zip64EndOfCentralDirectoryLocator());
            }
            zipModel.getZip64EndOfCentralDirectoryRecord().setOffsetStartCentralDirectoryWRTStartDiskNumber(zipModel.getEndOfCentralDirectoryRecord().getOffsetOfStartOfCentralDirectory());
            zipModel.getZip64EndOfCentralDirectoryLocator().setNumberOfDiskStartOfZip64EndOfCentralDirectoryRecord(i);
            zipModel.getZip64EndOfCentralDirectoryLocator().setTotalNumberOfDiscs(i + 1);
        }
        zipModel.getEndOfCentralDirectoryRecord().setNumberOfThisDisk(i);
        zipModel.getEndOfCentralDirectoryRecord().setNumberOfThisDiskStartOfCentralDir(i);
    }

    private void writeCentralDirectory(ZipModel zipModel, ByteArrayOutputStream byteArrayOutputStream, RawIO rawIO, Charset charset) throws ZipException {
        if (zipModel.getCentralDirectory() == null || zipModel.getCentralDirectory().getFileHeaders() == null || zipModel.getCentralDirectory().getFileHeaders().size() <= 0) {
            return;
        }
        for (FileHeader fileHeader : zipModel.getCentralDirectory().getFileHeaders()) {
            writeFileHeader(zipModel, fileHeader, byteArrayOutputStream, rawIO, charset);
        }
    }

    private void writeFileHeader(ZipModel zipModel, FileHeader fileHeader, ByteArrayOutputStream byteArrayOutputStream, RawIO rawIO, Charset charset) throws ZipException {
        byte[] bArr;
        byte[] bArr2;
        if (fileHeader == null) {
            throw new ZipException("input parameters is null, cannot write local file header");
        }
        try {
            byte[] bArr3 = {0, 0};
            boolean isZip64Entry = isZip64Entry(fileHeader);
            rawIO.writeIntLittleEndian(byteArrayOutputStream, (int) fileHeader.getSignature().getValue());
            rawIO.writeShortLittleEndian(byteArrayOutputStream, fileHeader.getVersionMadeBy());
            rawIO.writeShortLittleEndian(byteArrayOutputStream, fileHeader.getVersionNeededToExtract());
            byteArrayOutputStream.write(fileHeader.getGeneralPurposeFlag());
            rawIO.writeShortLittleEndian(byteArrayOutputStream, fileHeader.getCompressionMethod().getCode());
            rawIO.writeLongLittleEndian(this.longBuff, 0, fileHeader.getLastModifiedTime());
            byteArrayOutputStream.write(this.longBuff, 0, 4);
            rawIO.writeLongLittleEndian(this.longBuff, 0, fileHeader.getCrc());
            byteArrayOutputStream.write(this.longBuff, 0, 4);
            if (isZip64Entry) {
                rawIO.writeLongLittleEndian(this.longBuff, 0, 4294967295L);
                byteArrayOutputStream.write(this.longBuff, 0, 4);
                byteArrayOutputStream.write(this.longBuff, 0, 4);
                zipModel.setZip64Format(true);
                bArr = bArr3;
            } else {
                bArr = bArr3;
                rawIO.writeLongLittleEndian(this.longBuff, 0, fileHeader.getCompressedSize());
                byteArrayOutputStream.write(this.longBuff, 0, 4);
                rawIO.writeLongLittleEndian(this.longBuff, 0, fileHeader.getUncompressedSize());
                byteArrayOutputStream.write(this.longBuff, 0, 4);
            }
            byte[] bArr4 = new byte[0];
            if (Zip4jUtil.isStringNotNullAndNotEmpty(fileHeader.getFileName())) {
                bArr4 = fileHeader.getFileName().getBytes(charset);
            }
            rawIO.writeShortLittleEndian(byteArrayOutputStream, bArr4.length);
            byte[] bArr5 = new byte[4];
            if (isZip64Entry) {
                rawIO.writeLongLittleEndian(this.longBuff, 0, 4294967295L);
                System.arraycopy(this.longBuff, 0, bArr5, 0, 4);
            } else {
                rawIO.writeLongLittleEndian(this.longBuff, 0, fileHeader.getOffsetLocalHeader());
                System.arraycopy(this.longBuff, 0, bArr5, 0, 4);
            }
            rawIO.writeShortLittleEndian(byteArrayOutputStream, calculateExtraDataRecordsSize(fileHeader, isZip64Entry));
            String fileComment = fileHeader.getFileComment();
            byte[] bArr6 = new byte[0];
            if (Zip4jUtil.isStringNotNullAndNotEmpty(fileComment)) {
                bArr6 = fileComment.getBytes(charset);
            }
            rawIO.writeShortLittleEndian(byteArrayOutputStream, bArr6.length);
            if (isZip64Entry) {
                rawIO.writeIntLittleEndian(this.intBuff, 0, 65535);
                byteArrayOutputStream.write(this.intBuff, 0, 2);
                bArr2 = bArr;
            } else {
                rawIO.writeShortLittleEndian(byteArrayOutputStream, fileHeader.getDiskNumberStart());
                bArr2 = bArr;
            }
            byteArrayOutputStream.write(bArr2);
            byteArrayOutputStream.write(fileHeader.getExternalFileAttributes());
            byteArrayOutputStream.write(bArr5);
            if (bArr4.length > 0) {
                byteArrayOutputStream.write(bArr4);
            }
            if (isZip64Entry) {
                zipModel.setZip64Format(true);
                rawIO.writeShortLittleEndian(byteArrayOutputStream, (int) HeaderSignature.ZIP64_EXTRA_FIELD_SIGNATURE.getValue());
                rawIO.writeShortLittleEndian(byteArrayOutputStream, 28);
                rawIO.writeLongLittleEndian(byteArrayOutputStream, fileHeader.getUncompressedSize());
                rawIO.writeLongLittleEndian(byteArrayOutputStream, fileHeader.getCompressedSize());
                rawIO.writeLongLittleEndian(byteArrayOutputStream, fileHeader.getOffsetLocalHeader());
                rawIO.writeIntLittleEndian(byteArrayOutputStream, fileHeader.getDiskNumberStart());
            }
            if (fileHeader.getAesExtraDataRecord() != null) {
                AESExtraDataRecord aesExtraDataRecord = fileHeader.getAesExtraDataRecord();
                rawIO.writeShortLittleEndian(byteArrayOutputStream, (int) aesExtraDataRecord.getSignature().getValue());
                rawIO.writeShortLittleEndian(byteArrayOutputStream, aesExtraDataRecord.getDataSize());
                rawIO.writeShortLittleEndian(byteArrayOutputStream, aesExtraDataRecord.getAesVersion().getVersionNumber());
                byteArrayOutputStream.write(aesExtraDataRecord.getVendorID().getBytes());
                byteArrayOutputStream.write(new byte[]{(byte) aesExtraDataRecord.getAesKeyStrength().getRawCode()});
                rawIO.writeShortLittleEndian(byteArrayOutputStream, aesExtraDataRecord.getCompressionMethod().getCode());
            }
            writeRemainingExtraDataRecordsIfPresent(fileHeader, byteArrayOutputStream);
            if (bArr6.length > 0) {
                byteArrayOutputStream.write(bArr6);
            }
        } catch (Exception e) {
            throw new ZipException(e);
        }
    }

    private int calculateExtraDataRecordsSize(FileHeader fileHeader, boolean z) throws IOException {
        int i = z ? 32 : 0;
        if (fileHeader.getAesExtraDataRecord() != null) {
            i += 11;
        }
        if (fileHeader.getExtraDataRecords() != null) {
            for (ExtraDataRecord extraDataRecord : fileHeader.getExtraDataRecords()) {
                if (extraDataRecord.getHeader() != HeaderSignature.AES_EXTRA_DATA_RECORD.getValue() && extraDataRecord.getHeader() != HeaderSignature.ZIP64_EXTRA_FIELD_SIGNATURE.getValue()) {
                    i += extraDataRecord.getSizeOfData() + 4;
                }
            }
        }
        return i;
    }

    private void writeRemainingExtraDataRecordsIfPresent(FileHeader fileHeader, OutputStream outputStream) throws IOException {
        if (fileHeader.getExtraDataRecords() == null || fileHeader.getExtraDataRecords().size() == 0) {
            return;
        }
        for (ExtraDataRecord extraDataRecord : fileHeader.getExtraDataRecords()) {
            if (extraDataRecord.getHeader() != HeaderSignature.AES_EXTRA_DATA_RECORD.getValue() && extraDataRecord.getHeader() != HeaderSignature.ZIP64_EXTRA_FIELD_SIGNATURE.getValue()) {
                this.rawIO.writeShortLittleEndian(outputStream, (int) extraDataRecord.getHeader());
                this.rawIO.writeShortLittleEndian(outputStream, extraDataRecord.getSizeOfData());
                if (extraDataRecord.getSizeOfData() > 0 && extraDataRecord.getData() != null) {
                    outputStream.write(extraDataRecord.getData());
                }
            }
        }
    }

    private void writeZip64EndOfCentralDirectoryRecord(ZipModel zipModel, int i, long j, ByteArrayOutputStream byteArrayOutputStream, RawIO rawIO) throws IOException {
        byte[] bArr = {0, 0};
        rawIO.writeIntLittleEndian(byteArrayOutputStream, (int) HeaderSignature.ZIP64_END_CENTRAL_DIRECTORY_RECORD.getValue());
        rawIO.writeLongLittleEndian(byteArrayOutputStream, 44L);
        if (zipModel.getCentralDirectory() != null && zipModel.getCentralDirectory().getFileHeaders() != null && zipModel.getCentralDirectory().getFileHeaders().size() > 0) {
            rawIO.writeShortLittleEndian(byteArrayOutputStream, zipModel.getCentralDirectory().getFileHeaders().get(0).getVersionMadeBy());
            rawIO.writeShortLittleEndian(byteArrayOutputStream, zipModel.getCentralDirectory().getFileHeaders().get(0).getVersionNeededToExtract());
        } else {
            byteArrayOutputStream.write(bArr);
            byteArrayOutputStream.write(bArr);
        }
        rawIO.writeIntLittleEndian(byteArrayOutputStream, zipModel.getEndOfCentralDirectoryRecord().getNumberOfThisDisk());
        rawIO.writeIntLittleEndian(byteArrayOutputStream, zipModel.getEndOfCentralDirectoryRecord().getNumberOfThisDiskStartOfCentralDir());
        long size = zipModel.getCentralDirectory().getFileHeaders().size();
        rawIO.writeLongLittleEndian(byteArrayOutputStream, zipModel.isSplitArchive() ? countNumberOfFileHeaderEntriesOnDisk(zipModel.getCentralDirectory().getFileHeaders(), zipModel.getEndOfCentralDirectoryRecord().getNumberOfThisDisk()) : size);
        rawIO.writeLongLittleEndian(byteArrayOutputStream, size);
        rawIO.writeLongLittleEndian(byteArrayOutputStream, i);
        rawIO.writeLongLittleEndian(byteArrayOutputStream, j);
    }

    private void writeZip64EndOfCentralDirectoryLocator(ZipModel zipModel, ByteArrayOutputStream byteArrayOutputStream, RawIO rawIO) throws IOException {
        rawIO.writeIntLittleEndian(byteArrayOutputStream, (int) HeaderSignature.ZIP64_END_CENTRAL_DIRECTORY_LOCATOR.getValue());
        rawIO.writeIntLittleEndian(byteArrayOutputStream, zipModel.getZip64EndOfCentralDirectoryLocator().getNumberOfDiskStartOfZip64EndOfCentralDirectoryRecord());
        rawIO.writeLongLittleEndian(byteArrayOutputStream, zipModel.getZip64EndOfCentralDirectoryLocator().getOffsetZip64EndOfCentralDirectoryRecord());
        rawIO.writeIntLittleEndian(byteArrayOutputStream, zipModel.getZip64EndOfCentralDirectoryLocator().getTotalNumberOfDiscs());
    }

    private void writeEndOfCentralDirectoryRecord(ZipModel zipModel, int i, long j, ByteArrayOutputStream byteArrayOutputStream, RawIO rawIO, Charset charset) throws IOException {
        byte[] bArr = new byte[8];
        rawIO.writeIntLittleEndian(byteArrayOutputStream, (int) HeaderSignature.END_OF_CENTRAL_DIRECTORY.getValue());
        rawIO.writeShortLittleEndian(byteArrayOutputStream, zipModel.getEndOfCentralDirectoryRecord().getNumberOfThisDisk());
        rawIO.writeShortLittleEndian(byteArrayOutputStream, zipModel.getEndOfCentralDirectoryRecord().getNumberOfThisDiskStartOfCentralDir());
        long size = zipModel.getCentralDirectory().getFileHeaders().size();
        long countNumberOfFileHeaderEntriesOnDisk = zipModel.isSplitArchive() ? countNumberOfFileHeaderEntriesOnDisk(zipModel.getCentralDirectory().getFileHeaders(), zipModel.getEndOfCentralDirectoryRecord().getNumberOfThisDisk()) : size;
        if (countNumberOfFileHeaderEntriesOnDisk > 65535) {
            countNumberOfFileHeaderEntriesOnDisk = 65535;
        }
        rawIO.writeShortLittleEndian(byteArrayOutputStream, (int) countNumberOfFileHeaderEntriesOnDisk);
        if (size > 65535) {
            size = 65535;
        }
        rawIO.writeShortLittleEndian(byteArrayOutputStream, (int) size);
        rawIO.writeIntLittleEndian(byteArrayOutputStream, i);
        if (j > 4294967295L) {
            rawIO.writeLongLittleEndian(bArr, 0, 4294967295L);
            byteArrayOutputStream.write(bArr, 0, 4);
        } else {
            rawIO.writeLongLittleEndian(bArr, 0, j);
            byteArrayOutputStream.write(bArr, 0, 4);
        }
        String comment = zipModel.getEndOfCentralDirectoryRecord().getComment();
        if (Zip4jUtil.isStringNotNullAndNotEmpty(comment)) {
            byte[] bytes = comment.getBytes(charset);
            rawIO.writeShortLittleEndian(byteArrayOutputStream, bytes.length);
            byteArrayOutputStream.write(bytes);
            return;
        }
        rawIO.writeShortLittleEndian(byteArrayOutputStream, 0);
    }

    private long countNumberOfFileHeaderEntriesOnDisk(List<FileHeader> list, int i) throws ZipException {
        if (list == null) {
            throw new ZipException("file headers are null, cannot calculate number of entries on this disk");
        }
        int i2 = 0;
        for (FileHeader fileHeader : list) {
            if (fileHeader.getDiskNumberStart() == i) {
                i2++;
            }
        }
        return i2;
    }

    private boolean isZip64Entry(FileHeader fileHeader) {
        return fileHeader.getCompressedSize() >= 4294967295L || fileHeader.getUncompressedSize() >= 4294967295L || fileHeader.getOffsetLocalHeader() >= 4294967295L || fileHeader.getDiskNumberStart() >= 65535;
    }

    private long getOffsetOfCentralDirectory(ZipModel zipModel) {
        if (zipModel.isZip64Format() && zipModel.getZip64EndOfCentralDirectoryRecord() != null && zipModel.getZip64EndOfCentralDirectoryRecord().getOffsetStartCentralDirectoryWRTStartDiskNumber() != -1) {
            return zipModel.getZip64EndOfCentralDirectoryRecord().getOffsetStartCentralDirectoryWRTStartDiskNumber();
        }
        return zipModel.getEndOfCentralDirectoryRecord().getOffsetOfStartOfCentralDirectory();
    }
}
