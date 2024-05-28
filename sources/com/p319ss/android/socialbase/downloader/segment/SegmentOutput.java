package com.p319ss.android.socialbase.downloader.segment;

import android.support.annotation.NonNull;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.model.RandomAccessOutputStream;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.ss.android.socialbase.downloader.segment.SegmentOutput */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class SegmentOutput implements IOutput {
    private final RandomAccessOutputStream output;
    private final Segment segment;
    private final IOutput stub;

    public SegmentOutput(DownloadInfo downloadInfo, BufferQueue bufferQueue, Segment segment) throws BaseException {
        this.segment = segment;
        this.output = createOutStream(downloadInfo, segment);
        this.stub = new OutputStub(bufferQueue, this);
    }

    public IOutput getStub() {
        return this.stub;
    }

    @Override // com.p319ss.android.socialbase.downloader.segment.IOutput
    public void write(@NonNull Buffer buffer) throws IOException {
        this.output.write(buffer.data, 0, buffer.size);
        this.segment.increaseCurrentOffset(buffer.size);
    }

    public void flush() throws IOException {
        this.output.flush();
    }

    public void sync() throws IOException {
        this.output.sync();
    }

    public void close() {
        DownloadUtils.safeClose(this.output);
    }

    public Segment getSegment() {
        return this.segment;
    }

    private RandomAccessOutputStream createOutStream(DownloadInfo downloadInfo, Segment segment) throws BaseException {
        RandomAccessOutputStream createOutputStream = DownloadUtils.createOutputStream(downloadInfo, downloadInfo.getTempPath(), downloadInfo.getTempName(), DownloadSetting.obtain(downloadInfo.getId()).optInt("flush_buffer_size_byte", -1));
        try {
            createOutputStream.seek(segment.getCurrentOffsetRead());
            return createOutputStream;
        } catch (IOException e) {
            throw new BaseException(1054, e);
        }
    }
}
