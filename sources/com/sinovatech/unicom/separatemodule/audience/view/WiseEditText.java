package com.sinovatech.unicom.separatemodule.audience.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.p086v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class WiseEditText extends AppCompatEditText {
    private View.OnKeyListener keyListener;

    public WiseEditText(Context context) {
        super(context);
    }

    public WiseEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public WiseEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.support.p086v7.widget.AppCompatEditText, android.widget.TextView, android.view.View
    @Nullable
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return new MyInputConnection(super.onCreateInputConnection(editorInfo), true);
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class MyInputConnection extends InputConnectionWrapper {
        public MyInputConnection(InputConnection inputConnection, boolean z) {
            super(inputConnection, z);
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public boolean sendKeyEvent(KeyEvent keyEvent) {
            if (WiseEditText.this.keyListener != null) {
                WiseEditText.this.keyListener.onKey(WiseEditText.this, keyEvent.getKeyCode(), keyEvent);
            }
            return super.sendKeyEvent(keyEvent);
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public boolean deleteSurroundingText(int i, int i2) {
            if (i == 1 && i2 == 0) {
                return sendKeyEvent(new KeyEvent(0, 67));
            }
            return super.deleteSurroundingText(i, i2);
        }
    }

    public void setSoftKeyListener(View.OnKeyListener onKeyListener) {
        this.keyListener = onKeyListener;
    }
}
