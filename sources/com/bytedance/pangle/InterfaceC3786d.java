package com.bytedance.pangle;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.bytedance.pangle.InterfaceC3821f;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.d */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface InterfaceC3786d extends IInterface {
    /* renamed from: a */
    ComponentName mo16675a(Intent intent, String str);

    /* renamed from: a */
    void mo16674a(InterfaceC3821f interfaceC3821f);

    /* renamed from: a */
    boolean mo16677a(Intent intent, InterfaceC3821f interfaceC3821f, int i, String str);

    /* renamed from: b */
    boolean mo16668b(Intent intent, String str);

    /* renamed from: com.bytedance.pangle.d$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static abstract class AbstractBinderC3787a extends Binder implements InterfaceC3786d {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public AbstractBinderC3787a() {
            attachInterface(this, "com.bytedance.pangle.IServiceManager");
        }

        /* renamed from: a */
        public static InterfaceC3786d m16957a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.bytedance.pangle.IServiceManager");
            if (queryLocalInterface != null && (queryLocalInterface instanceof InterfaceC3786d)) {
                return (InterfaceC3786d) queryLocalInterface;
            }
            return new C3788a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString("com.bytedance.pangle.IServiceManager");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.bytedance.pangle.IServiceManager");
                    ComponentName a = mo16675a(parcel.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    if (a != null) {
                        parcel2.writeInt(1);
                        a.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface("com.bytedance.pangle.IServiceManager");
                    boolean b = mo16668b(parcel.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(b ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface("com.bytedance.pangle.IServiceManager");
                    boolean a2 = mo16677a(parcel.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(parcel) : null, InterfaceC3821f.AbstractBinderC3822a.m16879a(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(a2 ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface("com.bytedance.pangle.IServiceManager");
                    mo16674a(InterfaceC3821f.AbstractBinderC3822a.m16879a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.bytedance.pangle.d$a$a */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        public static class C3788a implements InterfaceC3786d {

            /* renamed from: a */
            public static InterfaceC3786d f9073a;

            /* renamed from: b */
            private IBinder f9074b;

            C3788a(IBinder iBinder) {
                this.f9074b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f9074b;
            }

            @Override // com.bytedance.pangle.InterfaceC3786d
            /* renamed from: a */
            public final ComponentName mo16675a(Intent intent, String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.bytedance.pangle.IServiceManager");
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (!this.f9074b.transact(1, obtain, obtain2, 0) && AbstractBinderC3787a.m16959a() != null) {
                        return AbstractBinderC3787a.m16959a().mo16675a(intent, str);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (ComponentName) ComponentName.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.bytedance.pangle.InterfaceC3786d
            /* renamed from: b */
            public final boolean mo16668b(Intent intent, String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.bytedance.pangle.IServiceManager");
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (!this.f9074b.transact(2, obtain, obtain2, 0) && AbstractBinderC3787a.m16959a() != null) {
                        return AbstractBinderC3787a.m16959a().mo16668b(intent, str);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.bytedance.pangle.InterfaceC3786d
            /* renamed from: a */
            public final boolean mo16677a(Intent intent, InterfaceC3821f interfaceC3821f, int i, String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.bytedance.pangle.IServiceManager");
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(interfaceC3821f != null ? interfaceC3821f.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (!this.f9074b.transact(3, obtain, obtain2, 0) && AbstractBinderC3787a.m16959a() != null) {
                        return AbstractBinderC3787a.m16959a().mo16677a(intent, interfaceC3821f, i, str);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.bytedance.pangle.InterfaceC3786d
            /* renamed from: a */
            public final void mo16674a(InterfaceC3821f interfaceC3821f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.bytedance.pangle.IServiceManager");
                    obtain.writeStrongBinder(interfaceC3821f != null ? interfaceC3821f.asBinder() : null);
                    if (!this.f9074b.transact(4, obtain, obtain2, 0) && AbstractBinderC3787a.m16959a() != null) {
                        AbstractBinderC3787a.m16959a().mo16674a(interfaceC3821f);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        /* renamed from: a */
        public static InterfaceC3786d m16959a() {
            return C3788a.f9073a;
        }
    }
}
