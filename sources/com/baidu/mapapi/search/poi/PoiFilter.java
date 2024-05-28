package com.baidu.mapapi.search.poi;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class PoiFilter implements Parcelable {

    /* renamed from: a */
    private String f6766a;

    /* renamed from: b */
    private String f6767b;

    /* renamed from: c */
    private String f6768c;

    /* renamed from: d */
    private String f6769d;

    /* renamed from: e */
    private String f6770e;

    /* renamed from: f */
    private static Map<SortName, String> f6765f = new HashMap();
    public static final Parcelable.Creator<PoiFilter> CREATOR = new C2819c();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum IndustryType {
        HOTEL,
        CATER,
        LIFE
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface SortName {

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        public enum CaterSortName implements SortName {
            DEFAULT,
            CATER_PRICE,
            CATER_DISTANCE,
            CATER_TASTE_RATING,
            CATER_OVERALL_RATING,
            CATER_SERVICE_RATING
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        public enum HotelSortName implements SortName {
            DEFAULT,
            HOTEL_PRICE,
            HOTEL_DISTANCE,
            HOTEL_TOTAL_SCORE,
            HOTEL_LEVEL,
            HOTEL_HEALTH_SCORE
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        public enum LifeSortName implements SortName {
            DEFAULT,
            PRICE,
            DISTANCE,
            LIFE_OVERALL_RATING,
            LIFE_COMMENT_RATING
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class Builder {

        /* renamed from: a */
        private String f6771a;

        /* renamed from: b */
        private String f6772b;

        /* renamed from: c */
        private String f6773c;

        /* renamed from: d */
        private String f6774d;

        /* renamed from: e */
        private String f6775e;

        public Builder() {
            PoiFilter.f6765f.put(SortName.HotelSortName.DEFAULT, "default");
            PoiFilter.f6765f.put(SortName.HotelSortName.HOTEL_LEVEL, "level");
            PoiFilter.f6765f.put(SortName.HotelSortName.HOTEL_PRICE, "price");
            PoiFilter.f6765f.put(SortName.HotelSortName.HOTEL_DISTANCE, "distance");
            PoiFilter.f6765f.put(SortName.HotelSortName.HOTEL_HEALTH_SCORE, "health_score");
            PoiFilter.f6765f.put(SortName.HotelSortName.HOTEL_TOTAL_SCORE, "total_score");
            PoiFilter.f6765f.put(SortName.CaterSortName.DEFAULT, "default");
            PoiFilter.f6765f.put(SortName.CaterSortName.CATER_DISTANCE, "distance");
            PoiFilter.f6765f.put(SortName.CaterSortName.CATER_PRICE, "price");
            PoiFilter.f6765f.put(SortName.CaterSortName.CATER_OVERALL_RATING, "overall_rating");
            PoiFilter.f6765f.put(SortName.CaterSortName.CATER_SERVICE_RATING, "service_rating");
            PoiFilter.f6765f.put(SortName.CaterSortName.CATER_TASTE_RATING, "taste_rating");
            PoiFilter.f6765f.put(SortName.LifeSortName.DEFAULT, "default");
            PoiFilter.f6765f.put(SortName.LifeSortName.PRICE, "price");
            PoiFilter.f6765f.put(SortName.LifeSortName.LIFE_COMMENT_RATING, "comment_num");
            PoiFilter.f6765f.put(SortName.LifeSortName.LIFE_OVERALL_RATING, "overall_rating");
            PoiFilter.f6765f.put(SortName.LifeSortName.DISTANCE, "distance");
        }

        public PoiFilter build() {
            return new PoiFilter(this.f6771a, this.f6772b, this.f6773c, this.f6775e, this.f6774d);
        }

        public Builder industryType(IndustryType industryType) {
            switch (industryType) {
                case HOTEL:
                    this.f6771a = "hotel";
                    break;
                case CATER:
                    this.f6771a = "cater";
                    break;
                case LIFE:
                    this.f6771a = "life";
                    break;
                default:
                    this.f6771a = "";
                    break;
            }
            return this;
        }

        public Builder sortName(SortName sortName) {
            if (!TextUtils.isEmpty(this.f6771a) && sortName != null) {
                this.f6772b = (String) PoiFilter.f6765f.get(sortName);
            }
            return this;
        }

        public Builder sortRule(int i) {
            this.f6773c = i + "";
            return this;
        }

        public Builder isGroupon(boolean z) {
            if (z) {
                this.f6774d = "1";
            } else {
                this.f6774d = "0";
            }
            return this;
        }

        public Builder isDiscount(boolean z) {
            if (z) {
                this.f6775e = "1";
            } else {
                this.f6775e = "0";
            }
            return this;
        }
    }

    PoiFilter(String str, String str2, String str3, String str4, String str5) {
        this.f6766a = "";
        this.f6767b = "";
        this.f6768c = "";
        this.f6769d = "";
        this.f6770e = "";
        this.f6766a = str;
        this.f6767b = str2;
        this.f6768c = str3;
        this.f6770e = str4;
        this.f6769d = str5;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PoiFilter(Parcel parcel) {
        this.f6766a = "";
        this.f6767b = "";
        this.f6768c = "";
        this.f6769d = "";
        this.f6770e = "";
        this.f6766a = parcel.readString();
        this.f6767b = parcel.readString();
        this.f6768c = parcel.readString();
        this.f6770e = parcel.readString();
        this.f6769d = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f6766a);
        parcel.writeString(this.f6767b);
        parcel.writeString(this.f6768c);
        parcel.writeString(this.f6770e);
        parcel.writeString(this.f6769d);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(this.f6766a)) {
            sb.append("industry_type:");
            sb.append(this.f6766a);
            sb.append("|");
        }
        if (!TextUtils.isEmpty(this.f6767b)) {
            sb.append("sort_name:");
            sb.append(this.f6767b);
            sb.append("|");
        }
        if (!TextUtils.isEmpty(this.f6768c)) {
            sb.append("sort_rule:");
            sb.append(this.f6768c);
            sb.append("|");
        }
        if (!TextUtils.isEmpty(this.f6770e)) {
            sb.append("discount:");
            sb.append(this.f6770e);
            sb.append("|");
        }
        if (!TextUtils.isEmpty(this.f6769d)) {
            sb.append("groupon:");
            sb.append(this.f6769d);
            sb.append("|");
        }
        if (!TextUtils.isEmpty(sb.toString())) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
