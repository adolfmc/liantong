package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.BusInfo;
import com.baidu.mapapi.search.core.CoachInfo;
import com.baidu.mapapi.search.core.PlaneInfo;
import com.baidu.mapapi.search.core.PriceInfo;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.RouteStep;
import com.baidu.mapapi.search.core.TrainInfo;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class MassTransitRouteLine extends RouteLine<TransitStep> implements Parcelable {
    public static final Parcelable.Creator<MassTransitRouteLine> CREATOR = new C2832i();

    /* renamed from: b */
    private String f6846b;

    /* renamed from: c */
    private double f6847c;

    /* renamed from: d */
    private List<PriceInfo> f6848d;

    /* renamed from: e */
    private List<List<TransitStep>> f6849e;

    @Override // com.baidu.mapapi.search.core.RouteLine, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MassTransitRouteLine() {
        this.f6849e = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MassTransitRouteLine(Parcel parcel) {
        super(parcel);
        this.f6849e = null;
        int readInt = parcel.readInt();
        this.f6846b = parcel.readString();
        this.f6847c = parcel.readDouble();
        this.f6848d = parcel.createTypedArrayList(PriceInfo.CREATOR);
        if (readInt > 0) {
            this.f6849e = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                this.f6849e.add(parcel.createTypedArrayList(TransitStep.CREATOR));
            }
        }
    }

    @Override // com.baidu.mapapi.search.core.RouteLine, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        List<List<TransitStep>> list = this.f6849e;
        parcel.writeInt(list == null ? 0 : list.size());
        parcel.writeString(this.f6846b);
        parcel.writeDouble(this.f6847c);
        parcel.writeTypedList(this.f6848d);
        for (List<TransitStep> list2 : this.f6849e) {
            parcel.writeTypedList(list2);
        }
    }

    public List<List<TransitStep>> getNewSteps() {
        return this.f6849e;
    }

    public void setNewSteps(List<List<TransitStep>> list) {
        this.f6849e = list;
    }

    public String getArriveTime() {
        return this.f6846b;
    }

    public void setArriveTime(String str) {
        this.f6846b = str;
    }

    public double getPrice() {
        return this.f6847c;
    }

    public void setPrice(double d) {
        this.f6847c = d;
    }

    public List<PriceInfo> getPriceInfo() {
        return this.f6848d;
    }

    public void setPriceInfo(List<PriceInfo> list) {
        this.f6848d = list;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class TransitStep extends RouteStep implements Parcelable {
        public static final Parcelable.Creator<TransitStep> CREATOR = new C2833j();

        /* renamed from: d */
        private List<TrafficCondition> f6850d;

        /* renamed from: e */
        private LatLng f6851e;

        /* renamed from: f */
        private LatLng f6852f;

        /* renamed from: g */
        private TrainInfo f6853g;

        /* renamed from: h */
        private PlaneInfo f6854h;

        /* renamed from: i */
        private CoachInfo f6855i;

        /* renamed from: j */
        private BusInfo f6856j;

        /* renamed from: k */
        private StepVehicleInfoType f6857k;

        /* renamed from: l */
        private String f6858l;

        /* renamed from: m */
        private String f6859m;

        @Override // com.baidu.mapapi.search.core.RouteStep, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        public static class TrafficCondition implements Parcelable {
            public static final Parcelable.Creator<TrafficCondition> CREATOR = new C2834k();

            /* renamed from: a */
            private int f6861a;

            /* renamed from: b */
            private int f6862b;

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public TrafficCondition() {
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public TrafficCondition(Parcel parcel) {
                this.f6861a = parcel.readInt();
                this.f6862b = parcel.readInt();
            }

            public int getTrafficStatus() {
                return this.f6861a;
            }

            public void setTrafficStatus(int i) {
                this.f6861a = i;
            }

            public int getTrafficGeoCnt() {
                return this.f6862b;
            }

            public void setTrafficGeoCnt(int i) {
                this.f6862b = i;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.f6861a);
                parcel.writeInt(this.f6862b);
            }
        }

        public TransitStep() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public TransitStep(Parcel parcel) {
            super(parcel);
            this.f6850d = parcel.createTypedArrayList(TrafficCondition.CREATOR);
            this.f6851e = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
            this.f6852f = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
            this.f6853g = (TrainInfo) parcel.readParcelable(TrainInfo.class.getClassLoader());
            this.f6854h = (PlaneInfo) parcel.readParcelable(PlaneInfo.class.getClassLoader());
            this.f6855i = (CoachInfo) parcel.readParcelable(CoachInfo.class.getClassLoader());
            this.f6856j = (BusInfo) parcel.readParcelable(BusInfo.class.getClassLoader());
            switch (parcel.readInt()) {
                case 1:
                    this.f6857k = StepVehicleInfoType.ESTEP_TRAIN;
                    break;
                case 2:
                    this.f6857k = StepVehicleInfoType.ESTEP_PLANE;
                    break;
                case 3:
                    this.f6857k = StepVehicleInfoType.ESTEP_BUS;
                    break;
                case 4:
                    this.f6857k = StepVehicleInfoType.ESTEP_DRIVING;
                    break;
                case 5:
                    this.f6857k = StepVehicleInfoType.ESTEP_WALK;
                    break;
                case 6:
                    this.f6857k = StepVehicleInfoType.ESTEP_COACH;
                    break;
            }
            this.f6858l = parcel.readString();
            this.f6859m = parcel.readString();
        }

        @Override // com.baidu.mapapi.search.core.RouteStep, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeTypedList(this.f6850d);
            parcel.writeParcelable(this.f6851e, i);
            parcel.writeParcelable(this.f6852f, i);
            parcel.writeParcelable(this.f6853g, i);
            parcel.writeParcelable(this.f6854h, i);
            parcel.writeParcelable(this.f6855i, i);
            parcel.writeParcelable(this.f6856j, i);
            parcel.writeInt(this.f6857k.getInt());
            parcel.writeString(this.f6858l);
            parcel.writeString(this.f6859m);
        }

        public List<TrafficCondition> getTrafficConditions() {
            return this.f6850d;
        }

        public void setTrafficConditions(List<TrafficCondition> list) {
            this.f6850d = list;
        }

        public LatLng getStartLocation() {
            return this.f6851e;
        }

        public void setStartLocation(LatLng latLng) {
            this.f6851e = latLng;
        }

        public LatLng getEndLocation() {
            return this.f6852f;
        }

        public void setEndLocation(LatLng latLng) {
            this.f6852f = latLng;
        }

        public TrainInfo getTrainInfo() {
            return this.f6853g;
        }

        public void setTrainInfo(TrainInfo trainInfo) {
            this.f6853g = trainInfo;
        }

        public PlaneInfo getPlaneInfo() {
            return this.f6854h;
        }

        public void setPlaneInfo(PlaneInfo planeInfo) {
            this.f6854h = planeInfo;
        }

        public CoachInfo getCoachInfo() {
            return this.f6855i;
        }

        public void setCoachInfo(CoachInfo coachInfo) {
            this.f6855i = coachInfo;
        }

        public BusInfo getBusInfo() {
            return this.f6856j;
        }

        public void setBusInfo(BusInfo busInfo) {
            this.f6856j = busInfo;
        }

        public String getInstructions() {
            return this.f6858l;
        }

        public void setInstructions(String str) {
            this.f6858l = str;
        }

        public StepVehicleInfoType getVehileType() {
            return this.f6857k;
        }

        public void setVehileType(StepVehicleInfoType stepVehicleInfoType) {
            this.f6857k = stepVehicleInfoType;
        }

        public void setPathString(String str) {
            this.f6859m = str;
        }

        /* renamed from: a */
        private List<LatLng> m18690a(String str) {
            String[] split;
            ArrayList arrayList = new ArrayList();
            String[] split2 = str.split(";");
            if (split2 != null) {
                for (int i = 0; i < split2.length; i++) {
                    if (split2[i] != null && split2[i] != "" && (split = split2[i].split(",")) != null && split[1] != "" && split[0] != "") {
                        LatLng latLng = new LatLng(Double.parseDouble(split[1]), Double.parseDouble(split[0]));
                        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                            latLng = CoordTrans.baiduToGcj(latLng);
                        }
                        arrayList.add(latLng);
                    }
                }
            }
            return arrayList;
        }

        @Override // com.baidu.mapapi.search.core.RouteStep
        public List<LatLng> getWayPoints() {
            if (this.mWayPoints == null) {
                this.mWayPoints = m18690a(this.f6859m);
            }
            return this.mWayPoints;
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        public enum StepVehicleInfoType {
            ESTEP_TRAIN(1),
            ESTEP_PLANE(2),
            ESTEP_BUS(3),
            ESTEP_DRIVING(4),
            ESTEP_WALK(5),
            ESTEP_COACH(6);
            

            /* renamed from: a */
            private int f6860a;

            StepVehicleInfoType(int i) {
                this.f6860a = 0;
                this.f6860a = i;
            }

            public int getInt() {
                return this.f6860a;
            }
        }
    }
}
