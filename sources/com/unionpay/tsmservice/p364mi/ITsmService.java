package com.unionpay.tsmservice.p364mi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.unionpay.tsmservice.p364mi.ITsmActivityCallback;
import com.unionpay.tsmservice.p364mi.ITsmCallback;
import com.unionpay.tsmservice.p364mi.ITsmProgressCallback;
import com.unionpay.tsmservice.p364mi.OnSafetyKeyboardCallback;
import com.unionpay.tsmservice.p364mi.request.AcquireSEAppListRequestParams;
import com.unionpay.tsmservice.p364mi.request.AddCardToVendorPayRequestParams;
import com.unionpay.tsmservice.p364mi.request.CancelPayRequestParams;
import com.unionpay.tsmservice.p364mi.request.CardListStatusChangedRequestParams;
import com.unionpay.tsmservice.p364mi.request.ClearEncryptDataRequestParams;
import com.unionpay.tsmservice.p364mi.request.EncryptDataRequestParams;
import com.unionpay.tsmservice.p364mi.request.GetEncryptDataRequestParams;
import com.unionpay.tsmservice.p364mi.request.GetMessageDetailsRequestParams;
import com.unionpay.tsmservice.p364mi.request.GetSeIdRequestParams;
import com.unionpay.tsmservice.p364mi.request.GetTransactionDetailsRequestParams;
import com.unionpay.tsmservice.p364mi.request.GetVendorPayStatusRequestParams;
import com.unionpay.tsmservice.p364mi.request.HideSafetyKeyboardRequestParams;
import com.unionpay.tsmservice.p364mi.request.InitRequestParams;
import com.unionpay.tsmservice.p364mi.request.OnlinePaymentVerifyRequestParams;
import com.unionpay.tsmservice.p364mi.request.PayResultNotifyRequestParams;
import com.unionpay.tsmservice.p364mi.request.PinRequestRequestParams;
import com.unionpay.tsmservice.p364mi.request.QueryVendorPayStatusRequestParams;
import com.unionpay.tsmservice.p364mi.request.SafetyKeyboardRequestParams;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.unionpay.tsmservice.mi.ITsmService */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface ITsmService extends IInterface {

    /* renamed from: com.unionpay.tsmservice.mi.ITsmService$Stub */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public abstract class Stub extends Binder implements ITsmService {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.unionpay.tsmservice.mi.ITsmService$Stub$a */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public final class C10796a implements ITsmService {

            /* renamed from: a */
            private IBinder f20783a;

            C10796a(IBinder iBinder) {
                this.f20783a = iBinder;
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmService
            public final int acquireSEAppList(AcquireSEAppListRequestParams acquireSEAppListRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (acquireSEAppListRequestParams != null) {
                        obtain.writeInt(1);
                        acquireSEAppListRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f20783a.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmService
            public final int addCardToVendorPay(AddCardToVendorPayRequestParams addCardToVendorPayRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (addCardToVendorPayRequestParams != null) {
                        obtain.writeInt(1);
                        addCardToVendorPayRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    obtain.writeStrongBinder(iTsmProgressCallback != null ? iTsmProgressCallback.asBinder() : null);
                    this.f20783a.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f20783a;
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmService
            public final int cancelPay(CancelPayRequestParams cancelPayRequestParams) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (cancelPayRequestParams != null) {
                        obtain.writeInt(1);
                        cancelPayRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f20783a.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmService
            public final int cardListStatusChanged(CardListStatusChangedRequestParams cardListStatusChangedRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (cardListStatusChangedRequestParams != null) {
                        obtain.writeInt(1);
                        cardListStatusChangedRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f20783a.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmService
            public final int clearEncryptData(int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    obtain.writeInt(i);
                    this.f20783a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmService
            public final int clearKeyboardEncryptData(ClearEncryptDataRequestParams clearEncryptDataRequestParams, int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (clearEncryptDataRequestParams != null) {
                        obtain.writeInt(1);
                        clearEncryptDataRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    this.f20783a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmService
            public final int encryptData(EncryptDataRequestParams encryptDataRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (encryptDataRequestParams != null) {
                        obtain.writeInt(1);
                        encryptDataRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f20783a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmService
            public final int exchangeKey(String str, String[] strArr) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    obtain.writeString(str);
                    if (strArr == null) {
                        obtain.writeInt(-1);
                    } else {
                        obtain.writeInt(strArr.length);
                    }
                    this.f20783a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.readStringArray(strArr);
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmService
            public final int getEncryptData(GetEncryptDataRequestParams getEncryptDataRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (getEncryptDataRequestParams != null) {
                        obtain.writeInt(1);
                        getEncryptDataRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f20783a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmService
            public final int getMessageDetails(GetMessageDetailsRequestParams getMessageDetailsRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (getMessageDetailsRequestParams != null) {
                        obtain.writeInt(1);
                        getMessageDetailsRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f20783a.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmService
            public final int getPubKey(int i, String[] strArr) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    obtain.writeInt(i);
                    if (strArr == null) {
                        obtain.writeInt(-1);
                    } else {
                        obtain.writeInt(strArr.length);
                    }
                    this.f20783a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.readStringArray(strArr);
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmService
            public final int getSEId(GetSeIdRequestParams getSeIdRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (getSeIdRequestParams != null) {
                        obtain.writeInt(1);
                        getSeIdRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f20783a.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmService
            public final int getTransactionDetails(GetTransactionDetailsRequestParams getTransactionDetailsRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (getTransactionDetailsRequestParams != null) {
                        obtain.writeInt(1);
                        getTransactionDetailsRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f20783a.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmService
            public final int getVendorPayStatus(GetVendorPayStatusRequestParams getVendorPayStatusRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (getVendorPayStatusRequestParams != null) {
                        obtain.writeInt(1);
                        getVendorPayStatusRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f20783a.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmService
            public final int getVendorPayStatusForBankApp(GetVendorPayStatusRequestParams getVendorPayStatusRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (getVendorPayStatusRequestParams != null) {
                        obtain.writeInt(1);
                        getVendorPayStatusRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f20783a.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmService
            public final int hideKeyboard() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    this.f20783a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmService
            public final int hideSafetyKeyboard(HideSafetyKeyboardRequestParams hideSafetyKeyboardRequestParams) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (hideSafetyKeyboardRequestParams != null) {
                        obtain.writeInt(1);
                        hideSafetyKeyboardRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f20783a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmService
            public final int init(InitRequestParams initRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (initRequestParams != null) {
                        obtain.writeInt(1);
                        initRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f20783a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmService
            public final int onlinePaymentVerify(OnlinePaymentVerifyRequestParams onlinePaymentVerifyRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (onlinePaymentVerifyRequestParams != null) {
                        obtain.writeInt(1);
                        onlinePaymentVerifyRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f20783a.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmService
            public final int payResultNotify(PayResultNotifyRequestParams payResultNotifyRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (payResultNotifyRequestParams != null) {
                        obtain.writeInt(1);
                        payResultNotifyRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f20783a.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmService
            public final int pinRequest(PinRequestRequestParams pinRequestRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (pinRequestRequestParams != null) {
                        obtain.writeInt(1);
                        pinRequestRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f20783a.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmService
            public final int queryVendorPayStatus(QueryVendorPayStatusRequestParams queryVendorPayStatusRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (queryVendorPayStatusRequestParams != null) {
                        obtain.writeInt(1);
                        queryVendorPayStatusRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f20783a.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmService
            public final int setSafetyKeyboardBitmap(SafetyKeyboardRequestParams safetyKeyboardRequestParams) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (safetyKeyboardRequestParams != null) {
                        obtain.writeInt(1);
                        safetyKeyboardRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f20783a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmService
            public final int showSafetyKeyboard(SafetyKeyboardRequestParams safetyKeyboardRequestParams, int i, OnSafetyKeyboardCallback onSafetyKeyboardCallback, ITsmActivityCallback iTsmActivityCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (safetyKeyboardRequestParams != null) {
                        obtain.writeInt(1);
                        safetyKeyboardRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(onSafetyKeyboardCallback != null ? onSafetyKeyboardCallback.asBinder() : null);
                    obtain.writeStrongBinder(iTsmActivityCallback != null ? iTsmActivityCallback.asBinder() : null);
                    this.f20783a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.unionpay.tsmservice.mi.ITsmService");
        }

        public static ITsmService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.unionpay.tsmservice.mi.ITsmService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ITsmService)) ? new C10796a(iBinder) : (ITsmService) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString("com.unionpay.tsmservice.mi.ITsmService");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmService");
                    int init = init(parcel.readInt() != 0 ? (InitRequestParams) InitRequestParams.CREATOR.createFromParcel(parcel) : null, ITsmCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(init);
                    return true;
                case 2:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmService");
                    int readInt = parcel.readInt();
                    int readInt2 = parcel.readInt();
                    String[] strArr = readInt2 >= 0 ? new String[readInt2] : null;
                    int pubKey = getPubKey(readInt, strArr);
                    parcel2.writeNoException();
                    parcel2.writeInt(pubKey);
                    parcel2.writeStringArray(strArr);
                    return true;
                case 3:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmService");
                    String readString = parcel.readString();
                    int readInt3 = parcel.readInt();
                    String[] strArr2 = readInt3 >= 0 ? new String[readInt3] : null;
                    int exchangeKey = exchangeKey(readString, strArr2);
                    parcel2.writeNoException();
                    parcel2.writeInt(exchangeKey);
                    parcel2.writeStringArray(strArr2);
                    return true;
                case 4:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmService");
                    int encryptData = encryptData(parcel.readInt() != 0 ? (EncryptDataRequestParams) EncryptDataRequestParams.CREATOR.createFromParcel(parcel) : null, ITsmCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(encryptData);
                    return true;
                case 5:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmService");
                    int showSafetyKeyboard = showSafetyKeyboard(parcel.readInt() != 0 ? (SafetyKeyboardRequestParams) SafetyKeyboardRequestParams.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), OnSafetyKeyboardCallback.Stub.asInterface(parcel.readStrongBinder()), ITsmActivityCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(showSafetyKeyboard);
                    return true;
                case 6:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmService");
                    int safetyKeyboardBitmap = setSafetyKeyboardBitmap(parcel.readInt() != 0 ? (SafetyKeyboardRequestParams) SafetyKeyboardRequestParams.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(safetyKeyboardBitmap);
                    return true;
                case 7:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmService");
                    int encryptData2 = getEncryptData(parcel.readInt() != 0 ? (GetEncryptDataRequestParams) GetEncryptDataRequestParams.CREATOR.createFromParcel(parcel) : null, ITsmCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(encryptData2);
                    return true;
                case 8:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmService");
                    int clearEncryptData = clearEncryptData(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(clearEncryptData);
                    return true;
                case 9:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmService");
                    int hideKeyboard = hideKeyboard();
                    parcel2.writeNoException();
                    parcel2.writeInt(hideKeyboard);
                    return true;
                case 10:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmService");
                    int clearKeyboardEncryptData = clearKeyboardEncryptData(parcel.readInt() != 0 ? (ClearEncryptDataRequestParams) ClearEncryptDataRequestParams.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(clearKeyboardEncryptData);
                    return true;
                case 11:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmService");
                    int hideSafetyKeyboard = hideSafetyKeyboard(parcel.readInt() != 0 ? (HideSafetyKeyboardRequestParams) HideSafetyKeyboardRequestParams.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(hideSafetyKeyboard);
                    return true;
                case 12:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmService");
                    int acquireSEAppList = acquireSEAppList(parcel.readInt() != 0 ? (AcquireSEAppListRequestParams) AcquireSEAppListRequestParams.CREATOR.createFromParcel(parcel) : null, ITsmCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(acquireSEAppList);
                    return true;
                case 13:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmService");
                    int cardListStatusChanged = cardListStatusChanged(parcel.readInt() != 0 ? (CardListStatusChangedRequestParams) CardListStatusChangedRequestParams.CREATOR.createFromParcel(parcel) : null, ITsmCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(cardListStatusChanged);
                    return true;
                case 14:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmService");
                    int queryVendorPayStatus = queryVendorPayStatus(parcel.readInt() != 0 ? (QueryVendorPayStatusRequestParams) QueryVendorPayStatusRequestParams.CREATOR.createFromParcel(parcel) : null, ITsmCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(queryVendorPayStatus);
                    return true;
                case 15:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmService");
                    int vendorPayStatus = getVendorPayStatus(parcel.readInt() != 0 ? (GetVendorPayStatusRequestParams) GetVendorPayStatusRequestParams.CREATOR.createFromParcel(parcel) : null, ITsmCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(vendorPayStatus);
                    return true;
                case 16:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmService");
                    int onlinePaymentVerify = onlinePaymentVerify(parcel.readInt() != 0 ? (OnlinePaymentVerifyRequestParams) OnlinePaymentVerifyRequestParams.CREATOR.createFromParcel(parcel) : null, ITsmCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(onlinePaymentVerify);
                    return true;
                case 17:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmService");
                    int pinRequest = pinRequest(parcel.readInt() != 0 ? (PinRequestRequestParams) PinRequestRequestParams.CREATOR.createFromParcel(parcel) : null, ITsmCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(pinRequest);
                    return true;
                case 18:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmService");
                    int cancelPay = cancelPay(parcel.readInt() != 0 ? (CancelPayRequestParams) CancelPayRequestParams.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(cancelPay);
                    return true;
                case 19:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmService");
                    int payResultNotify = payResultNotify(parcel.readInt() != 0 ? (PayResultNotifyRequestParams) PayResultNotifyRequestParams.CREATOR.createFromParcel(parcel) : null, ITsmCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(payResultNotify);
                    return true;
                case 20:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmService");
                    int vendorPayStatusForBankApp = getVendorPayStatusForBankApp(parcel.readInt() != 0 ? (GetVendorPayStatusRequestParams) GetVendorPayStatusRequestParams.CREATOR.createFromParcel(parcel) : null, ITsmCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(vendorPayStatusForBankApp);
                    return true;
                case 21:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmService");
                    int sEId = getSEId(parcel.readInt() != 0 ? (GetSeIdRequestParams) GetSeIdRequestParams.CREATOR.createFromParcel(parcel) : null, ITsmCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(sEId);
                    return true;
                case 22:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmService");
                    int addCardToVendorPay = addCardToVendorPay(parcel.readInt() != 0 ? (AddCardToVendorPayRequestParams) AddCardToVendorPayRequestParams.CREATOR.createFromParcel(parcel) : null, ITsmCallback.Stub.asInterface(parcel.readStrongBinder()), ITsmProgressCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(addCardToVendorPay);
                    return true;
                case 23:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmService");
                    int transactionDetails = getTransactionDetails(parcel.readInt() != 0 ? (GetTransactionDetailsRequestParams) GetTransactionDetailsRequestParams.CREATOR.createFromParcel(parcel) : null, ITsmCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(transactionDetails);
                    return true;
                case 24:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmService");
                    int messageDetails = getMessageDetails(parcel.readInt() != 0 ? (GetMessageDetailsRequestParams) GetMessageDetailsRequestParams.CREATOR.createFromParcel(parcel) : null, ITsmCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(messageDetails);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    int acquireSEAppList(AcquireSEAppListRequestParams acquireSEAppListRequestParams, ITsmCallback iTsmCallback);

    int addCardToVendorPay(AddCardToVendorPayRequestParams addCardToVendorPayRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback);

    int cancelPay(CancelPayRequestParams cancelPayRequestParams);

    int cardListStatusChanged(CardListStatusChangedRequestParams cardListStatusChangedRequestParams, ITsmCallback iTsmCallback);

    int clearEncryptData(int i);

    int clearKeyboardEncryptData(ClearEncryptDataRequestParams clearEncryptDataRequestParams, int i);

    int encryptData(EncryptDataRequestParams encryptDataRequestParams, ITsmCallback iTsmCallback);

    int exchangeKey(String str, String[] strArr);

    int getEncryptData(GetEncryptDataRequestParams getEncryptDataRequestParams, ITsmCallback iTsmCallback);

    int getMessageDetails(GetMessageDetailsRequestParams getMessageDetailsRequestParams, ITsmCallback iTsmCallback);

    int getPubKey(int i, String[] strArr);

    int getSEId(GetSeIdRequestParams getSeIdRequestParams, ITsmCallback iTsmCallback);

    int getTransactionDetails(GetTransactionDetailsRequestParams getTransactionDetailsRequestParams, ITsmCallback iTsmCallback);

    int getVendorPayStatus(GetVendorPayStatusRequestParams getVendorPayStatusRequestParams, ITsmCallback iTsmCallback);

    int getVendorPayStatusForBankApp(GetVendorPayStatusRequestParams getVendorPayStatusRequestParams, ITsmCallback iTsmCallback);

    int hideKeyboard();

    int hideSafetyKeyboard(HideSafetyKeyboardRequestParams hideSafetyKeyboardRequestParams);

    int init(InitRequestParams initRequestParams, ITsmCallback iTsmCallback);

    int onlinePaymentVerify(OnlinePaymentVerifyRequestParams onlinePaymentVerifyRequestParams, ITsmCallback iTsmCallback);

    int payResultNotify(PayResultNotifyRequestParams payResultNotifyRequestParams, ITsmCallback iTsmCallback);

    int pinRequest(PinRequestRequestParams pinRequestRequestParams, ITsmCallback iTsmCallback);

    int queryVendorPayStatus(QueryVendorPayStatusRequestParams queryVendorPayStatusRequestParams, ITsmCallback iTsmCallback);

    int setSafetyKeyboardBitmap(SafetyKeyboardRequestParams safetyKeyboardRequestParams);

    int showSafetyKeyboard(SafetyKeyboardRequestParams safetyKeyboardRequestParams, int i, OnSafetyKeyboardCallback onSafetyKeyboardCallback, ITsmActivityCallback iTsmActivityCallback);
}
