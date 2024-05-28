package com.sinovatech.unicom.separatemodule.user.manager;

import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.p315ui.home.manager.BaseFunction;
import com.sinovatech.unicom.basic.p315ui.home.manager.BaseRequestManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.user.entity.UserLibaoEntity;
import com.uber.autodispose.ObservableSubscribeProxy;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerUserLibao extends BaseRequestManager<UserLibaoEntity> {
    private static final String LIBAO_CACHE = "wode_libao_cache";

    public ManagerUserLibao(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
    }

    public ObservableSubscribeProxy<UserLibaoEntity> getLibao(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getResources().getString(2131886969));
        return setRequestType(str).setNetObserver(App.getAsyncHttpClient().rxGet(URLSet.userLibaoUrl(), hashMap)).setCacheKey(LIBAO_CACHE, UserManager.getInstance().getCurrentPhoneNumber()).setFunction(new BaseFunction<UserLibaoEntity>() { // from class: com.sinovatech.unicom.separatemodule.user.manager.ManagerUserLibao.1
            @Override // io.reactivex.functions.Function
            public UserLibaoEntity apply(String str2) throws Exception {
                JSONObject optJSONObject;
                UserLibaoEntity userLibaoEntity = new UserLibaoEntity();
                JSONObject jSONObject = new JSONObject(str2);
                userLibaoEntity.setRespCode(jSONObject.optString("respCode"));
                userLibaoEntity.setRespDesc(jSONObject.optString("respDesc"));
                if ("0000".equals(userLibaoEntity.getRespCode()) && (optJSONObject = jSONObject.optJSONObject("result")) != null) {
                    UserLibaoEntity.ResultDTO entity = ManagerUserLibao.this.getEntity(optJSONObject, false);
                    userLibaoEntity.setResult(entity);
                    if ("1".equals(entity.getMyGiftFloorSwitch())) {
                        if (TextUtils.isEmpty(entity.getTelVoucherTitle()) || TextUtils.isEmpty(entity.getTrafficPacketTitle()) || TextUtils.isEmpty(entity.getVoicePackageTitle()) || TextUtils.isEmpty(entity.getAllGiftTitle()) || TextUtils.isEmpty(entity.getTelVoucherUrl()) || TextUtils.isEmpty(entity.getTrafficPacketUrl()) || TextUtils.isEmpty(entity.getVoicePackageUrl()) || TextUtils.isEmpty(entity.getAllGiftUrl()) || TextUtils.isEmpty(entity.getTelVoucherUnit()) || TextUtils.isEmpty(entity.getTrafficPacketUnit()) || TextUtils.isEmpty(entity.getVoicePackageUnit())) {
                            String libao = CacheDataCenter.getInstance().getLibao(UserManager.getInstance().getCurrentPhoneNumber());
                            if (!TextUtils.isEmpty(libao)) {
                                userLibaoEntity.setResult(ManagerUserLibao.this.getEntity(new JSONObject(libao).optJSONObject("result"), true));
                            } else {
                                userLibaoEntity.setRespCode("9999");
                            }
                        } else {
                            CacheDataCenter.getInstance().setLibao(str2, UserManager.getInstance().getCurrentPhoneNumber());
                        }
                    }
                }
                return userLibaoEntity;
            }
        }).getObservable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UserLibaoEntity.ResultDTO getEntity(JSONObject jSONObject, boolean z) {
        UserLibaoEntity.ResultDTO resultDTO = new UserLibaoEntity.ResultDTO();
        if (jSONObject != null) {
            resultDTO.setMyGiftFloorSwitch(jSONObject.optString("myGiftFloorSwitch"));
            resultDTO.setAllGiftIconUrl(jSONObject.optString("allGiftIconUrl"));
            if (!z) {
                resultDTO.setAllGiftNewly(jSONObject.optString("allGiftNewly"));
            }
            resultDTO.setAllGiftTitle(jSONObject.optString("allGiftTitle"));
            resultDTO.setAllGiftUnit(jSONObject.optString("allGiftUnit"));
            resultDTO.setAllGiftUrl(jSONObject.optString("allGiftUrl"));
            resultDTO.setTelVoucherData(jSONObject.optString("telVoucherData"));
            if (!z) {
                resultDTO.setTelVoucherNewly(jSONObject.optString("telVoucherNewly"));
            } else {
                resultDTO.setTelVoucherData("-");
            }
            resultDTO.setTelVoucherTitle(jSONObject.optString("telVoucherTitle"));
            resultDTO.setTelVoucherUnit(jSONObject.optString("telVoucherUnit"));
            resultDTO.setTelVoucherUrl(jSONObject.optString("telVoucherUrl"));
            resultDTO.setTrafficPacketData(jSONObject.optString("trafficPacketData"));
            if (!z) {
                resultDTO.setTrafficPacketNewly(jSONObject.optString("trafficPacketNewly"));
            } else {
                resultDTO.setTrafficPacketData("-");
            }
            resultDTO.setTrafficPacketTitle(jSONObject.optString("trafficPacketTitle"));
            resultDTO.setTrafficPacketUnit(jSONObject.optString("trafficPacketUnit"));
            resultDTO.setTrafficPacketUrl(jSONObject.optString("trafficPacketUrl"));
            resultDTO.setVoicePackageData(jSONObject.optString("voicePackageData"));
            if (!z) {
                resultDTO.setVoicePackageNewly(jSONObject.optString("voicePackageNewly"));
            } else {
                resultDTO.setVoicePackageData("-");
            }
            resultDTO.setVoicePackageTitle(jSONObject.optString("voicePackageTitle"));
            resultDTO.setVoicePackageUnit(jSONObject.optString("voicePackageUnit"));
            resultDTO.setVoicePackageUrl(jSONObject.optString("voicePackageUrl"));
            resultDTO.setTitle(jSONObject.optString("title"));
        }
        return resultDTO;
    }
}
