package com.sinovatech.unicom.separatemodule.tongyicaiji;

import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.tongyicaiji.entity.TYCJAddressEntity;
import com.unicom.pay.utils.buried.WPBusinessInfoBean;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class TYCJParseManager {
    private static TYCJParseManager tycjParseManager;
    private String[] newSortArr;
    private HashMap<String, String> unicomDataMap;
    WorkInterFace workInterFace;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface WorkInterFace {
        void upLoadFail(String str, String str2);
    }

    private TYCJParseManager() {
    }

    public static TYCJParseManager getInstanse() {
        tycjParseManager = new TYCJParseManager();
        return tycjParseManager;
    }

    private void initUnicomData() {
        if (this.unicomDataMap == null) {
            this.unicomDataMap = new HashMap<>();
        }
        this.unicomDataMap.clear();
        addUnicomMap(this.unicomDataMap);
    }

    private void addUnicomMap(Map<String, String> map) {
        String deviceID = DeviceHelper.getDeviceID(false);
        String currentProvinceCode = UserManager.getInstance().getCurrentProvinceCode();
        String userAreaid = UserManager.getInstance().getUserAreaid();
        TYCJAddressEntity addressEntity = TYCJAddressUtil.getAddressEntity();
        String locateProvinceCode = addressEntity.getLocateProvinceCode();
        String locateCityCode = addressEntity.getLocateCityCode();
        String str = "直立式";
        str = (UIUtils.isXiaoMiFoldableDevice() || UIUtils.isViVOFoldableDevice()) ? "折叠式" : "折叠式";
        String loginType = UserManager.getInstance().getLoginType();
        String str2 = UserManager.getInstance().isYiwang() ? "异网" : "本网";
        String deviceBranD = DeviceHelper.getDeviceBranD();
        String deviceModel = DeviceHelper.getDeviceModel();
        String str3 = UIUtils.getScreenWidth(App.getInstance()) + "x" + UIUtils.getFullScreenHeight(App.getInstance());
        String deviceOSVersion = DeviceHelper.getDeviceOSVersion();
        String pvLogSessionId = App.getPvLogSessionId();
        String str4 = App.pvLogSessionIdTime;
        String pageNewOldUser = App.hasLogined() ? CacheDataCenter.getInstance().getPageNewOldUser() : "";
        String string = App.getInstance().getString(2131886969);
        if (TextUtils.isEmpty(deviceID)) {
            deviceID = "-";
        }
        map.put("a0", deviceID);
        if (TextUtils.isEmpty(currentProvinceCode)) {
            currentProvinceCode = "-";
        }
        map.put("a1", currentProvinceCode);
        if (TextUtils.isEmpty(userAreaid)) {
            userAreaid = "-";
        }
        map.put("a2", userAreaid);
        if (TextUtils.isEmpty(locateProvinceCode)) {
            locateProvinceCode = "-";
        }
        map.put("a3", locateProvinceCode);
        if (TextUtils.isEmpty(locateCityCode)) {
            locateCityCode = "-";
        }
        map.put("a4", locateCityCode);
        if (TextUtils.isEmpty(str)) {
            str = "-";
        }
        map.put("a5", str);
        if (TextUtils.isEmpty(loginType)) {
            loginType = "-";
        }
        map.put("a6", loginType);
        if (TextUtils.isEmpty(str2)) {
            str2 = "-";
        }
        map.put("a7", str2);
        if (TextUtils.isEmpty(deviceBranD)) {
            deviceBranD = "-";
        }
        map.put("a8", deviceBranD);
        if (TextUtils.isEmpty(deviceModel)) {
            deviceModel = "-";
        }
        map.put("a9", deviceModel);
        if (TextUtils.isEmpty(str3)) {
            str3 = "-";
        }
        map.put("a10", str3);
        map.put("a11", TextUtils.isEmpty("AND") ? "-" : "AND");
        if (TextUtils.isEmpty(deviceOSVersion)) {
            deviceOSVersion = "-";
        }
        map.put("a12", deviceOSVersion);
        map.put("a13", TextUtils.isEmpty(pvLogSessionId) ? "-" : pvLogSessionId);
        map.put("a14", TextUtils.isEmpty(str4) ? "-" : str4);
        if (TextUtils.isEmpty(pageNewOldUser)) {
            pageNewOldUser = "-";
        }
        map.put("a15", pageNewOldUser);
        map.put("a16", TextUtils.isEmpty("N") ? "-" : "N");
        if (TextUtils.isEmpty(string)) {
            string = "-";
        }
        map.put("a17", string);
    }

    public Map<String, String> getUnicomDataMap() {
        return this.unicomDataMap;
    }

    public void loadWorkData(String str, String str2, String str3, String str4) {
        try {
            if (TYCJConfigUtil.isOpen(str4)) {
                if (checkChannelType(str4)) {
                    initUnicomData();
                    TYCJBoxManager.getInstance().collectWork(parseWorkData(str, str2, str3, str4), str4);
                } else if (this.workInterFace != null) {
                    this.workInterFace.upLoadFail(str4, getJsonMsg("1", "渠道id错误"));
                }
            } else if (this.workInterFace != null) {
                this.workInterFace.upLoadFail(str4, getJsonMsg("3", "渠道未配置或关闭"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCallBack(WorkInterFace workInterFace) {
        this.workInterFace = workInterFace;
    }

    private String parseWorkData(String str, String str2, String str3, String str4) {
        try {
            String str5 = TextUtils.isEmpty(str3) ? "00" : str3;
            if (!TextUtils.isEmpty(str)) {
                str5 = str5 + WPBusinessInfoBean.SPLIT + buildUnicomStr(str, str4);
            }
            if (TextUtils.isEmpty(str2)) {
                return str5;
            }
            return str5 + WPBusinessInfoBean.SPLIT + str2;
        } catch (Exception e) {
            e.printStackTrace();
            if (this.workInterFace != null) {
                this.workInterFace.upLoadFail(str4, getJsonMsg("2", "unicom解析错误"));
                return "";
            }
            return "";
        }
    }

    private String buildUnicomStr(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        try {
        } catch (Exception e) {
            e.printStackTrace();
            if (this.workInterFace != null) {
                this.workInterFace.upLoadFail(str2, getJsonMsg("2", "unicom解析错误"));
            }
        }
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] sortData = sortData(str, str2);
        for (int i = 0; i < sortData.length; i++) {
            String str3 = getUnicomDataMap().get(sortData[i]);
            if (TextUtils.isEmpty(str3)) {
                str3 = "-";
            }
            sb.append(str3);
            if (i != sortData.length - 1) {
                sb.append(WPBusinessInfoBean.SPLIT);
            }
        }
        return sb.toString();
    }

    private String[] sortData(String str, String str2) {
        try {
            String[] split = str.split("\\|\\|");
            int[] iArr = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                iArr[i] = Integer.parseInt(split[i].split("a")[1]);
            }
            int i2 = 0;
            while (i2 < iArr.length) {
                int i3 = i2 + 1;
                for (int i4 = i3; i4 < iArr.length; i4++) {
                    if (iArr[i2] > iArr[i4]) {
                        int i5 = iArr[i2];
                        iArr[i2] = iArr[i4];
                        iArr[i4] = i5;
                    }
                }
                i2 = i3;
            }
            this.newSortArr = new String[iArr.length];
            for (int i6 = 0; i6 < iArr.length; i6++) {
                this.newSortArr[i6] = "a" + iArr[i6];
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (this.workInterFace != null) {
                this.workInterFace.upLoadFail(str2, getJsonMsg("2", "unicom解析错误"));
            }
        }
        return this.newSortArr;
    }

    private String getJsonMsg(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", str);
            jSONObject.put("msg", str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private boolean checkChannelType(String str) {
        char c;
        if (!TextUtils.isEmpty(str)) {
            switch (str.hashCode()) {
                case -1771887215:
                    if (str.equals("tianYanH5")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case -1411089043:
                    if (str.equals("appOut")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case -1324747356:
                    if (str.equals("tianYan")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case -287015979:
                    if (str.equals("unicomF")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -287015963:
                    if (str.equals("unicomV")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -145996538:
                    if (str.equals("config_channel")) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                case 93028134:
                    if (str.equals("appIn")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1058298011:
                    if (str.equals("H5Click")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 2112529211:
                    if (str.equals("H5Info")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case '\b':
                    return false;
            }
        }
        return true;
    }
}
