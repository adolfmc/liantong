package com.vivo.push.p372e;

import android.content.Context;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.text.TextUtils;
import android.util.Base64;
import com.vivo.push.util.LogUtil;
import java.math.BigInteger;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.UnrecoverableEntryException;
import java.util.Calendar;
import javax.security.auth.x500.X500Principal;

/* renamed from: com.vivo.push.e.c */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class RsaSecurity implements IRsaSecurity {

    /* renamed from: a */
    private PrivateKey f20967a = null;

    /* renamed from: b */
    private PublicKey f20968b = null;

    /* renamed from: c */
    private KeyStore f20969c;

    /* renamed from: d */
    private X500Principal f20970d;

    /* renamed from: e */
    private Context f20971e;

    public RsaSecurity(Context context) {
        this.f20971e = context;
        m5711a(this.f20971e);
    }

    /* renamed from: a */
    private synchronized void m5711a(Context context) {
        try {
            m5708b();
            if (!m5706b("PushRsaKeyAlias")) {
                m5707b(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.m5354a("RsaSecurity", "init error" + e.getMessage());
        }
    }

    @Override // com.vivo.push.p372e.IRsaSecurity
    /* renamed from: a */
    public final String mo5710a(String str) {
        try {
            if (TextUtils.isEmpty(str) || m5705c(this.f20971e) == null) {
                return null;
            }
            byte[] bytes = str.getBytes("UTF-8");
            PrivateKey m5705c = m5705c(this.f20971e);
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(m5705c);
            signature.update(bytes);
            String encodeToString = Base64.encodeToString(signature.sign(), 2);
            LogUtil.m5341d("RsaSecurity", str.hashCode() + " = " + encodeToString);
            return encodeToString;
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.m5354a("RsaSecurity", "signClientSDK error" + e.getMessage());
            return null;
        }
    }

    @Override // com.vivo.push.p372e.IRsaSecurity
    /* renamed from: a */
    public final boolean mo5709a(byte[] bArr, PublicKey publicKey, byte[] bArr2) {
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(publicKey);
            signature.update(bArr);
            return signature.verify(bArr2);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.m5354a("RsaSecurity", "verifyClientSDK error" + e.getMessage());
            return false;
        }
    }

    /* renamed from: b */
    private void m5707b(Context context) {
        try {
            if (context == null) {
                LogUtil.m5341d("RsaSecurity", " generateRSAKeyPairSign context == null ");
                return;
            }
            Calendar calendar = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(1, 999);
            if (Build.VERSION.SDK_INT >= 18) {
                KeyPairGeneratorSpec build = new KeyPairGeneratorSpec.Builder(context.getApplicationContext()).setAlias("PushRsaKeyAlias").setSubject(this.f20970d).setSerialNumber(BigInteger.valueOf(1337L)).setStartDate(calendar.getTime()).setEndDate(calendar2.getTime()).build();
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
                keyPairGenerator.initialize(build);
                keyPairGenerator.generateKeyPair();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.m5354a("RsaSecurity", "generateRSAKeyPairSign error" + e.getMessage());
        }
    }

    /* renamed from: b */
    private boolean m5706b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (this.f20969c == null) {
                m5708b();
            }
            return this.f20969c.containsAlias(str);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.m5354a("RsaSecurity", "getPrivateKeySigin error" + e.getMessage());
            return false;
        }
    }

    /* renamed from: b */
    private void m5708b() {
        try {
            this.f20969c = KeyStore.getInstance("AndroidKeyStore");
            this.f20969c.load(null);
            this.f20970d = new X500Principal("CN=Push SDK, OU=VIVO, O=VIVO PUSH, C=CN");
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.m5354a("RsaSecurity", "initKeyStore error" + e.getMessage());
        }
    }

    /* renamed from: c */
    private PrivateKey m5705c(Context context) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.m5354a("RsaSecurity", "getPrivateKeySigin error" + e.getMessage());
        }
        if (this.f20967a != null) {
            return this.f20967a;
        }
        if (context == null) {
            LogUtil.m5341d("RsaSecurity", " getPrivateKeySigin context == null ");
            return null;
        }
        KeyStore.Entry m5704d = m5704d(context);
        if (m5704d instanceof KeyStore.PrivateKeyEntry) {
            this.f20967a = ((KeyStore.PrivateKeyEntry) m5704d).getPrivateKey();
            return this.f20967a;
        }
        return null;
    }

    /* renamed from: d */
    private KeyStore.Entry m5704d(Context context) throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException {
        try {
            if (context == null) {
                LogUtil.m5341d("RsaSecurity", " getPrivateKeySigin context == null ");
                return null;
            }
            if (!m5706b("PushRsaKeyAlias")) {
                m5707b(context);
            }
            return this.f20969c.getEntry("PushRsaKeyAlias", null);
        } catch (Exception e) {
            m5707b(context);
            KeyStore.Entry entry = this.f20969c.getEntry("PushRsaKeyAlias", null);
            e.printStackTrace();
            LogUtil.m5354a("RsaSecurity", "getPrivateKeySigin error" + e.getMessage());
            return entry;
        }
    }

    @Override // com.vivo.push.p372e.IRsaSecurity
    /* renamed from: a */
    public final PublicKey mo5712a() {
        try {
            if (this.f20968b != null) {
                return this.f20968b;
            }
            KeyStore.Entry m5704d = m5704d(this.f20971e);
            if (m5704d instanceof KeyStore.PrivateKeyEntry) {
                this.f20968b = ((KeyStore.PrivateKeyEntry) m5704d).getCertificate().getPublicKey();
                return this.f20968b;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.m5354a("RsaSecurity", "getPublicKeySign error" + e.getMessage());
            return null;
        }
    }
}
