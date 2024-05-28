package cn.finalteam.galleryfinal.utils;

import cn.finalteam.toolsfinal.logger.LoggerFactory;
import cn.finalteam.toolsfinal.logger.LoggerPrinter;
import cn.finalteam.toolsfinal.logger.Printer;
import cn.finalteam.toolsfinal.logger.Settings;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class ILogger {
    private static final boolean DEBUG = false;
    public static final String DEFAULT_TAG = "GalleryFinal";
    private static final LoggerPrinter printer = LoggerFactory.getFactory("GalleryFinal", false);

    private ILogger() {
    }

    public static void clear() {
        printer.clear();
    }

    public static Settings getSettings() {
        return printer.getSettings();
    }

    /* renamed from: t */
    public static Printer m22033t(String str) {
        LoggerPrinter loggerPrinter = printer;
        return loggerPrinter.mo22019t(str, loggerPrinter.getSettings().getMethodCount());
    }

    /* renamed from: t */
    public static Printer m22034t(int i) {
        return printer.mo22019t(null, i);
    }

    /* renamed from: t */
    public static Printer m22032t(String str, int i) {
        return printer.mo22019t(str, i);
    }

    /* renamed from: d */
    public static void m22039d(String str, Object... objArr) {
        printer.mo22024d(str, objArr);
    }

    /* renamed from: e */
    public static void m22037e(Throwable th) {
        printer.mo22022e(th);
    }

    /* renamed from: e */
    public static void m22038e(String str, Object... objArr) {
        printer.mo22021e(null, str, objArr);
    }

    /* renamed from: e */
    public static void m22036e(Throwable th, String str, Object... objArr) {
        printer.mo22021e(th, str, objArr);
    }

    /* renamed from: i */
    public static void m22035i(String str, Object... objArr) {
        printer.mo22020i(str, objArr);
    }

    /* renamed from: v */
    public static void m22031v(String str, Object... objArr) {
        printer.mo22018v(str, objArr);
    }

    /* renamed from: w */
    public static void m22030w(String str, Object... objArr) {
        printer.mo22017w(str, objArr);
    }

    public static void wtf(String str, Object... objArr) {
        printer.wtf(str, objArr);
    }

    public static void json(String str) {
        printer.json(str);
    }

    public static void xml(String str) {
        printer.xml(str);
    }
}
