package com.android.client.asm.sdk;

import android.app.Activity;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.security.InvalidParameterException;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IMatcher {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class Extension {
        public byte[] data;
        public boolean fail_if_unknown;

        /* renamed from: id */
        public String f4062id;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class MatcherSettingsInParams {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum RESULT {
        SUCCESS,
        CANCEL,
        CHANGE_AUTHENTICATOR,
        CHANGE_TRANSACTION,
        MISMATCH,
        TOOMANYATTEMPTS,
        TIMEOUT,
        ERRORAUTH,
        USER_LOCKOUT,
        FINGER_SET_CHANGE,
        USER_BIOMETRIC_PREFERRED_IRIS
    }

    MatcherOutParams authenticate(MatcherInParams matcherInParams, Activity activity);

    void cancel();

    MatcherDefinedParamsClassList getMatcherDefinedParamsClassList();

    boolean isUserIDValid(byte[] bArr) throws AuthenticatorException;

    MatcherOutParams register(MatcherInParams matcherInParams, Activity activity);

    MatcherSettingsOutParams settings(MatcherSettingsInParams matcherSettingsInParams);

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class MatcherInParams {
        public byte[] FinalChallenge;
        public IAntiHammeringCallback m_antihammeringCallback;
        public String m_customUI;
        public MatcherUI m_matcherUI;
        public String m_transText;

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        public interface IAntiHammeringCallback {
            int getFailedCount();

            boolean incrementFailedCount();

            boolean resetFailedCount();
        }

        public MatcherInParams(String str, String str2, byte[] bArr, MatcherUI matcherUI, IAntiHammeringCallback iAntiHammeringCallback) {
            this.m_customUI = str;
            this.m_transText = str2;
            this.FinalChallenge = bArr;
            this.m_antihammeringCallback = iAntiHammeringCallback;
            this.m_matcherUI = matcherUI;
        }

        public MatcherInParams() {
        }

        public String getCustomUI() {
            return this.m_customUI;
        }

        public String getTransText() {
            return this.m_transText;
        }

        public byte[] getFinalChallenge() {
            return this.FinalChallenge;
        }

        public IAntiHammeringCallback getAntiHammeringCallback() {
            return this.m_antihammeringCallback;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class MatcherOutParams {
        protected List<Extension> m_Exts;
        public RESULT m_MatchResult;
        public byte[] m_UserID;

        public MatcherOutParams(RESULT result, byte[] bArr, List<Extension> list) {
            this.m_MatchResult = result;
            this.m_UserID = bArr;
            this.m_Exts = list;
        }

        public MatcherOutParams(RESULT result) {
            this.m_MatchResult = result;
            this.m_UserID = null;
            this.m_Exts = null;
        }

        public MatcherOutParams() {
        }

        public byte[] getUserID() {
            return this.m_UserID;
        }

        public RESULT getMatchResult() {
            return this.m_MatchResult;
        }

        public List<Extension> getExtensions() {
            return this.m_Exts;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class MatcherSettingsOutParams {
        protected RESULT m_Result;

        public MatcherSettingsOutParams(RESULT result) {
            this.m_Result = result;
        }

        public MatcherSettingsOutParams() {
        }

        public RESULT getResult() {
            return this.m_Result;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class MatcherDefinedParamsClassList {
        private final Class<? extends MatcherInParams> mMatcherInParamsClass;
        private final Class<? extends MatcherSettingsInParams> mMatcherManageInParamsClass;
        private final Class<? extends MatcherSettingsOutParams> mMatcherManageOutParamsClass;
        private final Class<? extends MatcherOutParams> mMatcherOutParamsClass;

        public MatcherDefinedParamsClassList(Class<? extends MatcherInParams> cls, Class<? extends MatcherOutParams> cls2, Class<? extends MatcherSettingsInParams> cls3, Class<? extends MatcherSettingsOutParams> cls4) {
            if (cls == null || cls2 == null) {
                throw new InvalidParameterException("MatcherInParams and MatcherOutParams are mandatory");
            }
            this.mMatcherInParamsClass = cls;
            this.mMatcherOutParamsClass = cls2;
            this.mMatcherManageInParamsClass = cls3;
            this.mMatcherManageOutParamsClass = cls4;
        }

        public Class<? extends MatcherInParams> getMatcherInParamsClass() {
            return this.mMatcherInParamsClass;
        }

        public Class<? extends MatcherOutParams> getMatcherOutParamsClass() {
            return this.mMatcherOutParamsClass;
        }

        public Class<? extends MatcherSettingsInParams> getMatcherManageInParamsClass() {
            return this.mMatcherManageInParamsClass;
        }

        public Class<? extends MatcherSettingsOutParams> getMatcherManageOutParamsClass() {
            return this.mMatcherManageOutParamsClass;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class MatcherUI {
        private String gestureUVT;
        private int maxMiss;
        private String title;

        public String getGestureUVT() {
            return this.gestureUVT;
        }

        public void setGestureUVT(String str) {
            this.gestureUVT = str;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public int getMaxMiss() {
            return this.maxMiss;
        }

        public void setMaxMiss(int i) {
            this.maxMiss = i;
        }

        public String toJson() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("title", this.title);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                jSONObject.put("maxMixx", this.maxMiss);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            try {
                jSONObject.put("gestureUVT", this.gestureUVT);
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
            return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        }

        public MatcherUI fromJson(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.title = jSONObject.optString("title");
                this.maxMiss = jSONObject.optInt("maxMiss", -1);
                this.gestureUVT = jSONObject.optString("gestureUVT");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }
    }
}
