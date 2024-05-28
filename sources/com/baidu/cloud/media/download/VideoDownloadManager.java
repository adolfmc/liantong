package com.baidu.cloud.media.download;

import android.content.Context;
import android.util.Log;
import com.baidu.cloud.media.download.DownloadableVideoItem;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VideoDownloadManager {
    private static final String DEFAULT_USER_NAME = "_unk@nown_##$ default $##_unk@nown_";
    private static final String DEFAULT_USER_NAME_MD5 = "4f0fecb0c26217433bafbf2a0d595c4e";
    protected static final String DOWNLOAD_FOLDER = "cyberplayer_download_videos";
    protected static final String SP_NAME_PREFIX = "__cyberplayer_dl_";
    private static final String TAG = "VideoDownloadManager";
    private static volatile VideoDownloadManager downloaderManager = null;
    private static volatile String globalUserName = null;
    private static int maxDownloadingSum = 5;
    private Context appContext;
    private String customizedPlayerId;
    private String md5User;
    private String userName;
    private volatile int nowDownloadingSum = 0;
    private Queue<String> queueList = new LinkedList();
    private ConcurrentHashMap<String, HLSDownloadableItem> downloadMap = new ConcurrentHashMap<>();

    private VideoDownloadManager(Context context, String str) {
        this.userName = "_unk@nown_##$ default $##_unk@nown_";
        this.md5User = "4f0fecb0c26217433bafbf2a0d595c4e";
        this.appContext = context.getApplicationContext();
        this.userName = str;
        this.md5User = DownloadUtils.getMD5(str);
        restoreFromSharedPref();
    }

    public static synchronized VideoDownloadManager getInstance(Context context, String str) {
        synchronized (VideoDownloadManager.class) {
            if (str != null) {
                if (!str.equals("")) {
                    if (!str.equals(globalUserName)) {
                        globalUserName = str;
                        if (downloaderManager != null) {
                            downloaderManager.stopAll();
                            downloaderManager = null;
                        }
                    }
                    if (downloaderManager == null) {
                        downloaderManager = new VideoDownloadManager(context, str);
                    }
                    return downloaderManager;
                }
            }
            Log.e("VideoDownloadManager", "getInstance failed, userName is null or empty.");
            return null;
        }
    }

    public void setCustomizedPlayerId(String str) {
        this.customizedPlayerId = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getCustomizedPlayerId() {
        return this.customizedPlayerId;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static VideoDownloadManager getInstanceForInner() {
        return downloaderManager;
    }

    public void stopAll() {
        try {
            for (Map.Entry<String, HLSDownloadableItem> entry : this.downloadMap.entrySet()) {
                entry.getValue().pause();
            }
        } catch (Exception e) {
            Log.d("VideoDownloadManager", "" + e.getMessage());
        }
    }

    private void restoreFromSharedPref() {
        this.downloadMap.clear();
        try {
            for (Map.Entry entry : ((HashMap) this.appContext.getSharedPreferences(getSharedPrefFileName(), 0).getAll()).entrySet()) {
                try {
                    JSONObject jSONObject = new JSONObject((String) entry.getValue());
                    jSONObject.put("urle", (String) entry.getKey());
                    this.downloadMap.put(jSONObject.getString("url"), HLSDownloadableItem.restore(this.appContext, getSharedPrefFileName(), jSONObject));
                } catch (Exception e) {
                    Log.d("VideoDownloadManager", "" + e.getMessage());
                }
            }
        } catch (Exception e2) {
            Log.d("VideoDownloadManager", "" + e2.getMessage());
        }
    }

    public DownloadableVideoItem getDownloadableVideoItemByUrl(String str) {
        return this.downloadMap.get(str);
    }

    public HashMap<String, DownloadableVideoItem> getAllDownloadableVideoItems() {
        HashMap<String, DownloadableVideoItem> hashMap = new HashMap<>();
        hashMap.putAll(this.downloadMap);
        return hashMap;
    }

    public void startOrResumeDownloaderWithToken(String str, String str2, DownloadObserver downloadObserver) {
        String str3;
        if (str == null) {
            Log.e("VideoDownloadManager", "url is null");
            return;
        }
        HLSDownloadableItem hLSDownloadableItem = this.downloadMap.get(str);
        if (hLSDownloadableItem == null) {
            String md5 = DownloadUtils.getMD5(str);
            String downloadRootForCurrentUser = getDownloadRootForCurrentUser();
            if (downloadRootForCurrentUser != null) {
                str3 = downloadRootForCurrentUser + md5 + "/";
            } else {
                str3 = null;
            }
            hLSDownloadableItem = new HLSDownloadableItem(this.appContext, str, md5, str3, md5 + ".m3u8", getSharedPrefFileName());
            this.downloadMap.put(str, hLSDownloadableItem);
        }
        if (downloadObserver != null) {
            hLSDownloadableItem.addObserver(downloadObserver);
        }
        if (str2 != null && !str2.equals("")) {
            hLSDownloadableItem.setDrmToken(str2);
        }
        if (tryStartOrPutInQueue(str)) {
            hLSDownloadableItem.start();
            return;
        }
        hLSDownloadableItem.setState(DownloadableVideoItem.DownloadStatus.PENDING);
        Log.w("VideoDownloadManager", "startOrResumeDownloader: too many tasks are downloading , this task is suspending now(will download automatically after other task down)");
    }

    public void startOrResumeDownloader(String str, DownloadObserver downloadObserver) {
        startOrResumeDownloaderWithToken(str, null, downloadObserver);
    }

    public void pauseDownloader(String str) {
        HLSDownloadableItem hLSDownloadableItem = this.downloadMap.get(str);
        if (hLSDownloadableItem == null) {
            Log.e("VideoDownloadManager", "pauseDownloader but there is no downloader for url=" + str);
            return;
        }
        if (hLSDownloadableItem.getStatus() == DownloadableVideoItem.DownloadStatus.PENDING) {
            removeFromQueue(str);
        }
        hLSDownloadableItem.pause();
    }

    protected boolean tryStartOrPutInQueue(String str) {
        boolean z;
        synchronized (this.queueList) {
            z = false;
            if (!this.queueList.contains(str)) {
                if (this.nowDownloadingSum >= maxDownloadingSum) {
                    this.queueList.offer(str);
                } else {
                    this.nowDownloadingSum++;
                    z = true;
                }
            }
        }
        return z;
    }

    protected void removeFromQueue(String str) {
        synchronized (this.queueList) {
            this.queueList.remove(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void tryConsumeSuspendingTask() {
        synchronized (this.queueList) {
            if (this.queueList.isEmpty()) {
                if (this.nowDownloadingSum > 0) {
                    this.nowDownloadingSum--;
                }
            } else {
                HLSDownloadableItem hLSDownloadableItem = this.downloadMap.get(this.queueList.poll());
                if (hLSDownloadableItem.getStatus() == DownloadableVideoItem.DownloadStatus.PENDING) {
                    hLSDownloadableItem.start();
                } else if (this.nowDownloadingSum > 0) {
                    this.nowDownloadingSum--;
                }
            }
        }
    }

    public void deleteDownloader(String str) {
        HLSDownloadableItem hLSDownloadableItem = this.downloadMap.get(str);
        if (hLSDownloadableItem == null) {
            Log.e("VideoDownloadManager", "deleteDownloader but there is no downloader for url=" + str);
            return;
        }
        if (hLSDownloadableItem.getStatus() == DownloadableVideoItem.DownloadStatus.PENDING) {
            removeFromQueue(str);
        }
        hLSDownloadableItem.removeSelf();
        this.downloadMap.remove(str);
    }

    public String getUserName() {
        return this.userName;
    }

    protected String getUserMd5Name() {
        return this.md5User;
    }

    protected String getSharedPrefFileName() {
        return "__cyberplayer_dl_" + getUserMd5Name();
    }

    public void changeMaxDownloadingItems(int i) {
        if (i > 0 && i <= 10) {
            maxDownloadingSum = i;
        } else {
            Log.e("VideoDownloadManager", "changeMaxDownloadingItems, maxItems should be 0<x<100");
        }
    }

    public String getDownloadRootForCurrentUser() {
        File externalFilesDir = this.appContext.getExternalFilesDir(null);
        if (externalFilesDir != null) {
            return externalFilesDir.getAbsolutePath() + "/cyberplayer_download_videos/" + getUserMd5Name() + "/";
        }
        return null;
    }
}
