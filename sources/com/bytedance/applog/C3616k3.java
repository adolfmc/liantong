package com.bytedance.applog;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import com.bytedance.applog.InterfaceC3645n3;

/* renamed from: com.bytedance.applog.k3 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3616k3 implements InterfaceC3645n3 {

    /* renamed from: a */
    public AbstractC3749z2<Boolean> f8540a = new C3617a(this);

    /* renamed from: com.bytedance.applog.k3$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C3617a extends AbstractC3749z2<Boolean> {
        public C3617a(C3616k3 c3616k3) {
        }

        @Override // com.bytedance.applog.AbstractC3749z2
        /* renamed from: a */
        public Boolean mo16989a(Object[] objArr) {
            try {
                PackageManager packageManager = ((Context) objArr[0]).getPackageManager();
                if (packageManager != null) {
                    return Boolean.valueOf(packageManager.resolveContentProvider("com.meizu.flyme.openidsdk", 0) != null);
                }
            } catch (Exception unused) {
            }
            return false;
        }
    }

    @Override // com.bytedance.applog.InterfaceC3645n3
    /* renamed from: a */
    public InterfaceC3645n3.C3646a mo17057a(Context context) {
        Cursor cursor;
        String string;
        try {
            cursor = context.getContentResolver().query(Uri.parse("content://com.meizu.flyme.openidsdk/"), null, null, new String[]{"oaid"}, null);
            if (cursor == null) {
                return null;
            }
            try {
                InterfaceC3645n3.C3646a c3646a = new InterfaceC3645n3.C3646a();
                if (!cursor.isClosed()) {
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex("value");
                    if (columnIndex >= 0) {
                        string = cursor.getString(columnIndex);
                        c3646a.f8617a = string;
                        cursor.close();
                        return c3646a;
                    }
                }
                string = null;
                c3646a.f8617a = string;
                cursor.close();
                return c3646a;
            } catch (Throwable th) {
                th = th;
                try {
                    th.printStackTrace();
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
    }

    @Override // com.bytedance.applog.InterfaceC3645n3
    /* renamed from: b */
    public boolean mo17056b(Context context) {
        if (context == null) {
            return false;
        }
        return this.f8540a.m16988b(context).booleanValue();
    }
}
