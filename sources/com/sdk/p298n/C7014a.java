package com.sdk.p298n;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.sdk.p290f.C6998d;
import com.sdk.p292h.C7006a;
import com.sdk.p302r.C7037a;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sdk.n.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C7014a extends C7006a {

    /* renamed from: a */
    public static final String f18170a = "com.sdk.n.a";

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* renamed from: com.sdk.n.a$b */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static abstract class EnumC7016b {

        /* renamed from: a */
        public static final EnumC7016b f18171a;

        /* renamed from: b */
        public static final EnumC7016b f18172b;

        /* renamed from: c */
        public static final EnumC7016b f18173c;

        /* renamed from: d */
        public static final EnumC7016b f18174d;

        /* renamed from: e */
        public static final /* synthetic */ EnumC7016b[] f18175e;

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.sdk.n.a$b$a */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public enum C7017a extends EnumC7016b {
            public C7017a(String str, int i) {
                super(str, i);
            }

            @Override // com.sdk.p298n.C7014a.EnumC7016b
            /* renamed from: a */
            public int mo8144a() {
                return 2;
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.sdk.n.a$b$b */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public enum C7018b extends EnumC7016b {
            public C7018b(String str, int i) {
                super(str, i);
            }

            @Override // com.sdk.p298n.C7014a.EnumC7016b
            /* renamed from: a */
            public int mo8144a() {
                return 0;
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.sdk.n.a$b$c */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public enum C7019c extends EnumC7016b {
            public C7019c(String str, int i) {
                super(str, i);
            }

            @Override // com.sdk.p298n.C7014a.EnumC7016b
            /* renamed from: a */
            public int mo8144a() {
                return 1;
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.sdk.n.a$b$d */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public enum C7020d extends EnumC7016b {
            public C7020d(String str, int i) {
                super(str, i);
            }

            @Override // com.sdk.p298n.C7014a.EnumC7016b
            /* renamed from: a */
            public int mo8144a() {
                return -1;
            }
        }

        static {
            C7017a c7017a = new C7017a("WIFI_NET", 0);
            f18171a = c7017a;
            C7018b c7018b = new C7018b("WIFI", 1);
            f18172b = c7018b;
            C7019c c7019c = new C7019c("NET", 2);
            f18173c = c7019c;
            C7020d c7020d = new C7020d("UNKNOW", 3);
            f18174d = c7020d;
            f18175e = new EnumC7016b[]{c7017a, c7018b, c7019c, c7020d};
        }

        public EnumC7016b(String str, int i) {
        }

        public static EnumC7016b valueOf(String str) {
            return (EnumC7016b) Enum.valueOf(EnumC7016b.class, str);
        }

        public static EnumC7016b[] values() {
            return (EnumC7016b[]) f18175e.clone();
        }

        /* renamed from: a */
        public abstract int mo8144a();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* renamed from: com.sdk.n.a$c */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static abstract class EnumC7021c {

        /* renamed from: a */
        public static final EnumC7021c f18176a;

        /* renamed from: b */
        public static final EnumC7021c f18177b;

        /* renamed from: c */
        public static final EnumC7021c f18178c;

        /* renamed from: d */
        public static final /* synthetic */ EnumC7021c[] f18179d;

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.sdk.n.a$c$a */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public enum C7022a extends EnumC7021c {
            public C7022a(String str, int i) {
                super(str, i);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.sdk.n.a$c$b */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public enum C7023b extends EnumC7021c {
            public C7023b(String str, int i) {
                super(str, i);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.sdk.n.a$c$c */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public enum C7024c extends EnumC7021c {
            public C7024c(String str, int i) {
                super(str, i);
            }
        }

        static {
            C7022a c7022a = new C7022a("CMCC", 0);
            f18176a = c7022a;
            C7023b c7023b = new C7023b("CUCC", 1);
            f18177b = c7023b;
            C7024c c7024c = new C7024c("CTC", 2);
            f18178c = c7024c;
            f18179d = new EnumC7021c[]{c7022a, c7023b, c7024c};
        }

        public EnumC7021c(String str, int i) {
        }

        public static EnumC7021c valueOf(String str) {
            return (EnumC7021c) Enum.valueOf(EnumC7021c.class, str);
        }

        public static EnumC7021c[] values() {
            return (EnumC7021c[]) f18179d.clone();
        }
    }

    static {
        boolean z = C6998d.f18135a;
    }

    /* renamed from: a */
    public static int m8146a(Context context) {
        String simOperator = ((TelephonyManager) context.getSystemService("phone")).getSimOperator();
        if (C7037a.m8130a(simOperator).booleanValue() || C7037a.m8130a(simOperator).booleanValue()) {
            return 0;
        }
        if (!"46001".equals(simOperator) && !"46006".equals(simOperator) && !"46009".equals(simOperator)) {
            if (!"46000".equals(simOperator) && !"46002".equals(simOperator) && !"46004".equals(simOperator) && !"46007".equals(simOperator)) {
                if (!"46003".equals(simOperator) && !"46005".equals(simOperator) && !"46011".equals(simOperator)) {
                    NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
                    if (activeNetworkInfo == null) {
                        return 0;
                    }
                    String extraInfo = activeNetworkInfo.getExtraInfo();
                    if (!"cmnet".equals(extraInfo) && !"cmwap".equals(extraInfo)) {
                        if (!"3gwap".equals(extraInfo) && !"uniwap".equals(extraInfo) && !"3gnet".equals(extraInfo) && !"uninet".equals(extraInfo)) {
                            if (!"ctnet".equals(extraInfo) && !"ctwap".equals(extraInfo)) {
                                return 0;
                            }
                        }
                    }
                }
                return 2;
            }
            return 1;
        }
        return 3;
    }

    /* renamed from: b */
    public static EnumC7016b m8145b(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        try {
            connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            boolean z = true;
            if (activeNetworkInfo.getType() != 1) {
                if (activeNetworkInfo.getType() == 0) {
                    String str = f18170a;
                    Log.d(str, "Only Data");
                    if (!TextUtils.isEmpty(activeNetworkInfo.getExtraInfo())) {
                        Log.d(str, "getOnlineType: ");
                    }
                    return EnumC7016b.f18173c;
                }
                return EnumC7016b.f18174d;
            }
            try {
                Method declaredMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", new Class[0]);
                declaredMethod.setAccessible(true);
                z = ((Boolean) declaredMethod.invoke(connectivityManager, new Object[0])).booleanValue();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (z) {
                Log.d(f18170a, "Data and WIFI");
                return EnumC7016b.f18171a;
            }
            Log.d(f18170a, "Only WIFI");
            return EnumC7016b.f18172b;
        }
        return EnumC7016b.f18174d;
    }
}
