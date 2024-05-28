package com.p319ss.android.downloadlib.p329b;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.utils.C10049hj;
import com.p319ss.android.socialbase.appdownloader.C10085b;
import com.p319ss.android.socialbase.appdownloader.p340u.p341mb.C10155h;
import com.p319ss.android.socialbase.downloader.depend.IDownloadCompleteHandler;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.File;

/* renamed from: com.ss.android.downloadlib.b.mb */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9958mb implements IDownloadCompleteHandler {
    @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadCompleteHandler
    public void handle(DownloadInfo downloadInfo) throws BaseException {
        if (downloadInfo == null || !m7330mb(downloadInfo)) {
            return;
        }
        m7331mb(C9940x.getContext(), downloadInfo);
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadCompleteHandler
    public boolean needHandle(DownloadInfo downloadInfo) {
        if (downloadInfo != null) {
            return C10049hj.m7065ox(DownloadSetting.obtain(downloadInfo.getId()));
        }
        return false;
    }

    /* renamed from: mb */
    private boolean m7330mb(DownloadInfo downloadInfo) {
        String str = downloadInfo.getSavePath() + File.separator + downloadInfo.getName();
        File file = new File(str);
        String m6549mb = C10155h.m6549mb(C9940x.getContext(), C10085b.m6906mb(downloadInfo, file), str);
        boolean z = false;
        if (!TextUtils.isEmpty(m6549mb)) {
            String str2 = m6549mb + ".apk";
            if (str2.equals(downloadInfo.getName())) {
                return true;
            }
            try {
                z = file.renameTo(new File(downloadInfo.getSavePath() + File.separator + str2));
                if (z) {
                    downloadInfo.setName(str2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return z;
    }

    /* renamed from: mb */
    private void m7331mb(Context context, final DownloadInfo downloadInfo) {
        String str = downloadInfo.getSavePath() + File.separator + downloadInfo.getName();
        Cursor query = context.getContentResolver().query(MediaStore.Files.getContentUri("external"), new String[]{"_id"}, "_data=? ", new String[]{str}, null);
        if (query == null || !query.moveToFirst()) {
            MediaScannerConnection.scanFile(context, new String[]{str}, new String[]{"application/vnd.android.package-archive"}, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.ss.android.downloadlib.b.mb.1
                @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                public void onScanCompleted(String str2, Uri uri) {
                    if (uri != null) {
                        downloadInfo.safePutToDBJsonData("file_content_uri", uri.toString());
                        DownloadComponentManager.getDownloadCache().updateDownloadInfo(downloadInfo);
                    }
                }
            });
        } else {
            downloadInfo.safePutToDBJsonData("file_content_uri", ContentUris.withAppendedId(MediaStore.Files.getContentUri("external"), query.getInt(query.getColumnIndex("_id"))).toString());
        }
        DownloadUtils.safeClose(query);
    }
}
