package com.unicom.pay.auto;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.unicom.pay.C10546a;
import com.unicom.pay.common.bean.EnvConfig;
import p090c.C1497g;
import p388f.C11925b;
import p388f.EnumC11924a;
import p390g.C11944a;
import p390g.C11945b;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class InitProvider extends ContentProvider {
    @Override // android.content.ContentProvider
    public final int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public final String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public final Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public final boolean onCreate() {
        C1497g m22187c = C1497g.m22187c();
        boolean z = C10546a.C10576i.f20125a.f20052b != EnvConfig.PRO;
        m22187c.getClass();
        C11944a.f24299a = z;
        C1497g m22190a = m22187c.m22190a((Application) getContext().getApplicationContext());
        m22190a.f2526o = true;
        m22190a.f2524m = false;
        C11925b c11925b = m22190a.f2514c;
        c11925b.f24295c = false;
        c11925b.f24296d = false;
        c11925b.f24297e = (EnumC11924a) C11945b.m2025a(EnumC11924a.MM, "The supportSubunits can not be null, use Subunits.NONE instead");
        return true;
    }

    @Override // android.content.ContentProvider
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
