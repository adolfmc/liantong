package com.zhpan.bannerview.transform;

import android.support.p083v4.view.ViewPager;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class PageTransformerFactory {
    public ViewPager.PageTransformer createPageTransformer(int i) {
        switch (i) {
            case 1:
                return new DepthPageTransformer();
            case 2:
                return new StackTransformer();
            case 3:
                return new AccordionTransformer();
            case 4:
                return new RotateUpTransformer();
            default:
                return null;
        }
    }
}
