package com.networkbench.agent.impl.harvest;

import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6653u;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HttpMultiPart {
    private static final String LINE_FEED = "\r\n";
    private final String boundary = "newlensboundary";
    private String charset;
    private HttpURLConnection httpConn;
    private OutputStream outputStream;
    private PrintWriter writer;

    public HttpMultiPart(String str, String str2) throws IOException {
        this.charset = str2;
        this.httpConn = (HttpURLConnection) new URL(str).openConnection();
        this.httpConn.setUseCaches(false);
        this.httpConn.setDoOutput(true);
        this.httpConn.setDoInput(true);
        HttpURLConnection httpURLConnection = this.httpConn;
        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + this.boundary);
        this.httpConn.setRequestProperty("X-License-Key", C6638h.m8963w().m9086A());
        this.outputStream = this.httpConn.getOutputStream();
        this.writer = new PrintWriter((Writer) new OutputStreamWriter(this.outputStream, str2), true);
    }

    public void addFormField(String str, String str2) {
        PrintWriter printWriter = this.writer;
        printWriter.append((CharSequence) ("--" + this.boundary)).append((CharSequence) "\r\n");
        PrintWriter printWriter2 = this.writer;
        printWriter2.append((CharSequence) ("Content-Disposition: form-data; name=\"" + str + "\"")).append((CharSequence) "\r\n");
        PrintWriter printWriter3 = this.writer;
        printWriter3.append((CharSequence) ("Content-Type: text/plain; charset=" + this.charset)).append((CharSequence) "\r\n");
        this.writer.append((CharSequence) "\r\n");
        this.writer.append((CharSequence) str2).append((CharSequence) "\r\n");
        this.writer.flush();
    }

    public void addFilePart(String str, File file) throws IOException {
        String name = file.getName();
        PrintWriter printWriter = this.writer;
        printWriter.append((CharSequence) ("--" + this.boundary)).append((CharSequence) "\r\n");
        PrintWriter printWriter2 = this.writer;
        printWriter2.append((CharSequence) ("Content-Disposition: form-data; name=\"" + str + "\"; filename=\"" + name + "\"")).append((CharSequence) "\r\n");
        PrintWriter printWriter3 = this.writer;
        StringBuilder sb = new StringBuilder();
        sb.append("Content-Type: ");
        sb.append(URLConnection.guessContentTypeFromName(name));
        printWriter3.append((CharSequence) sb.toString()).append((CharSequence) "\r\n");
        this.writer.append((CharSequence) "Content-Transfer-Encoding: binary").append((CharSequence) "\r\n");
        this.writer.append((CharSequence) "\r\n");
        this.writer.flush();
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bArr = new byte[4096];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read != -1) {
                this.outputStream.write(bArr, 0, read);
            } else {
                this.outputStream.flush();
                fileInputStream.close();
                this.writer.append((CharSequence) "\r\n");
                this.writer.flush();
                return;
            }
        }
    }

    public void addHeaderField(String str, String str2) {
        PrintWriter printWriter = this.writer;
        printWriter.append((CharSequence) (str + ": " + str2)).append((CharSequence) "\r\n");
        this.writer.flush();
    }

    public String finish() throws IOException {
        this.writer.append((CharSequence) "\r\n").flush();
        PrintWriter printWriter = this.writer;
        printWriter.append((CharSequence) ("--" + this.boundary + "--")).append((CharSequence) "\r\n");
        this.writer.close();
        int responseCode = this.httpConn.getResponseCode();
        if (responseCode == 200) {
            String m8746a = C6653u.m8746a(this.httpConn.getInputStream());
            this.httpConn.disconnect();
            return m8746a;
        }
        throw new IOException("Server returned non-OK status: " + responseCode);
    }
}
