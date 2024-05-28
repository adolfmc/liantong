package com.networkbench.agent.impl.harvest;

import com.networkbench.agent.impl.harvest.p260a.AbstractC6444i;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface HarvestConnectionInterface {
    HarvestResponse sendDataFile(Map<String, String[]> map, AbstractC6444i abstractC6444i) throws Exception;

    HarvestResponse sendDataGet(AbstractC6444i abstractC6444i) throws Exception;

    HarvestResponse sendDataStr(String str, AbstractC6444i abstractC6444i) throws Exception;
}
