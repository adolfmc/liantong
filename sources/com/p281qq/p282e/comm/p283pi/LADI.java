package com.p281qq.p282e.comm.p283pi;

import com.p281qq.p282e.comm.compliance.ApkDownloadComplianceInterface;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.comm.pi.LADI */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface LADI extends ApkDownloadComplianceInterface, IBidding {
    int getECPM();

    String getECPMLevel();

    Map<String, Object> getExtraInfo();

    boolean isValid();
}
