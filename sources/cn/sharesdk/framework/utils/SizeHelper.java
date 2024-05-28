package cn.sharesdk.framework.utils;

import android.content.Context;
import com.mob.tools.utils.ResHelper;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.utils.i */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SizeHelper {

    /* renamed from: a */
    public static float f2973a = 1.5f;

    /* renamed from: b */
    public static int f2974b = 540;

    /* renamed from: c */
    private static Context f2975c;

    /* renamed from: a */
    public static void m21680a(Context context) {
        Context context2 = f2975c;
        if (context2 == null || context2 != context.getApplicationContext()) {
            f2975c = context;
        }
    }

    /* renamed from: a */
    public static int m21681a(int i) {
        return ResHelper.designToDevice(f2975c, f2973a, i);
    }

    /* renamed from: b */
    public static int m21679b(int i) {
        return ResHelper.designToDevice(f2975c, f2974b, i);
    }
}
