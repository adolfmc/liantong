package com.baidu.cloud.media.player.render.encoder;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.util.Log;
import com.baidu.cloud.media.player.render.p135b.Logger;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.cloud.media.player.render.encoder.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MediaMuxerWrapper {

    /* renamed from: a */
    private final MediaMuxer f4469a;

    /* renamed from: b */
    private int f4470b = 2;

    /* renamed from: c */
    private int f4471c = 0;

    /* renamed from: d */
    private boolean f4472d = false;

    public MediaMuxerWrapper(String str) throws IOException {
        this.f4469a = new MediaMuxer(str, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized boolean m19988a() {
        Log.v("MediaMuxerWrapper", "start:");
        this.f4471c++;
        if (this.f4470b > 0 && this.f4471c == this.f4470b) {
            this.f4469a.start();
            this.f4472d = true;
            notifyAll();
            Log.v("MediaMuxerWrapper", "MediaMuxer started:");
        }
        return this.f4472d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public synchronized void m19985b() {
        Log.v("MediaMuxerWrapper", "stop:mStatredCount=" + this.f4471c);
        this.f4471c = this.f4471c + (-1);
        if (this.f4470b > 0 && this.f4471c <= 0) {
            if (this.f4472d) {
                this.f4469a.stop();
            }
            this.f4469a.release();
            this.f4472d = false;
            Log.v("MediaMuxerWrapper", "MediaMuxer stopped:");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized int m19986a(MediaFormat mediaFormat) {
        int addTrack;
        if (this.f4472d) {
            throw new IllegalStateException("muxer already started");
        }
        addTrack = this.f4469a.addTrack(mediaFormat);
        Log.i("MediaMuxerWrapper", "addTrack:trackNum=" + this.f4470b + ",trackIx=" + addTrack + ",format=" + mediaFormat);
        return addTrack;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized void m19987a(int i, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
        Logger.m20030b("muxer", "trackindex=" + i + "-- pts=" + bufferInfo.presentationTimeUs);
        if (this.f4471c > 0) {
            this.f4469a.writeSampleData(i, byteBuffer, bufferInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public synchronized boolean m19984c() {
        return this.f4472d;
    }
}
