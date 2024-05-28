package com.sinovatech.unicom.separatemodule.miniprogram.cump;

import android.app.Activity;
import com.chinaunicon.jtwifilib.jtcommon.util.JtClient;
import com.sinovatech.unicom.p318ui.App;
import io.objectbox.Box;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ManagerEdopYuyu {
    private Activity activityContext;
    private String fileType = JtClient.UXUE_TEMP_FILE_SUFFIX;
    private Box<CumpEntity> cumpBox = App.getBoxStore().boxFor(CumpEntity.class);

    public void queryYuYue() {
    }

    public ManagerEdopYuyu(Activity activity) {
        this.activityContext = activity;
    }
}
