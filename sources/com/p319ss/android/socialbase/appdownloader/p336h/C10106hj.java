package com.p319ss.android.socialbase.appdownloader.p336h;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.p083v4.app.NotificationManagerCompat;
import android.view.KeyEvent;
import com.bytedance.applog.tracker.Tracker;
import com.p319ss.android.socialbase.appdownloader.C10125lz;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10097o;
import com.p319ss.android.socialbase.appdownloader.view.FragmentC10173mb;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.appdownloader.h.hj */
/* loaded from: E:\567196_dexfile_execute.dex */
public class C10106hj {

    /* renamed from: b */
    private static FragmentC10173mb f19472b = null;

    /* renamed from: hj */
    private static AlertDialog f19473hj = null;

    /* renamed from: mb */
    private static final String f19474mb = "hj";

    /* renamed from: ox */
    private static List<InterfaceC10097o> f19475ox = new ArrayList();

    /* renamed from: mb */
    public static boolean m6838mb() {
        try {
            return NotificationManagerCompat.from(DownloadComponentManager.getAppContext()).areNotificationsEnabled();
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    /* renamed from: mb */
    public static synchronized void m6835mb(boolean z) {
        synchronized (C10106hj.class) {
            try {
                if (f19473hj != null) {
                    f19473hj.cancel();
                    f19473hj = null;
                }
                for (InterfaceC10097o interfaceC10097o : f19475ox) {
                    if (interfaceC10097o != null) {
                        if (z) {
                            interfaceC10097o.mo6785mb();
                        } else {
                            interfaceC10097o.mo6784ox();
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: mb */
    public static synchronized void m6837mb(@NonNull final Activity activity, @NonNull final InterfaceC10097o interfaceC10097o) {
        synchronized (C10106hj.class) {
            if (interfaceC10097o == null) {
                return;
            }
            if (activity != null) {
                try {
                } catch (Throwable th) {
                    th.printStackTrace();
                    m6835mb(false);
                }
                if (!activity.isFinishing()) {
                    int m6745mb = C10125lz.m6745mb(DownloadComponentManager.getAppContext(), "tt_appdownloader_notification_request_title");
                    int m6745mb2 = C10125lz.m6745mb(DownloadComponentManager.getAppContext(), "tt_appdownloader_notification_request_message");
                    int m6745mb3 = C10125lz.m6745mb(DownloadComponentManager.getAppContext(), "tt_appdownloader_notification_request_btn_yes");
                    int m6745mb4 = C10125lz.m6745mb(DownloadComponentManager.getAppContext(), "tt_appdownloader_notification_request_btn_no");
                    f19475ox.add(interfaceC10097o);
                    if (f19473hj == null || !f19473hj.isShowing()) {
                        f19473hj = new AlertDialog.Builder(activity).setTitle(m6745mb).setMessage(m6745mb2).setPositiveButton(m6745mb3, new DialogInterface.OnClickListener() { // from class: com.ss.android.socialbase.appdownloader.h.hj.3
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Tracker.onClick(dialogInterface, i);
                                C10106hj.m6834ox(activity, interfaceC10097o);
                                dialogInterface.cancel();
                                AlertDialog unused = C10106hj.f19473hj = null;
                            }
                        }).setNegativeButton(m6745mb4, new DialogInterface.OnClickListener() { // from class: com.ss.android.socialbase.appdownloader.h.hj.2
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Tracker.onClick(dialogInterface, i);
                                C10106hj.m6835mb(false);
                            }
                        }).setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.ss.android.socialbase.appdownloader.h.hj.1
                            @Override // android.content.DialogInterface.OnKeyListener
                            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                                if (i == 4) {
                                    if (keyEvent.getAction() == 1) {
                                        C10106hj.m6835mb(false);
                                    }
                                    return true;
                                }
                                return false;
                            }
                        }).setCancelable(false).show();
                    }
                    return;
                }
            }
            interfaceC10097o.mo6784ox();
        }
    }

    /* renamed from: ox */
    public static void m6834ox(@NonNull Activity activity, @NonNull InterfaceC10097o interfaceC10097o) {
        if (activity != null) {
            try {
                if (!activity.isFinishing()) {
                    FragmentManager fragmentManager = activity.getFragmentManager();
                    f19472b = (FragmentC10173mb) fragmentManager.findFragmentByTag(f19474mb);
                    if (f19472b == null) {
                        f19472b = new FragmentC10173mb();
                        fragmentManager.beginTransaction().add(f19472b, f19474mb).commitAllowingStateLoss();
                        fragmentManager.executePendingTransactions();
                    }
                    f19472b.m6495mb();
                    return;
                }
            } catch (Throwable th) {
                try {
                    th.printStackTrace();
                    interfaceC10097o.mo6785mb();
                    return;
                } catch (Throwable th2) {
                    th2.printStackTrace();
                    return;
                }
            }
        }
        interfaceC10097o.mo6785mb();
    }
}
