package com.liulishuo.filedownloader.message;

import android.os.Parcel;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class MessageSnapshot implements IMessageSnapshot {

    /* renamed from: id */
    private final int f12210id;
    protected boolean largeFile;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface IWarnMessageSnapshot {
        MessageSnapshot turnToPending();
    }

    MessageSnapshot(int i) {
        this.f12210id = i;
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    public int getId() {
        return this.f12210id;
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    public Throwable getThrowable() {
        throw new NoFieldException("getThrowable", this);
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    public int getRetryingTimes() {
        throw new NoFieldException("getRetryingTimes", this);
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    public boolean isResuming() {
        throw new NoFieldException("isResuming", this);
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    public String getEtag() {
        throw new NoFieldException("getEtag", this);
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    public long getLargeSofarBytes() {
        throw new NoFieldException("getLargeSofarBytes", this);
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    public long getLargeTotalBytes() {
        throw new NoFieldException("getLargeTotalBytes", this);
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    public int getSmallSofarBytes() {
        throw new NoFieldException("getSmallSofarBytes", this);
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    public int getSmallTotalBytes() {
        throw new NoFieldException("getSmallTotalBytes", this);
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    public boolean isReusedDownloadedFile() {
        throw new NoFieldException("isReusedDownloadedFile", this);
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    public String getFileName() {
        throw new NoFieldException("getFileName", this);
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    public boolean isLargeFile() {
        return this.largeFile;
    }

    MessageSnapshot(Parcel parcel) {
        this.f12210id = parcel.readInt();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class NoFieldException extends IllegalStateException {
        NoFieldException(String str, MessageSnapshot messageSnapshot) {
            super(String.format(Locale.ENGLISH, "There isn't a field for '%s' in this message %d %d %s", str, Integer.valueOf(messageSnapshot.getId()), Byte.valueOf(messageSnapshot.getStatus()), messageSnapshot.getClass().getName()));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class StartedMessageSnapshot extends MessageSnapshot {
        @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
        public byte getStatus() {
            return (byte) 6;
        }

        StartedMessageSnapshot(int i) {
            super(i);
        }

        StartedMessageSnapshot(Parcel parcel) {
            super(parcel);
        }
    }
}
