package com.froad.froadeid.base.libs.sdk;

import com.froad.froadeid.base.libs.core.ReadInfoType;
import com.froad.libreadcard.constants.ReadCardStatus;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ReadCardInfoCallBack {
    void readResult(ReadCardStatus readCardStatus, ReadInfoType readInfoType, String str, String str2);
}
