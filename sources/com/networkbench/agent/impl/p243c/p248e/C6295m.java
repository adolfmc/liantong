package com.networkbench.agent.impl.p243c.p248e;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.e.m */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6295m {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.c.e.m$b */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum EnumC6297b {
        OTHER,
        AttachBaseContext,
        AppCreate,
        ActivityCreate,
        ActivityStart,
        ActivityRestart,
        ActivityResume,
        BLANK
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.c.e.m$d */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum EnumC6299d {
        queueIdle,
        setPageLoadingEndTime,
        timeOut
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.c.e.m$e */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum EnumC6300e {
        OTHER(0),
        NETWORK(1),
        JSON(2),
        DATABASE(3),
        BITMAP(4),
        CUSTOM(9);
        

        /* renamed from: g */
        private final int f15791g;

        EnumC6300e(int i) {
            this.f15791g = i;
        }

        /* renamed from: a */
        public int m10531a() {
            return this.f15791g;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.c.e.m$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum EnumC6296a {
        SYNC(1),
        ASYNC(2);
        

        /* renamed from: c */
        private final int f15758c;

        EnumC6296a(int i) {
            this.f15758c = i;
        }

        /* renamed from: a */
        public int m10532a() {
            return this.f15758c;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.c.e.m$f */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum EnumC6301f {
        eventAction(0),
        appstart(1),
        pageLoading(2);
        

        /* renamed from: d */
        private final int f15796d;

        EnumC6301f(int i) {
            this.f15796d = i;
        }

        /* renamed from: a */
        public int m10530a() {
            return this.f15796d;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.c.e.m$c */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum EnumC6298c {
        View(0),
        Clicked(1),
        ItemClicked(2),
        ItemSelected(3),
        MenuItemClick(4),
        OptionsItemSelected(5),
        PageSelected(6),
        CustomAction(7),
        LongClicked(8),
        BackBtnPress(9);
        

        /* renamed from: k */
        public final int f15779k;

        EnumC6298c(int i) {
            this.f15779k = i;
        }
    }
}
