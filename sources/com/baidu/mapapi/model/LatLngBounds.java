package com.baidu.mapapi.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class LatLngBounds implements Parcelable {
    public static final Parcelable.Creator<LatLngBounds> CREATOR = new C2788b();
    public final LatLng northeast;
    public final LatLng southwest;

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
        private double f6602a;

        /* renamed from: b */
        private double f6603b;

        /* renamed from: c */
        private double f6604c;

        /* renamed from: d */
        private double f6605d;

        /* renamed from: e */
        private double f6606e;

        /* renamed from: f */
        private double f6607f;

        /* renamed from: g */
        private boolean f6608g = true;

        public LatLngBounds build() {
            if (this.f6606e != 0.0d || this.f6607f != 0.0d) {
                if (this.f6605d != 0.0d || this.f6604c != 0.0d) {
                    this.f6604c = this.f6607f + 360.0d;
                    double d = this.f6604c;
                    double d2 = this.f6605d;
                    if (d > d2) {
                        this.f6605d = d;
                        this.f6604c = d2;
                    }
                } else {
                    this.f6605d = this.f6606e;
                    this.f6604c = this.f6607f;
                }
            }
            double d3 = this.f6605d;
            if (d3 > 180.0d) {
                this.f6605d = d3 - 360.0d;
                double d4 = this.f6605d;
                double d5 = this.f6604c;
                if (d4 < d5) {
                    this.f6605d = d5;
                    this.f6604c = d4;
                }
            }
            return new LatLngBounds(new LatLng(this.f6603b, this.f6605d), new LatLng(this.f6602a, this.f6604c));
        }

        public Builder include(LatLng latLng) {
            if (latLng == null) {
                return this;
            }
            if (this.f6608g) {
                this.f6608g = false;
                if (latLng.longitude >= 0.0d) {
                    double d = latLng.longitude;
                    this.f6604c = d;
                    this.f6605d = d;
                } else {
                    double d2 = latLng.longitude;
                    this.f6607f = d2;
                    this.f6606e = d2;
                }
                double d3 = latLng.latitude;
                this.f6602a = d3;
                this.f6603b = d3;
            }
            m18776a(latLng);
            return this;
        }

        public Builder include(List<LatLng> list) {
            if (list == null || list.size() == 0) {
                return this;
            }
            if (list.get(0) != null && this.f6608g) {
                this.f6608g = false;
                if (list.get(0).longitude >= 0.0d) {
                    double d = list.get(0).longitude;
                    this.f6604c = d;
                    this.f6605d = d;
                } else {
                    double d2 = list.get(0).longitude;
                    this.f6607f = d2;
                    this.f6606e = d2;
                }
                double d3 = list.get(0).latitude;
                this.f6602a = d3;
                this.f6603b = d3;
            }
            for (LatLng latLng : list) {
                m18776a(latLng);
            }
            return this;
        }

        /* renamed from: a */
        private void m18776a(LatLng latLng) {
            if (latLng == null) {
                return;
            }
            double d = latLng.latitude;
            double d2 = latLng.longitude;
            if (d < this.f6602a) {
                this.f6602a = d;
            }
            if (d > this.f6603b) {
                this.f6603b = d;
            }
            int i = (d2 > 0.0d ? 1 : (d2 == 0.0d ? 0 : -1));
            if (i >= 0) {
                if (d2 < this.f6604c) {
                    this.f6604c = d2;
                }
                if (d2 > this.f6605d) {
                    this.f6605d = d2;
                    if (this.f6604c == 0.0d) {
                        this.f6604c = d2;
                    }
                }
                if (i == 0) {
                    this.f6606e = d2;
                    return;
                }
                return;
            }
            if (d2 < this.f6607f) {
                this.f6607f = d2;
            }
            if (d2 > this.f6606e) {
                this.f6606e = d2;
            }
        }
    }

    LatLngBounds(LatLng latLng, LatLng latLng2) {
        this.northeast = latLng;
        this.southwest = latLng2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LatLngBounds(Parcel parcel) {
        this.northeast = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.southwest = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
    }

    public boolean contains(LatLng latLng) {
        if (latLng == null) {
            return false;
        }
        double d = this.southwest.latitude;
        double d2 = this.northeast.latitude;
        double d3 = this.southwest.longitude;
        double d4 = this.northeast.longitude;
        double d5 = latLng.latitude;
        double d6 = latLng.longitude;
        return d5 >= d && d5 <= d2 && d6 >= d3 && d6 <= d4;
    }

    public LatLng getCenter() {
        return new LatLng(((this.northeast.latitude - this.southwest.latitude) / 2.0d) + this.southwest.latitude, ((this.northeast.longitude - this.southwest.longitude) / 2.0d) + this.southwest.longitude);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.northeast, i);
        parcel.writeParcelable(this.southwest, i);
    }

    public String toString() {
        return "southwest: " + this.southwest.latitude + ", " + this.southwest.longitude + "\nnortheast: " + this.northeast.latitude + ", " + this.northeast.longitude;
    }
}
