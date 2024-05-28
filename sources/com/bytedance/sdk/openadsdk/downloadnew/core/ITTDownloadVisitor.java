package com.bytedance.sdk.openadsdk.downloadnew.core;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import com.bytedance.sdk.openadsdk.TTDownloadEventLogger;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ITTDownloadVisitor {
    public static final int TYPE_TT_DOWNLOAD_VISITOR = 1;

    void checkAutoControl(JSONObject jSONObject, String str);

    void clearAllData(String str);

    void execute(int i, String str, Map<String, Object> map, ITTHttpCallback iTTHttpCallback);

    void executeLogUpload(TTDownloadEventModel tTDownloadEventModel, boolean z);

    boolean getAppIsBackground();

    JSONObject getDownloadSettings();

    TTDownloadEventLogger getTTDownloadEventLogger();

    boolean hasPermission(Context context, String str);

    String initPath(boolean z);

    boolean isOpenSdkEvent(String str);

    void postBody(String str, byte[] bArr, String str2, ITTHttpCallback iTTHttpCallback);

    void requestPermission(Activity activity, String[] strArr, ITTPermissionCallback iTTPermissionCallback);

    void showDialogByDelegate(WeakReference<Context> weakReference, boolean z, DialogBuilder dialogBuilder);

    AlertDialog showDialogBySelf(Activity activity, boolean z, DialogBuilder dialogBuilder);
}
