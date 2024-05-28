package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.CardResult;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.EnumReaderType;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.SmartCard;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.utils.LogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.utils.OmapiUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.log.CumpLogEnu;
import com.sinovatech.unicom.separatemodule.miniprogram.log.CumpLogManager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/OMApi")
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class OmapiJSPlugin extends BaseJSPlugin {
    private static final String TAG = "com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.OmapiJSPlugin";
    private String aid;
    private JSONArray apduListArray = null;
    private Handler handler;
    private String type;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        execApdu();
    }

    private void execApdu() {
        try {
            if (this.parameterJO != null) {
                this.type = this.parameterJO.optString("type", "");
                this.aid = this.parameterJO.optString("aid", "");
                this.apduListArray = this.parameterJO.optJSONArray("apduList");
            }
            if (TextUtils.isEmpty(this.type)) {
                LogUtil.m7990d("type参数不能为空!");
                addCardResult("type参数不能为空！");
                return;
            }
            this.handler = new Handler();
            this.webFragment.addLifeListener("/MsJSPlugin/OMApi", new BaseWebFragment.LifeListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.OmapiJSPlugin.1
                @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment.LifeListener
                public void onDestory() {
                    OmapiUtils.closeSEService();
                }
            });
            new Thread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.OmapiJSPlugin.2
                @Override // java.lang.Runnable
                public void run() {
                    char c;
                    SmartCard.getInstance().setmReaderType(EnumReaderType.READER_TYPE_SIM);
                    ArrayList arrayList = new ArrayList();
                    String str = OmapiJSPlugin.this.type;
                    int hashCode = str.hashCode();
                    if (hashCode == -1470128183) {
                        if (str.equals("openChannelOnExec")) {
                            c = 0;
                        }
                        c = 65535;
                    } else if (hashCode == -764521765) {
                        if (str.equals("closeChannelOnExec")) {
                            c = 2;
                        }
                        c = 65535;
                    } else if (hashCode != -530515522) {
                        if (hashCode == -386385152 && str.equals("transmitApduOnExec")) {
                            c = 1;
                        }
                        c = 65535;
                    } else {
                        if (str.equals("getSimAllInfoOnExec")) {
                            c = 3;
                        }
                        c = 65535;
                    }
                    switch (c) {
                        case 0:
                            if (!TextUtils.isEmpty(OmapiJSPlugin.this.aid)) {
                                CardResult openAid = OmapiUtils.openAid(OmapiJSPlugin.this.aid);
                                String str2 = OmapiJSPlugin.TAG;
                                LogUtil.m7989d(str2, "打开通道成功：" + openAid.toString());
                                arrayList.add(openAid);
                                OmapiJSPlugin.this.callbackSucces(arrayList);
                                return;
                            }
                            LogUtil.m7990d("aid参数不能为空!");
                            OmapiJSPlugin.this.addCardResult("aid参数不能为空！");
                            return;
                        case 1:
                            if (OmapiJSPlugin.this.apduListArray != null && OmapiJSPlugin.this.apduListArray.length() > 0) {
                                List<CardResult> cardResultList = OmapiUtils.getCardResultList(OmapiJSPlugin.this.apduListArray);
                                String str3 = OmapiJSPlugin.TAG;
                                LogUtil.m7989d(str3, "发送指令TRANSMIT_APDU_ON_EXEC：" + cardResultList);
                                OmapiJSPlugin.this.callbackSucces(cardResultList);
                                return;
                            }
                            LogUtil.m7990d("apduList数组不能为空!");
                            OmapiJSPlugin.this.addCardResult("apduList数组不能为空！");
                            return;
                        case 2:
                            CardResult closeAid = OmapiUtils.closeAid();
                            String str4 = OmapiJSPlugin.TAG;
                            LogUtil.m7989d(str4, "关闭通道指令成功：" + closeAid.toString());
                            arrayList.add(closeAid);
                            OmapiJSPlugin.this.callbackSucces(arrayList);
                            return;
                        case 3:
                            if (TextUtils.isEmpty(OmapiJSPlugin.this.aid)) {
                                LogUtil.m7989d(OmapiJSPlugin.TAG, "aid参数不能为空!");
                                OmapiJSPlugin.this.addCardResult("aid参数不能为空！");
                                return;
                            } else if (OmapiJSPlugin.this.apduListArray != null && OmapiJSPlugin.this.apduListArray.length() > 0) {
                                List<CardResult> cardAllList = OmapiUtils.getCardAllList(OmapiJSPlugin.this.aid, OmapiJSPlugin.this.apduListArray);
                                String str5 = OmapiJSPlugin.TAG;
                                LogUtil.m7989d(str5, "发送指令GET_SIM_ALL_INFO_ON_EXEC：" + cardAllList);
                                OmapiJSPlugin.this.callbackSucces(cardAllList);
                                return;
                            } else {
                                LogUtil.m7990d("apduList数组不能为空!");
                                OmapiJSPlugin.this.addCardResult("apduList数组不能为空！");
                                return;
                            }
                        default:
                            return;
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
            String str = TAG;
            LogUtil.m7987e(str, "异常：" + e.getMessage());
            addCardResult("omapi异常:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackSucces(List<CardResult> list) {
        final JSONArray jSONArray = new JSONArray();
        if (list != null) {
            try {
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("status", list.get(i).getStatus());
                        jSONObject.put("resApdu", list.get(i).getApdu());
                        jSONObject.put("resRapdu", list.get(i).getRapdu());
                        jSONObject.put("resSw", list.get(i).getSw());
                        jSONObject.put("msg", list.get(i).getMsg());
                        jSONArray.put(jSONObject);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        this.handler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.OmapiJSPlugin.3
            @Override // java.lang.Runnable
            public void run() {
                OmapiJSPlugin omapiJSPlugin = OmapiJSPlugin.this;
                JSONArray jSONArray2 = jSONArray;
                omapiJSPlugin.addLog(!(jSONArray2 instanceof JSONArray) ? jSONArray2.toString() : NBSJSONArrayInstrumentation.toString(jSONArray2));
                OmapiJSPlugin.this.callbackSuccess(jSONArray);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addCardResult(String str) {
        ArrayList arrayList = new ArrayList();
        CardResult cardResult = new CardResult();
        cardResult.setMsg(str);
        cardResult.setStatus("10");
        arrayList.add(cardResult);
        callbackSucces(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addLog(String str) {
        try {
            String str2 = "";
            if (this.parameterJO != null) {
                JSONObject jSONObject = this.parameterJO;
                str2 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
            }
            String str3 = !TextUtils.isEmpty(this.type) ? this.type : "";
            CumpLogManager.getInstance(this.activityContext).log_5gsimOma(this.logStartDate, new Date(), getCurrentCumpAppId(), this.logAppName, str3, str2 + "########" + str, CumpLogEnu.urlStatus_Success, this.logNewAppVersion);
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }
}
