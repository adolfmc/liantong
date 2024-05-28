package com.baidu.mapapi.search.geocode;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ReverseGeoCodeResult extends SearchResult {
    public static final Parcelable.Creator<ReverseGeoCodeResult> CREATOR = new C2813b();

    /* renamed from: a */
    private String f6750a;

    /* renamed from: b */
    private String f6751b;

    /* renamed from: c */
    private AddressComponent f6752c;

    /* renamed from: d */
    private LatLng f6753d;

    /* renamed from: e */
    private int f6754e;

    /* renamed from: f */
    private List<PoiInfo> f6755f;

    /* renamed from: g */
    private String f6756g;

    /* renamed from: h */
    private List<PoiRegionsInfo> f6757h;

    /* renamed from: i */
    private List<RoadInfo> f6758i;

    /* renamed from: j */
    private int f6759j;

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ReverseGeoCodeResult() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ReverseGeoCodeResult(Parcel parcel) {
        super(parcel);
        this.f6750a = parcel.readString();
        this.f6751b = parcel.readString();
        this.f6752c = (AddressComponent) parcel.readParcelable(AddressComponent.class.getClassLoader());
        this.f6753d = (LatLng) parcel.readValue(LatLng.class.getClassLoader());
        this.f6755f = parcel.createTypedArrayList(PoiInfo.CREATOR);
        this.f6756g = parcel.readString();
        this.f6757h = parcel.createTypedArrayList(PoiRegionsInfo.CREATOR);
        this.f6758i = parcel.createTypedArrayList(RoadInfo.CREATOR);
    }

    public String getBusinessCircle() {
        return this.f6750a;
    }

    public void setBusinessCircle(String str) {
        this.f6750a = str;
    }

    public int getCityCode() {
        return this.f6754e;
    }

    public void setCityCode(int i) {
        this.f6754e = i;
    }

    public String getAddress() {
        return this.f6751b;
    }

    public void setAddress(String str) {
        this.f6751b = str;
    }

    public AddressComponent getAddressDetail() {
        return this.f6752c;
    }

    public void setAddressDetail(AddressComponent addressComponent) {
        this.f6752c = addressComponent;
    }

    public int getAdcode() {
        return this.f6759j;
    }

    public void setAdcode(int i) {
        this.f6759j = i;
    }

    public LatLng getLocation() {
        return this.f6753d;
    }

    public void setLocation(LatLng latLng) {
        this.f6753d = latLng;
    }

    public List<PoiInfo> getPoiList() {
        return this.f6755f;
    }

    public void setPoiList(List<PoiInfo> list) {
        this.f6755f = list;
    }

    public String getSematicDescription() {
        return this.f6756g;
    }

    public void setSematicDescription(String str) {
        this.f6756g = str;
    }

    public List<PoiRegionsInfo> getPoiRegionsInfoList() {
        return this.f6757h;
    }

    public void setPoiRegionsInfoList(List<PoiRegionsInfo> list) {
        this.f6757h = list;
    }

    public List<RoadInfo> getRoadInfoList() {
        return this.f6758i;
    }

    public void setRoadInfoList(List<RoadInfo> list) {
        this.f6758i = list;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class AddressComponent implements Parcelable {
        public static final Parcelable.Creator<AddressComponent> CREATOR = new C2814c();
        public int adcode;
        public String city;
        public int cityLevel;
        public int countryCode;
        public String countryCodeIso;
        public String countryCodeIso2;
        public String countryName;
        public String direction;
        public String distance;
        public String district;
        public String province;
        public String street;
        public String streetNumber;
        public String town;
        public String townCode;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.streetNumber);
            parcel.writeString(this.street);
            parcel.writeString(this.town);
            parcel.writeString(this.district);
            parcel.writeString(this.city);
            parcel.writeString(this.province);
            parcel.writeString(this.countryName);
            parcel.writeInt(this.countryCode);
            parcel.writeInt(this.adcode);
            parcel.writeString(this.direction);
            parcel.writeString(this.distance);
            parcel.writeString(this.countryCodeIso);
            parcel.writeString(this.countryCodeIso2);
            parcel.writeString(this.townCode);
            parcel.writeInt(this.cityLevel);
        }

        public AddressComponent() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public AddressComponent(Parcel parcel) {
            this.streetNumber = parcel.readString();
            this.street = parcel.readString();
            this.town = parcel.readString();
            this.district = parcel.readString();
            this.city = parcel.readString();
            this.province = parcel.readString();
            this.countryName = parcel.readString();
            this.countryCode = parcel.readInt();
            this.adcode = parcel.readInt();
            this.direction = parcel.readString();
            this.distance = parcel.readString();
            this.countryCodeIso = parcel.readString();
            this.countryCodeIso2 = parcel.readString();
            this.townCode = parcel.readString();
            this.cityLevel = parcel.readInt();
        }

        public String getTown() {
            return this.town;
        }

        public void setTown(String str) {
            this.town = str;
        }

        public String getDirection() {
            return this.direction;
        }

        public void setDirection(String str) {
            this.direction = str;
        }

        public String getDistance() {
            return this.distance;
        }

        public void setDistance(String str) {
            this.distance = str;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class PoiRegionsInfo implements Parcelable {
        public static final Parcelable.Creator<PoiRegionsInfo> CREATOR = new C2815d();
        public String directionDesc;
        public String regionName;
        public String regionTag;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public PoiRegionsInfo() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public PoiRegionsInfo(Parcel parcel) {
            this.directionDesc = parcel.readString();
            this.regionName = parcel.readString();
            this.regionTag = parcel.readString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.directionDesc);
            parcel.writeString(this.regionName);
            parcel.writeString(this.regionTag);
        }

        public String getDirectionDesc() {
            return this.directionDesc;
        }

        public void setDirectionDesc(String str) {
            this.directionDesc = str;
        }

        public String getRegionName() {
            return this.regionName;
        }

        public void setRegionName(String str) {
            this.regionName = str;
        }

        public String getRegionTag() {
            return this.regionTag;
        }

        public void setRegionTag(String str) {
            this.regionTag = str;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class RoadInfo implements Parcelable {
        public static final Parcelable.Creator<RoadInfo> CREATOR = new C2816e();
        public String distance;
        public String name;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public RoadInfo() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public RoadInfo(Parcel parcel) {
            this.name = parcel.readString();
            this.distance = parcel.readString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.name);
            parcel.writeString(this.distance);
        }
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f6750a);
        parcel.writeString(this.f6751b);
        parcel.writeParcelable(this.f6752c, 0);
        parcel.writeValue(this.f6753d);
        parcel.writeTypedList(this.f6755f);
        parcel.writeString(this.f6756g);
        parcel.writeTypedList(this.f6757h);
        parcel.writeTypedList(this.f6758i);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("ReverseGeoCodeResult: \n");
        stringBuffer.append("businessCircle = ");
        stringBuffer.append(this.f6750a);
        stringBuffer.append("; address = ");
        stringBuffer.append(this.f6751b);
        stringBuffer.append("; location = ");
        stringBuffer.append(this.f6753d);
        stringBuffer.append("; sematicDescription = ");
        stringBuffer.append(this.f6756g);
        if (this.f6752c != null) {
            stringBuffer.append("\n#AddressComponent Info BEGIN# \n");
            stringBuffer.append("streetNumber = ");
            stringBuffer.append(this.f6752c.streetNumber);
            stringBuffer.append("; street = ");
            stringBuffer.append(this.f6752c.street);
            stringBuffer.append("; town = ");
            stringBuffer.append(this.f6752c.town);
            stringBuffer.append("; district = ");
            stringBuffer.append(this.f6752c.district);
            stringBuffer.append("; city = ");
            stringBuffer.append(this.f6752c.city);
            stringBuffer.append("; province = ");
            stringBuffer.append(this.f6752c.province);
            stringBuffer.append("; countryName = ");
            stringBuffer.append(this.f6752c.countryName);
            stringBuffer.append("; countryCode = ");
            stringBuffer.append(this.f6752c.countryCode);
            stringBuffer.append("; adcode = ");
            stringBuffer.append(this.f6752c.adcode);
            stringBuffer.append("; direction = ");
            stringBuffer.append(this.f6752c.direction);
            stringBuffer.append("; distance = ");
            stringBuffer.append(this.f6752c.distance);
            stringBuffer.append("; countryCodeIso = ");
            stringBuffer.append(this.f6752c.countryCodeIso);
            stringBuffer.append("; countryCodeIso2 = ");
            stringBuffer.append(this.f6752c.countryCodeIso2);
            stringBuffer.append("; townCode = ");
            stringBuffer.append(this.f6752c.townCode);
            stringBuffer.append("; cityLevel = ");
            stringBuffer.append(this.f6752c.cityLevel);
            stringBuffer.append("\n#AddressComponent Info END# \n");
        }
        List<PoiRegionsInfo> list = this.f6757h;
        if (list != null && !list.isEmpty()) {
            stringBuffer.append("\n#PoiRegions Info  BEGIN#");
            for (int i = 0; i < this.f6757h.size(); i++) {
                PoiRegionsInfo poiRegionsInfo = this.f6757h.get(i);
                if (poiRegionsInfo != null) {
                    stringBuffer.append("\ndirectionDesc = ");
                    stringBuffer.append(poiRegionsInfo.getDirectionDesc());
                    stringBuffer.append("; regionName = ");
                    stringBuffer.append(poiRegionsInfo.getRegionName());
                    stringBuffer.append("; regionTag = ");
                    stringBuffer.append(poiRegionsInfo.getRegionTag());
                }
            }
            stringBuffer.append("\n#PoiRegions Info  END# \n");
        }
        List<PoiInfo> list2 = this.f6755f;
        if (list2 != null && !list2.isEmpty()) {
            stringBuffer.append("\n #PoiList Info  BEGIN#");
            for (int i2 = 0; i2 < this.f6755f.size(); i2++) {
                PoiInfo poiInfo = this.f6755f.get(i2);
                if (poiInfo != null) {
                    stringBuffer.append("\n address = ");
                    stringBuffer.append(poiInfo.getAddress());
                    stringBuffer.append("; phoneNumber = ");
                    stringBuffer.append(poiInfo.getPhoneNum());
                    stringBuffer.append("; uid = ");
                    stringBuffer.append(poiInfo.getUid());
                    stringBuffer.append("; postCode = ");
                    stringBuffer.append(poiInfo.getPostCode());
                    stringBuffer.append("; name = ");
                    stringBuffer.append(poiInfo.getName());
                    stringBuffer.append("; location = ");
                    stringBuffer.append(poiInfo.getLocation());
                    stringBuffer.append("; city = ");
                    stringBuffer.append(poiInfo.getCity());
                    stringBuffer.append("; direction = ");
                    stringBuffer.append(poiInfo.getDirection());
                    stringBuffer.append("; distance = ");
                    stringBuffer.append(poiInfo.getDistance());
                    if (poiInfo.getParentPoi() != null) {
                        stringBuffer.append("\n parentPoiAddress = ");
                        stringBuffer.append(poiInfo.getParentPoi().getParentPoiAddress());
                        stringBuffer.append("; parentPoiDirection = ");
                        stringBuffer.append(poiInfo.getParentPoi().getParentPoiDirection());
                        stringBuffer.append("; parentPoiDistance = ");
                        stringBuffer.append(poiInfo.getParentPoi().getParentPoiDistance());
                        stringBuffer.append("; parentPoiName = ");
                        stringBuffer.append(poiInfo.getParentPoi().getParentPoiName());
                        stringBuffer.append("; parentPoiTag = ");
                        stringBuffer.append(poiInfo.getParentPoi().getParentPoiTag());
                        stringBuffer.append("; parentPoiUid = ");
                        stringBuffer.append(poiInfo.getParentPoi().getParentPoiUid());
                        stringBuffer.append("; parentPoiLocation = ");
                        stringBuffer.append(poiInfo.getParentPoi().getParentPoiLocation());
                    }
                }
            }
            stringBuffer.append("\n #PoiList Info  END# \n");
        }
        List<RoadInfo> list3 = this.f6758i;
        if (list3 != null && !list3.isEmpty()) {
            stringBuffer.append("\n #RoadInfoList Info  BEGIN#");
            for (int i3 = 0; i3 < this.f6758i.size(); i3++) {
                RoadInfo roadInfo = this.f6758i.get(i3);
                if (roadInfo != null) {
                    stringBuffer.append("; name = ");
                    stringBuffer.append(roadInfo.name);
                    stringBuffer.append("; distance = ");
                    stringBuffer.append(roadInfo.distance);
                }
            }
            stringBuffer.append("\n #RoadInfoList Info  END# \n");
        }
        return stringBuffer.toString();
    }
}
