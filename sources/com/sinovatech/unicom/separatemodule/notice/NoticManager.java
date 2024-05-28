package com.sinovatech.unicom.separatemodule.notice;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.common.FileTools;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.notice.NoticeConfigEntity;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NoticManager {
    private static final String TAG = "NoticManager";

    public void getNoticConfigData() {
        HashMap hashMap = new HashMap();
        hashMap.put("version", App.getInstance().getString(2131886969));
        App.getAsyncHttpClient().rxPost(URLSet.getNoticConfigUrl(), hashMap).subscribeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.notice.-$$Lambda$NoticManager$V6LUHILNJI8mlqsaqEF7K5RCn58
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return NoticManager.lambda$getNoticConfigData$0((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.sinovatech.unicom.separatemodule.notice.NoticManager.1
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onNext(String str) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                th.printStackTrace();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$getNoticConfigData$0(String str) throws Exception {
        MsLogUtil.m7979d(TAG, "获取消息配置信息：" + str);
        new JSONObject(str);
        CacheDataCenter.getInstance().setNoticeConfigDefault(str);
        NoticeConfigEntity parserEntity = parserEntity(str);
        NoticeConfigEntity noticeEntity = getNoticeEntity();
        try {
            List<NoticeConfigEntity.FirstLevelDTO> firstLevel = parserEntity.getFirstLevel();
            List<NoticeConfigEntity.FirstLevelDTO> firstLevel2 = noticeEntity.getFirstLevel();
            for (NoticeConfigEntity.FirstLevelDTO firstLevelDTO : firstLevel) {
                for (NoticeConfigEntity.FirstLevelDTO firstLevelDTO2 : firstLevel2) {
                    if (firstLevelDTO.getFirstLevelNo().equals(firstLevelDTO2.getFirstLevelNo())) {
                        firstLevelDTO.setChecked(firstLevelDTO2.isChecked());
                        UIUtils.logD(TAG, "设置数据1:" + firstLevelDTO.getFirstClassificationName() + firstLevelDTO.isChecked());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            List<NoticeConfigEntity.SecondLevelDTO> secondLevel = parserEntity.getSecondLevel();
            List<NoticeConfigEntity.SecondLevelDTO> secondLevel2 = noticeEntity.getSecondLevel();
            for (NoticeConfigEntity.SecondLevelDTO secondLevelDTO : secondLevel) {
                for (NoticeConfigEntity.SecondLevelDTO secondLevelDTO2 : secondLevel2) {
                    if (secondLevelDTO.getSecondLevelNo().equals(secondLevelDTO2.getSecondLevelNo())) {
                        secondLevelDTO.setChecked(secondLevelDTO2.isChecked());
                        UIUtils.logD(TAG, "设置数据2:" + secondLevelDTO.getSecondClassificationName() + secondLevelDTO.isChecked());
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            List<NoticeConfigEntity.ServiceIdDTO> serviceId = parserEntity.getServiceId();
            List<NoticeConfigEntity.ServiceIdDTO> serviceId2 = noticeEntity.getServiceId();
            for (NoticeConfigEntity.ServiceIdDTO serviceIdDTO : serviceId) {
                for (NoticeConfigEntity.ServiceIdDTO serviceIdDTO2 : serviceId2) {
                    if (serviceIdDTO.getServiceId().equals(serviceIdDTO2.getServiceId())) {
                        serviceIdDTO.setChecked(serviceIdDTO2.isChecked());
                        UIUtils.logD(TAG, "设置数据3:" + serviceIdDTO.getServiceName() + serviceIdDTO.isChecked());
                    }
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return saveNoticeEntity(parserEntity);
    }

    public static NoticeConfigEntity getNoticeEntity() {
        String noticeConfigData = CacheDataCenter.getInstance().getNoticeConfigData();
        try {
            if (TextUtils.isEmpty(noticeConfigData)) {
                noticeConfigData = FileTools.readFile(App.getInstance().getResources().getAssets().open("pushtype.json"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parserEntity(noticeConfigData);
    }

    private static NoticeConfigEntity parserEntity(String str) {
        UIUtils.logD("NoticeConfigEntity", "0--" + System.currentTimeMillis());
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        NoticeConfigEntity.ConfigUrlDTO configUrlDTO = new NoticeConfigEntity.ConfigUrlDTO();
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONArray optJSONArray = jSONObject.optJSONArray("firstLevel");
            JSONArray optJSONArray2 = jSONObject.optJSONArray("secondLevel");
            JSONArray optJSONArray3 = jSONObject.optJSONArray("serviceId");
            JSONObject optJSONObject = jSONObject.optJSONObject("configUrl");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    NoticeConfigEntity.FirstLevelDTO firstLevelDTO = new NoticeConfigEntity.FirstLevelDTO();
                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                    firstLevelDTO.setFirstLevelImg(optJSONObject2.optString("firstLevelImg"));
                    firstLevelDTO.setFirstClassificationName(optJSONObject2.optString("firstClassificationName"));
                    firstLevelDTO.setFirstLevelNo(optJSONObject2.optString("firstLevelNo"));
                    firstLevelDTO.setChecked(optJSONObject2.optBoolean("checked"));
                    arrayList.add(firstLevelDTO);
                }
            }
            if (optJSONArray2 != null) {
                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                    JSONObject optJSONObject3 = optJSONArray2.optJSONObject(i2);
                    NoticeConfigEntity.SecondLevelDTO secondLevelDTO = new NoticeConfigEntity.SecondLevelDTO();
                    secondLevelDTO.setSecondLevelImg(optJSONObject3.optString("secondLevelImg"));
                    secondLevelDTO.setSecondLevelNo(optJSONObject3.optString("secondLevelNo"));
                    secondLevelDTO.setSecondClassificationName(optJSONObject3.optString("secondClassificationName"));
                    secondLevelDTO.setChecked(optJSONObject3.optBoolean("checked", true));
                    arrayList2.add(secondLevelDTO);
                }
            }
            if (optJSONArray3 != null) {
                for (int i3 = 0; i3 < optJSONArray3.length(); i3++) {
                    JSONObject optJSONObject4 = optJSONArray3.optJSONObject(i3);
                    NoticeConfigEntity.ServiceIdDTO serviceIdDTO = new NoticeConfigEntity.ServiceIdDTO();
                    serviceIdDTO.setServiceId(optJSONObject4.optString("serviceId"));
                    serviceIdDTO.setServiceName(optJSONObject4.optString("serviceName"));
                    serviceIdDTO.setChecked(optJSONObject4.optBoolean("checked"));
                    arrayList3.add(serviceIdDTO);
                }
            }
            if (optJSONObject != null) {
                configUrlDTO.setKuaixun(optJSONObject.optString("kuaixun"));
                configUrlDTO.setXiaoxi(optJSONObject.optString("xiaoxi"));
                configUrlDTO.setLiuyan(optJSONObject.optString("liuyan"));
                configUrlDTO.setSetting(optJSONObject.optString("setting"));
                configUrlDTO.setCollect(optJSONObject.optString("collect"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        NoticeConfigEntity noticeConfigEntity = new NoticeConfigEntity();
        noticeConfigEntity.setFirstLevel(arrayList);
        noticeConfigEntity.setSecondLevel(arrayList2);
        noticeConfigEntity.setServiceId(arrayList3);
        noticeConfigEntity.setConfigUrl(configUrlDTO);
        UIUtils.logD("NoticeConfigEntity", "1--" + System.currentTimeMillis());
        return noticeConfigEntity;
    }

    public static String saveNoticeEntity(NoticeConfigEntity noticeConfigEntity) {
        Gson gson = new Gson();
        String json = !(gson instanceof Gson) ? gson.toJson(noticeConfigEntity, NoticeConfigEntity.class) : NBSGsonInstrumentation.toJson(gson, noticeConfigEntity, NoticeConfigEntity.class);
        CacheDataCenter.getInstance().setNoticeConfigData(json);
        return json;
    }
}
