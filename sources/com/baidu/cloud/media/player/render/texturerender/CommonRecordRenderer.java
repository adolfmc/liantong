package com.baidu.cloud.media.player.render.texturerender;

import android.graphics.SurfaceTexture;
import com.baidu.cloud.media.player.render.encoder.TextureMovieEncoder;
import com.baidu.cloud.media.player.render.p134a.FullFrameRect;
import com.baidu.cloud.media.player.render.record.IOnRecordFrameListener;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.cloud.media.player.render.texturerender.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CommonRecordRenderer implements IOnRecordFrameListener, IMediaRenderCallback {

    /* renamed from: a */
    private int f4502a;

    /* renamed from: b */
    private FullFrameRect f4503b;

    /* renamed from: c */
    private float[] f4504c = new float[16];

    @Override // com.baidu.cloud.media.player.render.record.IOnRecordFrameListener
    public void onRecord(TextureMovieEncoder textureMovieEncoder, SurfaceTexture surfaceTexture) {
        textureMovieEncoder.m20024a(surfaceTexture);
    }

    @Override // com.baidu.cloud.media.player.render.texturerender.IMediaRenderCallback
    public void onDrawFrame(FullFrameRect fullFrameRect, int i, float[] fArr) {
        this.f4502a = i;
        this.f4503b = fullFrameRect;
        this.f4504c = fArr;
    }

    /* renamed from: a */
    public void m19975a() {
        this.f4503b.m20040a(this.f4502a, this.f4504c);
    }
}
