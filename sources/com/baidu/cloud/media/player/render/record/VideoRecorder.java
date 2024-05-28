package com.baidu.cloud.media.player.render.record;

import android.graphics.SurfaceTexture;
import android.util.Log;
import com.baidu.cloud.media.player.render.encoder.TextureMovieEncoder;
import com.baidu.cloud.media.player.render.p135b.Logger;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.cloud.media.player.render.record.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VideoRecorder implements IVideoRecorder {

    /* renamed from: a */
    private int f4488a = 720;

    /* renamed from: b */
    private int f4489b = 1280;

    /* renamed from: c */
    private int f4490c = 2097152;

    /* renamed from: d */
    private int f4491d = 30;

    /* renamed from: e */
    private int f4492e = 1;

    /* renamed from: f */
    private float f4493f = 0.0f;

    /* renamed from: g */
    private TextureMovieEncoder f4494g;

    /* renamed from: h */
    private TextureMovieEncoder.OnEncoderStatusUpdateListener f4495h;

    /* renamed from: i */
    private IOnRecordFrameListener f4496i;

    /* renamed from: j */
    private String f4497j;

    /* renamed from: k */
    private int f4498k;

    /* renamed from: l */
    private SurfaceTexture f4499l;

    /* renamed from: m */
    private int f4500m;

    /* renamed from: n */
    private TextureMovieEncoder.OnDrawEncoderFrameCallback f4501n;

    /* renamed from: a */
    public void m19977a(TextureMovieEncoder.OnDrawEncoderFrameCallback onDrawEncoderFrameCallback) {
        this.f4501n = onDrawEncoderFrameCallback;
    }

    @Override // com.baidu.cloud.media.player.render.record.IVideoRecorder
    public void setRecordSize(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            return;
        }
        if (this.f4500m <= 0) {
            this.f4500m = 720;
        }
        this.f4488a = i;
        this.f4489b = i2;
        int i3 = this.f4488a;
        int i4 = this.f4489b;
        if (i4 > i3) {
            int i5 = this.f4500m;
            i4 = ((i4 * i5) / i3) - (((i5 * i4) / i3) % 16);
            i3 = i5;
        } else if (i4 < i3) {
            int i6 = this.f4500m;
            i3 = ((i3 * i6) / i4) - (((i6 * i3) / i4) % 16);
            i4 = i6;
        }
        this.f4488a = i3;
        this.f4489b = i4;
    }

    @Override // com.baidu.cloud.media.player.render.record.IVideoRecorder
    public void setOnEncoderStatusUpdateListener(TextureMovieEncoder.OnEncoderStatusUpdateListener onEncoderStatusUpdateListener) {
        this.f4495h = onEncoderStatusUpdateListener;
    }

    @Override // com.baidu.cloud.media.player.render.record.IVideoRecorder
    public void setSurfaceTexture(SurfaceTexture surfaceTexture) {
        this.f4499l = surfaceTexture;
    }

    @Override // com.baidu.cloud.media.player.render.record.IVideoRecorder
    public void setRecordFrameListener(IOnRecordFrameListener iOnRecordFrameListener) {
        this.f4496i = iOnRecordFrameListener;
    }

    @Override // com.baidu.cloud.media.player.render.record.IVideoRecorder
    public void startRecord(String str) {
        Logger.m20031a("record_time", "vRecorder_startRecord: " + str);
        this.f4497j = str;
        try {
            this.f4494g = new TextureMovieEncoder();
            this.f4494g.m20022a(this.f4501n);
            if (this.f4493f != 0.0f) {
                this.f4494g.m20027a(this.f4493f);
            }
            this.f4494g.m20021a(this.f4495h);
        } catch (Throwable th) {
            Log.d("VideoRecorder", th.toString());
        }
    }

    @Override // com.baidu.cloud.media.player.render.record.IVideoRecorder
    public void updateExternalAudioData(byte[] bArr, int i) {
        TextureMovieEncoder textureMovieEncoder = this.f4494g;
        if (textureMovieEncoder != null) {
            textureMovieEncoder.m20009a(bArr, i);
        }
    }

    @Override // com.baidu.cloud.media.player.render.record.IVideoRecorder
    public String stopRecord() {
        Logger.m20031a("record_time", "vRecorder_stopRecord: " + this.f4497j);
        TextureMovieEncoder textureMovieEncoder = this.f4494g;
        if (textureMovieEncoder != null) {
            textureMovieEncoder.m20028a();
        }
        return this.f4497j;
    }

    @Override // com.baidu.cloud.media.player.render.record.IVideoRecorder
    public void onRecord() {
        this.f4498k++;
        try {
            if (this.f4494g != null && this.f4494g.m20026a(2)) {
                File file = new File(this.f4497j);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                if (this.f4493f == 90.0f || this.f4493f == 270.0f) {
                    int i = this.f4488a;
                    this.f4488a = this.f4489b;
                    this.f4489b = i;
                }
                this.f4494g.m20020a(new TextureMovieEncoder.C2515c(file, this.f4488a, this.f4489b, this.f4490c, this.f4491d, null, this.f4499l.getTimestamp()));
            }
            Logger.m20030b("VideoRecorder", "frameid= " + this.f4498k);
            if (this.f4494g == null || this.f4498k % this.f4492e != 0 || this.f4496i == null) {
                return;
            }
            this.f4496i.onRecord(this.f4494g, this.f4499l);
        } catch (Throwable th) {
            Log.d("VideoRecorder", th.toString());
        }
    }

    @Override // com.baidu.cloud.media.player.render.record.IVideoRecorder
    public int getVideoWidth() {
        return this.f4488a;
    }

    @Override // com.baidu.cloud.media.player.render.record.IVideoRecorder
    public int getVideoHeight() {
        return this.f4489b;
    }

    @Override // com.baidu.cloud.media.player.render.record.IVideoRecorder
    public String getVideoPath() {
        return this.f4497j;
    }

    /* renamed from: a */
    public void m19978a(int i) {
        this.f4490c = i;
    }

    /* renamed from: b */
    public void m19976b(int i) {
        this.f4491d = i;
    }

    /* renamed from: a */
    public boolean m19979a() {
        TextureMovieEncoder textureMovieEncoder = this.f4494g;
        if (textureMovieEncoder != null) {
            return textureMovieEncoder.m20026a(1);
        }
        return false;
    }
}
