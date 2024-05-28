package com.chinaunicon.jtwifilib.core.crashreport;

import androidx.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ThreadCollector {
    @Nullable
    public static String collect(@Nullable Thread thread) {
        StringBuffer stringBuffer = new StringBuffer();
        if (thread != null) {
            stringBuffer.append("id=");
            stringBuffer.append(thread.getId());
            stringBuffer.append("\n");
            stringBuffer.append("name=");
            stringBuffer.append(thread.getName());
            stringBuffer.append("\n");
            stringBuffer.append("priority=");
            stringBuffer.append(thread.getPriority());
            stringBuffer.append("\n");
            if (thread.getThreadGroup() != null) {
                stringBuffer.append("groupName=");
                stringBuffer.append(thread.getThreadGroup().getName());
                stringBuffer.append("\n");
            }
        }
        return stringBuffer.toString();
    }
}
