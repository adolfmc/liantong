package com.sinovatech.unicom.separatemodule.anquanzhongxin;

import android.app.Activity;
import android.content.Intent;
import com.sinovatech.unicom.basic.p315ui.activity.FingerDialogActivity;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FingerScreenManager {
    public static final int ONCANCEL = 4;
    public static final int ONFAIL = 2;
    public static final int ONRESET = 3;
    public static final int ONSUCCESS = 1;
    private final Activity activity;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface FingerCompleteInterface {
        void cancel();

        void fail();

        void reset();

        void success();
    }

    public FingerScreenManager(Activity activity) {
        this.activity = activity;
    }

    public void startFingerprit(final FingerCompleteInterface fingerCompleteInterface) {
        new AvoidOnResult(this.activity).startForResult(FingerDialogActivity.class, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager.1
            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
            public void onActivityResult(int i, Intent intent) {
                if (-1 == i) {
                    int intExtra = intent.getIntExtra("method", 0);
                    if (intExtra == 1) {
                        fingerCompleteInterface.success();
                        return;
                    }
                    switch (intExtra) {
                        case 3:
                            fingerCompleteInterface.reset();
                            return;
                        case 4:
                            fingerCompleteInterface.cancel();
                            return;
                        default:
                            fingerCompleteInterface.fail();
                            return;
                    }
                }
            }
        });
    }
}
