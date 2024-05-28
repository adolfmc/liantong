package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PoiDetailInfo implements Parcelable {
    public static final Parcelable.Creator<PoiDetailInfo> CREATOR = new C2799g();

    /* renamed from: a */
    private String f6675a;

    /* renamed from: b */
    private LatLng f6676b;

    /* renamed from: c */
    private String f6677c;
    public int checkinNum;
    public int commentNum;

    /* renamed from: d */
    private String f6678d;
    public String detailUrl;
    public int discountNum;
    public int distance;

    /* renamed from: e */
    private String f6679e;
    public double environmentRating;

    /* renamed from: f */
    private String f6680f;
    public double facilityRating;
    public int favoriteNum;

    /* renamed from: g */
    private int f6681g;
    public int grouponNum;

    /* renamed from: h */
    private String f6682h;
    public double hygieneRating;

    /* renamed from: i */
    private String f6683i;
    public int imageNum;

    /* renamed from: j */
    private String f6684j;

    /* renamed from: k */
    private int f6685k;

    /* renamed from: l */
    private List<PoiChildrenInfo> f6686l;
    public LatLng naviLocation;
    public double overallRating;
    public double price;
    public double serviceRating;
    public String shopHours;
    public String tag;
    public double tasteRating;
    public double technologyRating;
    public String type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PoiDetailInfo() {
    }

    public String getName() {
        return this.f6675a;
    }

    public void setName(String str) {
        this.f6675a = str;
    }

    public LatLng getLocation() {
        return this.f6676b;
    }

    public void setLocation(LatLng latLng) {
        this.f6676b = latLng;
    }

    public String getAddress() {
        return this.f6677c;
    }

    public void setAddress(String str) {
        this.f6677c = str;
    }

    public String getProvince() {
        return this.f6678d;
    }

    public void setProvince(String str) {
        this.f6678d = str;
    }

    public String getCity() {
        return this.f6679e;
    }

    public void setCity(String str) {
        this.f6679e = str;
    }

    public String getArea() {
        return this.f6680f;
    }

    public void setArea(String str) {
        this.f6680f = str;
    }

    public int getAdCode() {
        return this.f6681g;
    }

    public void setAdCode(int i) {
        this.f6681g = i;
    }

    public String getTelephone() {
        return this.f6682h;
    }

    public void setTelephone(String str) {
        this.f6682h = str;
    }

    public String getUid() {
        return this.f6683i;
    }

    public void setUid(String str) {
        this.f6683i = str;
    }

    public String getStreetId() {
        return this.f6684j;
    }

    public void setStreetId(String str) {
        this.f6684j = str;
    }

    public int getDetail() {
        return this.f6685k;
    }

    public void setDetail(String str) {
        try {
            this.f6685k = Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            this.f6685k = 0;
        }
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public LatLng getNaviLocation() {
        return this.naviLocation;
    }

    public void setNaviLocation(LatLng latLng) {
        this.naviLocation = latLng;
    }

    public String getDetailUrl() {
        return this.detailUrl;
    }

    public void setDetailUrl(String str) {
        this.detailUrl = str;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double d) {
        this.price = d;
    }

    public String getShopHours() {
        return this.shopHours;
    }

    public void setShopHours(String str) {
        this.shopHours = str;
    }

    public double getOverallRating() {
        return this.overallRating;
    }

    public void setOverallRating(double d) {
        this.overallRating = d;
    }

    public double getTasteRating() {
        return this.tasteRating;
    }

    public void setTasteRating(double d) {
        this.tasteRating = d;
    }

    public double getServiceRating() {
        return this.serviceRating;
    }

    public void setServiceRating(double d) {
        this.serviceRating = d;
    }

    public double getEnvironmentRating() {
        return this.environmentRating;
    }

    public void setEnvironmentRating(double d) {
        this.environmentRating = d;
    }

    public double getFacilityRating() {
        return this.facilityRating;
    }

    public void setFacilityRating(double d) {
        this.facilityRating = d;
    }

    public double getHygieneRating() {
        return this.hygieneRating;
    }

    public void setHygieneRating(double d) {
        this.hygieneRating = d;
    }

    public double getTechnologyRating() {
        return this.technologyRating;
    }

    public void setTechnologyRating(double d) {
        this.technologyRating = d;
    }

    public int getImageNum() {
        return this.imageNum;
    }

    public void setImageNum(int i) {
        this.imageNum = i;
    }

    public int getGrouponNum() {
        return this.grouponNum;
    }

    public void setGrouponNum(int i) {
        this.grouponNum = i;
    }

    public int getCommentNum() {
        return this.commentNum;
    }

    public void setCommentNum(int i) {
        this.commentNum = i;
    }

    public int getDiscountNum() {
        return this.discountNum;
    }

    public void setDiscountNum(int i) {
        this.discountNum = i;
    }

    public int getFavoriteNum() {
        return this.favoriteNum;
    }

    public void setFavoriteNum(int i) {
        this.favoriteNum = i;
    }

    public int getCheckinNum() {
        return this.checkinNum;
    }

    public void setCheckinNum(int i) {
        this.checkinNum = i;
    }

    public List<PoiChildrenInfo> getPoiChildrenInfoList() {
        return this.f6686l;
    }

    public void setPoiChildrenInfoList(List<PoiChildrenInfo> list) {
        this.f6686l = list;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PoiDetailInfo(Parcel parcel) {
        this.f6675a = parcel.readString();
        this.f6676b = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.f6677c = parcel.readString();
        this.f6678d = parcel.readString();
        this.f6679e = parcel.readString();
        this.f6680f = parcel.readString();
        this.f6682h = parcel.readString();
        this.f6683i = parcel.readString();
        this.f6684j = parcel.readString();
        this.f6685k = parcel.readInt();
        this.distance = parcel.readInt();
        this.type = parcel.readString();
        this.tag = parcel.readString();
        this.naviLocation = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.detailUrl = parcel.readString();
        this.price = parcel.readDouble();
        this.shopHours = parcel.readString();
        this.overallRating = parcel.readDouble();
        this.tasteRating = parcel.readDouble();
        this.serviceRating = parcel.readDouble();
        this.environmentRating = parcel.readDouble();
        this.facilityRating = parcel.readDouble();
        this.hygieneRating = parcel.readDouble();
        this.technologyRating = parcel.readDouble();
        this.imageNum = parcel.readInt();
        this.grouponNum = parcel.readInt();
        this.discountNum = parcel.readInt();
        this.commentNum = parcel.readInt();
        this.favoriteNum = parcel.readInt();
        this.checkinNum = parcel.readInt();
        this.f6686l = parcel.createTypedArrayList(PoiChildrenInfo.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f6675a);
        parcel.writeParcelable(this.f6676b, i);
        parcel.writeString(this.f6677c);
        parcel.writeString(this.f6678d);
        parcel.writeString(this.f6679e);
        parcel.writeString(this.f6680f);
        parcel.writeString(this.f6682h);
        parcel.writeString(this.f6683i);
        parcel.writeString(this.f6684j);
        parcel.writeInt(this.f6685k);
        parcel.writeInt(this.distance);
        parcel.writeString(this.type);
        parcel.writeString(this.tag);
        parcel.writeParcelable(this.naviLocation, i);
        parcel.writeString(this.detailUrl);
        parcel.writeDouble(this.price);
        parcel.writeString(this.shopHours);
        parcel.writeDouble(this.overallRating);
        parcel.writeDouble(this.tasteRating);
        parcel.writeDouble(this.serviceRating);
        parcel.writeDouble(this.environmentRating);
        parcel.writeDouble(this.facilityRating);
        parcel.writeDouble(this.hygieneRating);
        parcel.writeDouble(this.technologyRating);
        parcel.writeInt(this.imageNum);
        parcel.writeInt(this.grouponNum);
        parcel.writeInt(this.discountNum);
        parcel.writeInt(this.commentNum);
        parcel.writeInt(this.favoriteNum);
        parcel.writeInt(this.checkinNum);
        parcel.writeTypedList(this.f6686l);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("PoiDetailInfo: ");
        stringBuffer.append("name = ");
        stringBuffer.append(this.f6675a);
        stringBuffer.append("; location = ");
        LatLng latLng = this.f6676b;
        if (latLng != null) {
            stringBuffer.append(latLng.toString());
        } else {
            stringBuffer.append("null");
        }
        stringBuffer.append("; address = ");
        stringBuffer.append(this.f6677c);
        stringBuffer.append("; province = ");
        stringBuffer.append(this.f6678d);
        stringBuffer.append("; city = ");
        stringBuffer.append(this.f6679e);
        stringBuffer.append("; area = ");
        stringBuffer.append(this.f6680f);
        stringBuffer.append("; telephone = ");
        stringBuffer.append(this.f6682h);
        stringBuffer.append("; uid = ");
        stringBuffer.append(this.f6683i);
        stringBuffer.append("; detail = ");
        stringBuffer.append(this.f6685k);
        stringBuffer.append("; distance = ");
        stringBuffer.append(this.distance);
        stringBuffer.append("; type = ");
        stringBuffer.append(this.type);
        stringBuffer.append("; tag = ");
        stringBuffer.append(this.tag);
        stringBuffer.append("; naviLocation = ");
        LatLng latLng2 = this.naviLocation;
        if (latLng2 != null) {
            stringBuffer.append(latLng2.toString());
        } else {
            stringBuffer.append("null");
        }
        stringBuffer.append("; detailUrl = ");
        stringBuffer.append(this.detailUrl);
        stringBuffer.append("; price = ");
        stringBuffer.append(this.price);
        stringBuffer.append("; shopHours = ");
        stringBuffer.append(this.shopHours);
        stringBuffer.append("; overallRating = ");
        stringBuffer.append(this.overallRating);
        stringBuffer.append("; tasteRating = ");
        stringBuffer.append(this.tasteRating);
        stringBuffer.append("; serviceRating = ");
        stringBuffer.append(this.serviceRating);
        stringBuffer.append("; environmentRating = ");
        stringBuffer.append(this.environmentRating);
        stringBuffer.append("; facilityRating = ");
        stringBuffer.append(this.facilityRating);
        stringBuffer.append("; hygieneRating = ");
        stringBuffer.append(this.hygieneRating);
        stringBuffer.append("; technologyRating = ");
        stringBuffer.append(this.technologyRating);
        stringBuffer.append("; imageNum = ");
        stringBuffer.append(this.imageNum);
        stringBuffer.append("; grouponNum = ");
        stringBuffer.append(this.grouponNum);
        stringBuffer.append("; discountNum = ");
        stringBuffer.append(this.discountNum);
        stringBuffer.append("; commentNum = ");
        stringBuffer.append(this.commentNum);
        stringBuffer.append("; favoriteNum = ");
        stringBuffer.append(this.favoriteNum);
        stringBuffer.append("; checkinNum = ");
        stringBuffer.append(this.checkinNum);
        List<PoiChildrenInfo> list = this.f6686l;
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < this.f6686l.size(); i++) {
                stringBuffer.append("; The ");
                stringBuffer.append(i);
                stringBuffer.append(" poiChildrenInfo is: ");
                PoiChildrenInfo poiChildrenInfo = this.f6686l.get(i);
                if (poiChildrenInfo != null) {
                    stringBuffer.append(poiChildrenInfo.toString());
                } else {
                    stringBuffer.append("null");
                }
            }
        }
        return stringBuffer.toString();
    }
}
