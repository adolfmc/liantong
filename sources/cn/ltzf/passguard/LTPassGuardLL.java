package cn.ltzf.passguard;

import android.content.Context;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import java.lang.ref.SoftReference;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class LTPassGuardLL extends LinearLayout {
    private LTPassGuardKeyBoard m_pkb;
    public SoftReference softReference;

    public LTPassGuardLL(Context context) {
        super(context);
    }

    public LTPassGuardLL(Context context, LTPassGuardKeyBoard lTPassGuardKeyBoard) {
        super(context);
        this.softReference = new SoftReference(lTPassGuardKeyBoard);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        LTPassGuardKeyBoard lTPassGuardKeyBoard;
        if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 0) {
            if (this.softReference.get() == null || (lTPassGuardKeyBoard = (LTPassGuardKeyBoard) this.softReference.get()) == null || !lTPassGuardKeyBoard.isShowing()) {
                return true;
            }
            lTPassGuardKeyBoard.dismiss();
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }
}
