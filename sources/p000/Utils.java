package p000;

import android.annotation.SuppressLint;
import android.app.Application;
import java.lang.reflect.InvocationTargetException;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* renamed from: b0  reason: case insensitive filesystem */
/* loaded from: E:\567196_dexfile_execute.dex */
public class Utils {
    @SuppressLint({"StaticFieldLeak"})

    /* renamed from: a */
    public static Application f2503a;

    /* renamed from: a */
    public static Application m22196a() {
        Application application = f2503a;
        if (application != null) {
            return application;
        }
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Object invoke = cls.getMethod("getApplication", new Class[0]).invoke(cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]), new Object[0]);
            if (invoke != null) {
                Application application2 = (Application) invoke;
                f2503a = application2;
                return application2;
            }
            throw new NullPointerException("u should init first");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            throw new NullPointerException("u should init first");
        }
    }
}
