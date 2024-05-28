package com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class DanmakuViewFactory {
    DanmakuViewFactory() {
    }

    @SuppressLint({"InflateParams"})
    static DanmakuView createDanmakuView(Context context) {
        return (DanmakuView) LayoutInflater.from(context).inflate(2131493079, (ViewGroup) null, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DanmakuView createDanmakuView(Context context, ViewGroup viewGroup) {
        return (DanmakuView) LayoutInflater.from(context).inflate(2131493079, viewGroup, false);
    }
}
