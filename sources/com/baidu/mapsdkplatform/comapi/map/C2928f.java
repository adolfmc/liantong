package com.baidu.mapsdkplatform.comapi.map;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.mapapi.NetworkUtil;
import com.baidu.mapapi.http.AsyncHttpClient;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.mapsdkplatform.comapi.commonutils.p144a.C2892c;
import com.baidu.mapsdkplatform.comapi.util.SyncSysInfo;
import com.baidu.mapsdkplatform.comjni.util.AppMD5;
import com.chinaunicon.jtwifilib.jtcommon.util.JtClient;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.mapsdkplatform.comapi.map.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2928f {

    /* renamed from: a */
    private static final String f7296a = "f";

    /* renamed from: b */
    private AsyncHttpClient f7297b;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapsdkplatform.comapi.map.f$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC2929a {
        /* renamed from: a */
        void mo18241a(int i, String str, String str2);

        /* renamed from: a */
        void mo18240a(String str);

        /* renamed from: a */
        void mo18239a(boolean z, String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapsdkplatform.comapi.map.f$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class C2930b {

        /* renamed from: a */
        private static final C2928f f7298a = new C2928f(null);
    }

    private C2928f() {
        this.f7297b = new AsyncHttpClient();
    }

    /* synthetic */ C2928f(C2931g c2931g) {
        this();
    }

    /* renamed from: a */
    public static C2928f m18263a() {
        return C2930b.f7298a;
    }

    /* renamed from: a */
    private String m18261a(Context context) {
        BufferedReader bufferedReader = null;
        if (context == null) {
            return null;
        }
        File file = new File(context.getFilesDir().getAbsolutePath(), "ver.cfg");
        if (!file.exists()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine != null) {
                            sb.append(readLine);
                        } else {
                            try {
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (IOException e2) {
                        e = e2;
                        bufferedReader = bufferedReader2;
                        e.printStackTrace();
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        return "";
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
                bufferedReader2.close();
                return sb.toString();
            } catch (IOException e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public String m18259a(Context context, String str) {
        if (context == null) {
            return null;
        }
        return context.getFilesDir().getAbsolutePath() + File.separator + "sc_sty_" + str + ".sty";
    }

    /* renamed from: a */
    private String m18254a(Context context, String str, boolean z) {
        String str2;
        String str3;
        String str4;
        if (context == null) {
            return "";
        }
        String m18244b = m18244b(context, str);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("style_id", str);
        if (z) {
            str2 = "type";
            str3 = "publish";
        } else {
            str2 = "type";
            str3 = "edit";
        }
        linkedHashMap.put(str2, str3);
        linkedHashMap.put("md5", m18244b);
        linkedHashMap.put("token", SyncSysInfo.getAuthToken());
        return m18242b("api.map.baidu.com/sdkproxy/v2/lbs_androidsdk/custom/v2/getjsonstyle") + "?" + ((m18245a(linkedHashMap) + SyncSysInfo.getPhoneInfo()) + "&sign=" + AppMD5.getSignMD5String(str4));
    }

    /* renamed from: a */
    private String m18245a(Map<String, String> map) {
        if (map.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String str : map.keySet()) {
            String encodeUrlParamsValue = AppMD5.encodeUrlParamsValue(map.get(str));
            if (i != 0) {
                sb.append("&");
            }
            sb.append(str);
            sb.append("=");
            sb.append(encodeUrlParamsValue);
            i++;
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007a A[Catch: all -> 0x008c, Exception -> 0x008f, TryCatch #4 {Exception -> 0x008f, all -> 0x008c, blocks: (B:28:0x0076, B:30:0x007a, B:32:0x0085, B:31:0x007f), top: B:50:0x0076 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007f A[Catch: all -> 0x008c, Exception -> 0x008f, TryCatch #4 {Exception -> 0x008f, all -> 0x008c, blocks: (B:28:0x0076, B:30:0x007a, B:32:0x0085, B:31:0x007f), top: B:50:0x0076 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m18257a(android.content.Context r8, java.lang.String r9, java.lang.String r10) {
        /*
            r7 = this;
            if (r8 != 0) goto L3
            return
        L3:
            java.io.File r0 = r8.getFilesDir()
            java.lang.String r0 = r0.getAbsolutePath()
            java.io.File r1 = new java.io.File
            java.lang.String r2 = "ver.cfg"
            r1.<init>(r0, r2)
            java.lang.String r8 = r7.m18261a(r8)
            r0 = 0
            boolean r2 = android.text.TextUtils.isEmpty(r8)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            if (r2 == 0) goto L24
            org.json.JSONArray r8 = new org.json.JSONArray     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            r8.<init>()     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            goto L2a
        L24:
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            r2.<init>(r8)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            r8 = r2
        L2a:
            int r2 = r8.length()     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            if (r2 != 0) goto L3c
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            r2.<init>()     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            r2.put(r9, r10)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
        L38:
            r8.put(r2)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            goto L68
        L3c:
            r3 = 0
            r4 = r0
        L3e:
            r5 = -1
            if (r3 >= r2) goto L53
            java.lang.Object r4 = r8.opt(r3)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            org.json.JSONObject r4 = (org.json.JSONObject) r4     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            if (r4 == 0) goto L50
            boolean r6 = r4.has(r9)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            if (r6 == 0) goto L50
            goto L54
        L50:
            int r3 = r3 + 1
            goto L3e
        L53:
            r3 = r5
        L54:
            if (r3 == r5) goto L5f
            if (r4 == 0) goto L5f
            r4.put(r9, r10)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            r8.put(r3, r4)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            goto L68
        L5f:
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            r2.<init>()     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            r2.put(r9, r10)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            goto L38
        L68:
            java.io.FileWriter r9 = new java.io.FileWriter     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            java.io.File r10 = r1.getAbsoluteFile()     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            java.io.PrintWriter r10 = new java.io.PrintWriter     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            r10.<init>(r9)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            boolean r9 = r8 instanceof org.json.JSONArray     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8f
            if (r9 != 0) goto L7f
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8f
            goto L85
        L7f:
            org.json.JSONArray r8 = (org.json.JSONArray) r8     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8f
            java.lang.String r8 = com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation.toString(r8)     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8f
        L85:
            r10.write(r8)     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8f
            r10.close()
            goto L9d
        L8c:
            r8 = move-exception
            r0 = r10
            goto L9e
        L8f:
            r8 = move-exception
            r0 = r10
            goto L95
        L92:
            r8 = move-exception
            goto L9e
        L94:
            r8 = move-exception
        L95:
            r8.printStackTrace()     // Catch: java.lang.Throwable -> L92
            if (r0 == 0) goto L9d
            r0.close()
        L9d:
            return
        L9e:
            if (r0 == 0) goto La3
            r0.close()
        La3:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapsdkplatform.comapi.map.C2928f.m18257a(android.content.Context, java.lang.String, java.lang.String):void");
    }

    /* renamed from: a */
    private void m18256a(Context context, String str, String str2, InterfaceC2929a interfaceC2929a) {
        this.f7297b.get(str, new C2931g(this, context, str2, interfaceC2929a));
    }

    /* renamed from: a */
    private void m18255a(Context context, String str, String str2, String str3, InterfaceC2929a interfaceC2929a) {
        if (TextUtils.isEmpty(str) || context == null) {
            return;
        }
        String m18242b = m18242b(str);
        String m18259a = m18259a(context, str2);
        String absolutePath = context.getFilesDir().getAbsolutePath();
        new C2892c().m18463a(m18242b, absolutePath, str2 + JtClient.UXUE_TEMP_FILE_SUFFIX, 2, new C2932h(this, context, str2, interfaceC2929a, str3, m18259a));
    }

    /* renamed from: a */
    private void m18253a(Context context, String str, boolean z, InterfaceC2929a interfaceC2929a) {
        String m18259a = m18259a(context, str);
        if (!m18246a(m18259a)) {
            m18259a = null;
        }
        if (interfaceC2929a != null) {
            interfaceC2929a.mo18240a(m18259a);
        }
        if (!NetworkUtil.isNetworkAvailable(context)) {
            if (interfaceC2929a != null) {
                interfaceC2929a.mo18241a(HttpClient.HttpStateError.NETWORK_ERROR.ordinal(), HttpClient.HttpStateError.NETWORK_ERROR.name(), m18259a);
            }
        } else if (TextUtils.isEmpty(str)) {
        } else {
            String m18254a = m18254a(context, str, z);
            if (TextUtils.isEmpty(m18254a)) {
                Log.e(f7296a, "build request url failed");
            } else {
                m18256a(context, m18254a, str, interfaceC2929a);
            }
        }
    }

    /* renamed from: a */
    private boolean m18262a(int i, String str) {
        return (103 == i && m18246a(str)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m18260a(Context context, File file, String str) {
        ZipFile zipFile;
        if (file == null || context == null) {
            return false;
        }
        ZipFile zipFile2 = null;
        try {
            try {
                try {
                    zipFile = new ZipFile(file.getAbsoluteFile());
                    try {
                        ZipEntry entry = zipFile.getEntry(str + ".sty");
                        if (entry == null) {
                            try {
                                zipFile.close();
                            } catch (IOException e) {
                                Log.e(f7296a, "Close zipFile failed", e);
                            }
                            return false;
                        }
                        boolean m18247a = m18247a(zipFile.getInputStream(entry), new FileOutputStream(new File(m18259a(context, str))));
                        file.delete();
                        try {
                            zipFile.close();
                        } catch (IOException e2) {
                            Log.e(f7296a, "Close zipFile failed", e2);
                        }
                        return m18247a;
                    } catch (FileNotFoundException e3) {
                        e = e3;
                        zipFile2 = zipFile;
                        Log.e(f7296a, "unzip style file FileNotFoundException", e);
                        if (zipFile2 != null) {
                            zipFile2.close();
                        }
                        return false;
                    } catch (IOException e4) {
                        e = e4;
                        zipFile2 = zipFile;
                        Log.e(f7296a, "unzip style file IOException", e);
                        if (zipFile2 != null) {
                            zipFile2.close();
                        }
                        return false;
                    } catch (IllegalStateException e5) {
                        e = e5;
                        zipFile2 = zipFile;
                        Log.e(f7296a, "unzip style file IllegalStateException", e);
                        if (zipFile2 != null) {
                            zipFile2.close();
                        }
                        return false;
                    } catch (NullPointerException e6) {
                        e = e6;
                        zipFile2 = zipFile;
                        Log.e(f7296a, "unzip style file NullPointerException", e);
                        if (zipFile2 != null) {
                            zipFile2.close();
                        }
                        return false;
                    } catch (SecurityException e7) {
                        e = e7;
                        zipFile2 = zipFile;
                        Log.e(f7296a, "unzip style file SecurityException", e);
                        if (zipFile2 != null) {
                            zipFile2.close();
                        }
                        return false;
                    } catch (ZipException e8) {
                        e = e8;
                        zipFile2 = zipFile;
                        Log.e(f7296a, "unzip style file ZipException", e);
                        if (zipFile2 != null) {
                            zipFile2.close();
                        }
                        return false;
                    } catch (Exception unused) {
                        if (zipFile != null) {
                            zipFile.close();
                        }
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        zipFile2 = zipFile;
                        if (zipFile2 != null) {
                            try {
                                zipFile2.close();
                            } catch (IOException e9) {
                                Log.e(f7296a, "Close zipFile failed", e9);
                            }
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e10) {
                    e = e10;
                } catch (IOException e11) {
                    e = e11;
                } catch (IllegalStateException e12) {
                    e = e12;
                } catch (NullPointerException e13) {
                    e = e13;
                } catch (SecurityException e14) {
                    e = e14;
                } catch (ZipException e15) {
                    e = e15;
                } catch (Exception unused2) {
                    zipFile = null;
                }
            } catch (IOException e16) {
                Log.e(f7296a, "Close zipFile failed", e16);
                return false;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* renamed from: a */
    private boolean m18247a(InputStream inputStream, FileOutputStream fileOutputStream) throws IOException, NullPointerException {
        if (inputStream == null || fileOutputStream == null) {
            return false;
        }
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(f7296a, "Close InputStream error", e);
                }
                try {
                    fileOutputStream.close();
                } catch (IOException e2) {
                    Log.e(f7296a, "Close OutputStream error", e2);
                }
                throw th;
            }
        }
        fileOutputStream.flush();
        try {
            inputStream.close();
        } catch (IOException e3) {
            Log.e(f7296a, "Close InputStream error", e3);
        }
        try {
            fileOutputStream.close();
            return true;
        } catch (IOException e4) {
            Log.e(f7296a, "Close OutputStream error", e4);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m18246a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return new File(str).exists();
    }

    /* renamed from: b */
    private String m18244b(Context context, String str) {
        try {
            JSONArray jSONArray = new JSONArray(m18261a(context));
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                String optString = ((JSONObject) jSONArray.opt(i)).optString(str);
                if (!TextUtils.isEmpty(optString)) {
                    return optString;
                }
            }
            return "";
        } catch (JSONException unused) {
            return "";
        }
    }

    /* renamed from: b */
    private String m18242b(String str) {
        StringBuilder sb;
        String str2;
        if (HttpClient.isHttpsEnable) {
            sb = new StringBuilder();
            str2 = "https://";
        } else {
            sb = new StringBuilder();
            str2 = "http://";
        }
        sb.append(str2);
        sb.append(str);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m18243b(Context context, String str, String str2, InterfaceC2929a interfaceC2929a) {
        String m18259a = m18259a(context, str2);
        String str3 = m18246a(m18259a) ? m18259a : null;
        if (TextUtils.isEmpty(str)) {
            if (interfaceC2929a != null) {
                interfaceC2929a.mo18241a(HttpClient.HttpStateError.SERVER_ERROR.ordinal(), HttpClient.HttpStateError.SERVER_ERROR.name(), str3);
                return;
            }
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("status");
            String optString = jSONObject.optString("message");
            if (!m18262a(optInt, m18259a)) {
                if (interfaceC2929a != null) {
                    interfaceC2929a.mo18239a(false, str3);
                }
            } else if (optInt != 0) {
                if (interfaceC2929a != null) {
                    interfaceC2929a.mo18241a(optInt, optString, str3);
                }
            } else {
                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                if (optJSONObject != null && optJSONObject.length() != 0) {
                    m18255a(context, optJSONObject.optString("pb_url", ""), str2, optJSONObject.optString("md5", ""), interfaceC2929a);
                } else if (interfaceC2929a != null) {
                    interfaceC2929a.mo18241a(HttpClient.HttpStateError.SERVER_ERROR.ordinal(), "custom style data is null", str3);
                }
            }
        } catch (JSONException unused) {
            if (interfaceC2929a != null) {
                interfaceC2929a.mo18241a(HttpClient.HttpStateError.INNER_ERROR.ordinal(), "parse response result failed", str3);
            }
        }
    }

    /* renamed from: a */
    public void m18258a(Context context, String str, InterfaceC2929a interfaceC2929a) {
        m18253a(context, str, true, interfaceC2929a);
    }
}
