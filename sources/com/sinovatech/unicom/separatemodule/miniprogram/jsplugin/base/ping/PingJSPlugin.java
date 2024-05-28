package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.ping;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/ping")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PingJSPlugin extends BaseJSPlugin {
    private Handler handler;
    private String host;
    private boolean isContinuePing;
    private String pingTimeout;
    private String sequenceNumberMax;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            this.pingTimeout = "";
            this.host = "";
            this.sequenceNumberMax = "";
            this.isContinuePing = true;
            this.handler = new Handler();
            if (this.parameterJO != null) {
                this.pingTimeout = this.parameterJO.optString("pingTimeout", "");
                this.host = this.parameterJO.optString("host", "");
                this.sequenceNumberMax = this.parameterJO.optString("sequenceNumberMax", "");
            }
            if (TextUtils.isEmpty(this.host)) {
                callbackFail("host参数不能为空！");
            } else if (TextUtils.isEmpty(this.pingTimeout)) {
                callbackFail("pingTimeout参数不能为空！");
            } else if (TextUtils.isEmpty(this.sequenceNumberMax)) {
                callbackFail("sequenceNumberMax参数不能为空！");
            } else {
                if (isNumeric(this.pingTimeout)) {
                    int parseInt = Integer.parseInt(this.pingTimeout);
                    if (parseInt <= 0 || parseInt > 10) {
                        this.pingTimeout = "10";
                    }
                } else {
                    this.pingTimeout = "10";
                }
                if (isNumeric(this.sequenceNumberMax)) {
                    int parseInt2 = Integer.parseInt(this.sequenceNumberMax);
                    if (parseInt2 <= 0 || parseInt2 > 10) {
                        this.sequenceNumberMax = "10";
                    }
                } else {
                    this.sequenceNumberMax = "10";
                }
                new Thread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.ping.PingJSPlugin.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i = 1; i <= Integer.parseInt(PingJSPlugin.this.sequenceNumberMax); i++) {
                            if (PingJSPlugin.this.isContinuePing) {
                                PingJSPlugin.this.startPing(String.valueOf(i));
                            }
                        }
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("参数解析异常:" + e.getMessage());
        }
    }

    private void callbackSucces(PingEntity pingEntity) {
        try {
            final JSONObject jSONObject = new JSONObject();
            jSONObject.put("code", pingEntity.getCode());
            jSONObject.put("sequenceNumber", pingEntity.getSequenceNumber());
            jSONObject.put("consumeTime", pingEntity.getConsumeTime());
            jSONObject.put("msg", pingEntity.getMsg());
            this.handler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.ping.PingJSPlugin.2
                @Override // java.lang.Runnable
                public void run() {
                    PingJSPlugin.this.callbackSuccess(jSONObject);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callbackFail(final String str) {
        this.handler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.ping.PingJSPlugin.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("msg", str);
                    PingJSPlugin.this.callbackFail(jSONObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v9 */
    public void startPing(String str) {
        Process process;
        BufferedReader bufferedReader;
        StringBuilder sb;
        ?? r0;
        PingEntity paramsText;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                process = Runtime.getRuntime().exec("ping -c 1 -W " + this.pingTimeout + " " + this.host);
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
                    r0 = 1;
                    boolean z = true;
                    int i = 0;
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            if (i == 1 && !readLine.contains("statistics") && !readLine.contains("transmitted") && !readLine.contains("rtt")) {
                                if (readLine.equals("")) {
                                    paramsText = new PingEntity("11", String.valueOf(str), "", "ping请求超时");
                                } else {
                                    paramsText = paramsText(readLine, str);
                                    if (paramsText == null) {
                                        paramsText = new PingEntity("11", String.valueOf(str), "", "ping请求超时");
                                    }
                                }
                                callbackSucces(paramsText);
                            }
                            i++;
                            z = false;
                        } catch (Exception e) {
                            e = e;
                            bufferedReader2 = bufferedReader;
                            e.printStackTrace();
                            callbackFail("程序异常：" + e.getMessage());
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                    bufferedReader2 = bufferedReader2;
                                } catch (Exception e2) {
                                    e = e2;
                                    e.printStackTrace();
                                    sb = new StringBuilder();
                                    sb.append("程序异常,关闭Reader：");
                                    sb.append(e.getMessage());
                                    callbackFail(sb.toString());
                                    process.destroy();
                                }
                                process.destroy();
                            }
                            return;
                        } catch (Throwable th) {
                            th = th;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                    callbackFail("程序异常,关闭Reader：" + e3.getMessage());
                                }
                                process.destroy();
                            }
                            throw th;
                        }
                    }
                    if (z) {
                        this.isContinuePing = false;
                        r0 = "12";
                        callbackSucces(new PingEntity("12", "1", "", "ping请求失败"));
                    }
                } catch (Exception e4) {
                    e = e4;
                }
            } catch (Exception e5) {
                e = e5;
                process = null;
            } catch (Throwable th2) {
                th = th2;
                process = null;
                bufferedReader = null;
            }
            try {
                bufferedReader.close();
                bufferedReader2 = r0;
            } catch (Exception e6) {
                e = e6;
                e.printStackTrace();
                sb = new StringBuilder();
                sb.append("程序异常,关闭Reader：");
                sb.append(e.getMessage());
                callbackFail(sb.toString());
                process.destroy();
            }
            process.destroy();
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = bufferedReader2;
        }
    }

    public PingEntity paramsText(String str, String str2) {
        if (str.contains("icmp_seq") && str.contains("ttl") && str.contains("time") && str.contains("ms")) {
            return new PingEntity("10", str2, getDengYu(str.substring(str.indexOf("time"), str.indexOf("ms")).trim()), "ping请求成功");
        }
        return null;
    }

    public String getDengYu(String str) {
        int indexOf = str.indexOf("=");
        return indexOf != -1 ? str.substring(indexOf + 1) : "";
    }

    public boolean isNumeric(String str) {
        if (str == null || "".equals(str.trim())) {
            return false;
        }
        return str.matches("^[0-9]*$");
    }
}
