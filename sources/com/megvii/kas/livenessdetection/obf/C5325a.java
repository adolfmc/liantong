package com.megvii.kas.livenessdetection.obf;

import com.example.asus.detectionandalign.animation.C4280b;
import com.megvii.kas.livenessdetection.Detector;

/* renamed from: com.megvii.kas.livenessdetection.obf.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C5325a {

    /* renamed from: b */
    private long f12339b = -1;

    /* renamed from: a */
    private StringBuilder f12338a = new StringBuilder();

    /* renamed from: a */
    public final synchronized void m13649a(Detector.DetectionType detectionType) {
        if (detectionType == null) {
            return;
        }
        m13648b();
        StringBuilder sb = this.f12338a;
        String str = "";
        switch (detectionType) {
            case NONE:
                str = "N";
                break;
            case DONE:
                str = "O";
                break;
            case BLINK:
                str = "E";
                break;
            case MOUTH:
                str = "M";
                break;
            case POS_YAW:
                str = "Y";
                break;
            case POS_YAW_LEFT:
                str = "L";
                break;
            case POS_YAW_RIGHT:
                str = "R";
                break;
            case POS_PITCH:
                str = "P";
                break;
            case POS_PITCH_UP:
                str = "U";
                break;
            case POS_PITCH_DOWN:
                str = "D";
                break;
            case AIMLESS:
                str = "A";
                break;
        }
        sb.append(str);
        this.f12338a.append("\n");
    }

    /* renamed from: a */
    public final synchronized void m13650a(Detector.DetectionFailedType detectionFailedType) {
        if (detectionFailedType == null) {
            return;
        }
        m13648b();
        StringBuilder sb = this.f12338a;
        String str = "";
        switch (detectionFailedType) {
            case NOTVIDEO:
                str = "n";
                break;
            case ACTIONBLEND:
                str = C4280b.f10047a;
                break;
            case TIMEOUT:
                str = "t";
                break;
            case MASK:
                str = "m";
                break;
            case TOOMANYFACELOST:
                str = "o";
                break;
            case FACELOSTNOTCONTINUOUS:
                str = "l";
                break;
            case FACENOTCONTINUOUS:
                str = "c";
                break;
        }
        sb.append(str);
        this.f12338a.append("\n");
    }

    /* renamed from: b */
    private void m13648b() {
        if (this.f12339b == -1) {
            this.f12339b = System.currentTimeMillis();
            this.f12338a.append(System.currentTimeMillis() / 1000);
            this.f12338a.append("\n");
        }
        this.f12338a.append(System.currentTimeMillis() - this.f12339b);
        this.f12338a.append(":");
    }

    /* renamed from: a */
    public final synchronized void m13651a() {
        this.f12338a = new StringBuilder();
        this.f12339b = -1L;
    }

    public final synchronized String toString() {
        return this.f12338a.toString();
    }
}
