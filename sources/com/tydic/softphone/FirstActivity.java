package com.tydic.softphone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.bytedance.applog.tracker.Tracker;
import com.huawei.hms.api.FailedBinderCallBack;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class FirstActivity extends Activity {
    public NBSTraceUnit _nbs_trace;
    EditText busiEntrance;
    EditText callMode;
    EditText callerShowNumber;
    String callerShowNumber_str;
    EditText channelId;
    EditText customerGroupType;
    String customerGroupType_str;
    EditText dialSwitch;
    EditText dockingBusiSys;
    EditText fouseCall;
    EditText fouseCallName;
    EditText isShowCalleeNumber;
    EditText isSubstitution;
    String isSubstitution_str;
    EditText isother;
    private NetBroadcastReceiver networkChangeReceiver;
    EditText orderId;
    EditText originalFouseCall;
    String originalFouseCall_str;
    EditText phoneNumber;
    EditText roleCode;
    TextView talkduration;
    EditText timestamp;
    String timestamp_str;
    Button tydic_call;
    Button tydic_call_again;
    EditText userName;
    String[] perms = {"android.permission.CAMERA", "android.permission.RECORD_AUDIO"};
    Context context = null;
    String dialSwitch_str = "1";
    String isShowCalleeNumber_str = "1";
    String channelId_str = "302001";
    String userName_str = "田杰";
    String phoneNumber_str = "13070153759";
    String callMode_str = "5";
    String dockingBusiSys_str = "500";
    String busiEntrance_str = "2";
    String fouseCall_str = "10010";
    String fouseCallName_str = "田杰";
    String roleCode_str = "01";
    String orderId_str = "gkfdaklfjal";
    String isother_str = "0";

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.view.Window.Callback
    public void onPointerCaptureChanged(boolean z) {
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

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        if ((getIntent().getFlags() & 4194304) != 0) {
            finish();
            NBSAppInstrumentation.activityCreateEndIns();
            return;
        }
        setContentView(C10458R.C10462layout.tydic_softphone_first_main);
        this.dialSwitch = (EditText) findViewById(C10458R.C10461id.dialSwitch);
        this.channelId = (EditText) findViewById(C10458R.C10461id.channelId);
        this.phoneNumber = (EditText) findViewById(C10458R.C10461id.phoneNumber);
        this.callMode = (EditText) findViewById(C10458R.C10461id.callMode);
        this.isShowCalleeNumber = (EditText) findViewById(C10458R.C10461id.isShowCalleeNumber);
        this.userName = (EditText) findViewById(C10458R.C10461id.userName);
        this.dockingBusiSys = (EditText) findViewById(C10458R.C10461id.dockingBusiSys);
        this.busiEntrance = (EditText) findViewById(C10458R.C10461id.busiEntrance);
        this.fouseCall = (EditText) findViewById(C10458R.C10461id.fouseCall);
        this.fouseCallName = (EditText) findViewById(C10458R.C10461id.fouseCallName);
        this.orderId = (EditText) findViewById(C10458R.C10461id.orderId);
        this.talkduration = (TextView) findViewById(C10458R.C10461id.talkduration);
        this.roleCode = (EditText) findViewById(C10458R.C10461id.roleCode);
        this.customerGroupType = (EditText) findViewById(C10458R.C10461id.customerGroupType);
        this.isSubstitution = (EditText) findViewById(C10458R.C10461id.isSubstitution);
        this.originalFouseCall = (EditText) findViewById(C10458R.C10461id.originalFouseCall);
        this.callerShowNumber = (EditText) findViewById(C10458R.C10461id.callerShowNumber);
        this.networkChangeReceiver = new NetBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.tydic.softphone.callid");
        registerReceiver(this.networkChangeReceiver, intentFilter);
        this.context = this;
        this.channelId.setText(this.channelId_str);
        this.phoneNumber.setText(this.phoneNumber_str);
        this.callMode.setText(this.callMode_str);
        this.userName.setText(this.userName_str);
        this.roleCode.setText(this.roleCode_str);
        this.dialSwitch.setText(this.dialSwitch_str);
        this.dockingBusiSys.setText(this.dockingBusiSys_str);
        this.busiEntrance.setText(this.busiEntrance_str);
        this.fouseCall.setText(this.fouseCall_str);
        this.fouseCallName.setText(this.fouseCallName_str);
        this.orderId.setText(this.orderId_str);
        this.isShowCalleeNumber.setText(this.isShowCalleeNumber_str);
        this.customerGroupType.setText(this.customerGroupType_str);
        this.isSubstitution.setText(this.isSubstitution_str);
        this.originalFouseCall.setText(this.originalFouseCall_str);
        this.callerShowNumber.setText(this.callerShowNumber_str);
        String str = Build.VERSION.RELEASE;
        Log.i("tydic221", "Product Model: " + Build.MODEL + "," + Build.VERSION.SDK + "," + Build.VERSION.RELEASE);
        Log.i("tydic221", Build.VERSION.SDK_INT + "");
        this.tydic_call = (Button) findViewById(C10458R.C10461id.tydic_call);
        this.tydic_call.setOnClickListener(new View.OnClickListener() { // from class: com.tydic.softphone.FirstActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (Boolean.valueOf(FirstActivity.this.context.getPackageManager().checkPermission("android.permission.RECORD_AUDIO", "com.tydic.softphone") == 0).booleanValue()) {
                    FirstActivity.this.openActivity();
                } else if (Build.VERSION.SDK_INT >= 23) {
                    FirstActivity.this.requestPermissions(new String[]{"android.permission.RECORD_AUDIO"}, 1001);
                } else {
                    Toast.makeText(FirstActivity.this.context, "您现在无法进行语音通话，请打开您的麦克风权限", 0).show();
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        NBSAppInstrumentation.activityCreateEndIns();
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        PackageManager packageManager = this.context.getPackageManager();
        if (i == 1001) {
            if (Boolean.valueOf(packageManager.checkPermission("android.permission.RECORD_AUDIO", "com.tydic.softphone") == 0).booleanValue()) {
                openActivity();
            } else {
                Toast.makeText(this.context, "您现在无法进行语音通话，请打开您的麦克风权限", 0).show();
            }
        }
    }

    public void openActivity() {
        Intent intent = new Intent();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("channelId", this.channelId.getText().toString());
            jSONObject.put("phoneNumber", this.phoneNumber.getText().toString());
            jSONObject.put("callMode", this.callMode.getText().toString());
            jSONObject.put("dialSwitch", 1);
            jSONObject.put("userName", this.userName.getText().toString());
            jSONObject.put("dockingBusiSys", this.dockingBusiSys.getText().toString());
            jSONObject.put("busiEntrance", this.busiEntrance.getText().toString());
            jSONObject.put("fouseCall", this.fouseCall.getText().toString());
            jSONObject.put("fouseCallName", this.fouseCallName.getText().toString());
            jSONObject.put("orderId", "null");
            jSONObject.put("roleCode", this.roleCode.getText().toString());
            jSONObject.put("starRating", "11");
            jSONObject.put("isShowCalleeNumber", this.isShowCalleeNumber.getText().toString());
            jSONObject.put("customerGroupType", this.customerGroupType.getText().toString());
            jSONObject.put("isSubstitution", this.isSubstitution.getText().toString());
            jSONObject.put("originalFouseCall", this.originalFouseCall.getText().toString());
            jSONObject.put("callerShowNumber", this.callerShowNumber.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String jSONObject2 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        Log.i("tydic", jSONObject2);
        intent.putExtra("voiceCall", jSONObject2);
        intent.setClass(this, CallActivity.class);
        int versionCode = getVersionCode(this);
        String sDKVersion = CallActivity.getSDKVersion(this);
        Log.i("tydic222", versionCode + "");
        Log.i("tydic222", sDKVersion);
        startActivity(intent);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1) {
            Log.i("tydic334", "onActivityResult6");
            String string = intent.getExtras().getString(FailedBinderCallBack.CALLER_ID);
            int i3 = intent.getExtras().getInt("code");
            int i4 = intent.getExtras().getInt("durationTalk");
            Log.i("tydic334", String.valueOf(i4));
            intent.getExtras().getString("msg");
            String str = "";
            switch (i3) {
                case 1:
                    str = "获取麦克风权限异常";
                    break;
                case 2:
                    str = "主叫挂断";
                    break;
                case 3:
                    str = "被叫挂断";
                    break;
                case 4:
                    str = "注册失败";
                    break;
                case 5:
                    str = "网络异常";
                    break;
                case 6:
                    str = "通话中";
                    break;
                case 7:
                    str = "参数异常";
                    break;
            }
            Log.i("tydic334", "" + i3);
            TextView textView = this.talkduration;
            textView.setText(str + ";通话时长：" + i4 + ";callid:" + string);
        }
    }

    public void jumpCall() {
        Intent intent = new Intent();
        intent.putExtra("channelId", this.channelId.getText().toString());
        intent.putExtra("phoneNumber", this.phoneNumber.getText().toString());
        intent.putExtra("callMode", this.callMode.getText().toString());
        intent.putExtra("dockingBusiSys", this.dockingBusiSys.getText().toString());
        intent.putExtra("busiEntrance", this.busiEntrance.getText().toString());
        intent.putExtra("fouseCall", this.fouseCall.getText().toString());
    }

    public int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Log.e("tydic1", " FirstActivity onDestroy--->");
    }
}
