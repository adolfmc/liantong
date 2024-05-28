package com.sinovatech.unicom.separatemodule.advtise.service;

import android.text.TextUtils;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.service.TaskModel;
import com.sinovatech.unicom.separatemodule.chuanshanjia.SignUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class TaskModel {
    private AdConfigEntity entity;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface ITaskQueryInterface {
        void onComplete(TaskQueryBean taskQueryBean);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface ITaskSendInterface {
        void onComplete(TaskSendBean taskSendBean);
    }

    public TaskModel(AdConfigEntity adConfigEntity) {
        this.entity = adConfigEntity;
    }

    public void getTaskInfo(final ITaskQueryInterface iTaskQueryInterface) {
        if (TextUtils.isEmpty(this.entity.getAcId()) || TextUtils.isEmpty(this.entity.getTaskId())) {
            iTaskQueryInterface.onComplete(null);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("arguments1", this.entity.getAcId());
        hashMap.put("arguments2", this.entity.getChannel());
        hashMap.put("arguments3", this.entity.getTaskId());
        hashMap.put("arguments4", System.currentTimeMillis() + "");
        hashMap.put("sign", SignUtils.getSign(hashMap));
        hashMap.put("arguments6", this.entity.getAccountChannel());
        hashMap.put("netWay", DeviceHelper.getNETType(App.getInstance()));
        hashMap.put("version", App.getInstance().getString(2131886969));
        App.getAsyncHttpClient().rxPost(URLSet.getToutiaoTask(), hashMap, 3, 0).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.advtise.service.-$$Lambda$TaskModel$s7DCsDuWlKj6jX4cSBTTOMJquAI
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TaskModel.lambda$getTaskInfo$0(TaskModel.ITaskQueryInterface.this, (String) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.advtise.service.-$$Lambda$TaskModel$v_rp-KynMYeaXOYkjBq_BjBJ7gE
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TaskModel.lambda$getTaskInfo$1(TaskModel.ITaskQueryInterface.this, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getTaskInfo$0(ITaskQueryInterface iTaskQueryInterface, String str) throws Exception {
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("code");
        String optString2 = jSONObject.optString("timeflag");
        String optString3 = jSONObject.optString("desc");
        TaskQueryBean taskQueryBean = new TaskQueryBean();
        taskQueryBean.setCode(optString);
        taskQueryBean.setTimeFlag(optString2);
        if ("1".equals(optString2)) {
            optString3 = "今日您参加活动已达上限，观看将不赠送积分";
        }
        if (TextUtils.isEmpty(optString3)) {
            optString3 = "";
        }
        taskQueryBean.setDesc(optString3);
        iTaskQueryInterface.onComplete(taskQueryBean);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getTaskInfo$1(ITaskQueryInterface iTaskQueryInterface, Throwable th) throws Exception {
        TaskQueryBean taskQueryBean = new TaskQueryBean();
        taskQueryBean.setCode("9999");
        taskQueryBean.setTimeFlag("2");
        taskQueryBean.setDesc("领取积分任务失败" + th.getMessage());
        iTaskQueryInterface.onComplete(taskQueryBean);
    }

    public void taskComplete(String str, final ITaskSendInterface iTaskSendInterface) {
        HashMap hashMap = new HashMap();
        hashMap.put("arguments1", this.entity.getAcId());
        hashMap.put("arguments2", this.entity.getChannel());
        hashMap.put("arguments3", this.entity.getTaskId());
        hashMap.put("arguments4", System.currentTimeMillis() + "");
        hashMap.put("sign", SignUtils.getSign(hashMap));
        hashMap.put("remark", this.entity.getRemark());
        hashMap.put("arguments6", this.entity.getAccountChannel());
        hashMap.put("arguments7", this.entity.getAccountUserName());
        hashMap.put("arguments8", this.entity.getAccountPassword());
        hashMap.put("arguments9", this.entity.getAccountToken());
        hashMap.put("orderId", str);
        hashMap.put("netWay", DeviceHelper.getNETType(App.getInstance()));
        hashMap.put("version", App.getInstance().getString(2131886969));
        App.getAsyncHttpClient().rxPost(URLSet.sendCompleteTask(), hashMap, 3, 0).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.advtise.service.-$$Lambda$TaskModel$gR9ZQYpNr3hQOAMywyQL-9WSewk
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TaskModel.lambda$taskComplete$2(TaskModel.ITaskSendInterface.this, (String) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.advtise.service.-$$Lambda$TaskModel$uaDrEV5tfg_rAXDDY-tvjoplXKs
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                TaskModel.ITaskSendInterface.this.onComplete(null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$taskComplete$2(ITaskSendInterface iTaskSendInterface, String str) throws Exception {
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("code");
        String optString2 = jSONObject.optString("desc");
        String optString3 = jSONObject.optString("prizeCount");
        TaskSendBean taskSendBean = new TaskSendBean();
        taskSendBean.setCode(optString);
        taskSendBean.setDesc(optString2);
        taskSendBean.setPrizeCount(optString3);
        iTaskSendInterface.onComplete(taskSendBean);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class TaskQueryBean {
        private String code;
        private String desc;
        private String timeFlag;

        public String getCode() {
            return this.code;
        }

        public void setCode(String str) {
            this.code = str;
        }

        public String getTimeFlag() {
            return this.timeFlag;
        }

        public void setTimeFlag(String str) {
            this.timeFlag = str;
        }

        public String getDesc() {
            return this.desc;
        }

        public void setDesc(String str) {
            this.desc = str;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class TaskSendBean {
        private String code;
        private String desc;
        private String prizeCount;

        public String getCode() {
            return this.code;
        }

        public void setCode(String str) {
            this.code = str;
        }

        public String getPrizeCount() {
            return this.prizeCount;
        }

        public void setPrizeCount(String str) {
            this.prizeCount = str;
        }

        public String getDesc() {
            return this.desc;
        }

        public void setDesc(String str) {
            this.desc = str;
        }
    }
}
