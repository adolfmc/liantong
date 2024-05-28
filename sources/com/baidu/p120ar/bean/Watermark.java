package com.baidu.p120ar.bean;

import android.graphics.Bitmap;
import android.graphics.Point;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.bean.Watermark */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Watermark {
    private Bitmap mBitmap;
    private String mFilePath;
    private float[] mRenderRect;
    private Point mStartPoint;
    private StorageType mStorageType = StorageType.SDCARD;
    private CoordinateType mCoordinateType = CoordinateType.LEFT_BOTTOM;
    private RotationType mRotationType = RotationType.ROTATE_0;
    private float mScale = 1.0f;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.bean.Watermark$CoordinateType */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum CoordinateType {
        LEFT_TOP,
        LEFT_BOTTOM,
        RIGHT_TOP,
        RIGHT_BOTTOM
    }

    public Watermark(Bitmap bitmap, Point point) {
        this.mBitmap = bitmap;
        this.mStartPoint = point;
    }

    public Watermark(String str, Point point) {
        this.mFilePath = str;
        this.mStartPoint = point;
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }

    public String getFilePath() {
        return this.mFilePath;
    }

    public void setFilePath(String str) {
        this.mFilePath = str;
    }

    public StorageType getStorageType() {
        return this.mStorageType;
    }

    public void setStorageType(StorageType storageType) {
        this.mStorageType = storageType;
    }

    public Point getStartPoint() {
        return this.mStartPoint;
    }

    public void setStartPoint(Point point) {
        this.mStartPoint = point;
    }

    public CoordinateType getCoordinateType() {
        return this.mCoordinateType;
    }

    public void setCoordinateType(CoordinateType coordinateType) {
        this.mCoordinateType = coordinateType;
    }

    public RotationType getRotationType() {
        return this.mRotationType;
    }

    public void setRotationType(RotationType rotationType) {
        this.mRotationType = rotationType;
    }

    public float getScale() {
        return this.mScale;
    }

    public void setScale(float f) {
        this.mScale = f;
    }

    public float[] getRenderRect() {
        return this.mRenderRect;
    }

    public void setRenderRect(float[] fArr) {
        this.mRenderRect = fArr;
    }
}
