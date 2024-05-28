package com.mob.tools.network;

import com.mob.tools.proguard.PublicMemberKeeper;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface RawNetworkCallback extends PublicMemberKeeper {
    void onResponse(InputStream inputStream) throws Throwable;
}
