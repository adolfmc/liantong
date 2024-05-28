package com.vivo.push.p369c;

import android.content.Context;
import android.text.TextUtils;
import com.vivo.push.util.LogUtil;
import java.util.HashMap;

/* renamed from: com.vivo.push.c.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class CoreConfigManager {

    /* renamed from: a */
    private Context f20926a;

    /* renamed from: b */
    private HashMap<String, String> f20927b = new HashMap<>();

    public CoreConfigManager(Context context) {
        this.f20926a = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00da A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v25 */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.content.ContentProviderClient, android.database.Cursor] */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int m5753e() {
        /*
            Method dump skipped, instructions count: 242
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.p369c.CoreConfigManager.m5753e():int");
    }

    /* renamed from: a */
    public final int m5759a() {
        int m5758a = m5758a(1);
        LogUtil.m5341d("CoreConfigManager", "isSupportNewControlStrategies : ".concat(String.valueOf(m5758a)));
        return m5758a;
    }

    /* renamed from: b */
    public final int m5756b() {
        int m5758a = m5758a(4);
        LogUtil.m5341d("CoreConfigManager", "isSupportSyncProfileInfo : ".concat(String.valueOf(m5758a)));
        return m5758a;
    }

    /* renamed from: c */
    public final boolean m5755c() {
        int m5758a = m5758a(8);
        LogUtil.m5341d("CoreConfigManager", "isSupportdeleteRegid : ".concat(String.valueOf(m5758a)));
        return m5758a == 0;
    }

    /* renamed from: d */
    public final boolean m5754d() {
        int m5758a = m5758a(16);
        LogUtil.m5341d("CoreConfigManager", "isSupportQueryCurrentAppState : ".concat(String.valueOf(m5758a)));
        return m5758a == 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x00be A[Catch: Exception -> 0x0093, TRY_ENTER, TryCatch #8 {Exception -> 0x0093, blocks: (B:41:0x008f, B:45:0x0097, B:47:0x009b, B:60:0x00be, B:62:0x00c3, B:64:0x00c7), top: B:86:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00c3 A[Catch: Exception -> 0x0093, TryCatch #8 {Exception -> 0x0093, blocks: (B:41:0x008f, B:45:0x0097, B:47:0x009b, B:60:0x00be, B:62:0x00c3, B:64:0x00c7), top: B:86:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00d7 A[Catch: Exception -> 0x00d3, TryCatch #7 {Exception -> 0x00d3, blocks: (B:69:0x00cf, B:73:0x00d7, B:75:0x00db), top: B:84:0x00cf }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00cf A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m5757a(android.content.Context r12, java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 231
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.p369c.CoreConfigManager.m5757a(android.content.Context, java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    private int m5758a(int i) {
        int m5753e = m5753e();
        if (m5753e != 0) {
            return m5753e;
        }
        HashMap<String, String> hashMap = this.f20927b;
        if (hashMap == null || hashMap.size() == 0) {
            return 8006;
        }
        String str = this.f20927b.get("pushSupport");
        if (TextUtils.isEmpty(str)) {
            return 2;
        }
        try {
            return (i & Integer.parseInt(str)) > 0 ? 0 : 1;
        } catch (Exception unused) {
            return 8007;
        }
    }
}
