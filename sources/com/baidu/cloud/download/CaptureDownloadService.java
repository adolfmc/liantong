package com.baidu.cloud.download;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.p083v4.content.LocalBroadcastManager;
import com.baidu.cloud.download.DownloadRequest;
import com.baidu.cloud.download.base.DownloadCallback;
import com.baidu.cloud.download.exception.DownloadException;
import com.baidu.cloud.download.utils.CommonUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CaptureDownloadService extends Service {
    public static final String ACTION_CANCEL = "com.baidu.cloudar.download.ACTION_CANCEL";
    public static final String ACTION_CANCEL_ALL = "com.baidu.cloudar.download.ACTION_CANCEL_ALL";
    public static final String ACTION_DOWNLOAD = "com.baidu.cloudar.download.ACTION_DOWNLOAD";
    public static final String ACTION_DOWNLOAD_BROAD_CAST = "com.baidu.cloudar.download.ACTION_BROAD_CAST";
    public static final String ACTION_PAUSE = "com.baidu.cloudar.download.ACTION_PAUSE";
    public static final String ACTION_PAUSE_ALL = "com.baidu.cloudar.download.ACTION_PAUSE_ALL";
    public static final String EXTRA_FILE_INFO = "extra_file_info";
    public static final String EXTRA_POSITION = "extra_position";
    public static final String EXTRA_TAG = "extra_tag";
    private static final String TAG = "CaptureDownloadService";
    private DownloadManager mDownloadManager;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void start(Context context, String str, RequestTask requestTask) {
        start(context, 0, str, requestTask);
    }

    public static void start(Context context, int i, String str, RequestTask requestTask) {
        Intent intent = new Intent(context, CaptureDownloadService.class);
        intent.setAction("com.baidu.cloudar.download.ACTION_DOWNLOAD");
        intent.putExtra("extra_position", i);
        intent.putExtra("extra_tag", str);
        JSONObject json = requestTask.toJson();
        intent.putExtra("extra_file_info", !(json instanceof JSONObject) ? json.toString() : NBSJSONObjectInstrumentation.toString(json));
        context.startService(intent);
    }

    public static void pause(Context context, String str) {
        Intent intent = new Intent(context, CaptureDownloadService.class);
        intent.setAction("com.baidu.cloudar.download.ACTION_PAUSE");
        intent.putExtra("extra_tag", str);
        context.startService(intent);
    }

    private void pause(String str) {
        this.mDownloadManager.pause(str);
    }

    public static void cancel(Context context, String str) {
        Intent intent = new Intent(context, CaptureDownloadService.class);
        intent.setAction("com.baidu.cloudar.download.ACTION_CANCEL");
        intent.putExtra("extra_tag", str);
        context.startService(intent);
    }

    private void cancel(String str) {
        this.mDownloadManager.cancel(str);
    }

    public static void pauseAll(Context context) {
        Intent intent = new Intent(context, CaptureDownloadService.class);
        intent.setAction("com.baidu.cloudar.download.ACTION_PAUSE_ALL");
        context.startService(intent);
    }

    private void pauseAll() {
        this.mDownloadManager.pauseAll();
    }

    public static void cancelAll(Context context) {
        Intent intent = new Intent(context, CaptureDownloadService.class);
        intent.setAction("com.baidu.cloudar.download.ACTION_CANCEL_ALL");
        context.startService(intent);
    }

    private void cancelAll() {
        this.mDownloadManager.cancelAll();
    }

    public static void destroy(Context context) {
        context.stopService(new Intent(context, CaptureDownloadService.class));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0030, code lost:
        if (r0.equals("com.baidu.cloudar.download.ACTION_DOWNLOAD") != false) goto L6;
     */
    @Override // android.app.Service
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int onStartCommand(android.content.Intent r8, int r9, int r10) {
        /*
            r7 = this;
            if (r8 == 0) goto L73
            java.lang.String r0 = r8.getAction()
            java.lang.String r1 = "extra_position"
            r2 = 0
            int r1 = r8.getIntExtra(r1, r2)
            com.baidu.cloud.download.RequestTask r3 = new com.baidu.cloud.download.RequestTask
            r3.<init>()
            java.lang.String r4 = "extra_file_info"
            java.lang.String r4 = r8.getStringExtra(r4)
            r3.parse(r4)
            java.lang.String r4 = "extra_tag"
            java.lang.String r4 = r8.getStringExtra(r4)
            r5 = -1
            int r6 = r0.hashCode()
            switch(r6) {
                case -1845641145: goto L51;
                case -1229466579: goto L47;
                case -540402171: goto L3d;
                case 1657148651: goto L33;
                case 1915551059: goto L2a;
                default: goto L29;
            }
        L29:
            goto L5b
        L2a:
            java.lang.String r6 = "com.baidu.cloudar.download.ACTION_DOWNLOAD"
            boolean r0 = r0.equals(r6)
            if (r0 == 0) goto L5b
            goto L5c
        L33:
            java.lang.String r2 = "com.baidu.cloudar.download.ACTION_PAUSE"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L5b
            r2 = 1
            goto L5c
        L3d:
            java.lang.String r2 = "com.baidu.cloudar.download.ACTION_CANCEL"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L5b
            r2 = 2
            goto L5c
        L47:
            java.lang.String r2 = "com.baidu.cloudar.download.ACTION_PAUSE_ALL"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L5b
            r2 = 3
            goto L5c
        L51:
            java.lang.String r2 = "com.baidu.cloudar.download.ACTION_CANCEL_ALL"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L5b
            r2 = 4
            goto L5c
        L5b:
            r2 = r5
        L5c:
            switch(r2) {
                case 0: goto L70;
                case 1: goto L6c;
                case 2: goto L68;
                case 3: goto L64;
                case 4: goto L60;
                default: goto L5f;
            }
        L5f:
            goto L73
        L60:
            r7.cancelAll()
            goto L73
        L64:
            r7.pauseAll()
            goto L73
        L68:
            r7.cancel(r4)
            goto L73
        L6c:
            r7.pause(r4)
            goto L73
        L70:
            r7.download(r1, r3, r4)
        L73:
            int r8 = super.onStartCommand(r8, r9, r10)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.cloud.download.CaptureDownloadService.onStartCommand(android.content.Intent, int, int):int");
    }

    private void download(int i, RequestTask requestTask, String str) {
        this.mDownloadManager.download(new DownloadRequest.Builder().setUri(requestTask.getUrl()).build(), str, new ProgressCallback(i, requestTask, getApplicationContext()));
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class ProgressCallback extends DownloadCallback {
        private RequestTask mAppInfo;
        private int mLastProgress;
        private long mLastTime;
        private LocalBroadcastManager mLocalBroadcastManager;
        private int mPosition;

        public ProgressCallback(int i, RequestTask requestTask, Context context) {
            this.mPosition = i;
            this.mAppInfo = requestTask;
            this.mLocalBroadcastManager = LocalBroadcastManager.getInstance(context);
        }

        @Override // com.baidu.cloud.download.base.DownloadCallback
        public void onProgress(long j, long j2, int i) {
            if (this.mLastTime == 0) {
                this.mLastTime = System.currentTimeMillis();
            }
            this.mAppInfo.setStatus(3);
            this.mAppInfo.setProgress(i);
            this.mAppInfo.setDownloadPerSize(CommonUtils.getDownloadPerSize(j, j2));
            if (checkSendBroadLimit(i)) {
                sendBroadCast(this.mAppInfo);
            }
        }

        @Override // com.baidu.cloud.download.base.DownloadCallback
        public void onCompleted(String str) {
            this.mAppInfo.setStatus(6);
            this.mAppInfo.setProgress(100);
            this.mAppInfo.setSavePath(str);
            sendBroadCast(this.mAppInfo);
        }

        @Override // com.baidu.cloud.download.base.DownloadCallback
        public void onDownloadPaused() {
            this.mAppInfo.setStatus(4);
            sendBroadCast(this.mAppInfo);
        }

        @Override // com.baidu.cloud.download.base.DownloadCallback
        public void onDownloadCanceled() {
            this.mAppInfo.setStatus(0);
            this.mAppInfo.setProgress(0);
            this.mAppInfo.setDownloadPerSize("");
            sendBroadCast(this.mAppInfo);
        }

        @Override // com.baidu.cloud.download.base.DownloadCallback
        public void onFailed(DownloadException downloadException) {
            downloadException.printStackTrace();
            this.mAppInfo.setStatus(5);
            sendBroadCast(this.mAppInfo);
        }

        private void sendBroadCast(RequestTask requestTask) {
            Intent intent = new Intent();
            intent.setAction("com.baidu.cloudar.download.ACTION_BROAD_CAST");
            intent.putExtra("extra_position", this.mPosition);
            JSONObject json = requestTask.toJson();
            intent.putExtra("extra_file_info", !(json instanceof JSONObject) ? json.toString() : NBSJSONObjectInstrumentation.toString(json));
            this.mLocalBroadcastManager.sendBroadcast(intent);
        }

        private boolean checkSendBroadLimit(int i) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.mLastTime <= 300 || i == this.mLastProgress) {
                return false;
            }
            this.mLastTime = currentTimeMillis;
            this.mLastProgress = i;
            return true;
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mDownloadManager = DownloadManager.getInstance();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.mDownloadManager.pauseAll();
    }
}
