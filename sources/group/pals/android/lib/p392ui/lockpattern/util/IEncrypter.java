package group.pals.android.lib.p392ui.lockpattern.util;

import android.content.Context;
import group.pals.android.lib.p392ui.lockpattern.widget.LockPatternView;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: group.pals.android.lib.ui.lockpattern.util.IEncrypter */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IEncrypter {
    List<LockPatternView.Cell> decrypt(Context context, char[] cArr);

    char[] encrypt(Context context, List<LockPatternView.Cell> list);
}
