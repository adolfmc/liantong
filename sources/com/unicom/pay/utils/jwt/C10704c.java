package com.unicom.pay.utils.jwt;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Base64;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Map;

@NBSInstrumented
/* renamed from: com.unicom.pay.utils.jwt.c */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C10704c implements Parcelable {
    public static final Parcelable.Creator<C10704c> CREATOR = new C10705a();

    /* renamed from: a */
    public final String f20445a;

    /* renamed from: b */
    public C10707d f20446b;

    /* renamed from: com.unicom.pay.utils.jwt.c$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10705a implements Parcelable.Creator<C10704c> {
        @Override // android.os.Parcelable.Creator
        public final C10704c createFromParcel(Parcel parcel) {
            return new C10704c(parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final C10704c[] newArray(int i) {
            return new C10704c[i];
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.utils.jwt.c$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10706b extends TypeToken<Map<String, String>> {
    }

    public C10704c(@NonNull String str) {
        m6057a(str);
        this.f20445a = str;
    }

    /* renamed from: a */
    public final void m6057a(String str) {
        String[] split = str.split("\\.");
        String[] strArr = (split.length == 2 && str.endsWith(".")) ? new String[]{split[0], split[1], ""} : split;
        if (strArr.length == 3) {
            try {
                Map map = (Map) m6056a(new String(Base64.decode(strArr[0], 11), Charset.defaultCharset()), new C10706b().getType());
                try {
                    this.f20446b = (C10707d) m6056a(new String(Base64.decode(strArr[1], 11), Charset.defaultCharset()), C10707d.class);
                    String str2 = strArr[2];
                    return;
                } catch (IllegalArgumentException e) {
                    throw new C10703b("Received bytes didn't correspond to a valid Base64 encoded string.", e);
                }
            } catch (IllegalArgumentException e2) {
                throw new C10703b("Received bytes didn't correspond to a valid Base64 encoded string.", e2);
            }
        }
        throw new C10703b(String.format("The token was expected to have 3 parts, but got %s.", Integer.valueOf(strArr.length)));
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return this.f20445a;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f20445a);
    }

    /* renamed from: a */
    public final <T> T m6056a(String str, Type type) {
        try {
            Gson create = new GsonBuilder().registerTypeAdapter(C10707d.class, new WPJWTDeserializer()).create();
            return !(create instanceof Gson) ? (T) create.fromJson(str, type) : (T) NBSGsonInstrumentation.fromJson(create, str, type);
        } catch (Exception e) {
            throw new C10703b("The token's payload had an invalid JSON format.", e);
        }
    }
}
