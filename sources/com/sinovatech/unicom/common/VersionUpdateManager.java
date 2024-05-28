package com.sinovatech.unicom.common;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.p083v4.content.FileProvider;
import com.sinovatech.unicom.p318ui.App;
import java.io.File;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class VersionUpdateManager {
    private final String TAG = "VersionUpdateManager";
    private DownloadCompletedBroadcastReceiver completedBR;
    private long downID;
    private DownloadManager downloadManager;
    private Uri uri;

    public void startDown(String str) throws Exception {
        try {
            this.uri = Uri.parse(str);
            this.downloadManager = (DownloadManager) App.getInstance().getSystemService("download");
            this.completedBR = new DownloadCompletedBroadcastReceiver();
            App.getInstance().registerReceiver(this.completedBR, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
            DownloadManager.Request request = new DownloadManager.Request(this.uri);
            request.setTitle("中国联通");
            request.setNotificationVisibility(1);
            request.setVisibleInDownloadsUi(true);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Unicom_ShouJiYingYeTing.apk");
            this.downID = this.downloadManager.enqueue(request);
        } catch (Exception e) {
            throw e;
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class DownloadCompletedBroadcastReceiver extends BroadcastReceiver {
        DownloadCompletedBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            intent.getLongExtra("extra_download_id", -1L);
            try {
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/Unicom_ShouJiYingYeTing.apk");
                if (Build.VERSION.SDK_INT >= 24) {
                    Uri uriForFile = FileProvider.getUriForFile(context, "com.sinovatech.unicom.ui.fileprovider", file);
                    Intent intent2 = new Intent("android.intent.action.VIEW");
                    intent2.setFlags(268435456);
                    intent2.addFlags(1);
                    intent2.setDataAndType(uriForFile, "application/vnd.android.package-archive");
                    context.startActivity(intent2);
                } else {
                    Uri fromFile = Uri.fromFile(file);
                    Intent intent3 = new Intent("android.intent.action.VIEW");
                    intent3.setDataAndType(fromFile, "application/vnd.android.package-archive");
                    intent3.setFlags(268435456);
                    context.startActivity(intent3);
                }
            } catch (Exception e) {
                try {
                    e.printStackTrace();
                    UIUtils.toast("安装失败");
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
}
