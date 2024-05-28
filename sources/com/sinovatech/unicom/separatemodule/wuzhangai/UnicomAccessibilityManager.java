package com.sinovatech.unicom.separatemodule.wuzhangai;

import android.content.Context;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UnicomAccessibilityManager {
    private static UnicomAccessibilityManager unicomAccessibilityManager;
    private final String TAG = "UnicomAccessibilityManager";
    private AccessibilityManager accessibilityManager;

    private UnicomAccessibilityManager() {
    }

    public static UnicomAccessibilityManager getInstance() {
        if (unicomAccessibilityManager == null) {
            synchronized (UnicomAccessibilityManager.class) {
                if (unicomAccessibilityManager == null) {
                    unicomAccessibilityManager = new UnicomAccessibilityManager();
                }
            }
        }
        return unicomAccessibilityManager;
    }

    public void init(Context context) {
        try {
            if (this.accessibilityManager == null) {
                this.accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isOpen() {
        try {
            if (this.accessibilityManager == null) {
                return false;
            }
            return this.accessibilityManager.isTouchExplorationEnabled();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void sayText(String str) {
        try {
            if (this.accessibilityManager == null) {
                return;
            }
            AccessibilityEvent obtain = AccessibilityEvent.obtain(16384);
            obtain.getText().add(str);
            this.accessibilityManager.sendAccessibilityEvent(obtain);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTalkBackListener(final TalkBackListener talkBackListener) {
        try {
            if (this.accessibilityManager == null) {
                return;
            }
            this.accessibilityManager.addAccessibilityStateChangeListener(new AccessibilityManager.AccessibilityStateChangeListener() { // from class: com.sinovatech.unicom.separatemodule.wuzhangai.UnicomAccessibilityManager.1
                @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
                public void onAccessibilityStateChanged(boolean z) {
                    if (z) {
                        MsLogUtil.m7979d("UnicomAccessibilityManager", "Accessibility service enabled");
                    } else {
                        MsLogUtil.m7979d("UnicomAccessibilityManager", "Accessibility service disabled");
                    }
                    talkBackListener.onTalkBackStatusChanged(z);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
