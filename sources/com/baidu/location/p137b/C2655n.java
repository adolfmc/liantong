package com.baidu.location.p137b;

import android.util.Base64;
import com.baidu.location.Jni;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.b.n */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2655n {

    /* renamed from: a */
    private boolean f5326a;

    /* renamed from: b */
    private String[] f5327b;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.n$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class C2657a {

        /* renamed from: a */
        private static C2655n f5328a = new C2655n();
    }

    private C2655n() {
        this.f5326a = false;
        this.f5327b = null;
        try {
            String str = Jni.getldkaiv();
            if (str == null || !str.contains("|")) {
                return;
            }
            this.f5327b = str.split("\\|");
            if (this.f5327b == null || this.f5327b.length != 2) {
                return;
            }
            this.f5326a = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static C2655n m19419a() {
        return C2657a.f5328a;
    }

    /* renamed from: a */
    public synchronized String m19418a(String str) {
        if (this.f5326a) {
            try {
                IvParameterSpec ivParameterSpec = new IvParameterSpec(this.f5327b[1].getBytes("UTF-8"));
                SecretKeySpec secretKeySpec = new SecretKeySpec(this.f5327b[0].getBytes("UTF-8"), C0108a.f85c);
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                cipher.init(1, secretKeySpec, ivParameterSpec);
                return Base64.encodeToString(cipher.doFinal(str.getBytes()), 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /* renamed from: b */
    public synchronized String m19416b(String str) {
        if (this.f5326a) {
            try {
                IvParameterSpec ivParameterSpec = new IvParameterSpec(this.f5327b[1].getBytes("UTF-8"));
                SecretKeySpec secretKeySpec = new SecretKeySpec(this.f5327b[0].getBytes("UTF-8"), C0108a.f85c);
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                cipher.init(2, secretKeySpec, ivParameterSpec);
                return new String(cipher.doFinal(Base64.decode(str, 0)), "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /* renamed from: b */
    public boolean m19417b() {
        return this.f5326a;
    }
}
