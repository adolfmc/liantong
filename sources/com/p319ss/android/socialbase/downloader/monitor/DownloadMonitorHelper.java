package com.p319ss.android.socialbase.downloader.monitor;

import android.net.Uri;
import android.text.TextUtils;
import com.p319ss.android.socialbase.downloader.constants.DownloadStatus;
import com.p319ss.android.socialbase.downloader.depend.AbsDownloadMonitorDepend;
import com.p319ss.android.socialbase.downloader.depend.IDownloadDepend;
import com.p319ss.android.socialbase.downloader.depend.IDownloadMonitorDepend;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.downloader.IMonitorConfig;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.exception.DownloadTTNetException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.model.DownloadTask;
import com.p319ss.android.socialbase.downloader.network.AbsDownloadHttpConnection;
import com.p319ss.android.socialbase.downloader.network.IDefaultDownloadHttpConnection;
import com.p319ss.android.socialbase.downloader.network.IDownloadHttpConnection;
import com.p319ss.android.socialbase.downloader.segment.Segment;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.monitor.DownloadMonitorHelper */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class DownloadMonitorHelper {
    private static final String DEFAULT_MONITOR_SCENE = "default";

    public static void monitorSend(DownloadTask downloadTask, BaseException baseException, int i) {
        if (downloadTask == null) {
            return;
        }
        try {
            DownloadInfo downloadInfo = downloadTask.getDownloadInfo();
            if (downloadInfo == null) {
                return;
            }
            IDownloadMonitorDepend monitorDepend = downloadTask.getMonitorDepend();
            boolean isMonitorStatus = DownloadStatus.isMonitorStatus(i);
            if (!isMonitorStatus && !(isMonitorStatus = isMonitorStatus(downloadInfo.getExtraMonitorStatus(), i)) && monitorDepend != null && (monitorDepend instanceof AbsDownloadMonitorDepend)) {
                isMonitorStatus = isMonitorStatus(((AbsDownloadMonitorDepend) monitorDepend).getAdditionalMonitorStatus(), i);
            }
            if (isMonitorStatus) {
                IDownloadDepend depend = downloadTask.getDepend();
                if (depend != null) {
                    depend.monitorLogSend(downloadInfo, baseException, i);
                }
                monitorSendWithTaskMonitor(monitorDepend, downloadInfo, baseException, i);
                monitorSendWithGlobalSdkMonitor(DownloadComponentManager.getDownloadMonitorListener(), downloadInfo, baseException, i);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static boolean isMonitorStatus(int[] iArr, int i) {
        if (iArr != null && iArr.length > 0) {
            for (int i2 : iArr) {
                if (i == i2) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void monitorSendWithTaskMonitor(IDownloadMonitorDepend iDownloadMonitorDepend, DownloadInfo downloadInfo, BaseException baseException, int i) {
        if (iDownloadMonitorDepend == null) {
            return;
        }
        try {
            String eventPage = iDownloadMonitorDepend.getEventPage();
            if (TextUtils.isEmpty(eventPage)) {
                eventPage = "default";
            }
            JSONObject monitorJson = getMonitorJson(eventPage, downloadInfo, baseException, i);
            if (monitorJson == null) {
                monitorJson = new JSONObject();
            }
            iDownloadMonitorDepend.monitorLogSend(monitorJson);
        } catch (Throwable unused) {
        }
    }

    public static void monitorSendWithGlobalSdkMonitor(IDownloadMonitorListener iDownloadMonitorListener, DownloadInfo downloadInfo, BaseException baseException, int i) {
        if (iDownloadMonitorListener == null || !downloadInfo.isNeedSDKMonitor() || TextUtils.isEmpty(downloadInfo.getMonitorScene())) {
            return;
        }
        try {
            JSONObject monitorJson = getMonitorJson(downloadInfo.getMonitorScene(), downloadInfo, baseException, i);
            if (monitorJson == null) {
                monitorJson = new JSONObject();
            }
            if (i == -1) {
                monitorJson.put("status", baseException.getErrorCode());
                iDownloadMonitorListener.monitorEvent("download_failed", monitorJson, null, null);
                return;
            }
            putMonitorJsonStatus(i, monitorJson, downloadInfo);
            iDownloadMonitorListener.monitorEvent("download_common", monitorJson, null, null);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void putMonitorJsonStatus(int i, JSONObject jSONObject, DownloadInfo downloadInfo) throws JSONException {
        String str;
        if (i == 0) {
            str = "download_create";
        } else if (i == 2) {
            str = "download_start";
        } else if (i != 6) {
            switch (i) {
                case -5:
                    str = "download_uncomplete";
                    break;
                case -4:
                    str = "download_cancel";
                    break;
                case -3:
                    str = "download_success";
                    double downloadSpeed = downloadInfo.getDownloadSpeed();
                    if (downloadSpeed >= 0.0d) {
                        jSONObject.put("download_speed", downloadSpeed);
                        break;
                    }
                    break;
                case -2:
                    str = "download_pause";
                    break;
                default:
                    str = "";
                    break;
            }
        } else {
            str = "download_first_start";
        }
        jSONObject.put("status", str);
    }

    public static String parseDevicePostfix(String str) {
        try {
            return TextUtils.isDigitsOnly(str) ? String.valueOf(Long.valueOf(str).longValue() % 100) : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static JSONObject getMonitorJson(String str, DownloadInfo downloadInfo, BaseException baseException, int i) {
        JSONObject jSONObject;
        int i2;
        String str2;
        try {
            jSONObject = new JSONObject();
        } catch (JSONException e) {
            e = e;
            jSONObject = null;
        }
        try {
            IMonitorConfig monitorConfig = DownloadComponentManager.getMonitorConfig();
            String str3 = "";
            String str4 = "";
            String str5 = "";
            String str6 = "";
            if (monitorConfig != null) {
                str3 = monitorConfig.getDeviceId();
                str4 = parseDevicePostfix(str3);
                str5 = monitorConfig.getAppId();
                i2 = monitorConfig.getUpdateVersion();
            } else {
                i2 = 0;
            }
            if (baseException != null && (baseException instanceof DownloadTTNetException)) {
                str6 = ((DownloadTTNetException) baseException).getRequestLog();
            }
            jSONObject.put("event_page", str);
            jSONObject.put("app_id", str5);
            jSONObject.put("device_id", str3);
            jSONObject.put("device_id_postfix", str4);
            jSONObject.put("update_version", i2);
            jSONObject.put("download_status", i);
            if (downloadInfo != null) {
                jSONObject.put("setting_tag", DownloadSetting.obtain(downloadInfo.getId()).optString("setting_tag"));
                jSONObject.put("download_id", downloadInfo.getId());
                jSONObject.put("name", downloadInfo.getName());
                jSONObject.put("url", downloadInfo.getUrl());
                jSONObject.put("save_path", downloadInfo.getSavePath());
                jSONObject.put("download_time", downloadInfo.getDownloadTime());
                jSONObject.put("cur_bytes", downloadInfo.getCurBytes());
                jSONObject.put("total_bytes", downloadInfo.getTotalBytes());
                jSONObject.put("network_quality", downloadInfo.getNetworkQuality());
                int i3 = 1;
                jSONObject.put("only_wifi", downloadInfo.isOnlyWifi() ? 1 : 0);
                jSONObject.put("need_https_degrade", downloadInfo.isNeedHttpsToHttpRetry() ? 1 : 0);
                jSONObject.put("https_degrade_retry_used", downloadInfo.isHttpsToHttpRetryUsed() ? 1 : 0);
                jSONObject.put("md5", downloadInfo.getMd5() == null ? "" : downloadInfo.getMd5());
                jSONObject.put("chunk_count", downloadInfo.getChunkCount());
                jSONObject.put("is_force", downloadInfo.isForce() ? 1 : 0);
                jSONObject.put("retry_count", downloadInfo.getRetryCount());
                jSONObject.put("cur_retry_time", downloadInfo.getCurRetryTime());
                jSONObject.put("need_retry_delay", downloadInfo.isNeedRetryDelay() ? 1 : 0);
                jSONObject.put("need_reuse_first_connection", downloadInfo.isNeedReuseFirstConnection() ? 1 : 0);
                jSONObject.put("default_http_service_backup", downloadInfo.isNeedDefaultHttpServiceBackUp() ? 1 : 0);
                jSONObject.put("retry_delay_status", downloadInfo.getRetryDelayStatus().ordinal());
                jSONObject.put("backup_url_used", downloadInfo.isBackUpUrlUsed() ? 1 : 0);
                jSONObject.put("download_byte_error_retry_status", downloadInfo.getByteInvalidRetryStatus().ordinal());
                jSONObject.put("forbidden_handler_status", downloadInfo.getAsyncHandleStatus().ordinal());
                jSONObject.put("need_independent_process", downloadInfo.isNeedIndependentProcess() ? 1 : 0);
                jSONObject.put("head_connection_error_msg", downloadInfo.getHeadConnectionException() != null ? downloadInfo.getHeadConnectionException() : "");
                jSONObject.put("extra", downloadInfo.getExtra() != null ? downloadInfo.getExtra() : "");
                if (!downloadInfo.isAddListenerToSameTask()) {
                    i3 = 0;
                }
                jSONObject.put("add_listener_to_same_task", i3);
                jSONObject.put("backup_url_count", downloadInfo.getBackUpUrls() != null ? downloadInfo.getBackUpUrls().size() : 0);
                jSONObject.put("cur_backup_url_index", downloadInfo.getBackUpUrls() != null ? downloadInfo.getCurBackUpUrlIndex() : -1);
                jSONObject.put("forbidden_urls", downloadInfo.getForbiddenBackupUrls() != null ? downloadInfo.getForbiddenBackupUrls().toString() : "");
                jSONObject.put("task_id", TextUtils.isEmpty(downloadInfo.getTaskId()) ? "" : downloadInfo.getTaskId());
                String str7 = "";
                String str8 = "";
                String url = downloadInfo.getUrl();
                if (TextUtils.isEmpty(url)) {
                    str2 = "";
                } else {
                    Uri parse = Uri.parse(url);
                    str2 = parse.getHost();
                    str7 = parse.getPath();
                    str8 = parse.getLastPathSegment();
                    if (!TextUtils.isEmpty(str7) && !TextUtils.isEmpty(str8)) {
                        str7 = str7.substring(0, str7.length() - str8.length());
                    }
                }
                jSONObject.put("url_host", str2);
                jSONObject.put("url_path", str7);
                jSONObject.put("url_last_path_segment", str8);
            }
            jSONObject.put("error_code", baseException != null ? baseException.getErrorCode() : 0);
            jSONObject.put("error_msg", baseException != null ? baseException.getErrorMessage() : "");
            jSONObject.put("request_log", str6);
        } catch (JSONException e2) {
            e = e2;
            e.printStackTrace();
            return jSONObject;
        }
        return jSONObject;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(22:11|12|13|(4:71|72|73|(1:75)(1:76))(1:15)|16|(2:53|(1:(13:61|(1:63)(2:64|65)|20|(2:24|25)|28|29|30|(1:32)|33|34|(1:46)|37|(2:41|43)(1:40)))(1:58))|19|20|(3:22|24|25)|28|29|30|(0)|33|34|(0)|44|46|37|(0)|41|43) */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x011d, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x011e, code lost:
        r0.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00f2 A[Catch: JSONException -> 0x011d, Throwable -> 0x0145, TryCatch #4 {JSONException -> 0x011d, blocks: (B:51:0x00af, B:53:0x00f2, B:54:0x0103), top: B:79:0x00af, outer: #3 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void monitorDownloadConnect(@android.support.annotation.Nullable com.p319ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection r13, java.lang.String r14, java.lang.String r15, long r16, java.lang.String r18, int r19, java.io.IOException r20, com.p319ss.android.socialbase.downloader.model.DownloadInfo r21) {
        /*
            Method dump skipped, instructions count: 330
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.downloader.monitor.DownloadMonitorHelper.monitorDownloadConnect(com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection, java.lang.String, java.lang.String, long, java.lang.String, int, java.io.IOException, com.ss.android.socialbase.downloader.model.DownloadInfo):void");
    }

    public static void monitorDownloadIO(DownloadSetting downloadSetting, DownloadInfo downloadInfo, String str, IDownloadHttpConnection iDownloadHttpConnection, boolean z, boolean z2, BaseException baseException, long j, long j2, boolean z3, long j3, long j4, long j5, JSONObject jSONObject) {
        monitorIO("download_io", downloadSetting.optInt("monitor_download_io"), downloadSetting, downloadInfo, str, null, null, iDownloadHttpConnection, z, z2, baseException, j, j2, z3, j3, j4, j5, null);
    }

    public static void monitorSegmentIO(DownloadSetting downloadSetting, DownloadInfo downloadInfo, String str, String str2, String str3, boolean z, IDownloadHttpConnection iDownloadHttpConnection, BaseException baseException, long j, long j2) {
        monitorIO("segment_io", downloadSetting.optInt("monitor_segment_io"), downloadSetting, downloadInfo, str, str2, str3, iDownloadHttpConnection, z, false, baseException, j, j2, false, -1L, -1L, -1L, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r17v4 */
    private static void monitorIO(String str, int i, DownloadSetting downloadSetting, DownloadInfo downloadInfo, String str2, String str3, String str4, IDownloadHttpConnection iDownloadHttpConnection, boolean z, boolean z2, BaseException baseException, long j, long j2, boolean z3, long j3, long j4, long j5, JSONObject jSONObject) {
        int i2;
        String str5;
        long j6;
        String str6;
        int i3;
        int i4;
        String str7;
        int i5;
        if (i <= 0 || j2 <= 0) {
            return;
        }
        try {
            Uri parse = Uri.parse(str2);
            String host = parse.getHost();
            String path = parse.getPath();
            String lastPathSegment = parse.getLastPathSegment();
            if (!TextUtils.isEmpty(path) && !TextUtils.isEmpty(lastPathSegment)) {
                try {
                    path = path.substring(0, path.length() - lastPathSegment.length());
                } catch (Throwable unused) {
                }
            }
            if (z) {
                i2 = 1;
                str5 = null;
            } else if (z2) {
                i2 = 2;
                str5 = null;
            } else if (baseException != null) {
                i2 = !DownloadUtils.isNetworkConnected(DownloadComponentManager.getAppContext()) ? 1049 : baseException.getErrorCode();
                str5 = baseException.getErrorMessage();
            } else {
                i2 = 0;
                str5 = null;
            }
            JSONObject jSONObject2 = new JSONObject();
            if (iDownloadHttpConnection != null) {
                int i6 = iDownloadHttpConnection instanceof IDefaultDownloadHttpConnection ? 0 : 1;
                String responseHeaderField = iDownloadHttpConnection.getResponseHeaderField("X-Cache");
                int contains = TextUtils.isEmpty(responseHeaderField) ? -1 : responseHeaderField.toLowerCase().contains("hit");
                if (downloadSetting.optInt("monitor_sla", 1) == 1 && !z && !z2 && (iDownloadHttpConnection instanceof AbsDownloadHttpConnection)) {
                    ((AbsDownloadHttpConnection) iDownloadHttpConnection).monitorNetworkInfo(jSONObject2, true);
                }
                if (iDownloadHttpConnection instanceof AbsDownloadHttpConnection) {
                    i3 = i6;
                    str6 = ((AbsDownloadHttpConnection) iDownloadHttpConnection).getRequestLog();
                    i4 = contains;
                    j6 = j;
                } else {
                    i3 = i6;
                    str6 = "";
                    i4 = contains;
                    j6 = j;
                }
            } else {
                j6 = j;
                str6 = "";
                i3 = -1;
                i4 = -1;
            }
            double d = j6 / 1048576.0d;
            double d2 = j2;
            double nanos = d2 / TimeUnit.SECONDS.toNanos(1L);
            jSONObject2.put("setting_tag", downloadSetting.optString("setting_tag"));
            jSONObject2.put("url_host", host);
            jSONObject2.putOpt("host_ip", str3);
            jSONObject2.putOpt("host_real_ip", str4);
            jSONObject2.put("url_path", path);
            jSONObject2.put("url_last_path_segment", lastPathSegment);
            jSONObject2.put("net_lib", i3);
            jSONObject2.put("hit_cdn_cache", i4);
            jSONObject2.put("status_code", i2);
            jSONObject2.put("request_log", str6);
            if (str5 != null) {
                jSONObject2.put("error_msg", DownloadUtils.getFixLengthString(str5, downloadSetting.optInt("exception_msg_length", 500)));
            }
            jSONObject2.put("download_sec", nanos);
            jSONObject2.put("download_mb", d);
            if (nanos > 0.0d) {
                jSONObject2.put("download_speed", d / nanos);
            }
            if (z3) {
                jSONObject2.put("rw_read_time", j3 / d2);
                jSONObject2.put("rw_write_time", j4 / d2);
                jSONObject2.put("rw_sync_time", j5 / d2);
            }
            jSONObject2.put("pkg_name", downloadInfo.getPackageName());
            jSONObject2.put("name", downloadInfo.getTitle());
            if (i != 1 && i != 3) {
                i5 = 2;
                str7 = str;
                if (i != i5 || i == 3) {
                    DownloadComponentManager.getEventListener().onEvent(downloadInfo.getId(), str7, jSONObject2);
                }
                return;
            }
            IDownloadMonitorListener downloadMonitorListener = DownloadComponentManager.getDownloadMonitorListener();
            if (downloadMonitorListener != null) {
                str7 = str;
                downloadMonitorListener.monitorEvent(str7, jSONObject2, null, null);
                i5 = 2;
            } else {
                str7 = str;
                i5 = 2;
            }
            if (i != i5) {
            }
            DownloadComponentManager.getEventListener().onEvent(downloadInfo.getId(), str7, jSONObject2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void monitorSegmentsError(DownloadInfo downloadInfo, List<Segment> list) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("segments", Segment.toString(list));
            jSONObject.put("cur_bytes", downloadInfo.getCurBytes());
            jSONObject.put("total_bytes", downloadInfo.getTotalBytes());
            InnerEventListener eventListener = DownloadComponentManager.getEventListener();
            if (eventListener != null) {
                eventListener.onEvent(downloadInfo.getId(), "segments_error", jSONObject);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
