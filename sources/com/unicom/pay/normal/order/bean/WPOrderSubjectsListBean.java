package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPOrderSubjectsListBean implements Parcelable {
    public static final Parcelable.Creator<WPOrderSubjectsListBean> CREATOR = new Parcelable.Creator<WPOrderSubjectsListBean>() { // from class: com.unicom.pay.normal.order.bean.WPOrderSubjectsListBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPOrderSubjectsListBean createFromParcel(Parcel parcel) {
            return new WPOrderSubjectsListBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPOrderSubjectsListBean[] newArray(int i) {
            return new WPOrderSubjectsListBean[i];
        }
    };
    private List<WPOrderSubjectBean> subSubjects;
    private String title;

    public WPOrderSubjectsListBean(Parcel parcel) {
        this.title = parcel.readString();
        this.subSubjects = parcel.createTypedArrayList(WPOrderSubjectBean.CREATOR);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<WPOrderSubjectBean> getSubSubjects() {
        return this.subSubjects;
    }

    public String getTitle() {
        return this.title;
    }

    public void setSubSubjects(List<WPOrderSubjectBean> list) {
        this.subSubjects = list;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeTypedList(this.subSubjects);
    }
}
