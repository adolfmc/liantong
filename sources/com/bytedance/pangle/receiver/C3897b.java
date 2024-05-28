package com.bytedance.pangle.receiver;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.bytedance.pangle.util.FieldUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.receiver.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3897b {

    /* renamed from: a */
    private static final InterfaceC3900c f9283a;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.pangle.receiver.b$c */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    interface InterfaceC3900c {
        /* renamed from: a */
        boolean mo16736a(Context context);
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i < 24) {
            f9283a = new C3898a((byte) 0);
        } else if (i < 26) {
            f9283a = new C3901d((byte) 0);
        } else if (i < 28) {
            f9283a = new C3902e((byte) 0);
        } else {
            f9283a = new C3899b((byte) 0);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.pangle.receiver.b$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class C3899b extends C3902e {
        @Override // com.bytedance.pangle.receiver.C3897b.C3902e, com.bytedance.pangle.receiver.C3897b.C3898a, com.bytedance.pangle.receiver.C3897b.InterfaceC3900c
        /* renamed from: a */
        public final boolean mo16736a(Context context) {
            return false;
        }

        private C3899b() {
            super((byte) 0);
        }

        /* synthetic */ C3899b(byte b) {
            this();
        }
    }

    /* renamed from: com.bytedance.pangle.receiver.b$e */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class C3902e extends C3898a {
        private C3902e() {
            super((byte) 0);
        }

        /* synthetic */ C3902e(byte b) {
            this();
        }

        @Override // com.bytedance.pangle.receiver.C3897b.C3898a, com.bytedance.pangle.receiver.C3897b.InterfaceC3900c
        /* renamed from: a */
        public boolean mo16736a(Context context) {
            Object a = m16739a(context, "mWhiteListMap");
            if (a instanceof Map) {
                Map map = (Map) a;
                List list = (List) map.get(0);
                if (list == null) {
                    list = new ArrayList();
                    map.put(0, list);
                }
                list.add(context.getPackageName());
                return true;
            }
            return false;
        }
    }

    /* renamed from: com.bytedance.pangle.receiver.b$d */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class C3901d extends C3898a {
        private C3901d() {
            super((byte) 0);
        }

        /* synthetic */ C3901d(byte b) {
            this();
        }

        @Override // com.bytedance.pangle.receiver.C3897b.C3898a, com.bytedance.pangle.receiver.C3897b.InterfaceC3900c
        /* renamed from: a */
        public final boolean mo16736a(Context context) {
            Object a = m16739a(context, "mWhiteList");
            if (a instanceof List) {
                ((List) a).add(context.getPackageName());
                return true;
            }
            return false;
        }
    }

    /* renamed from: com.bytedance.pangle.receiver.b$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class C3898a implements InterfaceC3900c {
        private C3898a() {
        }

        /* synthetic */ C3898a(byte b) {
            this();
        }

        @Override // com.bytedance.pangle.receiver.C3897b.InterfaceC3900c
        /* renamed from: a */
        public boolean mo16736a(Context context) {
            Object m16737b = m16737b(context);
            Object m16738a = m16738a(m16737b, "mWhiteList");
            if (!(m16738a instanceof String[])) {
                if (m16737b != null) {
                    FieldUtils.writeField(m16737b, "mResourceConfig", (Object) null);
                    return false;
                }
                return false;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(context.getPackageName());
            Collections.addAll(arrayList, (String[]) m16738a);
            FieldUtils.writeField(m16737b, "mWhiteList", arrayList.toArray(new String[arrayList.size()]));
            return true;
        }

        /* renamed from: a */
        static Object m16739a(Context context, String str) {
            return m16738a(m16737b(context), str);
        }

        /* renamed from: a */
        private static Object m16738a(Object obj, String str) {
            if (obj != null) {
                try {
                    return FieldUtils.readField(obj, str);
                } catch (Throwable unused) {
                    return null;
                }
            }
            return null;
        }

        /* renamed from: b */
        private static Object m16737b(Context context) {
            Field field;
            Object readField;
            try {
                Field field2 = FieldUtils.getField(Class.forName("android.app.LoadedApk"), "mReceiverResource");
                if (field2 == null || (field = FieldUtils.getField(Class.forName("android.app.ContextImpl"), "mPackageInfo")) == null || (readField = FieldUtils.readField(field, context)) == null) {
                    return null;
                }
                return FieldUtils.readField(field2, readField);
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    /* renamed from: a */
    public static void m16740a(Application application) {
        if (application != null) {
            try {
                if (TextUtils.equals(Build.BRAND.toLowerCase(), "huawei")) {
                    f9283a.mo16736a(application.getBaseContext());
                }
            } catch (Throwable unused) {
            }
        }
    }
}
