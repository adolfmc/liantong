package com.megvii.lv5.sdk.manager;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public enum LivenessTypeE {
    Meglive(2),
    Flash(3),
    Initiative_Flash(5),
    Distance_Flash(7);
    
    public int index;

    LivenessTypeE(int i) {
        this.index = i;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }
}
