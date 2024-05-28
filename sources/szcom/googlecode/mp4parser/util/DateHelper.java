package szcom.googlecode.mp4parser.util;

import java.util.Date;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DateHelper {
    public static long convert(Date date) {
        return (date.getTime() / 1000) + 2082844800;
    }

    public static Date convert(long j) {
        return new Date((j - 2082844800) * 1000);
    }
}
