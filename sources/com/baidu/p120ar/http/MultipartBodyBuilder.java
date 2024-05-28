package com.baidu.p120ar.http;

import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.http.MultipartBodyBuilder */
/* loaded from: E:\10201592_dexfile_execute.dex */
class MultipartBodyBuilder implements IBodyBuilder {
    private static final String BOUNDARY_DELIMITER = "--";
    private static final String FILE_INFO_FORMAT = "Content-Disposition: form-data; name=\"%s\"; filename=\"%s\"";
    private static final String LINE_END = "\r\n";
    private Charset mCharset;
    private int mTotalSize = 0;
    private String mBoundary = HttpDefaultConfig.MULTIPART_BOUNDARY;
    private StringBuilder mDataBuilder = new StringBuilder();
    private Map<String, byte[]> mFileDatas = new HashMap();
    private Map<String, String> mFilePaths = new HashMap();

    public String getBoundary() {
        return this.mBoundary;
    }

    public void setBoundary(String str) {
        this.mBoundary = str;
    }

    public boolean isEmpty() {
        return this.mDataBuilder.length() == 0 && this.mFileDatas.isEmpty() && this.mFilePaths.isEmpty();
    }

    public void appendPart(String str, String str2) {
        if (TextUtils.isEmpty(str) || str2 == null) {
            return;
        }
        StringBuilder sb = this.mDataBuilder;
        sb.append("--");
        sb.append(this.mBoundary);
        sb.append("\r\n");
        StringBuilder sb2 = this.mDataBuilder;
        sb2.append("Content-Disposition: form-data; name=\"");
        sb2.append(HttpUtil.encodeUrlComponent(str, this.mCharset));
        sb2.append("\"\r\n");
        this.mDataBuilder.append("\r\n");
        this.mDataBuilder.append(HttpUtil.encodeUrlComponent(str2, this.mCharset));
        this.mDataBuilder.append("\r\n");
    }

    public void appendFile(String str, String str2) {
        File file = new File(str2);
        if (file.exists()) {
            this.mTotalSize = (int) (this.mTotalSize + file.length());
        }
        this.mFilePaths.put(str, str2);
    }

    public void appendFile(String str, byte[] bArr) {
        if (bArr != null) {
            this.mTotalSize += bArr.length;
        }
        this.mFileDatas.put(str, bArr);
    }

    @Override // com.baidu.p120ar.http.IBodyBuilder
    public void setCharset(Charset charset) {
        this.mCharset = charset;
    }

    @Override // com.baidu.p120ar.http.IBodyBuilder
    public InputStream[] build() throws FileNotFoundException {
        ArrayList arrayList = new ArrayList();
        if (this.mDataBuilder.length() > 0) {
            arrayList.add(new ByteArrayInputStream(this.mDataBuilder.toString().getBytes(this.mCharset)));
        }
        String str = "";
        if (!this.mFilePaths.isEmpty()) {
            String str2 = "";
            for (Map.Entry<String, String> entry : this.mFilePaths.entrySet()) {
                arrayList.add(buildFileInfoStream(str2, entry.getKey(), new File(entry.getValue()).getName(), HttpUtil.getFileMimeType(entry.getValue()), false));
                arrayList.add(new FileInputStream(entry.getValue()));
                str2 = "\r\n";
            }
            str = str2;
        }
        if (!this.mFileDatas.isEmpty()) {
            String valueOf = String.valueOf(System.currentTimeMillis());
            int i = 1;
            String str3 = str;
            for (Map.Entry<String, byte[]> entry2 : this.mFileDatas.entrySet()) {
                arrayList.add(buildFileInfoStream(str3, entry2.getKey(), valueOf + i + ".jpg", "application/octet-stream", true));
                arrayList.add(new ByteArrayInputStream(entry2.getValue()));
                str3 = "\r\n";
                i++;
            }
            str = str3;
        }
        if (!this.mFilePaths.isEmpty() || !this.mFileDatas.isEmpty()) {
            arrayList.add(new ByteArrayInputStream((str + "--" + this.mBoundary + "--\r\n").getBytes(this.mCharset)));
        }
        return (InputStream[]) arrayList.toArray(new InputStream[arrayList.size()]);
    }

    @Override // com.baidu.p120ar.http.IBodyBuilder
    public String getContentType() {
        return "multipart/form-data;boundary=" + getBoundary();
    }

    @Override // com.baidu.p120ar.http.IBodyBuilder
    public int getSize() {
        return this.mTotalSize;
    }

    private InputStream buildFileInfoStream(String str, String str2, String str3, String str4, boolean z) {
        StringBuilder sb = new StringBuilder();
        String format = String.format("Content-Disposition: form-data; name=\"%s\"; filename=\"%s\"", HttpUtil.encodeUrlComponent(str2, this.mCharset), HttpUtil.encodeUrlComponent(str3, this.mCharset));
        sb.append(str);
        sb.append("--");
        sb.append(this.mBoundary);
        sb.append("\r\n");
        sb.append(format);
        sb.append("\r\n");
        sb.append("Content-Type:");
        sb.append(str4);
        sb.append(";");
        if (z) {
            sb.append("charset=");
            sb.append(this.mCharset.name());
        }
        sb.append("\r\n");
        sb.append("\r\n");
        return new ByteArrayInputStream(sb.toString().getBytes(this.mCharset));
    }
}
