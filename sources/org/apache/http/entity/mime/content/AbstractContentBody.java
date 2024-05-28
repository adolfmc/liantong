package org.apache.http.entity.mime.content;

import java.nio.charset.Charset;
import org.apache.http.entity.ContentType;
import org.apache.http.util.Args;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractContentBody implements ContentBody {
    private final ContentType contentType;

    public AbstractContentBody(ContentType contentType) {
        Args.notNull(contentType, "Content type");
        this.contentType = contentType;
    }

    @Deprecated
    public AbstractContentBody(String str) {
        this(ContentType.parse(str));
    }

    public ContentType getContentType() {
        return this.contentType;
    }

    @Override // org.apache.http.entity.mime.content.ContentDescriptor
    public String getMimeType() {
        return this.contentType.getMimeType();
    }

    @Override // org.apache.http.entity.mime.content.ContentDescriptor
    public String getMediaType() {
        String mimeType = this.contentType.getMimeType();
        int indexOf = mimeType.indexOf(47);
        return indexOf != -1 ? mimeType.substring(0, indexOf) : mimeType;
    }

    @Override // org.apache.http.entity.mime.content.ContentDescriptor
    public String getSubType() {
        String mimeType = this.contentType.getMimeType();
        int indexOf = mimeType.indexOf(47);
        if (indexOf != -1) {
            return mimeType.substring(indexOf + 1);
        }
        return null;
    }

    @Override // org.apache.http.entity.mime.content.ContentDescriptor
    public String getCharset() {
        Charset charset = this.contentType.getCharset();
        if (charset != null) {
            return charset.name();
        }
        return null;
    }
}
