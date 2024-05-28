package com.sinovatech.unicom.basic.p315ui.home.fragment;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.home.fragment.OnUnicomHomeListener */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface OnUnicomHomeListener {
    public static final String CITYCHANGE = "3";
    public static final String ONLINECALLBACK = "2";
    public static final String XIALASHUAXIN = "1";

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.sinovatech.unicom.basic.ui.home.fragment.OnUnicomHomeListener$HomeMethod */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public @interface HomeMethod {
    }

    void onEventTriggered(String str);
}
