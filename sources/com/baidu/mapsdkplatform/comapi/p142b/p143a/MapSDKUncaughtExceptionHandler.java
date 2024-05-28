package com.baidu.mapsdkplatform.comapi.p142b.p143a;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.b.a.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MapSDKUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    /* renamed from: a */
    private static int f7122a = 10240;

    /* renamed from: c */
    private static volatile boolean f7123c;

    /* renamed from: b */
    private String f7124b;

    /* renamed from: d */
    private Thread.UncaughtExceptionHandler f7125d;

    private MapSDKUncaughtExceptionHandler() {
        this.f7124b = "";
        this.f7125d = Thread.getDefaultUncaughtExceptionHandler();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: MapSDKUncaughtExceptionHandler.java */
    /* renamed from: com.baidu.mapsdkplatform.comapi.b.a.a$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class C2884a {

        /* renamed from: a */
        private static final MapSDKUncaughtExceptionHandler f7126a = new MapSDKUncaughtExceptionHandler();
    }

    /* renamed from: a */
    public static MapSDKUncaughtExceptionHandler m18500a() {
        return C2884a.f7126a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m18499a(String str) {
        this.f7124b = str;
        if (Thread.getDefaultUncaughtExceptionHandler() instanceof MapSDKUncaughtExceptionHandler) {
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /* renamed from: a */
    private void m18498a(Throwable th) {
        if (th == null) {
            return;
        }
        try {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            Throwable cause = th.getCause();
            if (cause != null) {
                cause.printStackTrace(printWriter);
            }
            printWriter.close();
            String obj = stringWriter.toString();
            if (obj.isEmpty()) {
                return;
            }
            if (obj.length() > f7122a) {
                obj = obj.substring(0, f7122a);
            }
            if (obj.contains("BDMapSDKException")) {
                return;
            }
            if ((obj.contains("com.baidu.platform") || obj.contains("com.baidu.mapsdkplatform")) && this.f7124b != null && !this.f7124b.isEmpty()) {
                NativeCrashUtil.m18496a().m18490a(this.f7124b + (System.currentTimeMillis() / 1000) + ".txt", obj);
            }
        } catch (Exception unused) {
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        if (f7123c) {
            return;
        }
        f7123c = true;
        m18498a(th);
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f7125d;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }
}
