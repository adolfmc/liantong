package com.p319ss.android.downloadlib.addownload.model;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.p319ss.android.download.api.download.DownloadController;
import com.p319ss.android.download.api.download.DownloadEventConfig;
import com.p319ss.android.download.api.download.DownloadModel;
import com.p319ss.android.downloadad.api.download.AdDownloadController;
import com.p319ss.android.downloadad.api.download.AdDownloadEventConfig;
import com.p319ss.android.downloadad.api.download.AdDownloadModel;
import com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadlib.addownload.model.h */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9916h implements InterfaceC9836mb {

    /* renamed from: b */
    public DownloadEventConfig f19100b;

    /* renamed from: hj */
    public DownloadController f19101hj;

    /* renamed from: mb */
    public long f19102mb;

    /* renamed from: ox */
    public DownloadModel f19103ox;

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: m */
    public int mo7479m() {
        return 0;
    }

    public C9916h() {
    }

    public C9916h(long j, @NonNull DownloadModel downloadModel, @NonNull DownloadEventConfig downloadEventConfig, @NonNull DownloadController downloadController) {
        this.f19102mb = j;
        this.f19103ox = downloadModel;
        this.f19100b = downloadEventConfig;
        this.f19101hj = downloadController;
    }

    /* renamed from: on */
    public boolean m7475on() {
        DownloadModel downloadModel;
        if (this.f19102mb == 0 || (downloadModel = this.f19103ox) == null || this.f19100b == null || this.f19101hj == null) {
            return true;
        }
        return downloadModel.isAd() && this.f19102mb <= 0;
    }

    /* renamed from: jq */
    public boolean m7484jq() {
        if (m7475on()) {
            return false;
        }
        if (this.f19103ox.isAd()) {
            DownloadModel downloadModel = this.f19103ox;
            return (downloadModel instanceof AdDownloadModel) && !TextUtils.isEmpty(downloadModel.getLogExtra()) && (this.f19100b instanceof AdDownloadEventConfig) && (this.f19101hj instanceof AdDownloadController);
        }
        return this.f19103ox instanceof AdDownloadModel;
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: mb */
    public String mo7478mb() {
        return this.f19103ox.getDownloadUrl();
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: ox */
    public long mo7474ox() {
        return this.f19103ox.getId();
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: b */
    public boolean mo7494b() {
        return this.f19103ox.isAd();
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: hj */
    public String mo7488hj() {
        return this.f19103ox.getLogExtra();
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: h */
    public String mo7489h() {
        return this.f19103ox.getPackageName();
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: u */
    public String mo7472u() {
        if (this.f19103ox.getDeepLink() != null) {
            return this.f19103ox.getDeepLink().getOpenUrl();
        }
        return null;
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: ko */
    public JSONObject mo7483ko() {
        return this.f19103ox.getExtra();
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: ww */
    public int mo7471ww() {
        if (this.f19101hj.getDownloadMode() == 2) {
            return 2;
        }
        return this.f19103ox.getFunnelType();
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: je */
    public long mo7485je() {
        return this.f19103ox.getExtraValue();
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: o */
    public List<String> mo7476o() {
        return this.f19103ox.getClickTrackUrl();
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: lz */
    public String mo7480lz() {
        return this.f19100b.getRefer();
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: x */
    public String mo7470x() {
        return this.f19100b.getClickButtonTag();
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: jb */
    public JSONObject mo7486jb() {
        return this.f19100b.getParamsJson();
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: nk */
    public boolean mo7477nk() {
        return this.f19100b.isEnableV3Event();
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: lc */
    public Object mo7481lc() {
        return this.f19100b.getExtraEventObject();
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: io */
    public JSONObject mo7487io() {
        return this.f19100b.getExtraJson();
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: e */
    public boolean mo7492e() {
        return this.f19101hj.enableNewActivity();
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: l */
    public JSONObject mo7482l() {
        return this.f19103ox.getDownloadSettings();
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: g */
    public DownloadModel mo7491g() {
        return this.f19103ox;
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: df */
    public DownloadEventConfig mo7493df() {
        return this.f19100b;
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: r */
    public DownloadController mo7473r() {
        return this.f19101hj;
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: gm */
    public int mo7490gm() {
        return this.f19100b.getDownloadScene();
    }
}
