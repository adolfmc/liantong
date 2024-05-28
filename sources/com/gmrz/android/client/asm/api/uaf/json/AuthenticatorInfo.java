package com.gmrz.android.client.asm.api.uaf.json;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class AuthenticatorInfo {
    @Expose
    public static final short AAID_VALID_LENGTH = 9;
    @Expose
    public String aaid;
    @Expose
    public String assertionScheme;
    @Expose
    public long attachmentHint;
    @Expose
    public List<Short> attestationTypes;
    @Expose
    public short authenticationAlgorithm;
    @Expose
    public short authenticatorIndex;
    @Expose
    public String description;
    @Expose
    public boolean hasSettings;
    @Expose
    public String icon;
    @Expose
    public boolean isRoamingAuthenticator;
    @Expose
    public boolean isSecondFactorOnly;
    @Expose
    public boolean isUserEnrolled;
    @Expose
    public short keyProtection;
    @Expose
    public short matcherProtection;
    @Expose
    public short tcDisplay;
    @Expose
    public String tcDisplayContentType;
    @Expose
    public List<DisplayPNGCharacteristicsDescriptor> tcDisplayPNGCharacteristics;
    @Expose
    public String title;
    @Expose
    public long userVerification;
    @Expose
    public List<Version> asmVersions = new ArrayList();
    @Expose
    public List<String> supportedExtensionIDs = new ArrayList();

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface AttachmentHint {
        public static final long ATTACHMENT_HINT_BLUETOOTH = 32;
        public static final long ATTACHMENT_HINT_EXTERNAL = 2;
        public static final long ATTACHMENT_HINT_INTERNAL = 1;
        public static final long ATTACHMENT_HINT_NETWORK = 64;
        public static final long ATTACHMENT_HINT_NFC = 16;
        public static final long ATTACHMENT_HINT_READY = 128;
        public static final long ATTACHMENT_HINT_WIRED = 4;
        public static final long ATTACHMENT_HINT_WIRELESS = 8;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface KeyProtection {
        public static final short KEY_PROTECTION_HARDWARE = 2;
        public static final short KEY_PROTECTION_REMOTE_HANDLE = 16;
        public static final short KEY_PROTECTION_SECURE_ELEMENT = 8;
        public static final short KEY_PROTECTION_SOFTWARE = 1;
        public static final short KEY_PROTECTION_TEE = 4;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface MatcherProtection {
        public static final short MATCHER_PROTECTION_ON_CHIP = 4;
        public static final short MATCHER_PROTECTION_SOFTWARE = 1;
        public static final short MATCHER_PROTECTION_TEE = 2;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface ProtocolTags {
        public static final short TAG_AAID = 11787;
        public static final short TAG_ATTESTATION_BASIC_FULL = 15879;
        public static final short TAG_ATTESTATION_BASIC_SURROGATE = 15880;
        public static final short TAG_ATTESTATION_CERT = 11781;
        public static final short TAG_AUTHENTICATOR_INFO = 11790;
        public static final short TAG_AUTHENTICATOR_NONCE = 11791;
        public static final short TAG_COUNTERS = 11789;
        public static final short TAG_FINAL_CHALLENGE = 11786;
        public static final short TAG_KEYID = 11785;
        public static final short TAG_PUB_KEY = 11788;
        public static final short TAG_SIGNATURE = 11782;
        public static final short TAG_TRANSACTION_CONTENT = 11792;
        public static final short TAG_UAFV1_AUTH_ASSERTION = 15874;
        public static final short TAG_UAFV1_KRD = 15875;
        public static final short TAG_UAFV1_REG_ASSERTION = 15873;
        public static final short TAG_UAFV1_SIGNED_DATA = 15876;
        public static final short TAG_VENDOR_SPECIFIC_EXTENSION = 3839;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface TransactionConfirmationDisplay {
        public static final short TRANSACTION_CONFIRMATION_DISPLAY_ANY = 1;
        public static final short TRANSACTION_CONFIRMATION_DISPLAY_HARDWARE = 8;
        public static final short TRANSACTION_CONFIRMATION_DISPLAY_PRIVILEGED_SOFTWARE = 2;
        public static final short TRANSACTION_CONFIRMATION_DISPLAY_REMOTE = 16;
        public static final short TRANSACTION_CONFIRMATION_DISPLAY_TEE = 4;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface UserVerification {
        public static final long USER_VERIFY_ALL = 1024;
        public static final long USER_VERIFY_EYEPRINT = 64;
        public static final long USER_VERIFY_FACEPRINT = 16;
        public static final long USER_VERIFY_FINGERPRINT = 2;
        public static final long USER_VERIFY_HANDPRINT = 256;
        public static final long USER_VERIFY_LOCATION = 32;
        public static final long USER_VERIFY_NONE = 512;
        public static final long USER_VERIFY_PASSCODE = 4;
        public static final long USER_VERIFY_PATTERN = 128;
        public static final long USER_VERIFY_PRESENCE = 1;
        public static final long USER_VERIFY_VOICEPRINT = 8;
    }

    public String toString() {
        return "authenticatorIndex: " + ((int) this.authenticatorIndex) + "\n aiVersion: " + this.asmVersions + "\n aaid: " + this.aaid + "\n userVerification: " + this.userVerification + "\n keyProtection: " + ((int) this.keyProtection) + "\n matcherProtection: " + ((int) this.matcherProtection) + "\n attachmentHint: " + this.attachmentHint + "\n tcDisplay: " + ((int) this.tcDisplay) + "\n tcDisplayContentType: " + this.tcDisplayContentType + "\n tcDisplayPNGCharacteristics: " + this.tcDisplayPNGCharacteristics + "\n authenticationAlgorithm: " + ((int) this.authenticationAlgorithm) + "\n attestationTypes: " + this.attestationTypes + "\n scheme: " + this.assertionScheme + "\n isSecondFactorOnly: " + this.isSecondFactorOnly + "\n isRoamingAuthenticator: " + this.isRoamingAuthenticator + "\n isUserEnrolled: " + this.isUserEnrolled + "\n supportedExtensionIDs: " + this.supportedExtensionIDs + "\n title: " + this.title + "\n description: " + this.description + "\n hasSettings: " + this.hasSettings;
    }
}
