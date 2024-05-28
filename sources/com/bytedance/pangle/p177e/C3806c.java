package com.bytedance.pangle.p177e;

import android.support.annotation.NonNull;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.p176d.C3792c;
import com.bytedance.pangle.p177e.C3811f;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;

/* renamed from: com.bytedance.pangle.e.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3806c implements C3811f.InterfaceC3814a {
    @Override // com.bytedance.pangle.p177e.C3811f.InterfaceC3814a
    /* renamed from: a */
    public final boolean mo16892a(String str, int i) {
        String str2;
        String m16887a = C3815g.m16887a(str, i);
        String m16932c = C3792c.m16932c(str, i);
        String[] split = m16887a.split(":");
        ZeusLogger.m16792i("Zeus/install_pangle", "full DexOpt start:".concat(String.valueOf(m16887a)));
        long currentTimeMillis = System.currentTimeMillis();
        int length = split.length;
        boolean z = false;
        int i2 = 0;
        boolean z2 = false;
        while (true) {
            if (i2 >= length) {
                z = z2;
                break;
            }
            if (!m16899a(split[i2], m16932c + File.separator + C3805b.m16905a(str2))) {
                break;
            }
            ZeusLogger.m16792i("Zeus/install_pangle", "full DexOpt result:true");
            i2++;
            z2 = true;
        }
        ZeusLogger.m16794d("Zeus/load_pangle", "compile cost:" + (System.currentTimeMillis() - currentTimeMillis) + " result:" + z);
        return z;
    }

    /* renamed from: a */
    private static boolean m16899a(@NonNull String str, @NonNull String str2) {
        try {
            DexFile.loadDex(str, str2, 0);
            return true;
        } catch (IOException unused) {
            return false;
        }
    }
}
