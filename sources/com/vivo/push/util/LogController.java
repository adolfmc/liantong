package com.vivo.push.util;

import android.content.Context;
import android.os.Process;
import android.util.Log;
import com.vivo.push.p367a.CommandBridge;
import com.vivo.push.p368b.OnLogReceiveCommand;
import com.vivo.push.p374g.TestManager;

/* renamed from: com.vivo.push.util.s */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class LogController implements LogProxy {

    /* renamed from: a */
    private static final String f21240a = "(" + Process.myPid() + ")";

    @Override // com.vivo.push.util.LogProxy
    /* renamed from: a */
    public final int mo5369a(String str, String str2) {
        String concat = "VivoPush.Client.".concat(String.valueOf(str));
        return Log.e(concat, f21240a + str2);
    }

    @Override // com.vivo.push.util.LogProxy
    /* renamed from: a */
    public final int mo5367a(String str, Throwable th) {
        return Log.e("VivoPush.Client.".concat(String.valueOf(str)), Log.getStackTraceString(th));
    }

    @Override // com.vivo.push.util.LogProxy
    /* renamed from: a */
    public final int mo5368a(String str, String str2, Throwable th) {
        String concat = "VivoPush.Client.".concat(String.valueOf(str));
        return Log.e(concat, f21240a + str2, th);
    }

    @Override // com.vivo.push.util.LogProxy
    /* renamed from: b */
    public final int mo5364b(String str, String str2) {
        String concat = "VivoPush.Client.".concat(String.valueOf(str));
        return Log.w(concat, f21240a + str2);
    }

    @Override // com.vivo.push.util.LogProxy
    /* renamed from: c */
    public final int mo5361c(String str, String str2) {
        String concat = "VivoPush.Client.".concat(String.valueOf(str));
        return Log.d(concat, f21240a + str2);
    }

    @Override // com.vivo.push.util.LogProxy
    /* renamed from: d */
    public final int mo5360d(String str, String str2) {
        if (LogUtil.m5358a()) {
            String concat = "VivoPush.Client.".concat(String.valueOf(str));
            return Log.i(concat, f21240a + str2);
        }
        return -1;
    }

    @Override // com.vivo.push.util.LogProxy
    /* renamed from: b */
    public final int mo5363b(String str, String str2, Throwable th) {
        if (LogUtil.m5358a()) {
            String concat = "VivoPush.Client.".concat(String.valueOf(str));
            return Log.i(concat, f21240a + str2, th);
        }
        return -1;
    }

    @Override // com.vivo.push.util.LogProxy
    /* renamed from: e */
    public final int mo5359e(String str, String str2) {
        if (LogUtil.m5358a()) {
            String concat = "VivoPush.Client.".concat(String.valueOf(str));
            return Log.v(concat, f21240a + str2);
        }
        return -1;
    }

    @Override // com.vivo.push.util.LogProxy
    /* renamed from: a */
    public final String mo5366a(Throwable th) {
        return Log.getStackTraceString(th);
    }

    @Override // com.vivo.push.util.LogProxy
    /* renamed from: a */
    public final void mo5370a(Context context, String str) {
        if (m5372a()) {
            m5371a(context, str, 0);
        }
    }

    @Override // com.vivo.push.util.LogProxy
    /* renamed from: b */
    public final void mo5365b(Context context, String str) {
        if (m5372a()) {
            m5371a(context, str, 1);
        }
    }

    @Override // com.vivo.push.util.LogProxy
    /* renamed from: c */
    public final void mo5362c(Context context, String str) {
        if (m5372a()) {
            m5371a(context, str, 2);
        }
    }

    /* renamed from: a */
    private void m5371a(Context context, String str, int i) {
        OnLogReceiveCommand onLogReceiveCommand = new OnLogReceiveCommand();
        onLogReceiveCommand.m5792b(str);
        onLogReceiveCommand.m5793a(i);
        if (i > 0) {
            mo5360d("LogController", str);
        }
        onLogReceiveCommand.m5788g();
        CommandBridge.m5816a(context, onLogReceiveCommand, context.getPackageName());
    }

    /* renamed from: a */
    private static boolean m5372a() {
        LogUtil.m5358a();
        return TestManager.m5664a().m5663b();
    }
}
