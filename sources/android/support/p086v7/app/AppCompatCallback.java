package android.support.p086v7.app;

import android.support.annotation.Nullable;
import android.support.p086v7.view.ActionMode;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: android.support.v7.app.AppCompatCallback */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface AppCompatCallback {
    void onSupportActionModeFinished(ActionMode actionMode);

    void onSupportActionModeStarted(ActionMode actionMode);

    @Nullable
    ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback);
}
