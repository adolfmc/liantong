package com.networkbench.agent.impl.harvest.p260a;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.harvest.a.l */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6448l {

    /* renamed from: a */
    public static boolean f16292a = true;

    /* renamed from: a */
    public static AbstractC6444i m9964a(EnumC6455q enumC6455q, String str, boolean z) {
        if (enumC6455q == null) {
            throw new IllegalArgumentException("error uploadData");
        }
        switch (enumC6455q) {
            case REDIRECT:
                return new C6451n(str, z);
            case INIT_MOBILE:
                return new C6440f(str, z);
            case METIRC_DATA:
                return new C6439e(str, z);
            case ACTION_SELECTED:
                return new C6435b(str, z);
            case UPDATE_HINT:
                return new C6454p(str, z);
            case USER_ACTION:
                return new C6456r(str, z);
            case USER_PROFILE:
                return new C6457s(str, z);
            case CONTROLLER_DATA:
                return new C6442g(str, z);
            case CRASH_DATA:
                return new C6443h(str, z);
            case ANR_DATA:
                return new C6436c(str, z);
            case RESOURE_HOST:
                return new C6453o(str, z);
            case EXTENSION:
                return new C6446k(str, z);
            case EXTENSION_SEND:
                return new C6445j(str, z);
            default:
                return null;
        }
    }
}
