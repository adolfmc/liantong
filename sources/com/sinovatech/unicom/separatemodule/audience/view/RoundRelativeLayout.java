package com.sinovatech.unicom.separatemodule.audience.view;

import android.content.Context;
import android.graphics.Outline;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.RelativeLayout;
import com.sinovatech.unicom.common.UIUtils;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RoundRelativeLayout extends RelativeLayout {
    private static final int DEFAULT_ROUND_SIZE = 5;

    public RoundRelativeLayout(Context context) {
        this(context, null);
    }

    public RoundRelativeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public RoundRelativeLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    private void init() {
        setOutlineProvider(new RoundViewOutlineProvider(UIUtils.dip2px(5.0f)));
        setClipToOutline(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class RoundViewOutlineProvider extends ViewOutlineProvider {
        private final int roundSize;

        public RoundViewOutlineProvider(int i) {
            this.roundSize = i;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), this.roundSize);
        }
    }
}
