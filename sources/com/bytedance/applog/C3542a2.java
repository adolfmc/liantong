package com.bytedance.applog;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.ListMenuItemView;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.a2 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3542a2 {
    /* renamed from: a */
    public static View m17336a(View view, MenuItem menuItem) {
        Object itemData;
        int i = 0;
        if (view.getClass() == C3560c2.f8400e) {
            itemData = C3560c2.f8401f.invoke(view, new Object[0]);
        } else {
            boolean z = true;
            if (!(C3737y1.f8962n && (view instanceof ListMenuItemView))) {
                if (!C3737y1.f8957i || !(view instanceof android.support.p086v7.view.menu.ListMenuItemView)) {
                    z = false;
                }
                if (!z) {
                    itemData = null;
                }
            }
            itemData = ((android.support.p086v7.view.menu.ListMenuItemView) view).getItemData();
        }
        if (itemData == menuItem) {
            return view;
        }
        if (view instanceof ViewGroup) {
            while (true) {
                ViewGroup viewGroup = (ViewGroup) view;
                if (i >= viewGroup.getChildCount()) {
                    break;
                }
                View m17336a = m17336a(viewGroup.getChildAt(i), menuItem);
                if (m17336a != null) {
                    return m17336a;
                }
                i++;
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:119:0x01f5, code lost:
        if (r0 < 0) goto L189;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x008f, code lost:
        if (r7 < 2999) goto L206;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x009f, code lost:
        if (r7 == com.bytedance.applog.C3560c2.f8399d) goto L203;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00a4, code lost:
        r7 = "/CustomWindow";
     */
    /* JADX WARN: Removed duplicated region for block: B:164:0x036e  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x03e1  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x04c2  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x04cc  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x04cf  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x04d7  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00fd  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.bytedance.applog.C3654o1 m17335a(android.view.View r21, boolean r22) {
        /*
            Method dump skipped, instructions count: 1272
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3542a2.m17335a(android.view.View, boolean):com.bytedance.applog.o1");
    }
}
