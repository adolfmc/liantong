package p470p0;

import android.text.TextUtils;
import java.text.DecimalFormat;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: p0.i */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class C13646i {
    /* renamed from: a */
    public static String m182a(long j) {
        return new DecimalFormat("#0.00").format(Double.valueOf(Math.abs(j / 1000.0d)));
    }

    /* renamed from: a */
    public static String m181a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "0.00";
        }
        return new DecimalFormat("#0.00").format(Double.valueOf(Double.parseDouble(str) * 0.01d));
    }
}
