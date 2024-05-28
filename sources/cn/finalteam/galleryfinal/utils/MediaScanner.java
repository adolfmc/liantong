package cn.finalteam.galleryfinal.utils;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MediaScanner {
    private MusicSannerClient client;
    private MediaScannerConnection mediaScanConn;
    private String filePath = null;
    private String fileType = null;
    private String[] filePaths = null;

    public MediaScanner(Context context) {
        this.mediaScanConn = null;
        this.client = null;
        if (this.client == null) {
            this.client = new MusicSannerClient();
        }
        if (this.mediaScanConn == null) {
            this.mediaScanConn = new MediaScannerConnection(context, this.client);
        }
    }

    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    class MusicSannerClient implements MediaScannerConnection.MediaScannerConnectionClient {
        MusicSannerClient() {
        }

        @Override // android.media.MediaScannerConnection.MediaScannerConnectionClient
        public void onMediaScannerConnected() {
            if (MediaScanner.this.filePath != null) {
                MediaScanner.this.mediaScanConn.scanFile(MediaScanner.this.filePath, MediaScanner.this.fileType);
            }
            if (MediaScanner.this.filePaths != null) {
                for (String str : MediaScanner.this.filePaths) {
                    MediaScanner.this.mediaScanConn.scanFile(str, MediaScanner.this.fileType);
                }
            }
            MediaScanner.this.filePath = null;
            MediaScanner.this.fileType = null;
            MediaScanner.this.filePaths = null;
        }

        @Override // android.media.MediaScannerConnection.OnScanCompletedListener
        public void onScanCompleted(String str, Uri uri) {
            MediaScanner.this.mediaScanConn.disconnect();
        }
    }

    public void scanFile(String str, String str2) {
        this.filePath = str;
        this.fileType = str2;
        this.mediaScanConn.connect();
    }

    public void scanFile(String[] strArr, String str) {
        this.filePaths = strArr;
        this.fileType = str;
        this.mediaScanConn.connect();
    }

    public void unScanFile() {
        this.mediaScanConn.disconnect();
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    public String getFileType() {
        return this.fileType;
    }

    public void setFileType(String str) {
        this.fileType = str;
    }
}
