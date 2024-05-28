package com.bytedance.pangle;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.c */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface InterfaceC3780c extends IInterface {
    /* renamed from: a */
    boolean mo16878a(String str);

    /* renamed from: a */
    boolean mo16877a(String str, String str2);

    /* renamed from: b */
    int mo16874b(String str);

    /* renamed from: com.bytedance.pangle.c$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static abstract class AbstractBinderC3781a extends Binder implements InterfaceC3780c {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public AbstractBinderC3781a() {
            attachInterface(this, "com.bytedance.pangle.IPackageManager");
        }

        /* renamed from: a */
        public static InterfaceC3780c m16962a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.bytedance.pangle.IPackageManager");
            if (queryLocalInterface != null && (queryLocalInterface instanceof InterfaceC3780c)) {
                return (InterfaceC3780c) queryLocalInterface;
            }
            return new C3782a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString("com.bytedance.pangle.IPackageManager");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.bytedance.pangle.IPackageManager");
                    boolean a = mo16878a(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(a ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface("com.bytedance.pangle.IPackageManager");
                    int b = mo16874b(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(b);
                    return true;
                case 3:
                    parcel.enforceInterface("com.bytedance.pangle.IPackageManager");
                    boolean a2 = mo16877a(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(a2 ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.bytedance.pangle.c$a$a */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        public static class C3782a implements InterfaceC3780c {

            /* renamed from: a */
            public static InterfaceC3780c f9034a;

            /* renamed from: b */
            private IBinder f9035b;

            C3782a(IBinder iBinder) {
                this.f9035b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f9035b;
            }

            @Override // com.bytedance.pangle.InterfaceC3780c
            /* renamed from: a */
            public final boolean mo16878a(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.bytedance.pangle.IPackageManager");
                    obtain.writeString(str);
                    if (!this.f9035b.transact(1, obtain, obtain2, 0) && AbstractBinderC3781a.m16963a() != null) {
                        return AbstractBinderC3781a.m16963a().mo16878a(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.bytedance.pangle.InterfaceC3780c
            /* renamed from: b */
            public final int mo16874b(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.bytedance.pangle.IPackageManager");
                    obtain.writeString(str);
                    if (!this.f9035b.transact(2, obtain, obtain2, 0) && AbstractBinderC3781a.m16963a() != null) {
                        return AbstractBinderC3781a.m16963a().mo16874b(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.bytedance.pangle.InterfaceC3780c
            /* renamed from: a */
            public final boolean mo16877a(String str, String str2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.bytedance.pangle.IPackageManager");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.f9035b.transact(3, obtain, obtain2, 0) && AbstractBinderC3781a.m16963a() != null) {
                        return AbstractBinderC3781a.m16963a().mo16877a(str, str2);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        /* renamed from: a */
        public static InterfaceC3780c m16963a() {
            return C3782a.f9034a;
        }
    }
}
