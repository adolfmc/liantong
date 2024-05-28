package com.taisys.oti;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import com.android.internal.telephony.IIccPhoneBook;
import com.unionpay.tsmservice.request.GetTransElementsRequestParams;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.taisys.oti.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C10333a {

    /* renamed from: B */
    private static final int f19797B = 28474;

    /* renamed from: E */
    private static final boolean f19798E = true;

    /* renamed from: a */
    public static final int f19799a = 0;

    /* renamed from: b */
    public static final int f19800b = 1;

    /* renamed from: c */
    public static final int f19801c = 14;

    /* renamed from: d */
    public static final int f19802d = 0;

    /* renamed from: e */
    public static final int f19803e = 1;

    /* renamed from: f */
    private static final String f19804f = "SmartSIM";

    /* renamed from: g */
    private static final String f19805g = "000000990000";

    /* renamed from: D */
    private int f19808D;

    /* renamed from: h */
    private Context f19810h;

    /* renamed from: i */
    private InterfaceC10336b f19811i;

    /* renamed from: l */
    private Method f19814l;

    /* renamed from: m */
    private Method f19815m;

    /* renamed from: n */
    private Method f19816n;

    /* renamed from: q */
    private String f19819q;

    /* renamed from: r */
    private ContentResolver f19820r;

    /* renamed from: s */
    private Uri f19821s;

    /* renamed from: t */
    private String f19822t;

    /* renamed from: u */
    private String f19823u;

    /* renamed from: x */
    private int f19826x;

    /* renamed from: y */
    private String f19827y;

    /* renamed from: z */
    private String f19828z;

    /* renamed from: o */
    private int f19817o = 1;

    /* renamed from: p */
    private int f19818p = 14;

    /* renamed from: v */
    private String f19824v = f19805g;

    /* renamed from: A */
    private boolean f19806A = false;

    /* renamed from: C */
    private IIccPhoneBook f19807C = null;

    /* renamed from: F */
    private Handler f19809F = new Handler() { // from class: com.taisys.oti.a.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    C10350e.m6257d("ADN supported");
                    if (C10333a.this.f19811i != null) {
                        C10333a.this.f19811i.mo6233a(true);
                        return;
                    }
                    return;
                case 1:
                    C10350e.m6257d("ADN NO supported");
                    if (C10333a.this.f19811i != null) {
                        C10333a.this.f19811i.mo6233a(false);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };

    /* renamed from: j */
    private SmsManager f19812j = SmsManager.getDefault();

    /* renamed from: w */
    private int f19825w = 1;

    /* renamed from: k */
    private Method f19813k = null;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.taisys.oti.a$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC10336b {
        /* renamed from: a */
        void mo6233a(boolean z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.taisys.oti.a$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10335a {

        /* renamed from: a */
        int f19830a = -1;

        /* renamed from: b */
        String f19831b = null;

        /* renamed from: c */
        String f19832c = null;

        public C10335a() {
        }
    }

    public C10333a(Context context, InterfaceC10336b interfaceC10336b) {
        this.f19820r = null;
        this.f19821s = null;
        this.f19822t = null;
        this.f19826x = -1;
        this.f19827y = null;
        this.f19828z = null;
        this.f19808D = -1;
        this.f19810h = context;
        this.f19811i = interfaceC10336b;
        this.f19820r = this.f19810h.getContentResolver();
        this.f19826x = -1;
        this.f19821s = null;
        this.f19822t = null;
        this.f19808D = -1;
        this.f19827y = this.f19823u;
        this.f19828z = this.f19824v;
        new C10337c(this.f19809F).start();
    }

    /* renamed from: com.taisys.oti.a$c */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    class C10337c extends Thread {

        /* renamed from: b */
        private Handler f19835b;

        public C10337c(Handler handler) {
            this.f19835b = handler;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Message obtain = Message.obtain(this.f19835b, 1);
            C10350e.m6257d("ADN Helper Open");
            if (C10333a.this.m6349a() && C10333a.this.m6327h() && C10333a.this.m6328g()) {
                C10350e.m6257d("ADN Helper OK");
                obtain = Message.obtain(this.f19835b, 0);
            }
            obtain.sendToTarget();
        }
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public boolean m6349a() {
        boolean z = false;
        try {
            this.f19813k = Class.forName("android.telephony.SmsManager").getMethod("getAllMessagesFromIcc", new Class[0]);
            z = true;
        } catch (ClassNotFoundException e) {
            C10350e.m6266a(e.getStackTrace());
        } catch (NoSuchMethodException e2) {
            C10350e.m6266a(e2.getStackTrace());
        }
        if (m6330f()) {
            this.f19807C = m6334d();
            C10350e.m6257d("check AdnRecord is Ready");
            try {
                if (this.f19807C.getAdnRecordsInEf((int) f19797B) != null) {
                    C10350e.m6257d("AdnRecord not null");
                }
            } catch (RemoteException e3) {
                C10350e.m6266a(e3.getStackTrace());
            } catch (Exception e4) {
                C10350e.m6266a(e4.getStackTrace());
            }
            C10350e.m6257d("check AdnRecord is Ready end");
        } else {
            this.f19807C = null;
        }
        this.f19821s = m6332e();
        if (this.f19821s == null) {
            C10350e.m6257d("Uri is null");
        }
        return z;
    }

    /* renamed from: d */
    private IIccPhoneBook m6334d() {
        IIccPhoneBook iIccPhoneBook;
        Class<?> cls;
        Method method;
        Object invoke;
        C10350e.m6257d("Get Sim Phone Book Start");
        try {
            cls = Class.forName("android.os.ServiceManager");
            method = cls.getMethod("getService", String.class);
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
        if (method != null && (invoke = method.invoke(cls, "simphonebook")) != null) {
            iIccPhoneBook = IIccPhoneBook.Stub.asInterface((IBinder) invoke);
            C10350e.m6257d("Get Sim Phone Book End");
            return iIccPhoneBook;
        }
        iIccPhoneBook = null;
        C10350e.m6257d("Get Sim Phone Book End");
        return iIccPhoneBook;
    }

    /* renamed from: e */
    private Uri m6332e() {
        Cursor cursor;
        String[] strArr = {"content://icc/adn", "content://icc0/adn", "content://icc1/adn"};
        int i = 0;
        Uri uri = null;
        while (true) {
            if (i >= strArr.length) {
                break;
            }
            uri = Uri.parse(strArr[i]);
            if (uri != null) {
                C10350e.m6257d(String.valueOf(strArr[i]) + " Query Phone Book Star");
                try {
                    cursor = this.f19820r.query(uri, null, null, null, null);
                } catch (Exception e) {
                    C10350e.m6266a(e.getStackTrace());
                    cursor = null;
                }
                if (cursor != null) {
                    C10350e.m6257d(strArr[i]);
                    this.f19822t = strArr[i];
                    cursor.close();
                    break;
                }
                C10350e.m6257d(String.valueOf(strArr[i]) + " Query Phone Book end");
                uri = null;
            }
            i++;
        }
        return uri;
    }

    /* renamed from: f */
    private boolean m6330f() {
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
        this.f19816n = null;
        this.f19814l = null;
        this.f19815m = null;
        boolean z = false;
        if (cls != null) {
            try {
                this.f19814l = cls.getMethod("getAlphaTag", new Class[0]);
            } catch (NoSuchMethodException e3) {
                C10350e.m6266a(e3.getStackTrace());
                this.f19814l = null;
                C10350e.m6257d("get Tag Method NoSuchMethodException");
            }
            try {
                this.f19815m = cls.getMethod("getNumber", new Class[0]);
            } catch (NoSuchMethodException e4) {
                C10350e.m6266a(e4.getStackTrace());
                this.f19815m = null;
                C10350e.m6257d("get Number Method NoSuchMethodException");
            }
            if (this.f19814l == null || this.f19815m == null) {
                this.f19814l = null;
                this.f19815m = null;
            } else {
                z = true;
            }
            C10350e.m6257d("all AdnRecord Method success");
        }
        return z;
    }

    /* renamed from: b */
    public void m6340b() {
        C10350e.m6257d("ADN Close");
        m6345a(this.f19823u, this.f19824v);
        this.f19812j = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x005e  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean m6345a(java.lang.String r12, java.lang.String r13) {
        /*
            r11 = this;
            java.lang.String r0 = r11.f19822t
            r1 = 0
            if (r0 == 0) goto La
            android.net.Uri r0 = android.net.Uri.parse(r0)
            goto Lb
        La:
            r0 = r1
        Lb:
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L63
            java.lang.String r4 = "ContentProvider to delete"
            com.taisys.oti.C10350e.m6257d(r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L4a java.lang.SecurityException -> L53
            java.lang.String r5 = "tag='"
            r4.<init>(r5)     // Catch: java.lang.Exception -> L4a java.lang.SecurityException -> L53
            r4.append(r12)     // Catch: java.lang.Exception -> L4a java.lang.SecurityException -> L53
            java.lang.String r5 = "' AND "
            r4.append(r5)     // Catch: java.lang.Exception -> L4a java.lang.SecurityException -> L53
            java.lang.String r5 = "number='"
            r4.append(r5)     // Catch: java.lang.Exception -> L4a java.lang.SecurityException -> L53
            r4.append(r13)     // Catch: java.lang.Exception -> L4a java.lang.SecurityException -> L53
            java.lang.String r5 = "'"
            r4.append(r5)     // Catch: java.lang.Exception -> L4a java.lang.SecurityException -> L53
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Exception -> L4a java.lang.SecurityException -> L53
            android.content.ContentResolver r5 = r11.f19820r     // Catch: java.lang.Exception -> L4a java.lang.SecurityException -> L53
            int r0 = r5.delete(r0, r4, r1)     // Catch: java.lang.Exception -> L4a java.lang.SecurityException -> L53
            if (r0 > 0) goto L43
            java.lang.String r0 = "ContentProvider delete fail"
            com.taisys.oti.C10350e.m6257d(r0)     // Catch: java.lang.Exception -> L4a java.lang.SecurityException -> L53
            goto L5b
        L43:
            java.lang.String r0 = "ContentProvider delete success"
            com.taisys.oti.C10350e.m6257d(r0)     // Catch: java.lang.Exception -> L4a java.lang.SecurityException -> L53
            r0 = r2
            goto L5c
        L4a:
            r0 = move-exception
            java.lang.StackTraceElement[] r0 = r0.getStackTrace()
            com.taisys.oti.C10350e.m6266a(r0)
            goto L5b
        L53:
            r0 = move-exception
            java.lang.StackTraceElement[] r0 = r0.getStackTrace()
            com.taisys.oti.C10350e.m6266a(r0)
        L5b:
            r0 = r3
        L5c:
            if (r0 != 0) goto L64
            r11.f19822t = r1
            r11.f19821s = r1
            goto L64
        L63:
            r0 = r3
        L64:
            if (r0 != 0) goto Ld5
            com.android.internal.telephony.IIccPhoneBook r1 = r11.f19807C
            if (r1 == 0) goto Ld5
            java.lang.String r1 = "IccPhoneBook to delete"
            com.taisys.oti.C10350e.m6257d(r1)
            com.android.internal.telephony.IIccPhoneBook r4 = r11.f19807C     // Catch: java.lang.Exception -> Lbb java.lang.SecurityException -> Lc4 android.os.RemoteException -> Lcd
            r5 = 28474(0x6f3a, float:3.99E-41)
            java.lang.String r8 = ""
            java.lang.String r9 = ""
            r10 = 0
            r6 = r12
            r7 = r13
            boolean r1 = r4.updateAdnRecordsInEfBySearch(r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Exception -> Lbb java.lang.SecurityException -> Lc4 android.os.RemoteException -> Lcd
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lbb java.lang.SecurityException -> Lc4 android.os.RemoteException -> Lcd
            java.lang.String r5 = "IccPhoneBook delete "
            r4.<init>(r5)     // Catch: java.lang.Exception -> Lbb java.lang.SecurityException -> Lc4 android.os.RemoteException -> Lcd
            if (r1 == 0) goto L8a
            java.lang.String r5 = "success"
            goto L8c
        L8a:
            java.lang.String r5 = "fail"
        L8c:
            r4.append(r5)     // Catch: java.lang.Exception -> Lbb java.lang.SecurityException -> Lc4 android.os.RemoteException -> Lcd
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Exception -> Lbb java.lang.SecurityException -> Lc4 android.os.RemoteException -> Lcd
            com.taisys.oti.C10350e.m6257d(r4)     // Catch: java.lang.Exception -> Lbb java.lang.SecurityException -> Lc4 android.os.RemoteException -> Lcd
            if (r1 != 0) goto Lb9
            boolean r12 = r11.m6335c(r12, r13)     // Catch: java.lang.Exception -> Lbb java.lang.SecurityException -> Lc4 android.os.RemoteException -> Lcd
            if (r12 == 0) goto L9f
            r2 = r3
        L9f:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lbb java.lang.SecurityException -> Lc4 android.os.RemoteException -> Lcd
            java.lang.String r13 = "IccPhoneBook insert "
            r12.<init>(r13)     // Catch: java.lang.Exception -> Lbb java.lang.SecurityException -> Lc4 android.os.RemoteException -> Lcd
            if (r2 == 0) goto Lab
            java.lang.String r13 = "success"
            goto Lad
        Lab:
            java.lang.String r13 = "fail"
        Lad:
            r12.append(r13)     // Catch: java.lang.Exception -> Lbb java.lang.SecurityException -> Lc4 android.os.RemoteException -> Lcd
            java.lang.String r12 = r12.toString()     // Catch: java.lang.Exception -> Lbb java.lang.SecurityException -> Lc4 android.os.RemoteException -> Lcd
            com.taisys.oti.C10350e.m6257d(r12)     // Catch: java.lang.Exception -> Lbb java.lang.SecurityException -> Lc4 android.os.RemoteException -> Lcd
            r0 = r2
            goto Ld5
        Lb9:
            r0 = r1
            goto Ld5
        Lbb:
            r12 = move-exception
            java.lang.StackTraceElement[] r12 = r12.getStackTrace()
            com.taisys.oti.C10350e.m6266a(r12)
            goto Ld5
        Lc4:
            r12 = move-exception
            java.lang.StackTraceElement[] r12 = r12.getStackTrace()
            com.taisys.oti.C10350e.m6266a(r12)
            goto Ld5
        Lcd:
            r12 = move-exception
            java.lang.StackTraceElement[] r12 = r12.getStackTrace()
            com.taisys.oti.C10350e.m6266a(r12)
        Ld5:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taisys.oti.C10333a.m6345a(java.lang.String, java.lang.String):boolean");
    }

    /* renamed from: b */
    private boolean m6338b(String str, String str2) {
        boolean z;
        ContentValues contentValues;
        C10350e.m6257d("insert ADN Tag=" + str + ", number=" + str2);
        String str3 = this.f19822t;
        Uri parse = str3 != null ? Uri.parse(str3) : null;
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
            if (this.f19820r.insert(parse, contentValues) != null) {
                C10350e.m6257d("ContentProvider insert success");
                z = true;
                if (z && this.f19807C != null) {
                    C10350e.m6257d("IccPhoneBook to insert");
                    try {
                        boolean updateAdnRecordsInEfBySearch = this.f19807C.updateAdnRecordsInEfBySearch((int) f19797B, "", "", str, str2, (String) null);
                        StringBuilder sb = new StringBuilder("IccPhoneBook insertFunction ");
                        sb.append(updateAdnRecordsInEfBySearch ? "success" : "fail");
                        C10350e.m6257d(sb.toString());
                        if (updateAdnRecordsInEfBySearch) {
                            return updateAdnRecordsInEfBySearch;
                        }
                        boolean m6335c = m6335c(str, str2);
                        StringBuilder sb2 = new StringBuilder("IccPhoneBook insert ");
                        sb2.append(m6335c ? "success" : "fail");
                        C10350e.m6257d(sb2.toString());
                        return m6335c;
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
    private boolean m6343a(String str, String str2, String str3, String str4) {
        C10350e.m6257d("Update ADN tag=" + str + ", number=" + str2 + " ==> newTag=" + str3 + ", newNumber=" + str4);
        String str5 = this.f19822t;
        Uri parse = str5 != null ? Uri.parse(str5) : null;
        boolean z = false;
        if (parse != null) {
            C10350e.m6257d("ContentProvider to update");
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("tag", str);
                contentValues.put("number", str2);
                contentValues.put("newTag", str3);
                contentValues.put("newNumber", str4);
                if (this.f19820r.update(parse, contentValues, null, null) <= 0) {
                    C10350e.m6257d("ContentProvider update fail");
                } else {
                    C10350e.m6257d("ContentProvider update success");
                    z = true;
                }
            } catch (SecurityException e) {
                C10350e.m6266a(e.getStackTrace());
            } catch (Exception e2) {
                C10350e.m6266a(e2.getStackTrace());
            }
            if (!z) {
                this.f19822t = null;
                this.f19821s = null;
            }
        }
        if (z || this.f19807C == null) {
            return z;
        }
        C10350e.m6257d("IccPhoneBook to update");
        try {
            boolean updateAdnRecordsInEfBySearch = this.f19807C.updateAdnRecordsInEfBySearch((int) f19797B, str, str2, str3, str4, (String) null);
            StringBuilder sb = new StringBuilder("IccPhoneBook updateFunction ");
            sb.append(updateAdnRecordsInEfBySearch ? "success" : "fail");
            C10350e.m6257d(sb.toString());
            if (updateAdnRecordsInEfBySearch) {
                return updateAdnRecordsInEfBySearch;
            }
            boolean m6335c = m6335c(str3, str4);
            StringBuilder sb2 = new StringBuilder("IccPhoneBook update ");
            sb2.append(m6335c ? "success" : "fail");
            C10350e.m6257d(sb2.toString());
            this.f19806A = true;
            C10350e.m6257d("Maybe try IccPhoneBook insert if update fail");
            return m6335c;
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

    /* JADX WARN: Removed duplicated region for block: B:40:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00b2  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.List<com.taisys.oti.C10333a.C10335a> m6342a(boolean r10) {
        /*
            Method dump skipped, instructions count: 293
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taisys.oti.C10333a.m6342a(boolean):java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public boolean m6328g() {
        return m6341a(C10350e.f19911d, C10350e.m6270a("00")) != null;
    }

    /* renamed from: a */
    private String m6346a(String str, Character ch, int i) {
        StringBuilder sb = new StringBuilder(str);
        if (str.length() < i) {
            for (int length = str.length(); length < i; length++) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    public boolean m6344a(String str, String str2, int i) {
        int i2;
        C10350e.m6257d("write adn with " + this.f19817o);
        if (this.f19817o == 0) {
            i2 = this.f19818p - 2;
        } else {
            i2 = this.f19818p;
        }
        boolean z = true;
        int i3 = this.f19817o != 0 ? 1 : 2;
        int i4 = i2 / i3;
        C10350e.m6257d("writeContent...");
        int length = str2.length();
        int i5 = length % i4 > 0 ? (length / i4) + 1 : length / i4;
        ArrayList arrayList = new ArrayList();
        int i6 = 0;
        int i7 = 0;
        while (i6 < i5) {
            C10335a c10335a = new C10335a();
            int i8 = i6 * i4;
            i6++;
            int i9 = i6 * i4;
            if (i9 > str2.length()) {
                i9 = str2.length();
            }
            i7 += (i9 - i8) * i3;
            if (i7 > i) {
            }
            c10335a.f19831b = str2.substring(i8, i9);
            c10335a.f19832c = String.valueOf(str) + String.format("%02d", Integer.valueOf(this.f19825w)) + String.format("%02d", Integer.valueOf(i5)) + String.format("%02d", Integer.valueOf(i6));
            arrayList.add(c10335a);
        }
        this.f19825w++;
        this.f19825w %= 100;
        int i10 = 0;
        while (true) {
            if (i10 >= arrayList.size()) {
                break;
            }
            C10335a c10335a2 = (C10335a) arrayList.get(i10);
            if (!m6329f(c10335a2.f19831b, c10335a2.f19832c)) {
                z = false;
                break;
            }
            i10++;
        }
        m6326i();
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public boolean m6327h() {
        boolean z = false;
        for (int i : new int[]{14}) {
            this.f19818p = i;
            this.f19823u = m6346a(f19804f, (Character) ' ', this.f19818p);
            List<C10335a> m6342a = m6342a(false);
            if (m6342a != null) {
                if (m6342a.size() == 0) {
                    C10350e.m6257d("ADN cursor count 0 insert " + this.f19823u);
                    z = m6331e(this.f19823u, this.f19824v);
                } else {
                    C10350e.m6257d("ADN cursor size=" + m6342a.size());
                    z = m6333d(this.f19823u, this.f19824v);
                    if (!z) {
                        z = m6331e(this.f19823u, this.f19824v);
                    }
                }
                if (z) {
                    String str = this.f19823u;
                    String str2 = this.f19824v;
                    m6343a(str, str2, str, str2);
                }
            } else {
                z = false;
            }
            if (z) {
                break;
            }
        }
        if (z) {
            this.f19827y = this.f19823u;
            this.f19828z = this.f19824v;
        }
        StringBuilder sb = new StringBuilder("ADNRecord check ");
        sb.append(z ? "success" : "fail");
        C10350e.m6257d(sb.toString());
        return z;
    }

    /* renamed from: c */
    private boolean m6335c(String str, String str2) {
        List<C10335a> m6342a = m6342a(true);
        for (int i = 0; i < m6342a.size(); i++) {
            C10335a c10335a = m6342a.get(i);
            if (c10335a.f19831b.equals(str) && c10335a.f19832c.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: d */
    private boolean m6333d(String str, String str2) {
        List<C10335a> m6342a = m6342a(false);
        for (int i = 0; i < m6342a.size(); i++) {
            C10335a c10335a = m6342a.get(i);
            if (c10335a.f19831b.equals(str) && c10335a.f19832c.equals(str2)) {
                this.f19826x = c10335a.f19830a;
                C10350e.m6257d("Dummy RecId=" + this.f19826x);
                return true;
            }
        }
        return false;
    }

    /* renamed from: e */
    private boolean m6331e(String str, String str2) {
        try {
            return m6338b(str, str2);
        } catch (Exception e) {
            C10350e.m6266a(e.getStackTrace());
            return false;
        }
    }

    /* renamed from: f */
    private boolean m6329f(String str, String str2) {
        C10350e.m6257d("ADN write Apdu...");
        boolean m6343a = m6343a(this.f19827y, this.f19828z, str, str2);
        if (!m6343a) {
            return this.f19806A ? m6331e(str, str2) : m6343a;
        }
        this.f19827y = str;
        this.f19828z = str2;
        return m6343a;
    }

    /* renamed from: i */
    private boolean m6326i() {
        C10350e.m6257d("restore to dummy");
        int i = this.f19825w;
        int i2 = i == 0 ? 99 : i - 1;
        this.f19824v = "000000" + String.format("%02d", Integer.valueOf(i2)) + GetTransElementsRequestParams.TRANS_TYPE_DOWNLOAD_APPLY;
        boolean m6343a = m6343a(this.f19827y, this.f19828z, this.f19823u, this.f19824v);
        if (m6343a) {
            this.f19827y = this.f19823u;
            this.f19828z = this.f19824v;
        }
        return m6343a;
    }

    /* renamed from: a */
    private List<SmsMessage> m6348a(SmsManager smsManager) {
        List<SmsMessage> list;
        try {
            C10350e.m6257d("Start read all sms");
            list = (List) this.f19813k.invoke(smsManager, null);
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
    public byte[] m6337c() {
        byte[] bArr = null;
        List<SmsMessage> m6348a = m6348a(this.f19812j);
        return m6348a != null ? C10349d.m6281a(m6348a, this.f19819q) : bArr;
    }

    /* renamed from: a */
    public byte[] m6341a(byte[] bArr, byte[] bArr2) {
        byte[] m6337c;
        byte[] bArr3 = null;
        if (this.f19817o == 0) {
            C10350e.m6257d("ADN write mode hex");
            this.f19819q = C10350e.m6256d(bArr2);
            String m6262b = C10350e.m6262b(bArr);
            String str = this.f19819q;
            if (m6344a(m6262b, str, str.length() / 2)) {
                bArr3 = m6337c();
            }
        }
        if (bArr3 == null || !C10350e.m6267a(bArr3)) {
            if (this.f19817o == 0) {
                this.f19817o = 1;
            }
            C10350e.m6257d("ADN write mode ascii");
            this.f19819q = C10350e.m6262b(bArr2);
            String m6262b2 = C10350e.m6262b(bArr);
            String str2 = this.f19819q;
            if (m6344a(m6262b2, str2, str2.length()) && (m6337c = m6337c()) != null && C10350e.m6267a(m6337c)) {
                return m6337c;
            }
            return null;
        }
        return bArr3;
    }
}
