package com.baidu.mapapi.search.poi;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PoiDetailResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<PoiDetailResult> CREATOR = new C2817a();
    public String address;
    public int checkinNum;
    public int commentNum;
    public String detailUrl;
    public int discountNum;
    public double environmentRating;
    public double facilityRating;
    public int favoriteNum;
    public int grouponNum;
    public double hygieneRating;
    public int imageNum;
    public LatLng location;
    public String name;
    public double overallRating;
    public double price;
    public double serviceRating;
    public String shopHours;
    public String tag;
    public double tasteRating;
    public double technologyRating;
    public String telephone;
    public String type;
    public String uid;

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PoiDetailResult() {
    }

    public PoiDetailResult(SearchResult.ERRORNO errorno) {
        super(errorno);
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public String getDetailUrl() {
        return this.detailUrl;
    }

    public void setDetailUrl(String str) {
        this.detailUrl = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double d) {
        this.price = d;
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

    public String getShopHours() {
        return this.shopHours;
    }

    public void setShopHours(String str) {
        this.shopHours = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public LatLng getLocation() {
        return this.location;
    }

    public void setLocation(LatLng latLng) {
        this.location = latLng;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String str) {
        this.telephone = str;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PoiDetailResult(Parcel parcel) {
        super(parcel);
        this.name = parcel.readString();
        this.location = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.address = parcel.readString();
        this.telephone = parcel.readString();
        this.uid = parcel.readString();
        this.tag = parcel.readString();
        this.detailUrl = parcel.readString();
        this.type = parcel.readString();
        this.price = parcel.readDouble();
        this.overallRating = parcel.readDouble();
        this.tasteRating = parcel.readDouble();
        this.serviceRating = parcel.readDouble();
        this.environmentRating = parcel.readDouble();
        this.facilityRating = parcel.readDouble();
        this.hygieneRating = parcel.readDouble();
        this.technologyRating = parcel.readDouble();
        this.imageNum = parcel.readInt();
        this.grouponNum = parcel.readInt();
        this.commentNum = parcel.readInt();
        this.discountNum = parcel.readInt();
        this.favoriteNum = parcel.readInt();
        this.checkinNum = parcel.readInt();
        this.shopHours = parcel.readString();
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.name);
        parcel.writeParcelable(this.location, i);
        parcel.writeString(this.address);
        parcel.writeString(this.telephone);
        parcel.writeString(this.uid);
        parcel.writeString(this.tag);
        parcel.writeString(this.detailUrl);
        parcel.writeString(this.type);
        parcel.writeDouble(this.price);
        parcel.writeDouble(this.overallRating);
        parcel.writeDouble(this.tasteRating);
        parcel.writeDouble(this.serviceRating);
        parcel.writeDouble(this.environmentRating);
        parcel.writeDouble(this.facilityRating);
        parcel.writeDouble(this.hygieneRating);
        parcel.writeDouble(this.technologyRating);
        parcel.writeInt(this.imageNum);
        parcel.writeInt(this.grouponNum);
        parcel.writeInt(this.commentNum);
        parcel.writeInt(this.discountNum);
        parcel.writeInt(this.favoriteNum);
        parcel.writeInt(this.checkinNum);
        parcel.writeString(this.shopHours);
    }
}
