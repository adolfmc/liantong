package com.froad.libreadcard.constants;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public enum ReadCardType {
    ID_CARD(0),
    BANK_CARD(1),
    E_CARD_NFC(2),
    E_CARD_LOCAL_SIM(3),
    E_CARD_LOCAL_OTHER(4),
    TRAVEL_CARD(5);
    
    public int type;

    ReadCardType(int i) {
        this.type = i;
    }

    public int getType() {
        return this.type;
    }
}
