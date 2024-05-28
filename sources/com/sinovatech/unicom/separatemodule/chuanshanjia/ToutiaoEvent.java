package com.sinovatech.unicom.separatemodule.chuanshanjia;

import com.sinovatech.unicom.common.EventMessage;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ToutiaoEvent extends EventMessage {
    private String action;
    private int bannerHeight;
    private int bannerWidth;
    private int height;
    private int modelType;
    private String position;
    private double scale;

    public ToutiaoEvent(int i, Object obj) {
        super(i, obj);
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String str) {
        this.position = str;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public int getBannerWidth() {
        return this.bannerWidth;
    }

    public void setBannerWidth(int i) {
        this.bannerWidth = i;
    }

    public int getBannerHeight() {
        return this.bannerHeight;
    }

    public void setBannerHeight(int i) {
        this.bannerHeight = i;
    }

    public double getScale() {
        return this.scale;
    }

    public void setScale(double d) {
        this.scale = d;
    }

    public int getModelType() {
        return this.modelType;
    }

    public void setModelType(int i) {
        this.modelType = i;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String str) {
        this.action = str;
    }
}
