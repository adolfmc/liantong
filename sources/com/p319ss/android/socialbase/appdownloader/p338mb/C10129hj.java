package com.p319ss.android.socialbase.appdownloader.p338mb;

import android.content.Context;
import android.text.TextUtils;
import com.p319ss.android.socialbase.appdownloader.C10085b;
import com.p319ss.android.socialbase.appdownloader.C10112hj;
import com.p319ss.android.socialbase.appdownloader.p340u.C10152hj;
import com.p319ss.android.socialbase.downloader.depend.IDownloadFileUriProvider;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import java.io.File;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.appdownloader.mb.hj */
/* loaded from: E:\567196_dexfile_execute.dex */
public class C10129hj {
    /* renamed from: mb */
    public static AbstractC10134mb m6733mb(Context context, String str, JSONObject jSONObject, DownloadInfo downloadInfo) {
        if (downloadInfo == null || context == null || jSONObject == null) {
            return null;
        }
        String savePath = downloadInfo.getSavePath();
        if (TextUtils.isEmpty(savePath) || TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(savePath);
        DownloadSetting obtain = DownloadSetting.obtain(downloadInfo);
        if (str.equals("v1")) {
            return new C10139x(context, obtain, downloadInfo.getTargetFilePath());
        }
        if (str.equals("v2")) {
            return new C10130jb(context, obtain, file.getAbsolutePath());
        }
        if (str.equals("v3")) {
            return new C10131je(context, obtain, file.getAbsolutePath());
        }
        if (str.equals("o1")) {
            return new C10132ko(context, obtain, file.getAbsolutePath());
        }
        if (str.equals("o2")) {
            return new C10138ww(context, obtain, file.getAbsolutePath());
        }
        if (str.equals("o3")) {
            String dBJsonString = downloadInfo.getDBJsonString("file_content_uri");
            if (TextUtils.isEmpty(dBJsonString)) {
                return null;
            }
            return new C10133lz(context, obtain, file.getAbsolutePath(), dBJsonString, downloadInfo.getName());
        } else if (str.equals("custom")) {
            return new C10127b(context, obtain, file.getAbsolutePath(), jSONObject);
        } else {
            if (str.equals("vbi")) {
                IDownloadFileUriProvider downloadFileUriProvider = Downloader.getInstance(context).getDownloadFileUriProvider(downloadInfo.getId());
                String m6822hj = C10112hj.m6786x().m6822hj();
                return new C10135nk(context, obtain, C10085b.m6925mb(downloadInfo.getId(), downloadFileUriProvider, context, m6822hj, new File(downloadInfo.getSavePath() + File.separator + downloadInfo.getName())).toString());
            }
            return null;
        }
    }

    /* renamed from: mb */
    public static boolean m6732mb(Context context, String str, JSONObject jSONObject, DownloadSetting downloadSetting) {
        if (context == null || str == null) {
            return false;
        }
        AbstractC10134mb abstractC10134mb = null;
        String m6899ox = C10085b.m6899ox();
        if (TextUtils.isEmpty(m6899ox) || TextUtils.isEmpty(str)) {
            return false;
        }
        if (C10152hj.m6580b() && str.equals("v1")) {
            abstractC10134mb = new C10139x(context, downloadSetting, m6899ox);
        } else if (C10152hj.m6580b() && str.equals("v2")) {
            abstractC10134mb = new C10130jb(context, downloadSetting, m6899ox);
        } else if (C10152hj.m6580b() && str.equals("v3")) {
            abstractC10134mb = new C10131je(context, downloadSetting, m6899ox);
        } else if (C10152hj.m6577hj() && str.equals("o1")) {
            abstractC10134mb = new C10132ko(context, downloadSetting, m6899ox);
        } else if (C10152hj.m6577hj() && str.equals("o2")) {
            abstractC10134mb = new C10138ww(context, downloadSetting, m6899ox);
        } else if (C10152hj.m6577hj() && str.equals("o3")) {
            abstractC10134mb = new C10133lz(context, downloadSetting, m6899ox, m6899ox, m6899ox);
        } else if (C10152hj.m6580b() && str.equals("custom")) {
            abstractC10134mb = new C10127b(context, downloadSetting, m6899ox, jSONObject);
        } else if (C10152hj.m6580b() && str.equals("vbi")) {
            abstractC10134mb = new C10135nk(context, downloadSetting, m6899ox);
        }
        return abstractC10134mb != null && abstractC10134mb.m6731mb();
    }
}
