package com.sinovatech.unicom.separatemodule.anquanzhongxin;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Entity
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AnquanAuthCodeEntity {
    private boolean authCanUsed;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18457id;
    private boolean isUserChecked;
    private int gesture = 1;
    private int touchId = 1;
    private int faceId = 2;

    public long getId() {
        return this.f18457id;
    }

    public void setId(long j) {
        this.f18457id = j;
    }

    public int getGesture() {
        return this.gesture;
    }

    public void setGesture(int i) {
        this.gesture = i;
    }

    public int getTouchId() {
        return this.touchId;
    }

    public void setTouchId(int i) {
        this.touchId = i;
    }

    public int getFaceId() {
        return this.faceId;
    }

    public void setFaceId(int i) {
        this.faceId = i;
    }

    public boolean isUserChecked() {
        return this.isUserChecked;
    }

    public void setUserChecked(boolean z) {
        this.isUserChecked = z;
    }

    public boolean isAuthCanUsed() {
        return this.authCanUsed;
    }

    public void setAuthCanUsed(boolean z) {
        this.authCanUsed = z;
    }
}
