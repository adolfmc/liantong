package com.sinovatech.unicom.basic.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.binioter.guideview.Component;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SettingCareComponent implements Component {
    @Override // com.binioter.guideview.Component
    public int getAnchor() {
        return 4;
    }

    @Override // com.binioter.guideview.Component
    public int getFitPosition() {
        return 48;
    }

    @Override // com.binioter.guideview.Component
    public int getXOffset() {
        return -20;
    }

    @Override // com.binioter.guideview.Component
    public int getYOffset() {
        return 20;
    }

    @Override // com.binioter.guideview.Component
    public View getView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(2131493035, (ViewGroup) null);
    }
}
