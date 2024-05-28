package com.sinovatech.unicom.basic.p315ui.view;

import android.content.Context;
import android.support.p086v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.style.ScaleXSpan;
import android.util.AttributeSet;
import android.widget.TextView;

/* renamed from: com.sinovatech.unicom.basic.ui.view.ShangshabanChangeTextSpaceView */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ShangshabanChangeTextSpaceView extends AppCompatTextView {
    private CharSequence originalText;
    private float spacing;

    public ShangshabanChangeTextSpaceView(Context context) {
        super(context);
        this.spacing = 1.0f;
        this.originalText = "";
    }

    public ShangshabanChangeTextSpaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.spacing = 1.0f;
        this.originalText = "";
    }

    public ShangshabanChangeTextSpaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.spacing = 1.0f;
        this.originalText = "";
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        this.originalText = charSequence;
        applySpacing();
    }

    @Override // android.support.p086v7.widget.AppCompatTextView, android.widget.TextView
    public CharSequence getText() {
        return this.originalText;
    }

    private void applySpacing() {
        if (this.originalText == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < this.originalText.length()) {
            sb.append(this.originalText.charAt(i));
            i++;
            if (i < this.originalText.length()) {
                sb.append(" ");
            }
        }
        SpannableString spannableString = new SpannableString(sb.toString());
        if (sb.toString().length() > 1) {
            for (int i2 = 1; i2 < sb.toString().length(); i2 += 2) {
                spannableString.setSpan(new ScaleXSpan(1.0f), i2, i2 + 1, 33);
            }
        }
        super.setText(spannableString, TextView.BufferType.SPANNABLE);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.view.ShangshabanChangeTextSpaceView$Spacing */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class Spacing {
        public static final float NORMAL = 1.0f;

        public Spacing() {
        }
    }
}
