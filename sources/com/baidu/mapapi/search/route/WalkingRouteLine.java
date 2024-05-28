package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.RouteStep;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WalkingRouteLine extends RouteLine<WalkingStep> implements Parcelable {
    public static final Parcelable.Creator<WalkingRouteLine> CREATOR = new C2841r();

    @Override // com.baidu.mapapi.search.core.RouteLine, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WalkingRouteLine() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public WalkingRouteLine(Parcel parcel) {
        super(parcel);
    }

    @Override // com.baidu.mapapi.search.core.RouteLine, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.setType(RouteLine.TYPE.WALKSTEP);
        super.writeToParcel(parcel, 1);
    }

    @Override // com.baidu.mapapi.search.core.RouteLine
    public List<WalkingStep> getAllStep() {
        return super.getAllStep();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class WalkingStep extends RouteStep implements Parcelable {
        public static final Parcelable.Creator<WalkingStep> CREATOR = new C2842s();

        /* renamed from: d */
        private int f6898d;

        /* renamed from: e */
        private RouteNode f6899e;

        /* renamed from: f */
        private RouteNode f6900f;

        /* renamed from: g */
        private String f6901g;

        /* renamed from: h */
        private String f6902h;

        /* renamed from: i */
        private String f6903i;

        /* renamed from: j */
        private String f6904j;

        @Override // com.baidu.mapapi.search.core.RouteStep, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // com.baidu.mapapi.search.core.RouteStep
        public List<LatLng> getWayPoints() {
            if (this.mWayPoints == null) {
                this.mWayPoints = CoordUtil.decodeLocationList(this.f6901g);
            }
            return this.mWayPoints;
        }

        public int getDirection() {
            return this.f6898d;
        }

        public void setDirection(int i) {
            this.f6898d = i;
        }

        public RouteNode getEntrance() {
            return this.f6899e;
        }

        public void setEntrance(RouteNode routeNode) {
            this.f6899e = routeNode;
        }

        public RouteNode getExit() {
            return this.f6900f;
        }

        public void setExit(RouteNode routeNode) {
            this.f6900f = routeNode;
        }

        public void setPathString(String str) {
            this.f6901g = str;
        }

        public String getEntranceInstructions() {
            return this.f6902h;
        }

        public void setEntranceInstructions(String str) {
            this.f6902h = str;
        }

        public String getExitInstructions() {
            return this.f6903i;
        }

        public void setExitInstructions(String str) {
            this.f6903i = str;
        }

        public String getInstructions() {
            return this.f6904j;
        }

        public void setInstructions(String str) {
            this.f6904j = str;
        }

        @Override // com.baidu.mapapi.search.core.RouteStep, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, 1);
            parcel.writeInt(this.f6898d);
            parcel.writeParcelable(this.f6899e, 1);
            parcel.writeParcelable(this.f6900f, 1);
            parcel.writeString(this.f6901g);
            parcel.writeString(this.f6902h);
            parcel.writeString(this.f6903i);
            parcel.writeString(this.f6904j);
        }

        public WalkingStep() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public WalkingStep(Parcel parcel) {
            super(parcel);
            this.f6898d = parcel.readInt();
            this.f6899e = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            this.f6900f = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            this.f6901g = parcel.readString();
            this.f6902h = parcel.readString();
            this.f6903i = parcel.readString();
            this.f6904j = parcel.readString();
        }
    }
}
