package com.baidu.minivideo.arface.utils;

import android.content.Context;
import android.os.AsyncTask;
import com.baidu.minivideo.arface.DuArResConfig;
import java.io.File;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARSourceCopyManager extends BaseTask {
    private static final String TAG = "ARSourceCopyManager";

    /* renamed from: jn */
    public static JSONObject f7469jn;
    private static ARSourceCopyManager sInstance;
    private String mAssetPath;
    private AssetsCopyToSdcard mAssetsCopyToSdcard;
    private WeakReference<Context> mContext;
    private boolean mOverrid;
    private File mSdcardPath;

    private ARSourceCopyManager() {
    }

    private void setContext(Context context) {
        this.mContext = new WeakReference<>(context);
    }

    public void copyArResource(String str, File file, boolean z) {
        this.mAssetPath = str;
        this.mSdcardPath = file;
        this.mOverrid = z;
    }

    public static ARSourceCopyManager getInstance(Context context) {
        if (sInstance == null) {
            init(context);
        }
        sInstance.setContext(context);
        return sInstance;
    }

    private static synchronized void init(Context context) {
        synchronized (ARSourceCopyManager.class) {
            if (sInstance == null) {
                sInstance = new ARSourceCopyManager();
            }
        }
    }

    private Context getContext() {
        WeakReference<Context> weakReference = this.mContext;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // com.baidu.minivideo.arface.utils.ITask
    public void run() {
        boolean isExist = isExist();
        f7469jn = new JSONObject();
        if (isExist) {
            try {
                f7469jn.put("type", DuArResConfig.RES_COPY_NEEN ? "assets" : "soloader");
                f7469jn.put("exist", isExist);
                f7469jn.put("path", this.mSdcardPath);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            setState(2);
        } else if (getContext() != null) {
            try {
                doLoad();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public boolean isExist() {
        File file = this.mSdcardPath;
        return file != null && file.isDirectory() && this.mSdcardPath.exists();
    }

    private void doLoad() {
        this.mAssetsCopyToSdcard = new AssetsCopyToSdcard(getContext());
        new AsyncTask<Void, Void, Boolean>() { // from class: com.baidu.minivideo.arface.utils.ARSourceCopyManager.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Boolean doInBackground(Void... voidArr) {
                if (ARSourceCopyManager.f7469jn == null) {
                    ARSourceCopyManager.f7469jn = new JSONObject();
                }
                if (ARSourceCopyManager.this.mSdcardPath != null) {
                    if (ARSourceCopyManager.this.mOverrid || !ARSourceCopyManager.this.isExist()) {
                        if (!ARSourceCopyManager.this.mSdcardPath.isDirectory()) {
                            try {
                                ARSourceCopyManager.f7469jn.put("file-del", true);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            ARSourceCopyManager.this.mSdcardPath.delete();
                        }
                        File file = new File(ARSourceCopyManager.this.mSdcardPath.getAbsoluteFile() + ".loading");
                        boolean assetToSD = ARSourceCopyManager.this.mAssetsCopyToSdcard.assetToSD(ARSourceCopyManager.this.mAssetPath, file);
                        if (assetToSD) {
                            try {
                                ARSourceCopyManager.f7469jn.put("assetsToSD", true);
                            } catch (JSONException e2) {
                                e2.printStackTrace();
                            }
                            assetToSD = file.renameTo(ARSourceCopyManager.this.mSdcardPath);
                        }
                        if (!assetToSD) {
                            try {
                                ARSourceCopyManager.f7469jn.put("renameTo-del", true);
                            } catch (JSONException e3) {
                                e3.printStackTrace();
                            }
                            FileUtil.deleteDir(file);
                            if (ARSourceCopyManager.this.mSdcardPath.exists()) {
                                FileUtil.deleteDir(ARSourceCopyManager.this.mSdcardPath);
                            }
                        }
                        try {
                            ARSourceCopyManager.f7469jn.put("result", assetToSD);
                        } catch (JSONException e4) {
                            e4.printStackTrace();
                        }
                        return Boolean.valueOf(assetToSD);
                    }
                    try {
                        ARSourceCopyManager.f7469jn.put("exist", true);
                    } catch (JSONException e5) {
                        e5.printStackTrace();
                    }
                    return true;
                }
                try {
                    ARSourceCopyManager.f7469jn.put("sdcardPath", "null");
                } catch (JSONException e6) {
                    e6.printStackTrace();
                }
                return false;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Boolean bool) {
                super.onPostExecute((AsyncTaskC29691) bool);
                ARSourceCopyManager.this.setState(bool.booleanValue() ? 2 : 3);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
    }
}
