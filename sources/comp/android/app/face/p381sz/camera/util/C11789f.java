package comp.android.app.face.p381sz.camera.util;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.view.Surface;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.ArrayBlockingQueue;

/* renamed from: comp.android.app.face.sz.camera.util.f */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11789f {

    /* renamed from: b */
    public byte[] f24015b;

    /* renamed from: d */
    private MediaCodec f24017d;

    /* renamed from: e */
    private int f24018e;

    /* renamed from: f */
    private int f24019f;

    /* renamed from: g */
    private int f24020g;

    /* renamed from: h */
    private BufferedOutputStream f24021h;

    /* renamed from: a */
    public boolean f24014a = false;

    /* renamed from: c */
    public ArrayBlockingQueue<byte[]> f24016c = new ArrayBlockingQueue<>(10);

    /* renamed from: i */
    private boolean f24022i = false;

    public C11789f(int i, int i2, int i3, File file, int i4) {
        this.f24018e = i;
        this.f24019f = i2;
        this.f24020g = i3;
        MediaFormat createVideoFormat = i4 == 0 ? MediaFormat.createVideoFormat("video/avc", i, i2) : i4 == 1 ? MediaFormat.createVideoFormat("video/avc", i2, i) : null;
        createVideoFormat.setInteger("color-format", 21);
        createVideoFormat.setInteger("bitrate", i * i2 * 4);
        createVideoFormat.setInteger("frame-rate", 30);
        createVideoFormat.setInteger("i-frame-interval", 1);
        try {
            this.f24017d = MediaCodec.createEncoderByType("video/avc");
            this.f24017d.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
            this.f24017d.start();
            m2132a(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public long m2136a(long j) {
        return ((j * 1000000) / this.f24020g) + 132;
    }

    /* renamed from: a */
    private void m2132a(File file) {
        if (file.exists()) {
            file.delete();
        }
        try {
            this.f24021h = new BufferedOutputStream(new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m2129a(byte[] bArr, byte[] bArr2, int i, int i2) {
        int i3;
        if (bArr == null || bArr2 == null) {
            return;
        }
        int i4 = i * i2;
        System.arraycopy(bArr, 0, bArr2, 0, i4);
        for (int i5 = 0; i5 < i4; i5++) {
            bArr2[i5] = bArr[i5];
        }
        int i6 = 0;
        while (true) {
            i3 = i4 / 2;
            if (i6 >= i3) {
                break;
            }
            int i7 = i4 + i6;
            bArr2[i7 - 1] = bArr[i7];
            i6 += 2;
        }
        for (int i8 = 0; i8 < i3; i8 += 2) {
            int i9 = i4 + i8;
            bArr2[i9] = bArr[i9 - 1];
        }
    }

    /* renamed from: a */
    private byte[] m2130a(byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        byte[] bArr2 = new byte[(i3 * 3) / 2];
        int i4 = i - 1;
        int i5 = i4;
        int i6 = 0;
        while (i5 >= 0) {
            int i7 = i6;
            for (int i8 = 0; i8 < i2; i8++) {
                bArr2[i7] = bArr[(i8 * i) + i5];
                i7++;
            }
            i5--;
            i6 = i7;
        }
        while (i4 > 0) {
            for (int i9 = 0; i9 < i2 / 2; i9++) {
                int i10 = (i9 * i) + i3;
                bArr2[i6] = bArr[(i4 - 1) + i10];
                int i11 = i6 + 1;
                bArr2[i11] = bArr[i10 + i4];
                i6 = i11 + 1;
            }
            i4 -= 2;
        }
        return bArr2;
    }

    /* renamed from: a */
    public void m2137a() {
        this.f24022i = true;
    }

    /* renamed from: a */
    public void m2131a(byte[] bArr, int i) {
        ArrayBlockingQueue<byte[]> arrayBlockingQueue;
        if (this.f24016c.size() >= 10) {
            this.f24016c.poll();
        }
        if (i == 0) {
            arrayBlockingQueue = this.f24016c;
        } else if (i != 1) {
            return;
        } else {
            arrayBlockingQueue = this.f24016c;
            bArr = m2130a(bArr, this.f24018e, this.f24019f);
        }
        arrayBlockingQueue.add(bArr);
    }

    /* renamed from: b */
    public void m2128b() {
        this.f24022i = false;
    }

    /* renamed from: c */
    public void m2126c() {
        new Thread(new Runnable() { // from class: comp.android.app.face.sz.camera.util.f.1
            @Override // java.lang.Runnable
            public void run() {
                byte[] bArr;
                C11789f.this.f24014a = true;
                byte[] bArr2 = null;
                long j = 0;
                while (C11789f.this.f24014a) {
                    if (!C11789f.this.f24022i) {
                        if (C11789f.this.f24016c.size() > 0) {
                            bArr = new byte[((C11789f.this.f24018e * C11789f.this.f24019f) * 3) / 2];
                            C11789f c11789f = C11789f.this;
                            c11789f.m2129a(C11789f.this.f24016c.poll(), bArr, c11789f.f24018e, C11789f.this.f24019f);
                        } else {
                            bArr = bArr2;
                        }
                        if (bArr != null) {
                            try {
                                ByteBuffer[] inputBuffers = C11789f.this.f24017d.getInputBuffers();
                                ByteBuffer[] outputBuffers = C11789f.this.f24017d.getOutputBuffers();
                                int dequeueInputBuffer = C11789f.this.f24017d.dequeueInputBuffer(-1L);
                                if (dequeueInputBuffer >= 0) {
                                    C11789f.this.m2136a(j);
                                    ByteBuffer byteBuffer = inputBuffers[dequeueInputBuffer];
                                    byteBuffer.clear();
                                    byteBuffer.put(bArr);
                                    C11789f.this.f24017d.queueInputBuffer(dequeueInputBuffer, 0, bArr.length, System.currentTimeMillis(), 0);
                                    j++;
                                }
                                MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
                                int dequeueOutputBuffer = C11789f.this.f24017d.dequeueOutputBuffer(bufferInfo, 12000L);
                                while (dequeueOutputBuffer >= 0) {
                                    ByteBuffer byteBuffer2 = outputBuffers[dequeueOutputBuffer];
                                    byte[] bArr3 = new byte[bufferInfo.size];
                                    byteBuffer2.get(bArr3);
                                    if (bufferInfo.flags == 2) {
                                        C11789f.this.f24015b = new byte[bufferInfo.size];
                                        C11789f.this.f24015b = bArr3;
                                    } else if (bufferInfo.flags == 1) {
                                        byte[] bArr4 = new byte[bufferInfo.size + C11789f.this.f24015b.length];
                                        System.arraycopy(C11789f.this.f24015b, 0, bArr4, 0, C11789f.this.f24015b.length);
                                        System.arraycopy(bArr3, 0, bArr4, C11789f.this.f24015b.length, bArr3.length);
                                        C11789f.this.f24021h.write(bArr4, 0, bArr4.length);
                                    } else {
                                        C11789f.this.f24021h.write(bArr3, 0, bArr3.length);
                                    }
                                    C11789f.this.f24017d.releaseOutputBuffer(dequeueOutputBuffer, false);
                                    dequeueOutputBuffer = C11789f.this.f24017d.dequeueOutputBuffer(bufferInfo, 12000L);
                                }
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        } else {
                            try {
                                Thread.sleep(500L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        bArr2 = bArr;
                    }
                }
                try {
                    C11789f.this.f24017d.stop();
                    C11789f.this.f24017d.release();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                try {
                    C11789f.this.f24021h.flush();
                    C11789f.this.f24021h.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }).start();
    }

    /* renamed from: d */
    public void m2124d() {
        this.f24014a = false;
    }
}
