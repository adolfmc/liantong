package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BuildingInfo implements Parcelable {
    public static final Parcelable.Creator<BuildingInfo> CREATOR = new C2793a();

    /* renamed from: a */
    private String f6653a;

    /* renamed from: b */
    private int f6654b;

    /* renamed from: c */
    private float f6655c;

    /* renamed from: d */
    private int f6656d;

    /* renamed from: e */
    private String f6657e;

    /* renamed from: f */
    private String f6658f;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BuildingInfo() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BuildingInfo(Parcel parcel) {
        this.f6655c = parcel.readFloat();
        this.f6656d = parcel.readInt();
        this.f6657e = parcel.readString();
        this.f6658f = parcel.readString();
    }

    public String getStructID() {
        return this.f6653a;
    }

    /* renamed from: a */
    public void m18762a(String str) {
        this.f6653a = str;
    }

    public int getLabel() {
        return this.f6654b;
    }

    /* renamed from: a */
    public void m18763a(int i) {
        this.f6654b = i;
    }

    public void setHeight(float f) {
        this.f6655c = f;
    }

    /* renamed from: b */
    public void m18761b(int i) {
        this.f6656d = i;
    }

    /* renamed from: b */
    public void m18760b(String str) {
        this.f6657e = str;
    }

    /* renamed from: c */
    public void m18759c(String str) {
        this.f6658f = str;
    }

    public float getHeight() {
        return this.f6655c;
    }

    public int getAccuracy() {
        return this.f6656d;
    }

    public String getGeom() {
        return this.f6657e;
    }

    public String getCenter() {
        return this.f6658f;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("BuidingInfo: \n");
        stringBuffer.append("; height = ");
        stringBuffer.append(this.f6655c);
        stringBuffer.append("; accuracy = ");
        stringBuffer.append(this.f6656d);
        stringBuffer.append("; geom = ");
        stringBuffer.append(this.f6657e);
        stringBuffer.append("; center = ");
        stringBuffer.append(this.f6658f);
        return stringBuffer.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f6655c);
        parcel.writeInt(this.f6656d);
        parcel.writeString(this.f6657e);
        parcel.writeString(this.f6658f);
    }
}
