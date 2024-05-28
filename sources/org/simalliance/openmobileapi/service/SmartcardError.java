package org.simalliance.openmobileapi.service;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SmartcardError implements Parcelable {
    public static final Class[] ALLOWED_EXCEPTIONS = {IOException.class, SecurityException.class, NoSuchElementException.class, IllegalStateException.class, IllegalArgumentException.class, UnsupportedOperationException.class, NullPointerException.class};
    public static final Parcelable.Creator<SmartcardError> CREATOR = new Parcelable.Creator<SmartcardError>() { // from class: org.simalliance.openmobileapi.service.SmartcardError.1
        @Override // android.os.Parcelable.Creator
        public SmartcardError createFromParcel(Parcel parcel) {
            return new SmartcardError(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public SmartcardError[] newArray(int i) {
            return new SmartcardError[i];
        }
    };
    public String mClazz;
    public String mMessage;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isSet() {
        String str = this.mClazz;
        return (str == null || str.isEmpty()) ? false : true;
    }

    public void readFromParcel(Parcel parcel) {
        this.mClazz = parcel.readString();
        this.mMessage = parcel.readString();
    }

    public void set(Exception exc) {
        if (exc != null) {
            Class<?> cls = exc.getClass();
            if (Arrays.asList(ALLOWED_EXCEPTIONS).contains(cls)) {
                this.mClazz = cls.getCanonicalName();
                this.mMessage = exc.getMessage() != null ? exc.getMessage() : "";
                return;
            }
            throw new IllegalArgumentException("Unexpected exception class: " + cls.getCanonicalName());
        }
        throw new IllegalArgumentException("Cannot set a null exception");
    }

    public void throwException() {
        if (!this.mClazz.equals(IOException.class.getCanonicalName())) {
            if (!this.mClazz.equals(SecurityException.class.getCanonicalName())) {
                if (!this.mClazz.equals(NoSuchElementException.class.getCanonicalName())) {
                    if (!this.mClazz.equals(IllegalStateException.class.getCanonicalName())) {
                        if (!this.mClazz.equals(IllegalArgumentException.class.getCanonicalName())) {
                            if (!this.mClazz.equals(UnsupportedOperationException.class.getCanonicalName())) {
                                if (!this.mClazz.equals(NullPointerException.class.getCanonicalName())) {
                                    String simpleName = SmartcardError.class.getSimpleName();
                                    Log.wtf(simpleName, "SmartcardError.throwException() finished without throwing exception. mClazz: " + this.mClazz);
                                    return;
                                }
                                throw new NullPointerException(this.mMessage);
                            }
                            throw new UnsupportedOperationException(this.mMessage);
                        }
                        throw new IllegalArgumentException(this.mMessage);
                    }
                    throw new IllegalStateException(this.mMessage);
                }
                throw new NoSuchElementException(this.mMessage);
            }
            throw new SecurityException(this.mMessage);
        }
        throw new IOException(this.mMessage);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mClazz);
        parcel.writeString(this.mMessage);
    }

    public SmartcardError() {
        this.mClazz = "";
        this.mMessage = "";
    }

    public SmartcardError(Parcel parcel) {
        readFromParcel(parcel);
    }
}
