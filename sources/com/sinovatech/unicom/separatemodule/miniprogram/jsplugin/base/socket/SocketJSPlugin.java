package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.socket;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.common.EncodeUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.xuhao.didi.core.iocore.interfaces.ISendable;
import com.xuhao.didi.core.pojo.OriginalData;
import com.xuhao.didi.socket.client.impl.client.action.ActionDispatcher;
import com.xuhao.didi.socket.client.sdk.OkSocket;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.client.sdk.client.action.SocketActionAdapter;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;
import com.xuhao.didi.socket.client.sdk.client.connection.NoneReconnect;
import java.nio.charset.Charset;
import org.json.JSONException;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/sendDataByTcpSocket")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SocketJSPlugin extends BaseJSPlugin {
    private static final String TAG = "SocketJSPlugin";
    String data;
    private OkSocketOptions mOkOptions;
    IConnectionManager manager;
    SocketActionAdapter socketActionAdapter = new SocketActionAdapter() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.socket.SocketJSPlugin.1
        @Override // com.xuhao.didi.socket.client.sdk.client.action.SocketActionAdapter, com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener
        public void onSocketConnectionSuccess(ConnectionInfo connectionInfo, String str) {
            Log.d(SocketJSPlugin.TAG, "onSocketConnectionSuccess==" + connectionInfo.toString());
            SocketJSPlugin.this.manager.send(new SendData(SocketJSPlugin.this.data));
        }

        @Override // com.xuhao.didi.socket.client.sdk.client.action.SocketActionAdapter, com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener
        public void onSocketConnectionFailed(ConnectionInfo connectionInfo, String str, final Exception exc) {
            super.onSocketConnectionFailed(connectionInfo, str, exc);
            Log.d(SocketJSPlugin.TAG, "onSocketConnectionFailed==" + exc.getMessage());
            if (exc != null) {
                SocketJSPlugin.this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.socket.SocketJSPlugin.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put("status", "11");
                            jSONObject.put("msg", exc.getMessage());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        SocketJSPlugin.this.callbackFail(jSONObject);
                    }
                });
            }
        }

        @Override // com.xuhao.didi.socket.client.sdk.client.action.SocketActionAdapter, com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener
        public void onSocketDisconnection(ConnectionInfo connectionInfo, String str, Exception exc) {
            super.onSocketDisconnection(connectionInfo, str, exc);
            if (exc != null) {
                Log.d(SocketJSPlugin.TAG, "onSocketDisconnection==" + exc.getMessage());
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("status", "11");
                    jSONObject.put("msg", exc.getMessage());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                SocketJSPlugin.this.callbackFail(jSONObject);
            } else {
                SocketJSPlugin.this.callbackSuccess("");
            }
            if (SocketJSPlugin.this.socketActionAdapter != null) {
                SocketJSPlugin.this.manager.unRegisterReceiver(SocketJSPlugin.this.socketActionAdapter);
            }
        }

        @Override // com.xuhao.didi.socket.client.sdk.client.action.SocketActionAdapter, com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener
        public void onSocketWriteResponse(ConnectionInfo connectionInfo, String str, ISendable iSendable) {
            super.onSocketWriteResponse(connectionInfo, str, iSendable);
            new String(iSendable.parse(), Charset.forName("utf-8"));
            if (iSendable != null) {
                Log.d(SocketJSPlugin.TAG, "onSocketWriteResponse==" + iSendable.parse());
            }
        }

        @Override // com.xuhao.didi.socket.client.sdk.client.action.SocketActionAdapter, com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener
        public void onSocketReadResponse(ConnectionInfo connectionInfo, String str, OriginalData originalData) {
            super.onSocketReadResponse(connectionInfo, str, originalData);
            if (originalData != null) {
                final StringBuilder sb = new StringBuilder();
                sb.append(EncodeUtils.hexEncode(originalData.getHeadBytes()));
                sb.append(EncodeUtils.hexEncode(originalData.getBodyBytes()));
                Log.d(SocketJSPlugin.TAG, "onSocketReadResponse==" + sb.toString());
                SocketJSPlugin.this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.socket.SocketJSPlugin.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        SocketJSPlugin.this.callbackSuccess(sb.toString());
                    }
                });
            }
        }
    };

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            String optString = this.parameterJO.optString("type");
            if (!TextUtils.isEmpty(optString) && "disConnectServer".equals(optString)) {
                if (this.manager != null) {
                    if (this.manager.isConnect()) {
                        this.manager.disconnect();
                    } else {
                        callbackSuccess("");
                    }
                }
            } else {
                String string = this.parameterJO.getString("address");
                int i = this.parameterJO.getInt("port");
                int i2 = this.parameterJO.getInt("timeout");
                this.data = this.parameterJO.getString("data");
                final Handler handler = new Handler();
                ConnectionInfo connectionInfo = new ConnectionInfo(string, i);
                this.mOkOptions = new OkSocketOptions.Builder().setReconnectionManager(new NoneReconnect()).setConnectTimeoutSecond(i2).setCallbackThreadModeToken(new OkSocketOptions.ThreadModeToken() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.socket.SocketJSPlugin.2
                    @Override // com.xuhao.didi.socket.client.sdk.client.OkSocketOptions.ThreadModeToken
                    public void handleCallbackEvent(ActionDispatcher.ActionRunnable actionRunnable) {
                        handler.post(actionRunnable);
                    }
                }).build();
                this.manager = OkSocket.open(connectionInfo).option(this.mOkOptions);
                this.manager.registerReceiver(this.socketActionAdapter);
                this.manager.connect();
            }
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
    }

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
        this.SingletonPattern = true;
    }
}
