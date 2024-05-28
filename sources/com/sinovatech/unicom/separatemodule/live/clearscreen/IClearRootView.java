package com.sinovatech.unicom.separatemodule.live.clearscreen;

import android.view.View;
import com.sinovatech.unicom.separatemodule.live.clearscreen.Constants;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface IClearRootView {
    void addView(View view, int i);

    void setClearSide(Constants.Orientation orientation);

    void setIClearEvent(IClearEvent iClearEvent);

    void setIPositionCallBack(IPositionCallBack iPositionCallBack);
}
