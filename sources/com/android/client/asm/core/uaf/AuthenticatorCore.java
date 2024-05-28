package com.android.client.asm.core.uaf;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.android.client.asm.core.GetInfoParams;
import com.android.client.asm.core.MatcherAssertionException;
import com.android.client.asm.core.MatcherParamsHelper;
import com.android.client.asm.core.shared.DescriptorLoader;
import com.android.client.asm.sdk.IAKDigestMethod;
import com.android.client.asm.sdk.IAKSelector;
import com.android.client.asm.sdk.IAuthenticatorDescriptor;
import com.android.client.asm.sdk.IMatcher;
import com.android.client.asm.sdk.ProtocolType;
import com.android.client.asm.sdk.UVTMatcherInParams;
import com.android.client.asm.sdk.UVTMatcherOutParams;
import com.android.uaf.asmcore.AKProcessor;
import com.android.uaf.asmcore.AuthenticatorDatabase;
import com.android.uaf.asmcore.AuthenticatorDatabaseFactory;
import com.android.uaf.asmcore.SelectFromDialogActivity;
import com.android.uaf.asmcore.TLVCommandEncoder;
import com.fido.android.framework.p197tm.core.prov.CryptoModule;
import com.gmrz.android.client.asm.api.AsmError;
import com.gmrz.android.client.asm.api.AsmException;
import com.gmrz.android.client.asm.api.uaf.json.AuthenticatorInfo;
import com.gmrz.android.client.asm.api.uaf.json.DeregisterIn;
import com.gmrz.android.client.asm.api.uaf.json.DisplayPNGCharacteristicsDescriptor;
import com.gmrz.android.client.asm.api.uaf.json.Extension;
import com.gmrz.android.client.asm.api.uaf.json.GetRegistrationsOut;
import com.gmrz.android.client.asm.api.uaf.json.RegisterIn;
import com.gmrz.android.client.asm.api.uaf.json.RegisterOut;
import com.gmrz.android.client.utils.ActivityStarter;
import com.gmrz.android.client.utils.Charsets;
import com.gmrz.android.client.utils.Logger;
import com.utils.MetaDataUtils;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AuthenticatorCore {
    private static final String TAG = "AuthenticatorCore";
    protected byte[] mASMToken;
    protected AKProcessor mAkProcessor;
    private AntiHammering mAntihammering;
    protected IAuthenticatorDescriptor mAuthDx;
    private IMatcher mAuthMatcher;
    protected AuthenticatorDatabase mAuthenticatorDb;
    protected byte[] mCallerID;
    protected Context mContext;
    protected IAKDigestMethod mDigestMethod;
    private MatcherParamsHelper mMatcherParamsHelper;
    protected byte[] mPersonaID;
    protected IAuthenticatorDescriptor.IUAFDescriptor mUafAuthDx;
    protected TCDisplayResponse tTCDisplayResponse;
    protected CryptoModule mCryptoModule = null;
    protected AKProcessor.AkAuthnrInfo mInfo = new AKProcessor.AkAuthnrInfo();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class Response {
        public Object data;
        public List<Extension> exts;
        public int statusCode;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class TCDisplayResponse {
        public byte[] additionalAKArgument;
        public int statusCode;
        public byte[] tcToken;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class UserVerifyResponse {
        public byte[] additionalAKArgument;
        public List<IMatcher.Extension> extensions;
        public IMatcher.MatcherInParams matcherInParams = null;
        public MatcherParamsHelper.AuthenticatorMatcherType matcherType;
        public short statusCode;
        public String userID;
        public byte[] userVerifyToken;
    }

    private void getPersonaID() {
    }

    public AuthenticatorCore(IAuthenticatorDescriptor iAuthenticatorDescriptor) throws AsmException {
        this.mAuthDx = iAuthenticatorDescriptor;
        this.mUafAuthDx = this.mAuthDx.getUAFDescriptor();
        if (this.mUafAuthDx == null) {
            Logger.m15892e("AuthenticatorCore", "UAF specific auth descriptor is not provided.");
            throw new AsmException(AsmError.FAILURE);
        } else {
            new HashMap().put(UVTMatcherInParams.class, MatcherParamsHelper.AuthenticatorMatcherType.MATCHER_TYPE_UVT);
        }
    }

    public void initialize(Context context, String str) throws AsmException {
        Logger.m15889i("AuthenticatorCore", "initialize");
        assertNotAnActivityContext(context);
        this.mContext = context;
        if (!this.mAuthDx.isAKManagedMatcher() && !this.mUafAuthDx.isRoamingAuthenticator()) {
            this.mAuthMatcher = DescriptorLoader.loadAuthenticatorUIFromClassName(this.mAuthDx.getMatcherClass(), context, ProtocolType.UAF);
            MatcherParamsHelper.AuthenticatorMatcherType matcherType = MatcherParamsHelper.getMatcherType(this.mAuthDx, this.mAuthMatcher);
            if (matcherType != MatcherParamsHelper.AuthenticatorMatcherType.MATCHER_TYPE_UVT) {
                AsmError asmError = AsmError.FAILURE;
                throw new AsmException(asmError, "Unsupported MatcherType. matcherType = " + matcherType.toString());
            }
        } else {
            Logger.m15895d("AuthenticatorCore", "Is AK Managed Matcher.");
            this.mAuthMatcher = DescriptorLoader.loadAuthenticatorUIFromClassName(this.mAuthDx.getMatcherClass(), context, ProtocolType.UAF);
        }
        IAKSelector loadAKSelectorFromClassName = DescriptorLoader.loadAKSelectorFromClassName(this.mAuthMatcher, this.mAuthDx, context, ProtocolType.UAF);
        if (loadAKSelectorFromClassName == null || loadAKSelectorFromClassName.getAAIDInfo() == null) {
            Log.wtf("AuthenticatorCore", "**** akSelector is null ***** ");
            throw new AsmException(AsmError.FAILURE, "LoadAKSelector failed. No AAID Selected");
        }
        String str2 = loadAKSelectorFromClassName.getAAIDInfo().aaid;
        Logger.m15889i("AuthenticatorCore", "initialize( filePath = " + str2 + " )");
        this.mCryptoModule = new CryptoModule(str2, context);
        this.mCallerID = getCallerID(context, str);
        this.mContext = context;
        this.mDigestMethod = loadAKSelectorFromClassName.getAuthenticatorKernel().getDigestMethod();
        IAuthenticatorDescriptor.AAIDInfo aAIDInfo = loadAKSelectorFromClassName.getAAIDInfo();
        this.mAkProcessor = new AKProcessor(loadAKSelectorFromClassName.getAuthenticatorKernel());
        try {
            this.mInfo = this.mAkProcessor.getAKInfo(loadAKSelectorFromClassName.getAAIDInfo().aaid);
        } catch (AsmException e) {
            Logger.m15891e("AuthenticatorCore", "Failed to Query the AK and get AK information.", e);
        }
        this.mAuthenticatorDb = AuthenticatorDatabaseFactory.createAuthenticatorStore(this.mUafAuthDx.isRoamingAuthenticator(), str2, this.mCryptoModule, context, loadAKSelectorFromClassName.getAuthenticatorKernel(), this.mInfo.generalInfo.authenticatorIndex);
        getPersonaID();
        getASMToken();
        if (!this.mUafAuthDx.isRoamingAuthenticator()) {
            this.mAntihammering = new AntiHammering(this.mAuthenticatorDb, null, true);
        }
        this.mInfo.generalInfo.userVerification = this.mUafAuthDx.getUserVerification();
        this.mInfo.generalInfo.isSecondFactorOnly = this.mUafAuthDx.isSecondFactorOnly();
        this.mInfo.generalInfo.isRoamingAuthenticator = this.mUafAuthDx.isRoamingAuthenticator();
        this.mInfo.generalInfo.aaid = aAIDInfo.aaid;
        this.mInfo.generalInfo.tcDisplay = this.mUafAuthDx.getTcDisplay();
        this.mInfo.generalInfo.tcDisplayContentType = this.mUafAuthDx.getTcDisplayContentType();
        this.mInfo.generalInfo.tcDisplayPNGCharacteristics = new ArrayList();
        this.mInfo.generalInfo.tcDisplayPNGCharacteristics.add(DisplayPNGCharacteristicsDescriptor.getDefaultPNGDescriptor());
        this.mInfo.generalInfo.attachmentHint = this.mUafAuthDx.getAttachmentHint();
        this.mInfo.generalInfo.hasSettings = this.mAuthDx.hasSettings();
        this.mInfo.generalInfo.isUserEnrolled = isUserEnrolled();
        Bitmap decodeResource = BitmapFactory.decodeResource(this.mContext.getResources(), this.mAuthDx.getIcon());
        if (decodeResource != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            decodeResource.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            AuthenticatorInfo authenticatorInfo = this.mInfo.generalInfo;
            authenticatorInfo.icon = "data:image/png;base64," + Base64.encodeToString(byteArray, 2);
        }
        this.mInfo.generalInfo.description = this.mContext.getString(this.mAuthDx.getDescription());
        AuthenticatorInfo authenticatorInfo2 = this.mInfo.generalInfo;
        authenticatorInfo2.title = "UAF " + this.mContext.getString(this.mAuthDx.getTitle());
        Logger.m15895d("AuthenticatorCore", "ASM info:\n" + this.mInfo.generalInfo);
        if (this.mUafAuthDx.isRoamingAuthenticator()) {
            return;
        }
        this.mAuthenticatorDb.validateUserRegistrations(this.mAuthMatcher);
    }

    public byte[] getCallerID(Context context, String str) {
        byte[] bArr = null;
        try {
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(str, 64).signatures;
            if (MetaDataUtils.getHashSaltType(this.mContext) != MetaDataUtils.HASH_SALT_TYPE.NONE) {
                Log.wtf("AuthenticatorCore", "** CallerId calculate add salt **");
                if (signatureArr != null && signatureArr.length != 0) {
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                    messageDigest.update(byteMerger(signatureArr[0].toByteArray(), context.getPackageName().getBytes()));
                    bArr = messageDigest.digest();
                }
            } else {
                Log.wtf("AuthenticatorCore", "** CallerId calculate not add salt **");
                if (signatureArr != null && signatureArr.length != 0) {
                    MessageDigest messageDigest2 = MessageDigest.getInstance("SHA256");
                    messageDigest2.update(signatureArr[0].toByteArray());
                    bArr = messageDigest2.digest();
                }
            }
        } catch (Exception e) {
            Logger.m15891e("AuthenticatorCore", "Failed to get callerId.", e);
        }
        return bArr;
    }

    public AuthenticatorInfo getInfo() {
        return this.mInfo.generalInfo;
    }

    private void getResult(IMatcher.RESULT result) throws AsmException {
        if (result == IMatcher.RESULT.CANCEL) {
            throw new AsmException(AsmError.CANCELED);
        }
        if (result == IMatcher.RESULT.TOOMANYATTEMPTS) {
            throw new AsmException(AsmError.FAILURE).setDetails("{\"errorDetails\" : \"Too many failed attempts.\"}");
        }
        if (result == IMatcher.RESULT.CHANGE_AUTHENTICATOR) {
            throw new AsmException(AsmError.CHANGE_TOKEN).setDetails("{\"errorDetails\" : \"change authenticator.\"}");
        }
        if (result == IMatcher.RESULT.CHANGE_TRANSACTION) {
            throw new AsmException(AsmError.UPDATE).setDetails("{\"errorDetails\" : \"User is hanging trans during auth\"}");
        }
        if (result == IMatcher.RESULT.ERRORAUTH) {
            throw new AsmException(AsmError.FAILURE).setDetails("{\"errorDetails\" : \"unspecified failure.\"}");
        }
        if (result == IMatcher.RESULT.MISMATCH) {
            throw new AsmException(AsmError.NO_MATCH).setDetails("{\"errorDetails\" : \"Mismatch.\"}");
        }
        if (result == IMatcher.RESULT.TIMEOUT) {
            throw new AsmException(AsmError.CANCELED).setDetails("{\"errorDetails\" : \"Timeout.\"}");
        }
        if (result == IMatcher.RESULT.USER_LOCKOUT) {
            throw new AsmException(AsmError.USER_LOCKOUT).setDetails("{\"errorDetails\" : \"User Lockout due to too many failed attempts.\"}");
        }
        if (result == IMatcher.RESULT.FINGER_SET_CHANGE) {
            throw new AsmException(AsmError.KEY_DISAPPEARED_PERMANENTLY).setDetails("{\"errorDetails\" : \"User finger have change.\"}");
        }
    }

    private void validateResult(IMatcher.MatcherOutParams matcherOutParams) throws AsmException {
        getResult(matcherOutParams.getMatchResult());
    }

    private AKProcessor.AKResponseParams addAuthenticator(IAuthenticatorDescriptor.AAIDInfo aAIDInfo) throws AsmException {
        AKProcessor.AKRequestParams matcherVersion = new AKProcessor.AKRequestParams().setCmd((short) 13320).setAaid(aAIDInfo.aaid.getBytes(Charsets.utf8Charset)).setMatcherVersion(this.mAuthDx.getMatcherVersion());
        int size = aAIDInfo.certificateChain == null ? 0 : aAIDInfo.certificateChain.size();
        matcherVersion.setAttestationCerts(new ArrayList(size));
        for (int i = 0; i < size; i++) {
            matcherVersion.attestationCerts.add(aAIDInfo.certificateChain.get(i));
        }
        return this.mAkProcessor.processAK(matcherVersion);
    }

    public UserVerifyResponse enrollUser(byte[] bArr, IMatcher.MatcherUI matcherUI) throws AsmException {
        GetInfoParams getInfoParams;
        Logger.m15895d("AuthenticatorCore", "**!!** enrollUser 注册 **!!**");
        UserVerifyResponse userVerifyResponse = new UserVerifyResponse();
        userVerifyResponse.statusCode = (short) 2;
        if (matcherUI != null && !TextUtils.isEmpty(matcherUI.getGestureUVT())) {
            getInfoParams = new GetInfoParams(matcherUI.toJson());
        } else {
            getInfoParams = new GetInfoParams();
        }
        MatcherParamsHelper.AuthenticatorMatcherType matcherType = MatcherParamsHelper.getMatcherType(this.mAuthDx, this.mAuthMatcher);
        IMatcher.MatcherInParams createMatcherInParams = MatcherParamsHelper.createMatcherInParams(MatcherParamsHelper.MatcherOpEnum.OP_ID_ENROLL, matcherType, getInfoParams, bArr, this.mAuthMatcher, matcherUI, this.mAntihammering);
        userVerifyResponse.matcherInParams = createMatcherInParams;
        userVerifyResponse.matcherType = matcherType;
        if (matcherType != MatcherParamsHelper.AuthenticatorMatcherType.MATCHER_TYPE_AKMANAGED && !this.mUafAuthDx.isRoamingAuthenticator()) {
            Logger.m15895d("AuthenticatorCore", "**!!** enrollUser 注册 - 不是AK管理的Matcher **!!**");
            IMatcher.MatcherOutParams register = this.mAuthMatcher.register(createMatcherInParams, null);
            validateResult(register);
            userVerifyResponse.statusCode = (short) 0;
            userVerifyResponse.additionalAKArgument = null;
            userVerifyResponse.extensions = register.getExtensions();
            if (register.getUserID() != null) {
                userVerifyResponse.userID = Base64.encodeToString(register.getUserID(), 0);
            }
            if (matcherType == MatcherParamsHelper.AuthenticatorMatcherType.MATCHER_TYPE_UVT) {
                userVerifyResponse.userVerifyToken = createFakeUVT(this.mInfo.generalInfo.aaid, bArr, ((UVTMatcherOutParams) register).getUVT());
            }
        } else {
            Logger.m15895d("AuthenticatorCore", "**!!** enrollUser 注册 - 是AK管理的Matcher **!!**");
            userVerifyResponse.statusCode = (short) 0;
            userVerifyResponse.userVerifyToken = createFakeUVT(this.mInfo.generalInfo.aaid, bArr, "12345678123456781234567812345678".getBytes());
        }
        return userVerifyResponse;
    }

    public UserVerifyResponse verifyUser(String str, String str2, String str3, IMatcher.MatcherUI matcherUI, byte[] bArr, boolean z) throws AsmException {
        GetInfoParams getInfoParams;
        IMatcher.MatcherOutParams authenticate;
        Logger.m15895d("AuthenticatorCore", "**!!** verifyUser 认证 **!!**");
        UserVerifyResponse userVerifyResponse = new UserVerifyResponse();
        userVerifyResponse.statusCode = (short) 2;
        if (str2 != null) {
            this.tTCDisplayResponse = new TCDisplayResponse();
            this.tTCDisplayResponse.statusCode = 0;
            byte[] decode = Base64.decode(str2, 11);
            if (decode.length == 0) {
                Logger.m15892e("AuthenticatorCore", "Nothing to display");
                this.tTCDisplayResponse.statusCode = 1;
                return userVerifyResponse;
            }
            this.tTCDisplayResponse.tcToken = prepareTCToken(decode, bArr);
        }
        if (matcherUI != null && !TextUtils.isEmpty(matcherUI.getGestureUVT())) {
            getInfoParams = new GetInfoParams(str2, null, matcherUI.toJson());
        } else {
            getInfoParams = new GetInfoParams(str2, null, null);
        }
        MatcherParamsHelper.AuthenticatorMatcherType matcherType = MatcherParamsHelper.getMatcherType(this.mAuthDx, this.mAuthMatcher);
        IMatcher.MatcherInParams createMatcherInParams = MatcherParamsHelper.createMatcherInParams(MatcherParamsHelper.MatcherOpEnum.OP_ID_AUTHENTICATE, matcherType, getInfoParams, bArr, this.mAuthMatcher, matcherUI, this.mAntihammering);
        userVerifyResponse.matcherInParams = createMatcherInParams;
        userVerifyResponse.matcherType = matcherType;
        if (matcherType != MatcherParamsHelper.AuthenticatorMatcherType.MATCHER_TYPE_AKMANAGED && !this.mUafAuthDx.isRoamingAuthenticator()) {
            Logger.m15895d("AuthenticatorCore", "**!!** verifyUser 认证 - 不是AK管理的Matcher **!!**");
            if (z) {
                authenticate = this.mAuthMatcher.register(createMatcherInParams, null);
            } else {
                authenticate = this.mAuthMatcher.authenticate(createMatcherInParams, null);
            }
            if (authenticate == null) {
                userVerifyResponse.statusCode = (short) 1;
                return userVerifyResponse;
            }
            userVerifyResponse.statusCode = (short) 0;
            userVerifyResponse.additionalAKArgument = null;
            userVerifyResponse.extensions = authenticate.getExtensions();
            if (authenticate.getUserID() != null) {
                userVerifyResponse.userID = Base64.encodeToString(authenticate.getUserID(), 0);
            }
            if (matcherType == MatcherParamsHelper.AuthenticatorMatcherType.MATCHER_TYPE_UVT) {
                userVerifyResponse.userVerifyToken = createFakeUVT(this.mInfo.generalInfo.aaid, bArr, ((UVTMatcherOutParams) authenticate).getUVT());
            }
            validateResult(authenticate);
        } else {
            Logger.m15895d("AuthenticatorCore", "**!!** verifyUser 认证 - 是AK管理的Matcher **!!**");
            userVerifyResponse.statusCode = (short) 0;
            userVerifyResponse.userVerifyToken = createFakeUVT(this.mInfo.generalInfo.aaid, bArr, "12345678123456781234567812345678".getBytes());
        }
        return userVerifyResponse;
    }

    public Username displayUsernamePicker(List<Username> list) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Username username : list) {
            arrayList.add(username.username);
        }
        Intent intent = new Intent(this.mContext, SelectFromDialogActivity.class);
        intent.addFlags(268435456);
        intent.putStringArrayListExtra("DIALOGLIST", arrayList);
        intent.putExtra("DIALOGTITLEID", "Select user");
        String str = (String) ActivityStarter.startActivityForResult(this.mContext, intent, null, 0);
        if (str != null) {
            for (Username username2 : list) {
                if (username2.username.equals(str)) {
                    return username2;
                }
            }
        }
        return null;
    }

    public boolean isUserEnrolled() {
        if (this.mUafAuthDx.isRoamingAuthenticator()) {
            return true;
        }
        return this.mAuthenticatorDb.hasRegistrations();
    }

    public Response openSettings() {
        Response response = new Response();
        if (!this.mUafAuthDx.isRoamingAuthenticator() && MatcherParamsHelper.getMatcherType(this.mAuthDx, this.mAuthMatcher) != MatcherParamsHelper.AuthenticatorMatcherType.MATCHER_TYPE_AKMANAGED) {
            IMatcher.MatcherSettingsOutParams matcherSettingsOutParams = this.mAuthMatcher.settings(MatcherParamsHelper.createMatcherManageInParams(this.mAuthMatcher, this.mAntihammering));
            if (matcherSettingsOutParams == null || matcherSettingsOutParams.getResult() != IMatcher.RESULT.SUCCESS) {
                response.statusCode = 0;
                return response;
            }
        }
        response.statusCode = 0;
        return response;
    }

    private byte[] prepareTCToken(byte[] bArr, byte[] bArr2) throws AsmException {
        byte[] bArr3;
        try {
            if (bArr != null) {
                bArr3 = MessageDigest.getInstance("SHA-256").digest(bArr);
            } else {
                bArr3 = new byte[0];
            }
            ByteBuffer allocate = ByteBuffer.allocate((short) (bArr3.length + 9 + bArr2.length));
            allocate.order(ByteOrder.LITTLE_ENDIAN);
            allocate.putShort((short) 10485);
            allocate.putShort((short) 1);
            allocate.put((byte) 0);
            allocate.putShort((short) 10486);
            allocate.putShort((short) (bArr3.length + bArr2.length));
            allocate.put(bArr3);
            allocate.put(bArr2);
            return allocate.array();
        } catch (Exception e) {
            Logger.m15891e("AuthenticatorCore", "Error preparing transaction content", e);
            throw new AsmException(AsmError.FAILURE, "cannot hash transaction content.");
        }
    }

    static String getHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append("0123456789ABCDEF".charAt((b & 240) >> 4));
            sb.append("0123456789ABCDEF".charAt(b & 15));
        }
        return sb.toString();
    }

    private void assertNotAnActivityContext(Context context) {
        if (context instanceof Activity) {
            throw new MatcherAssertionException("Context to be used for IMatcher must not be an Activity Context.");
        }
    }

    private void getASMToken() throws AsmException {
        String aSMToken = this.mAuthenticatorDb.getASMToken();
        if (aSMToken == null) {
            if (this.mUafAuthDx.isRoamingAuthenticator()) {
                return;
            }
            Logger.m15895d("AuthenticatorCore", "Not roaming authenticator setting ASMToken");
            this.mASMToken = new byte[32];
            new Random().nextBytes(this.mASMToken);
            this.mAuthenticatorDb.storeASMToken(Base64.encodeToString(this.mASMToken, 11));
            return;
        }
        this.mASMToken = Base64.decode(aSMToken, 11);
    }

    public long getReferenceID() {
        return this.mInfo.generalInfo.authenticatorIndex;
    }

    public Response register(RegisterIn registerIn, List<Extension> list, Activity activity) {
        int i;
        byte[] prepareFinalChallenge;
        byte[] bArr;
        UserVerifyResponse verifyUser;
        String encodeToString;
        Logger.startTimer("AuthenticatorCore", "register");
        this.mAkProcessor.prepareProcessAK(activity);
        Response response = new Response();
        RegisterOut registerOut = new RegisterOut();
        if (registerIn.appID == null || registerIn.appID.equals("") || registerIn.username == null || registerIn.username.equals("") || registerIn.finalChallenge == null || registerIn.finalChallenge.equals("")) {
            Logger.m15892e("AuthenticatorCore", "Invalid RegisterIn.");
            response.statusCode = 2;
            Logger.endTimer("AuthenticatorCore", "register");
            return response;
        }
        Logger.m15895d("AuthenticatorCore", "AppID for Register in bytes: " + TLVCommandEncoder.getHex(registerIn.appID.getBytes(Charsets.utf8Charset)));
        try {
            try {
                prepareFinalChallenge = this.mAkProcessor.prepareFinalChallenge(registerIn.finalChallenge);
            } catch (AsmException e) {
                if (e.error().equals(AsmError.CANCELED)) {
                    Logger.m15883w("AuthenticatorCore", "User cancelled to register UAF");
                    i = 3;
                } else {
                    Logger.m15891e("AuthenticatorCore", "Failed to registers UAF credentials.", e);
                    i = 1;
                }
                this.mAkProcessor.postProcessAK();
            } catch (JSONException e2) {
                Logger.m15891e("AuthenticatorCore", "Failed to registers UAF credentials.", e2);
                this.mAkProcessor.postProcessAK();
                i = 1;
            }
            if (prepareFinalChallenge != null) {
                IMatcher.MatcherUI titleAndMaxMiss = getTitleAndMaxMiss(list);
                if (titleAndMaxMiss != null && !TextUtils.isEmpty(titleAndMaxMiss.getGestureUVT())) {
                    Logger.m15883w("AuthenticatorCore", "== gesture uvt: special process use aes encrypt with challenge value ==");
                    byte[] bArr2 = new byte[16];
                    byte[] bArr3 = new byte[16];
                    System.arraycopy(EncodeUtil.getSHA256(new JSONObject(new String(Base64.decode(registerIn.finalChallenge, 10))).getString("challenge").getBytes()), 0, bArr2, 0, 16);
                    System.arraycopy(EncodeUtil.getSHA256(titleAndMaxMiss.getGestureUVT().getBytes()), 0, bArr3, 0, 16);
                    titleAndMaxMiss.setGestureUVT(Base64.encodeToString(EncodeUtil.aesEncrypt(bArr2, bArr3), 10));
                    Logger.m15883w("AuthenticatorCore", "reg encode data:" + getHex(Base64.decode(encodeToString, 10)));
                }
                if (isUserEnrolled()) {
                    bArr = prepareFinalChallenge;
                    verifyUser = verifyUser(null, null, null, titleAndMaxMiss, prepareFinalChallenge, true);
                } else {
                    verifyUser = enrollUser(prepareFinalChallenge, titleAndMaxMiss);
                    bArr = prepareFinalChallenge;
                }
                if (verifyUser.statusCode != 0) {
                    Logger.m15892e("AuthenticatorCore", "failed to enroll user");
                    response.statusCode = 2;
                    Logger.endTimer("AuthenticatorCore", "register");
                    this.mAkProcessor.postProcessAK();
                    return response;
                }
                Logger.m15883w("AuthenticatorCore", "APPID is expected:" + this.mInfo.additionalInfo.expectAPPID + "  is RoamingAuthenticator: " + this.mInfo.generalInfo.isRoamingAuthenticator);
                ArrayList arrayList = new ArrayList();
                if (verifyUser.extensions != null && verifyUser.extensions.size() > 0) {
                    for (IMatcher.Extension extension : verifyUser.extensions) {
                        arrayList.add(extension);
                    }
                }
                if (list != null && list.size() > 0) {
                    for (Extension extension2 : list) {
                        IMatcher.Extension extension3 = new IMatcher.Extension();
                        extension3.fail_if_unknown = extension2.fail_if_unknown;
                        extension3.f4062id = extension2.f10152id;
                        extension3.data = extension2.data.getBytes(Charsets.utf8Charset);
                        arrayList.add(extension3);
                    }
                }
                AKProcessor.AKRequestParams cmd = new AKProcessor.AKRequestParams().setAuthenticatorIndex((byte) this.mInfo.generalInfo.authenticatorIndex).setAppID(this.mInfo.additionalInfo.expectAPPID | this.mInfo.generalInfo.isRoamingAuthenticator ? registerIn.appID.getBytes(Charsets.utf8Charset) : null).setFinalChallenge(bArr).setUserName(registerIn.username.getBytes(Charsets.utf8Charset)).setAttestationType(registerIn.attestationType).setKHAccessToken(getKHAccessToken(registerIn.appID, this.mCallerID, this.mASMToken, this.mPersonaID)).setUserVerifyToken(verifyUser.userVerifyToken).setAdditionalAKArgument(getAuthenticatorConfig()).setMatcherInParams(verifyUser.matcherInParams).setAuthenticatorDescriptor(this.mAuthDx).setMatcherType(verifyUser.matcherType).setExtensions(arrayList).setCmd((short) 13314);
                extInit(cmd);
                Logger.m15895d("AuthenticatorCore", "mAkProcessor prepare");
                AKProcessor.AKResponseParams processAK = this.mAkProcessor.processAK(cmd);
                cmd.setAdditionalAKArgument(processAK.additionalAKInfoToBeStored);
                Logger.m15895d("AuthenticatorCore", "mAkProcessor finish");
                saveAuthenticatorConfig(processAK.additionalAKInfoToBeStored);
                if (processAK.statusCode != 0) {
                    Logger.m15892e("AuthenticatorCore", "AK failed to register");
                    response.statusCode = processAK.statusCode;
                    Logger.endTimer("AuthenticatorCore", "register");
                    if (!this.mAuthenticatorDb.hasRegistrations() && !this.mInfo.generalInfo.isRoamingAuthenticator) {
                        this.mAuthenticatorDb.storeAKConfig("");
                    }
                    this.mAkProcessor.postProcessAK();
                    return response;
                }
                processAK.regToBeStored.callerID = Base64.encodeToString(this.mCallerID, 11);
                processAK.regToBeStored.appID = registerIn.appID;
                processAK.regToBeStored.timeStamp = System.currentTimeMillis();
                if (verifyUser.userID != null && !verifyUser.userID.equals("")) {
                    Logger.m15895d("AuthenticatorCore", "UserID is not null");
                    try {
                        Base64.decode(verifyUser.userID, 0);
                        processAK.regToBeStored.userID = verifyUser.userID;
                    } catch (IllegalArgumentException unused) {
                        Logger.endTimer("AuthenticatorCore", "register");
                        throw new AsmException(AsmError.INVALID_MESSAGE);
                    }
                }
                if (!this.mInfo.generalInfo.isRoamingAuthenticator) {
                    this.mAuthenticatorDb.addRegistration(processAK.regToBeStored);
                }
                registerOut.assertion = processAK.assertion;
                registerOut.assertionScheme = this.mInfo.generalInfo.assertionScheme;
                response.data = registerOut;
                if (extExtract(processAK) != null) {
                    response.exts = new ArrayList();
                    response.exts.addAll(extExtract(processAK));
                }
                this.mAkProcessor.postProcessAK();
                i = 0;
                response.statusCode = i;
                Logger.endTimer("AuthenticatorCore", "register");
                return response;
            }
            throw new AsmException(AsmError.FAILURE, "Value \"finalChallenge\" is null");
        } catch (Throwable th) {
            this.mAkProcessor.postProcessAK();
            throw th;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:29|(1:265)(7:33|34|35|36|37|38|39)|40|41|42|(3:195|196|(6:198|(6:201|202|203|(5:205|206|207|208|(2:210|(3:233|234|235)(4:212|(1:214)(1:232)|215|(7:220|221|222|223|224|225|226)(2:217|218)))(3:236|237|238))(2:241|242)|219|199)|246|247|47|(2:49|50)(13:51|(3:55|(2:58|56)|59)|(3:63|(2:66|64)|67)|68|69|(1:190)(1:73)|74|(3:76|(3:78|(2:81|79)|82)|83)(2:180|(2:182|183)(3:184|(3:187|188|185)|189))|84|86|87|88|(3:90|91|92)(8:93|94|(1:144)(2:96|(3:98|99|100)(2:101|(3:103|104|105)(11:106|(3:108|(2:111|109)|112)(1:143)|113|114|(2:116|(3:118|119|120)(1:121))(1:141)|122|(1:124)(1:140)|125|(1:127)(1:139)|128|(3:130|131|132))))|133|(1:135)|136|137|138))))|44|45|46|47|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:224:0x05a6, code lost:
        r0 = e;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 15, insn: 0x0623: MOVE  (r21 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r15 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:264:0x0622 */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0243 A[Catch: JSONException -> 0x0218, AsmException -> 0x0256, all -> 0x05be, TRY_ENTER, TRY_LEAVE, TryCatch #6 {AsmException -> 0x0256, blocks: (B:64:0x017c, B:66:0x0182, B:68:0x0186, B:70:0x0197, B:72:0x01a3, B:74:0x01ba, B:76:0x01bd, B:79:0x01c7, B:85:0x01db, B:73:0x01ac, B:86:0x01ed, B:87:0x01f6, B:102:0x0243, B:108:0x0261, B:110:0x0269, B:111:0x026f, B:113:0x0275, B:115:0x0284, B:117:0x028a, B:118:0x028e, B:120:0x0294, B:125:0x02c6, B:132:0x0339, B:134:0x033d, B:135:0x0343, B:137:0x0349, B:142:0x0396, B:147:0x03b2), top: B:274:0x017c }] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0258 A[Catch: AsmException -> 0x05a6, all -> 0x05be, JSONException -> 0x05c3, TRY_ENTER, TRY_LEAVE, TryCatch #4 {JSONException -> 0x05c3, blocks: (B:23:0x0071, B:34:0x009e, B:36:0x00a8, B:51:0x0132, B:100:0x023f, B:106:0x0258, B:123:0x02ba, B:130:0x02da, B:149:0x03c8, B:140:0x035e, B:144:0x03a8, B:145:0x03ac, B:129:0x02d2, B:99:0x0238), top: B:270:0x0071 }] */
    /* JADX WARN: Removed duplicated region for block: B:243:0x05d0  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x05ea A[Catch: all -> 0x0621, TryCatch #1 {all -> 0x0621, blocks: (B:241:0x05c7, B:251:0x05de, B:253:0x05ea, B:254:0x05f2, B:256:0x05fe, B:257:0x0608), top: B:270:0x0071 }] */
    /* JADX WARN: Removed duplicated region for block: B:254:0x05f2 A[Catch: all -> 0x0621, TryCatch #1 {all -> 0x0621, blocks: (B:241:0x05c7, B:251:0x05de, B:253:0x05ea, B:254:0x05f2, B:256:0x05fe, B:257:0x0608), top: B:270:0x0071 }] */
    /* JADX WARN: Removed duplicated region for block: B:260:0x0612  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0627  */
    /* JADX WARN: Type inference failed for: r13v0, types: [com.android.uaf.asmcore.AKProcessor$AKRequestParams] */
    /* JADX WARN: Type inference failed for: r13v16 */
    /* JADX WARN: Type inference failed for: r13v17 */
    /* JADX WARN: Type inference failed for: r13v18 */
    /* JADX WARN: Type inference failed for: r13v19 */
    /* JADX WARN: Type inference failed for: r13v20 */
    /* JADX WARN: Type inference failed for: r13v21 */
    /* JADX WARN: Type inference failed for: r22v0, types: [com.android.client.asm.core.uaf.AuthenticatorCore] */
    /* JADX WARN: Type inference failed for: r4v16, types: [com.android.uaf.asmcore.AKProcessor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.android.client.asm.core.uaf.AuthenticatorCore.Response authenticate(com.gmrz.android.client.asm.api.uaf.json.AuthenticateIn r23, java.util.List<com.gmrz.android.client.asm.api.uaf.json.Extension> r24, android.app.Activity r25) {
        /*
            Method dump skipped, instructions count: 1599
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.client.asm.core.uaf.AuthenticatorCore.authenticate(com.gmrz.android.client.asm.api.uaf.json.AuthenticateIn, java.util.List, android.app.Activity):com.android.client.asm.core.uaf.AuthenticatorCore$Response");
    }

    public Response deregister(DeregisterIn deregisterIn, List<Extension> list) throws AsmException {
        Logger.startTimer("AuthenticatorCore", "deregister");
        Response response = new Response();
        if (deregisterIn.appID == null || deregisterIn.appID.equals("") || deregisterIn.keyID == null) {
            Logger.m15892e("AuthenticatorCore", "Invalid DeregisterIn.");
        } else if (deregisterIn.keyID.equals("")) {
            response.statusCode = 0;
            for (GetRegistrationsOut.AppRegistration appRegistration : this.mAuthenticatorDb.getAppRegistrations(Base64.encodeToString(this.mCallerID, 11)).appRegs) {
                for (String str : appRegistration.keyIDs) {
                    Response deleteRegistration = deleteRegistration(deregisterIn.appID, str);
                    if (deleteRegistration.statusCode != 0) {
                        response.statusCode = deleteRegistration.statusCode;
                    }
                }
            }
        } else {
            response = deleteRegistration(deregisterIn.appID, deregisterIn.keyID);
        }
        Logger.endTimer("AuthenticatorCore", "deregister");
        return response;
    }

    private Response deleteRegistration(String str, String str2) {
        int i;
        ArrayList arrayList;
        Response response = new Response();
        try {
            byte[] bArr = null;
            if (this.mInfo.generalInfo.isRoamingAuthenticator) {
                arrayList = null;
            } else {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(str2);
                String encodeToString = Base64.encodeToString(this.mCallerID, 11);
                List<AuthenticatorDatabase.RegistrationRecord> registrations = this.mAuthenticatorDb.getRegistrations(encodeToString, str, arrayList2);
                if (registrations == null || registrations.size() <= 0) {
                    arrayList = null;
                } else {
                    arrayList = new ArrayList();
                    for (AuthenticatorDatabase.RegistrationRecord registrationRecord : registrations) {
                        arrayList.add(Base64.decode(registrationRecord.keyHandle, 11));
                    }
                }
                Logger.m15892e("AuthenticatorCore", "Delete Registration GetRegistrations for calledID: ." + encodeToString);
                this.mAuthenticatorDb.removeRegistration(encodeToString, str, str2);
            }
            AKProcessor.AKRequestParams aKRequestParams = new AKProcessor.AKRequestParams();
            if (this.mInfo.additionalInfo.expectAPPID || this.mInfo.generalInfo.isRoamingAuthenticator) {
                bArr = str.getBytes(Charsets.utf8Charset);
            }
            try {
                this.mAkProcessor.processAK(aKRequestParams.setAppID(bArr).setKeyID(Base64.decode(str2, 11)).setKeyHandles(arrayList).setKHAccessToken(getKHAccessToken(str, this.mCallerID, this.mASMToken, this.mPersonaID)).setAuthenticatorIndex((byte) this.mInfo.generalInfo.authenticatorIndex).setCmd((short) 13316));
            } catch (AsmException e) {
                Logger.m15882w("AuthenticatorCore", "Dereg call to AK failed.", e);
            }
            if (!this.mAuthenticatorDb.hasRegistrations() && !this.mInfo.generalInfo.isRoamingAuthenticator) {
                this.mAuthenticatorDb.storeAKConfig("");
            }
            i = 0;
        } catch (AsmException e2) {
            Logger.m15882w("AuthenticatorCore", "Failed to delete registration.", e2);
            i = 1;
        }
        response.statusCode = i;
        return response;
    }

    public Response getRegistrations() throws AsmException {
        Response response = new Response();
        response.data = this.mAuthenticatorDb.getAppRegistrations(Base64.encodeToString(this.mCallerID, 11));
        response.statusCode = 0;
        return response;
    }

    private byte[] getKHAccessToken(String str, byte[] bArr, byte[] bArr2, byte[] bArr3) throws AsmException {
        try {
            boolean z = MetaDataUtils.getHashSaltType(this.mContext) != MetaDataUtils.HASH_SALT_TYPE.NONE;
            Log.wtf("AuthenticatorCore", "** getKHAccessToken - Hash with salt: " + z);
            String str2 = null;
            if (z) {
                str2 = this.mContext.getPackageName();
                if (TextUtils.isEmpty(str2)) {
                    throw new IllegalArgumentException("get package name failed");
                }
            }
            if (str != null) {
                if (z) {
                    this.mDigestMethod.update(byteMerger(str.getBytes(Charsets.utf8Charset), str2.getBytes(Charsets.utf8Charset)));
                } else {
                    this.mDigestMethod.update(str.getBytes(Charsets.utf8Charset));
                }
            }
            if (bArr2 != null) {
                if (z) {
                    this.mDigestMethod.update(byteMerger(bArr2, str2.getBytes(Charsets.utf8Charset)));
                } else {
                    this.mDigestMethod.update(bArr2);
                }
            }
            if (bArr3 != null) {
                if (z) {
                    this.mDigestMethod.update(byteMerger(bArr3, str2.getBytes(Charsets.utf8Charset)));
                } else {
                    this.mDigestMethod.update(bArr3);
                }
            }
            if (bArr != null) {
                if (z) {
                    this.mDigestMethod.update(byteMerger(bArr, str2.getBytes(Charsets.utf8Charset)));
                } else {
                    this.mDigestMethod.update(bArr);
                }
            }
            return this.mDigestMethod.digest();
        } catch (Exception e) {
            Logger.m15891e("AuthenticatorCore", "Error creating access key ", e);
            throw new AsmException(AsmError.FAILURE, "cannot get access key: no such algorithm");
        }
    }

    protected byte[] getAuthenticatorConfig() throws AsmException {
        String aKConfig = this.mAuthenticatorDb.getAKConfig();
        if (aKConfig != null) {
            return Base64.decode(aKConfig, 11);
        }
        return new byte[0];
    }

    protected void saveAuthenticatorConfig(byte[] bArr) throws AsmException {
        if (bArr == null || this.mUafAuthDx.isRoamingAuthenticator()) {
            return;
        }
        this.mAuthenticatorDb.storeAKConfig(Base64.encodeToString(bArr, 11));
    }

    private byte[] createFakeUVT(String str, byte[] bArr, byte[] bArr2) {
        if (TextUtils.equals("001A#3333", str)) {
            bArr2 = "fakeRawUVI".getBytes(Charsets.utf8Charset);
        }
        ByteBuffer allocate = ByteBuffer.allocate(str.length() + 32 + bArr.length + 4 + bArr2.length + 20);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.putShort((short) 25093);
        allocate.putShort((short) str.length());
        allocate.put(str.getBytes(Charsets.utf8Charset));
        allocate.putShort((short) 25095);
        allocate.putShort((short) 32);
        allocate.put(ByteBuffer.allocate(32));
        allocate.putShort((short) 25098);
        allocate.putShort((short) bArr.length);
        allocate.put(bArr);
        allocate.putShort((short) 259);
        allocate.putShort((short) bArr2.length);
        allocate.put(bArr2);
        allocate.putShort((short) 25099);
        allocate.putShort((short) 4);
        allocate.putInt(0);
        return allocate.array();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class Username {
        public String keyHandle;
        public long timeStamp;
        public String username;

        public Username(String str, String str2, long j) {
            this.username = str;
            this.keyHandle = str2;
            this.timeStamp = j;
        }
    }

    void GetNamesToDisplay(List<Username> list, List<AuthenticatorDatabase.RegistrationRecord> list2, Map<String, Username> map) {
        ArrayList<AuthenticatorDatabase.RegistrationRecord> arrayList = new ArrayList();
        for (Username username : list) {
            Iterator<AuthenticatorDatabase.RegistrationRecord> it = list2.iterator();
            while (true) {
                if (it.hasNext()) {
                    AuthenticatorDatabase.RegistrationRecord next = it.next();
                    if (next.keyHandle.equals(username.keyHandle)) {
                        Username username2 = map.get(username.username);
                        if (username2 == null || next.timeStamp > username2.timeStamp) {
                            if (username2 != null) {
                                AuthenticatorDatabase.RegistrationRecord registrationRecord = new AuthenticatorDatabase.RegistrationRecord();
                                Iterator<AuthenticatorDatabase.RegistrationRecord> it2 = list2.iterator();
                                while (true) {
                                    if (!it2.hasNext()) {
                                        break;
                                    }
                                    AuthenticatorDatabase.RegistrationRecord next2 = it2.next();
                                    if (next2.keyHandle.equals(username2.keyHandle)) {
                                        registrationRecord.appID = next2.appID;
                                        registrationRecord.keyID = next2.keyID;
                                        break;
                                    }
                                }
                                arrayList.add(registrationRecord);
                            }
                            username.timeStamp = next.timeStamp;
                            map.put(username.username, username);
                        } else {
                            AuthenticatorDatabase.RegistrationRecord registrationRecord2 = new AuthenticatorDatabase.RegistrationRecord();
                            registrationRecord2.appID = next.appID;
                            registrationRecord2.keyID = next.keyID;
                            arrayList.add(registrationRecord2);
                        }
                    }
                }
            }
        }
        for (AuthenticatorDatabase.RegistrationRecord registrationRecord3 : arrayList) {
            if (deleteRegistration(registrationRecord3.appID, registrationRecord3.keyID).statusCode != 0) {
                Logger.m15892e("AuthenticatorCore", "Database cleanup info: Can't delete old registration");
            }
        }
    }

    public IMatcher.MatcherUI getTitleAndMaxMiss(List<Extension> list) {
        if (list != null) {
            for (Extension extension : list) {
                if (extension != null && ("EXTENSION_ID_UI".equals(extension.f10152id) || "EXTENSION_ID_GESTURE".equals(extension.f10152id))) {
                    return new IMatcher.MatcherUI().fromJson(extension.data);
                }
            }
            return null;
        }
        return null;
    }

    private void extInit(AKProcessor.AKRequestParams aKRequestParams) {
        Logger.m15895d("AuthenticatorCore", "extInit");
        this.mAkProcessor.extInit(aKRequestParams);
    }

    private List<Extension> extExtract(AKProcessor.AKResponseParams aKResponseParams) {
        Logger.m15895d("AuthenticatorCore", "extExtract");
        return this.mAkProcessor.extExtract(aKResponseParams);
    }

    byte[] byteMerger(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }
}
