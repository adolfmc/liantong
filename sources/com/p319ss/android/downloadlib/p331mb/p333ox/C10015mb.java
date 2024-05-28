package com.p319ss.android.downloadlib.p331mb.p333ox;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Pair;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.p331mb.p333ox.InterfaceC10009b;
import com.p319ss.android.socialbase.appdownloader.p340u.C10150b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadlib.mb.ox.mb */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C10015mb {

    /* renamed from: h */
    private static String f19297h = "";

    /* renamed from: hj */
    private static String f19298hj = "";

    /* renamed from: ko */
    private static volatile C10015mb f19299ko = null;

    /* renamed from: u */
    private static String f19300u = "";

    /* renamed from: jb */
    private Context f19302jb;

    /* renamed from: mb */
    public InterfaceC10009b f19305mb;

    /* renamed from: ww */
    private boolean f19309ww = true;

    /* renamed from: lz */
    private boolean f19304lz = false;

    /* renamed from: x */
    private volatile boolean f19310x = false;

    /* renamed from: je */
    private final List<Pair<C10018ox, InterfaceC10012hj>> f19303je = new ArrayList();

    /* renamed from: ox */
    public final List<InterfaceC10017mb> f19308ox = new ArrayList();

    /* renamed from: nk */
    private final ServiceConnection f19306nk = new ServiceConnection() { // from class: com.ss.android.downloadlib.mb.ox.mb.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            synchronized (C10015mb.this.f19301b) {
                C10015mb.this.m7167mb(false);
                C10015mb.this.f19305mb = InterfaceC10009b.AbstractBinderC10010mb.m7180mb(iBinder);
                C10015mb.this.m7173b();
                for (InterfaceC10017mb interfaceC10017mb : C10015mb.this.f19308ox) {
                    interfaceC10017mb.m7165mb();
                }
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            synchronized (C10015mb.this.f19301b) {
                C10015mb.this.m7167mb(false);
                C10015mb.this.f19305mb = null;
                for (InterfaceC10017mb interfaceC10017mb : C10015mb.this.f19308ox) {
                    interfaceC10017mb.m7164ox();
                }
            }
        }
    };

    /* renamed from: o */
    private String f19307o = "";

    /* renamed from: b */
    public final Object f19301b = new Object();

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.mb.ox.mb$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface InterfaceC10017mb {
        /* renamed from: mb */
        void m7165mb();

        /* renamed from: ox */
        void m7164ox();
    }

    private C10015mb() {
    }

    /* renamed from: mb */
    public static C10015mb m7171mb() {
        if (f19299ko == null) {
            synchronized (C10015mb.class) {
                if (f19299ko == null) {
                    f19299ko = new C10015mb();
                }
            }
        }
        return f19299ko;
    }

    /* renamed from: mb */
    public boolean m7169mb(Context context, boolean z) {
        if (TextUtils.isEmpty(f19298hj)) {
            JSONObject m7364lz = C9940x.m7364lz();
            String optString = m7364lz.optString("s");
            f19298hj = C10150b.m6594mb(m7364lz.optString("q"), optString);
            f19297h = C10150b.m6594mb(m7364lz.optString("u"), optString);
            f19300u = C10150b.m6594mb(m7364lz.optString("w"), optString);
        }
        this.f19304lz = z;
        if (context != null) {
            this.f19302jb = context.getApplicationContext();
            if (TextUtils.isEmpty(f19300u)) {
                f19300u = this.f19302jb.getPackageName();
            }
            if (this.f19305mb != null || m7172hj()) {
                return true;
            }
            return this.f19302jb.bindService(m7170mb(context), this.f19306nk, 33);
        }
        return true;
    }

    /* renamed from: ox */
    public void m7166ox() {
        if (this.f19305mb != null) {
            this.f19302jb.unbindService(this.f19306nk);
            this.f19305mb = null;
        }
        this.f19308ox.clear();
        this.f19303je.clear();
    }

    /* renamed from: mb */
    public Intent m7170mb(Context context) {
        Intent intent = new Intent();
        intent.setAction(f19298hj);
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.size() != 1) {
            return null;
        }
        for (ResolveInfo resolveInfo : queryIntentServices) {
            String str = resolveInfo.serviceInfo.packageName;
            String str2 = resolveInfo.serviceInfo.name;
            if (f19297h.equals(str)) {
                ComponentName componentName = new ComponentName(str, str2);
                Intent intent2 = new Intent(intent);
                intent2.setComponent(componentName);
                return intent2;
            }
        }
        return null;
    }

    /* renamed from: mb */
    public void m7168mb(C10018ox c10018ox, InterfaceC10012hj interfaceC10012hj) {
        synchronized (this.f19301b) {
            c10018ox.f19313h = f19300u;
            if (TextUtils.isEmpty(c10018ox.f19317u)) {
                c10018ox.f19317u = this.f19307o;
            }
            if (this.f19305mb != null) {
                try {
                    this.f19305mb.mo7178mb(c10018ox, interfaceC10012hj);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else if (m7172hj() || m7169mb(this.f19302jb, this.f19304lz)) {
                this.f19303je.add(Pair.create(c10018ox, interfaceC10012hj));
            }
        }
    }

    /* renamed from: b */
    public void m7173b() {
        for (Pair<C10018ox, InterfaceC10012hj> pair : this.f19303je) {
            try {
                this.f19305mb.mo7178mb((C10018ox) pair.first, (InterfaceC10012hj) pair.second);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        this.f19303je.clear();
    }

    /* renamed from: hj */
    public boolean m7172hj() {
        return this.f19310x;
    }

    /* renamed from: mb */
    public void m7167mb(boolean z) {
        this.f19310x = z;
    }
}
