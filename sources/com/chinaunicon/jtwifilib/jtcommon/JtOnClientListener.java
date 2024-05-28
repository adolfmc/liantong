package com.chinaunicon.jtwifilib.jtcommon;

import android.content.Context;
import com.chinaunicon.jtwifilib.core.utils.JtGsonUtil;
import com.chinaunicon.jtwifilib.core.utils.JtL;
import com.chinaunicon.jtwifilib.jtcommon.model.JtBaseModel;
import com.chinaunicon.jtwifilib.jtcommon.model.Register1Bean;
import com.chinaunicon.jtwifilib.jtcommon.util.JtUploadLog;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class JtOnClientListener implements JtOnConnectListener {
    private Context context;

    @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnConnectListener
    public void downLoadHtmlProgress(int i) {
    }

    public JtOnClientListener(Context context) {
        this.context = context;
    }

    @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnConnectListener
    public void onClose(String str) {
        Register1Bean register1Bean = new Register1Bean();
        register1Bean.setCmdType("on_close");
        register1Bean.setStatus("0");
        register1Bean.setMsg(str);
        JtUploadLog.getInstance(this.context).updateData("2", JtGsonUtil.getInstance().toJson(register1Bean), "on_close");
    }

    @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnConnectListener
    public void onError(String str) {
        Register1Bean register1Bean = new Register1Bean();
        register1Bean.setCmdType("on_error");
        register1Bean.setStatus("-1");
        register1Bean.setMsg(str);
        JtUploadLog.getInstance(this.context).updateData("2", JtGsonUtil.getInstance().toJson(register1Bean), "on_error");
    }

    @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnConnectListener
    public void onMessage(String str) {
        JtBaseModel jtBaseModel;
        JtL.m16342e("---------收到消息了-------");
        try {
            jtBaseModel = (JtBaseModel) JtGsonUtil.getInstance().fromJson(str, (Class<Object>) JtBaseModel.class);
        } catch (Exception unused) {
            jtBaseModel = null;
        }
        if (jtBaseModel != null) {
            Register1Bean register1Bean = new Register1Bean();
            register1Bean.setCmdType(jtBaseModel.getCmdType());
            register1Bean.setStatus("0");
            register1Bean.setMsg(str);
            JtUploadLog.getInstance(this.context).updateData("1", JtGsonUtil.getInstance().toJson(register1Bean), jtBaseModel.getCmdType());
            return;
        }
        Register1Bean register1Bean2 = new Register1Bean();
        register1Bean2.setCmdType("on_message");
        register1Bean2.setStatus("0");
        register1Bean2.setMsg(str);
        JtUploadLog.getInstance(this.context).updateData("1", JtGsonUtil.getInstance().toJson(register1Bean2), "on_message");
    }

    @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnConnectListener
    public void startDowloadHtml() {
        Register1Bean register1Bean = new Register1Bean();
        register1Bean.setCmdType("download_html_start");
        register1Bean.setStatus("0");
        register1Bean.setMsg("开始下载");
        JtUploadLog.getInstance(this.context).updateData("1", JtGsonUtil.getInstance().toJson(register1Bean), "download_html_start");
    }

    @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnConnectListener
    public void dowloadHtmlSuccess(String str) {
        Register1Bean register1Bean = new Register1Bean();
        register1Bean.setCmdType("download_html_success");
        register1Bean.setStatus("0");
        register1Bean.setMsg("下载成功：" + str);
        JtUploadLog.getInstance(this.context).updateData("1", JtGsonUtil.getInstance().toJson(register1Bean), "download_html_success");
    }

    @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnConnectListener
    public void downloadHtmlFiled(Exception exc) {
        Register1Bean register1Bean = new Register1Bean();
        register1Bean.setCmdType("download_html_error");
        register1Bean.setStatus("-1");
        register1Bean.setMsg("下载异常" + exc.getMessage());
        JtUploadLog.getInstance(this.context).updateData("2", JtGsonUtil.getInstance().toJson(register1Bean), "download_html_error");
    }

    @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnConnectListener
    public void unZipStat() {
        Register1Bean register1Bean = new Register1Bean();
        register1Bean.setCmdType("download_unzip_start");
        register1Bean.setStatus("0");
        register1Bean.setMsg("开始解压");
        JtUploadLog.getInstance(this.context).updateData("1", JtGsonUtil.getInstance().toJson(register1Bean), "download_unzip_start");
    }

    @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnConnectListener
    public void unZipSuccess() {
        Register1Bean register1Bean = new Register1Bean();
        register1Bean.setCmdType("download_unzip_success");
        register1Bean.setStatus("0");
        register1Bean.setMsg("解压成功！");
        JtUploadLog.getInstance(this.context).updateData("1", JtGsonUtil.getInstance().toJson(register1Bean), "download_unzip_success");
    }

    @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnConnectListener
    public void unZipFiled(Exception exc) {
        Register1Bean register1Bean = new Register1Bean();
        register1Bean.setCmdType("download_unzip_error");
        register1Bean.setStatus("-1");
        register1Bean.setMsg("解压失败！" + exc.getMessage());
        JtUploadLog.getInstance(this.context).updateData("1", JtGsonUtil.getInstance().toJson(register1Bean), "download_unzip_error");
    }

    @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnConnectListener
    public void finish() {
        Register1Bean register1Bean = new Register1Bean();
        register1Bean.setCmdType("download_unzip_finish");
        register1Bean.setStatus("0");
        register1Bean.setMsg("下载解压完成");
        JtUploadLog.getInstance(this.context).updateData("1", JtGsonUtil.getInstance().toJson(register1Bean), "download_unzip_finish");
    }
}
