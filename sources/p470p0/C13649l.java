package p470p0;

import android.text.TextUtils;
import com.unicom.pay.utils.jwt.C10704c;
import java.util.Date;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: p0.l */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class C13649l {
    /* renamed from: a */
    public static boolean m176a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return true;
            }
            C10704c c10704c = new C10704c(str);
            Date date = new Date(((long) (Math.floor(new Date().getTime() / 1000) * 1000.0d)) + 60000);
            Date date2 = c10704c.f20446b.f20447a;
            if (date2 != null) {
                return date.after(date2);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}
