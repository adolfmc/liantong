package p470p0;

import android.text.TextUtils;
import com.gmrz.appsdk.util.Constant;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: p0.h */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class C13644h {

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: p0.h$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class C13645a {

        /* renamed from: a */
        public static final C13644h f27491a = new C13644h();
    }

    /* renamed from: a */
    public static boolean m183a(C13644h c13644h, Map.Entry entry) {
        c13644h.getClass();
        for (Map.Entry entry2 : ((Map) entry.getValue()).entrySet()) {
            Constant.SecurityLevel securityLevel = (Constant.SecurityLevel) entry2.getKey();
            if (TextUtils.equals("Support", ((Constant.SecurityLevelInfo) entry2.getValue()).name())) {
                return true;
            }
        }
        return false;
    }
}
