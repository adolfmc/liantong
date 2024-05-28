package com.android.uaf.asmcore;

import android.util.Base64;
import com.android.client.asm.core.uaf.AuthenticatorCore;
import com.android.client.asm.sdk.IMatcher;
import com.android.uaf.asmcore.AKProcessor;
import com.gmrz.android.client.asm.api.AsmError;
import com.gmrz.android.client.asm.api.AsmException;
import com.gmrz.android.client.asm.api.uaf.json.DisplayPNGCharacteristicsDescriptor;
import com.gmrz.android.client.asm.api.uaf.json.GetRegistrationsOut;
import com.gmrz.android.client.asm.api.uaf.json.Version;
import com.gmrz.android.client.utils.Charsets;
import com.gmrz.android.client.utils.Logger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class TLVCommandEncoder {
    public static final int BASE64_ENCODING = 11;
    public static final short LENGTH_SIZE = 2;
    private static final String TAG = "TLVCommandEncoder";
    public static final short TAG_AAID = 11787;
    public static final short TAG_AND_LENGTH_SIZE = 4;
    public static final short TAG_API_VERSION = 10254;
    public static final short TAG_APPID = 10244;
    public static final short TAG_ASSERTION_SCHEME = 10250;
    public static final short TAG_ATTESTATION_CERT = 11781;
    public static final short TAG_ATTESTATION_TYPE = 10247;
    public static final short TAG_AUTHENTICATOR_ASSERTION = 10255;
    public static final short TAG_AUTHENTICATOR_INDEX = 10253;
    public static final short TAG_AUTHENTICATOR_INFO = 14353;
    public static final short TAG_AUTHENTICATOR_METADATA = 10249;
    public static final short TAG_CUSTOM_CONFIG = 10482;
    public static final short TAG_EXTENSION_CRITICAL = 15889;
    public static final short TAG_EXTENSION_DATA = 11796;
    public static final short TAG_EXTENSION_ID = 11795;
    public static final short TAG_EXTENSION_NON_CRITICAL = 15890;
    public static final short TAG_FINAL_CHALLENGE = 11786;
    public static final short TAG_KEYHANDLE = 10241;
    public static final short TAG_KEYHANDLE_ACCESS_TOKEN = 10245;
    public static final short TAG_KEYID = 11785;
    public static final short TAG_NNL_AK_ADDITIONAL_INFO = 10481;
    public static final short TAG_NNL_AK_MATCHER_VERSION = 10745;
    public static final short TAG_SIZE = 2;
    public static final short TAG_STATUS_CODE = 10248;
    public static final short TAG_SUPPORTED_EXTENSION_ID = 10258;
    public static final short TAG_TC_DISPLAY_CONTENT_TYPE = 10252;
    public static final short TAG_TC_DISPLAY_PNG_CHARACTERISTICS = 10251;
    public static final short TAG_TC_TOKEN_CONTENT = 10486;
    public static final short TAG_TC_TOKEN_TYPE = 10485;
    public static final short TAG_TRANSACTION_CONFIRMATION_TOKEN = 14580;
    public static final short TAG_TRANSACTION_CONTENT = 10256;
    public static final short TAG_UAFV1_ADD_AUTHNR_CMD = 13320;
    public static final short TAG_UAFV1_ADD_AUTHNR_CMD_RESP = 13832;
    public static final short TAG_UAFV1_AUTH_ASSERTION = 15874;
    public static final short TAG_UAFV1_DEREGISTER_CMD = 13316;
    public static final short TAG_UAFV1_GETINFO_CMD = 13313;
    public static final short TAG_UAFV1_GET_REGISTRATIONS_CMD = 13321;
    public static final short TAG_UAFV1_GET_REGISTRATIONS_CMD_RESP = 13833;
    public static final short TAG_UAFV1_KRD = 15875;
    public static final short TAG_UAFV1_OPEN_SETTINGS_CMD = 13318;
    public static final short TAG_UAFV1_REGISTER_CMD = 13314;
    public static final short TAG_UAFV1_REG_ASSERTION = 15873;
    public static final short TAG_UAFV1_SIGNED_DATA = 15876;
    public static final short TAG_UAFV1_SIGN_CMD = 13315;
    public static final short TAG_USERNAME = 10246;
    public static final short TAG_USERNAME_AND_KEYHANDLE = 14338;
    public static final short TAG_USERVERIFY_TOKEN = 10243;
    public static final short TYPE_BUILTIN_ENROLL_UI = 8;
    public static final short TYPE_BUILTIN_SETTING_UI = 16;
    public static final short TYPE_EXPECT_APPID = 32;
    public static final short TYPE_KEYHANDLE_STORED_INSIDE = 4;
    public static final short TYPE_ROAMING_AUTHNR = 2;
    public static final short TYPE_SECOND_FACTOR_AUTHNR = 1;
    public static final byte TYPE_TCT_PLAINTEXT = 0;
    public static final byte TYPE_TCT_WRAPPED = 1;
    public static final short TYPE_USERENROLLED = 64;
    public static final short UINT16_SIZE = 2;
    public static final short UINT32_SIZE = 4;
    public static final short UINT8_SIZE = 1;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class Commands {
        public static final short ADD_AUTHNR = 13320;
        public static final short DEREGISTER = 13316;
        public static final short GET_INFO = 13313;
        public static final short GET_REGISTRATIONS = 13321;
        public static final short REGISTER = 13314;
        public static final short SETTINGS = 13318;
        public static final short SIGN = 13315;

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        public static class StatusCode {
            public static final short UAF_CMD_STATUS_ACCESS_DENIED = 2;
            public static final short UAF_CMD_STATUS_ATTESTATION_NOT_SUPPORTED = 7;
            public static final short UAF_CMD_STATUS_AUTHENTICATOR_EXISTS = 17;
            public static final short UAF_CMD_STATUS_CANNOT_RENDER_TRANSACTION_CONTENT = 4;
            public static final short UAF_CMD_STATUS_CMD_NOT_SUPPORTED = 6;
            public static final short UAF_CMD_STATUS_ERR_UNKNOWN = 1;
            public static final short UAF_CMD_STATUS_KEY_DISAPPEARED_PERMANENTLY = 9;
            public static final short UAF_CMD_STATUS_OK = 0;
            public static final short UAF_CMD_STATUS_USER_CANCELLED = 5;
            public static final short UAF_CMD_STATUS_USER_LOCKOUT = 16;
            public static final short UAF_CMD_STATUS_USER_NOT_ENROLLED = 3;
        }
    }

    public byte[] encode(AKProcessor.AKRequestParams aKRequestParams) throws Exception {
        switch (aKRequestParams.cmd) {
            case 13313:
                ByteBuffer allocate = ByteBuffer.allocate(4);
                allocate.order(ByteOrder.LITTLE_ENDIAN);
                allocate.putShort((short) 13313);
                allocate.putShort((short) 0);
                return allocate.array();
            case 13314:
            case 13315:
            case 13316:
                return prepareCommand(aKRequestParams.cmd, aKRequestParams);
            case 13317:
            case 13318:
            case 13319:
            default:
                throw new AsmException(AsmError.FAILURE, "Not supported AK command");
            case 13320:
                return prepareAddAuthnrCommand(aKRequestParams);
            case 13321:
                return prepareGetRegistrationsCommand(aKRequestParams);
        }
    }

    private byte[] prepareAddAuthnrCommand(AKProcessor.AKRequestParams aKRequestParams) {
        short size = (short) aKRequestParams.attestationCerts.size();
        short s = 0;
        for (byte[] bArr : aKRequestParams.attestationCerts) {
            s = (short) (s + bArr.length);
        }
        short length = (short) (aKRequestParams.aaid.length + 4 + (size * 4) + s + 4 + 4 + 1);
        ByteBuffer allocate = ByteBuffer.allocate(length);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.putShort((short) 13320);
        allocate.putShort((short) (length - 4));
        allocate.putShort((short) 11787);
        allocate.putShort((short) aKRequestParams.aaid.length);
        allocate.put(aKRequestParams.aaid);
        allocate.putShort((short) 10745);
        allocate.putShort((short) 1);
        allocate.put(aKRequestParams.matcherVersion);
        for (byte[] bArr2 : aKRequestParams.attestationCerts) {
            allocate.putShort((short) 11781);
            allocate.putShort((short) bArr2.length);
            allocate.put(bArr2);
        }
        return allocate.array();
    }

    private byte[] prepareGetRegistrationsCommand(AKProcessor.AKRequestParams aKRequestParams) {
        ByteBuffer allocate = ByteBuffer.allocate(9);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.putShort((short) 13321);
        allocate.putShort((short) 5);
        allocate.putShort((short) 10253);
        allocate.putShort((short) 1);
        allocate.put(aKRequestParams.authenticatorIndex);
        return allocate.array();
    }

    private byte[] prepareCommand(short s, AKProcessor.AKRequestParams aKRequestParams) {
        String str = TAG;
        Logger.m15895d(str, "Preparing command : " + Arrays.toString(aKRequestParams.appID));
        short s2 = 9;
        if (aKRequestParams.appID != null && aKRequestParams.appID.length > 0) {
            s2 = (short) (((short) (aKRequestParams.appID.length + 4)) + 9);
        }
        if (s == 13314 || s == 13315) {
            if (aKRequestParams.userVerifyToken != null && aKRequestParams.userVerifyToken.length > 0) {
                s2 = (short) (s2 + ((short) (aKRequestParams.userVerifyToken.length + 4)));
            }
            s2 = (short) (s2 + ((short) (aKRequestParams.finalChallenge.length + 4)));
        }
        if (s == 13314) {
            s2 = (short) (s2 + ((short) (aKRequestParams.userName.length + 4 + 4 + 2)));
        } else if (s == 13315) {
            for (byte[] bArr : aKRequestParams.keyHandles) {
                s2 = (short) (s2 + ((short) (bArr.length + 4)));
            }
            if (aKRequestParams.transaction != null) {
                s2 = (short) (s2 + ((short) (aKRequestParams.transaction.length + 4)));
            }
            if (aKRequestParams.transactionConfirmationToken != null) {
                s2 = (short) (s2 + ((short) (aKRequestParams.transactionConfirmationToken.length + 4)));
            }
        } else if (s == 13316) {
            s2 = (short) (s2 + ((short) (aKRequestParams.keyID.length + 4)));
            if (aKRequestParams.keyHandles != null) {
                for (byte[] bArr2 : aKRequestParams.keyHandles) {
                    s2 = (short) (s2 + ((short) (bArr2.length + 4)));
                }
            }
        }
        short length = (short) (s2 + ((short) (aKRequestParams.KHAccessToken.length + 4)));
        if (aKRequestParams.additionalAKArgument != null && aKRequestParams.additionalAKArgument.length > 0) {
            length = (short) (length + ((short) (aKRequestParams.additionalAKArgument.length + 4)));
        }
        if (aKRequestParams.extensions != null && aKRequestParams.extensions.size() > 0) {
            for (IMatcher.Extension extension : aKRequestParams.extensions) {
                length = (short) (length + ((short) (extension.data.length + 12 + extension.f4062id.getBytes(Charsets.utf8Charset).length)));
            }
        }
        ByteBuffer allocate = ByteBuffer.allocate(length);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.putShort(s);
        allocate.putShort((short) (length - 4));
        allocate.putShort((short) 10253);
        allocate.putShort((short) 1);
        allocate.put(aKRequestParams.authenticatorIndex);
        Logger.m15895d(TAG, "Preaparing command APPID");
        if (aKRequestParams.appID != null && aKRequestParams.appID.length > 0) {
            String str2 = TAG;
            Logger.m15895d(str2, "prepareCommand: appIDlen: " + aKRequestParams.appID.length + "  appID: " + getHex(aKRequestParams.appID));
            appendTag((short) 10244, allocate, aKRequestParams.appID);
        }
        if (s == 13314 || s == 13315) {
            appendTag((short) 11786, allocate, aKRequestParams.finalChallenge);
            if (s == 13314) {
                appendTag((short) 10246, allocate, aKRequestParams.userName);
                allocate.putShort((short) 10247);
                allocate.putShort((short) 2);
                allocate.putShort(aKRequestParams.attestationType);
            } else if (aKRequestParams.transaction != null) {
                appendTag((short) 10256, allocate, aKRequestParams.transaction);
            }
        } else if (s == 13316) {
            appendTag((short) 11785, allocate, aKRequestParams.keyID);
            if (aKRequestParams.keyHandles != null) {
                for (byte[] bArr3 : aKRequestParams.keyHandles) {
                    appendTag((short) 10241, allocate, bArr3);
                }
            }
        }
        appendTag((short) 10245, allocate, aKRequestParams.KHAccessToken);
        if ((s == 13314 || s == 13315) && aKRequestParams.userVerifyToken != null && aKRequestParams.userVerifyToken.length > 0) {
            appendTag((short) 10243, allocate, aKRequestParams.userVerifyToken);
        }
        if (s == 13315) {
            if (aKRequestParams.transactionConfirmationToken != null) {
                appendTag((short) 14580, allocate, aKRequestParams.transactionConfirmationToken);
            }
            for (byte[] bArr4 : aKRequestParams.keyHandles) {
                appendTag((short) 10241, allocate, bArr4);
            }
        }
        if (aKRequestParams.additionalAKArgument != null && aKRequestParams.additionalAKArgument.length > 0) {
            appendTag((short) 10482, allocate, aKRequestParams.additionalAKArgument);
        }
        if (aKRequestParams.extensions != null && aKRequestParams.extensions.size() > 0) {
            for (IMatcher.Extension extension2 : aKRequestParams.extensions) {
                byte[] bytes = extension2.f4062id.getBytes(Charsets.utf8Charset);
                short length2 = (short) (extension2.data.length + 8 + bytes.length);
                short s3 = 15890;
                if (extension2.fail_if_unknown) {
                    s3 = 15889;
                }
                allocate.putShort(s3);
                allocate.putShort(length2);
                appendTag((short) 11795, allocate, bytes);
                appendTag((short) 11796, allocate, extension2.data);
            }
        }
        return allocate.array();
    }

    private void appendTag(short s, ByteBuffer byteBuffer, byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return;
        }
        byteBuffer.putShort(s);
        byteBuffer.putShort((short) bArr.length);
        byteBuffer.put(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class TagLength {
        private final short length;
        private final short tag;

        public TagLength() {
            this.tag = (short) 0;
            this.length = (short) -1;
        }

        public TagLength(short s, short s2) {
            this.tag = s;
            this.length = s2;
        }

        public short Tag() {
            return this.tag;
        }

        public short Length() {
            return this.length;
        }
    }

    private TagLength readTagAndLength(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() < 4) {
            Logger.m15892e(TAG, "Invalid TLV: size < 4");
            return new TagLength();
        }
        TagLength tagLength = new TagLength(byteBuffer.getShort(), byteBuffer.getShort());
        String str = TAG;
        Logger.m15889i(str, "TAG:  " + String.format("%04X", Integer.valueOf(tagLength.Tag() & 65535)) + "  LENGTH: " + String.format("%04X", Integer.valueOf(tagLength.Length() & 65535)));
        if (tagLength.Length() < 0 || tagLength.Length() > byteBuffer.remaining()) {
            Logger.m15892e(TAG, "Invalid TLV: size too large.");
            return new TagLength();
        }
        return tagLength;
    }

    public AKProcessor.AKResponseParams decode(short s, byte[] bArr) {
        AKProcessor.AKResponseParams aKResponseParams = new AKProcessor.AKResponseParams();
        aKResponseParams.statusCode = (short) 1;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        TagLength readTagAndLength = readTagAndLength(wrap);
        String str = TAG;
        Logger.m15895d(str, "Response tag to decode " + String.format("%04X", Integer.valueOf(readTagAndLength.Tag() & 65535)));
        if (s + 512 != readTagAndLength.Tag()) {
            String str2 = TAG;
            Logger.m15892e(str2, "Response tag " + String.format("%04X", Integer.valueOf(readTagAndLength.Tag() & 65535)) + "does not match command tag.");
            return aKResponseParams;
        } else if (readTagAndLength.Length() != wrap.remaining()) {
            String str3 = TAG;
            Logger.m15892e(str3, "Response size (" + wrap.remaining() + ") does not match tlv length (" + ((int) readTagAndLength.Length()) + ").");
            return aKResponseParams;
        } else {
            try {
                short parseStatusCode = parseStatusCode(wrap);
                if (parseStatusCode != 0) {
                    String str4 = TAG;
                    Logger.m15892e(str4, "Error: status code = " + ((int) parseStatusCode));
                    aKResponseParams.statusCode = parseStatusCode;
                }
                switch (s) {
                    case 13313:
                        return parseGetInfo(aKResponseParams, wrap);
                    case 13314:
                        return parseRegister(aKResponseParams, wrap, parseStatusCode);
                    case 13315:
                        return parseSign(aKResponseParams, wrap, parseStatusCode);
                    case 13316:
                        aKResponseParams.statusCode = parseStatusCode;
                        return aKResponseParams;
                    case 13317:
                    case 13318:
                    case 13319:
                    default:
                        return aKResponseParams;
                    case 13320:
                        aKResponseParams.statusCode = parseStatusCode;
                        return aKResponseParams;
                    case 13321:
                        return parseGetRegistrations(aKResponseParams, wrap, parseStatusCode);
                }
            } catch (AsmException e) {
                Logger.m15891e(TAG, "Failed to parse StatusCode.", e);
                return aKResponseParams;
            }
        }
    }

    private short parseStatusCode(ByteBuffer byteBuffer) throws AsmException {
        TagLength readTagAndLength = readTagAndLength(byteBuffer);
        if (readTagAndLength.Length() < 0) {
            throw new AsmException(AsmError.FAILURE, "Invalid TAG_STATUS_CODE.");
        }
        if (readTagAndLength.Tag() != 10248) {
            throw new AsmException(AsmError.FAILURE, "TAG_STATUS_CODE is not found.");
        }
        if (readTagAndLength.Length() != 2) {
            throw new AsmException(AsmError.FAILURE, "Invalid status code size.");
        }
        return byteBuffer.getShort();
    }

    private AKProcessor.AKResponseParams parseGetInfo(AKProcessor.AKResponseParams aKResponseParams, ByteBuffer byteBuffer) {
        while (byteBuffer.hasRemaining()) {
            TagLength readTagAndLength = readTagAndLength(byteBuffer);
            if (readTagAndLength.Length() < 0) {
                Logger.m15892e(TAG, "Invalid GETINFO_CMD_RESPONSE.");
                return aKResponseParams;
            }
            short Tag = readTagAndLength.Tag();
            if (Tag != 10254) {
                if (Tag == 10481) {
                    byteBuffer.position(byteBuffer.position() + readTagAndLength.Length());
                } else if (Tag == 14353) {
                    AKProcessor.AkAuthnrInfo akAuthnrInfo = new AKProcessor.AkAuthnrInfo();
                    akAuthnrInfo.generalInfo.asmVersions.add(new Version(1, 0));
                    byte[] bArr = new byte[readTagAndLength.Length()];
                    byteBuffer.get(bArr);
                    ByteBuffer wrap = ByteBuffer.wrap(bArr);
                    wrap.order(ByteOrder.LITTLE_ENDIAN);
                    while (wrap.hasRemaining()) {
                        TagLength readTagAndLength2 = readTagAndLength(wrap);
                        if (readTagAndLength2.Length() < 0) {
                            Logger.m15892e(TAG, "Invalid TAG_AUTHENTICATOR_INFO.");
                            return aKResponseParams;
                        }
                        short Tag2 = readTagAndLength2.Tag();
                        if (Tag2 != 10247) {
                            if (Tag2 == 10258) {
                                byte[] bArr2 = new byte[readTagAndLength2.Length()];
                                wrap.get(bArr2);
                                try {
                                    akAuthnrInfo.generalInfo.supportedExtensionIDs.add(new String(bArr2, Charsets.utf8Charset));
                                } catch (Exception e) {
                                    Logger.m15891e(TAG, "Error encoding scheme!", e);
                                    return aKResponseParams;
                                }
                            } else if (Tag2 == 11787) {
                                byte[] bArr3 = new byte[readTagAndLength2.Length()];
                                wrap.get(bArr3);
                                if (readTagAndLength2.length != 9) {
                                    String str = TAG;
                                    Logger.m15892e(str, "Invalid AAID length: " + ((int) readTagAndLength2.length));
                                    return aKResponseParams;
                                }
                                try {
                                    akAuthnrInfo.generalInfo.aaid = new String(bArr3, Charsets.utf8Charset);
                                } catch (Exception e2) {
                                    Logger.m15891e(TAG, "Error encoding AAID!", e2);
                                    return aKResponseParams;
                                }
                            } else {
                                switch (Tag2) {
                                    case 10249:
                                        short s = wrap.getShort();
                                        String str2 = TAG;
                                        Logger.m15895d(str2, "authenticatorType = " + ((int) s));
                                        if ((s & 1) != 0) {
                                            akAuthnrInfo.generalInfo.isSecondFactorOnly = true;
                                        }
                                        if ((s & 2) != 0) {
                                            akAuthnrInfo.generalInfo.isRoamingAuthenticator = true;
                                        }
                                        if ((s & 4) != 0) {
                                            akAuthnrInfo.additionalInfo.keyHandleStoredInside = true;
                                        }
                                        if ((s & 8) != 0) {
                                            akAuthnrInfo.additionalInfo.builtinEnrollUI = true;
                                        }
                                        if ((s & 16) != 0) {
                                            akAuthnrInfo.additionalInfo.builtinSettingUI = true;
                                        }
                                        if ((s & 32) != 0) {
                                            akAuthnrInfo.additionalInfo.expectAPPID = true;
                                        }
                                        if ((s & 64) != 0) {
                                            akAuthnrInfo.generalInfo.isUserEnrolled = true;
                                        }
                                        String str3 = TAG;
                                        Logger.m15883w(str3, "ParseGetInfo APPID is expected:" + akAuthnrInfo.additionalInfo.expectAPPID + "  is RoamingAuthenticator: " + akAuthnrInfo.generalInfo.isRoamingAuthenticator);
                                        akAuthnrInfo.additionalInfo.maxKeyHandle = wrap.get();
                                        akAuthnrInfo.generalInfo.userVerification = (long) wrap.getInt();
                                        akAuthnrInfo.generalInfo.keyProtection = wrap.getShort();
                                        akAuthnrInfo.generalInfo.matcherProtection = wrap.getShort();
                                        akAuthnrInfo.generalInfo.tcDisplay = wrap.getShort();
                                        akAuthnrInfo.generalInfo.authenticationAlgorithm = wrap.getShort();
                                        if (akAuthnrInfo.generalInfo.authenticationAlgorithm <= 8) {
                                            break;
                                        } else {
                                            Logger.m15892e(TAG, "Unsupported hash algorithm!");
                                            return aKResponseParams;
                                        }
                                    case 10250:
                                        byte[] bArr4 = new byte[readTagAndLength2.Length()];
                                        wrap.get(bArr4);
                                        try {
                                            akAuthnrInfo.generalInfo.assertionScheme = new String(bArr4, Charsets.utf8Charset);
                                            break;
                                        } catch (Exception e3) {
                                            Logger.m15891e(TAG, "Error encoding scheme!", e3);
                                            return aKResponseParams;
                                        }
                                    case 10251:
                                        short Length = readTagAndLength2.Length();
                                        if (Length < 13) {
                                            Logger.m15892e(TAG, "Invalid display characteristics.");
                                            return aKResponseParams;
                                        }
                                        DisplayPNGCharacteristicsDescriptor displayPNGCharacteristicsDescriptor = new DisplayPNGCharacteristicsDescriptor(wrap.getInt() & (-1), wrap.getInt() & (-1), wrap.get(), wrap.get(), wrap.get(), wrap.get(), wrap.get());
                                        short s2 = (short) (Length - 13);
                                        if (s2 % 6 != 0) {
                                            Logger.m15892e(TAG, "Invalid PNG PALLETE.");
                                            return aKResponseParams;
                                        }
                                        while (s2 >= 6) {
                                            displayPNGCharacteristicsDescriptor.addRGBPalletteEntry(new DisplayPNGCharacteristicsDescriptor.rgbPalletteEntry(wrap.getShort(), wrap.getShort(), wrap.getShort()));
                                            s2 = (short) (s2 - 6);
                                        }
                                        if (akAuthnrInfo.generalInfo.tcDisplayPNGCharacteristics == null) {
                                            akAuthnrInfo.generalInfo.tcDisplayPNGCharacteristics = new ArrayList();
                                        }
                                        akAuthnrInfo.generalInfo.tcDisplayPNGCharacteristics.add(displayPNGCharacteristicsDescriptor);
                                        break;
                                    case 10252:
                                        byte[] bArr5 = new byte[readTagAndLength2.Length()];
                                        wrap.get(bArr5);
                                        try {
                                            akAuthnrInfo.generalInfo.tcDisplayContentType = new String(bArr5, Charsets.utf8Charset);
                                            break;
                                        } catch (Exception e4) {
                                            Logger.m15891e(TAG, "Error encoding tcDisplayContentType!", e4);
                                            return aKResponseParams;
                                        }
                                    case 10253:
                                        if (readTagAndLength2.Length() != 1) {
                                            Logger.m15892e(TAG, "TAG_AUTHENTICATOR_INDEX Length is not 1.");
                                            return aKResponseParams;
                                        }
                                        akAuthnrInfo.generalInfo.authenticatorIndex = wrap.get();
                                        break;
                                    default:
                                        String str4 = TAG;
                                        Logger.m15892e(str4, "Unknown tag , TAG:0x" + Integer.toHexString(readTagAndLength2.tag));
                                        return aKResponseParams;
                                }
                            }
                        } else if (readTagAndLength2.Length() != 2) {
                            Logger.m15892e(TAG, "TAG_ATTESTATION_TYPE Length is not 2.");
                            return aKResponseParams;
                        } else {
                            if (akAuthnrInfo.generalInfo.attestationTypes == null) {
                                akAuthnrInfo.generalInfo.attestationTypes = new ArrayList();
                            }
                            short s3 = wrap.getShort();
                            if (s3 != 15879 && s3 != 15880) {
                                String str5 = TAG;
                                Logger.m15892e(str5, "Invalid Attestation Type : " + ((int) s3));
                                return aKResponseParams;
                            }
                            akAuthnrInfo.generalInfo.attestationTypes.add(Short.valueOf(s3));
                        }
                    }
                    aKResponseParams.info.authenticators.add(akAuthnrInfo);
                } else {
                    String str6 = TAG;
                    Logger.m15892e(str6, "Unknown tag , TAG:0x" + Integer.toHexString(readTagAndLength.tag));
                    return aKResponseParams;
                }
            } else if (readTagAndLength.Length() != 1) {
                Logger.m15892e(TAG, "API Version Length is not 1.");
                return aKResponseParams;
            } else {
                aKResponseParams.info.apiVersion = byteBuffer.get();
            }
        }
        aKResponseParams.statusCode = (short) 0;
        return aKResponseParams;
    }

    private AKProcessor.AKResponseParams parseRegister(AKProcessor.AKResponseParams aKResponseParams, ByteBuffer byteBuffer, short s) {
        Logger.m15895d(TAG, "parseRegister");
        while (byteBuffer.hasRemaining()) {
            TagLength readTagAndLength = readTagAndLength(byteBuffer);
            if (readTagAndLength.Length() < 0) {
                Logger.m15892e(TAG, "Invalid REGISTER_CMD_RESPONSE.");
                return aKResponseParams;
            }
            short Tag = readTagAndLength.Tag();
            if (Tag == 10241) {
                byte[] bArr = new byte[readTagAndLength.Length()];
                byteBuffer.get(bArr);
                aKResponseParams.regToBeStored.keyHandle = Base64.encodeToString(bArr, 11);
            } else if (Tag == 10255) {
                byte[] bArr2 = new byte[readTagAndLength.Length()];
                byteBuffer.get(bArr2);
                String str = TAG;
                Logger.m15895d(str, "Assertion response from authenticator: " + getHex(bArr2));
                aKResponseParams.assertion = Base64.encodeToString(bArr2, 11);
                String str2 = TAG;
                Logger.m15895d(str2, "Assertion response from authenticator in base64: " + aKResponseParams.assertion);
                ByteBuffer wrap = ByteBuffer.wrap(bArr2);
                wrap.order(ByteOrder.LITTLE_ENDIAN);
                TagLength readTagAndLength2 = readTagAndLength(wrap);
                if (readTagAndLength2.Tag() != 15873 || readTagAndLength2.Length() < 0) {
                    Logger.m15892e(TAG, "Invalid TAG_UAFV1_REG_ASSERTION.");
                    return aKResponseParams;
                }
                TagLength readTagAndLength3 = readTagAndLength(wrap);
                if (readTagAndLength3.Tag() != 15875 || readTagAndLength3.Length() < 0) {
                    Logger.m15892e(TAG, "Invalid TAG_UAFV1_KRD.");
                    return aKResponseParams;
                }
                while (wrap.hasRemaining()) {
                    TagLength readTagAndLength4 = readTagAndLength(wrap);
                    if (readTagAndLength4.Length() < 0) {
                        Logger.m15892e(TAG, "Invalid tlv in registration assertion.");
                        return aKResponseParams;
                    } else if (readTagAndLength4.Tag() == 11785) {
                        byte[] bArr3 = new byte[readTagAndLength4.Length()];
                        wrap.get(bArr3);
                        aKResponseParams.regToBeStored.keyID = Base64.encodeToString(bArr3, 11);
                    } else {
                        wrap.position(wrap.position() + readTagAndLength4.Length());
                    }
                }
                continue;
            } else if (Tag == 10482) {
                aKResponseParams.additionalAKInfoToBeStored = new byte[readTagAndLength.Length()];
                byteBuffer.get(aKResponseParams.additionalAKInfoToBeStored);
            } else {
                String str3 = TAG;
                Logger.m15892e(str3, "Unknown tag , TAG:0x" + Integer.toHexString(readTagAndLength.tag));
                return aKResponseParams;
            }
        }
        aKResponseParams.statusCode = s;
        return aKResponseParams;
    }

    private AKProcessor.AKResponseParams parseSign(AKProcessor.AKResponseParams aKResponseParams, ByteBuffer byteBuffer, short s) {
        while (byteBuffer.hasRemaining()) {
            TagLength readTagAndLength = readTagAndLength(byteBuffer);
            if (readTagAndLength.Length() < 0) {
                Logger.m15892e(TAG, "Invalid SIGN_CMD_RESPONSE.");
                return aKResponseParams;
            }
            short Tag = readTagAndLength.Tag();
            if (Tag == 10255) {
                byte[] bArr = new byte[readTagAndLength.Length()];
                byteBuffer.get(bArr);
                String str = TAG;
                Logger.m15895d(str, "Assertion response from authenticator: " + getHex(bArr));
                aKResponseParams.assertion = Base64.encodeToString(bArr, 11);
                String str2 = TAG;
                Logger.m15895d(str2, "Assertion response from authenticator in base64: " + aKResponseParams.assertion);
                String str3 = TAG;
                Logger.m15895d(str3, "Assertion decoded base64: " + getHex(Base64.decode(aKResponseParams.assertion, 11)));
            } else if (Tag == 10482) {
                aKResponseParams.additionalAKInfoToBeStored = new byte[readTagAndLength.Length()];
                byteBuffer.get(aKResponseParams.additionalAKInfoToBeStored);
            } else if (Tag == 14338) {
                byte[] bArr2 = new byte[readTagAndLength.Length()];
                byteBuffer.get(bArr2);
                ByteBuffer wrap = ByteBuffer.wrap(bArr2);
                wrap.order(ByteOrder.LITTLE_ENDIAN);
                String str4 = "";
                String str5 = "";
                while (wrap.hasRemaining()) {
                    TagLength readTagAndLength2 = readTagAndLength(wrap);
                    if (readTagAndLength2.Length() < 0) {
                        Logger.m15892e(TAG, "Invalid TAG_USERNAME_AND_KEYHANDLE.");
                        return aKResponseParams;
                    } else if (10246 == readTagAndLength2.Tag()) {
                        byte[] bArr3 = new byte[readTagAndLength2.Length()];
                        wrap.get(bArr3);
                        str4 = new String(bArr3);
                    } else if (10241 != readTagAndLength2.Tag()) {
                        Logger.m15892e(TAG, "Unknown tag in TAG_USERNAME_AND_KEYHANDLE.");
                        return aKResponseParams;
                    } else {
                        byte[] bArr4 = new byte[readTagAndLength2.Length()];
                        wrap.get(bArr4);
                        str5 = Base64.encodeToString(bArr4, 11);
                    }
                }
                aKResponseParams.usernames.add(new AuthenticatorCore.Username(str4, str5, 0L));
            } else {
                String str6 = TAG;
                Logger.m15892e(str6, "Unknown tag , TAG:0x" + Integer.toHexString(readTagAndLength.tag));
                return aKResponseParams;
            }
        }
        aKResponseParams.statusCode = s;
        return aKResponseParams;
    }

    private AKProcessor.AKResponseParams parseGetRegistrations(AKProcessor.AKResponseParams aKResponseParams, ByteBuffer byteBuffer, short s) {
        Logger.m15883w(TAG, "parseGetRegistrations");
        GetRegistrationsOut.AppRegistration appRegistration = null;
        boolean z = false;
        while (byteBuffer.hasRemaining()) {
            TagLength readTagAndLength = readTagAndLength(byteBuffer);
            if (readTagAndLength.Length() < 0) {
                Logger.m15892e(TAG, "Invalid GETREGISTRATIONS_CMD_RESPONSE.");
                return aKResponseParams;
            }
            short Tag = readTagAndLength.Tag();
            if (Tag == 10244) {
                if (appRegistration != null) {
                    aKResponseParams.appRegistrations.add(appRegistration);
                }
                appRegistration = new GetRegistrationsOut.AppRegistration();
                byte[] bArr = new byte[readTagAndLength.Length()];
                byteBuffer.get(bArr);
                String str = TAG;
                Logger.m15895d(str, "appID from GetRegistrations cmd: " + getHex(bArr));
                String str2 = TAG;
                Logger.m15895d(str2, "appID from GetRegistrations cmd with Base64: " + Base64.encodeToString(bArr, 11));
                appRegistration.appID = new String(bArr, Charsets.utf8Charset);
                String str3 = TAG;
                Logger.m15895d(str3, "appID from GetRegistrations cmd with Base64: " + appRegistration.appID);
                z = true;
            } else if (Tag != 11785) {
                String str4 = TAG;
                Logger.m15883w(str4, "Unknown tag:  , TAG:0x" + Integer.toHexString(readTagAndLength.tag));
                byteBuffer.position(byteBuffer.position() + readTagAndLength.Length());
            } else if (!z) {
                String str5 = TAG;
                Logger.m15883w(str5, "Invalid format, No AppID found: " + ((int) readTagAndLength.Tag()));
                aKResponseParams.statusCode = (short) 1;
                return aKResponseParams;
            } else {
                byte[] bArr2 = new byte[readTagAndLength.Length()];
                byteBuffer.get(bArr2);
                appRegistration.keyIDs.add(Base64.encodeToString(bArr2, 11));
            }
        }
        if (appRegistration != null) {
            aKResponseParams.appRegistrations.add(appRegistration);
        }
        aKResponseParams.statusCode = s;
        return aKResponseParams;
    }

    public static String getHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append("0123456789ABCDEF".charAt((b & 240) >> 4));
            sb.append("0123456789ABCDEF".charAt(b & 15));
        }
        return sb.toString();
    }
}
