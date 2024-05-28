package com.yhao.floatwindow;

import android.view.View;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class FloatView {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void dismiss();

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getX() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getY() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void init();

    boolean isDrag() {
        return false;
    }

    void setDrag(boolean z) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setGravity(int i, int i2, int i3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setSize(int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setView(View view);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateSize(int i, int i2) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateX(int i) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateXY(int i, int i2) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateY(int i) {
    }
}
