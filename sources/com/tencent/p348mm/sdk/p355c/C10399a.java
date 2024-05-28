package com.tencent.p348mm.sdk.p355c;

import android.net.Uri;
import android.provider.BaseColumns;
import com.tencent.p348mm.sdk.p354b.C10393b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.c.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C10399a {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.sdk.c.a$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class C10400a {
        /* renamed from: a */
        public static Object m6183a(int i, String str) {
            try {
                switch (i) {
                    case 1:
                        return Integer.valueOf(str);
                    case 2:
                        return Long.valueOf(str);
                    case 3:
                        return str;
                    case 4:
                        return Boolean.valueOf(str);
                    case 5:
                        return Float.valueOf(str);
                    case 6:
                        return Double.valueOf(str);
                    default:
                        C10393b.m6195a("MicroMsg.SDK.PluginProvider.Resolver", "unknown type");
                        return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.sdk.c.a$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class C10401b implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://com.tencent.mm.sdk.plugin.provider/sharedpref");
    }
}
