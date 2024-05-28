package com.baidu.cloud.mediaprocess.filter;

import android.opengl.EGLContext;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface ITextureReader {
    void onTextureUpdate(int i, int i2, int i3);

    void release();

    void setup(EGLContext eGLContext);
}
