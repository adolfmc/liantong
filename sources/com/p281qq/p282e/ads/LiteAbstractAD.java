package com.p281qq.p282e.ads;

import android.app.Activity;
import com.p281qq.p282e.comm.compliance.DownloadConfirmCallBack;
import com.p281qq.p282e.comm.compliance.DownloadConfirmListener;
import com.p281qq.p282e.comm.p283pi.LADI;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.qq.e.ads.LiteAbstractAD */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public abstract class LiteAbstractAD<T extends LADI> extends AbstractAD<T> implements DownloadConfirmListener, LADI {

    /* renamed from: f */
    private DownloadConfirmListener f17752f;

    @Override // com.p281qq.p282e.comm.compliance.ApkDownloadComplianceInterface
    public String getApkInfoUrl() {
        T t = this.f17738a;
        if (t != 0) {
            return ((LADI) t).getApkInfoUrl();
        }
        m8340a("getApkInfoUrl");
        return null;
    }

    @Override // com.p281qq.p282e.comm.p283pi.LADI
    public int getECPM() {
        T t = this.f17738a;
        if (t != 0) {
            return ((LADI) t).getECPM();
        }
        m8340a("getECPM");
        return -1;
    }

    @Override // com.p281qq.p282e.comm.p283pi.LADI
    public String getECPMLevel() {
        T t = this.f17738a;
        if (t != 0) {
            return ((LADI) t).getECPMLevel();
        }
        m8340a("getECPMLevel");
        return null;
    }

    @Override // com.p281qq.p282e.comm.p283pi.LADI
    public Map<String, Object> getExtraInfo() {
        T t = this.f17738a;
        if (t != 0) {
            return ((LADI) t).getExtraInfo();
        }
        m8340a("getExtraInfo");
        return new HashMap();
    }

    @Override // com.p281qq.p282e.comm.p283pi.LADI
    public boolean isValid() {
        T t = this.f17738a;
        if (t != 0) {
            return ((LADI) t).isValid();
        }
        m8340a("isValid");
        return false;
    }

    @Override // com.p281qq.p282e.comm.compliance.DownloadConfirmListener
    public void onDownloadConfirm(Activity activity, int i, String str, DownloadConfirmCallBack downloadConfirmCallBack) {
        DownloadConfirmListener downloadConfirmListener = this.f17752f;
        if (downloadConfirmListener != null) {
            downloadConfirmListener.onDownloadConfirm(activity, i, str, downloadConfirmCallBack);
        }
    }

    @Override // com.p281qq.p282e.comm.p283pi.IBidding
    public void sendLossNotification(int i, int i2, String str) {
        T t = this.f17738a;
        if (t != 0) {
            ((LADI) t).sendLossNotification(i, i2, str);
        } else {
            m8340a("sendLossNotification");
        }
    }

    @Override // com.p281qq.p282e.comm.p283pi.IBidding
    public void sendLossNotification(Map<String, Object> map) {
        T t = this.f17738a;
        if (t != 0) {
            ((LADI) t).sendLossNotification(map);
        } else {
            m8340a("sendLossNotification");
        }
    }

    @Override // com.p281qq.p282e.comm.p283pi.IBidding
    public void sendWinNotification(int i) {
        T t = this.f17738a;
        if (t != 0) {
            ((LADI) t).sendWinNotification(i);
        } else {
            m8340a("sendWinNotification");
        }
    }

    @Override // com.p281qq.p282e.comm.p283pi.IBidding
    public void sendWinNotification(Map<String, Object> map) {
        T t = this.f17738a;
        if (t != 0) {
            ((LADI) t).sendWinNotification(map);
        } else {
            m8340a("sendWinNotification");
        }
    }

    @Override // com.p281qq.p282e.comm.p283pi.IBidding
    public void setBidECPM(int i) {
        T t = this.f17738a;
        if (t != 0) {
            ((LADI) t).setBidECPM(i);
        } else {
            m8340a("setBidECPM");
        }
    }

    @Override // com.p281qq.p282e.comm.compliance.ApkDownloadComplianceInterface
    public void setDownloadConfirmListener(DownloadConfirmListener downloadConfirmListener) {
        this.f17752f = downloadConfirmListener;
        T t = this.f17738a;
        if (t != 0) {
            ((LADI) t).setDownloadConfirmListener(this);
        }
    }
}
