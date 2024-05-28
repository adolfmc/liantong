package com.huawei.hms.base.log;

import android.content.Context;
import android.util.Log;

/* renamed from: com.huawei.hms.base.log.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class LogAdaptor {

    /* renamed from: b */
    private String f11051b;

    /* renamed from: a */
    private int f11050a = 4;

    /* renamed from: c */
    private LogNode f11052c = new LogCatNode();

    /* renamed from: a */
    public void m15181a(Context context, int i, String str) {
        this.f11050a = i;
        this.f11051b = str;
        this.f11052c.mo15176a(context, "HMSCore");
    }

    /* renamed from: b */
    public void m15177b(int i, String str, String str2, Throwable th) {
        try {
            if (m15184a(i)) {
                LogRecord m15182a = m15182a(i, str, str2, th);
                LogNode logNode = this.f11052c;
                logNode.mo15174a(m15182a.m15166c() + m15182a.m15173a(), i, str, str2 + '\n' + Log.getStackTraceString(th));
            }
        } catch (OutOfMemoryError unused) {
            m15178b();
        }
    }

    /* renamed from: a */
    public LogNode m15185a() {
        return this.f11052c;
    }

    /* renamed from: a */
    public void m15180a(LogNode logNode) {
        this.f11052c = logNode;
    }

    /* renamed from: a */
    public boolean m15184a(int i) {
        return i >= this.f11050a;
    }

    /* renamed from: a */
    public void m15183a(int i, String str, String str2) {
        try {
            if (m15184a(i)) {
                LogRecord m15182a = m15182a(i, str, str2, null);
                this.f11052c.mo15174a(m15182a.m15166c() + m15182a.m15173a(), i, str, str2);
            }
        } catch (OutOfMemoryError unused) {
            m15178b();
        }
    }

    /* renamed from: b */
    private void m15178b() {
        try {
            Log.e("HMSSDK_LogAdaptor", "log happened OOM error.");
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public void m15179a(String str, String str2) {
        try {
            LogRecord m15182a = m15182a(4, str, str2, null);
            this.f11052c.mo15174a(m15182a.m15166c() + '\n' + m15182a.m15173a(), 4, str, str2);
        } catch (OutOfMemoryError unused) {
            m15178b();
        }
    }

    /* renamed from: a */
    private LogRecord m15182a(int i, String str, String str2, Throwable th) {
        LogRecord logRecord = new LogRecord(8, this.f11051b, i, str);
        logRecord.m15171a((LogRecord) str2);
        logRecord.m15169a(th);
        return logRecord;
    }
}
