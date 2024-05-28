package com.sinovatech.unicom.separatemodule.Log;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import java.text.SimpleDateFormat;
import java.util.Date;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DiagnoseActivity extends Activity {
    public NBSTraceUnit _nbs_trace;
    private Button backButton;
    private TextView baiduText;
    private Button copyButton;
    private Button emailButton;
    private TextView ipDebugText;
    private TextView jdText;
    private Button startButton;
    private TextView unicomAD443Text;
    private TextView unicomAD80Text;
    private TextView unicomDebugText;
    private TextView yidongDebugText;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String baiduURL = "https://www.baidu.com";
    private String jdURL = "https://m.jd.com";
    private String unicomProductionAdvertise443URL = "https://m.client.10010.com/mobileService/activity/get_client_adv_a5.htm?cityCode1=110&timestamp=20180125155445&desmobile=18611370982&cityCode=110&version=android@5.62&provinceCode1=011&provinceCode=011";
    private String unicomProductionAdvertise80URL = "https://m.client.10010.com/mobileService/activity/get_client_adv_a5.htm?cityCode1=110&timestamp=20180125155445&desmobile=18611370982&cityCode=110&version=android@5.62&provinceCode1=011&provinceCode=011";
    private String unicomDebugURL = "https://m.10010.com";
    private String yidongDebugURL = "https://www.10086.cn";

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 65);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.Log.DiagnoseActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class View$OnClickListenerC81641 implements View.OnClickListener {
        View$OnClickListenerC81641() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            DiagnoseActivity.this.finish();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.Log.DiagnoseActivity$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC81652 implements View.OnClickListener {
        View$OnClickListenerC81652() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            try {
                ((ClipboardManager) DiagnoseActivity.this.getSystemService("clipboard")).setText((((((((((((("" + ((Object) DiagnoseActivity.this.baiduText.getText())) + "#################") + ((Object) DiagnoseActivity.this.jdText.getText())) + "#################") + ((Object) DiagnoseActivity.this.unicomAD443Text.getText())) + "#################") + ((Object) DiagnoseActivity.this.unicomAD80Text.getText())) + "#################") + ((Object) DiagnoseActivity.this.unicomDebugText.getText())) + "#################") + ((Object) DiagnoseActivity.this.yidongDebugText.getText())) + "#################") + ((Object) DiagnoseActivity.this.ipDebugText.getText()));
                UIUtils.toast("已复制到剪切板");
            } catch (Exception e) {
                e.printStackTrace();
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.Log.DiagnoseActivity$3 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC81663 implements View.OnClickListener {
        View$OnClickListenerC81663() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            try {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("plain/text");
                intent.putExtra("android.intent.extra.EMAIL", new String[]{"dev-client@sinovatech.com"});
                intent.putExtra("android.intent.extra.SUBJECT", "客户端日志(" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ")");
                intent.putExtra("android.intent.extra.TEXT", (((((((((((("" + ((Object) DiagnoseActivity.this.baiduText.getText())) + "#################") + ((Object) DiagnoseActivity.this.jdText.getText())) + "#################") + ((Object) DiagnoseActivity.this.unicomAD443Text.getText())) + "#################") + ((Object) DiagnoseActivity.this.unicomAD80Text.getText())) + "#################") + ((Object) DiagnoseActivity.this.unicomDebugText.getText())) + "#################") + ((Object) DiagnoseActivity.this.yidongDebugText.getText())) + "#################") + ((Object) DiagnoseActivity.this.ipDebugText.getText()));
                DiagnoseActivity.this.startActivity(Intent.createChooser(intent, "请选择您使用的邮箱客户端"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.Log.DiagnoseActivity$4 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC81674 implements View.OnClickListener {
        View$OnClickListenerC81674() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            App.getAsyncHttpClient().get(DiagnoseActivity.this.baiduURL, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.separatemodule.Log.DiagnoseActivity.4.1
                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onStart() {
                    super.onStart();
                    TextView textView = DiagnoseActivity.this.baiduText;
                    textView.setText(((Object) DiagnoseActivity.this.baiduText.getText()) + "\n\n(" + DiagnoseActivity.this.sdf.format(new Date()) + ")开始访问百度...");
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onSuccess(int i, String str) {
                    super.onSuccess(i, str);
                    TextView textView = DiagnoseActivity.this.baiduText;
                    textView.setText(((Object) DiagnoseActivity.this.baiduText.getText()) + "\n\n(" + DiagnoseActivity.this.sdf.format(new Date()) + ")访问百度返回-状态码：" + i);
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onFailure(Throwable th, String str) {
                    super.onFailure(th, str);
                    TextView textView = DiagnoseActivity.this.baiduText;
                    textView.setText(((Object) DiagnoseActivity.this.baiduText.getText()) + "\n\n(" + DiagnoseActivity.this.sdf.format(new Date()) + ")访问百度-异常：" + str);
                }
            });
            App.getAsyncHttpClient().get(DiagnoseActivity.this.jdURL, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.separatemodule.Log.DiagnoseActivity.4.2
                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onStart() {
                    super.onStart();
                    TextView textView = DiagnoseActivity.this.jdText;
                    textView.setText(((Object) DiagnoseActivity.this.jdText.getText()) + "\n\n(" + DiagnoseActivity.this.sdf.format(new Date()) + ")开始访问京东...");
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onSuccess(int i, String str) {
                    super.onSuccess(i, str);
                    TextView textView = DiagnoseActivity.this.jdText;
                    textView.setText(((Object) DiagnoseActivity.this.jdText.getText()) + "\n\n(" + DiagnoseActivity.this.sdf.format(new Date()) + ")访问京东返回-状态码：" + i);
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onFailure(Throwable th, String str) {
                    super.onFailure(th, str);
                    TextView textView = DiagnoseActivity.this.jdText;
                    textView.setText(((Object) DiagnoseActivity.this.jdText.getText()) + "\n\n(" + DiagnoseActivity.this.sdf.format(new Date()) + ")访问京东-异常：" + str);
                }
            });
            App.getAsyncHttpClient().get(DiagnoseActivity.this.unicomProductionAdvertise443URL, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.separatemodule.Log.DiagnoseActivity.4.3
                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onStart() {
                    super.onStart();
                    TextView textView = DiagnoseActivity.this.unicomAD443Text;
                    textView.setText(((Object) DiagnoseActivity.this.unicomAD443Text.getText()) + "\n\n(" + DiagnoseActivity.this.sdf.format(new Date()) + ")开始访问联通m.client.10010.com:443-广告接口...");
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onSuccess(int i, String str) {
                    super.onSuccess(i, str);
                    TextView textView = DiagnoseActivity.this.unicomAD443Text;
                    textView.setText(((Object) DiagnoseActivity.this.unicomAD443Text.getText()) + "\n\n(" + DiagnoseActivity.this.sdf.format(new Date()) + ")访问联通m.client.10010.com:443-广告接口-状态码：" + i);
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onFailure(Throwable th, String str) {
                    super.onFailure(th, str);
                    TextView textView = DiagnoseActivity.this.unicomAD443Text;
                    textView.setText(((Object) DiagnoseActivity.this.unicomAD443Text.getText()) + "\n\n(" + DiagnoseActivity.this.sdf.format(new Date()) + ")访问联通m.client.10010.com:443-广告接口-异常：" + str);
                }
            });
            App.getAsyncHttpClient().get(DiagnoseActivity.this.unicomProductionAdvertise80URL, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.separatemodule.Log.DiagnoseActivity.4.4
                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onStart() {
                    super.onStart();
                    TextView textView = DiagnoseActivity.this.unicomAD80Text;
                    textView.setText(((Object) DiagnoseActivity.this.unicomAD80Text.getText()) + "\n\n(" + DiagnoseActivity.this.sdf.format(new Date()) + ")开始访问联通m.client.10010.com:80-广告接口...");
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onSuccess(int i, String str) {
                    super.onSuccess(i, str);
                    TextView textView = DiagnoseActivity.this.unicomAD80Text;
                    textView.setText(((Object) DiagnoseActivity.this.unicomAD80Text.getText()) + "\n\n(" + DiagnoseActivity.this.sdf.format(new Date()) + ")访问联通m.client.10010.com:80-广告接口-状态码：" + i);
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onFailure(Throwable th, String str) {
                    super.onFailure(th, str);
                    TextView textView = DiagnoseActivity.this.unicomAD80Text;
                    textView.setText(((Object) DiagnoseActivity.this.unicomAD80Text.getText()) + "\n\n(" + DiagnoseActivity.this.sdf.format(new Date()) + ")访问联通m.client.10010.com:80-广告接口-异常：" + str);
                }
            });
            App.getAsyncHttpClient().get(DiagnoseActivity.this.unicomDebugURL, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.separatemodule.Log.DiagnoseActivity.4.5
                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onStart() {
                    super.onStart();
                    TextView textView = DiagnoseActivity.this.unicomDebugText;
                    textView.setText(((Object) DiagnoseActivity.this.unicomDebugText.getText()) + "\n\n(" + DiagnoseActivity.this.sdf.format(new Date()) + ")开始访问联通域名...");
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onSuccess(int i, String str) {
                    super.onSuccess(i, str);
                    TextView textView = DiagnoseActivity.this.unicomDebugText;
                    textView.setText(((Object) DiagnoseActivity.this.unicomDebugText.getText()) + "\n\n(" + DiagnoseActivity.this.sdf.format(new Date()) + ")访问联通域名-状态码：" + i);
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onFailure(Throwable th, String str) {
                    super.onFailure(th, str);
                    TextView textView = DiagnoseActivity.this.unicomDebugText;
                    textView.setText(((Object) DiagnoseActivity.this.unicomDebugText.getText()) + "\n\n(" + DiagnoseActivity.this.sdf.format(new Date()) + ")访问联通域名-异常：" + str);
                }
            });
            App.getAsyncHttpClient().get(DiagnoseActivity.this.yidongDebugURL, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.separatemodule.Log.DiagnoseActivity.4.6
                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onStart() {
                    super.onStart();
                    TextView textView = DiagnoseActivity.this.yidongDebugText;
                    textView.setText(((Object) DiagnoseActivity.this.yidongDebugText.getText()) + "\n\n(" + DiagnoseActivity.this.sdf.format(new Date()) + ")开始访问移动域名(" + DiagnoseActivity.this.yidongDebugURL + ")...");
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onSuccess(int i, String str) {
                    super.onSuccess(i, str);
                    TextView textView = DiagnoseActivity.this.yidongDebugText;
                    textView.setText(((Object) DiagnoseActivity.this.yidongDebugText.getText()) + "\n\n(" + DiagnoseActivity.this.sdf.format(new Date()) + ")访问移动域名(" + DiagnoseActivity.this.yidongDebugURL + ")-状态码：" + i);
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onFailure(Throwable th, String str) {
                    super.onFailure(th, str);
                    TextView textView = DiagnoseActivity.this.yidongDebugText;
                    textView.setText(((Object) DiagnoseActivity.this.yidongDebugText.getText()) + "\n\n(" + DiagnoseActivity.this.sdf.format(new Date()) + ")访问移动域名(" + DiagnoseActivity.this.yidongDebugURL + ")-异常：" + str);
                }
            });
            NBSActionInstrumentation.onClickEventExit();
        }
    }
}
