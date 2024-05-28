package com.example.asus.detectionandalign;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class LivenessConfig implements Serializable {
    private float livenessParam = 0.5f;
    private float EnfiladeParam = 0.6f;
    private boolean returnType = false;
    private boolean isTailor = true;
    private boolean isCountDown = true;
    private boolean isPreposition = true;
    private boolean isTailor4_3 = false;

    public float getEnfiladeParam() {
        return this.EnfiladeParam;
    }

    public boolean getIsCountDown() {
        return this.isCountDown;
    }

    public boolean getIsPreposition() {
        return this.isPreposition;
    }

    public boolean getIsTailor() {
        return this.isTailor;
    }

    public float getLivenessParam() {
        return this.livenessParam;
    }

    public boolean getReturnType() {
        return this.returnType;
    }

    public boolean isTailor4_3() {
        return this.isTailor4_3;
    }

    public void setEnfiladeParam(float f) {
        this.EnfiladeParam = f;
    }

    public void setIsCountDown(boolean z) {
        this.isCountDown = z;
    }

    public void setIsPreposition(boolean z) {
        this.isPreposition = z;
    }

    public void setIsTailor(boolean z) {
        this.isTailor = z;
    }

    public void setLivenessParam(float f) {
        this.livenessParam = f;
    }

    public void setReturnType(boolean z) {
        this.returnType = z;
    }

    public void setTailor4_3(boolean z) {
        this.isTailor4_3 = z;
    }
}
