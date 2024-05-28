package com.sinovatech.unicom.separatemodule.gamedistribution;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.OkDownload;
import com.liulishuo.okdownload.StatusUtil;
import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.tms.TMSUtil;
import java.io.File;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/getGameDownloadTask")
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GetGameDownloadTaskJSPlugin extends BaseJSPlugin {
    private File parentFile;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("请求创建游戏分发任务-onExec：");
        JSONObject jSONObject = this.parameterJO;
        sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        MsLogUtil.m7980d(sb.toString());
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        String str;
        String str2;
        long j;
        String str3;
        String str4;
        String str5;
        long j2;
        StringBuilder sb = new StringBuilder();
        sb.append("请求创建游戏分发任务-onExecSync：");
        JSONObject jSONObject = this.parameterJO;
        sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        MsLogUtil.m7980d(sb.toString());
        this.parentFile = GameDownloadUtils.createDic();
        String string = this.parameterJO.getString("mainTitle");
        String string2 = this.parameterJO.getString("downloadUrl");
        String string3 = this.parameterJO.getString("pkgName");
        long j3 = 0;
        if (TMSUtil.isPkgInstalled(App.getInstance().getApplicationContext(), string3)) {
            str5 = "";
            str4 = "";
            str3 = "INSTALLED";
            j2 = 0;
        } else {
            DownloadTask createDownloadTask = GameDownloadUtils.createDownloadTask(this.parentFile, string3, string2);
            BreakpointInfo breakpointInfo = OkDownload.with().breakpointStore().get(createDownloadTask.getId());
            if (breakpointInfo != null) {
                j3 = breakpointInfo.getTotalLength();
                j = breakpointInfo.getTotalOffset();
                str = Util.humanReadableBytes(j3, true);
                str2 = Util.humanReadableBytes(j, true);
            } else {
                str = "";
                str2 = "";
                j = 0;
            }
            if (StatusUtil.isCompleted(createDownloadTask)) {
                str3 = "COMPLETED";
                str4 = str2;
                str5 = str;
                j2 = j;
            } else if (StatusUtil.getStatus(createDownloadTask) == StatusUtil.Status.PENDING) {
                str3 = "PENDING";
                str4 = str2;
                str5 = str;
                j2 = j;
            } else if (StatusUtil.getStatus(createDownloadTask) == StatusUtil.Status.RUNNING) {
                str3 = "RUNNING";
                str4 = str2;
                str5 = str;
                j2 = j;
            } else {
                str3 = "CANCEL";
                str4 = str2;
                str5 = str;
                j2 = j;
            }
        }
        GameDownloadTask gameDownloadTask = new GameDownloadTask(string, string3, string2, j3, j2, str5, str4, "", str3);
        Gson gson = new Gson();
        String json = !(gson instanceof Gson) ? gson.toJson(gameDownloadTask) : NBSGsonInstrumentation.toJson(gson, gameDownloadTask);
        MsLogUtil.m7980d("返回游戏分发任务：" + json);
        return callbackSuccessSync(new JSONObject(json));
    }
}
