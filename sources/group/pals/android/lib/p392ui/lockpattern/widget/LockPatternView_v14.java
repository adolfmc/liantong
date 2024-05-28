package group.pals.android.lib.p392ui.lockpattern.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityManager;

/* renamed from: group.pals.android.lib.ui.lockpattern.widget.LockPatternView_v14 */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class LockPatternView_v14 extends LockPatternView {
    private final AccessibilityManager mAccessibilityManager;

    public LockPatternView_v14(Context context) {
        this(context, null);
    }

    public LockPatternView_v14(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAccessibilityManager = isInEditMode() ? null : (AccessibilityManager) context.getSystemService("accessibility");
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        AccessibilityManager accessibilityManager = this.mAccessibilityManager;
        if (accessibilityManager != null && accessibilityManager.isTouchExplorationEnabled()) {
            int action = motionEvent.getAction();
            if (action != 7) {
                switch (action) {
                    case 9:
                        motionEvent.setAction(0);
                        break;
                    case 10:
                        motionEvent.setAction(1);
                        break;
                }
            } else {
                motionEvent.setAction(2);
            }
            onTouchEvent(motionEvent);
            motionEvent.setAction(action);
        }
        return super.onHoverEvent(motionEvent);
    }
}
