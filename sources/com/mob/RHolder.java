package com.mob;

import com.mob.tools.proguard.PublicMemberKeeper;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\10762272_dexfile_execute.dex */
public class RHolder implements PublicMemberKeeper {

    /* renamed from: a */
    private static RHolder f13992a;

    /* renamed from: b */
    private int f13993b;

    /* renamed from: c */
    private int f13994c;

    /* renamed from: d */
    private int f13995d;

    private RHolder() {
    }

    public static RHolder getInstance() {
        if (f13992a == null) {
            synchronized (RHolder.class) {
                if (f13992a == null) {
                    f13992a = new RHolder();
                }
            }
        }
        return f13992a;
    }

    public RHolder setActivityThemeId(int i) {
        this.f13993b = i;
        return f13992a;
    }

    public int getActivityThemeId() {
        return this.f13993b;
    }

    public RHolder setDialogLayoutId(int i) {
        this.f13994c = i;
        return f13992a;
    }

    public int getDialogLayoutId() {
        return this.f13994c;
    }

    public RHolder setDialogThemeId(int i) {
        this.f13995d = i;
        return f13992a;
    }

    public int getDialogThemeId() {
        return this.f13995d;
    }
}
