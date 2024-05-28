package com.megvii.lv5.sdk.detect;

import android.text.TextUtils;
import com.megvii.lv5.C5391c;
import com.megvii.lv5.C5537q;
import com.megvii.lv5.lib.jni.MegLiveDetector;
import com.megvii.lv5.sdk.base.BaseModel;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class MegliveModeImpl extends BaseModel {

    /* renamed from: a */
    public long f13283a = 0;

    /* renamed from: b */
    public C5391c f13284b;

    /* renamed from: a */
    public C5391c m13156a(byte[] bArr, int i, int i2, int i3, boolean z, float f, boolean z2, boolean z3) {
        C5391c c5391c;
        MegLiveDetector m13440a;
        long j;
        C5391c c5391c2;
        MegLiveDetector m13440a2;
        long j2;
        synchronized (MegliveModeImpl.class) {
            if (this.f13283a == 0) {
                return null;
            }
            MegLiveDetector.m13440a().nativeLiveDetect(this.f13283a, bArr, i, i2, i3, z, f, z2, z3);
            int currentStep = MegLiveDetector.m13440a().getCurrentStep(this.f13283a);
            C5391c c5391c3 = this.f13284b;
            c5391c3.f12400a = currentStep;
            c5391c3.f12407h = MegLiveDetector.m13440a().doActionVideoRecord(this.f13283a);
            this.f13284b.f12405f = MegLiveDetector.m13440a().getActionFailedType(this.f13283a);
            if (currentStep == 0) {
                c5391c2 = this.f13284b;
                m13440a2 = MegLiveDetector.m13440a();
                j2 = this.f13283a;
            } else {
                if (currentStep == 1) {
                    this.f13284b.f12408i = MegLiveDetector.m13440a().getMoveProgress(this.f13283a);
                } else {
                    if (currentStep == 2) {
                        c5391c2 = this.f13284b;
                        m13440a2 = MegLiveDetector.m13440a();
                    } else if (currentStep == 3) {
                        c5391c2 = this.f13284b;
                        m13440a2 = MegLiveDetector.m13440a();
                    } else if (currentStep == 5) {
                        c5391c2 = this.f13284b;
                        m13440a2 = MegLiveDetector.m13440a();
                    } else {
                        if (currentStep != 6 && currentStep != 4) {
                            if (currentStep == 9) {
                                c5391c2 = this.f13284b;
                                m13440a2 = MegLiveDetector.m13440a();
                            } else if (currentStep == 11) {
                                c5391c2 = this.f13284b;
                                m13440a2 = MegLiveDetector.m13440a();
                            } else if (currentStep == 12) {
                                this.f13284b.f12406g = MegLiveDetector.m13440a().getLiveProgress(this.f13283a);
                            } else {
                                if (currentStep == 13) {
                                    c5391c2 = this.f13284b;
                                    m13440a2 = MegLiveDetector.m13440a();
                                } else {
                                    if (currentStep == 14) {
                                        c5391c = this.f13284b;
                                        m13440a = MegLiveDetector.m13440a();
                                        j = this.f13283a;
                                    } else if (currentStep == 15) {
                                        c5391c2 = this.f13284b;
                                        m13440a2 = MegLiveDetector.m13440a();
                                    } else if (currentStep != 16 && currentStep == 17) {
                                        c5391c = this.f13284b;
                                        m13440a = MegLiveDetector.m13440a();
                                        j = this.f13283a;
                                    }
                                    c5391c.f12404e = m13440a.getLiveFailedType(j);
                                }
                                j2 = this.f13283a;
                            }
                        }
                        this.f13284b.f12402c = MegLiveDetector.m13440a().getActionCurrentIndex(this.f13283a);
                        this.f13284b.f12403d = MegLiveDetector.m13440a().getActionCurrentType(this.f13283a);
                    }
                    j2 = this.f13283a;
                }
                return this.f13284b;
            }
            c5391c2.f12401b = m13440a2.getQualityErrorType(j2);
            return this.f13284b;
        }
    }

    /* renamed from: a */
    public final String m13160a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int indexOf = str.indexOf("{");
        int length = str.length();
        return (indexOf < 0 || length <= 3) ? "" : str.substring(indexOf, length);
    }

    /* renamed from: a */
    public void m13163a(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18, float f19, int i, int i2, boolean z, float f20) {
        synchronized (MegliveModeImpl.class) {
            if (this.f13283a == 0) {
                return;
            }
            MegLiveDetector.m13440a().nativeSetLiveConfig(this.f13283a, f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, f17, f18, f19, i, i2, z, f20);
        }
    }

    /* renamed from: a */
    public void m13162a(int i, int[] iArr) {
        synchronized (MegliveModeImpl.class) {
            if (this.f13283a == 0) {
                return;
            }
            MegLiveDetector.m13440a().setBadImageTypeArray(this.f13283a, i, iArr);
        }
    }

    /* renamed from: a */
    public void m13158a(boolean z) {
        synchronized (MegliveModeImpl.class) {
            if (this.f13283a == 0) {
                return;
            }
            MegLiveDetector.m13440a().enableWhiteBalance(this.f13283a, z);
        }
    }

    /* renamed from: a */
    public void m13157a(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
        synchronized (MegliveModeImpl.class) {
            if (this.f13283a == 0) {
                return;
            }
            MegLiveDetector.m13440a().nativeSetActionBlockConfig(this.f13283a, z, z2, z3, z4, z5, z6, z7, z8);
        }
    }

    /* renamed from: a */
    public void m13155a(int[] iArr) {
        synchronized (MegliveModeImpl.class) {
            if (this.f13283a == 0) {
                return;
            }
            MegLiveDetector.m13440a().nativeResetAction(this.f13283a, iArr);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x006c A[Catch: all -> 0x0084, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x0011, B:10:0x0016, B:15:0x0021, B:17:0x006c, B:18:0x0080, B:20:0x0082), top: B:25:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0082 A[Catch: all -> 0x0084, DONT_GENERATE, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x0011, B:10:0x0016, B:15:0x0021, B:17:0x006c, B:18:0x0080, B:20:0x0082), top: B:25:0x0007 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean m13161a(com.megvii.lv5.EnumC5633w r41, double r42, double r44, int r46, int r47, int r48, int[] r49, byte[] r50, byte[] r51, byte[] r52, java.lang.String r53, java.lang.String r54, int r55, long r56, long r58, long r60, long r62, float r64, java.lang.String r65, float r66, float r67, java.lang.String r68, int r69, boolean r70, boolean r71, int r72, float r73, int r74, int r75) {
        /*
            r40 = this;
            r1 = r40
            r0 = r49
            java.lang.Class<com.megvii.lv5.sdk.detect.MegliveModeImpl> r36 = com.megvii.lv5.sdk.detect.MegliveModeImpl.class
            monitor-enter(r36)
            long r2 = r1.f13283a     // Catch: java.lang.Throwable -> L84
            r37 = 0
            int r2 = (r2 > r37 ? 1 : (r2 == r37 ? 0 : -1))
            r39 = 0
            if (r2 == 0) goto L13
            monitor-exit(r36)     // Catch: java.lang.Throwable -> L84
            return r39
        L13:
            r2 = 1
            if (r0 == 0) goto L1e
            int r3 = r0.length     // Catch: java.lang.Throwable -> L84
            r11 = r48
            if (r11 != r3) goto L20
            r9 = r39
            goto L21
        L1e:
            r11 = r48
        L20:
            r9 = r2
        L21:
            com.megvii.lv5.c r2 = new com.megvii.lv5.c     // Catch: java.lang.Throwable -> L84
            r2.<init>()     // Catch: java.lang.Throwable -> L84
            r1.f13284b = r2     // Catch: java.lang.Throwable -> L84
            com.megvii.lv5.lib.jni.MegLiveDetector r2 = com.megvii.lv5.lib.jni.MegLiveDetector.m13440a()     // Catch: java.lang.Throwable -> L84
            int r3 = r41.ordinal()     // Catch: java.lang.Throwable -> L84
            r4 = r42
            r6 = r44
            r8 = r46
            r10 = r47
            r11 = r48
            r12 = r49
            r13 = r53
            r14 = r54
            r15 = r55
            r16 = r56
            r18 = r58
            r20 = r60
            r22 = r62
            r24 = r64
            r25 = r65
            r26 = r66
            r27 = r67
            r28 = r68
            r29 = r69
            r30 = r70
            r31 = r71
            r32 = r72
            r33 = r73
            r34 = r74
            r35 = r75
            long r2 = r2.nativeCreateHandle(r3, r4, r6, r8, r9, r10, r11, r12, r13, r14, r15, r16, r18, r20, r22, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35)     // Catch: java.lang.Throwable -> L84
            r1.f13283a = r2     // Catch: java.lang.Throwable -> L84
            int r0 = (r2 > r37 ? 1 : (r2 == r37 ? 0 : -1))
            if (r0 == 0) goto L82
            com.megvii.lv5.lib.jni.MegLiveDetector r0 = com.megvii.lv5.lib.jni.MegLiveDetector.m13440a()     // Catch: java.lang.Throwable -> L84
            long r2 = r1.f13283a     // Catch: java.lang.Throwable -> L84
            r41 = r0
            r42 = r2
            r44 = r50
            r45 = r51
            r46 = r52
            boolean r0 = r41.nativeLoadModel(r42, r44, r45, r46)     // Catch: java.lang.Throwable -> L84
            monitor-exit(r36)     // Catch: java.lang.Throwable -> L84
            return r0
        L82:
            monitor-exit(r36)     // Catch: java.lang.Throwable -> L84
            return r39
        L84:
            r0 = move-exception
            monitor-exit(r36)     // Catch: java.lang.Throwable -> L84
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.sdk.detect.MegliveModeImpl.m13161a(com.megvii.lv5.w, double, double, int, int, int, int[], byte[], byte[], byte[], java.lang.String, java.lang.String, int, long, long, long, long, float, java.lang.String, float, float, java.lang.String, int, boolean, boolean, int, float, int, int):boolean");
    }

    /* renamed from: a */
    public byte[] m13164a() {
        synchronized (MegliveModeImpl.class) {
            if (this.f13283a == 0) {
                return null;
            }
            return MegLiveDetector.m13440a().getImageBest(this.f13283a);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0020 A[Catch: all -> 0x0053, TryCatch #0 {, blocks: (B:4:0x0005, B:6:0x000d, B:7:0x0013, B:15:0x0020, B:18:0x0029, B:20:0x0033, B:21:0x0051), top: B:26:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0029 A[Catch: all -> 0x0053, TryCatch #0 {, blocks: (B:4:0x0005, B:6:0x000d, B:7:0x0013, B:15:0x0020, B:18:0x0029, B:20:0x0033, B:21:0x0051), top: B:26:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0031  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] m13159a(java.lang.String r19, boolean r20, boolean r21, java.lang.String r22, java.lang.String r23, byte[] r24, byte[] r25, byte[] r26, byte[] r27, byte[] r28, int r29, int r30) {
        /*
            r18 = this;
            r1 = r18
            java.lang.Class<com.megvii.lv5.sdk.detect.MegliveModeImpl> r2 = com.megvii.lv5.sdk.detect.MegliveModeImpl.class
            monitor-enter(r2)
            long r3 = r1.f13283a     // Catch: java.lang.Throwable -> L53
            r5 = 0
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 != 0) goto L15
            java.lang.String r0 = ""
            byte[] r0 = r0.getBytes()     // Catch: java.lang.Throwable -> L53
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L53
            return r0
        L15:
            if (r20 == 0) goto L1d
            if (r21 != 0) goto L1a
            goto L1d
        L1a:
            r0 = r24
            goto L1e
        L1d:
            r0 = 0
        L1e:
            if (r0 != 0) goto L26
            java.lang.String r0 = ""
            byte[] r0 = r0.getBytes()     // Catch: java.lang.Throwable -> L53
        L26:
            r11 = r0
            if (r25 != 0) goto L31
            java.lang.String r0 = ""
            byte[] r0 = r0.getBytes()     // Catch: java.lang.Throwable -> L53
            r12 = r0
            goto L33
        L31:
            r12 = r25
        L33:
            com.megvii.lv5.lib.jni.MegLiveDetector r3 = com.megvii.lv5.lib.jni.MegLiveDetector.m13440a()     // Catch: java.lang.Throwable -> L53
            long r4 = r1.f13283a     // Catch: java.lang.Throwable -> L53
            r6 = r19
            r7 = r20
            r8 = r21
            r9 = r22
            r10 = r23
            r13 = r26
            r14 = r27
            r15 = r28
            r16 = r29
            r17 = r30
            byte[] r0 = r3.getDelta(r4, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)     // Catch: java.lang.Throwable -> L53
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L53
            return r0
        L53:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L53
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.sdk.detect.MegliveModeImpl.m13159a(java.lang.String, boolean, boolean, java.lang.String, java.lang.String, byte[], byte[], byte[], byte[], byte[], int, int):byte[]");
    }

    /* renamed from: b */
    public String m13154b() {
        synchronized (MegliveModeImpl.class) {
            if (this.f13283a == 0) {
                return "";
            }
            return m13160a(MegLiveDetector.m13440a().getPassLivenessQualityInfoJson(this.f13283a));
        }
    }

    /* renamed from: c */
    public String m13153c() {
        synchronized (MegliveModeImpl.class) {
            if (this.f13283a == 0) {
                return "";
            }
            return m13160a(MegLiveDetector.m13440a().getPassMirrorQualityInfoJson(this.f13283a));
        }
    }

    /* renamed from: d */
    public void m13152d() {
        synchronized (MegliveModeImpl.class) {
            if (this.f13283a != 0) {
                MegLiveDetector.m13440a().nativeRelease(this.f13283a);
                this.f13283a = 0L;
            }
            C5537q c5537q = C5537q.f13180g;
            c5537q.f13186f = false;
            c5537q.f13184d = 0;
        }
    }

    /* renamed from: e */
    public void m13151e() {
        synchronized (MegliveModeImpl.class) {
            if (this.f13283a == 0) {
                return;
            }
            MegLiveDetector.m13440a().nativeResetLiveDetect(this.f13283a);
        }
    }

    /* renamed from: f */
    public void m13150f() {
        synchronized (MegliveModeImpl.class) {
            if (this.f13283a == 0) {
                return;
            }
            MegLiveDetector.m13440a().setPrivate(this.f13283a, true);
            MegLiveDetector.m13440a().nativeStartLiveDetect(this.f13283a);
        }
    }

    /* renamed from: g */
    public void m13149g() {
        synchronized (MegliveModeImpl.class) {
            if (this.f13283a == 0) {
                return;
            }
            MegLiveDetector.m13440a().nativeStopLiveDetect(this.f13283a);
        }
    }
}
