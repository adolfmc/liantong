package szcom.googlecode.mp4parser.util;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CastUtils {
    public static int l2i(long j) {
        if (j > 2147483647L || j < -2147483648L) {
            throw new RuntimeException("A cast to int has gone wrong. Please contact the mp4parser discussion group (" + j + ")");
        }
        return (int) j;
    }
}
