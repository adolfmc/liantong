package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.service.C11537ah;
import com.xiaomi.push.service.XMPushService;
import java.io.File;

/* renamed from: com.xiaomi.push.ga */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11398ga implements XMPushService.InterfaceC11513n {

    /* renamed from: a */
    private static boolean f22419a;

    /* renamed from: a */
    private int f22420a;

    /* renamed from: a */
    private Context f22421a;

    /* renamed from: b */
    private boolean f22422b;

    public C11398ga(Context context) {
        this.f22421a = context;
    }

    @Override // com.xiaomi.push.service.XMPushService.InterfaceC11513n
    /* renamed from: a */
    public void mo2566a() {
        m3703a(this.f22421a);
        if (this.f22422b && m3704a()) {
            AbstractC11049b.m5282a("TinyData TinyDataCacheProcessor.pingFollowUpAction ts:" + System.currentTimeMillis());
            InterfaceC11403ge m3689a = C11402gd.m3687a(this.f22421a).m3689a();
            if (!m3702a(m3689a)) {
                AbstractC11049b.m5282a("TinyData TinyDataCacheProcessor.pingFollowUpAction !canUpload(uploader) ts:" + System.currentTimeMillis());
                return;
            }
            f22419a = true;
            C11399gb.m3698a(this.f22421a, m3689a);
        }
    }

    /* renamed from: a */
    private void m3703a(Context context) {
        this.f22422b = C11537ah.m2715a(context).m2716a(EnumC11409gk.TinyDataUploadSwitch.m3637a(), true);
        this.f22420a = C11537ah.m2715a(context).m2719a(EnumC11409gk.TinyDataUploadFrequency.m3637a(), 7200);
        this.f22420a = Math.max(60, this.f22420a);
    }

    /* renamed from: a */
    private boolean m3704a() {
        return Math.abs((System.currentTimeMillis() / 1000) - this.f22421a.getSharedPreferences("mipush_extra", 4).getLong("last_tiny_data_upload_timestamp", -1L)) > ((long) this.f22420a);
    }

    /* renamed from: a */
    private boolean m3702a(InterfaceC11403ge interfaceC11403ge) {
        if (!C11169au.m4849a(this.f22421a) || interfaceC11403ge == null || TextUtils.isEmpty(m3701a(this.f22421a.getPackageName())) || !new File(this.f22421a.getFilesDir(), "tiny_data.data").exists() || f22419a) {
            return false;
        }
        return !C11537ah.m2715a(this.f22421a).m2716a(EnumC11409gk.ScreenOnOrChargingTinyDataUploadSwitch.m3637a(), false) || C11455i.m3049a(this.f22421a) || C11455i.m3040b(this.f22421a);
    }

    /* renamed from: a */
    private String m3701a(String str) {
        return "com.xiaomi.xmsf".equals(str) ? "1000271" : this.f22421a.getSharedPreferences("pref_registered_pkg_names", 0).getString(str, null);
    }

    /* renamed from: a */
    public static void m3700a(boolean z) {
        f22419a = z;
    }
}
