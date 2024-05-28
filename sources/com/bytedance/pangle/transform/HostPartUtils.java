package com.bytedance.pangle.transform;

import android.support.annotation.Keep;
import android.support.p083v4.app.FragmentActivity;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Keep
/* loaded from: E:\10762272_dexfile_execute.dex */
public class HostPartUtils {
    private Class fragmentActivityClazz;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.pangle.transform.HostPartUtils$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class C3937a {

        /* renamed from: a */
        private static final HostPartUtils f9365a = new HostPartUtils();
    }

    public HostPartUtils() {
        try {
            this.fragmentActivityClazz = FragmentActivity.class;
        } catch (Throwable unused) {
        }
    }

    public static final Class getFragmentActivityClass() {
        return C3937a.f9365a.fragmentActivityClazz;
    }

    public static FragmentActivity getFragmentActivity(Object obj, String str) {
        return (FragmentActivity) ZeusTransformUtils._getActivity(obj, str);
    }
}
