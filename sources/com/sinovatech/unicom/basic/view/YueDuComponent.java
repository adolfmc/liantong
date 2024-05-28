package com.sinovatech.unicom.basic.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.binioter.guideview.Component;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class YueDuComponent implements Component {
    private int fitPosition;
    private int position;
    private int xOffest;

    @Override // com.binioter.guideview.Component
    public int getAnchor() {
        return 4;
    }

    @Override // com.binioter.guideview.Component
    public int getYOffset() {
        return 4;
    }

    public YueDuComponent(int i, Activity activity) {
        this.position = i;
    }

    @Override // com.binioter.guideview.Component
    public View getView(LayoutInflater layoutInflater) {
        View inflate = layoutInflater.inflate(2131493056, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(2131299861);
        switch (this.position) {
            case 0:
            case 1:
                imageView.setImageResource(2131232728);
                this.xOffest = -30;
                this.fitPosition = 16;
                break;
            case 2:
                imageView.setImageResource(2131232729);
                this.xOffest = -85;
                this.fitPosition = 16;
                break;
            case 3:
                imageView.setImageResource(2131232730);
                this.xOffest = -20;
                this.fitPosition = 32;
                break;
            case 4:
                imageView.setImageResource(2131232731);
                this.xOffest = 17;
                this.fitPosition = 48;
                break;
            case 5:
                imageView.setImageResource(2131232732);
                this.xOffest = 7;
                this.fitPosition = 48;
                break;
        }
        return inflate;
    }

    @Override // com.binioter.guideview.Component
    public int getFitPosition() {
        return this.fitPosition;
    }

    @Override // com.binioter.guideview.Component
    public int getXOffset() {
        return this.xOffest;
    }
}
