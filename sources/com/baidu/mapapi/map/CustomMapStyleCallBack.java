package com.baidu.mapapi.map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface CustomMapStyleCallBack {
    boolean onCustomMapStyleLoadFailed(int i, String str, String str2);

    boolean onCustomMapStyleLoadSuccess(boolean z, String str);

    boolean onPreLoadLastCustomMapStyle(String str);
}
