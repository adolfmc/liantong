package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.wechat.utils.WXMediaMessage;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SendMessageReq extends WechatReq {

    /* renamed from: a */
    public WXMediaMessage f3243a;

    /* renamed from: b */
    public int f3244b;

    /* renamed from: c */
    public String f3245c;

    /* renamed from: d */
    public IWXSceneDataObject f3246d;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface IWXSceneDataObject {
        boolean checkArgs();

        int getJumpType();

        void serialize(Bundle bundle);

        void unserialize(Bundle bundle);
    }

    @Override // cn.sharesdk.wechat.utils.WechatReq
    /* renamed from: a */
    public int mo21237a() {
        return 2;
    }

    @Override // cn.sharesdk.wechat.utils.WechatReq
    /* renamed from: a */
    public void mo21236a(Bundle bundle) {
        super.mo21236a(bundle);
        this.f3243a = WXMediaMessage.C1869a.m21322a(bundle);
        this.f3244b = bundle.getInt("_wxapi_sendmessagetowx_req_scene");
        this.f3245c = bundle.getString("_wxapi_sendmessagetowx_req_use_open_id");
        if (bundle.getString("_scene_data_object_identifie") != null) {
            try {
                this.f3246d = (IWXSceneDataObject) Class.forName(bundle.getString("_scene_data_object_identifie")).newInstance();
                this.f3246d.unserialize(bundle);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (InstantiationException e3) {
                e3.printStackTrace();
            }
        }
    }

    @Override // cn.sharesdk.wechat.utils.WechatReq
    /* renamed from: b */
    public void mo21234b(Bundle bundle) {
        super.mo21234b(bundle);
        bundle.putAll(WXMediaMessage.C1869a.m21321a(this.f3243a));
        bundle.putInt("_wxapi_sendmessagetowx_req_scene", this.f3244b);
        bundle.putInt("_wxapi_sendmessagetowx_req_media_type", this.f3243a.getType());
        bundle.putString("_wxapi_sendmessagetowx_req_use_open_id", this.f3245c);
        IWXSceneDataObject iWXSceneDataObject = this.f3246d;
        if (iWXSceneDataObject != null) {
            bundle.putString("_scene_data_object_identifier", iWXSceneDataObject.getClass().getName());
            this.f3246d.serialize(bundle);
        }
    }

    @Override // cn.sharesdk.wechat.utils.WechatReq
    /* renamed from: b */
    public boolean mo21235b() {
        int type = this.f3243a.getType();
        WXMediaMessage wXMediaMessage = this.f3243a;
        if (wXMediaMessage == null) {
            SSDKLog.m21740b().m21744a("MicroMsg.SDK.SendMessageToWX.Req", "checkArgs fail ,message is null");
            return false;
        }
        if (type == 6 && this.f3244b == 2) {
            ((WXFileObject) wXMediaMessage.mediaObject).setContentLengthLimit(26214400);
        }
        if (this.f3244b == 3 && this.f3245c == null) {
            SSDKLog.m21740b().m21744a("MicroMsg.SDK.SendMessageToWX.Req", "Send specifiedContact userOpenId can not be null.");
            return false;
        } else if (this.f3244b == 3 && this.f3299f == null) {
            SSDKLog.m21740b().m21744a("MicroMsg.SDK.SendMessageToWX.Req", "Send specifiedContact openid can not be null.");
            return false;
        } else if (this.f3244b != 4) {
            return this.f3243a.m21323a();
        } else {
            if (this.f3246d == null) {
                SSDKLog.m21740b().m21744a("MicroMsg.SDK.SendMessageToWX.Req", "checkArgs fail, sceneDataObject is null");
                return false;
            } else if (this.f3243a.getType() == 1) {
                return this.f3246d.checkArgs();
            } else {
                return this.f3243a.m21323a() && this.f3246d.checkArgs();
            }
        }
    }
}
