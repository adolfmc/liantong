package com.sinovatech.unicom.separatemodule.tongyicaiji;

import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EncodeHelper;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.entity.TYCJConfigEntity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex */
public class TYCJConfigUtil {
    public static final long DB_MAX_SIZE = 2000;
    private static String TAG = "TYCJConfigUtil";
    private static TYCJConfigEntity tycjConfigEntity = new TYCJConfigEntity();
    private static final Map<String, TYCJConfigEntity.ChannelRule> CHANNEL_RULE_MAP = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$loadH5JavaScriptStr$0(String str) throws Exception {
    }

    public static void init() {
        tycjConfigEntity = getTongyicaijiConfig();
        List<TYCJConfigEntity.ChannelRule> channelRuleList = tycjConfigEntity.getChannelRuleList();
        if (channelRuleList != null) {
            for (TYCJConfigEntity.ChannelRule channelRule : channelRuleList) {
                CHANNEL_RULE_MAP.put(channelRule.getChannelTopic(), channelRule);
            }
        }
        TYCJNetParseUtils.getInstance().init();
    }

    public static boolean isOpenAndWhiteUrl(String str, String str2, String str3) {
        return isOpen(str) && isWhiteUrl(str, str2, str3);
    }

    public static boolean isOpen(String str) {
        TYCJConfigEntity.ChannelRule channelRule = CHANNEL_RULE_MAP.get(str);
        if (channelRule != null) {
            if (isMobileWhite(channelRule)) {
                return true;
            }
            if (channelRule.isOpen() && isPVWhite(channelRule)) {
                return isProportion(channelRule);
            }
            return false;
        }
        return false;
    }

