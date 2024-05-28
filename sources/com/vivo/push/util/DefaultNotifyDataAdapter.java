package com.vivo.push.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import com.vivo.push.model.InsideNotificationItem;

/* renamed from: com.vivo.push.util.l */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class DefaultNotifyDataAdapter implements BaseNotifyDataAdapter {

    /* renamed from: e */
    private static int f21215e;

    /* renamed from: f */
    private static int f21216f;

    /* renamed from: a */
    private Resources f21217a;

    /* renamed from: b */
    private String f21218b;

    /* renamed from: c */
    private String f21219c;

    /* renamed from: d */
    private String f21220d;

    /* renamed from: a */
    private static boolean m5389a(int i) {
        return (i == -1 || i == 0) ? false : true;
    }

    @Override // com.vivo.push.util.BaseNotifyDataAdapter
    public final void init(Context context) {
        this.f21218b = context.getPackageName();
        this.f21217a = context.getResources();
        this.f21219c = Device.m5386a();
        this.f21220d = Build.VERSION.RELEASE;
    }

    @Override // com.vivo.push.util.BaseNotifyDataAdapter
    public final int getDefaultNotifyIcon() {
        if (m5389a(f21215e)) {
            return f21215e;
        }
        String str = this.f21220d;
        int m5387a = !m5388a(str) ? -1 : m5387a(str, "_notifyicon");
        f21215e = m5387a;
        if (m5389a(m5387a)) {
            return f21215e;
        }
        for (String str2 = this.f21219c; !TextUtils.isEmpty(str2); str2 = str2.substring(0, str2.length() - 1)) {
            Resources resources = this.f21217a;
            int identifier = resources.getIdentifier("vivo_push_rom" + str2 + "_notifyicon", "drawable", this.f21218b);
            if (identifier > 0) {
                return identifier;
            }
        }
        return this.f21217a.getIdentifier("vivo_push_notifyicon", "drawable", this.f21218b);
    }

    @Override // com.vivo.push.util.BaseNotifyDataAdapter
    public final int getDefaultSmallIconId() {
        if (m5389a(f21216f)) {
            return f21216f;
        }
        String str = this.f21220d;
        int m5387a = !m5388a(str) ? -1 : m5387a(str, "_icon");
        f21216f = m5387a;
        if (m5389a(m5387a)) {
            return f21216f;
        }
        for (String str2 = this.f21219c; !TextUtils.isEmpty(str2); str2 = str2.substring(0, str2.length() - 1)) {
            Resources resources = this.f21217a;
            int identifier = resources.getIdentifier("vivo_push_rom" + str2 + "_icon", "drawable", this.f21218b);
            if (identifier > 0) {
                return identifier;
            }
        }
        return this.f21217a.getIdentifier("vivo_push_icon", "drawable", this.f21218b);
    }

    /* renamed from: a */
    private static boolean m5388a(String str) {
        if (Build.VERSION.SDK_INT < 26) {
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.m5341d("DefaultNotifyDataAdapter", "systemVersion is not suit ");
            return false;
        }
        return true;
    }

    /* renamed from: a */
    private int m5387a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return -1;
        }
        String[] split = str.split("\\.");
        if (split != null && split.length > 0) {
            str = split[0];
        }
        try {
            for (int parseInt = Integer.parseInt(str); parseInt > 0; parseInt--) {
                String str3 = "vivo_push_ard" + parseInt + str2;
                LogUtil.m5342c("DefaultNotifyDataAdapter", "get notify icon : ".concat(String.valueOf(str3)));
                int identifier = this.f21217a.getIdentifier(str3, "drawable", this.f21218b);
                if (identifier > 0) {
                    LogUtil.m5342c("DefaultNotifyDataAdapter", "find notify icon : ".concat(String.valueOf(str3)));
                    return identifier;
                }
            }
        } catch (Exception e) {
            LogUtil.m5352a("DefaultNotifyDataAdapter", e);
        }
        return -1;
    }

    @Override // com.vivo.push.util.BaseNotifyDataAdapter
    public final int getNotifyMode(InsideNotificationItem insideNotificationItem) {
        return Build.VERSION.SDK_INT >= 21 ? 2 : 1;
    }
}
