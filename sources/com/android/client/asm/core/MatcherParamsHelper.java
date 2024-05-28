package com.android.client.asm.core;

import android.annotation.SuppressLint;
import android.util.Base64;
import com.android.client.asm.sdk.IAuthenticatorDescriptor;
import com.android.client.asm.sdk.IMatcher;
import com.android.client.asm.sdk.UVTMatcherInParams;
import com.gmrz.android.client.asm.api.AsmError;
import com.gmrz.android.client.asm.api.AsmException;
import com.gmrz.android.client.utils.Charsets;
import java.security.InvalidParameterException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MatcherParamsHelper {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum AuthenticatorMatcherType {
        MATCHER_TYPE_AKMANAGED,
        MATCHER_TYPE_UVT,
        MATCHER_TYPE_UNKNOWN
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum MatcherOpEnum {
        OP_ID_AUTHENTICATE,
        OP_ID_ENROLL
    }

    public static IMatcher.MatcherInParams createMatcherInParams(MatcherOpEnum matcherOpEnum, AuthenticatorMatcherType authenticatorMatcherType, GetInfoParams getInfoParams, byte[] bArr, IMatcher iMatcher, IMatcher.MatcherUI matcherUI, IMatcher.MatcherInParams.IAntiHammeringCallback iAntiHammeringCallback) throws AsmException {
        if (authenticatorMatcherType == AuthenticatorMatcherType.MATCHER_TYPE_AKMANAGED) {
            return buildBaseMatcherInParams(getInfoParams, bArr, matcherUI, iAntiHammeringCallback);
        }
        if (iMatcher.getMatcherDefinedParamsClassList().getMatcherInParamsClass() == UVTMatcherInParams.class) {
            return m20459a(getInfoParams, bArr, iAntiHammeringCallback);
        }
        throw new InvalidParameterException("UnSupported MatcherInParams class " + iMatcher.getMatcherDefinedParamsClassList().getMatcherInParamsClass());
    }

    @SuppressLint({"NewApi"})
    public static IMatcher.MatcherInParams buildBaseMatcherInParams(GetInfoParams getInfoParams, byte[] bArr, IMatcher.MatcherUI matcherUI, IMatcher.MatcherInParams.IAntiHammeringCallback iAntiHammeringCallback) throws AsmException {
        String str;
        if (getInfoParams == null) {
            throw new InvalidParameterException("GetInfoParams is NULL");
        }
        String transText = getInfoParams.getTransText();
        String customUIJson = getInfoParams.getCustomUIJson();
        if (transText != null) {
            try {
                str = new String(Base64.decode(transText, 8), Charsets.utf8Charset);
            } catch (IllegalArgumentException unused) {
                throw new AsmException(AsmError.FAILURE, "Error while building base matcher.");
            }
        } else {
            str = transText;
        }
        return new IMatcher.MatcherInParams(customUIJson, str, bArr, matcherUI, iAntiHammeringCallback);
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    private static UVTMatcherInParams m20459a(GetInfoParams getInfoParams, byte[] bArr, IMatcher.MatcherInParams.IAntiHammeringCallback iAntiHammeringCallback) throws AsmException {
        String str;
        String str2 = null;
        if (getInfoParams != null) {
            str2 = getInfoParams.getCustomUIJson();
            str = getInfoParams.getTransText();
        } else {
            str = null;
        }
        if (str != null) {
            try {
                str = new String(Base64.decode(str, 0), Charsets.utf8Charset);
            } catch (IllegalArgumentException unused) {
                throw new AsmException(AsmError.FAILURE, "Error building UVT matcher.");
            }
        }
        return new UVTMatcherInParams().setAntihammeringCallback(iAntiHammeringCallback).setCustomUI(str2).setFinalChallenge(bArr).setTransText(str);
    }

    public static IMatcher.MatcherSettingsInParams createMatcherManageInParams(IMatcher iMatcher, IMatcher.MatcherInParams.IAntiHammeringCallback iAntiHammeringCallback) {
        Class<? extends IMatcher.MatcherSettingsInParams> matcherManageInParamsClass = iMatcher.getMatcherDefinedParamsClassList().getMatcherManageInParamsClass();
        if (matcherManageInParamsClass == IMatcher.MatcherSettingsInParams.class) {
            return new IMatcher.MatcherSettingsInParams();
        }
        throw new InvalidParameterException("Unsupported ManageInparams class : " + matcherManageInParamsClass);
    }

    public static AuthenticatorMatcherType getMatcherType(IAuthenticatorDescriptor iAuthenticatorDescriptor, IMatcher iMatcher) {
        if (iAuthenticatorDescriptor.isAKManagedMatcher() || iAuthenticatorDescriptor.getUAFDescriptor().isRoamingAuthenticator()) {
            return AuthenticatorMatcherType.MATCHER_TYPE_AKMANAGED;
        }
        if (iMatcher.getMatcherDefinedParamsClassList().getMatcherInParamsClass() == UVTMatcherInParams.class) {
            return AuthenticatorMatcherType.MATCHER_TYPE_UVT;
        }
        return AuthenticatorMatcherType.MATCHER_TYPE_UNKNOWN;
    }
}
