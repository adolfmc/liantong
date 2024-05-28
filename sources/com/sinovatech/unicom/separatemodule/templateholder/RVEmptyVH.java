package com.sinovatech.unicom.separatemodule.templateholder;

import android.app.Activity;
import android.util.Log;
import android.view.View;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RVEmptyVH extends RVItemViewHolder {
    private final String TAG;

    public RVEmptyVH(Activity activity, View view) {
        super(activity, view);
        this.TAG = getClass().getSimpleName();
        String str = this.TAG;
        Log.d(str, ">>>>>>>>>>【" + this.TAG + "】执行构造函数");
    }

    @Override // com.sinovatech.unicom.separatemodule.templateholder.RVItemViewHolder
    public void bindData(Object obj) {
        String str = this.TAG;
        Log.d(str, ">>>>>>>>>>【" + this.TAG + "】执行bindData");
    }
}
