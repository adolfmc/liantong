package com.mob.commons.dialog;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\10762272_dexfile_execute.dex */
public class PolicyThrowable extends Throwable {
    private int code;

    public PolicyThrowable() {
        super("Privacy policy is not accepted");
    }

    public PolicyThrowable(String str) {
        super(str);
    }

    public PolicyThrowable(String str, Throwable th) {
        super(str, th);
    }

    public PolicyThrowable(int i, String str) {
        this(str);
        this.code = i;
    }

    public PolicyThrowable(int i, String str, Throwable th) {
        this(str, th);
        this.code = i;
    }

    public PolicyThrowable(Throwable th) {
        super(th);
    }

    public int getCode() {
        return this.code;
    }
}
