package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PlanNode implements Parcelable {
    public static final Parcelable.Creator<PlanNode> CREATOR = new C2836m();

    /* renamed from: a */
    private LatLng f6875a;

    /* renamed from: b */
    private String f6876b;

    /* renamed from: c */
    private String f6877c;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    PlanNode(LatLng latLng, String str, String str2) {
        this.f6875a = null;
        this.f6876b = null;
        this.f6877c = null;
        this.f6875a = latLng;
        this.f6876b = str;
        this.f6877c = str2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PlanNode(Parcel parcel) {
        this.f6875a = null;
        this.f6876b = null;
        this.f6877c = null;
        this.f6875a = (LatLng) parcel.readValue(LatLng.class.getClassLoader());
        this.f6876b = parcel.readString();
        this.f6877c = parcel.readString();
    }

    public LatLng getLocation() {
        return this.f6875a;
    }

    public String getCity() {
        return this.f6876b;
    }

    public String getName() {
        return this.f6877c;
    }

    public static PlanNode withLocation(LatLng latLng) {
        return new PlanNode(latLng, null, null);
    }

    public static PlanNode withCityNameAndPlaceName(String str, String str2) {
        return new PlanNode(null, str, str2);
    }

    public static PlanNode withCityCodeAndPlaceName(int i, String str) {
        return new PlanNode(null, String.valueOf(i), str);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.f6875a);
        parcel.writeString(this.f6876b);
        parcel.writeString(this.f6877c);
    }
}