    private static boolean isMobileWhite(TYCJConfigEntity.ChannelRule channelRule) {
        try {
            String mobileList = channelRule.getMobileList();
            if (TextUtils.isEmpty(mobileList)) {
                return false;
            }
            String decodeByAESTycjWhite = EncodeHelper.decodeByAESTycjWhite(mobileList);
            String defaultPhoneNumber = UserManager.getInstance().getDefaultPhoneNumber();
            if (!decodeByAESTycjWhite.contains(defaultPhoneNumber) || TextUtils.isEmpty(defaultPhoneNumber)) {
                return false;
            }
            return !"0".equals(defaultPhoneNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean isPVWhite(TYCJConfigEntity.ChannelRule channelRule) {
        String pv = tycjConfigEntity.getPv();
        String provice = channelRule.getProvice();
        return isMobileWhite(channelRule) || TextUtils.isEmpty(pv) || TextUtils.isEmpty(provice) || provice.contains(pv) || provice.contains("098") || "098".equals(pv);
    }

    private static boolean isProportion(TYCJConfigEntity.ChannelRule channelRule) {
        boolean z = true;
        try {
            String collectProportion = channelRule.getCollectProportion();
            String str = channelRule.getChannelTopic() + "_collectProp_b";
            String str2 = channelRule.getChannelTopic() + "_collectProp_s";
            String string = App.getSharePreferenceUtil().getString(str2);
            if (!TextUtils.isEmpty(collectProportion)) {
                if (collectProportion.equals(string)) {
                    z = App.getSharePreferenceUtil().getBoolean(str);
                } else {
                    if (Integer.parseInt(collectProportion) <= new Random().nextInt(1000)) {
                        z = false;
                    }
                    App.getSharePreferenceUtil().putString(str2, collectProportion);
                    App.getSharePreferenceUtil().putBoolean(str, Boolean.valueOf(z));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return z;
    }

    public static boolean isWhiteUrl(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) || isBlackUrl(str, str2, str3)) {
            return false;
        }
        ArrayList<String> whiteUrlList = getCbEntity(str).getWhiteUrlList();
        if (whiteUrlList == null || whiteUrlList.size() == 0) {
            return true;
        }
        Iterator<String> it = whiteUrlList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (str2.contains(next)) {
                return true;
            }
            if (!TextUtils.isEmpty(str3) && next.contains(str3)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBlackUrl(String str, String str2, String str3) {
        ArrayList<String> blackUrlList = getCbEntity(str).getBlackUrlList();
        if (blackUrlList == null || blackUrlList.size() == 0) {
            return false;
        }
        Iterator<String> it = blackUrlList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (str2.contains(next)) {
                return true;
            }
            if (!TextUtils.isEmpty(str3) && next.contains(str3)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotJsInjection() {
        return tycjConfigEntity.getCommonRule().getIsNotJsInjection() == 1;
    }

    public static boolean isNeedUpload(long j, long j2) {
        return j >= tycjConfigEntity.getCommonRule().getUploadPackageNum() || j2 >= tycjConfigEntity.getCommonRule().getUploadPackageSize() * 1000;
    }

    public static boolean isUpDataConfig(String str) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String ls = tycjConfigEntity.getLs();
        if (TextUtils.isEmpty(ls)) {
            return false;
        }
        return !str.equals(ls);
    }

    public static boolean isNeedZip(long j) {
        return tycjConfigEntity.getCommonRule().getIsNotZip() != 0 && j >= tycjConfigEntity.getCommonRule().getUploadZipSize() * 1000;
    }

    private static TYCJConfigEntity.ChannelRule getCbEntity(String str) {
        TYCJConfigEntity.ChannelRule channelRule = CHANNEL_RULE_MAP.get(str);
        return channelRule != null ? channelRule : new TYCJConfigEntity.ChannelRule();
    }

    public static int getUploadTimeInterval() {
        int uploadTimeInterval = tycjConfigEntity.getCommonRule().getUploadTimeInterval();
        if (uploadTimeInterval <= 10) {
            return 10;
        }
        return uploadTimeInterval;
    }

    public static void requestTongyicaijiConfigDataAll() {
        HashMap hashMap = new HashMap();
        hashMap.put("deviceCode", DeviceHelper.getDeviceID(true));
        hashMap.put("reqTime", System.currentTimeMillis() + "");
        hashMap.put("cfgType", "0");
        hashMap.put("channel", "");
        hashMap.put("appVersion", App.getInstance().getString(2131886969));
        App.getAsyncHttpClient().rxPost(URLSet.tongyicaijiConfigUrl(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, String>() { // from class: com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJConfigUtil.2
            @Override // io.reactivex.functions.Function
            public String apply(String str) throws Exception {
                String optString = new JSONObject(str).optString("code");
                if ("0000".equals(optString)) {
                    CacheDataCenter.getInstance().setTongyicaijiConfigData(str);
                    TYCJConfigUtil.init();
                }
                return optString;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJConfigUtil.1
            @Override // io.reactivex.functions.Consumer
            public void accept(String str) throws Exception {
                if ("0000".equals(str)) {
                    TYCJConfigUtil.loadH5JavaScriptStr();
                    TYCJUploadManager.startInterval();
                    MsLogUtil.m7979d("TYCJConfigUtil", "配置项更新成功");
                }
            }
        }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
    }

    private static TYCJConfigEntity getTongyicaijiConfig() {
        String tongyicaijiConfigData;
        JSONObject optJSONObject;
        JSONObject jSONObject;
        JSONObject jSONObject2;
        JSONObject jSONObject3;
        JSONObject jSONObject4;
        JSONArray jSONArray;
        TYCJConfigEntity tYCJConfigEntity = new TYCJConfigEntity();
        try {
            tongyicaijiConfigData = CacheDataCenter.getInstance().getTongyicaijiConfigData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(tongyicaijiConfigData)) {
            return tYCJConfigEntity;
        }
        MsLogUtil.m7979d("采集配置项", tongyicaijiConfigData);
        JSONObject jSONObject5 = new JSONObject(tongyicaijiConfigData);
        String optString = jSONObject5.optString("code");
        tYCJConfigEntity.setCode(optString);
        if ("0000".equals(optString) && (optJSONObject = jSONObject5.optJSONObject("msg")) != null) {
            tYCJConfigEntity.setPv(optJSONObject.optString("PV"));
            JSONObject optJSONObject2 = optJSONObject.optJSONObject("KR");
            if (optJSONObject2 != null) {
                MsLogUtil.m7979d("采集配置项KR", !(optJSONObject2 instanceof JSONObject) ? optJSONObject2.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject2));
                TYCJConfigEntity.CommonRule commonRule = new TYCJConfigEntity.CommonRule();
                long optLong = optJSONObject2.optLong("uploadPackageNum");
                long optLong2 = optJSONObject2.optLong("uploadPackageSize");
                int optInt = optJSONObject2.optInt("uploadTimeInterval");
                int optInt2 = optJSONObject2.optInt("isNotZip");
                long optLong3 = optJSONObject2.optLong("uploadZipSize");
                int optInt3 = optJSONObject2.optInt("isNotJsInjection");
                String optString2 = optJSONObject2.optString("jsUrl");
                String optString3 = optJSONObject2.optString("spaceUrl");
                commonRule.setNumberLog(TextUtils.isEmpty(optJSONObject2.optString("numberLog")) ? "2000" : optJSONObject2.optString("numberLog"));
                commonRule.setUploadPackageNum(optLong);
                commonRule.setUploadPackageSize(optLong2);
                commonRule.setUploadTimeInterval(optInt);
                commonRule.setIsNotZip(optInt2);
                commonRule.setUploadZipSize(optLong3);
                commonRule.setIsNotJsInjection(optInt3);
                commonRule.setJsUrl(optString2);
                commonRule.setSpaceUrl(optString3);
                tYCJConfigEntity.setCommonRule(commonRule);
            }
            JSONObject optJSONObject3 = optJSONObject.optJSONObject("BLM");
            if (optJSONObject3 != null) {
                JSONArray optJSONArray = optJSONObject3.optJSONArray("cb");
                if (optJSONArray != null) {
                    ArrayList arrayList = new ArrayList();
                    int i = 0;
                    while (i < optJSONArray.length()) {
                        TYCJConfigEntity.ChannelRule channelRule = new TYCJConfigEntity.ChannelRule();
                        JSONObject optJSONObject4 = optJSONArray.optJSONObject(i);
                        String optString4 = optJSONObject4.optString("channelTopic");
                        String optString5 = optJSONObject4.optString("state");
                        String optString6 = optJSONObject4.optString("mobileList");
                        String optString7 = optJSONObject4.optString("channelName");
                        String optString8 = optJSONObject4.optString("isNotMatchingUrl");
                        String optString9 = optJSONObject4.optString("provice");
                        String optString10 = optJSONObject4.optString("collectProportion");
                        JSONArray optJSONArray2 = optJSONObject4.optJSONArray("urlList");
                        if (optJSONArray2 == null || optJSONArray2.length() <= 0) {
                            jSONObject3 = optJSONObject;
                            jSONObject4 = optJSONObject3;
                            jSONArray = optJSONArray;
                        } else {
                            jSONArray = optJSONArray;
                            ArrayList<String> arrayList2 = new ArrayList<>();
                            jSONObject3 = optJSONObject;
                            jSONObject4 = optJSONObject3;
                            for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                arrayList2.add(optJSONArray2.optString(i2));
                            }
                            channelRule.setWhiteUrlList(arrayList2);
                        }
                        JSONArray optJSONArray3 = optJSONObject4.optJSONArray("urlbList");
                        if (optJSONArray3 != null && optJSONArray3.length() > 0) {
                            ArrayList<String> arrayList3 = new ArrayList<>();
                            for (int i3 = 0; i3 < optJSONArray3.length(); i3++) {
                                arrayList3.add(optJSONArray3.optString(i3));
                            }
                            channelRule.setBlackUrlList(arrayList3);
                        }
                        channelRule.setChannelTopic(optString4);
                        channelRule.setState(optString5);
                        channelRule.setIsNotMatchingUrl(optString8);
                        channelRule.setMobileList(optString6);
                        channelRule.setChannelName(optString7);
                        channelRule.setCollectProportion(optString10);
                        arrayList.add(channelRule);
                        channelRule.setProvice(optString9);
                        i++;
                        optJSONArray = jSONArray;
                        optJSONObject = jSONObject3;
                        optJSONObject3 = jSONObject4;
                    }
                    jSONObject = optJSONObject;
                    jSONObject2 = optJSONObject3;
                    tYCJConfigEntity.setChannelRuleList(arrayList);
                } else {
                    jSONObject = optJSONObject;
                    jSONObject2 = optJSONObject3;
                }
                JSONArray optJSONArray4 = jSONObject2.optJSONArray("ab");
                if (optJSONArray4 != null) {
                    ArrayList arrayList4 = new ArrayList();
                    for (int i4 = 0; i4 < optJSONArray4.length(); i4++) {
                        TYCJConfigEntity.TouchuanRule touchuanRule = new TYCJConfigEntity.TouchuanRule();
                        JSONObject optJSONObject5 = optJSONArray4.optJSONObject(i4);
                        String optString11 = optJSONObject5.optString("businessId");
                        String optString12 = optJSONObject5.optString("number");
                        String optString13 = optJSONObject5.optString("realTimeUpload");
                        touchuanRule.setBusinessId(optString11);
                        touchuanRule.setNumber(optString12);
                        touchuanRule.setRealTimeUpload(optString13);
                        arrayList4.add(touchuanRule);
                    }
                    tYCJConfigEntity.setTouchuanRuleList(arrayList4);
                }
            } else {
                jSONObject = optJSONObject;
            }
            tYCJConfigEntity.setLs(jSONObject.optString("LS"));
        }
        return tYCJConfigEntity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void loadH5JavaScriptStr() {
        TYCJConfigEntity.CommonRule commonRule = tycjConfigEntity.getCommonRule();
        if (isNotJsInjection()) {
            App.getAsyncHttpClient().rxGet(commonRule.getJsUrl(), new HashMap()).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).subscribeOn(Schedulers.m1934io()).map(new Function<String, String>() { // from class: com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJConfigUtil.3
                @Override // io.reactivex.functions.Function
                public String apply(String str) throws Exception {
                    CacheDataCenter.getInstance().setTongyicaijiJs(str);
                    return str;
                }
            }).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.tongyicaiji.-$$Lambda$TYCJConfigUtil$2GV7_X0C7f50jspjh_Q7Y5n7ijw
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    TYCJConfigUtil.lambda$loadH5JavaScriptStr$0((String) obj);
                }
            }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
        }
    }

    public static String getUpLoadUrl() {
        TYCJConfigEntity tYCJConfigEntity = tycjConfigEntity;
        if (tYCJConfigEntity == null) {
            return URLSet.tongyicaijiUploadUrl();
        }
        TYCJConfigEntity.CommonRule commonRule = tYCJConfigEntity.getCommonRule();
        if (commonRule == null) {
            return URLSet.tongyicaijiUploadUrl();
        }
        String spaceUrl = commonRule.getSpaceUrl();
        if (TextUtils.isEmpty(spaceUrl)) {
            return URLSet.tongyicaijiUploadUrl();
        }
        String[] split = spaceUrl.split(",");
        if (split.length > 0) {
            return split[new Random().nextInt(split.length)];
        }
        return URLSet.tongyicaijiUploadUrl();
    }

    public static boolean isClearBox(long j) {
        try {
            if (tycjConfigEntity != null && j != 0) {
                MsLogUtil.m7979d("采集长度", j + "==" + tycjConfigEntity.getCommonRule().getNumberLog());
                return j > tycjConfigEntity.getCommonRule().getNumberLog();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
