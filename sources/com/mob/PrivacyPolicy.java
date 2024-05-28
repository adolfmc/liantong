package com.mob;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import com.mob.commons.C5855l;
import com.mob.commons.C5871t;
import com.mob.tools.MobLog;
import com.mob.tools.proguard.ClassKeeper;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.UIHandler;
import java.io.Serializable;
import java.util.Locale;
import org.json.JSONObject;

@Deprecated
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class PrivacyPolicy implements ClassKeeper, PublicMemberKeeper, Serializable {
    public static final int POLICY_TYPE_TXT = 2;
    public static final int POLICY_TYPE_URL = 1;

    /* renamed from: a */
    private String f13986a;

    /* renamed from: b */
    private String f13987b;

    /* renamed from: c */
    private int f13988c;

    /* renamed from: d */
    private long f13989d;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface OnPolicyListener extends ClassKeeper, PublicMemberKeeper {
        void onComplete(PrivacyPolicy privacyPolicy);

        void onFailure(Throwable th);
    }

    public static PrivacyPolicy getPrivacyPolicy(int i, Locale locale) throws Throwable {
        return null;
    }

    public PrivacyPolicy() {
    }

    public PrivacyPolicy(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            setTimestamp(jSONObject.optLong(C5855l.m12238a("009kAfkfhAh?hj:kf3fh>l")));
            setTitle(m12866a(jSONObject.optString("title")));
            setContent(m12866a(jSONObject.optString(C5855l.m12238a("007eSgfAgkhgk"))));
            String m12866a = m12866a(jSONObject.optString("ppVersion"));
            if (TextUtils.isEmpty(m12866a)) {
                return;
            }
            setPpVersion(Integer.parseInt(m12866a.trim()));
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
    }

    public String getTitle() {
        return this.f13986a;
    }

    public void setTitle(String str) {
        this.f13986a = str;
    }

    public String getContent() {
        return this.f13987b;
    }

    public void setContent(String str) {
        this.f13987b = str;
    }

    public int getPpVersion() {
        return this.f13988c;
    }

    public void setPpVersion(int i) {
        this.f13988c = i;
    }

    public long getTimestamp() {
        return this.f13989d;
    }

    public void setTimestamp(long j) {
        this.f13989d = j;
    }

    /* renamed from: a */
    private String m12866a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            String m12196a = C5871t.m12196a();
            return new String(Data.AES128Decode(Data.rawMD5(m12196a + ":" + C6152DH.SyncMtd.getPackageName() + ":" + getTimestamp()), Base64.decode(str, 0)), "UTF-8");
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return null;
        }
    }

    @Deprecated
    public static void getPrivacyPolicyAsync(int i, OnPolicyListener onPolicyListener) {
        getPrivacyPolicyAsync(i, null, onPolicyListener);
    }

    @Deprecated
    public static void getPrivacyPolicyAsync(int i, Locale locale, final OnPolicyListener onPolicyListener) {
        if (onPolicyListener != null) {
            final Throwable th = new Throwable("This api is Deprecated, please do not call it");
            UIHandler.sendEmptyMessage(0, new Handler.Callback() { // from class: com.mob.PrivacyPolicy.1
                @Override // android.os.Handler.Callback
                public boolean handleMessage(Message message) {
                    OnPolicyListener.this.onFailure(th);
                    return false;
                }
            });
        }
    }
}
