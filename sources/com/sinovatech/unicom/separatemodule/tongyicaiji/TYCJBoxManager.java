package com.sinovatech.unicom.separatemodule.tongyicaiji;

import android.app.Activity;
import android.text.TextUtils;
import com.baidu.location.BDLocation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.server.ManagerLocation;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EncodeHelper;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.entity.TYCJtBoxEntity;
import com.sinovatech.unicom.separatemodule.tongyicaiji.entity.TYCJtBoxEntity_;
import com.tydic.tydic_tracker.app.TYApplication;
import com.unicom.pay.utils.buried.WPBusinessInfoBean;
import io.objectbox.Box;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class TYCJBoxManager {
    public static long logCount;
    public static long logLength;
    private static TYCJBoxManager tycjBoxManager;
    private Box<TYCJtBoxEntity> webBox;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class ChannelType {
        public static final String H5Click = "H5Click";
        public static final String H5Info = "H5Info";
        public static final String all_config_channel = "config_channel";
        public static final String appIn = "appIn";
        public static final String appOut = "appOut";
        public static final String image = "imgLoading";
        public static final String netWork = "netLoading";

        /* renamed from: pv */
        public static final String f18613pv = "pv";
        public static final String tianYan = "tianYan";
        public static final String tianYanH5 = "tianYanH5";
        public static final String unicomF = "unicomF";
        public static final String unicomV = "unicomV";
    }

    public static TYCJBoxManager getInstance() {
        if (tycjBoxManager == null) {
            synchronized (TYCJBoxManager.class) {
                if (tycjBoxManager == null) {
                    tycjBoxManager = new TYCJBoxManager();
                }
            }
        }
        return tycjBoxManager;
    }

    private TYCJBoxManager() {
        try {
            this.webBox = App.getBoxStore().boxFor(TYCJtBoxEntity.class);
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    public void collectUnicomF(Activity activity) {
        try {
            if (TYCJConfigUtil.isOpen("unicomF")) {
                String deviceInfo = TYCJBuildUtil.getDeviceInfo(activity);
                logCount++;
                logLength += deviceInfo.length();
                setData("unicomF", deviceInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void collectAppIn(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        try {
            if (TYCJConfigUtil.isOpen("appIn")) {
                String appInString = TYCJBuildUtil.getAppInString(str, str2, str3, str4, str5, str6, "0".equals(str7) ? "-" : str7, "0".equals(str8) ? "-" : str8, "0".equals(str9) ? "-" : str9, str10);
                logCount++;
                logLength += appInString.length();
                try {
                    setData("appIn", appInString);
                } catch (Exception e) {
                    e = e;
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public void collectUnicomV(String str, String str2) {
        try {
            if (TYCJConfigUtil.isOpen("unicomV")) {
                String sessionString = TYCJBuildUtil.getSessionString(str, str2);
                logCount++;
                logLength += sessionString.length();
                setData("unicomV", sessionString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void collectAppOut(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        try {
            if (TYCJConfigUtil.isOpen("appOut")) {
                String appOutString = TYCJBuildUtil.getAppOutString(str, str2, str3, str4, str5, str6, str7, str8);
                logCount++;
                logLength += appOutString.length();
                setData("appOut", appOutString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void collectH5clickInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        try {
            if (TYCJConfigUtil.isOpen("H5Click") && TYCJConfigUtil.isWhiteUrl("H5Click", str5, "")) {
                String h5Click = TYCJBuildUtil.getH5Click(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11);
                logCount++;
                logLength += h5Click.length();
                setData("H5Click", h5Click);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void collectWork(String str, String str2) {
        try {
            if (TYCJConfigUtil.isOpen(str2)) {
                logCount++;
                logLength += str.length();
                try {
                    TYCJtBoxEntity tYCJtBoxEntity = new TYCJtBoxEntity();
                    tYCJtBoxEntity.setJson(str);
                    tYCJtBoxEntity.setChannelTopic(str2);
                    tYCJtBoxEntity.setApiName("config_channel");
                    this.webBox.put((Box<TYCJtBoxEntity>) tYCJtBoxEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void collectUnicomImage(Activity activity, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        try {
            if (TYCJConfigUtil.isOpen("imgLoading")) {
                String imageInfo = TYCJBuildUtil.getImageInfo(activity, str, str2, str3, str4, str5, str6, str7, str8);
                MsLogUtil.m7979d("imagecollect", imageInfo);
                logCount++;
                logLength += imageInfo.length();
                TYInsertDataManager.getInstance().insertImageData(str2, str3, str8, str6);
                setData("imgLoading", imageInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void collectNetInfo(String str, String str2, List<String> list) {
        try {
            if (TYCJConfigUtil.isOpen(str)) {
                String info = TYCJBuildUtil.getInfo(str2, list);
                MsLogUtil.m7979d("collectInfo", info);
                logCount++;
                logLength += info.length();
                setData(str, info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void collectClickSdk(Activity activity, String str, String str2, String str3, String str4, String str5, String str6) {
        try {
            if (TYCJConfigUtil.isOpen("pv")) {
                String sdkInfo = TYCJBuildUtil.getSdkInfo(TYCJBuildUtil.getPvCommon(activity, str, str2, "", "", str3, str4, str5, "", str6, ""));
                logCount++;
                logLength += sdkInfo.length();
                try {
                    setData("pv", sdkInfo);
                } catch (Exception e) {
                    e = e;
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public void collectH5Info(JSONObject jSONObject, Map<String, String> map) {
        try {
            if (TYCJConfigUtil.isOpen("H5Info")) {
                String h5Info = TYCJBuildUtil.getH5Info(jSONObject, map);
                logCount++;
                logLength += h5Info.length();
                setData("H5Info", h5Info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearBoxData(String str) {
        try {
            if ("unicomF_1".equals(str)) {
                str = "unicomF";
            }
            List<TYCJtBoxEntity> list = getList(str);
            if (list.size() > 0) {
                this.webBox.remove(list);
            }
            if ("tianYan".equals(str)) {
                TYApplication.clearTable();
                clearTianynH5Info();
            }
            logCount = 0L;
            logLength = 0L;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<TYCJtBoxEntity> getList(String str) throws Exception {
        try {
            return this.webBox.query().equal(TYCJtBoxEntity_.channelTopic, str).build().find();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void clearBox() {
        try {
            if (this.webBox == null || !TYCJConfigUtil.isClearBox(this.webBox.count())) {
                return;
            }
            logCount = 0L;
            logLength = 0L;
            this.webBox.removeAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONArray getTianyanH5info() {
        try {
            List<TYCJtBoxEntity> list = getList("tianYanH5");
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                try {
                    try {
                        jSONArray.put(new JSONObject(list.get(i).getJson()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return jSONArray;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public void setTianyanH5Info(String str) {
        try {
            TYCJtBoxEntity tYCJtBoxEntity = new TYCJtBoxEntity();
            tYCJtBoxEntity.setJson(str);
            tYCJtBoxEntity.setChannelTopic("tianYanH5");
            this.webBox.put((Box<TYCJtBoxEntity>) tYCJtBoxEntity);
            logCount++;
            logLength += str.length();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void collectApiInfo(String str, String str2, String str3) {
        try {
            TYCJtBoxEntity tYCJtBoxEntity = new TYCJtBoxEntity();
            tYCJtBoxEntity.setJson(str2 + WPBusinessInfoBean.SPLIT + str3);
            tYCJtBoxEntity.setChannelTopic(str);
            tYCJtBoxEntity.setApiName("config_channel");
            this.webBox.put((Box<TYCJtBoxEntity>) tYCJtBoxEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearTianynH5Info() throws Exception {
        try {
            this.webBox.remove(getList("tianYanH5"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setData(String str, String str2) throws Exception {
        try {
            clearBox();
            TYCJtBoxEntity tYCJtBoxEntity = new TYCJtBoxEntity();
            tYCJtBoxEntity.setJson(str2);
            tYCJtBoxEntity.setChannelTopic(str);
            this.webBox.put((Box<TYCJtBoxEntity>) tYCJtBoxEntity);
            if (TYCJConfigUtil.isNeedUpload(logCount, logLength)) {
                MsLogUtil.m7979d("TYCJUploadManager", "达到了后台配置的缓存大小，开始上传");
                TYCJUploadManager.upoloadTongyicaiji();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> getAllData() {
        BDLocation bdLocation;
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            List<TYCJtBoxEntity> list = getList("unicomF");
            List<TYCJtBoxEntity> list2 = getList("unicomV");
            if (list.size() > 0 && TYCJConfigUtil.isOpen("unicomF")) {
                hashMap.put("unicomF_1", EncodeHelper.encodeByAESSDCJ_PRO(getParamsString(list)));
            }
            if (list2.size() > 0 && TYCJConfigUtil.isOpen("unicomV")) {
                hashMap.put("unicomV", getParamsString(list2));
            }
            List<TYCJtBoxEntity> list3 = getList("appIn");
            List<TYCJtBoxEntity> list4 = getList("appOut");
            List<TYCJtBoxEntity> list5 = getList("H5Info");
            List<TYCJtBoxEntity> list6 = getList("H5Click");
            List<TYCJtBoxEntity> list7 = getList("imgLoading");
            List<TYCJtBoxEntity> list8 = getList("netLoading");
            List<TYCJtBoxEntity> list9 = getList("pv");
            if (list3.size() > 0 && TYCJConfigUtil.isOpen("appIn")) {
                hashMap.put("appIn", getParamsString(list3));
            }
            if (list4.size() > 0 && TYCJConfigUtil.isOpen("appOut")) {
                hashMap.put("appOut", getParamsString(list4));
            }
            if (list5.size() > 0 && TYCJConfigUtil.isOpen("H5Info")) {
                String h5InfoParamsString = getH5InfoParamsString(list5);
                if (!TextUtils.isEmpty(h5InfoParamsString)) {
                    hashMap.put("H5Info", h5InfoParamsString);
                }
            }
            if (list6.size() > 0 && TYCJConfigUtil.isOpen("H5Click")) {
                hashMap.put("H5Click", getParamsString(list6));
            }
            if (list8.size() > 0 && TYCJConfigUtil.isOpen("netLoading")) {
                hashMap.put("netLoading", getValueParamsString(list8));
            }
            if (list7.size() > 0 && TYCJConfigUtil.isOpen("imgLoading")) {
                hashMap.put("imgLoading", getParamsString(list7));
            }
            if (list9.size() > 0 && TYCJConfigUtil.isOpen("pv")) {
                hashMap.put("pv", getParamsString(list9));
            }
            try {
                ManagerLocation.LocationEntity locationEntity = ManagerLocation.getInstance().getLocationEntity();
                String str = "";
                String str2 = "";
                String str3 = "";
                if (locationEntity.isLocationSuccess() && (bdLocation = locationEntity.getBdLocation()) != null) {
                    str = bdLocation.getCountry();
                    str2 = bdLocation.getProvince();
                    str3 = bdLocation.getCity();
                }
                JSONObject allData = TYApplication.getAllData(str, str2, str3, App.getInstance().getString(2131886249), DeviceHelper.getDeviceID(true), TYInsertDataManager.getInstance().getNetWorkData(), TYInsertDataManager.getInstance().getImageLoadData());
                MsLogUtil.m7979d("tianyandata", !(allData instanceof JSONObject) ? allData.toString() : NBSJSONObjectInstrumentation.toString(allData));
                allData.put("nest_resource_tracking", tycjBoxManager.getTianyanH5info());
                if (allData.optJSONArray("nest_sessions").length() + allData.optJSONArray("nest_startups").length() + allData.optJSONArray("thread_breakdowns").length() + allData.optJSONArray("nest_anr").length() + allData.optJSONArray("nest_resource_tracking").length() + allData.optJSONArray("request_infos").length() + allData.optJSONArray("business_info").length() > 0 && TYCJConfigUtil.isOpen("tianYan")) {
                    hashMap.put("tianYan", !(allData instanceof JSONObject) ? allData.toString() : NBSJSONObjectInstrumentation.toString(allData));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            List<TYCJtBoxEntity> find = this.webBox.query().equal(TYCJtBoxEntity_.apiName, "config_channel").build().find();
            ArrayList arrayList = new ArrayList();
            if (find.size() > 0) {
                for (int i = 0; i < find.size(); i++) {
                    String channelTopic = find.get(i).getChannelTopic();
                    if (!TextUtils.isEmpty(channelTopic) && !arrayList.contains(channelTopic)) {
                        arrayList.add(channelTopic);
                        hashMap.put(channelTopic, getParamsString(getList(channelTopic)));
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return hashMap;
    }

    private String getParamsString(List<TYCJtBoxEntity> list) throws Exception {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < list.size()) {
            sb.append(list.get(i).getJson());
            i++;
            if (i != list.size()) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    private String getValueParamsString(List<TYCJtBoxEntity> list) throws Exception {
        HashMap hashMap = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            try {
                JSONObject jSONObject = new JSONObject(list.get(i).getJson());
                String optString = jSONObject.optString("url");
                String optString2 = jSONObject.optString("value");
                if (hashMap.containsKey(optString)) {
                    ArrayList arrayList = (ArrayList) hashMap.get(optString);
                    if (arrayList != null) {
                        arrayList.add(optString2);
                        hashMap.put(optString, arrayList);
                    }
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(optString2);
                    hashMap.put(optString, arrayList2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Set<String> keySet = hashMap.keySet();
        StringBuilder sb = new StringBuilder();
        for (String str : keySet) {
            ArrayList arrayList3 = (ArrayList) hashMap.get(str);
            if (arrayList3 != null) {
                sb.append(str);
                sb.append("\n");
                sb.append(getHuanhangStr(arrayList3));
                sb.append("\n\n");
            }
        }
        MsLogUtil.m7979d("netWorkcaiji", "-----" + sb.toString());
        return sb.toString();
    }

    private String getH5InfoParamsString(List<TYCJtBoxEntity> list) throws Exception {
        HashMap hashMap = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            try {
                JSONObject jSONObject = new JSONObject(list.get(i).getJson());
                String optString = jSONObject.optString("url");
                String optString2 = jSONObject.optString("value");
                if (hashMap.containsKey(optString)) {
                    ArrayList arrayList = (ArrayList) hashMap.get(optString);
                    if (arrayList != null) {
                        arrayList.add(optString2);
                        hashMap.put(optString, arrayList);
                    }
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(optString2);
                    hashMap.put(optString, arrayList2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Set<String> keySet = hashMap.keySet();
        StringBuilder sb = new StringBuilder();
        for (String str : keySet) {
            ArrayList arrayList3 = (ArrayList) hashMap.get(str);
            if (arrayList3 != null) {
                sb.append(str);
                sb.append("\n");
                sb.append(getHuanhangStr(arrayList3));
                sb.append("\n\n");
            }
        }
        MsLogUtil.m7979d("builderTotle", "-----" + sb.toString());
        return sb.toString();
    }

    private static String getHuanhangStr(ArrayList<String> arrayList) throws Exception {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < arrayList.size()) {
            sb.append(arrayList.get(i));
            i++;
            if (i != arrayList.size()) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
