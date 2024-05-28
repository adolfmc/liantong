package com.sinovatech.unicom.separatemodule.tongyicaiji;

import android.content.Context;
import android.text.TextUtils;
import com.chinaunicon.jtwifilib.jtcommon.util.JtClient;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ZipManager {
    private static ZipManager instance;
    private Context activity;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface ToZipInterFace {
        void fail(String str);

        void upLoadSucess(String str);
    }

    private ZipManager(Context context) {
        this.activity = context;
    }

    public static ZipManager getInstance(Context context) {
        if (instance == null) {
            instance = new ZipManager(context);
        }
        return instance;
    }

    public void upLoadZipFile(String str, Map<String, String> map, ToZipInterFace toZipInterFace) {
        String str2 = this.activity.getFilesDir() + "/log/";
        String str3 = System.currentTimeMillis() + "log";
        makeFilePath(str2, str3);
        String str4 = str2 + str3;
        String str5 = str + "\r\n";
        try {
            File file = new File(str4);
            if (!file.exists()) {
                MsLogUtil.m7979d("writeTxtToFile", "Create the file:" + str4);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
            randomAccessFile.seek(file.length());
            randomAccessFile.write(str5.getBytes());
            randomAccessFile.close();
            toZip(str4, this.activity.getFilesDir() + "/zip/", System.currentTimeMillis() + JtClient.UXUE_TEMP_FILE_SUFFIX, map, toZipInterFace);
        } catch (Exception e) {
            MsLogUtil.m7977e("writeTxtToFile", "Error on write File:" + e);
        }
    }

    private File makeFilePath(String str, String str2) {
        File file;
        makeRootDirectory(str);
        try {
            file = new File(str + str2);
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
                return file;
            }
        } catch (Exception e2) {
            e = e2;
            file = null;
        }
        return file;
    }

    private void makeRootDirectory(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return;
            }
            file.mkdir();
        } catch (Exception e) {
            MsLogUtil.m7979d("error:", e + "");
        }
    }

    public void toZip(String str, String str2, String str3, Map<String, String> map, ToZipInterFace toZipInterFace) {
        FileOutputStream fileOutputStream;
        ZipOutputStream zipOutputStream;
        try {
            makeFilePath(str2, str3);
            fileOutputStream = new FileOutputStream(new File(str2 + str3));
            try {
                zipOutputStream = new ZipOutputStream(fileOutputStream);
                try {
                    File file = new File(str);
                    compress(file, zipOutputStream, file.getName());
                    zipOutputStream.flush();
                    zipOutputStream.close();
                    fileOutputStream.close();
                    if (new File(str).exists()) {
                        new File(str).delete();
                    }
                    upLoadZip(str2, str3, map, toZipInterFace);
                } catch (Exception unused) {
                    if (zipOutputStream != null) {
                        try {
                            zipOutputStream.close();
                        } catch (IOException unused2) {
                            return;
                        }
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                }
            } catch (Exception unused3) {
                zipOutputStream = null;
            }
        } catch (Exception unused4) {
            fileOutputStream = null;
            zipOutputStream = null;
        }
    }

    private void compress(File file, ZipOutputStream zipOutputStream, String str) throws Exception {
        byte[] bArr = new byte[2048];
        if (file.isFile()) {
            zipOutputStream.putNextEntry(new ZipEntry(str));
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    zipOutputStream.write(bArr, 0, read);
                } else {
                    zipOutputStream.closeEntry();
                    fileInputStream.close();
                    return;
                }
            }
        } else {
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                zipOutputStream.putNextEntry(new ZipEntry(str + "/"));
                zipOutputStream.closeEntry();
                return;
            }
            for (File file2 : listFiles) {
                compress(file2, zipOutputStream, str + "/" + file2.getName());
            }
        }
    }

    private void upLoadZip(final String str, String str2, Map<String, String> map, final ToZipInterFace toZipInterFace) {
        try {
            File file = new File(str + str2);
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            builder.addFormDataPart("reqData", "reqData", RequestBody.create(MediaType.parse("application/octet-stream"), getBytesByFile(file)));
            NBSOkHttp3Instrumentation.init().newCall(new Request.Builder().url(TYCJConfigUtil.getUpLoadUrl()).headers(Headers.m1783of(map)).post(builder.build()).build()).enqueue(new Callback() { // from class: com.sinovatech.unicom.separatemodule.tongyicaiji.ZipManager.1
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    String message = iOException.getMessage();
                    MsLogUtil.m7979d("压缩上传失败", message);
                    ZipManager.this.deleteZip(str);
                    toZipInterFace.fail(message);
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) throws IOException {
                    String string = response.body().string();
                    MsLogUtil.m7979d("压缩上传成功", string);
                    ZipManager.this.deleteZip(str);
                    toZipInterFace.upLoadSucess(string);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteZip(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        File file = new File(str);
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            for (File file2 : listFiles) {
                file2.delete();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] getBytesByFile(java.io.File r6) throws java.io.IOException {
        /*
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r1 = 1000(0x3e8, float:1.401E-42)
            r0.<init>(r1)
            r2 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L28 java.lang.Exception -> L2b
            r3.<init>(r6)     // Catch: java.lang.Throwable -> L28 java.lang.Exception -> L2b
            byte[] r6 = new byte[r1]     // Catch: java.lang.Exception -> L26 java.lang.Throwable -> L51
        Lf:
            int r1 = r3.read(r6)     // Catch: java.lang.Exception -> L26 java.lang.Throwable -> L51
            r4 = -1
            if (r1 == r4) goto L1b
            r4 = 0
            r0.write(r6, r4, r1)     // Catch: java.lang.Exception -> L26 java.lang.Throwable -> L51
            goto Lf
        L1b:
            byte[] r6 = r0.toByteArray()     // Catch: java.lang.Exception -> L26 java.lang.Throwable -> L51
            r3.close()
            r0.close()
            return r6
        L26:
            r6 = move-exception
            goto L2d
        L28:
            r6 = move-exception
            r3 = r2
            goto L52
        L2b:
            r6 = move-exception
            r3 = r2
        L2d:
            java.lang.String r1 = "转换byte"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L51
            r4.<init>()     // Catch: java.lang.Throwable -> L51
            java.lang.String r5 = "getBytesByFile: "
            r4.append(r5)     // Catch: java.lang.Throwable -> L51
            java.lang.String r6 = r6.getMessage()     // Catch: java.lang.Throwable -> L51
            r4.append(r6)     // Catch: java.lang.Throwable -> L51
            java.lang.String r6 = r4.toString()     // Catch: java.lang.Throwable -> L51
            android.util.Log.d(r1, r6)     // Catch: java.lang.Throwable -> L51
            if (r3 == 0) goto L4d
            r3.close()
        L4d:
            r0.close()
            return r2
        L51:
            r6 = move-exception
        L52:
            if (r3 == 0) goto L57
            r3.close()
        L57:
            r0.close()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.tongyicaiji.ZipManager.getBytesByFile(java.io.File):byte[]");
    }

    public String mapToJson(Map<String, String> map) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (map != null && map.size() > 0) {
                for (String str : map.keySet()) {
                    jSONObject.put(str, map.get(str));
                }
            }
            return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
