package com.yhao.floatwindow;

import android.view.View;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class IFloatWindow {
    public abstract void dismiss();

    public abstract View getView();

    public abstract int getX();

    public abstract int getY();

    public abstract void hide();

    public abstract boolean isDrag();

    public abstract boolean isShowing();

    public abstract void setDrag(boolean z);

    public abstract void show();

    public abstract void updateSize(int i, int i2);

    public abstract void updateX(int i);

    public abstract void updateX(int i, float f);

    public abstract void updateY(int i);

    public abstract void updateY(int i, float f);
}
