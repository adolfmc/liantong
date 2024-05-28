package com.baidu.p120ar.face;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.FaceListener */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface FaceListener {
    void onFaceResult(Object obj);

    void onStickerLoadingFinished(List<String> list);

    void onTriggerFired(String str);
}
