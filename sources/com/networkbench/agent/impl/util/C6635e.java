package com.networkbench.agent.impl.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.util.e */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6635e {
    /* renamed from: a */
    public static void m9098a(File file) {
        if (!file.getAbsolutePath().contains("filepath")) {
            C6631a.m9134a(false);
            return;
        }
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                m9098a(file2);
            }
        }
        file.delete();
    }

    /* renamed from: b */
    public static String m9096b(File file) throws IOException {
        FileInputStream fileInputStream;
        StringBuilder sb = new StringBuilder();
        InputStreamReader inputStreamReader = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                InputStreamReader inputStreamReader2 = new InputStreamReader(fileInputStream);
                while (true) {
                    try {
                        int read = inputStreamReader2.read();
                        if (read != -1) {
                            sb.append((char) read);
                        } else {
                            fileInputStream.close();
                            inputStreamReader2.close();
                            return sb.toString();
                        }
                    } catch (Throwable th) {
                        th = th;
                        inputStreamReader = inputStreamReader2;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
        }
    }

    /* renamed from: a */
    public static String m9097a(String str) throws Exception {
        if (str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader(str));
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine != null) {
                            sb.append(readLine);
                        } else {
                            try {
                                bufferedReader2.close();
                                return sb.toString();
                            } catch (IOException e) {
                                throw e;
                            }
                        }
                    } catch (Exception e2) {
                        throw e2;
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e3) {
                                throw e3;
                            }
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e4) {
            throw e4;
        }
    }
}
