package net.lingala.zip4j.model;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class Zip64EndOfCentralDirectoryLocator extends ZipHeader {
    private int numberOfDiskStartOfZip64EndOfCentralDirectoryRecord;
    private long offsetZip64EndOfCentralDirectoryRecord;
    private int totalNumberOfDiscs;

    public int getNumberOfDiskStartOfZip64EndOfCentralDirectoryRecord() {
        return this.numberOfDiskStartOfZip64EndOfCentralDirectoryRecord;
    }

    public void setNumberOfDiskStartOfZip64EndOfCentralDirectoryRecord(int i) {
        this.numberOfDiskStartOfZip64EndOfCentralDirectoryRecord = i;
    }

    public long getOffsetZip64EndOfCentralDirectoryRecord() {
        return this.offsetZip64EndOfCentralDirectoryRecord;
    }

    public void setOffsetZip64EndOfCentralDirectoryRecord(long j) {
        this.offsetZip64EndOfCentralDirectoryRecord = j;
    }

    public int getTotalNumberOfDiscs() {
        return this.totalNumberOfDiscs;
    }

    public void setTotalNumberOfDiscs(int i) {
        this.totalNumberOfDiscs = i;
    }
}
