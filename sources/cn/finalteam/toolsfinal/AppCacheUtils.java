package cn.finalteam.toolsfinal;

import android.content.Context;
import cn.finalteam.toolsfinal.coder.MD5Coder;
import cn.finalteam.toolsfinal.p093io.FileUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class AppCacheUtils {
    public static final String DEFAULT_CACHE_NAME = "appCache";
    private static Map<String, AppCacheUtils> mCacheUtilsMap = new HashMap();
    private File mCacheFile;

    private AppCacheUtils(File file) {
        this.mCacheFile = file;
        FileUtils.mkdirs(file);
    }

    public static AppCacheUtils getInstance(Context context) {
        return getInstance(StorageUtils.getIndividualCacheDirectory(context));
    }

    public static AppCacheUtils getInstance(String str, String str2) {
        return getInstance(new File(str, str2));
    }

    public static AppCacheUtils getInstance(Context context, String str) {
        return getInstance(new File(StorageUtils.getIndividualCacheDirectory(context), str));
    }

    public static AppCacheUtils getInstance(File file) {
        AppCacheUtils appCacheUtils = mCacheUtilsMap.get(file.getAbsolutePath());
        if (appCacheUtils == null) {
            AppCacheUtils appCacheUtils2 = new AppCacheUtils(file);
            mCacheUtilsMap.put(file.getAbsolutePath(), appCacheUtils2);
            return appCacheUtils2;
        }
        return appCacheUtils;
    }

    public void put(String str, int i) {
        put(str, i + "");
    }

    public void put(String str, float f) {
        put(str, f + "");
    }

    public void put(String str, double d) {
        put(str, d + "");
    }

    public void put(String str, boolean z) {
        put(str, z + "");
    }

    public void put(String str, long j) {
        put(str, j + "");
    }

    public void put(String str, String str2) {
        if (StringUtils.isEmpty(str)) {
            return;
        }
        if (StringUtils.isEmpty(str2)) {
            str2 = "";
        }
        BufferedWriter bufferedWriter = null;
        try {
            try {
                try {
                    BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(newFile(str)), 1024);
                    try {
                        bufferedWriter2.write(str2);
                        bufferedWriter2.flush();
                        bufferedWriter2.close();
                    } catch (IOException e) {
                        e = e;
                        bufferedWriter = bufferedWriter2;
                        e.printStackTrace();
                        if (bufferedWriter != null) {
                            bufferedWriter.flush();
                            bufferedWriter.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedWriter = bufferedWriter2;
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.flush();
                                bufferedWriter.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    e = e3;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    public void put(String str, byte[] bArr) {
        if (bArr == null || bArr.length == 0 || StringUtils.isEmpty(str)) {
            return;
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(newFile(str));
                    try {
                        fileOutputStream2.write(bArr);
                        fileOutputStream2.flush();
                        fileOutputStream2.close();
                    } catch (Exception e) {
                        e = e;
                        fileOutputStream = fileOutputStream2;
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public void put(String str, JSONArray jSONArray) {
        if (jSONArray == null) {
            return;
        }
        put(str, !(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray));
    }

    public void put(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        put(str, !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
    }

    public void put(String str, Serializable serializable) {
        if (StringUtils.isEmpty(str) || serializable == null) {
            return;
        }
        ObjectOutputStream objectOutputStream = null;
        try {
            try {
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(byteArrayOutputStream);
                    try {
                        objectOutputStream2.writeObject(serializable);
                        put(str, byteArrayOutputStream.toByteArray());
                        objectOutputStream2.close();
                    } catch (Exception e) {
                        e = e;
                        objectOutputStream = objectOutputStream2;
                        e.printStackTrace();
                        objectOutputStream.close();
                    } catch (Throwable th) {
                        th = th;
                        objectOutputStream = objectOutputStream2;
                        try {
                            objectOutputStream.close();
                        } catch (IOException unused) {
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (IOException unused2) {
        }
    }

    public int getInt(String str, int i) {
        String string = getString(str);
        if (!StringUtils.isEmpty(string)) {
            try {
                return Integer.parseInt(string);
            } catch (Exception unused) {
            }
        }
        return i;
    }

    public float getFloat(String str, float f) {
        String string = getString(str);
        if (!StringUtils.isEmpty(string)) {
            try {
                return Float.parseFloat(string);
            } catch (Exception unused) {
            }
        }
        return f;
    }

    public Double getDouble(String str, double d) {
        String string = getString(str);
        if (!StringUtils.isEmpty(string)) {
            try {
                return Double.valueOf(Double.parseDouble(string));
            } catch (Exception unused) {
            }
        }
        return Double.valueOf(d);
    }

    public long getLong(String str, long j) {
        String string = getString(str);
        if (!StringUtils.isEmpty(string)) {
            try {
                return Long.parseLong(string);
            } catch (Exception unused) {
            }
        }
        return j;
    }

    public boolean getBoolean(String str, boolean z) {
        String string = getString(str);
        if (!StringUtils.isEmpty(string)) {
            try {
                return Boolean.parseBoolean(string);
            } catch (Exception unused) {
            }
        }
        return z;
    }

    public String getString(String str) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        File newFile = newFile(str);
        if (!newFile.exists()) {
            return null;
        }
        String str2 = "";
        try {
            try {
                bufferedReader = new BufferedReader(new FileReader(newFile));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            str2 = str2 + readLine;
                        } else {
                            try {
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (IOException e2) {
                        e = e2;
                        bufferedReader2 = bufferedReader;
                        e.printStackTrace();
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        return str2;
                    } catch (Throwable th) {
                        th = th;
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
                bufferedReader.close();
                return str2;
            } catch (IOException e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.ByteArrayInputStream] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.ByteArrayInputStream] */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.io.ByteArrayInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.ObjectInputStream] */
    /* JADX WARN: Type inference failed for: r1v7 */
    public Object getObject(String str) {
        Throwable th;
        ObjectInputStream objectInputStream;
        ?? isEmpty = StringUtils.isEmpty(str);
        ?? r1 = 0;
        r1 = 0;
        if (isEmpty != 0) {
            return null;
        }
        byte[] binary = getBinary(str);
        try {
            if (binary != null) {
                try {
                    isEmpty = new ByteArrayInputStream(binary);
                    try {
                        objectInputStream = new ObjectInputStream(isEmpty);
                    } catch (Exception e) {
                        e = e;
                        objectInputStream = null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (isEmpty != 0) {
                            try {
                                isEmpty.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        if (r1 != 0) {
                            try {
                                r1.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Exception e4) {
                    e = e4;
                    objectInputStream = null;
                    isEmpty = 0;
                } catch (Throwable th3) {
                    th = th3;
                    isEmpty = 0;
                }
                try {
                    Object readObject = objectInputStream.readObject();
                    try {
                        isEmpty.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                    try {
                        objectInputStream.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                    return readObject;
                } catch (Exception e7) {
                    e = e7;
                    e.printStackTrace();
                    if (isEmpty != 0) {
                        try {
                            isEmpty.close();
                        } catch (IOException e8) {
                            e8.printStackTrace();
                        }
                    }
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e9) {
                            e9.printStackTrace();
                        }
                    }
                    return null;
                }
            }
            return null;
        } catch (Throwable th4) {
            r1 = binary;
            th = th4;
        }
    }

    public byte[] getBinary(String str) {
        byte[] bArr;
        RandomAccessFile randomAccessFile = null;
        r1 = null;
        byte[] bArr2 = null;
        randomAccessFile = null;
        try {
            if (StringUtils.isEmpty(str)) {
                return null;
            }
            try {
                File newFile = newFile(str);
                if (newFile.exists()) {
                    RandomAccessFile randomAccessFile2 = new RandomAccessFile(newFile, "r");
                    try {
                        if (randomAccessFile2.length() != 0) {
                            bArr2 = new byte[(int) randomAccessFile2.length()];
                            randomAccessFile2.read(bArr2);
                        }
                        try {
                            randomAccessFile2.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return bArr2;
                    } catch (Exception e2) {
                        e = e2;
                        byte[] bArr3 = bArr2;
                        randomAccessFile = randomAccessFile2;
                        bArr = bArr3;
                        e.printStackTrace();
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                                return bArr;
                            } catch (IOException e3) {
                                e3.printStackTrace();
                                return bArr;
                            }
                        }
                        return bArr;
                    } catch (Throwable th) {
                        th = th;
                        randomAccessFile = randomAccessFile2;
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
                return null;
            } catch (Exception e5) {
                e = e5;
                bArr = null;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public JSONArray getJSONArray(String str) {
        try {
            return new JSONArray(getString(str));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONObject getJSONObject(String str) {
        try {
            return new JSONObject(getString(str));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private File newFile(String str) {
        return new File(this.mCacheFile, MD5Coder.getMD5Code(str));
    }

    public void remove(String str) {
        try {
            newFile(str).delete();
        } catch (Exception unused) {
        }
    }
}
