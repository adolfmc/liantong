package com.p281qq.p282e.comm.p283pi;

import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.comm.pi.AdData */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface AdData {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.qq.e.comm.pi.AdData$VideoPlayer */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface VideoPlayer {
        int getCurrentPosition();

        int getDuration();

        int getVideoState();
    }

    boolean equalsAdData(AdData adData);

    int getAdPatternType();

    String getDesc();

    int getECPM();

    String getECPMLevel();

    Map<String, Object> getExtraInfo();

    <T> T getProperty(Class<T> cls);

    String getProperty(String str);

    String getTitle();

    int getVideoDuration();

    void setECPMLevel(String str);
}
