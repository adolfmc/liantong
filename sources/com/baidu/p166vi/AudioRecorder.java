package com.baidu.p166vi;

import android.media.AudioRecord;
import android.os.Handler;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.vi.AudioRecorder */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AudioRecorder {

    /* renamed from: j */
    private static Handler f8174j = new HandlerC3327a();

    /* renamed from: a */
    private volatile AudioRecord f8175a;

    /* renamed from: b */
    private int f8176b;

    /* renamed from: c */
    private int f8177c;

    /* renamed from: d */
    private int f8178d;

    /* renamed from: e */
    private boolean f8179e;

    /* renamed from: f */
    private int f8180f;

    /* renamed from: g */
    private int f8181g;

    /* renamed from: h */
    private volatile boolean f8182h = false;

    /* renamed from: i */
    private Object f8183i = new Object();

    /* renamed from: k */
    private Thread f8184k = new C3328b(this, AudioRecorder.class.getSimpleName() + "-Record");

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.vi.AudioRecorder$a */
    /* loaded from: E:\567196_dexfile_execute.dex */
    public class C3326a {

        /* renamed from: a */
        AudioRecorder f8185a;

        /* renamed from: b */
        byte[] f8186b;

        /* renamed from: c */
        int f8187c;

        public C3326a(AudioRecorder audioRecorder, byte[] bArr, int i) {
            this.f8185a = audioRecorder;
            this.f8186b = bArr;
            this.f8187c = i;
        }
    }

    public AudioRecorder(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.f8179e = true;
        if (i3 == 8) {
            this.f8178d = 3;
        } else {
            this.f8178d = 2;
        }
        if (i4 == 2) {
            this.f8177c = 3;
        } else {
            this.f8177c = 2;
        }
        this.f8179e = i7 == 1;
        this.f8176b = i2;
        this.f8181g = i5;
        this.f8180f = i6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m17414a() {
        if (this.f8179e) {
            C3326a c3326a = new C3326a(this, null, 0);
            Handler handler = f8174j;
            handler.sendMessage(handler.obtainMessage(2, c3326a));
        } else if (this.f8182h) {
            onReadError();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m17411a(byte[] bArr, int i) {
        if (this.f8179e) {
            C3326a c3326a = new C3326a(this, bArr, i);
            Handler handler = f8174j;
            handler.sendMessage(handler.obtainMessage(1, c3326a));
        } else if (this.f8182h) {
            onReadData(bArr, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public native void onReadData(byte[] bArr, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native void onReadError();
}
