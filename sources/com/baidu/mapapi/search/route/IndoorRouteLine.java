package com.baidu.mapapi.search.route;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.RouteStep;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@SuppressLint({"ParcelCreator"})
/* loaded from: E:\10201592_dexfile_execute.dex */
public class IndoorRouteLine extends RouteLine<IndoorRouteStep> {
    public static final Parcelable.Creator<IndoorRouteLine> CREATOR = new C2830g();

    @Override // com.baidu.mapapi.search.core.RouteLine, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IndoorRouteLine() {
        setType(RouteLine.TYPE.WALKSTEP);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public IndoorRouteLine(Parcel parcel) {
        super(parcel);
    }

    @Override // com.baidu.mapapi.search.core.RouteLine
    public List<IndoorRouteStep> getAllStep() {
        return super.getAllStep();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class IndoorRouteStep extends RouteStep {

        /* renamed from: d */
        private RouteNode f6834d;

        /* renamed from: e */
        private RouteNode f6835e;

        /* renamed from: f */
        private String f6836f;

        /* renamed from: g */
        private String f6837g;

        /* renamed from: h */
        private String f6838h;

        /* renamed from: i */
        private List<IndoorStepNode> f6839i;

        /* renamed from: j */
        private List<Double> f6840j;

        /* renamed from: a */
        private List<LatLng> m18692a(List<Double> list) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i += 2) {
                arrayList.add(new LatLng(list.get(i).doubleValue(), list.get(i + 1).doubleValue()));
            }
            return arrayList;
        }

        @Override // com.baidu.mapapi.search.core.RouteStep
        public List<LatLng> getWayPoints() {
            if (this.mWayPoints == null) {
                this.mWayPoints = m18692a(this.f6840j);
            }
            return this.mWayPoints;
        }

        public String getInstructions() {
            return this.f6836f;
        }

        public void setInstructions(String str) {
            this.f6836f = str;
        }

        public String getBuildingId() {
            return this.f6838h;
        }

        public void setBuildingId(String str) {
            this.f6838h = str;
        }

        public String getFloorId() {
            return this.f6837g;
        }

        public void setFloorId(String str) {
            this.f6837g = str;
        }

        public List<IndoorStepNode> getStepNodes() {
            return this.f6839i;
        }

        public void setStepNodes(List<IndoorStepNode> list) {
            this.f6839i = list;
        }

        public RouteNode getEntrace() {
            return this.f6834d;
        }

        public void setEntrace(RouteNode routeNode) {
            this.f6834d = routeNode;
        }

        public RouteNode getExit() {
            return this.f6835e;
        }

        public void setExit(RouteNode routeNode) {
            this.f6835e = routeNode;
        }

        public void setPath(List<Double> list) {
            this.f6840j = list;
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        public static class IndoorStepNode {

            /* renamed from: a */
            private String f6841a;

            /* renamed from: b */
            private int f6842b;

            /* renamed from: c */
            private LatLng f6843c;

            /* renamed from: d */
            private String f6844d;

            public String getName() {
                return this.f6841a;
            }

            public void setName(String str) {
                this.f6841a = str;
            }

            public int getType() {
                return this.f6842b;
            }

            public void setType(int i) {
                this.f6842b = i;
            }

            public String getDetail() {
                return this.f6844d;
            }

            public void setDetail(String str) {
                this.f6844d = str;
            }

            public LatLng getLocation() {
                return this.f6843c;
            }

            public void setLocation(LatLng latLng) {
                this.f6843c = latLng;
            }
        }
    }

    @Override // com.baidu.mapapi.search.core.RouteLine, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
