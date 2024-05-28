package com.p319ss.android.downloadlib.addownload;

import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import com.p319ss.android.socialbase.downloader.downloader.IReserveWifiStatusListener;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadlib.addownload.jb */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9895jb implements IReserveWifiStatusListener {
    @Override // com.p319ss.android.socialbase.downloader.downloader.IReserveWifiStatusListener
    public void onStatusChanged(DownloadInfo downloadInfo, int i, int i2) {
        C9837ox m7442mb = C9923u.m7451mb().m7442mb(downloadInfo);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("reserve_wifi_source", Integer.valueOf(i2));
            jSONObject.putOpt("reserve_wifi_status", Integer.valueOf(i));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AdEventHandler.m7315mb().m7298mb("pause_reserve_wifi", jSONObject, m7442mb);
    }
}
