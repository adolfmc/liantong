package cn.finalteam.toolsfinal.logger;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface Printer {
    void clear();

    /* renamed from: d */
    void mo22024d(String str, Object... objArr);

    /* renamed from: e */
    void mo22023e(String str, Object... objArr);

    /* renamed from: e */
    void mo22022e(Throwable th);

    /* renamed from: e */
    void mo22021e(Throwable th, String str, Object... objArr);

    Settings getSettings();

    /* renamed from: i */
    void mo22020i(String str, Object... objArr);

    void json(String str);

    /* renamed from: t */
    Printer mo22019t(String str, int i);

    /* renamed from: v */
    void mo22018v(String str, Object... objArr);

    /* renamed from: w */
    void mo22017w(String str, Object... objArr);

    void wtf(String str, Object... objArr);

    void xml(String str);
}
