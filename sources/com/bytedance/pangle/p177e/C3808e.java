package com.bytedance.pangle.p177e;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ResultReceiver;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.p176d.C3792c;
import com.bytedance.pangle.p177e.C3811f;
import com.bytedance.pangle.util.C3947g;
import com.bytedance.pangle.util.C3948h;
import com.bytedance.pangle.util.FieldUtils;
import com.bytedance.pangle.util.MethodUtils;
import java.io.File;
import java.io.FileDescriptor;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* renamed from: com.bytedance.pangle.e.e */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3808e implements C3811f.InterfaceC3814a {

    /* renamed from: a */
    private static volatile IBinder f9105a;

    /* renamed from: b */
    private static volatile Object f9106b;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.pangle.e.e$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC3809a {
    }

    /* renamed from: a */
    private static Object m16897a(Object obj, String str, Object[] objArr, Class<?>[] clsArr) {
        try {
            return MethodUtils.invokeMethod(obj, str, objArr, clsArr);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static Object m16896a(Field field, Object obj) {
        try {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            Object obj2 = field.get(obj);
            field.setAccessible(false);
            return obj2;
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static void m16895a(String[] strArr) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        obtain.writeFileDescriptor(FileDescriptor.in);
        obtain.writeFileDescriptor(FileDescriptor.out);
        obtain.writeFileDescriptor(FileDescriptor.err);
        obtain.writeStringArray(strArr);
        obtain.writeStrongBinder(null);
        new ResultReceiverC3810b().writeToParcel(obtain, 0);
        try {
            f9105a.transact(1598246212, obtain, obtain2, 0);
            obtain2.readException();
        } catch (Exception unused) {
        } catch (Throwable th) {
            obtain.recycle();
            obtain2.recycle();
            throw th;
        }
        obtain.recycle();
        obtain2.recycle();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:817)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:406)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:204)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:138)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:406)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:204)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:138)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0030 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String e1672829046107dc(java.lang.String r4) {
        /*
        L0:
            r0 = 73
            r1 = 96
        L4:
            r2 = 0
            switch(r0) {
                case 72: goto L4f;
                case 73: goto L9;
                case 74: goto Lc;
                default: goto L8;
            }
        L8:
            goto L54
        L9:
            switch(r1) {
                case 94: goto L0;
                case 95: goto L10;
                case 96: goto L4f;
                default: goto Lc;
            }
        Lc:
            switch(r1) {
                case 55: goto L39;
                case 56: goto L4f;
                case 57: goto L10;
                default: goto Lf;
            }
        Lf:
            goto L0
        L10:
            r0 = 18
            r1 = 1
            switch(r1) {
                case 60: goto L17;
                case 61: goto L23;
                case 62: goto L30;
                default: goto L16;
            }
        L16:
            goto L4f
        L17:
            int r3 = 0 - r1
            int r3 = r3 * r2
            r2 = 0
            int r2 = r2 * 2
            int r2 = r2 - r1
            int r3 = r3 * r2
            int r3 = r3 % 6
            if (r3 == 0) goto L0
        L23:
            int r2 = 18 - r1
            int r2 = r2 * r0
            r3 = 18
            int r3 = r3 * 2
            int r3 = r3 - r1
            int r2 = r2 * r3
            int r2 = r2 % 6
            if (r2 == 0) goto L4f
        L30:
            r1 = 99
            int r1 = r1 * r1
            int r0 = r0 * r0
            int r0 = r0 * 34
            int r1 = r1 - r0
            r0 = -1
            goto L0
        L39:
            char[] r4 = r4.toCharArray()
        L3d:
            int r0 = r4.length
            if (r2 >= r0) goto L49
            char r0 = r4[r2]
            r0 = r0 ^ r2
            char r0 = (char) r0
            r4[r2] = r0
            int r2 = r2 + 1
            goto L3d
        L49:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r4)
            return r0
        L4f:
            r0 = 74
            r1 = 55
            goto L4
        L54:
            r0 = 72
            goto L4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.p177e.C3808e.e1672829046107dc(java.lang.String):java.lang.String");
    }

    @Override // com.bytedance.pangle.p177e.C3811f.InterfaceC3814a
    /* renamed from: a */
    public final boolean mo16892a(String str, int i) {
        boolean z;
        IBinder asBinder;
        if (f9105a == null) {
            PackageManager packageManager = Zeus.getAppApplication().getPackageManager();
            Field field = packageManager == null ? null : FieldUtils.getField(packageManager.getClass(), "mPM");
            if (field != null) {
                Object m16896a = m16896a(field, packageManager);
                f9106b = m16896a;
                if ((m16896a instanceof IInterface) && (asBinder = ((IInterface) f9106b).asBinder()) != null) {
                    f9105a = asBinder;
                }
            }
        }
        if (str != null) {
            String m16934b = C3792c.m16934b(str, i);
            String m16929e = C3792c.m16929e(str, i);
            try {
                C3948h.m16627a(m16934b, m16929e);
            } catch (Exception unused) {
            }
            String packageName = Zeus.getAppApplication().getPackageName();
            String m16907a = C3805b.m16907a();
            if (Build.VERSION.SDK_INT == 30) {
                if (f9106b != null && packageName != null && m16929e != null && m16907a != null) {
                    m16897a(f9106b, "notifyDexLoad", new Object[]{packageName, Collections.singletonMap(m16929e, "PCL[]"), m16907a}, new Class[]{String.class, Map.class, String.class});
                }
            } else if (Build.VERSION.SDK_INT == 29 && f9106b != null && packageName != null && m16929e != null && m16907a != null) {
                m16897a(f9106b, "notifyDexLoad", new Object[]{packageName, Collections.singletonList("dalvik.system.DexClassLoader"), Collections.singletonList(m16929e), m16907a}, new Class[]{String.class, List.class, List.class, String.class});
            }
        }
        String m16929e2 = C3792c.m16929e(str, i);
        String str2 = C3792c.m16926h(str, i) + File.separator + C3805b.m16905a(m16929e2);
        int i2 = 1;
        while (true) {
            if (i2 > 3) {
                z = false;
                break;
            }
            m16895a(new String[]{"compile", "-m", "speed", "-f", "--secondary-dex", Zeus.getAppApplication().getPackageName()});
            if (C3805b.m16901a(str2)) {
                z = true;
                break;
            }
            i2++;
        }
        if (str != null) {
            try {
                C3947g.m16631a(C3792c.m16927g(str, i), C3792c.m16928f(str, i));
            } catch (Exception unused2) {
            }
            try {
                File file = new File(C3792c.m16929e(str, i));
                if (file.exists()) {
                    file.delete();
                }
            } catch (Exception unused3) {
            }
            m16895a(new String[]{"reconcile-secondary-dex-files", Zeus.getAppApplication().getPackageName()});
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.pangle.e.e$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class ResultReceiverC3810b extends ResultReceiver {

        /* renamed from: a */
        private InterfaceC3809a f9107a;

        public ResultReceiverC3810b() {
            super(null);
            this.f9107a = null;
        }

        @Override // android.os.ResultReceiver
        protected final void onReceiveResult(int i, Bundle bundle) {
            super.onReceiveResult(i, bundle);
        }
    }
}
