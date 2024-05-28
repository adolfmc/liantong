package net.lingala.zip4j.headers;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.AESExtraDataRecord;
import net.lingala.zip4j.model.CentralDirectory;
import net.lingala.zip4j.model.DataDescriptor;
import net.lingala.zip4j.model.DigitalSignature;
import net.lingala.zip4j.model.EndOfCentralDirectoryRecord;
import net.lingala.zip4j.model.ExtraDataRecord;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.model.Zip64EndOfCentralDirectoryLocator;
import net.lingala.zip4j.model.Zip64EndOfCentralDirectoryRecord;
import net.lingala.zip4j.model.Zip64ExtendedInfo;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.AesVersion;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import net.lingala.zip4j.p410io.inputstream.NumberedSplitRandomAccessFile;
import net.lingala.zip4j.util.BitUtils;
import net.lingala.zip4j.util.RawIO;
import net.lingala.zip4j.util.Zip4jUtil;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class HeaderReader {
    private ZipModel zipModel;
    private RawIO rawIO = new RawIO();
    private byte[] intBuff = new byte[4];

    public ZipModel readAllHeaders(RandomAccessFile randomAccessFile, Charset charset) throws IOException {
        if (randomAccessFile.length() < 22) {
            throw new ZipException("Zip file size less than minimum expected zip file size. Probably not a zip file or a corrupted zip file");
        }
        this.zipModel = new ZipModel();
        try {
            this.zipModel.setEndOfCentralDirectoryRecord(readEndOfCentralDirectoryRecord(randomAccessFile, this.rawIO, charset));
            if (this.zipModel.getEndOfCentralDirectoryRecord().getTotalNumberOfEntriesInCentralDirectory() == 0) {
                return this.zipModel;
            }
            ZipModel zipModel = this.zipModel;
            zipModel.setZip64EndOfCentralDirectoryLocator(readZip64EndOfCentralDirectoryLocator(randomAccessFile, this.rawIO, zipModel.getEndOfCentralDirectoryRecord().getOffsetOfEndOfCentralDirectory()));
            if (this.zipModel.isZip64Format()) {
                this.zipModel.setZip64EndOfCentralDirectoryRecord(readZip64EndCentralDirRec(randomAccessFile, this.rawIO));
                if (this.zipModel.getZip64EndOfCentralDirectoryRecord() != null && this.zipModel.getZip64EndOfCentralDirectoryRecord().getNumberOfThisDisk() > 0) {
                    this.zipModel.setSplitArchive(true);
                } else {
                    this.zipModel.setSplitArchive(false);
                }
            }
            this.zipModel.setCentralDirectory(readCentralDirectory(randomAccessFile, this.rawIO, charset));
            return this.zipModel;
        } catch (ZipException e) {
            throw e;
        } catch (IOException e2) {
            throw new ZipException("Zip headers not found. Probably not a zip file or a corrupted zip file", e2);
        }
    }

    private EndOfCentralDirectoryRecord readEndOfCentralDirectoryRecord(RandomAccessFile randomAccessFile, RawIO rawIO, Charset charset) throws IOException {
        long length = randomAccessFile.length() - 22;
        seekInCurrentPart(randomAccessFile, length);
        if (rawIO.readIntLittleEndian(randomAccessFile) != HeaderSignature.END_OF_CENTRAL_DIRECTORY.getValue()) {
            length = determineOffsetOfEndOfCentralDirectory(randomAccessFile);
            randomAccessFile.seek(4 + length);
        }
        EndOfCentralDirectoryRecord endOfCentralDirectoryRecord = new EndOfCentralDirectoryRecord();
        endOfCentralDirectoryRecord.setSignature(HeaderSignature.END_OF_CENTRAL_DIRECTORY);
        endOfCentralDirectoryRecord.setNumberOfThisDisk(rawIO.readShortLittleEndian(randomAccessFile));
        endOfCentralDirectoryRecord.setNumberOfThisDiskStartOfCentralDir(rawIO.readShortLittleEndian(randomAccessFile));
        endOfCentralDirectoryRecord.setTotalNumberOfEntriesInCentralDirectoryOnThisDisk(rawIO.readShortLittleEndian(randomAccessFile));
        endOfCentralDirectoryRecord.setTotalNumberOfEntriesInCentralDirectory(rawIO.readShortLittleEndian(randomAccessFile));
        endOfCentralDirectoryRecord.setSizeOfCentralDirectory(rawIO.readIntLittleEndian(randomAccessFile));
        endOfCentralDirectoryRecord.setOffsetOfEndOfCentralDirectory(length);
        randomAccessFile.readFully(this.intBuff);
        endOfCentralDirectoryRecord.setOffsetOfStartOfCentralDirectory(rawIO.readLongLittleEndian(this.intBuff, 0));
        endOfCentralDirectoryRecord.setComment(readZipComment(randomAccessFile, rawIO.readShortLittleEndian(randomAccessFile), charset));
        this.zipModel.setSplitArchive(endOfCentralDirectoryRecord.getNumberOfThisDisk() > 0);
        return endOfCentralDirectoryRecord;
    }

    private CentralDirectory readCentralDirectory(RandomAccessFile randomAccessFile, RawIO rawIO, Charset charset) throws IOException {
        int i;
        CentralDirectory centralDirectory = new CentralDirectory();
        ArrayList arrayList = new ArrayList();
        long offsetStartOfCentralDirectory = HeaderUtil.getOffsetStartOfCentralDirectory(this.zipModel);
        long numberOfEntriesInCentralDirectory = getNumberOfEntriesInCentralDirectory(this.zipModel);
        randomAccessFile.seek(offsetStartOfCentralDirectory);
        int i2 = 2;
        byte[] bArr = new byte[2];
        byte[] bArr2 = new byte[4];
        int i3 = 0;
        int i4 = 0;
        while (i4 < numberOfEntriesInCentralDirectory) {
            FileHeader fileHeader = new FileHeader();
            byte[] bArr3 = bArr2;
            if (rawIO.readIntLittleEndian(randomAccessFile) != HeaderSignature.CENTRAL_DIRECTORY.getValue()) {
                throw new ZipException("Expected central directory entry not found (#" + (i4 + 1) + ")");
            }
            fileHeader.setSignature(HeaderSignature.CENTRAL_DIRECTORY);
            fileHeader.setVersionMadeBy(rawIO.readShortLittleEndian(randomAccessFile));
            fileHeader.setVersionNeededToExtract(rawIO.readShortLittleEndian(randomAccessFile));
            byte[] bArr4 = new byte[i2];
            randomAccessFile.readFully(bArr4);
            fileHeader.setEncrypted(BitUtils.isBitSet(bArr4[i3], i3));
            fileHeader.setDataDescriptorExists(BitUtils.isBitSet(bArr4[i3], 3));
            fileHeader.setFileNameUTF8Encoded(BitUtils.isBitSet(bArr4[1], 3));
            fileHeader.setGeneralPurposeFlag((byte[]) bArr4.clone());
            fileHeader.setCompressionMethod(CompressionMethod.getCompressionMethodFromCode(rawIO.readShortLittleEndian(randomAccessFile)));
            fileHeader.setLastModifiedTime(rawIO.readIntLittleEndian(randomAccessFile));
            randomAccessFile.readFully(bArr3);
            fileHeader.setCrc(rawIO.readLongLittleEndian(bArr3, 0));
            fileHeader.setCrcRawData(bArr3);
            long j = numberOfEntriesInCentralDirectory;
            fileHeader.setCompressedSize(rawIO.readLongLittleEndian(randomAccessFile, 4));
            fileHeader.setUncompressedSize(rawIO.readLongLittleEndian(randomAccessFile, 4));
            int readShortLittleEndian = rawIO.readShortLittleEndian(randomAccessFile);
            fileHeader.setFileNameLength(readShortLittleEndian);
            fileHeader.setExtraFieldLength(rawIO.readShortLittleEndian(randomAccessFile));
            int readShortLittleEndian2 = rawIO.readShortLittleEndian(randomAccessFile);
            fileHeader.setFileCommentLength(readShortLittleEndian2);
            fileHeader.setDiskNumberStart(rawIO.readShortLittleEndian(randomAccessFile));
            randomAccessFile.readFully(bArr);
            fileHeader.setInternalFileAttributes((byte[]) bArr.clone());
            randomAccessFile.readFully(bArr3);
            fileHeader.setExternalFileAttributes((byte[]) bArr3.clone());
            randomAccessFile.readFully(bArr3);
            fileHeader.setOffsetLocalHeader(rawIO.readLongLittleEndian(bArr3, 0));
            if (readShortLittleEndian > 0) {
                byte[] bArr5 = new byte[readShortLittleEndian];
                randomAccessFile.readFully(bArr5);
                String decodeStringWithCharset = HeaderUtil.decodeStringWithCharset(bArr5, fileHeader.isFileNameUTF8Encoded(), charset);
                if (decodeStringWithCharset.contains(":\\")) {
                    i = 2;
                    decodeStringWithCharset = decodeStringWithCharset.substring(decodeStringWithCharset.indexOf(":\\") + 2);
                } else {
                    i = 2;
                }
                fileHeader.setFileName(decodeStringWithCharset);
                fileHeader.setDirectory(decodeStringWithCharset.endsWith("/") || decodeStringWithCharset.endsWith("\\"));
            } else {
                i = 2;
                fileHeader.setFileName(null);
            }
            readExtraDataRecords(randomAccessFile, fileHeader);
            readZip64ExtendedInfo(fileHeader, rawIO);
            readAesExtraDataRecord(fileHeader, rawIO);
            if (readShortLittleEndian2 > 0) {
                byte[] bArr6 = new byte[readShortLittleEndian2];
                randomAccessFile.readFully(bArr6);
                fileHeader.setFileComment(HeaderUtil.decodeStringWithCharset(bArr6, fileHeader.isFileNameUTF8Encoded(), charset));
            }
            if (fileHeader.isEncrypted()) {
                if (fileHeader.getAesExtraDataRecord() != null) {
                    fileHeader.setEncryptionMethod(EncryptionMethod.AES);
                } else {
                    fileHeader.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD);
                }
            }
            arrayList.add(fileHeader);
            i4++;
            bArr2 = bArr3;
            i2 = i;
            numberOfEntriesInCentralDirectory = j;
            i3 = 0;
        }
        centralDirectory.setFileHeaders(arrayList);
        DigitalSignature digitalSignature = new DigitalSignature();
        if (rawIO.readIntLittleEndian(randomAccessFile) == HeaderSignature.DIGITAL_SIGNATURE.getValue()) {
            digitalSignature.setSignature(HeaderSignature.DIGITAL_SIGNATURE);
            digitalSignature.setSizeOfData(rawIO.readShortLittleEndian(randomAccessFile));
            if (digitalSignature.getSizeOfData() > 0) {
                byte[] bArr7 = new byte[digitalSignature.getSizeOfData()];
                randomAccessFile.readFully(bArr7);
                digitalSignature.setSignatureData(new String(bArr7));
            }
        }
        return centralDirectory;
    }

    private void readExtraDataRecords(RandomAccessFile randomAccessFile, FileHeader fileHeader) throws IOException {
        int extraFieldLength = fileHeader.getExtraFieldLength();
        if (extraFieldLength <= 0) {
            return;
        }
        fileHeader.setExtraDataRecords(readExtraDataRecords(randomAccessFile, extraFieldLength));
    }

    private void readExtraDataRecords(InputStream inputStream, LocalFileHeader localFileHeader) throws IOException {
        int extraFieldLength = localFileHeader.getExtraFieldLength();
        if (extraFieldLength <= 0) {
            return;
        }
        localFileHeader.setExtraDataRecords(readExtraDataRecords(inputStream, extraFieldLength));
    }

    private List<ExtraDataRecord> readExtraDataRecords(RandomAccessFile randomAccessFile, int i) throws IOException {
        if (i < 4) {
            if (i > 0) {
                randomAccessFile.skipBytes(i);
                return null;
            }
            return null;
        }
        byte[] bArr = new byte[i];
        randomAccessFile.read(bArr);
        try {
            return parseExtraDataRecords(bArr, i);
        } catch (Exception unused) {
            return Collections.emptyList();
        }
    }

    private List<ExtraDataRecord> readExtraDataRecords(InputStream inputStream, int i) throws IOException {
        if (i < 4) {
            if (i > 0) {
                inputStream.skip(i);
                return null;
            }
            return null;
        }
        byte[] bArr = new byte[i];
        Zip4jUtil.readFully(inputStream, bArr);
        try {
            return parseExtraDataRecords(bArr, i);
        } catch (Exception unused) {
            return Collections.emptyList();
        }
    }

    private List<ExtraDataRecord> parseExtraDataRecords(byte[] bArr, int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < i) {
            ExtraDataRecord extraDataRecord = new ExtraDataRecord();
            extraDataRecord.setHeader(this.rawIO.readShortLittleEndian(bArr, i2));
            int i3 = i2 + 2;
            int readShortLittleEndian = this.rawIO.readShortLittleEndian(bArr, i3);
            extraDataRecord.setSizeOfData(readShortLittleEndian);
            int i4 = i3 + 2;
            if (readShortLittleEndian > 0) {
                byte[] bArr2 = new byte[readShortLittleEndian];
                System.arraycopy(bArr, i4, bArr2, 0, readShortLittleEndian);
                extraDataRecord.setData(bArr2);
            }
            i2 = i4 + readShortLittleEndian;
            arrayList.add(extraDataRecord);
        }
        if (arrayList.size() > 0) {
            return arrayList;
        }
        return null;
    }

    private Zip64EndOfCentralDirectoryLocator readZip64EndOfCentralDirectoryLocator(RandomAccessFile randomAccessFile, RawIO rawIO, long j) throws IOException {
        Zip64EndOfCentralDirectoryLocator zip64EndOfCentralDirectoryLocator = new Zip64EndOfCentralDirectoryLocator();
        setFilePointerToReadZip64EndCentralDirLoc(randomAccessFile, j);
        if (rawIO.readIntLittleEndian(randomAccessFile) == HeaderSignature.ZIP64_END_CENTRAL_DIRECTORY_LOCATOR.getValue()) {
            this.zipModel.setZip64Format(true);
            zip64EndOfCentralDirectoryLocator.setSignature(HeaderSignature.ZIP64_END_CENTRAL_DIRECTORY_LOCATOR);
            zip64EndOfCentralDirectoryLocator.setNumberOfDiskStartOfZip64EndOfCentralDirectoryRecord(rawIO.readIntLittleEndian(randomAccessFile));
            zip64EndOfCentralDirectoryLocator.setOffsetZip64EndOfCentralDirectoryRecord(rawIO.readLongLittleEndian(randomAccessFile));
            zip64EndOfCentralDirectoryLocator.setTotalNumberOfDiscs(rawIO.readIntLittleEndian(randomAccessFile));
            return zip64EndOfCentralDirectoryLocator;
        }
        this.zipModel.setZip64Format(false);
        return null;
    }

    private Zip64EndOfCentralDirectoryRecord readZip64EndCentralDirRec(RandomAccessFile randomAccessFile, RawIO rawIO) throws IOException {
        if (this.zipModel.getZip64EndOfCentralDirectoryLocator() == null) {
            throw new ZipException("invalid zip64 end of central directory locator");
        }
        long offsetZip64EndOfCentralDirectoryRecord = this.zipModel.getZip64EndOfCentralDirectoryLocator().getOffsetZip64EndOfCentralDirectoryRecord();
        if (offsetZip64EndOfCentralDirectoryRecord < 0) {
            throw new ZipException("invalid offset for start of end of central directory record");
        }
        randomAccessFile.seek(offsetZip64EndOfCentralDirectoryRecord);
        Zip64EndOfCentralDirectoryRecord zip64EndOfCentralDirectoryRecord = new Zip64EndOfCentralDirectoryRecord();
        if (rawIO.readIntLittleEndian(randomAccessFile) != HeaderSignature.ZIP64_END_CENTRAL_DIRECTORY_RECORD.getValue()) {
            throw new ZipException("invalid signature for zip64 end of central directory record");
        }
        zip64EndOfCentralDirectoryRecord.setSignature(HeaderSignature.ZIP64_END_CENTRAL_DIRECTORY_RECORD);
        zip64EndOfCentralDirectoryRecord.setSizeOfZip64EndCentralDirectoryRecord(rawIO.readLongLittleEndian(randomAccessFile));
        zip64EndOfCentralDirectoryRecord.setVersionMadeBy(rawIO.readShortLittleEndian(randomAccessFile));
        zip64EndOfCentralDirectoryRecord.setVersionNeededToExtract(rawIO.readShortLittleEndian(randomAccessFile));
        zip64EndOfCentralDirectoryRecord.setNumberOfThisDisk(rawIO.readIntLittleEndian(randomAccessFile));
        zip64EndOfCentralDirectoryRecord.setNumberOfThisDiskStartOfCentralDirectory(rawIO.readIntLittleEndian(randomAccessFile));
        zip64EndOfCentralDirectoryRecord.setTotalNumberOfEntriesInCentralDirectoryOnThisDisk(rawIO.readLongLittleEndian(randomAccessFile));
        zip64EndOfCentralDirectoryRecord.setTotalNumberOfEntriesInCentralDirectory(rawIO.readLongLittleEndian(randomAccessFile));
        zip64EndOfCentralDirectoryRecord.setSizeOfCentralDirectory(rawIO.readLongLittleEndian(randomAccessFile));
        zip64EndOfCentralDirectoryRecord.setOffsetStartCentralDirectoryWRTStartDiskNumber(rawIO.readLongLittleEndian(randomAccessFile));
        long sizeOfZip64EndCentralDirectoryRecord = zip64EndOfCentralDirectoryRecord.getSizeOfZip64EndCentralDirectoryRecord() - 44;
        if (sizeOfZip64EndCentralDirectoryRecord > 0) {
            byte[] bArr = new byte[(int) sizeOfZip64EndCentralDirectoryRecord];
            randomAccessFile.readFully(bArr);
            zip64EndOfCentralDirectoryRecord.setExtensibleDataSector(bArr);
        }
        return zip64EndOfCentralDirectoryRecord;
    }

    private void readZip64ExtendedInfo(FileHeader fileHeader, RawIO rawIO) throws ZipException {
        Zip64ExtendedInfo readZip64ExtendedInfo;
        if (fileHeader.getExtraDataRecords() == null || fileHeader.getExtraDataRecords().size() <= 0 || (readZip64ExtendedInfo = readZip64ExtendedInfo(fileHeader.getExtraDataRecords(), rawIO, fileHeader.getUncompressedSize(), fileHeader.getCompressedSize(), fileHeader.getOffsetLocalHeader(), fileHeader.getDiskNumberStart())) == null) {
            return;
        }
        fileHeader.setZip64ExtendedInfo(readZip64ExtendedInfo);
        if (readZip64ExtendedInfo.getUncompressedSize() != -1) {
            fileHeader.setUncompressedSize(readZip64ExtendedInfo.getUncompressedSize());
        }
        if (readZip64ExtendedInfo.getCompressedSize() != -1) {
            fileHeader.setCompressedSize(readZip64ExtendedInfo.getCompressedSize());
        }
        if (readZip64ExtendedInfo.getOffsetLocalHeader() != -1) {
            fileHeader.setOffsetLocalHeader(readZip64ExtendedInfo.getOffsetLocalHeader());
        }
        if (readZip64ExtendedInfo.getDiskNumberStart() != -1) {
            fileHeader.setDiskNumberStart(readZip64ExtendedInfo.getDiskNumberStart());
        }
    }

    private void readZip64ExtendedInfo(LocalFileHeader localFileHeader, RawIO rawIO) throws ZipException {
        Zip64ExtendedInfo readZip64ExtendedInfo;
        if (localFileHeader == null) {
            throw new ZipException("file header is null in reading Zip64 Extended Info");
        }
        if (localFileHeader.getExtraDataRecords() == null || localFileHeader.getExtraDataRecords().size() <= 0 || (readZip64ExtendedInfo = readZip64ExtendedInfo(localFileHeader.getExtraDataRecords(), rawIO, localFileHeader.getUncompressedSize(), localFileHeader.getCompressedSize(), 0L, 0)) == null) {
            return;
        }
        localFileHeader.setZip64ExtendedInfo(readZip64ExtendedInfo);
        if (readZip64ExtendedInfo.getUncompressedSize() != -1) {
            localFileHeader.setUncompressedSize(readZip64ExtendedInfo.getUncompressedSize());
        }
        if (readZip64ExtendedInfo.getCompressedSize() != -1) {
            localFileHeader.setCompressedSize(readZip64ExtendedInfo.getCompressedSize());
        }
    }

    private Zip64ExtendedInfo readZip64ExtendedInfo(List<ExtraDataRecord> list, RawIO rawIO, long j, long j2, long j3, int i) {
        for (ExtraDataRecord extraDataRecord : list) {
            if (extraDataRecord != null && HeaderSignature.ZIP64_EXTRA_FIELD_SIGNATURE.getValue() == extraDataRecord.getHeader()) {
                Zip64ExtendedInfo zip64ExtendedInfo = new Zip64ExtendedInfo();
                byte[] data = extraDataRecord.getData();
                if (extraDataRecord.getSizeOfData() <= 0) {
                    return null;
                }
                int i2 = 0;
                if (extraDataRecord.getSizeOfData() > 0 && j == 4294967295L) {
                    zip64ExtendedInfo.setUncompressedSize(rawIO.readLongLittleEndian(data, 0));
                    i2 = 8;
                }
                if (i2 < extraDataRecord.getSizeOfData() && j2 == 4294967295L) {
                    zip64ExtendedInfo.setCompressedSize(rawIO.readLongLittleEndian(data, i2));
                    i2 += 8;
                }
                if (i2 < extraDataRecord.getSizeOfData() && j3 == 4294967295L) {
                    zip64ExtendedInfo.setOffsetLocalHeader(rawIO.readLongLittleEndian(data, i2));
                    i2 += 8;
                }
                if (i2 < extraDataRecord.getSizeOfData() && i == 65535) {
                    zip64ExtendedInfo.setDiskNumberStart(rawIO.readIntLittleEndian(data, i2));
                }
                return zip64ExtendedInfo;
            }
        }
        return null;
    }

    private void setFilePointerToReadZip64EndCentralDirLoc(RandomAccessFile randomAccessFile, long j) throws IOException {
        seekInCurrentPart(randomAccessFile, (((j - 4) - 8) - 4) - 4);
    }

    public LocalFileHeader readLocalFileHeader(InputStream inputStream, Charset charset) throws IOException {
        LocalFileHeader localFileHeader = new LocalFileHeader();
        byte[] bArr = new byte[4];
        if (this.rawIO.readIntLittleEndian(inputStream) != HeaderSignature.LOCAL_FILE_HEADER.getValue()) {
            return null;
        }
        localFileHeader.setSignature(HeaderSignature.LOCAL_FILE_HEADER);
        localFileHeader.setVersionNeededToExtract(this.rawIO.readShortLittleEndian(inputStream));
        byte[] bArr2 = new byte[2];
        if (Zip4jUtil.readFully(inputStream, bArr2) != 2) {
            throw new ZipException("Could not read enough bytes for generalPurposeFlags");
        }
        localFileHeader.setEncrypted(BitUtils.isBitSet(bArr2[0], 0));
        localFileHeader.setDataDescriptorExists(BitUtils.isBitSet(bArr2[0], 3));
        boolean z = true;
        localFileHeader.setFileNameUTF8Encoded(BitUtils.isBitSet(bArr2[1], 3));
        localFileHeader.setGeneralPurposeFlag((byte[]) bArr2.clone());
        localFileHeader.setCompressionMethod(CompressionMethod.getCompressionMethodFromCode(this.rawIO.readShortLittleEndian(inputStream)));
        localFileHeader.setLastModifiedTime(this.rawIO.readIntLittleEndian(inputStream));
        Zip4jUtil.readFully(inputStream, bArr);
        localFileHeader.setCrc(this.rawIO.readLongLittleEndian(bArr, 0));
        localFileHeader.setCrcRawData((byte[]) bArr.clone());
        localFileHeader.setCompressedSize(this.rawIO.readLongLittleEndian(inputStream, 4));
        localFileHeader.setUncompressedSize(this.rawIO.readLongLittleEndian(inputStream, 4));
        int readShortLittleEndian = this.rawIO.readShortLittleEndian(inputStream);
        localFileHeader.setFileNameLength(readShortLittleEndian);
        localFileHeader.setExtraFieldLength(this.rawIO.readShortLittleEndian(inputStream));
        if (readShortLittleEndian > 0) {
            byte[] bArr3 = new byte[readShortLittleEndian];
            Zip4jUtil.readFully(inputStream, bArr3);
            String decodeStringWithCharset = HeaderUtil.decodeStringWithCharset(bArr3, localFileHeader.isFileNameUTF8Encoded(), charset);
            if (decodeStringWithCharset == null) {
                throw new ZipException("file name is null, cannot assign file name to local file header");
            }
            if (decodeStringWithCharset.contains(":" + System.getProperty("file.separator"))) {
                decodeStringWithCharset = decodeStringWithCharset.substring(decodeStringWithCharset.indexOf(":" + System.getProperty("file.separator")) + 2);
            }
            localFileHeader.setFileName(decodeStringWithCharset);
            if (!decodeStringWithCharset.endsWith("/") && !decodeStringWithCharset.endsWith("\\")) {
                z = false;
            }
            localFileHeader.setDirectory(z);
        } else {
            localFileHeader.setFileName(null);
        }
        readExtraDataRecords(inputStream, localFileHeader);
        readZip64ExtendedInfo(localFileHeader, this.rawIO);
        readAesExtraDataRecord(localFileHeader, this.rawIO);
        if (localFileHeader.isEncrypted() && localFileHeader.getEncryptionMethod() != EncryptionMethod.AES) {
            if (BigInteger.valueOf(localFileHeader.getGeneralPurposeFlag()[0]).testBit(6)) {
                localFileHeader.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD_VARIANT_STRONG);
            } else {
                localFileHeader.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD);
            }
        }
        return localFileHeader;
    }

    public DataDescriptor readDataDescriptor(InputStream inputStream, boolean z) throws IOException {
        DataDescriptor dataDescriptor = new DataDescriptor();
        byte[] bArr = new byte[4];
        Zip4jUtil.readFully(inputStream, bArr);
        long readLongLittleEndian = this.rawIO.readLongLittleEndian(bArr, 0);
        if (readLongLittleEndian == HeaderSignature.EXTRA_DATA_RECORD.getValue()) {
            dataDescriptor.setSignature(HeaderSignature.EXTRA_DATA_RECORD);
            Zip4jUtil.readFully(inputStream, bArr);
            dataDescriptor.setCrc(this.rawIO.readLongLittleEndian(bArr, 0));
        } else {
            dataDescriptor.setCrc(readLongLittleEndian);
        }
        if (z) {
            dataDescriptor.setCompressedSize(this.rawIO.readLongLittleEndian(inputStream));
            dataDescriptor.setUncompressedSize(this.rawIO.readLongLittleEndian(inputStream));
        } else {
            dataDescriptor.setCompressedSize(this.rawIO.readIntLittleEndian(inputStream));
            dataDescriptor.setUncompressedSize(this.rawIO.readIntLittleEndian(inputStream));
        }
        return dataDescriptor;
    }

    private void readAesExtraDataRecord(FileHeader fileHeader, RawIO rawIO) throws ZipException {
        AESExtraDataRecord readAesExtraDataRecord;
        if (fileHeader.getExtraDataRecords() == null || fileHeader.getExtraDataRecords().size() <= 0 || (readAesExtraDataRecord = readAesExtraDataRecord(fileHeader.getExtraDataRecords(), rawIO)) == null) {
            return;
        }
        fileHeader.setAesExtraDataRecord(readAesExtraDataRecord);
        fileHeader.setEncryptionMethod(EncryptionMethod.AES);
    }

    private void readAesExtraDataRecord(LocalFileHeader localFileHeader, RawIO rawIO) throws ZipException {
        AESExtraDataRecord readAesExtraDataRecord;
        if (localFileHeader.getExtraDataRecords() == null || localFileHeader.getExtraDataRecords().size() <= 0 || (readAesExtraDataRecord = readAesExtraDataRecord(localFileHeader.getExtraDataRecords(), rawIO)) == null) {
            return;
        }
        localFileHeader.setAesExtraDataRecord(readAesExtraDataRecord);
        localFileHeader.setEncryptionMethod(EncryptionMethod.AES);
    }

    private AESExtraDataRecord readAesExtraDataRecord(List<ExtraDataRecord> list, RawIO rawIO) throws ZipException {
        if (list == null) {
            return null;
        }
        for (ExtraDataRecord extraDataRecord : list) {
            if (extraDataRecord != null && extraDataRecord.getHeader() == HeaderSignature.AES_EXTRA_DATA_RECORD.getValue()) {
                if (extraDataRecord.getData() == null) {
                    throw new ZipException("corrupt AES extra data records");
                }
                AESExtraDataRecord aESExtraDataRecord = new AESExtraDataRecord();
                aESExtraDataRecord.setSignature(HeaderSignature.AES_EXTRA_DATA_RECORD);
                aESExtraDataRecord.setDataSize(extraDataRecord.getSizeOfData());
                byte[] data = extraDataRecord.getData();
                aESExtraDataRecord.setAesVersion(AesVersion.getFromVersionNumber(rawIO.readShortLittleEndian(data, 0)));
                byte[] bArr = new byte[2];
                System.arraycopy(data, 2, bArr, 0, 2);
                aESExtraDataRecord.setVendorID(new String(bArr));
                aESExtraDataRecord.setAesKeyStrength(AesKeyStrength.getAesKeyStrengthFromRawCode(data[4] & 255));
                aESExtraDataRecord.setCompressionMethod(CompressionMethod.getCompressionMethodFromCode(rawIO.readShortLittleEndian(data, 5)));
                return aESExtraDataRecord;
            }
        }
        return null;
    }

    private long getNumberOfEntriesInCentralDirectory(ZipModel zipModel) {
        if (zipModel.isZip64Format()) {
            return zipModel.getZip64EndOfCentralDirectoryRecord().getTotalNumberOfEntriesInCentralDirectory();
        }
        return zipModel.getEndOfCentralDirectoryRecord().getTotalNumberOfEntriesInCentralDirectory();
    }

    private long determineOffsetOfEndOfCentralDirectory(RandomAccessFile randomAccessFile) throws IOException {
        byte[] bArr = new byte[4096];
        long filePointer = randomAccessFile.getFilePointer();
        do {
            int i = filePointer > 4096 ? 4096 : (int) filePointer;
            filePointer = (filePointer - i) + 4;
            if (filePointer == 4) {
                filePointer = 0;
            }
            seekInCurrentPart(randomAccessFile, filePointer);
            randomAccessFile.read(bArr, 0, i);
            for (int i2 = 0; i2 < i - 3; i2++) {
                if (this.rawIO.readIntLittleEndian(bArr, i2) == HeaderSignature.END_OF_CENTRAL_DIRECTORY.getValue()) {
                    return filePointer + i2;
                }
            }
        } while (filePointer > 0);
        throw new ZipException("Zip headers not found. Probably not a zip file");
    }

    private void seekInCurrentPart(RandomAccessFile randomAccessFile, long j) throws IOException {
        if (randomAccessFile instanceof NumberedSplitRandomAccessFile) {
            ((NumberedSplitRandomAccessFile) randomAccessFile).seekInCurrentPart(j);
        } else {
            randomAccessFile.seek(j);
        }
    }

    private String readZipComment(RandomAccessFile randomAccessFile, int i, Charset charset) {
        if (i <= 0) {
            return null;
        }
        try {
            byte[] bArr = new byte[i];
            randomAccessFile.readFully(bArr);
            return new String(bArr, charset);
        } catch (IOException unused) {
            return null;
        }
    }
}
