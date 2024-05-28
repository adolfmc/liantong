package com.android.client.asm.sdk;

import java.util.ArrayList;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IAuthenticatorDescriptor {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class AAIDInfo {
        public String label = "";
        public String aaid = "";
        public ArrayList<byte[]> certificateChain = null;
        public boolean autoConfigure = false;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface IOSTPDescriptor {
        String getAuthDBOldFileName();

        String getAuthFactor();

        String getAuthInstallMethod();

        ArrayList<String> getAuthSchemes();

        String getAuthSecType();

        ArrayList<String> getAuthSuites();

        ArrayList<String> getRegModes();

        ArrayList<String> getRegSchemes();

        String getVersion();

        Boolean isSecureDisplaySupported();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface IUAFDescriptor {
        long getAttachmentHint();

        short getTcDisplay();

        String getTcDisplayContentType();

        long getUserVerification();

        boolean isRoamingAuthenticator();

        boolean isSecondFactorOnly();
    }

    Map<String, AAIDInfo> getAAIDInfo();

    Class<? extends IAKSelector> getAuthenticatorSelectorClass();

    int getDescription();

    int getIcon();

    Class<? extends IMatcher> getMatcherClass();

    byte getMatcherVersion();

    IOSTPDescriptor getOSTPDescriptor();

    int getTitle();

    IUAFDescriptor getUAFDescriptor();

    boolean hasSettings();

    boolean isAKManagedMatcher();

    boolean isTransactionShownByAuthUI();
}
