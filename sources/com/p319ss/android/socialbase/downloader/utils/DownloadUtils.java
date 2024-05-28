package com.p319ss.android.socialbase.downloader.utils;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.p319ss.android.socialbase.downloader.constants.ListenerType;
import com.p319ss.android.socialbase.downloader.depend.IDownloadMonitorDepend;
import com.p319ss.android.socialbase.downloader.depend.ITempFileSaveCompleteCallback;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.exception.DownloadHttpException;
import com.p319ss.android.socialbase.downloader.exception.DownloadTTNetException;
import com.p319ss.android.socialbase.downloader.impls.DownloadProxy;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.model.DownloadChunk;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.model.HttpHeader;
import com.p319ss.android.socialbase.downloader.model.RandomAccessOutputStream;
import com.p319ss.android.socialbase.downloader.monitor.DownloadMonitorHelper;
import com.p319ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.ttmd5.TTMd5;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.ConnectException;
import java.net.HttpRetryException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import okhttp3.internal.http2.StreamResetException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.utils.DownloadUtils */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class DownloadUtils {
    public static final String ACCEPT_RANGES = "Accept-Ranges";
    public static final String CACHE_CONTROL = "Cache-Control";
    public static final int CHUNKED_CONTENT_LENGTH = -1;
    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_RANGE = "Content-Range";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final int DEFAULT_MIN_TIME_INTERVAL = 1000;
    public static final String ETAG = "Etag";
    private static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final long FILE_COPY_BUFFER_SIZE = 31457280;
    public static final String HEADER_TAG_DOWNLOAD_CACHE = "download-tc21-1-15";
    public static final String IF_MODIFIED_SINCE = "if-modified-since";
    public static final String LAST_MODIFIED = "last-modified";
    public static final String LAST_MODIFIED_CASE = "Last-Modified";
    private static final long ONE_KB = 1024;
    private static final long ONE_MB = 1048576;
    private static final String TAG = "DownloadUtils";
    public static final String TRANSFER_ENCODING = "Transfer-Encoding";
    public static final String VALUE_CHUNKED = "chunked";
    public static final String X_CACHE = "X-Cache";
    private static ConnectivityManager connectivityManager;
    private static Boolean sIsDownloaderProcess;
    private static Boolean sIsMainProcess;
    private static final Pattern CONTENT_RANGE_RIGHT_VALUE = Pattern.compile(".*\\d+ *- *(\\d+) */ *\\d+");
    private static String sCurProcessName = null;
    private static volatile SparseArray<Boolean> saveTempFileStatusMap = new SparseArray<>();
    private static volatile SparseArray<List<ITempFileSaveCompleteCallback>> saveTempFileListeners = new SparseArray<>();
    private static final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static Pattern CONTENT_DISPOSITION_QUOTED_PATTERN = null;
    private static Pattern CONTENT_DISPOSITION_NON_QUOTED_PATTERN = null;

    public static double byteToMb(long j) {
        return j / 1048576.0d;
    }

    public static long getMaxBytesOverMobile() {
        return 2147483648L;
    }

    public static boolean isChunkedTask(long j) {
        return j == -1;
    }

    public static boolean isMd5Valid(int i) {
        return i == 0 || i == 2;
    }

    public static boolean isResponseCodeValid(int i) {
        return i == 206 || i == 200;
    }

    public static boolean isResponseDataFromBegin(int i) {
        return i == 200 || i == 201 || i == 0;
    }

    public static String toHexString(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("bytes is null");
        }
        return toHexString(bArr, 0, bArr.length);
    }

    public static String toHexString(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new NullPointerException("bytes is null");
        }
        if (i < 0 || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        int i3 = i2 * 2;
        char[] cArr = new char[i3];
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = bArr[i5 + i] & 255;
            int i7 = i4 + 1;
            char[] cArr2 = HEX_CHARS;
            cArr[i4] = cArr2[i6 >> 4];
            i4 = i7 + 1;
            cArr[i7] = cArr2[i6 & 15];
        }
        return new String(cArr, 0, i3);
    }

    public static String hexToString(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        byte[] bArr = new byte[str.length() / 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            try {
                bArr[i] = (byte) (Integer.parseInt(str.substring(i2, i2 + 2), 16) & 255);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            return new String(bArr, "utf-8");
        } catch (Exception e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public static long getContentLength(IDownloadHeadHttpConnection iDownloadHeadHttpConnection) {
        if (iDownloadHeadHttpConnection == null) {
            return -1L;
        }
        String respHeadFieldIgnoreCase = getRespHeadFieldIgnoreCase(iDownloadHeadHttpConnection, "Content-Length");
        if (TextUtils.isEmpty(respHeadFieldIgnoreCase) && DownloadExpSwitchCode.isSwitchEnable(1)) {
            return parseContentLengthFromContentRange(iDownloadHeadHttpConnection);
        }
        try {
            return Long.parseLong(respHeadFieldIgnoreCase);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    public static long parseContentLengthFromContentRange(IDownloadHeadHttpConnection iDownloadHeadHttpConnection) {
        if (iDownloadHeadHttpConnection == null) {
            return -1L;
        }
        String respHeadFieldIgnoreCase = getRespHeadFieldIgnoreCase(iDownloadHeadHttpConnection, "Content-Range");
        if (TextUtils.isEmpty(respHeadFieldIgnoreCase)) {
            return -1L;
        }
        try {
            Matcher matcher = Pattern.compile("bytes (\\d+)-(\\d+)/\\d+").matcher(respHeadFieldIgnoreCase);
            if (matcher.find()) {
                return (Long.parseLong(matcher.group(2)) - Long.parseLong(matcher.group(1))) + 1;
            }
        } catch (Exception e) {
            String str = TAG;
            Logger.m6460w(str, "parse content-length from content-range failed " + e);
        }
        return -1L;
    }

    public static long parseContentRangeOfInstanceLength(String str) {
        if (str == null) {
            return -1L;
        }
        String[] split = str.split("/");
        if (split.length >= 2) {
            try {
                return Long.parseLong(split[1]);
            } catch (NumberFormatException unused) {
                String str2 = TAG;
                Logger.m6460w(str2, "parse instance length failed with " + str);
            }
        }
        return -1L;
    }

    private static String parseContentDisposition(String str) {
        Matcher matcher;
        if (str == null) {
            return null;
        }
        try {
            if (CONTENT_DISPOSITION_QUOTED_PATTERN == null) {
                CONTENT_DISPOSITION_QUOTED_PATTERN = Pattern.compile("attachment;\\s*filename\\s*=\\s*\"([^\"]*)\"");
            }
            matcher = CONTENT_DISPOSITION_QUOTED_PATTERN.matcher(str);
        } catch (Exception unused) {
        }
        if (matcher.find()) {
            return matcher.group(1);
        }
        if (CONTENT_DISPOSITION_NON_QUOTED_PATTERN == null) {
            CONTENT_DISPOSITION_NON_QUOTED_PATTERN = Pattern.compile("attachment;\\s*filename\\s*=\\s*(.*)");
        }
        Matcher matcher2 = CONTENT_DISPOSITION_NON_QUOTED_PATTERN.matcher(str);
        if (matcher2.find()) {
            return matcher2.group(1);
        }
        return null;
    }

    public static String getFileNameFromConnection(IDownloadHeadHttpConnection iDownloadHeadHttpConnection, String str) {
        String parseContentDisposition = parseContentDisposition(iDownloadHeadHttpConnection.getResponseHeaderField("Content-Disposition"));
        return TextUtils.isEmpty(parseContentDisposition) ? md5Hex(str) : parseContentDisposition;
    }

    public static RandomAccessOutputStream createOutputStream(DownloadInfo downloadInfo, String str, String str2, int i) throws BaseException {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new BaseException(1021, new IOException("path must be not empty"));
        }
        File file = new File(str, str2);
        boolean z = false;
        if (file.exists() && file.isDirectory()) {
            throw new BaseException(1035, new IOException(String.format("path is :%s, path is directory:%B:", str, Boolean.valueOf(file.isDirectory()))));
        }
        if (!file.exists()) {
            try {
                File file2 = new File(str);
                if (!file2.exists() || !file2.isDirectory()) {
                    if (!file2.exists()) {
                        if (!file2.mkdirs() && !file2.exists()) {
                            if (DownloadSetting.obtain(downloadInfo).optInt("opt_mkdir_failed", 0) != 1) {
                                throw new BaseException(1030, "download savePath directory can not created:" + str);
                            }
                            int i2 = 0;
                            while (!z) {
                                int i3 = i2 + 1;
                                if (i2 >= 3) {
                                    break;
                                }
                                try {
                                    Thread.sleep(10L);
                                    z = file2.mkdirs();
                                    i2 = i3;
                                } catch (InterruptedException unused) {
                                }
                            }
                            if (!z) {
                                if (getAvailableSpaceBytes(downloadInfo.getSavePath()) < 16384) {
                                    throw new BaseException(1006, "download savePath directory can not created:" + str);
                                }
                                throw new BaseException(1030, "download savePath directory can not created:" + str);
                            }
                        }
                    } else {
                        file2.delete();
                        if (!file2.mkdirs() && !file2.exists()) {
                            throw new BaseException(1031, "download savePath is not directory:path=" + str);
                        }
                        throw new BaseException(1031, "download savePath is not directory:" + str);
                    }
                }
                file.createNewFile();
            } catch (IOException e) {
                throw new BaseException(1036, e);
            }
        }
        return new RandomAccessOutputStream(file, i);
    }

    public static List<DownloadChunk> parseHostChunkList(List<DownloadChunk> list) {
        SparseArray sparseArray = new SparseArray();
        SparseArray sparseArray2 = new SparseArray();
        for (DownloadChunk downloadChunk : list) {
            if (downloadChunk != null) {
                if (downloadChunk.isHostChunk()) {
                    sparseArray.put(downloadChunk.getChunkIndex(), downloadChunk);
                    List<DownloadChunk> list2 = (List) sparseArray2.get(downloadChunk.getChunkIndex());
                    if (list2 != null) {
                        for (DownloadChunk downloadChunk2 : list2) {
                            downloadChunk2.setHostChunk(downloadChunk);
                        }
                        downloadChunk.setSubChunkList(list2);
                    }
                } else {
                    DownloadChunk downloadChunk3 = (DownloadChunk) sparseArray.get(downloadChunk.getHostChunkIndex());
                    if (downloadChunk3 != null) {
                        List<DownloadChunk> subChunkList = downloadChunk3.getSubChunkList();
                        if (subChunkList == null) {
                            subChunkList = new ArrayList<>();
                            downloadChunk3.setSubChunkList(subChunkList);
                        }
                        downloadChunk.setHostChunk(downloadChunk3);
                        subChunkList.add(downloadChunk);
                    } else {
                        List list3 = (List) sparseArray2.get(downloadChunk.getHostChunkIndex());
                        if (list3 == null) {
                            list3 = new ArrayList();
                            sparseArray2.put(downloadChunk.getHostChunkIndex(), list3);
                        }
                        list3.add(downloadChunk);
                    }
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < sparseArray.size(); i++) {
            arrayList.add(sparseArray.get(sparseArray.keyAt(i)));
        }
        return arrayList.isEmpty() ? list : arrayList;
    }

    public static String getTargetFilePath(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return String.format("%s%s%s", str, File.separator, str2);
    }

    public static String getTempFilePath(String str, String str2, String str3) {
        String targetFilePath;
        if ((TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) || TextUtils.isEmpty(str3)) {
            return null;
        }
        if (!TextUtils.isEmpty(str2)) {
            targetFilePath = getTargetFilePath(str2, str3);
        } else {
            targetFilePath = getTargetFilePath(str, str3);
        }
        if (TextUtils.isEmpty(targetFilePath)) {
            return null;
        }
        return String.format("%s.tp", targetFilePath);
    }

    public static String getTempFileSavePath(String str, String str2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return null;
        }
        return !TextUtils.isEmpty(str2) ? str2 : str;
    }

    public static String getTempFileName(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return String.format("%s.tp", str);
    }

    public static long getTotalOffset(List<DownloadChunk> list) {
        long j = 0;
        for (DownloadChunk downloadChunk : list) {
            j += downloadChunk.getDownloadChunkBytes();
        }
        return j;
    }

    public static void deleteAllDownloadFiles(DownloadInfo downloadInfo) {
        deleteAllDownloadFiles(downloadInfo, true);
    }

    public static void deleteAllDownloadFiles(DownloadInfo downloadInfo, boolean z) {
        if (downloadInfo == null) {
            return;
        }
        if (z) {
            try {
                deleteFile(downloadInfo.getSavePath(), downloadInfo.getName());
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
        deleteFile(downloadInfo.getTempPath(), downloadInfo.getTempName());
        if (downloadInfo.isSavePathRedirected()) {
            clearAntiHijackDir(downloadInfo);
        }
        if (z) {
            String md5Hex = md5Hex(downloadInfo.getUrl());
            if (TextUtils.isEmpty(md5Hex) || TextUtils.isEmpty(downloadInfo.getSavePath()) || !downloadInfo.getSavePath().contains(md5Hex)) {
                return;
            }
            deleteDirIfEmpty(downloadInfo.getSavePath());
        }
    }

    public static void clearAntiHijackDir(DownloadInfo downloadInfo) {
        DownloadSetting obtain;
        JSONObject optJSONObject;
        if (downloadInfo == null || (optJSONObject = (obtain = DownloadSetting.obtain(downloadInfo.getId())).optJSONObject("download_dir")) == null) {
            return;
        }
        String optString = optJSONObject.optString("ins_desc");
        if (!TextUtils.isEmpty(optString)) {
            deleteFile(downloadInfo.getSavePath(), optString);
        }
        String title = downloadInfo.getTitle();
        if (TextUtils.isEmpty(title)) {
            title = downloadInfo.getName();
        }
        String redirectSavePath = getRedirectSavePath(title, obtain);
        String savePath = downloadInfo.getSavePath();
        if (TextUtils.isEmpty(redirectSavePath) || TextUtils.isEmpty(savePath)) {
            return;
        }
        File file = new File(redirectSavePath);
        for (File file2 = new File(savePath); file != null && file2 != null && file2.isDirectory() && TextUtils.equals(file.getName(), file2.getName()); file2 = file2.getParentFile()) {
            deleteDirIfEmpty(file2.getPath());
            file = file.getParentFile();
        }
    }

    private static boolean deleteDirIfEmpty(String str) {
        String str2 = TAG;
        Log.w(str2, "deleteDirIfEmpty on thread: " + Thread.currentThread());
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            if (file.isDirectory()) {
                if (file.delete()) {
                    return true;
                }
                Log.w(TAG, "deleteDirIfEmpty return false");
                return false;
            }
        }
        return false;
    }

    public static void deleteFile(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        File file = new File(str, str2);
        if (file.exists()) {
            String str3 = TAG;
            Log.e(str3, "deleteFile: " + str + "/" + str2);
            file.delete();
        }
    }

    public static boolean checkPermission(Context context, String str) {
        return (context == null || TextUtils.isEmpty(str) || context.checkCallingOrSelfPermission(str) != 0) ? false : true;
    }

    public static boolean isFileDownloaded(DownloadInfo downloadInfo) {
        return isFileDownloaded(downloadInfo, downloadInfo.isForce(), downloadInfo.getMd5());
    }

    public static boolean isFileDownloaded(DownloadInfo downloadInfo, boolean z, String str) {
        if (z || TextUtils.isEmpty(downloadInfo.getSavePath()) || TextUtils.isEmpty(downloadInfo.getName())) {
            return false;
        }
        try {
            if (new File(downloadInfo.getSavePath(), downloadInfo.getName()).exists()) {
                if (checkMd5Valid(downloadInfo.getSavePath(), downloadInfo.getName(), str)) {
                    return true;
                }
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isFileDownloaded(String str, String str2, boolean z) {
        if (z || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        try {
            if (new File(str, str2).exists()) {
                if (checkMd5Valid(str, str2, null)) {
                    return true;
                }
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isFileDownloaded(String str, String str2, String str3, boolean z) {
        if (z || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        try {
            if (new File(str, str2).exists()) {
                if (checkMd5Valid(str, str2, str3)) {
                    return true;
                }
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean cacheExpired(DownloadInfo downloadInfo) {
        boolean z = false;
        if (!downloadInfo.isDeleteCacheIfCheckFailed() && TextUtils.isEmpty(downloadInfo.getLastModified())) {
            Logger.m6475d(TAG, "dcache::last modify is emtpy, so just return cache");
        } else {
            String str = TAG;
            Logger.m6475d(str, "dcache::curt=" + System.currentTimeMillis() + " expired=" + downloadInfo.getCacheExpiredTime());
            if (System.currentTimeMillis() > downloadInfo.getCacheExpiredTime()) {
                z = true;
            }
        }
        String str2 = TAG;
        Logger.m6475d(str2, "cacheExpired::dcache::name=" + downloadInfo.getName() + " expired=" + z);
        return z;
    }

    public static int checkMd5Status(String str, String str2, String str3) {
        return TTMd5.checkMd5(str3, new File(str, str2));
    }

    public static boolean checkMd5Valid(String str, String str2, String str3) {
        return isMd5Valid(checkMd5Status(str, str2, str3));
    }

    public static int checkMd5Status(File file, String str) {
        return TTMd5.checkMd5(str, file);
    }

    public static boolean checkMd5Valid(File file, String str) {
        return isMd5Valid(TTMd5.checkMd5(str, file));
    }

    public static String getMd5StatusMsg(int i) {
        String str = "ttmd5 check code = " + i + ", ";
        if (i != 99) {
            switch (i) {
                case 0:
                    return str + "md5 match";
                case 1:
                    return str + "md5 not match";
                case 2:
                    return str + "md5 empty";
                case 3:
                    return str + "ttmd5 version not support";
                case 4:
                    return str + "ttmd5 tag parser error";
                case 5:
                    return str + "file not exist";
                case 6:
                    return str + "get file md5 error";
                default:
                    return str;
            }
        }
        return str + "unknown error";
    }

    public static long getAvailableSpaceBytes(String str) throws BaseException {
        try {
            StatFs statFs = new StatFs(str);
            if (Build.VERSION.SDK_INT >= 18) {
                return statFs.getAvailableBytes();
            }
            return statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (IllegalArgumentException e) {
            throw new BaseException(1050, e);
        } catch (Throwable th) {
            throw new BaseException(1052, th);
        }
    }

    public static void saveFileAsTargetName(DownloadInfo downloadInfo, IDownloadMonitorDepend iDownloadMonitorDepend, ITempFileSaveCompleteCallback iTempFileSaveCompleteCallback) {
        boolean z;
        BaseException baseException;
        boolean z2;
        Logger.m6475d(TAG, "saveFileAsTargetName targetName is " + downloadInfo.getTargetFilePath());
        try {
            synchronized (saveTempFileStatusMap) {
                if (saveTempFileStatusMap.get(downloadInfo.getId()) == Boolean.TRUE) {
                    Logger.m6475d(TAG, "has another same task is saving temp file");
                    if (iTempFileSaveCompleteCallback != null) {
                        List<ITempFileSaveCompleteCallback> list = saveTempFileListeners.get(downloadInfo.getId());
                        if (list == null) {
                            list = new ArrayList<>();
                            saveTempFileListeners.put(downloadInfo.getId(), list);
                        }
                        list.add(iTempFileSaveCompleteCallback);
                    }
                    return;
                }
                Logger.m6475d(TAG, "saveTempFileStatusMap put id:" + downloadInfo.getId());
                saveTempFileStatusMap.put(downloadInfo.getId(), Boolean.TRUE);
                File file = new File(downloadInfo.getTempPath(), downloadInfo.getTempName());
                File file2 = new File(downloadInfo.getSavePath(), downloadInfo.getName());
                boolean isSavePathSecurity = isSavePathSecurity(downloadInfo.getSavePath());
                if (file2.exists()) {
                    Logger.m6475d(TAG, "targetFile exist");
                    int checkMd5Status = checkMd5Status(file2, downloadInfo.getMd5());
                    if (isMd5Valid(checkMd5Status)) {
                        Logger.m6475d(TAG, "tempFile not exist , targetFile exists and md5 check valid");
                        downloadInfo.setTTMd5CheckStatus(checkMd5Status);
                        if (iTempFileSaveCompleteCallback != null) {
                            iTempFileSaveCompleteCallback.onSuccess();
                        }
                        handleTempSaveCallback(downloadInfo.getId(), true, null);
                        z = false;
                    } else {
                        if (file.exists()) {
                            z = true;
                        } else {
                            BaseException baseException2 = new BaseException(1005, String.format("Can't save the temp downloaded file(%s/%s) to the target file(%s/%s) because tempFile is not exist and target file is exist but md5 verify invalid :%s", downloadInfo.getTempPath(), downloadInfo.getTempName(), downloadInfo.getSavePath(), downloadInfo.getName(), getMd5StatusMsg(checkMd5Status)));
                            if (iTempFileSaveCompleteCallback != null) {
                                iTempFileSaveCompleteCallback.onFailed(baseException2);
                            }
                            handleTempSaveCallback(downloadInfo.getId(), false, baseException2);
                            z = false;
                        }
                        if (isSavePathSecurity && !file2.delete()) {
                            if (z) {
                                BaseException baseException3 = new BaseException(1037, "delete targetPath file existed with md5 check invalid status:" + getMd5StatusMsg(checkMd5Status));
                                if (iTempFileSaveCompleteCallback != null) {
                                    iTempFileSaveCompleteCallback.onFailed(baseException3);
                                }
                                handleTempSaveCallback(downloadInfo.getId(), false, baseException3);
                                z = false;
                            } else if (iDownloadMonitorDepend != null) {
                                DownloadMonitorHelper.monitorSendWithTaskMonitor(iDownloadMonitorDepend, downloadInfo, new BaseException(1038, "tempFile is not exist and target file is exist but md5 verify invalid, delete target file failed"), downloadInfo.getStatus());
                            }
                        }
                    }
                } else if (file.exists()) {
                    z = true;
                } else {
                    BaseException baseException4 = new BaseException(1005, String.format("Can't save the temp downloaded file(%s/%s) to the target file(%s/%s) because tempFile is not exist", downloadInfo.getTempPath(), downloadInfo.getTempName(), downloadInfo.getSavePath(), downloadInfo.getName()));
                    if (iTempFileSaveCompleteCallback != null) {
                        iTempFileSaveCompleteCallback.onFailed(baseException4);
                    }
                    handleTempSaveCallback(downloadInfo.getId(), false, baseException4);
                    z = false;
                }
                if (z) {
                    try {
                        int optInt = DownloadSetting.obtain(downloadInfo.getId()).optInt("download_finish_check_ttmd5", 2);
                        if (optInt > 0) {
                            int checkMd5Status2 = checkMd5Status(file, downloadInfo.getMd5());
                            downloadInfo.setTTMd5CheckStatus(checkMd5Status2);
                            if (optInt >= 2 && !isMd5Valid(checkMd5Status2)) {
                                BaseException baseException5 = new BaseException(1034, getMd5StatusMsg(checkMd5Status2));
                                if (iTempFileSaveCompleteCallback != null) {
                                    iTempFileSaveCompleteCallback.onFailed(baseException5);
                                }
                                handleTempSaveCallback(downloadInfo.getId(), false, baseException5);
                                deleteAllDownloadFiles(downloadInfo, isSavePathSecurity);
                                return;
                            }
                        }
                        z2 = !moveFile(file, file2);
                        baseException = null;
                    } catch (BaseException e) {
                        baseException = e;
                        z2 = DownloadSetting.obtain(downloadInfo.getId()).optBugFix("fix_file_rename_failed");
                    }
                    if (z2) {
                        if (baseException == null) {
                            baseException = new BaseException(1038, String.format("Can't save the temp downloaded file(%s/%s) to the target file(%s/%s)", downloadInfo.getTempPath(), downloadInfo.getTempName(), downloadInfo.getSavePath(), downloadInfo.getName()));
                        }
                        if (iTempFileSaveCompleteCallback != null) {
                            iTempFileSaveCompleteCallback.onFailed(baseException);
                        }
                        handleTempSaveCallback(downloadInfo.getId(), false, baseException);
                        return;
                    }
                    if (iTempFileSaveCompleteCallback != null) {
                        iTempFileSaveCompleteCallback.onSuccess();
                    }
                    handleTempSaveCallback(downloadInfo.getId(), true, null);
                }
            }
        } catch (Throwable th) {
            Logger.m6475d(TAG, "saveFileAsTargetName throwable " + th.getMessage());
            if (iTempFileSaveCompleteCallback != null) {
                iTempFileSaveCompleteCallback.onFailed(new BaseException(1038, getErrorMsgWithTagPrefix(th, "saveFileAsTargetName")));
            }
        }
    }

    private static void handleTempSaveCallback(int i, boolean z, BaseException baseException) {
        synchronized (saveTempFileStatusMap) {
            List<ITempFileSaveCompleteCallback> list = saveTempFileListeners.get(i);
            if (list != null) {
                for (ITempFileSaveCompleteCallback iTempFileSaveCompleteCallback : list) {
                    if (iTempFileSaveCompleteCallback != null) {
                        if (z) {
                            iTempFileSaveCompleteCallback.onSuccess();
                        } else {
                            iTempFileSaveCompleteCallback.onFailed(baseException);
                        }
                    }
                }
            }
            String str = TAG;
            Logger.m6475d(str, "handleTempSaveCallback id:" + i);
            saveTempFileStatusMap.remove(i);
        }
    }

    public static void copyFileFromExistFileWithSameName(DownloadInfo downloadInfo, String str) throws BaseException {
        if (downloadInfo == null || TextUtils.isEmpty(str) || str.equals(downloadInfo.getName())) {
            return;
        }
        File file = new File(downloadInfo.getSavePath(), str);
        File file2 = new File(downloadInfo.getSavePath(), downloadInfo.getName());
        String str2 = TAG;
        Log.e(str2, "copyFileFromExistFileWithSameName: existFile:" + file.getPath() + " targetFile:" + file2.getPath());
        if (file2.exists() && !file2.canWrite()) {
            throw new BaseException(1001, "targetPath file exists but read-only");
        }
        if (!copyFile(file, file2)) {
            throw new BaseException(1001, String.format("Can't copy the exist file(%s/%s) to the target file(%s/%s)", downloadInfo.getSavePath(), str, downloadInfo.getSavePath(), downloadInfo.getName()));
        }
    }

    public static boolean copyFile(File file, File file2) throws BaseException {
        return copyFile(file, file2, true);
    }

    public static boolean copyFile(File file, File file2, boolean z) throws BaseException {
        if (file != null && file2 != null) {
            try {
                if (file.exists() && !file.isDirectory() && !file.getCanonicalPath().equals(file2.getCanonicalPath())) {
                    File parentFile = file2.getParentFile();
                    if (parentFile != null && !parentFile.mkdirs() && !parentFile.isDirectory()) {
                        throw new BaseException(1053, "Destination '" + parentFile + "' directory cannot be created");
                    }
                    String str = TAG;
                    Log.e(str, "copyFile: srcFile:" + file.getPath() + " destFile:" + file2.getPath());
                    if (file2.exists() && !file2.canWrite()) {
                        throw new IOException("Destination '" + file2 + "' exists but is read-only");
                    }
                    doCopyFile(file, file2, z);
                    return true;
                }
            } catch (BaseException e) {
                throw e;
            } catch (Throwable th) {
                parseException(th, "CopyFile");
                return false;
            }
        }
        return false;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    @android.annotation.TargetApi(19)
    private static void doCopyFile(java.io.File r19, java.io.File r20, boolean r21) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 293
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.downloader.utils.DownloadUtils.doCopyFile(java.io.File, java.io.File, boolean):void");
    }

    public static boolean moveFile(File file, File file2) throws BaseException {
        String str = TAG;
        Log.e(str, "moveFile1: src:" + file.getPath() + " dest:" + file2.getPath());
        boolean renameTo = file.renameTo(file2);
        if (!renameTo) {
            renameTo = copyFile(file, file2);
            try {
                String str2 = TAG;
                Log.e(str2, "moveFile2: src:" + file.getPath() + " dest:" + file2.getPath());
                file.delete();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return renameTo;
    }

    public static boolean isFileExist(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return new File(str, str2).exists();
    }

    public static boolean canAcceptPartial(int i, String str) {
        if (DownloadExpSwitchCode.isSwitchEnable(16777216)) {
            return i == 206 || i == 1;
        } else if (i >= 400) {
            return false;
        } else {
            return i == 206 || i == 1 || "bytes".equals(str);
        }
    }

    public static boolean isChunkedTask(IDownloadHeadHttpConnection iDownloadHeadHttpConnection) {
        if (iDownloadHeadHttpConnection == null) {
            return false;
        }
        return DownloadExpSwitchCode.isSwitchEnable(8) ? "chunked".equals(iDownloadHeadHttpConnection.getResponseHeaderField("Transfer-Encoding")) || getContentLength(iDownloadHeadHttpConnection) == -1 : getContentLength(iDownloadHeadHttpConnection) == -1;
    }

    public static List<HttpHeader> add0_0RangeHeader(List<HttpHeader> list, String str) {
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() > 0) {
            for (HttpHeader httpHeader : list) {
                if (httpHeader != null) {
                    arrayList.add(httpHeader);
                }
            }
        }
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(new HttpHeader("If-Match", str));
        }
        arrayList.add(new HttpHeader("Accept-Encoding", "identity"));
        arrayList.add(new HttpHeader("Range", "bytes=0-0"));
        return arrayList;
    }

    public static List<HttpHeader> addRangeHeader(List<HttpHeader> list, String str, DownloadChunk downloadChunk) {
        return addRangeHeader(list, str, downloadChunk.getCurOffset(), downloadChunk.getEndOffset());
    }

    public static List<HttpHeader> addRangeHeader(List<HttpHeader> list, String str, long j, long j2) {
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() > 0) {
            for (HttpHeader httpHeader : list) {
                if (httpHeader != null) {
                    arrayList.add(httpHeader);
                }
            }
        }
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(new HttpHeader("If-Match", str));
        }
        arrayList.add(new HttpHeader("Accept-Encoding", "identity"));
        String format = j2 <= 0 ? String.format("bytes=%s-", String.valueOf(j)) : String.format("bytes=%s-%s", String.valueOf(j), String.valueOf(j2));
        arrayList.add(new HttpHeader("Range", format));
        String str2 = TAG;
        Logger.m6475d(str2, " range CurrentOffset:" + j + " EndOffset:" + j2 + ", range = " + format);
        return arrayList;
    }

    public static File getDatabaseFile(Context context, boolean z, String str) {
        String str2;
        try {
            str2 = Environment.getExternalStorageState();
        } catch (IncompatibleClassChangeError unused) {
            str2 = "";
        } catch (NullPointerException unused2) {
            str2 = "";
        }
        File externalDBFile = (z && "mounted".equals(str2) && hasExternalStoragePermission(context)) ? getExternalDBFile(str) : null;
        if (externalDBFile == null) {
            externalDBFile = context.getDatabasePath(str);
        }
        if (externalDBFile == null) {
            return new File("/data/data/" + context.getPackageName() + "/database/", str);
        }
        return externalDBFile;
    }

    private static boolean hasExternalStoragePermission(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    private static File getExternalDBFile(String str) {
        File file;
        if (isSdcardAvailable() && isSdcardWritable()) {
            try {
                file = DownloadComponentManager.getAppContext().getExternalFilesDir("database" + File.separator + str);
            } catch (Exception e) {
                e = e;
                file = null;
            }
            if (file == null) {
                return null;
            }
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                if (Logger.debug()) {
                    Logger.m6475d(TAG, "download db path:" + file.getAbsolutePath());
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return file;
            }
            return file;
        }
        return null;
    }

    public static void ensureDirExists(String str) {
        if (str == null) {
            return;
        }
        ensureDirExists(new File(str));
    }

    private static void ensureDirExists(File file) {
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    private static boolean isSdcardAvailable() {
        String externalStorageState = Environment.getExternalStorageState();
        return "mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState);
    }

    private static boolean isSdcardWritable() {
        try {
            return "mounted".equals(Environment.getExternalStorageState());
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isDownloadSuccessAndFileNotExist(int i, String str, String str2) {
        return i == -3 && !isFileExist(str, str2);
    }

    public static ConnectivityManager getConnectivityManager(Context context) {
        ConnectivityManager connectivityManager2 = connectivityManager;
        if (connectivityManager2 == null) {
            ConnectivityManager connectivityManager3 = (ConnectivityManager) context.getSystemService("connectivity");
            connectivityManager = connectivityManager3;
            return connectivityManager3;
        }
        return connectivityManager2;
    }

    public static boolean isWifi(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager2 = getConnectivityManager(context);
            if (connectivityManager2 != null && (activeNetworkInfo = connectivityManager2.getActiveNetworkInfo()) != null && activeNetworkInfo.isAvailable()) {
                return 1 == activeNetworkInfo.getType();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager2 = getConnectivityManager(context);
            if (connectivityManager2 == null || (activeNetworkInfo = connectivityManager2.getActiveNetworkInfo()) == null) {
                return false;
            }
            return activeNetworkInfo.isConnected();
        } catch (Exception unused) {
            return false;
        }
    }

    public static String md5Hex(String str) {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    messageDigest.update(str.getBytes("UTF-8"));
                    return toHexString(messageDigest.digest());
                }
            } catch (Exception unused) {
                return null;
            }
        }
        return null;
    }

    public static String getCurProcessName(Context context) {
        String str = sCurProcessName;
        if (TextUtils.isEmpty(str)) {
            sCurProcessName = getCurProcessNameByApplication();
            if (!TextUtils.isEmpty(sCurProcessName)) {
                return sCurProcessName;
            }
            sCurProcessName = getCurProcessNameByActivityThread();
            if (!TextUtils.isEmpty(sCurProcessName)) {
                return sCurProcessName;
            }
            sCurProcessName = getCurProcessNameByActivityManager(context);
            if (!TextUtils.isEmpty(sCurProcessName)) {
                return sCurProcessName;
            }
            sCurProcessName = getCurProcessNameFromProc();
            return sCurProcessName;
        }
        return str;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    private static java.lang.String getCurProcessNameFromProc() {
        /*
            r0 = 0
            r1 = 0
            r2 = 1
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L78
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L78
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L78
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L78
            r6.<init>()     // Catch: java.lang.Throwable -> L78
            java.lang.String r7 = "/proc/"
            r6.append(r7)     // Catch: java.lang.Throwable -> L78
            int r7 = android.os.Process.myPid()     // Catch: java.lang.Throwable -> L78
            r6.append(r7)     // Catch: java.lang.Throwable -> L78
            java.lang.String r7 = "/cmdline"
            r6.append(r7)     // Catch: java.lang.Throwable -> L78
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L78
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L78
            java.lang.String r6 = "iso-8859-1"
            r4.<init>(r5, r6)     // Catch: java.lang.Throwable -> L78
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L78
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L79
            r4.<init>()     // Catch: java.lang.Throwable -> L79
        L33:
            int r5 = r3.read()     // Catch: java.lang.Throwable -> L79
            if (r5 <= 0) goto L3e
            char r5 = (char) r5     // Catch: java.lang.Throwable -> L79
            r4.append(r5)     // Catch: java.lang.Throwable -> L79
            goto L33
        L3e:
            boolean r5 = com.p319ss.android.socialbase.downloader.logger.Logger.debug()     // Catch: java.lang.Throwable -> L79
            if (r5 == 0) goto L5e
            java.lang.String r5 = "Process"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L79
            r6.<init>()     // Catch: java.lang.Throwable -> L79
            java.lang.String r7 = "get processName = "
            r6.append(r7)     // Catch: java.lang.Throwable -> L79
            java.lang.String r7 = r4.toString()     // Catch: java.lang.Throwable -> L79
            r6.append(r7)     // Catch: java.lang.Throwable -> L79
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L79
            com.p319ss.android.socialbase.downloader.logger.Logger.m6475d(r5, r6)     // Catch: java.lang.Throwable -> L79
        L5e:
            java.lang.String r0 = r4.toString()     // Catch: java.lang.Throwable -> L79
            java.io.Closeable[] r2 = new java.io.Closeable[r2]
            r2[r1] = r3
            safeClose(r2)
            return r0
        L6a:
            r0 = move-exception
            r8 = r3
            r3 = r0
            r0 = r8
            goto L70
        L6f:
            r3 = move-exception
        L70:
            java.io.Closeable[] r2 = new java.io.Closeable[r2]
            r2[r1] = r0
            safeClose(r2)
            throw r3
        L78:
            r3 = r0
        L79:
            java.io.Closeable[] r2 = new java.io.Closeable[r2]
            r2[r1] = r3
            safeClose(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.downloader.utils.DownloadUtils.getCurProcessNameFromProc():java.lang.String");
    }

    private static String getCurProcessNameByApplication() {
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                String processName = Application.getProcessName();
                if (!TextUtils.isEmpty(processName) && Logger.debug()) {
                    Logger.m6475d("Process", "processName = " + processName);
                }
                return processName;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    private static String getCurProcessNameByActivityThread() {
        String str = null;
        try {
            Method declaredMethod = Class.forName("android.app.ActivityThread", false, Application.class.getClassLoader()).getDeclaredMethod("currentProcessName", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            if (invoke instanceof String) {
                String str2 = (String) invoke;
                try {
                    if (!TextUtils.isEmpty(str2) && Logger.debug()) {
                        Logger.m6475d("Process", "processName = " + str2);
                    }
                    return str2;
                } catch (Throwable th) {
                    th = th;
                    str = str2;
                    th.printStackTrace();
                    return str;
                }
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static String getCurProcessNameByActivityManager(Context context) {
        if (context == null) {
            return null;
        }
        try {
            int myPid = Process.myPid();
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager != null) {
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                    if (runningAppProcessInfo.pid == myPid) {
                        if (Logger.debug()) {
                            Logger.m6475d("Process", "processName = " + runningAppProcessInfo.processName);
                        }
                        return runningAppProcessInfo.processName;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isMainProcess() {
        Boolean bool = sIsMainProcess;
        if (bool != null) {
            return bool.booleanValue();
        }
        String curProcessName = getCurProcessName(DownloadComponentManager.getAppContext());
        boolean z = false;
        if (curProcessName != null && curProcessName.contains(":")) {
            sIsMainProcess = false;
        } else {
            if (curProcessName != null && curProcessName.equals(DownloadComponentManager.getAppContext().getPackageName())) {
                z = true;
            }
            sIsMainProcess = Boolean.valueOf(z);
        }
        return sIsMainProcess.booleanValue();
    }

    public static boolean needNotifyDownloaderProcess() {
        return !isDownloaderProcess() && DownloadComponentManager.isDownloadInMultiProcess() && DownloadProxy.get(true).isServiceAlive();
    }

    public static boolean isDownloaderProcess() {
        Boolean bool = sIsDownloaderProcess;
        if (bool != null) {
            return bool.booleanValue();
        }
        String curProcessName = getCurProcessName(DownloadComponentManager.getAppContext());
        if (curProcessName != null) {
            if (curProcessName.equals(DownloadComponentManager.getAppContext().getPackageName() + ":downloader")) {
                sIsDownloaderProcess = true;
                return sIsDownloaderProcess.booleanValue();
            }
        }
        sIsDownloaderProcess = false;
        return sIsDownloaderProcess.booleanValue();
    }

    public static boolean isProcessNameSame(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        String curProcessName = getCurProcessName(DownloadComponentManager.getAppContext());
        return curProcessName != null && curProcessName.equals(str);
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static String getLoggerTag(String str) {
        return Logger.downloaderTag(str);
    }

    public static String getEncodedStr(String str) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt <= 31 || charAt >= 127) {
                sb.append(String.format("\\u%04x", Integer.valueOf(charAt)));
            } else {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    public static boolean isTimeOutException(Throwable th) {
        if (th == null) {
            return false;
        }
        String throwableMsg = getThrowableMsg(th);
        if (!(th instanceof SocketTimeoutException)) {
            if (TextUtils.isEmpty(throwableMsg)) {
                return false;
            }
            if (!throwableMsg.contains("time out") && !throwableMsg.contains("Time-out")) {
                return false;
            }
        }
        return true;
    }

    public static boolean isForbiddenException(Throwable th) {
        DownloadHttpException downloadHttpException;
        if (th == null) {
            return false;
        }
        String throwableMsg = getThrowableMsg(th);
        if (!(th instanceof DownloadHttpException) || (((downloadHttpException = (DownloadHttpException) th) == null || downloadHttpException.getHttpStatusCode() != 403) && (TextUtils.isEmpty(throwableMsg) || !throwableMsg.contains("403")))) {
            return !TextUtils.isEmpty(throwableMsg) && throwableMsg.contains("Forbidden");
        }
        return true;
    }

    public static boolean isNetNotAvailableException(Throwable th) {
        if (th == null) {
            return false;
        }
        String throwableMsg = getThrowableMsg(th);
        return !TextUtils.isEmpty(throwableMsg) && throwableMsg.contains("network not available");
    }

    public static boolean isConnectionException(Throwable th) {
        if (th == null) {
            return false;
        }
        String throwableMsg = getThrowableMsg(th);
        return !TextUtils.isEmpty(throwableMsg) && throwableMsg.contains("Exception in connect");
    }

    public static boolean isResponseCodeError(Throwable th) {
        if (th == null) {
            return false;
        }
        String throwableMsg = getThrowableMsg(th);
        if (TextUtils.isEmpty(throwableMsg)) {
            return false;
        }
        return throwableMsg.contains("Requested Range Not Satisfiable") || throwableMsg.contains("Precondition Failed");
    }

    public static boolean isResponseCode412Error(Throwable th) {
        if (th == null) {
            return false;
        }
        String throwableMsg = getThrowableMsg(th);
        return !TextUtils.isEmpty(throwableMsg) && throwableMsg.contains("Precondition Failed");
    }

    public static boolean isResponseCode416Error(Throwable th) {
        if (th == null) {
            return false;
        }
        String throwableMsg = getThrowableMsg(th);
        return !TextUtils.isEmpty(throwableMsg) && throwableMsg.contains("Requested Range Not Satisfiable");
    }

    public static boolean isResponseCode304Error(Throwable th) {
        return DownloadComponentManager.getTTNetHandler().isResponseCode304Error(th);
    }

    public static boolean isHttpDataDirtyError(BaseException baseException) {
        return baseException != null && baseException.getErrorCode() == 1051;
    }

    public static boolean isResponseCodeError(BaseException baseException) {
        if (baseException instanceof DownloadHttpException) {
            DownloadHttpException downloadHttpException = (DownloadHttpException) baseException;
            if (downloadHttpException.getHttpStatusCode() == 412 || downloadHttpException.getHttpStatusCode() == 416) {
                return true;
            }
        }
        return false;
    }

    public static boolean isHttpsError(BaseException baseException) {
        if (baseException == null) {
            return false;
        }
        return baseException.getErrorCode() == 1011 || (baseException.getCause() != null && (baseException.getCause() instanceof SSLHandshakeException));
    }

    public static void parseException(Throwable th, String str) throws BaseException {
        String str2 = TextUtils.isEmpty(str) ? "" : str;
        if (th instanceof BaseException) {
            BaseException baseException = (BaseException) th;
            baseException.setErrorMsg(str2 + "-" + baseException.getErrorMessage());
            throw baseException;
        } else if (th instanceof SSLHandshakeException) {
            throw new BaseException(1011, getErrorMsgWithTagPrefix(th, str2));
        } else {
            if (isTimeOutException(th)) {
                throw new BaseException(1048, getErrorMsgWithTagPrefix(th, str2));
            }
            if (isResponseCode412Error(th)) {
                throw new DownloadHttpException(1004, 412, getErrorMsgWithTagPrefix(th, str2));
            }
            if (isResponseCode416Error(th)) {
                throw new DownloadHttpException(1004, 416, getErrorMsgWithTagPrefix(th, str2));
            }
            if (isForbiddenException(th)) {
                throw new BaseException(1047, getErrorMsgWithTagPrefix(th, str2));
            }
            if (isNetNotAvailableException(th)) {
                throw new BaseException(1049, getErrorMsgWithTagPrefix(th, str2));
            }
            if (isConnectionException(th)) {
                throw new BaseException(1041, getErrorMsgWithTagPrefix(th, str2));
            }
            if (th instanceof IOException) {
                parseTTNetException(th, str);
                parseIOException((IOException) th, str);
                return;
            }
            throw new BaseException(1000, getErrorMsgWithTagPrefix(th, str2));
        }
    }

    private static void parseTTNetException(Throwable th, String str) throws DownloadTTNetException {
        DownloadTTNetException translateTTNetException = DownloadComponentManager.getTTNetHandler().translateTTNetException(th, null);
        if (translateTTNetException == null) {
            translateTTNetException = DownloadComponentManager.getTTNetHandler().translateTTNetException(th.getCause(), null);
        }
        if (translateTTNetException == null) {
            return;
        }
        throw new DownloadTTNetException(translateTTNetException.getErrorCode(), getErrorMsgWithTagPrefix(translateTTNetException, str)).setRequestLog(translateTTNetException.getRequestLog());
    }

    public static void parseIOException(IOException iOException, String str) throws BaseException {
        if (str == null) {
            str = "";
        }
        String errorMsgWithTagPrefix = getErrorMsgWithTagPrefix(iOException, str);
        if (iOException instanceof ConnectException) {
            throw new BaseException(1041, errorMsgWithTagPrefix);
        }
        if (iOException instanceof UnknownHostException) {
            throw new BaseException(1055, errorMsgWithTagPrefix);
        }
        if (iOException instanceof NoRouteToHostException) {
            throw new BaseException(1056, errorMsgWithTagPrefix);
        }
        if (iOException instanceof UnknownServiceException) {
            throw new BaseException(1057, errorMsgWithTagPrefix);
        }
        if (iOException instanceof PortUnreachableException) {
            throw new BaseException(1058, errorMsgWithTagPrefix);
        }
        if (iOException instanceof SocketTimeoutException) {
            throw new BaseException(1048, errorMsgWithTagPrefix);
        }
        if (iOException instanceof SocketException) {
            throw new BaseException(1059, errorMsgWithTagPrefix);
        }
        if (iOException instanceof HttpRetryException) {
            throw new BaseException(1060, errorMsgWithTagPrefix);
        }
        if (iOException instanceof ProtocolException) {
            throw new BaseException(1061, errorMsgWithTagPrefix);
        }
        if (iOException instanceof MalformedURLException) {
            throw new BaseException(1062, errorMsgWithTagPrefix);
        }
        if (iOException instanceof FileNotFoundException) {
            throw new BaseException(1063, errorMsgWithTagPrefix);
        }
        if (iOException instanceof InterruptedIOException) {
            throw new BaseException(1064, errorMsgWithTagPrefix);
        }
        if (iOException instanceof UnsupportedEncodingException) {
            throw new BaseException(1065, errorMsgWithTagPrefix);
        }
        if (iOException instanceof EOFException) {
            throw new BaseException(1066, errorMsgWithTagPrefix);
        }
        if (iOException instanceof StreamResetException) {
            throw new BaseException(1067, errorMsgWithTagPrefix);
        }
        if (iOException instanceof SSLException) {
            throw new BaseException(1011, errorMsgWithTagPrefix);
        }
        if (isInsufficientSpaceError(iOException)) {
            throw new BaseException(1006, errorMsgWithTagPrefix);
        }
        throw new BaseException(1023, errorMsgWithTagPrefix);
    }

    public static boolean isInsufficientSpaceError(Throwable th) {
        if (th == null) {
            return false;
        }
        if (th instanceof BaseException) {
            BaseException baseException = (BaseException) th;
            int errorCode = baseException.getErrorCode();
            if (errorCode == 1006) {
                return true;
            }
            if (errorCode == 1023 || errorCode == 1039 || errorCode == 1040 || errorCode == 1054 || errorCode == 1064) {
                String message = baseException.getMessage();
                return !TextUtils.isEmpty(message) && message.contains("ENOSPC");
            }
        } else if (th instanceof IOException) {
            String throwableMsg = getThrowableMsg(th);
            return !TextUtils.isEmpty(throwableMsg) && throwableMsg.contains("ENOSPC");
        }
        return false;
    }

    public static boolean isNetworkError(Throwable th) {
        if (th instanceof BaseException) {
            int errorCode = ((BaseException) th).getErrorCode();
            return errorCode == 1055 || errorCode == 1023 || errorCode == 1041 || errorCode == 1022 || errorCode == 1048 || errorCode == 1056 || errorCode == 1057 || errorCode == 1058 || errorCode == 1059 || errorCode == 1060 || errorCode == 1061 || errorCode == 1067 || errorCode == 1049 || errorCode == 1047 || errorCode == 1051 || errorCode == 1004 || errorCode == 1011 || errorCode == 1002 || errorCode == 1013;
        }
        return false;
    }

    public static boolean canChunkDowngradeRetry(BaseException baseException, DownloadInfo downloadInfo) {
        if (baseException == null) {
            return false;
        }
        int errorCode = baseException.getErrorCode();
        if (errorCode == 1000 || errorCode == 1032 || errorCode == 1033 || errorCode == 1034 || errorCode == 1008 || errorCode == 1026 || errorCode == 1027 || errorCode == 1044 || errorCode == 1020) {
            return true;
        }
        return (errorCode == 1049 || errorCode == 1055 || errorCode == 1006 || downloadInfo == null || downloadInfo.getCurBytes() >= 8388608) ? false : true;
    }

    public static String getErrorMsgWithTagPrefix(Throwable th, String str) {
        if (str == null) {
            return getThrowableMsg(th);
        }
        return str + "-" + getThrowableMsg(th);
    }

    public static String getThrowableMsg(Throwable th) {
        if (th == null) {
            return "";
        }
        try {
            return th.toString();
        } catch (Throwable th2) {
            th2.printStackTrace();
            return "throwable getMsg error";
        }
    }

    public static ListenerType convertListenerType(int i) {
        ListenerType listenerType = ListenerType.MAIN;
        if (i == ListenerType.SUB.ordinal()) {
            return ListenerType.SUB;
        }
        return i == ListenerType.NOTIFICATION.ordinal() ? ListenerType.NOTIFICATION : listenerType;
    }

    public static <K> HashMap<Integer, K> sparseArrayToHashMap(SparseArray<K> sparseArray) {
        if (sparseArray == null) {
            return null;
        }
        HashMap<Integer, K> hashMap = new HashMap<>();
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            int keyAt = sparseArray.keyAt(i);
            hashMap.put(Integer.valueOf(keyAt), sparseArray.valueAt(i));
        }
        return hashMap;
    }

    public static <K> void sparseArrayPutAll(SparseArray<K> sparseArray, Map<Integer, K> map) {
        if (map == null || sparseArray == null) {
            return;
        }
        for (Integer num : map.keySet()) {
            if (num != null) {
                sparseArray.put(num.intValue(), map.get(num));
            }
        }
    }

    public static long getFirstOffset(DownloadInfo downloadInfo) {
        if (downloadInfo == null) {
            return -1L;
        }
        List<DownloadChunk> downloadChunk = DownloadComponentManager.getDownloadCache().getDownloadChunk(downloadInfo.getId());
        if (downloadInfo.getChunkCount() == 1) {
            return downloadInfo.getCurBytes();
        }
        if (downloadChunk == null || downloadChunk.size() <= 1) {
            return 0L;
        }
        long firstChunkCurOffset = getFirstChunkCurOffset(downloadChunk);
        if (firstChunkCurOffset >= 0) {
            return firstChunkCurOffset;
        }
        return 0L;
    }

    private static long getFirstChunkCurOffset(List<DownloadChunk> list) {
        if (list == null || list.isEmpty()) {
            return -1L;
        }
        long j = -1;
        for (DownloadChunk downloadChunk : list) {
            if (downloadChunk != null && (downloadChunk.getCurrentOffset() <= downloadChunk.getEndOffset() || downloadChunk.getEndOffset() == 0)) {
                if (j == -1 || j > downloadChunk.getCurrentOffset()) {
                    j = downloadChunk.getCurrentOffset();
                }
            }
        }
        return j;
    }

    public static long getCurByte(DownloadInfo downloadInfo) {
        if (downloadInfo == null) {
            return 0L;
        }
        List<DownloadChunk> downloadChunk = DownloadComponentManager.getDownloadCache().getDownloadChunk(downloadInfo.getId());
        int chunkCount = downloadInfo.getChunkCount();
        boolean z = chunkCount > 1;
        if (downloadInfo.isBreakpointAvailable()) {
            if (z) {
                if (downloadChunk == null || chunkCount != downloadChunk.size()) {
                    return 0L;
                }
                return getTotalOffset(downloadChunk);
            }
            return downloadInfo.getCurBytes();
        }
        return 0L;
    }

    public static boolean isHeaderEqual(List<HttpHeader> list, List<HttpHeader> list2) {
        if (list == list2) {
            return true;
        }
        if (list == null || list2 == null || list.size() != list2.size()) {
            return false;
        }
        return new HashSet(list).equals(new HashSet(list2));
    }

    public static void safeClose(Closeable... closeableArr) {
        if (closeableArr == null) {
            return;
        }
        for (Closeable closeable : closeableArr) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    public static void safeClose(Cursor... cursorArr) {
        if (cursorArr == null) {
            return;
        }
        for (Cursor cursor : cursorArr) {
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    public static String getFixLengthString(String str, int i) {
        return i == 0 ? "" : (TextUtils.isEmpty(str) || str.length() <= i) ? str : str.substring(0, i);
    }

    public static String getRedirectSavePath(String str, DownloadSetting downloadSetting) {
        JSONObject optJSONObject;
        if (downloadSetting == null || (optJSONObject = downloadSetting.optJSONObject("download_dir")) == null) {
            return "";
        }
        String optString = optJSONObject.optString("dir_name");
        String substring = (TextUtils.isEmpty(optString) || !optString.startsWith("/")) ? optString : optString.substring(1);
        if (TextUtils.isEmpty(substring)) {
            return substring;
        }
        if (substring.contains("%s")) {
            try {
                substring = String.format(substring, str);
            } catch (Throwable unused) {
            }
        } else {
            substring = substring + str;
        }
        return substring.length() > 255 ? substring.substring(substring.length() - 255) : substring;
    }

    public static String getDownloadPath() {
        return getValidDownloadPath(Downloader.getInstance(DownloadComponentManager.getAppContext()).getGlobalSaveDir(), true);
    }

    public static String getDownloadTempPath() {
        return getValidDownloadPath(Downloader.getInstance(DownloadComponentManager.getAppContext()).getGlobalSaveTempDir(), false);
    }

    private static String getValidDownloadPath(File file, boolean z) {
        Context appContext = DownloadComponentManager.getAppContext();
        if (isValidDirectory(file)) {
            return file.getAbsolutePath();
        }
        int i = appContext.getApplicationInfo().targetSdkVersion;
        if (Build.VERSION.SDK_INT >= 29 && ((i == 29 && !Environment.isExternalStorageLegacy()) || i > 29)) {
            File externalFilesDir = appContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            if (isValidDirectory(externalFilesDir)) {
                return externalFilesDir.getAbsolutePath();
            }
        } else {
            if (z) {
                File externalDownloadPath = getExternalDownloadPath();
                if (isValidDirectory(externalDownloadPath)) {
                    return externalDownloadPath.getAbsolutePath();
                }
            }
            File externalFilesDir2 = appContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            if (isValidDirectory(externalFilesDir2)) {
                return externalFilesDir2.getAbsolutePath();
            }
        }
        return appContext.getFilesDir().getAbsolutePath();
    }

    public static boolean isSavePathSecurity(String str) {
        Context appContext;
        if (DownloadSetting.obtainGlobal().optInt("save_path_security") <= 0 || (appContext = DownloadComponentManager.getAppContext()) == null || TextUtils.isEmpty(str) || str.startsWith("/data")) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Android/data/");
        sb.append(appContext.getPackageName());
        return str.contains(sb.toString());
    }

    public static boolean isValidDirectory(File file) {
        if (file == null) {
            return false;
        }
        try {
            if (file.exists() || file.mkdirs()) {
                return file.isDirectory();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static File getExternalDownloadPath() {
        String str;
        try {
            str = Environment.getExternalStorageState();
        } catch (Exception unused) {
            str = "";
        }
        if ("mounted".equals(str)) {
            return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        }
        return null;
    }

    public static void addThrottleNetSpeed(List<HttpHeader> list, DownloadInfo downloadInfo) {
        long throttleNetSpeed = downloadInfo.getThrottleNetSpeed();
        if (throttleNetSpeed > 0) {
            list.add(new HttpHeader("extra_throttle_net_speed", String.valueOf(throttleNetSpeed)));
        }
    }

    public static void addTTNetProtectTimeout(List<HttpHeader> list, DownloadInfo downloadInfo) {
        long ttnetProtectTimeout = downloadInfo.getTtnetProtectTimeout();
        if (ttnetProtectTimeout > 300) {
            list.add(new HttpHeader("extra_ttnet_protect_timeout", String.valueOf(ttnetProtectTimeout)));
        }
    }

    public static long cost(long j) {
        return System.currentTimeMillis() - j;
    }

    public static String getRespHeadFieldIgnoreCase(IDownloadHeadHttpConnection iDownloadHeadHttpConnection, String str) {
        if (iDownloadHeadHttpConnection == null || TextUtils.isEmpty(str)) {
            return null;
        }
        String responseHeaderField = iDownloadHeadHttpConnection.getResponseHeaderField(str);
        if (DownloadSetting.obtainGlobal().optBugFix("fix_get_http_resp_head_ignore_case", true)) {
            if (TextUtils.isEmpty(responseHeaderField)) {
                responseHeaderField = iDownloadHeadHttpConnection.getResponseHeaderField(str.toLowerCase());
            }
            return TextUtils.isEmpty(responseHeaderField) ? iDownloadHeadHttpConnection.getResponseHeaderField(str.toUpperCase()) : responseHeaderField;
        }
        return responseHeaderField;
    }

    public static int getInt(Object obj, int i) {
        try {
            return ((Integer) obj).intValue();
        } catch (ClassCastException unused) {
            return i;
        }
    }

    public static String getString(Object obj, String str) {
        try {
            return (String) obj;
        } catch (ClassCastException unused) {
            return str;
        }
    }

    public static boolean getBoolean(Object obj, boolean z) {
        try {
            return ((Boolean) obj).booleanValue();
        } catch (ClassCastException unused) {
            return z;
        }
    }

    public static boolean isWaitWifiAndInNet(BaseException baseException, DownloadInfo downloadInfo) {
        return downloadInfo != null && downloadInfo.isOnlyWifi() && isNetworkConnected(DownloadComponentManager.getAppContext());
    }

    public static boolean isNoWifiAndInNet() {
        Context appContext = DownloadComponentManager.getAppContext();
        return (appContext == null || isWifi(appContext) || !isNetworkConnected(appContext)) ? false : true;
    }

    public static long parserMaxAge(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        Matcher matcher = Pattern.compile("max-age=([0-9]+)").matcher(str);
        if (matcher.find()) {
            try {
                return Long.parseLong(matcher.group(1));
            } catch (Throwable th) {
                th.printStackTrace();
                return 0L;
            }
        }
        return 0L;
    }

    public static String generateDistinctDirectory(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            String md5Hex = md5Hex(str2);
            if (!TextUtils.isEmpty(md5Hex) && !str.contains(md5Hex)) {
                return new File(str, md5Hex).getAbsolutePath();
            }
        }
        return str;
    }

    public static boolean hasDownloadCacheHeader(List<HttpHeader> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        for (HttpHeader httpHeader : list) {
            if (httpHeader != null && !TextUtils.isEmpty(httpHeader.getName()) && !TextUtils.isEmpty(httpHeader.getValue()) && "download-tc21-1-15".equals(httpHeader.getName()) && "download-tc21-1-15".equals(httpHeader.getValue())) {
                return true;
            }
        }
        return false;
    }
}
