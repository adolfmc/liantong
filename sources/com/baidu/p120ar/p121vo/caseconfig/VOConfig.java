package com.baidu.p120ar.p121vo.caseconfig;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.vo.caseconfig.VOConfig */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VOConfig {

    /* renamed from: id */
    private String f4096id;
    private int pitchAngle;
    private int placeType;
    private String position;
    private String rotation;
    private int distance = 1000;
    private boolean mImmediatelyPlaceModel = true;

    public String getId() {
        return this.f4096id;
    }

    public void setId(String str) {
        this.f4096id = str;
    }

    public int getPlaceType() {
        return this.placeType;
    }

    public void setPlaceType(int i) {
        this.placeType = i;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String str) {
        this.position = str;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public int getPitchAngle() {
        return this.pitchAngle;
    }

    public void setPitchAngle(int i) {
        this.pitchAngle = i;
    }

    public String getRotation() {
        return this.rotation;
    }

    public void setRotation(String str) {
        this.rotation = str;
    }

    public boolean isImmediatelyPlaceModel() {
        return this.mImmediatelyPlaceModel;
    }

    public void setImmediatelyPlaceModel(boolean z) {
        this.mImmediatelyPlaceModel = z;
    }

    public String toString() {
        return "SlamModel{id='" + this.f4096id + "', placeType=" + this.placeType + ", position='" + this.position + "', distance=" + this.distance + ", pitchAngle=" + this.pitchAngle + ", rotation='" + this.rotation + "', mImmediatelyPlaceModel=" + this.mImmediatelyPlaceModel + '}';
    }
}
