package com.baidu.platform.comapi.map;

import android.graphics.Bitmap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class OverlayLocationData {

    /* renamed from: a */
    private String f7722a;

    /* renamed from: b */
    private Bitmap f7723b;

    /* renamed from: c */
    private int f7724c;

    /* renamed from: d */
    private int f7725d;

    /* renamed from: e */
    private int f7726e;

    public Bitmap getImage() {
        return this.f7723b;
    }

    public int getImgHeight() {
        return this.f7725d;
    }

    public String getImgName() {
        return this.f7722a;
    }

    public int getImgWidth() {
        return this.f7724c;
    }

    public int isRotation() {
        return this.f7726e;
    }

    public void setImage(Bitmap bitmap) {
        this.f7723b = bitmap;
    }

    public void setImgHeight(int i) {
        this.f7725d = i;
    }

    public void setImgName(String str) {
        this.f7722a = str;
    }

    public void setImgWidth(int i) {
        this.f7724c = i;
    }

    public void setRotation(int i) {
        this.f7726e = i;
    }
}
