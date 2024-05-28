package com.baidu.cloud.mediaprocess.utils;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FPSController {

    /* renamed from: a */
    public static boolean f4845a = true;

    /* renamed from: c */
    public int f4847c;

    /* renamed from: b */
    public boolean f4846b = false;

    /* renamed from: d */
    public int f4848d = 0;

    /* renamed from: e */
    public long f4849e = 0;

    public FPSController(int i) {
        this.f4847c = 33;
        if (i > 0) {
            this.f4847c = 1000 / i;
        }
    }

    public boolean needDraw() {
        if (this.f4846b) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.f4849e == 0) {
                this.f4849e = currentTimeMillis;
            }
            long j = currentTimeMillis % 1000;
            if (currentTimeMillis / 1000 != this.f4849e / 1000) {
                this.f4849e = currentTimeMillis;
                this.f4848d = 0;
            }
            int i = this.f4848d;
            if (this.f4847c * i >= j) {
                if (f4845a) {
                    Log.d("FPSController", "drop frame TargetFrameSeq: " + this.f4848d + " Offset:" + j);
                }
                return false;
            }
            this.f4848d = i + 1;
            if (f4845a) {
                Log.d("FPSController", "need draw frame TargetFrameSeq: " + this.f4848d + " Offset:" + j);
            }
            return true;
        }
        return true;
    }

    public void setIsFpsControlEnabled(boolean z) {
        this.f4846b = z;
    }
}
