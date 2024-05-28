package android.arch.lifecycle;

import android.support.annotation.RestrictTo;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MethodCallsLogger {
    private Map<String, Integer> mCalledMethods = new HashMap();

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean approveCall(String str, int i) {
        Integer num = this.mCalledMethods.get(str);
        int intValue = num != null ? num.intValue() : 0;
        boolean z = (intValue & i) != 0;
        this.mCalledMethods.put(str, Integer.valueOf(i | intValue));
        return !z;
    }
}
