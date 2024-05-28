package com.baidu.mapsdkplatform.comapi.p142b.p143a;

import java.io.File;
import java.util.Comparator;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.b.a.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SortClassByTime implements Comparator {
    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        try {
            return ((File) obj2).getName().split("_")[2].compareTo(((File) obj).getName().split("_")[2]);
        } catch (Exception unused) {
            return 0;
        }
    }
}
