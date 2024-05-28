package com.baidu.cloud.media.download;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.cloud.media.player.apm.AndroidIDCache;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class M3U8Parser {
    private static final String SP_FILE_FOR_KEY = "__cyberplayer_dl_sec";
    private static final String TAG = "M3U8Parser";
    private Context applicationContext;
    private String filePath;
    private String mBaseUrl;
    private M3U8ParserListener mResultListener;
    private List<String> mSegmentUrlList;
    private String encryptSecKey = null;
    private String drmToken = null;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static abstract class M3U8ParserListener {
        public abstract void onParseComplete(List<String> list);

        public abstract void onParseFailed(int i);
    }

    public M3U8Parser(Context context, String str) {
        this.mBaseUrl = "";
        this.mSegmentUrlList = null;
        this.mResultListener = null;
        this.applicationContext = context.getApplicationContext();
        this.filePath = str;
        this.mBaseUrl = "";
        this.mSegmentUrlList = new ArrayList();
        this.mResultListener = null;
    }

    private void getSegmentListFromM3U8(String str, List<String> list) {
        if (str == null || TextUtils.isEmpty(str)) {
            this.mResultListener.onParseFailed(1);
            return;
        }
        Scanner scanner = new Scanner(str);
        StringBuilder sb = new StringBuilder();
        String str2 = null;
        long j = 0;
        String str3 = null;
        boolean z = false;
        boolean z2 = false;
        while (scanner.hasNextLine()) {
            try {
                String nextLine = scanner.nextLine();
                if (!z2 && !nextLine.startsWith("#EXTM3U")) {
                    scanner.close();
                    this.mResultListener.onParseFailed(4);
                    return;
                }
                if (!TextUtils.isEmpty(str3) && str3.startsWith("#EXT-X-STREAM-INF:")) {
                    long bandWidthFromStreamInfo = getBandWidthFromStreamInfo(str3);
                    if (bandWidthFromStreamInfo > j) {
                        str2 = nextLine;
                    } else {
                        bandWidthFromStreamInfo = j;
                    }
                    j = bandWidthFromStreamInfo;
                    str3 = nextLine;
                    z = true;
                } else if (!TextUtils.isEmpty(str3) && str3.startsWith("#EXTINF:")) {
                    String str4 = nextLine.contains("://") ? "" : this.mBaseUrl;
                    if (nextLine.startsWith("/")) {
                        str4 = str4.substring(0, str4.indexOf("/", str4.indexOf("://") + 3));
                    } else if (nextLine.startsWith("../")) {
                        if (str4.endsWith("/")) {
                            str4 = str4.substring(0, str4.length() - 1);
                        }
                        str4 = str4.substring(0, str4.lastIndexOf("/") + 1);
                        nextLine = nextLine.substring(3);
                    }
                    list.add(str4 + nextLine);
                    sb.append(String.valueOf(list.size()) + ".ts\n");
                    str3 = nextLine;
                } else {
                    if (!TextUtils.isEmpty(nextLine) && nextLine.startsWith("#EXT-X-KEY:")) {
                        String newLineForExtKey = getNewLineForExtKey(nextLine);
                        if (newLineForExtKey == null) {
                            scanner.close();
                            this.mResultListener.onParseFailed(6);
                            return;
                        }
                        sb.append(newLineForExtKey + "\n");
                    } else {
                        sb.append(nextLine + "\n");
                    }
                    str3 = nextLine;
                }
                z2 = true;
            } catch (Exception e) {
                Log.d("M3U8Parser", Log.getStackTraceString(e));
                this.mResultListener.onParseFailed(2);
                return;
            }
        }
        scanner.close();
        if (z) {
            this.mSegmentUrlList.clear();
            getSegmentListFromM3U8(DownloadUtils.httpGet((str2.startsWith("http://") ? "" : this.mBaseUrl) + str2), this.mSegmentUrlList);
            return;
        }
        if (this.encryptSecKey != null) {
            saveEncryptKey();
        }
        if (!DownloadUtils.saveDataToFile(sb.toString().getBytes(), this.filePath)) {
            this.mResultListener.onParseFailed(5);
        } else {
            this.mResultListener.onParseComplete(this.mSegmentUrlList);
        }
    }

    private void saveEncryptKey() {
        SharedPreferences.Editor edit = this.applicationContext.getSharedPreferences("__cyberplayer_dl_sec", 0).edit();
        edit.putString(this.filePath, this.encryptSecKey);
        if (Build.VERSION.SDK_INT >= 9) {
            edit.apply();
        } else {
            edit.commit();
        }
    }

    private long getBandWidthFromStreamInfo(String str) {
        try {
            String[] split = str.split(":|=|,");
            for (int i = 0; i < split.length; i++) {
                if (split[i].trim().equals("BANDWIDTH")) {
                    return Long.parseLong(split[i + 1].trim());
                }
            }
            return 0L;
        } catch (Exception e) {
            Log.d("M3U8Parser", "" + e.getMessage());
            return 0L;
        }
    }

    private String getNewLineForExtKey(String str) {
        String str2 = null;
        try {
            boolean contains = str.contains("=media-drm-player-binding");
            boolean contains2 = str.contains("=media-drm-safe-code");
            boolean contains3 = str.contains("=media-drm-token");
            if (contains || contains2 || contains3) {
                if (this.encryptSecKey == null) {
                    String substring = str.substring(str.indexOf("URI=\"") + 5);
                    String substring2 = substring.substring(0, substring.indexOf("\""));
                    String str3 = "pid-android-1";
                    try {
                        String customizedPlayerId = VideoDownloadManager.getInstanceForInner().getCustomizedPlayerId();
                        if (!TextUtils.isEmpty(customizedPlayerId)) {
                            str3 = customizedPlayerId;
                        }
                    } catch (Exception unused) {
                    }
                    String str4 = substring2 + "&playerId=" + str3;
                    if (contains3) {
                        str4 = str4 + "&token=" + this.drmToken;
                    }
                    this.encryptSecKey = LocalHlsSec.bytes2HexStr(new LocalHlsSec().crypt(this.applicationContext, new AndroidIDCache(this.applicationContext).getAndroidID(), LocalHlsSec.hexStr2Bytes(new JSONObject(DownloadUtils.httpGet(str4)).getString("encryptedVideoKey")), 0));
                }
                try {
                    if (contains) {
                        str = str.replace("media-drm-player-binding", "media-drm-local-key");
                    } else if (contains2) {
                        str = str.replace("media-drm-safe-code", "media-drm-local-key");
                    } else if (!contains3) {
                        return str;
                    } else {
                        str = str.replace("media-drm-token", "media-drm-local-key");
                    }
                    return str;
                } catch (Exception e) {
                    str2 = str;
                    e = e;
                    Log.d("M3U8Parser", "", e);
                    return str2;
                }
            }
            return str;
        } catch (Exception e2) {
            e = e2;
        }
    }

    public int parseM3U8Stream(String str, String str2, M3U8ParserListener m3U8ParserListener) {
        if (TextUtils.isEmpty(str) || m3U8ParserListener == null) {
            return 1;
        }
        this.drmToken = str2;
        this.mResultListener = m3U8ParserListener;
        this.mSegmentUrlList.clear();
        if (TextUtils.isEmpty(this.mBaseUrl)) {
            this.mBaseUrl = str.substring(0, str.lastIndexOf(47) + 1);
        }
        getSegmentListFromM3U8(DownloadUtils.httpGet(str), this.mSegmentUrlList);
        return 0;
    }
}
