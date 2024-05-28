package com.baidu.location.p137b;

import android.location.GnssNavigationMessage;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.baidu.location.p138c.C2688a;
import com.baidu.location.p138c.C2710k;
import com.baidu.location.p138c.C2711l;
import com.baidu.location.p140e.C2735k;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.b.aa */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class HandlerC2627aa extends Handler {

    /* renamed from: a */
    final /* synthetic */ C2686z f5152a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC2627aa(C2686z c2686z, Looper looper) {
        super(looper);
        this.f5152a = c2686z;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        C2688a m19314c;
        C2710k m19112n;
        Location m19313d;
        String m19321a;
        Handler handler;
        Handler handler2;
        switch (message.what) {
            case 1:
                Bundle data = message.getData();
                try {
                    Location location = (Location) data.getParcelable("loc");
                    data.getInt("satnum");
                    if (location != null) {
                        C2644h.m19473a().m19471a(location);
                        return;
                    }
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case 2:
                m19314c = C2676u.m19314c();
                m19112n = C2711l.m19133a().m19112n();
                m19313d = C2676u.m19313d();
                m19321a = C2676u.m19321a();
                break;
            case 3:
                m19314c = C2676u.m19314c();
                m19112n = null;
                m19313d = C2676u.m19313d();
                m19321a = C2628b.m19560a().m19548c();
                break;
            case 4:
                boolean m19117i = C2711l.m19133a().m19117i();
                if (C2735k.m19056b()) {
                    m19117i = false;
                }
                if (m19117i) {
                    C2650l.m19439a().m19428d();
                }
                try {
                    handler = this.f5152a.f5486d;
                    if (handler != null) {
                        handler2 = this.f5152a.f5486d;
                        handler2.sendEmptyMessageDelayed(4, C2735k.f5758Q);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                C2681x.m19305a().m19303b();
                return;
            case 5:
            case 6:
            case 10:
            default:
                return;
            case 7:
                C2684y.m19299a().m19286c();
                return;
            case 8:
            case 9:
                message.getData();
                return;
            case 11:
                Bundle data2 = message.getData();
                try {
                    long j = data2.getLong("gps_time");
                    C2681x.m19305a().m19304a((GnssNavigationMessage) data2.getParcelable("gnss_navigation_message"), j);
                    return;
                } catch (Exception unused) {
                    return;
                }
        }
        C2684y.m19292a(m19314c, m19112n, m19313d, m19321a, C2676u.m19312e());
    }
}
