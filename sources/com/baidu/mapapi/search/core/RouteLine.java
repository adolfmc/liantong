package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.RouteStep;
import com.baidu.mapapi.search.route.BikingRouteLine;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RouteLine<T extends RouteStep> implements Parcelable {

    /* renamed from: a */
    TYPE f6696a;

    /* renamed from: b */
    private RouteNode f6697b;

    /* renamed from: c */
    private RouteNode f6698c;

    /* renamed from: d */
    private String f6699d;

    /* renamed from: e */
    private List<T> f6700e;

    /* renamed from: f */
    private int f6701f;

    /* renamed from: g */
    private int f6702g;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum TYPE {
        DRIVESTEP(0),
        TRANSITSTEP(1),
        WALKSTEP(2),
        BIKINGSTEP(3);
        

        /* renamed from: a */
        private int f6704a;

        TYPE(int i) {
            this.f6704a = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public int m18758a() {
            return this.f6704a;
        }
    }

    protected TYPE getType() {
        return this.f6696a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setType(TYPE type) {
        this.f6696a = type;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public RouteLine() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public RouteLine(Parcel parcel) {
        int readInt = parcel.readInt();
        this.f6697b = (RouteNode) parcel.readValue(RouteNode.class.getClassLoader());
        this.f6698c = (RouteNode) parcel.readValue(RouteNode.class.getClassLoader());
        this.f6699d = parcel.readString();
        switch (readInt) {
            case 0:
                this.f6700e = parcel.createTypedArrayList(DrivingRouteLine.DrivingStep.CREATOR);
                break;
            case 1:
                this.f6700e = parcel.createTypedArrayList(TransitRouteLine.TransitStep.CREATOR);
                break;
            case 2:
                this.f6700e = parcel.createTypedArrayList(WalkingRouteLine.WalkingStep.CREATOR);
                break;
            case 3:
                this.f6700e = parcel.createTypedArrayList(BikingRouteLine.BikingStep.CREATOR);
                break;
        }
        this.f6701f = parcel.readInt();
        this.f6702g = parcel.readInt();
    }

    public int getDistance() {
        return this.f6701f;
    }

    public void setDistance(int i) {
        this.f6701f = i;
    }

    public int getDuration() {
        return this.f6702g;
    }

    public void setDuration(int i) {
        this.f6702g = i;
    }

    public RouteNode getStarting() {
        return this.f6697b;
    }

    public void setStarting(RouteNode routeNode) {
        this.f6697b = routeNode;
    }

    public RouteNode getTerminal() {
        return this.f6698c;
    }

    public void setTerminal(RouteNode routeNode) {
        this.f6698c = routeNode;
    }

    public String getTitle() {
        return this.f6699d;
    }

    public void setTitle(String str) {
        this.f6699d = str;
    }

    public void setSteps(List<T> list) {
        this.f6700e = list;
    }

    public List<T> getAllStep() {
        return this.f6700e;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        TYPE type = this.f6696a;
        if (type != null) {
            parcel.writeInt(type.m18758a());
        } else {
            parcel.writeInt(10);
        }
        parcel.writeValue(this.f6697b);
        parcel.writeValue(this.f6698c);
        parcel.writeString(this.f6699d);
        if (this.f6696a != null) {
            parcel.writeTypedList(this.f6700e);
        }
        parcel.writeInt(this.f6701f);
        parcel.writeInt(this.f6702g);
    }
}
