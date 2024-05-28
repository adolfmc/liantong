package com.sinovatech.unicom.separatemodule.audience.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AudienceXiaboView extends FrameLayout {
    public AudienceXiaboView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public AudienceXiaboView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    private void init(Context context) {
        final Activity activity = (Activity) context;
        View inflate = LayoutInflater.from(context).inflate(2131492994, (ViewGroup) null);
        ((ImageView) inflate.findViewById(2131296465)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceXiaboView$HVn8uo9RHVfjzJznKWjZUMV9PYg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                activity.finish();
            }
        });
        addView(inflate);
    }
}
