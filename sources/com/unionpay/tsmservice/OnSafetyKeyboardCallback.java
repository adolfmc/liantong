package com.unionpay.tsmservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface OnSafetyKeyboardCallback extends IInterface {

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public abstract class Stub extends Binder implements OnSafetyKeyboardCallback {

        /* renamed from: com.unionpay.tsmservice.OnSafetyKeyboardCallback$Stub$a */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        final class C10776a implements OnSafetyKeyboardCallback {

            /* renamed from: a */
            private IBinder f20706a;

            /* JADX INFO: Access modifiers changed from: package-private */
            public C10776a(IBinder iBinder) {
                this.f20706a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f20706a;
            }

            @Override // com.unionpay.tsmservice.OnSafetyKeyboardCallback
            public final void onEditorChanged(int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.OnSafetyKeyboardCallback");
                    obtain.writeInt(i);
                    this.f20706a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.OnSafetyKeyboardCallback
            public final void onHide() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.OnSafetyKeyboardCallback");
                    this.f20706a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.OnSafetyKeyboardCallback
            public final void onShow() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.OnSafetyKeyboardCallback");
                    this.f20706a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.unionpay.tsmservice.OnSafetyKeyboardCallback");
        }

        public static OnSafetyKeyboardCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.unionpay.tsmservice.OnSafetyKeyboardCallback");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof OnSafetyKeyboardCallback)) ? new C10776a(iBinder) : (OnSafetyKeyboardCallback) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString("com.unionpay.tsmservice.OnSafetyKeyboardCallback");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.unionpay.tsmservice.OnSafetyKeyboardCallback");
                    onShow();
                    break;
                case 2:
                    parcel.enforceInterface("com.unionpay.tsmservice.OnSafetyKeyboardCallback");
                    onHide();
                    break;
                case 3:
                    parcel.enforceInterface("com.unionpay.tsmservice.OnSafetyKeyboardCallback");
                    onEditorChanged(parcel.readInt());
                    break;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeNoException();
            return true;
        }
    }

    void onEditorChanged(int i);

    void onHide();

    void onShow();
}
