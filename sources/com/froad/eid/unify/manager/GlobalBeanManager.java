package com.froad.eid.unify.manager;

import android.os.Parcel;
import android.os.Parcelable;
import com.froad.eid.unify.bean.IDSEConfig;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class GlobalBeanManager implements Parcelable {
    public static final Parcelable.Creator<GlobalBeanManager> CREATOR = new Parcelable.Creator<GlobalBeanManager>() { // from class: com.froad.eid.unify.manager.GlobalBeanManager.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GlobalBeanManager createFromParcel(Parcel parcel) {
            return new GlobalBeanManager(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GlobalBeanManager[] newArray(int i) {
            return new GlobalBeanManager[i];
        }
    };
    private static final String TAG = "GlobalBeanManager";
    private volatile boolean isReadPhoto;
    private volatile boolean isReading;
    private volatile IDSEConfig mIDSEConfig;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class SingletonHolder {
        private static final GlobalBeanManager INSTANCE = new GlobalBeanManager();

        private SingletonHolder() {
        }
    }

    private GlobalBeanManager() {
        this.isReading = false;
        this.isReadPhoto = true;
    }

    public GlobalBeanManager(Parcel parcel) {
        this.isReading = false;
        this.isReadPhoto = true;
        this.mIDSEConfig = (IDSEConfig) parcel.readParcelable(IDSEConfig.class.getClassLoader());
        this.isReading = parcel.readByte() != 0;
    }

    public static GlobalBeanManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IDSEConfig getIDSEConfig() {
        return this.mIDSEConfig;
    }

    public boolean isReadPhoto() {
        return this.isReadPhoto;
    }

    public boolean isReading() {
        return this.isReading;
    }

    public void setIDSEConfig(IDSEConfig iDSEConfig) {
        this.mIDSEConfig = iDSEConfig;
    }

    public void setReadPhoto(boolean z) {
        this.isReadPhoto = z;
    }

    public void setReading(boolean z) {
        this.isReading = z;
    }

    public String toString() {
        return "GlobalBeanManager{, isReading=" + this.isReading + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mIDSEConfig, i);
        parcel.writeByte(this.isReading ? (byte) 1 : (byte) 0);
    }
}
