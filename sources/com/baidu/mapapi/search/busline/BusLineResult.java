package com.baidu.mapapi.search.busline;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.RouteStep;
import com.baidu.mapapi.search.core.SearchResult;
import java.util.Date;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BusLineResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<BusLineResult> CREATOR = new C2792a();

    /* renamed from: a */
    private String f6640a;

    /* renamed from: b */
    private String f6641b;

    /* renamed from: c */
    private boolean f6642c;

    /* renamed from: d */
    private Date f6643d;

    /* renamed from: e */
    private Date f6644e;

    /* renamed from: f */
    private String f6645f;

    /* renamed from: g */
    private List<BusStation> f6646g;

    /* renamed from: h */
    private List<BusStep> f6647h;

    /* renamed from: i */
    private float f6648i;

    /* renamed from: j */
    private float f6649j;

    /* renamed from: k */
    private String f6650k;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class BusStation extends RouteNode {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class BusStep extends RouteStep {
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getLineDirection() {
        return this.f6650k;
    }

    public void setLineDirection(String str) {
        this.f6650k = str;
    }

    public float getBasePrice() {
        return this.f6648i;
    }

    public void setBasePrice(float f) {
        this.f6648i = f;
    }

    public float getMaxPrice() {
        return this.f6649j;
    }

    public void setMaxPrice(float f) {
        this.f6649j = f;
    }

    public String getBusCompany() {
        return this.f6640a;
    }

    public String getBusLineName() {
        return this.f6641b;
    }

    public void setBusLineName(String str) {
        this.f6641b = str;
    }

    public boolean isMonthTicket() {
        return this.f6642c;
    }

    public void setMonthTicket(boolean z) {
        this.f6642c = z;
    }

    public Date getStartTime() {
        return this.f6643d;
    }

    public void setStartTime(Date date) {
        this.f6643d = date;
    }

    public Date getEndTime() {
        return this.f6644e;
    }

    public void setEndTime(Date date) {
        this.f6644e = date;
    }

    public List<BusStation> getStations() {
        return this.f6646g;
    }

    public void setStations(List<BusStation> list) {
        this.f6646g = list;
    }

    public List<BusStep> getSteps() {
        return this.f6647h;
    }

    public void setSteps(List<BusStep> list) {
        this.f6647h = list;
    }

    public String getUid() {
        return this.f6645f;
    }

    public void setUid(String str) {
        this.f6645f = str;
    }

    public BusLineResult() {
        this.f6640a = null;
        this.f6641b = null;
        this.f6646g = null;
        this.f6647h = null;
        this.f6650k = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BusLineResult(Parcel parcel) {
        this.f6640a = null;
        this.f6641b = null;
        this.f6646g = null;
        this.f6647h = null;
        this.f6650k = null;
        this.f6640a = parcel.readString();
        this.f6641b = parcel.readString();
        this.f6642c = ((Boolean) parcel.readValue(Boolean.class.getClassLoader())).booleanValue();
        this.f6643d = (Date) parcel.readValue(Date.class.getClassLoader());
        this.f6644e = (Date) parcel.readValue(Date.class.getClassLoader());
        this.f6645f = parcel.readString();
        this.f6646g = parcel.readArrayList(BusStation.class.getClassLoader());
        this.f6647h = parcel.readArrayList(RouteStep.class.getClassLoader());
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f6640a);
        parcel.writeString(this.f6641b);
        parcel.writeValue(Boolean.valueOf(this.f6642c));
        parcel.writeValue(this.f6643d);
        parcel.writeValue(this.f6644e);
        parcel.writeString(this.f6645f);
        parcel.writeList(this.f6646g);
        parcel.writeList(this.f6647h);
    }
}
