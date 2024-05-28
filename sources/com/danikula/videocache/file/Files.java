package com.danikula.videocache.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
class Files {
    private static final Logger LOG = LoggerFactory.getLogger("Files");

    Files() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void makeDir(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                return;
            }
            throw new IOException("File " + file + " is not directory!");
        } else if (!file.mkdirs()) {
            throw new IOException(String.format("Directory %s can't be created", file.getAbsolutePath()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<File> getLruListFiles(File file) {
        LinkedList linkedList = new LinkedList();
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            List<File> asList = Arrays.asList(listFiles);
            Collections.sort(asList, new LastModifiedComparator());
            return asList;
        }
        return linkedList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setLastModifiedNow(File file) throws IOException {
        if (file.exists()) {
            long currentTimeMillis = System.currentTimeMillis();
            if (file.setLastModified(currentTimeMillis)) {
                return;
            }
            modify(file);
            if (file.lastModified() < currentTimeMillis) {
                LOG.warn("Last modified date {} is not set for file {}", new Date(file.lastModified()), file.getAbsolutePath());
            }
        }
    }

    static void modify(File file) throws IOException {
        long length = file.length();
        if (length == 0) {
            recreateZeroSizeFile(file);
            return;
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
        long j = length - 1;
        randomAccessFile.seek(j);
        byte readByte = randomAccessFile.readByte();
        randomAccessFile.seek(j);
        randomAccessFile.write(readByte);
        randomAccessFile.close();
    }

    private static void recreateZeroSizeFile(File file) throws IOException {
        if (file.delete() && file.createNewFile()) {
            return;
        }
        throw new IOException("Error recreate zero-size file " + file);
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static final class LastModifiedComparator implements Comparator<File> {
        private int compareLong(long j, long j2) {
            int i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
            if (i < 0) {
                return -1;
            }
            return i == 0 ? 0 : 1;
        }

        private LastModifiedComparator() {
        }

        @Override // java.util.Comparator
        public int compare(File file, File file2) {
            return compareLong(file.lastModified(), file2.lastModified());
        }
    }
}
