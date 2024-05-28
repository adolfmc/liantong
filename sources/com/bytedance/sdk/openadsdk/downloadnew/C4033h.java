package com.bytedance.sdk.openadsdk.downloadnew;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.bykv.p167vk.openvk.api.proto.Bridge;
import com.bykv.p167vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.api.C3969b;
import com.bytedance.sdk.openadsdk.downloadnew.core.ExitInstallListener;
import com.bytedance.sdk.openadsdk.downloadnew.core.ITTDownloadAdapter;
import com.p319ss.android.download.api.config.IDownloadButtonClickListener;
import com.p319ss.android.download.api.config.OnItemClickListener;
import com.p319ss.android.download.api.download.DownloadController;
import com.p319ss.android.download.api.download.DownloadEventConfig;
import com.p319ss.android.download.api.download.DownloadModel;
import com.p319ss.android.download.api.download.DownloadStatusChangeListener;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.bytedance.sdk.openadsdk.downloadnew.h */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C4033h extends AbstractC4052mb implements Bridge {

    /* renamed from: mb */
    private static volatile C4033h f9634mb;

    /* renamed from: ox */
    private final Context f9635ox;

    /* renamed from: mb */
    private static boolean m16412mb(IDownloadButtonClickListener iDownloadButtonClickListener) {
        return iDownloadButtonClickListener != null;
    }

    private C4033h(Context context) {
        this.f9635ox = context;
    }

    /* renamed from: mb */
    public static C4033h m16413mb(Context context) {
        if (f9634mb == null) {
            synchronized (C4033h.class) {
                if (f9634mb == null) {
                    f9634mb = new C4033h(context);
                }
            }
        }
        return f9634mb;
    }

    @Override // com.bytedance.sdk.openadsdk.downloadnew.AbstractC4052mb, com.bytedance.sdk.openadsdk.TTAdBridge
    public <T> T callMethod(Class<T> cls, int i, Map<String, Object> map) {
        switch (i) {
            case 0:
                return (T) Boolean.valueOf(C4034hj.m16401mb(getActivity(map.get("activity")), m16416h(map.get("exitInstallListener"))));
            case 1:
                return (T) C4034hj.m16404mb().m6946u();
            case 2:
                try {
                    return (T) Boolean.valueOf(C4034hj.m16392mb((String) map.get("tagIntercept"), (String) map.get("label"), new JSONObject((String) map.get("meta")), new HashMap()));
                } catch (JSONException unused) {
                    return (T) Boolean.FALSE;
                }
            case 3:
                C4034hj.m16403mb(((Integer) map.get("hid")).intValue());
                return null;
            case 4:
                int intValue = ((Integer) map.get("hashCode")).intValue();
                C4034hj.m16404mb().m6955mb((String) map.get("downloadUrl"), intValue);
                return null;
            case 5:
                int intValue2 = ((Integer) map.get("hashCode")).intValue();
                DownloadModel m16410ox = m16410ox(map.get("downloadModel"));
                C4034hj.m16404mb().m6959mb(this.f9635ox, intValue2, m16411mb(map.get("downloadStatusChangeListener")), m16410ox);
                return null;
            case 6:
                return (T) Boolean.valueOf(C4034hj.m16397mb(this.f9635ox, (String) map.get("downloadUrl")));
            case 7:
                C4034hj.m16391ox();
                return null;
            case 8:
                boolean booleanValue = ((Boolean) map.get("force")).booleanValue();
                C4034hj.m16404mb().m6951mb((String) map.get("downloadUrl"), booleanValue);
                return null;
            case 9:
                C4034hj.m16402mb(((Integer) map.get("id")).intValue(), (ITTDownloadAdapter.OnEventLogHandler) map.get("onEventLogHandler"));
                return null;
            case 10:
                C4034hj.m16393mb((String) map.get("downloadPath"));
                return null;
            case 11:
            default:
                return (T) super.callMethod(cls, i, map);
            case 12:
                Uri uri = (Uri) map.get("uri");
                DownloadModel m16410ox2 = m16410ox(map.get("downloadModel"));
                DownloadEventConfig m16417b = m16417b(map.get("downloadEventConfig"));
                DownloadController m16415hj = m16415hj(map.get("downloadController"));
                IDownloadButtonClickListener m16414ko = m16414ko(map.get("downloadButtonClickListener"));
                if (m16412mb(m16414ko)) {
                    return (T) Boolean.valueOf(C4034hj.m16398mb(this.f9635ox, uri, m16410ox2, m16417b, m16415hj, m16414ko));
                }
                return (T) Boolean.valueOf(C4034hj.m16399mb(this.f9635ox, uri, m16410ox2, m16417b, m16415hj));
            case 13:
                int intValue3 = ((Integer) map.get("hashCode")).intValue();
                boolean booleanValue2 = ((Boolean) map.get("isDisableDialog")).booleanValue();
                String str = (String) map.get("userAgent");
                DownloadModel m16410ox3 = m16410ox(map.get("downloadModel"));
                DownloadEventConfig m16417b2 = m16417b(map.get("downloadEventConfig"));
                DownloadController m16415hj2 = m16415hj(map.get("downloadController"));
                DownloadStatusChangeListener m16411mb = m16411mb(map.get("downloadStatusChangeListener"));
                IDownloadButtonClickListener m16414ko2 = m16414ko(map.get("downloadButtonClickListener"));
                if (m16412mb(m16414ko2)) {
                    C4034hj.m16404mb().m6964h().mo7152mb(this.f9635ox, str, booleanValue2, m16410ox3, m16417b2, m16415hj2, m16411mb, intValue3, m16414ko2);
                    return null;
                }
                C4034hj.m16404mb().m6964h().mo7153mb(this.f9635ox, str, booleanValue2, m16410ox3, m16417b2, m16415hj2, m16411mb, intValue3);
                return null;
            case 14:
                return (T) Boolean.valueOf(C4034hj.m16404mb().m6964h().mo7156mb(this.f9635ox, ((Long) map.get("id")).longValue(), (String) map.get("logExtra"), (DownloadStatusChangeListener) null, ((Integer) map.get("hashCode")).intValue()));
            case 15:
                return (T) Boolean.valueOf(C4034hj.m16395mb((Uri) map.get("uri")));
            case 16:
                C4034hj.m16404mb().m6954mb((String) map.get("downloadUrl"), ((Long) map.get("id")).longValue(), ((Integer) map.get("action_type_button")).intValue(), m16417b(map.get("downloadEventConfig")), m16415hj(map.get("downloadController")));
                return null;
            case 17:
                C4034hj.m16404mb().m6952mb((String) map.get("downloadUrl"), ((Long) map.get("id")).longValue(), ((Integer) map.get("action_type_button")).intValue(), m16417b(map.get("downloadEventConfig")), m16415hj(map.get("downloadController")), m16409u(map.get("itemClickListener")), m16414ko(map.get("downloadButtonClickListener")));
                return null;
            case 18:
                return (T) Boolean.valueOf(C4034hj.m16404mb().m6964h().mo7158mb(((Long) map.get("id")).longValue(), ((Integer) map.get("hashCode")).intValue()));
            case 19:
                return (T) Boolean.valueOf(C4034hj.m16404mb().m6964h().mo7159mb(((Long) map.get("id")).longValue()));
        }
    }

    @Override // com.bytedance.sdk.openadsdk.downloadnew.AbstractC4052mb, com.bytedance.sdk.openadsdk.TTAdBridge
    public <T> T getObj(Class<T> cls, int i, Map<String, Object> map) {
        switch (i) {
            case 0:
                return (T) C4034hj.f9640mb;
            case 1:
                return (T) Boolean.valueOf(C4034hj.f9641ox);
            default:
                return (T) super.getObj(cls);
        }
    }

    @Override // com.bytedance.sdk.openadsdk.downloadnew.AbstractC4052mb, com.bytedance.sdk.openadsdk.TTAdBridge
    public void init(Bundle bundle) {
        super.init(bundle);
        C4034hj.m16400mb(this.f9635ox);
    }

    /* renamed from: mb */
    private DownloadStatusChangeListener m16411mb(Object obj) {
        if (obj instanceof DownloadStatusChangeListener) {
            return (DownloadStatusChangeListener) obj;
        }
        return null;
    }

    /* renamed from: ox */
    private DownloadModel m16410ox(Object obj) {
        if (obj instanceof DownloadModel) {
            return (DownloadModel) obj;
        }
        return null;
    }

    /* renamed from: b */
    private DownloadEventConfig m16417b(Object obj) {
        if (obj instanceof DownloadEventConfig) {
            return (DownloadEventConfig) obj;
        }
        return null;
    }

    /* renamed from: hj */
    private DownloadController m16415hj(Object obj) {
        if (obj instanceof DownloadController) {
            return (DownloadController) obj;
        }
        return null;
    }

    private Activity getActivity(Object obj) {
        if (obj instanceof Activity) {
            return (Activity) obj;
        }
        return null;
    }

    /* renamed from: h */
    private ExitInstallListener m16416h(Object obj) {
        if (obj instanceof ExitInstallListener) {
            return (ExitInstallListener) obj;
        }
        return null;
    }

    /* renamed from: u */
    private OnItemClickListener m16409u(Object obj) {
        if (obj instanceof OnItemClickListener) {
            return (OnItemClickListener) obj;
        }
        return null;
    }

    /* renamed from: ko */
    private IDownloadButtonClickListener m16414ko(Object obj) {
        if (obj instanceof IDownloadButtonClickListener) {
            return (IDownloadButtonClickListener) obj;
        }
        return null;
    }

    @Override // com.bykv.p167vk.openvk.api.proto.Bridge
    public ValueSet values() {
        return C3969b.m16559mb().m16557mb(0, C4034hj.f9640mb).m16558mb(1, Boolean.valueOf(C4034hj.f9641ox)).m16555ox();
    }

    @Override // com.bykv.p167vk.openvk.api.proto.Caller
    public <T> T call(int i, ValueSet valueSet, Class<T> cls) {
        if (i == 20) {
            init((Bundle) valueSet.objectValue(0, Bundle.class));
            return null;
        }
        return (T) callMethod(cls, i, (Map) valueSet.objectValue(0, Map.class));
    }
}
