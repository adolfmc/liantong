package com.mahc.custombottomsheetbehavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.p083v4.content.ContextCompat;
import android.support.p086v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class MergedAppBarLayout extends AppBarLayout {
    protected View background;
    protected Toolbar toolbar;

    public MergedAppBarLayout(Context context) {
        super(context);
        init();
    }

    public MergedAppBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        inflate(getContext(), C5284R.C5288layout.mergedappbarlayout, this);
        setBackgroundColor(ContextCompat.getColor(getContext(), 17170445));
        getContext().setTheme(C5284R.C5289style.AppTheme_AppBarOverlay);
        this.toolbar = (Toolbar) findViewById(C5284R.C5287id.expanded_toolbar);
        this.background = findViewById(C5284R.C5287id.background);
    }
}
