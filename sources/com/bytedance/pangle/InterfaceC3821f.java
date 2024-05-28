package com.bytedance.pangle;

import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.f */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface InterfaceC3821f extends IInterface {
    /* renamed from: a */
    int mo16662a();

    /* renamed from: a */
    void mo16661a(ComponentName componentName, IBinder iBinder);

    /* renamed from: com.bytedance.pangle.f$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static abstract class AbstractBinderC3822a extends Binder implements InterfaceC3821f {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public AbstractBinderC3822a() {
            attachInterface(this, "com.bytedance.pangle.ServiceConnection");
        }

        /* renamed from: a */
        public static InterfaceC3821f m16879a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.bytedance.pangle.ServiceConnection");
            if (queryLocalInterface != null && (queryLocalInterface instanceof InterfaceC3821f)) {
                return (InterfaceC3821f) queryLocalInterface;
            }
            return new C3823a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString("com.bytedance.pangle.ServiceConnection");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.bytedance.pangle.ServiceConnection");
                    mo16661a(parcel.readInt() != 0 ? (ComponentName) ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("com.bytedance.pangle.ServiceConnection");
                    int a = mo16662a();
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.bytedance.pangle.f$a$a */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        public static class C3823a implements InterfaceC3821f {

            /* renamed from: a */
            public static InterfaceC3821f f9146a;

            /* renamed from: b */
            private IBinder f9147b;

            C3823a(IBinder iBinder) {
                this.f9147b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f9147b;
            }

            @Override // com.bytedance.pangle.InterfaceC3821f
            /* renamed from: a */
            public final void mo16661a(ComponentName componentName, IBinder iBinder) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.bytedance.pangle.ServiceConnection");
                    if (componentName != null) {
                        obtain.writeInt(1);
                        componentName.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (!this.f9147b.transact(1, obtain, obtain2, 0) && AbstractBinderC3822a.m16875b() != null) {
                        AbstractBinderC3822a.m16875b().mo16661a(componentName, iBinder);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.bytedance.pangle.InterfaceC3821f
            /* renamed from: a */
            public final int mo16662a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.bytedance.pangle.ServiceConnection");
                    if (!this.f9147b.transact(2, obtain, obtain2, 0) && AbstractBinderC3822a.m16875b() != null) {
                        return AbstractBinderC3822a.m16875b().mo16662a();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        /* renamed from: b */
        public static InterfaceC3821f m16875b() {
            return C3823a.f9146a;
        }
    }
}
