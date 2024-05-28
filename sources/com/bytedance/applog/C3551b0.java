package com.bytedance.applog;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.b0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3551b0 extends AbstractC3692t {

    /* renamed from: e */
    public final Context f8368e;

    public C3551b0(Context context) {
        super(true, false);
        this.f8368e = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r1v11, types: [int[]] */
    /* JADX WARN: Type inference failed for: r1v12, types: [int] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r4v0, types: [int] */
    @Override // com.bytedance.applog.AbstractC3692t
    /* renamed from: a */
    public boolean mo16995a(JSONObject jSONObject) {
        String str;
        Class<Display> cls;
        int i;
        int i2;
        int i3 = this.f8368e.getResources().getDisplayMetrics().densityDpi;
        switch (i3) {
            case 120:
                str = "ldpi";
                break;
            case 240:
                str = "hdpi";
                break;
            case 260:
            case 280:
            case 300:
            case 320:
                str = "xhdpi";
                break;
            case 340:
            case 360:
            case 400:
            case 420:
            case 440:
            case 480:
                str = "xxhdpi";
                break;
            case 560:
            case 640:
                str = "xxxhdpi";
                break;
            default:
                str = "mdpi";
                break;
        }
        jSONObject.put("density_dpi", i3);
        jSONObject.put("display_density", str);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = ((WindowManager) this.f8368e.getSystemService("window")).getDefaultDisplay();
        try {
            if (Build.VERSION.SDK_INT >= 17) {
                defaultDisplay.getRealMetrics(displayMetrics);
                int i4 = displayMetrics.widthPixels;
                try {
                    i = i4;
                    i2 = displayMetrics.heightPixels;
                } catch (NoSuchMethodException e) {
                    e = e;
                    cls = i4;
                    e.printStackTrace();
                    i = cls;
                    i2 = 0;
                    ?? r1 = {i, i2};
                    jSONObject.put("resolution", r1[1] + "x" + r1[0]);
                    return true;
                }
            } else {
                Method method = Display.class.getMethod("getRawHeight", new Class[0]);
                cls = Display.class;
                try {
                    try {
                        i = ((Integer) cls.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                    } catch (IllegalAccessException e2) {
                        e = e2;
                        i = 0;
                    } catch (IllegalArgumentException e3) {
                        e = e3;
                        i = 0;
                    } catch (InvocationTargetException e4) {
                        e = e4;
                        i = 0;
                    }
                    try {
                        i2 = ((Integer) method.invoke(defaultDisplay, new Object[0])).intValue();
                    } catch (IllegalAccessException e5) {
                        e = e5;
                        e.printStackTrace();
                        i = i;
                        i2 = 0;
                        ?? r12 = {i, i2};
                        jSONObject.put("resolution", r12[1] + "x" + r12[0]);
                        return true;
                    } catch (IllegalArgumentException e6) {
                        e = e6;
                        e.printStackTrace();
                        i = i;
                        i2 = 0;
                        ?? r122 = {i, i2};
                        jSONObject.put("resolution", r122[1] + "x" + r122[0]);
                        return true;
                    } catch (InvocationTargetException e7) {
                        e = e7;
                        e.printStackTrace();
                        i = i;
                        i2 = 0;
                        ?? r1222 = {i, i2};
                        jSONObject.put("resolution", r1222[1] + "x" + r1222[0]);
                        return true;
                    }
                } catch (NoSuchMethodException e8) {
                    e = e8;
                    e.printStackTrace();
                    i = cls;
                    i2 = 0;
                    ?? r12222 = {i, i2};
                    jSONObject.put("resolution", r12222[1] + "x" + r12222[0]);
                    return true;
                }
            }
        } catch (NoSuchMethodException e9) {
            e = e9;
            cls = null;
        }
        ?? r122222 = {i, i2};
        jSONObject.put("resolution", r122222[1] + "x" + r122222[0]);
        return true;
    }
}
