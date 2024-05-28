package android.support.p086v7.view.menu;

import android.support.annotation.RestrictTo;
import android.widget.ListView;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.view.menu.ShowableListMenu */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface ShowableListMenu {
    void dismiss();

    ListView getListView();

    boolean isShowing();

    void show();
}
