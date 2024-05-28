package com.bytedance.applog;

import android.content.ContentProviderClient;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.applog.InterfaceC3645n3;

/* renamed from: com.bytedance.applog.l3 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3623l3 implements InterfaceC3645n3 {
    @Override // com.bytedance.applog.InterfaceC3645n3
    /* renamed from: a */
    public InterfaceC3645n3.C3646a mo17057a(Context context) {
        Bundle call;
        Uri parse = Uri.parse("content://cn.nubia.identity/identity");
        try {
            if (Build.VERSION.SDK_INT > 17) {
                ContentProviderClient acquireContentProviderClient = context.getContentResolver().acquireContentProviderClient(parse);
                if (acquireContentProviderClient == null) {
                    return null;
                }
                call = acquireContentProviderClient.call("getOAID", null, null);
                if (Build.VERSION.SDK_INT >= 24) {
                    acquireContentProviderClient.close();
                } else {
                    acquireContentProviderClient.release();
                }
            } else {
                call = context.getContentResolver().call(parse, "getOAID", (String) null, (Bundle) null);
            }
            if (call == null) {
                return null;
            }
            if (call.getInt("code", -1) == 0) {
                InterfaceC3645n3.C3646a c3646a = new InterfaceC3645n3.C3646a();
                c3646a.f8617a = call.getString("id");
                return c3646a;
            }
            String string = call.getString("message");
            if (!TextUtils.isEmpty(string)) {
                C3720w2.m17061a(string);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.bytedance.applog.InterfaceC3645n3
    /* renamed from: b */
    public boolean mo17056b(Context context) {
        return Build.VERSION.SDK_INT > 28;
    }
}
