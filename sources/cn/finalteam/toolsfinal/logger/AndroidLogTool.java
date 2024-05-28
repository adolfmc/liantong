package cn.finalteam.toolsfinal.logger;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AndroidLogTool implements LogTool {
    @Override // cn.finalteam.toolsfinal.logger.LogTool
    /* renamed from: d */
    public void mo22029d(String str, String str2) {
        Log.d(str, str2);
    }

    @Override // cn.finalteam.toolsfinal.logger.LogTool
    /* renamed from: e */
    public void mo22028e(String str, String str2) {
        Log.e(str, str2);
    }

    @Override // cn.finalteam.toolsfinal.logger.LogTool
    /* renamed from: w */
    public void mo22025w(String str, String str2) {
        Log.w(str, str2);
    }

    @Override // cn.finalteam.toolsfinal.logger.LogTool
    /* renamed from: i */
    public void mo22027i(String str, String str2) {
        Log.i(str, str2);
    }

    @Override // cn.finalteam.toolsfinal.logger.LogTool
    /* renamed from: v */
    public void mo22026v(String str, String str2) {
        Log.v(str, str2);
    }

    @Override // cn.finalteam.toolsfinal.logger.LogTool
    public void wtf(String str, String str2) {
        Log.wtf(str, str2);
    }
}
