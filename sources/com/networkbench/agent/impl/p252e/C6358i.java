package com.networkbench.agent.impl.p252e;

import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import com.networkbench.agent.impl.util.C6653u;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.i */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6358i {

    /* renamed from: a */
    private static final String f16002a = "mWindowManager";

    /* renamed from: b */
    private static final String f16003b = "mGlobal";

    /* renamed from: c */
    private static final String f16004c = "mRoots";

    /* renamed from: d */
    private static final String f16005d = "mParams";

    /* renamed from: e */
    private static final String f16006e = "mView";

    private C6358i() {
    }

    /* renamed from: a */
    public static List<C6374u> m10301a() {
        Object m10300a;
        Object[] objArr;
        WindowManager.LayoutParams[] layoutParamsArr;
        ArrayList arrayList = new ArrayList();
        Object systemService = C6653u.m8714d().getSystemService("window");
        if (Build.VERSION.SDK_INT >= 17) {
            m10300a = m10300a("mGlobal", systemService);
        } else {
            m10300a = m10300a("mWindowManager", systemService);
        }
        Object m10300a2 = m10300a("mRoots", m10300a);
        Object m10300a3 = m10300a("mParams", m10300a);
        if (Build.VERSION.SDK_INT >= 19) {
            objArr = ((List) m10300a2).toArray();
            List list = (List) m10300a3;
            layoutParamsArr = (WindowManager.LayoutParams[]) list.toArray(new WindowManager.LayoutParams[list.size()]);
        } else {
            objArr = (Object[]) m10300a2;
            layoutParamsArr = (WindowManager.LayoutParams[]) m10300a3;
        }
        for (int i = 0; i < objArr.length; i++) {
            View view = (View) m10300a("mView", objArr[i]);
            if (view.getVisibility() == 0) {
                arrayList.add(new C6374u(view, layoutParamsArr[i]));
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private static Object m10300a(String str, Object obj) {
        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
