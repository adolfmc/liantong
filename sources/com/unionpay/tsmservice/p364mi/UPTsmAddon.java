package com.unionpay.tsmservice.p364mi;

import android.app.ActivityManager;
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
import com.unionpay.tsmservice.p364mi.ITsmActivityCallback;
import com.unionpay.tsmservice.p364mi.ITsmCallback;
import com.unionpay.tsmservice.p364mi.ITsmService;
import com.unionpay.tsmservice.p364mi.request.AcquireSEAppListRequestParams;
import com.unionpay.tsmservice.p364mi.request.AddCardToVendorPayRequestParams;
import com.unionpay.tsmservice.p364mi.request.CancelPayRequestParams;
import com.unionpay.tsmservice.p364mi.request.CardListStatusChangedRequestParams;
import com.unionpay.tsmservice.p364mi.request.EncryptDataRequestParams;
import com.unionpay.tsmservice.p364mi.request.GetEncryptDataRequestParams;
import com.unionpay.tsmservice.p364mi.request.GetMessageDetailsRequestParams;
import com.unionpay.tsmservice.p364mi.request.GetSeIdRequestParams;
import com.unionpay.tsmservice.p364mi.request.GetTransactionDetailsRequestParams;
import com.unionpay.tsmservice.p364mi.request.GetVendorPayStatusRequestParams;
import com.unionpay.tsmservice.p364mi.request.InitRequestParams;
import com.unionpay.tsmservice.p364mi.request.OnlinePaymentVerifyRequestParams;
import com.unionpay.tsmservice.p364mi.request.PayResultNotifyRequestParams;
import com.unionpay.tsmservice.p364mi.request.PinRequestRequestParams;
import com.unionpay.tsmservice.p364mi.request.QueryVendorPayStatusRequestParams;
import com.unionpay.tsmservice.p364mi.request.RequestParams;
import com.unionpay.tsmservice.p364mi.request.SafetyKeyboardRequestParams;
import com.unionpay.tsmservice.p364mi.utils.IUPJniInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.unionpay.tsmservice.mi.UPTsmAddon */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class UPTsmAddon {

    /* renamed from: a */
    private static UPTsmAddon f20794a;

    /* renamed from: b */
    private static CopyOnWriteArrayList f20795b;

    /* renamed from: c */
    private Context f20796c;

    /* renamed from: d */
    private ServiceConnection f20797d = null;

    /* renamed from: e */
    private ITsmService f20798e = null;

    /* renamed from: f */
    private boolean f20799f = false;

    /* renamed from: g */
    private HashMap f20800g = new HashMap();

    /* renamed from: h */
    private HashMap f20801h = new HashMap();

    /* renamed from: i */
    private HashMap f20802i = new HashMap();

    /* renamed from: j */
    private HashMap f20803j = new HashMap();

    /* renamed from: k */
    private HashMap f20804k = new HashMap();

    /* renamed from: l */
    private HashMap f20805l = new HashMap();

    /* renamed from: m */
    private HashMap f20806m = new HashMap();

    /* renamed from: n */
    private HashMap f20807n = new HashMap();

    /* renamed from: o */
    private HashMap f20808o = new HashMap();

    /* renamed from: p */
    private HashMap f20809p = new HashMap();

    /* renamed from: q */
    private HashMap f20810q = new HashMap();

    /* renamed from: r */
    private HashMap f20811r = new HashMap();

    /* renamed from: s */
    private HashMap f20812s = new HashMap();

    /* renamed from: t */
    private HashMap f20813t = new HashMap();

    /* renamed from: u */
    private HashMap f20814u = new HashMap();

    /* renamed from: v */
    private HashMap f20815v = new HashMap();

    /* renamed from: x */
    private final Handler.Callback f20817x = new Handler.Callback() { // from class: com.unionpay.tsmservice.mi.UPTsmAddon.1
        @Override // android.os.Handler.Callback
        public final synchronized boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    UPTsmAddon.m5896a();
                    return true;
                case 1:
                    UPTsmAddon.m5883b();
                    return true;
                default:
                    return false;
            }
        }
    };

    /* renamed from: y */
    private final Handler f20818y = new Handler(Looper.getMainLooper(), this.f20817x);

    /* renamed from: w */
    private int[] f20816w = new int[19];

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unionpay.tsmservice.mi.UPTsmAddon$UPTsmConnectionListener */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface UPTsmConnectionListener {
        void onTsmConnected();

        void onTsmDisconnected();
    }

    /* renamed from: com.unionpay.tsmservice.mi.UPTsmAddon$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public final class BinderC10800a extends ITsmActivityCallback.Stub {

        /* renamed from: b */
        private int f20822b = 1000;

        public BinderC10800a() {
        }

        @Override // com.unionpay.tsmservice.p364mi.ITsmActivityCallback
        public final void startActivity(String str, String str2, int i, Bundle bundle) {
            UPTsmAddon.m5891a((ITsmActivityCallback) UPTsmAddon.m5881b(UPTsmAddon.this, this.f20822b).get(UPTsmAddon.m5878c(UPTsmAddon.this)), str, str2, i, bundle);
            UPTsmAddon.m5881b(UPTsmAddon.this, this.f20822b).remove(UPTsmAddon.m5878c(UPTsmAddon.this));
        }
    }

    /* renamed from: com.unionpay.tsmservice.mi.UPTsmAddon$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    final class BinderC10801b extends ITsmCallback.Stub {

        /* renamed from: b */
        private int f20824b;

        /* renamed from: c */
        private int f20825c;

        private BinderC10801b(int i, int i2) {
            this.f20824b = i;
            this.f20825c = i2;
        }

        /* synthetic */ BinderC10801b(UPTsmAddon uPTsmAddon, int i, int i2, byte b) {
            this(i, i2);
        }

        @Override // com.unionpay.tsmservice.p364mi.ITsmCallback
        public final void onError(String str, String str2) {
            Bundle bundle = new Bundle();
            bundle.putString("errorCode", str);
            bundle.putString("errorDesc", str2);
            UPTsmAddon.m5890a((ITsmCallback) UPTsmAddon.m5888a(UPTsmAddon.this, this.f20824b).get(String.valueOf(this.f20825c)), bundle);
            UPTsmAddon.m5888a(UPTsmAddon.this, this.f20824b).remove(String.valueOf(this.f20825c));
            if (UPTsmAddon.m5888a(UPTsmAddon.this, this.f20824b).isEmpty()) {
                UPTsmAddon.this.f20816w[this.f20824b] = 0;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:30:0x012d  */
        /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
        @Override // com.unionpay.tsmservice.p364mi.ITsmCallback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void onResult(android.os.Bundle r7) {
            /*
                Method dump skipped, instructions count: 354
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.p364mi.UPTsmAddon.BinderC10801b.onResult(android.os.Bundle):void");
        }
    }

    static {
        try {
            System.loadLibrary("uptsmaddon");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
        f20794a = null;
        f20795b = null;
    }

    private UPTsmAddon(Context context) {
        this.f20796c = null;
        this.f20796c = context;
        if (!m5893a(context)) {
            throw new RuntimeException();
        }
    }

    /* renamed from: a */
    private static int m5895a(int i, RequestParams requestParams, ITsmCallback iTsmCallback) {
        return new SessionKeyReExchange(f20794a, i, requestParams, iTsmCallback).reExchangeKey();
    }

    /* renamed from: a */
    private static int m5894a(int i, SafetyKeyboardRequestParams safetyKeyboardRequestParams, int i2, OnSafetyKeyboardCallback onSafetyKeyboardCallback, Context context) {
        return new SessionKeyReExchange(f20794a, i, safetyKeyboardRequestParams, i2, onSafetyKeyboardCallback, context).reExchangeKey();
    }

    /* renamed from: a */
    private static int m5885a(RequestParams requestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) {
        return new SessionKeyReExchange(f20794a, 16, requestParams, iTsmCallback, iTsmProgressCallback).reExchangeKey();
    }

    /* renamed from: a */
    private static String m5892a(Bundle bundle) {
        String str = "";
        Parcel obtain = Parcel.obtain();
        obtain.writeBundle(bundle);
        byte[] marshall = obtain.marshall();
        if (marshall != null && marshall.length != 0) {
            str = m5877c(Base64.encodeToString(marshall, 0));
        }
        obtain.recycle();
        return str;
    }

    /* renamed from: a */
    static /* synthetic */ HashMap m5888a(UPTsmAddon uPTsmAddon, int i) {
        switch (i) {
            case 0:
                return uPTsmAddon.f20800g;
            case 1:
                return uPTsmAddon.f20801h;
            case 2:
                return uPTsmAddon.f20802i;
            case 3:
            case 4:
            case 5:
            case 13:
            default:
                return null;
            case 6:
                return uPTsmAddon.f20804k;
            case 7:
                return uPTsmAddon.f20805l;
            case 8:
                return uPTsmAddon.f20806m;
            case 9:
                return uPTsmAddon.f20807n;
            case 10:
                return uPTsmAddon.f20808o;
            case 11:
                return uPTsmAddon.f20809p;
            case 12:
                return uPTsmAddon.f20810q;
            case 14:
                return uPTsmAddon.f20811r;
            case 15:
                return uPTsmAddon.f20812s;
            case 16:
                return uPTsmAddon.f20813t;
            case 17:
                return uPTsmAddon.f20814u;
            case 18:
                return uPTsmAddon.f20815v;
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m5896a() {
        CopyOnWriteArrayList copyOnWriteArrayList = f20795b;
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.size() <= 0) {
            return;
        }
        Iterator it = f20795b.iterator();
        while (it.hasNext()) {
            UPTsmConnectionListener uPTsmConnectionListener = (UPTsmConnectionListener) it.next();
            if (uPTsmConnectionListener != null) {
                uPTsmConnectionListener.onTsmConnected();
            }
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m5891a(ITsmActivityCallback iTsmActivityCallback, String str, String str2, int i, Bundle bundle) {
        if (iTsmActivityCallback != null) {
            try {
                iTsmActivityCallback.startActivity(str, str2, i, bundle);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m5890a(ITsmCallback iTsmCallback, Bundle bundle) {
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
    private static boolean m5893a(Context context) {
        try {
            return IUPJniInterface.iJE(context);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    static /* synthetic */ HashMap m5881b(UPTsmAddon uPTsmAddon, int i) {
        if (i != 1000) {
            return null;
        }
        return uPTsmAddon.f20803j;
    }

    /* renamed from: b */
    static /* synthetic */ void m5883b() {
        CopyOnWriteArrayList copyOnWriteArrayList = f20795b;
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.size() <= 0) {
            return;
        }
        Iterator it = f20795b.iterator();
        while (it.hasNext()) {
            UPTsmConnectionListener uPTsmConnectionListener = (UPTsmConnectionListener) it.next();
            if (uPTsmConnectionListener != null) {
                uPTsmConnectionListener.onTsmDisconnected();
            }
        }
    }

    /* renamed from: b */
    private static boolean m5880b(String str) {
        try {
            return IUPJniInterface.cSKV(str);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: c */
    static /* synthetic */ String m5878c(UPTsmAddon uPTsmAddon) {
        return uPTsmAddon.f20796c.getPackageName();
    }

    /* renamed from: c */
    private static String m5877c(String str) {
        try {
            return IUPJniInterface.eMG(str);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: c */
    private boolean m5879c() {
        String m5874f = m5874f("com.unionpay.tsmservice.mi");
        return m5874f != null && m5874f.compareTo("01.00.02") >= 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public static String m5876d(String str) {
        try {
            return IUPJniInterface.dMG(str);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: e */
    private boolean m5875e(String str) {
        String m5874f = m5874f("com.unionpay.tsmservice.mi");
        return m5874f != null && m5874f.compareTo(str) >= 0;
    }

    /* renamed from: f */
    private String m5874f(String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = this.f20796c.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo != null) {
            return packageInfo.versionName;
        }
        return null;
    }

    /* renamed from: g */
    private static String m5873g(String str) {
        try {
            JSONObject jSONObject = TextUtils.isEmpty(str) ? new JSONObject() : new JSONObject(str);
            jSONObject.put("jarVersionCode", 19);
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
            if (f20794a == null) {
                f20794a = new UPTsmAddon(context.getApplicationContext());
            }
            if (f20795b == null) {
                f20795b = new CopyOnWriteArrayList();
            }
            return f20794a;
        }
    }

    /* renamed from: h */
    private String m5872h(String str) {
        try {
            JSONObject jSONObject = TextUtils.isEmpty(str) ? new JSONObject() : new JSONObject(str);
            jSONObject.put("packageName", this.f20796c.getPackageName());
            return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
            return str;
        }
    }

    public static boolean isAppRunInBackground(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return true;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (TextUtils.equals(runningAppProcessInfo.processName, context.getPackageName()) && runningAppProcessInfo.importance == 100) {
                return false;
            }
        }
        return true;
    }

    public synchronized int acquireSEAppList(AcquireSEAppListRequestParams acquireSEAppListRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else if (!m5875e("01.00.08")) {
            i = -8;
        } else if (this.f20798e != null) {
            if (!m5880b(this.f20796c.getPackageName())) {
                return m5895a(6, acquireSEAppListRequestParams, iTsmCallback);
            }
            AcquireSEAppListRequestParams acquireSEAppListRequestParams2 = new AcquireSEAppListRequestParams();
            String str = "";
            if (acquireSEAppListRequestParams != null) {
                str = acquireSEAppListRequestParams.getReserve();
                Bundle params = acquireSEAppListRequestParams.getParams();
                if (params != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("encryptData", m5892a(params));
                    acquireSEAppListRequestParams2.setParams(bundle);
                }
            }
            acquireSEAppListRequestParams2.setReserve(m5877c(m5872h(m5873g(str))));
            this.f20804k.put(String.valueOf(this.f20816w[6]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f20798e;
                int[] iArr = this.f20816w;
                int i2 = iArr[6];
                iArr[6] = i2 + 1;
                int acquireSEAppList = iTsmService.acquireSEAppList(acquireSEAppListRequestParams2, new BinderC10801b(this, 6, i2, (byte) 0));
                if (acquireSEAppList != 0) {
                    HashMap hashMap = this.f20804k;
                    int[] iArr2 = this.f20816w;
                    int i3 = iArr2[6] - 1;
                    iArr2[6] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == acquireSEAppList) {
                    return m5895a(6, acquireSEAppListRequestParams, iTsmCallback);
                }
                return acquireSEAppList;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    public synchronized int addCardToVendorPay(AddCardToVendorPayRequestParams addCardToVendorPayRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) {
        int i;
        if (addCardToVendorPayRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else if (!m5875e("01.00.11")) {
            i = -8;
        } else if (this.f20798e != null) {
            if (!m5880b(this.f20796c.getPackageName())) {
                return m5885a(addCardToVendorPayRequestParams, iTsmCallback, iTsmProgressCallback);
            }
            AddCardToVendorPayRequestParams addCardToVendorPayRequestParams2 = new AddCardToVendorPayRequestParams();
            Bundle params = addCardToVendorPayRequestParams.getParams();
            if (params != null) {
                Bundle bundle = new Bundle();
                bundle.putString("encryptData", m5892a(params));
                addCardToVendorPayRequestParams2.setParams(bundle);
            }
            addCardToVendorPayRequestParams2.setReserve(m5877c(m5872h(m5873g(addCardToVendorPayRequestParams.getReserve()))));
            this.f20813t.put(String.valueOf(this.f20816w[16]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f20798e;
                int[] iArr = this.f20816w;
                int i2 = iArr[16];
                iArr[16] = i2 + 1;
                int addCardToVendorPay = iTsmService.addCardToVendorPay(addCardToVendorPayRequestParams2, new BinderC10801b(this, 16, i2, (byte) 0), iTsmProgressCallback);
                if (addCardToVendorPay != 0) {
                    HashMap hashMap = this.f20813t;
                    int[] iArr2 = this.f20816w;
                    int i3 = iArr2[16] - 1;
                    iArr2[16] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == addCardToVendorPay) {
                    return m5885a(addCardToVendorPayRequestParams, iTsmCallback, iTsmProgressCallback);
                }
                return addCardToVendorPay;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    public synchronized void addConnectionListener(UPTsmConnectionListener uPTsmConnectionListener) {
        if (uPTsmConnectionListener != null) {
            f20795b.add(uPTsmConnectionListener);
        }
    }

    public boolean bind() {
        if (this.f20797d == null) {
            this.f20797d = new ServiceConnection() { // from class: com.unionpay.tsmservice.mi.UPTsmAddon.2
                @Override // android.content.ServiceConnection
                public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    UPTsmAddon.this.f20799f = true;
                    UPTsmAddon.this.f20798e = ITsmService.Stub.asInterface(iBinder);
                    UPTsmAddon.this.f20818y.sendEmptyMessage(0);
                }

                @Override // android.content.ServiceConnection
                public final synchronized void onServiceDisconnected(ComponentName componentName) {
                    UPTsmAddon.this.f20799f = false;
                    UPTsmAddon.this.f20798e = null;
                    UPTsmAddon.this.f20818y.sendEmptyMessage(1);
                }
            };
        }
        if (this.f20799f) {
            return true;
        }
        Intent intent = new Intent("com.unionpay.tsmservice.mi.UPTsmService");
        intent.setPackage("com.unionpay.tsmservice.mi");
        return this.f20796c.bindService(intent, this.f20797d, 1);
    }

    public synchronized int cancelPay() {
        int i;
        if (!m5875e("01.00.08")) {
            i = -8;
        } else if (this.f20798e != null) {
            if (!m5880b(this.f20796c.getPackageName())) {
                return m5894a(13, (SafetyKeyboardRequestParams) null, 0, (OnSafetyKeyboardCallback) null, (Context) null);
            }
            CancelPayRequestParams cancelPayRequestParams = new CancelPayRequestParams();
            cancelPayRequestParams.setReserve(m5877c(m5872h(m5873g(""))));
            try {
                int cancelPay = this.f20798e.cancelPay(cancelPayRequestParams);
                if (-2 == cancelPay) {
                    return m5894a(13, (SafetyKeyboardRequestParams) null, 0, (OnSafetyKeyboardCallback) null, (Context) null);
                }
                return cancelPay;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    public synchronized int cardListStatusChanged(CardListStatusChangedRequestParams cardListStatusChangedRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else if (!m5875e("01.00.08")) {
            i = -8;
        } else if (this.f20798e != null) {
            if (!m5880b(this.f20796c.getPackageName())) {
                return m5895a(7, cardListStatusChangedRequestParams, iTsmCallback);
            }
            CardListStatusChangedRequestParams cardListStatusChangedRequestParams2 = new CardListStatusChangedRequestParams();
            cardListStatusChangedRequestParams2.setReserve(m5877c(m5872h(m5873g(cardListStatusChangedRequestParams != null ? cardListStatusChangedRequestParams.getReserve() : ""))));
            this.f20805l.put(String.valueOf(this.f20816w[7]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f20798e;
                int[] iArr = this.f20816w;
                int i2 = iArr[7];
                iArr[7] = i2 + 1;
                int cardListStatusChanged = iTsmService.cardListStatusChanged(cardListStatusChangedRequestParams2, new BinderC10801b(this, 7, i2, (byte) 0));
                if (cardListStatusChanged != 0) {
                    HashMap hashMap = this.f20805l;
                    int[] iArr2 = this.f20816w;
                    int i3 = iArr2[7] - 1;
                    iArr2[7] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == cardListStatusChanged) {
                    return m5895a(7, cardListStatusChangedRequestParams, iTsmCallback);
                }
                return cardListStatusChanged;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0052 A[Catch: all -> 0x006d, TRY_ENTER, TRY_LEAVE, TryCatch #2 {, blocks: (B:8:0x000a, B:10:0x000e, B:12:0x001c, B:14:0x0022, B:15:0x0038, B:23:0x0052, B:18:0x0040, B:19:0x0048, B:20:0x0049, B:29:0x005b, B:30:0x0063, B:31:0x0064), top: B:45:0x000a, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0058 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized int clearEncryptData(int r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 2000(0x7d0, float:2.803E-42)
            if (r5 < r0) goto L70
            r0 = 2002(0x7d2, float:2.805E-42)
            if (r5 <= r0) goto La
            goto L70
        La:
            com.unionpay.tsmservice.mi.ITsmService r0 = r4.f20798e     // Catch: java.lang.Throwable -> L6d
            if (r0 == 0) goto L6a
            android.content.Context r0 = r4.f20796c     // Catch: java.lang.Throwable -> L6d
            java.lang.String r0 = r0.getPackageName()     // Catch: java.lang.Throwable -> L6d
            boolean r0 = m5880b(r0)     // Catch: java.lang.Throwable -> L6d
            r1 = 4
            r2 = 0
            if (r0 == 0) goto L64
            boolean r0 = r4.m5879c()     // Catch: java.lang.Throwable -> L6d
            if (r0 == 0) goto L49
            com.unionpay.tsmservice.mi.request.ClearEncryptDataRequestParams r0 = new com.unionpay.tsmservice.mi.request.ClearEncryptDataRequestParams     // Catch: java.lang.Throwable -> L6d
            r0.<init>()     // Catch: java.lang.Throwable -> L6d
            java.lang.String r3 = ""
            java.lang.String r3 = m5873g(r3)     // Catch: java.lang.Throwable -> L6d
            java.lang.String r3 = r4.m5872h(r3)     // Catch: java.lang.Throwable -> L6d
            java.lang.String r3 = m5877c(r3)     // Catch: java.lang.Throwable -> L6d
            r0.setReserve(r3)     // Catch: java.lang.Throwable -> L6d
            com.unionpay.tsmservice.mi.ITsmService r3 = r4.f20798e     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L6d
            int r0 = r3.clearKeyboardEncryptData(r0, r5)     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L6d
            goto L4f
        L3f:
            r5 = move-exception
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L6d
            android.os.RemoteException r5 = new android.os.RemoteException     // Catch: java.lang.Throwable -> L6d
            r5.<init>()     // Catch: java.lang.Throwable -> L6d
            throw r5     // Catch: java.lang.Throwable -> L6d
        L49:
            com.unionpay.tsmservice.mi.ITsmService r0 = r4.f20798e     // Catch: java.lang.Exception -> L5a java.lang.Throwable -> L6d
            int r0 = r0.clearEncryptData(r5)     // Catch: java.lang.Exception -> L5a java.lang.Throwable -> L6d
        L4f:
            r3 = -2
            if (r3 != r0) goto L58
            int r5 = m5894a(r1, r2, r5, r2, r2)     // Catch: java.lang.Throwable -> L6d
            monitor-exit(r4)
            return r5
        L58:
            monitor-exit(r4)
            return r0
        L5a:
            r5 = move-exception
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L6d
            android.os.RemoteException r5 = new android.os.RemoteException     // Catch: java.lang.Throwable -> L6d
            r5.<init>()     // Catch: java.lang.Throwable -> L6d
            throw r5     // Catch: java.lang.Throwable -> L6d
        L64:
            int r5 = m5894a(r1, r2, r5, r2, r2)     // Catch: java.lang.Throwable -> L6d
            monitor-exit(r4)
            return r5
        L6a:
            r5 = -1
        L6b:
            monitor-exit(r4)
            return r5
        L6d:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L70:
            r5 = -3
            goto L6b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.p364mi.UPTsmAddon.clearEncryptData(int):int");
    }

    public synchronized int encryptData(EncryptDataRequestParams encryptDataRequestParams, ITsmCallback iTsmCallback) {
        if (encryptDataRequestParams == null || iTsmCallback == null) {
            return -3;
        }
        if (this.f20798e != null) {
            if (!m5880b(this.f20796c.getPackageName())) {
                return m5895a(1, encryptDataRequestParams, iTsmCallback);
            }
            EncryptDataRequestParams encryptDataRequestParams2 = new EncryptDataRequestParams();
            String reserve = encryptDataRequestParams.getReserve();
            if (m5879c()) {
                reserve = m5872h(m5873g(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                encryptDataRequestParams2.setReserve(m5877c(reserve));
            }
            ArrayList arrayList = (ArrayList) encryptDataRequestParams.getData();
            if (arrayList != null) {
                int size = arrayList.size();
                if (size == 0) {
                    return -3;
                }
                ArrayList arrayList2 = new ArrayList();
                for (int i = 0; i < size; i++) {
                    String str = (String) arrayList.get(i);
                    if (!TextUtils.isEmpty(str)) {
                        arrayList2.add(m5877c(str));
                    }
                }
                encryptDataRequestParams2.setData(arrayList2);
            }
            this.f20801h.put(String.valueOf(this.f20816w[1]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f20798e;
                int[] iArr = this.f20816w;
                int i2 = iArr[1];
                iArr[1] = i2 + 1;
                int encryptData = iTsmService.encryptData(encryptDataRequestParams2, new BinderC10801b(this, 1, i2, (byte) 0));
                if (encryptData != 0) {
                    HashMap hashMap = this.f20801h;
                    int[] iArr2 = this.f20816w;
                    int i3 = iArr2[1] - 1;
                    iArr2[1] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == encryptData) {
                    return m5895a(1, encryptDataRequestParams, iTsmCallback);
                }
                return encryptData;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        }
        return -1;
    }

    public int exchangeKey(String str, String[] strArr) {
        if (TextUtils.isEmpty(str) || strArr == null || strArr.length == 0) {
            return -3;
        }
        ITsmService iTsmService = this.f20798e;
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

    public Context getContext() {
        return this.f20796c;
    }

    public synchronized int getEncryptData(GetEncryptDataRequestParams getEncryptDataRequestParams, ITsmCallback iTsmCallback) {
        if (iTsmCallback == null || getEncryptDataRequestParams == null) {
            return -3;
        }
        int type = getEncryptDataRequestParams.getType();
        String pan = getEncryptDataRequestParams.getPan();
        if (type >= 2000 && type <= 2001) {
            if (type == 2000 && TextUtils.isEmpty(pan)) {
                return -3;
            }
            if (this.f20798e != null) {
                if (!m5880b(this.f20796c.getPackageName())) {
                    return m5895a(2, getEncryptDataRequestParams, iTsmCallback);
                }
                GetEncryptDataRequestParams getEncryptDataRequestParams2 = new GetEncryptDataRequestParams();
                if (type == 2000) {
                    getEncryptDataRequestParams2.setPan(m5877c(pan));
                }
                getEncryptDataRequestParams2.setType(type);
                String reserve = getEncryptDataRequestParams.getReserve();
                if (m5879c()) {
                    reserve = m5872h(m5873g(reserve));
                }
                if (!TextUtils.isEmpty(reserve)) {
                    getEncryptDataRequestParams2.setReserve(m5877c(reserve));
                }
                this.f20802i.put(String.valueOf(this.f20816w[2]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f20798e;
                    int[] iArr = this.f20816w;
                    int i = iArr[2];
                    iArr[2] = i + 1;
                    int encryptData = iTsmService.getEncryptData(getEncryptDataRequestParams2, new BinderC10801b(this, 2, i, (byte) 0));
                    if (encryptData != 0) {
                        HashMap hashMap = this.f20802i;
                        int[] iArr2 = this.f20816w;
                        int i2 = iArr2[2] - 1;
                        iArr2[2] = i2;
                        hashMap.remove(String.valueOf(i2));
                    }
                    if (-2 == encryptData) {
                        return m5895a(2, getEncryptDataRequestParams, iTsmCallback);
                    }
                    return encryptData;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RemoteException();
                }
            }
            return -1;
        }
        return -3;
    }

    public synchronized int getListenerCount() {
        if (f20795b != null) {
            return f20795b.size();
        }
        return 0;
    }

    public synchronized int getMessageDetails(GetMessageDetailsRequestParams getMessageDetailsRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else if (!m5875e("01.00.17")) {
            i = -8;
        } else if (this.f20798e != null) {
            if (!m5880b(this.f20796c.getPackageName())) {
                return m5895a(18, getMessageDetailsRequestParams, iTsmCallback);
            }
            GetMessageDetailsRequestParams getMessageDetailsRequestParams2 = new GetMessageDetailsRequestParams();
            String str = "";
            if (getMessageDetailsRequestParams != null) {
                str = getMessageDetailsRequestParams.getReserve();
                Bundle params = getMessageDetailsRequestParams.getParams();
                if (params != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("encryptData", m5892a(params));
                    getMessageDetailsRequestParams2.setParams(bundle);
                }
            }
            getMessageDetailsRequestParams2.setReserve(m5877c(m5872h(m5873g(str))));
            this.f20815v.put(String.valueOf(this.f20816w[18]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f20798e;
                int[] iArr = this.f20816w;
                int i2 = iArr[18];
                iArr[18] = i2 + 1;
                int messageDetails = iTsmService.getMessageDetails(getMessageDetailsRequestParams2, new BinderC10801b(this, 18, i2, (byte) 0));
                if (messageDetails != 0) {
                    HashMap hashMap = this.f20815v;
                    int[] iArr2 = this.f20816w;
                    int i3 = iArr2[18] - 1;
                    iArr2[18] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == messageDetails) {
                    return m5895a(18, getMessageDetailsRequestParams, iTsmCallback);
                }
                return messageDetails;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    public int getPubKey(int i, String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return -3;
        }
        ITsmService iTsmService = this.f20798e;
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

    public synchronized int getSeId(GetSeIdRequestParams getSeIdRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else if (!m5875e("01.00.10")) {
            i = -8;
        } else if (this.f20798e != null) {
            if (!m5880b(this.f20796c.getPackageName())) {
                return m5895a(15, getSeIdRequestParams, iTsmCallback);
            }
            GetSeIdRequestParams getSeIdRequestParams2 = new GetSeIdRequestParams();
            getSeIdRequestParams2.setReserve(m5877c(m5872h(m5873g(getSeIdRequestParams != null ? getSeIdRequestParams.getReserve() : ""))));
            this.f20812s.put(String.valueOf(this.f20816w[15]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f20798e;
                int[] iArr = this.f20816w;
                int i2 = iArr[15];
                iArr[15] = i2 + 1;
                int sEId = iTsmService.getSEId(getSeIdRequestParams2, new BinderC10801b(this, 15, i2, (byte) 0));
                if (sEId != 0) {
                    HashMap hashMap = this.f20812s;
                    int[] iArr2 = this.f20816w;
                    int i3 = iArr2[15] - 1;
                    iArr2[15] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == sEId) {
                    return m5895a(15, getSeIdRequestParams, iTsmCallback);
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

    public synchronized int getTransactionDetails(GetTransactionDetailsRequestParams getTransactionDetailsRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else if (!m5875e("01.00.17")) {
            i = -8;
        } else if (this.f20798e != null) {
            if (!m5880b(this.f20796c.getPackageName())) {
                return m5895a(17, getTransactionDetailsRequestParams, iTsmCallback);
            }
            GetTransactionDetailsRequestParams getTransactionDetailsRequestParams2 = new GetTransactionDetailsRequestParams();
            String str = "";
            if (getTransactionDetailsRequestParams != null) {
                str = getTransactionDetailsRequestParams.getReserve();
                Bundle params = getTransactionDetailsRequestParams.getParams();
                if (params != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("encryptData", m5892a(params));
                    getTransactionDetailsRequestParams2.setParams(bundle);
                }
            }
            getTransactionDetailsRequestParams2.setReserve(m5877c(m5872h(m5873g(str))));
            this.f20814u.put(String.valueOf(this.f20816w[17]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f20798e;
                int[] iArr = this.f20816w;
                int i2 = iArr[17];
                iArr[17] = i2 + 1;
                int transactionDetails = iTsmService.getTransactionDetails(getTransactionDetailsRequestParams2, new BinderC10801b(this, 17, i2, (byte) 0));
                if (transactionDetails != 0) {
                    HashMap hashMap = this.f20814u;
                    int[] iArr2 = this.f20816w;
                    int i3 = iArr2[17] - 1;
                    iArr2[17] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == transactionDetails) {
                    return m5895a(17, getTransactionDetailsRequestParams, iTsmCallback);
                }
                return transactionDetails;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    public synchronized int getVendorPayStatus(GetVendorPayStatusRequestParams getVendorPayStatusRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else if (!m5875e("01.00.08")) {
            i = -8;
        } else if (this.f20798e != null) {
            if (!m5880b(this.f20796c.getPackageName())) {
                return m5895a(9, getVendorPayStatusRequestParams, iTsmCallback);
            }
            GetVendorPayStatusRequestParams getVendorPayStatusRequestParams2 = new GetVendorPayStatusRequestParams();
            getVendorPayStatusRequestParams2.setReserve(m5877c(m5872h(m5873g(getVendorPayStatusRequestParams != null ? getVendorPayStatusRequestParams.getReserve() : ""))));
            this.f20807n.put(String.valueOf(this.f20816w[9]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f20798e;
                int[] iArr = this.f20816w;
                int i2 = iArr[9];
                iArr[9] = i2 + 1;
                int vendorPayStatus = iTsmService.getVendorPayStatus(getVendorPayStatusRequestParams2, new BinderC10801b(this, 9, i2, (byte) 0));
                if (vendorPayStatus != 0) {
                    HashMap hashMap = this.f20807n;
                    int[] iArr2 = this.f20816w;
                    int i3 = iArr2[9] - 1;
                    iArr2[9] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == vendorPayStatus) {
                    return m5895a(9, getVendorPayStatusRequestParams, iTsmCallback);
                }
                return vendorPayStatus;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    public synchronized int getVendorPayStatusForBankApp(GetVendorPayStatusRequestParams getVendorPayStatusRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else if (!m5875e("01.00.09")) {
            i = -8;
        } else if (this.f20798e != null) {
            if (!m5880b(this.f20796c.getPackageName())) {
                return m5895a(14, getVendorPayStatusRequestParams, iTsmCallback);
            }
            GetVendorPayStatusRequestParams getVendorPayStatusRequestParams2 = new GetVendorPayStatusRequestParams();
            getVendorPayStatusRequestParams2.setReserve(m5877c(m5872h(m5873g(getVendorPayStatusRequestParams != null ? getVendorPayStatusRequestParams.getReserve() : ""))));
            this.f20811r.put(String.valueOf(this.f20816w[14]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f20798e;
                int[] iArr = this.f20816w;
                int i2 = iArr[14];
                iArr[14] = i2 + 1;
                int vendorPayStatusForBankApp = iTsmService.getVendorPayStatusForBankApp(getVendorPayStatusRequestParams2, new BinderC10801b(this, 14, i2, (byte) 0));
                if (vendorPayStatusForBankApp != 0) {
                    HashMap hashMap = this.f20811r;
                    int[] iArr2 = this.f20816w;
                    int i3 = iArr2[14] - 1;
                    iArr2[14] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == vendorPayStatusForBankApp) {
                    return m5895a(14, getVendorPayStatusRequestParams, iTsmCallback);
                }
                return vendorPayStatusForBankApp;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004a A[Catch: all -> 0x0065, TRY_ENTER, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0005, B:7:0x0014, B:9:0x001a, B:10:0x0030, B:18:0x004a, B:13:0x0038, B:14:0x0040, B:15:0x0041, B:24:0x0053, B:25:0x005b, B:26:0x005c), top: B:35:0x0001, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0050 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized int hideKeyboard() {
        /*
            r5 = this;
            monitor-enter(r5)
            com.unionpay.tsmservice.mi.ITsmService r0 = r5.f20798e     // Catch: java.lang.Throwable -> L65
            if (r0 == 0) goto L62
            android.content.Context r0 = r5.f20796c     // Catch: java.lang.Throwable -> L65
            java.lang.String r0 = r0.getPackageName()     // Catch: java.lang.Throwable -> L65
            boolean r0 = m5880b(r0)     // Catch: java.lang.Throwable -> L65
            r1 = 0
            r2 = 5
            r3 = 0
            if (r0 == 0) goto L5c
            boolean r0 = r5.m5879c()     // Catch: java.lang.Throwable -> L65
            if (r0 == 0) goto L41
            com.unionpay.tsmservice.mi.request.HideSafetyKeyboardRequestParams r0 = new com.unionpay.tsmservice.mi.request.HideSafetyKeyboardRequestParams     // Catch: java.lang.Throwable -> L65
            r0.<init>()     // Catch: java.lang.Throwable -> L65
            java.lang.String r4 = ""
            java.lang.String r4 = m5873g(r4)     // Catch: java.lang.Throwable -> L65
            java.lang.String r4 = r5.m5872h(r4)     // Catch: java.lang.Throwable -> L65
            java.lang.String r4 = m5877c(r4)     // Catch: java.lang.Throwable -> L65
            r0.setReserve(r4)     // Catch: java.lang.Throwable -> L65
            com.unionpay.tsmservice.mi.ITsmService r4 = r5.f20798e     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L65
            int r0 = r4.hideSafetyKeyboard(r0)     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L65
            goto L47
        L37:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L65
            android.os.RemoteException r0 = new android.os.RemoteException     // Catch: java.lang.Throwable -> L65
            r0.<init>()     // Catch: java.lang.Throwable -> L65
            throw r0     // Catch: java.lang.Throwable -> L65
        L41:
            com.unionpay.tsmservice.mi.ITsmService r0 = r5.f20798e     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L65
            int r0 = r0.hideKeyboard()     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L65
        L47:
            r4 = -2
            if (r4 != r0) goto L50
            int r0 = m5894a(r2, r3, r1, r3, r3)     // Catch: java.lang.Throwable -> L65
            monitor-exit(r5)
            return r0
        L50:
            monitor-exit(r5)
            return r0
        L52:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L65
            android.os.RemoteException r0 = new android.os.RemoteException     // Catch: java.lang.Throwable -> L65
            r0.<init>()     // Catch: java.lang.Throwable -> L65
            throw r0     // Catch: java.lang.Throwable -> L65
        L5c:
            int r0 = m5894a(r2, r3, r1, r3, r3)     // Catch: java.lang.Throwable -> L65
            monitor-exit(r5)
            return r0
        L62:
            r0 = -1
            monitor-exit(r5)
            return r0
        L65:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.p364mi.UPTsmAddon.hideKeyboard():int");
    }

    public synchronized int init(InitRequestParams initRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else if (this.f20798e != null) {
            if (!m5880b(this.f20796c.getPackageName())) {
                return m5895a(0, initRequestParams, iTsmCallback);
            }
            InitRequestParams initRequestParams2 = new InitRequestParams();
            String str = "";
            if (initRequestParams != null) {
                str = initRequestParams.getReserve();
                String signature = initRequestParams.getSignature();
                if (!TextUtils.isEmpty(signature)) {
                    initRequestParams2.setSignature(m5877c(signature));
                }
            }
            if (m5879c()) {
                str = m5872h(m5873g(str));
            }
            if (!TextUtils.isEmpty(str)) {
                initRequestParams2.setReserve(m5877c(str));
            }
            this.f20800g.put(String.valueOf(this.f20816w[0]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f20798e;
                int[] iArr = this.f20816w;
                int i2 = iArr[0];
                iArr[0] = i2 + 1;
                int init = iTsmService.init(initRequestParams2, new BinderC10801b(this, 0, i2, (byte) 0));
                if (init != 0) {
                    HashMap hashMap = this.f20800g;
                    int[] iArr2 = this.f20816w;
                    int i3 = iArr2[0] - 1;
                    iArr2[0] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == init) {
                    return m5895a(0, initRequestParams, iTsmCallback);
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
        return this.f20799f;
    }

    public synchronized int onlinePaymentVerify(OnlinePaymentVerifyRequestParams onlinePaymentVerifyRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (onlinePaymentVerifyRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else if (!m5875e("01.00.08")) {
            i = -8;
        } else if (this.f20798e != null) {
            if (!m5880b(this.f20796c.getPackageName())) {
                return m5895a(10, onlinePaymentVerifyRequestParams, iTsmCallback);
            }
            OnlinePaymentVerifyRequestParams onlinePaymentVerifyRequestParams2 = new OnlinePaymentVerifyRequestParams();
            Bundle params = onlinePaymentVerifyRequestParams.getParams();
            if (params != null) {
                Bundle bundle = new Bundle();
                bundle.putString("encryptData", m5892a(params));
                onlinePaymentVerifyRequestParams2.setParams(bundle);
            }
            onlinePaymentVerifyRequestParams2.setReserve(m5877c(m5872h(m5873g(onlinePaymentVerifyRequestParams.getReserve()))));
            this.f20808o.put(String.valueOf(this.f20816w[10]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f20798e;
                int[] iArr = this.f20816w;
                int i2 = iArr[10];
                iArr[10] = i2 + 1;
                int onlinePaymentVerify = iTsmService.onlinePaymentVerify(onlinePaymentVerifyRequestParams2, new BinderC10801b(this, 10, i2, (byte) 0));
                if (onlinePaymentVerify != 0) {
                    HashMap hashMap = this.f20808o;
                    int[] iArr2 = this.f20816w;
                    int i3 = iArr2[10] - 1;
                    iArr2[10] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == onlinePaymentVerify) {
                    return m5895a(10, onlinePaymentVerifyRequestParams, iTsmCallback);
                }
                return onlinePaymentVerify;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    public synchronized int payResultNotify(PayResultNotifyRequestParams payResultNotifyRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (payResultNotifyRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else if (!m5875e("01.00.08")) {
            i = -8;
        } else if (this.f20798e != null) {
            if (!m5880b(this.f20796c.getPackageName())) {
                return m5895a(12, payResultNotifyRequestParams, iTsmCallback);
            }
            PayResultNotifyRequestParams payResultNotifyRequestParams2 = new PayResultNotifyRequestParams();
            Bundle params = payResultNotifyRequestParams.getParams();
            if (params != null) {
                Bundle bundle = new Bundle();
                bundle.putString("encryptData", m5892a(params));
                payResultNotifyRequestParams2.setParams(bundle);
            }
            payResultNotifyRequestParams2.setReserve(m5877c(m5872h(m5873g(payResultNotifyRequestParams.getReserve()))));
            this.f20810q.put(String.valueOf(this.f20816w[12]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f20798e;
                int[] iArr = this.f20816w;
                int i2 = iArr[12];
                iArr[12] = i2 + 1;
                int payResultNotify = iTsmService.payResultNotify(payResultNotifyRequestParams2, new BinderC10801b(this, 12, i2, (byte) 0));
                if (payResultNotify != 0) {
                    HashMap hashMap = this.f20810q;
                    int[] iArr2 = this.f20816w;
                    int i3 = iArr2[12] - 1;
                    iArr2[12] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == payResultNotify) {
                    return m5895a(12, payResultNotifyRequestParams, iTsmCallback);
                }
                return payResultNotify;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    public synchronized int pinRequest(PinRequestRequestParams pinRequestRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else if (!m5875e("01.00.08")) {
            i = -8;
        } else if (this.f20798e != null) {
            if (!m5880b(this.f20796c.getPackageName())) {
                return m5895a(11, pinRequestRequestParams, iTsmCallback);
            }
            PinRequestRequestParams pinRequestRequestParams2 = new PinRequestRequestParams();
            String str = "";
            if (pinRequestRequestParams != null) {
                str = pinRequestRequestParams.getReserve();
                Bundle params = pinRequestRequestParams.getParams();
                if (params != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("encryptData", m5892a(params));
                    pinRequestRequestParams2.setParams(bundle);
                }
            }
            pinRequestRequestParams2.setReserve(m5877c(m5872h(m5873g(str))));
            this.f20809p.put(String.valueOf(this.f20816w[11]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f20798e;
                int[] iArr = this.f20816w;
                int i2 = iArr[11];
                iArr[11] = i2 + 1;
                int pinRequest = iTsmService.pinRequest(pinRequestRequestParams2, new BinderC10801b(this, 11, i2, (byte) 0));
                if (pinRequest != 0) {
                    HashMap hashMap = this.f20809p;
                    int[] iArr2 = this.f20816w;
                    int i3 = iArr2[11] - 1;
                    iArr2[11] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == pinRequest) {
                    return m5895a(11, pinRequestRequestParams, iTsmCallback);
                }
                return pinRequest;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    public synchronized int queryVendorPayStatus(QueryVendorPayStatusRequestParams queryVendorPayStatusRequestParams, ITsmCallback iTsmCallback) {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else if (!m5875e("01.00.07")) {
            i = -8;
        } else if (this.f20798e != null) {
            if (!m5880b(this.f20796c.getPackageName())) {
                return m5895a(8, queryVendorPayStatusRequestParams, iTsmCallback);
            }
            QueryVendorPayStatusRequestParams queryVendorPayStatusRequestParams2 = new QueryVendorPayStatusRequestParams();
            queryVendorPayStatusRequestParams2.setReserve(m5877c(m5872h(m5873g(queryVendorPayStatusRequestParams != null ? queryVendorPayStatusRequestParams.getReserve() : ""))));
            this.f20806m.put(String.valueOf(this.f20816w[8]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f20798e;
                int[] iArr = this.f20816w;
                int i2 = iArr[8];
                iArr[8] = i2 + 1;
                int queryVendorPayStatus = iTsmService.queryVendorPayStatus(queryVendorPayStatusRequestParams2, new BinderC10801b(this, 8, i2, (byte) 0));
                if (queryVendorPayStatus != 0) {
                    HashMap hashMap = this.f20806m;
                    int[] iArr2 = this.f20816w;
                    int i3 = iArr2[8] - 1;
                    iArr2[8] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == queryVendorPayStatus) {
                    return m5895a(8, queryVendorPayStatusRequestParams, iTsmCallback);
                }
                return queryVendorPayStatus;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = -1;
        }
        return i;
    }

    public synchronized void removeConnectionListener(UPTsmConnectionListener uPTsmConnectionListener) {
        if (uPTsmConnectionListener != null) {
            f20795b.remove(uPTsmConnectionListener);
        }
    }

    public synchronized int setSafetyKeyboardBitmap(SafetyKeyboardRequestParams safetyKeyboardRequestParams) {
        int i;
        if (safetyKeyboardRequestParams == null) {
            i = -3;
        } else if (this.f20798e != null) {
            if (!m5880b(this.f20796c.getPackageName())) {
                return m5895a(3, safetyKeyboardRequestParams, (ITsmCallback) null);
            }
            String reserve = safetyKeyboardRequestParams.getReserve();
            if (m5879c()) {
                reserve = m5872h(m5873g(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                safetyKeyboardRequestParams.setReserve(m5877c(reserve));
            }
            try {
                int safetyKeyboardBitmap = this.f20798e.setSafetyKeyboardBitmap(safetyKeyboardRequestParams);
                if (-2 == safetyKeyboardBitmap) {
                    return m5895a(3, safetyKeyboardRequestParams, (ITsmCallback) null);
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

    public synchronized int showSafetyKeyboard(SafetyKeyboardRequestParams safetyKeyboardRequestParams, int i, OnSafetyKeyboardCallback onSafetyKeyboardCallback, Context context) {
        int i2;
        if (safetyKeyboardRequestParams == null || i < 2000 || i > 2002) {
            i2 = -3;
        } else if (this.f20798e != null) {
            if (!m5880b(this.f20796c.getPackageName())) {
                return m5894a(1000, safetyKeyboardRequestParams, i, onSafetyKeyboardCallback, context);
            }
            this.f20803j.put(this.f20796c.getPackageName(), new BinderC10802a(context));
            String reserve = safetyKeyboardRequestParams.getReserve();
            if (m5879c()) {
                reserve = m5872h(m5873g(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                safetyKeyboardRequestParams.setReserve(m5877c(reserve));
            }
            try {
                int showSafetyKeyboard = this.f20798e.showSafetyKeyboard(safetyKeyboardRequestParams, i, onSafetyKeyboardCallback, new BinderC10800a());
                if (showSafetyKeyboard != 0) {
                    this.f20803j.remove(this.f20796c.getPackageName());
                }
                if (-2 == showSafetyKeyboard) {
                    return m5894a(1000, safetyKeyboardRequestParams, i, onSafetyKeyboardCallback, context);
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
        ServiceConnection serviceConnection = this.f20797d;
        if (serviceConnection == null || !this.f20799f) {
            return;
        }
        this.f20796c.unbindService(serviceConnection);
        this.f20799f = false;
    }
}
