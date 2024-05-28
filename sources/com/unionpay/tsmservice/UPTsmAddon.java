package com.unionpay.tsmservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Base64;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.unionpay.tsmservice.ITsmActivityCallback;
import com.unionpay.tsmservice.ITsmCallback;
import com.unionpay.tsmservice.ITsmService;
import com.unionpay.tsmservice.data.Amount;
import com.unionpay.tsmservice.request.AcquireSEAppListRequestParams;
import com.unionpay.tsmservice.request.ActivateVendorPayRequestParams;
import com.unionpay.tsmservice.request.AddCardToVendorPayRequestParams;
import com.unionpay.tsmservice.request.AppDataUpdateRequestParams;
import com.unionpay.tsmservice.request.AppDeleteRequestParams;
import com.unionpay.tsmservice.request.AppDownloadApplyRequestParams;
import com.unionpay.tsmservice.request.AppDownloadRequestParams;
import com.unionpay.tsmservice.request.AppLockRequestParams;
import com.unionpay.tsmservice.request.AppUnlockRequestParams;
import com.unionpay.tsmservice.request.CardListStatusChangedRequestParams;
import com.unionpay.tsmservice.request.CheckSSamsungPayRequestParams;
import com.unionpay.tsmservice.request.CloseChannelRequestParams;
import com.unionpay.tsmservice.request.ECashTopUpRequestParams;
import com.unionpay.tsmservice.request.EncryptDataRequestParams;
import com.unionpay.tsmservice.request.ExecuteCmdRequestParams;
import com.unionpay.tsmservice.request.GetAccountBalanceRequestParams;
import com.unionpay.tsmservice.request.GetAccountInfoRequestParams;
import com.unionpay.tsmservice.request.GetAppDetailRequestParams;
import com.unionpay.tsmservice.request.GetAppListRequestParams;
import com.unionpay.tsmservice.request.GetAppStatusRequestParams;
import com.unionpay.tsmservice.request.GetAssociatedAppRequestParams;
import com.unionpay.tsmservice.request.GetCardInfoBySpayRequestParams;
import com.unionpay.tsmservice.request.GetCardInfoRequestParams;
import com.unionpay.tsmservice.request.GetDefaultCardRequestParams;
import com.unionpay.tsmservice.request.GetEncryptDataRequestParams;
import com.unionpay.tsmservice.request.GetMessageDetailsRequestParams;
import com.unionpay.tsmservice.request.GetSMSAuthCodeRequestParams;
import com.unionpay.tsmservice.request.GetSeAppListRequestParams;
import com.unionpay.tsmservice.request.GetSeIdRequestParams;
import com.unionpay.tsmservice.request.GetTransElementsRequestParams;
import com.unionpay.tsmservice.request.GetTransRecordRequestParams;
import com.unionpay.tsmservice.request.GetTransactionDetailsRequestParams;
import com.unionpay.tsmservice.request.GetVendorPayStatusRequestParams;
import com.unionpay.tsmservice.request.HideAppApplyRequestParams;
import com.unionpay.tsmservice.request.InitRequestParams;
import com.unionpay.tsmservice.request.OnlinePaymentVerifyRequestParams;
import com.unionpay.tsmservice.request.OpenChannelRequestParams;
import com.unionpay.tsmservice.request.PreDownloadRequestParams;
import com.unionpay.tsmservice.request.QueryVendorPayStatusRequestParams;
import com.unionpay.tsmservice.request.RequestParams;
import com.unionpay.tsmservice.request.SafetyKeyboardRequestParams;
import com.unionpay.tsmservice.request.SendApduRequestParams;
import com.unionpay.tsmservice.request.SendCustomDataRequestParams;
import com.unionpay.tsmservice.request.SetDefaultCardRequestParams;
import com.unionpay.tsmservice.request.SetSamsungDefWalletRequestParams;
import com.unionpay.tsmservice.request.UniteRequestParams;
import com.unionpay.tsmservice.utils.IUPJniInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class UPTsmAddon {

    /* renamed from: a */
    private static UPTsmAddon f20716a;

    /* renamed from: b */
    private static CopyOnWriteArrayList f20717b;

    /* renamed from: c */
    private Context f20746c;

    /* renamed from: d */
    private ServiceConnection f20747d = null;

    /* renamed from: e */
    private ITsmService f20748e = null;

    /* renamed from: f */
    private boolean f20749f = false;

    /* renamed from: g */
    private int f20750g = 1;

    /* renamed from: h */
    private boolean f20751h = false;

    /* renamed from: i */
    private HashMap f20752i = new HashMap();

    /* renamed from: j */
    private HashMap f20753j = new HashMap();

    /* renamed from: k */
    private HashMap f20754k = new HashMap();

    /* renamed from: l */
    private HashMap f20755l = new HashMap();

    /* renamed from: m */
    private HashMap f20756m = new HashMap();

    /* renamed from: n */
    private HashMap f20757n = new HashMap();

    /* renamed from: o */
    private HashMap f20758o = new HashMap();

    /* renamed from: p */
    private HashMap f20759p = new HashMap();

    /* renamed from: q */
    private HashMap f20760q = new HashMap();

    /* renamed from: r */
    private HashMap f20761r = new HashMap();

    /* renamed from: s */
    private HashMap f20762s = new HashMap();

    /* renamed from: t */
    private HashMap f20763t = new HashMap();

    /* renamed from: u */
    private HashMap f20764u = new HashMap();

    /* renamed from: v */
    private HashMap f20765v = new HashMap();

    /* renamed from: w */
    private HashMap f20766w = new HashMap();

    /* renamed from: x */
    private HashMap f20767x = new HashMap();

    /* renamed from: y */
    private HashMap f20768y = new HashMap();

    /* renamed from: z */
    private HashMap f20769z = new HashMap();

    /* renamed from: A */
    private HashMap f20718A = new HashMap();

    /* renamed from: B */
    private HashMap f20719B = new HashMap();

    /* renamed from: C */
    private HashMap f20720C = new HashMap();

    /* renamed from: D */
    private HashMap f20721D = new HashMap();

    /* renamed from: E */
    private HashMap f20722E = new HashMap();

    /* renamed from: F */
    private HashMap f20723F = new HashMap();

    /* renamed from: G */
    private HashMap f20724G = new HashMap();

    /* renamed from: H */
    private HashMap f20725H = new HashMap();

    /* renamed from: I */
    private HashMap f20726I = new HashMap();

    /* renamed from: J */
    private HashMap f20727J = new HashMap();

    /* renamed from: K */
    private HashMap f20728K = new HashMap();

    /* renamed from: L */
    private HashMap f20729L = new HashMap();

    /* renamed from: M */
    private HashMap f20730M = new HashMap();

    /* renamed from: N */
    private HashMap f20731N = new HashMap();

    /* renamed from: O */
    private HashMap f20732O = new HashMap();

    /* renamed from: P */
    private HashMap f20733P = new HashMap();

    /* renamed from: Q */
    private HashMap f20734Q = new HashMap();

    /* renamed from: R */
    private HashMap f20735R = new HashMap();

    /* renamed from: S */
    private HashMap f20736S = new HashMap();

    /* renamed from: T */
    private HashMap f20737T = new HashMap();

    /* renamed from: U */
    private HashMap f20738U = new HashMap();

    /* renamed from: V */
    private HashMap f20739V = new HashMap();

    /* renamed from: W */
    private HashMap f20740W = new HashMap();

    /* renamed from: X */
    private HashMap f20741X = new HashMap();

    /* renamed from: Y */
    private HashMap f20742Y = new HashMap();

    /* renamed from: aa */
    private final Handler.Callback f20744aa = new Handler.Callback() { // from class: com.unionpay.tsmservice.UPTsmAddon.1
        @Override // android.os.Handler.Callback
        public final synchronized boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    UPTsmAddon.this.m5924a();
                    return true;
                case 1:
                    UPTsmAddon.this.m5909b();
                    return true;
                default:
                    return false;
            }
        }
    };

    /* renamed from: ab */
    private final Handler f20745ab = new Handler(Looper.getMainLooper(), this.f20744aa);

    /* renamed from: Z */
    private int[] f20743Z = new int[47];

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface UPTsmConnectionListener {
        void onTsmConnected();

        void onTsmDisconnected();
    }

    /* renamed from: com.unionpay.tsmservice.UPTsmAddon$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public final class BinderC10779a extends ITsmActivityCallback.Stub {

        /* renamed from: b */
        private int f20773b = 1000;

        public BinderC10779a() {
        }

        @Override // com.unionpay.tsmservice.ITsmActivityCallback
        public final void startActivity(String str, String str2, int i, Bundle bundle) {
            UPTsmAddon.m5918a((ITsmActivityCallback) UPTsmAddon.m5907b(UPTsmAddon.this, this.f20773b).get(UPTsmAddon.m5900e(UPTsmAddon.this)), str, str2, i, bundle);
            UPTsmAddon.m5907b(UPTsmAddon.this, this.f20773b).remove(UPTsmAddon.m5900e(UPTsmAddon.this));
        }
    }

    /* renamed from: com.unionpay.tsmservice.UPTsmAddon$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    final class BinderC10780b extends ITsmCallback.Stub {

        /* renamed from: b */
        private int f20775b;

        /* renamed from: c */
        private int f20776c;

        private BinderC10780b(int i, int i2) {
            this.f20775b = i;
            this.f20776c = i2;
        }

        /* synthetic */ BinderC10780b(UPTsmAddon uPTsmAddon, int i, int i2, byte b) {
            this(i, i2);
        }

        @Override // com.unionpay.tsmservice.ITsmCallback
        public final void onError(String str, String str2) {
            Bundle bundle = new Bundle();
            bundle.putString("errorCode", str);
            bundle.putString("errorDesc", str2);
            UPTsmAddon.m5917a((ITsmCallback) UPTsmAddon.m5915a(UPTsmAddon.this, this.f20775b).get(String.valueOf(this.f20776c)), bundle);
            UPTsmAddon.m5915a(UPTsmAddon.this, this.f20775b).remove(String.valueOf(this.f20776c));
            if (UPTsmAddon.m5915a(UPTsmAddon.this, this.f20775b).isEmpty()) {
                UPTsmAddon.this.f20743Z[this.f20775b] = 0;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:49:0x0193  */
        /* JADX WARN: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
        @Override // com.unionpay.tsmservice.ITsmCallback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void onResult(android.os.Bundle r7) {
            /*
                Method dump skipped, instructions count: 454
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.BinderC10780b.onResult(android.os.Bundle):void");
        }
    }

    static {
        try {
            System.loadLibrary("uptsmaddon");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
        f20716a = null;
        f20717b = null;
    }

    private UPTsmAddon(Context context) {
        this.f20746c = null;
        this.f20746c = context;
        if (!m5920a(context)) {
            throw new RuntimeException();
        }
    }

    /* renamed from: a */
    private static int m5923a(int i, RequestParams requestParams, ITsmCallback iTsmCallback) {
        return new SessionKeyReExchange(f20716a, i, requestParams, iTsmCallback).reExchangeKey();
    }

    /* renamed from: a */
    private static int m5922a(int i, RequestParams requestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) {
        return new SessionKeyReExchange(f20716a, i, requestParams, iTsmCallback, iTsmProgressCallback).reExchangeKey();
    }

    /* renamed from: a */
    private static int m5921a(int i, SafetyKeyboardRequestParams safetyKeyboardRequestParams, int i2, OnSafetyKeyboardCallback onSafetyKeyboardCallback, Context context) {
        return new SessionKeyReExchange(f20716a, i, safetyKeyboardRequestParams, i2, onSafetyKeyboardCallback, context).reExchangeKey();
    }

    /* renamed from: a */
    private String m5919a(Bundle bundle) {
        String str = "";
        Parcel obtain = Parcel.obtain();
        obtain.writeBundle(bundle);
        byte[] marshall = obtain.marshall();
        if (marshall != null && marshall.length != 0) {
            str = m5906b(Base64.encodeToString(marshall, 0));
        }
        obtain.recycle();
        return str;
    }

    /* renamed from: a */
    static /* synthetic */ HashMap m5915a(UPTsmAddon uPTsmAddon, int i) {
        switch (i) {
            case 0:
                return uPTsmAddon.f20752i;
            case 1:
                return uPTsmAddon.f20753j;
            case 2:
                return uPTsmAddon.f20755l;
            case 3:
                return uPTsmAddon.f20754k;
            case 4:
                return uPTsmAddon.f20757n;
            case 5:
                return uPTsmAddon.f20756m;
            case 6:
                return uPTsmAddon.f20718A;
            case 7:
                return uPTsmAddon.f20764u;
            case 8:
                return uPTsmAddon.f20765v;
            case 9:
                return uPTsmAddon.f20758o;
            case 10:
                return uPTsmAddon.f20763t;
            case 11:
                return uPTsmAddon.f20761r;
            case 12:
                return uPTsmAddon.f20769z;
            case 13:
                return uPTsmAddon.f20768y;
            case 14:
                return uPTsmAddon.f20722E;
            case 15:
                return uPTsmAddon.f20759p;
            case 16:
                return uPTsmAddon.f20720C;
            case 17:
                return uPTsmAddon.f20721D;
            case 18:
                return uPTsmAddon.f20760q;
            case 19:
                return uPTsmAddon.f20762s;
            case 20:
                return uPTsmAddon.f20766w;
            case 21:
                return uPTsmAddon.f20723F;
            case 22:
                return uPTsmAddon.f20767x;
            case 23:
                return uPTsmAddon.f20719B;
            case 24:
                return uPTsmAddon.f20724G;
            case 25:
                return uPTsmAddon.f20725H;
            case 26:
            case 27:
            case 32:
            case 33:
            case 34:
            default:
                return null;
            case 28:
                return uPTsmAddon.f20726I;
            case 29:
                return uPTsmAddon.f20727J;
            case 30:
                return uPTsmAddon.f20728K;
            case 31:
                return uPTsmAddon.f20729L;
            case 35:
                return uPTsmAddon.f20730M;
            case 36:
                return uPTsmAddon.f20731N;
            case 37:
                return uPTsmAddon.f20732O;
            case 38:
                return uPTsmAddon.f20733P;
            case 39:
                return uPTsmAddon.f20734Q;
            case 40:
                return uPTsmAddon.f20735R;
            case 41:
                return uPTsmAddon.f20736S;
            case 42:
                return uPTsmAddon.f20737T;
            case 43:
                return uPTsmAddon.f20738U;
            case 44:
                return uPTsmAddon.f20739V;
            case 45:
                return uPTsmAddon.f20740W;
            case 46:
                return uPTsmAddon.f20741X;
        }
    }

    /* renamed from: a */
    private static HashMap m5910a(HashMap hashMap) {
        String str;
        if (hashMap == null) {
            return new HashMap();
        }
        HashMap hashMap2 = new HashMap();
        for (String str2 : hashMap.keySet()) {
            if (str2 != null && (str = (String) hashMap.get(str2)) != null) {
                hashMap2.put(new String(str2), new String(str));
            }
        }
        return hashMap2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public synchronized void m5924a() {
        if (f20717b != null && f20717b.size() > 0) {
            Iterator it = f20717b.iterator();
            while (it.hasNext()) {
                UPTsmConnectionListener uPTsmConnectionListener = (UPTsmConnectionListener) it.next();
                if (uPTsmConnectionListener != null) {
                    uPTsmConnectionListener.onTsmConnected();
                }
            }
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m5918a(ITsmActivityCallback iTsmActivityCallback, String str, String str2, int i, Bundle bundle) {
        if (iTsmActivityCallback != null) {
            try {
                iTsmActivityCallback.startActivity(str, str2, i, bundle);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m5917a(ITsmCallback iTsmCallback, Bundle bundle) {
        if (iTsmCallback != null) {
            try {
                String string = bundle.getString("errorCode");
                if ("10000".equals(string)) {
                    iTsmCallback.onResult(bundle);
                } else {
                    iTsmCallback.onError(string, bundle.getString("errorDesc"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private static boolean m5920a(Context context) {
        try {
            return IUPJniInterface.iJE(context);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    private static boolean m5911a(String str) {
        try {
            return IUPJniInterface.cSKV(str);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    private String m5906b(String str) {
        try {
            return IUPJniInterface.eMG(str, this.f20750g);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: b */
    static /* synthetic */ HashMap m5907b(UPTsmAddon uPTsmAddon, int i) {
        if (i != 1000) {
            return null;
        }
        return uPTsmAddon.f20742Y;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public synchronized void m5909b() {
        if (f20717b != null && f20717b.size() > 0) {
            Iterator it = f20717b.iterator();
            while (it.hasNext()) {
                UPTsmConnectionListener uPTsmConnectionListener = (UPTsmConnectionListener) it.next();
                if (uPTsmConnectionListener != null) {
                    uPTsmConnectionListener.onTsmDisconnected();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public String m5903c(String str) {
        try {
            return IUPJniInterface.dMG(str, this.f20750g);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: c */
    private boolean m5905c() {
        String m5899e = m5899e("com.unionpay.tsmservice");
        if (m5899e == null || m5899e.compareTo("01.00.11") < 0) {
            return false;
        }
        if (m5899e.compareTo("01.00.18") >= 0) {
            this.f20750g = 1;
            this.f20751h = true;
            return true;
        } else if (m5899e.compareTo("01.00.12") >= 0 && m5899e.compareTo("01.00.16") <= 0) {
            this.f20750g = 2;
            this.f20751h = false;
            return true;
        } else if (m5899e.compareTo("01.00.17") == 0 || m5899e.compareTo("01.00.11") == 0) {
            this.f20750g = 1;
            this.f20751h = false;
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: d */
    private boolean m5901d(String str) {
        String m5899e = m5899e("com.unionpay.tsmservice");
        return m5899e != null && m5899e.compareTo(str) >= 0;
    }

    /* renamed from: e */
    static /* synthetic */ String m5900e(UPTsmAddon uPTsmAddon) {
        return uPTsmAddon.f20746c.getPackageName();
    }

    /* renamed from: e */
    private String m5899e(String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = this.f20746c.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo != null) {
            return packageInfo.versionName;
        }
        return null;
    }

    /* renamed from: f */
    private static String m5898f(String str) {
        try {
            JSONObject jSONObject = TextUtils.isEmpty(str) ? new JSONObject() : new JSONObject(str);
            jSONObject.put("jarVersionCode", 52);
            return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
            return str;
        }
    }

    /* renamed from: g */
    private String m5897g(String str) {
        try {
            JSONObject jSONObject = TextUtils.isEmpty(str) ? new JSONObject() : new JSONObject(str);
            jSONObject.put("packageName", this.f20746c.getPackageName());
            return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
            return str;
        }
    }

    public static synchronized UPTsmAddon getInstance(Context context) {
        synchronized (UPTsmAddon.class) {
            if (context == null) {
                return null;
            }
            if (f20716a == null) {
                f20716a = new UPTsmAddon(context.getApplicationContext());
            }
            if (f20717b == null) {
                f20717b = new CopyOnWriteArrayList();
            }
            return f20716a;
        }
    }

    public synchronized int acquireSEAppList(AcquireSEAppListRequestParams acquireSEAppListRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else if (!m5901d("01.00.28")) {
            return -8;
        } else {
            if (!m5905c()) {
                return -8;
            }
            if (this.f20748e != null) {
                if (!m5911a(this.f20746c.getPackageName())) {
                    return m5923a(42, acquireSEAppListRequestParams, iTsmCallback);
                }
                AcquireSEAppListRequestParams acquireSEAppListRequestParams2 = new AcquireSEAppListRequestParams();
                String str = "";
                if (acquireSEAppListRequestParams != null) {
                    str = acquireSEAppListRequestParams.getReserve();
                    Bundle params = acquireSEAppListRequestParams.getParams();
                    if (params != null) {
                        Bundle bundle = new Bundle();
                        bundle.putString("encryptData", m5919a(params));
                        acquireSEAppListRequestParams2.setParams(bundle);
                    }
                }
                if (this.f20751h) {
                    str = m5897g(m5898f(str));
                }
                if (!TextUtils.isEmpty(str)) {
                    acquireSEAppListRequestParams2.setReserve(m5906b(str));
                }
                this.f20737T.put(String.valueOf(this.f20743Z[42]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f20748e;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[42];
                    iArr[42] = i2 + 1;
                    int acquireSEAppList = iTsmService.acquireSEAppList(acquireSEAppListRequestParams2, new BinderC10780b(this, 42, i2, (byte) 0));
                    if (acquireSEAppList != 0) {
                        HashMap hashMap = this.f20737T;
                        int[] iArr2 = this.f20743Z;
                        int i3 = iArr2[42] - 1;
                        iArr2[42] = i3;
                        hashMap.remove(String.valueOf(i3));
                    }
                    if (-2 == acquireSEAppList) {
                        return m5923a(42, acquireSEAppListRequestParams, iTsmCallback);
                    }
                    return acquireSEAppList;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RemoteException();
                }
            }
            i = -1;
        }
        return i;
    }

    public synchronized int activateVendorPay(ActivateVendorPayRequestParams activateVendorPayRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else if (!m5901d("01.00.20")) {
            return -8;
        } else {
            if (!m5905c()) {
                return -8;
            }
            if (this.f20748e != null) {
                if (!m5911a(this.f20746c.getPackageName())) {
                    return m5923a(37, activateVendorPayRequestParams, iTsmCallback);
                }
                ActivateVendorPayRequestParams activateVendorPayRequestParams2 = new ActivateVendorPayRequestParams();
                String reserve = activateVendorPayRequestParams != null ? activateVendorPayRequestParams.getReserve() : "";
                if (this.f20751h) {
                    reserve = m5897g(m5898f(reserve));
                }
                if (!TextUtils.isEmpty(reserve)) {
                    activateVendorPayRequestParams2.setReserve(m5906b(reserve));
                }
                this.f20732O.put(String.valueOf(this.f20743Z[37]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f20748e;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[37];
                    iArr[37] = i2 + 1;
                    int activateVendorPay = iTsmService.activateVendorPay(activateVendorPayRequestParams2, new BinderC10780b(this, 37, i2, (byte) 0));
                    if (activateVendorPay != 0) {
                        HashMap hashMap = this.f20732O;
                        int[] iArr2 = this.f20743Z;
                        int i3 = iArr2[37] - 1;
                        iArr2[37] = i3;
                        hashMap.remove(String.valueOf(i3));
                    }
                    if (-2 == activateVendorPay) {
                        return m5923a(37, activateVendorPayRequestParams, iTsmCallback);
                    }
                    return activateVendorPay;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RemoteException();
                }
            }
            i = -1;
        }
        return i;
    }

    public synchronized int addCardToVendorPay(AddCardToVendorPayRequestParams addCardToVendorPayRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) {
        int i;
        if (addCardToVendorPayRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else if (!m5901d("01.00.20")) {
            return -8;
        } else {
            if (!m5905c()) {
                return -8;
            }
            if (this.f20748e != null) {
                if (!m5911a(this.f20746c.getPackageName())) {
                    return m5923a(38, addCardToVendorPayRequestParams, iTsmCallback);
                }
                AddCardToVendorPayRequestParams addCardToVendorPayRequestParams2 = new AddCardToVendorPayRequestParams();
                Bundle params = addCardToVendorPayRequestParams.getParams();
                if (params != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("encryptData", m5919a(params));
                    addCardToVendorPayRequestParams2.setParams(bundle);
                }
                String reserve = addCardToVendorPayRequestParams.getReserve();
                if (this.f20751h) {
                    reserve = m5897g(m5898f(reserve));
                }
                if (!TextUtils.isEmpty(reserve)) {
                    addCardToVendorPayRequestParams2.setReserve(m5906b(reserve));
                }
                this.f20733P.put(String.valueOf(this.f20743Z[38]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f20748e;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[38];
                    iArr[38] = i2 + 1;
                    int addCardToVendorPay = iTsmService.addCardToVendorPay(addCardToVendorPayRequestParams2, new BinderC10780b(this, 38, i2, (byte) 0), iTsmProgressCallback);
                    if (addCardToVendorPay != 0) {
                        HashMap hashMap = this.f20733P;
                        int[] iArr2 = this.f20743Z;
                        int i3 = iArr2[38] - 1;
                        iArr2[38] = i3;
                        hashMap.remove(String.valueOf(i3));
                    }
                    if (-2 == addCardToVendorPay) {
                        return m5922a(38, addCardToVendorPayRequestParams, iTsmCallback, iTsmProgressCallback);
                    }
                    return addCardToVendorPay;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RemoteException();
                }
            }
            i = -1;
        }
        return i;
    }

    public synchronized void addConnectionListener(UPTsmConnectionListener uPTsmConnectionListener) {
        if (uPTsmConnectionListener != null) {
            f20717b.add(uPTsmConnectionListener);
        }
    }

    @Deprecated
    public synchronized int appDataUpdate(AppDataUpdateRequestParams appDataUpdateRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) {
        int i;
        if (appDataUpdateRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5922a(18, appDataUpdateRequestParams, iTsmCallback, iTsmProgressCallback);
            }
            AppDataUpdateRequestParams appDataUpdateRequestParams2 = new AppDataUpdateRequestParams();
            String reserve = appDataUpdateRequestParams.getReserve();
            AppID appID = appDataUpdateRequestParams.getAppID();
            if (!TextUtils.isEmpty(reserve)) {
                appDataUpdateRequestParams2.setReserve(m5906b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    appDataUpdateRequestParams2.setAppID(new AppID(m5906b(appAid), m5906b(appVersion)));
                }
            }
            try {
                int appDataUpdate = this.f20748e.appDataUpdate(appDataUpdateRequestParams2, new BinderC10780b(this, 18, this.f20743Z[18], (byte) 0), iTsmProgressCallback);
                if (-2 == appDataUpdate) {
                    return m5922a(18, appDataUpdateRequestParams, iTsmCallback, iTsmProgressCallback);
                }
                if (appDataUpdate == 0) {
                    HashMap hashMap = this.f20760q;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[18];
                    iArr[18] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return appDataUpdate;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    @Deprecated
    public synchronized int appDelete(AppDeleteRequestParams appDeleteRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) {
        int i;
        if (appDeleteRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5922a(17, appDeleteRequestParams, iTsmCallback, iTsmProgressCallback);
            }
            AppDeleteRequestParams appDeleteRequestParams2 = new AppDeleteRequestParams();
            String reserve = appDeleteRequestParams.getReserve();
            AppID appID = appDeleteRequestParams.getAppID();
            if (!TextUtils.isEmpty(reserve)) {
                appDeleteRequestParams2.setReserve(m5906b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    appDeleteRequestParams2.setAppID(new AppID(m5906b(appAid), m5906b(appVersion)));
                }
            }
            HashMap hashMap = (HashMap) appDeleteRequestParams.getParams();
            if (hashMap == null) {
                try {
                    int appDelete = this.f20748e.appDelete(appDeleteRequestParams2, new BinderC10780b(this, 17, this.f20743Z[17], (byte) 0), iTsmProgressCallback);
                    if (-2 == appDelete) {
                        return m5922a(17, appDeleteRequestParams, iTsmCallback, iTsmProgressCallback);
                    }
                    if (appDelete == 0) {
                        HashMap hashMap2 = this.f20721D;
                        int[] iArr = this.f20743Z;
                        int i2 = iArr[17];
                        iArr[17] = i2 + 1;
                        hashMap2.put(String.valueOf(i2), iTsmCallback);
                    }
                    return appDelete;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RemoteException();
                }
            }
            HashMap m5910a = m5910a(hashMap);
            String str = (String) m5910a.get("cardHolderName");
            String str2 = (String) m5910a.get("idType");
            String str3 = (String) m5910a.get("idNo");
            String str4 = (String) m5910a.get("pan");
            String str5 = (String) m5910a.get("pin");
            String str6 = (String) m5910a.get("expiryDate");
            String str7 = (String) m5910a.get("cvn2");
            String str8 = (String) m5910a.get("msisdn");
            String str9 = (String) m5910a.get("smsAuthCode");
            String str10 = (String) m5910a.get("ecashBalance");
            String str11 = (String) m5910a.get("cardType");
            if (!TextUtils.isEmpty(str)) {
                m5910a.put("cardHolderName", m5906b(str));
            }
            if (!TextUtils.isEmpty(str2)) {
                m5910a.put("idType", m5906b(str2));
            }
            if (!TextUtils.isEmpty(str3)) {
                m5910a.put("idNo", m5906b(str3));
            }
            if (!TextUtils.isEmpty(str4)) {
                m5910a.put("pan", m5906b(str4));
            }
            if (!TextUtils.isEmpty(str5)) {
                m5910a.put("pin", str5);
            }
            if (!TextUtils.isEmpty(str6)) {
                m5910a.put("expiryDate", m5906b(str6));
            }
            if (!TextUtils.isEmpty(str7)) {
                m5910a.put("cvn2", m5906b(str7));
            }
            if (!TextUtils.isEmpty(str8)) {
                m5910a.put("msisdn", m5906b(str8));
            }
            if (!TextUtils.isEmpty(str9)) {
                m5910a.put("smsAuthCode", m5906b(str9));
            }
            if (!TextUtils.isEmpty(str10)) {
                m5910a.put("ecashBalance", m5906b(str10));
            }
            if (!TextUtils.isEmpty(str11)) {
                m5910a.put("cardType", m5906b(str11));
            }
            appDeleteRequestParams2.setParams(m5910a);
            try {
                int appDelete2 = this.f20748e.appDelete(appDeleteRequestParams2, new BinderC10780b(this, 17, this.f20743Z[17], (byte) 0), iTsmProgressCallback);
                if (-2 == appDelete2) {
                    return m5922a(17, appDeleteRequestParams, iTsmCallback, iTsmProgressCallback);
                }
                if (appDelete2 == 0) {
                    HashMap hashMap3 = this.f20721D;
                    int[] iArr2 = this.f20743Z;
                    int i3 = iArr2[17];
                    iArr2[17] = i3 + 1;
                    hashMap3.put(String.valueOf(i3), iTsmCallback);
                }
                return appDelete2;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    @Deprecated
    public synchronized int appDownload(AppDownloadRequestParams appDownloadRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) {
        int i;
        if (appDownloadRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5922a(16, appDownloadRequestParams, iTsmCallback, iTsmProgressCallback);
            }
            AppDownloadRequestParams appDownloadRequestParams2 = new AppDownloadRequestParams();
            String reserve = appDownloadRequestParams.getReserve();
            AppID appID = appDownloadRequestParams.getAppID();
            String appName = appDownloadRequestParams.getAppName();
            if (!TextUtils.isEmpty(reserve)) {
                appDownloadRequestParams2.setReserve(m5906b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    appDownloadRequestParams2.setAppID(new AppID(m5906b(appAid), m5906b(appVersion)));
                }
            }
            if (!TextUtils.isEmpty(appName)) {
                appDownloadRequestParams2.setAppName(m5906b(appName));
            }
            try {
                int appDownload = this.f20748e.appDownload(appDownloadRequestParams2, new BinderC10780b(this, 16, this.f20743Z[16], (byte) 0), iTsmProgressCallback);
                if (-2 == appDownload) {
                    return m5922a(16, appDownloadRequestParams, iTsmCallback, iTsmProgressCallback);
                }
                if (appDownload == 0) {
                    HashMap hashMap = this.f20720C;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[16];
                    iArr[16] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return appDownload;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    @Deprecated
    public synchronized int appDownloadApply(AppDownloadApplyRequestParams appDownloadApplyRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (appDownloadApplyRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(15, appDownloadApplyRequestParams, iTsmCallback);
            }
            AppDownloadApplyRequestParams appDownloadApplyRequestParams2 = new AppDownloadApplyRequestParams();
            String reserve = appDownloadApplyRequestParams.getReserve();
            AppID appID = appDownloadApplyRequestParams.getAppID();
            if (!TextUtils.isEmpty(reserve)) {
                appDownloadApplyRequestParams2.setReserve(m5906b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    appDownloadApplyRequestParams2.setAppID(new AppID(m5906b(appAid), m5906b(appVersion)));
                }
            }
            HashMap hashMap = (HashMap) appDownloadApplyRequestParams.getParams();
            if (hashMap == null) {
                try {
                    int appDownloadApply = this.f20748e.appDownloadApply(appDownloadApplyRequestParams2, new BinderC10780b(this, 15, this.f20743Z[15], (byte) 0));
                    if (-2 == appDownloadApply) {
                        return m5923a(15, appDownloadApplyRequestParams, iTsmCallback);
                    }
                    if (appDownloadApply == 0) {
                        HashMap hashMap2 = this.f20759p;
                        int[] iArr = this.f20743Z;
                        int i2 = iArr[15];
                        iArr[15] = i2 + 1;
                        hashMap2.put(String.valueOf(i2), iTsmCallback);
                    }
                    return appDownloadApply;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RemoteException();
                }
            }
            HashMap m5910a = m5910a(hashMap);
            String str = (String) m5910a.get("accountLimit");
            String str2 = (String) m5910a.get("accountType");
            String str3 = (String) m5910a.get("cardHolderName");
            String str4 = (String) m5910a.get("idType");
            String str5 = (String) m5910a.get("idNo");
            String str6 = (String) m5910a.get("pan");
            String str7 = (String) m5910a.get("pin");
            String str8 = (String) m5910a.get("expiryDate");
            String str9 = (String) m5910a.get("cvn2");
            String str10 = (String) m5910a.get("msisdn");
            String str11 = (String) m5910a.get("smsAuthCode");
            String str12 = (String) m5910a.get("cardType");
            if (!TextUtils.isEmpty(str)) {
                m5910a.put("accountLimit", m5906b(str));
            }
            if (!TextUtils.isEmpty(str2)) {
                m5910a.put("accountType", m5906b(str2));
            }
            if (!TextUtils.isEmpty(str3)) {
                m5910a.put("cardHolderName", m5906b(str3));
            }
            if (!TextUtils.isEmpty(str4)) {
                m5910a.put("idType", m5906b(str4));
            }
            if (!TextUtils.isEmpty(str5)) {
                m5910a.put("idNo", m5906b(str5));
            }
            if (!TextUtils.isEmpty(str6)) {
                m5910a.put("pan", m5906b(str6));
            }
            if (!TextUtils.isEmpty(str7)) {
                m5910a.put("pin", str7);
            }
            if (!TextUtils.isEmpty(str8)) {
                m5910a.put("expiryDate", m5906b(str8));
            }
            if (!TextUtils.isEmpty(str9)) {
                m5910a.put("cvn2", m5906b(str9));
            }
            if (!TextUtils.isEmpty(str10)) {
                m5910a.put("msisdn", m5906b(str10));
            }
            if (!TextUtils.isEmpty(str11)) {
                m5910a.put("smsAuthCode", m5906b(str11));
            }
            if (!TextUtils.isEmpty(str12)) {
                m5910a.put("cardType", m5906b(str12));
            }
            appDownloadApplyRequestParams2.setParams(m5910a);
            try {
                int appDownloadApply2 = this.f20748e.appDownloadApply(appDownloadApplyRequestParams2, new BinderC10780b(this, 15, this.f20743Z[15], (byte) 0));
                if (-2 == appDownloadApply2) {
                    return m5923a(15, appDownloadApplyRequestParams, iTsmCallback);
                }
                if (appDownloadApply2 == 0) {
                    HashMap hashMap3 = this.f20759p;
                    int[] iArr2 = this.f20743Z;
                    int i3 = iArr2[15];
                    iArr2[15] = i3 + 1;
                    hashMap3.put(String.valueOf(i3), iTsmCallback);
                }
                return appDownloadApply2;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    @Deprecated
    public synchronized int appLock(AppLockRequestParams appLockRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (appLockRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(26, appLockRequestParams, iTsmCallback);
            }
            AppLockRequestParams appLockRequestParams2 = new AppLockRequestParams();
            String reserve = appLockRequestParams.getReserve();
            AppID appID = appLockRequestParams.getAppID();
            if (!TextUtils.isEmpty(reserve)) {
                appLockRequestParams2.setReserve(m5906b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    appLockRequestParams2.setAppID(new AppID(m5906b(appAid), m5906b(appVersion)));
                }
            }
            try {
                int appLock = this.f20748e.appLock(appLockRequestParams2, iTsmCallback);
                if (-2 == appLock) {
                    return m5923a(26, appLockRequestParams, iTsmCallback);
                }
                return appLock;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    @Deprecated
    public synchronized int appUnlock(AppUnlockRequestParams appUnlockRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (appUnlockRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(27, appUnlockRequestParams, iTsmCallback);
            }
            AppUnlockRequestParams appUnlockRequestParams2 = new AppUnlockRequestParams();
            String reserve = appUnlockRequestParams.getReserve();
            AppID appID = appUnlockRequestParams.getAppID();
            if (!TextUtils.isEmpty(reserve)) {
                appUnlockRequestParams2.setReserve(m5906b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    appUnlockRequestParams2.setAppID(new AppID(m5906b(appAid), m5906b(appVersion)));
                }
            }
            try {
                int appUnlock = this.f20748e.appUnlock(appUnlockRequestParams2, iTsmCallback);
                if (-2 == appUnlock) {
                    return m5923a(27, appUnlockRequestParams, iTsmCallback);
                }
                return appUnlock;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    public boolean bind() {
        try {
            if (this.f20747d == null) {
                this.f20747d = new ServiceConnection() { // from class: com.unionpay.tsmservice.UPTsmAddon.2
                    @Override // android.content.ServiceConnection
                    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        UPTsmAddon.this.f20749f = true;
                        UPTsmAddon.this.f20748e = ITsmService.Stub.asInterface(iBinder);
                        UPTsmAddon.this.f20745ab.sendEmptyMessage(0);
                    }

                    @Override // android.content.ServiceConnection
                    public final synchronized void onServiceDisconnected(ComponentName componentName) {
                        UPTsmAddon.this.f20749f = false;
                        UPTsmAddon.this.f20748e = null;
                        UPTsmAddon.this.f20745ab.sendEmptyMessage(1);
                    }
                };
            }
            if (this.f20749f) {
                return true;
            }
            Intent intent = new Intent("com.unionpay.tsmservice.UPTsmService");
            intent.setPackage("com.unionpay.tsmservice");
            return this.f20746c.bindService(intent, this.f20747d, 1);
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public synchronized int cardListStatusChanged(CardListStatusChangedRequestParams cardListStatusChangedRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else if (!m5901d("01.00.14")) {
            return -8;
        } else {
            if (!m5905c()) {
                return -8;
            }
            if (this.f20748e != null) {
                if (!m5911a(this.f20746c.getPackageName())) {
                    return m5923a(35, cardListStatusChangedRequestParams, iTsmCallback);
                }
                CardListStatusChangedRequestParams cardListStatusChangedRequestParams2 = new CardListStatusChangedRequestParams();
                String reserve = cardListStatusChangedRequestParams != null ? cardListStatusChangedRequestParams.getReserve() : "";
                if (this.f20751h) {
                    reserve = m5897g(m5898f(reserve));
                }
                if (!TextUtils.isEmpty(reserve)) {
                    cardListStatusChangedRequestParams2.setReserve(m5906b(reserve));
                }
                this.f20730M.put(String.valueOf(this.f20743Z[35]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f20748e;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[35];
                    iArr[35] = i2 + 1;
                    int cardListStatusChanged = iTsmService.cardListStatusChanged(cardListStatusChangedRequestParams2, new BinderC10780b(this, 35, i2, (byte) 0));
                    if (cardListStatusChanged != 0) {
                        HashMap hashMap = this.f20730M;
                        int[] iArr2 = this.f20743Z;
                        int i3 = iArr2[35] - 1;
                        iArr2[35] = i3;
                        hashMap.remove(String.valueOf(i3));
                    }
                    if (-2 == cardListStatusChanged) {
                        return m5923a(35, cardListStatusChangedRequestParams, iTsmCallback);
                    }
                    return cardListStatusChanged;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RemoteException();
                }
            }
            i = -1;
        }
        return i;
    }

    public synchronized int checkSSamsungPay(CheckSSamsungPayRequestParams checkSSamsungPayRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (checkSSamsungPayRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(29, checkSSamsungPayRequestParams, iTsmCallback);
            }
            CheckSSamsungPayRequestParams checkSSamsungPayRequestParams2 = new CheckSSamsungPayRequestParams();
            String reserve = checkSSamsungPayRequestParams.getReserve();
            if (this.f20751h) {
                reserve = m5897g(m5898f(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                checkSSamsungPayRequestParams2.setReserve(m5906b(reserve));
            }
            this.f20727J.put(String.valueOf(this.f20743Z[29]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f20748e;
                int[] iArr = this.f20743Z;
                int i2 = iArr[29];
                iArr[29] = i2 + 1;
                int checkSSamsungPay = iTsmService.checkSSamsungPay(checkSSamsungPayRequestParams2, new BinderC10780b(this, 29, i2, (byte) 0));
                if (checkSSamsungPay != 0) {
                    HashMap hashMap = this.f20727J;
                    int[] iArr2 = this.f20743Z;
                    int i3 = iArr2[29] - 1;
                    iArr2[29] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == checkSSamsungPay) {
                    return m5923a(29, checkSSamsungPayRequestParams, iTsmCallback);
                }
                return checkSSamsungPay;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0069 A[Catch: all -> 0x0083, TRY_ENTER, TRY_LEAVE, TryCatch #0 {, blocks: (B:8:0x000b, B:13:0x0014, B:15:0x0018, B:17:0x0027, B:19:0x002f, B:21:0x003a, B:22:0x0042, B:24:0x0048, B:25:0x004f, B:28:0x0057, B:29:0x005f, B:33:0x0069, B:30:0x0060, B:39:0x0072, B:40:0x007a, B:41:0x007b), top: B:49:0x000b, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x006f A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized int clearEncryptData(int r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            r0 = 2000(0x7d0, float:2.803E-42)
            if (r6 < r0) goto L86
            r0 = 2001(0x7d1, float:2.804E-42)
            if (r6 <= r0) goto Lb
            goto L86
        Lb:
            boolean r0 = r5.m5905c()     // Catch: java.lang.Throwable -> L83
            if (r0 != 0) goto L14
            r6 = -8
        L12:
            monitor-exit(r5)
            return r6
        L14:
            com.unionpay.tsmservice.ITsmService r0 = r5.f20748e     // Catch: java.lang.Throwable -> L83
            if (r0 == 0) goto L81
            android.content.Context r0 = r5.f20746c     // Catch: java.lang.Throwable -> L83
            java.lang.String r0 = r0.getPackageName()     // Catch: java.lang.Throwable -> L83
            boolean r0 = m5911a(r0)     // Catch: java.lang.Throwable -> L83
            r1 = 33
            r2 = 0
            if (r0 == 0) goto L7b
            java.lang.String r0 = "01.00.24"
            boolean r0 = r5.m5901d(r0)     // Catch: java.lang.Throwable -> L83
            if (r0 == 0) goto L60
            com.unionpay.tsmservice.request.ClearEncryptDataRequestParams r0 = new com.unionpay.tsmservice.request.ClearEncryptDataRequestParams     // Catch: java.lang.Throwable -> L83
            r0.<init>()     // Catch: java.lang.Throwable -> L83
            java.lang.String r3 = ""
            boolean r4 = r5.f20751h     // Catch: java.lang.Throwable -> L83
            if (r4 == 0) goto L42
            java.lang.String r3 = m5898f(r3)     // Catch: java.lang.Throwable -> L83
            java.lang.String r3 = r5.m5897g(r3)     // Catch: java.lang.Throwable -> L83
        L42:
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Throwable -> L83
            if (r4 != 0) goto L4f
            java.lang.String r3 = r5.m5906b(r3)     // Catch: java.lang.Throwable -> L83
            r0.setReserve(r3)     // Catch: java.lang.Throwable -> L83
        L4f:
            com.unionpay.tsmservice.ITsmService r3 = r5.f20748e     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L83
            int r0 = r3.clearKeyboardEncryptData(r0, r6)     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L83
            goto L66
        L56:
            r6 = move-exception
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L83
            android.os.RemoteException r6 = new android.os.RemoteException     // Catch: java.lang.Throwable -> L83
            r6.<init>()     // Catch: java.lang.Throwable -> L83
            throw r6     // Catch: java.lang.Throwable -> L83
        L60:
            com.unionpay.tsmservice.ITsmService r0 = r5.f20748e     // Catch: java.lang.Exception -> L71 java.lang.Throwable -> L83
            int r0 = r0.clearEncryptData(r6)     // Catch: java.lang.Exception -> L71 java.lang.Throwable -> L83
        L66:
            r3 = -2
            if (r3 != r0) goto L6f
            int r6 = m5921a(r1, r2, r6, r2, r2)     // Catch: java.lang.Throwable -> L83
            monitor-exit(r5)
            return r6
        L6f:
            monitor-exit(r5)
            return r0
        L71:
            r6 = move-exception
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L83
            android.os.RemoteException r6 = new android.os.RemoteException     // Catch: java.lang.Throwable -> L83
            r6.<init>()     // Catch: java.lang.Throwable -> L83
            throw r6     // Catch: java.lang.Throwable -> L83
        L7b:
            int r6 = m5921a(r1, r2, r6, r2, r2)     // Catch: java.lang.Throwable -> L83
            monitor-exit(r5)
            return r6
        L81:
            r6 = -1
            goto L12
        L83:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        L86:
            r6 = -3
            goto L12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.clearEncryptData(int):int");
    }

    public synchronized int closeChannel(CloseChannelRequestParams closeChannelRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (closeChannelRequestParams == null || iTsmCallback == null) {
            return -3;
        }
        String channel = closeChannelRequestParams.getChannel();
        if (TextUtils.isEmpty(channel)) {
            return -3;
        }
        if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(21, closeChannelRequestParams, iTsmCallback);
            }
            String m5906b = m5906b(channel);
            CloseChannelRequestParams closeChannelRequestParams2 = new CloseChannelRequestParams();
            closeChannelRequestParams2.setChannel(m5906b);
            String reserve = closeChannelRequestParams.getReserve();
            if (this.f20751h) {
                reserve = m5897g(m5898f(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                closeChannelRequestParams2.setReserve(m5906b(reserve));
            }
            this.f20723F.put(String.valueOf(this.f20743Z[21]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f20748e;
                int[] iArr = this.f20743Z;
                int i2 = iArr[21];
                iArr[21] = i2 + 1;
                int closeChannel = iTsmService.closeChannel(closeChannelRequestParams2, new BinderC10780b(this, 21, i2, (byte) 0));
                if (closeChannel != 0) {
                    HashMap hashMap = this.f20723F;
                    int[] iArr2 = this.f20743Z;
                    int i3 = iArr2[21] - 1;
                    iArr2[21] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == closeChannel) {
                    return m5923a(21, closeChannelRequestParams, iTsmCallback);
                }
                return closeChannel;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    public synchronized int createSSD(UniteRequestParams uniteRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else if (!m5901d("01.00.38")) {
            return -8;
        } else {
            if (!m5905c()) {
                return -8;
            }
            if (this.f20748e != null) {
                if (!m5911a(this.f20746c.getPackageName())) {
                    return m5923a(46, uniteRequestParams, iTsmCallback);
                }
                UniteRequestParams uniteRequestParams2 = new UniteRequestParams();
                if (uniteRequestParams == null) {
                    uniteRequestParams = new UniteRequestParams();
                }
                Bundle params = uniteRequestParams.getParams();
                if (params != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("encryptData", m5919a(params));
                    uniteRequestParams2.setParams(bundle);
                }
                String reserve = uniteRequestParams.getReserve();
                if (this.f20751h) {
                    reserve = m5897g(m5898f(reserve));
                }
                if (!TextUtils.isEmpty(reserve)) {
                    uniteRequestParams2.setReserve(m5906b(reserve));
                }
                this.f20741X.put(String.valueOf(this.f20743Z[46]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f20748e;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[46];
                    iArr[46] = i2 + 1;
                    int createSSD = iTsmService.createSSD(uniteRequestParams2, new BinderC10780b(this, 46, i2, (byte) 0));
                    if (createSSD != 0) {
                        HashMap hashMap = this.f20741X;
                        int[] iArr2 = this.f20743Z;
                        int i3 = iArr2[46] - 1;
                        iArr2[46] = i3;
                        hashMap.remove(String.valueOf(i3));
                    }
                    if (-2 == createSSD) {
                        return m5923a(46, uniteRequestParams, iTsmCallback);
                    }
                    return createSSD;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RemoteException();
                }
            }
            i = -1;
        }
        return i;
    }

    @Deprecated
    public synchronized int eCashTopUp(ECashTopUpRequestParams eCashTopUpRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (eCashTopUpRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(19, eCashTopUpRequestParams, iTsmCallback);
            }
            ECashTopUpRequestParams eCashTopUpRequestParams2 = new ECashTopUpRequestParams();
            String reserve = eCashTopUpRequestParams.getReserve();
            AppID appID = eCashTopUpRequestParams.getAppID();
            String type = eCashTopUpRequestParams.getType();
            String amount = eCashTopUpRequestParams.getAmount();
            if (!TextUtils.isEmpty(reserve)) {
                eCashTopUpRequestParams2.setReserve(m5906b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    eCashTopUpRequestParams2.setAppID(new AppID(m5906b(appAid), m5906b(appVersion)));
                }
            }
            String encrpytPin = eCashTopUpRequestParams.getEncrpytPin();
            if (!TextUtils.isEmpty(encrpytPin)) {
                eCashTopUpRequestParams2.setEncrpytPin(encrpytPin);
            }
            if (!TextUtils.isEmpty(type)) {
                eCashTopUpRequestParams2.setType(m5906b(type));
            }
            if (!TextUtils.isEmpty(amount)) {
                eCashTopUpRequestParams2.setAmount(m5906b(amount));
            }
            try {
                int eCashTopUp = this.f20748e.eCashTopUp(eCashTopUpRequestParams2, new BinderC10780b(this, 19, this.f20743Z[19], (byte) 0));
                if (-2 == eCashTopUp) {
                    return m5923a(19, eCashTopUpRequestParams, iTsmCallback);
                }
                if (eCashTopUp == 0) {
                    HashMap hashMap = this.f20762s;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[19];
                    iArr[19] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return eCashTopUp;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    public synchronized int encryptData(EncryptDataRequestParams encryptDataRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (encryptDataRequestParams == null || iTsmCallback == null) {
            return -3;
        }
        if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(23, encryptDataRequestParams, iTsmCallback);
            }
            EncryptDataRequestParams encryptDataRequestParams2 = new EncryptDataRequestParams();
            String reserve = encryptDataRequestParams.getReserve();
            if (this.f20751h) {
                reserve = m5897g(m5898f(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                encryptDataRequestParams2.setReserve(m5906b(reserve));
            }
            ArrayList arrayList = (ArrayList) encryptDataRequestParams.getData();
            if (arrayList != null) {
                int size = arrayList.size();
                if (size == 0) {
                    return -3;
                }
                ArrayList arrayList2 = new ArrayList();
                for (int i2 = 0; i2 < size; i2++) {
                    String str = (String) arrayList.get(i2);
                    if (!TextUtils.isEmpty(str)) {
                        arrayList2.add(m5906b(str));
                    }
                }
                encryptDataRequestParams2.setData(arrayList2);
            }
            this.f20719B.put(String.valueOf(this.f20743Z[23]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f20748e;
                int[] iArr = this.f20743Z;
                int i3 = iArr[23];
                iArr[23] = i3 + 1;
                int encryptData = iTsmService.encryptData(encryptDataRequestParams2, new BinderC10780b(this, 23, i3, (byte) 0));
                if (encryptData != 0) {
                    HashMap hashMap = this.f20719B;
                    int[] iArr2 = this.f20743Z;
                    int i4 = iArr2[23] - 1;
                    iArr2[23] = i4;
                    hashMap.remove(String.valueOf(i4));
                }
                if (-2 == encryptData) {
                    return m5923a(23, encryptDataRequestParams, iTsmCallback);
                }
                return encryptData;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    public int exchangeKey(String str, String[] strArr) {
        if (TextUtils.isEmpty(str) || strArr == null || strArr.length == 0) {
            return -3;
        }
        if (m5905c()) {
            ITsmService iTsmService = this.f20748e;
            if (iTsmService != null) {
                try {
                    return iTsmService.exchangeKey(str, strArr);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RemoteException();
                }
            }
            return -1;
        }
        return -8;
    }

    public synchronized int executeCmd(ExecuteCmdRequestParams executeCmdRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) {
        int i;
        if (executeCmdRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5922a(25, executeCmdRequestParams, iTsmCallback, iTsmProgressCallback);
            }
            ExecuteCmdRequestParams executeCmdRequestParams2 = new ExecuteCmdRequestParams();
            String reserve = executeCmdRequestParams.getReserve();
            String ssid = executeCmdRequestParams.getSsid();
            String sign = executeCmdRequestParams.getSign();
            if (this.f20751h) {
                reserve = m5897g(m5898f(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                executeCmdRequestParams2.setReserve(m5906b(reserve));
            }
            if (!TextUtils.isEmpty(ssid)) {
                executeCmdRequestParams2.setSsid(m5906b(ssid));
            }
            if (!TextUtils.isEmpty(sign)) {
                executeCmdRequestParams2.setSign(m5906b(sign));
            }
            this.f20725H.put(String.valueOf(this.f20743Z[25]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f20748e;
                int[] iArr = this.f20743Z;
                int i2 = iArr[25];
                iArr[25] = i2 + 1;
                int executeCmd = iTsmService.executeCmd(executeCmdRequestParams2, new BinderC10780b(this, 25, i2, (byte) 0), iTsmProgressCallback);
                if (executeCmd != 0) {
                    HashMap hashMap = this.f20725H;
                    int[] iArr2 = this.f20743Z;
                    int i3 = iArr2[25] - 1;
                    iArr2[25] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == executeCmd) {
                    return m5922a(25, executeCmdRequestParams, iTsmCallback, iTsmProgressCallback);
                }
                return executeCmd;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    @Deprecated
    public synchronized int getAccountBalance(GetAccountBalanceRequestParams getAccountBalanceRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (getAccountBalanceRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(8, getAccountBalanceRequestParams, iTsmCallback);
            }
            GetAccountBalanceRequestParams getAccountBalanceRequestParams2 = new GetAccountBalanceRequestParams();
            String reserve = getAccountBalanceRequestParams.getReserve();
            AppID appID = getAccountBalanceRequestParams.getAppID();
            if (!TextUtils.isEmpty(reserve)) {
                getAccountBalanceRequestParams2.setReserve(m5906b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    getAccountBalanceRequestParams2.setAppID(new AppID(m5906b(appAid), m5906b(appVersion)));
                }
            }
            String encryptPin = getAccountBalanceRequestParams.getEncryptPin();
            if (!TextUtils.isEmpty(encryptPin)) {
                getAccountBalanceRequestParams2.setEncryptPin(encryptPin);
            }
            try {
                int accountBalance = this.f20748e.getAccountBalance(getAccountBalanceRequestParams2, new BinderC10780b(this, 8, this.f20743Z[8], (byte) 0));
                if (-2 == accountBalance) {
                    return m5923a(8, getAccountBalanceRequestParams, iTsmCallback);
                }
                if (accountBalance == 0) {
                    HashMap hashMap = this.f20765v;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[8];
                    iArr[8] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return accountBalance;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    @Deprecated
    public synchronized int getAccountInfo(GetAccountInfoRequestParams getAccountInfoRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (getAccountInfoRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(7, getAccountInfoRequestParams, iTsmCallback);
            }
            GetAccountInfoRequestParams getAccountInfoRequestParams2 = new GetAccountInfoRequestParams();
            String reserve = getAccountInfoRequestParams.getReserve();
            AppID appID = getAccountInfoRequestParams.getAppID();
            if (!TextUtils.isEmpty(reserve)) {
                getAccountInfoRequestParams2.setReserve(m5906b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    getAccountInfoRequestParams2.setAppID(new AppID(m5906b(appAid), m5906b(appVersion)));
                }
            }
            try {
                int accountInfo = this.f20748e.getAccountInfo(getAccountInfoRequestParams2, new BinderC10780b(this, 7, this.f20743Z[7], (byte) 0));
                if (-2 == accountInfo) {
                    return m5923a(7, getAccountInfoRequestParams, iTsmCallback);
                }
                if (accountInfo == 0) {
                    HashMap hashMap = this.f20764u;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[7];
                    iArr[7] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return accountInfo;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    @Deprecated
    public synchronized int getAppDetail(GetAppDetailRequestParams getAppDetailRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (getAppDetailRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(4, getAppDetailRequestParams, iTsmCallback);
            }
            GetAppDetailRequestParams getAppDetailRequestParams2 = new GetAppDetailRequestParams();
            String reserve = getAppDetailRequestParams.getReserve();
            AppID appID = getAppDetailRequestParams.getAppID();
            String transType = getAppDetailRequestParams.getTransType();
            if (!TextUtils.isEmpty(reserve)) {
                getAppDetailRequestParams2.setReserve(m5906b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    getAppDetailRequestParams2.setAppID(new AppID(m5906b(appAid), m5906b(appVersion)));
                }
            }
            if (!TextUtils.isEmpty(transType)) {
                getAppDetailRequestParams2.setTransType(m5906b(transType));
            }
            try {
                int appDetail = this.f20748e.getAppDetail(getAppDetailRequestParams2, new BinderC10780b(this, 4, this.f20743Z[4], (byte) 0));
                if (-2 == appDetail) {
                    return m5923a(4, getAppDetailRequestParams, iTsmCallback);
                }
                if (appDetail == 0) {
                    HashMap hashMap = this.f20757n;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[4];
                    iArr[4] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return appDetail;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    @Deprecated
    public synchronized int getAppList(GetAppListRequestParams getAppListRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (getAppListRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(2, getAppListRequestParams, iTsmCallback);
            }
            GetAppListRequestParams getAppListRequestParams2 = new GetAppListRequestParams();
            String reserve = getAppListRequestParams.getReserve();
            String keyword = getAppListRequestParams.getKeyword();
            String[] status = getAppListRequestParams.getStatus();
            if (!TextUtils.isEmpty(reserve)) {
                getAppListRequestParams2.setReserve(m5906b(reserve));
            }
            if (!TextUtils.isEmpty(keyword)) {
                getAppListRequestParams2.setKeyword(m5906b(keyword));
            }
            if (status != null) {
                int length = status.length;
                String[] strArr = new String[length];
                for (int i2 = 0; i2 < length; i2++) {
                    if (!TextUtils.isEmpty(status[i2])) {
                        strArr[i2] = m5906b(status[i2]);
                    }
                }
                getAppListRequestParams2.setStatus(strArr);
            }
            try {
                int appList = this.f20748e.getAppList(getAppListRequestParams2, new BinderC10780b(this, 2, this.f20743Z[2], (byte) 0));
                if (-2 == appList) {
                    return m5923a(2, getAppListRequestParams, iTsmCallback);
                }
                if (appList == 0) {
                    HashMap hashMap = this.f20755l;
                    int[] iArr = this.f20743Z;
                    int i3 = iArr[2];
                    iArr[2] = i3 + 1;
                    hashMap.put(String.valueOf(i3), iTsmCallback);
                }
                return appList;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    @Deprecated
    public synchronized int getAppStatus(GetAppStatusRequestParams getAppStatusRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (getAppStatusRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(5, getAppStatusRequestParams, iTsmCallback);
            }
            GetAppStatusRequestParams getAppStatusRequestParams2 = new GetAppStatusRequestParams();
            String reserve = getAppStatusRequestParams.getReserve();
            AppID appID = getAppStatusRequestParams.getAppID();
            if (!TextUtils.isEmpty(reserve)) {
                getAppStatusRequestParams2.setReserve(m5906b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    getAppStatusRequestParams2.setAppID(new AppID(m5906b(appAid), m5906b(appVersion)));
                }
            }
            try {
                int appStatus = this.f20748e.getAppStatus(getAppStatusRequestParams2, new BinderC10780b(this, 5, this.f20743Z[5], (byte) 0));
                if (-2 == appStatus) {
                    return m5923a(5, getAppStatusRequestParams, iTsmCallback);
                }
                if (appStatus == 0) {
                    HashMap hashMap = this.f20756m;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[5];
                    iArr[5] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return appStatus;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    @Deprecated
    public synchronized int getAssociatedApp(GetAssociatedAppRequestParams getAssociatedAppRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (getAssociatedAppRequestParams == null || iTsmCallback == null) {
            return -3;
        }
        String encryptPan = getAssociatedAppRequestParams.getEncryptPan();
        if (TextUtils.isEmpty(encryptPan)) {
            return -3;
        }
        if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(1, getAssociatedAppRequestParams, iTsmCallback);
            }
            GetAssociatedAppRequestParams getAssociatedAppRequestParams2 = new GetAssociatedAppRequestParams();
            String reserve = getAssociatedAppRequestParams.getReserve();
            if (this.f20751h) {
                reserve = m5897g(m5898f(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                getAssociatedAppRequestParams2.setReserve(m5906b(reserve));
            }
            getAssociatedAppRequestParams2.setEncryptPan(m5906b(encryptPan));
            try {
                int associatedApp = this.f20748e.getAssociatedApp(getAssociatedAppRequestParams2, new BinderC10780b(this, 1, this.f20743Z[1], (byte) 0));
                if (-2 == associatedApp) {
                    return m5923a(1, getAssociatedAppRequestParams, iTsmCallback);
                }
                if (associatedApp == 0) {
                    HashMap hashMap = this.f20753j;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[1];
                    iArr[1] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return associatedApp;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    @Deprecated
    public synchronized int getCardInfo(GetCardInfoRequestParams getCardInfoRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (getCardInfoRequestParams == null || iTsmCallback == null) {
            return -3;
        }
        String[] appAID = getCardInfoRequestParams.getAppAID();
        int length = appAID.length;
        if (appAID != null && length != 0) {
            int i2 = 0;
            while (i2 < length && appAID[i2] == null) {
                i2++;
            }
            if (i2 == length) {
                return -3;
            }
            if (!m5905c()) {
                i = -8;
            } else if (this.f20748e != null) {
                if (!m5911a(this.f20746c.getPackageName())) {
                    return m5923a(6, getCardInfoRequestParams, iTsmCallback);
                }
                String[] strArr = new String[length];
                for (int i3 = 0; i3 < length; i3++) {
                    if (appAID[i3] == null) {
                        strArr[i3] = appAID[i3];
                    } else {
                        strArr[i3] = m5906b(appAID[i3]);
                    }
                }
                GetCardInfoRequestParams getCardInfoRequestParams2 = new GetCardInfoRequestParams();
                getCardInfoRequestParams2.setAppAID(strArr);
                String reserve = getCardInfoRequestParams.getReserve();
                if (this.f20751h) {
                    reserve = m5897g(m5898f(reserve));
                }
                if (!TextUtils.isEmpty(reserve)) {
                    getCardInfoRequestParams2.setReserve(m5906b(reserve));
                }
                try {
                    int cardInfo = this.f20748e.getCardInfo(getCardInfoRequestParams2, new BinderC10780b(this, 6, this.f20743Z[6], (byte) 0));
                    if (-2 == cardInfo) {
                        return m5923a(6, getCardInfoRequestParams, iTsmCallback);
                    }
                    if (cardInfo == 0) {
                        HashMap hashMap = this.f20718A;
                        int[] iArr = this.f20743Z;
                        int i4 = iArr[6];
                        iArr[6] = i4 + 1;
                        hashMap.put(String.valueOf(i4), iTsmCallback);
                    }
                    return cardInfo;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RemoteException();
                }
            } else {
                i = -1;
            }
            return i;
        }
        return -3;
    }

    public synchronized int getCardInfoBySamsungPay(GetCardInfoBySpayRequestParams getCardInfoBySpayRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (getCardInfoBySpayRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(28, getCardInfoBySpayRequestParams, iTsmCallback);
            }
            GetCardInfoBySpayRequestParams getCardInfoBySpayRequestParams2 = new GetCardInfoBySpayRequestParams();
            String reserve = getCardInfoBySpayRequestParams.getReserve();
            if (this.f20751h) {
                reserve = m5897g(m5898f(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                getCardInfoBySpayRequestParams2.setReserve(m5906b(reserve));
            }
            Amount amount = getCardInfoBySpayRequestParams.getAmount();
            if (amount != null) {
                String currencyType = amount.getCurrencyType();
                String productPrice = amount.getProductPrice();
                Amount amount2 = new Amount();
                if (!TextUtils.isEmpty(currencyType)) {
                    amount2.setCurrencyType(m5906b(currencyType));
                }
                if (!TextUtils.isEmpty(productPrice)) {
                    amount2.setProductPrice(m5906b(productPrice));
                }
                getCardInfoBySpayRequestParams2.setAmount(amount2);
            }
            this.f20726I.put(String.valueOf(this.f20743Z[28]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f20748e;
                int[] iArr = this.f20743Z;
                int i2 = iArr[28];
                iArr[28] = i2 + 1;
                int cardInfoBySamsungPay = iTsmService.getCardInfoBySamsungPay(getCardInfoBySpayRequestParams2, new BinderC10780b(this, 28, i2, (byte) 0));
                if (cardInfoBySamsungPay != 0) {
                    HashMap hashMap = this.f20726I;
                    int[] iArr2 = this.f20743Z;
                    int i3 = iArr2[28] - 1;
                    iArr2[28] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == cardInfoBySamsungPay) {
                    return m5923a(28, getCardInfoBySpayRequestParams, iTsmCallback);
                }
                return cardInfoBySamsungPay;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    public Context getContext() {
        return this.f20746c;
    }

    public int getCryptType() {
        return this.f20750g;
    }

    @Deprecated
    public synchronized int getDefaultCard(GetDefaultCardRequestParams getDefaultCardRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(13, getDefaultCardRequestParams, iTsmCallback);
            }
            GetDefaultCardRequestParams getDefaultCardRequestParams2 = new GetDefaultCardRequestParams();
            if (getDefaultCardRequestParams != null) {
                String reserve = getDefaultCardRequestParams.getReserve();
                if (!TextUtils.isEmpty(reserve)) {
                    getDefaultCardRequestParams2.setReserve(m5906b(reserve));
                }
            }
            try {
                int defaultCard = this.f20748e.getDefaultCard(getDefaultCardRequestParams2, new BinderC10780b(this, 13, this.f20743Z[13], (byte) 0));
                if (-2 == defaultCard) {
                    return m5923a(13, getDefaultCardRequestParams, iTsmCallback);
                }
                if (defaultCard == 0) {
                    HashMap hashMap = this.f20768y;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[13];
                    iArr[13] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return defaultCard;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    public synchronized int getEncryptData(GetEncryptDataRequestParams getEncryptDataRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (iTsmCallback == null || getEncryptDataRequestParams == null) {
            return -3;
        }
        int type = getEncryptDataRequestParams.getType();
        String pan = getEncryptDataRequestParams.getPan();
        if (type >= 2000 && type <= 2001) {
            if (type == 2000 && TextUtils.isEmpty(pan)) {
                return -3;
            }
            if (!m5905c()) {
                i = -8;
            } else if (this.f20748e != null) {
                if (!m5911a(this.f20746c.getPackageName())) {
                    return m5923a(31, getEncryptDataRequestParams, iTsmCallback);
                }
                GetEncryptDataRequestParams getEncryptDataRequestParams2 = new GetEncryptDataRequestParams();
                if (type == 2000) {
                    getEncryptDataRequestParams2.setPan(m5906b(pan));
                }
                getEncryptDataRequestParams2.setType(type);
                String reserve = getEncryptDataRequestParams.getReserve();
                if (this.f20751h) {
                    reserve = m5897g(m5898f(reserve));
                }
                if (!TextUtils.isEmpty(reserve)) {
                    getEncryptDataRequestParams2.setReserve(m5906b(reserve));
                }
                this.f20729L.put(String.valueOf(this.f20743Z[31]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f20748e;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[31];
                    iArr[31] = i2 + 1;
                    int encryptData = iTsmService.getEncryptData(getEncryptDataRequestParams2, new BinderC10780b(this, 31, i2, (byte) 0));
                    if (encryptData != 0) {
                        HashMap hashMap = this.f20729L;
                        int[] iArr2 = this.f20743Z;
                        int i3 = iArr2[31] - 1;
                        iArr2[31] = i3;
                        hashMap.remove(String.valueOf(i3));
                    }
                    if (-2 == encryptData) {
                        return m5923a(31, getEncryptDataRequestParams, iTsmCallback);
                    }
                    return encryptData;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RemoteException();
                }
            } else {
                i = -1;
            }
            return i;
        }
        return -3;
    }

    public synchronized int getListenerCount() {
        if (f20717b != null) {
            return f20717b.size();
        }
        return 0;
    }

    public synchronized int getMessageDetails(GetMessageDetailsRequestParams getMessageDetailsRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else if (!m5901d("01.00.35")) {
            return -8;
        } else {
            if (!m5905c()) {
                return -8;
            }
            if (this.f20748e != null) {
                if (!m5911a(this.f20746c.getPackageName())) {
                    return m5923a(44, getMessageDetailsRequestParams, iTsmCallback);
                }
                GetMessageDetailsRequestParams getMessageDetailsRequestParams2 = new GetMessageDetailsRequestParams();
                String str = "";
                if (getMessageDetailsRequestParams != null) {
                    str = getMessageDetailsRequestParams.getReserve();
                    Bundle params = getMessageDetailsRequestParams.getParams();
                    if (params != null) {
                        Bundle bundle = new Bundle();
                        bundle.putString("encryptData", m5919a(params));
                        getMessageDetailsRequestParams2.setParams(bundle);
                    }
                }
                if (this.f20751h) {
                    str = m5897g(m5898f(str));
                }
                if (!TextUtils.isEmpty(str)) {
                    getMessageDetailsRequestParams2.setReserve(m5906b(str));
                }
                this.f20739V.put(String.valueOf(this.f20743Z[44]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f20748e;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[44];
                    iArr[44] = i2 + 1;
                    int messageDetails = iTsmService.getMessageDetails(getMessageDetailsRequestParams2, new BinderC10780b(this, 44, i2, (byte) 0));
                    if (messageDetails != 0) {
                        HashMap hashMap = this.f20739V;
                        int[] iArr2 = this.f20743Z;
                        int i3 = iArr2[44] - 1;
                        iArr2[44] = i3;
                        hashMap.remove(String.valueOf(i3));
                    }
                    if (-2 == messageDetails) {
                        return m5923a(44, getMessageDetailsRequestParams, iTsmCallback);
                    }
                    return messageDetails;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RemoteException();
                }
            }
            i = -1;
        }
        return i;
    }

    public int getPubKey(int i, String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return -3;
        }
        if (m5905c()) {
            ITsmService iTsmService = this.f20748e;
            if (iTsmService != null) {
                try {
                    return iTsmService.getPubKey(i, strArr);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RemoteException();
                }
            }
            return -1;
        }
        return -8;
    }

    @Deprecated
    public synchronized int getSEAppList(GetSeAppListRequestParams getSeAppListRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(3, getSeAppListRequestParams, iTsmCallback);
            }
            GetSeAppListRequestParams getSeAppListRequestParams2 = new GetSeAppListRequestParams();
            String reserve = getSeAppListRequestParams != null ? getSeAppListRequestParams.getReserve() : "";
            if (this.f20751h) {
                reserve = m5897g(m5898f(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                getSeAppListRequestParams2.setReserve(m5906b(reserve));
            }
            this.f20754k.put(String.valueOf(this.f20743Z[3]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f20748e;
                int[] iArr = this.f20743Z;
                int i2 = iArr[3];
                iArr[3] = i2 + 1;
                int sEAppList = iTsmService.getSEAppList(getSeAppListRequestParams2, new BinderC10780b(this, 3, i2, (byte) 0));
                if (sEAppList != 0) {
                    HashMap hashMap = this.f20754k;
                    int[] iArr2 = this.f20743Z;
                    int i3 = iArr2[3] - 1;
                    iArr2[3] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == sEAppList) {
                    return m5923a(3, getSeAppListRequestParams, iTsmCallback);
                }
                return sEAppList;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    @Deprecated
    public synchronized int getSMSAuthCode(GetSMSAuthCodeRequestParams getSMSAuthCodeRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (getSMSAuthCodeRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(11, getSMSAuthCodeRequestParams, iTsmCallback);
            }
            GetSMSAuthCodeRequestParams getSMSAuthCodeRequestParams2 = new GetSMSAuthCodeRequestParams();
            String reserve = getSMSAuthCodeRequestParams.getReserve();
            AppID appID = getSMSAuthCodeRequestParams.getAppID();
            if (!TextUtils.isEmpty(reserve)) {
                getSMSAuthCodeRequestParams2.setReserve(m5906b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    getSMSAuthCodeRequestParams2.setAppID(new AppID(m5906b(appAid), m5906b(appVersion)));
                }
            }
            String pan = getSMSAuthCodeRequestParams.getPan();
            String msisdn = getSMSAuthCodeRequestParams.getMsisdn();
            if (!TextUtils.isEmpty(pan)) {
                getSMSAuthCodeRequestParams2.setPan(m5906b(pan));
            }
            if (!TextUtils.isEmpty(msisdn)) {
                getSMSAuthCodeRequestParams2.setMsisdn(m5906b(msisdn));
            }
            try {
                int sMSAuthCode = this.f20748e.getSMSAuthCode(getSMSAuthCodeRequestParams2, new BinderC10780b(this, 11, this.f20743Z[11], (byte) 0));
                if (-2 == sMSAuthCode) {
                    return m5923a(11, getSMSAuthCodeRequestParams, iTsmCallback);
                }
                if (sMSAuthCode == 0) {
                    HashMap hashMap = this.f20761r;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[11];
                    iArr[11] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return sMSAuthCode;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    public synchronized int getSeId(GetSeIdRequestParams getSeIdRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(12, getSeIdRequestParams, iTsmCallback);
            }
            GetSeIdRequestParams getSeIdRequestParams2 = new GetSeIdRequestParams();
            String reserve = getSeIdRequestParams != null ? getSeIdRequestParams.getReserve() : "";
            if (this.f20751h) {
                reserve = m5897g(m5898f(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                getSeIdRequestParams2.setReserve(m5906b(reserve));
            }
            this.f20769z.put(String.valueOf(this.f20743Z[12]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f20748e;
                int[] iArr = this.f20743Z;
                int i2 = iArr[12];
                iArr[12] = i2 + 1;
                int sEId = iTsmService.getSEId(getSeIdRequestParams2, new BinderC10780b(this, 12, i2, (byte) 0));
                if (sEId != 0) {
                    HashMap hashMap = this.f20769z;
                    int[] iArr2 = this.f20743Z;
                    int i3 = iArr2[12] - 1;
                    iArr2[12] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == sEId) {
                    return m5923a(12, getSeIdRequestParams, iTsmCallback);
                }
                return sEId;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    @Deprecated
    public synchronized int getTransElements(GetTransElementsRequestParams getTransElementsRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (getTransElementsRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(9, getTransElementsRequestParams, iTsmCallback);
            }
            GetTransElementsRequestParams getTransElementsRequestParams2 = new GetTransElementsRequestParams();
            String reserve = getTransElementsRequestParams.getReserve();
            AppID appID = getTransElementsRequestParams.getAppID();
            String transType = getTransElementsRequestParams.getTransType();
            if (!TextUtils.isEmpty(reserve)) {
                getTransElementsRequestParams2.setReserve(m5906b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    getTransElementsRequestParams2.setAppID(new AppID(m5906b(appAid), m5906b(appVersion)));
                }
            }
            if (!TextUtils.isEmpty(transType)) {
                getTransElementsRequestParams2.setTransType(m5906b(transType));
            }
            try {
                int transElements = this.f20748e.getTransElements(getTransElementsRequestParams2, new BinderC10780b(this, 9, this.f20743Z[9], (byte) 0));
                if (-2 == transElements) {
                    return m5923a(9, getTransElementsRequestParams, iTsmCallback);
                }
                if (transElements == 0) {
                    HashMap hashMap = this.f20758o;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[9];
                    iArr[9] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return transElements;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    @Deprecated
    public synchronized int getTransRecord(GetTransRecordRequestParams getTransRecordRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (getTransRecordRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(10, getTransRecordRequestParams, iTsmCallback);
            }
            GetTransRecordRequestParams getTransRecordRequestParams2 = new GetTransRecordRequestParams();
            String reserve = getTransRecordRequestParams.getReserve();
            AppID appID = getTransRecordRequestParams.getAppID();
            if (!TextUtils.isEmpty(reserve)) {
                getTransRecordRequestParams2.setReserve(m5906b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    getTransRecordRequestParams2.setAppID(new AppID(m5906b(appAid), m5906b(appVersion)));
                }
            }
            try {
                int transRecord = this.f20748e.getTransRecord(getTransRecordRequestParams2, new BinderC10780b(this, 10, this.f20743Z[10], (byte) 0));
                if (-2 == transRecord) {
                    return m5923a(10, getTransRecordRequestParams, iTsmCallback);
                }
                if (transRecord == 0) {
                    HashMap hashMap = this.f20763t;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[10];
                    iArr[10] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return transRecord;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    public synchronized int getTransactionDetails(GetTransactionDetailsRequestParams getTransactionDetailsRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else if (!m5901d("01.00.35")) {
            return -8;
        } else {
            if (!m5905c()) {
                return -8;
            }
            if (this.f20748e != null) {
                if (!m5911a(this.f20746c.getPackageName())) {
                    return m5923a(43, getTransactionDetailsRequestParams, iTsmCallback);
                }
                GetTransactionDetailsRequestParams getTransactionDetailsRequestParams2 = new GetTransactionDetailsRequestParams();
                String str = "";
                if (getTransactionDetailsRequestParams != null) {
                    str = getTransactionDetailsRequestParams.getReserve();
                    Bundle params = getTransactionDetailsRequestParams.getParams();
                    if (params != null) {
                        Bundle bundle = new Bundle();
                        bundle.putString("encryptData", m5919a(params));
                        getTransactionDetailsRequestParams2.setParams(bundle);
                    }
                }
                if (this.f20751h) {
                    str = m5897g(m5898f(str));
                }
                if (!TextUtils.isEmpty(str)) {
                    getTransactionDetailsRequestParams2.setReserve(m5906b(str));
                }
                this.f20738U.put(String.valueOf(this.f20743Z[43]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f20748e;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[43];
                    iArr[43] = i2 + 1;
                    int transactionDetails = iTsmService.getTransactionDetails(getTransactionDetailsRequestParams2, new BinderC10780b(this, 43, i2, (byte) 0));
                    if (transactionDetails != 0) {
                        HashMap hashMap = this.f20738U;
                        int[] iArr2 = this.f20743Z;
                        int i3 = iArr2[43] - 1;
                        iArr2[43] = i3;
                        hashMap.remove(String.valueOf(i3));
                    }
                    if (-2 == transactionDetails) {
                        return m5923a(43, getTransactionDetailsRequestParams, iTsmCallback);
                    }
                    return transactionDetails;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RemoteException();
                }
            }
            i = -1;
        }
        return i;
    }

    public synchronized int getVendorPayStatus(GetVendorPayStatusRequestParams getVendorPayStatusRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else if (!m5901d("01.00.20")) {
            return -8;
        } else {
            if (!m5905c()) {
                return -8;
            }
            if (this.f20748e != null) {
                if (!m5911a(this.f20746c.getPackageName())) {
                    return m5923a(36, getVendorPayStatusRequestParams, iTsmCallback);
                }
                GetVendorPayStatusRequestParams getVendorPayStatusRequestParams2 = new GetVendorPayStatusRequestParams();
                String reserve = getVendorPayStatusRequestParams != null ? getVendorPayStatusRequestParams.getReserve() : "";
                if (this.f20751h) {
                    reserve = m5897g(m5898f(reserve));
                }
                if (!TextUtils.isEmpty(reserve)) {
                    getVendorPayStatusRequestParams2.setReserve(m5906b(reserve));
                }
                this.f20731N.put(String.valueOf(this.f20743Z[36]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f20748e;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[36];
                    iArr[36] = i2 + 1;
                    int vendorPayStatus = iTsmService.getVendorPayStatus(getVendorPayStatusRequestParams2, new BinderC10780b(this, 36, i2, (byte) 0));
                    if (vendorPayStatus != 0) {
                        HashMap hashMap = this.f20731N;
                        int[] iArr2 = this.f20743Z;
                        int i3 = iArr2[36] - 1;
                        iArr2[36] = i3;
                        hashMap.remove(String.valueOf(i3));
                    }
                    if (-2 == vendorPayStatus) {
                        return m5923a(36, getVendorPayStatusRequestParams, iTsmCallback);
                    }
                    return vendorPayStatus;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RemoteException();
                }
            }
            i = -1;
        }
        return i;
    }

    public synchronized int hideAppApply(HideAppApplyRequestParams hideAppApplyRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (hideAppApplyRequestParams == null || iTsmCallback == null) {
            return -3;
        }
        String applyId = hideAppApplyRequestParams.getApplyId();
        if (TextUtils.isEmpty(applyId)) {
            return -3;
        }
        if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(24, hideAppApplyRequestParams, iTsmCallback);
            }
            String m5906b = m5906b(applyId);
            HideAppApplyRequestParams hideAppApplyRequestParams2 = new HideAppApplyRequestParams();
            hideAppApplyRequestParams2.setApplyId(m5906b);
            String reserve = hideAppApplyRequestParams.getReserve();
            if (!TextUtils.isEmpty(reserve)) {
                hideAppApplyRequestParams2.setReserve(m5906b(reserve));
            }
            try {
                int hideAppApply = this.f20748e.hideAppApply(hideAppApplyRequestParams2, new BinderC10780b(this, 24, this.f20743Z[24], (byte) 0));
                if (-2 == hideAppApply) {
                    return m5923a(24, hideAppApplyRequestParams, iTsmCallback);
                }
                if (hideAppApply == 0) {
                    HashMap hashMap = this.f20724G;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[24];
                    iArr[24] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return hideAppApply;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0060 A[Catch: all -> 0x007a, TRY_ENTER, TRY_LEAVE, TryCatch #2 {, blocks: (B:3:0x0001, B:8:0x000a, B:10:0x000e, B:12:0x001e, B:14:0x0026, B:16:0x0031, B:17:0x0039, B:19:0x003f, B:20:0x0046, B:23:0x004e, B:24:0x0056, B:28:0x0060, B:25:0x0057, B:34:0x0069, B:35:0x0071, B:36:0x0072), top: B:47:0x0001, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0066 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized int hideKeyboard() {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = r6.m5905c()     // Catch: java.lang.Throwable -> L7a
            if (r0 != 0) goto La
            r0 = -8
        L8:
            monitor-exit(r6)
            return r0
        La:
            com.unionpay.tsmservice.ITsmService r0 = r6.f20748e     // Catch: java.lang.Throwable -> L7a
            if (r0 == 0) goto L78
            android.content.Context r0 = r6.f20746c     // Catch: java.lang.Throwable -> L7a
            java.lang.String r0 = r0.getPackageName()     // Catch: java.lang.Throwable -> L7a
            boolean r0 = m5911a(r0)     // Catch: java.lang.Throwable -> L7a
            r1 = 0
            r2 = 34
            r3 = 0
            if (r0 == 0) goto L72
            java.lang.String r0 = "01.00.24"
            boolean r0 = r6.m5901d(r0)     // Catch: java.lang.Throwable -> L7a
            if (r0 == 0) goto L57
            com.unionpay.tsmservice.request.HideSafetyKeyboardRequestParams r0 = new com.unionpay.tsmservice.request.HideSafetyKeyboardRequestParams     // Catch: java.lang.Throwable -> L7a
            r0.<init>()     // Catch: java.lang.Throwable -> L7a
            java.lang.String r4 = ""
            boolean r5 = r6.f20751h     // Catch: java.lang.Throwable -> L7a
            if (r5 == 0) goto L39
            java.lang.String r4 = m5898f(r4)     // Catch: java.lang.Throwable -> L7a
            java.lang.String r4 = r6.m5897g(r4)     // Catch: java.lang.Throwable -> L7a
        L39:
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Throwable -> L7a
            if (r5 != 0) goto L46
            java.lang.String r4 = r6.m5906b(r4)     // Catch: java.lang.Throwable -> L7a
            r0.setReserve(r4)     // Catch: java.lang.Throwable -> L7a
        L46:
            com.unionpay.tsmservice.ITsmService r4 = r6.f20748e     // Catch: java.lang.Exception -> L4d java.lang.Throwable -> L7a
            int r0 = r4.hideSafetyKeyboard(r0)     // Catch: java.lang.Exception -> L4d java.lang.Throwable -> L7a
            goto L5d
        L4d:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L7a
            android.os.RemoteException r0 = new android.os.RemoteException     // Catch: java.lang.Throwable -> L7a
            r0.<init>()     // Catch: java.lang.Throwable -> L7a
            throw r0     // Catch: java.lang.Throwable -> L7a
        L57:
            com.unionpay.tsmservice.ITsmService r0 = r6.f20748e     // Catch: java.lang.Exception -> L68 java.lang.Throwable -> L7a
            int r0 = r0.hideKeyboard()     // Catch: java.lang.Exception -> L68 java.lang.Throwable -> L7a
        L5d:
            r4 = -2
            if (r4 != r0) goto L66
            int r0 = m5921a(r2, r3, r1, r3, r3)     // Catch: java.lang.Throwable -> L7a
            monitor-exit(r6)
            return r0
        L66:
            monitor-exit(r6)
            return r0
        L68:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L7a
            android.os.RemoteException r0 = new android.os.RemoteException     // Catch: java.lang.Throwable -> L7a
            r0.<init>()     // Catch: java.lang.Throwable -> L7a
            throw r0     // Catch: java.lang.Throwable -> L7a
        L72:
            int r0 = m5921a(r2, r3, r1, r3, r3)     // Catch: java.lang.Throwable -> L7a
            monitor-exit(r6)
            return r0
        L78:
            r0 = -1
            goto L8
        L7a:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.hideKeyboard():int");
    }

    public synchronized int init(InitRequestParams initRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(0, initRequestParams, iTsmCallback);
            }
            InitRequestParams initRequestParams2 = new InitRequestParams();
            String str = "";
            if (initRequestParams != null) {
                str = initRequestParams.getReserve();
                String signature = initRequestParams.getSignature();
                if (!TextUtils.isEmpty(signature)) {
                    initRequestParams2.setSignature(m5906b(signature));
                }
            }
            if (this.f20751h) {
                str = m5897g(m5898f(str));
            }
            if (!TextUtils.isEmpty(str)) {
                initRequestParams2.setReserve(m5906b(str));
            }
            this.f20752i.put(String.valueOf(this.f20743Z[0]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f20748e;
                int[] iArr = this.f20743Z;
                int i2 = iArr[0];
                iArr[0] = i2 + 1;
                int init = iTsmService.init(initRequestParams2, new BinderC10780b(this, 0, i2, (byte) 0));
                if (init != 0) {
                    HashMap hashMap = this.f20752i;
                    int[] iArr2 = this.f20743Z;
                    int i3 = iArr2[0] - 1;
                    iArr2[0] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == init) {
                    return m5923a(0, initRequestParams, iTsmCallback);
                }
                return init;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    public boolean isConnected() {
        return this.f20749f;
    }

    public synchronized int onlinePaymentVerify(OnlinePaymentVerifyRequestParams onlinePaymentVerifyRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (onlinePaymentVerifyRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else if (!m5901d("01.00.21")) {
            return -8;
        } else {
            if (!m5905c()) {
                return -8;
            }
            if (this.f20748e != null) {
                if (!m5911a(this.f20746c.getPackageName())) {
                    return m5923a(39, onlinePaymentVerifyRequestParams, iTsmCallback);
                }
                OnlinePaymentVerifyRequestParams onlinePaymentVerifyRequestParams2 = new OnlinePaymentVerifyRequestParams();
                Bundle resource = onlinePaymentVerifyRequestParams.getResource();
                if (resource != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("encryptData", m5919a(resource));
                    onlinePaymentVerifyRequestParams2.setResource(bundle);
                }
                String orderNumber = onlinePaymentVerifyRequestParams.getOrderNumber();
                String aId = onlinePaymentVerifyRequestParams.getAId();
                if (!TextUtils.isEmpty(orderNumber)) {
                    onlinePaymentVerifyRequestParams2.setOrderNumber(m5906b(orderNumber));
                }
                if (!TextUtils.isEmpty(aId)) {
                    onlinePaymentVerifyRequestParams2.setAId(m5906b(aId));
                }
                String reserve = onlinePaymentVerifyRequestParams.getReserve();
                if (this.f20751h) {
                    reserve = m5897g(m5898f(reserve));
                }
                if (!TextUtils.isEmpty(reserve)) {
                    onlinePaymentVerifyRequestParams2.setReserve(m5906b(reserve));
                }
                this.f20734Q.put(String.valueOf(this.f20743Z[39]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f20748e;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[39];
                    iArr[39] = i2 + 1;
                    int onlinePaymentVerify = iTsmService.onlinePaymentVerify(onlinePaymentVerifyRequestParams2, new BinderC10780b(this, 39, i2, (byte) 0));
                    if (onlinePaymentVerify != 0) {
                        HashMap hashMap = this.f20734Q;
                        int[] iArr2 = this.f20743Z;
                        int i3 = iArr2[39] - 1;
                        iArr2[39] = i3;
                        hashMap.remove(String.valueOf(i3));
                    }
                    if (-2 == onlinePaymentVerify) {
                        return m5923a(39, onlinePaymentVerifyRequestParams, iTsmCallback);
                    }
                    return onlinePaymentVerify;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RemoteException();
                }
            }
            i = -1;
        }
        return i;
    }

    public synchronized int openChannel(OpenChannelRequestParams openChannelRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (openChannelRequestParams == null || iTsmCallback == null) {
            return -3;
        }
        String appAID = openChannelRequestParams.getAppAID();
        if (TextUtils.isEmpty(appAID)) {
            return -3;
        }
        if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(20, openChannelRequestParams, iTsmCallback);
            }
            String m5906b = m5906b(appAID);
            OpenChannelRequestParams openChannelRequestParams2 = new OpenChannelRequestParams();
            openChannelRequestParams2.setAppAID(m5906b);
            String reserve = openChannelRequestParams.getReserve();
            if (this.f20751h) {
                reserve = m5897g(m5898f(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                openChannelRequestParams2.setReserve(m5906b(reserve));
            }
            this.f20766w.put(String.valueOf(this.f20743Z[20]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f20748e;
                int[] iArr = this.f20743Z;
                int i2 = iArr[20];
                iArr[20] = i2 + 1;
                int openChannel = iTsmService.openChannel(openChannelRequestParams2, new BinderC10780b(this, 20, i2, (byte) 0));
                if (openChannel != 0) {
                    HashMap hashMap = this.f20766w;
                    int[] iArr2 = this.f20743Z;
                    int i3 = iArr2[20] - 1;
                    iArr2[20] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == openChannel) {
                    return m5923a(20, openChannelRequestParams, iTsmCallback);
                }
                return openChannel;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    public synchronized int preDownload(PreDownloadRequestParams preDownloadRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else if (!m5901d("01.00.26")) {
            return -8;
        } else {
            if (!m5905c()) {
                return -8;
            }
            if (this.f20748e != null) {
                if (!m5911a(this.f20746c.getPackageName())) {
                    return m5922a(40, preDownloadRequestParams, iTsmCallback, iTsmProgressCallback);
                }
                PreDownloadRequestParams preDownloadRequestParams2 = new PreDownloadRequestParams();
                String str = "";
                if (preDownloadRequestParams != null) {
                    str = preDownloadRequestParams.getReserve();
                    Bundle params = preDownloadRequestParams.getParams();
                    if (params != null) {
                        Bundle bundle = new Bundle();
                        bundle.putString("encryptData", m5919a(params));
                        preDownloadRequestParams2.setParams(bundle);
                    }
                }
                if (this.f20751h) {
                    str = m5897g(m5898f(str));
                }
                if (!TextUtils.isEmpty(str)) {
                    preDownloadRequestParams2.setReserve(m5906b(str));
                }
                this.f20735R.put(String.valueOf(this.f20743Z[40]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f20748e;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[40];
                    iArr[40] = i2 + 1;
                    int preDownload = iTsmService.preDownload(preDownloadRequestParams2, new BinderC10780b(this, 40, i2, (byte) 0), iTsmProgressCallback);
                    if (preDownload != 0) {
                        HashMap hashMap = this.f20735R;
                        int[] iArr2 = this.f20743Z;
                        int i3 = iArr2[40] - 1;
                        iArr2[40] = i3;
                        hashMap.remove(String.valueOf(i3));
                    }
                    if (-2 == preDownload) {
                        return m5922a(40, preDownloadRequestParams, iTsmCallback, iTsmProgressCallback);
                    }
                    return preDownload;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RemoteException();
                }
            }
            i = -1;
        }
        return i;
    }

    public synchronized int queryVendorPayStatus(QueryVendorPayStatusRequestParams queryVendorPayStatusRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else if (!m5901d("01.00.27")) {
            return -8;
        } else {
            if (!m5905c()) {
                return -8;
            }
            if (this.f20748e != null) {
                if (!m5911a(this.f20746c.getPackageName())) {
                    return m5923a(41, queryVendorPayStatusRequestParams, iTsmCallback);
                }
                QueryVendorPayStatusRequestParams queryVendorPayStatusRequestParams2 = new QueryVendorPayStatusRequestParams();
                String reserve = queryVendorPayStatusRequestParams != null ? queryVendorPayStatusRequestParams.getReserve() : "";
                if (this.f20751h) {
                    reserve = m5897g(m5898f(reserve));
                }
                if (!TextUtils.isEmpty(reserve)) {
                    queryVendorPayStatusRequestParams2.setReserve(m5906b(reserve));
                }
                this.f20736S.put(String.valueOf(this.f20743Z[41]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f20748e;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[41];
                    iArr[41] = i2 + 1;
                    int queryVendorPayStatus = iTsmService.queryVendorPayStatus(queryVendorPayStatusRequestParams2, new BinderC10780b(this, 41, i2, (byte) 0));
                    if (queryVendorPayStatus != 0) {
                        HashMap hashMap = this.f20736S;
                        int[] iArr2 = this.f20743Z;
                        int i3 = iArr2[41] - 1;
                        iArr2[41] = i3;
                        hashMap.remove(String.valueOf(i3));
                    }
                    if (-2 == queryVendorPayStatus) {
                        return m5923a(41, queryVendorPayStatusRequestParams, iTsmCallback);
                    }
                    return queryVendorPayStatus;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RemoteException();
                }
            }
            i = -1;
        }
        return i;
    }

    public synchronized void removeConnectionListener(UPTsmConnectionListener uPTsmConnectionListener) {
        if (uPTsmConnectionListener != null) {
            f20717b.remove(uPTsmConnectionListener);
        }
    }

    public synchronized int sendApdu(SendApduRequestParams sendApduRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (sendApduRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(22, sendApduRequestParams, iTsmCallback);
            }
            SendApduRequestParams sendApduRequestParams2 = new SendApduRequestParams();
            String reserve = sendApduRequestParams.getReserve();
            String channel = sendApduRequestParams.getChannel();
            String hexApdu = sendApduRequestParams.getHexApdu();
            if (this.f20751h) {
                reserve = m5897g(m5898f(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                sendApduRequestParams2.setReserve(m5906b(reserve));
            }
            if (!TextUtils.isEmpty(channel)) {
                sendApduRequestParams2.setChannel(m5906b(channel));
            }
            if (!TextUtils.isEmpty(hexApdu)) {
                sendApduRequestParams2.setHexApdu(m5906b(hexApdu));
            }
            this.f20767x.put(String.valueOf(this.f20743Z[22]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f20748e;
                int[] iArr = this.f20743Z;
                int i2 = iArr[22];
                iArr[22] = i2 + 1;
                int sendApdu = iTsmService.sendApdu(sendApduRequestParams2, new BinderC10780b(this, 22, i2, (byte) 0));
                if (sendApdu != 0) {
                    HashMap hashMap = this.f20767x;
                    int[] iArr2 = this.f20743Z;
                    int i3 = iArr2[22] - 1;
                    iArr2[22] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == sendApdu) {
                    return m5923a(22, sendApduRequestParams, iTsmCallback);
                }
                return sendApdu;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    public synchronized int sendCustomData(SendCustomDataRequestParams sendCustomDataRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (sendCustomDataRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else if (!m5901d("01.00.37")) {
            return -8;
        } else {
            if (!m5905c()) {
                return -8;
            }
            if (this.f20748e != null) {
                if (!m5911a(this.f20746c.getPackageName())) {
                    return m5923a(45, sendCustomDataRequestParams, iTsmCallback);
                }
                SendCustomDataRequestParams sendCustomDataRequestParams2 = new SendCustomDataRequestParams();
                Bundle params = sendCustomDataRequestParams.getParams();
                if (params != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("encryptData", m5919a(params));
                    sendCustomDataRequestParams2.setParams(bundle);
                }
                String reserve = sendCustomDataRequestParams.getReserve();
                if (this.f20751h) {
                    reserve = m5897g(m5898f(reserve));
                }
                if (!TextUtils.isEmpty(reserve)) {
                    sendCustomDataRequestParams2.setReserve(m5906b(reserve));
                }
                this.f20740W.put(String.valueOf(this.f20743Z[45]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f20748e;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[45];
                    iArr[45] = i2 + 1;
                    int sendCustomData = iTsmService.sendCustomData(sendCustomDataRequestParams2, new BinderC10780b(this, 45, i2, (byte) 0));
                    if (sendCustomData != 0) {
                        HashMap hashMap = this.f20740W;
                        int[] iArr2 = this.f20743Z;
                        int i3 = iArr2[45] - 1;
                        iArr2[45] = i3;
                        hashMap.remove(String.valueOf(i3));
                    }
                    if (-2 == sendCustomData) {
                        return m5923a(45, sendCustomDataRequestParams, iTsmCallback);
                    }
                    return sendCustomData;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RemoteException();
                }
            }
            i = -1;
        }
        return i;
    }

    @Deprecated
    public synchronized int setDefaultCard(SetDefaultCardRequestParams setDefaultCardRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (setDefaultCardRequestParams == null || iTsmCallback == null) {
            return -3;
        }
        String appAID = setDefaultCardRequestParams.getAppAID();
        if (TextUtils.isEmpty(appAID)) {
            return -3;
        }
        if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(14, setDefaultCardRequestParams, iTsmCallback);
            }
            String m5906b = m5906b(appAID);
            SetDefaultCardRequestParams setDefaultCardRequestParams2 = new SetDefaultCardRequestParams();
            setDefaultCardRequestParams2.setAppAID(m5906b);
            String reserve = setDefaultCardRequestParams.getReserve();
            if (!TextUtils.isEmpty(reserve)) {
                setDefaultCardRequestParams2.setReserve(m5906b(reserve));
            }
            try {
                int defaultCard = this.f20748e.setDefaultCard(setDefaultCardRequestParams2, new BinderC10780b(this, 14, this.f20743Z[14], (byte) 0));
                if (-2 == defaultCard) {
                    return m5923a(14, setDefaultCardRequestParams, iTsmCallback);
                }
                if (defaultCard == 0) {
                    HashMap hashMap = this.f20722E;
                    int[] iArr = this.f20743Z;
                    int i2 = iArr[14];
                    iArr[14] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return defaultCard;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    public synchronized int setSafetyKeyboardBitmap(SafetyKeyboardRequestParams safetyKeyboardRequestParams) {
        int i;
        if (safetyKeyboardRequestParams == null) {
            i = -3;
        } else if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(32, safetyKeyboardRequestParams, null);
            }
            String reserve = safetyKeyboardRequestParams.getReserve();
            if (this.f20751h) {
                reserve = m5897g(m5898f(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                safetyKeyboardRequestParams.setReserve(m5906b(reserve));
            }
            try {
                int safetyKeyboardBitmap = this.f20748e.setSafetyKeyboardBitmap(safetyKeyboardRequestParams);
                if (-2 == safetyKeyboardBitmap) {
                    return m5923a(32, safetyKeyboardRequestParams, null);
                }
                return safetyKeyboardBitmap;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    public synchronized int setSamsungDefaultWallet(SetSamsungDefWalletRequestParams setSamsungDefWalletRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else if (!m5905c()) {
            i = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5923a(30, setSamsungDefWalletRequestParams, iTsmCallback);
            }
            SetSamsungDefWalletRequestParams setSamsungDefWalletRequestParams2 = new SetSamsungDefWalletRequestParams();
            if (setSamsungDefWalletRequestParams != null) {
                String reserve = setSamsungDefWalletRequestParams.getReserve();
                if (!TextUtils.isEmpty(reserve)) {
                    setSamsungDefWalletRequestParams2.setReserve(m5906b(reserve));
                }
            }
            this.f20728K.put(String.valueOf(this.f20743Z[30]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f20748e;
                int[] iArr = this.f20743Z;
                int i2 = iArr[30];
                iArr[30] = i2 + 1;
                int samsungDefaultWallet = iTsmService.setSamsungDefaultWallet(setSamsungDefWalletRequestParams2, new BinderC10780b(this, 30, i2, (byte) 0));
                if (samsungDefaultWallet != 0) {
                    HashMap hashMap = this.f20728K;
                    int[] iArr2 = this.f20743Z;
                    int i3 = iArr2[30] - 1;
                    iArr2[30] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == samsungDefaultWallet) {
                    return m5923a(30, setSamsungDefWalletRequestParams, iTsmCallback);
                }
                return samsungDefaultWallet;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    public synchronized int showSafetyKeyboard(SafetyKeyboardRequestParams safetyKeyboardRequestParams, int i, OnSafetyKeyboardCallback onSafetyKeyboardCallback, Context context) {
        int i2;
        if (safetyKeyboardRequestParams == null || i < 2000 || i > 2001) {
            i2 = -3;
        } else if (!m5905c()) {
            i2 = -8;
        } else if (this.f20748e != null) {
            if (!m5911a(this.f20746c.getPackageName())) {
                return m5921a(1000, safetyKeyboardRequestParams, i, onSafetyKeyboardCallback, context);
            }
            this.f20742Y.put(this.f20746c.getPackageName(), new BinderC10781a(context));
            String reserve = safetyKeyboardRequestParams.getReserve();
            if (this.f20751h) {
                reserve = m5897g(m5898f(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                safetyKeyboardRequestParams.setReserve(m5906b(reserve));
            }
            try {
                int showSafetyKeyboard = this.f20748e.showSafetyKeyboard(safetyKeyboardRequestParams, i, onSafetyKeyboardCallback, new BinderC10779a());
                if (showSafetyKeyboard != 0) {
                    this.f20742Y.remove(this.f20746c.getPackageName());
                }
                if (-2 == showSafetyKeyboard) {
                    return m5921a(1000, safetyKeyboardRequestParams, i, onSafetyKeyboardCallback, context);
                }
                return showSafetyKeyboard;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    public void unbind() {
        ServiceConnection serviceConnection = this.f20747d;
        if (serviceConnection == null || !this.f20749f) {
            return;
        }
        this.f20746c.unbindService(serviceConnection);
        this.f20749f = false;
    }
}
