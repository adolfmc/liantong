package com.megvii.lv5;

import android.media.MediaCodec;
import android.media.MediaFormat;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.Vector;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.h1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class AbstractRunnableC5460h1 implements Runnable {

    /* renamed from: a */
    public final Object f12696a;

    /* renamed from: b */
    public volatile boolean f12697b;

    /* renamed from: c */
    public int f12698c;

    /* renamed from: d */
    public volatile boolean f12699d;

    /* renamed from: e */
    public boolean f12700e;

    /* renamed from: f */
    public boolean f12701f;

    /* renamed from: g */
    public int f12702g;

    /* renamed from: h */
    public MediaCodec f12703h;

    /* renamed from: i */
    public int f12704i;

    /* renamed from: j */
    public int f12705j;

    /* renamed from: k */
    public int f12706k;

    /* renamed from: l */
    public final WeakReference<C5475i1> f12707l;

    /* renamed from: m */
    public MediaCodec.BufferInfo f12708m;

    /* renamed from: n */
    public final InterfaceC5461a f12709n;

    /* renamed from: o */
    public Vector<C5452g1> f12710o;

    /* renamed from: p */
    public EnumC5488k1 f12711p;

    /* renamed from: q */
    public C5452g1 f12712q;

    /* renamed from: r */
    public long f12713r;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.h1$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC5461a {
        /* renamed from: a */
        void mo12905a(AbstractRunnableC5460h1 abstractRunnableC5460h1);

        /* renamed from: b */
        void mo12904b(AbstractRunnableC5460h1 abstractRunnableC5460h1);

        /* renamed from: c */
        void mo12903c(AbstractRunnableC5460h1 abstractRunnableC5460h1);
    }

    public AbstractRunnableC5460h1(C5475i1 c5475i1, InterfaceC5461a interfaceC5461a) {
        Object obj = new Object();
        this.f12696a = obj;
        this.f12710o = new Vector<>();
        this.f12712q = null;
        this.f12713r = 0L;
        if (interfaceC5461a == null) {
            throw new NullPointerException("MediaEncoderListener is null");
        }
        if (c5475i1 == null) {
            throw new NullPointerException("MediaMuxerColorWrapper is null");
        }
        this.f12707l = new WeakReference<>(c5475i1);
        c5475i1.m13456a(this);
        this.f12711p = c5475i1.m13454b();
        this.f12709n = interfaceC5461a;
        synchronized (obj) {
            this.f12708m = new MediaCodec.BufferInfo();
            new Thread(this, getClass().getSimpleName()).start();
            try {
                obj.wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    /* renamed from: a */
    public void m13500a() {
        int addTrack;
        boolean z;
        boolean z2;
        byte[] bArr;
        if (this.f12703h == null) {
            return;
        }
        if (this.f12710o.size() > 0) {
            C5452g1 remove = this.f12710o.remove(0);
            this.f12712q = remove;
            byte[] bArr2 = remove.f12686a;
            int i = this.f12705j * this.f12706k;
            byte[] bArr3 = new byte[(i * 3) / 2];
            int i2 = this.f12704i;
            if (i2 == 21 || i2 == 2130706688) {
                int i3 = i / 4;
                bArr = new byte[(int) (i * 1.5d)];
                System.arraycopy(bArr2, 0, bArr, 0, i);
                for (int i4 = 0; i4 < i3; i4++) {
                    int i5 = (i4 * 2) + i;
                    byte b = bArr2[i5];
                    int i6 = i5 + 1;
                    bArr[i5] = bArr2[i6];
                    bArr[i6] = b;
                }
            } else if (i2 == 19) {
                int i7 = i / 4;
                bArr = new byte[(int) (i * 1.5d)];
                System.arraycopy(bArr2, 0, bArr, 0, i);
                for (int i8 = 0; i8 < i7; i8++) {
                    int i9 = (i8 * 2) + i;
                    byte b2 = bArr2[i9];
                    byte b3 = bArr2[i9 + 1];
                    int i10 = i + i8;
                    bArr[i10 + i7] = b2;
                    bArr[i10] = b3;
                }
            } else {
                C5628v2.m12959a("mfx", "This color format is not yet supported, passing the NV21 frame directly to the encoder and hoping for the best!");
                bArr = bArr3;
            }
            try {
                ByteBuffer[] inputBuffers = this.f12703h.getInputBuffers();
                int dequeueInputBuffer = this.f12703h.dequeueInputBuffer(10000L);
                if (dequeueInputBuffer >= 0) {
                    ByteBuffer byteBuffer = inputBuffers[dequeueInputBuffer];
                    byteBuffer.clear();
                    byteBuffer.put(bArr);
                    this.f12703h.queueInputBuffer(dequeueInputBuffer, 0, bArr.length, m13499b(), 0);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        ByteBuffer[] outputBuffers = this.f12703h.getOutputBuffers();
        C5475i1 c5475i1 = this.f12707l.get();
        if (c5475i1 == null) {
            return;
        }
        ByteBuffer[] byteBufferArr = outputBuffers;
        int i11 = 0;
        int i12 = 0;
        while (this.f12697b) {
            try {
                i11 = this.f12703h.dequeueOutputBuffer(this.f12708m, 1000L);
            } catch (Throwable unused) {
            }
            if (i11 == -1) {
                if (!this.f12700e && (i12 = i12 + 1) > 5) {
                    return;
                }
            } else if (i11 == -3) {
                byteBufferArr = this.f12703h.getOutputBuffers();
            } else if (i11 == -2) {
                if (!this.f12701f) {
                    MediaFormat outputFormat = this.f12703h.getOutputFormat();
                    synchronized (c5475i1) {
                        if (c5475i1.f12801e) {
                            throw new IllegalStateException("muxer already started");
                        }
                        addTrack = c5475i1.f12798b.addTrack(outputFormat);
                    }
                    this.f12702g = addTrack;
                    this.f12701f = true;
                    synchronized (c5475i1) {
                        int i13 = c5475i1.f12800d + 1;
                        c5475i1.f12800d = i13;
                        int i14 = c5475i1.f12799c;
                        if (i14 > 0 && i13 == i14) {
                            c5475i1.f12798b.start();
                            c5475i1.f12801e = true;
                            c5475i1.notifyAll();
                        }
                        z = c5475i1.f12801e;
                    }
                    if (z) {
                        continue;
                    } else {
                        synchronized (c5475i1) {
                            while (true) {
                                synchronized (c5475i1) {
                                    z2 = c5475i1.f12801e;
                                }
                                c5475i1.wait(100L);
                            }
                        }
                        if (!z2) {
                            try {
                                c5475i1.wait(100L);
                            } catch (InterruptedException unused2) {
                                return;
                            }
                        }
                    }
                } else {
                    throw new RuntimeException("format changed twice");
                }
            } else if (i11 >= 0) {
                ByteBuffer byteBuffer2 = byteBufferArr[i11];
                if (byteBuffer2 == null) {
                    throw new RuntimeException("encoderOutputBuffer " + i11 + " was null");
                }
                MediaCodec.BufferInfo bufferInfo = this.f12708m;
                if ((bufferInfo.flags & 2) != 0) {
                    bufferInfo.size = 0;
                }
                if (bufferInfo.size != 0) {
                    if (this.f12701f) {
                        String str = "drain: presentationTimeUs = " + this.f12708m.presentationTimeUs;
                        int i15 = this.f12702g;
                        MediaCodec.BufferInfo bufferInfo2 = this.f12708m;
                        synchronized (c5475i1) {
                            if (c5475i1.f12800d > 0) {
                                c5475i1.f12798b.writeSampleData(i15, byteBuffer2, bufferInfo2);
                            }
                        }
                        i12 = 0;
                    } else {
                        throw new RuntimeException("drain:muxer hasn't started");
                    }
                }
                this.f12703h.releaseOutputBuffer(i11, false);
                if ((this.f12708m.flags & 4) != 0) {
                    this.f12697b = false;
                    return;
                }
            } else {
                continue;
            }
        }
    }

    /* renamed from: b */
    public long m13499b() {
        if (this.f12711p != EnumC5488k1.AllFrames) {
            long j = this.f12713r;
            this.f12713r = j == 0 ? System.nanoTime() / 1000 : j + 33000;
        } else {
            C5452g1 c5452g1 = this.f12712q;
            if (c5452g1 != null) {
                this.f12713r = c5452g1.f12687b;
            }
        }
        return this.f12713r;
    }

    /* renamed from: c */
    public abstract void mo13448c();

    /* renamed from: d */
    public abstract void mo13447d();

    /* renamed from: e */
    public void m13498e() {
        MediaCodec mediaCodec;
        if (this.f12711p == EnumC5488k1.Screen && (this instanceof C5482j1)) {
            this.f12703h.signalEndOfInputStream();
            return;
        }
        long m13499b = m13499b();
        if (this.f12697b && (mediaCodec = this.f12703h) != null) {
            ByteBuffer[] inputBuffers = mediaCodec.getInputBuffers();
            while (this.f12697b) {
                int dequeueInputBuffer = this.f12703h.dequeueInputBuffer(10000L);
                if (dequeueInputBuffer >= 0) {
                    inputBuffers[dequeueInputBuffer].clear();
                    this.f12700e = true;
                    this.f12703h.queueInputBuffer(dequeueInputBuffer, 0, 0, m13499b, 4);
                    return;
                }
            }
        }
    }

    /* renamed from: f */
    public void m13497f() {
        synchronized (this.f12696a) {
            this.f12697b = true;
            this.f12699d = false;
            if (this.f12711p == EnumC5488k1.Screen) {
                this.f12698c = 1;
            }
            this.f12696a.notifyAll();
        }
    }

    /* renamed from: g */
    public void m13496g() {
        synchronized (this.f12696a) {
            if (this.f12697b && !this.f12699d) {
                if (this.f12711p == EnumC5488k1.Screen) {
                    this.f12698c = 0;
                }
                this.f12699d = true;
                this.f12696a.notifyAll();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0065 A[Catch: all -> 0x006b, TRY_ENTER, TryCatch #4 {all -> 0x0076, blocks: (B:2:0x0000, B:3:0x0002, B:7:0x000e, B:8:0x0010, B:32:0x0042, B:43:0x0062, B:44:0x0064, B:34:0x0051, B:35:0x0055, B:36:0x0057, B:9:0x0011, B:11:0x0018, B:13:0x001e, B:30:0x003f, B:17:0x0026, B:19:0x002a, B:23:0x0031, B:28:0x003a, B:5:0x0004, B:6:0x000d, B:45:0x0065, B:46:0x0069, B:37:0x0058, B:38:0x005d, B:42:0x0061), top: B:62:0x0000 }] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.f12696a     // Catch: java.lang.Throwable -> L76
            monitor-enter(r0)     // Catch: java.lang.Throwable -> L76
            r1 = 0
            r6.f12699d = r1     // Catch: java.lang.Throwable -> L73
            r6.f12698c = r1     // Catch: java.lang.Throwable -> L73
            java.lang.Object r2 = r6.f12696a     // Catch: java.lang.Throwable -> L73
            r2.notify()     // Catch: java.lang.Throwable -> L73
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L73
        Le:
            java.lang.Object r0 = r6.f12696a     // Catch: java.lang.Throwable -> L76
            monitor-enter(r0)     // Catch: java.lang.Throwable -> L76
            com.megvii.lv5.k1 r2 = r6.f12711p     // Catch: java.lang.Throwable -> L70
            com.megvii.lv5.k1 r3 = com.megvii.lv5.EnumC5488k1.Screen     // Catch: java.lang.Throwable -> L70
            r4 = 1
            if (r2 != r3) goto L26
            boolean r2 = r6.f12699d     // Catch: java.lang.Throwable -> L70
            boolean r3 = r6.f12699d     // Catch: java.lang.Throwable -> L70
            if (r3 != 0) goto L24
            int r3 = r6.f12698c     // Catch: java.lang.Throwable -> L70
            if (r3 <= 0) goto L24
            r3 = r4
            goto L3f
        L24:
            r3 = r1
            goto L3f
        L26:
            boolean r2 = r6.f12699d     // Catch: java.lang.Throwable -> L70
            if (r2 == 0) goto L30
            int r2 = r6.f12698c     // Catch: java.lang.Throwable -> L70
            if (r2 > 0) goto L30
            r2 = r4
            goto L31
        L30:
            r2 = r1
        L31:
            int r3 = r6.f12698c     // Catch: java.lang.Throwable -> L70
            if (r3 <= 0) goto L37
            r5 = r4
            goto L38
        L37:
            r5 = r1
        L38:
            if (r5 == 0) goto L3e
            int r3 = r3 + (-1)
            r6.f12698c = r3     // Catch: java.lang.Throwable -> L70
        L3e:
            r3 = r5
        L3f:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L70
            if (r2 == 0) goto L4f
            r6.m13500a()     // Catch: java.lang.Throwable -> L76
            r6.m13498e()     // Catch: java.lang.Throwable -> L76
            r6.m13500a()     // Catch: java.lang.Throwable -> L76
            r6.mo13447d()     // Catch: java.lang.Throwable -> L76
            goto L62
        L4f:
            if (r3 == 0) goto L55
            r6.m13500a()     // Catch: java.lang.Throwable -> L76
            goto Le
        L55:
            java.lang.Object r0 = r6.f12696a     // Catch: java.lang.Throwable -> L76
            monitor-enter(r0)     // Catch: java.lang.Throwable -> L76
            java.lang.Object r2 = r6.f12696a     // Catch: java.lang.Throwable -> L5f java.lang.InterruptedException -> L61
            r2.wait()     // Catch: java.lang.Throwable -> L5f java.lang.InterruptedException -> L61
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L5f
            goto Le
        L5f:
            r1 = move-exception
            goto L6e
        L61:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L5f
        L62:
            java.lang.Object r0 = r6.f12696a     // Catch: java.lang.Throwable -> L76
            monitor-enter(r0)     // Catch: java.lang.Throwable -> L76
            r6.f12699d = r4     // Catch: java.lang.Throwable -> L6b
            r6.f12697b = r1     // Catch: java.lang.Throwable -> L6b
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L6b
            goto L7a
        L6b:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L6b
            throw r1     // Catch: java.lang.Throwable -> L76
        L6e:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L5f
            throw r1     // Catch: java.lang.Throwable -> L76
        L70:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L70
            throw r1     // Catch: java.lang.Throwable -> L76
        L73:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L73
            throw r1     // Catch: java.lang.Throwable -> L76
        L76:
            r0 = move-exception
            r0.printStackTrace()
        L7a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.AbstractRunnableC5460h1.run():void");
    }
}
