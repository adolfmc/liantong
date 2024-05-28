package android.support.p086v7.widget.helper;

import android.graphics.Canvas;
import android.os.Build;
import android.support.p083v4.view.ViewCompat;
import android.support.p086v7.recyclerview.C1287R;
import android.support.p086v7.widget.RecyclerView;
import android.view.View;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: android.support.v7.widget.helper.ItemTouchUIUtilImpl */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ItemTouchUIUtilImpl implements ItemTouchUIUtil {
    static final ItemTouchUIUtil INSTANCE = new ItemTouchUIUtilImpl();

    @Override // android.support.p086v7.widget.helper.ItemTouchUIUtil
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, View view, float f, float f2, int i, boolean z) {
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchUIUtil
    public void onSelected(View view) {
    }

    ItemTouchUIUtilImpl() {
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchUIUtil
    public void onDraw(Canvas canvas, RecyclerView recyclerView, View view, float f, float f2, int i, boolean z) {
        if (Build.VERSION.SDK_INT >= 21 && z && view.getTag(C1287R.C1290id.item_touch_helper_previous_elevation) == null) {
            Float valueOf = Float.valueOf(ViewCompat.getElevation(view));
            ViewCompat.setElevation(view, findMaxElevation(recyclerView, view) + 1.0f);
            view.setTag(C1287R.C1290id.item_touch_helper_previous_elevation, valueOf);
        }
        view.setTranslationX(f);
        view.setTranslationY(f2);
    }

    private static float findMaxElevation(RecyclerView recyclerView, View view) {
        int childCount = recyclerView.getChildCount();
        float f = 0.0f;
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            if (childAt != view) {
                float elevation = ViewCompat.getElevation(childAt);
                if (elevation > f) {
                    f = elevation;
                }
            }
        }
        return f;
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchUIUtil
    public void clearView(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            Object tag = view.getTag(C1287R.C1290id.item_touch_helper_previous_elevation);
            if (tag != null && (tag instanceof Float)) {
                ViewCompat.setElevation(view, ((Float) tag).floatValue());
            }
            view.setTag(C1287R.C1290id.item_touch_helper_previous_elevation, null);
        }
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
    }
}
