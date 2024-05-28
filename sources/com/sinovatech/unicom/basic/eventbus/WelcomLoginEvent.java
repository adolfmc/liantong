package com.sinovatech.unicom.basic.eventbus;

import com.sinovatech.unicom.common.EventMessage;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class WelcomLoginEvent extends EventMessage<HashMap<String, String>> {
    public WelcomLoginEvent(int i, HashMap<String, String> hashMap) {
        super(i, hashMap);
    }
}
