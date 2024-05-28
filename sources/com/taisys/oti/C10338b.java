package com.taisys.oti;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import com.android.internal.telephony.IIccPhoneBook;
import com.unionpay.tsmservice.request.GetTransElementsRequestParams;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.taisys.oti.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C10338b {

    /* renamed from: B */
    private static final int f19836B = 28474;

    /* renamed from: G */
    private static final boolean f19837G = true;

    /* renamed from: a */
    public static final int f19838a = 0;

    /* renamed from: b */
    public static final int f19839b = 1;

    /* renamed from: c */
    public static final int f19840c = 14;

    /* renamed from: d */
    public static final int f19841d = 0;

    /* renamed from: e */
    public static final int f19842e = 1;

    /* renamed from: f */
    private static final String f19843f = "SmartSIM";

    /* renamed from: g */
    private static final String f19844g = "000000990000";

    /* renamed from: F */
    private List<C10342c> f19849F;

    /* renamed from: h */
    private Context f19851h;

    /* renamed from: i */
    private InterfaceC10341b f19852i;

    /* renamed from: l */
    private Method f19855l;

    /* renamed from: m */
    private Method f19856m;

    /* renamed from: n */
    private Method f19857n;

    /* renamed from: q */
    private String f19860q;

    /* renamed from: r */
    private ContentResolver f19861r;

    /* renamed from: u */
    private String f19864u;

    /* renamed from: x */
    private int f19867x;

    /* renamed from: y */
    private String f19868y;

    /* renamed from: z */
    private String f19869z;

    /* renamed from: o */
    private int f19858o = 1;

    /* renamed from: p */
    private int f19859p = 14;

    /* renamed from: s */
    private Uri f19862s = null;

    /* renamed from: t */
    private String f19863t = null;

    /* renamed from: v */
    private String f19865v = f19844g;

    /* renamed from: A */
    private boolean f19845A = false;

    /* renamed from: C */
    private IIccPhoneBook f19846C = null;

    /* renamed from: H */
    private Handler f19850H = new Handler() { // from class: com.taisys.oti.b.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    C10350e.m6257d("ADN2 supported");
                    if (C10338b.this.f19852i != null) {
                        C10338b.this.f19852i.mo6232a(true);
                        return;
                    }
                    return;
                case 1:
                    C10350e.m6257d("ADN2 NO supported");
                    if (C10338b.this.f19852i != null) {
                        C10338b.this.f19852i.mo6232a(false);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };

    /* renamed from: j */
    private SmsManager[] f19853j = null;

    /* renamed from: k */
    private Method f19854k = null;

    /* renamed from: w */
    private int f19866w = 1;

    /* renamed from: D */
    private int f19847D = -1;

    /* renamed from: E */
    private List<C10342c> f19848E = new ArrayList();

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.taisys.oti.b$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC10341b {
        /* renamed from: a */
        void mo6232a(boolean z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.taisys.oti.b$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10340a {

        /* renamed from: a */
        int f19871a = -1;

        /* renamed from: b */
        String f19872b = null;

        /* renamed from: c */
        String f19873c = null;

        public C10340a() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.taisys.oti.b$c */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10342c {

        /* renamed from: a */
        Uri f19875a = null;

        /* renamed from: b */
        String f19876b = null;

        /* renamed from: c */
        int f19877c = -1;

        /* renamed from: d */
        int f19878d = -1;

        /* renamed from: e */
        int f19879e = -1;

        /* renamed from: f */
        String f19880f = null;

        /* renamed from: g */
        String f19881g = null;

        /* renamed from: h */
        SmsManager f19882h = null;

        /* renamed from: i */
        String f19883i = null;

        public C10342c() {
        }
    }

    public C10338b(Context context, InterfaceC10341b interfaceC10341b) {
        this.f19861r = null;
        this.f19867x = -1;
        this.f19868y = null;
        this.f19869z = null;
        this.f19851h = context;
        this.f19852i = interfaceC10341b;
        this.f19861r = this.f19851h.getContentResolver();
        this.f19867x = -1;
        this.f19868y = this.f19864u;
        this.f19869z = this.f19865v;
        new C10343d(this.f19850H).start();
    }

    /* renamed from: com.taisys.oti.b$d */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    class C10343d extends Thread {

        /* renamed from: b */
        private Handler f19886b;

        public C10343d(Handler handler) {
            this.f19886b = handler;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Message obtain = Message.obtain(this.f19886b, 1);
            C10350e.m6257d("ADN Helper 2 Open");
            if (C10338b.this.m6325a() && C10338b.this.m6298h() && C10338b.this.m6299g()) {
                C10350e.m6257d("ADN Helper 2 OK");
                obtain = Message.obtain(this.f19886b, 0);
            }
            obtain.sendToTarget();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0075 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @android.annotation.SuppressLint({"NewApi"})
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean m6325a() {
        /*
            Method dump skipped, instructions count: 358
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taisys.oti.C10338b.m6325a():boolean");
    }

    /* renamed from: d */
    private IIccPhoneBook m6305d() {
        Object invoke;
        try {
            Class<?> cls = Class.forName("android.os.ServiceManager");
            Method method = cls.getMethod("getService", String.class);
            if (method != null && (invoke = method.invoke(cls, "simphonebook")) != null) {
                return IIccPhoneBook.Stub.asInterface((IBinder) invoke);
            }
        } catch (ClassNotFoundException e) {
            C10350e.m6266a(e.getStackTrace());
        } catch (IllegalAccessException e2) {
            C10350e.m6266a(e2.getStackTrace());
        } catch (IllegalArgumentException e3) {
            C10350e.m6266a(e3.getStackTrace());
        } catch (NoSuchMethodException e4) {
            C10350e.m6266a(e4.getStackTrace());
        } catch (InvocationTargetException e5) {
            C10350e.m6266a(e5.getStackTrace());
        }
        return null;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    private String m6323a(int i, int i2) {
        String str;
        TelephonyManager telephonyManager = (TelephonyManager) this.f19851h.getSystemService("phone");
        try {
            Class<?> cls = Class.forName("android.telephony.TelephonyManager");
            if (Build.VERSION.SDK_INT > 21) {
                str = (String) cls.getMethod("getSubscriberId", Integer.TYPE).invoke(telephonyManager, Integer.valueOf(i2));
            } else {
                str = Build.VERSION.SDK_INT == 21 ? (String) cls.getMethod("getSubscriberId", Long.TYPE).invoke(telephonyManager, Long.valueOf(i2)) : (String) Class.forName("android.telephony.MSimTelephonyManager").getMethod("getSubscriberId", Integer.TYPE).invoke(this.f19851h.getSystemService("phone_msim"), Integer.valueOf(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
            str = null;
        }
        C10350e.m6257d("IMSI" + i2 + "=" + str);
        return str;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    private SmsManager m6324a(int i) {
        SmsManager smsManager;
        SmsManager smsManager2;
        try {
            smsManager2 = (SmsManager) Class.forName("android.telephony.SmsManager").getMethod("getSmsManagerForSubscriptionId", Integer.TYPE).invoke(null, Integer.valueOf(i));
        } catch (Exception e) {
            e = e;
            smsManager = null;
        }
        try {
            C10350e.m6257d("Get SmsManager with SubscriptionId >> index=" + i);
            if (smsManager2 == null) {
                C10350e.m6257d("SmsManager is null");
                return SmsManager.getDefault();
            }
            return smsManager2;
        } catch (Exception e2) {
            e = e2;
            C10350e.m6266a(e.getStackTrace());
            try {
                SmsManager smsManager3 = (SmsManager) Class.forName("android.telephony.SmsManager").getMethod("getSmsManagerForSubscriber", Integer.TYPE).invoke(null, Integer.valueOf(i));
                try {
                    C10350e.m6257d("Get SmsManager with Subscriber >> index=" + i);
                    if (smsManager3 == null) {
                        C10350e.m6257d("SmsManager is null");
                        return SmsManager.getDefault();
                    }
                    return smsManager3;
                } catch (Exception e3) {
                    e = e3;
                    smsManager = smsManager3;
                    C10350e.m6266a(e.getStackTrace());
                    return smsManager;
                }
            } catch (Exception e4) {
                e = e4;
            }
        }
    }

    /* renamed from: e */
    private Uri m6303e() {
        Cursor cursor;
        String[] strArr = {"content://icc/adn", "content://icc0/adn", "content://icc1/adn"};
        int i = 0;
        Uri uri = null;
        while (true) {
            if (i >= strArr.length) {
                break;
            }
            this.f19863t = null;
            uri = Uri.parse(strArr[i]);
            if (uri != null) {
                C10350e.m6257d(String.valueOf(strArr[i]) + " Query Phone Book Star");
                try {
                    cursor = this.f19861r.query(uri, null, null, null, null);
                } catch (Exception e) {
                    C10350e.m6266a(e.getStackTrace());
                    cursor = null;
                }
                if (cursor != null) {
                    this.f19863t = strArr[i];
                    C10350e.m6257d("default uri=" + this.f19863t);
                    cursor.close();
                    break;
                }
                C10350e.m6257d(String.valueOf(strArr[i]) + " Query Phone Book end");
                uri = null;
            }
            i++;
        }
        if (uri == null) {
            C10350e.m6257d("default uri is null");
        }
        return uri;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0052 A[SYNTHETIC] */
    @android.annotation.SuppressLint({"NewApi"})
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m6314a(int[] r12) {
        /*
            r11 = this;
            java.util.List<com.taisys.oti.b$c> r0 = r11.f19848E
            r0.clear()
            if (r12 == 0) goto L6a
            r0 = 0
        L8:
            int r1 = r12.length
            if (r0 < r1) goto Lc
            goto L6a
        Lc:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "content://icc/adn/subId/"
            r1.<init>(r2)
            r2 = r12[r0]
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.net.Uri r8 = android.net.Uri.parse(r1)
            com.taisys.oti.b$c r9 = new com.taisys.oti.b$c
            r9.<init>()
            r9.f19877c = r0
            r2 = r12[r0]
            r9.f19878d = r2
            r10 = 0
            if (r8 == 0) goto L48
            android.content.ContentResolver r2 = r11.f19861r     // Catch: java.lang.Exception -> L3f
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r3 = r8
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Exception -> L3f
            if (r2 == 0) goto L49
            r2.close()     // Catch: java.lang.Exception -> L3f
            goto L48
        L3f:
            r2 = move-exception
            java.lang.StackTraceElement[] r2 = r2.getStackTrace()
            com.taisys.oti.C10350e.m6266a(r2)
            goto L49
        L48:
            r10 = r8
        L49:
            if (r10 == 0) goto L52
            com.taisys.oti.C10350e.m6257d(r1)
            r9.f19875a = r10
            r9.f19876b = r1
        L52:
            r1 = r12[r0]
            android.telephony.SmsManager r1 = r11.m6324a(r1)
            r9.f19882h = r1
            r1 = r12[r0]
            java.lang.String r1 = r11.m6323a(r0, r1)
            r9.f19883i = r1
            java.util.List<com.taisys.oti.b$c> r1 = r11.f19848E
            r1.add(r9)
            int r0 = r0 + 1
            goto L8
        L6a:
            java.util.List<com.taisys.oti.b$c> r12 = r11.f19848E
            int r12 = r12.size()
            if (r12 != 0) goto L9a
            com.taisys.oti.b$c r12 = new com.taisys.oti.b$c
            r12.<init>()
            android.net.Uri r0 = r11.f19862s
            r12.f19875a = r0
            java.lang.String r0 = r11.f19863t
            r12.f19876b = r0
            android.telephony.SmsManager r0 = android.telephony.SmsManager.getDefault()
            r12.f19882h = r0
            android.content.Context r0 = r11.f19851h
            java.lang.String r1 = "phone"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0
            java.lang.String r0 = r0.getSubscriberId()
            r12.f19883i = r0
            java.util.List<com.taisys.oti.b$c> r0 = r11.f19848E
            r0.add(r12)
        L9a:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r0 = "Uri List Count="
            r12.<init>(r0)
            java.util.List<com.taisys.oti.b$c> r0 = r11.f19848E
            int r0 = r0.size()
            r12.append(r0)
            java.lang.String r12 = r12.toString()
            com.taisys.oti.C10350e.m6257d(r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taisys.oti.C10338b.m6314a(int[]):void");
    }

    /* renamed from: f */
    private boolean m6301f() {
        Class<?> cls;
        try {
            C10350e.m6257d("try uicc AdnRecord");
            cls = Class.forName("com.android.internal.telephony.uicc.AdnRecord");
        } catch (ClassNotFoundException e) {
            C10350e.m6266a(e.getStackTrace());
            try {
                C10350e.m6257d("try telephony AdnRecord");
                cls = Class.forName("com.android.internal.telephony.AdnRecord");
            } catch (ClassNotFoundException e2) {
                C10350e.m6266a(e2.getStackTrace());
                cls = null;
            }
        }
        this.f19857n = null;
        this.f19855l = null;
        this.f19856m = null;
        boolean z = false;
        if (cls != null) {
            try {
                this.f19855l = cls.getMethod("getAlphaTag", new Class[0]);
            } catch (NoSuchMethodException e3) {
                C10350e.m6266a(e3.getStackTrace());
                this.f19855l = null;
                C10350e.m6257d("get Tag Method NoSuchMethodException");
            }
            try {
                this.f19856m = cls.getMethod("getNumber", new Class[0]);
            } catch (NoSuchMethodException e4) {
                C10350e.m6266a(e4.getStackTrace());
                this.f19856m = null;
                C10350e.m6257d("get Number Method NoSuchMethodException");
            }
            if (this.f19855l == null || this.f19856m == null) {
                this.f19855l = null;
                this.f19856m = null;
            } else {
                z = true;
            }
            C10350e.m6257d("all AdnRecord Method success");
        }
        return z;
    }

    /* renamed from: b */
    public void m6313b() {
        C10350e.m6257d("ADN2 Close");
        for (int i = 0; i < this.f19848E.size(); i++) {
            C10342c c10342c = this.f19848E.get(i);
            m6322a(i, c10342c.f19880f, c10342c.f19881g);
        }
        this.f19853j = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00a1 A[Catch: Exception -> 0x010f, SecurityException -> 0x0118, RemoteException -> 0x0121, TryCatch #5 {RemoteException -> 0x0121, SecurityException -> 0x0118, Exception -> 0x010f, blocks: (B:43:0x009c, B:45:0x00a1, B:48:0x00d4, B:51:0x00db, B:55:0x00ea, B:56:0x00f5, B:60:0x0104, B:46:0x00b9), top: B:75:0x009c }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00b9 A[Catch: Exception -> 0x010f, SecurityException -> 0x0118, RemoteException -> 0x0121, TryCatch #5 {RemoteException -> 0x0121, SecurityException -> 0x0118, Exception -> 0x010f, blocks: (B:43:0x009c, B:45:0x00a1, B:48:0x00d4, B:51:0x00db, B:55:0x00ea, B:56:0x00f5, B:60:0x0104, B:46:0x00b9), top: B:75:0x009c }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00d4 A[Catch: Exception -> 0x010f, SecurityException -> 0x0118, RemoteException -> 0x0121, TryCatch #5 {RemoteException -> 0x0121, SecurityException -> 0x0118, Exception -> 0x010f, blocks: (B:43:0x009c, B:45:0x00a1, B:48:0x00d4, B:51:0x00db, B:55:0x00ea, B:56:0x00f5, B:60:0x0104, B:46:0x00b9), top: B:75:0x009c }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0102  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean m6322a(int r18, java.lang.String r19, java.lang.String r20) {
        /*
            Method dump skipped, instructions count: 299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taisys.oti.C10338b.m6322a(int, java.lang.String, java.lang.String):boolean");
    }

    /* renamed from: b */
    private boolean m6311b(int i, String str, String str2) {
        boolean z;
        boolean updateAdnRecordsInEfBySearchForSubscriber;
        ContentValues contentValues;
        C10350e.m6257d("insert ADN Tag=" + str + ", number=" + str2);
        C10342c c10342c = this.f19848E.get(i);
        Uri parse = c10342c.f19876b != null ? Uri.parse(c10342c.f19876b) : null;
        if (parse != null) {
            C10350e.m6257d("ContentProvider to insert");
            try {
                contentValues = new ContentValues();
                contentValues.put("tag", str);
                contentValues.put("number", str2);
            } catch (SecurityException e) {
                C10350e.m6266a(e.getStackTrace());
            } catch (Exception e2) {
                C10350e.m6266a(e2.getStackTrace());
            }
            if (this.f19861r.insert(parse, contentValues) != null) {
                C10350e.m6257d("ContentProvider insert success");
                z = true;
                if (z && this.f19846C != null) {
                    C10350e.m6257d("IccPhoneBook to insert");
                    try {
                        if (c10342c.f19878d == -1) {
                            C10350e.m6257d("IccPhoneBook insert ");
                            updateAdnRecordsInEfBySearchForSubscriber = this.f19846C.updateAdnRecordsInEfBySearch((int) f19836B, "", "", str, str2, (String) null);
                        } else {
                            int i2 = c10342c.f19878d;
                            C10350e.m6257d("IccPhoneBook insert for Subscriber Id=" + i2);
                            updateAdnRecordsInEfBySearchForSubscriber = this.f19846C.updateAdnRecordsInEfBySearchForSubscriber(i2, (int) f19836B, "", "", str, str2, (String) null);
                        }
                        StringBuilder sb = new StringBuilder("IccPhoneBook insertFunction ");
                        sb.append(updateAdnRecordsInEfBySearchForSubscriber ? "success" : "fail");
                        C10350e.m6257d(sb.toString());
                        if (updateAdnRecordsInEfBySearchForSubscriber) {
                            return updateAdnRecordsInEfBySearchForSubscriber;
                        }
                        boolean m6307c = m6307c(i, str, str2);
                        StringBuilder sb2 = new StringBuilder("IccPhoneBook insert ");
                        sb2.append(m6307c ? "success" : "fail");
                        C10350e.m6257d(sb2.toString());
                        return m6307c;
                    } catch (RemoteException e3) {
                        C10350e.m6266a(e3.getStackTrace());
                        return z;
                    } catch (SecurityException e4) {
                        C10350e.m6266a(e4.getStackTrace());
                        return z;
                    } catch (Exception e5) {
                        C10350e.m6266a(e5.getStackTrace());
                        return z;
                    }
                }
            }
            C10350e.m6257d("ContentProvider insert fail");
        }
        z = false;
        return z ? z : z;
    }

    /* renamed from: a */
    private boolean m6320a(int i, String str, String str2, String str3, String str4) {
        boolean z;
        boolean updateAdnRecordsInEfBySearchForSubscriber;
        C10350e.m6257d("Update ADN tag=" + str + ", number=" + str2 + " ==> newTag=" + str3 + ", newNumber=" + str4);
        C10342c c10342c = this.f19848E.get(i);
        Uri parse = c10342c.f19876b != null ? Uri.parse(c10342c.f19876b) : null;
        boolean z2 = false;
        if (parse != null) {
            C10350e.m6257d("ContentProvider to update");
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("tag", str);
                contentValues.put("number", str2);
                contentValues.put("newTag", str3);
                contentValues.put("newNumber", str4);
                if (this.f19861r.update(parse, contentValues, null, null) <= 0) {
                    C10350e.m6257d("ContentProvider update fail");
                } else {
                    C10350e.m6257d("ContentProvider update success");
                    z2 = true;
                }
            } catch (SecurityException e) {
                C10350e.m6266a(e.getStackTrace());
            } catch (Exception e2) {
                C10350e.m6266a(e2.getStackTrace());
            }
            if (!z2) {
                c10342c.f19875a = null;
                c10342c.f19876b = null;
                this.f19848E.set(i, c10342c);
            }
            z = z2;
        } else {
            z = false;
        }
        if (z || this.f19846C == null) {
            return z;
        }
        C10350e.m6257d("IccPhoneBook to update");
        try {
            if (c10342c.f19878d == -1) {
                C10350e.m6257d("IccPhoneBook update");
                updateAdnRecordsInEfBySearchForSubscriber = this.f19846C.updateAdnRecordsInEfBySearch((int) f19836B, str, str2, str3, str4, (String) null);
            } else {
                int i2 = c10342c.f19878d;
                C10350e.m6257d("IccPhoneBook update for Subscriber Id=" + i2);
                updateAdnRecordsInEfBySearchForSubscriber = this.f19846C.updateAdnRecordsInEfBySearchForSubscriber(i2, (int) f19836B, str, str2, str3, str4, (String) null);
            }
            StringBuilder sb = new StringBuilder("IccPhoneBook updateFunction ");
            sb.append(updateAdnRecordsInEfBySearchForSubscriber ? "success" : "fail");
            C10350e.m6257d(sb.toString());
            if (updateAdnRecordsInEfBySearchForSubscriber) {
                return updateAdnRecordsInEfBySearchForSubscriber;
            }
            boolean m6307c = m6307c(i, str3, str4);
            StringBuilder sb2 = new StringBuilder("IccPhoneBook update ");
            sb2.append(m6307c ? "success" : "fail");
            C10350e.m6257d(sb2.toString());
            this.f19845A = true;
            C10350e.m6257d("Maybe try IccPhoneBook insert if update fail");
            return m6307c;
        } catch (RemoteException e3) {
            C10350e.m6266a(e3.getStackTrace());
            return z;
        } catch (SecurityException e4) {
            C10350e.m6266a(e4.getStackTrace());
            return z;
        } catch (Exception e5) {
            C10350e.m6266a(e5.getStackTrace());
            return z;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00b9 A[Catch: Exception -> 0x0129, SecurityException -> 0x0132, RemoteException -> 0x013b, TryCatch #5 {RemoteException -> 0x013b, SecurityException -> 0x0132, Exception -> 0x0129, blocks: (B:49:0x00b2, B:51:0x00b9, B:55:0x00e1, B:58:0x00e8, B:61:0x0104, B:63:0x010c, B:65:0x0114, B:66:0x0120, B:67:0x0123, B:52:0x00c5), top: B:78:0x00b2 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00c5 A[Catch: Exception -> 0x0129, SecurityException -> 0x0132, RemoteException -> 0x013b, TryCatch #5 {RemoteException -> 0x013b, SecurityException -> 0x0132, Exception -> 0x0129, blocks: (B:49:0x00b2, B:51:0x00b9, B:55:0x00e1, B:58:0x00e8, B:61:0x0104, B:63:0x010c, B:65:0x0114, B:66:0x0120, B:67:0x0123, B:52:0x00c5), top: B:78:0x00b2 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0123 A[Catch: Exception -> 0x0129, SecurityException -> 0x0132, RemoteException -> 0x013b, TRY_LEAVE, TryCatch #5 {RemoteException -> 0x013b, SecurityException -> 0x0132, Exception -> 0x0129, blocks: (B:49:0x00b2, B:51:0x00b9, B:55:0x00e1, B:58:0x00e8, B:61:0x0104, B:63:0x010c, B:65:0x0114, B:66:0x0120, B:67:0x0123, B:52:0x00c5), top: B:78:0x00b2 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.List<com.taisys.oti.C10338b.C10340a> m6319a(int r11, boolean r12) {
        /*
            Method dump skipped, instructions count: 324
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taisys.oti.C10338b.m6319a(int, boolean):java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public boolean m6299g() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            if (i >= this.f19848E.size()) {
                break;
            }
            this.f19866w = 0;
            C10342c c10342c = this.f19848E.get(i);
            this.f19864u = c10342c.f19880f;
            this.f19865v = c10342c.f19881g;
            this.f19868y = c10342c.f19880f;
            this.f19869z = c10342c.f19881g;
            this.f19847D = c10342c.f19877c;
            C10350e.m6257d("try slot " + this.f19847D);
            if (m6315a(C10350e.f19911d, C10350e.m6270a("00")) != null) {
                arrayList.add(c10342c);
                break;
            }
            i++;
        }
        if (arrayList.size() > 0) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                C10342c c10342c2 = (C10342c) arrayList.get(i2);
                if (c10342c2.f19876b != null) {
                    for (int i3 = 0; i3 < this.f19848E.size(); i3++) {
                        C10342c c10342c3 = this.f19848E.get(i3);
                        if (!c10342c2.f19876b.equals(c10342c3.f19876b)) {
                            m6322a(i3, c10342c3.f19880f, c10342c3.f19881g);
                        }
                    }
                }
            }
            this.f19848E = arrayList;
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private String m6316a(String str, Character ch, int i) {
        StringBuilder sb = new StringBuilder(str);
        if (str.length() < i) {
            for (int length = str.length(); length < i; length++) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    public boolean m6321a(int i, String str, String str2, int i2) {
        int i3;
        String str3;
        int i4;
        int i5 = i;
        this.f19859p = this.f19848E.get(i5).f19879e;
        if (this.f19858o == 0) {
            i3 = this.f19859p - 2;
        } else {
            i3 = this.f19859p;
        }
        boolean z = true;
        int i6 = this.f19858o == 0 ? 2 : 1;
        int i7 = i3 / i6;
        C10350e.m6257d("writeContent...");
        int length = str2.length();
        int i8 = length / i7;
        if (length % i7 > 0) {
            i8++;
        }
        ArrayList arrayList = new ArrayList();
        int i9 = 0;
        int i10 = 0;
        while (i9 < i8) {
            C10340a c10340a = new C10340a();
            int i11 = i9 * i7;
            i9++;
            int i12 = i9 * i7;
            if (i12 > str2.length()) {
                i4 = str2.length();
                str3 = str2;
            } else {
                str3 = str2;
                i4 = i12;
            }
            String substring = str3.substring(i11, i4);
            int length2 = substring.length() * i6;
            i10 += length2;
            if (i10 > i2 * ((2 - i6) + 1)) {
                length2 -= i10 - i2;
            }
            C10350e.m6257d("cmdLen=" + i2 + ",start=" + i11 + ",subend=" + i4 + ",end=" + i12 + ",sendCmdLen=" + length2);
            c10340a.f19872b = substring;
            StringBuilder sb = new StringBuilder(String.valueOf(str));
            sb.append(String.format("%02d", Integer.valueOf(this.f19866w)));
            sb.append(String.format("%02d", Integer.valueOf(i8)));
            sb.append(String.format("%02d", Integer.valueOf(i9)));
            c10340a.f19873c = sb.toString();
            arrayList.add(c10340a);
            z = true;
            i7 = i7;
            i5 = i;
        }
        this.f19866w += z ? 1 : 0;
        this.f19866w %= 100;
        int i13 = 0;
        while (true) {
            if (i13 >= arrayList.size()) {
                break;
            }
            C10340a c10340a2 = (C10340a) arrayList.get(i13);
            C10350e.m6257d("tag=" + c10340a2.f19872b + ",number=" + c10340a2.f19873c);
            if (!m6300f(i5, c10340a2.f19872b, c10340a2.f19873c)) {
                z = false;
                break;
            }
            i13++;
        }
        m6312b(i);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public boolean m6298h() {
        boolean z;
        int[] iArr = {14};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.f19848E.size(); i++) {
            C10342c c10342c = this.f19848E.get(i);
            int i2 = 0;
            while (true) {
                if (i2 >= iArr.length) {
                    z = false;
                    break;
                }
                this.f19859p = iArr[i2];
                this.f19864u = m6316a(f19843f, (Character) ' ', this.f19859p);
                List<C10340a> m6319a = m6319a(i, false);
                if (m6319a != null) {
                    if (m6319a.size() == 0) {
                        C10350e.m6257d("ADN cursor count 0 insert " + this.f19864u);
                        z = m6302e(i, this.f19864u, this.f19865v);
                    } else {
                        C10350e.m6257d("ADN cursor size=" + m6319a.size());
                        boolean m6304d = m6304d(i, this.f19864u, this.f19865v);
                        z = !m6304d ? m6302e(i, this.f19864u, this.f19865v) : m6304d;
                    }
                    if (z) {
                        String str = this.f19864u;
                        String str2 = this.f19865v;
                        m6320a(i, str, str2, str, str2);
                    }
                    StringBuilder sb = new StringBuilder("index=");
                    sb.append(i);
                    sb.append(", result=");
                    sb.append(z ? "success" : "fail");
                    C10350e.m6257d(sb.toString());
                    if (z) {
                        break;
                    }
                }
                i2++;
            }
            if (z) {
                C10342c c10342c2 = new C10342c();
                c10342c2.f19875a = c10342c.f19875a;
                c10342c2.f19876b = c10342c.f19876b;
                c10342c2.f19877c = c10342c.f19877c;
                c10342c2.f19878d = c10342c.f19878d;
                c10342c2.f19879e = this.f19859p;
                c10342c2.f19880f = this.f19864u;
                c10342c2.f19881g = this.f19865v;
                c10342c2.f19882h = c10342c.f19882h;
                arrayList.add(c10342c2);
            }
        }
        this.f19848E = arrayList;
        return this.f19848E.size() > 0;
    }

    /* renamed from: c */
    private boolean m6307c(int i, String str, String str2) {
        List<C10340a> m6319a = m6319a(i, true);
        for (int i2 = 0; i2 < m6319a.size(); i2++) {
            C10340a c10340a = m6319a.get(i2);
            if (c10340a.f19872b.equals(str) && c10340a.f19873c.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: d */
    private boolean m6304d(int i, String str, String str2) {
        List<C10340a> m6319a = m6319a(i, false);
        for (int i2 = 0; i2 < m6319a.size(); i2++) {
            C10340a c10340a = m6319a.get(i2);
            if (c10340a.f19872b.equals(str) && c10340a.f19873c.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: e */
    private boolean m6302e(int i, String str, String str2) {
        try {
            boolean m6311b = m6311b(i, str, str2);
            return !m6311b ? m6304d(i, str, str2) : m6311b;
        } catch (Exception e) {
            C10350e.m6266a(e.getStackTrace());
            return false;
        }
    }

    /* renamed from: f */
    private boolean m6300f(int i, String str, String str2) {
        C10350e.m6257d("ADN write Apdu...");
        boolean m6320a = m6320a(i, this.f19868y, this.f19869z, str, str2);
        if (!m6320a) {
            return this.f19845A ? m6302e(i, str, str2) : m6320a;
        }
        this.f19868y = str;
        this.f19869z = str2;
        return m6320a;
    }

    /* renamed from: b */
    private boolean m6312b(int i) {
        C10350e.m6257d("restore to dummy");
        int i2 = this.f19866w;
        int i3 = i2 == 0 ? 99 : i2 - 1;
        C10342c c10342c = this.f19848E.get(i);
        this.f19865v = "000000" + String.format("%02d", Integer.valueOf(i3)) + GetTransElementsRequestParams.TRANS_TYPE_DOWNLOAD_APPLY;
        boolean m6320a = m6320a(i, this.f19868y, this.f19869z, this.f19864u, this.f19865v);
        if (m6320a) {
            this.f19868y = this.f19864u;
            String str = this.f19865v;
            this.f19869z = str;
            c10342c.f19881g = str;
            this.f19848E.set(i, c10342c);
        }
        return m6320a;
    }

    /* renamed from: b */
    private List<SmsMessage> m6310b(SmsManager smsManager) {
        List<SmsMessage> list;
        try {
            C10350e.m6257d("Start read all sms");
            list = (List) this.f19854k.invoke(smsManager, null);
        } catch (Exception e) {
            C10350e.m6266a(e.getStackTrace());
        }
        if (list != null) {
            C10350e.m6257d("read sms list count=" + list.size());
            return list;
        }
        C10350e.m6257d("read sms is null");
        return null;
    }

    /* renamed from: c */
    public byte[] m6308c() {
        byte[] bArr = null;
        for (int i = 0; i < this.f19848E.size() && (bArr = m6318a(this.f19848E.get(i).f19882h)) == null; i++) {
        }
        return bArr;
    }

    /* renamed from: a */
    public byte[] m6318a(SmsManager smsManager) {
        byte[] bArr = null;
        C10350e.m6257d("AND2 receive Data");
        if (smsManager != null) {
            List<SmsMessage> m6310b = m6310b(smsManager);
            return m6310b != null ? C10349d.m6281a(m6310b, this.f19860q) : bArr;
        }
        C10350e.m6257d("smsManager is null");
        return bArr;
    }

    /* renamed from: a */
    public byte[] m6315a(byte[] bArr, byte[] bArr2) {
        this.f19860q = C10350e.m6262b(bArr2);
        byte[] bArr3 = null;
        for (int i = 0; i < this.f19848E.size(); i++) {
            C10342c c10342c = this.f19848E.get(i);
            this.f19864u = c10342c.f19880f;
            this.f19865v = c10342c.f19881g;
            this.f19868y = c10342c.f19880f;
            this.f19869z = c10342c.f19881g;
            if (this.f19858o == 0) {
                C10350e.m6257d("ADN write mode hex");
                this.f19860q = C10350e.m6256d(bArr2);
                String m6262b = C10350e.m6262b(bArr);
                String str = this.f19860q;
                if (m6321a(i, m6262b, str, str.length() / 2)) {
                    bArr3 = m6318a(c10342c.f19882h);
                }
            }
            if (bArr3 != null && C10350e.m6267a(bArr3)) {
                return bArr3;
            }
            if (this.f19858o == 0) {
                this.f19858o = 1;
            }
            C10350e.m6257d("ADN write mode ascii");
            this.f19860q = C10350e.m6262b(bArr2);
            String m6262b2 = C10350e.m6262b(bArr);
            String str2 = this.f19860q;
            if (m6321a(i, m6262b2, str2, str2.length() / 2) && (bArr3 = m6318a(c10342c.f19882h)) != null && C10350e.m6267a(bArr3)) {
                return bArr3;
            }
        }
        return null;
    }
}
