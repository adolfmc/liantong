package com.sinovatech.unicom.basic.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.binioter.guideview.Component;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.common.UIUtils;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeVideoComponent implements Component {
    private MengCengClick mengCengClick;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface MengCengClick {
        void onMengCengClick(boolean z);
    }

    @Override // com.binioter.guideview.Component
    public int getAnchor() {
        return 4;
    }

    @Override // com.binioter.guideview.Component
    public int getFitPosition() {
        return 32;
    }

    public HomeVideoComponent(MengCengClick mengCengClick) {
        this.mengCengClick = mengCengClick;
    }

    @Override // com.binioter.guideview.Component
    public View getView(LayoutInflater layoutInflater) {
        View inflate = layoutInflater.inflate(2131493048, (ViewGroup) null);
        ((ImageView) inflate.findViewById(2131299514)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.HomeVideoComponent.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                HomeVideoComponent.this.mengCengClick.onMengCengClick(true);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        ((ImageView) inflate.findViewById(2131299515)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.HomeVideoComponent.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                HomeVideoComponent.this.mengCengClick.onMengCengClick(false);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        return inflate;
    }

    @Override // com.binioter.guideview.Component
    public int getXOffset() {
        return UIUtils.dip2px(0.0f);
    }

    @Override // com.binioter.guideview.Component
    public int getYOffset() {
        return -UIUtils.dip2px(0.0f);
    }
}
