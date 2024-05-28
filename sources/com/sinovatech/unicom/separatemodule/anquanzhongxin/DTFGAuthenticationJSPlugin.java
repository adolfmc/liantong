package com.sinovatech.unicom.separatemodule.anquanzhongxin;

import android.content.Context;
import android.content.Intent;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.p315ui.activity.LoginActivity;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EncodeHelper;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.security.LockPatternUtil;
import io.objectbox.Box;
import io.reactivex.functions.Consumer;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/DTFGAuthentication")
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DTFGAuthenticationJSPlugin extends BaseJSPlugin {
    private static final String BUSINESSCODE = "businessCode";
    private static final String BUSINESSDESC = "businessDesc";
    private static final String BUSINESSKEY = "businessKey";
    private CustomePorgressDialog porgressDialog;
    private final String TAG = "DTFGAuthenticationJSPlugin";
    private Box<AnquanzhongxinEntity> box = App.getBoxStore().boxFor(AnquanzhongxinEntity.class);
    private Box<AnquanWhiteEntity> anquanWhiteEntityBox = App.getBoxStore().boxFor(AnquanWhiteEntity.class);
    private String businessCode = "";

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        String optString = this.parameterJO.optString("action");
        if ("getInfo".equals(optString)) {
            JSONObject optJSONObject = this.parameterJO.optJSONObject("params");
            if (optJSONObject == null) {
                optJSONObject = new JSONObject();
            }
            final String optString2 = optJSONObject.optString(BUSINESSCODE);
            String optString3 = optJSONObject.optString(BUSINESSDESC);
            this.businessCode = optString2;
            if (TextUtils.isEmpty(optString2) || TextUtils.isEmpty(optString3)) {
                callFail("21");
                return;
            }
            final String businessKey = getBusinessKey(optString2, optString3);
            final JSONObject jSONObject = new JSONObject();
            jSONObject.put("deviceCode", DeviceHelper.getAnquanzhongxinDeviceID(optString2));
            final AnquanzhongxinEntity findFirst = this.box.query().equal(AnquanzhongxinEntity_.mobile, UserManager.getInstance().getCurrentPhoneNumber()).build().findFirst();
            new AnquanzhongxinManager((AppCompatActivity) this.activityContext).getWhiteList(new Consumer<List<AnquanWhiteEntity>>() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.DTFGAuthenticationJSPlugin.1
                @Override // io.reactivex.functions.Consumer
                public void accept(List<AnquanWhiteEntity> list) throws Exception {
                    int i;
                    boolean z;
                    boolean z2;
                    Iterator<AnquanWhiteEntity> it = list.iterator();
                    while (true) {
                        i = 1;
                        if (!it.hasNext()) {
                            z = false;
                            break;
                        } else if (optString2.equals(it.next().getCode())) {
                            z = true;
                            break;
                        }
                    }
                    if (z) {
                        AnquanzhongxinEntity anquanzhongxinEntity = findFirst;
                        if (anquanzhongxinEntity != null && anquanzhongxinEntity.isChooseGroup3() && (findFirst.isSelected3() || findFirst.isSelected4() || findFirst.isSelected5())) {
                            Iterator it2 = DTFGAuthenticationJSPlugin.this.anquanWhiteEntityBox.query().equal(AnquanWhiteEntity_.mobile, UserManager.getInstance().getCurrentPhoneNumber()).build().find().iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    z2 = false;
                                    break;
                                }
                                AnquanWhiteEntity anquanWhiteEntity = (AnquanWhiteEntity) it2.next();
                                if (optString2.equals(anquanWhiteEntity.getCode())) {
                                    z2 = anquanWhiteEntity.isSelected();
                                    break;
                                }
                            }
                            if (z2) {
                                jSONObject.put("code", "10");
                                jSONObject.put(DTFGAuthenticationJSPlugin.BUSINESSKEY, businessKey);
                                JSONObject jSONObject2 = new JSONObject();
                                int supportFinggerNum = DeviceHelper.getSupportFinggerNum();
                                int i2 = (supportFinggerNum == 0 && findFirst.isSelected3()) ? 0 : supportFinggerNum == 0 ? 1 : 2;
                                int i3 = !findFirst.isSelected4();
                                if (findFirst.isSelected5()) {
                                    jSONObject.put("code", "15");
                                    i = 0;
                                }
                                jSONObject2.put("touchId", i2 + "");
                                jSONObject2.put("faceId", "2");
                                jSONObject2.put("gesture", i3 + "");
                                jSONObject2.put("sms", i + "");
                                jSONObject.put("data", jSONObject2);
                            } else {
                                jSONObject.put("code", "12");
                                jSONObject.put(DTFGAuthenticationJSPlugin.BUSINESSKEY, businessKey);
                            }
                        } else {
                            jSONObject.put("code", "12");
                            jSONObject.put(DTFGAuthenticationJSPlugin.BUSINESSKEY, businessKey);
                            DTFGAuthenticationJSPlugin.this.callbackSuccess(jSONObject);
                            DTFGAuthenticationJSPlugin.this.log(jSONObject);
                        }
                    } else {
                        jSONObject.put("code", "11");
                    }
                    DTFGAuthenticationJSPlugin.this.callbackSuccess(jSONObject);
                    DTFGAuthenticationJSPlugin.this.log(jSONObject);
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.DTFGAuthenticationJSPlugin.2
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    MsLogUtil.m7979d("DTFGAuthenticationJSPlugin", "回传数据失败：" + th.getMessage());
                    DTFGAuthenticationJSPlugin.this.callFail("22");
                }
            });
        } else if ("set".equals(optString)) {
            JSONObject optJSONObject2 = this.parameterJO.optJSONObject("params");
            if (optJSONObject2 == null) {
                optJSONObject2 = new JSONObject();
            }
            JSONObject jiexiBusiness = jiexiBusiness(optJSONObject2.optString(BUSINESSKEY));
            final String optString4 = jiexiBusiness.optString(BUSINESSCODE);
            this.businessCode = optString4;
            String optString5 = jiexiBusiness.optString(BUSINESSDESC);
            boolean optBoolean = jiexiBusiness.optBoolean("timeIsOk");
            if (TextUtils.isEmpty(optString4) || TextUtils.isEmpty(optString5) || !optBoolean) {
                callFail("21");
                return;
            }
            Intent intent = new Intent(this.activityContext, AnquanzhongxinActivity.class);
            intent.putExtra(BUSINESSCODE, optString4);
            new AvoidOnResult(this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.DTFGAuthenticationJSPlugin.3
                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                public void onActivityResult(int i, Intent intent2) {
                    boolean z;
                    try {
                        AnquanzhongxinEntity anquanzhongxinEntity = (AnquanzhongxinEntity) DTFGAuthenticationJSPlugin.this.box.query().equal(AnquanzhongxinEntity_.mobile, UserManager.getInstance().getCurrentPhoneNumber()).build().findFirst();
                        if (anquanzhongxinEntity == null) {
                            anquanzhongxinEntity = new AnquanzhongxinEntity();
                        }
                        if (anquanzhongxinEntity.isChooseGroup3() && !anquanzhongxinEntity.isChooseGroup1() && (anquanzhongxinEntity.isSelected3() || anquanzhongxinEntity.isSelected4())) {
                            Iterator it = DTFGAuthenticationJSPlugin.this.anquanWhiteEntityBox.query().equal(AnquanWhiteEntity_.mobile, UserManager.getInstance().getCurrentPhoneNumber()).build().find().iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    z = false;
                                    break;
                                }
                                AnquanWhiteEntity anquanWhiteEntity = (AnquanWhiteEntity) it.next();
                                if (optString4.equals(anquanWhiteEntity.getCode())) {
                                    z = anquanWhiteEntity.isSelected();
                                    break;
                                }
                            }
                            if (!z) {
                                DTFGAuthenticationJSPlugin.this.callFail("12");
                            } else if (intent2 == null || !intent2.getBooleanExtra("userChecked", false)) {
                                DTFGAuthenticationJSPlugin.this.auth();
                            } else {
                                if (!anquanzhongxinEntity.isSelected3() && !anquanzhongxinEntity.isSelected4()) {
                                    DTFGAuthenticationJSPlugin.this.callFail("12");
                                    return;
                                }
                                DTFGAuthenticationJSPlugin.this.generateKey(anquanzhongxinEntity);
                            }
                        } else if (anquanzhongxinEntity.isSelected5()) {
                            DTFGAuthenticationJSPlugin.this.callFail("15");
                        } else {
                            DTFGAuthenticationJSPlugin.this.callFail("12");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        DTFGAuthenticationJSPlugin.this.callbackFail(e.getMessage());
                    }
                }
            });
        } else if ("authentication".equals(optString)) {
            JSONObject optJSONObject3 = this.parameterJO.optJSONObject("params");
            if (optJSONObject3 == null) {
                optJSONObject3 = new JSONObject();
            }
            JSONObject jiexiBusiness2 = jiexiBusiness(optJSONObject3.optString(BUSINESSKEY));
            String optString6 = jiexiBusiness2.optString(BUSINESSCODE);
            this.businessCode = optString6;
            String optString7 = jiexiBusiness2.optString(BUSINESSDESC);
            boolean optBoolean2 = jiexiBusiness2.optBoolean("timeIsOk");
            if (TextUtils.isEmpty(optString6) || TextUtils.isEmpty(optString7) || !optBoolean2) {
                callFail("21");
            } else {
                auth();
            }
        } else if ("cancel".equals(this.action)) {
            JSONObject optJSONObject4 = this.parameterJO.optJSONObject("params");
            if (optJSONObject4 == null) {
                optJSONObject4 = new JSONObject();
            }
            JSONObject jiexiBusiness3 = jiexiBusiness(optJSONObject4.optString(BUSINESSKEY));
            String optString8 = jiexiBusiness3.optString(BUSINESSCODE);
            this.businessCode = optString8;
            String optString9 = jiexiBusiness3.optString(BUSINESSDESC);
            boolean optBoolean3 = jiexiBusiness3.optBoolean("timeIsOk");
            if (TextUtils.isEmpty(optString8) || TextUtils.isEmpty(optString9) || !optBoolean3) {
                callFail("21");
                return;
            }
            for (AnquanWhiteEntity anquanWhiteEntity : this.anquanWhiteEntityBox.query().equal(AnquanWhiteEntity_.mobile, UserManager.getInstance().getCurrentPhoneNumber()).build().find()) {
                if (optString8.equals(anquanWhiteEntity.getCode())) {
                    anquanWhiteEntity.setSelected(false);
                    this.anquanWhiteEntityBox.remove(anquanWhiteEntity.getId());
                    this.anquanWhiteEntityBox.put((Box<AnquanWhiteEntity>) anquanWhiteEntity);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void auth() {
        final AnquanzhongxinEntity findFirst = this.box.query().equal(AnquanzhongxinEntity_.mobile, UserManager.getInstance().getCurrentPhoneNumber()).build().findFirst();
        if (findFirst == null) {
            findFirst = new AnquanzhongxinEntity();
        }
        if (findFirst.isSelected3()) {
            new FingerScreenManager(this.activityContext).startFingerprit(new FingerScreenManager.FingerCompleteInterface() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.DTFGAuthenticationJSPlugin.4
                @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager.FingerCompleteInterface
                public void success() {
                    DTFGAuthenticationJSPlugin.this.generateKey(findFirst);
                }

                @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager.FingerCompleteInterface
                public void fail() {
                    DTFGAuthenticationJSPlugin.this.callFail("24");
                }

                @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager.FingerCompleteInterface
                public void reset() {
                    DeviceHelper.resetAnquanzhongxinDeviceID(DTFGAuthenticationJSPlugin.this.businessCode);
                    findFirst.setSelected3(false);
                    findFirst.setSelected4(false);
                    findFirst.setSelected5(false);
                    DTFGAuthenticationJSPlugin.this.box.remove(findFirst.getId());
                    DTFGAuthenticationJSPlugin.this.box.put((Box) findFirst);
                    LockPatternUtil.clearLockPattern(DTFGAuthenticationJSPlugin.this.activityContext, UserManager.getInstance().getCurrentPhoneNumber());
                    MsLogUtil.m7979d("", "重置了手势和指纹，更新数据库--" + findFirst);
                    UserManager.getInstance().removeSelectAccountName(UserManager.getInstance().getCurrentPhoneNumber());
                    Intent intent = new Intent(DTFGAuthenticationJSPlugin.this.activityContext, LoginActivity.class);
                    intent.putExtra("from", "LoginBindActivity");
                    intent.putExtra("logintype", UserManager.getInstance().getCurrentPhoneNumber());
                    intent.putExtra("fromLockPattern", "fromLockPattern");
                    intent.putExtra("fromLockPatternbackground", "fromLockPatternbackground");
                    DTFGAuthenticationJSPlugin.this.activityContext.startActivity(intent);
                    LoginManager.logout(DTFGAuthenticationJSPlugin.this.activityContext);
                }

                @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager.FingerCompleteInterface
                public void cancel() {
                    DTFGAuthenticationJSPlugin.this.callFail("23");
                }
            });
        } else if (findFirst.isSelected4()) {
            LockPatternUtil.compareLockPattern(this.activityContext, UserManager.getInstance().getCurrentPhoneNumber(), true, this.businessCode, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.-$$Lambda$DTFGAuthenticationJSPlugin$Tq8u1_Wqxu0deNJPH64Kce3JlO4
                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                public final void onActivityResult(int i, Intent intent) {
                    DTFGAuthenticationJSPlugin.lambda$auth$0(DTFGAuthenticationJSPlugin.this, findFirst, i, intent);
                }
            });
        } else {
            callFail("21");
        }
    }

    public static /* synthetic */ void lambda$auth$0(DTFGAuthenticationJSPlugin dTFGAuthenticationJSPlugin, AnquanzhongxinEntity anquanzhongxinEntity, int i, Intent intent) {
        if (i == -1) {
            dTFGAuthenticationJSPlugin.generateKey(anquanzhongxinEntity);
        } else {
            dTFGAuthenticationJSPlugin.callFail("23");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void generateKey(final AnquanzhongxinEntity anquanzhongxinEntity) {
        String str;
        String str2;
        final String sign = anquanzhongxinEntity.getSign();
        String str3 = "";
        String str4 = "0";
        if (anquanzhongxinEntity.isSelected4()) {
            str3 = "1";
            if (anquanzhongxinEntity.getSignWay() != 1) {
                str4 = "1";
            }
        }
        if (!anquanzhongxinEntity.isSelected3()) {
            str = str3;
            str2 = str4;
        } else if (anquanzhongxinEntity.getSignWay() != 3) {
            str = "3";
            str2 = "1";
        } else {
            str = "3";
            str2 = str4;
        }
        if (TextUtils.isEmpty(sign) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            this.box.remove(anquanzhongxinEntity.getId());
        }
        if (this.porgressDialog == null) {
            this.porgressDialog = new CustomePorgressDialog(this.activityContext);
        }
        this.porgressDialog.setMessage("正在请求数据");
        if (UIUtils.isShowDialog(this.activityContext, this.porgressDialog)) {
            this.porgressDialog.show();
        }
        new AnquanzhongxinManager((AppCompatActivity) this.activityContext).getGenerateKey("", sign, str, str2, new Consumer<String>() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.DTFGAuthenticationJSPlugin.5
            @Override // io.reactivex.functions.Consumer
            public void accept(String str5) throws Exception {
                if (UIUtils.isDismissDialog(DTFGAuthenticationJSPlugin.this.activityContext, DTFGAuthenticationJSPlugin.this.porgressDialog)) {
                    DTFGAuthenticationJSPlugin.this.porgressDialog.dismiss();
                }
                JSONObject jSONObject = new JSONObject(str5);
                String optString = jSONObject.optString("code");
                jSONObject.optString("msg");
                String optString2 = jSONObject.optString("data");
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("deviceCode", DeviceHelper.getAnquanzhongxinDeviceID(DTFGAuthenticationJSPlugin.this.businessCode));
                if ("0000".equals(optString)) {
                    jSONObject2.put("code", "10");
                    jSONObject2.put("authCode", optString2);
                    DTFGAuthenticationJSPlugin.this.callbackSuccess(jSONObject2);
                    anquanzhongxinEntity.setSign(optString2);
                    if (anquanzhongxinEntity.isSelected3()) {
                        anquanzhongxinEntity.setSignWay(3);
                    }
                    if (anquanzhongxinEntity.isSelected4()) {
                        anquanzhongxinEntity.setSignWay(1);
                    }
                    DTFGAuthenticationJSPlugin.this.setEntity(anquanzhongxinEntity);
                    DTFGAuthenticationJSPlugin.this.log(jSONObject2);
                } else if (!"0003".equals(optString) && !"2333".equals(optString)) {
                    DTFGAuthenticationJSPlugin.this.callFail("22");
                } else {
                    if (TextUtils.isEmpty(optString2)) {
                        jSONObject2.put("authCode", sign);
                        anquanzhongxinEntity.setSign(sign);
                    } else {
                        jSONObject2.put("authCode", optString2);
                        anquanzhongxinEntity.setSign(optString2);
                    }
                    jSONObject2.put("code", "10");
                    DTFGAuthenticationJSPlugin.this.callbackSuccess(jSONObject2);
                    DTFGAuthenticationJSPlugin.this.log(jSONObject2);
                    StringBuilder sb = new StringBuilder();
                    sb.append("回传验证结果：");
                    sb.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                    MsLogUtil.m7979d("DTFGAuthenticationJSPlugin", sb.toString());
                    if (anquanzhongxinEntity.isSelected3()) {
                        anquanzhongxinEntity.setSignWay(3);
                    }
                    if (anquanzhongxinEntity.isSelected4()) {
                        anquanzhongxinEntity.setSignWay(1);
                    }
                }
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.DTFGAuthenticationJSPlugin.6
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                if (UIUtils.isDismissDialog(DTFGAuthenticationJSPlugin.this.activityContext, DTFGAuthenticationJSPlugin.this.porgressDialog)) {
                    DTFGAuthenticationJSPlugin.this.porgressDialog.dismiss();
                }
                UIUtils.toast("校验失败~");
                DTFGAuthenticationJSPlugin.this.callFail("22");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEntity(AnquanzhongxinEntity anquanzhongxinEntity) {
        try {
            if (anquanzhongxinEntity.getId() != 0) {
                this.box.remove(anquanzhongxinEntity.getId());
            }
            this.box.put((Box<AnquanzhongxinEntity>) anquanzhongxinEntity);
            MsLogUtil.m7979d("DTFGAuthenticationJSPlugin", "设置缓存---" + getEntity().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private AnquanzhongxinEntity getEntity() {
        AnquanzhongxinEntity findFirst = this.box.query().equal(AnquanzhongxinEntity_.mobile, UserManager.getInstance().getCurrentPhoneNumber()).build().findFirst();
        if (findFirst == null) {
            findFirst = new AnquanzhongxinEntity();
            findFirst.setMobile(UserManager.getInstance().getCurrentPhoneNumber());
            findFirst.setChooseGroup1(true);
            findFirst.setGroupChooseNumber(1);
        }
        MsLogUtil.m7979d("DTFGAuthenticationJSPlugin", "读取缓存---" + findFirst.toString());
        return findFirst;
    }

    private String getBusinessKey(String str, String str2) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(",");
        sb.append(str2);
        sb.append(",");
        sb.append(System.currentTimeMillis());
        sb.append(",");
        sb.append(random.nextInt(9) + "" + random.nextInt(9) + "" + random.nextInt(9) + "" + random.nextInt(9) + "" + random.nextInt(9) + "" + random.nextInt(9));
        MsLogUtil.m7979d("DTFGAuthenticationJSPlugin", "加密后:" + sb.toString());
        return EncodeHelper.encodeByAESAnquanzhongxin(sb.toString());
    }

    private JSONObject jiexiBusiness(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            String decodeByAESAnquanzhongxin = EncodeHelper.decodeByAESAnquanzhongxin(str);
            MsLogUtil.m7979d("DTFGAuthenticationJSPlugin", "解密后:" + decodeByAESAnquanzhongxin);
            String[] split = decodeByAESAnquanzhongxin.split(",");
            String str2 = split[0];
            String str3 = split[1];
            long parseLong = Long.parseLong(split[2]);
            jSONObject.put(BUSINESSCODE, str2);
            jSONObject.put(BUSINESSDESC, str3);
            if (System.currentTimeMillis() - parseLong > 600000) {
                jSONObject.put("timeIsOk", false);
            } else {
                jSONObject.put("timeIsOk", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callFail(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("code", str);
            jSONObject.put("deviceCode", DeviceHelper.getAnquanzhongxinDeviceID(this.businessCode));
            callbackSuccess(jSONObject);
            log(jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail(e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = this.parameterJO;
            PvCurrencyLogUtils.pvAnquanzhongxin(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2), !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
