package com.chinaunicon.jtwifilib.jtcommon.util;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import androidx.annotation.RequiresApi;
import com.chinaunicon.jtwifilib.core.global.JtApp;
import com.chinaunicon.jtwifilib.core.utils.JtFileUtils;
import com.chinaunicon.jtwifilib.core.utils.JtGsonUtil;
import com.chinaunicon.jtwifilib.core.utils.JtL;
import com.chinaunicon.jtwifilib.core.utils.SharedPreferencesHelp;
import com.chinaunicon.jtwifilib.core.utils.StringUtil;
import com.chinaunicon.jtwifilib.core.utils.ZipFileUtil;
import com.chinaunicon.jtwifilib.jtcommon.JtDataBuffer;
import com.chinaunicon.jtwifilib.jtcommon.JtOnClientListener;
import com.chinaunicon.jtwifilib.jtcommon.model.JtBaseModel;
import com.chinaunicon.jtwifilib.jtcommon.model.JtChallengeCode;
import com.chinaunicon.jtwifilib.jtcommon.model.JtNetConfig;
import com.chinaunicon.jtwifilib.jtcommon.model.JtNetPremission;
import com.chinaunicon.jtwifilib.jtcommon.model.JtRequestBean;
import com.chinaunicon.jtwifilib.jtcommon.model.JtResponseBean;
import com.chinaunicon.jtwifilib.jtcommon.model.Register1Bean;
import com.chinaunicon.jtwifilib.jtcommon.p188db.DBManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class JtClient {
    private static final int CONNECT_FILD = 103;
    private static final int CONNECT_SUCCESS = 102;
    private static final int DOWNLOAD_ERROR = 107;
    private static final int DOWNLOAD_FINISH = 106;
    private static final int DOWNLOAD_PROGRESS = 105;
    private static final int MESSAGE_RECEIVE = 101;
    private static final int SELECT_FINISH = 104;
    private static final int SEND_FILD = 100;
    private static final int UNZIP_ERROR = 109;
    private static final int UNZIP_SUCCESS = 108;
    public static final String UXUE_TEMP_FILE_SUFFIX = ".zip";
    private Context context;
    private DBManager dbManager;
    JtActivityHandler handler;
    private JtOnClientListener jtOnConnectListener;
    JtNetConfig localConfig;
    private int nowVersion;
    public static final String MAIN_FILE_PATH = JtFileUtils.getAppPath();
    public static final String BLACKTECH_HOT_UPDATE_FILE_PATH = MAIN_FILE_PATH + "html/";
    private boolean authorization = false;
    private String fileName = "YJHtml.zip";
    private String filePath = BLACKTECH_HOT_UPDATE_FILE_PATH;
    private boolean isDownlod = true;
    public String string16 = "";
    String sCmdType = "";

    public void setOnConnectListener(JtOnClientListener jtOnClientListener) {
        this.jtOnConnectListener = jtOnClientListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class JtActivityHandler extends Handler {
        private WeakReference<Activity> mWeakReference;

        public JtActivityHandler(Activity activity) {
            this.mWeakReference = new WeakReference<>(activity);
        }

        @Override // android.os.Handler
        @RequiresApi(api = 21)
        public void handleMessage(Message message) {
            super.handleMessage(message);
            WeakReference<Activity> weakReference = this.mWeakReference;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            switch (message.what) {
                case 100:
                    if (JtClient.this.jtOnConnectListener != null) {
                        JtClient.this.jtOnConnectListener.onError(message.obj.toString());
                        return;
                    }
                    return;
                case 101:
                    if (!"BIND_SEARCH".equals(((JtBaseModel) JtGsonUtil.getInstance().fromJson(message.obj.toString(), (Class<Object>) JtBaseModel.class)).getCmdType())) {
                        if (JtClient.this.jtOnConnectListener != null) {
                            JtClient.this.jtOnConnectListener.onMessage(message.obj.toString());
                            return;
                        }
                        return;
                    }
                    JtChallengeCode jtChallengeCode = (JtChallengeCode) JtGsonUtil.getInstance().fromJson(message.obj.toString(), (Class<Object>) JtChallengeCode.class);
                    JtClient.this.login(jtChallengeCode.getChallengeCode(), jtChallengeCode.getChallengeCode1());
                    return;
                case 102:
                default:
                    return;
                case 103:
                    if (JtClient.this.jtOnConnectListener != null) {
                        JtClient.this.jtOnConnectListener.onClose(message.obj.toString());
                        return;
                    }
                    return;
                case 104:
                    JtNetConfig jtNetConfig = (JtNetConfig) message.obj;
                    JtClient.this.localConfig = jtNetConfig;
                    if ("wifi".equals(jtNetConfig.getIsSuceess())) {
                        JtClient.this.authorization = true;
                    } else if ("0".equals(jtNetConfig.getIsSuceess())) {
                        JtClient.this.authorization = true;
                    }
                    int i = 0;
                    if ("1".equals(jtNetConfig.getIsLatest())) {
                        int i2 = SharedPreferencesHelp.getInstance(JtClient.this.context, "version").getInt("JT_VERSION_CODE", 0);
                        try {
                            i = Integer.parseInt(jtNetConfig.getVersion());
                        } catch (Exception unused) {
                        }
                        if (i2 < i) {
                            JtClient.this.nowVersion = i;
                            if (JtClient.this.jtOnConnectListener != null) {
                                JtClient.this.jtOnConnectListener.startDowloadHtml();
                            }
                            JtClient.this.downloadFile(jtNetConfig.getDownload());
                            return;
                        }
                        if (new File(JtFileUtils.getAppPath() + "html/index.html").exists()) {
                            JtClient.this.jtOnConnectListener.finish();
                            return;
                        }
                        JtClient.this.nowVersion = i;
                        if (JtClient.this.jtOnConnectListener != null) {
                            JtClient.this.jtOnConnectListener.startDowloadHtml();
                        }
                        JtClient.this.downloadFile(jtNetConfig.getDownload());
                        return;
                    }
                    if (new File(JtFileUtils.getAppPath() + "html/index.html").exists()) {
                        JtClient.this.jtOnConnectListener.finish();
                        return;
                    }
                    try {
                        i = Integer.parseInt(jtNetConfig.getVersion());
                    } catch (Exception unused2) {
                    }
                    JtClient.this.nowVersion = i;
                    if (JtClient.this.jtOnConnectListener != null) {
                        JtClient.this.jtOnConnectListener.startDowloadHtml();
                    }
                    JtClient.this.downloadFile(jtNetConfig.getDownload());
                    return;
                case 105:
                    if (JtClient.this.jtOnConnectListener != null) {
                        JtClient.this.jtOnConnectListener.downLoadHtmlProgress(((Integer) message.obj).intValue());
                        return;
                    }
                    return;
                case 106:
                    if (JtClient.this.jtOnConnectListener != null) {
                        JtL.m16342e("Thread.currentThread().getId() = " + Thread.currentThread().getId());
                        JtL.m16342e("getMainLooper().getThread().getId() = " + JtClient.this.context.getMainLooper().getThread().getId());
                        JtClient.this.jtOnConnectListener.dowloadHtmlSuccess((String) message.obj);
                        JtClient.this.jtOnConnectListener.unZipStat();
                    }
                    JtClient.this.deleteOldData();
                    return;
                case 107:
                    if (JtClient.this.jtOnConnectListener != null) {
                        JtClient.this.jtOnConnectListener.downloadHtmlFiled((Exception) message.obj);
                        return;
                    }
                    return;
                case 108:
                    SharedPreferencesHelp.getInstance(JtClient.this.context, "version").putInt("JT_VERSION_CODE", JtClient.this.nowVersion);
                    JtApp.getInstance().clearCache(JtClient.this.context);
                    if (JtClient.this.jtOnConnectListener != null) {
                        JtClient.this.jtOnConnectListener.unZipSuccess();
                        JtClient.this.jtOnConnectListener.finish();
                        return;
                    }
                    return;
                case 109:
                    if (JtClient.this.jtOnConnectListener != null) {
                        JtClient.this.jtOnConnectListener.unZipFiled((Exception) message.obj);
                        return;
                    }
                    return;
            }
        }
    }

    public JtClient(Activity activity) {
        this.context = activity.getBaseContext();
        if (JtApp.getInstance().executorService.isShutdown()) {
            JtApp.getInstance().executorService = Executors.newFixedThreadPool(1);
        }
        this.handler = new JtActivityHandler(activity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getNameFromUrl(String str) {
        return str.substring(str.lastIndexOf("/") + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadFile(String str) {
        if (!this.isDownlod) {
            Message message = new Message();
            message.what = 106;
            message.obj = "";
            this.handler.sendMessage(message);
        } else if (TextUtils.isEmpty(str)) {
            Message message2 = new Message();
            message2.what = 107;
            message2.obj = new Exception("下载地址不能为空：" + str);
            this.handler.sendMessage(message2);
        } else {
            Register1Bean register1Bean = new Register1Bean();
            register1Bean.setCmdType("download_html_start");
            register1Bean.setStatus("0");
            register1Bean.setMsg("开始下载获取到URL：" + str);
            if (this.jtOnConnectListener != null) {
                JtUploadLog.getInstance(this.context).updateData("1", JtGsonUtil.getInstance().toJson(register1Bean), "download_html_start");
            } else {
                JtUploadLog.getInstance(this.context).updateData("1", JtGsonUtil.getInstance().toJson(register1Bean), "download_html_start");
            }
            OkHttpClient.Builder readTimeout = new OkHttpClient.Builder().connectTimeout(20L, TimeUnit.SECONDS).writeTimeout(5L, TimeUnit.SECONDS).readTimeout(5L, TimeUnit.SECONDS);
            (!(readTimeout instanceof OkHttpClient.Builder) ? readTimeout.build() : NBSOkHttp3Instrumentation.builderInit(readTimeout)).newCall(new Request.Builder().url(str).build()).enqueue(new Callback() { // from class: com.chinaunicon.jtwifilib.jtcommon.util.JtClient.1
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    Message message3 = new Message();
                    message3.what = 107;
                    message3.obj = iOException;
                    JtClient.this.handler.sendMessage(message3);
                }

                /* JADX WARN: Removed duplicated region for block: B:59:0x0127 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:61:0x0122 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                @Override // okhttp3.Callback
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public void onResponse(okhttp3.Call r11, okhttp3.Response r12) throws java.io.IOException {
                    /*
                        Method dump skipped, instructions count: 299
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.chinaunicon.jtwifilib.jtcommon.util.JtClient.C41091.onResponse(okhttp3.Call, okhttp3.Response):void");
                }
            });
        }
    }

    public void setIsDownlod(boolean z) {
        this.isDownlod = z;
    }

    public void selectPremission() {
        this.dbManager = new DBManager(this.context);
        JtApp.getInstance().executorService.execute(new Runnable() { // from class: com.chinaunicon.jtwifilib.jtcommon.util.JtClient.2
            @Override // java.lang.Runnable
            public void run() {
                JtNetConfig selectConfig = JtClient.this.dbManager.selectConfig();
                List<JtNetPremission> selectPremission = JtClient.this.dbManager.selectPremission();
                if (selectConfig != null && selectPremission != null) {
                    selectConfig.setCmd(selectPremission);
                }
                Message message = new Message();
                message.obj = selectConfig;
                message.what = 104;
                JtClient.this.handler.sendMessage(message);
            }
        });
    }

    private boolean isPermission(String str) {
        JtNetConfig jtNetConfig = this.localConfig;
        if (jtNetConfig == null) {
            return true;
        }
        List<JtNetPremission> cmd = jtNetConfig.getCmd();
        int size = cmd.size();
        for (int i = 0; i < size; i++) {
            if (cmd.get(i).getName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void login(String str, String str2) {
        this.string16 = getString16();
        if (str2 != null && !TextUtils.isEmpty(str2)) {
            JtDataBuffer.token = JtStringUtil.getToken(JtDataBuffer.f9704pw, str2);
        } else {
            JtDataBuffer.token = JtStringUtil.getToken(JtDataBuffer.f9704pw, str);
        }
        sendMessage("{\"CmdType\":\"CHECK_PASSWD_PARAM\",\"SequenceId\":\"" + this.string16 + "\",\"Token\":\"" + JtDataBuffer.token + "\"}");
    }

    public void getDevices() {
        this.string16 = getString16();
        String str = "{\"CmdType\":\"GET_HG_SYSTEM_INFO\",\"SequenceId\":\"" + this.string16 + "\",\"Token\":\"" + JtDataBuffer.token + "\"}";
        JtL.m16342e("-------" + str);
        sendMessage(str);
    }

    public void openSocket(String str, String str2, String str3, int i) {
        JtDataBuffer.user = str;
        JtDataBuffer.f9703ip = str2;
        JtDataBuffer.f9704pw = str3;
        JtDataBuffer.PORT = i;
        this.string16 = getString16();
        sendMessage("{\"CmdType\":\"BIND_SEARCH\",\"SequenceId\":\"" + this.string16 + "\"}");
    }

    public void openSocket(String str, String str2, String str3) {
        JtDataBuffer.user = str;
        JtDataBuffer.f9703ip = str2;
        JtDataBuffer.f9704pw = str3;
        this.string16 = getString16();
        sendMessage("{\"CmdType\":\"BIND_SEARCH\",\"SequenceId\":\"" + this.string16 + "\"}");
    }

    public void sendMessage(final String str) {
        JtBaseModel jtBaseModel;
        try {
            jtBaseModel = (JtBaseModel) JtGsonUtil.getInstance().fromJson(str, (Class<Object>) JtBaseModel.class);
        } catch (Exception unused) {
            jtBaseModel = null;
        }
        if (jtBaseModel != null) {
            this.sCmdType = jtBaseModel.getCmdType();
        }
        Register1Bean register1Bean = new Register1Bean();
        register1Bean.setCmdType(jtBaseModel == null ? "send_message" : jtBaseModel.getCmdType());
        register1Bean.setStatus("0");
        register1Bean.setMsg(str);
        if (this.jtOnConnectListener != null) {
            JtUploadLog.getInstance(this.context).updateData("1", JtGsonUtil.getInstance().toJson(register1Bean), jtBaseModel == null ? "send_message" : jtBaseModel.getCmdType());
        } else {
            JtUploadLog.getInstance(this.context).updateData("1", JtGsonUtil.getInstance().toJson(register1Bean), jtBaseModel == null ? "send_message" : jtBaseModel.getCmdType());
        }
        if (jtBaseModel == null) {
            JtOnClientListener jtOnClientListener = this.jtOnConnectListener;
            if (jtOnClientListener != null) {
                jtOnClientListener.onError("传入数据格式不正确：" + str);
            }
        } else if (TextUtils.isEmpty(jtBaseModel.getCmdType())) {
            JtOnClientListener jtOnClientListener2 = this.jtOnConnectListener;
            if (jtOnClientListener2 != null) {
                jtOnClientListener2.onError("CmdType不能为空");
            }
        } else if (!this.authorization) {
            JtL.m16342e("-------装维专区-------授权失败------------");
            JtL.m16342e("-------装维专区------请检查appkey------------");
            Register1Bean register1Bean2 = new Register1Bean();
            register1Bean2.setCmdType("send_message");
            register1Bean2.setStatus("-1");
            register1Bean2.setMsg("授权失败，请检查appkey");
            if (this.jtOnConnectListener != null) {
                JtUploadLog.getInstance(this.context).updateData("2", JtGsonUtil.getInstance().toJson(register1Bean2), "send_message");
            } else {
                JtUploadLog.getInstance(this.context).updateData("2", JtGsonUtil.getInstance().toJson(register1Bean2), "send_message");
            }
        } else if (!isPermission(jtBaseModel.getCmdType())) {
            Register1Bean register1Bean3 = new Register1Bean();
            register1Bean3.setFailReason("无权限,请到后台配置权限再试吧");
            register1Bean3.setCmdType(jtBaseModel.getCmdType());
            register1Bean3.setStatus("-1");
            register1Bean3.setSequenceId(StringUtil.getString16());
            JtOnClientListener jtOnClientListener3 = this.jtOnConnectListener;
            if (jtOnClientListener3 != null) {
                jtOnClientListener3.onMessage(JtGsonUtil.getInstance().toJson(register1Bean3));
            }
        } else {
            JtL.m16342e("发送消息：" + str);
            Runnable runnable = new Runnable() { // from class: com.chinaunicon.jtwifilib.jtcommon.util.JtClient.3
                @Override // java.lang.Runnable
                public void run() {
                    Socket socket;
                    int i = JtDataBuffer.PORT;
                    Socket socket2 = null;
                    try {
                        try {
                            try {
                                socket = new Socket();
                                try {
                                    socket.connect(new InetSocketAddress(JtDataBuffer.f9703ip, i), 10000);
                                    socket.setKeepAlive(true);
                                    socket.setSoTimeout(10000);
                                    OutputStream outputStream = socket.getOutputStream();
                                    outputStream.write(JtClient.this.getRequestByte(str));
                                    outputStream.flush();
                                    String convertStreamToString = JtClient.this.convertStreamToString(socket.getInputStream());
                                    Gson gson = new Gson();
                                    JtResponseBean jtResponseBean = (JtResponseBean) (!(gson instanceof Gson) ? gson.fromJson(convertStreamToString, (Class<Object>) JtResponseBean.class) : NBSGsonInstrumentation.fromJson(gson, convertStreamToString, (Class<Object>) JtResponseBean.class));
                                    if (jtResponseBean != null) {
                                        String str2 = new String(Base64.decode(jtResponseBean.getReturn_Parameter(), 0));
                                        JtL.m16342e("返回值1:" + str2);
                                        if (!TextUtils.isEmpty(str2)) {
                                            JtL.m16341e("返回值2：", str2);
                                            Message message = new Message();
                                            message.what = 101;
                                            message.obj = str2;
                                            JtClient.this.handler.sendMessage(message);
                                        } else {
                                            Message message2 = new Message();
                                            message2.what = 100;
                                            message2.obj = convertStreamToString;
                                            JtClient.this.handler.sendMessage(message2);
                                        }
                                    } else {
                                        Message message3 = new Message();
                                        message3.what = 100;
                                        message3.obj = convertStreamToString;
                                        JtClient.this.handler.sendMessage(message3);
                                    }
                                    socket.close();
                                } catch (IOException unused2) {
                                    socket2 = socket;
                                    Register1Bean register1Bean4 = new Register1Bean();
                                    register1Bean4.setCmdType(JtClient.this.sCmdType);
                                    register1Bean4.setStatus("1");
                                    register1Bean4.setFailReason("连接断开");
                                    String json = JtGsonUtil.getInstance().toJson(register1Bean4);
                                    Message message4 = new Message();
                                    message4.what = 103;
                                    message4.obj = json;
                                    JtClient.this.handler.sendMessage(message4);
                                    Log.e("IO异常：", "报错了" + json);
                                    socket2.close();
                                } catch (Throwable th) {
                                    th = th;
                                    try {
                                        socket.close();
                                    } catch (Exception unused3) {
                                    }
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                socket = socket2;
                            }
                        } catch (IOException unused4) {
                        }
                    } catch (Exception unused5) {
                    }
                }
            };
            if (JtApp.getInstance().executorService.isShutdown()) {
                return;
            }
            JtApp.getInstance().executorService.execute(runnable);
        }
    }

    protected String convertStreamToString(InputStream inputStream) {
        byte[] bArr = new byte[0];
        try {
            try {
                bArr = JtStreamTool.readStream(inputStream);
                if (bArr.length == 0) {
                    return "";
                }
            } catch (IOException unused) {
                inputStream.close();
                Log.e("IO异常：", "2报错了");
                return new String(bArr);
            }
        } catch (IOException unused2) {
            Log.e("IO异常：", "2报错了");
            return new String(bArr);
        }
        return new String(bArr);
    }

    public String getString16() {
        StringBuffer stringBuffer = new StringBuffer();
        while (stringBuffer.length() <= 8) {
            stringBuffer.append(Integer.toHexString((int) (Math.random() * 100.0d)));
        }
        return stringBuffer.length() > 8 ? stringBuffer.substring(0, 8) : "";
    }

    @RequiresApi(api = 8)
    public byte[] getRequestByte(String str) {
        JtRequestBean jtRequestBean = new JtRequestBean();
        jtRequestBean.setID(new Random().nextLong());
        jtRequestBean.setPlugin_Name("");
        jtRequestBean.setRPCMethod("Post1");
        jtRequestBean.setVersion("1.0");
        String[] split = new String(Base64.encode(str.getBytes(), 0)).split("\n");
        StringBuffer stringBuffer = new StringBuffer();
        for (String str2 : split) {
            stringBuffer.append(str2);
        }
        jtRequestBean.setParameter(stringBuffer.toString());
        Gson create = new GsonBuilder().disableHtmlEscaping().create();
        String json = !(create instanceof Gson) ? create.toJson(jtRequestBean) : NBSGsonInstrumentation.toJson(create, jtRequestBean);
        byte[] bArr = new byte[json.getBytes().length + 4];
        byte[] m16336by = m16336by(json.length());
        byte[] bytes = json.getBytes();
        bArr[0] = m16336by[0];
        bArr[1] = m16336by[1];
        bArr[2] = m16336by[2];
        bArr[3] = m16336by[3];
        System.arraycopy(bytes, 0, bArr, 4, bytes.length);
        bArr[0] = m16336by[0];
        bArr[1] = m16336by[1];
        bArr[2] = m16336by[2];
        bArr[3] = m16336by[3];
        System.arraycopy(bytes, 0, bArr, 4, bytes.length);
        return bArr;
    }

    /* renamed from: by */
    static byte[] m16336by(int i) {
        return new byte[]{(byte) (i >>> 24), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public void onDestroy() {
        this.handler.removeCallbacksAndMessages(null);
        JtApp.getInstance().executorService.shutdownNow();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteOldData() {
        if (!this.isDownlod) {
            Message message = new Message();
            message.what = 108;
            message.obj = "";
            this.handler.sendMessage(message);
            return;
        }
        JtApp.getInstance().executorService.execute(new Runnable() { // from class: com.chinaunicon.jtwifilib.jtcommon.util.JtClient.4
            @Override // java.lang.Runnable
            @RequiresApi(api = 24)
            public void run() {
                String str = JtClient.BLACKTECH_HOT_UPDATE_FILE_PATH;
                for (File file : JtFileUtils.getFilesInDirectory(str)) {
                    if (file.getName().endsWith(JtClient.UXUE_TEMP_FILE_SUFFIX)) {
                        JtFileUtils.deleteDirectory(str);
                        try {
                            ZipFileUtil.upZipFile(file.getAbsolutePath(), str);
                            file.delete();
                            Message message2 = new Message();
                            message2.what = 108;
                            message2.obj = "";
                            JtClient.this.handler.sendMessage(message2);
                        } catch (Exception e) {
                            Message message3 = new Message();
                            message3.what = 109;
                            message3.obj = e;
                            JtClient.this.handler.sendMessage(message3);
                        }
                    }
                }
            }
        });
    }
}
