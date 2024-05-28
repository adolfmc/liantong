package com.p281qq.p282e.comm.p283pi;

import com.p281qq.p282e.comm.constants.LoadAdParams;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.comm.pi.NUADI */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface NUADI extends ADI {
    String getAdNetWorkName();

    void loadData(int i);

    void loadData(int i, LoadAdParams loadAdParams);

    void setCategories(List<String> list);

    void setMaxVideoDuration(int i);

    void setMinVideoDuration(int i);

    void setVastClassName(String str);
}
