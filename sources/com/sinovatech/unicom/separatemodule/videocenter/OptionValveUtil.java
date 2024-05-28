package com.sinovatech.unicom.separatemodule.videocenter;

import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.sinovatech.unicom.basic.eventbus.HomeGuidEvent;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.audience.entity.BaseVideoEntity;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public enum OptionValveUtil {
    INSTENCE;
    
    private static final String OPTION_SCREEN_SHOW_STRING = "OPTION_SCREEN_SHOW_STRING";
    public static boolean isRequestFinish = false;
    private Map<String, String> optionScreen;
    private final String ATTENTION = "attentionShow";
    private final String RECOMMEND = "recommendShow";
    private final String RINGTONE = "ringtoneShow";
    private final String CHATROOM = "chatRoomShow";
    private final String SCREEN = "screenShow";
    private final String AD_DRAW = "advertShow";
    private final String AD_PAUSE = "adPauseShow";
    private final String AD_BANNER = "adOrderShow";
    private final String LIVE_REPORT = "chatInformantShow";
    private final String SEARCH_BAR = "searchShow";
    private final String LIVE_FYP = "liveShow";
    private final String ZHIBO = "liveShow";
    private final String MORE_VIEW_SHOW = "slowLive";
    private final String GUIDE_SHOW = "guideShow";
    private final String PAUSE_ADSHOW = "pauseAdShow";
    private final String GUIDEEDITSHOW = "guideEditShow";
    private final String DETAILSADSHOW = "detailsAdShow";
    private final String RECOMMENDADSHOW = "recommendAdShow";
    private final String THE_SAME_CITY_SHOW = "theSameCityShow";

    public boolean isShowMultiView() {
        return false;
    }

    OptionValveUtil() {
    }

    public OptionValveUtil getInstence() {
        return INSTENCE;
    }

    public void getOptionInfo(AppCompatActivity appCompatActivity) {
        isRequestFinish = false;
        String optionInfo = URLSet.getOptionInfo();
        UIUtils.logD("xcy", "获取屏蔽显示开关信息=" + optionInfo);
        ((ObservableSubscribeProxy) App.getAsyncHttpClient(10, 15, 10).rxGet(optionInfo, null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$OptionValveUtil$_ncZV68zc9fVIYeXnaoUh24VitU
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return OptionValveUtil.lambda$getOptionInfo$0(OptionValveUtil.this, (String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$OptionValveUtil$TpsT1dVYBVNa6GcA8cdvBdr005Q
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                OptionValveUtil.lambda$getOptionInfo$1(OptionValveUtil.this, (BaseVideoEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$OptionValveUtil$QPWHH_mr8nDYjeeedu5-uUezofs
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                OptionValveUtil.lambda$getOptionInfo$2(OptionValveUtil.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ BaseVideoEntity lambda$getOptionInfo$0(OptionValveUtil optionValveUtil, String str) throws Exception {
        UIUtils.logD("xcy", "开关信息-->" + str);
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        JSONObject jSONObject = new JSONObject(str);
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        String optString = jSONObject.optString("data");
        baseVideoEntity.setData(optionValveUtil.analysisJson(optString));
        App.getSharePreferenceUtil().putString("OPTION_SCREEN_SHOW_STRING", optString);
        return baseVideoEntity;
    }

    public static /* synthetic */ void lambda$getOptionInfo$1(OptionValveUtil optionValveUtil, BaseVideoEntity baseVideoEntity) throws Exception {
        optionValveUtil.optionScreen = (Map) baseVideoEntity.getData();
        isRequestFinish = true;
        if (App.getSharePreferenceUtil().getBoolean("unicom_app_guid_flag") || UserManager.getInstance().isYiwang()) {
            return;
        }
        EventBusUtils.post(new HomeGuidEvent(EventBusUtils.EVENT_MAIN_HOME_GUID));
    }

    public static /* synthetic */ void lambda$getOptionInfo$2(OptionValveUtil optionValveUtil, Throwable th) throws Exception {
        th.printStackTrace();
        optionValveUtil.optionScreen = optionValveUtil.analysisJson(optionValveUtil.getCache());
        isRequestFinish = true;
        if (App.getSharePreferenceUtil().getBoolean("unicom_app_guid_flag") || UserManager.getInstance().isYiwang()) {
            return;
        }
        EventBusUtils.post(new HomeGuidEvent(EventBusUtils.EVENT_MAIN_HOME_GUID));
    }

    private Map<String, String> analysisJson(String str) throws JSONException {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject = new JSONObject(str);
            hashMap.put("attentionShow", jSONObject.optString("attentionShow"));
            hashMap.put("recommendShow", jSONObject.optString("recommendShow"));
            hashMap.put("ringtoneShow", jSONObject.optString("ringtoneShow"));
            hashMap.put("liveShow", jSONObject.optString("liveShow"));
            hashMap.put("chatRoomShow", jSONObject.optString("chatRoomShow"));
            hashMap.put("screenShow", jSONObject.optString("screenShow"));
            hashMap.put("advertShow", jSONObject.optString("advertShow"));
            hashMap.put("adPauseShow", jSONObject.optString("adPauseShow"));
            hashMap.put("chatInformantShow", jSONObject.optString("chatInformantShow"));
            hashMap.put("searchShow", jSONObject.optString("searchShow"));
            hashMap.put("adOrderShow", jSONObject.optString("adOrderShow"));
            hashMap.put("liveShow", jSONObject.optString("liveShow"));
            hashMap.put("slowLive", jSONObject.optString("slowLive"));
            hashMap.put("guideShow", jSONObject.optString("guideShow"));
            hashMap.put("pauseAdShow", jSONObject.optString("pauseAdShow"));
            hashMap.put("guideEditShow", jSONObject.optString("guideEditShow"));
            hashMap.put("detailsAdShow", jSONObject.optString("detailsAdShow"));
            hashMap.put("recommendAdShow", jSONObject.optString("recommendAdShow"));
            hashMap.put("theSameCityShow", jSONObject.optString("theSameCityShow"));
        }
        return hashMap;
    }

    public String getCache() {
        return App.getSharePreferenceUtil().getString("OPTION_SCREEN_SHOW_STRING");
    }

    public boolean isShowAttentionTab() {
        if (this.optionScreen == null) {
            try {
                this.optionScreen = analysisJson(getCache());
            } catch (JSONException e) {
                e.printStackTrace();
                this.optionScreen = new HashMap();
            }
        }
        return !"N".equals(this.optionScreen.get("attentionShow"));
    }

    public boolean isShowRecommendTab() {
        if (this.optionScreen == null) {
            try {
                this.optionScreen = analysisJson(getCache());
            } catch (JSONException e) {
                e.printStackTrace();
                this.optionScreen = new HashMap();
            }
        }
        return !"N".equals(this.optionScreen.get("recommendShow"));
    }

    public boolean isShowRingtoneTab() {
        if (this.optionScreen == null) {
            try {
                this.optionScreen = analysisJson(getCache());
            } catch (JSONException e) {
                e.printStackTrace();
                this.optionScreen = new HashMap();
            }
        }
        return !"N".equals(this.optionScreen.get("ringtoneShow"));
    }

    public boolean isShowZhiBoTab() {
        if (this.optionScreen == null) {
            try {
                this.optionScreen = analysisJson(getCache());
            } catch (JSONException e) {
                e.printStackTrace();
                this.optionScreen = new HashMap();
            }
        }
        return !"N".equals(this.optionScreen.get("liveShow"));
    }

    public boolean isShowChatRoomTab() {
        if (this.optionScreen == null) {
            try {
                this.optionScreen = analysisJson(getCache());
            } catch (JSONException e) {
                e.printStackTrace();
                this.optionScreen = new HashMap();
            }
        }
        return !"N".equals(this.optionScreen.get("chatRoomShow"));
    }

    public boolean isShowScreenTab() {
        if (this.optionScreen == null) {
            try {
                this.optionScreen = analysisJson(getCache());
            } catch (JSONException e) {
                e.printStackTrace();
                this.optionScreen = new HashMap();
            }
        }
        boolean z = !"N".equals(this.optionScreen.get("screenShow"));
        StringBuilder sb = new StringBuilder();
        sb.append("开关信息-->负一屏开关=");
        sb.append(z ? "开" : "关");
        UIUtils.logD("xcy", sb.toString());
        return z;
    }

    public boolean isShowDrawAD() {
        if (this.optionScreen == null) {
            try {
                this.optionScreen = analysisJson(getCache());
            } catch (JSONException e) {
                e.printStackTrace();
                this.optionScreen = new HashMap();
            }
        }
        return !"N".equals(this.optionScreen.get("advertShow"));
    }

    public boolean isShowPauseAD() {
        if (this.optionScreen == null) {
            try {
                this.optionScreen = analysisJson(getCache());
            } catch (JSONException e) {
                e.printStackTrace();
                this.optionScreen = new HashMap();
            }
        }
        return !"N".equals(this.optionScreen.get("adPauseShow"));
    }

    public boolean isShowLiveReport() {
        if (this.optionScreen == null) {
            try {
                this.optionScreen = analysisJson(getCache());
            } catch (JSONException e) {
                e.printStackTrace();
                this.optionScreen = new HashMap();
            }
        }
        return !"N".equals(this.optionScreen.get("chatInformantShow"));
    }

    public boolean isShowOrderAD() {
        if (this.optionScreen == null) {
            try {
                this.optionScreen = analysisJson(getCache());
            } catch (JSONException e) {
                e.printStackTrace();
                this.optionScreen = new HashMap();
            }
        }
        return !"N".equals(this.optionScreen.get("adOrderShow"));
    }

    public boolean isShowSearchBtn() {
        if (this.optionScreen == null) {
            try {
                this.optionScreen = analysisJson(getCache());
            } catch (JSONException e) {
                e.printStackTrace();
                this.optionScreen = new HashMap();
            }
        }
        return !"N".equals(this.optionScreen.get("searchShow"));
    }

    public boolean isShowSlowLive() {
        if (this.optionScreen == null) {
            try {
                this.optionScreen = analysisJson(getCache());
            } catch (JSONException e) {
                e.printStackTrace();
                this.optionScreen = new HashMap();
            }
        }
        return !"N".equals(this.optionScreen.get("slowLive"));
    }

    public boolean isShowGuideShow() {
        if (this.optionScreen == null) {
            try {
                this.optionScreen = analysisJson(getCache());
            } catch (JSONException e) {
                e.printStackTrace();
                this.optionScreen = new HashMap();
            }
        }
        return "Y".equals(this.optionScreen.get("guideShow"));
    }

    public boolean isShowPauseAdv() {
        if (this.optionScreen == null) {
            try {
                this.optionScreen = analysisJson(getCache());
            } catch (JSONException e) {
                e.printStackTrace();
                this.optionScreen = new HashMap();
            }
        }
        return !"N".equals(this.optionScreen.get("pauseAdShow"));
    }

    public boolean isShowChannelBannerAdv() {
        if (this.optionScreen == null) {
            try {
                this.optionScreen = analysisJson(getCache());
            } catch (JSONException e) {
                e.printStackTrace();
                this.optionScreen = new HashMap();
            }
        }
        return !"N".equals(this.optionScreen.get("guideEditShow"));
    }

    public boolean isShowHuoShanDetailsAdv() {
        if (this.optionScreen == null) {
            try {
                this.optionScreen = analysisJson(getCache());
            } catch (JSONException e) {
                e.printStackTrace();
                this.optionScreen = new HashMap();
            }
        }
        return !"N".equals(this.optionScreen.get("detailsAdShow"));
    }

    public boolean isShowTuiJianWeiShiPinAdv() {
        if (this.optionScreen == null) {
            try {
                this.optionScreen = analysisJson(getCache());
            } catch (JSONException e) {
                e.printStackTrace();
                this.optionScreen = new HashMap();
            }
        }
        return !"N".equals(this.optionScreen.get("recommendAdShow"));
    }

    public boolean isShowTheSameCity() {
        if (this.optionScreen == null) {
            try {
                this.optionScreen = analysisJson(getCache());
            } catch (JSONException e) {
                e.printStackTrace();
                this.optionScreen = new HashMap();
            }
        }
        return "Y".equals(this.optionScreen.get("theSameCityShow"));
    }
}
