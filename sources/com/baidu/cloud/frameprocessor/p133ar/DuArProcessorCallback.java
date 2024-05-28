package com.baidu.cloud.frameprocessor.p133ar;

import com.baidu.minivideo.arface.bean.BeautyEnableStatus;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.cloud.frameprocessor.ar.DuArProcessorCallback */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface DuArProcessorCallback {
    void onBeautyEnableChanged(BeautyEnableStatus beautyEnableStatus);

    void onChangeGender(boolean z);

    void onLuaMessage(HashMap<String, Object> hashMap);

    void onStickerSwitchCamera(int i);
}
