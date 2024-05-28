package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.RouteStep;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BikingRouteLine extends RouteLine<BikingStep> implements Parcelable {
    public static final Parcelable.Creator<BikingRouteLine> CREATOR = new C2824a();

    @Override // com.baidu.mapapi.search.core.RouteLine, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BikingRouteLine() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BikingRouteLine(Parcel parcel) {
        super(parcel);
    }

    @Override // com.baidu.mapapi.search.core.RouteLine, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.setType(RouteLine.TYPE.BIKINGSTEP);
        super.writeToParcel(parcel, 1);
    }

    @Override // com.baidu.mapapi.search.core.RouteLine
    public List<BikingStep> getAllStep() {
        return super.getAllStep();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class BikingStep extends RouteStep implements Parcelable {
        public static final Parcelable.Creator<BikingStep> CREATOR = new C2825b();

        /* renamed from: d */
        private int f6797d;

        /* renamed from: e */
        private RouteNode f6798e;

        /* renamed from: f */
        private RouteNode f6799f;

        /* renamed from: g */
        private String f6800g;

        /* renamed from: h */
        private String f6801h;

        /* renamed from: i */
        private String f6802i;

        /* renamed from: j */
        private String f6803j;

        /* renamed from: k */
        private String f6804k;

        @Override // com.baidu.mapapi.search.core.RouteStep, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // com.baidu.mapapi.search.core.RouteStep
        public List<LatLng> getWayPoints() {
            if (this.mWayPoints == null) {
                this.mWayPoints = m18695a(this.f6800g);
            }
            return this.mWayPoints;
        }

        /* renamed from: a */
        private List<LatLng> m18695a(String str) {
            if (str == null || str.length() == 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            String[] split = str.split(";");
            if (split == null || split.length == 0) {
                return null;
            }
            for (String str2 : split) {
                String[] split2 = str2.split(",");
                if (split2 != null && split2.length >= 2) {
                    LatLng latLng = new LatLng(Double.valueOf(split2[1]).doubleValue(), Double.valueOf(split2[0]).doubleValue());
                    if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                        latLng = CoordTrans.baiduToGcj(latLng);
                    }
                    arrayList.add(latLng);
                }
            }
            return arrayList;
        }

        public int getDirection() {
            return this.f6797d;
        }

        public void setDirection(int i) {
            this.f6797d = i;
        }

        public RouteNode getEntrance() {
            return this.f6798e;
        }

        public void setEntrance(RouteNode routeNode) {
            this.f6798e = routeNode;
        }

        public RouteNode getExit() {
            return this.f6799f;
        }

        public void setExit(RouteNode routeNode) {
            this.f6799f = routeNode;
        }

        public void setPathString(String str) {
            this.f6800g = str;
        }

        public String getEntranceInstructions() {
            return this.f6801h;
        }

        public void setEntranceInstructions(String str) {
            this.f6801h = str;
        }

        public String getExitInstructions() {
            return this.f6802i;
        }

        public void setExitInstructions(String str) {
            this.f6802i = str;
        }

        public String getInstructions() {
            return this.f6803j;
        }

        public void setInstructions(String str) {
            this.f6803j = str;
        }

        public String getTurnType() {
            return this.f6804k;
        }

        public void setTurnType(String str) {
            this.f6804k = str;
        }

        @Override // com.baidu.mapapi.search.core.RouteStep, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, 1);
            parcel.writeInt(this.f6797d);
            parcel.writeParcelable(this.f6798e, 1);
            parcel.writeParcelable(this.f6799f, 1);
            parcel.writeString(this.f6800g);
            parcel.writeString(this.f6801h);
            parcel.writeString(this.f6802i);
            parcel.writeString(this.f6803j);
            parcel.writeString(this.f6804k);
        }

        public BikingStep() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public BikingStep(Parcel parcel) {
            super(parcel);
            this.f6797d = parcel.readInt();
            this.f6798e = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            this.f6799f = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            this.f6800g = parcel.readString();
            this.f6801h = parcel.readString();
            this.f6802i = parcel.readString();
            this.f6803j = parcel.readString();
            this.f6804k = parcel.readString();
        }
    }
}
