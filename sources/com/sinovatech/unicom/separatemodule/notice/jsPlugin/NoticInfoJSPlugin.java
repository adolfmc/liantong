package com.sinovatech.unicom.separatemodule.notice.jsPlugin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.p086v7.app.AppCompatActivity;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.sinovatech.unicom.basic.eventbus.NoticEvent;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.notice.NoticManager;
import com.sinovatech.unicom.separatemodule.notice.NoticeConfigEntity;
import com.sinovatech.unicom.separatemodule.notice.PushMessageEntity;
import com.sinovatech.unicom.separatemodule.notice.PushMsgDao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
@Route(path = "/MsJSPlugin/noticInfo")
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NoticInfoJSPlugin extends BaseJSPlugin {
    private int allCountMessage = 500;
    private AppCompatActivity appCompatActivity;
    private int num;
    private PushMsgDao pushMsgDao;
    private String type;

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x045d: CAST (r15 I:double) = (double) (r4 I:int), expected to be less than 11
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    private java.lang.String exec(boolean r10) {
        /*
            Method dump skipped, instructions count: 1221
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.notice.jsPlugin.NoticInfoJSPlugin.exec(boolean):java.lang.String");
    }

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    @SuppressLint({"CheckResult"})
    public void onExec() throws Exception {
        exec(false);
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        return exec(true);
    }

    private JSONArray getListJSONArray(List<PushMessageEntity> list) {
        JSONArray jSONArray;
        Gson gson = new Gson();
        try {
            List<PushMessageEntity> paserTimeList = paserTimeList(list);
            jSONArray = new JSONArray(!(gson instanceof Gson) ? gson.toJson(paserTimeList) : NBSGsonInstrumentation.toJson(gson, paserTimeList));
            UIUtils.logD("getListJSONArray", !(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray));
        } catch (Exception e) {
            e.printStackTrace();
            jSONArray = new JSONArray();
        }
        UIUtils.logD("getNoticList", "1-------------" + System.currentTimeMillis());
        return jSONArray;
    }

    private void deleteAll(List<String> list) {
        try {
            Iterator<PushMessageEntity> it = this.pushMsgDao.getPushMessageRecord("0", "0").iterator();
            while (it.hasNext()) {
                PushMessageEntity next = it.next();
                if (list.contains(next.getId())) {
                    this.pushMsgDao.deletePushMessageRecord(next);
                    it.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void changeUIState() {
        try {
            EventBusUtils.post(new NoticEvent(EventBusUtils.EVENT_NOTIC));
            App.refreshXiaohongdianClear = true;
            App.refreshXiaohongdianClear2 = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<PushMessageEntity> paserTimeList(List<PushMessageEntity> list) {
        ArrayList arrayList = new ArrayList();
        NoticeConfigEntity noticeEntity = NoticManager.getNoticeEntity();
        ArrayList arrayList2 = new ArrayList();
        for (NoticeConfigEntity.FirstLevelDTO firstLevelDTO : noticeEntity.getFirstLevel()) {
            arrayList2.add(firstLevelDTO.getFirstLevelNo());
        }
        for (int i = 0; i < list.size(); i++) {
            PushMessageEntity pushMessageEntity = list.get(i);
            if (!arrayList2.contains(pushMessageEntity.getMsgType())) {
                this.pushMsgDao.deletePushMessageRecord(pushMessageEntity);
            } else {
                if (!"1".equals(pushMessageEntity.getStatus())) {
                    int i2 = this.num;
                    if (i2 >= 3) {
                        pushMessageEntity.setStatus("1");
                        this.pushMsgDao.updatePushMessageRecordStatus(pushMessageEntity.getId(), "1");
                    } else {
                        this.num = i2 + 1;
                    }
                }
                arrayList.add(pushMessageEntity);
            }
        }
        MsLogUtil.m7979d("paserTimeList", "列表长度：" + list.size() + "   结束排序：---" + System.currentTimeMillis());
        return arrayList;
    }
}
