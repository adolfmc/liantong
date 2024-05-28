package com.sina.weibo.sdk.web.p313b;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.net.InterfaceC7099c;
import com.sina.weibo.sdk.p310a.C7064b;
import com.sina.weibo.sdk.p310a.C7075d;
import com.sina.weibo.sdk.p311b.C7092c;
import com.sina.weibo.sdk.p311b.C7094e;
import com.sina.weibo.sdk.web.p313b.AbstractC7125b;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sina.weibo.sdk.web.b.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C7128d extends AbstractC7125b {

    /* renamed from: aE */
    public WeiboMultiMessage f18364aE;

    /* renamed from: aF */
    private byte[] f18365aF;

    /* renamed from: aG */
    String f18366aG;

    /* renamed from: ae */
    public String f18367ae;
    public String packageName;
    private String text;

    public C7128d(AuthInfo authInfo) {
        super(authInfo, 1, null, null);
    }

    public C7128d(Context context) {
        this.f18361Z = context;
    }

    @Override // com.sina.weibo.sdk.web.p313b.AbstractC7125b
    public final String getUrl() {
        Uri.Builder buildUpon = Uri.parse("https://service.weibo.com/share/mobilesdk.php").buildUpon();
        buildUpon.appendQueryParameter("title", this.text);
        buildUpon.appendQueryParameter("version", "0041005000");
        String appKey = this.f18362aC.m8020a().getAppKey();
        if (!TextUtils.isEmpty(appKey)) {
            buildUpon.appendQueryParameter("source", appKey);
        }
        if (!TextUtils.isEmpty(this.f18367ae)) {
            buildUpon.appendQueryParameter("access_token", this.f18367ae);
        }
        if (!TextUtils.isEmpty(this.packageName)) {
            buildUpon.appendQueryParameter("packagename", this.packageName);
        }
        if (!TextUtils.isEmpty(this.f18366aG)) {
            buildUpon.appendQueryParameter("picinfo", this.f18366aG);
        }
        buildUpon.appendQueryParameter("luicode", "10000360");
        buildUpon.appendQueryParameter("lfid", "OP_".concat(String.valueOf(appKey)));
        return buildUpon.build().toString();
    }

    @Override // com.sina.weibo.sdk.web.p313b.AbstractC7125b
    /* renamed from: a */
    protected final void mo8007a(Bundle bundle) {
        WeiboMultiMessage weiboMultiMessage = this.f18364aE;
        if (weiboMultiMessage != null) {
            weiboMultiMessage.writeToBundle(bundle);
        }
        bundle.putString("token", this.f18367ae);
        bundle.putString("packageName", this.packageName);
    }

    @Override // com.sina.weibo.sdk.web.p313b.AbstractC7125b
    /* renamed from: b */
    protected final void mo8005b(Bundle bundle) {
        byte[] bArr;
        this.f18364aE = new WeiboMultiMessage();
        this.f18364aE.readFromBundle(bundle);
        this.f18367ae = bundle.getString("token");
        this.packageName = bundle.getString("packageName");
        StringBuilder sb = new StringBuilder();
        if (this.f18364aE.textObject != null) {
            sb.append(this.f18364aE.textObject.text);
        }
        if (this.f18364aE.imageObject != null) {
            ImageObject imageObject = this.f18364aE.imageObject;
            String str = imageObject.imagePath;
            if (!TextUtils.isEmpty(str)) {
                File file = new File(str);
                if (file.exists() && file.canRead() && file.length() > 0) {
                    byte[] bArr2 = new byte[(int) file.length()];
                    FileInputStream fileInputStream = null;
                    try {
                        try {
                            FileInputStream fileInputStream2 = new FileInputStream(file);
                            try {
                                fileInputStream2.read(bArr2);
                                this.f18365aF = C7094e.m8066b(bArr2);
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } catch (Exception e2) {
                                e = e2;
                                fileInputStream = fileInputStream2;
                                e.printStackTrace();
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                                bArr = imageObject.imageData;
                                if (bArr != null) {
                                    this.f18365aF = C7094e.m8066b(bArr);
                                }
                                this.text = sb.toString();
                            } catch (Throwable th) {
                                th = th;
                                fileInputStream = fileInputStream2;
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e4) {
                                        e4.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        } catch (Exception e5) {
                            e = e5;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            }
            bArr = imageObject.imageData;
            if (bArr != null && bArr.length > 0) {
                this.f18365aF = C7094e.m8066b(bArr);
            }
        }
        this.text = sb.toString();
    }

    @Override // com.sina.weibo.sdk.web.p313b.AbstractC7125b
    /* renamed from: t */
    public final boolean mo8004t() {
        byte[] bArr = this.f18365aF;
        if (bArr == null || bArr.length <= 0) {
            return super.mo8004t();
        }
        return true;
    }

    @Override // com.sina.weibo.sdk.web.p313b.AbstractC7125b
    /* renamed from: a */
    public final void mo8006a(final AbstractC7125b.InterfaceC7126a interfaceC7126a) {
        C7064b c7064b;
        C7075d c7075d = new C7075d(this.f18361Z, new String(this.f18365aF), this.f18362aC.m8020a().getAppKey(), this.f18367ae, new InterfaceC7099c<String>() { // from class: com.sina.weibo.sdk.web.b.d.1
            @Override // com.sina.weibo.sdk.net.InterfaceC7099c
            /* renamed from: a */
            public final /* synthetic */ void mo8003a(String str) {
                String str2 = str;
                C7092c.m8072a("WbShareTag", "handle image result :".concat(String.valueOf(str2)));
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        JSONObject jSONObject = new JSONObject(str2);
                        int optInt = jSONObject.optInt("code");
                        String optString = jSONObject.optString("data");
                        if (optInt != 1 || TextUtils.isEmpty(optString)) {
                            if (interfaceC7126a != null) {
                                interfaceC7126a.onError("图片内容不合适，禁止上传！");
                                return;
                            }
                            return;
                        }
                        C7128d.this.f18366aG = optString;
                        if (interfaceC7126a != null) {
                            interfaceC7126a.onComplete();
                            return;
                        }
                        return;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        AbstractC7125b.InterfaceC7126a interfaceC7126a2 = interfaceC7126a;
                        if (interfaceC7126a2 != null) {
                            interfaceC7126a2.onError("解析服务端返回的字符串时发生异常！");
                            return;
                        }
                        return;
                    }
                }
                AbstractC7125b.InterfaceC7126a interfaceC7126a3 = interfaceC7126a;
                if (interfaceC7126a3 != null) {
                    interfaceC7126a3.onError("处理图片，服务端返回null!");
                }
            }

            @Override // com.sina.weibo.sdk.net.InterfaceC7099c
            public final void onError(Throwable th) {
                AbstractC7125b.InterfaceC7126a interfaceC7126a2 = interfaceC7126a;
                if (interfaceC7126a2 != null) {
                    interfaceC7126a2.onError(th.getMessage());
                }
            }
        });
        c7064b = C7064b.C7065a.f18270K;
        c7064b.m8102a(c7075d);
    }
}
