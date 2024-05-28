package com.networkbench.agent.impl.harvest;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface HarvestLifecycleAware {
    void onHarvest();

    void onHarvestBefore();

    void onHarvestComplete();

    void onHarvestConnected();

    void onHarvestDeviceIdError();

    void onHarvestDisabled();

    void onHarvestDisconnected();

    void onHarvestError();

    void onHarvestFilter();

    void onHarvestFinalize();

    void onHarvestSendFailed();

    void onHarvestStart();

    void onHarvestStop();
}
