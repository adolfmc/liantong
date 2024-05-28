package cn.ltzf.passguard;

import android.content.Context;
import android.media.AudioManager;
import android.view.View;
import android.widget.ImageButton;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class LTPGImageButton extends ImageButton {
    private View.OnClickListener m_OnClickListener;
    private Context m_context;

    public LTPGImageButton(Context context) {
        super(context);
        this.m_OnClickListener = null;
        this.m_context = context;
    }

    @Override // android.view.View
    public boolean performClick() {
        sendAccessibilityEvent(1);
        if (this.m_OnClickListener != null) {
            ((AudioManager) this.m_context.getSystemService("audio")).playSoundEffect(5, 0.5f);
            this.m_OnClickListener.onClick(this);
            return true;
        }
        return false;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        if (!isClickable()) {
            setClickable(true);
        }
        this.m_OnClickListener = onClickListener;
    }
}
