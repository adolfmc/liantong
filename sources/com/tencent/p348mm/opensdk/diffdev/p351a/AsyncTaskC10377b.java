package com.tencent.p348mm.opensdk.diffdev.p351a;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Base64;
import com.tencent.p348mm.opensdk.channel.p350a.C10372a;
import com.tencent.p348mm.opensdk.diffdev.OAuthErrCode;
import com.tencent.p348mm.opensdk.diffdev.OAuthListener;
import com.tencent.p348mm.opensdk.utils.C10384Log;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.diffdev.a.b */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class AsyncTaskC10377b extends AsyncTask<Void, Void, C10378a> {

    /* renamed from: a */
    private String f19958a;

    /* renamed from: b */
    private String f19959b;

    /* renamed from: c */
    private String f19960c;

    /* renamed from: d */
    private String f19961d;

    /* renamed from: e */
    private String f19962e;

    /* renamed from: f */
    private OAuthListener f19963f;

    /* renamed from: g */
    private AsyncTaskC10379c f19964g;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.diffdev.a.b$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class C10378a {

        /* renamed from: a */
        public OAuthErrCode f19965a;

        /* renamed from: b */
        public String f19966b;

        /* renamed from: c */
        public String f19967c;

        /* renamed from: d */
        public String f19968d;

        /* renamed from: e */
        public byte[] f19969e;

        private C10378a() {
        }

        /* renamed from: a */
        public static C10378a m6218a(byte[] bArr) {
            OAuthErrCode oAuthErrCode;
            Object[] objArr;
            String str;
            C10378a c10378a = new C10378a();
            if (bArr != null && bArr.length != 0) {
                try {
                } catch (Exception e) {
                    objArr = new Object[]{e.getMessage()};
                    str = "parse fail, build String fail, ex = %s";
                }
                try {
                    JSONObject jSONObject = new JSONObject(new String(bArr, "utf-8"));
                    int i = jSONObject.getInt("errcode");
                    if (i != 0) {
                        C10384Log.m6210e("MicroMsg.SDK.GetQRCodeResult", String.format("resp errcode = %d", Integer.valueOf(i)));
                        c10378a.f19965a = OAuthErrCode.WechatAuth_Err_NormalErr;
                        jSONObject.optString("errmsg");
                        return c10378a;
                    }
                    String string = jSONObject.getJSONObject("qrcode").getString("qrcodebase64");
                    if (string != null && string.length() != 0) {
                        byte[] decode = Base64.decode(string, 0);
                        if (decode != null && decode.length != 0) {
                            c10378a.f19965a = OAuthErrCode.WechatAuth_Err_OK;
                            c10378a.f19969e = decode;
                            c10378a.f19966b = jSONObject.getString("uuid");
                            String string2 = jSONObject.getString("appname");
                            c10378a.f19967c = string2;
                            C10384Log.m6211d("MicroMsg.SDK.GetQRCodeResult", String.format("parse succ, save in memory, uuid = %s, appname = %s, imgBufLength = %d", c10378a.f19966b, string2, Integer.valueOf(c10378a.f19969e.length)));
                            return c10378a;
                        }
                        C10384Log.m6210e("MicroMsg.SDK.GetQRCodeResult", "parse fail, qrcodeBuf is null");
                        c10378a.f19965a = OAuthErrCode.WechatAuth_Err_JsonDecodeErr;
                        return c10378a;
                    }
                    C10384Log.m6210e("MicroMsg.SDK.GetQRCodeResult", "parse fail, qrcodeBase64 is null");
                    c10378a.f19965a = OAuthErrCode.WechatAuth_Err_JsonDecodeErr;
                    return c10378a;
                } catch (Exception e2) {
                    objArr = new Object[]{e2.getMessage()};
                    str = "parse json fail, ex = %s";
                    C10384Log.m6210e("MicroMsg.SDK.GetQRCodeResult", String.format(str, objArr));
                    oAuthErrCode = OAuthErrCode.WechatAuth_Err_NormalErr;
                    c10378a.f19965a = oAuthErrCode;
                    return c10378a;
                }
            }
            C10384Log.m6210e("MicroMsg.SDK.GetQRCodeResult", "parse fail, buf is null");
            oAuthErrCode = OAuthErrCode.WechatAuth_Err_NetworkErr;
            c10378a.f19965a = oAuthErrCode;
            return c10378a;
        }
    }

    public AsyncTaskC10377b(String str, String str2, String str3, String str4, String str5, OAuthListener oAuthListener) {
        this.f19958a = str;
        this.f19959b = str2;
        this.f19960c = str3;
        this.f19961d = str4;
        this.f19962e = str5;
        this.f19963f = oAuthListener;
    }

    /* renamed from: a */
    public boolean m6219a() {
        C10384Log.m6209i("MicroMsg.SDK.GetQRCodeTask", "cancelTask");
        AsyncTaskC10379c asyncTaskC10379c = this.f19964g;
        return asyncTaskC10379c == null ? cancel(true) : asyncTaskC10379c.cancel(true);
    }

    @Override // android.os.AsyncTask
    protected C10378a doInBackground(Void[] voidArr) {
        Thread.currentThread().setName("OpenSdkGetQRCodeTask");
        C10384Log.m6209i("MicroMsg.SDK.GetQRCodeTask", "doInBackground");
        String format = String.format("https://open.weixin.qq.com/connect/sdk/qrconnect?appid=%s&noncestr=%s&timestamp=%s&scope=%s&signature=%s", this.f19958a, this.f19960c, this.f19961d, this.f19959b, this.f19962e);
        long currentTimeMillis = System.currentTimeMillis();
        byte[] m6224a = C10372a.m6224a(format, 60000);
        C10384Log.m6211d("MicroMsg.SDK.GetQRCodeTask", String.format("doInBackground, url = %s, time consumed = %d(ms)", format, Long.valueOf(System.currentTimeMillis() - currentTimeMillis)));
        return C10378a.m6218a(m6224a);
    }

    @Override // android.os.AsyncTask
    protected void onPostExecute(C10378a c10378a) {
        C10378a c10378a2 = c10378a;
        OAuthErrCode oAuthErrCode = c10378a2.f19965a;
        if (oAuthErrCode != OAuthErrCode.WechatAuth_Err_OK) {
            C10384Log.m6210e("MicroMsg.SDK.GetQRCodeTask", String.format("onPostExecute, get qrcode fail, OAuthErrCode = %s", oAuthErrCode));
            this.f19963f.onAuthFinish(c10378a2.f19965a, null);
            return;
        }
        C10384Log.m6211d("MicroMsg.SDK.GetQRCodeTask", "onPostExecute, get qrcode success imgBufSize = " + c10378a2.f19969e.length);
        this.f19963f.onAuthGotQrcode(c10378a2.f19968d, c10378a2.f19969e);
        AsyncTaskC10379c asyncTaskC10379c = new AsyncTaskC10379c(c10378a2.f19966b, this.f19963f);
        this.f19964g = asyncTaskC10379c;
        if (Build.VERSION.SDK_INT >= 11) {
            asyncTaskC10379c.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            asyncTaskC10379c.execute(new Void[0]);
        }
    }
}
