package p470p0;

import cn.microdone.txcrypto.C1731txcrypto;

/* renamed from: p0.e */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C13640e {

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: p0.e$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class C13641a {

        /* renamed from: a */
        public static C13640e f27486a = new C13640e();
    }

    /* renamed from: a */
    public final String m184a(String str, String str2) {
        if (str.length() != 130) {
            return "";
        }
        String substring = str.substring(2);
        return new C1731txcrypto().microdoneSM2Encrypt(str2, substring.substring(0, 64), substring.substring(64), 0, 1, 0, 0);
    }
}
