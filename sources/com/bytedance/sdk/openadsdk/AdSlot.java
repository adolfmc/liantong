package com.bytedance.sdk.openadsdk;

import android.text.TextUtils;
import com.bytedance.sdk.openadsdk.api.C3972mb;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class AdSlot implements TTAdSlot {

    /* renamed from: b */
    private int f9382b;

    /* renamed from: df */
    private String f9383df;

    /* renamed from: e */
    private String f9384e;

    /* renamed from: g */
    private String f9385g;

    /* renamed from: gm */
    private String f9386gm;

    /* renamed from: h */
    private float f9387h;

    /* renamed from: hj */
    private float f9388hj;

    /* renamed from: io */
    private int[] f9389io;

    /* renamed from: jb */
    private int f9390jb;

    /* renamed from: je */
    private int f9391je;

    /* renamed from: jq */
    private TTAdLoadType f9392jq;

    /* renamed from: ko */
    private boolean f9393ko;

    /* renamed from: l */
    private int f9394l;

    /* renamed from: lc */
    private boolean f9395lc;

    /* renamed from: lz */
    private String f9396lz;

    /* renamed from: m */
    private String f9397m;

    /* renamed from: mb */
    private String f9398mb;

    /* renamed from: nk */
    private int f9399nk;

    /* renamed from: o */
    private int f9400o;

    /* renamed from: on */
    private String f9401on;

    /* renamed from: ox */
    private int f9402ox;

    /* renamed from: r */
    private String f9403r;

    /* renamed from: u */
    private int f9404u;

    /* renamed from: ww */
    private boolean f9405ww;

    /* renamed from: x */
    private String f9406x;

    private AdSlot() {
        this.f9390jb = 2;
        this.f9395lc = true;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public String getAdId() {
        return this.f9385g;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public String getCreativeId() {
        return this.f9383df;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public String getExt() {
        return this.f9403r;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public String getCodeId() {
        return this.f9398mb;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public boolean isAutoPlay() {
        return this.f9395lc;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public int getImgAcceptedWidth() {
        return this.f9402ox;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public int getImgAcceptedHeight() {
        return this.f9382b;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public float getExpressViewAcceptedWidth() {
        return this.f9388hj;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public float getExpressViewAcceptedHeight() {
        return this.f9387h;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public boolean isSupportDeepLink() {
        return this.f9393ko;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public boolean isSupportRenderConrol() {
        return this.f9405ww;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public int getAdCount() {
        return this.f9404u;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public void setAdCount(int i) {
        this.f9404u = i;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public String getMediaExtra() {
        return this.f9396lz;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public String getUserID() {
        return this.f9406x;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public int getOrientation() {
        return this.f9390jb;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public int getNativeAdType() {
        return this.f9399nk;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public void setNativeAdType(int i) {
        this.f9399nk = i;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public void setDurationSlotType(int i) {
        this.f9400o = i;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public int getDurationSlotType() {
        return this.f9400o;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public int[] getExternalABVid() {
        return this.f9389io;
    }

    public void setExternalABVid(int... iArr) {
        this.f9389io = iArr;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public int getAdloadSeq() {
        return this.f9394l;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public String getPrimeRit() {
        String str = this.f9397m;
        return str == null ? "" : str;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public int getAdType() {
        return this.f9391je;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public String getBidAdm() {
        return this.f9386gm;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public String getExtraSmartLookParam() {
        return this.f9384e;
    }

    public void setUserData(String str) {
        this.f9401on = str;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public String getUserData() {
        return this.f9401on;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public TTAdLoadType getAdLoadType() {
        return this.f9392jq;
    }

    public void setAdLoadType(TTAdLoadType tTAdLoadType) {
        this.f9392jq = tTAdLoadType;
    }

    public void setGroupLoadMore(int i) {
        this.f9396lz = m16585mb(this.f9396lz, i);
    }

    public String toString() {
        return "AdSlot{mCodeId='" + this.f9398mb + "', mImgAcceptedWidth=" + this.f9402ox + ", mImgAcceptedHeight=" + this.f9382b + ", mExpressViewAcceptedWidth=" + this.f9388hj + ", mExpressViewAcceptedHeight=" + this.f9387h + ", mAdCount=" + this.f9404u + ", mSupportDeepLink=" + this.f9393ko + ", mSupportRenderControl=" + this.f9405ww + ", mMediaExtra='" + this.f9396lz + "', mUserID='" + this.f9406x + "', mOrientation=" + this.f9390jb + ", mNativeAdType=" + this.f9399nk + ", mIsAutoPlay=" + this.f9395lc + ", mPrimeRit" + this.f9397m + ", mAdloadSeq" + this.f9394l + ", mAdId" + this.f9385g + ", mCreativeId" + this.f9383df + ", mExt" + this.f9403r + ", mUserData" + this.f9401on + ", mAdLoadType" + this.f9392jq + '}';
    }

    public JSONObject toJsonObj() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mCodeId", this.f9398mb);
            jSONObject.put("mIsAutoPlay", this.f9395lc);
            jSONObject.put("mImgAcceptedWidth", this.f9402ox);
            jSONObject.put("mImgAcceptedHeight", this.f9382b);
            jSONObject.put("mExpressViewAcceptedWidth", this.f9388hj);
            jSONObject.put("mExpressViewAcceptedHeight", this.f9387h);
            jSONObject.put("mAdCount", this.f9404u);
            jSONObject.put("mSupportDeepLink", this.f9393ko);
            jSONObject.put("mSupportRenderControl", this.f9405ww);
            jSONObject.put("mMediaExtra", this.f9396lz);
            jSONObject.put("mUserID", this.f9406x);
            jSONObject.put("mOrientation", this.f9390jb);
            jSONObject.put("mNativeAdType", this.f9399nk);
            jSONObject.put("mAdloadSeq", this.f9394l);
            jSONObject.put("mPrimeRit", this.f9397m);
            jSONObject.put("mExtraSmartLookParam", this.f9384e);
            jSONObject.put("mAdId", this.f9385g);
            jSONObject.put("mCreativeId", this.f9383df);
            jSONObject.put("mExt", this.f9403r);
            jSONObject.put("mBidAdm", this.f9386gm);
            jSONObject.put("mUserData", this.f9401on);
            jSONObject.put("mAdLoadType", this.f9392jq);
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class Builder {

        /* renamed from: df */
        private String f9408df;

        /* renamed from: e */
        private int f9409e;

        /* renamed from: gm */
        private String f9411gm;

        /* renamed from: io */
        private int[] f9414io;

        /* renamed from: jb */
        private String f9415jb;

        /* renamed from: je */
        private int f9416je;

        /* renamed from: ko */
        private String f9417ko;

        /* renamed from: l */
        private String f9418l;

        /* renamed from: m */
        private String f9421m;

        /* renamed from: mb */
        private String f9422mb;

        /* renamed from: nk */
        private float f9423nk;

        /* renamed from: o */
        private float f9424o;

        /* renamed from: on */
        private String f9425on;

        /* renamed from: r */
        private String f9427r;

        /* renamed from: x */
        private int f9430x;

        /* renamed from: ox */
        private int f9426ox = 640;

        /* renamed from: b */
        private int f9407b = 320;

        /* renamed from: hj */
        private boolean f9413hj = true;

        /* renamed from: h */
        private boolean f9412h = false;

        /* renamed from: u */
        private int f9428u = 1;

        /* renamed from: ww */
        private String f9429ww = "defaultUser";

        /* renamed from: lz */
        private int f9420lz = 2;

        /* renamed from: lc */
        private boolean f9419lc = true;

        /* renamed from: g */
        private TTAdLoadType f9410g = TTAdLoadType.UNKNOWN;

        public Builder setExtraParam(String str) {
            this.f9415jb = str;
            return this;
        }

        public Builder setAdType(int i) {
            this.f9416je = i;
            return this;
        }

        public Builder setAdId(String str) {
            this.f9408df = str;
            return this;
        }

        public Builder setCreativeId(String str) {
            this.f9427r = str;
            return this;
        }

        public Builder setExt(String str) {
            this.f9425on = str;
            return this;
        }

        public Builder setIsAutoPlay(boolean z) {
            this.f9419lc = z;
            return this;
        }

        public Builder setCodeId(String str) {
            this.f9422mb = str;
            return this;
        }

        public Builder setImageAcceptedSize(int i, int i2) {
            this.f9426ox = i;
            this.f9407b = i2;
            return this;
        }

        public Builder setExpressViewAcceptedSize(float f, float f2) {
            this.f9423nk = f;
            this.f9424o = f2;
            return this;
        }

        public Builder setSupportDeepLink(boolean z) {
            this.f9413hj = z;
            return this;
        }

        public Builder supportRenderControl() {
            this.f9412h = true;
            return this;
        }

        public Builder setAdCount(int i) {
            if (i <= 0) {
                i = 1;
                C3972mb.m16554b("TT_AD_SDK", "setAdCount: adCount must greater than 0 ");
            }
            if (i > 20) {
                C3972mb.m16554b("TT_AD_SDK", "setAdCount: adCount must less than or equal to 20 ");
                i = 20;
            }
            this.f9428u = i;
            return this;
        }

        public Builder setMediaExtra(String str) {
            this.f9417ko = str;
            return this;
        }

        public Builder setUserID(String str) {
            this.f9429ww = str;
            return this;
        }

        public Builder setOrientation(int i) {
            this.f9420lz = i;
            return this;
        }

        public Builder setNativeAdType(int i) {
            this.f9430x = i;
            return this;
        }

        public Builder setAdloadSeq(int i) {
            this.f9409e = i;
            return this;
        }

        public Builder setPrimeRit(String str) {
            this.f9418l = str;
            return this;
        }

        public Builder setExternalABVid(int... iArr) {
            this.f9414io = iArr;
            return this;
        }

        public Builder setUserData(String str) {
            this.f9411gm = str;
            return this;
        }

        public Builder setAdLoadType(TTAdLoadType tTAdLoadType) {
            this.f9410g = tTAdLoadType;
            return this;
        }

        public Builder withBid(String str) {
            if (str == null) {
                return this;
            }
            this.f9421m = str;
            return this;
        }

        public AdSlot build() {
            AdSlot adSlot = new AdSlot();
            adSlot.f9398mb = this.f9422mb;
            adSlot.f9404u = this.f9428u;
            adSlot.f9393ko = this.f9413hj;
            adSlot.f9405ww = this.f9412h;
            adSlot.f9402ox = this.f9426ox;
            adSlot.f9382b = this.f9407b;
            float f = this.f9423nk;
            if (f <= 0.0f) {
                adSlot.f9388hj = this.f9426ox;
                adSlot.f9387h = this.f9407b;
            } else {
                adSlot.f9388hj = f;
                adSlot.f9387h = this.f9424o;
            }
            adSlot.f9396lz = this.f9417ko;
            adSlot.f9406x = this.f9429ww;
            adSlot.f9390jb = this.f9420lz;
            adSlot.f9399nk = this.f9430x;
            adSlot.f9395lc = this.f9419lc;
            adSlot.f9389io = this.f9414io;
            adSlot.f9394l = this.f9409e;
            adSlot.f9397m = this.f9418l;
            adSlot.f9384e = this.f9415jb;
            adSlot.f9385g = this.f9408df;
            adSlot.f9383df = this.f9427r;
            adSlot.f9403r = this.f9425on;
            adSlot.f9391je = this.f9416je;
            adSlot.f9386gm = this.f9421m;
            adSlot.f9401on = this.f9411gm;
            adSlot.f9392jq = this.f9410g;
            return adSlot;
        }
    }

    /* renamed from: mb */
    private String m16585mb(String str, int i) {
        JSONObject jSONObject;
        if (i < 1) {
            return str;
        }
        try {
            if (TextUtils.isEmpty(str)) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(str);
            }
            jSONObject.put("_tt_group_load_more", i);
            return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
            return str;
        }
    }
}
