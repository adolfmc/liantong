package com.bytedance.android.live.base.api;

import com.bytedance.android.live.base.IService;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface MethodChannelService extends IService {
    String identity();

    Object invokeMethod(String str, Object... objArr);
}
