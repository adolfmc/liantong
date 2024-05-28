package com.sinovatech.unicom.separatemodule.keyboard;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.sinovatech.unicom.common.UIUtils;
import java.util.ArrayList;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class EmojiIndicatorView extends LinearLayout {
    private Context mContext;
    private ArrayList<View> mImageViews;
    private int marginLeft;
    private int marginSize;
    private int pointSize;
    private int size;

    public EmojiIndicatorView(Context context) {
        this(context, null);
    }

    public EmojiIndicatorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EmojiIndicatorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.size = 6;
        this.marginSize = 15;
        this.mContext = context;
        Activity activity = (Activity) context;
        this.pointSize = UIUtils.dip2px(activity, this.size);
        this.marginLeft = UIUtils.dip2px(activity, this.marginSize);
    }

    public void initIndicator(int i) {
        this.mImageViews = new ArrayList<>();
        removeAllViews();
        for (int i2 = 0; i2 < i; i2++) {
            View view = new View(this.mContext);
            int i3 = this.pointSize;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i3, i3);
            if (i2 != 0) {
                layoutParams.leftMargin = this.marginLeft;
            }
            view.setLayoutParams(layoutParams);
            if (i2 == 0) {
                view.setBackgroundResource(2131232202);
            } else {
                view.setBackgroundResource(2131232201);
            }
            this.mImageViews.add(view);
            addView(view);
        }
    }

    public void playByStartPointToNext(int i, int i2) {
        if (i < 0 || i2 < 0 || i2 == i) {
            i = 0;
            i2 = 0;
        }
        this.mImageViews.get(i2).setBackgroundResource(2131232202);
        this.mImageViews.get(i).setBackgroundResource(2131232201);
    }
}
