package com.baidu.cloud.videocache.file;

import com.baidu.cloud.videocache.C2574j;
import com.baidu.cloud.videocache.Cache;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class oia implements Cache {

    /* renamed from: a */
    public File f4880a;

    /* renamed from: b */
    private final DiskUsage f4881b;

    /* renamed from: c */
    private RandomAccessFile f4882c;

    public oia(File file, DiskUsage diskUsage) {
        File file2;
        try {
            if (diskUsage == null) {
                throw new NullPointerException();
            }
            this.f4881b = diskUsage;
            nxb.m19815a(file.getParentFile());
            boolean exists = file.exists();
            if (exists) {
                file2 = file;
            } else {
                File parentFile = file.getParentFile();
                file2 = new File(parentFile, file.getName() + ".download");
            }
            this.f4880a = file2;
            this.f4882c = new RandomAccessFile(this.f4880a, exists ? "r" : "rw");
        } catch (IOException e) {
            throw new C2574j("Error using file " + file + " as disc cache", e);
        }
    }

    /* renamed from: a */
    private boolean m19808a(File file) {
        return file.getName().endsWith(".download");
    }

    @Override // com.baidu.cloud.videocache.Cache
    public synchronized void append(byte[] bArr, int i) {
        try {
            if (isCompleted()) {
                throw new C2574j("Error append cache: cache file " + this.f4880a + " is completed!");
            }
            this.f4882c.seek(available());
            this.f4882c.write(bArr, 0, i);
        } catch (IOException e) {
            throw new C2574j(String.format("Error writing %d bytes to %s from buffer with size %d", Integer.valueOf(i), this.f4882c, Integer.valueOf(bArr.length)), e);
        }
    }

    @Override // com.baidu.cloud.videocache.Cache
    public synchronized long available() {
        try {
        } catch (IOException e) {
            throw new C2574j("Error reading length of file " + this.f4880a, e);
        }
        return (int) this.f4882c.length();
    }

    @Override // com.baidu.cloud.videocache.Cache
    public synchronized void close() {
        try {
            this.f4882c.close();
            this.f4881b.touch(this.f4880a);
        } catch (IOException e) {
            throw new C2574j("Error closing file " + this.f4880a, e);
        }
    }

    @Override // com.baidu.cloud.videocache.Cache
    public synchronized void complete() {
        if (isCompleted()) {
            return;
        }
        close();
        File file = new File(this.f4880a.getParentFile(), this.f4880a.getName().substring(0, this.f4880a.getName().length() - 9));
        if (!this.f4880a.renameTo(file)) {
            throw new C2574j("Error renaming file " + this.f4880a + " to " + file + " for completion!");
        }
        this.f4880a = file;
        try {
            this.f4882c = new RandomAccessFile(this.f4880a, "r");
            this.f4881b.touch(this.f4880a);
        } catch (IOException e) {
            throw new C2574j("Error opening " + this.f4880a + " as disc cache", e);
        }
    }

    @Override // com.baidu.cloud.videocache.Cache
    public synchronized boolean isCompleted() {
        return !m19808a(this.f4880a);
    }

    @Override // com.baidu.cloud.videocache.Cache
    public synchronized int read(byte[] bArr, long j, int i) {
        try {
            this.f4882c.seek(j);
        } catch (IOException e) {
            throw new C2574j(String.format("Error reading %d bytes with offset %d from file[%d bytes] to buffer[%d bytes]", Integer.valueOf(i), Long.valueOf(j), Long.valueOf(available()), Integer.valueOf(bArr.length)), e);
        }
        return this.f4882c.read(bArr, 0, i);
    }
}
