package com.p319ss.android.socialbase.appdownloader;

import android.content.Context;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.appdownloader.lz */
/* loaded from: E:\567196_dexfile_execute.dex */
public class C10125lz {
    /* renamed from: mb */
    public static int m6744mb(String str) {
        try {
            return DownloadComponentManager.getAppContext().getResources().getIdentifier(str, "layout", DownloadComponentManager.getAppContext().getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* renamed from: mb */
    public static int m6745mb(Context context, String str) {
        try {
            return context.getResources().getIdentifier(str, "string", context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* renamed from: ox */
    public static int m6742ox(String str) {
        return m6745mb(DownloadComponentManager.getAppContext(), str);
    }

    /* renamed from: b */
    public static int m6748b(String str) {
        try {
            return m6743mb(str, DownloadComponentManager.getAppContext().getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* renamed from: mb */
    public static int m6743mb(String str, String str2) {
        try {
            return DownloadComponentManager.getAppContext().getResources().getIdentifier(str, "drawable", str2);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* renamed from: hj */
    public static int m6746hj(String str) {
        try {
            return DownloadComponentManager.getAppContext().getResources().getIdentifier(str, "style", DownloadComponentManager.getAppContext().getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* renamed from: h */
    public static int m6747h(String str) {
        try {
            return DownloadComponentManager.getAppContext().getResources().getIdentifier(str, "id", DownloadComponentManager.getAppContext().getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* renamed from: u */
    public static int m6740u(String str) {
        try {
            return DownloadComponentManager.getAppContext().getResources().getIdentifier(str, "color", DownloadComponentManager.getAppContext().getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* renamed from: ox */
    public static int m6741ox(String str, String str2) {
        try {
            return DownloadComponentManager.getAppContext().getResources().getIdentifier(str, "attr", str2);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
