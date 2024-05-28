package com.chinaunicon.jtwifilib.jtcommon.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkSpecifier;
import android.os.Handler;
import android.os.Message;
import android.os.PatternMatcher;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import com.bytedance.applog.tracker.Tracker;
import com.chinaunicon.jtwifilib.C4079R;
import com.chinaunicon.jtwifilib.core.global.JtApp;
import com.chinaunicon.jtwifilib.core.utils.JtDialog;
import com.chinaunicon.jtwifilib.core.utils.JtGsonUtil;
import com.chinaunicon.jtwifilib.core.utils.JtL;
import com.chinaunicon.jtwifilib.core.utils.JtToastUtil;
import com.chinaunicon.jtwifilib.core.utils.JtWifiAdmin;
import com.chinaunicon.jtwifilib.jtcommon.JtOnWifiClientListener;
import com.chinaunicon.jtwifilib.jtcommon.model.Register1Bean;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import java.lang.ref.WeakReference;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class JtWifiClient {
    public static final int ACCESS_FINE_LOCATION_CODE = 2009;
    public static final int CONNECT_WIFI = 2010;
    public static final int HANDLER_WIFI_ERROR = 2007;
    public static final int HANDLER_WIFI_WHAT = 2006;
    public static final int HANDLER_WIFI_WHAT_API29 = 2008;
    private Activity context;
    private MyHandler handler;
    private JtOnWifiClientListener jtOnWifiClientListener;
    private JtWifiAdmin mWifiAdmin;
    private WifiManager mWifiManager;
    private String password;
    private String ssid1;
    int targetSdkVersion;
    private int countReTest = 0;
    private boolean isGoWifiSetting = false;

    public void onReStartWifi() {
    }

    @RequiresApi(api = 4)
    public JtWifiClient(Activity activity) {
        this.handler = new MyHandler(this.context);
        this.context = activity;
        this.targetSdkVersion = this.context.getApplicationInfo().targetSdkVersion;
        this.mWifiAdmin = new JtWifiAdmin(activity);
        this.mWifiManager = this.mWifiAdmin.getWifiManager();
    }

    public void getWifiInfo() {
        this.mWifiAdmin.getWifi();
        this.mWifiManager = this.mWifiAdmin.getWifiManager();
    }

    public String getBSSID() {
        return this.mWifiAdmin.getBSSID();
    }

    public void setJtOnWifiClientListener(JtOnWifiClientListener jtOnWifiClientListener) {
        this.jtOnWifiClientListener = jtOnWifiClientListener;
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class MyHandler extends Handler {
        private WeakReference<Activity> mWeakReference;

        public MyHandler(Activity activity) {
            this.mWeakReference = new WeakReference<>(activity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            WeakReference<Activity> weakReference = this.mWeakReference;
            if (weakReference != null) {
                weakReference.get();
                if (message.what == 2006) {
                    if (JtWifiClient.this.isWifiConnect()) {
                        if (JtWifiClient.this.jtOnWifiClientListener != null) {
                            JtWifiClient.this.jtOnWifiClientListener.onConnect("1");
                        }
                    } else {
                        JtL.m16342e("wifi未连接---");
                        if (JtWifiClient.this.countReTest >= 15) {
                            if (JtWifiClient.this.jtOnWifiClientListener != null) {
                                JtWifiClient.this.jtOnWifiClientListener.onConnect("0");
                            }
                        } else {
                            JtWifiClient.this.countReTest++;
                            JtWifiClient.this.handler.sendEmptyMessageDelayed(2006, 1000L);
                        }
                    }
                } else if (message.what == 2008) {
                    if (JtWifiClient.this.isWifiConnect()) {
                        if (JtWifiClient.this.jtOnWifiClientListener != null) {
                            JtWifiClient.this.jtOnWifiClientListener.onConnect("1");
                        }
                    } else {
                        JtL.m16342e("wifi未连接---" + JtWifiClient.this.countReTest);
                        if (JtWifiClient.this.countReTest >= 120000) {
                            if (JtWifiClient.this.jtOnWifiClientListener != null) {
                                JtWifiClient.this.jtOnWifiClientListener.onConnect("0");
                            }
                        } else {
                            JtWifiClient.this.countReTest++;
                            JtWifiClient.this.handler.sendEmptyMessageDelayed(2008, 100L);
                        }
                    }
                }
                if (message.what != 2007 || JtWifiClient.this.jtOnWifiClientListener == null) {
                    return;
                }
                JtWifiClient.this.jtOnWifiClientListener.onConnect("0");
            }
        }
    }

    public String getWIFISSID() {
        if (isWifiConnect()) {
            Register1Bean register1Bean = new Register1Bean();
            register1Bean.setCmdType("wifi_connect_state");
            register1Bean.setStatus("0");
            register1Bean.setMsg("当前WIFI名称:" + this.mWifiAdmin.getSSID());
            JtUploadLog.getInstance(this.context).updateData("1", JtGsonUtil.getInstance().toJson(register1Bean), "get_wifi_name");
            return this.mWifiAdmin.getSSID();
        }
        return "";
    }

    @RequiresApi(api = 3)
    public String getIpAddress() {
        return Formatter.formatIpAddress(this.mWifiAdmin.getIPAddress());
    }

    public String getMacAddress() {
        return this.mWifiAdmin.getBSSID();
    }

    public String getCurrentNetworkRssi() {
        return this.mWifiAdmin.getCurrentNetworkRssi();
    }

    @RequiresApi(api = 21)
    public String getWifiAgreement() {
        Register1Bean register1Bean = new Register1Bean();
        register1Bean.setCmdType("wifi_version");
        register1Bean.setStatus("0");
        register1Bean.setMsg("当前WIFI版本:" + this.mWifiAdmin.getWifiAgreement());
        JtUploadLog.getInstance(this.context).updateData("1", JtGsonUtil.getInstance().toJson(register1Bean), "get_wifi_name");
        return this.mWifiAdmin.getWifiAgreement();
    }

    @RequiresApi(api = 21)
    public String getConnectFrequency() {
        return this.mWifiAdmin.getConnectFrequency();
    }

    public String getLinkSpeed() {
        return this.mWifiAdmin.getLinkSpeed();
    }

    public void initWIFISetting(String str, String str2) {
        JtOnWifiClientListener jtOnWifiClientListener;
        MyHandler myHandler = this.handler;
        if (myHandler != null) {
            myHandler.removeCallbacksAndMessages(null);
        }
        this.ssid1 = str;
        this.password = str2;
        if (!TextUtils.isEmpty(this.ssid1) && this.ssid1.equals(this.mWifiAdmin.getSSID()) && (jtOnWifiClientListener = this.jtOnWifiClientListener) != null) {
            jtOnWifiClientListener.onConnect("1");
        }
        if (this.targetSdkVersion < 29) {
            if (this.jtOnWifiClientListener != null) {
                Register1Bean register1Bean = new Register1Bean();
                register1Bean.setCmdType("wifi_connect_start");
                register1Bean.setStatus("0");
                register1Bean.setMsg("开始连接WIFI：SSID1=" + str + ",password=" + str2);
                JtUploadLog.getInstance(this.context).updateData("1", JtGsonUtil.getInstance().toJson(register1Bean), "wifi_connect_start");
            }
            checkState();
        } else if (ActivityCompat.checkSelfPermission(this.context, "android.permission.ACCESS_FINE_LOCATION") != 0 && ActivityCompat.checkSelfPermission(this.context, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
            ActivityCompat.requestPermissions(this.context, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, 2009);
        } else {
            startWifiSetting();
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 2009) {
            if (iArr.length > 0 && iArr[0] == 0) {
                startWifiSetting();
            } else {
                showDialog(C4079R.string.jt_premission_info, C4079R.string.jt_wifi_premission, C4079R.string.jt_dialog_sure, new View.OnClickListener() { // from class: com.chinaunicon.jtwifilib.jtcommon.util.JtWifiClient.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        JtWifiClient.this.context.startActivity(new Intent("android.settings.SETTINGS"));
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            }
        }
    }

    public boolean isWifiUsable() {
        return this.mWifiAdmin.checkWifiIsEnable() && isWifiConnect();
    }

    private void startWifiSetting() {
        JtL.m16342e("当前WIFI名称：" + getWIFISSID());
        connectBySug(this.ssid1, this.password);
    }

    private void connectBySug(String str, String str2) {
        ((ClipboardManager) this.context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(null, str2));
        Activity activity = this.context;
        JtToastUtil.showLong(activity, "Wi-Fi密码已复制，请手动粘贴密码连接" + str);
        this.context.startActivityForResult(new Intent("android.settings.WIFI_SETTINGS"), 2010);
    }

    public void receiver(int i) {
        if (i == 2010) {
            if (isWifiConnect()) {
                getWIFISSID();
                JtOnWifiClientListener jtOnWifiClientListener = this.jtOnWifiClientListener;
                if (jtOnWifiClientListener != null) {
                    jtOnWifiClientListener.onConnect("1");
                    return;
                }
                return;
            }
            JtOnWifiClientListener jtOnWifiClientListener2 = this.jtOnWifiClientListener;
            if (jtOnWifiClientListener2 != null) {
                jtOnWifiClientListener2.onConnect("0");
            }
        }
    }

    public boolean isWifiConnect() {
        return ((ConnectivityManager) this.context.getSystemService("connectivity")).getNetworkInfo(1).isConnected();
    }

    @RequiresApi(api = 29)
    private void connectedWifi() {
        ((ConnectivityManager) this.context.getSystemService("connectivity")).requestNetwork(new NetworkRequest.Builder().addTransportType(1).removeCapability(12).setNetworkSpecifier(new WifiNetworkSpecifier.Builder().setSsidPattern(new PatternMatcher(this.ssid1, 1)).setWpa2Passphrase(this.password).build()).build(), new ConnectivityManager.NetworkCallback() { // from class: com.chinaunicon.jtwifilib.jtcommon.util.JtWifiClient.2
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                super.onCapabilitiesChanged(network, networkCapabilities);
                if (networkCapabilities.hasCapability(16)) {
                    if (networkCapabilities.hasTransport(1)) {
                        JtWifiClient.this.context.runOnUiThread(new Runnable() { // from class: com.chinaunicon.jtwifilib.jtcommon.util.JtWifiClient.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (JtWifiClient.this.jtOnWifiClientListener != null) {
                                    JtWifiClient.this.jtOnWifiClientListener.onConnect("1");
                                }
                            }
                        });
                    } else if (networkCapabilities.hasTransport(0)) {
                        JtWifiClient.this.context.runOnUiThread(new Runnable() { // from class: com.chinaunicon.jtwifilib.jtcommon.util.JtWifiClient.2.2
                            @Override // java.lang.Runnable
                            public void run() {
                                if (JtWifiClient.this.jtOnWifiClientListener != null) {
                                    JtWifiClient.this.jtOnWifiClientListener.onConnect("0");
                                }
                            }
                        });
                    } else {
                        JtWifiClient.this.context.runOnUiThread(new Runnable() { // from class: com.chinaunicon.jtwifilib.jtcommon.util.JtWifiClient.2.3
                            @Override // java.lang.Runnable
                            public void run() {
                                if (JtWifiClient.this.jtOnWifiClientListener != null) {
                                    JtWifiClient.this.jtOnWifiClientListener.onConnect("0");
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    @SuppressLint({"WrongConstant"})
    private void checkState() {
        if (this.mWifiManager.getWifiState() == 0) {
            JtToastUtil.showShort(this.context, C4079R.string.jt_log_dia04);
        } else if (this.mWifiManager.getWifiState() == 1) {
            JtToastUtil.showShort(this.context, C4079R.string.jt_log_dia05);
            showDialog(C4079R.string.jt_log_dia10, C4079R.string.jt_log_dia06, C4079R.string.jt_dialog_sure, new View.OnClickListener() { // from class: com.chinaunicon.jtwifilib.jtcommon.util.JtWifiClient.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if (!JtWifiClient.this.mWifiAdmin.openWifi(JtWifiClient.this.context)) {
                        if (JtWifiClient.this.jtOnWifiClientListener != null) {
                            JtWifiClient.this.jtOnWifiClientListener.onConnect("0");
                        }
                    } else {
                        JtL.m16342e("打开WIFI");
                        JtWifiClient.this.startConnectWifi();
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        } else if (this.mWifiManager.getWifiState() == 2) {
            JtToastUtil.showShort(this.context, C4079R.string.jt_log_dia07);
        } else if (this.mWifiManager.getWifiState() == 3) {
            JtToastUtil.showShort(this.context, C4079R.string.jt_log_dia08);
            startConnectWifi();
        } else {
            JtOnWifiClientListener jtOnWifiClientListener = this.jtOnWifiClientListener;
            if (jtOnWifiClientListener != null) {
                jtOnWifiClientListener.onConnect("0");
            }
            JtToastUtil.showShort(this.context, C4079R.string.jt_log_dia09);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startConnectWifi() {
        JtApp.getInstance().executorService.execute(new Runnable() { // from class: com.chinaunicon.jtwifilib.jtcommon.util.JtWifiClient.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException unused) {
                }
                JtWifiClient.this.mWifiAdmin.disconnectWifi(JtWifiClient.this.mWifiAdmin.getNetworkId());
                if (JtWifiClient.this.mWifiAdmin.addNetwork(JtWifiClient.this.mWifiAdmin.CreateWifiInfo(JtWifiClient.this.ssid1, JtWifiClient.this.password, 3))) {
                    JtWifiClient.this.countReTest = 0;
                    JtWifiClient.this.handler.sendEmptyMessageDelayed(2006, 1000L);
                    return;
                }
                Message message = new Message();
                message.what = 2007;
                JtWifiClient.this.handler.sendMessage(message);
            }
        });
    }

    private void showDialog(int i, int i2, int i3, final View.OnClickListener onClickListener) {
        View inflate = LayoutInflater.from(this.context).inflate(C4079R.C4083layout.jt_w_affirm, (ViewGroup) null);
        final JtDialog jtDialog = new JtDialog(this.context, C4079R.C4084style.JtPgDialog, inflate);
        ((TextView) inflate.findViewById(C4079R.C4082id.infoa)).setText(i2);
        ((TextView) inflate.findViewById(C4079R.C4082id.titlea)).setText(i);
        Button button = (Button) inflate.findViewById(C4079R.C4082id.submita);
        button.setText(i3);
        ((Button) inflate.findViewById(C4079R.C4082id.closea)).setOnClickListener(new View.OnClickListener() { // from class: com.chinaunicon.jtwifilib.jtcommon.util.JtWifiClient.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (jtDialog.isShowing()) {
                    jtDialog.dismiss();
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.chinaunicon.jtwifilib.jtcommon.util.JtWifiClient.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (jtDialog.isShowing()) {
                    jtDialog.dismiss();
                }
                View.OnClickListener onClickListener2 = onClickListener;
                if (onClickListener2 != null) {
                    onClickListener2.onClick(view);
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        jtDialog.show();
    }

    private void showDialog(int i, int i2, int i3, final View.OnClickListener onClickListener, final View.OnClickListener onClickListener2) {
        View inflate = LayoutInflater.from(this.context).inflate(C4079R.C4083layout.jt_w_affirm, (ViewGroup) null);
        final JtDialog jtDialog = new JtDialog(this.context, C4079R.C4084style.JtPgDialog, inflate);
        ((TextView) inflate.findViewById(C4079R.C4082id.infoa)).setText(i2);
        ((TextView) inflate.findViewById(C4079R.C4082id.titlea)).setText(i);
        Button button = (Button) inflate.findViewById(C4079R.C4082id.submita);
        button.setText(i3);
        ((Button) inflate.findViewById(C4079R.C4082id.closea)).setOnClickListener(new View.OnClickListener() { // from class: com.chinaunicon.jtwifilib.jtcommon.util.JtWifiClient.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (jtDialog.isShowing()) {
                    jtDialog.dismiss();
                }
                View.OnClickListener onClickListener3 = onClickListener2;
                if (onClickListener3 != null) {
                    onClickListener3.onClick(view);
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.chinaunicon.jtwifilib.jtcommon.util.JtWifiClient.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (jtDialog.isShowing()) {
                    jtDialog.dismiss();
                }
                View.OnClickListener onClickListener3 = onClickListener;
                if (onClickListener3 != null) {
                    onClickListener3.onClick(view);
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        jtDialog.show();
    }

    public void onDestroy() {
        this.handler.removeCallbacksAndMessages(null);
        this.isGoWifiSetting = false;
    }
}
