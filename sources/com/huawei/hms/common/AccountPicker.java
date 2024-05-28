package com.huawei.hms.common;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import com.huawei.hms.support.log.HMSLog;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class AccountPicker {
    public static final String CHOOSE_ACCOUNT = "com.huawei.hms.common.account.CHOOSE_ACCOUNT";
    public static final int CUSTOM_THEME_ACCOUNT_CHIPS = 2;
    public static final int CUSTOM_THEME_GAMES = 1;
    public static final int CUSTOM_THEME_NONE = 0;
    public static final String EXTRA_ADD_ACCOUNT_AUTH_TOKEN_TYPE_STRING = "authTokenType";
    public static final String EXTRA_ADD_ACCOUNT_OPTIONS_BUNDLE = "addAccountOptions";
    public static final String EXTRA_ADD_ACCOUNT_REQUIRED_FEATURES_STRING_ARRAY = "addAccountRequiredFeatures";
    public static final String EXTRA_ALLOWABLE_ACCOUNTS_ARRAYLIST = "allowableAccounts";
    public static final String EXTRA_ALLOWABLE_ACCOUNT_TYPES_STRING_ARRAY = "allowableAccountTypes";
    public static final String EXTRA_ALWAYS_PROMPT_FOR_ACCOUNT = "alwaysPromptForAccount";
    public static final String EXTRA_DESCRIPTION_TEXT_OVERRIDE = "descriptionTextOverride";
    public static final String EXTRA_HOSTED_DOMAIN_FILTER = "hostedDomainFilter";
    public static final String EXTRA_IS_ACCOUNT_CHIPS_ACCOUNT_PICKER = "pickedFromAccountChips";
    public static final String EXTRA_OVERRIDE_CUSTOM_THEME = "overrideCustomTheme";
    public static final String EXTRA_OVERRIDE_THEME = "overrideTheme";
    public static final String EXTRA_REAL_CLIENT_PACKAGE = "realClientPackage";
    public static final String EXTRA_SELECTED_ACCOUNT = "selectedAccount";
    public static final String EXTRA_SET_HMS_CORE_ACCOUNT = "setHmsCoreAccount";
    public static final int THEME_DEFAULT = 0;
    public static final int THEME_LIGHT = 1;

    private AccountPicker() {
    }

    public static Intent newChooseAccountIntent(Account account, ArrayList<Account> arrayList, String[] strArr, boolean z, String str, String str2, String[] strArr2, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction("com.huawei.hms.common.account.CHOOSE_ACCOUNT");
        try {
            intent.setPackage("com.huawei.hms");
        } catch (IllegalArgumentException unused) {
            HMSLog.m14112e("AccountPicker", "IllegalArgumentException when newChooseAccountIntent intent.setPackage");
        }
        intent.putExtra("allowableAccounts", arrayList);
        intent.putExtra("allowableAccountTypes", strArr);
        intent.putExtra("addAccountOptions", bundle);
        intent.putExtra("selectedAccount", account);
        intent.putExtra("alwaysPromptForAccount", z);
        intent.putExtra("descriptionTextOverride", str);
        intent.putExtra("authTokenType", str2);
        intent.putExtra("addAccountRequiredFeatures", strArr2);
        intent.putExtra("setHmsCoreAccount", false);
        intent.putExtra("overrideTheme", 0);
        intent.putExtra("overrideCustomTheme", 0);
        return intent;
    }
}
