package com.p319ss.android.socialbase.appdownloader.view;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.bytedance.applog.tracker.Tracker;
import com.p319ss.android.socialbase.appdownloader.p336h.C10106hj;
import com.p319ss.android.socialbase.downloader.constants.DownloadConstants;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;

/* renamed from: com.ss.android.socialbase.appdownloader.view.mb */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class FragmentC10173mb extends Fragment {
    @Override // android.app.Fragment
    public void onHiddenChanged(boolean z) {
        Tracker.onHiddenChanged(this, z);
        super.onHiddenChanged(z);
    }

    @Override // android.app.Fragment
    public void onPause() {
        Tracker.onPause(this);
        super.onPause();
    }

    @Override // android.app.Fragment
    public void onResume() {
        Tracker.onResume(this);
        super.onResume();
    }

    @Override // android.app.Fragment
    public void setUserVisibleHint(boolean z) {
        Tracker.setUserVisibleHint(this, z);
        super.setUserVisibleHint(z);
    }

    /* renamed from: mb */
    public void m6495mb() {
        try {
            try {
                try {
                    startActivityForResult(m6493u(), 1000);
                } catch (Throwable unused) {
                    startActivityForResult(m6498b(), 1000);
                }
            } catch (Throwable unused2) {
                startActivityForResult(m6494ox(), 1000);
            }
        } catch (Throwable unused3) {
            startActivityForResult(m6496hj(), 1000);
        }
    }

    /* renamed from: ox */
    public Intent m6494ox() {
        Context m6497h = m6497h();
        if (m6497h == null) {
            return null;
        }
        String packageName = m6497h.getPackageName();
        if (!TextUtils.isEmpty(Build.MANUFACTURER)) {
            String lowerCase = Build.MANUFACTURER.toLowerCase();
            if (lowerCase.contains(DownloadConstants.LOWER_OPPO)) {
                Intent intent = new Intent();
                intent.putExtra("packageName", packageName);
                intent.setComponent(new ComponentName("com.color.safecenter", "com.color.safecenter.permission.PermissionManagerActivity"));
                return intent;
            } else if (lowerCase.contains("vivo")) {
                Intent intent2 = new Intent();
                intent2.putExtra("packagename", packageName);
                if (Build.VERSION.SDK_INT >= 25) {
                    intent2.setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.SoftPermissionDetailActivity"));
                } else {
                    intent2.setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.safeguard.SoftPermissionDetailActivity"));
                }
                return intent2;
            } else if (lowerCase.contains("meizu") && Build.VERSION.SDK_INT < 25) {
                Intent intent3 = new Intent("com.meizu.safe.security.SHOW_APPSEC");
                intent3.putExtra("packageName", packageName);
                intent3.setComponent(new ComponentName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity"));
                return intent3;
            }
        }
        return new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + m6497h.getPackageName()));
    }

    /* renamed from: h */
    private Context m6497h() {
        Context appContext = DownloadComponentManager.getAppContext();
        return (appContext != null || getActivity() == null || getActivity().isFinishing()) ? appContext : getActivity().getApplicationContext();
    }

    /* renamed from: b */
    public Intent m6498b() {
        Context m6497h = m6497h();
        if (m6497h == null) {
            return null;
        }
        return new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + m6497h.getPackageName()));
    }

    /* renamed from: u */
    private Intent m6493u() {
        Context m6497h = m6497h();
        if (m6497h == null) {
            return null;
        }
        Intent intent = new Intent("android.settings.APP_NOTIFICATION_SETTINGS");
        String packageName = m6497h.getPackageName();
        intent.putExtra("package", packageName);
        intent.putExtra("android.provider.extra.APP_PACKAGE", packageName);
        intent.putExtra("app_package", packageName);
        int i = m6497h.getApplicationInfo().uid;
        intent.putExtra("uid", i);
        intent.putExtra("app_uid", i);
        return intent;
    }

    /* renamed from: hj */
    public static Intent m6496hj() {
        return new Intent("android.settings.APPLICATION_SETTINGS");
    }

    @Override // android.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (C10106hj.m6838mb()) {
            C10106hj.m6835mb(true);
        } else {
            C10106hj.m6835mb(false);
        }
    }
}
