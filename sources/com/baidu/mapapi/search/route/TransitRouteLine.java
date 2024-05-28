package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.RouteStep;
import com.baidu.mapapi.search.core.TaxiInfo;
import com.baidu.mapapi.search.core.VehicleInfo;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class TransitRouteLine extends RouteLine<TransitStep> implements Parcelable {
    public static final Parcelable.Creator<TransitRouteLine> CREATOR = new C2838o();

    /* renamed from: b */
    private TaxiInfo f6886b;

    @Override // com.baidu.mapapi.search.core.RouteLine, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TransitRouteLine() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TransitRouteLine(Parcel parcel) {
        super(parcel);
        this.f6886b = (TaxiInfo) parcel.readParcelable(TaxiInfo.class.getClassLoader());
    }

    @Deprecated
    public TaxiInfo getTaxitInfo() {
        return this.f6886b;
    }

    public void setTaxitInfo(TaxiInfo taxiInfo) {
        this.f6886b = taxiInfo;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class TransitStep extends RouteStep implements Parcelable {
        public static final Parcelable.Creator<TransitStep> CREATOR = new C2839p();

        /* renamed from: d */
        private VehicleInfo f6887d;

        /* renamed from: e */
        private RouteNode f6888e;

        /* renamed from: f */
        private RouteNode f6889f;

        /* renamed from: g */
        private TransitRouteStepType f6890g;

        /* renamed from: h */
        private String f6891h;

        /* renamed from: i */
        private String f6892i;

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        public enum TransitRouteStepType {
            BUSLINE,
            SUBWAY,
            WAKLING
        }

        @Override // com.baidu.mapapi.search.core.RouteStep, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String getInstructions() {
            return this.f6891h;
        }

        public void setVehicleInfo(VehicleInfo vehicleInfo) {
            this.f6887d = vehicleInfo;
        }

        public void setInstructions(String str) {
            this.f6891h = str;
        }

        public TransitRouteStepType getStepType() {
            return this.f6890g;
        }

        public void setStepType(TransitRouteStepType transitRouteStepType) {
            this.f6890g = transitRouteStepType;
        }

        public void setPathString(String str) {
            this.f6892i = str;
        }

        @Override // com.baidu.mapapi.search.core.RouteStep
        public List<LatLng> getWayPoints() {
            if (this.mWayPoints == null) {
                this.mWayPoints = CoordUtil.decodeLocationList(this.f6892i);
            }
            return this.mWayPoints;
        }

        public RouteNode getEntrance() {
            return this.f6888e;
        }

        public void setEntrace(RouteNode routeNode) {
            this.f6888e = routeNode;
        }

        public RouteNode getExit() {
            return this.f6889f;
        }

        public void setExit(RouteNode routeNode) {
            this.f6889f = routeNode;
        }

        public VehicleInfo getVehicleInfo() {
            return this.f6887d;
        }

        @Override // com.baidu.mapapi.search.core.RouteStep, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.f6887d, 1);
            parcel.writeParcelable(this.f6888e, 1);
            parcel.writeParcelable(this.f6889f, 1);
            TransitRouteStepType transitRouteStepType = this.f6890g;
            parcel.writeInt(transitRouteStepType == null ? -1 : transitRouteStepType.ordinal());
            parcel.writeString(this.f6891h);
            parcel.writeString(this.f6892i);
        }

        public TransitStep() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public TransitStep(Parcel parcel) {
            super(parcel);
            this.f6887d = (VehicleInfo) parcel.readParcelable(VehicleInfo.class.getClassLoader());
            this.f6888e = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            this.f6889f = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            int readInt = parcel.readInt();
            this.f6890g = readInt == -1 ? null : TransitRouteStepType.values()[readInt];
            this.f6891h = parcel.readString();
            this.f6892i = parcel.readString();
        }
    }

    @Override // com.baidu.mapapi.search.core.RouteLine, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.setType(RouteLine.TYPE.TRANSITSTEP);
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.f6886b, 1);
    }
}
