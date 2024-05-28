package com.taisys.oti;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"NewApi"})
/* renamed from: com.taisys.oti.c */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C10344c {

    /* renamed from: a */
    public final int f19887a = 0;

    /* renamed from: b */
    public final int f19888b = 1;

    /* renamed from: c */
    private Context f19889c;

    /* renamed from: d */
    private InterfaceC10347c f19890d;

    /* renamed from: e */
    private List<C10348d> f19891e;

    /* renamed from: f */
    private Method f19892f;

    /* renamed from: g */
    private Method f19893g;

    /* renamed from: h */
    private String f19894h;

    /* renamed from: i */
    private int f19895i;

    /* renamed from: j */
    private HandlerC10346b f19896j;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.taisys.oti.c$c */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC10347c {
        /* renamed from: a */
        void mo6234a(boolean z);
    }

    /* renamed from: b */
    public void m6287b() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.taisys.oti.c$d */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10348d {

        /* renamed from: a */
        int f19900a = -1;

        /* renamed from: b */
        int f19901b = -1;

        /* renamed from: c */
        SmsManager f19902c = null;

        /* renamed from: d */
        String f19903d = null;

        public C10348d() {
        }
    }

    public C10344c(Context context, InterfaceC10347c interfaceC10347c) {
        this.f19891e = null;
        this.f19895i = -1;
        this.f19896j = null;
        this.f19889c = context;
        this.f19890d = interfaceC10347c;
        this.f19896j = new HandlerC10346b(this);
        this.f19895i = -1;
        this.f19891e = new ArrayList();
        new C10345a(this.f19896j).start();
    }

    /* renamed from: com.taisys.oti.c$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static class HandlerC10346b extends Handler {

        /* renamed from: a */
        private WeakReference<C10344c> f19899a;

        public HandlerC10346b(C10344c c10344c) {
            this.f19899a = new WeakReference<>(c10344c);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            C10344c c10344c = this.f19899a.get();
            if (c10344c != null) {
                c10344c.m6294a(message);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m6294a(Message message) {
        switch (message.what) {
            case 0:
                InterfaceC10347c interfaceC10347c = this.f19890d;
                if (interfaceC10347c != null) {
                    interfaceC10347c.mo6234a(true);
                    return;
                }
                return;
            case 1:
                InterfaceC10347c interfaceC10347c2 = this.f19890d;
                if (interfaceC10347c2 != null) {
                    interfaceC10347c2.mo6234a(false);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: com.taisys.oti.c$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    class C10345a extends Thread {

        /* renamed from: b */
        private Handler f19898b;

        public C10345a(Handler handler) {
            this.f19898b = handler;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Message obtain = Message.obtain(this.f19898b, 1);
            C10350e.m6257d("open smsHelper");
            if (C10344c.this.m6285c()) {
                C10350e.m6257d("smsHelper open ok");
                if (C10344c.this.m6283e()) {
                    obtain = Message.obtain(this.f19898b, 0);
                }
            }
            obtain.sendToTarget();
        }
    }

    /* renamed from: a */
    public int m6297a() {
        return this.f19895i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public boolean m6283e() {
        boolean z = false;
        C10348d c10348d = null;
        for (int i = 0; i < this.f19891e.size(); i++) {
            c10348d = this.f19891e.get(i);
            C10350e.m6257d("try slot " + c10348d.f19900a);
            this.f19895i = c10348d.f19900a;
            int i2 = 0;
            while (true) {
                if (i2 >= 2) {
                    break;
                }
                byte[] m6288a = m6288a(C10350e.f19909b, C10350e.m6270a("20"), c10348d.f19902c);
                if (m6288a != null && C10350e.m6267a(m6288a)) {
                    z = true;
                    break;
                }
                i2++;
            }
            if (z) {
                break;
            }
        }
        if (z) {
            this.f19891e.clear();
            this.f19891e.add(c10348d);
        }
        return z;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    private String m6295a(int i, int i2) {
        String str;
        TelephonyManager telephonyManager = (TelephonyManager) this.f19889c.getSystemService("phone");
        try {
            Class<?> cls = Class.forName("android.telephony.TelephonyManager");
            if (Build.VERSION.SDK_INT > 21) {
                str = (String) cls.getMethod("getSubscriberId", Integer.TYPE).invoke(telephonyManager, Integer.valueOf(i2));
            } else {
                str = Build.VERSION.SDK_INT == 21 ? (String) cls.getMethod("getSubscriberId", Long.TYPE).invoke(telephonyManager, Long.valueOf(i2)) : (String) Class.forName("android.telephony.MSimTelephonyManager").getMethod("getSubscriberId", Integer.TYPE).invoke(this.f19889c.getSystemService("phone_msim"), Integer.valueOf(i));
            }
        } catch (Exception e) {
            C10350e.m6266a(e.getStackTrace());
            str = null;
        }
        C10350e.m6257d("IMSI(" + i + ")=" + str);
        return str;
    }

    /* renamed from: a */
    private SmsManager m6296a(int i) {
        SmsManager smsManager;
        if (Build.VERSION.SDK_INT >= 22) {
            return SmsManager.getSmsManagerForSubscriptionId(i);
        }
        try {
            smsManager = (SmsManager) Class.forName("android.telephony.SmsManager").getMethod("getSmsManagerForSubscriptionId", Integer.TYPE).invoke(null, Integer.valueOf(i));
            try {
                C10350e.m6257d("Get SmsManager with SubscriptionId >> index=" + i);
                if (smsManager == null) {
                    C10350e.m6257d("SmsManager is null");
                    return SmsManager.getDefault();
                }
                return smsManager;
            } catch (Exception e) {
                e = e;
                C10350e.m6266a(e.getStackTrace());
                try {
                    SmsManager smsManager2 = (SmsManager) Class.forName("android.telephony.SmsManager").getMethod("getSmsManagerForSubscriber", Long.TYPE).invoke(null, Integer.valueOf(i));
                    try {
                        C10350e.m6257d("Get SmsManager with Subscriber >> index=" + i);
                        if (smsManager2 == null) {
                            C10350e.m6257d("SmsManager is null");
                            return SmsManager.getDefault();
                        }
                        return smsManager2;
                    } catch (Exception e2) {
                        e = e2;
                        smsManager = smsManager2;
                        C10350e.m6266a(e.getStackTrace());
                        return smsManager;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            }
        } catch (Exception e4) {
            e = e4;
            smsManager = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00bd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean m6285c() {
        /*
            Method dump skipped, instructions count: 424
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taisys.oti.C10344c.m6285c():boolean");
    }

    /* renamed from: d */
    public byte[] m6284d() {
        byte[] m6293a;
        for (int i = 0; i < this.f19891e.size(); i++) {
            SmsManager smsManager = this.f19891e.get(i).f19902c;
            if (smsManager != null && (m6293a = m6293a(smsManager)) != null) {
                return m6293a;
            }
        }
        return null;
    }

    /* renamed from: a */
    public byte[] m6293a(SmsManager smsManager) {
        byte[] bArr = null;
        List<SmsMessage> m6286b = m6286b(smsManager);
        return (m6286b == null || (bArr = C10349d.m6281a(m6286b, this.f19894h)) == null) ? bArr : bArr;
    }

    /* renamed from: b */
    private List<SmsMessage> m6286b(SmsManager smsManager) {
        List<SmsMessage> list;
        try {
            C10350e.m6257d("Start read all sms");
            list = (List) this.f19892f.invoke(smsManager, null);
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

    /* renamed from: a */
    public boolean m6292a(SmsManager smsManager, int i, int i2, byte[] bArr) {
        boolean z = false;
        Object[] objArr = {Integer.valueOf(i), Integer.valueOf(i2), bArr};
        if (smsManager != null) {
            try {
                z = ((Boolean) this.f19893g.invoke(smsManager, objArr)).booleanValue();
                C10350e.m6257d("update Message=" + z);
                return z;
            } catch (IllegalAccessException e) {
                C10350e.m6266a(e.getStackTrace());
                return z;
            } catch (IllegalArgumentException e2) {
                C10350e.m6266a(e2.getStackTrace());
                return z;
            } catch (SecurityException e3) {
                C10350e.m6266a(e3.getStackTrace());
                return z;
            } catch (InvocationTargetException e4) {
                C10350e.m6266a(e4.getStackTrace());
                return z;
            } catch (Exception e5) {
                C10350e.m6266a(e5.getStackTrace());
                return z;
            }
        }
        return false;
    }

    /* renamed from: a */
    public byte[] m6289a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = null;
        for (int i = 0; i < this.f19891e.size() && (bArr3 = m6288a(bArr, bArr2, this.f19891e.get(i).f19902c)) == null; i++) {
        }
        return bArr3;
    }

    /* renamed from: a */
    public byte[] m6288a(byte[] bArr, byte[] bArr2, SmsManager smsManager) {
        this.f19894h = C10350e.m6262b(bArr2);
        return m6292a(smsManager, 1, 1, C10349d.m6278a(bArr, bArr2)) ? m6293a(smsManager) : null;
    }
}
