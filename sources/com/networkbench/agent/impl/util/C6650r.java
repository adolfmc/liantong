package com.networkbench.agent.impl.util;

import android.os.Looper;
import android.support.annotation.RequiresApi;
import com.networkbench.agent.impl.p254f.C6396h;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.util.r */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6650r {

    /* renamed from: b */
    private static final int f17243b = 1048576;

    /* renamed from: a */
    private int f17244a;

    public C6650r(int i) {
        this.f17244a = i;
    }

    @RequiresApi(api = 3)
    /* renamed from: a */
    public String m8766a() {
        for (Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
            if (entry.getKey().getName() == Looper.getMainLooper().getThread().getName()) {
                return m8765a(entry.getValue());
            }
        }
        return "";
    }

    /* renamed from: a */
    private String m8765a(StackTraceElement[] stackTraceElementArr) {
        StringBuilder sb = new StringBuilder();
        int length = stackTraceElementArr.length;
        int i = this.f17244a;
        if (i < length) {
            length = i;
        }
        for (int i2 = 0; i2 < length; i2++) {
            sb.append("\tat " + stackTraceElementArr[i2] + "\n");
        }
        return sb.toString();
    }

    /* renamed from: b */
    public String m8764b() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
            if (sb.length() >= 1048576) {
                break;
            }
            sb.append("--- ");
            sb.append(entry.getKey().getName());
            sb.append(" ---");
            sb.append("\r\n");
            sb.append(m8765a(entry.getValue()));
        }
        C6396h.m10131k("TraceFileRunnable : 11111getAllStackTrace... :  " + sb.toString());
        return sb.toString();
    }
}
