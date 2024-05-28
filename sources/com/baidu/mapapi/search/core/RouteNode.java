package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RouteNode implements Parcelable {
    public static final Parcelable.Creator<RouteNode> CREATOR = new C2804l();

    /* renamed from: a */
    private String f6705a;

    /* renamed from: b */
    private LatLng f6706b;

    /* renamed from: c */
    private String f6707c;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RouteNode() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public RouteNode(Parcel parcel) {
        this.f6705a = parcel.readString();
        this.f6706b = (LatLng) parcel.readValue(LatLng.class.getClassLoader());
        this.f6707c = parcel.readString();
    }

    public static RouteNode titleAndLocation(String str, LatLng latLng) {
        RouteNode routeNode = new RouteNode();
        routeNode.setTitle(str);
        routeNode.setLocation(latLng);
        return routeNode;
    }

    public static RouteNode location(LatLng latLng) {
        RouteNode routeNode = new RouteNode();
        routeNode.setLocation(latLng);
        return routeNode;
    }

    public String getTitle() {
        return this.f6705a;
    }

    public void setTitle(String str) {
        this.f6705a = str;
    }

    public LatLng getLocation() {
        return this.f6706b;
    }

    public void setLocation(LatLng latLng) {
        this.f6706b = latLng;
    }

    public String getUid() {
        return this.f6707c;
    }

    public void setUid(String str) {
        this.f6707c = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f6705a);
        parcel.writeValue(this.f6706b);
        parcel.writeString(this.f6707c);
    }
}
