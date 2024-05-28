package org.fidoalliance.aidl;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface IUAFResponseListener extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.fidoalliance.aidl.IUAFResponseListener$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static abstract class AbstractBinderC13421a extends Binder implements IUAFResponseListener {
        public AbstractBinderC13421a() {
            attachInterface(this, "org.fidoalliance.aidl.IUAFResponseListener");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("org.fidoalliance.aidl.IUAFResponseListener");
                return true;
            }
            parcel.enforceInterface("org.fidoalliance.aidl.IUAFResponseListener");
            onResult(parcel.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }
    }

    void onResult(Intent intent);
}
