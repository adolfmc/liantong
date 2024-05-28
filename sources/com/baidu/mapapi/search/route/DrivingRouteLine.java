package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.RouteStep;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DrivingRouteLine extends RouteLine<DrivingStep> implements Parcelable {
    public static final Parcelable.Creator<DrivingRouteLine> CREATOR = new C2827d();

    /* renamed from: b */
    private boolean f6807b;

    /* renamed from: c */
    private List<RouteNode> f6808c;

    /* renamed from: d */
    private int f6809d;

    /* renamed from: e */
    private int f6810e;

    /* renamed from: f */
    private int f6811f;

    @Override // com.baidu.mapapi.search.core.RouteLine, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DrivingRouteLine() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DrivingRouteLine(Parcel parcel) {
        super(parcel);
        this.f6807b = parcel.readByte() != 0;
        this.f6808c = new ArrayList();
        parcel.readList(this.f6808c, RouteNode.class.getClassLoader());
        this.f6809d = parcel.readInt();
        this.f6810e = parcel.readInt();
        this.f6811f = parcel.readInt();
    }

    @Deprecated
    public boolean isSupportTraffic() {
        return this.f6807b;
    }

    public void setSupportTraffic(boolean z) {
        this.f6807b = z;
    }

    public List<RouteNode> getWayPoints() {
        return this.f6808c;
    }

    public void setWayPoints(List<RouteNode> list) {
        this.f6808c = list;
    }

    public int getCongestionDistance() {
        return this.f6809d;
    }

    public void setCongestionDistance(int i) {
        this.f6809d = i;
    }

    public int getLightNum() {
        return this.f6810e;
    }

    public void setLightNum(int i) {
        this.f6810e = i;
    }

    public int getToll() {
        return this.f6811f;
    }

    public void setToll(int i) {
        this.f6811f = i;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class DrivingStep extends RouteStep implements Parcelable {
        public static final Parcelable.Creator<DrivingStep> CREATOR = new C2828e();

        /* renamed from: d */
        List<LatLng> f6812d;

        /* renamed from: e */
        int[] f6813e;

        /* renamed from: f */
        private int f6814f;

        /* renamed from: g */
        private RouteNode f6815g;

        /* renamed from: h */
        private RouteNode f6816h;

        /* renamed from: i */
        private String f6817i;

        /* renamed from: j */
        private String f6818j;

        /* renamed from: k */
        private String f6819k;

        /* renamed from: l */
        private String f6820l;

        /* renamed from: m */
        private int f6821m;

        /* renamed from: n */
        private int f6822n;

        /* renamed from: o */
        private String f6823o;

        @Override // com.baidu.mapapi.search.core.RouteStep, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // com.baidu.mapapi.search.core.RouteStep
        public List<LatLng> getWayPoints() {
            if (this.mWayPoints == null) {
                this.mWayPoints = CoordUtil.decodeLocationList(this.f6817i);
            }
            return this.f6812d;
        }

        public int getDirection() {
            return this.f6814f;
        }

        public void setDirection(int i) {
            this.f6814f = i;
        }

        public RouteNode getEntrance() {
            return this.f6815g;
        }

        public void setEntrance(RouteNode routeNode) {
            this.f6815g = routeNode;
        }

        public RouteNode getExit() {
            return this.f6816h;
        }

        public void setExit(RouteNode routeNode) {
            this.f6816h = routeNode;
        }

        public void setPathString(String str) {
            this.f6817i = str;
        }

        public int[] getTrafficList() {
            return this.f6813e;
        }

        public void setPathList(List<LatLng> list) {
            this.f6812d = list;
        }

        public void setTrafficList(int[] iArr) {
            this.f6813e = iArr;
        }

        public String getEntranceInstructions() {
            return this.f6818j;
        }

        public void setEntranceInstructions(String str) {
            this.f6818j = str;
        }

        public String getExitInstructions() {
            return this.f6819k;
        }

        public void setExitInstructions(String str) {
            this.f6819k = str;
        }

        public String getInstructions() {
            return this.f6820l;
        }

        public void setInstructions(String str) {
            this.f6820l = str;
        }

        public int getNumTurns() {
            return this.f6821m;
        }

        public void setNumTurns(int i) {
            this.f6821m = i;
        }

        public int getRoadLevel() {
            return this.f6822n;
        }

        public void setRoadLevel(int i) {
            this.f6822n = i;
        }

        public String getRoadName() {
            return this.f6823o;
        }

        public void setRoadName(String str) {
            this.f6823o = str;
        }

        @Override // com.baidu.mapapi.search.core.RouteStep, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f6814f);
            parcel.writeParcelable(this.f6815g, 1);
            parcel.writeParcelable(this.f6816h, 1);
            parcel.writeString(this.f6817i);
            parcel.writeString(this.f6818j);
            parcel.writeString(this.f6819k);
            parcel.writeString(this.f6820l);
            parcel.writeInt(this.f6821m);
            parcel.writeTypedList(this.f6812d);
            parcel.writeIntArray(this.f6813e);
            parcel.writeInt(this.f6822n);
            parcel.writeString(this.f6823o);
        }

        public DrivingStep() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public DrivingStep(Parcel parcel) {
            super(parcel);
            this.f6814f = parcel.readInt();
            this.f6815g = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            this.f6816h = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            this.f6817i = parcel.readString();
            this.f6818j = parcel.readString();
            this.f6819k = parcel.readString();
            this.f6820l = parcel.readString();
            this.f6821m = parcel.readInt();
            this.f6812d = parcel.createTypedArrayList(LatLng.CREATOR);
            this.f6813e = parcel.createIntArray();
            this.f6822n = parcel.readInt();
            this.f6823o = parcel.readString();
        }
    }

    @Override // com.baidu.mapapi.search.core.RouteLine, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.setType(RouteLine.TYPE.DRIVESTEP);
        super.writeToParcel(parcel, i);
        parcel.writeByte(this.f6807b ? (byte) 1 : (byte) 0);
        parcel.writeList(this.f6808c);
        parcel.writeInt(this.f6809d);
        parcel.writeInt(this.f6810e);
        parcel.writeInt(this.f6811f);
    }
}
