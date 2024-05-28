package com.app.hubert.guide.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.p083v4.view.ViewPager;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ViewUtils {
    private static final String FRAGMENT_CON = "NoSaveStateFrameLayout";

    public static Rect getLocationInView(View view, View view2) {
        if (view2 == null || view == null) {
            throw new IllegalArgumentException("parent and child can not be null .");
        }
        Context context = view2.getContext();
        View decorView = context instanceof Activity ? ((Activity) context).getWindow().getDecorView() : null;
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        if (view2 == view) {
            view2.getHitRect(rect);
            return rect;
        }
        View view3 = view2;
        while (view3 != decorView && view3 != view) {
            LogUtil.m20451i("tmp class:" + view3.getClass().getSimpleName());
            view3.getHitRect(rect2);
            LogUtil.m20451i("tmp hit Rect:" + rect2);
            if (!view3.getClass().equals("NoSaveStateFrameLayout")) {
                rect.left += rect2.left;
                rect.top += rect2.top;
            }
            view3 = (View) view3.getParent();
            if (view3 == null) {
                throw new IllegalArgumentException("the view is not showing in the window!");
            }
            if (view3.getParent() instanceof ScrollView) {
                int scrollY = ((ScrollView) view3.getParent()).getScrollY();
                LogUtil.m20451i("scrollY:" + scrollY);
                rect.top = rect.top - scrollY;
            }
            if (view3.getParent() instanceof HorizontalScrollView) {
                int scrollX = ((HorizontalScrollView) view3.getParent()).getScrollX();
                LogUtil.m20451i("scrollX:" + scrollX);
                rect.left = rect.left - scrollX;
            }
            if (view3.getParent() != null && (view3.getParent() instanceof ViewPager)) {
                view3 = (View) view3.getParent();
            }
        }
        rect.right = rect.left + view2.getMeasuredWidth();
        rect.bottom = rect.top + view2.getMeasuredHeight();
        return rect;
    }
}
