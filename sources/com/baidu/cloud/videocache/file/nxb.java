package com.baidu.cloud.videocache.file;

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
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
class nxb {

    /* renamed from: a */
    private static final Logger f4879a = LoggerFactory.getLogger("Files");

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static final class oia implements Comparator {
        private oia() {
        }

        /* renamed from: a */
        private int m19810a(long j, long j2) {
            int i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
            if (i < 0) {
                return -1;
            }
            return i == 0 ? 0 : 1;
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(File file, File file2) {
            return m19810a(file.lastModified(), file2.lastModified());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m19815a(File file) {
        if (!file.exists()) {
            if (!file.mkdirs()) {
                throw new IOException(String.format("Directory %s can't be created", file.getAbsolutePath()));
            }
        } else if (file.isDirectory()) {
        } else {
            throw new IOException("File " + file + " is not directory!");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static List m19814b(File file) {
        LinkedList linkedList = new LinkedList();
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            List asList = Arrays.asList(listFiles);
            Collections.sort(asList, new oia());
            return asList;
        }
        return linkedList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static void m19813c(File file) {
        if (file.exists()) {
            long currentTimeMillis = System.currentTimeMillis();
            if (file.setLastModified(currentTimeMillis)) {
                return;
            }
            m19812d(file);
            if (file.lastModified() < currentTimeMillis) {
                f4879a.warn("Last modified date {} is not set for file {}", new Date(file.lastModified()), file.getAbsolutePath());
            }
        }
    }

    /* renamed from: d */
    static void m19812d(File file) {
        long length = file.length();
        if (length == 0) {
            m19811e(file);
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

    /* renamed from: e */
    private static void m19811e(File file) {
        if (file.delete() && file.createNewFile()) {
            return;
        }
        throw new IOException("Error recreate zero-size file " + file);
    }
}
