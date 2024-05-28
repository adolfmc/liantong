package com.liulishuo.okdownload.core.file;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.file.DownloadOutputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DownloadUriOutputStream implements DownloadOutputStream {
    @NonNull
    private final FileChannel channel;
    @NonNull
    final FileOutputStream fos;
    @NonNull
    final BufferedOutputStream out;
    @NonNull
    final ParcelFileDescriptor pdf;

    public DownloadUriOutputStream(Context context, Uri uri, int i) throws FileNotFoundException {
        ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "rw");
        if (openFileDescriptor == null) {
            throw new FileNotFoundException("result of " + uri + " is null!");
        }
        this.pdf = openFileDescriptor;
        this.fos = new FileOutputStream(openFileDescriptor.getFileDescriptor());
        this.channel = this.fos.getChannel();
        this.out = new BufferedOutputStream(this.fos, i);
    }

    DownloadUriOutputStream(@NonNull FileChannel fileChannel, @NonNull ParcelFileDescriptor parcelFileDescriptor, @NonNull FileOutputStream fileOutputStream, @NonNull BufferedOutputStream bufferedOutputStream) {
        this.channel = fileChannel;
        this.pdf = parcelFileDescriptor;
        this.fos = fileOutputStream;
        this.out = bufferedOutputStream;
    }

    @Override // com.liulishuo.okdownload.core.file.DownloadOutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
    }

    @Override // com.liulishuo.okdownload.core.file.DownloadOutputStream
    public void close() throws IOException {
        this.out.close();
        this.fos.close();
        this.pdf.close();
    }

    @Override // com.liulishuo.okdownload.core.file.DownloadOutputStream
    public void flushAndSync() throws IOException {
        this.out.flush();
        this.pdf.getFileDescriptor().sync();
    }

    @Override // com.liulishuo.okdownload.core.file.DownloadOutputStream
    public void seek(long j) throws IOException {
        this.channel.position(j);
    }

    @Override // com.liulishuo.okdownload.core.file.DownloadOutputStream
    public void setLength(long j) {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                Os.posix_fallocate(this.pdf.getFileDescriptor(), 0L, j);
                return;
            } catch (Throwable th) {
                if (th instanceof ErrnoException) {
                    ErrnoException errnoException = (ErrnoException) th;
                    if (errnoException.errno == OsConstants.ENOSYS || errnoException.errno == OsConstants.ENOTSUP) {
                        Util.m13738w("DownloadUriOutputStream", "fallocate() not supported; falling back to ftruncate()");
                        try {
                            Os.ftruncate(this.pdf.getFileDescriptor(), j);
                            return;
                        } catch (Throwable th2) {
                            Util.m13738w("DownloadUriOutputStream", "It can't pre-allocate length(" + j + ") on the sdk version(" + Build.VERSION.SDK_INT + "), because of " + th2);
                            return;
                        }
                    }
                    return;
                }
                Util.m13738w("DownloadUriOutputStream", "It can't pre-allocate length(" + j + ") on the sdk version(" + Build.VERSION.SDK_INT + "), because of " + th);
                return;
            }
        }
        Util.m13738w("DownloadUriOutputStream", "It can't pre-allocate length(" + j + ") on the sdk version(" + Build.VERSION.SDK_INT + ")");
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class Factory implements DownloadOutputStream.Factory {
        @Override // com.liulishuo.okdownload.core.file.DownloadOutputStream.Factory
        public boolean supportSeek() {
            return true;
        }

        @Override // com.liulishuo.okdownload.core.file.DownloadOutputStream.Factory
        public DownloadOutputStream create(Context context, File file, int i) throws FileNotFoundException {
            return new DownloadUriOutputStream(context, Uri.fromFile(file), i);
        }

        @Override // com.liulishuo.okdownload.core.file.DownloadOutputStream.Factory
        public DownloadOutputStream create(Context context, Uri uri, int i) throws FileNotFoundException {
            return new DownloadUriOutputStream(context, uri, i);
        }
    }
}
