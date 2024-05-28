package net.lingala.zip4j.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.List;
import net.lingala.zip4j.headers.HeaderWriter;
import net.lingala.zip4j.model.EndOfCentralDirectoryRecord;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.Zip64EndOfCentralDirectoryLocator;
import net.lingala.zip4j.model.Zip64EndOfCentralDirectoryRecord;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.enums.RandomAccessFileMode;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;
import net.lingala.zip4j.util.RawIO;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class MergeSplitZipFileTask extends AsyncZipTask<MergeSplitZipFileTaskParameters> {
    private RawIO rawIO;
    private ZipModel zipModel;

    public MergeSplitZipFileTask(ZipModel zipModel, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(asyncTaskParameters);
        this.rawIO = new RawIO();
        this.zipModel = zipModel;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public void executeTask(net.lingala.zip4j.tasks.MergeSplitZipFileTask.MergeSplitZipFileTaskParameters r24, net.lingala.zip4j.progress.ProgressMonitor r25) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 278
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.tasks.MergeSplitZipFileTask.executeTask(net.lingala.zip4j.tasks.MergeSplitZipFileTask$MergeSplitZipFileTaskParameters, net.lingala.zip4j.progress.ProgressMonitor):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public long calculateTotalWork(MergeSplitZipFileTaskParameters mergeSplitZipFileTaskParameters) {
        long j = 0;
        if (this.zipModel.isSplitArchive()) {
            for (int i = 0; i <= this.zipModel.getEndOfCentralDirectoryRecord().getNumberOfThisDisk(); i++) {
                j += getNextSplitZipFile(this.zipModel, i).length();
            }
            return j;
        }
        return 0L;
    }

    private void updateFileHeaderOffsetsForIndex(List<FileHeader> list, long j, int i, int i2) {
        for (FileHeader fileHeader : list) {
            if (fileHeader.getDiskNumberStart() == i) {
                fileHeader.setOffsetLocalHeader((fileHeader.getOffsetLocalHeader() + j) - i2);
                fileHeader.setDiskNumberStart(0);
            }
        }
    }

    private File getNextSplitZipFile(ZipModel zipModel, int i) {
        if (i == zipModel.getEndOfCentralDirectoryRecord().getNumberOfThisDisk()) {
            return zipModel.getZipFile();
        }
        String str = i >= 9 ? ".z" : ".z0";
        String path = zipModel.getZipFile().getPath();
        return new File(zipModel.getZipFile().getPath().substring(0, path.lastIndexOf(".")) + str + (i + 1));
    }

    private RandomAccessFile createSplitZipFileStream(ZipModel zipModel, int i) throws FileNotFoundException {
        return new RandomAccessFile(getNextSplitZipFile(zipModel, i), RandomAccessFileMode.READ.getValue());
    }

    private void updateHeadersForMergeSplitFileAction(ZipModel zipModel, long j, OutputStream outputStream, Charset charset) throws IOException, CloneNotSupportedException {
        ZipModel zipModel2 = (ZipModel) zipModel.clone();
        zipModel2.getEndOfCentralDirectoryRecord().setOffsetOfStartOfCentralDirectory(j);
        updateSplitZipModel(zipModel2, j);
        new HeaderWriter().finalizeZipFileWithoutValidations(zipModel2, outputStream, charset);
    }

    private void updateSplitZipModel(ZipModel zipModel, long j) {
        zipModel.setSplitArchive(false);
        updateSplitEndCentralDirectory(zipModel);
        if (zipModel.isZip64Format()) {
            updateSplitZip64EndCentralDirLocator(zipModel, j);
            updateSplitZip64EndCentralDirRec(zipModel, j);
        }
    }

    private void updateSplitEndCentralDirectory(ZipModel zipModel) {
        int size = zipModel.getCentralDirectory().getFileHeaders().size();
        EndOfCentralDirectoryRecord endOfCentralDirectoryRecord = zipModel.getEndOfCentralDirectoryRecord();
        endOfCentralDirectoryRecord.setNumberOfThisDisk(0);
        endOfCentralDirectoryRecord.setNumberOfThisDiskStartOfCentralDir(0);
        endOfCentralDirectoryRecord.setTotalNumberOfEntriesInCentralDirectory(size);
        endOfCentralDirectoryRecord.setTotalNumberOfEntriesInCentralDirectoryOnThisDisk(size);
    }

    private void updateSplitZip64EndCentralDirLocator(ZipModel zipModel, long j) {
        if (zipModel.getZip64EndOfCentralDirectoryLocator() == null) {
            return;
        }
        Zip64EndOfCentralDirectoryLocator zip64EndOfCentralDirectoryLocator = zipModel.getZip64EndOfCentralDirectoryLocator();
        zip64EndOfCentralDirectoryLocator.setNumberOfDiskStartOfZip64EndOfCentralDirectoryRecord(0);
        zip64EndOfCentralDirectoryLocator.setOffsetZip64EndOfCentralDirectoryRecord(zip64EndOfCentralDirectoryLocator.getOffsetZip64EndOfCentralDirectoryRecord() + j);
        zip64EndOfCentralDirectoryLocator.setTotalNumberOfDiscs(1);
    }

    private void updateSplitZip64EndCentralDirRec(ZipModel zipModel, long j) {
        if (zipModel.getZip64EndOfCentralDirectoryRecord() == null) {
            return;
        }
        Zip64EndOfCentralDirectoryRecord zip64EndOfCentralDirectoryRecord = zipModel.getZip64EndOfCentralDirectoryRecord();
        zip64EndOfCentralDirectoryRecord.setNumberOfThisDisk(0);
        zip64EndOfCentralDirectoryRecord.setNumberOfThisDiskStartOfCentralDirectory(0);
        zip64EndOfCentralDirectoryRecord.setTotalNumberOfEntriesInCentralDirectoryOnThisDisk(zipModel.getEndOfCentralDirectoryRecord().getTotalNumberOfEntriesInCentralDirectory());
        zip64EndOfCentralDirectoryRecord.setOffsetStartCentralDirectoryWRTStartDiskNumber(zip64EndOfCentralDirectoryRecord.getOffsetStartCentralDirectoryWRTStartDiskNumber() + j);
    }

    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    protected ProgressMonitor.Task getTask() {
        return ProgressMonitor.Task.MERGE_ZIP_FILES;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class MergeSplitZipFileTaskParameters extends AbstractZipTaskParameters {
        private File outputZipFile;

        static /* synthetic */ File access$000(MergeSplitZipFileTaskParameters mergeSplitZipFileTaskParameters) {
            return mergeSplitZipFileTaskParameters.outputZipFile;
        }

        public MergeSplitZipFileTaskParameters(File file, Charset charset) {
            super(charset);
            this.outputZipFile = file;
        }
    }
}
