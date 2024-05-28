package com.chinaunicon.jtwifilib.jtcommon.util;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import androidx.annotation.RequiresApi;
import com.chinaunicon.jtwifilib.core.global.JtApp;
import com.chinaunicon.jtwifilib.core.utils.JtGsonUtil;
import com.chinaunicon.jtwifilib.core.utils.JtL;
import com.chinaunicon.jtwifilib.jtcommon.JtOnSpeedClientListener;
import com.chinaunicon.jtwifilib.jtcommon.OnSpeedListener;
import com.chinaunicon.jtwifilib.jtcommon.model.JtIntnetInfo;
import com.chinaunicon.jtwifilib.jtcommon.testspeed.manager.DownloadTask;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation;
import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import okhttp3.OkHttpClient;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class JtSpeedClient {
    private static final int ON_SPEED = 1000;
    private static final int ON_SPEED_FINISH = 1001;
    float[] arr;
    private Activity context;
    Map<Integer, String> errorSpeed;
    private ExecutorService executorService;
    private JtActivityHandler handler;
    List<String> historySpeed;
    private JtOnSpeedClientListener onSpeedClientListener;
    private int millisecond = 1000;
    private boolean isStart = false;
    private int CORE_POOL_SIZE = 21;

    /* renamed from: ic */
    int f9712ic = 0;
    float max = 0.0f;
    float min = 0.0f;
    float aveg = 0.0f;
    int index = 0;
    private boolean isRun = true;
    private OkHttpClient okClient = NBSOkHttp3Instrumentation.init();

    public JtSpeedClient(Activity activity) {
        this.context = activity;
        this.handler = new JtActivityHandler(activity);
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
                case 1000:
                    Bundle bundle = (Bundle) message.obj;
                    float f = bundle.getFloat("avg");
                    float f2 = bundle.getFloat("max");
                    float f3 = bundle.getFloat("min");
                    float f4 = bundle.getFloat("speed");
                    if (JtSpeedClient.this.onSpeedClientListener != null) {
                        JtSpeedClient.this.onSpeedClientListener.onSpeed(f4, f2, f3, f);
                        return;
                    }
                    return;
                case 1001:
                    if (JtSpeedClient.this.onSpeedClientListener != null) {
                        JtOnSpeedClientListener jtOnSpeedClientListener = JtSpeedClient.this.onSpeedClientListener;
                        jtOnSpeedClientListener.onAverageSpeed(JtSpeedClient.this.aveg + "");
                    }
                    if (JtSpeedClient.this.onSpeedClientListener != null) {
                        JtOnSpeedClientListener jtOnSpeedClientListener2 = JtSpeedClient.this.onSpeedClientListener;
                        StringBuilder sb = new StringBuilder();
                        JtSpeedClient jtSpeedClient = JtSpeedClient.this;
                        sb.append(jtSpeedClient.round(jtSpeedClient.min));
                        sb.append("");
                        jtOnSpeedClientListener2.onMinSpeed(sb.toString());
                    }
                    if (JtSpeedClient.this.onSpeedClientListener != null) {
                        JtOnSpeedClientListener jtOnSpeedClientListener3 = JtSpeedClient.this.onSpeedClientListener;
                        StringBuilder sb2 = new StringBuilder();
                        JtSpeedClient jtSpeedClient2 = JtSpeedClient.this;
                        sb2.append(jtSpeedClient2.round(jtSpeedClient2.max));
                        sb2.append("");
                        jtOnSpeedClientListener3.onMaxSpeed(sb2.toString());
                    }
                    if (JtSpeedClient.this.onSpeedClientListener != null) {
                        JtOnSpeedClientListener jtOnSpeedClientListener4 = JtSpeedClient.this.onSpeedClientListener;
                        jtOnSpeedClientListener4.onCurrentSpeed(JtSpeedClient.this.aveg + "");
                    }
                    if (JtSpeedClient.this.onSpeedClientListener != null) {
                        JtSpeedClient.this.onSpeedClientListener.onFinish(8, 8, 8);
                    }
                    JtSpeedClient.this.stopDownloadSpeedTest();
                    return;
                default:
                    return;
            }
        }
    }

    public void startSpeed(List<JtIntnetInfo.WifiSpeedNode> list) {
        startSpeed(list, this.millisecond);
    }

    public void startSpeed(List<JtIntnetInfo.WifiSpeedNode> list, int i) {
        startSpeed(list, this.CORE_POOL_SIZE, i);
    }

    public void startSpeed(List<JtIntnetInfo.WifiSpeedNode> list, int i, int i2) {
        if (list == null) {
            JtOnSpeedClientListener jtOnSpeedClientListener = this.onSpeedClientListener;
            if (jtOnSpeedClientListener != null) {
                jtOnSpeedClientListener.onFiled("downUrl不能为空");
            }
        } else if (list.size() == 0) {
            JtOnSpeedClientListener jtOnSpeedClientListener2 = this.onSpeedClientListener;
            if (jtOnSpeedClientListener2 != null) {
                jtOnSpeedClientListener2.onFiled("downUrl不能为空");
            }
        } else if (this.isStart) {
            JtOnSpeedClientListener jtOnSpeedClientListener3 = this.onSpeedClientListener;
            if (jtOnSpeedClientListener3 != null) {
                jtOnSpeedClientListener3.onFiled("测速中请勿重新点击");
            }
        } else if (i < 5) {
            JtOnSpeedClientListener jtOnSpeedClientListener4 = this.onSpeedClientListener;
            if (jtOnSpeedClientListener4 != null) {
                jtOnSpeedClientListener4.onFiled("corePoolSize不能小于5");
            }
        } else {
            this.isStart = true;
            this.CORE_POOL_SIZE = i;
            this.millisecond = i2;
            initDownloadManager(list);
        }
    }

    public String getIdString() {
        return "uuid=" + UUID.randomUUID().toString().replace("-", "");
    }

    private void initDownloadManager(List<JtIntnetInfo.WifiSpeedNode> list) {
        this.max = 0.0f;
        this.min = 0.0f;
        this.aveg = 0.0f;
        this.index = 0;
        this.executorService = Executors.newFixedThreadPool(this.CORE_POOL_SIZE);
        this.historySpeed = new ArrayList();
        this.errorSpeed = new HashMap();
        this.arr = new float[this.CORE_POOL_SIZE - 1];
        for (int i = 0; i < this.CORE_POOL_SIZE - 1; i++) {
            if (!TextUtils.isEmpty(list.get(this.index).getSpeedUrl())) {
                this.executorService.execute(new DownloadTask(i, list.get(this.index).getSpeedUrl(), new OnSpeedListener() { // from class: com.chinaunicon.jtwifilib.jtcommon.util.JtSpeedClient.1
                    @Override // com.chinaunicon.jtwifilib.jtcommon.OnSpeedListener
                    public void onSpeed(int i2, float f) {
                        JtSpeedClient.this.arr[i2] = f;
                    }

                    @Override // com.chinaunicon.jtwifilib.jtcommon.OnSpeedListener
                    public void onFiled(int i2, String str) {
                        if (JtSpeedClient.this.errorSpeed != null) {
                            JtSpeedClient.this.errorSpeed.put(Integer.valueOf(i2), str);
                        }
                    }
                }, this.okClient));
            }
            this.index++;
            if (this.index > list.size() - 1) {
                this.index = 0;
            }
        }
        this.isRun = true;
        this.executorService.execute(new Runnable() { // from class: com.chinaunicon.jtwifilib.jtcommon.util.JtSpeedClient.2
            @Override // java.lang.Runnable
            public void run() {
                JtSpeedClient.this.f9712ic = 0;
                while (JtSpeedClient.this.f9712ic < 15000 / JtSpeedClient.this.millisecond) {
                    try {
                        if (!JtSpeedClient.this.isRun) {
                            throw new Exception();
                        }
                        JtL.m16342e("测速速率：" + JtGsonUtil.getInstance().toJson(JtSpeedClient.this.arr));
                        float f = 0.0f;
                        for (int i2 = 0; i2 < JtSpeedClient.this.arr.length; i2++) {
                            f += JtSpeedClient.this.arr[i2];
                        }
                        JtSpeedClient.this.historySpeed.add(JtSpeedClient.this.round(f) + "");
                        JtL.m16342e("测速速率：" + JtSpeedClient.this.round(f));
                        if (JtSpeedClient.this.f9712ic > 4000 / JtSpeedClient.this.millisecond) {
                            JtL.m16342e("记录个数：" + JtSpeedClient.this.f9712ic);
                            if (JtSpeedClient.this.max < f) {
                                JtSpeedClient.this.max = f;
                            }
                            if (JtSpeedClient.this.f9712ic == 5000 / JtSpeedClient.this.millisecond) {
                                JtSpeedClient.this.min = f;
                            }
                            if (JtSpeedClient.this.min > f) {
                                JtSpeedClient.this.min = f;
                            }
                        }
                        if (JtSpeedClient.this.f9712ic > 4000 / JtSpeedClient.this.millisecond) {
                            JtSpeedClient.this.aveg = JtSpeedClient.this.round(f);
                            JtL.m16342e("进入这里了2");
                        } else {
                            JtSpeedClient.this.aveg = 0.0f;
                            JtL.m16342e("进入这里了");
                        }
                        Bundle bundle = new Bundle();
                        bundle.putFloat("avg", JtSpeedClient.this.aveg);
                        bundle.putFloat("speed", JtSpeedClient.this.round(f));
                        bundle.putFloat("max", JtSpeedClient.this.round(JtSpeedClient.this.max));
                        bundle.putFloat("min", JtSpeedClient.this.round(JtSpeedClient.this.min));
                        Message message = new Message();
                        message.what = 1000;
                        message.obj = bundle;
                        JtSpeedClient.this.handler.sendMessage(message);
                        JtSpeedClient.this.f9712ic++;
                        try {
                            Thread.sleep(JtSpeedClient.this.millisecond);
                        } catch (InterruptedException unused) {
                        }
                    } catch (Exception unused2) {
                        return;
                    }
                }
                Message message2 = new Message();
                message2.what = 1001;
                JtSpeedClient.this.handler.sendMessage(message2);
                JtSpeedClient.this.isRun = false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float round(float f) {
        return Float.valueOf(new DecimalFormat("#.00").format(f)).floatValue();
    }

    public void stopDownloadSpeedTest() {
        onDistory();
    }

    public void onDistory() {
        if (this.historySpeed != null || this.errorSpeed != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("msg", "测速完成");
            hashMap.put("downloadSpeed", JtGsonUtil.getInstance().toJson((Object) this.historySpeed));
            hashMap.put("errorSpeed", JtGsonUtil.getInstance().toJson(this.errorSpeed));
            JtUploadLog.getInstance(JtApp.getInstance().getContext()).updateData("1", JtGsonUtil.getInstance().toJson(hashMap), "speed_download_success");
        }
        this.isRun = false;
        ExecutorService executorService = this.executorService;
        if (executorService != null) {
            executorService.shutdownNow();
            this.executorService = null;
        }
        this.isStart = false;
        this.arr = null;
        this.historySpeed = null;
        this.errorSpeed = null;
        this.handler.removeCallbacksAndMessages(null);
    }

    public void onOnSpeedClientListener(JtOnSpeedClientListener jtOnSpeedClientListener) {
        this.onSpeedClientListener = jtOnSpeedClientListener;
    }
}
