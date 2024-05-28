package com.baidu.cloud.media.download;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import com.baidu.cloud.media.download.DownloadableVideoItem;
import com.baidu.cloud.media.download.HttpTSTask;
import com.baidu.cloud.media.download.M3U8Parser;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class HLSDownloadableItem extends DownloadableVideoItem {
    protected static final String SP_JSON_VALUE_FILEFOLDER = "fold";
    protected static final String SP_JSON_VALUE_FILENAME = "file";
    protected static final String SP_JSON_VALUE_PROGRESS = "prgr";
    protected static final String SP_JSON_VALUE_STATE = "st";
    protected static final String SP_JSON_VALUE_TSDOWNLOADED = "tsdl";
    protected static final String SP_JSON_VALUE_URL = "url";
    protected static final String SP_JSON_VALUE_URLMD5 = "urle";
    private static final String TAG = "HLSVideoDownloader";
    protected static final String TS_LIST_FILE_NAME = "tsdl.data";
    private Context context;
    private volatile boolean saveM3U8Success;
    private String spName;
    private ArrayList<String> tsLists;
    private String urlMd5;
    private ExecutorService threadRunner = null;
    private volatile int downloadedSize = 0;
    private String drmToken = null;

    static /* synthetic */ int access$608(HLSDownloadableItem hLSDownloadableItem) {
        int i = hLSDownloadableItem.downloadedSize;
        hLSDownloadableItem.downloadedSize = i + 1;
        return i;
    }

    public String getDrmToken() {
        return this.drmToken;
    }

    public void setDrmToken(String str) {
        this.drmToken = str;
    }

    public HLSDownloadableItem(Context context, String str, String str2, String str3, String str4, String str5) {
        this.urlMd5 = "";
        this.spName = "";
        this.context = context;
        this.url = str;
        this.urlMd5 = str2;
        this.folderPath = str3;
        this.fileName = str4;
        this.spName = str5;
    }

    private void downloadM3U8Files() {
        try {
            if (!new URL(this.url).getPath().endsWith(".m3u8")) {
                this.failReason = "only download m3u8 video";
                this.errorCode = 1;
                setState(DownloadableVideoItem.DownloadStatus.ERROR);
                return;
            }
            if (this.folderPath == null || this.folderPath.equals("")) {
                String downloadRootForCurrentUser = VideoDownloadManager.getInstanceForInner().getDownloadRootForCurrentUser();
                if (downloadRootForCurrentUser == null) {
                    this.failReason = "sdcard is unmounted";
                    this.errorCode = 3;
                    setState(DownloadableVideoItem.DownloadStatus.ERROR);
                    return;
                }
                this.folderPath = downloadRootForCurrentUser + this.urlMd5 + "/";
            }
            if (!this.saveM3U8Success) {
                if (!tryInfoFromFile()) {
                    if (!checkEnvironment()) {
                        setState(DownloadableVideoItem.DownloadStatus.ERROR);
                        return;
                    }
                    Runnable runnable = new Runnable() { // from class: com.baidu.cloud.media.download.HLSDownloadableItem.1
                        @Override // java.lang.Runnable
                        public void run() {
                            M3U8Parser.M3U8ParserListener m3U8ParserListener = new M3U8Parser.M3U8ParserListener() { // from class: com.baidu.cloud.media.download.HLSDownloadableItem.1.1
                                @Override // com.baidu.cloud.media.download.M3U8Parser.M3U8ParserListener
                                public void onParseComplete(List<String> list) {
                                    if (HLSDownloadableItem.this.downloadStatus == DownloadableVideoItem.DownloadStatus.DOWNLOADING) {
                                        HLSDownloadableItem.this.tsLists = (ArrayList) list;
                                        HLSDownloadableItem.this.saveTSList();
                                        HLSDownloadableItem.this.saveM3U8Success = true;
                                        HLSDownloadableItem.this.downloadTSFiles();
                                    }
                                }

                                @Override // com.baidu.cloud.media.download.M3U8Parser.M3U8ParserListener
                                public void onParseFailed(int i) {
                                    if (HLSDownloadableItem.this.downloadStatus == DownloadableVideoItem.DownloadStatus.DOWNLOADING) {
                                        Log.d("HLSVideoDownloader", "Parse failed: error code is " + i);
                                        HLSDownloadableItem hLSDownloadableItem = HLSDownloadableItem.this;
                                        hLSDownloadableItem.failReason = "parse M3U8 failed, reason = " + DownloadableVideoItem.ERROR_CODE_DESC[i];
                                        HLSDownloadableItem.this.errorCode = i;
                                        HLSDownloadableItem.this.setState(DownloadableVideoItem.DownloadStatus.ERROR);
                                    }
                                }
                            };
                            Context context = HLSDownloadableItem.this.context;
                            new M3U8Parser(context, HLSDownloadableItem.this.folderPath + "/" + HLSDownloadableItem.this.fileName).parseM3U8Stream(HLSDownloadableItem.this.url, HLSDownloadableItem.this.drmToken, m3U8ParserListener);
                        }
                    };
                    ExecutorService executorService = this.threadRunner;
                    if (executorService == null || executorService.isShutdown()) {
                        Log.d("HLSVideoDownloader", "new executor is created now to download m3u8 file");
                        this.threadRunner = Executors.newSingleThreadExecutor();
                    }
                    this.threadRunner.execute(runnable);
                    return;
                }
                this.saveM3U8Success = true;
            }
            downloadTSFiles();
        } catch (Exception e) {
            Log.d("HLSVideoDownloader", "" + e.getMessage());
            this.failReason = "url format is invalid";
            this.errorCode = 1;
            setState(DownloadableVideoItem.DownloadStatus.ERROR);
        }
    }

    private boolean checkEnvironment() {
        boolean z = true;
        try {
            if (!"mounted".equals(Environment.getExternalStorageState())) {
                this.failReason = "save file failed, sdcard unmounted";
                this.errorCode = 3;
                z = false;
            }
            if (DownloadUtils.isNetworkAvailable(this.context)) {
                return z;
            }
            this.failReason = "network is not available";
            this.errorCode = 2;
            return false;
        } catch (Exception e) {
            Log.d("HLSVideoDownloader", "checkEnvironment " + e.getMessage());
            return z;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void saveTSList() {
        ArrayList<String> arrayList = this.tsLists;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(this.folderPath + "/tsdl.data");
                    try {
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream2);
                        objectOutputStream.writeObject(this.tsLists);
                        fileOutputStream2.close();
                        fileOutputStream = objectOutputStream;
                    } catch (Exception e) {
                        e = e;
                        fileOutputStream = fileOutputStream2;
                        Log.d("HLSVideoDownloader", "" + e.getMessage());
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                            fileOutputStream = fileOutputStream;
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException unused) {
                            }
                        }
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (IOException unused2) {
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private boolean tryInfoFromFile() {
        if (!new File(this.folderPath + "/" + this.fileName).exists()) {
            return false;
        }
        if (this.tsLists != null) {
            return true;
        }
        FileInputStream fileInputStream = null;
        try {
            try {
                FileInputStream fileInputStream2 = new FileInputStream(this.folderPath + "/tsdl.data");
                try {
                    this.tsLists = (ArrayList) new ObjectInputStream(fileInputStream2).readObject();
                    try {
                        fileInputStream2.close();
                        return true;
                    } catch (IOException unused) {
                        return true;
                    }
                } catch (Exception e) {
                    e = e;
                    fileInputStream = fileInputStream2;
                    Log.d("HLSVideoDownloader", "" + e.getMessage());
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException unused2) {
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException unused3) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadTSFiles() {
        ArrayList<String> arrayList = this.tsLists;
        if (arrayList == null || arrayList.size() == 0) {
            this.failReason = "tsList.size == 0";
            this.errorCode = 4;
            setState(DownloadableVideoItem.DownloadStatus.ERROR);
        } else if (!checkEnvironment()) {
            setState(DownloadableVideoItem.DownloadStatus.ERROR);
        } else {
            executeNextTask(new HttpTSTask.HttpResponseListener() { // from class: com.baidu.cloud.media.download.HLSDownloadableItem.2
                @Override // com.baidu.cloud.media.download.HttpTSTask.HttpResponseListener
                public void onHttpResponse(int i, int i2, int i3) {
                    if (i == 1) {
                        if (HLSDownloadableItem.this.downloadStatus == DownloadableVideoItem.DownloadStatus.PAUSED || HLSDownloadableItem.this.downloadStatus == DownloadableVideoItem.DownloadStatus.DELETED) {
                            return;
                        }
                        HLSDownloadableItem.access$608(HLSDownloadableItem.this);
                        HLSDownloadableItem.this.stateChanged(i2, i3);
                        HLSDownloadableItem.this.executeNextTask(this);
                    } else if (HLSDownloadableItem.this.downloadStatus == DownloadableVideoItem.DownloadStatus.PAUSED || HLSDownloadableItem.this.downloadStatus == DownloadableVideoItem.DownloadStatus.DELETED) {
                    } else {
                        if (i == -2) {
                            HLSDownloadableItem hLSDownloadableItem = HLSDownloadableItem.this;
                            hLSDownloadableItem.failReason = "network has problem";
                            hLSDownloadableItem.errorCode = 2;
                        } else {
                            HLSDownloadableItem hLSDownloadableItem2 = HLSDownloadableItem.this;
                            hLSDownloadableItem2.failReason = "save the " + HLSDownloadableItem.this.downloadedSize + "th ts file - interrupted";
                            HLSDownloadableItem.this.errorCode = 7;
                        }
                        HLSDownloadableItem.this.setState(DownloadableVideoItem.DownloadStatus.ERROR);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeNextTask(HttpTSTask.HttpResponseListener httpResponseListener) {
        if (this.downloadedSize >= this.tsLists.size()) {
            done();
            return;
        }
        HttpTSTask httpTSTask = new HttpTSTask(this.tsLists.get(this.downloadedSize), this.folderPath + "/" + (this.downloadedSize + 1) + ".ts", httpResponseListener);
        ExecutorService executorService = this.threadRunner;
        if (executorService == null || executorService.isShutdown()) {
            Log.d("HLSVideoDownloader", "new executor is created now");
            this.threadRunner = Executors.newSingleThreadExecutor();
        }
        this.threadRunner.execute(httpTSTask);
    }

    private void done() {
        setState(DownloadableVideoItem.DownloadStatus.COMPLETED);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setState(DownloadableVideoItem.DownloadStatus downloadStatus) {
        VideoDownloadManager instanceForInner;
        if (this.downloadStatus == DownloadableVideoItem.DownloadStatus.DOWNLOADING && downloadStatus != DownloadableVideoItem.DownloadStatus.DOWNLOADING && (instanceForInner = VideoDownloadManager.getInstanceForInner()) != null) {
            instanceForInner.tryConsumeSuspendingTask();
        }
        this.downloadStatus = downloadStatus;
        if (downloadStatus != DownloadableVideoItem.DownloadStatus.PAUSED && downloadStatus != DownloadableVideoItem.DownloadStatus.ERROR) {
            this.failReason = "";
        }
        if (downloadStatus != DownloadableVideoItem.DownloadStatus.ERROR) {
            this.errorCode = 0;
        }
        stateChanged(0, 0);
    }

    protected void stateChanged(int i, int i2) {
        ArrayList<String> arrayList;
        if (this.downloadStatus == DownloadableVideoItem.DownloadStatus.DOWNLOADING && (arrayList = this.tsLists) != null && arrayList.size() > 0) {
            this.progress = Math.round((this.downloadedSize / this.tsLists.size()) * 10000.0f);
            this.speed = i;
            this.totalSize += i2;
        }
        hibernate();
        setChanged();
        notifyObservers();
    }

    public boolean start() {
        if (this.downloadStatus != DownloadableVideoItem.DownloadStatus.DOWNLOADING) {
            setState(DownloadableVideoItem.DownloadStatus.DOWNLOADING);
            downloadM3U8Files();
            return true;
        }
        Log.e("HLSVideoDownloader", "start failed because downloadStatus = " + this.downloadStatus.name());
        return false;
    }

    public boolean pause() {
        if (this.downloadStatus != DownloadableVideoItem.DownloadStatus.PAUSED && this.downloadStatus != DownloadableVideoItem.DownloadStatus.COMPLETED && this.downloadStatus != DownloadableVideoItem.DownloadStatus.DELETED) {
            ExecutorService executorService = this.threadRunner;
            if (executorService != null && !executorService.isShutdown()) {
                this.threadRunner.shutdownNow();
            }
            this.failReason = "manually pause";
            setState(DownloadableVideoItem.DownloadStatus.PAUSED);
            return true;
        }
        Log.e("HLSVideoDownloader", "pause not work, && downloadStatus = " + this.downloadStatus.name());
        return false;
    }

    private void hibernate() {
        String json;
        if (this.downloadStatus == DownloadableVideoItem.DownloadStatus.DELETED || (json = toJson()) == null) {
            return;
        }
        SharedPreferences.Editor edit = this.context.getSharedPreferences(this.spName, 0).edit();
        edit.putString(this.urlMd5, json);
        if (Build.VERSION.SDK_INT >= 9) {
            edit.apply();
        } else {
            edit.commit();
        }
    }

    private String toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("url", this.url);
            jSONObject.put("fold", this.folderPath);
            jSONObject.put("file", this.fileName);
            jSONObject.put("st", this.downloadStatus.getCode());
            jSONObject.put("prgr", this.progress);
            jSONObject.put("tsdl", this.downloadedSize);
            return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        } catch (Exception e) {
            Log.e("HLSVideoDownloader", "" + e.getMessage());
            return null;
        }
    }

    public static HLSDownloadableItem restore(Context context, String str, JSONObject jSONObject) {
        HLSDownloadableItem hLSDownloadableItem;
        try {
            String string = jSONObject.getString("url");
            String string2 = jSONObject.getString("urle");
            String optString = jSONObject.optString("fold", null);
            String string3 = jSONObject.getString("file");
            int i = jSONObject.getInt("st");
            int i2 = jSONObject.getInt("prgr");
            int i3 = jSONObject.getInt("tsdl");
            hLSDownloadableItem = new HLSDownloadableItem(context, string, string2, optString, string3, str);
            try {
                hLSDownloadableItem.restoreMoreDetail(i, i2, i3);
            } catch (Exception e) {
                e = e;
                Log.e("HLSVideoDownloader", "" + Log.getStackTraceString(e));
                return hLSDownloadableItem;
            }
        } catch (Exception e2) {
            e = e2;
            hLSDownloadableItem = null;
        }
        return hLSDownloadableItem;
    }

    protected void restoreMoreDetail(int i, int i2, int i3) {
        this.progress = i2;
        this.downloadedSize = i3;
        DownloadableVideoItem.DownloadStatus downloadStatus = DownloadableVideoItem.DownloadStatus.values()[i];
        if (downloadStatus == DownloadableVideoItem.DownloadStatus.DOWNLOADING || downloadStatus == DownloadableVideoItem.DownloadStatus.PENDING) {
            downloadStatus = DownloadableVideoItem.DownloadStatus.PAUSED;
        }
        this.downloadStatus = downloadStatus;
    }

    public boolean removeSelf() {
        try {
            setState(DownloadableVideoItem.DownloadStatus.DELETED);
            if (this.threadRunner != null && !this.threadRunner.isTerminated() && !this.threadRunner.isShutdown()) {
                this.threadRunner.shutdownNow();
            }
            SharedPreferences.Editor edit = this.context.getSharedPreferences(this.spName, 0).edit();
            edit.remove(this.urlMd5);
            if (Build.VERSION.SDK_INT >= 9) {
                edit.apply();
            } else {
                edit.commit();
            }
            if (this.folderPath != null) {
                File file = new File(this.folderPath);
                if (file.exists() && file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    for (int i = 0; i < listFiles.length; i++) {
                        if (listFiles[i].isFile()) {
                            listFiles[i].delete();
                        }
                    }
                }
            }
            this.folderPath = "";
            this.fileName = "";
            this.progress = 0;
            this.errorCode = 0;
            this.failReason = "delete manually";
            return true;
        } catch (Exception e) {
            Log.e("HLSVideoDownloader", "" + e.getMessage());
            return true;
        }
    }
}
