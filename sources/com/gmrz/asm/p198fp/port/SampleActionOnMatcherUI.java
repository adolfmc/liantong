package com.gmrz.asm.p198fp.port;

import com.gmrz.asm.p198fp.utils.Logger;

/* renamed from: com.gmrz.asm.fp.port.SampleActionOnMatcherUI */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class SampleActionOnMatcherUI implements CustomActionOnMatcherUI {
    private static final String TAG = "SampleActionOnMatcherUI";
    private static boolean sSwitchOneCheckedStatus;
    private static boolean sSwitchTwoCheckedStatus;

    @Override // com.gmrz.asm.p198fp.port.CustomActionOnMatcherUI
    public void onCancelBtnClicked() {
        Logger.m15751e(TAG, "onCancelBtnClicked");
    }

    @Override // com.gmrz.asm.p198fp.port.CustomActionOnMatcherUI
    public void onUsePwdBtnClicked() {
        Logger.m15751e(TAG, "onUsePwdBtnClicked");
    }

    @Override // com.gmrz.asm.p198fp.port.CustomActionOnMatcherUI
    public void onMatcherUserInterfaceDismissed() {
        Logger.m15751e(TAG, "onMatcherUserInterfaceDismissed");
    }

    @Override // com.gmrz.asm.p198fp.port.CustomActionOnMatcherUI
    public void onSwitchOneCheckedChangeListener(boolean z) {
        Logger.m15751e(TAG, "onSwitchOneCheckedChangeListener: isChecked value:: " + z);
        sSwitchOneCheckedStatus = z;
    }

    @Override // com.gmrz.asm.p198fp.port.CustomActionOnMatcherUI
    public void onSwitchTwoCheckedChangeListener(boolean z) {
        Logger.m15751e(TAG, "onSwitchTwoCheckedChangeListener: isChecked value:: " + z);
        sSwitchTwoCheckedStatus = z;
    }

    public static void setSwitchOneChecked(boolean z) {
        sSwitchOneCheckedStatus = z;
    }

    public static void setSwitchTwoChecked(boolean z) {
        sSwitchTwoCheckedStatus = z;
    }

    public static boolean getSwitchOneCheckedStatus() {
        return sSwitchOneCheckedStatus;
    }

    public static boolean getSwitchTwoCheckedStatus() {
        return sSwitchTwoCheckedStatus;
    }
}
