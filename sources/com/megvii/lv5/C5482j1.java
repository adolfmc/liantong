package com.megvii.lv5;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.projection.MediaProjection;
import android.os.Build;
import android.view.Surface;
import com.megvii.lv5.AbstractRunnableC5460h1;
import java.lang.ref.WeakReference;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.j1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5482j1 extends AbstractRunnableC5460h1 {

    /* renamed from: s */
    public RunnableC5516n1 f12815s;

    /* renamed from: t */
    public Surface f12816t;

    /* renamed from: u */
    public float f12817u;

    /* renamed from: v */
    public MediaProjection f12818v;

    /* renamed from: w */
    public int f12819w;

    /* renamed from: x */
    public C5475i1 f12820x;

    public C5482j1(C5475i1 c5475i1, MediaProjection mediaProjection, AbstractRunnableC5460h1.InterfaceC5461a interfaceC5461a, int i, int i2, int i3) {
        super(c5475i1, interfaceC5461a);
        this.f12817u = 1.0f;
        this.f12818v = null;
        this.f12820x = c5475i1;
        this.f12817u = c5475i1.m13459a();
        this.f12705j = i;
        this.f12706k = i2;
        this.f12815s = RunnableC5516n1.m13281a("MediaVideoEncoder");
    }

    public C5482j1(C5475i1 c5475i1, AbstractRunnableC5460h1.InterfaceC5461a interfaceC5461a, int i, int i2) {
        this(c5475i1, null, interfaceC5461a, i, i2, 0);
    }

    /* renamed from: a */
    public static final int m13449a(MediaCodecInfo mediaCodecInfo, String str) {
        try {
            Thread.currentThread().setPriority(10);
            MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
            Thread.currentThread().setPriority(5);
            int i = 0;
            while (true) {
                int[] iArr = capabilitiesForType.colorFormats;
                if (i >= iArr.length) {
                    return 0;
                }
                int i2 = iArr[i];
                if (i2 == 19 || i2 == 21 || i2 == 2130706688) {
                    return i2;
                }
                i++;
            }
        } catch (Throwable th) {
            Thread.currentThread().setPriority(5);
            throw th;
        }
    }

    @Override // com.megvii.lv5.AbstractRunnableC5460h1
    /* renamed from: c */
    public void mo13448c() {
        MediaCodec createByCodecName;
        this.f12702g = -1;
        this.f12700e = false;
        this.f12701f = false;
        try {
            createByCodecName = MediaCodec.createByCodecName("OMX.google.h264.encoder");
            this.f12703h = createByCodecName;
        } catch (Throwable unused) {
            MediaCodec createEncoderByType = MediaCodec.createEncoderByType("video/avc");
            this.f12703h = createEncoderByType;
            if (createEncoderByType == null) {
                return;
            }
            this.f12704i = m13449a(createEncoderByType.getCodecInfo(), "video/avc");
        }
        if (createByCodecName == null) {
            return;
        }
        this.f12704i = m13449a(createByCodecName.getCodecInfo(), "video/avc");
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", this.f12705j, this.f12706k);
        createVideoFormat.setInteger("color-format", this.f12704i);
        createVideoFormat.setInteger("bitrate", ((int) (((this.f12705j * 13.333334f) * this.f12706k) * this.f12817u)) / 2);
        createVideoFormat.setInteger("frame-rate", 25);
        createVideoFormat.setInteger("i-frame-interval", 10);
        this.f12703h.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
        if (this.f12820x.f12804h == EnumC5488k1.Screen) {
            try {
                this.f12816t = this.f12703h.createInputSurface();
            } catch (Exception e) {
                e.printStackTrace();
            }
            MediaProjection mediaProjection = this.f12818v;
            if (mediaProjection != null && Build.VERSION.SDK_INT >= 21) {
                mediaProjection.createVirtualDisplay("MediaVideoEncoder", this.f12705j, this.f12706k, this.f12819w, 16, this.f12816t, null, null);
            }
        }
        this.f12703h.start();
        AbstractRunnableC5460h1.InterfaceC5461a interfaceC5461a = this.f12709n;
        if (interfaceC5461a != null) {
            try {
                interfaceC5461a.mo12904b(this);
            } catch (Exception unused2) {
            }
        }
    }

    @Override // com.megvii.lv5.AbstractRunnableC5460h1
    /* renamed from: d */
    public void mo13447d() {
        Surface surface = this.f12816t;
        if (surface != null) {
            surface.release();
            this.f12816t = null;
        }
        RunnableC5516n1 runnableC5516n1 = this.f12815s;
        if (runnableC5516n1 != null) {
            synchronized (runnableC5516n1.f13048a) {
                if (!runnableC5516n1.f13050c) {
                    runnableC5516n1.f13050c = true;
                    runnableC5516n1.f13048a.notifyAll();
                    try {
                        runnableC5516n1.f13048a.wait();
                    } catch (InterruptedException unused) {
                    }
                }
            }
            this.f12815s = null;
        }
        try {
            this.f12709n.mo12905a(this);
        } catch (Exception unused2) {
        }
        this.f12697b = false;
        MediaCodec mediaCodec = this.f12703h;
        if (mediaCodec != null) {
            try {
                mediaCodec.stop();
                this.f12703h.release();
                this.f12703h = null;
            } catch (Exception unused3) {
            }
        }
        if (this.f12701f) {
            WeakReference<C5475i1> weakReference = this.f12707l;
            C5475i1 c5475i1 = weakReference != null ? weakReference.get() : null;
            if (c5475i1 != null) {
                try {
                    synchronized (c5475i1) {
                        try {
                            int i = c5475i1.f12800d - 1;
                            c5475i1.f12800d = i;
                            if (c5475i1.f12799c > 0 && i <= 0) {
                                c5475i1.f12798b.stop();
                                c5475i1.f12798b.release();
                                c5475i1.f12801e = false;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception unused4) {
                }
            }
        }
        this.f12708m = null;
        this.f12709n.mo12903c(this);
    }
}
