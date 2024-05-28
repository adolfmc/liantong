package com.android.client.asm.core.shared;

import android.content.Context;
import com.android.client.asm.sdk.IAKSelector;
import com.android.client.asm.sdk.IAuthenticatorDescriptor;
import com.android.client.asm.sdk.IMatcher;
import com.android.client.asm.sdk.ProtocolType;
import com.gmrz.android.client.asm.api.AsmError;
import com.gmrz.android.client.asm.api.AsmException;
import com.gmrz.android.client.utils.JsonObjectAdapter;
import com.gmrz.android.client.utils.Logger;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DescriptorLoader {

    /* renamed from: a */
    private static final String f4056a = "DescriptorLoader";

    /* renamed from: e */
    private static DescriptorLoader f4057e;

    /* renamed from: c */
    private Context f4059c;

    /* renamed from: d */
    private List<IAuthenticatorDescriptor> f4060d;

    /* renamed from: b */
    private final String f4058b = "asmdescriptors";

    /* renamed from: f */
    private String[] f4061f = null;

    private DescriptorLoader(Context context) {
        this.f4059c = null;
        this.f4059c = context;
    }

    public static DescriptorLoader instance(Context context) {
        if (f4057e == null) {
            f4057e = new DescriptorLoader(context);
        }
        return f4057e;
    }

    public List<IAuthenticatorDescriptor> enumerateAndLoadRegisteredDescriptors() {
        List<IAuthenticatorDescriptor> list = this.f4060d;
        if (list != null) {
            list.clear();
        } else {
            this.f4060d = new ArrayList();
        }
        int identifier = this.f4059c.getResources().getIdentifier("asmdescriptors", "raw", this.f4059c.getPackageName());
        this.f4059c.getPackageName();
        String str = f4056a;
        Logger.m15895d(str, "Package name is " + this.f4059c.getPackageName());
        try {
            String m20457a = m20457a(this.f4059c, identifier);
            Gson create = JsonObjectAdapter.GsonBuilder().create();
            this.f4061f = (String[]) ((DescriptorClassList) (!(create instanceof Gson) ? create.fromJson(m20457a, (Class<Object>) DescriptorClassList.class) : NBSGsonInstrumentation.fromJson(create, m20457a, (Class<Object>) DescriptorClassList.class))).getDescriptorclass().clone();
            String str2 = f4056a;
            Logger.m15895d(str2, "Registered descriptor class names count: " + this.f4061f.length);
        } catch (JsonSyntaxException e) {
            String str3 = f4056a;
            Logger.m15883w(str3, "Warning to parse the json " + e);
        }
        m20458a();
        return this.f4060d;
    }

    /* renamed from: a */
    private void m20458a() {
        String[] strArr;
        for (String str : this.f4061f) {
            IAuthenticatorDescriptor m20456a = m20456a(str, this.f4059c);
            if (m20456a != null) {
                this.f4060d.add(m20456a);
            } else {
                Logger.m15883w(f4056a, "Loading of " + str + " class failed.");
            }
        }
    }

    /* renamed from: a */
    private static IAuthenticatorDescriptor m20456a(String str, Context context) {
        String str2 = f4056a;
        Logger.m15895d(str2, "loadDescriptor(" + str + ")");
        try {
            return (IAuthenticatorDescriptor) Class.forName(str).asSubclass(IAuthenticatorDescriptor.class).getConstructor(Context.class).newInstance(context);
        } catch (Exception e) {
            String str3 = f4056a;
            Logger.m15891e(str3, "Failed to load Descriptor with class Name: " + str, e);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x007c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String m20457a(android.content.Context r4, int r5) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            android.content.res.Resources r4 = r4.getResources()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            java.io.InputStream r4 = r4.openRawResource(r5)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            java.nio.charset.Charset r3 = com.gmrz.android.client.utils.Charsets.utf8Charset     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            r2.<init>(r4, r3)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            r5.<init>(r2)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
        L1a:
            java.lang.String r4 = r5.readLine()     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
            if (r4 == 0) goto L29
            r0.append(r4)     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
            r4 = 10
            r0.append(r4)     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
            goto L1a
        L29:
            r5.close()     // Catch: java.io.IOException -> L2d java.lang.Exception -> L5c java.lang.Throwable -> L79
            goto L35
        L2d:
            r4 = move-exception
            java.lang.String r2 = com.android.client.asm.core.shared.DescriptorLoader.f4056a     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
            java.lang.String r3 = "readRawTextFile: failed to close buffered reader."
            com.gmrz.android.client.utils.Logger.m15891e(r2, r3, r4)     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
        L35:
            java.lang.String r4 = com.android.client.asm.core.shared.DescriptorLoader.f4056a     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
            r2.<init>()     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
            java.lang.String r3 = "readRawTextFile: "
            r2.append(r3)     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
            r2.append(r0)     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
            com.gmrz.android.client.utils.Logger.m15895d(r4, r2)     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
            r5.close()     // Catch: java.io.IOException -> L4f
            goto L57
        L4f:
            r4 = move-exception
            java.lang.String r5 = com.android.client.asm.core.shared.DescriptorLoader.f4056a
            java.lang.String r1 = "readRawTextFile: failed to close buffered reader."
            com.gmrz.android.client.utils.Logger.m15891e(r5, r1, r4)
        L57:
            java.lang.String r4 = r0.toString()
            return r4
        L5c:
            r4 = move-exception
            goto L63
        L5e:
            r4 = move-exception
            r5 = r1
            goto L7a
        L61:
            r4 = move-exception
            r5 = r1
        L63:
            java.lang.String r0 = com.android.client.asm.core.shared.DescriptorLoader.f4056a     // Catch: java.lang.Throwable -> L79
            java.lang.String r2 = "Error reading raw text file"
            com.gmrz.android.client.utils.Logger.m15891e(r0, r2, r4)     // Catch: java.lang.Throwable -> L79
            if (r5 == 0) goto L78
            r5.close()     // Catch: java.io.IOException -> L70
            goto L78
        L70:
            r4 = move-exception
            java.lang.String r5 = com.android.client.asm.core.shared.DescriptorLoader.f4056a
            java.lang.String r0 = "readRawTextFile: failed to close buffered reader."
            com.gmrz.android.client.utils.Logger.m15891e(r5, r0, r4)
        L78:
            return r1
        L79:
            r4 = move-exception
        L7a:
            if (r5 == 0) goto L88
            r5.close()     // Catch: java.io.IOException -> L80
            goto L88
        L80:
            r5 = move-exception
            java.lang.String r0 = com.android.client.asm.core.shared.DescriptorLoader.f4056a
            java.lang.String r1 = "readRawTextFile: failed to close buffered reader."
            com.gmrz.android.client.utils.Logger.m15891e(r0, r1, r5)
        L88:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.client.asm.core.shared.DescriptorLoader.m20457a(android.content.Context, int):java.lang.String");
    }

    public static IAKSelector loadAKSelectorFromClassName(IMatcher iMatcher, IAuthenticatorDescriptor iAuthenticatorDescriptor, Context context, ProtocolType protocolType) throws AsmException {
        Class<? extends IAKSelector> authenticatorSelectorClass = iAuthenticatorDescriptor.getAuthenticatorSelectorClass();
        try {
            return authenticatorSelectorClass.getConstructor(Context.class, ProtocolType.class, IAuthenticatorDescriptor.class, IMatcher.class).newInstance(context, protocolType, iAuthenticatorDescriptor, iMatcher);
        } catch (Exception e) {
            AsmError asmError = AsmError.FAILURE;
            throw new AsmException(asmError, "loadAKSelectorFromClassName: class load error for [" + authenticatorSelectorClass.getCanonicalName() + "]", e);
        }
    }

    public static IMatcher loadAuthenticatorUIFromClassName(Class<? extends IMatcher> cls, Context context, ProtocolType protocolType) throws AsmException {
        try {
            return cls.getConstructor(Context.class, ProtocolType.class).newInstance(context, protocolType);
        } catch (Exception e) {
            AsmError asmError = AsmError.FAILURE;
            throw new AsmException(asmError, "loadAuthenticatorUIFromClassName: class load error for [" + cls.getCanonicalName() + "]", e);
        }
    }
}
