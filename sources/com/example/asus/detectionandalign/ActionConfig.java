package com.example.asus.detectionandalign;

import java.io.Serializable;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ActionConfig implements Serializable {
    private List<Double> actionThresholdList;
    private List<String> fixActionList;
    private float livenessParam = 0.5f;
    private float EnfiladeParam = 0.6f;
    private boolean isFixAction = false;
    private boolean isActionLivenessCheck = false;
    private boolean isVerifyAnimation = false;
    private boolean isTailor = true;
    private boolean isPreposition = true;
    private boolean isTailor4_3 = false;

    public List<Double> getActionThresholdList() {
        return this.actionThresholdList;
    }

    public float getEnfiladeParam() {
        return this.EnfiladeParam;
    }

    public List<String> getFixActionList() {
        return this.fixActionList;
    }

    public boolean getIsActionLivenessCheck() {
        return this.isActionLivenessCheck;
    }

    public boolean getIsFixAction() {
        return this.isFixAction;
    }

    public boolean getIsPreposition() {
        return this.isPreposition;
    }

    public boolean getIsTailor() {
        return this.isTailor;
    }

    public boolean getIsVerifyAnimation() {
        return this.isVerifyAnimation;
    }

    public float getLivenessParam() {
        return this.livenessParam;
    }

    public boolean isTailor4_3() {
        return this.isTailor4_3;
    }

    public void setActionThresholdList(List<Double> list) {
        this.actionThresholdList = list;
    }

    public void setEnfiladeParam(float f) {
        this.EnfiladeParam = f;
    }

    public void setFixActionList(List<String> list) {
        this.fixActionList = list;
    }

    public void setIsActionLivenessCheck(boolean z) {
        this.isActionLivenessCheck = z;
    }

    public void setIsFixAction(boolean z) {
        this.isFixAction = z;
    }

    public void setIsPreposition(boolean z) {
        this.isPreposition = z;
    }

    public void setIsTailor(boolean z) {
        this.isTailor = z;
    }

    public void setIsVerifyAnimation(boolean z) {
        this.isVerifyAnimation = z;
    }

    public void setLivenessParam(float f) {
        this.livenessParam = f;
    }

    public void setTailor4_3(boolean z) {
        this.isTailor4_3 = z;
    }
}
