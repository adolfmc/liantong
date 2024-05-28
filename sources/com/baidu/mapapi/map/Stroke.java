package com.baidu.mapapi.map;

import android.os.Bundle;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class Stroke {
    public final int color;
    public final int strokeWidth;

    public Stroke(int i, int i2) {
        this.strokeWidth = i <= 0 ? 5 : i;
        this.color = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public Bundle m18875a(Bundle bundle) {
        bundle.putInt("width", this.strokeWidth);
        Overlay.m18893a(this.color, bundle);
        return bundle;
    }
}
