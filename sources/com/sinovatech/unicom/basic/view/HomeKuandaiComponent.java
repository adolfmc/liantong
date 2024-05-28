package com.sinovatech.unicom.basic.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.binioter.guideview.Component;
import com.sinovatech.unicom.common.UIUtils;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeKuandaiComponent implements Component {
    @Override // com.binioter.guideview.Component
    public int getAnchor() {
        return 4;
    }

    @Override // com.binioter.guideview.Component
    public int getFitPosition() {
        return 48;
    }

    @Override // com.binioter.guideview.Component
    public View getView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(2131493056, (ViewGroup) null);
    }

    @Override // com.binioter.guideview.Component
    public int getXOffset() {
        return UIUtils.dip2px(10.0f);
    }

    @Override // com.binioter.guideview.Component
    public int getYOffset() {
        return UIUtils.dip2px(10.0f);
    }
}
