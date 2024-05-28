package com.bytedance.applog;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.IBinder;
import android.os.Parcel;
import android.text.TextUtils;
import com.bytedance.applog.C3713v3;
import com.bytedance.applog.InterfaceC3545a4;
import com.bytedance.applog.InterfaceC3645n3;

/* renamed from: com.bytedance.applog.r3 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3681r3 implements InterfaceC3645n3 {

    /* renamed from: a */
    public final InterfaceC3645n3 f8791a;

    /* renamed from: b */
    public AbstractC3749z2<Boolean> f8792b = new C3682a(this);

    /* renamed from: com.bytedance.applog.r3$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C3682a extends AbstractC3749z2<Boolean> {
        public C3682a(C3681r3 c3681r3) {
        }

        @Override // com.bytedance.applog.AbstractC3749z2
        /* renamed from: a */
        public Boolean mo16989a(Object[] objArr) {
            try {
                PackageInfo packageInfo = ((Context) objArr[0]).getPackageManager().getPackageInfo("com.heytap.openid", 0);
                if (packageInfo == null) {
                    return false;
                }
                return Boolean.valueOf((Build.VERSION.SDK_INT >= 28 ? packageInfo.getLongVersionCode() : (long) packageInfo.versionCode) >= 1);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    /* renamed from: com.bytedance.applog.r3$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C3683b implements C3713v3.InterfaceC3715b<InterfaceC3545a4, String> {

        /* renamed from: a */
        public final /* synthetic */ Context f8793a;

        public C3683b(Context context) {
            this.f8793a = context;
        }

        @Override // com.bytedance.applog.C3713v3.InterfaceC3715b
        /* renamed from: a */
        public InterfaceC3545a4 mo16998a(IBinder iBinder) {
            return InterfaceC3545a4.AbstractBinderC3546a.m17334a(iBinder);
        }

        @Override // com.bytedance.applog.C3713v3.InterfaceC3715b
        /* renamed from: a */
        public String mo16997a(InterfaceC3545a4 interfaceC3545a4) {
            InterfaceC3545a4 interfaceC3545a42 = interfaceC3545a4;
            if (interfaceC3545a42 != null) {
                String m17128c = C3681r3.this.m17128c(this.f8793a);
                if (!TextUtils.isEmpty(m17128c)) {
                    String packageName = this.f8793a.getPackageName();
                    InterfaceC3545a4.AbstractBinderC3546a.C3547a c3547a = (InterfaceC3545a4.AbstractBinderC3546a.C3547a) interfaceC3545a42;
                    Parcel obtain = Parcel.obtain();
                    Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.heytap.openid.IOpenID");
                        obtain.writeString(packageName);
                        obtain.writeString(m17128c);
                        obtain.writeString("OUID");
                        c3547a.f8366a.transact(1, obtain, obtain2, 0);
                        obtain2.readException();
                        return obtain2.readString();
                    } finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                }
            }
            return null;
        }
    }

    public C3681r3(InterfaceC3645n3 interfaceC3645n3) {
        this.f8791a = interfaceC3645n3;
    }

    @Override // com.bytedance.applog.InterfaceC3645n3
    /* renamed from: a */
    public InterfaceC3645n3.C3646a mo17057a(Context context) {
        if (this.f8791a == null || this.f8792b.m16988b(new Object[0]).booleanValue()) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.heytap.openid", "com.heytap.openid.IdentifyService"));
            intent.setAction("action.com.heytap.openid.OPEN_ID_SERVICE");
            InterfaceC3645n3.C3646a c3646a = new InterfaceC3645n3.C3646a();
            c3646a.f8617a = (String) new C3713v3(context, intent, new C3683b(context)).m17071a();
            return c3646a;
        }
        return this.f8791a.mo17057a(context);
    }

    @Override // com.bytedance.applog.InterfaceC3645n3
    /* renamed from: b */
    public boolean mo17056b(Context context) {
        if (context == null) {
            return false;
        }
        Boolean m16988b = this.f8792b.m16988b(context);
        return (this.f8791a == null || m16988b.booleanValue()) ? m16988b.booleanValue() : this.f8791a.mo17056b(context);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002d A[Catch: Exception -> 0x0054, TryCatch #0 {Exception -> 0x0054, blocks: (B:14:0x0025, B:16:0x002d, B:18:0x0039, B:19:0x004f), top: B:24:0x0025 }] */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String m17128c(android.content.Context r8) {
        /*
            r7 = this;
            r0 = 0
            android.content.pm.PackageManager r1 = r8.getPackageManager()     // Catch: java.lang.Exception -> L14
            java.lang.String r8 = r8.getPackageName()     // Catch: java.lang.Exception -> L14
            r2 = 64
            android.content.pm.PackageInfo r8 = r1.getPackageInfo(r8, r2)     // Catch: java.lang.Exception -> L14
            if (r8 == 0) goto L18
            android.content.pm.Signature[] r8 = r8.signatures     // Catch: java.lang.Exception -> L14
            goto L19
        L14:
            r8 = move-exception
            r8.printStackTrace()
        L18:
            r8 = r0
        L19:
            if (r8 == 0) goto L58
            int r1 = r8.length
            if (r1 <= 0) goto L58
            r1 = 0
            r8 = r8[r1]
            byte[] r8 = r8.toByteArray()
            java.lang.String r2 = "SHA1"
            java.security.MessageDigest r2 = java.security.MessageDigest.getInstance(r2)     // Catch: java.lang.Exception -> L54
            if (r2 == 0) goto L58
            byte[] r8 = r2.digest(r8)     // Catch: java.lang.Exception -> L54
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L54
            r2.<init>()     // Catch: java.lang.Exception -> L54
            int r3 = r8.length     // Catch: java.lang.Exception -> L54
        L37:
            if (r1 >= r3) goto L4f
            r4 = r8[r1]     // Catch: java.lang.Exception -> L54
            r4 = r4 & 255(0xff, float:3.57E-43)
            r4 = r4 | 256(0x100, float:3.59E-43)
            java.lang.String r4 = java.lang.Integer.toHexString(r4)     // Catch: java.lang.Exception -> L54
            r5 = 3
            r6 = 1
            java.lang.String r4 = r4.substring(r6, r5)     // Catch: java.lang.Exception -> L54
            r2.append(r4)     // Catch: java.lang.Exception -> L54
            int r1 = r1 + 1
            goto L37
        L4f:
            java.lang.String r8 = r2.toString()     // Catch: java.lang.Exception -> L54
            return r8
        L54:
            r8 = move-exception
            r8.printStackTrace()
        L58:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3681r3.m17128c(android.content.Context):java.lang.String");
    }
}
