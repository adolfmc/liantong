package com.xiaomi.mipush.sdk;

import android.os.Bundle;
import com.xiaomi.mipush.sdk.PushMessageHandler;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class MiPushCommandMessage implements PushMessageHandler.InterfaceC11085a {
    private static final String KEY_AUTO_MARK_PKGS = "autoMarkPkgs";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_COMMAND = "command";
    private static final String KEY_COMMAND_ARGUMENTS = "commandArguments";
    private static final String KEY_REASON = "reason";
    private static final String KEY_RESULT_CODE = "resultCode";
    private static final long serialVersionUID = 1;
    private List<String> autoMarkPkgs;
    private String category;
    private String command;
    private List<String> commandArguments;
    private String reason;
    private long resultCode;

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String str) {
        this.command = str;
    }

    public List<String> getCommandArguments() {
        return this.commandArguments;
    }

    public void setCommandArguments(List<String> list) {
        this.commandArguments = list;
    }

    public long getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(long j) {
        this.resultCode = j;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String str) {
        this.category = str;
    }

    public List<String> getAutoMarkPkgs() {
        return this.autoMarkPkgs;
    }

    public void setAutoMarkPkgs(List<String> list) {
        this.autoMarkPkgs = list;
    }

    public String toString() {
        return "command={" + this.command + "}, resultCode={" + this.resultCode + "}, reason={" + this.reason + "}, category={" + this.category + "}, commandArguments={" + this.commandArguments + "}";
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("command", this.command);
        bundle.putLong("resultCode", this.resultCode);
        bundle.putString(KEY_REASON, this.reason);
        List<String> list = this.commandArguments;
        if (list != null) {
            bundle.putStringArrayList(KEY_COMMAND_ARGUMENTS, (ArrayList) list);
        }
        bundle.putString(KEY_CATEGORY, this.category);
        List<String> list2 = this.autoMarkPkgs;
        if (list2 != null) {
            bundle.putStringArrayList(KEY_AUTO_MARK_PKGS, (ArrayList) list2);
        }
        return bundle;
    }

    public static MiPushCommandMessage fromBundle(Bundle bundle) {
        MiPushCommandMessage miPushCommandMessage = new MiPushCommandMessage();
        miPushCommandMessage.command = bundle.getString("command");
        miPushCommandMessage.resultCode = bundle.getLong("resultCode");
        miPushCommandMessage.reason = bundle.getString(KEY_REASON);
        miPushCommandMessage.commandArguments = bundle.getStringArrayList(KEY_COMMAND_ARGUMENTS);
        miPushCommandMessage.category = bundle.getString(KEY_CATEGORY);
        miPushCommandMessage.autoMarkPkgs = bundle.getStringArrayList(KEY_AUTO_MARK_PKGS);
        return miPushCommandMessage;
    }
}
