package com.p319ss.android.downloadlib.addownload.compliance;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Pair;
import com.p319ss.android.download.api.config.InterfaceC9802l;
import com.p319ss.android.download.api.download.DownloadModel;
import com.p319ss.android.downloadlib.C9992ko;
import com.p319ss.android.downloadlib.activity.TTDelegateActivity;
import com.p319ss.android.downloadlib.addownload.C9878h;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.addownload.model.C9916h;
import com.p319ss.android.downloadlib.addownload.model.C9922ox;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.exception.C9971b;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.downloadlib.utils.Chain;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadlib.addownload.compliance.ox */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9873ox {

    /* renamed from: mb */
    private SoftReference<Activity> f18969mb;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.addownload.compliance.ox$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C9877mb {

        /* renamed from: mb */
        private static C9873ox f18978mb = new C9873ox();
    }

    /* renamed from: mb */
    public static C9873ox m7653mb() {
        return C9877mb.f18978mb;
    }

    private C9873ox() {
    }

    /* renamed from: mb */
    public void m7652mb(long j) {
        TTDelegateActivity.m7723mb(j);
    }

    /* renamed from: mb */
    public boolean m7649mb(DownloadModel downloadModel) {
        if (downloadModel.isAd() && C9940x.m7364lz().optInt("ad_lp_show_app_dialog") != 0) {
            String webUrl = downloadModel.getDeepLink() == null ? null : downloadModel.getDeepLink().getWebUrl();
            return (TextUtils.isEmpty(webUrl) || Pattern.compile(C9940x.m7364lz().optString("ad_allow_web_url_regex", ".+(www.chengzijianzhan.com|www.toutiaopage.com/tetris/page|ad.toutiao.com/tetris/page).+")).matcher(webUrl).matches()) ? false : true;
        }
        return false;
    }

    /* renamed from: mb */
    public boolean m7647mb(@NonNull C9916h c9916h) {
        long j;
        long j2;
        if (!TextUtils.isEmpty(c9916h.f19103ox.getLogExtra())) {
            try {
                j = C10050jb.m7041mb(new JSONObject(c9916h.f19103ox.getLogExtra()), "convert_id");
            } catch (Exception e) {
                e.printStackTrace();
                j = 0;
            }
            if (j <= 0) {
                C9860h.m7671mb(3, c9916h);
            }
            j2 = j;
        } else {
            C9860h.m7671mb(9, c9916h);
            C9971b.m7285mb().m7284mb("requestAppInfo getLogExtra null");
            j2 = 0;
        }
        final long j3 = c9916h.f19102mb;
        C9922ox m7675mb = C9857b.m7677mb().m7675mb(j2, j3);
        if (m7675mb != null) {
            C9861hj.m7666mb().m7664mb(m7675mb.m7457mb(), j3, m7675mb.f19124hj);
            m7652mb(m7675mb.m7457mb());
            C9860h.m7669mb("lp_app_dialog_try_show", c9916h);
            return true;
        }
        StringBuilder sb = new StringBuilder();
        if (j2 > 0) {
            sb.append("convert_id=");
            sb.append(j2);
        }
        if (!TextUtils.isEmpty(c9916h.f19103ox.getPackageName())) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append("package_name=");
            sb.append(c9916h.f19103ox.getPackageName());
        }
        if (sb.length() <= 0) {
            C9860h.m7671mb(6, c9916h);
            return false;
        }
        final long j4 = j2;
        Chain.m7093mb((Chain.InterfaceC10046mb<String, R>) new Chain.InterfaceC10046mb<String, Boolean>() { // from class: com.ss.android.downloadlib.addownload.compliance.ox.2
            @Override // com.p319ss.android.downloadlib.utils.Chain.InterfaceC10046mb
            /* renamed from: mb  reason: avoid collision after fix types in other method */
            public Boolean mo7091mb(String str) {
                final boolean[] zArr = {false};
                C9940x.m7371hj().mo7932mb("GET", str, new HashMap(), new InterfaceC9802l() { // from class: com.ss.android.downloadlib.addownload.compliance.ox.2.1
                    @Override // com.p319ss.android.download.api.config.InterfaceC9802l
                    /* renamed from: mb */
                    public void mo6999mb(String str2) {
                        zArr[0] = C9873ox.this.m7651mb(j4, j3, str2);
                    }

                    @Override // com.p319ss.android.download.api.config.InterfaceC9802l
                    /* renamed from: mb */
                    public void mo6998mb(Throwable th) {
                        C9860h.m7672mb(2, j3);
                        zArr[0] = false;
                    }
                });
                return Boolean.valueOf(zArr[0]);
            }
        }, "https://apps.oceanengine.com/customer/api/app/pkg_info?" + sb.toString()).m7094mb(new Chain.InterfaceC10046mb<Boolean, Object>() { // from class: com.ss.android.downloadlib.addownload.compliance.ox.1
            @Override // com.p319ss.android.downloadlib.utils.Chain.InterfaceC10046mb
            /* renamed from: mb  reason: avoid collision after fix types in other method */
            public Object mo7091mb(Boolean bool) {
                if (bool.booleanValue()) {
                    C9873ox.this.m7652mb(C9922ox.m7456mb(j4, j3));
                    C9860h.m7667ox("lp_app_dialog_try_show", j3);
                    return null;
                }
                C9873ox.this.m7645ox(j3);
                return null;
            }
        }).m7096mb();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: mb */
    public boolean m7651mb(long j, long j2, String str) {
        try {
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("package");
            if (optJSONObject != null && optJSONObject.length() != 0) {
                C9922ox c9922ox = new C9922ox();
                c9922ox.f19127mb = j;
                c9922ox.f19128ox = j2;
                c9922ox.f19124hj = optJSONObject.optString("icon_url");
                c9922ox.f19123h = optJSONObject.optString("app_name");
                c9922ox.f19122b = optJSONObject.optString("package_name");
                c9922ox.f19129u = optJSONObject.optString("version_name");
                c9922ox.f19125ko = optJSONObject.optString("developer_name");
                c9922ox.f19126lz = optJSONObject.optString("policy_url");
                JSONArray optJSONArray = optJSONObject.optJSONArray("permissions");
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject jSONObject = (JSONObject) optJSONArray.get(i);
                        c9922ox.f19130ww.add(new Pair<>(jSONObject.optString("permission_name"), jSONObject.optString("permission_desc")));
                    }
                }
                C9857b.m7677mb().m7674mb(c9922ox);
                C9861hj.m7666mb().m7664mb(c9922ox.m7457mb(), j2, c9922ox.f19124hj);
                return true;
            }
            C9860h.m7672mb(7, j2);
            return false;
        } catch (Exception e) {
            C9971b.m7285mb().mo7282mb(e, "AdLpComplianceManager parseResponse");
            C9860h.m7672mb(7, j2);
            return false;
        }
    }

    /* renamed from: ox */
    public void m7645ox(long j) {
        C9878h m7228mb = C9992ko.m7236mb().m7228mb(C9923u.m7451mb().m7453h(j).f19103ox.getDownloadUrl());
        if (m7228mb != null) {
            m7228mb.m7611mb(true, true);
            return;
        }
        C9860h.m7672mb(11, j);
        C9971b.m7285mb().m7278ox("startDownload handler null");
    }

    /* renamed from: mb */
    public void m7650mb(Activity activity) {
        this.f18969mb = new SoftReference<>(activity);
    }

    /* renamed from: ox */
    public Activity m7646ox() {
        Activity activity = this.f18969mb.get();
        this.f18969mb = null;
        return activity;
    }
}
