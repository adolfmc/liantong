package com.sinovatech.unicom.separatemodule.live.clearscreen;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.sinovatech.unicom.separatemodule.live.clearscreen.Constants;
import com.sinovatech.unicom.separatemodule.live.clearscreen.view.ScreenSideView;
import java.util.LinkedList;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ClearScreenHelper {
    private LinkedList<View> mClearList;
    private IClearEvent mIClearEvent;
    private IClearRootView mScreenSideView;

    @Deprecated
    public ClearScreenHelper(Context context) {
        this(context, null);
    }

    public ClearScreenHelper(Context context, IClearRootView iClearRootView) {
        initView(context, iClearRootView);
        initPara();
        initCallback();
    }

    private void initView(Context context, IClearRootView iClearRootView) {
        if (iClearRootView == null) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
            this.mScreenSideView = new ScreenSideView(context);
            ((ViewGroup) ((Activity) context).getWindow().getDecorView()).addView((View) this.mScreenSideView, layoutParams);
            return;
        }
        this.mScreenSideView = iClearRootView;
        View view = new View(context);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        view.setClickable(true);
        iClearRootView.addView(view, 0);
    }

    private void initPara() {
        this.mClearList = new LinkedList<>();
        setOrientation(Constants.Orientation.RIGHT);
    }

    private void initCallback() {
        this.mScreenSideView.setIPositionCallBack(new IPositionCallBack() { // from class: com.sinovatech.unicom.separatemodule.live.clearscreen.ClearScreenHelper.1
            @Override // com.sinovatech.unicom.separatemodule.live.clearscreen.IPositionCallBack
            public void onPositionChange(int i, int i2) {
                for (int i3 = 0; i3 < ClearScreenHelper.this.mClearList.size(); i3++) {
                    ((View) ClearScreenHelper.this.mClearList.get(i3)).setTranslationX(i);
                    ((View) ClearScreenHelper.this.mClearList.get(i3)).setTranslationY(i2);
                }
            }
        });
        this.mScreenSideView.setIClearEvent(new IClearEvent() { // from class: com.sinovatech.unicom.separatemodule.live.clearscreen.ClearScreenHelper.2
            @Override // com.sinovatech.unicom.separatemodule.live.clearscreen.IClearEvent
            public void onClearEnd() {
                if (ClearScreenHelper.this.mIClearEvent != null) {
                    ClearScreenHelper.this.mIClearEvent.onClearEnd();
                }
            }

            @Override // com.sinovatech.unicom.separatemodule.live.clearscreen.IClearEvent
            public void onRecovery() {
                if (ClearScreenHelper.this.mIClearEvent != null) {
                    ClearScreenHelper.this.mIClearEvent.onRecovery();
                }
            }
        });
    }

    public void setIClearEvent(IClearEvent iClearEvent) {
        this.mIClearEvent = iClearEvent;
    }

    public void setOrientation(Constants.Orientation orientation) {
        this.mScreenSideView.setClearSide(orientation);
    }

    public void bind(@NonNull View... viewArr) {
        for (View view : viewArr) {
            if (!this.mClearList.contains(view)) {
                this.mClearList.add(view);
            }
        }
    }

    public void unbind(@NonNull View... viewArr) {
        for (View view : viewArr) {
            if (this.mClearList.contains(view)) {
                this.mClearList.remove(view);
            }
        }
    }

    public void unbindAllCell() {
        this.mClearList.clear();
    }
}
