package org.limlee.hipraiseanimationlib.base;

import android.graphics.Canvas;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface IDrawTask {
    void addDrawable(IDrawable iDrawable);

    void clearDrawable();

    void draw(Canvas canvas);

    void start();

    void stop();
}
